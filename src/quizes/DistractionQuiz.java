/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import quizes.models.StatementModel;

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

    private static ArrayList<StatementModel> statements = new ArrayList<>();

    public static void main(String[] args) {

        loadTest();
        //printAllStatements();
        startQuiz();
    }

    private static void loadTest() {
        resetTest();
        StatementModel statement = new StatementModel("This is the first statement");
        statement.getQuestionMap().put("Question1", "CorrectAnswer1");
        statement.getQuestionMap().put("Question2", "CorrectAnswer2");

        statements.add(statement);

        statement = new StatementModel("This is the second statement");
        statement.getQuestionMap().put("Question3", "CorrectAnswer3");
        statement.getQuestionMap().put("Question4", "CorrectAnswer4");

        statements.add(statement);

        statement = new StatementModel("This is the third statement");
        statement.getQuestionMap().put("Question5", "CorrectAnswer5");
        statement.getQuestionMap().put("Question6", "CorrectAnswer6");

        statements.add(statement);

    }

    private static void resetTest() {
        statements = new ArrayList<>();

    }

    private static void resetScores() {
        score = 0;
        totalQuestionsAsked = 0;
        totalTestSubjects = 0;

    }

    private static void printAllStatements() {
        Iterator<StatementModel> iter = statements.iterator();

        System.out.println("Here is a list of statements and questions and their answers used in this program.");
        while (iter.hasNext()) {
            StatementModel statement = iter.next();
            System.out.println(statement.getStatement());
            Map<String, String> questionMap = statement.getQuestionMap();
            printAllQuestions(questionMap);

            System.out.println("\n\n");
        }

    }

    private static void printAllQuestions(Map<String, String> questionMap) {
        Set keySet = questionMap.keySet();
        Iterator<String> keyIterator = keySet.iterator();

        System.out.println();
        while (keyIterator.hasNext()) {
            String question = keyIterator.next();
            System.out.println("Question: " + question + generateWhiteSpace(30, question.length()) + "Answer: " + questionMap.get(question));

        }

        sleep(5);

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

    public static void startQuiz() {
        Iterator<StatementModel> iter = statements.iterator();
        while (iter.hasNext()){
            StatementModel statement = iter.next();
            System.out.println(statement.getStatement() + "\n");
            sleep(STATEMENT_DISPLAY_TIME_SECONDS);
            System.out.println(CLEAR_SCREEN);
            System.out.println("Now you will be prompted with two questions about the previous statement. \nEach question has a word and 4 choices. \nChoose the word that appeared with the sentence.");
            sleep(INSTRUCTION_TIME_SECONDS);
            System.out.println(CLEAR_SCREEN);
            for (Map.Entry<String, String> questions : statement.getQuestionMap().entrySet()) {
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

    public static void sleep(int timeInSeconds) {
        int timeInms = (timeInSeconds * 1000);
        try {
            Thread.sleep(timeInms);
        } catch (InterruptedException e) {
            System.out.println("Interuppted Exception: " + e);
        }

    }
}
