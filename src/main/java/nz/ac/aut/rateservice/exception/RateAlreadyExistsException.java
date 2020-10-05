package nz.ac.aut.rateservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RateAlreadyExistsException extends RuntimeException {

    public RateAlreadyExistsException(String exception) {
		super(exception);
	}

}