/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package speedplay;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Tichnut
 */
public class Question implements Serializable{
    private String Question;
    private int numRight;
    HashMap<Integer, String> answers;

    public Question() {
        answers=new HashMap<>();
    }

    public Question(String Question, int numRight, HashMap<Integer, String> answers) {
        this.Question = Question;
        this.numRight = numRight;
        this.answers = answers;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }

    public int getNumRight() {
        return numRight;
    }

    public void setNumRight(int numRight) {
        this.numRight = numRight;
    }

    public Map<Integer, String> getAnswers() {
        return answers;
    }

    public void setAnswers(HashMap<Integer, String> answers) {
        this.answers = answers;
    }
    public void addAnswer(int num,String answer){
        answers.put(num, answer);
    }

    @Override
    public String toString() {
        return "Question{" + "Question=" + Question + ", numRight=" + numRight + ", answers=" + answers + '}';
    }
    
}
