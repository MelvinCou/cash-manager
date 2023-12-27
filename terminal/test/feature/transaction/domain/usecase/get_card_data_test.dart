import 'dart:typed_data';

import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';
import 'package:nfc_manager/nfc_manager.dart';
import 'package:terminal/common/data_state.dart';
import 'package:terminal/features/transaction/domain/entity/credit_card.dart';
import 'package:terminal/features/transaction/domain/usecase/get_card_data.dart';

import './../../../../helpers/test_helper.mocks.dart';

void main() {
  test(" throw exception if no tag dat on ndef format", () async {
    // final mockNfcManager = MockNfcManager();
    // GetCardData getcardData = GetCardData();
  });

  test("throw exception on no ndef message from tag", () {});
  test("throw exception on no record on message", () {});
  test("throw exception on bat header in record ", () {});
  test("throw exception on playload text not encoded with UTF-8", () {});
  test("throw exception on bad format on record", () {});
  test("should return card data ", () async {
    // arrange
    GetCardData getcardData = GetCardData();

    CreditCard dummyWaitingCardData = CreditCard(
      cardNumber: 1234126854,
      expire: DateTime.utc(2065, 11, 9),
    );

    // act
    DataState result = await getcardData();

    // assert
    expect(result, isA<Success>());
    expect((result as Success).data, isA<CreditCard>());
    expect(result, equals(dummyWaitingCardData));
  });
}
