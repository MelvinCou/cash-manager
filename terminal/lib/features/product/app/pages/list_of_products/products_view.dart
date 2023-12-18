import 'package:auto_route/annotations.dart';
import 'package:flutter/material.dart';

@RoutePage()
class ProductListPage extends StatefulWidget {

  @override
  State<ProductListPage> createState() => _ProductListPageState();
}

class _ProductListPageState extends State<ProductListPage> {

  var products = ["Salade", "Tomate", "Oignon"];//TODO à supprimer

  @override
  Widget build(BuildContext context) {
        return ListView(
          children: [
            for (var product in products)
              ListTile(
                leading: Icon(Icons.add_shopping_cart),
                title: Text(product),
              ),
          ],
        );
  }
}