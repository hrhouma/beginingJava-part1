# Suite de l'Exercice: Rendre le Projet Encore Plus Impressionnant

Pour rendre le projet de gestion de contacts encore plus impressionnant, nous allons ajouter les fonctionnalités suivantes :

1. **Intégration de l'IA pour les Suggestions de Contacts** : Utiliser l'intelligence artificielle pour suggérer des contacts.
2. **Reconnaissance Vocale pour Ajouter des Contacts** : Utiliser la reconnaissance vocale pour ajouter des contacts.
3. **Interface Responsive** : Adapter l'interface pour qu'elle soit responsive et compatible avec différents écrans.
4. **Intégration avec des API Tierces** : Intégrer des API tierces pour enrichir les données des contacts.
5. **Gestion des Tâches Associées aux Contacts** : Ajouter la gestion des tâches associées aux contacts.
6. **Analyse des Sentiments pour les Notes de Contact** : Analyser les sentiments des notes de contact.
7. **Suivi des Interactions avec les Contacts** : Suivre les interactions avec les contacts.

## Étape 24: Intégration de l'IA pour les Suggestions de Contacts

### 1. Utiliser une Bibliothèque d'IA pour les Suggestions

- Utilisez une bibliothèque de machine learning comme Weka ou TensorFlow pour intégrer des suggestions basées sur l'IA.

### 2. Ajouter une Méthode pour les Suggestions de Contacts

