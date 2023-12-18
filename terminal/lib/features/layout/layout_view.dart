import 'package:auto_route/annotations.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:terminal/features/home/home_page.dart';
import 'package:terminal/features/product/app/pages/list_of_products/products_view.dart';

@RoutePage()
class LayoutPage extends StatefulWidget{
  @override
  State<LayoutPage> createState() => _LayoutPageState();
}

class _LayoutPageState extends State<LayoutPage> {
  var _selectedIndex = 0;
  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    Widget page;
    switch (_selectedIndex) {
      case 0:
        page = MyHomePage();
        break;
      case 1:
        page = ProductListPage();
        break;
      default:
        throw UnimplementedError('no widget for $_selectedIndex');
    }

    return LayoutBuilder(
        builder: (context, constraints) {
          return Scaffold(
            appBar: AppBar(
              backgroundColor: Theme.of(context).colorScheme.inversePrimary,
              title: Text("My SHOP"),
            ),
            body: Center(
              child: page
            ),
            bottomNavigationBar: BottomNavigationBar(
              items: const <BottomNavigationBarItem>[
                BottomNavigationBarItem(
                  icon: Icon(Icons.home),
                  label: 'Home',
                ),
                BottomNavigationBarItem(
                  icon: Icon(Icons.shopping_bag),
                  label: 'Shop',
                ),
              ],
              currentIndex: _selectedIndex,
              backgroundColor: Theme.of(context).colorScheme.primary,
              selectedItemColor: Colors.lime,
              onTap: _onItemTapped,
            ),
            // This trailing comma makes auto-formatting nicer for build methods.
          );
        }
    );
  }
}
