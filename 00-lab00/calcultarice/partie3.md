# Calculatrice Java avec Interface Graphique

## Schéma de l'Interface Utilisateur

Voici un schéma simple de l'interface utilisateur de la calculatrice.

```
------------------------------
|         champAffichage     |
------------------------------
|  1  |  2  |  3  |   +      |
------------------------------
|  4  |  5  |  6  |   -      |
------------------------------
|  7  |  8  |  9  |   *      |
------------------------------
|  0  |  ,  |  =  |   /      |
------------------------------
```

### Explication du Schéma

1. **Champ d'Affichage**
   - **Description** : Zone de texte où les chiffres et les résultats des calculs sont affichés.
   - **Composant Java** : `JTextField`
   - **Position** : `BorderLayout.NORTH`

2. **Boutons Numériques**
   - **Description** : Boutons pour les chiffres de 0 à 9.
   - **Composant Java** : `JButton`
   - **Position** : Placés dans une grille de 4x3 au centre de la fenêtre (`GridLayout(4, 3)`, `BorderLayout.CENTER`).

3. **Boutons d'Opérations**
   - **Description** : Boutons pour les opérations de base (addition, soustraction, multiplication, division).
   - **Composant Java** : `JButton`
   - **Position** : Placés dans une grille de 4x1 à droite de la fenêtre (`GridLayout(4, 1)`, `BorderLayout.EAST`).

4. **Boutons Spéciaux**
   - **Bouton Virgule (",")** : Utilisé pour les nombres décimaux.
   - **Bouton Égal ("=")** : Utilisé pour effectuer le calcul et afficher le résultat.

### Code Source Simplifié

#### Classe `Calculatrice`

```java
package calculatrice;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class Calculatrice {
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

    Calculatrice() {
        BorderLayout disposition = new BorderLayout();
        contenuFenêtre.setLayout(disposition);
        contenuFenêtre.add("North", champAffichage);

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
        contenuFenêtre.add("Center", panneauChiffres);

        JPanel panneauOpérations = new JPanel();
        GridLayout dispositionOpérations = new GridLayout(4, 1);
        panneauOpérations.setLayout(dispositionOpérations);
        panneauOpérations.add(boutonPlus);
        panneauOpérations.add(boutonMoins);
        panneauOpérations.add(boutonMultiplier);
        panneauOpérations.add(boutonDiviser);
        contenuFenêtre.add("East", panneauOpérations);

        JFrame frame = new JFrame("Calculatrice");
        frame.setContentPane(contenuFenêtre);
        frame.pack();
        frame.setVisible(true);

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
        Calculatrice calc = new Calculatrice();
    }
}
```

#### Classe `MoteurCalcul`

```java
package calculatrice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParsePosition;
import javax.swing.JButton;

public class MoteurCalcul implements ActionListener {
    Calculatrice parent;
    char actionSélectionnée = ' ';
    double résultatCourant = 0;
    NumberFormat formatNombres = NumberFormat.getInstance();

    MoteurCalcul(Calculatrice parent) {
        this.parent = parent;
    }

    public void actionPerformed(ActionEvent événement) {
        JButton boutonCliqué = (JButton) événement.getSource();
        String texteChampAffichage = parent.champAffichage.getText();

        double valeurAffichée = 0;
        if (!"".equals(texteChampAffichage)) {
            valeurAffichée = formatNombres.parse(texteChampAffichage, new ParsePosition(0)).doubleValue();
        }
        Object sourceEvénement = événement.getSource();

        if (sourceEvénement == parent.boutonPlus) {
            actionSélectionnée = '+';
            résultatCourant = valeurAffichée;
            parent.champAffichage.setText("");
        } else if (sourceEvénement == parent.boutonMoins) {
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
            String libelléBoutonCliqué = boutonCliqué.getText();
            parent.champAffichage.setText(texteChampAffichage + libelléBoutonCliqué);
        }
    }
}
```

Ce README fournit un schéma visuel simple et une explication des différentes parties de l'interface utilisateur de la calculatrice Java, ainsi que le code source correspondant.
