package nz.ac.aut.rateservice.dto;

public class RateDTO {

    private String id;
    private String jobID;
    private int rate;

    public RateDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RateDTO(String id, String jobID, int rate) {
        this.id = id;
        this.jobID = jobID;
        this.rate = rate;
    }

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

    public RateDTO(String jobID, int rate) {
        this.jobID = jobID;
        this.rate = rate;
    }

}
