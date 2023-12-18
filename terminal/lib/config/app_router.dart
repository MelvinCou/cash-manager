import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:terminal/features/home/home_page.dart';
import 'package:terminal/features/product/app/pages/list_of_products/products_view.dart';

import '../features/layout/layout_view.dart';

part 'app_router.gr.dart';


@AutoRouterConfig()
class AppRouter extends _$AppRouter {
  @override
  List<AutoRoute> get routes => [
    // add your routes here
    AutoRoute(page: LayoutRoute.page, initial: true),
    AutoRoute(page: MyHomeRoute.page),
    AutoRoute(page: ProductListRoute.page)
  ];
}
final appRouter = AppRouter();