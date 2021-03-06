package nz.ac.aut.rateservice.exception;

import java.util.Date;

public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;
    private String statusCode;

    public ExceptionResponse(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public ExceptionResponse(Date timestamp, String message, String details, String statusCode) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.statusCode = statusCode;
    }
}
