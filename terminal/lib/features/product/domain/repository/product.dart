import 'package:terminal/common/data_state.dart';
import 'package:terminal/features/product/domain/entity/order.dart';

abstract class ProductRepository {
  Future<DataState> getProduct(String id);
  Future<DataState> getAllProducts();
  Future<DataState> makeOrder(Order order);
}
