import 'package:terminal/features/product/domain/entity/product.dart';
import 'package:terminal/features/product/domain/usecase/get_all_products.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:equatable/equatable.dart';
part 'products_event.dart';
part 'products_state.dart';

class ProductsBloc extends Bloc<ProductsEvent, ProductsState>{
  final GetAllProducts _getAllProducts;

  //constructor
  ProductsBloc({
    required GetAllProducts getAllProducts,
}) : _getAllProducts = getAllProducts,
        super(const ProductsState()){
    on<ProductsFetched>(_onProductsFetched);
  }

  Future<void> _onProductsFetched (
      ProductsFetched event,
      Emitter<ProductsState> emit) async{

    try{
      final products = await _getAllProducts.call();
      products.isEmpty ? emit(state.copyWith(status: ProductsStatus.failure))
                        : emit(state.copyWith(status: ProductsStatus.success, products: List.of(state.products)..addAll(products))
      );
    }catch(err){
      emit(state.copyWith(status: ProductsStatus.failure));

    }
  }

}