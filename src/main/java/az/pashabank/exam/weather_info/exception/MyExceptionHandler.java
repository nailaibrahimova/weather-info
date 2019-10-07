package az.pashabank.exam.weather_info.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MyExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationExceptions(MethodArgumentNotValidException exp) {
        return exp.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    }

//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @org.springframework.web.bind.annotation.ExceptionHandler(WeatherException.class)
//    public String handleWeatherException(WeatherException exp){
//        return exp.getMessage();
//    }
}
