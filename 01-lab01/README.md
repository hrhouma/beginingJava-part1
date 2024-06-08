
# Instructions - partie 1 :

- Téléchargez les fichiers 
- Pour exécuter et tester le serveur de formes avec Telnet, voici la suite des commandes et les étapes nécessaires :

1. **Activer Telnet sur Windows :**

   Si Telnet n'est pas déjà activé sur votre machine, vous devez l'activer :

   - Ouvrez le Panneau de configuration.
   - Cliquez sur "Programmes".
   - Sous "Programmes et fonctionnalités", cliquez sur "Activer ou désactiver des fonctionnalités Windows".
   - Cochez "Client Telnet" et cliquez sur "OK".

2. **Démarrer le serveur ServeurForme.jar :**

   Pour démarrer le serveur, ouvrez une invite de commandes (CMD) et naviguez jusqu'au répertoire où se trouve le fichier `ServeurForme.jar`. Ensuite, exécutez la commande suivante :

   ```bash
   java -jar ServeurForme.jar
   ```

3. **Tester avec Telnet :**

   Une fois le serveur démarré, ouvrez une autre invite de commandes pour utiliser Telnet et tester la connexion avec le serveur. Tapez la commande suivante pour vous connecter au serveur local sur le port 10000 :

   ```bash
   telnet localhost 10000
   ```

4. **Envoyer des commandes GET :**

   Après vous être connecté au serveur avec Telnet, vous verrez le prompt `commande>`. Tapez la commande suivante pour obtenir une forme :

   ```bash
   GET
   ```

   Répétez cette commande plusieurs fois pour observer les différentes formes envoyées par le serveur.

5. **Observer l'interface du serveur :**

- Pendant que vous envoyez des commandes GET, vous pouvez observer l'interface graphique du serveur (comme montré dans l'image) pour vérifier les formes transmises et les connexions actives. Vous devriez voir les compteurs de "Formes transmises" et "Connexions" se mettre à jour à chaque requête GET.

---
# Étape 1 -  Cliquez sur serveurForme.jar :
![image](https://github.com/hrhouma/beginingJava-part1/assets/10111526/90cecbcf-4f38-403e-bacb-58aef1acd2eb)
---
# Étape 2 - Ouvrir : 
![image](https://github.com/hrhouma/beginingJava-part1/assets/10111526/7907673a-0774-4e83-9567-4d333e313a25)
---
# Étape 3 - Exécutez la commande suivante : 
![image](https://github.com/hrhouma/beginingJava-part1/assets/10111526/a66d196c-7303-4043-9d83-b8a43ce10620)
---
# Étape 4 -  Écrire GET :  
![image](https://github.com/hrhouma/beginingJava-part1/assets/10111526/3dca051a-862f-4b29-8a60-8c77db1e2700)
---
# Étape 5 - Écrire GET plsueiurs fois et regardez l'interface serveur (serveurForme.jar) :
![image](https://github.com/hrhouma/beginingJava-part1/assets/10111526/6b668637-0fe2-4d5d-9b3c-98e670114edb)
---


Voici un exemple de séquence de commandes pour référence :

```bash
# Activer Telnet (si nécessaire) - faire une seule fois
# Démarrer le serveur
java -jar ServeurForme.jar

# Dans une autre fenêtre de commande
telnet localhost 10000

# Une fois connecté, tapez :
GET
# Observez la réponse, puis tapez de nouveau :
GET
# Répétez plusieurs fois pour obtenir différentes formes
# Observer l'interface du serveur 
```

Chaque commande GET devrait générer une réponse avec une nouvelle forme, et l'interface du serveur devrait refléter les changements en temps réel, comme illustré dans l'image fournie.


---
# Instructions - partie 2 :

## 1 - Téléchargez le lab1-enonce.zip et importez le projet : 
![image](https://github.com/hrhouma/beginingJava-part1/assets/10111526/55bd66cb-f821-4d4e-9b39-754a1ed88a56)
## 2 - Cliquez sur lib :
![image](https://github.com/hrhouma/beginingJava-part1/assets/10111526/696248ba-36be-4d95-9360-62fcee723280)
## 3 - Cliquez sur Add JAR : 
![image](https://github.com/hrhouma/beginingJava-part1/assets/10111526/970c2160-077f-4792-9dfb-3da6b79ff180)
## 4 -  Choisir IDLogger.jar 
![image](https://github.com/hrhouma/beginingJava-part1/assets/10111526/55fd8dda-ece3-48d9-9473-59e3e20b143f)

## 5 - Observez le JAR importé
![image](https://github.com/hrhouma/beginingJava-part1/assets/10111526/d07d1e50-4f8c-4788-a58b-bb0d7667a485)

## 6 - Exécutez le projet en cliquant sur le bouton vert
![image](https://github.com/hrhouma/beginingJava-part1/assets/10111526/1315bedc-4b45-45c3-b176-4ad4b0a66be9)

## 7 - Observez l'interafce cliente apparue et aussi ouvrir l'interface serveur (serveurForme.jar) :
![image](https://github.com/hrhouma/beginingJava-part1/assets/10111526/1315bedc-4b45-45c3-b176-4ad4b0a66be9)

## 8 - Pour l'interface cliente, cliquez sur DRAW, START
![image](https://github.com/hrhouma/beginingJava-part1/assets/10111526/3548e455-799a-4abb-9f10-3e2602914241)

## 9 - Choisir le nombre de formes, par exemple 10
![image](https://github.com/hrhouma/beginingJava-part1/assets/10111526/4b93e43f-389b-485f-b379-f832cd157e10)

## 10 - Observeur les deux interfaces : l'interface cliente et l'interface serveur 
![image](https://github.com/hrhouma/beginingJava-part1/assets/10111526/6254652b-eb1f-4b97-ac27-a7950bdcce9d)

## 11 - vos remarques ? 
![image](https://github.com/hrhouma/beginingJava-part1/assets/10111526/6254652b-eb1f-4b97-ac27-a7950bdcce9d)





---
# Instructions - partie 3 :


---
# Instructions - partie 4 :


## Résumé commandes 1

![image](https://github.com/hrhouma/beginingJava-part1/assets/10111526/90cecbcf-4f38-403e-bacb-58aef1acd2eb)

![image](https://github.com/hrhouma/beginingJava-part1/assets/10111526/7907673a-0774-4e83-9567-4d333e313a25)

![image](https://github.com/hrhouma/beginingJava-part1/assets/10111526/a66d196c-7303-4043-9d83-b8a43ce10620)

![image](https://github.com/hrhouma/beginingJava-part1/assets/10111526/3dca051a-862f-4b29-8a60-8c77db1e2700)

![image](https://github.com/hrhouma/beginingJava-part1/assets/10111526/6b668637-0fe2-4d5d-9b3c-98e670114edb)

