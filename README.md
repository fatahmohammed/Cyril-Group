# 🌱 Ciril-Group-Simulation-Feu-En-Foret 🌱
# 👋 FATAH Mohammed 👋
# Simulation de propagation de feu

Ce projet simule la propagation du feu dans une grille en utilisant un modèle probabiliste. Les cases de la grille peuvent être dans l'un des trois états : ARBRE, EN_FEU, ou CENDRE.

## Algorithme

L'algorithme suit les étapes suivantes :

1. **Initialisation** :
    - Créer une grille de taille définie où chaque case est initialement un ARBRE.
    - Définir la probabilité `p` de propagation du feu à une case adjacente.
    - Placer quelques cases initiales en état EN_FEU pour démarrer la simulation.

2. **Simulation** :
    - Pour chaque tour de simulation :
        - Identifier Si les positions des cases actuellement en feu `(x, y)`.
        - Pour chaque case `(X, Y)` en feu :
            - Passer l'état de la case à `CENDRE`.
            - Récupérer les positions adjacentes `ADJX, ADJY` des 4 cases voisines (haut, bas, gauche, droite).
            - Pour chaque case adjacente `(ADJX, ADJY)` :
                - Si `(ADJX, ADJY)` est une position valide dans la grille et que la case `[ADJX, ADJY]` est un `ARBRE` et que `A < p` (où `A` est un nombre aléatoire entre 0 et 1) :
                    - Passer l'état de la case adjacente à `EN_FEU`.
                - Fin Si
            - Fin Pour
        - Fin Pour
    - Fin Si
- Fin

# Présentation du projet 


## ⚡ Structure du Projet

Dans ce projet, j'ai structuré l'organisation de manière efficace. Voici la structure du projet :

- **`src/main/java/ `** Contient tout le code source du projet ainsi que les ressources nécessaires.
- **`com/fatah/simulationfeuenforet/`**  Paquetage principal du projet.
- **`config/ `** Contient la classe de configuration du programme. Les paramètres tels que la `hauteur`, la `largeur`, la `probabilité` et `les cases initiales en feu` sont stockés dans un fichier de configuration `application.yaml`.
- **`controller/`**  Contient une classe RestController pour gérer les interactions via des `API REST`.
- **`frame/`**  Contient la classe relatives à l'interface utilisateur `JFrame`.
- **`grille/`**  Contient les classes de gestion de la grille de simulation.
- **`image/`**  Contient la classe pour enregistrer un état de grille sous forme d'image.
- **`json/ `** Contient les classes pour enregistrer un état de grille sous forme un fichiers JSON.
- **`mainRun/ `** Contient deux classes Main principale pour exécuter le programme en console ou en JFrame.
- **`simulation/`**  Contient la classe de la simulation de propagation de feu sur la grille.
- **`resources/ `** Contient les ressources non Java.
- **`SemulationFeuEnForetApplication`** Classe principale pour lancer l'API REST.


## ⚡ Prérequis

- Avant de commencer, assurez-vous d'avoir Java JDK 17 ou + installé sur votre système.
- Maven installé
- ## ⚡ Configuration

Avant d'exécuter les tests, assurez-vous de recharger Maven pour télécharger les dépendances nécessaires en exécutant la
commande suivante :
⚡⚡⚡⚡⚡⚡⚡
```bash
mvn clean install 
````
## ⚡ Installation

1. Clonez ce dépôt sur votre machine locale.
2. Importez le projet dans votre environnement de développement Java préféré IntelliJ IDEA.
3. Configurez les dépendances nécessaires .
4. Exécutez. `Main` sous Console `main2` sous JFrame et `SemulationFeuEnForetApplication` est API REST.

## Console
![](https://github.com/fatahmohammed/Ciril-Group-Semulation-Feu-Foret/blob/main/Simulation%20sous%20console.png)
## JFrame
![](https://github.com/fatahmohammed/Ciril-Group-Semulation-Feu-Foret/blob/main/Simulation%20sous%20JFrame.png)
## API REST 
Pour utiliser cette fonctionnalité, vous aurez besoin de fournir un corps JSON. Voici un exemple :
```json
{
  "hauteur": 100,
  "largeur": 200,
  "probabilite": 0.5,
  "feuxInitials": [
    [4, 4],
    [6, 8]
  ]
}
```
![](https://github.com/fatahmohammed/Ciril-Group-Semulation-Feu-Foret/blob/main/Simulation%20sous%20API%20REST.png)

## un exemple d'ensemble d'états en image aprés simulation  
![](https://github.com/fatahmohammed/Ciril-Group-Semulation-Feu-Foret/blob/main/Etat%20140.png)
![](https://github.com/fatahmohammed/Ciril-Group-Semulation-Feu-Foret/blob/main/Etat%20141.png)
![](https://github.com/fatahmohammed/Ciril-Group-Semulation-Feu-Foret/blob/main/Etat%20142.png)
![](https://github.com/fatahmohammed/Ciril-Group-Semulation-Feu-Foret/blob/main/Etat%20143.png)
![](https://github.com/fatahmohammed/Ciril-Group-Semulation-Feu-Foret/blob/main/Etat%20144.png)
![](https://github.com/fatahmohammed/Ciril-Group-Semulation-Feu-Foret/blob/main/Etat%20145.png)
![](https://github.com/fatahmohammed/Ciril-Group-Semulation-Feu-Foret/blob/main/Etat%20160.png)

## un exemple d'un d'état en JSON
```json
{"Etat 0": [

    ["ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE"],
    ["ARBRE","ARBRE","EN_FEU","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE"],
    ["ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE"],
    ["ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE"],
    ["ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE"],
    ["ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","EN_FEU","ARBRE","ARBRE","ARBRE","ARBRE"],
    ["ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE"],
    ["ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE"],
    ["ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE"],
    ["ARBRE","EN_FEU","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE","ARBRE"]
]}
```





