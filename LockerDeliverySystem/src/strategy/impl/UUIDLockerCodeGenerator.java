package strategy.impl;

import strategy.LockerCodeGenerator;

import java.util.UUID;

public class UUIDLockerCodeGenerator implements LockerCodeGenerator {
    @Override
    public String generateCode() {
        return UUID.randomUUID().toString();
    }
}
