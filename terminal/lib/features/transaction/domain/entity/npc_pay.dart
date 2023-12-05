import 'package:equatable/equatable.dart';

class NpcPay extends Equatable {
  final DateTime expire;
  final int cardNumber;

  const NpcPay({required this.expire, required this.cardNumber});

  @override
  List<Object?> get props => throw UnimplementedError();
}
