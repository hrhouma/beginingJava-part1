# Suite de l'Exercice: Ajout de Fonctionnalités Avancées à l'Application de Gestion de Contacts

Dans cette suite, nous allons ajouter d'autres fonctionnalités à notre application de gestion de contacts, telles que la modification de contacts existants, la suppression de contacts et l'ajout d'une photo de profil.

## Étape 5: Modifier un Contact

1. **Ajouter un Bouton de Modification** :
   - Ajoutez un bouton pour modifier un contact sélectionné.

     ```java
     import javax.swing.JOptionPane;

     public class MainFrame extends JFrame {
         // ... (rest of the code remains the same)

         public MainFrame() {
             // ... (rest of the constructor remains the same)

             JButton editButton = new JButton("Modifier Contact");
             panel.add(editButton);

             editButton.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                     String selectedContact = JOptionPane.showInputDialog("Entrez le nom du contact à modifier:");
                     for (Contact c : contacts) {
                         if (c.getName().equalsIgnoreCase(selectedContact)) {
                             String newName = JOptionPane.showInputDialog("Nouveau Nom:", c.getName());
                             String newPhone = JOptionPane.showInputDialog("Nouveau Téléphone:", c.getPhone());
                             String newEmail = JOptionPane.showInputDialog("Nouveau Email:", c.getEmail());
                             c.setName(newName);
                             c.setPhone(newPhone);
                             c.setEmail(newEmail);
                             displayContacts();
                             break;
                         }
                     }
                 }
             });
         }

         // ... (rest of the methods remain the same)
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

         public String getName() {
             return name;
         }

         public void setName(String name) {
             this.name = name;
         }

         public String getPhone() {
             return phone;
         }

         public void setPhone(String phone) {
             this.phone = phone;
         }

         public String getEmail() {
             return email;
         }

         public void setEmail(String email) {
             this.email = email;
         }

         @Override
         public String toString() {
             return "Nom: " + name + ", Téléphone: " + phone + ", Email: " + email;
         }
     }
     ```

2. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que vous pouvez modifier les contacts en entrant leur nom et en fournissant de nouvelles informations.

## Étape 6: Supprimer un Contact

1. **Ajouter un Bouton de Suppression** :
   - Ajoutez un bouton pour supprimer un contact sélectionné.

     ```java
     public class MainFrame extends JFrame {
         // ... (rest of the code remains the same)

         public MainFrame() {
             // ... (rest of the constructor remains the same)

             JButton deleteButton = new JButton("Supprimer Contact");
             panel.add(deleteButton);

             deleteButton.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                     String selectedContact = JOptionPane.showInputDialog("Entrez le nom du contact à supprimer:");
                     contacts.removeIf(c -> c.getName().equalsIgnoreCase(selectedContact));
                     displayContacts();
                 }
             });
         }

         // ... (rest of the methods remain the same)
     }
     ```

2. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que vous pouvez supprimer les contacts en entrant leur nom.

## Étape 7: Ajouter une Photo de Profil

1. **Ajouter un Champ de Sélection de Fichier** :
   - Ajoutez un champ de texte et un bouton pour sélectionner une photo de profil.

     ```java
     import javax.swing.ImageIcon;
     import javax.swing.JFileChooser;

     public class MainFrame extends JFrame {
         // ... (rest of the code remains the same)

         public MainFrame() {
             // ... (rest of the constructor remains the same)

             JLabel photoLabel = new JLabel("Photo:");
             panel.add(photoLabel);
             JTextField photoField = new JTextField();
             panel.add(photoField);
             JButton photoButton = new JButton("Choisir Photo");
             panel.add(photoButton);

             photoButton.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                     JFileChooser fileChooser = new JFileChooser();
                     if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                         File file = fileChooser.getSelectedFile();
                         photoField.setText(file.getAbsolutePath());
                     }
                 }
             });

             addButton.addActionListener(new ActionListener() {
                 public void actionPerformed(ActionEvent e) {
                     String name = nameField.getText();
                     String phone = phoneField.getText();
                     String email = emailField.getText();
                     String photo = photoField.getText();
                     contacts.add(new Contact(name, phone, email, photo));
                     displayContacts();
                     nameField.setText("");
                     phoneField.setText("");
                     emailField.setText("");
                     photoField.setText("");
                 }
             });
         }

         private void displayContacts() {
             contactDisplay.setText("");
             for (Contact c : contacts) {
                 contactDisplay.append(c.toString() + "\n");
                 if (!c.getPhoto().isEmpty()) {
                     contactDisplay.append(new ImageIcon(c.getPhoto()).toString() + "\n");
                 }
             }
         }

         // ... (rest of the methods remain the same)
     }

     class Contact {
         private String name;
         private String phone;
         private String email;
         private String photo;

         public Contact(String name, String phone, String email, String photo) {
             this.name = name;
             this.phone = phone;
             this.email = email;
             this.photo = photo;
         }

         public String getName() {
             return name;
         }

         public void setName(String name) {
             this.name = name;
         }

         public String getPhone() {
             return phone;
         }

         public void setPhone(String phone) {
             this.phone = phone;
         }

         public String getEmail() {
             return email;
         }

         public void setEmail(String email) {
             this.email = email;
         }

         public String getPhoto() {
             return photo;
         }

         public void setPhoto(String photo) {
             this.photo = photo;
         }

         @Override
         public String toString() {
             return "Nom: " + name + ", Téléphone: " + phone + ", Email: " + email + ", Photo: " + photo;
         }
     }
     ```

2. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que vous pouvez ajouter des photos de profil aux contacts.

## Conclusion

Avec ces étapes supplémentaires, vos étudiants auront ajouté des fonctionnalités avancées à leur application de gestion de contacts. Ils auront appris à modifier et supprimer des contacts, ainsi qu'à ajouter des photos de profil. Encouragez-les à explorer davantage Swing et à ajouter d'autres fonctionnalités pour rendre leur application encore plus complète et utile.
