# Explication Simple du Code de la Calculatrice Java

## Introduction

Ce projet est une calculatrice en Java qui utilise une interface graphique (GUI) pour permettre aux utilisateurs d'effectuer des calculs simples. Il se compose de deux classes principales : `Calculatrice` et `MoteurCalcul`. La classe `Calculatrice` crée l'interface utilisateur, tandis que `MoteurCalcul` gère les clics sur les boutons.

## Classe `MoteurCalcul`

La classe `MoteurCalcul` est responsable de la logique de calcul. Elle implémente l'interface `ActionListener`, ce qui signifie qu'elle peut répondre aux événements de clic sur les boutons de la calculatrice.

### Variables

1. **parent** : Une référence à l'objet `Calculatrice` parent. Cela permet à `MoteurCalcul` d'accéder aux composants de l'interface utilisateur, comme le champ de texte.
    ```java
    Calculatrice parent;
    ```

2. **actionSélectionnée** : Un caractère qui stocke l'opération arithmétique sélectionnée par l'utilisateur (comme '+', '-', '/', '*').
    ```java
    char actionSélectionnée = ' ';
    ```

3. **résultatCourant** : Un double qui stocke le résultat courant du calcul.
    ```java
    double résultatCourant = 0;
    ```

4. **formatNombres** : Un objet de formatage des nombres qui peut analyser et formater des chaînes de caractères représentant des nombres.
    ```java
    NumberFormat formatNombres = NumberFormat.getInstance();
    ```

### Constructeur

Le constructeur initialise la référence à l'objet `Calculatrice` parent.
```java
MoteurCalcul(Calculatrice parent) {
    this.parent = parent;
}
```

### Méthode `actionPerformed`

Cette méthode est appelée chaque fois qu'un bouton est cliqué. Elle gère les clics sur les boutons et effectue les actions appropriées.

#### 1. Récupération du Bouton Cliqué

```java
JButton boutonCliqué = (JButton) événement.getSource();
String texteChampAffichage = parent.champAffichage.getText();
```

#### 2. Récupération de la Valeur Affichée

```java
double valeurAffichée = 0;
if (!"".equals(texteChampAffichage)) {
    valeurAffichée = formatNombres.parse(texteChampAffichage, new ParsePosition(0)).doubleValue();
}
```

#### 3. Gestion des Clics sur les Boutons d'Opérations

```java
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
```

## Explication des Étapes

1. **Récupération du Bouton Cliqué** : La méthode `actionPerformed` récupère le bouton cliqué par l'utilisateur.
    ```java
    JButton boutonCliqué = (JButton) événement.getSource();
    ```

2. **Récupération de la Valeur Affichée** : La méthode récupère la valeur actuellement affichée dans le champ de texte et la convertit en un nombre.
    ```java
    String texteChampAffichage = parent.champAffichage.getText();
    double valeurAffichée = 0;
    if (!"".equals(texteChampAffichage)) {
        valeurAffichée = formatNombres.parse(texteChampAffichage, new ParsePosition(0)).doubleValue();
    }
    ```

3. **Gestion des Clics sur les Boutons d'Opérations** : La méthode vérifie quel bouton d'opération a été cliqué et enregistre l'opération correspondante (+, -, /, *).
    ```java
    Object sourceEvénement = événement.getSource();
    if (sourceEvénement == parent.boutonPlus) {
        actionSélectionnée = '+';
        résultatCourant = valeurAffichée;
        parent.champAffichage.setText("");
    }
    // Répété pour les autres opérations...
    ```

4. **Calcul du Résultat** : Si le bouton "=" est cliqué, la méthode effectue le calcul en fonction de l'opération sélectionnée et affiche le résultat.
    ```java
    else if (sourceEvénement == parent.boutonEgale) {
        if (actionSélectionnée == '+') {
            résultatCourant += valeurAffichée;
            parent.champAffichage.setText(formatNombres.format(résultatCourant));
        }
        // Répété pour les autres opérations...
    }
    ```

5. **Gestion des Clics sur les Boutons Numériques** : Si un bouton numérique est cliqué, le chiffre est ajouté au champ de texte.
    ```java
    else {
        String libelléBoutonCliqué = boutonCliqué.getText();
        parent.champAffichage.setText(texteChampAffichage + libelléBoutonCliqué);
    }
    ```

## Conclusion

La classe `MoteurCalcul` gère la logique de calcul de la calculatrice. Elle écoute les clics sur les boutons, enregistre les opérations sélectionnées, et effectue les calculs lorsque le bouton "=" est cliqué. Les résultats sont affichés dans le champ de texte de la calculatrice.
