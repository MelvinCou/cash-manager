import 'package:flutter/material.dart';
import 'package:terminal/features/transaction/presentation/check_scan.dart';
import 'package:terminal/injection_container.dart';

void main() async {
  await initializeDependencies();
  runApp( MyApp());
}

class MyApp extends StatelessWidget {
   MyApp({super.key});
  // This widget is the root of your application.

  @override
  Widget build(BuildContext context) {
    return MaterialApp.router(
      routerConfig: appRouter.config(),
      title: 'My Shop',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.lightGreen),
        useMaterial3: true,
        fontFamily: 'Roboto'
      ),
      /*home: const MyHomePage(title: 'My Shop'),*/
    );
  }
}
