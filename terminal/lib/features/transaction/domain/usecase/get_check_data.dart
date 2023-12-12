import 'dart:convert';

import 'package:equatable/equatable.dart';
import 'package:terminal/common/usecase.dart';
import 'package:terminal/features/transaction/domain/entity/check.dart';
import 'package:flutter_zxing/flutter_zxing.dart';

class GetCheckData implements Equatable, UseCase<Check, Code> {
  @override
  Future<Check> call({Code? params}) {
    throw UnimplementedError();
  }

  @override
  // TODO: implement props
  List<Object?> get props => throw UnimplementedError();

  @override
  // TODO: implement stringify
  bool? get stringify => throw UnimplementedError();
}
