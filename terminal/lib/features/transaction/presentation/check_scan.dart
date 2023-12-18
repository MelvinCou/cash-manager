import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_zxing/flutter_zxing.dart';
import 'package:terminal/features/transaction/domain/usecase/get_check_data.dart';

class CheckScan extends StatelessWidget {
  const CheckScan({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ReaderWidget(
        onScan: (result) async {
          GetCheckData getCheckData = GetCheckData();
          print(getCheckData(params: result));
        },
      ),
    );
  }
}
