
import 'package:get_it/get_it.dart';
import 'package:terminal/features/product/data/repository/product_repository_impl.dart';
import 'package:terminal/features/product/domain/repository/product.dart';
import 'package:terminal/features/product/domain/usecase/get_all_products.dart';
import 'package:terminal/features/product/domain/usecase/get_product.dart';
import 'package:terminal/features/product/domain/usecase/make_order.dart';
import 'package:terminal/features/product/presentation/pages/list_of_products/bloc/products_bloc.dart';

final getIt = GetIt.instance;
Future<void> initializeDependencies() async {
  // repository
  getIt.registerSingleton<ProductRepository>(ProductRepositoryImpl());

  // usecase
  getIt.registerSingleton<GetProduct>(GetProduct(productRepository: getIt()));
  getIt.registerSingleton<GetAllProducts>(GetAllProducts());
  getIt.registerSingleton<MakeOrder>(MakeOrder(productRepository: getIt()));


}
