package in.com.redR.JobApplication.ExceptionHandler;

public class NoCompanyFoundException extends RuntimeException{
    public NoCompanyFoundException(String msg){
        super(msg);
    }
}
