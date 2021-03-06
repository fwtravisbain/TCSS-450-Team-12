/**
 * The QuestionLibrary holds all of the possible questions and answers to our quiz. It is currently static,
 * but in the future it will dynamically pull data from a database and be able to randomize our quiz.
 *
 * Created by kferg on 5/10/2018.
 */

package edu.tacoma.uw.css.sextod.memeups;

/**
 * Question Library that stores all of the quiz questions.
 *
 * @author Kerry Ferguson
 * @author Travis Bain
 * @author Dirk Sexton
 */
public class QuestionLibrary {

    private String mQuestions [] = {
            "What is your favorite type of meme?",
            "When you're behind on work but you need to finish your Buzfeed quiz so you know what " +
                    "kind of _____ you are.",
            "In what year was our great leader Harambe gruesomely murdered.",
            "What time is it for the banana.",
            "A 2017 hit, a chef is known as.",
            "Where you trynna catch me at.",
            "Where do you get your memes."

    };


    private String mChoices [][] = {
            {"General memes", "Dank memes", "Degenerate memes"},
            {"doggo", "Garlic bread", "Spaghetti sauce"},
            {"2015", "1000 BC", "He is still alive"},
            {"4:20 get lit", "Peanut butter jelly time","nap time"},
            {"Salt bae", "Dang bae", "Bae"},
            {"Inside", "At work", "Outside"},
            {"Reddit", "Instagram", "Deepweb"}
    };



    private String mCorrectAnswers[] = {"Dank memes", "Garlic bread", "2015", "Peanut butter" +
            " jelly time","Salt bae", "Outside", "Deepweb"};



    /**
     * Getter method to retrieve a question for our quiz.
     */
    public String getQuestion(int a) {
        String question = mQuestions[a];
        return question;
    }


    /**
     * Getter method to retrieve choice1 for the current question for our quiz.
     */
    public String getChoice1(int a) {
        String choice0 = mChoices[a][0];
        return choice0;
    }


    /**
     * Getter method to retrieve choice2 for the current question for our quiz.
     */
    public String getChoice2(int a) {
        String choice1 = mChoices[a][1];
        return choice1;
    }

    /**
     * Getter method to retrieve choice3 for the current question for our quiz.
     */
    public String getChoice3(int a) {
        String choice2 = mChoices[a][2];
        return choice2;
    }

    /**
     * Getter method to retrieve the answer for the current question for our quiz.
     */
    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }

}