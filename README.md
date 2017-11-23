# Java Bowling Kata

This is simply a few variation of the traditionnal bowling coding Kata, 
written in Java. The repo contains various solutions, which
describe different way of understanding the problem.

## How to run

This is a maven project, so if you are good to make maven build, just `mvn clean test`

## Description of the solutions

1. `RecBowling` An elegant, functionnal, recursive solution. It simply sum the score of 
the current frame and the score of the reset of the frame, until it detect the last frame.
The simplest and smallest solution I was able to code.
2. `OOBowling` An Object Oriented solution. The Game track the frame, creating new one
when necessary; while the Frame tracks how a roll increase its own score, and whether 
it is complete (i.e a new frame need to be created).
3. `StateBowling` A solution that modelize the game as a state machine, using immutable 
object that represent the game state. Rather than a frame object tracking its score, the 
game use a `Bonus` state machine that track by how much a given roll increase the score.
An interesting take on how to compute the score