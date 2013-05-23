


import java.util.ArrayList;


public class Question_Markers {
    private int question_id;
    private ArrayList<Marker> question_markers_list = new ArrayList<Marker>();

    public Question_Markers(int qID, ArrayList<Marker> qml){
        question_id=qID;
        question_markers_list=qml;
    }
    
    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public ArrayList<Marker> getQuestion_markers_list() {
        return question_markers_list;
    }

    public void setQuestion_markers_list(ArrayList<Marker> question_markers_list) {
        this.question_markers_list = question_markers_list;
    }
}