1. **Ajouter une Méthode pour Entraîner le Modèle et Suggérer des Contacts** :

   ```java
   import weka.classifiers.Classifier;
   import weka.classifiers.functions.Logistic;
   import weka.core.Instance;
   import weka.core.Instances;
   import weka.core.converters.ConverterUtils.DataSource;

   public class MainFrame extends JFrame {
       private Classifier classifier;
       private Instances data;

       public MainFrame(Locale locale) {
           // Charger les données et entraîner le modèle
           trainModel();

           // ... (existing code remains the same)

           JButton suggestButton = new JButton("Suggérer des Contacts");
           panel.add(suggestButton);
           suggestButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   suggestContacts();
               }
           });

           // Ajouter les autres champs et boutons comme précédemment

           add(panel, BorderLayout.NORTH);

           contactDisplay = new JTextArea();
           JScrollPane scrollPane = new JScrollPane(contactDisplay);
           add(scrollPane, BorderLayout.CENTER);

           displayContacts();
       }

       private void trainModel() {
           try {
               DataSource source = new DataSource("contacts.arff");
               data = source.getDataSet();
               data.setClassIndex(data.numAttributes() - 1);
               classifier = new Logistic();
               classifier.buildClassifier(data);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }

       private void suggestContacts() {
           try {
               for (int i = 0; i < data.numInstances(); i++) {
                   Instance instance = data.instance(i);
                   double label = classifier.classifyInstance(instance);
                   if (label == 1.0) {
                       contactDisplay.append("Suggéré: " + instance.toString() + "\n");
                   }
               }
           } catch (Exception e) {
               e.printStackTrace();
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
   - Exécutez le projet et vérifiez que les suggestions de contacts fonctionnent correctement.

## Étape 25: Reconnaissance Vocale pour Ajouter des Contacts

### 1. Ajouter une Bibliothèque pour la Reconnaissance Vocale

- Utilisez une bibliothèque comme CMU Sphinx pour la reconnaissance vocale.

### 2. Ajouter une Méthode pour Utiliser la Reconnaissance Vocale

1. **Ajouter une Méthode pour Ajouter des Contacts via la Reconnaissance Vocale** :

   ```java
   import edu.cmu.sphinx.api.Configuration;
   import edu.cmu.sphinx.api.LiveSpeechRecognizer;
   import edu.cmu.sphinx.api.SpeechResult;

   public class MainFrame extends JFrame {
       private LiveSpeechRecognizer recognizer;

       public MainFrame(Locale locale) {
           // Initialiser la reconnaissance vocale
           initializeSpeechRecognizer();

           // ... (existing code remains the same)

           JButton voiceButton = new JButton("Ajouter Contact via Reconnaissance Vocale");
           panel.add(voiceButton);
           voiceButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   addContactByVoice();
               }
           });

           // Ajouter les autres champs et boutons comme précédemment

           add(panel, BorderLayout.NORTH);

           contactDisplay = new JTextArea();
           JScrollPane scrollPane = new JScrollPane(contactDisplay);
           add(scrollPane, BorderLayout.CENTER);

           displayContacts();
       }

       private void initializeSpeechRecognizer() {
           try {
               Configuration configuration = new Configuration();
               configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
               configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
               configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
               recognizer = new LiveSpeechRecognizer(configuration);
               recognizer.startRecognition(true);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }

       private void addContactByVoice() {
           new Thread(() -> {
               SpeechResult result;
               String name = "";
               String phone = "";
               String email = "";

               while ((result = recognizer.getResult()) != null) {
                   String command = result.getHypothesis();

                   if (command.contains("nom")) {
                       name = command.replace("nom", "").trim();
                       contactDisplay.append("Nom: " + name + "\n");
                   } else if (command.contains("téléphone")) {
                       phone = command.replace("téléphone", "").trim();
                       contactDisplay.append("Téléphone: " + phone + "\n");
                   } else if (command.contains("email")) {
                       email = command.replace("email", "").trim();
                       contactDisplay.append("Email: " + email + "\n");
                   }

                   if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
                       addContact(name, phone, email, "", "");
                       displayContacts();
                       break;
                   }
               }
               recognizer.stopRecognition();
           }).start();
       }

       public static void main(String[] args) {
           Locale locale = new Locale("fr", "FR"); // Changez en "en", "US" pour l'anglais
           MainFrame frame = new MainFrame(locale);
           frame.setVisible(true);
       }
   }
   ```

2. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que vous pouvez ajouter des contacts via la reconnaissance vocale.

## Étape 26: Interface Responsive

### 1. Utiliser un Framework UI pour une Interface Responsive

- Utilisez un framework comme JavaFX pour créer une interface utilisateur responsive.

### 2. Adapter l'Interface avec JavaFX

1. **Migrer l'Interface vers JavaFX** :

   ```java
   import javafx.application.Application;
   import javafx.scene.Scene;
   import javafx.scene.control.Button;
   import javafx.scene.control.Label;
   import javafx.scene.control.TextField;
   import javafx.scene.layout.GridPane;
   import javafx.stage.Stage;

   public class MainApp extends Application {
       public static void main(String[] args) {
           launch(args);
       }

       @Override
       public void start(Stage primaryStage) {
           primaryStage.setTitle("Gestion de Contacts");

           GridPane grid = new GridPane();
           grid.setHgap(10);
           grid.setVgap(10);

           Label nameLabel = new Label("Nom:");
           TextField nameField = new TextField();
           grid.add(nameLabel, 0, 0);
           grid.add(nameField, 1, 0);

           Label phoneLabel = new Label("Téléphone:");
           TextField phoneField = new TextField();
           grid.add(phoneLabel, 0, 1);
           grid.add(phoneField, 1, 1);

           Label emailLabel = new Label("Email:");
           TextField emailField = new TextField();
           grid.add(emailLabel, 0, 2);
           grid.add(emailField, 1, 2);

           Button addButton = new Button("Ajouter Contact");
           grid.add(addButton, 1, 3);
           addButton.setOnAction(e -> {
               // Ajouter le contact
           });

           Scene scene = new Scene(grid, 400, 300);
           primaryStage.setScene(scene);
           primaryStage.show();
       }
   }
   ```

2. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que l'interface est responsive.

## Étape 27: Intégration avec des API Tierces

### 1. Utiliser des API pour Enrichir les Données

- Utilisez des API comme Clearbit pour obtenir des informations supplémentaires sur les contacts.

### 2. Ajouter une Méthode pour Utiliser l'API Clearbit

1. **Ajouter une Méthode pour Enrichir les Données des Contacts** :

   ```java
   import java.io.BufferedReader;
   import java.io.InputStreamReader;
   import java.net.HttpURLConnection;
   import java.net.URL;
   import org.json.JSONObject;

   public class

 MainFrame extends JFrame {
       public MainFrame(Locale locale) {
           // ... (existing code remains the same)

           JButton enrichButton = new JButton("Enrichir les Données des Contacts");
           panel.add(enrichButton);
           enrichButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   enrichContactData();
               }
           });

           // Ajouter les autres champs et boutons comme précédemment

           add(panel, BorderLayout.NORTH);

           contactDisplay = new JTextArea();
           JScrollPane scrollPane = new JScrollPane(contactDisplay);
           add(scrollPane, BorderLayout.CENTER);

           displayContacts();
       }

       private void enrichContactData() {
           String sql = "SELECT * FROM contacts";
           try (Connection conn = DatabaseHelper.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
               while (rs.next()) {
                   String email = rs.getString("email");
                   if (!email.isEmpty()) {
                       JSONObject data = getContactDataFromClearbit(email);
                       contactDisplay.append("Enrichi: " + data.toString() + "\n");
                   }
               }
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }

       private JSONObject getContactDataFromClearbit(String email) {
           try {
               String url = "https://person.clearbit.com/v1/people/email/" + email;
               HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
               conn.setRequestProperty("Authorization", "Bearer your_api_key");
               BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
               String inputLine;
               StringBuffer response = new StringBuffer();
               while ((inputLine = in.readLine()) != null) {
                   response.append(inputLine);
               }
               in.close();
               return new JSONObject(response.toString());
           } catch (Exception e) {
               e.printStackTrace();
               return null;
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
   - Exécutez le projet et vérifiez que les données des contacts sont enrichies avec les informations de Clearbit.

## Étape 28: Gestion des Tâches Associées aux Contacts

### 1. Ajouter des Tâches pour les Contacts

1. **Ajouter une Table pour les Tâches** :

   ```java
   public class DatabaseHelper {
       // ... (existing methods remain the same)

       public static void createTasksTable() {
           String sql = "CREATE TABLE IF NOT EXISTS tasks ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                   + "contact_id INTEGER,"
                   + "task TEXT,"
                   + "due_date DATE,"
                   + "completed BOOLEAN,"
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

2. **Modifier la Classe `MainFrame` pour Gérer les Tâches** :

   ```java
   public class MainFrame extends JFrame {
       public MainFrame(Locale locale) {
           DatabaseHelper.createTasksTable();
           // ... (existing code remains the same)

           JButton taskButton = new JButton("Ajouter Tâche");
           panel.add(taskButton);
           taskButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   addTask();
               }
           });

           // Ajouter les autres champs et boutons comme précédemment

           add(panel, BorderLayout.NORTH);

           contactDisplay = new JTextArea();
           JScrollPane scrollPane = new JScrollPane(contactDisplay);
           add(scrollPane, BorderLayout.CENTER);

           displayContacts();
       }

       private void addTask() {
           String contactId = JOptionPane.showInputDialog("Entrez l'ID du contact:");
           String task = JOptionPane.showInputDialog("Entrez la tâche:");
           String dueDate = JOptionPane.showInputDialog("Entrez la date d'échéance (YYYY-MM-DD):");

           String sql = "INSERT INTO tasks(contact_id, task, due_date, completed) VALUES(?, ?, ?, ?)";
           try (Connection conn = DatabaseHelper.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
               pstmt.setInt(1, Integer.parseInt(contactId));
               pstmt.setString(2, task);
               pstmt.setString(3, dueDate);
               pstmt.setBoolean(4, false);
               pstmt.executeUpdate();
           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
       }

       public static void main(String[] args) {
           Locale locale = new Locale("fr", "FR"); // Changez en "en", "US" pour l'anglais
           MainFrame frame = new MainFrame(locale);
           frame.setVisible(true);
       }
   }
   ```

3. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que vous pouvez ajouter et gérer des tâches pour les contacts.

## Étape 29: Analyse des Sentiments pour les Notes de Contact

### 1. Utiliser une Bibliothèque pour l'Analyse des Sentiments

- Utilisez une bibliothèque comme Stanford NLP pour l'analyse des sentiments.

### 2. Ajouter une Méthode pour Analyser les Sentiments des Notes

1. **Ajouter une Méthode pour Analyser les Sentiments des Notes** :

   ```java
   import edu.stanford.nlp.pipeline.*;

   public class MainFrame extends JFrame {
       private StanfordCoreNLP pipeline;

       public MainFrame(Locale locale) {
           // Initialiser le pipeline NLP
           initializeNLPPipeline();

           // ... (existing code remains the same)

           JButton sentimentButton = new JButton("Analyser les Sentiments des Notes");
           panel.add(sentimentButton);
           sentimentButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   analyzeSentiments();
               }
           });

           // Ajouter les autres champs et boutons comme précédemment

           add(panel, BorderLayout.NORTH);

           contactDisplay = new JTextArea();
           JScrollPane scrollPane = new JScrollPane(contactDisplay);
           add(scrollPane, BorderLayout.CENTER);

           displayContacts();
       }

       private void initializeNLPPipeline() {
           Properties props = new Properties();
           props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,sentiment");
           pipeline = new StanfordCoreNLP(props);
       }

       private void analyzeSentiments() {
           String sql = "SELECT * FROM notes";
           try (Connection conn = DatabaseHelper.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
               while (rs.next()) {
                   String note = rs.getString("note");
                   CoreDocument doc = new CoreDocument(note);
                   pipeline.annotate(doc);
                   for (CoreSentence sentence : doc.sentences()) {
                       String sentiment = sentence.sentiment();
                       contactDisplay.append("Note: " + note + " | Sentiment: " + sentiment + "\n");
                   }
               }
           } catch (SQLException e) {
               e.printStackTrace();
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
   - Exécutez le projet et vérifiez que vous pouvez analyser les sentiments des notes de contact.

## Étape 30: Suivi des Interactions avec les Contacts

### 1. Ajouter une Table pour les Interactions

1. **Ajouter une Table pour les Interactions** :

   ```java
   public class DatabaseHelper {
       // ... (existing methods remain the same)

       public static void createInteractionsTable() {
           String sql = "CREATE TABLE IF NOT EXISTS interactions ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                   + "contact_id INTEGER,"
                   + "interaction TEXT,"
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

2. **Modifier la Classe `MainFrame` pour Gérer les Interactions** :

   ```java
   public class MainFrame extends JFrame {
       public MainFrame(Locale locale) {
           DatabaseHelper.createInteractionsTable();
           // ... (existing code remains the same)

           JButton interactionButton = new JButton("Ajouter Interaction");
           panel.add(interactionButton);
           interactionButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                   addInteraction();
               }
           });

           // Ajouter les autres champs et boutons comme précédemment

           add(panel, BorderLayout.NORTH);

           contactDisplay = new JTextArea();
           JScrollPane scrollPane = new JScrollPane(contactDisplay);
           add(scrollPane, BorderLayout.CENTER);

           displayContacts();
       }

       private void addInteraction() {
           String contactId = JOptionPane.showInputDialog("Entrez l'ID du contact:");
           String interaction = JOptionPane.showInputDialog("Entrez l'interaction:");

           String sql = "INSERT

 INTO interactions(contact_id, interaction) VALUES(?, ?)";
           try (Connection conn = DatabaseHelper.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
               pstmt.setInt(1, Integer.parseInt(contactId));
               pstmt.setString(2, interaction);
               pstmt.executeUpdate();
           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
       }

       public static void main(String[] args) {
           Locale locale = new Locale("fr", "FR"); // Changez en "en", "US" pour l'anglais
           MainFrame frame = new MainFrame(locale);
           frame.setVisible(true);
       }
   }
   ```

3. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que vous pouvez ajouter et gérer des interactions avec les contacts.

## Conclusion

Avec ces étapes supplémentaires, vos étudiants auront transformé leur application de gestion de contacts en une application extrêmement complète, motivante et impressionnante. Ils auront ajouté des fonctionnalités telles que l'intégration de l'IA pour les suggestions de contacts, la reconnaissance vocale pour ajouter des contacts, une interface responsive, l'intégration avec des API tierces, la gestion des tâches associées aux contacts, l'analyse des sentiments pour les notes de contact, et le suivi des interactions avec les contacts. Encouragez-les à continuer à explorer et à ajouter d'autres fonctionnalités pour améliorer leur application encore plus.
