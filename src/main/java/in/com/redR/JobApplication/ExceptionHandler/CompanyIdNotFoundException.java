package in.com.redR.JobApplication.ExceptionHandler;

public class CompanyIdNotFoundException extends RuntimeException{
    private final Long id;
    public CompanyIdNotFoundException(String message, Long id){
        super(message);
        this.id=id;
    }

    public Long getId(){
        return id;
    }
}
