Tinashe Matate
Java Assignment 4
Scotlandyard game

In this folder you will find a make file to reconstruct the .jar file using maven and also to run the .jar file by just doing 
$make run

The src folder contains my .java files and the target has the maven generated folders/files where the .jar file can be found
The pom.xml is also included.

The game is played by pressing play to see the progress per round.


THE SMART MOVEMENT
For Detectives,to move smartly,I implemented moveSmart(),which allows detectives to randomly choose one of possibilities 
where Mr X can be found and then from this random value,a shortest distance is calculated.This distance is that from the possible moves at the disposal of the given detective and the random possible node where X can be found.Where X can be found is a good guess as the last known point,together with the log of mister x is used to give this value.

Mister X can move without running into detectives,as already implemented in his move() method.
