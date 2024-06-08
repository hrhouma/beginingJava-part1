/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatrice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Haythem
 */
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.text.ParsePosition;
import javax.swing.JButton;

public class MoteurCalcul implements ActionListener {

    Calculatrice parent; // une référence à la Calculatrice
    char actionSélectionnée = ' '; // +, -, /, ou *
    double résultatCourant = 0;
    NumberFormat formatNombres = NumberFormat.getInstance();
// un objet capable de lire et présenter les nombres
// Le constructeur stocke la référence à la fenêtre
// Calculatrice dans la variable membre parent
    MoteurCalcul(Calculatrice parent) {
        this.parent = parent;
    }

    public void actionPerformed(ActionEvent événement) {
// Retrouve la source de l'action
        JButton boutonCliqué = (JButton) événement.getSource();
        String texteChampAffichage =
                parent.champAffichage.getText();

        double valeurAffichée = 0;
// Retrouve le nombre présenté dans le champ texte
// s'il n'est pas vide
        if (!"".equals(texteChampAffichage)) {
            valeurAffichée =
                    // analyse la chaîne de caractères
                    formatNombres.parse(
                    texteChampAffichage,
                    new ParsePosition(0) /* ne sert pas */).
                    // puis donne sa valeur en tant que double
                    doubleValue();
        }
        Object sourceEvénement = événement.getSource();
// Pour chaque bouton d'action, mémorise l'action
// sélectionnée, +, -, /, ou *, stocke la valeur courante
// dans la variable résultatCourant et vide le champ
// Affichage avant l'entrée du nombre suivant
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
// Effectue les calculs en fonction de actionSélectionnée
// Modifie la valeur de la variable résultatCourant
// et affiche le résultat
            if (actionSélectionnée == '+') {
                résultatCourant += valeurAffichée;
// Convertit le résultat en le transformant en String
// à l'aide de formatNombres
                parent.champAffichage.setText(
                        formatNombres.format(résultatCourant));

            } else if (actionSélectionnée == '-') {
                résultatCourant -= valeurAffichée;
                parent.champAffichage.setText(
                        formatNombres.format(résultatCourant));

            } else if (actionSélectionnée == '/') {
                résultatCourant /= valeurAffichée;
                parent.champAffichage.setText(
                        formatNombres.format(résultatCourant));

            } else if (actionSélectionnée == '*') {
                résultatCourant *= valeurAffichée;
                parent.champAffichage.setText(
                        formatNombres.format(résultatCourant));

            }
        } else {
// Pour tous les boutons numériques, ajoute le libellé
// du bouton au champ texte
            String libelléBoutonCliqué = boutonCliqué.getText();
            parent.champAffichage.setText(texteChampAffichage
                    + libelléBoutonCliqué);
        }
    }
}
