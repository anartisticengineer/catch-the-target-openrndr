# Catch the Target
I originally created this game back in 2016 with Processing, but wanted to re-imagine it for a different, more advanced framework.
I came across OpenRNDR, and saw it as an opportunity to replicate and even expand on my original idea!

## How to Play
* Only need to use the arrow keys to control the player (the red circle).
* Press the arrow keys to change the direction of movement to hit (or avoid) the target.
* When hitting a target, a new target will appear at random on the screen.
  * The amount of points you earn depends on how quickly you hit the target. So act quickly!
* Sending your player out of bounds will result in losing a life.
* Game over when all your lives are gone.

## Target Types
* Normal Target
* Bonus Target (Gold) - Hit this target to get a 5x bonus on the points earned.
* Freeze Target (Blue) - Hit this target to slow you down a little.
* Bad Target (Pink) - Avoid this target! Hitting it means an instant game over. Wait for it to disappear after a few seconds.
* Life Target (Green) - Hit this target to get an extra life. But make it quick. It will disappear after a few seconds!

## Potential Improvements
* GUI Adjustments
* Refine speed of player movement
* Anything else I can really think of...

## Gradle tasks (Kept from Template)
- `run` runs the TemplateProgram
- `jar` creates an executable platform specific jar file with all dependencies
- `jpackageZip` creates a zip with a stand-alone executable for the current platform (works with Java 14 only)

## Cross builds (Kept from Template)
To create runnable jars for a platform different from the platform you use to build one uses `./gradlew jar --PtargetPlatform=<platform>`. The supported platforms are `windows`, `macos`, `linux-x64` and `linux-arm64`.

## Github Actions (Kept from Template)

This repository contains a number of Github Actions in `./github/workflows`.
The actions enable a basic build run on commit, plus publication actions that are executed when
a commit is tagged with a version number like `v0.*` or `v1.*`.