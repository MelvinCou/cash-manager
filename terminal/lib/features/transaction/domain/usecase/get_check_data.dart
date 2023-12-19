import 'dart:convert';

import 'package:terminal/common/data_state.dart';
import 'package:terminal/common/usecase.dart';
import 'package:terminal/features/transaction/domain/entity/check.dart';
import 'package:flutter_zxing/flutter_zxing.dart';

class GetCheckData implements UseCase<DataState, Code> {
  // test Qr code scnaning with https://fr.qr-code-generator.com/
  @override
  Future<DataState> call({Code? params}) {
    late DataState result;
    late dynamic data;
    try {
      if ((params!.text == null)) {
        throw Exception("params is null");
      }
      if (params.text!.isEmpty && params.text is! String) {
        throw Exception("The scan result is not a string or empty");
      }

      //* valild json : {"checkNumber":468516,"amount":85}
      data = jsonDecode(params.text!);

      if (data["amount"] is! int || data["checkNumber"] is! int) {
        throw Exception("json data not valid");
      }

      result = Success(
          Check(amount: data["amount"], checkNumber: data["checkNumber"]));
    } catch (e) {
      result = Error(e);
    }

    return Future(() => result);
  }
}
