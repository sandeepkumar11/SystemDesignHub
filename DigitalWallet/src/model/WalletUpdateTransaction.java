//package model;
//
//import strategy.TransactionIdGenerator;
//
//import java.util.UUID;
//
//public class WalletUpdateTransaction extends BaseTransaction{
//
//    private WalletUpdateTransaction(Builder builder) {
//        super(builder);
//    }
//
//    public static class Builder extends BaseTransaction.Builder<Builder> {
//
//        public Builder(TransactionIdGenerator idGenerator, String userId) {
//            super(idGenerator, userId);
//        }
//
//        @Override
//        protected Builder self() {
//            return this;
//        }
//
//        @Override
//        public WalletUpdateTransaction build() {
//            return new WalletUpdateTransaction(this);
//        }
//    }
//
//
//}
