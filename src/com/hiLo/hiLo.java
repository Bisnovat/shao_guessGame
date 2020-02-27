package com.hiLo;
//import packages
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class hiLo {
    //Define the UI elements
    private JPanel hiLoPanel;
    private JTextField nameField;
    private JTextField guessField;
    private JLabel youreWinner;
    private JButton playButton;
    private JButton quitButton;
    private JButton submitWagerButton;
    private JTextField betField;
    private JButton confirmNameButton;
    private JLabel hiName;
    private JLabel betLabel;
    private JLabel playerPointCount;
    private JButton ALLINButton;

    public static void main(String[] args) { //generate the Jframe
        JFrame frame = new JFrame("hiLo.exe");
        frame.setContentPane(new hiLo().hiLoPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    //define private variables
    private Double playerPoints = 1000.0;
    private Double wager;
    private int stopCount;
    private String callSign = "You";

    public hiLo(){
        confirmNameButton.addActionListener(new ActionListener() {
            //Listener on the button.
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //initialize variables
                String spaceDivide = " "; //This is a constraint
                String userName;
                String initializer = "";

                int[] lastNameLetters;
                lastNameLetters = new int[4]; //integer array
                String[] lastNameConverted;
                lastNameConverted = new String[4]; //String array

                String randomAnimal;
                int randomInteger;
                int indexOfSpace;
                String firstNameLetter;

                //get user input
                userName = nameField.getText();   //receive input from text field
                indexOfSpace = userName.indexOf(spaceDivide); //Find the location of the last name
                lastNameLetters[0] = indexOfSpace + 1; //find the location of the letters infront of the space is a letter in the last name
                lastNameLetters[1] = indexOfSpace + 2;
                lastNameLetters[2] = indexOfSpace + 3;
                lastNameLetters[3] = indexOfSpace + 4; //get 4 locations of the letters
                lastNameConverted[0] = String.valueOf(userName.charAt(lastNameLetters[0])); //get the letter of the last name using the location
                lastNameConverted[1] = String.valueOf(userName.charAt(lastNameLetters[1]));
                lastNameConverted[2] = String.valueOf(userName.charAt(lastNameLetters[2]));
                lastNameConverted[3] = String.valueOf(userName.charAt(lastNameLetters[3])); // 4 times
                firstNameLetter = userName.charAt(0) + initializer; //get the first letter of the name
                Random randomNumber = new Random(); //generate a random animal name
                randomInteger = randomNumber.nextInt(4); //4 times
                switch (randomInteger) {
                    case 0:
                        randomAnimal = "Raven";
                        break;
                    case 1:
                        randomAnimal = "Foxhound";
                        break;
                    case 2:
                        randomAnimal = "Tomcat";
                        break;
                    case 3:
                        randomAnimal = "Hound";
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + randomInteger); //in case of any issues for debug
                }
                callSign = lastNameConverted[0] + lastNameConverted[1] + lastNameConverted[2] + lastNameConverted[3] + firstNameLetter + randomAnimal; //puts all the variables together to generate a callsign
                hiName.setText("Howdy " + callSign);
            }
        });
        submitWagerButton.addActionListener(new ActionListener() {
            //Listener on the button.
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //initialize variables
                wager = Double.parseDouble(hiLo.this.betField.getText());//receive input from text field
                betLabel.setText("Wager: "+wager);
            }
        });
        ALLINButton.addActionListener(new ActionListener() {
            //Listener on the button.
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                wager = playerPoints;
                betLabel.setText("You sure m8? Wager: "+wager);
                submitWagerButton.setText("Nah");
            }
        });
        playButton.addActionListener(new ActionListener() {
            //Listener on the button.
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //initialize variables
                int winLose;
                Double bet;
                bet = Double.parseDouble(hiLo.this.guessField.getText());//receive input from text field
                Random winOrLose = new Random(); //randomizes the win conditions
                winLose = winOrLose.nextInt(13); //13 different numbers to chose from
                if (wager > playerPoints){
                    stopCount = 1; //Pull a stop code
                } else if (wager < playerPoints){
                    stopCount = 0; //clears the stop code if any
                } else {
                    JOptionPane.showMessageDialog(null, "You have no points. Net loss: 1000"); //Close the program when user is out of points
                    playerPointCount.setText("Your points: " + playerPoints);
                    System.exit(0); //stop the program
                }
                switch (stopCount) {
                    default: //Stop code not 1, continue
                        //Logic below
                        if (winLose <= 6 && bet == 0) {
                            playerPoints = playerPoints + wager;
                            youreWinner.setText("You Win! The number was: " + winLose);
                            playerPointCount.setText("Your points: " + playerPoints);
                        } else if (winLose <= 6 && bet == 1) {
                            youreWinner.setText("You Lose. The number was: " + winLose);
                            playerPoints = playerPoints - wager;
                            playerPointCount.setText("Your points: " + playerPoints);
                        } else if (winLose >= 8 && bet == 1) {
                            playerPoints = playerPoints + wager;
                            youreWinner.setText("You Win! The number was: " + winLose);
                            playerPointCount.setText("Your points: " + playerPoints);
                        } else if (winLose >= 8 && bet == 0) {
                            playerPoints = playerPoints - wager;
                            youreWinner.setText("You Lose. The number was: " + winLose);
                            playerPointCount.setText("Your points: " + playerPoints);
                        } else { //The house wins of the number is 7 or any forbidden number
                            playerPoints = playerPoints - wager;
                            youreWinner.setText("You Lose. The number was: " + winLose);
                            playerPointCount.setText("Your points: " + playerPoints);
                        }
                        submitWagerButton.setText("Submit Wager");//Reset the text if changed
                        break;
                    case 1: //If stopcode is true, stop the user
                        youreWinner.setText("You do not have enough money to make that bet");
                        submitWagerButton.setText("Submit Wager"); //Reset the text if changed
                        break;
                }
            }
        });
        quitButton.addActionListener(new ActionListener() {
            //Listener on the button.
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //Define Variables
                double net;
                double absoluteNet;
                net = playerPoints - 1000.0; //Calculate net points
                absoluteNet = Math.abs(net); //Determine the magnitude of the points
                if (net >= 1){
                    JOptionPane.showMessageDialog(null, callSign+" ended the day with a net gain of "+ absoluteNet+" points!");
                    System.exit(0);
                } else if(net < -1){
                    JOptionPane.showMessageDialog(null, callSign+" ended the day with a net loss of "+ absoluteNet+" points.");
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(null, callSign+" ended the day with no loss!");
                    System.exit(0);
                }
            }
        });
    }
}
