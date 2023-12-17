enum Status {
  success,
  error,
  pending,
  paymentPending,
  inProgress,
  over,
  refused,
  aborted,
}

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
  final T? dataPendingFor;

  Pending(this.dataPendingFor) : super(null, null, state: Status.pending);
}

Success<int> ss = Success(5);
Error<String> ee = Error("err");
// DataState wtf()=>switch (DataState) {
//   Success(int vs) => Success(vs),
//   Error() => Error()
//   // TODO: Handle this case.
//   Type() => null,
// };
// DataSuccess? test() => switch(DataState){
//   DataSuccess(state:var state, data: var data) => DataSuccess(state: state, data: data)
//   // TODO: Handle this case.
//   Type() => null,
// };

sealed class Shape {}

class Square implements Shape {
  final double length;
  Square(this.length);
}

class Circle implements Shape {
  final double radius;
  Circle(this.radius);
}

Shape calculateArea(Shape shape) => switch (shape) {
      Square(length: var l) => Square(l * l),
      Circle(radius: var r) => Circle(r * r)
    };
Square sh = Square(5);
Shape val = calculateArea(sh);
