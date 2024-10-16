package dev.themepark.model;

public class CustomThread extends Thread {
    private Person person;

    public CustomThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {

    }

}

