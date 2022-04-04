package br.com.project.register.exceptions;

public class ChooseMoreThanAllowedException extends RuntimeException{

    public ChooseMoreThanAllowedException(String msg){
        super(msg);
    }
}
