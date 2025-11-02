package in.com.redR.JobApplication.ExceptionHandler;

public class ReviewIdNotFoundException extends RuntimeException{
    public ReviewIdNotFoundException(String msg){
        super(msg);
    }
}
