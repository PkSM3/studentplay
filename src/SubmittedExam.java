/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Date;

/**
 *
 * @author pksm3
 */
public class SubmittedExam {
    private int submittedExam_id;
    private int module_id;
    private String module_name;
    private int test_id;
    private int submitted;
    private int reviewd;
    private String mail;
    private java.util.Date finishDate;
    
    public SubmittedExam(int usertestID,int modID, String modName, int test_id,int submitted, int reviewd, String mail, Date endDate){
        this.submittedExam_id=usertestID;
        this.module_id=modID;
        this.module_name=modName;
        this.test_id=test_id;
        this.submitted=submitted;
        this.reviewd=reviewd;
        this.mail=mail;
        this.finishDate=endDate;
    }

    public int getSubmittedExam_id() {
        return submittedExam_id;
    }

    public void setSubmittedExam_id(int submittedExam_id) {
        this.submittedExam_id = submittedExam_id;
    }

    public int getModule_id() {
        return module_id;
    }

    public void setModule_id(int module_id) {
        this.module_id = module_id;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public int getSubmitted() {
        return submitted;
    }

    public void setSubmitted(int submitted) {
        this.submitted = submitted;
    }

    public int getReviewd() {
        return reviewd;
    }

    public void setReviewd(int reviewd) {
        this.reviewd = reviewd;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public java.util.Date getFinishDate() {
        java.util.Date var = new java.util.Date(finishDate.getTime());
        return var;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

}
