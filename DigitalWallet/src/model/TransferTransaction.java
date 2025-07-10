//package model;
//
//import strategy.TransactionIdGenerator;
//
//import java.util.UUID;
//
//public class TransferTransaction extends BaseTransaction{
//    private final String fromUserId;
//    private final String toUserId;
//
//    private TransferTransaction(Builder builder) {
//        super(builder);
//        this.fromUserId = builder.fromUserId;
//        this.toUserId = builder.toUserId;
//    }
//
//    public static class Builder extends BaseTransaction.Builder<Builder> {
//        private String fromUserId;
//        private String toUserId;
//
//        public Builder(TransactionIdGenerator idGenerator, String userId) {
//            super(idGenerator, userId);
//        }
//
//        public Builder fromUserId(String fromUserId) {
//            this.fromUserId = fromUserId;
//            return this;
//        }
//
//        public Builder toUserId(String toUserId) {
//            this.toUserId = toUserId;
//            return this;
//        }
//
//        @Override
//        protected Builder self() {
//            return this;
//        }
//
//        @Override
//        public TransferTransaction build() {
//            return new TransferTransaction(this);
//        }
//    }
//
//    public String getFromUserId() {
//        return fromUserId;
//    }
//
//    public String getToUserId() {
//        return toUserId;
//    }
//}
