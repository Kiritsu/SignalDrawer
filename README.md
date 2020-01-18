TP1 :

# 1) 
Le but du TP est de pouvoir dessiner/afficher un signal 
composé de chiffres binaires sous la forme des différents
codes vus pendant le TD : NRZ, Manchester, Manchester
Différentiel, Miller. 



# 2) 
Chaque signal fonctionne différemment. Ainsi, pour les
représenter correctement, j'ai simplement énoncé toutes
les différentes conditions pour représenter les signaux

NRZ : si le signal est 1, alors on dessine un trait en haut
sinon, sur le bas. Pour chaque signal, si le prochain est 
différent, on fait aussi un trait vertical pour créer une
liaison entre les deux.

Manchester : si le signal est 1, on part de 1 en allant 
vers 0, sinon, c'est l'inverse. quand deux signaux à la
suite sont identiques, on créé une liaison entre les deux.

Manchester Différentiel : on commence en bas par défaut.
chaque signal 1 d'affilée inverse le sens du signal. si
on trouve un signal 0, celui-ci se dessine de la même 
façon que le signal précédent. lorsque l'on passe de 0
à 1, alors le signal s'inverse.

Miller : lorsque c'est 1, le signal change, passant de 
0 à 1 ou de 1 à 0, et quand c'est 0, le signal ne change
pas, il reste continu.



# 3) 
Pour se faire, j'ai décidé d'avoir une classe principale
contenant tous les éléments de ma fenêtre. le signal, le 
choix du type à dessiner, et un bouton pour dessiner.

on vérifie que le signal donné est correct, sinon on lève
une exception qui nous indique où se trouve l'erreur dans
le signal que l'on a donné. 

il y a un panel qui sert à dessiner le signal. celui-ci a
accès à des classes qui implémentent l'interface 
IDrawableSignal qui permet de dessiner un signal complet
en fonction du type de signal à dessiner (NRZ, Manchester,
Manchester Différentiel, Miller).

chacune de ces classes a sa propre implémentation. j'ai 
choisi d'utiliser une interface pour éviter d'avoir un
gros pavé de code sur un même fichier.

aussi, si l'on décide d'ajouter un nouveau type de signal
plus tard, il est simplement nécessaire d'implémenter 
l'interface IDrawableSignal et d'ajouter l'instance de
cette nouvelle classe dans notre HashMap<Type, Instance>
permettant ainsi dynamiquement en fonction du type de signal
d'appeler la méthode draw de la bonne implémentation.



# 4) 
![test 1](http://storage.alnmrc.com/7d9629db9a3d453a8d63338256efdff2.png "Test NRZ")
![test 2](http://storage.alnmrc.com/1c4fb5f329c24a7497a1fda14aa93a41.png "Test Manchester")
![test 3](http://storage.alnmrc.com/33082f0f04b24ea5aab03092d54f6cc5.png "Test Manchester Différentiel")
![test 4](http://storage.alnmrc.com/0c9d58eaf3d54399b1987a9554af2d17.png "Test Miller")



# 5) 
Au niveau des difficultés rencontrées, comprendre les 
différents types de signaux, pour correctement les 
dessiner, et respecter chacunes de leurs différentes
conditions.

Pour des perspectives d'évolution du programme : ajout
automatique de l'instance d'une classe implémentant 
IDrawableSignal avec son type associé en créant un 
attribut indiquant (avec final) le type du signal, et
en utilisant la Reflection en Java, pour récupérer et
instancier chacune des classes, pour les ajouter au
HashMap automatiquement à la création de notre Panel
de dessin.