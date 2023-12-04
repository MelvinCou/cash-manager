import 'package:terminal/common/data_state.dart';
import 'package:terminal/features/product/domain/entity/order.dart';

abstract class ProductRepository {
  Future<DataState> getProduct();
  Future<DataState> makeOrder(Order order);
}
