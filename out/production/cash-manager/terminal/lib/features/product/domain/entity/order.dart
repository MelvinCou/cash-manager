import 'package:equatable/equatable.dart';
import 'package:terminal/features/product/domain/entity/product.dart';

class Order extends Equatable {
  final int totalPrice;
  final List<Product> productList;

  const Order(this.totalPrice, {required this.productList});

  @override
  List<Object?> get props => [totalPrice, productList];
}
