# Evaluation-Appli-Formation
    Expressions des besoins : Votre client souhaite une application de vente de formation 

# Prérequis 
    Oracle OpenJDK 26.0.2 ou version supérieure
    Un IDE comme IntelliJ , Eclipse ou VS code
    GIT/ GIT HUB (si vous clonez le projet depuis un dépôt)

# Installation
    Cloner le dépôt
    Compiler le projet
    Exécuter le programme

# Structure du projet
    Un dossier Conception 
        BDD
        Diagrammes
        MCD
    
    Un dossier src
        Business
        Dao
        Database
        Models
        test
        User_interface

# Enoncé de l'exercice
    Dans un premier temps, l’application doit permettre à tous les utilisateurs non connectés d’afficher 
toutes les formations disponibles, d’afficher toutes les formations contenant un mot clé, toutes les 
formations en présentiel ou distanciel.

    Une formation est caractérisée par : nom + description + durée en jours + présentiel ou dist + prix 
….. 

    Dans un second temps, il souhaite constituer un panier en ajoutant/retirant des formations avec 
possibilité de passer commande à tout instant, à condition d’être connecté, sinon il sera orienté vers 
la création d’un utilisateur (login + password), une commande devra être associée à un client (nom, 
prénom, email, adresse, tel), un utilisateur peut réaliser plusieurs commandes pour différents clients.

    Le client est à votre disposition pour répondre à vos questions, il est préférable d’en poser un 
maximum avant de commencer votre projet.

    Contrainte technique : Votre application sera en mode console d’abord mais elle doit être prête à 
l’intégration dans une web application selon les modalités multi couche vues jusqu’ici.

L’ensemble des productions attendues (RoadMap) : - - - - - - - - 
    Démo 
    Diagrammes UML (Cas d’utilisations, classes, séquence) 
    Spécifications fonctionnelles 
    MCD (Looping), base de données + droit restreint sur celle-ci + Script SQL 
    Couche entités conforme aux diagrammes de classes 
    Couche Dao (Pattern Dao, Singleton, Factory, Fichier de config) [faites au mieux] 
    Couche Business puis Couche application 
    Utilisation de Git et de toutes les bonnes pratiques vues depuis le début [Poo, 
    Exceptions/Log, indentation, anglais, lisibilité, javadoc, tests…]

# Evolution possible 