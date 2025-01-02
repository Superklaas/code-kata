# Bowling game

## Bowling Rules
The game consists of 10 frames. In each frame the player has two rolls to knock down 10 pins. The score for the frame is the total number of pins knocked down, plus bonuses for strikes and spares.

A spare is when the player knocks down all 10 pins in two rolls. The bonus for that frame is the number of pins knocked down by the next roll.

A strike is when the player knocks down all 10 pins on his first roll. The frame is then completed with a single roll. The bonus for that frame is the value of the next two rolls.

In the tenth frame a player who rolls a spare or strike is allowed to roll the extra balls to complete the frame. However, no more than three balls can be rolled in tenth frame.

## Requirements
Write a class Game that has two methods

void roll(int) is called each time the player rolls a ball. The argument is the number of pins knocked down.
int score() returns the total score for that game.

## Credits
Copied from [Kata-Log](https://kata-log.rocks/bowling-game-kata)

Inspired by [Uncle Bob](http://butunclebob.com/ArticleS.UncleBob.TheBowlingGameKata)

## Validation rules
10 and only 10 frames in input.

Every frame can contain: digits 1-9, X, /, -

FRAME 1-9
* max. two characters in frame
* strike: X only character in frame
* spare: two characters
  * first character can be - or digit 1-9
  * second character should be / second character
* no strike or spare: two characters
  * first character can be - or digit 1-9
  * second character can be - or digit 1-9
  * sum should be smaller than 10

FRAME 10
* max. three characters in frame
* strike: three characters
  * X first character, followed by 2 characters:
    * second character can be: X or - or digit 1-9
    * third character can be: X or - or digit 1-9 or /
  * if two digits, sum should be smaller than 10
* spare: 
  * first character can be - or digit 1-9
  * second character should be / 
  * third character can be or - or digit 1-9
* no strike or spare: two characters
  * first character can be - or digit 1-9
  * second character can be - or digit 1-9
  * sum should be smaller than 10
