# Calculatrice Java avec Interface Graphique

Ce projet est une calculatrice simple en Java utilisant l'interface graphique Swing. Le projet comprend deux classes principales : `Calculatrice` et `MoteurCalcul`. La classe `Calculatrice` crée l'interface utilisateur, tandis que `MoteurCalcul` gère les événements de clic sur les boutons.

## Structure du Projet

### Classe `Calculatrice`

#### Composants de la Fenêtre

- **Boutons Numériques** : `JButton` pour les chiffres de 0 à 9.
- **Boutons d'Opérations** : `JButton` pour les opérations de base (addition, soustraction, division, multiplication).
- **Champ d'Affichage** : `JTextField` pour afficher les chiffres et les résultats.

#### Disposition des Composants

1. **Champ d'Affichage** : Placé en haut de la fenêtre (`BorderLayout.NORTH`).
2. **Panneau des Chiffres** : 
   - Disposé en grille de 4x3 (`GridLayout(4, 3)`).
   - Contient les boutons numériques et les boutons de virgule et égal.
   - Placé au centre de la fenêtre (`BorderLayout.CENTER`).
3. **Panneau des Opérations** :
   - Disposé en grille de 4x1 (`GridLayout(4, 1)`).
   - Contient les boutons d'opérations.
   - Placé à droite de la fenêtre (`BorderLayout.EAST`).

#### Construction de la Fenêtre

- Création de la fenêtre (`JFrame`).
- Ajout des panneaux à la fenêtre.
- Affichage de la fenêtre avec `frame.setVisible(true)`.

#### Gestion des Événements

- Instanciation de `MoteurCalcul` pour gérer les clics de boutons.
- Enregistrement de l'écouteur d'événements (`ActionListener`) pour chaque bouton.

### Classe `MoteurCalcul`

#### Fonctionnalité

1. **ActionListener** : Implémente `ActionListener` pour gérer les événements de clic.
2. **Gestion des Clics de Boutons** :
   - Si un bouton d'opération est cliqué, l'action est enregistrée, et le champ de texte est vidé pour le prochain nombre.
   - Si le bouton "=" est cliqué, le calcul est effectué en fonction de l'action enregistrée, et le résultat est affiché.
   - Si un bouton de chiffre est cliqué, le chiffre est ajouté au champ de texte.

#### Logique de Calcul

1. **Sélection d'Action** :
   - Enregistre l'opération sélectionnée (+, -, /, *).
   - Stocke la valeur actuelle affichée.
2. **Calcul du Résultat** :
   - Effectue le calcul en fonction de l'opération sélectionnée lorsque "=" est cliqué.
   - Met à jour le champ d'affichage avec le résultat.

#### Exemple d'Utilisation

1. **Addition** :
   - Cliquez sur "2".
   - Cliquez sur "+".
   - Cliquez sur "3".
   - Cliquez sur "=".
   - Le champ de texte affichera "5".

## Code Source

### Classe `Calculatrice`

```java
package calculatrice;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class Calculatrice {
    // Déclare et instancie les composants de la fenêtre
    JButton bouton0 = new JButton("0");
    JButton bouton1 = new JButton("1");
    JButton bouton2 = new JButton("2");
    JButton bouton3 = new JButton("3");
    JButton bouton4 = new JButton("4");
    JButton bouton5 = new JButton("5");
    JButton bouton6 = new JButton("6");
    JButton bouton7 = new JButton("7");
    JButton bouton8 = new JButton("8");
    JButton bouton9 = new JButton("9");
    JButton boutonVirgule = new JButton(",");
    JButton boutonEgale = new JButton("=");
    JButton boutonPlus = new JButton("+");
    JButton boutonMoins = new JButton("-");
    JButton boutonDiviser = new JButton("/");
    JButton boutonMultiplier = new JButton("*");
    JPanel contenuFenêtre = new JPanel();
    JTextField champAffichage = new JTextField(30);

    // Constructeur
    Calculatrice() {
        // Affecte le gestionnaire de disposition pour ce panneau
        BorderLayout disposition = new BorderLayout();
        contenuFenêtre.setLayout(disposition);

        // Ajoute le champ d'affichage en haut de la fenêtre
        contenuFenêtre.add("North", champAffichage);

        // Crée le panneau avec le quadrillage qui contient 12 boutons – les 10 boutons numériques et ceux représentant la virgule et le signe égale
        JPanel panneauChiffres = new JPanel();
        GridLayout dispositionChiffres = new GridLayout(4, 3);
        panneauChiffres.setLayout(dispositionChiffres);
        panneauChiffres.add(bouton1);
        panneauChiffres.add(bouton2);
        panneauChiffres.add(bouton3);
        panneauChiffres.add(bouton4);
        panneauChiffres.add(bouton5);
        panneauChiffres.add(bouton6);
        panneauChiffres.add(bouton7);
        panneauChiffres.add(bouton8);
        panneauChiffres.add(bouton9);
        panneauChiffres.add(bouton0);
        panneauChiffres.add(boutonVirgule);
        panneauChiffres.add(boutonEgale);

        // Ajoute le panneau des chiffres à la zone centrale de la fenêtre
        contenuFenêtre.add("Center", panneauChiffres);

        // Crée le panneau avec le quadrillage qui contient 4 boutons d'opération – Plus, Moins, Diviser, Multiplier
        JPanel panneauOpérations = new JPanel();
        GridLayout dispositionOpérations = new GridLayout(4, 1);
        panneauOpérations.setLayout(dispositionOpérations);
        panneauOpérations.add(boutonPlus);
        panneauOpérations.add(boutonMoins);
        panneauOpérations.add(boutonMultiplier);
        panneauOpérations.add(boutonDiviser);

        // Ajoute le panneau des opérations à la zone est de la fenêtre
        contenuFenêtre.add("East", panneauOpérations);

        // Crée le cadre et lui affecte son contenu
        JFrame frame = new JFrame("Calculatrice");
        frame.setContentPane(contenuFenêtre);

        // Affecte à la fenêtre des dimensions suffisantes pour prendre en compte tous les contrôles
        frame.pack();

        // Affiche la fenêtre
        frame.setVisible(true);

        // Instancie le récepteur d'événements et l'enregistre auprès de chaque bouton
        MoteurCalcul moteurCalcul = new MoteurCalcul(this);
        bouton0.addActionListener(moteurCalcul);
        bouton1.addActionListener(moteurCalcul);
        bouton2.addActionListener(moteurCalcul);
        bouton3.addActionListener(moteurCalcul);
        bouton4.addActionListener(moteurCalcul);
        bouton5.addActionListener(moteurCalcul);
        bouton6.addActionListener(moteurCalcul);
        bouton7.addActionListener(moteurCalcul);
        bouton8.addActionListener(moteurCalcul);
        bouton9.addActionListener(moteurCalcul);
        boutonVirgule.addActionListener(moteurCalcul);
        boutonPlus.addActionListener(moteurCalcul);
        boutonMoins.addActionListener(moteurCalcul);
        boutonDiviser.addActionListener(moteurCalcul);
        boutonMultiplier.addActionListener(moteurCalcul);
        boutonEgale.addActionListener(moteurCalcul);
    }

    public static void main(String[] args) {
        // Instancie la classe Calculatrice
        Calculatrice calc = new Calculatrice();
    }
}
```

