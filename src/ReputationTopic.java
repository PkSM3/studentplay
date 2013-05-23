


public class ReputationTopic {
    private short topic_id;
    private float reputation;
    private float temporalReputation;
    
    public ReputationTopic(short topicID, float rep, float tempRep){
        topic_id=topicID;
        reputation=rep;
        temporalReputation=tempRep;
    }

    public short getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(short topic_id) {
        this.topic_id = topic_id;
    }

    public float getReputation() {
        return reputation;
    }

    public void setReputation(float reputation) {
        this.reputation = reputation;
    }

    public float getTemporalReputation() {
        return temporalReputation;
    }

    public void setTemporalReputation(float temporalReputation) {
        this.temporalReputation = temporalReputation;
    }
}
