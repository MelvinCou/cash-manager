import 'dart:convert';

import 'package:terminal/common/data_state.dart';
import 'package:terminal/features/product/domain/entity/order.dart';
import 'package:terminal/features/product/domain/repository/product.dart';
import 'package:http/http.dart' as http;

import '../../domain/entity/product.dart';

class ProductRepositoryImpl implements ProductRepository {
  @override
  Future<DataState> getProduct() async {
    final response = await http.get(Uri.parse('https://jsonplaceholder.typicode.com/albums/1'));

    if (response.statusCode == 200) {
      // If the server did return a 200 OK response,
      // then parse the JSON.
      Product product = Product.fromJson(jsonDecode(response.body) as Map<String, dynamic>);
      return DataSuccess(product);
    } else {
      // If the server did not return a 200 OK response,
      // then throw an exception.
      throw Exception('Failed to load album');
    }
  }

  @override
  Future<DataState> makeOrder(Order? order) async {
    // TODO: implement makeOrder
    throw UnimplementedError();
  }
}
