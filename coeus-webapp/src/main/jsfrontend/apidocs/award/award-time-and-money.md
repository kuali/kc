## Time and Money Posts [/award/api/v1/time-and-money-posts/]

### Get All Active Time and Money Posts [GET /award/api/v1/time-and-money-posts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
          [
            {
              "id": 1,
              "accountNumber": null,
              "awardId": 2745,
              "active": true,
              "documentNumber": "4457",
              "timeAndMoney": {
                "awardAmountTransactions": [
                  {
                    "awardAmountTransactionId": 2,
                    "awardNumber": "000025-00001",
                    "documentNumber": "4457",
                    "noticeDate": null,
                    "comments": null,
                    "transactionTypeCode": 9,
                    "awardTransactionType": {
                      "awardTransactionTypeCode": 9,
                      "description": "New"
                    }
                  }
                ],
                "transactionDetails": [
                  {
                    "transactionDetailId": 2,
                    "awardNumber": "000025-00001",
                    "sequenceNumber": 1,
                    "transactionId": 0,
                    "timeAndMoneyDocumentNumber": "4457",
                    "comments": "Initial Time And Money creation transaction",
                    "sourceAwardNumber": "000000-00000",
                    "destinationAwardNumber": "000025-00001",
                    "transactionDetailType": "PRIMARY",
                    "obligatedAmount": 100000,
                    "obligatedDirectAmount": 100000,
                    "obligatedIndirectAmount": 0,
                    "anticipatedAmount": 100000,
                    "anticipatedDirectAmount": 100000,
                    "anticipatedIndirectAmount": 0
                  }
                ]
              },
              "updateUser": "quickstart",
              "updateTimestamp": 1468375988000
            },
            {
              "id": 2,
              "accountNumber": null,
              "awardId": 2745,
              "active": true,
              "documentNumber": "4464",
              "timeAndMoney": {
                "awardAmountTransactions": [
                  {
                    "awardAmountTransactionId": 3,
                    "awardNumber": "000000-00000",
                    "documentNumber": "4464",
                    "noticeDate": null,
                    "comments": null,
                    "transactionTypeCode": 2,
                    "awardTransactionType": {
                      "awardTransactionTypeCode": 2,
                      "description": "Allotment (Increment)"
                    }
                  },
                  {
                    "awardAmountTransactionId": 4,
                    "awardNumber": "000025-00001",
                    "documentNumber": "4464",
                    "noticeDate": null,
                    "comments": null,
                    "transactionTypeCode": 2,
                    "awardTransactionType": {
                      "awardTransactionTypeCode": 2,
                      "description": "Allotment (Increment)"
                    }
                  }
                ],
                "transactionDetails": [
                  {
                    "transactionDetailId": 3,
                    "awardNumber": "000025-00001",
                    "sequenceNumber": 1,
                    "transactionId": 1,
                    "timeAndMoneyDocumentNumber": "4464",
                    "comments": "ttt",
                    "sourceAwardNumber": "000000-00000",
                    "destinationAwardNumber": "000025-00001",
                    "transactionDetailType": "PRIMARY",
                    "obligatedAmount": 100,
                    "obligatedDirectAmount": 0,
                    "obligatedIndirectAmount": 0,
                    "anticipatedAmount": 100,
                    "anticipatedDirectAmount": 0,
                    "anticipatedIndirectAmount": 0
                  }
                ]
              },
              "updateUser": "quickstart",
              "updateTimestamp": 1468387045000
            },
            {
              "id": 3,
              "accountNumber": null,
              "awardId": 2799,
              "active": true,
              "documentNumber": "4471",
              "timeAndMoney": {
                "awardAmountTransactions": [
                  {
                    "awardAmountTransactionId": 6,
                    "awardNumber": "000000-00000",
                    "documentNumber": "4471",
                    "noticeDate": null,
                    "comments": null,
                    "transactionTypeCode": 1,
                    "awardTransactionType": {
                      "awardTransactionTypeCode": 1,
                      "description": "Administrative Amendment"
                    }
                  }
                ],
                "transactionDetails": []
              },
              "updateUser": "quickstart",
              "updateTimestamp": 1468440576000
            }
          ]

