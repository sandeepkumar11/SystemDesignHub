package strategy.impl;

import strategy.TransactionIdGenerator;

import java.util.UUID;

public class UUIDIdGenerator implements TransactionIdGenerator {

    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
