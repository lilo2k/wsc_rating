package nz.ac.aut.rateservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rates")
public class Rate {
    @Id
    private String id;
    private String jobID;
    private int rate;

    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Rate(String id, String jobID, int rate) {
        this.id = id;
        this.jobID = jobID;
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Rate{" + "id='" + id + '\'' + ", Job ID='" + jobID + '\'' + ", Rate=" + rate + '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Rate() {
    }
}
