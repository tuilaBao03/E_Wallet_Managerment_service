// ignore_for_file: file_names, library_private_types_in_public_api, prefer_final_fields, avoid_print, non_constant_identifier_names, sized_box_for_whitespace

import 'dart:collection';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:smartwalletapp/app/locallization/app_localizations.dart';
import 'package:smartwalletapp/models/create_cardholder_request.dart';
import 'package:smartwalletapp/screens/customer/components/add_customer.dart';
import 'package:smartwalletapp/screens/main/components/classInitial.dart';
import '../../../constants.dart';

class CustomerList extends StatefulWidget {
  final String token;
  final List<CreateCardHolderRequest> object;
  final String title;
  final HashSet<String> objectColumnName;
  final Function(CreateCardHolderRequest) onCustomer_Contracts;

  const CustomerList({
    super.key,
    required this.object,
    required this.objectColumnName,
    required this.title,
    required this.onCustomer_Contracts, required this.token,
  });

  @override
  _CustomerListState createState() => _CustomerListState();
}

class _CustomerListState extends State<CustomerList> {

  final TextEditingController _searchController = TextEditingController();
  List<CreateCardHolderRequest> _filteredCardHolder = [];

  @override
  void initState() {
    super.initState();
    _filteredCardHolder = widget.object;
    print("${_filteredCardHolder.length}");
  }

  @override
  void didUpdateWidget(covariant CustomerList oldWidget) {
    super.didUpdateWidget(oldWidget);

    // Nếu user thay đổi, cập nhật danh sách thẻ
    if (oldWidget.object.length != widget.object.length) {
      setState(() {
        _filteredCardHolder = widget.object;
      });
    }
  }

  @override
  void dispose() {
    _searchController.dispose();
    super.dispose();
  }

  void _searchUser() {
    String query = _searchController.text.trim().toLowerCase();
    if (query.isEmpty) {
      setState(() => _filteredCardHolder = widget.object);
      return;
    }
    setState(() {
      _filteredCardHolder = widget.object.where((user) {
        return user.firstName.toLowerCase().contains(query) ||
               user.lastName.toLowerCase().contains(query);
      }).toList();
    });
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      height: Get.height / 2,
      padding: EdgeInsets.all(defaultPadding),
      decoration: BoxDecoration(
        color: Theme.of(context).colorScheme.secondary,
        borderRadius: const BorderRadius.all(Radius.circular(10)),
      ),
      child: SingleChildScrollView(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Row(
              children: [
            Text(
              AppLocalizations.of(context).translate(widget.title),
              style: Theme.of(context).textTheme.titleMedium,
            ),

            IconButton(
              icon: Icon(Icons.add),
              onPressed: () {
                _showAddDialog(context, emptyCardHolder);
              },
            ),
            ],
            ),
            
            SizedBox(height: defaultPadding),
            Row(
              children: [
                Expanded(
                  child: TextField(
                    controller: _searchController,
                    decoration: InputDecoration(
                      hintText: AppLocalizations.of(context).translate("Finding..."),
                      border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(8.0),
                      ),
                    ),
                    onChanged: (_) => _searchUser(),
                    onSubmitted: (_) => _searchUser(),
                  ),
                ),
                const SizedBox(width: 8),
                IconButton(
                  icon: const Icon(Icons.search, color: Colors.blue),
                  onPressed: _searchUser,
                ),
              ],
            ),
            SizedBox(height: defaultPadding),
            _filteredCardHolder.isEmpty
                ? Center(
                    child: Text(
                      AppLocalizations.of(context).translate("There is no matching information"),
                      style: Theme.of(context).textTheme.bodyMedium,
                    ),
                  )
                : SizedBox(
                    width: double.infinity,
                    child: DataTable(
                      columnSpacing: defaultPadding,
                      columns: widget.objectColumnName
                          .map(
                            (name) => DataColumn(
                              label: Text(
                                AppLocalizations.of(context).translate(name),
                                overflow: TextOverflow.ellipsis,
                                maxLines: 1,
                              ),
                            ),
                          )
                          .toList(),
                      rows: _filteredCardHolder.map((cardHolder) {
                        return _buildDataRow(cardHolder, context);
                      }).toList(),
                    ),
                  ),
          ],
        ),
      ),
    );
  }

  DataRow _buildDataRow(CreateCardHolderRequest cardHolder, BuildContext context) {
    return DataRow(
      cells: [
        DataCell(Text(cardHolder.firstName)),
        DataCell(Text(cardHolder.lastName)),
        DataCell(
          IconButton(
            icon: const Icon(Icons.details, color: Colors.green),
            onPressed: () => _showDetailDialog(context, cardHolder),
          ),
        ),
        DataCell(
          IconButton(
            icon: const Icon(Icons.content_paste_search_outlined, color: Colors.blueAccent),
            onPressed: () => widget.onCustomer_Contracts(cardHolder),
          ),
        ),
      ],
    );
  }

  void _showDetailDialog(BuildContext context, CreateCardHolderRequest cardHolder) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          content: Container(
            width: Get.width / 2,
            child: AddCustomer(
              object: cardHolder,
              title: 'CustomerDetail',
              isDetail: true,
              isAdd: false, token: '',
            ),
          ),
          actions: [
            TextButton(
              onPressed: () => Navigator.of(context).pop(),
              child: Icon(Icons.cancel, color: Colors.red,),
            ),
          ],
        );
      },
    );
  }
  void _showAddDialog(BuildContext context, CreateCardHolderRequest cardHolder) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          content: Container(
            width: Get.width / 2,
            child: AddCustomer(
              object: cardHolder,
              title: 'CustomerAdd',
              isDetail: false,
              isAdd: true, token: widget.token,
            ),
          ),
          actions: [
            TextButton(
              onPressed: () => Navigator.of(context).pop(),
              child: Icon(Icons.cancel, color: Colors.red,),
            ),
          ],
        );
      },
    );
  }
}
