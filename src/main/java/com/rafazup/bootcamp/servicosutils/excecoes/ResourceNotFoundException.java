package com.rafazup.bootcamp.servicosutils.excecoes;

public class ResourceNotFoundException extends RuntimeException{
    
    public ResourceNotFoundException(String msg){
        super(msg);
    }
}
