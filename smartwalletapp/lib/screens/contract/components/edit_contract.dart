import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:get/get.dart';
import 'package:smartwalletapp/app/locallization/app_localizations.dart';
import 'package:smartwalletapp/bloc/Contract/contract_bloc.dart';
import 'package:smartwalletapp/bloc/Contract/contract_event.dart';
import 'package:smartwalletapp/request/edit_contract_request.dart';

class EditContractForm extends StatefulWidget {
  final String contractIdentifier;
  final String token;
  const EditContractForm({super.key, required this.contractIdentifier, required this.token});

  @override
  _EditContractFormState createState() => _EditContractFormState();
}

class _EditContractFormState extends State<EditContractForm> {
  final _formKey = GlobalKey<FormState>();

  // Controllers
  final TextEditingController contractSearchMethodController = TextEditingController(text: "CONTRACT_NUMBER");
  final TextEditingController contractIdentifierController = TextEditingController();
  final TextEditingController reasonController = TextEditingController(text: "to test");
  final TextEditingController contractNumberController = TextEditingController();
  final TextEditingController contractNameController = TextEditingController();
  final TextEditingController serviceGroupController = TextEditingController();
  final TextEditingController branchController = TextEditingController();
  final TextEditingController cbsIdController = TextEditingController();
  final TextEditingController cbsNumberController = TextEditingController();
  final TextEditingController closeDateController = TextEditingController();
  final TextEditingController addInfoTypeController = TextEditingController();
  final TextEditingController removeTagController = TextEditingController();
  final TextEditingController tagNameController = TextEditingController();
  final TextEditingController tagValueController = TextEditingController();

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    contractIdentifierController.text = widget.contractIdentifier;
  }

  void _saveContract() {
    if (_formKey.currentState!.validate()) {
      EditContractV4 request = EditContractV4(
        contractSearchMethod: contractSearchMethodController.text,
        contractIdentifier: contractIdentifierController.text,
        reason: reasonController.text,
        editContractInObject: EditContractInObjectEditV4(
          branch: branchController.text,
          serviceGroup: serviceGroupController.text,
          contractNumber: contractNumberController.text,
          contractName: contractNameController.text,
          cbsId: cbsIdController.text,
          cbsNumber: cbsNumberController.text,
          closeDate: closeDateController.text,
        ),
        customData: SetCustomDataInObjectEditV4(
          addInfoType: addInfoTypeController.text,
          removeTag: removeTagController.text,
          tagName: tagNameController.text,
          tagValue: tagValueController.text,
        ),
      );

      context.read<ContractBloc>().add(EditContractEvent(widget.token, request));
    }
  }

  @override
  Widget build(BuildContext context) {
    return Form(
      key: _formKey,
      child: Container(
        width: Get.width / 1.1,
        padding: EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            IconButton(
              onPressed: _saveContract,
              icon: Icon(Icons.save),
            ),
            SizedBox(height: 16),
            buildTextField('Contract Search Method', contractSearchMethodController),
            buildTextField('Contract Identifier', contractIdentifierController),
            Row(
              children: [
                Expanded(
                  flex: 1,
                  child: Column(
                    children: [
                      Text('Contract Information', style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
                      buildTextField('Contract Number', contractNumberController),
                      buildTextField('Contract Name', contractNameController),
                      buildTextField('Service Group', serviceGroupController),
                      buildTextField('Branch', branchController),
                    ],
                  ),
                ),
                Expanded(
                  flex: 1,
                  child: Column(
                    children: [
                      Text('Custom Data', style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
                      buildTextField('Additional Info Type', addInfoTypeController),
                      buildTextField('Remove Tag', removeTagController),
                      buildTextField('Tag Name', tagNameController),
                      buildTextField('Tag Value', tagValueController),
                    ],
                  ),
                ),
              ],
            ),
            SizedBox(height: 16),
            Text('System Information', style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
            buildTextField('CBS ID', cbsIdController),
            buildTextField('CBS Number', cbsNumberController),
            buildTextField('Close Date', closeDateController),
            SizedBox(height: 16),
            Text('Reason', style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
            buildTextField('Reason', reasonController),
          ],
        ),
      ),
    );
  }

  Widget buildTextField(String label, TextEditingController controller) {
    return Padding(
      padding: EdgeInsets.only(bottom: 10.0),
      child: TextFormField(
        controller: controller,
        decoration: InputDecoration(
          labelText: AppLocalizations.of(context).translate(label),
          border: OutlineInputBorder(),
        ),
        validator: (value) {
          if (value == null || value.isEmpty) {
            return '$label cannot be empty';
          }
          return null;
        },
      ),
    );
  }
}
