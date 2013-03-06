package Logic;

import DAOFactory.MySQL;
import Entities.Exam;
import Entities.Exam_Markers;
import Entities.Question_Markers;
import Entities.Marker;
import Entities.ReputationTopic;
import Entities.SubmittedExam;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import logicetoile.Main;

public class Algorythms {

    private MySQL inst;
    static final int numberOfMarkersPerQuestion=1;

    /*  Algorithm for markers selection of a single exam    */
    /**
   * Algorithm for markers selection of a single exam.
   * @param  {SubmittedExam} submitExam            The name of the event (or the events separated by spaces).
   * @return {ArrayList<Question_Markers>} Returns .
   */
    public ArrayList<Question_Markers> ASXMS(SubmittedExam submitExam) throws SQLException {
        inst.increaseAllMarkersTemporalReputation(5);
        ArrayList<Question_Markers> question_markers = new ArrayList<Question_Markers>();
        ArrayList<Integer> number_of_exam_questions = inst.getQuestionsPerExam(submitExam.getTest_id());
        for (int questionNumber : number_of_exam_questions) {
            Question_Markers neo = new Question_Markers(questionNumber, AQMS(submitExam.getModule_id(), numberOfMarkersPerQuestion));
            question_markers.add(neo);
        }
        return question_markers;
    }

    /*  Algorithm for selection of question markers */
    public ArrayList<Marker> AQMS(int question_topic, int expected_markers_number) throws SQLException {
        ArrayList<String> list_all = inst.getPossibleMarkers(question_topic);
        ArrayList<Marker> selected_markers = new ArrayList<Marker>();
        for (int markers_counter = 0; markers_counter < expected_markers_number; markers_counter++) {
            Marker new_marker = new Marker();
            new_marker = ASMS_RW(list_all, question_topic);
            if (list_all.size() > 0 && new_marker != null) {
                list_all.remove(new_marker.getMail());
            }
            selected_markers.add(new_marker);
        }
        return selected_markers;
    }

    /*  Algorithm for single marker selection using Roulette wheel  */
    public Marker ASMS_RW(ArrayList<String> markers_list, int topic) {
        float sum = 0;
        for (String mlist : markers_list) {
            for (Marker m : Main.markersAndReputation) {
                if (mlist.equals(m.getMail())) {
                    for (ReputationTopic rep : m.getReputation_topic()) {
                        if (rep.getTopic_id() == topic) {
                            sum += rep.getTemporalReputation();
                            break;
                        }
                    }
                }
            }
        }
        float RW_range = sum / markers_list.size();
        Random r = new Random();
        float roulette_position = r.nextFloat() * (0.00001f + RW_range);
        float reputation_sum = 0;
        Marker selected_marker = null;

        for (String mlist : markers_list) {
            if (selected_marker == null) {
                for (Marker m : Main.markersAndReputation) {
                    if (mlist.equals(m.getMail())) {
                        for (ReputationTopic rep : m.getReputation_topic()) {
                            if (rep.getTopic_id() == topic) {
                                reputation_sum += rep.getTemporalReputation();
                                if (reputation_sum >= roulette_position) {
                                    selected_marker = m;
                                    rep.setTemporalReputation(rep.getTemporalReputation() - 0.15f);
                                }
                                break;
                            }
                        }
                    }
                }
            } 
            else break;
        }
        return selected_marker;
    }

    public void monitorNewSubmttExams()
            throws ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            SQLException {

        inst = new MySQL();
        inst.generateSubmittedAndExpectedExams(); //Submitted Exams

        Set set = Main.exams.entrySet();
        Iterator i = set.iterator();

        while (i.hasNext()) {//while (i -> Submitted Exams) {
            Map.Entry iterator = (Map.Entry) i.next();
            System.out.println(
                    "\nExam #0" + iterator.getKey()
                    + " - #SubmittedExams: " + Main.submttExams[(int) iterator.getKey()].getSubmExams().size()
                    + " - #ExpectedSubmittedExams: " + Main.modules[(int) iterator.getValue()].getExpectedSubmittedExams());

            for (SubmittedExam sub : Main.submttExams[(int) iterator.getKey()].getSubmExams()) {

                System.out.println("\nExID: " + sub.getSubmittedExam_id() + " - email: " + sub.getMail());

                Calendar cal = Calendar.getInstance();
                Date date1 = sub.getFinishDate();
                Date date2 = cal.getTime();

                if (date1.compareTo(date2) < 0
                        || (Main.submttExams[(int) iterator.getKey()].getSubmExams().size() > 1
                        && Main.submttExams[(int) iterator.getKey()].getSubmExams().size() == Main.modules[(int) iterator.getValue()].getExpectedSubmittedExams())) {
                    //CurrentDate is > than DeadLine of the exam
                    //OR
                    //SubmittedExams == ExpectedSubmittedExams
                    //AND submittedExams > 1
                    ArrayList<Question_Markers> marksXquests = ASXMS(sub);
                    System.out.println(marksXquests.get(1).getQuestion_markers_list().get(0));
                    if (Main.exam_markers[sub.getSubmittedExam_id()] == null) {
                        Main.exam_markers[sub.getSubmittedExam_id()] = new Exam_Markers();
                        Main.exam_markers[sub.getSubmittedExam_id()].setMarkersPerQuestion(marksXquests);
                    } else {
                        Main.exam_markers[sub.getSubmittedExam_id()].setMarkersPerQuestion(marksXquests);
                    }
                }
            }
        }

    }
}
