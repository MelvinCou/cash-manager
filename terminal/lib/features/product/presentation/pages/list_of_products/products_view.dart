import 'package:auto_route/annotations.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:intl/intl.dart';
import 'package:terminal/features/product/presentation/pages/list_of_products/bloc/products_bloc.dart';
import 'package:terminal/features/product/domain/entity/product.dart';
import 'package:terminal/injection_container.dart';

@RoutePage()
class ProductListPage extends StatefulWidget {

  @override
  State<ProductListPage> createState() => _ProductListPageState();
}

class _ProductListPageState extends State<ProductListPage> {
  @override
  void initState(){
    super.initState();

  }
  List<Product> productsApi = [];

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
        create: (_) => ProductsBloc(
            getAllProducts: getIt())..add(ProductsFetched()),
        child: buildProducts());
  }
}

  Widget buildProducts() {
    var euroFormat = NumberFormat.currency(locale: "fr_FR",symbol: "€");

    return BlocBuilder<ProductsBloc, ProductsState>(
        builder:(context, state) {
          switch (state.status){
            case ProductsStatus.failure:
              return Text("Failed to fetch Products",
                style: TextStyle(fontSize: 25),);
            case ProductsStatus.success:
              if(state.products.isEmpty){
                return Text("No Products available",
                    style: TextStyle(fontSize: 25));
              }
              return ListView.builder(
                itemCount: state.products.length,
                itemBuilder: (context, index) {
                  final product = state.products[index];
                  return Container(
                    decoration: BoxDecoration(
                        border: Border.all(color: Colors.lime, width: 1),
                        borderRadius: BorderRadius.circular(20)),
                    margin: EdgeInsets.symmetric(vertical: 5, horizontal: 10),
                    padding: EdgeInsets.symmetric(vertical: 5, horizontal: 5),
                    height: 100,
                    width: double.maxFinite,
                    child: Row(
                      children: [
                        Expanded(flex: 1, child: Placeholder()),
                        SizedBox(width: 10),
                        Expanded(flex: 3, child: Text(product.name, style: TextStyle(fontSize: 20))),
                        Text("PRICE : ${euroFormat.format(product.price)}", style: TextStyle(fontSize: 15)),
                        Expanded(child: Column(
                          children: [
                            SizedBox(height: 20),
                            IconButton(
                              icon: Icon(Icons.add_shopping_cart_rounded),
                              tooltip: "Add one in the shopping cart" ,
                              disabledColor: Colors.black12,
                              onPressed: product.stock > 0 ? (){} : null,
                            ),
                            Text(product.stock == 0 ? "product unavailable" : "",
                              style: TextStyle(fontSize: 5),)
                          ],
                        )
                        ),
                      ],
                    ),
                  );
                },
              );
            case ProductsStatus.initial: return const CircularProgressIndicator();
          }
        });
  }
