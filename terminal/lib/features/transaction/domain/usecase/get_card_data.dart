import 'dart:convert';

import 'package:nfc_manager/nfc_manager.dart';
import 'package:terminal/common/data_state.dart';
import 'package:terminal/common/usecase.dart';
import 'package:terminal/features/transaction/domain/entity/credit_card.dart';

class GetCardData implements UseCase<DataState, NfcManager> {
  @override
  Future<DataState> call({NfcManager? params}) async {
    late DataState result;
    try {
      if (params is! NfcManager) {
        throw Exception("np params");
      }
      if (await params.isAvailable() == false) {
        print("object");

        throw Exception("nfc manager not available");
      }
      params.startSession(onDiscovered: (NfcTag tag) {
        var ndefTag = Ndef.from(tag);
        if (ndefTag == null) {
          throw Exception("ndef is null. Tag data is not on ndef format");
        }
        if (ndefTag.cachedMessage == null) {
          throw Exception("no ndef message from tag");
        }
        var message = ndefTag.cachedMessage!;

        if (message.records.isEmpty &&
            message.records.first.typeNameFormat !=
                NdefTypeNameFormat.nfcWellknown) {
          throw Exception("no records and bad format");
        }

        final wellKnownRecord = message.records.first;

        if (wellKnownRecord.payload.first != 0x02) {
          throw Exception("no Utf-8 encoded text");
        }
        final languageCodeAndContentBytes =
            wellKnownRecord.payload.skip(1).toList();

        final languageCodeAndContentText =
            utf8.decode(languageCodeAndContentBytes);

        final payload = languageCodeAndContentText.substring(2);

        var data = jsonDecode(payload);

        result =
            Success(CreditCard(cardNumber: data["card"], expire: data["date"]));
        return Future(() => payload);
      });
      params.stopSession();
    } catch (e) {
      result = Error(e);
    }
    return result;
  }
}
