import 'package:equatable/equatable.dart';
import 'package:terminal/common/usecase.dart';

class Proceed implements Equatable, UseCase<void, void> {
  @override
  Future<void> call({void params}) {
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
