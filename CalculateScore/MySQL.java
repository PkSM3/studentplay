

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

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

    public ArrayList<FinishedExam> getFinishedTests() throws SQLException {
        Statement st = conn.createStatement();
        ResultSet res = st.executeQuery("select id,finish_date from test");

        ArrayList<FinishedExam> finishedTests = new ArrayList<FinishedExam>();
        while (res.next()) {
            
            Calendar cal = Calendar.getInstance();
            java.util.Date finishDate = res.getDate("finish_date");
            java.util.Date today = cal.getTime();

            if (finishDate.compareTo(today) < 0) {
                //CurrentDate is > than DeadLine of the exam
                FinishedExam fn = new FinishedExam(res.getInt("id"),finishDate);
                finishedTests.add(fn);
            }
        }
        
        return finishedTests;
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

}
