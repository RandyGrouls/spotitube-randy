package nl.han.oose.controller;

public abstract class ErrorCheck {

    public Boolean paramIsEmpty(int parameter) {
        return parameter == 0;
    }

    public Boolean paramIsEmpty(String parameter) {
        return parameter == null || parameter.trim().isEmpty();
    }
}
