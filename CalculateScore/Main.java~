/*
 * This App will monitor the 'finish_date' tests.
 * If there are 'finish date's equal or older to the current date, 
 * it'll calculate the score of each exam reviewed by the markers.
 */


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author pksm3
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        try {
            MySQL inst = new MySQL();
            ArrayList<FinishedExam> finishedTests= inst.getFinishedTests();
            Algorythms alg = new Algorythms(finishedTests);
            alg.monitorMarkedExams();
            
//            for(FinishedExam fn : finishedTests){
//                System.out.println("TestID"+fn.getFinishedExam_id()+" - Date: "+fn.getFinishDate());
//            }
            
            
            
            inst.conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
