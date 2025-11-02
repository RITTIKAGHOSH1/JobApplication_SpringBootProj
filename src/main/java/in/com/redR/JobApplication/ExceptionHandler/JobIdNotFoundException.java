package in.com.redR.JobApplication.ExceptionHandler;

public class JobIdNotFoundException extends RuntimeException{
    public JobIdNotFoundException(String msg){
        super(msg);
    }
}
