/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
// Crée le panneau avec le quadrillage qui contient
// 12 boutons – les 10 boutons numériques et ceux
// représentant la virgule et le signe égale
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
// Ajoute le panneau des chiffres à la zone centrale
// de la fenêtre
        contenuFenêtre.add("Center", panneauChiffres);
// Crée le panneau avec le quadrillage qui contient 4
// boutons d'opération – Plus, Moins, Diviser, Multiplier
        JPanel panneauOpérations = new JPanel();
        GridLayout dispositionOpérations = new GridLayout(4, 1);
        panneauOpérations.setLayout(dispositionOpérations);
        panneauOpérations.add(boutonPlus);
        panneauOpérations.add(boutonMoins);
        panneauOpérations.add(boutonMultiplier);
        panneauOpérations.add(boutonDiviser);
// Ajoute le panneau des opérations à la zone est
// de la fenêtre
        contenuFenêtre.add("East", panneauOpérations);
// Crée le cadre et lui affecte son contenu
        JFrame frame = new JFrame("Calculatrice");
        frame.setContentPane(contenuFenêtre);
// Affecte à la fenêtre des dimensions suffisantes pour
// prendre en compte tous les contrôles
        frame.pack();
// Affiche la fenêtre
        frame.setVisible(true);
// Instancie le récepteur d'événements et l'enregistre
// auprès de chaque bouton
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
