# Crazy Circus
Ce projet est un projet universitaire optionnel fait en Java.
  

## Description
Cette application permet de jouer à Crazy Circus un jeu inventé par Dominique Ehrhard. Ce version du jeu se joue dans la console.

  

## Utilisation
  

1. Compiler le fichier Main/App.java et le lancer avec comme paramètres le nom de chaque joueur séparés par de espaces.
2. Une fois lancé vous aurez en console une partie. Le but étant d'obtenir ce qui est à droite de flèche en partant de ce qui est à gauche de la flèche.
3. Pour ce faire vous pouvez utiliser 5 ordres:

|Nom | Action|
|--|--|
|KI| L’animal se trouvant en haut de la pile du podium bleu saute pour rejoindre le sommet de la pile du podium rouge. |
|LO  |L’animal se trouvant en haut de la pile du podium rouge saute pour rejoindre le sommet de la pile du podium bleu.  |
|SO |Les deux animaux se trouvant au sommet des piles des deux podiums échangent leur place.|
|NI |Les deux animaux se trouvant au sommet des piles des deux podiums échangent leur place.|
|MA |L’animal se trouvant en bas de la pile du podium rouge monte et se place en haut de la pile de ce même podium. |
4. Pour donner une proposition écrivez en console : 
	> < nom du joueur > < Suite d'instruction sans espace >
  
	> Par exemple : J1 SONIMAKIKI
5. Si cette suite d'instruction est la bonne alors le joueur portant ce nom remporte un point.
6. A la fin des 23 parties, le classement est affiché.
