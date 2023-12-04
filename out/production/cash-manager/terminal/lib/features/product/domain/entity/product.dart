import 'package:equatable/equatable.dart';

class Product extends Equatable {
  final String name;
  final int price;
  final int stock;

  const Product({required this.name, required this.price, required this.stock});

  @override
  List<Object?> get props => [name, price, stock];
}
