### Démo Complète: Concepts de Programmation Orientée Objet

#### Partie 1 : Java

**Démo :**

1. **Définition de la Classe et Constructeur**

```java
public class Car {
    private String model;
    private int year;

    public Car(String model, int year) {
        this.model = model;
        this.year = year;
    }
}
```

2. **Méthodes et `toString`**

```java
public class Car {
    private String model;
    private int year;

    public Car(String model, int year) {
        this.model = model;
        this.year = year;
    }

    public void displayInfo() {
        System.out.println("Model: " + model + ", Year: " + year);
    }

    @Override
    public String toString() {
        return "Car(model=" + model + ", year=" + year + ")";
    }
}
```

3. **Instanciation et Appel des Méthodes**

```java
public class Main {
    public static void main(String[] args) {
        Car car = new Car("Toyota", 2021);
        car.displayInfo();  // Output: Model: Toyota, Year: 2021
        System.out.println(car);  // Output: Car(model=Toyota, year=2021)
    }
}
```

#### Partie 2 : Python

**Démo :**

1. **Définition de la Classe et Constructeur**

```python
class Car:
    def __init__(self, model, year):
        self.model = model
        self.year = year
```

2. **Méthodes et `__str__`**

```python
class Car:
    def __init__(self, model, year):
        self.model = model
        self.year = year

    def display_info(self):
        print(f"Model: {self.model}, Year: {self.year}")

    def __str__(self):
        return f"Car(model={self.model}, year={self.year})"
```

3. **Instanciation et Appel des Méthodes**

```python
car = Car("Toyota", 2021)
car.display_info()  # Output: Model: Toyota, Year: 2021
print(car)  # Output: Car(model=Toyota, year=2021)
```

### Examen: Concepts de Programmation Orientée Objet

#### Partie 1 : Java

**Votre tâche :**

Créez un fichier Java et réalisez les étapes suivantes :

1. Définissez une classe `Person` avec les attributs `name` et `age`.
2. Implémentez un constructeur pour initialiser ces attributs.
3. Ajoutez une méthode `greet` qui affiche un message de salutation incluant le nom de la personne.
4. Redéfinissez la méthode `toString` pour retourner une représentation de l'objet sous forme de chaîne de caractères.
5. Instanciez un objet de la classe `Person` avec votre nom et âge.
6. Appelez la méthode `greet` et affichez l'objet.

#### Partie 2 : Python

**Votre tâche :**

Créez un fichier Python et réalisez les étapes suivantes :

1. Définissez une classe `Person` avec les attributs `name` et `age`.
2. Implémentez un constructeur pour initialiser ces attributs.
3. Ajoutez une méthode `greet` qui affiche un message de salutation incluant le nom de la personne.
4. Redéfinissez la méthode `__str__` pour retourner une représentation de l'objet sous forme de chaîne de caractères.
5. Instanciez un objet de la classe `Person` avec votre nom et âge.
6. Appelez la méthode `greet` et affichez l'objet.

### Instructions :

1. Écrivez le code Java dans un fichier Java, compilez et exécutez-le pour vous assurer qu'il fonctionne correctement.
2. Écrivez le code Python dans un fichier Python et exécutez-le pour vous assurer qu'il fonctionne correctement.
3. Soumettez les fichiers Java et Python avec vos réponses.

### Critères de Notation :

- Exactitude des définitions de classe et des constructeurs (30%)
- Implémentation des méthodes et de `toString`/`__str__` (40%)
- Instanciation correcte des objets et appel des méthodes (30%)

Bonne chance !
