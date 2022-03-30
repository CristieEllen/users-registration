package br.com.project.register.exceptions;

public class chooseMoreThanAllowed extends RuntimeException{

    public chooseMoreThanAllowed(String msg){
        super(msg);
    }
}
