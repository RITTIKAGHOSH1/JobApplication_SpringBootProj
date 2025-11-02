package in.com.redR.JobApplication.ExceptionHandler;

public class NoJobFoundException extends RuntimeException{
    public NoJobFoundException(String msg){
        super(msg);
    }
}
