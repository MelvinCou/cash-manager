import 'package:equatable/equatable.dart';

class CreditCard extends Equatable {
  final DateTime expire;
  final int cardNumber;

  const CreditCard({required this.expire, required this.cardNumber});

  @override
  List<Object?> get props => throw UnimplementedError();
}
