class Contract {
  final String contractID;
  final bool status;
  final DateTime createdDate;
  final DateTime updatedDate;
  final String userID;
  final String note;
  final String url;
  final String cardHolderID;
  final String parent;

  Contract(  {
    required this.contractID,
    required this.userID,
    required this.createdDate,
    required this.status,
    required this.note,
    required this.url,
    required this.updatedDate,
    required this.cardHolderID,
    required this.parent,
  });
}

List<Contract> contractlist =[
  Contract(
  contractID: "1",
  userID: "1",
  createdDate: DateTime.now(),
  updatedDate: DateTime.now(),
  status: true,
  note: "Hợp đồng mở thẻ ngân hàng",
  url: "https://example.com/contracts/C12345",
  cardHolderID: '1', parent: 'null',
  
  ),
  Contract(
    contractID: "2",
    userID: "1",
    createdDate: DateTime.now(),
    updatedDate: DateTime.now(),
    status: true,
    note: "Hợp đồng mở thẻ ngân hàng",
    url: "https://example.com/contracts/C12345", cardHolderID: '1',
    parent: '1',
  ),
  Contract(
    contractID: "3",
    userID: "1",
    createdDate: DateTime.now(),
    updatedDate: DateTime.now(),
    status: true,
    note: "Hợp đồng mở thẻ ngân hàng",
    url: "https://example.com/contracts/C12345", cardHolderID: '1',
    parent: '1',
  )
];

