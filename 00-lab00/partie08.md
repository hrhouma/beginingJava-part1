# Suite de l'Exercice: Rendre le Projet Encore Plus Impressionnant

Pour rendre le projet de gestion de contacts encore plus impressionnant, nous allons ajouter des fonctionnalités avancées telles que :

1. Intégration de Google Maps pour localiser les contacts.
2. Implémentation de rappels et notifications de calendrier.
3. Utilisation de graphiques pour visualiser les statistiques des contacts.
4. Synchronisation avec des services en ligne (comme Google Contacts).
5. Ajout d'un thème sombre pour l'interface utilisateur.

## Étape 12: Intégration de Google Maps pour Localiser les Contacts

### 1. Ajouter une Bibliothèque pour Google Maps

- Téléchargez la bibliothèque Google Maps JavaScript API. Vous pouvez obtenir l'API Key depuis [Google Cloud Console](https://console.cloud.google.com/).

### 2. Ajouter une Carte Google Maps dans l'Application

1. **Modifier la Classe `MainFrame` pour inclure un bouton et une fenêtre pour afficher Google Maps** :

   ```java
   import javax.swing.JEditorPane;
   import javax.swing.event.HyperlinkEvent;
   import javax.swing.event.HyperlinkListener;
   import java.awt.Desktop;

   public class MainFrame extends JFrame {
       private JTextArea contactDisplay;

       public MainFrame() {
           setTitle("Gestion de Contacts");
           setSize(800, 600);
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

           panel.add(new JLabel("Adresse:"));
           JTextField addressField = new JTextField();
           panel.add(addressField);

           JButton mapButton = new JButton("Afficher sur Google Maps");
           panel.add(mapButton);

           mapButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   String address = addressField.getText();
                   if (!address.isEmpty()) {
                       showGoogleMaps(address);
                   } else {
                       JOptionPane.showMessageDialog(null, "Veuillez entrer une adresse.");
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

       private void showGoogleMaps(String address) {
           try {
               String url = "https://www.google.com/maps/search/?api=1&query=" + URLEncoder.encode(address, "UTF-8");
               if (Desktop.isDesktopSupported()) {
                   Desktop.getDesktop().browse(new URI(url));
               } else {
                   JOptionPane.showMessageDialog(this, "La fonctionnalité de navigation n'est pas supportée sur cette machine.");
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
       }

       // Ajouter les autres méthodes comme précédemment

       public static void main(String[] args) {
           MainFrame frame = new MainFrame();
           frame.setVisible(true);
       }
   }
   ```

2. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que vous pouvez afficher l'adresse des contacts sur Google Maps.

## Étape 13: Implémentation de Rappels et Notifications de Calendrier

### 1. Ajouter une Bibliothèque pour les Rappels

- Utilisez la bibliothèque `java.util.Timer` et `java.util.TimerTask` pour implémenter les rappels.

### 2. Ajouter une Méthode pour les Rappels

1. **Modifier la Classe `MainFrame` pour inclure un champ de rappel et une méthode de rappel** :

   ```java
   import java.util.Timer;
   import java.util.TimerTask;
   import java.util.Date;

   public class MainFrame extends JFrame {
       private JTextArea contactDisplay;
       private Timer timer;

       public MainFrame() {
           setTitle("Gestion de Contacts");
           setSize(800, 600);
           setDefaultCloseOperation(EXIT_ON_CLOSE);
           DatabaseHelper.createTable();
           timer = new Timer(true);

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

           panel.add(new JLabel("Rappel (en minutes):"));
           JTextField reminderField = new JTextField();
           panel.add(reminderField);

           JButton reminderButton = new JButton("Définir Rappel");
           panel.add(reminderButton);

           reminderButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   String minutesText = reminderField.getText();
                   try {
                       int minutes = Integer.parseInt(minutesText);
                       setReminder(minutes);
                   } catch (NumberFormatException ex) {
                       JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre valide.");
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

       private void setReminder(int minutes) {
           timer.schedule(new TimerTask() {
               @Override
               public void run() {
                   JOptionPane.showMessageDialog(null, "C'est l'heure de votre rappel!");
               }
           }, new Date(System.currentTimeMillis() + minutes * 60 * 1000));
       }

       // Ajouter les autres méthodes comme précédemment

       public static void main(String[] args) {
           MainFrame frame = new MainFrame();
           frame.setVisible(true);
       }
   }
   ```

2. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que vous pouvez définir des rappels et recevoir des notifications.

## Étape 14: Utilisation de Graphiques pour Visualiser les Statistiques des Contacts

### 1. Ajouter une Bibliothèque pour les Graphiques

- Utilisez la bibliothèque `JFreeChart` pour créer des graphiques. Téléchargez la bibliothèque depuis [JFreeChart](https://sourceforge.net/projects/jfreechart/).

### 2. Ajouter un Graphique pour Visualiser les Statistiques

1. **Ajouter un Graphique pour Visualiser le Nombre de Contacts par Groupe** :

   ```java
   import org.jfree.chart.ChartFactory;
   import org.jfree.chart.ChartPanel;
   import org.jfree.chart.JFreeChart;
   import org.jfree.chart.plot.PlotOrientation;
   import org.jfree.data.category.DefaultCategoryDataset;

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

           JButton chartButton = new JButton("Afficher Statistiques");
           panel.add(chartButton);

           chartButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   showStatistics();
               }
           });

           // Ajouter les autres champs et boutons comme précédemment

           add(panel, BorderLayout.NORTH);

           contactDisplay = new JTextArea();
           JScrollPane scrollPane = new JScrollPane(contactDisplay);
           add(scrollPane, BorderLayout.CENTER);

           displayContacts();
       }

       private void showStatistics() {
           DefaultCategoryDataset dataset = new DefaultCategoryDataset();
           String sql = "SELECT group_name, COUNT(*) AS count FROM contacts GROUP BY group_name";
           try (Connection conn = DatabaseHelper.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
               while (rs.next()) {
                   dataset.addValue(rs.getInt("count"), "Contacts", rs.getString("group_name"));
               }
           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }

           JFreeChart barChart = ChartFactory.createBarChart(
                   "Nombre de Contacts par Groupe",
                   "Groupe",
                   "Nombre de Contacts",
                   dataset,
                   PlotOrientation.VERTICAL,
                   true, true, false);

           ChartPanel chartPanel = new ChartPanel(barChart);
           JFrame chartFrame = new JFrame();
           chartFrame.add(chartPanel);
           chartFrame.pack();
           chartFrame.setVisible(true);
       }

       // Ajouter les autres méthodes comme précédemment

       public static void main(String[] args) {
           MainFrame frame = new MainFrame();
           frame.setVisible(true);
       }
   }
   ```

2. **Exéc

uter le Projet** :
   - Exécutez le projet et vérifiez que vous pouvez afficher un graphique montrant le nombre de contacts par groupe.

## Étape 15: Synchronisation avec des Services en Ligne (Google Contacts)

### 1. Ajouter une Bibliothèque pour l'API Google Contacts

- Utilisez la bibliothèque Google People API pour accéder aux contacts Google. Suivez les instructions sur [Google People API](https://developers.google.com/people).

### 2. Ajouter une Méthode pour Synchroniser les Contacts

1. **Configurer l'API Google** :

   - Créez un projet sur [Google Cloud Console](https://console.cloud.google.com/).
   - Activez l'API Google People.
   - Configurez l'authentification OAuth 2.0 et téléchargez le fichier `credentials.json`.

2. **Ajouter une Méthode pour Synchroniser les Contacts** :

   ```java
   import com.google.api.services.people.v1.PeopleService;
   import com.google.api.services.people.v1.model.ListConnectionsResponse;
   import com.google.api.services.people.v1.model.Person;
   import com.google.api.services.people.v1.PeopleServiceScopes;
   import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
   import com.google.api.client.json.jackson2.JacksonFactory;
   import com.google.api.client.auth.oauth2.Credential;
   import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
   import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
   import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
   import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
   import java.io.InputStream;
   import java.io.InputStreamReader;
   import java.util.Collections;

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

           JButton syncButton = new JButton("Synchroniser avec Google Contacts");
           panel.add(syncButton);

           syncButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   try {
                       syncGoogleContacts();
                   } catch (Exception ex) {
                       ex.printStackTrace();
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

       private void syncGoogleContacts() throws Exception {
           InputStream in = getClass().getResourceAsStream("/credentials.json");
           GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), new InputStreamReader(in));

           GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                   GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), clientSecrets, Collections.singleton(PeopleServiceScopes.CONTACTS_READONLY))
                   .setAccessType("offline")
                   .build();

           Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");

           PeopleService service = new PeopleService.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), credential)
                   .setApplicationName("Gestion de Contacts")
                   .build();

           ListConnectionsResponse response = service.people().connections()
                   .list("people/me")
                   .setPersonFields("names,emailAddresses")
                   .execute();

           for (Person person : response.getConnections()) {
               if (person.getNames() != null && !person.getNames().isEmpty()) {
                   String name = person.getNames().get(0).getDisplayName();
                   String email = (person.getEmailAddresses() != null && !person.getEmailAddresses().isEmpty()) ? person.getEmailAddresses().get(0).getValue() : "";
                   addContact(name, "", email, "", "");
               }
           }

           displayContacts();
       }

       // Ajouter les autres méthodes comme précédemment

       public static void main(String[] args) {
           MainFrame frame = new MainFrame();
           frame.setVisible(true);
       }
   }
   ```

2. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que vous pouvez synchroniser les contacts avec Google Contacts.

## Étape 16: Ajout d'un Thème Sombre pour l'Interface Utilisateur

1. **Ajouter une Option pour Activer le Thème Sombre** :

   ```java
   import javax.swing.UIManager;
   import javax.swing.UnsupportedLookAndFeelException;

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

           JButton themeButton = new JButton("Activer Thème Sombre");
           panel.add(themeButton);

           themeButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   setDarkTheme();
               }
           });

           // Ajouter les autres champs et boutons comme précédemment

           add(panel, BorderLayout.NORTH);

           contactDisplay = new JTextArea();
           JScrollPane scrollPane = new JScrollPane(contactDisplay);
           add(scrollPane, BorderLayout.CENTER);

           displayContacts();
       }

       private void setDarkTheme() {
           try {
               UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
               for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                   if ("Nimbus".equals(info.getName())) {
                       UIManager.setLookAndFeel(info.getClassName());
                       UIManager.put("control", new Color(50, 50, 50));
                       UIManager.put("info", new Color(50, 50, 50));
                       UIManager.put("nimbusBase", new Color(18, 30, 49));
                       UIManager.put("nimbusAlertYellow", new Color(248, 187, 0));
                       UIManager.put("nimbusDisabledText", new Color(142, 143, 145));
                       UIManager.put("nimbusFocus", new Color(115, 164, 209));
                       UIManager.put("nimbusGreen", new Color(176, 179, 50));
                       UIManager.put("nimbusInfoBlue", new Color(66, 139, 221));
                       UIManager.put("nimbusLightBackground", new Color(18, 30, 49));
                       UIManager.put("nimbusOrange", new Color(191, 98, 4));
                       UIManager.put("nimbusRed", new Color(169, 46, 34));
                       UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
                       UIManager.put("nimbusSelectionBackground", new Color(104, 93, 156));
                       UIManager.put("text", new Color(230, 230, 230));
                       SwingUtilities.updateComponentTreeUI(this);
                       break;
                   }
               }
           } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
               ex.printStackTrace();
           }
       }

       // Ajouter les autres méthodes comme précédemment

       public static void main(String[] args) {
           MainFrame frame = new MainFrame();
           frame.setVisible(true);
       }
   }
   ```

2. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que vous pouvez activer le thème sombre pour l'interface utilisateur.

## Conclusion

Avec ces étapes supplémentaires, vos étudiants auront transformé leur application de gestion de contacts en une application encore plus complète, motivante et impressionnante. Ils auront intégré Google Maps, ajouté des rappels et notifications, utilisé des graphiques pour visualiser des statistiques, synchronisé avec Google Contacts et ajouté un thème sombre. Encouragez-les à continuer à explorer et à ajouter d'autres fonctionnalités pour améliorer leur application encore plus.
