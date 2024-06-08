# Exercice Complet avec Swing: Création d'une Application de Gestion de Contacts

## Objectif

Cet exercice vous guidera  à travers la création d'une application de gestion de contacts en utilisant Java Swing et NetBeans. Vous apprendrez à manipuler plusieurs composants Swing et à implémenter diverses fonctionnalités.

## Étape 1: Préparer le Projet

1. **Créer un Nouveau Projet** :
   - Ouvrez NetBeans.
   - Allez dans `File` -> `New Project`.
   - Sélectionnez `Java` -> `Java Application`.
   - Cliquez sur `Next`.
   - Donnez un nom à votre projet (par exemple, `GestionContacts`).
   - Cliquez sur `Finish`.

## Étape 2: Créer la Fenêtre Principale

1. **Créer la Classe Principale `MainFrame`** :
   - Dans l'onglet `Projects`, faites un clic droit sur le dossier `Source Packages`.
   - Sélectionnez `New` -> `Java Class`.
   - Nommez la classe `MainFrame` et cliquez sur `Finish`.

2. **Ajouter le Code pour la Fenêtre** :
   - Ouvrez la classe `MainFrame`.
   - Copiez et collez le code suivant :

     ```java
     import javax.swing.JFrame;
     import javax.swing.JPanel;
     import javax.swing.JButton;
     import javax.swing.JLabel;
     import javax.swing.JTextField;
     import javax.swing.JTextArea;
     import javax.swing.JScrollPane;
     import java.awt.BorderLayout;
     import java.awt.GridLayout;
     import java.awt.event.ActionEvent;
     import java.awt.event.ActionListener;
     import java.util.ArrayList;

     public class MainFrame extends JFrame {
         private ArrayList<Contact> contacts;
         private JTextArea contactDisplay;

         public MainFrame() {
             setTitle("Gestion de Contacts");
             setSize(600, 400);
             setDefaultCloseOperation(EXIT_ON_CLOSE);
             contacts = new ArrayList<>();
             
             JPanel panel = new JPanel(new GridLayout(5, 2));
             
             panel.add(new JLabel("Nom:"));
             JTextField nameField = new JTextField();
             panel.add(nameField);
             
             panel.add(new JLabel("Téléphone:"));
             JTextField phoneField = new JTextField();
             panel.add(phoneField);
             
             panel.add(new JLabel("Email:"));
             JTextField emailField = new JTextField();
             panel.add(emailField);
             
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
                     contacts.add(new Contact(name, phone, email));
                     displayContacts();
                     nameField.setText("");
                     phoneField.setText("");
                     emailField.setText("");
                 }
             });

             clearButton.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                     nameField.setText("");
                     phoneField.setText("");
                     emailField.setText("");
                 }
             });
         }

         private void displayContacts() {
             contactDisplay.setText("");
             for (Contact c : contacts) {
                 contactDisplay.append(c.toString() + "\n");
             }
         }

         public static void main(String[] args) {
             MainFrame frame = new MainFrame();
             frame.setVisible(true);
         }
     }

     class Contact {
         private String name;
         private String phone;
         private String email;

         public Contact(String name, String phone, String email) {
             this.name = name;
             this.phone = phone;
             this.email = email;
         }

         @Override
         public String toString() {
             return "Nom: " + name + ", Téléphone: " + phone + ", Email: " + email;
         }
     }
     ```

3. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que vous pouvez ajouter des contacts et les afficher dans la zone de texte.

## Étape 3: Ajouter des Fonctionnalités Avancées

1. **Ajouter une Barre de Menu** :
   - Modifiez la classe `MainFrame` pour ajouter une barre de menu avec des options pour sauvegarder et charger les contacts à partir d'un fichier.

     ```java
     import javax.swing.JMenuBar;
     import javax.swing.JMenu;
     import javax.swing.JMenuItem;
     import javax.swing.JFileChooser;
     import java.io.File;
     import java.io.FileWriter;
     import java.io.FileReader;
     import java.io.BufferedReader;
     import java.io.BufferedWriter;

     public class MainFrame extends JFrame {
         // ... (rest of the code remains the same)

         public MainFrame() {
             // ... (rest of the constructor remains the same)

             JMenuBar menuBar = new JMenuBar();
             JMenu fileMenu = new JMenu("Fichier");
             JMenuItem saveItem = new JMenuItem("Sauvegarder");
             JMenuItem loadItem = new JMenuItem("Charger");
             
             fileMenu.add(saveItem);
             fileMenu.add(loadItem);
             menuBar.add(fileMenu);
             setJMenuBar(menuBar);

             saveItem.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                     JFileChooser fileChooser = new JFileChooser();
                     if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                         File file = fileChooser.getSelectedFile();
                         saveContactsToFile(file);
                     }
                 }
             });

             loadItem.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                     JFileChooser fileChooser = new JFileChooser();
                     if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                         File file = fileChooser.getSelectedFile();
                         loadContactsFromFile(file);
                     }
                 }
             });
         }

         private void saveContactsToFile(File file) {
             try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                 for (Contact c : contacts) {
                     writer.write(c.toString() + "\n");
                 }
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }

         private void loadContactsFromFile(File file) {
             try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                 String line;
                 contacts.clear();
                 while ((line = reader.readLine()) != null) {
                     String[] parts = line.split(", ");
                     if (parts.length == 3) {
                         String name = parts[0].split(": ")[1];
                         String phone = parts[1].split(": ")[1];
                         String email = parts[2].split(": ")[1];
                         contacts.add(new Contact(name, phone, email));
                     }
                 }
                 displayContacts();
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }
     }
     ```

2. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que vous pouvez sauvegarder et charger des contacts depuis un fichier.

## Étape 4: Ajouter des Fonctionnalités Supplémentaires

1. **Ajouter la Recherche de Contacts** :
   - Ajoutez un champ de texte et un bouton pour rechercher des contacts par nom.

     ```java
     public class MainFrame extends JFrame {
         // ... (rest of the code remains the same)

         public MainFrame() {
             // ... (rest of the constructor remains the same)

             JPanel searchPanel = new JPanel(new GridLayout(1, 2));
             JTextField searchField = new JTextField();
             JButton searchButton = new JButton("Rechercher");

             searchPanel.add(searchField);
             searchPanel.add(searchButton);
             add(searchPanel, BorderLayout.SOUTH);

             searchButton.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                     String searchTerm = searchField.getText().toLowerCase();
                     contactDisplay.setText("");
                     for (Contact c : contacts) {
                         if (c.getName().toLowerCase().contains(searchTerm)) {
                             contactDisplay.append(c.toString() + "\n");
                         }
                     }
                 }
             });
         }

         // ... (rest of the methods remain the same)
     }

     class Contact {
         // ... (rest of the code remains the same)

         public String getName() {
             return name;
         }
     }
     ```

2. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que vous pouvez rechercher des contacts par nom.

## Conclusion

- Avec ce tutoriel, vous avez créée une application de gestion de contacts en utilisant Java Swing et NetBeans. Vous avez utilisé divers composants Swing, implémenté des fonctionnalités telles que l'ajout, la suppression, la sauvegarde, le chargement et la recherche de contacts, et travaillé avec des événements et des fichiers.

- Ce projet peut être étendu avec plus de fonctionnalités, telles que la modification de contacts, l'ajout de photos de profil, etc. Encouragez vos étudiants à expérimenter et à ajouter leurs propres fonctionnalités pour améliorer l'application.
