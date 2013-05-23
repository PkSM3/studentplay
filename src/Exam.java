/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 *
 * @author pksm3
 */
public class Exam {
    private ArrayList<SubmittedExam> submExams= new ArrayList<SubmittedExam>();
    
    public void addMod(SubmittedExam neo) {
        submExams.add(neo);
    }

    public ArrayList<SubmittedExam> getSubmExams() {
        return submExams;
    }

    public void setSubmExams(ArrayList<SubmittedExam> aSubmExams) {
        submExams = aSubmExams;
    }
}
