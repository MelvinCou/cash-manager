import 'package:equatable/equatable.dart';
import 'package:terminal/common/usecase.dart';
import 'package:terminal/features/transaction/domain/entity/status.dart';

class GetStatus implements Equatable, UseCase<Status, void> {
  @override
  // TODO: implement props
  List<Object?> get props => throw UnimplementedError();

  @override
  Future<Status> call({void params}) {
    // TODO: implement call
    throw UnimplementedError();
  }

  @override
  // TODO: implement stringify
  bool? get stringify => throw UnimplementedError();
}
