import 'package:equatable/equatable.dart';

class Check implements Equatable {
  final int amount;
  final int checkNumber;

  Check({required this.amount, required this.checkNumber});

  @override
  List<Object?> get props => [amount, checkNumber];

  @override
  bool? get stringify => true;
}
