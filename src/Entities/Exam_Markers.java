/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;

/**
 *
 * @author pksm3
 */
public class Exam_Markers {
    private ArrayList<Question_Markers> markersPerQuestion = new ArrayList<Question_Markers>();

    public ArrayList<Question_Markers> getMarkersPerQuestion() {
        return markersPerQuestion;
    }

    public void setMarkersPerQuestion(ArrayList<Question_Markers> markersPerQuestion) {
        this.markersPerQuestion = markersPerQuestion;
    }
}
