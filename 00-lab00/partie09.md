# Suite de l'Exercice: Rendre le Projet Encore Plus Impressionnant

Pour rendre l'application de gestion de contacts encore plus impressionnante, nous allons ajouter les fonctionnalités suivantes :

1. **Authentification Utilisateur** : Ajouter un système de login pour différents utilisateurs.
2. **Historique des Modifications** : Suivre et afficher l'historique des modifications des contacts.
3. **Notifications par Email** : Envoyer des notifications par email pour les rappels de contacts.
4. **Support Multilingue** : Ajouter des options pour changer la langue de l'application.
5. **Sauvegarde et Restauration** : Ajouter des fonctionnalités pour sauvegarder et restaurer la base de données.
6. **Intégration avec les Réseaux Sociaux** : Ajouter des fonctionnalités pour partager des contacts sur les réseaux sociaux.
7. **OCR pour Importer des Contacts** : Utiliser l'OCR pour scanner et importer des cartes de visite en tant que contacts.

## Étape 17: Authentification Utilisateur

### 1. Ajouter un Système de Login

1. **Créer une Classe `LoginFrame` pour l'Authentification** :

   ```java
   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.ActionEvent;
   import java.awt.event.ActionListener;
   import java.sql.*;

   public class LoginFrame extends JFrame {
       public LoginFrame() {
           setTitle("Login");
           setSize(300, 150);
           setDefaultCloseOperation(EXIT_ON_CLOSE);
           setLayout(new GridLayout(3, 2));

           JLabel userLabel = new JLabel("Nom d'utilisateur:");
           JTextField userField = new JTextField();
           JLabel passLabel = new JLabel("Mot de passe:");
           JPasswordField passField = new JPasswordField();
           JButton loginButton = new JButton("Se Connecter");

           add(userLabel);
           add(userField);
           add(passLabel);
           add(passField);
           add(new JLabel());
           add(loginButton);

           loginButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   String username = userField.getText();
                   String password = new String(passField.getPassword());
                   if (authenticateUser(username, password)) {
                       MainFrame mainFrame = new MainFrame();
                       mainFrame.setVisible(true);
                       dispose();
                   } else {
                       JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou mot de passe incorrect.");
                   }
               }
           });
       }

       private boolean authenticateUser(String username, String password) {
           String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
           try (Connection conn = DatabaseHelper.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
               pstmt.setString(1, username);
               pstmt.setString(2, password);
               ResultSet rs = pstmt.executeQuery();
               return rs.next();
           } catch (SQLException e) {
               e.printStackTrace();
               return false;
           }
       }

       public static void main(String[] args) {
           LoginFrame loginFrame = new LoginFrame();
           loginFrame.setVisible(true);
       }
   }
   ```

