import 'package:terminal/features/transaction/domain/entity/status.dart';

abstract class TransactionRepository {
  Future<void> postTransaction();
  Future<Status> getTransactionStatus();
}
