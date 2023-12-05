import 'package:equatable/equatable.dart';
import 'package:terminal/common/usecase.dart';
import 'package:terminal/features/transaction/domain/entity/npc_pay.dart';

class GetNpcData implements Equatable, UseCase<NpcPay, void> {
  @override
  Future<NpcPay> call({void params}) {
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
