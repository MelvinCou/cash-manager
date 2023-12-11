import 'package:flutter/material.dart';
import 'package:terminal/config/app_router.dart';
import 'package:terminal/injection_container.dart';
import 'package:mobile_scanner/mobile_scanner.dart';
import 'package:terminal/features/transaction/domain/usecase/get_check_data.dart';

void main() async {
  await initializeDependencies();
  runApp( const MyApp());
}

class MyApp extends StatelessWidget {
   const MyApp({super.key});
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
    );
  }
}
