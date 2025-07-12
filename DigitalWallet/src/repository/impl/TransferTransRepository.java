//package repository.impl;
//
//import model.TransferTransaction;
//import repository.TransactionRepository;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.concurrent.ConcurrentHashMap;
//
//public class TransferTransRepository implements TransactionRepository<TransferTransaction> {
//
//    private final Map<String, TransferTransaction> transferTransactionDatabase;
//
//    public TransferTransRepository() {
//        this.transferTransactionDatabase = new ConcurrentHashMap<>();
//    }
//
//    @Override
//    public void save(TransferTransaction transferTransaction) {
//        transferTransactionDatabase.put(transferTransaction.getTransactionId(),transferTransaction);
//    }
//
//    @Override
//    public Optional<TransferTransaction> findById(String transactionId) {
//        return Optional.ofNullable(transferTransactionDatabase.get(transactionId));
//    }
//
//    @Override
//    public List<TransferTransaction> findByUserId(String userId) {
//        return List.of();
//    }
//
//    @Override
//    public void update(TransferTransaction walletTransaction) {
//        transferTransactionDatabase.put(walletTransaction.getTransactionId(), walletTransaction);
//    }
//
//    @Override
//    public void deleteById(String transactionId) {
//        transferTransactionDatabase.remove(transactionId);
//    }
//}
