import 'package:terminal/common/data_state.dart';
import 'package:terminal/common/usecase.dart';
import 'package:terminal/features/product/domain/entity/product.dart';
import 'package:terminal/features/product/domain/repository/product.dart';

class GetProduct implements UseCase<List<Product>, void> {
  final ProductRepository productRepository;

  GetProduct({required this.productRepository});
  @override
  Future<List<Product>> call({void params}) async {
    DataState result = await productRepository.getProduct();
    try {
      if (result is Success) {
        //return product list
        return result.data;
      } else {
        //Todo: Implement error handling
        throw Exception("Didn't get products ; result.errorMessage");
      }
    } catch (err) {
      // return empty array
      return [];
    }
  }
}
