import 'package:bloc_test/bloc_test.dart';
import 'package:equatable/equatable.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';
import 'package:terminal/features/product/domain/entity/product.dart';
import 'package:terminal/features/product/domain/usecase/get_all_products.dart';
import 'package:terminal/features/product/presentation/pages/list_of_products/bloc/products_bloc.dart';

import 'products_bloc_test.mocks.dart';

@GenerateMocks([GetAllProducts])
void main() {
  late GetAllProducts getAllProducts;
  late ProductsBloc productsBloc;
  late List<Product> products;

  setUp(() {
    EquatableConfig.stringify = true;
    getAllProducts = MockGetAllProducts();
    productsBloc = ProductsBloc(getAllProducts: getAllProducts);
    products= [const Product(id :1,name :"poivrons",price : 5.0, stock :100),
      const Product(id :1,name :"salades",price : 5.0, stock :100),
      const Product(id :1,name :"oignons",price : 5.0, stock :100)
    ];
  });

  group('Pokemon Bloc tests', () {
    blocTest<ProductsBloc, ProductsState>(
      'should succeed in fetching product.',
      setUp: () {
        when(getAllProducts.call())
            .thenAnswer((_) => Future.value(products));
      },
      build: () => productsBloc,
      act: (bloc) => bloc..add(ProductsFetched()),
      expect: () {
        const state = ProductsState();
        return [
          state.copyWith(
            status: ProductsStatus.success,
            products: products,
          ),
        ];
      },
    );

    blocTest<ProductsBloc, ProductsState>(
      'should not find product to fetch and fail',
      setUp: () {
        when(getAllProducts.call())
            .thenAnswer((_) => Future.value([]));
      },
      build: () => productsBloc,
      act: (bloc) => bloc..add(ProductsFetched()),
      expect: () {
        const state = ProductsState();
        return [
          state.copyWith(
            status: ProductsStatus.failure,
          ),
        ];
      },
    );

    blocTest<ProductsBloc, ProductsState>(
      'should fail to fetch products after error',
      setUp: () {
        when(getAllProducts.call())
            .thenThrow(StateError('fail to fetch'));
      },
      build: () => productsBloc,
      act: (bloc) => bloc..add(ProductsFetched()),
      expect: () {
        const state = ProductsState();
        return [
          state.copyWith(
            status: ProductsStatus.failure,
          ),
        ];
      },
    );
  });

}
