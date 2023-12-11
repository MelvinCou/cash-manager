import 'package:equatable/equatable.dart';
import 'package:flutter/material.dart';
import 'package:mobile_scanner/mobile_scanner.dart';
import 'package:terminal/common/usecase.dart';
import 'package:terminal/features/transaction/domain/entity/check.dart';
import 'dart:convert';

class GetCheckData implements Equatable, UseCase<Check, BarcodeCapture> {
  late final String? qrCodeRawValue;
  @override
  Future<Check> call({BarcodeCapture? params}) {
    late Check checkObject;
    try {
      detect(params!);
      //! don't know if json will be well decode to check object
      checkObject = json.decode(qrCodeRawValue!);
    } catch (e) {
      debugPrint("______________________________________________");
      debugPrint(e as String?);
    }

    return Future(() => checkObject);
  }

  void detect(BarcodeCapture capture) {
    // fit: BoxFit.contain,
    final List<Barcode> barcodes = capture.barcodes;
    for (final barcode in barcodes) {
      /// check barcodes is not empty. IF it is no qr code detected : do nothing , user can scan a qr code anytime he want
      /// check barcode.rawValue: if empty qr code data type is bad -> returen message telling user to search for good qr code
      /// check format = BarcodeFormat.qrCode
      ///
      // String checkData = "{'AccountNumber': '578985', 'Amount': 25}";
      if (barcode.format == BarcodeFormat.qrCode) {
        if (barcode.rawValue is String) {
          qrCodeRawValue = barcode.rawValue;
          debugPrint(
              'Barcode found! format:  ${barcode.format} ; rawValue: $qrCodeRawValue');
        } else {
          debugPrint("qr code raw value is not a string");
        }
      } else {
        // bad qr code format
        debugPrint('bad qr code format');
      }
    }
  }

  Check parseFromJsonToCheck(Map<String, dynamic> json) {
    return Check(amount: json["amount"], checkNumber: json["checkNumber"]);
  }

  @override
  // TODO: implement props
  List<Object?> get props => throw UnimplementedError();

  @override
  // TODO: implement stringify
  bool? get stringify => throw UnimplementedError();
}
