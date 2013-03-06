package Entities;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Marker {

    private String mail;
    private ArrayList<ReputationTopic> reputation_topic = new ArrayList<ReputationTopic>();
    private Date date;
    private Time time;
    private int marked_answer_id;

    public void addReputationTopic(ReputationTopic neo) {
        if (reputation_topic.size() > 0) {
            boolean flag = false;
            for (ReputationTopic r : reputation_topic) {
                if (r.getTopic_id() == neo.getTopic_id()) {
                    r.setReputation(neo.getReputation());
                    r.setTemporalReputation(neo.getTemporalReputation());
                    flag = true;
                }
            }
            if (flag == false) {
                reputation_topic.add(neo);
            }
        } else {
            reputation_topic.add(neo);
        }
    }

    public ArrayList<ReputationTopic> getReputation_topic() {
        return reputation_topic;
    }

    public void setReputation_topic(ArrayList<ReputationTopic> reputation_topic) {
        this.reputation_topic = reputation_topic;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getMarked_answer_id() {
        return marked_answer_id;
    }

    public void setMarked_answer_id(int marked_answer_id) {
        this.marked_answer_id = marked_answer_id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
