import 'package:terminal/common/status.dart';

abstract class TransactionRepository {
  Future<void> postTransaction();
  Future<Status> getTransactionStatus();
}
