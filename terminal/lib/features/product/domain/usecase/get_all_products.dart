import 'package:terminal/injection_container.dart';

import '../../../../common/data_state.dart';
import '../../../../common/usecase.dart';
import '../entity/product.dart';
import '../repository/product.dart';

class GetAllProducts implements UseCase<List<Product>, void> {
  final ProductRepository productRepository = getIt();

  GetAllProducts();
  @override
  Future<List<Product>> call({void params}) async {
    DataState result = await productRepository.getAllProducts();
    try {
      if (result is Success) {
        //return product list
        return result.data;
      } else {
        throw Exception("Didn't get products ; ${result.error.toString()}");
      }
    } catch (err) {
      // return empty array
      return [];
    }
  }
}