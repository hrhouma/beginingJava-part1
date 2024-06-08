# Suite de l'Exercice: Rendre le Projet Plus Motivant et Impressionnant sur Mac

Pour rendre le projet de gestion de contacts plus motivant et impressionnant sur un Mac, nous allons adapter certaines fonctionnalités spécifiques aux macOS, telles que les notifications système, tout en utilisant Java Swing et une base de données SQLite.

## Étape 8: Utiliser une Base de Données pour Stocker les Contacts

### 1. Ajouter des Bibliothèques de Base de Données

- Téléchargez la bibliothèque SQLite JDBC depuis [sqlite-jdbc](https://bitbucket.org/xerial/sqlite-jdbc/downloads/).
- Ajoutez le fichier JAR au projet :
  - Faites un clic droit sur le projet dans NetBeans -> `Properties` -> `Libraries` -> `Add JAR/Folder` et sélectionnez le fichier JAR téléchargé.

### 2. Configurer la Base de Données

- Créez un fichier `contacts.db` dans le répertoire du projet.

### 3. Implémenter la Connexion à la Base de Données

1. **Créer une Classe `DatabaseHelper`** :

   ```java
   import java.sql.Connection;
   import java.sql.DriverManager;
   import java.sql.ResultSet;
   import java.sql.SQLException;
   import java.sql.Statement;

   public class DatabaseHelper {
       private static final String URL = "jdbc:sqlite:contacts.db";

       public static Connection connect() {
           Connection conn = null;
           try {
               conn = DriverManager.getConnection(URL);
           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
           return conn;
       }

       public static void createTable() {
           String sql = "CREATE TABLE IF NOT EXISTS contacts ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                   + "name TEXT NOT NULL,"
                   + "phone TEXT NOT NULL,"
                   + "email TEXT NOT NULL,"
                   + "photo TEXT"
                   + ");";
           try (Connection conn = connect();
                Statement stmt = conn.createStatement()) {
               stmt.execute(sql);
           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
       }
   }
   ```

2. **Modifier la Classe `MainFrame`** :

   ```java
   import java.sql.Connection;
   import java.sql.PreparedStatement;
   import java.sql.ResultSet;
   import java.sql.SQLException;
   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.ActionEvent;
   import java.awt.event.ActionListener;

   public class MainFrame extends JFrame {
       private JTextArea contactDisplay;

       public MainFrame() {
           setTitle("Gestion de Contacts");
           setSize(600, 400);
           setDefaultCloseOperation(EXIT_ON_CLOSE);
           DatabaseHelper.createTable();

           JPanel panel = new JPanel(new GridLayout(6, 2));
           panel.add(new JLabel("Nom:"));
           JTextField nameField = new JTextField();
           panel.add(nameField);

           panel.add(new JLabel("Téléphone:"));
           JTextField phoneField = new JTextField();
           panel.add(phoneField);

           panel.add(new JLabel("Email:"));
           JTextField emailField = new JTextField();
           panel.add(emailField);

           panel.add(new JLabel("Photo:"));
           JTextField photoField = new JTextField();
           panel.add(photoField);

           JButton addButton = new JButton("Ajouter Contact");
           panel.add(addButton);

           JButton clearButton = new JButton("Effacer");
           panel.add(clearButton);

           add(panel, BorderLayout.NORTH);

           contactDisplay = new JTextArea();
           JScrollPane scrollPane = new JScrollPane(contactDisplay);
           add(scrollPane, BorderLayout.CENTER);

           addButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   String name = nameField.getText();
                   String phone = phoneField.getText();
                   String email = emailField.getText();
                   String photo = photoField.getText();
                   addContact(name, phone, email, photo);
                   displayContacts();
                   nameField.setText("");
                   phoneField.setText("");
                   emailField.setText("");
                   photoField.setText("");
               }
           });

           clearButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   nameField.setText("");
                   phoneField.setText("");
                   emailField.setText("");
                   photoField.setText("");
               }
           });

           displayContacts();
       }

       private void addContact(String name, String phone, String email, String photo) {
           String sql = "INSERT INTO contacts(name, phone, email, photo) VALUES(?, ?, ?, ?)";
           try (Connection conn = DatabaseHelper.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
               pstmt.setString(1, name);
               pstmt.setString(2, phone);
               pstmt.setString(3, email);
               pstmt.setString(4, photo);
               pstmt.executeUpdate();
           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
       }

       private void displayContacts() {
           contactDisplay.setText("");
           String sql = "SELECT * FROM contacts";
           try (Connection conn = DatabaseHelper.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
               while (rs.next()) {
                   contactDisplay.append("Nom: " + rs.getString("name") + ", Téléphone: " + rs.getString("phone") + ", Email: " + rs.getString("email") + ", Photo: " + rs.getString("photo") + "\n");
               }
           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
       }

       public static void main(String[] args) {
           MainFrame frame = new MainFrame();
           frame.setVisible(true);
       }
   }
   ```

3. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que vous pouvez ajouter et afficher des contacts stockés dans la base de données SQLite.

## Étape 9: Améliorer l'Interface Utilisateur

1. **Ajouter des Icônes** :
   - Ajoutez des icônes pour les boutons Ajouter, Modifier et Supprimer.

     ```java
     import javax.swing.ImageIcon;

     public class MainFrame extends JFrame {
         // ... (rest of the code remains the same)

         public MainFrame() {
             // ... (rest of the constructor remains the same)

             ImageIcon addIcon = new ImageIcon("path/to/add_icon.png");
             JButton addButton = new JButton("Ajouter Contact", addIcon);
             panel.add(addButton);

             ImageIcon editIcon = new ImageIcon("path/to/edit_icon.png");
             JButton editButton = new JButton("Modifier Contact", editIcon);
             panel.add(editButton);

             ImageIcon deleteIcon = new ImageIcon("path/to/delete_icon.png");
             JButton deleteButton = new JButton("Supprimer Contact", deleteIcon);
             panel.add(deleteButton);

             // ... (rest of the code remains the same)
         }
     }
     ```

2. **Améliorer l'Apparence des Composants** :
   - Utilisez des layouts plus complexes et des bordures pour une meilleure apparence.

     ```java
     import javax.swing.BorderFactory;

     public class MainFrame extends JFrame {
         // ... (rest of the code remains the same)

         public MainFrame() {
             // ... (rest of the constructor remains the same)

             JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
             panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
             add(panel, BorderLayout.NORTH);

             // ... (rest of the code remains the same)
         }
     }
     ```

## Étape 10: Ajouter des Fonctionnalités de Tri et de Recherche Avancée

1. **Ajouter des Options de Tri** :
   - Ajoutez des options pour trier les contacts par nom, téléphone et email.

     ```java
     import javax.swing.JComboBox;

     public class MainFrame extends JFrame {
         // ... (rest of the code remains the same)

         public MainFrame() {
             // ... (rest of the constructor remains the same)

             String[] sortOptions = {"Nom", "Téléphone", "Email"};
             JComboBox<String> sortComboBox = new JComboBox<>(sortOptions);
             panel.add(new JLabel("Trier par:"));
             panel.add(sortComboBox);

             sortComboBox.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                     String selectedOption = (String) sortComboBox.getSelectedItem();
                     displayContacts(selectedOption);
                 }
             });
         }

         private void displayContacts(String sortBy) {
             contactDisplay.setText("");
             String sql = "SELECT * FROM contacts ORDER BY " + sortBy.toLowerCase();
             try (Connection conn = DatabaseHelper.connect();
                  Statement stmt = conn.createStatement();
                  ResultSet rs = stmt.executeQuery(sql)) {
                 while (rs.next()) {
                     contactDisplay.append("Nom: " + rs.getString("name") + ", Téléphone: " + rs.getString("phone") + ", Email: " + rs.getString("email") + ", Photo: " + rs.getString("photo") + "\n");
                 }
             } catch (SQLException e) {
                 System.out.println(e.getMessage());
             }
         }
     }
     ```

2. **Ajouter une Recherche Avancée** :
   - Ajoutez des champs de recherche pour filtrer les contacts par nom, téléphone et email.

     ```java
     public class MainFrame extends JFrame {
         // ... (rest of the code remains the same)

         public MainFrame() {
             // ... (rest of the constructor remains the same)

             JPanel searchPanel =

 new JPanel(new GridLayout(1, 6));
             JTextField nameSearchField = new JTextField();
             JTextField phoneSearchField = new JTextField();
             JTextField emailSearchField = new JTextField();
             JButton searchButton = new JButton("Rechercher");

             searchPanel.add(new JLabel("Nom:"));
             searchPanel.add(nameSearchField);
             searchPanel.add(new JLabel("Téléphone:"));
             searchPanel.add(phoneSearchField);
             searchPanel.add(new JLabel("Email:"));
             searchPanel.add(emailSearchField);
             searchPanel.add(searchButton);

             add(searchPanel, BorderLayout.SOUTH);

             searchButton.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                     String name = nameSearchField.getText().toLowerCase();
                     String phone = phoneSearchField.getText().toLowerCase();
                     String email = emailSearchField.getText().toLowerCase();
                     displayContacts(name, phone, email);
                 }
             });
         }

         private void displayContacts(String name, String phone, String email) {
             contactDisplay.setText("");
             String sql = "SELECT * FROM contacts WHERE name LIKE ? AND phone LIKE ? AND email LIKE ?";
             try (Connection conn = DatabaseHelper.connect();
                  PreparedStatement pstmt = conn.prepareStatement(sql)) {
                 pstmt.setString(1, "%" + name + "%");
                 pstmt.setString(2, "%" + phone + "%");
                 pstmt.setString(3, "%" + email + "%");
                 ResultSet rs = pstmt.executeQuery();
                 while (rs.next()) {
                     contactDisplay.append("Nom: " + rs.getString("name") + ", Téléphone: " + rs.getString("phone") + ", Email: " + rs.getString("email") + ", Photo: " + rs.getString("photo") + "\n");
                 }
             } catch (SQLException e) {
                 System.out.println(e.getMessage());
             }
         }
     }
     ```

## Étape 11: Ajouter des Notifications sur macOS

Pour ajouter des notifications sur macOS, nous utiliserons la commande `osascript` pour afficher les notifications système.

1. **Ajouter une Méthode de Notification** :

   ```java
   import java.io.IOException;

   public class MainFrame extends JFrame {
       // ... (rest of the code remains the same)

       public MainFrame() {
           // ... (rest of the constructor remains the same)

           addButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   // ... (rest of the code remains the same)
                   showNotification("Contact ajouté", "Le contact a été ajouté avec succès!");
               }
           });

           deleteButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   // ... (rest of the code remains the same)
                   showNotification("Contact supprimé", "Le contact a été supprimé avec succès!");
               }
           });
       }

       private void showNotification(String title, String message) {
           try {
               String script = "display notification \"" + message + "\" with title \"" + title + "\"";
               ProcessBuilder processBuilder = new ProcessBuilder("osascript", "-e", script);
               processBuilder.start();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }

       // ... (rest of the methods remain the same)
   }
   ```

## Conclusion

Avec ces étapes supplémentaires adaptées pour macOS, vos étudiants auront transformé leur application de gestion de contacts en une application plus complète, motivante et impressionnante. Ils auront utilisé une base de données pour stocker les contacts, amélioré l'interface utilisateur avec des icônes et des layouts, ajouté des fonctionnalités de tri et de recherche avancée, et implémenté des notifications système spécifiques à macOS. Encouragez-les à continuer à explorer et à ajouter d'autres fonctionnalités pour améliorer leur application encore plus.
