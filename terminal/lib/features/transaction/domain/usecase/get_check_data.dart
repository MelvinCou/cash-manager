import 'package:equatable/equatable.dart';
import 'package:terminal/common/usecase.dart';
import 'package:terminal/features/transaction/domain/entity/check.dart';

class GetCheckData implements Equatable, UseCase<Check, void> {
  @override
  Future<Check> call({void params}) {
    // TODO: implement call
    throw UnimplementedError();
  }

  @override
  // TODO: implement props
  List<Object?> get props => throw UnimplementedError();

  @override
  // TODO: implement stringify
  bool? get stringify => throw UnimplementedError();
}
