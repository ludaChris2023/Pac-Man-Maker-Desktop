#Name: Christopher Heffernan
#Date: 2/12/24
#Assignment Description: Creating a map maker utilizing JSON marshaling
#!/bin/bash
set -u -e
javac Game.java View.java Controller.java Model.java Json.java Pacman.java Sprite.java
java Game
