### Vulgarisation de la Calculatrice en Java

La calculatrice que nous avons ici est un projet simple en Java qui utilise l'interface graphique Swing pour créer une calculatrice de base. Voici une explication étape par étape des principaux composants du code :

#### Composants Principaux de la Calculatrice

1. **Les Boutons et les Champs de Texte**
    - Les boutons de chiffres (0-9) et les boutons d'opérations (plus, moins, diviser, multiplier).
    - Un champ de texte pour afficher les chiffres et les résultats.

2. **Les Panneaux de Disposition**
    - **BorderLayout** : Utilisé pour disposer les composants dans les régions nord, sud, est, ouest et centre.
    - **GridLayout** : Utilisé pour créer une grille pour les boutons de chiffres et d'opérations.

#### Classe Calculatrice

1. **Déclaration des Composants**
    - Tous les boutons et le champ de texte sont déclarés et instanciés au début de la classe.
    - Exemple : `JButton bouton0 = new JButton("0");` crée un bouton avec le label "0".

2. **Disposition des Composants**
    - Les composants sont placés dans des panneaux (`JPanel`) avec des dispositions spécifiques.
    - Le champ de texte est placé en haut de la fenêtre.
    - Les boutons de chiffres sont placés dans une grille de 4x3 et ajoutés au centre de la fenêtre.
    - Les boutons d'opérations sont placés dans une grille de 4x1 et ajoutés à droite de la fenêtre.

3. **Création de la Fenêtre**
    - Une fenêtre (`JFrame`) est créée, les composants sont ajoutés, et la fenêtre est affichée avec `frame.setVisible(true);`.

#### Classe MoteurCalcul

1. **ActionListener**
    - La classe `MoteurCalcul` implémente `ActionListener` pour gérer les clics de boutons.
    - Lorsque l'utilisateur clique sur un bouton, la méthode `actionPerformed` est appelée.

2. **Gestion des Clics**
    - Si un bouton d'opération est cliqué, l'action (addition, soustraction, etc.) est enregistrée, et le champ de texte est vidé pour entrer le prochain nombre.
    - Si le bouton "=" est cliqué, le calcul est effectué en fonction de l'action enregistrée et le résultat est affiché dans le champ de texte.
    - Si un bouton de chiffre est cliqué, le chiffre est ajouté au champ de texte.

#### Exemple de Fonctionnement

1. **Ajouter Deux Nombres**
    - Cliquez sur "2".
    - Cliquez sur "+".
    - Cliquez sur "3".
    - Cliquez sur "=".
    - Le champ de texte affichera "5".

#### Résumé

Cette calculatrice est un exemple simple d'application Java avec une interface graphique. Elle montre comment utiliser les boutons et les champs de texte pour effectuer des opérations arithmétiques de base. Les concepts principaux incluent la gestion des événements (clics de boutons) et l'organisation des composants avec des dispositions (`Layout`).

En simplifiant, vous créez une interface utilisateur avec des boutons pour chaque chiffre et opération, et vous écrivez du code pour que la calculatrice sache quoi faire quand chaque bouton est pressé. Le reste du code s'assure que tout s'affiche correctement à l'écran.
