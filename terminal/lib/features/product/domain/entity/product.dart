import 'package:equatable/equatable.dart';

class Product extends Equatable {
  final int id;
  final String name;
  final double price;
  final int stock;

  const Product({
    required this.id,
    required this.name,
    required this.price,
    required this.stock,
  });

  factory Product.fromJson(Map<String, dynamic> json) {
    return switch (json) {
      {
      'id': int id,
      'name': String name,
      'price': double price,
      'stock': int stock,
      } =>
          Product(
            id: id,
            name: name,
            price : price,
            stock: stock,
          ),
      _ => throw const FormatException('Failed to load product.'),
    };
  }

  @override
  List<Object?> get props => [name, price, stock];
}
