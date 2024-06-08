# Tutoriel Pas à Pas pour Débutants: Utilisation de NetBeans pour Créer une Interface Graphique (GUI) avec Swing

## Introduction

- Ce tutoriel vous guidera pas à pas dans la création d'une interface graphique simple en utilisant NetBeans et la bibliothèque Swing de Java. 
- Nous commencerons par l'installation de NetBeans, puis passerons à la création d'un projet simple avec des boutons et d'autres composants Swing.

## Étape 1: Installation de NetBeans

1. **Téléchargement de NetBeans**: Allez sur le site officiel de NetBeans et téléchargez l'installateur correspondant à votre système d'exploitation.
2. **Installation de NetBeans**: Suivez les instructions d'installation fournies par NetBeans.

## Étape 2: Création d'un Nouveau Projet

1. **Ouvrir NetBeans**: Lancez NetBeans après l'installation.
2. **Créer un Nouveau Projet**:
   - Allez dans `File` -> `New Project`.
   - Sélectionnez `Java` dans la catégorie et `Java Application` dans les projets.
   - Cliquez sur `Next`.

3. **Configurer le Projet**:
   - Donnez un nom à votre projet (par exemple, `SimpleGUI`).
   - Choisissez un emplacement pour votre projet.
   - Cliquez sur `Finish`.

## Étape 3: Configuration de la Fenêtre Principale

1. **Création de la Classe Principale**:
   - Dans l'onglet `Projects`, faites un clic droit sur le dossier `Source Packages`.
   - Sélectionnez `New` -> `Java Class`.
   - Nommez la classe `SimpleGUI1` et cliquez sur `Finish`.

2. **Ajouter du Code pour la Fenêtre**:
   - Copiez et collez le code suivant dans votre classe `SimpleGUI1`:

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

3. **Exécuter le Projet**:
   - Cliquez sur l'icône `Run` (ou appuyez sur `Shift + F6`) pour exécuter le projet.
   - Vous devriez voir une fenêtre vide de 400x400 pixels.

## Étape 4: Ajouter un Bouton à la Fenêtre

1. **Créer une Nouvelle Classe**:
   - Répétez les étapes pour créer une nouvelle classe nommée `SimpleGUI2`.

2. **Ajouter du Code pour un Bouton**:
   - Copiez et collez le code suivant dans votre classe `SimpleGUI2`:

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

## Étape 5: Ajouter Deux Boutons à la Fenêtre

1. **Créer une Nouvelle Classe**:
   - Créez une nouvelle classe nommée `SimpleGUI3`.

2. **Ajouter du Code pour Deux Boutons**:
   - Copiez et collez le code suivant dans votre classe `SimpleGUI3`:

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

3. **Exécuter le Projet**:
   - Exécutez le projet et vérifiez que la fenêtre affiche deux boutons.

## Étape 6: Ajouter un Événement à un Bouton

1. **Créer une Nouvelle Classe**:
   - Créez une nouvelle classe nommée `SimpleGUI4`.

2. **Ajouter du Code pour l'Événement du Bouton**:
   - Copiez et collez le code suivant dans votre classe `SimpleGUI4`:

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

3. **Exécuter le Projet**:
   - Exécutez le projet et cliquez sur le bouton pour voir le message "Cliqué!!!".

Félicitations! Vos étudiants ont maintenant créé une interface graphique simple en utilisant NetBeans et Swing. Ils peuvent continuer à ajouter d'autres composants et fonctionnalités en utilisant les concepts appris dans ce tutoriel.
