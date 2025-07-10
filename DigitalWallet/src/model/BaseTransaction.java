//package model;
//
//import enums.TransactionStatus;
//import strategy.TransactionIdGenerator;
//
//import java.time.LocalDateTime;
//
//public abstract class BaseTransaction {
//    private final String transactionId;
//    private final String userId;
//    private final double amount;
//    private final TransactionStatus transactionStatus;
//    private final LocalDateTime timestamp;
//
//    protected BaseTransaction(Builder<?> builder) {
//        this.transactionId =  builder.idGenerator.generateId();
//        this.userId = builder.userId;
//        this.timestamp = builder.timestamp;
//        this.amount = builder.amount;
//        this.transactionStatus = builder.transactionStatus;
//    }
//
//    public static abstract class Builder<T extends Builder<T>> {
//        private final TransactionIdGenerator idGenerator;
//        private final String userId;
//        private double amount;
//        private TransactionStatus transactionStatus;
//        private LocalDateTime timestamp;
//
//        public Builder(TransactionIdGenerator idGenerator, String userId){
//            this.idGenerator = idGenerator;
//            this.userId = userId;
//        }
//
//        public T amount(double amount) {
//            this.amount = amount;
//            return self();
//        }
//
//        public T transactionStatus(TransactionStatus status) {
//            this.transactionStatus = status;
//            return self();
//        }
//
//        public T timestamp(LocalDateTime timestamp) {
//            this.timestamp = timestamp;
//            return self();
//        }
//
//        protected abstract T self();
//        public abstract BaseTransaction build();
//    }
//
//    public String getTransactionId() {
//        return transactionId;
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public double getAmount() {
//        return amount;
//    }
//
//    public TransactionStatus getTransactionStatus() {
//        return transactionStatus;
//    }
//
//    public LocalDateTime getTimestamp() {
//        return timestamp;
//    }
//
//}
