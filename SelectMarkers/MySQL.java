

import com.mysql.jdbc.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQL {

    public Connection conn = null;
    String url = "jdbc:mysql://localhost:3306/";
    String dbName = "etoile";
    String driver = "com.mysql.jdbc.Driver";
    String userName = "root";
    String password = "";

    public MySQL() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class.forName(driver).newInstance();
        conn = (Connection) DriverManager.getConnection(url + dbName, userName, password);
        System.out.println("Connected to the database!\n");
        //conn.close();
    }

    public void generateSubmittedAndExpectedExams() throws SQLException {
        Statement st = conn.createStatement();
        ResultSet res = st.executeQuery(
                "SELECT usertest.id,module.id,module.name,test.id,submitted,reviewd,user_email,test.finish_date "
                + "FROM module,lesson,test,usertest "
                + "WHERE test.id=usertest.test_id AND test.lesson_id=lesson.id AND module.id=lesson.module_id");

        while (res.next()) {
            int exam_id = res.getInt("usertest.id");
            int module_id = res.getInt("module.id");
            if (Main.modules[module_id] == null) {
                Main.modules[module_id] = new Module();
                Main.modules[module_id].setExpectedSubmittedExams(getCountMembers(module_id));
            }
            String module_name = res.getString("module.name");
            int test_id = res.getInt("test.id");
            Main.exams.put(test_id, module_id);
            int submitted = res.getInt("submitted");
            int reviewd = res.getInt("reviewd");
            String mail = res.getString("user_email");
            Date finishDate = res.getDate("test.finish_date");

            SubmittedExam neo = new SubmittedExam(exam_id, module_id, module_name, test_id, submitted, reviewd, mail, finishDate);

            if (Main.submttExams[test_id] == null) {
                Main.submttExams[test_id] = new Exam();
                Main.submttExams[test_id].addMod(neo);
            } else {
                Main.submttExams[test_id].addMod(neo);
            }
        }
    }

    private int getCountMembers(int module_id) throws SQLException {
        Statement st = conn.createStatement();
        ResultSet res = st.executeQuery("select module_id from account_module where module_id=" + module_id);
        int counter = 0;
        while (res.next()) {
            counter++;
        }
        return counter++;
    }

    public ArrayList<String> getPossibleMarkers(int module_id) throws SQLException {
        Statement st = conn.createStatement();
        ResultSet res = st.executeQuery("select account_email from account_module where module_id=" + module_id);
        ArrayList<String> list = new ArrayList<String>();
        while (res.next()) {
            list.add(res.getString("account_email"));
        }
        return list;
    }

    public ArrayList<Integer> getQuestionsPerExam(int test_id) throws SQLException {
        Statement st = conn.createStatement();
        String query =
                "SELECT question_group_question.question_id "
                + "FROM question_group_question,question_group "
                + "WHERE question_group.test_id=" + test_id + " AND "
                + "question_group_question.question_group_id=question_group.id;";
        ResultSet res = st.executeQuery(query);
        ArrayList<Integer> numberOfQuestions = new ArrayList<Integer>();
        while (res.next()) {
            numberOfQuestions.add(res.getInt("question_id"));
        }
        return numberOfQuestions;
    }

    public void increaseAllMarkersTemporalReputation(int exam_recover_rate) throws SQLException {
        Statement st = conn.createStatement();
        String query =
                "SELECT module.id,usertest.reputation_as_astudent,usertest.user_email "
                + "FROM module,lesson,test,usertest "
                + "WHERE test.id=usertest.test_id AND "
                + "test.lesson_id=lesson.id AND "
                + "module.id=lesson.module_id";

        ResultSet res = st.executeQuery(query);
        while (res.next()) {
            float rep = res.getInt("reputation_as_astudent")/100f;
            String mail = res.getString("usertest.user_email");
            ReputationTopic neo = new ReputationTopic((short) res.getInt("module.id"), rep, (rep + 0.05f));
            Marker newMarker = new Marker();

            if (Main.markersAndReputation.size() > 0) {
                boolean flag = false;
                for (Marker m : Main.markersAndReputation) {
                    if (m.getMail().equals(mail)) {
                        flag = true;
                        break;
                    }
                }
                if (flag == false) {
                    newMarker.setMail(mail);
                    newMarker.addReputationTopic(neo);
                    Main.markersAndReputation.add(newMarker);
                }
            } else {
                newMarker.setMail(mail);
                newMarker.addReputationTopic(neo);
                Main.markersAndReputation.add(newMarker);
            }
        }
    }
}
