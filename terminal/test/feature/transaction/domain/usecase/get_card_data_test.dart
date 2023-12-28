import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';
import 'package:nfc_manager/nfc_manager.dart';
import 'package:terminal/common/data_state.dart';
import 'package:terminal/features/transaction/domain/entity/credit_card.dart';
import 'package:terminal/features/transaction/domain/usecase/get_card_data.dart';

import './../../../../helpers/test_helper.mocks.dart';

void main() {
  late MockNfcManager mockNfcManager;
  late GetCardData getCardData;
  setUp(() {
    mockNfcManager = MockNfcManager();
    getCardData = GetCardData();
  });
  test("throw exception on no params ", () async {
    final result = await getCardData(params: null);

    expect(result, isA<Error>());
    expect((result as Error).error, isA<Exception>());
    expect(result.error, equals(Exception("no params")));
  });
  test(" throw exception if no tag dat on ndef format", () async {
    // arrange
    when(mockNfcManager.startSession(onDiscovered: argThat(isNull)))
        .thenThrow(Exception('No tag data on ndef format'));

    // Act
    var call = await getCardData(params: mockNfcManager);

    // Assert
    expect(call, throwsException);
  });
// // test availability too
// TODO
//   test("throw Error on no ndef message from tag", () async {
//     when(mockNfcManager.startSession(onDiscovered: (tag){var ndef = Ndef.from(tag);})).thenThrow(Exception("no "));
//   });
//   test("throw exception on no record on message", () {});
//   test("throw exception on bat header in record ", () {});
//   test("throw exception on playload text not encoded with UTF-8", () {});
//   test("throw exception on bad format on record", () {});
  test("should return card data ", () async {
    // arrange
    GetCardData getcardData = GetCardData();

    CreditCard dummyWaitingCardData = CreditCard(
      cardNumber: 1234126854,
      expire: DateTime.utc(2065, 11, 9),
    );
    when(mockNfcManager.isAvailable()).thenAnswer((_) async => true);

    when(mockNfcManager.startSession(onDiscovered: anyNamed("onDiscovered")))
        .thenAnswer((_) async => Success(dummyWaitingCardData));

    // act
    DataState result = await getcardData(params: mockNfcManager);

    // assert
    expect(result, isA<Success>());
    expect((result as Success).data, isA<CreditCard>());
    expect(result, equals(dummyWaitingCardData));
  });
}
