package in.com.redR.JobApplication.ExceptionHandler;

import java.time.LocalDateTime;

public class ExceptionResponse {
    private LocalDateTime time;
    private String message;
    private String details;

    public ExceptionResponse(LocalDateTime time,String msg,String details) {
        this.time = time;
        this.details = details;
        this.message = msg;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

}
