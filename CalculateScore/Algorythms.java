


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Algorythms {
    private MySQL inst;
    static final int numberOfMarkersPerQuestion = 1;
    ArrayList<FinishedExam> listFN = new ArrayList<FinishedExam>();
    
    public Algorythms(ArrayList<FinishedExam> fn) {
        this.listFN = fn;
    }
    
    /**************************************************************
    /*         < ALGORYTHMS TO CALCULATE THE SCORE OF EXAMS >
     */
    
    /*Algorithm for monitoring marked exams:*/
    public void monitorMarkedExams()
            throws ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            SQLException,
            FileNotFoundException,
            IOException {
        ASCA();
        /* obtain submittedExams */
//        Set set = Main.exams.entrySet();
//        Iterator examID = set.iterator();
//        while (examID.hasNext()) {//while (i -> Submitted Exams) {
//            Map.Entry iterator = (Map.Entry) examID.next();
//            int i = (int) iterator.getKey();
//            print("exam-0" + i);
//            for (SubmittedExam e : Main.submttExams[i].getSubmExams()) {
//                if(e.getReviewd()==0){
//                    print("\tsubmExamID: " + e.getSubmittedExam_id() + " - "+ e.getMail() + " - reviewed: " + e.getReviewd());
//                }
//            }
//        }
        /* obtain submittedExams */        
    }
    
    /*Algorithm to calculate student_exam_score: ASCRX (student, exam_id) :*/
    public void ASCRX() {
        
    }

    /*Algorithm to calculate student_answer_score: ASCA (answer_id):*/
    public void ASCA() throws FileNotFoundException, IOException {
        File archivo = new File ("../log.txt");
         FileReader fr = new FileReader (archivo);
         BufferedReader br = new BufferedReader(fr);
 
         // Lectura del fichero
         String linea;
         String [] buff;
         while((linea=br.readLine())!=null) {
            buff = linea.split("\t");
            String mail = buff[2];
         }
        /*
         Get a list from the selected markers for answer_id in markers_list
         Calculate the overall markers pool reputation {
            group_reputation = 0
            for i = 1 to markers_list.length() {
                group_reputation += markers_list[i].reputation(topic)
            }
         }
         Calculating the weight of each marker for the given question/answer
         for i = 1 to markers_list.length(){
             marker_weight = markers_list[i].reputation(topic)/group_reputation
             markers_list[i].setWeight(topic, marker_weight)
         }
         Calculating the overall score of the answer {
             answer_score = 0
             for i = 1 to markers_list.length(){
                 answer_score += marker_list[i].getWeight(topic)*marker_list[i].givenScore()
             }
         }
         */
    }
    
    
    /*         </ ALGORYTHMS TO CALCULATE THE SCORE OF EXAMS >
    /****************************************************************/
}
