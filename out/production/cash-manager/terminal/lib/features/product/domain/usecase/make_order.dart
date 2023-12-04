import 'package:terminal/common/data_state.dart';
import 'package:terminal/common/usecase.dart';
import 'package:terminal/features/product/domain/entity/order.dart';
import 'package:terminal/features/product/domain/repository/product.dart';

class MakeOrder implements UseCase<void, Order> {
  final ProductRepository productRepository;

  MakeOrder({required this.productRepository});
  @override
  Future<void> call({Order? params}) async {
    if (params != null) {
      try {
        DataState result = await productRepository.makeOrder(params);
        if (result is DataFailed) {
          throw Exception("do  something when you can't make orders");
        }
      } catch (e) {
        //doing something
      }
    } else {
      throw Exception("No Orders");
    }
  }
}
