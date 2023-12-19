import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_zxing/flutter_zxing.dart';
import 'package:terminal/common/data_state.dart';
import 'package:terminal/features/transaction/domain/entity/check.dart';
import 'package:terminal/features/transaction/domain/usecase/get_check_data.dart';

void main() {
  test('GetCheckData call method with valid JSON data', () async {
    // Arrange
    final useCase = GetCheckData();
    final code = Code(text: '{"checkNumber":468516,"amount":85}');

    // Act
    final result = await useCase.call(params: code);

    // Assert
    expect(result, isA<Success>());
    expect((result as Success).data, isA<Check>());
    expect((result).data.amount, equals(85));
    expect((result).data.checkNumber, equals(468516));
  });
  test('should throw exception for invalid JSON data', () async {
    // Arrange
    final useCase = GetCheckData();
    final code = Code(text: '{"checkNumber":468516,"amount":"85"}');

    // Act
    final result = await useCase.call(params: code);

    // Assert
    expect(result, isA<Error>());
    expect(result.error.toString(),
        equals(Exception("json data not valid").toString()));
  });

  test('Should return Error for empty scan data ', () async {
    // Arrange
    final useCase = GetCheckData();
    final code = Code(text: '');

    // Act
    final result = await useCase(params: code);

    // Assert
    expect(result, isA<Error>());
    expect((result as Error).error, isA<FormatException>());
  });
  test('Should return Error for no string data ', () async {
    // Arrange
    final useCase = GetCheckData();
    final code = Code(error: 'no string data');

    // Act
    final result = await useCase(params: code);

    // Assert
    expect(result, isA<Error>());
    expect((result as Error).error, isA<Exception>());
    expect(result.error.toString(), Exception("params is null").toString());
  });
  test("Should return Error for unvalid check data ", () async {
    final useCase = GetCheckData();
    final code = Code(text: '{"checkNumber":468516,"amount":HACKER}');

    final result = await useCase(params: code);

    expect(result, isA<Error>());
    expect((result as Error).error, isA<FormatException>());
  });
}
