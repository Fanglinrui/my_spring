package org.releaf.test.service;

public class WorldServiceWithExceptionImpl implements WorldService {
    @Override
    public void explode() {
        System.out.println("The Earth is going to explode, but with an EXCEPTION");
        throw new RuntimeException();
    }

    @Override
    public String getName() {
        return "";
    }
}
