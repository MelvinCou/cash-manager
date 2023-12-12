// ignore_for_file: avoid_print

import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:terminal/features/transaction/domain/entity/check.dart';
import 'package:terminal/features/transaction/domain/usecase/get_check_data.dart';
import 'package:terminal/injection_container.dart';
import 'package:flutter_zxing/flutter_zxing.dart';

void main() async {
  await initializeDependencies();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ReaderWidget(
        onScan: (result) async {
          //* valild json : {"checkNumer":468516,"amount":85}
          GetCheckData getCheckData = GetCheckData();
          print(getCheckData(params: result));
        },
      ),
    );
  }
}
