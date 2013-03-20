package logicetoile;

import DAOFactory.MySQL;
import Entities.Exam;
import Entities.Exam_Markers;
import Entities.Marker;
import Entities.Module;
import Entities.ReputationTopic;
import Logic.Algorythms;
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

    public static void createExamMarkers() {
        try {
            Algorythms inst = new Algorythms();
            inst.monitorNewSubmttExams();
            MySQL mysql = new MySQL();
            mysql.conn.close();
            for (int j = 0; j < Main.exam_markers.length; j++) {
                if (Main.exam_markers[j] != null) {
                    System.out.println("ID Submitted Exam: " + j);
                    System.out.println("MarkersPerQuestion: " + Main.exam_markers[j].getMarkersPerQuestion().size());
                    for (int k = 0; k < Main.exam_markers[j].getMarkersPerQuestion().size(); k++) {
                        System.out.println("Question ID " + Main.exam_markers[j].getMarkersPerQuestion().get(k).getQuestion_id());
                        for (int o = 0; o < Main.exam_markers[j].getMarkersPerQuestion().get(k).getQuestion_markers_list().size(); o++) {
                            System.out.println("lilu: "
                                    + Main.exam_markers[j].getMarkersPerQuestion().get(k).getQuestion_markers_list().get(o));
                        }
                    }
                } else {
                    j++;
                }
                System.out.println();
            }
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

    private static void calculateExamsScore() {
        try {
            
            
            Algorythms inst = new Algorythms();
            inst.monitorMarkedExams();
            
            
            
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

    public static void main(String[] args) {
        createExamMarkers();
        System.out.println("\n*********************************************\n");
        calculateExamsScore();
    }
}
