import 'dart:convert';

import 'package:terminal/common/data_state.dart';
import 'package:terminal/common/usecase.dart';
import 'package:terminal/features/transaction/domain/entity/check.dart';
import 'package:flutter_zxing/flutter_zxing.dart';

class GetCheckData implements UseCase<DataState, Code> {
  @override
  Future<DataState> call({Code? params}) {
    late DataState result;
    if (params is Code) {
      // params is not null and scan result contain string
      if (params.text!.isNotEmpty && params.text is String) {
        //* valild json : {"checkNumer":468516,"amount":85}
        final data = jsonDecode(params.text!);

        result = Success(
            Check(amount: data["amount"], checkNumber: data["checkNumer"]));
      } else {
        result = Error("he scan result is not a string or empty");
      }
    } else {
      result = Error("params is null");
    }
    return Future(() => result);
  }
}
