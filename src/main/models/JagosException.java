package main.models;

/**
 * Created by Kuznetsov on 25/04/2017.
 */

public class JagosException extends Exception {
    public JagosException(){
        super("Что-то пошло не так :(");
    }
}