### Get a Time and Money Post Record [GET /award/api/v1/time-and-money-posts/1]

+ Parameters
	+ timeAndMoneyPostId: `1` (number, required) - Returns the time and money post record associated with the timeAndMoneyPostId.

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
          {
            "id": 1,
            "accountNumber": null,
            "awardId": 2745,
            "active": true,
            "documentNumber": "4457",
            "timeAndMoney": {
              "awardAmountTransactions": [
                {
                  "awardAmountTransactionId": 2,
                  "awardNumber": "000025-00001",
                  "documentNumber": "4457",
                  "noticeDate": null,
                  "comments": null,
                  "transactionTypeCode": 9,
                  "awardTransactionType": {
                    "awardTransactionTypeCode": 9,
                    "description": "New"
                  }
                }
              ],
              "transactionDetails": [
                {
                  "transactionDetailId": 2,
                  "awardNumber": "000025-00001",
                  "sequenceNumber": 1,
                  "transactionId": 0,
                  "timeAndMoneyDocumentNumber": "4457",
                  "comments": "Initial Time And Money creation transaction",
                  "sourceAwardNumber": "000000-00000",
                  "destinationAwardNumber": "000025-00001",
                  "transactionDetailType": "PRIMARY",
                  "obligatedAmount": 100000,
                  "obligatedDirectAmount": 100000,
                  "obligatedIndirectAmount": 0,
                  "anticipatedAmount": 100000,
                  "anticipatedDirectAmount": 100000,
                  "anticipatedIndirectAmount": 0
                }
              ]
            },
            "updateUser": "quickstart",
            "updateTimestamp": 1468375988000
          }

### Update Time and Money Post [PUT /award/api/v1/time-and-money-posts/4]

Set the "active" field on a time and money post record.

+ Parameters
	+ timeAndMoneyPostId: `4` (number, required) - Updates the time and money post record associated with the timeAndMoneyPostId.

+ Request
    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

    + Body

            {
                "id": 4,
                "accountNumber": null,
                "awardId": 2799,
                "active": false,
                "documentNumber": "4478"
            }

+ Response 200
    + Headers

            Content-Length:0

    + Body

                {
                  "id": 4,
                  "accountNumber": null,
                  "awardId": 2799,
                  "active": false,
                  "documentNumber": "4478",
                  "timeAndMoney": null,
                  "updateUser": "quickstart",
                  "updateTimestamp": 1468454293000
                }


### Get a Time and Money Document [GET /award/api/v1/time-and-money-documents/4457]

+ Parameters
	+ timeAndMoneyDocumentNumber: `4457` (number, required) - Returns the time and money document associated with the timeAndMoneyDocumentNumber.

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
          {
            "id": 1,
            "accountNumber": null,
            "awardId": 2745,
            "active": true,
            "documentNumber": "4457",
            "timeAndMoney": {
              "awardAmountTransactions": [
                {
                  "awardAmountTransactionId": 2,
                  "awardNumber": "000025-00001",
                  "documentNumber": "4457",
                  "noticeDate": null,
                  "comments": null,
                  "transactionTypeCode": 9,
                  "awardTransactionType": {
                    "awardTransactionTypeCode": 9,
                    "description": "New"
                  }
                }
              ],
              "transactionDetails": [
                {
                  "transactionDetailId": 2,
                  "awardNumber": "000025-00001",
                  "sequenceNumber": 1,
                  "transactionId": 0,
                  "timeAndMoneyDocumentNumber": "4457",
                  "comments": "Initial Time And Money creation transaction",
                  "sourceAwardNumber": "000000-00000",
                  "destinationAwardNumber": "000025-00001",
                  "transactionDetailType": "PRIMARY",
                  "obligatedAmount": 100000,
                  "obligatedDirectAmount": 100000,
                  "obligatedIndirectAmount": 0,
                  "anticipatedAmount": 100000,
                  "anticipatedDirectAmount": 100000,
                  "anticipatedIndirectAmount": 0
                }
              ]
            },
            "updateUser": "quickstart",
            "updateTimestamp": 1468375988000
          }