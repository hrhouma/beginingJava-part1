# Suite de l'Exercice: Rendre le Projet Plus Impressionnant (Étape 7)

Dans cette section, nous allons ajouter davantage de fonctionnalités avancées pour rendre l'application de gestion de contacts encore plus impressionnante. Nous allons inclure les fonctionnalités suivantes :

1. Ajout de photos de profil avec prévisualisation.
2. Création de groupes de contacts.
3. Envoi d'emails directement depuis l'application.
4. Implémentation de la pagination pour les listes de contacts.
5. Exportation des contacts en format CSV.

## Étape 7: Ajouter des Photos de Profil avec Prévisualisation

### 1. Ajouter un Champ pour la Photo de Profil

1. **Modifier la Classe `MainFrame` pour inclure un bouton de sélection de photo et une prévisualisation** :

   ```java
   import javax.swing.ImageIcon;
   import javax.swing.JFileChooser;

   public class MainFrame extends JFrame {
       private JTextArea contactDisplay;
       private JLabel photoPreview;

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
           JButton photoButton = new JButton("Choisir Photo");
           panel.add(photoField);
           panel.add(photoButton);

           photoPreview = new JLabel();
           panel.add(photoPreview);

           photoButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   JFileChooser fileChooser = new JFileChooser();
                   if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                       File file = fileChooser.getSelectedFile();
                       photoField.setText(file.getAbsolutePath());
                       ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
                       photoPreview.setIcon(imageIcon);
                   }
               }
           });

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
                   photoPreview.setIcon(null);
               }
           });

           clearButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   nameField.setText("");
                   phoneField.setText("");
                   emailField.setText("");
                   photoField.setText("");
                   photoPreview.setIcon(null);
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
                   if (!rs.getString("photo").isEmpty()) {
                       contactDisplay.append(new ImageIcon(rs.getString("photo")).toString() + "\n");
                   }
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

2. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que vous pouvez ajouter des photos de profil avec une prévisualisation.

## Étape 8: Création de Groupes de Contacts

1. **Ajouter un Champ pour le Groupe de Contacts** :

   ```java
   import javax.swing.JComboBox;

   public class MainFrame extends JFrame {
       private JTextArea contactDisplay;

       public MainFrame() {
           setTitle("Gestion de Contacts");
           setSize(600, 400);
           setDefaultCloseOperation(EXIT_ON_CLOSE);
           DatabaseHelper.createTable();

           JPanel panel = new JPanel(new GridLayout(7, 2));
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
           JButton photoButton = new JButton("Choisir Photo");
           panel.add(photoField);
           panel.add(photoButton);

           String[] groups = {"Famille", "Amis", "Travail"};
           JComboBox<String> groupComboBox = new JComboBox<>(groups);
           panel.add(new JLabel("Groupe:"));
           panel.add(groupComboBox);

           photoButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   JFileChooser fileChooser = new JFileChooser();
                   if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                       File file = fileChooser.getSelectedFile();
                       photoField.setText(file.getAbsolutePath());
                       ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
                       photoPreview.setIcon(imageIcon);
                   }
               }
           });

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
                   String group = (String) groupComboBox.getSelectedItem();
                   addContact(name, phone, email, photo, group);
                   displayContacts();
                   nameField.setText("");
                   phoneField.setText("");
                   emailField.setText("");
                   photoField.setText("");
                   photoPreview.setIcon(null);
               }
           });

           clearButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   nameField.setText("");
                   phoneField.setText("");
                   emailField.setText("");
                   photoField.setText("");
                   photoPreview.setIcon(null);
               }
           });

           displayContacts();
       }

       private void addContact(String name, String phone, String email, String photo, String group) {
           String sql = "INSERT INTO contacts(name, phone, email, photo, group_name) VALUES(?, ?, ?, ?, ?)";
           try (Connection conn = DatabaseHelper.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
               pstmt.setString(1, name);
               pstmt.setString(2, phone);
               pstmt.setString(3, email);
               pstmt.setString(4, photo);
               pstmt.setString(5, group);
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
                   contactDisplay.append("Nom: " + rs.getString("name") + ", Téléphone: " + rs.getString("phone") + ", Email: " + rs.getString("email") + ", Groupe: " + rs.getString("group_name") + ", Photo: " + rs.getString("photo") + "\n");
                   if (!rs.getString("photo").isEmpty()) {
                       contactDisplay.append(new ImageIcon(rs.getString("photo")).toString() + "\n");
                   }
               }
           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
       }

       public static

 void main(String[] args) {
           MainFrame frame = new MainFrame();
           frame.setVisible(true);
       }
   }
   ```

2. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que vous pouvez ajouter des contacts à différents groupes et afficher ces groupes.

## Étape 9: Envoi d'Emails Directement Depuis l'Application

1. **Ajouter une Bibliothèque pour l'Envoi d'Emails** :
   - Téléchargez la bibliothèque JavaMail depuis [JavaMail](https://javaee.github.io/javamail/).
   - Ajoutez le fichier JAR au projet :
     - Faites un clic droit sur le projet dans NetBeans -> `Properties` -> `Libraries` -> `Add JAR/Folder` et sélectionnez le fichier JAR téléchargé.

2. **Ajouter une Méthode pour l'Envoi d'Emails** :

   ```java
   import javax.mail.*;
   import javax.mail.internet.InternetAddress;
   import javax.mail.internet.MimeMessage;
   import java.util.Properties;

   public class MainFrame extends JFrame {
       // ... (rest of the code remains the same)

       public MainFrame() {
           // ... (rest of the constructor remains the same)

           JButton emailButton = new JButton("Envoyer Email");
           panel.add(emailButton);

           emailButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   String email = JOptionPane.showInputDialog("Entrez l'email du destinataire:");
                   String subject = JOptionPane.showInputDialog("Entrez le sujet de l'email:");
                   String message = JOptionPane.showInputDialog("Entrez le message de l'email:");
                   sendEmail(email, subject, message);
               }
           });
       }

       private void sendEmail(String to, String subject, String messageText) {
           String from = "votre.email@exemple.com";
           final String username = "votre.email@exemple.com";
           final String password = "votreMotDePasse";

           String host = "smtp.exemple.com";

           Properties props = new Properties();
           props.put("mail.smtp.auth", "true");
           props.put("mail.smtp.starttls.enable", "true");
           props.put("mail.smtp.host", host);
           props.put("mail.smtp.port", "587");

           Session session = Session.getInstance(props,
             new javax.mail.Authenticator() {
               protected PasswordAuthentication getPasswordAuthentication() {
                   return new PasswordAuthentication(username, password);
               }
             });

           try {
               Message message = new MimeMessage(session);
               message.setFrom(new InternetAddress(from));
               message.setRecipients(Message.RecipientType.TO,
                       InternetAddress.parse(to));
               message.setSubject(subject);
               message.setText(messageText);

               Transport.send(message);

               System.out.println("Email envoyé avec succès!");

           } catch (MessagingException e) {
               throw new RuntimeException(e);
           }
       }

       public static void main(String[] args) {
           MainFrame frame = new MainFrame();
           frame.setVisible(true);
       }
   }
   ```

3. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que vous pouvez envoyer des emails directement depuis l'application.

## Étape 10: Implémentation de la Pagination pour les Listes de Contacts

1. **Ajouter des Boutons de Pagination** :

   ```java
   import javax.swing.JButton;

   public class MainFrame extends JFrame {
       private int currentPage = 1;
       private final int recordsPerPage = 10;

       public MainFrame() {
           // ... (rest of the constructor remains the same)

           JPanel paginationPanel = new JPanel();
           JButton prevButton = new JButton("Précédent");
           JButton nextButton = new JButton("Suivant");
           paginationPanel.add(prevButton);
           paginationPanel.add(nextButton);

           add(paginationPanel, BorderLayout.SOUTH);

           prevButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   if (currentPage > 1) {
                       currentPage--;
                       displayContacts();
                   }
               }
           });

           nextButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   currentPage++;
                   displayContacts();
               }
           });

           displayContacts();
       }

       private void displayContacts() {
           contactDisplay.setText("");
           int start = (currentPage - 1) * recordsPerPage;
           String sql = "SELECT * FROM contacts LIMIT " + start + ", " + recordsPerPage;
           try (Connection conn = DatabaseHelper.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
               while (rs.next()) {
                   contactDisplay.append("Nom: " + rs.getString("name") + ", Téléphone: " + rs.getString("phone") + ", Email: " + rs.getString("email") + ", Groupe: " + rs.getString("group_name") + ", Photo: " + rs.getString("photo") + "\n");
                   if (!rs.getString("photo").isEmpty()) {
                       contactDisplay.append(new ImageIcon(rs.getString("photo")).toString() + "\n");
                   }
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

2. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que vous pouvez naviguer entre les pages de contacts.

## Étape 11: Exportation des Contacts en Format CSV

1. **Ajouter une Méthode pour Exporter en CSV** :

   ```java
   import java.io.FileWriter;
   import java.io.IOException;

   public class MainFrame extends JFrame {
       public MainFrame() {
           // ... (rest of the constructor remains the same)

           JButton exportButton = new JButton("Exporter en CSV");
           panel.add(exportButton);

           exportButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   exportContactsToCSV();
               }
           });
       }

       private void exportContactsToCSV() {
           String sql = "SELECT * FROM contacts";
           try (Connection conn = DatabaseHelper.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                FileWriter csvWriter = new FileWriter("contacts.csv")) {
               csvWriter.append("Nom, Téléphone, Email, Groupe, Photo\n");
               while (rs.next()) {
                   csvWriter.append(rs.getString("name")).append(", ")
                            .append(rs.getString("phone")).append(", ")
                            .append(rs.getString("email")).append(", ")
                            .append(rs.getString("group_name")).append(", ")
                            .append(rs.getString("photo")).append("\n");
               }
               csvWriter.flush();
               JOptionPane.showMessageDialog(this, "Contacts exportés avec succès!");
           } catch (SQLException | IOException e) {
               System.out.println(e.getMessage());
           }
       }

       public static void main(String[] args) {
           MainFrame frame = new MainFrame();
           frame.setVisible(true);
       }
   }
   ```

2. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que vous pouvez exporter les contacts en format CSV.

## Conclusion

Avec ces étapes supplémentaires, vos étudiants auront transformé leur application de gestion de contacts en une application complète, motivante et impressionnante. Ils auront ajouté des fonctionnalités avancées telles que l'ajout de photos de profil avec prévisualisation, la création de groupes de contacts, l'envoi d'emails, la pagination et l'exportation en CSV. Encouragez-les à continuer à explorer et à ajouter d'autres fonctionnalités pour améliorer leur application encore plus.
