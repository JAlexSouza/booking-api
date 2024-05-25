package br.com.ntconsult.bookingapi.config.exception.custom;

public class AccommodationUnavailableException extends Exception{
    public AccommodationUnavailableException(String message){
        super(message);
    }
}