2. **Créer une Table pour les Utilisateurs dans la Base de Données** :

   ```java
   public class DatabaseHelper {
       // ... (existing methods remain the same)

       public static void createUsersTable() {
           String sql = "CREATE TABLE IF NOT EXISTS users ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                   + "username TEXT NOT NULL,"
                   + "password TEXT NOT NULL"
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

3. **Modifier la Classe `MainFrame` pour Vérifier l'Authentification** :

   ```java
   public class MainFrame extends JFrame {
       public MainFrame() {
           DatabaseHelper.createUsersTable();
           // ... (existing code remains the same)
       }

       public static void main(String[] args) {
           LoginFrame loginFrame = new LoginFrame();
           loginFrame.setVisible(true);
       }
   }
   ```

4. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que l'authentification fonctionne correctement.

## Étape 18: Historique des Modifications

### 1. Ajouter une Table pour l'Historique des Modifications

1. **Créer une Table pour l'Historique des Modifications** :

   ```java
   public class DatabaseHelper {
       // ... (existing methods remain the same)

       public static void createHistoryTable() {
           String sql = "CREATE TABLE IF NOT EXISTS history ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                   + "contact_id INTEGER,"
                   + "action TEXT,"
                   + "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,"
                   + "FOREIGN KEY(contact_id) REFERENCES contacts(id)"
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

2. **Modifier la Classe `MainFrame` pour Enregistrer l'Historique des Modifications** :

   ```java
   public class MainFrame extends JFrame {
       public MainFrame() {
           DatabaseHelper.createHistoryTable();
           // ... (existing code remains the same)
       }

       private void addContact(String name, String phone, String email, String photo, String group) {
           String sql = "INSERT INTO contacts(name, phone, email, photo, group_name) VALUES(?, ?, ?, ?, ?)";
           try (Connection conn = DatabaseHelper.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
               pstmt.setString(1, name);
               pstmt.setString(2, phone);
               pstmt.setString(3, email);
               pstmt.setString(4, photo);
               pstmt.setString(5, group);
               pstmt.executeUpdate();
               ResultSet rs = pstmt.getGeneratedKeys();
               if (rs.next()) {
                   int contactId = rs.getInt(1);
                   logHistory(contactId, "Ajouté");
               }
           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
       }

       private void logHistory(int contactId, String action) {
           String sql = "INSERT INTO history(contact_id, action) VALUES(?, ?)";
           try (Connection conn = DatabaseHelper.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
               pstmt.setInt(1, contactId);
               pstmt.setString(2, action);
               pstmt.executeUpdate();
           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
       }

       private void displayHistory() {
           JTextArea historyDisplay = new JTextArea();
           JScrollPane scrollPane = new JScrollPane(historyDisplay);
           JFrame historyFrame = new JFrame("Historique des Modifications");
           historyFrame.add(scrollPane);
           historyFrame.setSize(400, 300);
           historyFrame.setVisible(true);

           String sql = "SELECT h.action, h.timestamp, c.name FROM history h JOIN contacts c ON h.contact_id = c.id ORDER BY h.timestamp DESC";
           try (Connection conn = DatabaseHelper.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
               while (rs.next()) {
                   String log = rs.getString("timestamp") + " - " + rs.getString("name") + ": " + rs.getString("action");
                   historyDisplay.append(log + "\n");
               }
           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
       }

       // Ajouter un bouton pour afficher l'historique
       public MainFrame() {
           // ... (existing code remains the same)

           JButton historyButton = new JButton("Afficher l'Historique");
           panel.add(historyButton);
           historyButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   displayHistory();
               }
           });

           // ... (existing code remains the same)
       }
   }
   ```

3. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que l'historique des modifications est enregistré et affiché correctement.

## Étape 19: Notifications par Email

### 1. Modifier la Méthode de Notification par Email

1. **Ajouter une Méthode pour Envoyer des Notifications par Email** :

   ```java
   import javax.mail.*;
   import javax.mail.internet.InternetAddress;
   import javax.mail.internet.MimeMessage;
   import java.util.Properties;

   public class MainFrame extends JFrame {
       private JTextArea contactDisplay;

       public MainFrame() {
           setTitle("Gestion de Contacts");
           setSize(800, 600);
           setDefaultCloseOperation(EXIT_ON_CLOSE);
           DatabaseHelper.createTable();

           JPanel panel = new JPanel(new GridLayout(8, 2));
           panel.add(new JLabel("Nom:"));
           JTextField nameField = new JTextField();
           panel.add(nameField);

           panel.add(new JLabel("Téléphone:"));
           JTextField phoneField = new JTextField();
           panel.add(phoneField);

           panel.add(new JLabel("Email:"));
           JTextField emailField = new JTextField();
           panel.add(emailField);

           panel.add(new JLabel("Adresse:"));
           JTextField addressField = new JTextField();
           panel.add(addressField);

           JButton emailButton = new JButton("Envoyer Notification par Email");
           panel.add(emailButton);

           emailButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e

) {
                   String email = emailField.getText();
                   String subject = "Notification de Gestion de Contacts";
                   String message = "Vous avez un nouveau rappel de contact.";
                   sendEmail(email, subject, message);
               }
           });

           // Ajouter les autres champs et boutons comme précédemment

           add(panel, BorderLayout.NORTH);

           contactDisplay = new JTextArea();
           JScrollPane scrollPane = new JScrollPane(contactDisplay);
           add(scrollPane, BorderLayout.CENTER);

           displayContacts();
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

2. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que les notifications par email fonctionnent correctement.

## Étape 20: Support Multilingue

### 1. Ajouter des Fichiers de Propriétés pour le Multilingue

1. **Créer des Fichiers de Propriétés pour les Langues** :
   - Créez deux fichiers de propriétés `messages_en.properties` et `messages_fr.properties` dans le répertoire `src`.

   ```properties
   // messages_en.properties
   title=Contact Management
   name=Name
   phone=Phone
   email=Email
   address=Address
   addContact=Add Contact
   clear=Clear
   showHistory=Show History
   sendNotification=Send Email Notification
   ```

   ```properties
   // messages_fr.properties
   title=Gestion de Contacts
   name=Nom
   phone=Téléphone
   email=Email
   address=Adresse
   addContact=Ajouter Contact
   clear=Effacer
   showHistory=Afficher l'Historique
   sendNotification=Envoyer Notification par Email
   ```

2. **Modifier la Classe `MainFrame` pour Utiliser les Ressources de Langue** :

   ```java
   import java.util.Locale;
   import java.util.ResourceBundle;

   public class MainFrame extends JFrame {
       private JTextArea contactDisplay;
       private ResourceBundle messages;

       public MainFrame(Locale locale) {
           messages = ResourceBundle.getBundle("messages", locale);
           setTitle(messages.getString("title"));
           setSize(800, 600);
           setDefaultCloseOperation(EXIT_ON_CLOSE);
           DatabaseHelper.createTable();

           JPanel panel = new JPanel(new GridLayout(8, 2));
           panel.add(new JLabel(messages.getString("name") + ":"));
           JTextField nameField = new JTextField();
           panel.add(nameField);

           panel.add(new JLabel(messages.getString("phone") + ":"));
           JTextField phoneField = new JTextField();
           panel.add(phoneField);

           panel.add(new JLabel(messages.getString("email") + ":"));
           JTextField emailField = new JTextField();
           panel.add(emailField);

           panel.add(new JLabel(messages.getString("address") + ":"));
           JTextField addressField = new JTextField();
           panel.add(addressField);

           JButton addButton = new JButton(messages.getString("addContact"));
           panel.add(addButton);

           JButton clearButton = new JButton(messages.getString("clear"));
           panel.add(clearButton);

           JButton historyButton = new JButton(messages.getString("showHistory"));
           panel.add(historyButton);

           JButton emailButton = new JButton(messages.getString("sendNotification"));
           panel.add(emailButton);

           // Ajouter les listeners pour les boutons comme précédemment

           add(panel, BorderLayout.NORTH);

           contactDisplay = new JTextArea();
           JScrollPane scrollPane = new JScrollPane(contactDisplay);
           add(scrollPane, BorderLayout.CENTER);

           displayContacts();
       }

       public static void main(String[] args) {
           Locale locale = new Locale("fr", "FR"); // Changez en "en", "US" pour l'anglais
           MainFrame frame = new MainFrame(locale);
           frame.setVisible(true);
       }
   }
   ```

3. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que l'application utilise la langue correcte en fonction de la locale définie.

## Étape 21: Sauvegarde et Restauration

### 1. Ajouter des Méthodes pour Sauvegarder et Restaurer la Base de Données

1. **Modifier la Classe `DatabaseHelper` pour Sauvegarder et Restaurer la Base de Données** :

   ```java
   import java.io.File;
   import java.io.FileInputStream;
   import java.io.FileOutputStream;
   import java.nio.channels.FileChannel;

   public class DatabaseHelper {
       // ... (existing methods remain the same)

       public static void backupDatabase(String backupPath) {
           try {
               File source = new File("contacts.db");
               File destination = new File(backupPath);
               FileChannel src = new FileInputStream(source).getChannel();
               FileChannel dest = new FileOutputStream(destination).getChannel();
               dest.transferFrom(src, 0, src.size());
               src.close();
               dest.close();
           } catch (Exception e) {
               e.printStackTrace();
           }
       }

       public static void restoreDatabase(String backupPath) {
           try {
               File source = new File(backupPath);
               File destination = new File("contacts.db");
               FileChannel src = new FileInputStream(source).getChannel();
               FileChannel dest = new FileOutputStream(destination).getChannel();
               dest.transferFrom(src, 0, src.size());
               src.close();
               dest.close();
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
   }
   ```

2. **Modifier la Classe `MainFrame` pour Ajouter des Boutons de Sauvegarde et de Restauration** :

   ```java
   public class MainFrame extends JFrame {
       public MainFrame(Locale locale) {
           // ... (existing code remains the same)

           JButton backupButton = new JButton("Sauvegarder la Base de Données");
           panel.add(backupButton);
           backupButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   JFileChooser fileChooser = new JFileChooser();
                   if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                       File file = fileChooser.getSelectedFile();
                       DatabaseHelper.backupDatabase(file.getAbsolutePath());
                       JOptionPane.showMessageDialog(null, "Sauvegarde réussie!");
                   }
               }
           });

           JButton restoreButton = new JButton("Restaurer la Base de Données");
           panel.add(restoreButton);
           restoreButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   JFileChooser fileChooser = new JFileChooser();
                   if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                       File file = fileChooser.getSelectedFile();
                       DatabaseHelper.restoreDatabase(file.getAbsolutePath());
                       JOptionPane.showMessageDialog(null, "Restauration réussie!");
                       displayContacts();
                   }
               }
           });

           // Ajouter les autres champs et boutons comme précédemment

           add(panel, BorderLayout.NORTH);

           contactDisplay = new JTextArea();
           JScrollPane scrollPane = new JScrollPane(contactDisplay);
           add(scrollPane, BorderLayout.CENTER);

           displayContacts();
       }

       public static void main(String[] args) {
           Locale locale = new Locale("fr", "FR"); // Changez en "en", "US" pour l'anglais
           MainFrame frame = new MainFrame(locale);
           frame.setVisible(true);
       }
   }
   ```

3. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que les fonctionnalités de sauvegarde et de restauration fonctionnent correctement.

## Étape 22: Intégration avec les Réseaux Sociaux

### 1. Ajouter une Bibliothèque pour Intégrer les Réseaux Sociaux

- Utilisez des bibliothèques comme Facebook SDK ou Twitter4J pour intégrer les réseaux sociaux.

### 2. Ajouter des Boutons pour Partager des Contacts

1. **Ajouter des Boutons pour Partager sur Facebook et Twitter** :

   ```java
   import twitter4j.Twitter;
   import twitter4j.TwitterFactory;
   import twitter4j.conf.ConfigurationBuilder;

   public class MainFrame extends JFrame {
       private Twitter twitter;

       public MainFrame(Locale locale) {
           // Configurez l'authentification Twitter
           ConfigurationBuilder cb = new ConfigurationBuilder();
           cb.setDebugEnabled(true)
               .setOAuthConsumerKey("yourConsumerKey")
               .setOAuthConsumerSecret("yourConsumerSecret")
               .setOAuthAccessToken("your

AccessToken")
               .setOAuthAccessTokenSecret("yourAccessTokenSecret");
           TwitterFactory tf = new TwitterFactory(cb.build());
           twitter = tf.getInstance();

           // ... (existing code remains the same)

           JButton shareTwitterButton = new JButton("Partager sur Twitter");
           panel.add(shareTwitterButton);
           shareTwitterButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   String message = "Contact: " + nameField.getText() + ", Téléphone: " + phoneField.getText() + ", Email: " + emailField.getText();
                   shareOnTwitter(message);
               }
           });

           // Ajouter les autres champs et boutons comme précédemment

           add(panel, BorderLayout.NORTH);

           contactDisplay = new JTextArea();
           JScrollPane scrollPane = new JScrollPane(contactDisplay);
           add(scrollPane, BorderLayout.CENTER);

           displayContacts();
       }

       private void shareOnTwitter(String message) {
           try {
               twitter.updateStatus(message);
               JOptionPane.showMessageDialog(this, "Partage réussi sur Twitter!");
           } catch (Exception e) {
               e.printStackTrace();
               JOptionPane.showMessageDialog(this, "Échec du partage sur Twitter.");
           }
       }

       public static void main(String[] args) {
           Locale locale = new Locale("fr", "FR"); // Changez en "en", "US" pour l'anglais
           MainFrame frame = new MainFrame(locale);
           frame.setVisible(true);
       }
   }
   ```

2. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que vous pouvez partager des contacts sur Twitter.

## Étape 23: OCR pour Importer des Contacts

### 1. Ajouter une Bibliothèque pour l'OCR

- Utilisez la bibliothèque Tesseract pour l'OCR. Téléchargez et configurez Tesseract depuis [Tesseract](https://github.com/tesseract-ocr/tesseract).

### 2. Ajouter une Méthode pour Scanner et Importer des Cartes de Visite

1. **Ajouter une Méthode pour Scanner et Importer des Contacts** :

   ```java
   import net.sourceforge.tess4j.Tesseract;
   import net.sourceforge.tess4j.TesseractException;

   public class MainFrame extends JFrame {
       public MainFrame(Locale locale) {
           // ... (existing code remains the same)

           JButton scanButton = new JButton("Scanner une Carte de Visite");
           panel.add(scanButton);
           scanButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   JFileChooser fileChooser = new JFileChooser();
                   if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                       File file = fileChooser.getSelectedFile();
                       importContactFromBusinessCard(file.getAbsolutePath());
                   }
               }
           });

           // Ajouter les autres champs et boutons comme précédemment

           add(panel, BorderLayout.NORTH);

           contactDisplay = new JTextArea();
           JScrollPane scrollPane = new JScrollPane(contactDisplay);
           add(scrollPane, BorderLayout.CENTER);

           displayContacts();
       }

       private void importContactFromBusinessCard(String imagePath) {
           Tesseract tesseract = new Tesseract();
           tesseract.setDatapath("/path/to/tessdata"); // chemin vers les fichiers de données Tesseract
           try {
               String text = tesseract.doOCR(new File(imagePath));
               parseAndAddContact(text);
           } catch (TesseractException e) {
               e.printStackTrace();
           }
       }

       private void parseAndAddContact(String text) {
           // Implémentez le parsing du texte OCR pour extraire les informations de contact
           String[] lines = text.split("\n");
           String name = lines[0]; // Supposons que le nom est sur la première ligne
           String phone = ""; // Extrayez le téléphone
           String email = ""; // Extrayez l'email

           addContact(name, phone, email, "", "");
           displayContacts();
       }

       public static void main(String[] args) {
           Locale locale = new Locale("fr", "FR"); // Changez en "en", "US" pour l'anglais
           MainFrame frame = new MainFrame(locale);
           frame.setVisible(true);
       }
   }
   ```

2. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que vous pouvez scanner des cartes de visite et importer les contacts en utilisant l'OCR.

## Conclusion

Avec ces étapes supplémentaires, vos étudiants auront transformé leur application de gestion de contacts en une application extrêmement complète, motivante et impressionnante. Ils auront ajouté des fonctionnalités telles que l'authentification utilisateur, l'historique des modifications, les notifications par email, le support multilingue, la sauvegarde et la restauration de la base de données, l'intégration avec les réseaux sociaux et l'importation de contacts en utilisant l'OCR. Encouragez-les à continuer à explorer et à ajouter d'autres fonctionnalités pour améliorer leur application encore plus.
