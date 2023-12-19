import 'package:terminal/common/data_state.dart';
import 'package:terminal/common/usecase.dart';
import 'package:terminal/features/product/domain/entity/product.dart';
import 'package:terminal/features/product/domain/repository/product.dart';

class GetProduct implements UseCase<Product?, int> {
  final ProductRepository productRepository;

  GetProduct({required this.productRepository});

  @override
  Future<Product?> call({int? params}) async {
    if(params != null){
      DataState result = await productRepository.getProduct(params);
      try {
        if (result is DataSuccess) {
          //return product
          return result.data;
        } else {
          throw Exception("This product is not found ; ${result.errorMessage}");
        }
      } catch (err) {
        // return null TODO à changer
        return null;
      }
    }
    else{
      return null;
    }
  }
}
