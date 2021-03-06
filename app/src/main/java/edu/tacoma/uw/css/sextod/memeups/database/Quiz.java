/**
 * Quiz class that stores the questions, answers, and choices. Used for the quiz portion of the application
 * and is stored in QuestionLibrary.
 *
 * Created by kferg on 5/7/2018.
 */

package edu.tacoma.uw.css.sextod.memeups.database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Kerry Ferguson
 * @author Travis Bain
 * @author Dirk Sexton
 */
public class Quiz implements Serializable {

    /**
     * Constant for Question string
     */
    public static final String QUESTION = "question";
    /**
     * Constant for Choice string
     */
    public static final String CHOICE = "choice";
    /**
     * Constant for Answer string
     */
    public static final String ANSWER = "answer";
    //  public static final String PRE_REQS = "prereqs";

    private String mCourseId;
    private String mShortDescription;
    private String mLongDescription;
    private String mPrereqs;


    //Private Variable
    private String questions;
    private String choices;
    private String answers;

    //empty constructor
    public Quiz() {
    }

    //all parameter in Constructor
    public Quiz(String questions, String choices, String answers) {
        this.questions = questions;
        this.choices = choices;
        this.answers = answers;
    }

//    //three parameter Constructor
//    public Quiz(int _enroll_no, String _name, String _phone_number) {
//        this._enroll_no = _enroll_no;
//        this._name = _name;
//        this._phone_number = _phone_number;
//    }


    //Getters for  all fields


    public String get_questions() {
        return questions;
    }

    public String get_choices() {
        return choices;
    }

    public String get_answers() {
        return answers;
    }

    //Setters for all fields
    public void set_questions(String questions) {
        this.questions = questions;
    }

    public void set_choices(String choices) {
        this.choices = choices;
    }

    public void set_answers(String answers) {
        this.answers = answers;
    }


    /**
     * Function to parse JSON string for the fields.
     * @param quizJSON JSON String to be parsed
     * @return Returns list of Quiz objects
     * @throws JSONException
     */
    public static List<Quiz> parseQuizJSON(String quizJSON) throws JSONException {
        List<Quiz> quizList = new ArrayList<Quiz>();
        if (quizJSON != null) {
            JSONArray arr = new JSONArray(quizJSON);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                Quiz quiz = new Quiz(obj.getString(Quiz.QUESTION),
                        obj.getString(Quiz.CHOICE) ,
                        obj.getString(Quiz.ANSWER));
                quizList.add(quiz);
            }
        }
        return quizList;
    }


}
