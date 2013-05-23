

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    //public static int [] modules = new int[10];

    public static HashMap exams = new HashMap();/* { test.id , module.id} */
    public static Exam[] submttExams = new Exam[10];
    public static Module[] modules = new Module[10];
    public static ArrayList<Marker> markersAndReputation = new ArrayList<Marker>();
    public static Exam_Markers[] exam_markers = new Exam_Markers[10];

    public static void createExamMarkers() throws IOException, 
            ClassNotFoundException, InstantiationException, 
            IllegalAccessException, SQLException {
        
            PrintWriter out = new PrintWriter(new FileWriter("../log.txt")); 
            Algorythms inst = new Algorythms();
            inst.monitorNewSubmttExams();
            MySQL mysql = new MySQL();
            mysql.conn.close();
            for (int j = 0; j < Main.exam_markers.length; j++) {
                if (Main.exam_markers[j] != null) {
                    //System.out.println("IDSubmittedExam:" + j);
                    //System.out.println("MarkersPerQuestion: " + Main.exam_markers[j].getMarkersPerQuestion().size());
                    for (int k = 0; k < Main.exam_markers[j].getMarkersPerQuestion().size(); k++) {
                        out.print(j);
                        out.print("\t" + Main.exam_markers[j].getMarkersPerQuestion().get(k).getQuestion_id());
                        for (int o = 0; o < Main.exam_markers[j].getMarkersPerQuestion().get(k).getQuestion_markers_list().size(); o++) {
                            if(Main.exam_markers[j].getMarkersPerQuestion().get(k).getQuestion_markers_list().get(o)!=null){
                            out.print("\t"
                                    + Main.exam_markers[j].
                                    getMarkersPerQuestion().get(k).
                                    getQuestion_markers_list().get(o).
                                    getMail()
                                    );
                            for(ReputationTopic rp:Main.exam_markers[j].
                                    getMarkersPerQuestion().get(k).
                                    getQuestion_markers_list().get(o).
                                    getReputation_topic())
                                out.print("\t"+rp.getTopic_id()+
                                        "\t"+(rp.getReputation()-rp.getTemporalReputation())+" ");
                                //TempRep always give me negative, 
                                //so I supossed that is the Reputation's decreasing factor
                            }
                            else out.print("\tnone");
                            out.println();
                        }
                    }
                } else {
                    j++;
                }
                out.println();
            }
            out.close();
            //All this must be saved in the database:
            /*   IDSubmittedExam | QuestionID | MarkerMail | TopicID |  TemporalReputation(*)  */
        
    }


    public static void main(String[] args) 
            throws IOException, ClassNotFoundException, 
            InstantiationException, IllegalAccessException, 
            SQLException {
        createExamMarkers();
    }
}
