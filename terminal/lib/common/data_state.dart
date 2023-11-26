abstract class DataState<T> {
  final T? data;
  final List? errorMessage;

  DataState({this.data, this.errorMessage});
}

class DataSuccess<T> extends DataState {
  DataSuccess(T data) : super(data: data);
}

class DataFailed<T> extends DataState {
  DataFailed(List? error) : super(errorMessage: error);
}
