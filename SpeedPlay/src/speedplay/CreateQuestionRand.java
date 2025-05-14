/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package speedplay;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Tichnut
 */
public class CreateQuestionRand {

    //private Question question;
    private int type;
    //מייצג את מספר סוגי השאלות הקיימים
    private int numOfType;
    private Random random;
    private static CreateQuestionRand createQuestionRand = null;
//רשימת שאלות התחלתית
    //כשתיגמר הרשימה השאלות יוגרלו
    private ArrayList<Question> questionsStart;
    
    private CreateQuestionRand() {
        
        type = -1;
        numOfType = 4;
        random = new Random();
        questionsStart = new ArrayList<>();
       // fillListQuestion();
        
    }
    
    private void fillListQuestion() {
        Question new_Question = new Question();
        new_Question.setQuestion(" במערכת מרובת מעבדים קיימים שני תהליכים בעלי מזהים 0 ו–1 , המעוניינים לבצע את \n"
                + "קטע הקוד הקריטי הבא: \narr[i]=true; //acquire\n"
                + "while(arr[1-i]); //wait\n"
                + "[critical section]\n"
                + "arr[i]=false; //release\n\n"+"הערה:"+"arr[2] "+" הוא משתנה גלובלי מטיפוס בוליאני.\n"
                + "האם קטע הקוד הנתון מבטיח לתהליך "+" i "+" מניעה הדדית "+")כלומר,"+" קטע הקוד הקריטי לא "
                + "יבוצע בו זמנית על ידי יותר מתהליך אחד("+", וכן מונע עבורו מצב של קיפאון "+" (DeadLock)?");
        new_Question.addAnswer(0, "הוא לא מבטיח מניעה הדדית וכן מונע קיפאון. ");
        new_Question.addAnswer(1, "הוא מבטיח מניעה הדדית וכן מונע קיפאון.");
        new_Question.addAnswer(2, " .הוא לא מבטיח מניעה הדדית ולא מונע קיפאון");
        new_Question.addAnswer(3, " .הוא מבטיח מניעה הדדית ולא מונע קיפאון");
        new_Question.setNumRight(3);
        questionsStart.add(new_Question);
        
    }
    
    public static CreateQuestionRand getCreateQuestionRand() {
        if (createQuestionRand == null) {
            createQuestionRand = new CreateQuestionRand();
        }
        return createQuestionRand;
    }
    
    public Question getNextQuestion() {
        type = (type + 1) % numOfType;
        switch (type) {

            case 0:
                return getQuestionSum();
            case 1:
                return getQuestionMult();
            case 2:
                return getQuestionSub();
            case 3:
                return getQuestionDiv();
        }
        return null;
    }
    
    private Question getQuestionSum() {
        int num1 = random.nextInt(1000) + 11;
        int num2 = random.nextInt(1000) + 11;
        int sum = num1 + num2;
        int numRight = random.nextInt(4);
        //הסטייה של התשובה השגויה
        int numdeviation = 0;
        String question = "מהו פתרון התרגיל( נא לא להעזר במחשבון...):"
                + "\n" + num1 + "+" + num2 + "?";
        Question newQuestion = new Question();
        newQuestion.setQuestion(question);
        newQuestion.setNumRight(numRight);
        newQuestion.addAnswer(numRight, String.valueOf(sum));
        for (int i = 0; i < 4; i++) {
            if (i != numRight) {
                numdeviation = random.nextInt(40) - 20;
                if(numdeviation==0)
                {
                    i--;
                    continue;
                }
                newQuestion.addAnswer(i, String.valueOf(sum + numdeviation));
            }
        }
        return newQuestion;
    }
    
    private Question getQuestionMult() {
        int num1 = random.nextInt(90) + 11;
        int num2 = random.nextInt(90) + 11;
        int mult = num1 * num2;
        int numRight = random.nextInt(4);
        //הסטייה של התשובה השגויה
        int numdeviation = 0;
        String question = "מהו פתרון התרגיל( נא לא להעזר במחשבון...):"
                + "\n" + num1 + "*" + num2 + "?";
        Question newQuestion = new Question();
        newQuestion.setQuestion(question);
        newQuestion.setNumRight(numRight);
        newQuestion.addAnswer(numRight, String.valueOf(mult));
        for (int i = 0; i < 4; i++) {
            if (i != numRight) {
                numdeviation = random.nextInt(40) - 20;
                 if(numdeviation==0)
                {
                    i--;
                    continue;
                }
                newQuestion.addAnswer(i, String.valueOf(mult + numdeviation));
            }
        }
        return newQuestion;
    }
    
    private Question getQuestionSub() {
        int num1 = random.nextInt(1000) + 11;
        int num2 = random.nextInt(1000) + 11;
        int rest = num1 - num2;
        int numRight = random.nextInt(4);
        //הסטייה של התשובה השגויה
        int numdeviation = 0;
        String question = "מהו פתרון התרגיל( נא לא להעזר במחשבון...):"
                + "\n" + num1 + "-" + num2 + "?";
        Question newQuestion = new Question();
        newQuestion.setQuestion(question);
        newQuestion.setNumRight(numRight);
        newQuestion.addAnswer(numRight, String.valueOf(rest));
        for (int i = 0; i < 4; i++) {
            if (i != numRight) {
                numdeviation = random.nextInt(40) - 20;
                 if(numdeviation==0)
                {
                    i--;
                    continue;
                }
                newQuestion.addAnswer(i, String.valueOf(rest + numdeviation));
            }
        }
        return newQuestion;
    }
    
    private Question getQuestionDiv() {
        int num1 = random.nextInt(90) + 11;
       
        
        int div = random.nextInt(14) + 2;
        int num2 =num1*div; 
        int numRight = random.nextInt(4);
        //הסטייה של התשובה השגויה
        int numdeviation = 0;
        String question = "מהו פתרון התרגיל( נא לא להעזר במחשבון...):"
                + "\n" + num1 + "/" + num2 + "?";
        Question newQuestion = new Question();
        newQuestion.setQuestion(question);
        newQuestion.setNumRight(numRight);
        newQuestion.addAnswer(numRight, String.valueOf(div));
        for (int i = 0; i < 4; i++) {
            if (i != numRight) {
                numdeviation = random.nextInt(40) - 20;
                 if(numdeviation==0)
                {
                    i--;
                    continue;
                }
                newQuestion.addAnswer(i, String.valueOf(div + numdeviation));
            }
        }
        return newQuestion;
    }
    
}
