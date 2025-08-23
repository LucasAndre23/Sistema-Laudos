package br.com.ifdiagnosticos.patterns.singleton;

public class IDGenerator {
    private static IDGenerator instance;
    private int currentId;

    private IDGenerator() {
        this.currentId = 1000;
    }

    public static IDGenerator getInstance() {
        if (instance == null) {
            instance = new IDGenerator();
        }
        return instance;
    }

    public synchronized int getNextId() {
        return currentId++;
    }
}