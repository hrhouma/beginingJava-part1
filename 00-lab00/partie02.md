# Tutoriel Pas à Pas pour Débutants: Utilisation de NetBeans pour Créer une Interface Graphique (GUI) avec Swing

## Introduction

- Les interfaces graphiques en Java peuvent être créées en utilisant la bibliothèque Swing, qui offre une apparence et un comportement identiques sur tous les systèmes d'exploitation. 
- Ce tutoriel vous guidera pas à pas pour créer une interface graphique simple en utilisant NetBeans.

### Concepts Clés

- **AWT (Abstract Window Toolkit)** : Interface indépendante des systèmes d'exploitation, mais dépendante des composants natifs de chaque OS.
- **Swing** : Bibliothèque introduite avec J2SE (Java 1.2) pour créer des interfaces utilisateur indépendantes du système d'exploitation.

### Packages et Classes Importants

- **javax.swing** : Contient les classes Swing.
- **Classes importantes** : `JFrame`, `JButton`, `JLabel`, etc.

## Étape 1: Installation de NetBeans

1. **Télécharger NetBeans** : [Téléchargez NetBeans](https://netbeans.apache.org/download/index.html) pour votre système d'exploitation.
2. **Installer NetBeans** : Suivez les instructions d'installation pour installer NetBeans sur votre machine.

## Étape 2: Création d'un Nouveau Projet

1. **Ouvrir NetBeans** : Lancez NetBeans.
2. **Créer un Nouveau Projet** :
   - Allez dans `File` -> `New Project`.
   - Sélectionnez `Java` dans la catégorie et `Java Application` dans les projets.
   - Cliquez sur `Next`.
   - Donnez un nom à votre projet (par exemple, `SimpleGUI`).
   - Cliquez sur `Finish`.

## Étape 3: Configuration de la Fenêtre Principale

1. **Créer la Classe Principale** :
   - Dans l'onglet `Projects`, faites un clic droit sur le dossier `Source Packages`.
   - Sélectionnez `New` -> `Java Class`.
   - Nommez la classe `SimpleGUI1` et cliquez sur `Finish`.

2. **Ajouter le Code pour la Fenêtre** :
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

3. **Exécuter le Projet** :
   - Cliquez sur l'icône `Run` (ou appuyez sur `Shift + F6`) pour exécuter le projet.
   - Une fenêtre vide de 400x400 pixels devrait s'ouvrir.

## Étape 4: Ajouter un Bouton à la Fenêtre

1. **Créer une Nouvelle Classe** :
   - Créez une nouvelle classe nommée `SimpleGUI2` comme précédemment.

2. **Ajouter le Code pour un Bouton** :
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

3. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que la fenêtre affiche un bouton avec le texte "Bouton".

## Étape 5: Ajouter Deux Boutons à la Fenêtre

1. **Créer une Nouvelle Classe** :
   - Créez une nouvelle classe nommée `SimpleGUI3`.

2. **Ajouter le Code pour Deux Boutons** :
   - Ouvrez la classe `SimpleGUI3`.
   - Copiez et collez le code suivant :

     ```java
     import javax.swing.JButton;
     import javax.swing.JFrame;
     import java.awt.Container;
     import java.awt.FlowLayout;

     public class SimpleGUI3 extends JFrame {
         SimpleGUI3() {
             setSize(400, 400);
             setDefaultCloseOperation(EXIT_ON_CLOSE);
             JButton but1 = new JButton("Bouton1");
             JButton but2 = new JButton("Bouton2");
             Container cp = getContentPane();
             cp.setLayout(new FlowLayout(FlowLayout.CENTER));
             cp.add(but1);
             cp.add(but2);
             setVisible(true);
         }

         public static void main(String[] args) {
             SimpleGUI3 gui = new SimpleGUI3();
         }
     }
     ```

3. **Exécuter le Projet** :
   - Exécutez le projet et vérifiez que la fenêtre affiche deux boutons.

## Étape 6: Ajouter un Événement à un Bouton

1. **Créer une Nouvelle Classe** :
   - Créez une nouvelle classe nommée `SimpleGUI4`.

2. **Ajouter le Code pour l'Événement du Bouton** :
   - Ouvrez la classe `SimpleGUI4`.
   - Copiez et collez le code suivant :

     ```java
     import javax.swing.JButton;
     import javax.swing.JFrame;
     import javax.swing.JOptionPane;
     import java.awt.Container;
     import java.awt.FlowLayout;
     import java.awt.event.ActionEvent;
     import java.awt.event.ActionListener;

     public class SimpleGUI4 extends JFrame {
         SimpleGUI4() {
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
             SimpleGUI4 gui = new SimpleGUI4();
         }

         class MyActionListener implements ActionListener {
             public void actionPerformed(ActionEvent ae) {
                 JOptionPane.showMessageDialog(null, "Cliqué!!!");
             }
         }
     }
     ```

3. **Exécuter le Projet** :
   - Exécutez le projet et cliquez sur le bouton pour voir le message "Cliqué!!!".

Félicitations! Vous avez maintenant créé une interface graphique simple en utilisant NetBeans et Swing. 
