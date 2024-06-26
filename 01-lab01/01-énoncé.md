
# Instructions :

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

   Pendant que vous envoyez des commandes GET, vous pouvez observer l'interface graphique du serveur (comme montré dans l'image fournie) pour vérifier les formes transmises et les connexions actives. Vous devriez voir les compteurs de "Formes transmises" et "Connexions" se mettre à jour à chaque requête GET.

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
```

Chaque commande GET devrait générer une réponse avec une nouvelle forme, et l'interface du serveur devrait refléter les changements en temps réel, comme illustré dans l'image fournie.