### Classe `MoteurCalcul`

```java
package calculatrice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParsePosition;
import javax.swing.JButton;

public class MoteurCalcul implements ActionListener {
    Calculatrice parent; // une référence à la Calculatrice
    char actionSélectionnée = ' '; // +, -, /, ou *
    double résultatCourant = 0;
    NumberFormat formatNombres = NumberFormat.getInstance(); // un objet capable de lire et présenter les nombres

    // Le constructeur stocke la référence à la fenêtre Calculatrice dans la variable membre parent
    MoteurCalcul(Calculatrice parent) {
        this.parent = parent;
    }

    public void actionPerformed(ActionEvent événement) {
        // Retrouve la source de l'action
        JButton boutonCliqué = (JButton) événement.getSource();
        String texteChampAffichage = parent.champAffichage.getText();

        double valeurAffichée = 0;
        // Retrouve le nombre présenté dans le champ texte s'il n'est pas vide
        if (!"".equals(texteChampAffichage)) {
            valeurAffichée = formatNombres.parse(texteChampAffichage, new ParsePosition(0)).doubleValue();
        }
        Object sourceEvénement = événement.getSource();

        // Pour chaque bouton d'action, mémorise l'action sélectionnée, +, -, /, ou *, stocke la valeur courante dans la variable résultatCourant et vide le champ Affichage avant l'entrée du nombre suivant
        if (sourceEvénement == parent.boutonPlus) {
            actionSélectionnée = '+';
            résultatCourant = valeurAffichée;
            parent.champAffichage.setText("");
        } else if (sourceEvénement ==

 parent.boutonMoins) {
            actionSélectionnée = '-';
            résultatCourant = valeurAffichée;
            parent.champAffichage.setText("");
        } else if (sourceEvénement == parent.boutonDiviser) {
            actionSélectionnée = '/';
            résultatCourant = valeurAffichée;
            parent.champAffichage.setText("");
        } else if (sourceEvénement == parent.boutonMultiplier) {
            actionSélectionnée = '*';
            résultatCourant = valeurAffichée;
            parent.champAffichage.setText("");
        } else if (sourceEvénement == parent.boutonEgale) {
            // Effectue les calculs en fonction de actionSélectionnée, modifie la valeur de la variable résultatCourant et affiche le résultat
            if (actionSélectionnée == '+') {
                résultatCourant += valeurAffichée;
                parent.champAffichage.setText(formatNombres.format(résultatCourant));
            } else if (actionSélectionnée == '-') {
                résultatCourant -= valeurAffichée;
                parent.champAffichage.setText(formatNombres.format(résultatCourant));
            } else if (actionSélectionnée == '/') {
                résultatCourant /= valeurAffichée;
                parent.champAffichage.setText(formatNombres.format(résultatCourant));
            } else if (actionSélectionnée == '*') {
                résultatCourant *= valeurAffichée;
                parent.champAffichage.setText(formatNombres.format(résultatCourant));
            }
        } else {
            // Pour tous les boutons numériques, ajoute le libellé du bouton au champ texte
            String libelléBoutonCliqué = boutonCliqué.getText();
            parent.champAffichage.setText(texteChampAffichage + libelléBoutonCliqué);
        }
    }
}
```

Ce README explique la logique derrière l'interface graphique et le fonctionnement de la calculatrice.
