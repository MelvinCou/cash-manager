part of 'products_bloc.dart';

class ProductsState extends Equatable{
  final ProductsStatus status;
  final List<Product> products;

  const ProductsState({
    this.status = ProductsStatus.initial,
    this.products = const <Product>[],
});

  ProductsState copyWith({
    ProductsStatus? status,
    List<Product>? products
}){
    return ProductsState(
      status: status ?? this.status,
      products: products ?? this.products
    );
  }

  @override
  List<Object?> get props => [status, products];

}

enum ProductsStatus {initial, success, failure}