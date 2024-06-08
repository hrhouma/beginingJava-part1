# Tutoriel Pas à Pas pour Débutants: Utilisation de NetBeans pour Créer une Interface Graphique (GUI) avec Swing

## Introduction

### Interface Graphique (GUI)

- **Abstract Window Toolkit (AWT)**: Fournit une interface indépendante des systèmes d'exploitation en utilisant des composants natifs. Chaque composant AWT doit avoir un équivalent (peer component) sur chaque OS, mais leurs comportements peuvent différer d'un OS à l'autre.
- **Swing**: Introduit avec J2SE (Java 1.2), Swing permet de créer des interfaces utilisateur identiques quel que soit l'OS. Swing dessine lui-même les composants et permet de choisir le "look and feel".

### Packages et Classes Importants

- **javax.swing**: Contient les classes Swing.
- **Classes importantes**: `JFrame`, `JButton`, `JLabel`, etc.

### Fonctionnalités et Événements Importants

- **setVisible()**: Rend le frame visible.
- **pack()**: Ajuste la taille de la fenêtre.
- **setDefaultCloseOperation()**: Définit l'opération par défaut lorsque la fenêtre est fermée (DO_NOTHING_ON_CLOSE, HIDE_ON_CLOSE, DISPOSE_ON_CLOSE, EXIT_ON_CLOSE).
- **add() et remove()**: Ajoutent ou suppriment des composants des containers.

## Création de la Fenêtre Principale

### Étape 1: Créer la Classe Principale `SimpleGUI1`

1. **Ouvrir NetBeans**: Lancez NetBeans.
2. **Créer un Nouveau Projet**:
   - Allez dans `File` -> `New Project`.
   - Sélectionnez `Java` -> `Java Application`.
   - Cliquez sur `Next`.
   - Donnez un nom à votre projet (par exemple, `SimpleGUI`).
   - Cliquez sur `Finish`.

3. **Créer la Classe `SimpleGUI1`**:
   - Dans l'onglet `Projects`, faites un clic droit sur le dossier `Source Packages`.
   - Sélectionnez `New` -> `Java Class`.
   - Nommez la classe `SimpleGUI1` et cliquez sur `Finish`.

4. **Ajouter le Code pour la Fenêtre**:
   - Ouvrez la classe `SimpleGUI1`.
   - Copiez et collez le code suivant :

     ```java
     import javax.swing.JFrame;

     public class SimpleGUI1 extends JFrame {
         SimpleGUI1() {
             setSize(400, 400); // La taille de la fenêtre en pixels
             setDefaultCloseOperation(EXIT_ON_CLOSE); // Activer le bouton X pour fermer la fenêtre
             this.setVisible(true); // Activer la fenêtre
         }

         public static void main(String[] args) {
             SimpleGUI1 gui = new SimpleGUI1();
         }
     }
     ```

5. **Exécuter le Projet**:
   - Cliquez sur l'icône `Run` (ou appuyez sur `Shift + F6`) pour exécuter le projet.
   - Une fenêtre vide de 400x400 pixels devrait s'ouvrir.

### Étape 2: Ajouter un Bouton à la Fenêtre

1. **Créer une Nouvelle Classe `SimpleGUI2`**:
   - Créez une nouvelle classe nommée `SimpleGUI2` comme précédemment.

2. **Ajouter le Code pour un Bouton**:
   - Ouvrez la classe `SimpleGUI2`.
   - Copiez et collez le code suivant :

     ```java
     import javax.swing.JButton;
     import javax.swing.JFrame;
     import java.awt.Container;

     public class SimpleGUI2 extends JFrame {
         SimpleGUI2() {
             setSize(400, 400);
             setDefaultCloseOperation(EXIT_ON_CLOSE);
             JButton but1 = new JButton("Bouton");
             Container cp = getContentPane();
             cp.add(but1);
             setVisible(true);
         }

         public static void main(String[] args) {
             SimpleGUI2 gui = new SimpleGUI2();
         }
     }
     ```

3. **Exécuter le Projet**:
   - Exécutez le projet et vérifiez que la fenêtre affiche un bouton avec le texte "Bouton".

### Étape 3: Ajouter un Événement au Bouton

1. **Créer une Nouvelle Classe `SimpleGUI3`**:
   - Créez une nouvelle classe nommée `SimpleGUI3` comme précédemment.

2. **Ajouter le Code pour l'Événement du Bouton**:
   - Ouvrez la classe `SimpleGUI3`.
   - Copiez et collez le code suivant :

     ```java
     import javax.swing.JButton;
     import javax.swing.JFrame;
     import javax.swing.JOptionPane;
     import java.awt.Container;
     import java.awt.FlowLayout;
     import java.awt.event.ActionEvent;
     import java.awt.event.ActionListener;

     public class SimpleGUI3 extends JFrame {
         SimpleGUI3() {
             setSize(400, 400);
             setDefaultCloseOperation(EXIT_ON_CLOSE);
             JButton but1 = new JButton("Bouton");
             Container cp = getContentPane();
             cp.setLayout(new FlowLayout(FlowLayout.CENTER));
             but1.addActionListener(new MyActionListener());
             cp.add(but1);
             setVisible(true);
         }

         public static void main(String[] args) {
             SimpleGUI3 gui = new SimpleGUI3();
         }

         class MyActionListener implements ActionListener {
             public void actionPerformed(ActionEvent ae) {
                 JOptionPane.showMessageDialog(null, "Cliqué!!!");
             }
         }
     }
     ```

3. **Exécuter le Projet**:
   - Exécutez le projet et cliquez sur le bouton pour voir le message "Cliqué!!!".

