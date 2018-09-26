/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author christopher.eckles
 */
public class DistractionQuiz {

    private static final String CLEAR_SCREEN = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";

    private static final int MARGIN = 5;
    private static final int STATEMENT_DISPLAY_TIME_SECONDS = 10;
    private static final int INSTRUCTION_TIME_SECONDS = 10;
    private static final int WAIT_AFTER_ANSWER = 10;

    private static int score = 0;
    private static int totalQuestionsAsked = 0;
    private static int totalTestSubjects = 0;
    private static String wordBank[] = {"String1", "String2", "String4", "String3"};

    public static void main(String[] args) {
        Map<String, Map<String, String>> statementMap = new HashMap<>();
        Map<String, String> questionMap = new HashMap<>();
        questionMap.put("Question1", "CorrectAnswer1");
        questionMap.put("Question2", "CorrectAnswer2");

        statementMap.put("This is the first statement", questionMap);

        questionMap = new HashMap<>();
        questionMap.put("Question3", "CorrectAnswer3");
        questionMap.put("Question4", "CorrectAnswer4");

        statementMap.put("This is the second statement", questionMap);

        questionMap = new HashMap<>();
        questionMap.put("Question5", "CorrectAnswer5");
        questionMap.put("Question6", "CorrectAnswer6");

        statementMap.put("This is the third statement", questionMap);

        for (Map.Entry<String, Map<String, String>> entry : statementMap.entrySet()) {
            System.out.println(entry.getKey() + "\n");
            sleep(STATEMENT_DISPLAY_TIME_SECONDS);
            System.out.println(CLEAR_SCREEN);
            System.out.println("Now you will be prompted with two questions about the previous statement. \nEach question has a word and 4 choices. \nChoose the word that appeared with the sentence.");
            sleep(INSTRUCTION_TIME_SECONDS);
            System.out.println(CLEAR_SCREEN);
            for (Map.Entry<String, String> questions : entry.getValue().entrySet()) {
                System.out.println("What word was associated with " + questions.getKey() + "?");
                if (printPossibleAnswers(generatePossibleAnswers(questions.getValue()), questions.getValue())) {
                    score++;
                }
                totalQuestionsAsked++;
                System.out.println(CLEAR_SCREEN);
                sleep(WAIT_AFTER_ANSWER);
                
            }

        }
    }

    private static ArrayList generatePossibleAnswers(String correctAnswer) {
        //randomize until 4 answers are generated
        //print the 4 answers with either numbers or lettesr
        ArrayList<String> answers = new ArrayList<>();
        answers.add(correctAnswer);
        while (answers.size() < 4) {
            int rand = RandomNumber.generateRandomNumber(wordBank.length);
            boolean add = true;
            String answerToAdd = wordBank[rand];
            for (String answer : answers) {
                if (answer.equals(answerToAdd)) {
                    add = false;
                }
            }
            if (add) {
                //System.out.println("Adding: " + wordBank[rand]);
                answers.add(wordBank[rand]);
            }
        }
        Collections.shuffle(answers);
        //System.out.println(answers);
        return answers;
    }

    private static Boolean printPossibleAnswers(ArrayList<String> answers, String correctAnswer) {
        int i = 0;
        int answerIndex = 0;
        int maxColumnWidth = 0;

        while (i < answers.size()) {
            int currentAnswerLength = answers.get(i).length();
            if (currentAnswerLength > maxColumnWidth) {
                maxColumnWidth = currentAnswerLength;
            }
            i++;

        }
        i = 0;

        while (i < answers.size()) {
            System.out.print((i + 1) + ". " + answers.get(i));
            if (i % 2 == 0) {
                System.out.print(generateWhiteSpace(maxColumnWidth + MARGIN, answers.get(i).length()));
            } else {
                System.out.println("");
            }
            i++;
        }
        System.out.print("\n\n Answer: ");

        //TODO: add prompt
        System.out.println("");
        if (correctAnswer.equals(answers.get(answerIndex))) {
            return true;
        }
        return false;
    }

    private static String generateWhiteSpace(int maxlength, int lengthOffset) {
        String whiteSpace = "";
        for (int i = lengthOffset; i < maxlength; i++) {
            whiteSpace = whiteSpace + " ";
        }
        return whiteSpace;
    }

    public static void sleep(int timeInSeconds) {
        int timeInms = (timeInSeconds * 1000);
        try {
            Thread.sleep(timeInms);
        } catch (InterruptedException e) {
            System.out.println("Interuppted Exception: " + e);
        }

    }
}
