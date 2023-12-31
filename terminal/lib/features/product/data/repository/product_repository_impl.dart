import 'dart:convert';

import 'package:terminal/common/data_state.dart';
import 'package:terminal/features/product/domain/entity/order.dart';
import 'package:terminal/features/product/domain/repository/product.dart';
import 'package:http/http.dart' as http;

import '../../../../common/apis.dart';
import '../../domain/entity/product.dart';

class ProductRepositoryImpl implements ProductRepository {
  @override
  Future<DataState> getProduct(String id) async {
    final response = await http.get(Uri.parse('')); //TODO

    if (response.statusCode == 200) {
      // If the server did return a 200 OK response,
      // then parse the JSON.
      Product product = Product.fromJson(jsonDecode(response.body) as Map<String, dynamic>);
      return Success(product);
    } else {
      List messages = ["Error"];
      return Error(messages);
    }
  }

  @override
  Future<DataState> getAllProducts() async {
    try{
      var response = await http.get(Uri.parse(Apis.fetchedProducts));
      if(response.statusCode == 200){
        List body = json.decode(response.body);
        List products = body.map((e) => Product.fromJson(e)).toList();
        return Success(products);
      }else{
        List messages = ["Error"];
        return Error(messages);
      }
    }catch(err){
      return Error([err.toString()]);
    }

  }

  @override
  Future<DataState> makeOrder(Order? order) async {
    // TODO: implement makeOrder
    throw UnimplementedError();
  }


}
