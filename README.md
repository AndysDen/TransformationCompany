# TransformationCompany
# Repository hosted to contain solution for | Part 2: Aequilibrium | The Transformation Company
Aequilibrium does love transforming… people, lives, teams, companies. And there’s no better

representation of transformation than Hasbro’s Transformers , the classic television series featuring

heroic Autobots raging their battle to destroy the evil forces of the Deceptions.


write the following in whatever language you like:

The Transformers are at war and you are in charge of settling the score! You’re to evaluate who wins a

fight between the Autobots and the Decepticons. Here are the rules.

● Strength

● Intelligence

● Speed

● Endurance

● Rank

● Courage

● Firepower

● Skill


All of these criteria are ranked from 1 to 10.


The “overall rating” of a Transformer is the following formula:(Strength + Intelligence + Speed + Endurance + Firepower)

Each Transformer must either be an Autobot or a Deception.

Your program should take input that describes a group of Transformers and based on that group displays:

a. The number of battles

b. The winning team

c. The surviving members of the losing team

The basic rules of the battle are:

● The teams should be sorted by rank and faced off one on one against each other in order to

determine a victor, the loser is eliminated

● A battle between opponents uses the following rules:

○ If any fighter is down 4 or more points of courage and 3 or more points of strength

compared to their opponent, the opponent automatically wins the face-off regardless of

overall rating (opponent has ran away)

○ Otherwise, if one of the fighters is 3 or more points of skill above their opponent, they win

the fight regardless of overall rating

○ The winner is the Transformer with the highest overall rating

● In the event of a tie, both Transformers are considered destroyed

● Any Transformers who don’t have a fight are skipped (i.e. if it’s a team of 2 vs. a team of 1, there’s
only going to be one battle)

● The team who eliminated the largest number of the opposing team is the winner
Special rules:

● Any Transformer named Optimus Prime or Predaking wins his fight automatically regardless of any other criteria

● In the event either of the above face each other (or a duplicate of each other), the game immediately ends with all competitors destroyed

# Assumption : 
All the assumption are coded and are explained as well in comments where they are coded.

Input to the system is command line,To stop giving input we just need to provide an empty String on new line.

# How to run test : 
a. All the testcases are coded in : TransformationCompanyTest class

b. With Simple Junit.jar we should be able to run the testcases.


