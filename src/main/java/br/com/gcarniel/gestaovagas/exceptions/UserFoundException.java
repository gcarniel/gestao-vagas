package br.com.gcarniel.gestaovagas.exceptions;

public class UserFoundException extends RuntimeException{
    public  UserFoundException() {
        super("Usuário ja existe");
    }
}
