package in.com.redR.JobApplication.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CompanyIdNotFoundException.class)
    public ResponseEntity<?> handleCompanyIdNotFoundException(CompanyIdNotFoundException e ){
        ExceptionResponse resp=new ExceptionResponse(LocalDateTime.now(),e.getMessage(),"Given Id - "+ e.getId() + " not found");
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JobIdNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleJobIdNotFoundException(JobIdNotFoundException e){
        ExceptionResponse resp=new ExceptionResponse(LocalDateTime.now(), e.getLocalizedMessage(),"Given job Id not found! ");
        return new ResponseEntity(resp,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReviewIdNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleReviewIdNotFoundException(ReviewIdNotFoundException e){
        ExceptionResponse resp=new ExceptionResponse(LocalDateTime.now(), e.getMessage(),"Given review Id not found! ");
        return new ResponseEntity(resp,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoCompanyFoundException.class)
    public ResponseEntity<ExceptionResponse> handleReviewIdNotFoundException(NoCompanyFoundException e){
        ExceptionResponse resp=new ExceptionResponse(LocalDateTime.now(), e.getMessage(),"No company found! ");
        return new ResponseEntity(resp,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoJobFoundException.class)
    public ResponseEntity<ExceptionResponse> NoJobFoundException(NoJobFoundException e){
        ExceptionResponse res=new ExceptionResponse(LocalDateTime.now(),e.getMessage(),"No job Added in database");
        return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
    }

}
