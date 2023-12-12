import 'dart:convert';

import 'package:equatable/equatable.dart';
import 'package:terminal/common/usecase.dart';
import 'package:terminal/features/transaction/domain/entity/check.dart';
import 'package:flutter_zxing/flutter_zxing.dart';

class GetCheckData implements Equatable, UseCase<Check, Code> {
  @override
  Future<Check> call({Code? params}) {
    late Check result;
    if (params?.text.runtimeType == String) {
      // params is not null and scan result contain string
      if (params!.text!.isNotEmpty && params.text is String) {
        final data = jsonDecode(params.text!);
        result = Check(amount: data["amount"], checkNumber: data["checkNumer"]);
      } else {
        // the scan result is null or empty
      }
    } else {
      // params is null
    }
    return Future(() => result);
  }

  @override
  // TODO: implement props
  List<Object?> get props => throw UnimplementedError();

  @override
  // TODO: implement stringify
  bool? get stringify => throw UnimplementedError();
}
