/// ressource : https://dart.dev/language/branches#exhaustiveness-checking
///   This file define  each state of the the data between the app and the server. This allow clear and distinct identification of the characteristic of each state
///   The issues faced when handling asynchronous data in flutter: less readabily , difficulty to handle error , more difficult to handle each state of data on ui.
/// eg:
///  Success<int> ss = Success(5);
/// Error<String> ee = Error("err");
/// DataState testExhaustiveness(DataState st) => switch(st){
///   Success() => Success("successData"),
///   Error() => Error("err"),
///   Pending() => Pending("dataPendingFor")
/// };
// Define each state of data.  This enhances code conciseness and flexibility.
enum Status { success, error, pending }

// abstract class using sealed key word
//
sealed class DataState<T> {
  final T? data;
  final Object? error;
  final Status state;

  DataState(this.data, this.error, {required this.state});

  T get dataValue => data!;
}

class Success<T> extends DataState {
  Success(T successData) : super(successData, null, state: Status.success);
}

class Error<T> extends DataState {
  Error(Object err) : super(null, err, state: Status.error);
}

class Pending<T> extends DataState {
  Pending(T dataPendingFor) : super(null, null, state: Status.pending);
}
