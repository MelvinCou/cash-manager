import 'dart:convert';

import 'package:terminal/common/data_state.dart';
import 'package:terminal/common/usecase.dart';
import 'package:terminal/features/transaction/domain/entity/check.dart';
import 'package:flutter_zxing/flutter_zxing.dart';

class GetCheckData implements UseCase<DataState, Code> {
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

      //* valild json : {"checkNumer":468516,"amount":85}
      data = jsonDecode(params.text!);

      if (data["amount"] is! int || data["checkNumber"] is! int) {
        throw Exception("json data not valid");
      }

      result = Success(
          Check(amount: data["amount"], checkNumber: data["checkNumer"]));
    } catch (e) {
      result = Error(e);
    }

    return Future(() => result);
  }
}
    // if (params!.text != null) {
    //   if (params.text!.isNotEmpty && params.text is String) {
    //     //* valild json : {"checkNumer":468516,"amount":85}

    //     data = jsonDecode(params.text!);

    //     if (data["amount"].runtimeType is int ||
    //         data["checkNumer"].runtimeType is int) {
    //       result = Success(
    //           Check(amount: data["amount"], checkNumber: data["checkNumer"]));
    //     } else {
    //       result = Error("json data not valid");
    //     }
    //   } else {
    //     result = Error("The scan result is not a string or empty");
    //   }
    // } else {
    //   result = Error("params is null");
    // }