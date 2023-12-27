import 'package:terminal/common/data_state.dart';
import 'package:terminal/common/usecase.dart';

class GetCardData implements UseCase<DataState, void> {
  @override
  Future<DataState> call({void params}) {
    // TODO: implement call
// bool isAvailable = await NfcManager.instance.isAvailable();

    throw UnimplementedError();
  }
}
