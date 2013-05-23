/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Date;

/**
 *
 * @author pksm3
 */
public class FinishedExam {
    private int finishedExam_id;
    private java.util.Date finishDate;
    
    public FinishedExam(int submitted, Date endDate){
        this.finishedExam_id=submitted;
        this.finishDate=endDate;
    }



    public java.util.Date getFinishDate() {
        java.util.Date var = new java.util.Date(finishDate.getTime());
        return var;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public int getFinishedExam_id() {
        return finishedExam_id;
    }

    public void setFinishedExam_id(int finishedExam_id) {
        this.finishedExam_id = finishedExam_id;
    }


}
