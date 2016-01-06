## Award Accounts [/award/api/v1/accounts]  

### List All Accounts [GET /award/api/v1/accounts{?startIndex=0&size=5}]

Get all accounts that are available for use

+ Parameters
  	+ startIndex: `0` (number, optional) - Starting index of the results to be returned. If not included, all available accounts are returned.
  	+ size: `5` (number, optional) - Size of the results to be returned. If not included, all available accounts are returned.

+ Request
    + Headers

            Authorization:Basic
            Content-Type:application/json

    + Body



+ Response 200
    + Headers

            Content-Type:application/json;

    + Body

             {"totalFound":2,"count":2,"accounts":[{"id":8,"accountNumber":"123456455","createdByAwardId":2670,"status":"CLOSED","budgeted":6.69,"pending":7.70,"income":9.90,"expense":8.80,"available":5.50},{"id":9,"accountNumber":"55555","createdByAwardId":2742,"status":"CLOSED","budgeted":6.69,"pending":7.70,"income":999.99,"expense":8.80,"available":5.50}],"awards":[]}

### Get Account [GET /award/api/v1/accounts/55555{?showAwards=true}]

Get information on a particular account

+ Parameters
  	+ showAwards: `true` (boolean, optional) - Flag to indicate if award ids linked to account should be returned.

+ Request
    + Headers

            Authorization:Basic
            Content-Type:application/json

    + Body



+ Response 200
    + Headers

            Content-Type:application/json;charset=UTF-8


    + Body

            {"totalFound":1,"count":1,"accounts":[{"id":9,"accountNumber":"55555","createdByAwardId":2742,"status":"CLOSED","budgeted":6.69,"pending":7.70,"income":999.99,"expense":8.80,"available":5.50}],"awards":[2742,2778]}

### Get Award Details by Account [GET /award/api/v1/accounts/awards/2742]

Get award details required to create an account using award id

+ Request
     + Headers

             Authorization:Basic
             Content-Type:application/json

     + Body



+ Response 200
     + Headers

             Content-Type:application/json;charset=UTF-8

     + Body

			{
			  "count": 1,
			  "accounts": [
			    {
			      "awardNumber": "000021-00001",
			      "title": "Testing",
			      "accountNumber": null,
			      "unit": "000001",
			      "purposeText": "Testing",
			      "incomeGuidelineText": "Cost reimbursement Established ACH mechanism for sponsor",
			      "accountName": "NIH-TesterJoe",
			      "cfdaNumber": null,
			      "sponsorAwardNumber": null,
			      "expenseGuidelineText": "000021-00001",
			      "accountTypeCode": null,			      
			      "effectiveDate": "2016-01-01",
			      "expirationDate": "2017-12-31",
			      "projectStartDate": null,
			      "projectEndDate": "2017-12-31",
			      "anticipatedTotalIndirect": 0,
			      "obligatedTotalDirect": 0,
			      "obligatedTotalIndirect": 0,
			      "anticipatedTotalAmount": 6850,
			      "anticipatedTotalDirect": 0,
			      "amountObligatedToDate": 6850,
			      "higherEdFunctionCode": "IPR",
			      "offCampusIndicator": false,
			      "indirectCostTypeCode": "",
			      "indirectCostRate": "",
			      "principalId": "10000000002",
			      "defaultAddressStreetAddress": "1119 Kuali Drive",
			      "defaultAddressCityName": "Coeus",
			      "defaultAddressStateCode": "MA",
			      "defaultAddressZipCode": "53421",
			      "adminContactAddressStreetAddress": "1176 Kuali Drive",
			      "adminContactAddressCityName": "Coeus",
			      "adminContactAddressStateCode": "MA",
			      "adminContactAddressZipCode": "53421",
			      "sponsor": {
			        "sponsorName": "NIH",
			        "acronym": "NIH",
			        "sponsorCode": "000340"
			      },
			      "primeSponsor": {
			        "sponsorName": "NIH",
			        "acronym": "NIH",
			        "sponsorCode": "000340"
			      },
			      "awardStatus": {
			        "description": "Active",
			        "code": "1"
			      },
			      "leadUnit": {
			        "unitNumber": "000001",
			        "unitName": "University",
			        "parentUnitNumber": null,
			        "organizationId": "000001"
			      },
			      "awardType": {
			        "description": "Grant",
			        "code": 1
			      },
			      "unitAdministrators": [
			        {
			          "unitAdministratorType": {
			            "description": "ADMINISTRATIVE_OFFICER",
			            "code": "1"
			          },
			          "unitNumber": "000001",
			          "person": {
			            "state": "MA",
			            "emailAddress": "kcnotification+quickstart@gmail.com",
			            "addressLine2": "",
			            "firstName": "Geoff",
			            "addressLine3": "",
			            "lastName": "McGregor",
			            "personId": "10000000001",
			            "middleName": "",
			            "userName": "quickstart",
			            "fullName": "Geoff McGregor",
			            "city": "Coeus",
			            "postalCode": "53421",
			            "addressLine1": "1118 Kuali Drive"
			          }
			        },
			        {
			          "unitAdministratorType": {
			            "description": "OSP_ADMINISTRATOR",
			            "code": "2"
			          },
			          "unitNumber": "000001",
			          "person": {
			            "state": "MA",
			            "emailAddress": "kcnotification+quickstart@gmail.com",
			            "addressLine2": "",
			            "firstName": "Geoff",
			            "addressLine3": "",
			            "lastName": "McGregor",
			            "personId": "10000000001",
			            "middleName": "",
			            "userName": "quickstart",
			            "fullName": "Geoff McGregor",
			            "city": "Coeus",
			            "postalCode": "53421",
			            "addressLine1": "1118 Kuali Drive"
			          }
			        },
			        {
			          "unitAdministratorType": {
			            "description": "OSP_ADMINISTRATOR",
			            "code": "2"
			          },
			          "unitNumber": "000001",
			          "person": {
			            "state": "MA",
			            "emailAddress": "kcnotification+wacuna@gmail.com",
			            "addressLine2": "",
			            "firstName": "WM",
			            "addressLine3": "",
			            "lastName": "ACUNA",
			            "personId": "10000000035",
			            "middleName": "",
			            "userName": "wacuna",
			            "fullName": "WM ACUNA",
			            "city": "Coeus",
			            "postalCode": "53421",
			            "addressLine1": "1152 Kuali Drive"
			          }
			        }
			      ],
			      "principalInvestigator": {
			        "person": {
			          "state": "MA",
			          "emailAddress": "kcnotification+jtester@gmail.com",
			          "addressLine2": "",
			          "firstName": "Joe",
			          "addressLine3": "",
			          "lastName": "Tester",
			          "personId": "10000000002",
			          "middleName": "",
			          "userName": "jtester",
			          "fullName": "Joe Tester",
			          "city": "Coeus",
			          "postalCode": "53421",
			          "addressLine1": "1119 Kuali Drive"
			        },
			        "userName": "jtester",
			        "emailAddress": "kcnotification+jtester@gmail.com",
			        "fullName": "Joe Tester",
			        "roleCode": "PI",
			        "projectRole": "PI/Contact",
			        "personId": "10000000002"
			      },
			      "customData": [
			        {
			          "id": 1,
			          "name": "billingElement",
			          "value": "5"
			        },
			        {
			          "id": 2,
			          "name": "costSharingBudget",
			          "value": "1"
			        },
			        {
			          "id": 3,
			          "name": "numberOfTrainees",
			          "value": null
			        },
			        {
			          "id": 4,
			          "name": "graduateStudentCount",
			          "value": "5"
			        },
			        {
			          "id": 5,
			          "name": "tenured",
			          "value": null
			        },
			        {
			          "id": 6,
			          "name": "exportControls",
			          "value": null
			        },
			        {
			          "id": 7,
			          "name": "inventions",
			          "value": null
			        }
			      ],
			      "transactions": [
			        {
			          "obligatedChangeIndirect": 0,
			          "anticipatedChangeIndirect": 0,
			          "anticipatedChangeDirect": 0,
			          "awardAmountInfoId": 2590,
			          "obligatedChange": 0,
			          "originatingAwardVersion": 2,
			          "amountObligatedToDate": 5100,
			          "obligatedChangeDirect": 0,
			          "currentFundEffectiveDate": "2016-01-01",
			          "anticipatedTotalDirect": 0,
			          "obligatedTotalIndirect": 0,
			          "transactionId": 3,
			          "obligationExpirationDate": "2017-12-31",
			          "obligatedTotalDirect": 0,
			          "anticipatedTotalAmount": 5100,
			          "obliDistributableAmount": 5100,
			          "anticipatedChange": 0,
			          "finalExpirationDate": "2017-12-31",
			          "anticipatedTotalIndirect": 0,
			          "antDistributableAmount": 5100,
			          "timeAndMoneyDocumentNumber": null
			        },
			        {
			          "obligatedChangeIndirect": 0,
			          "anticipatedChangeIndirect": 0,
			          "anticipatedChangeDirect": 0,
			          "awardAmountInfoId": 2605,
			          "obligatedChange": 2000,
			          "originatingAwardVersion": 2,
			          "amountObligatedToDate": 7100,
			          "obligatedChangeDirect": 0,
			          "currentFundEffectiveDate": "2016-01-01",
			          "anticipatedTotalDirect": 0,
			          "obligatedTotalIndirect": 0,
			          "transactionId": 4,
			          "obligationExpirationDate": "2017-12-31",
			          "obligatedTotalDirect": 0,
			          "anticipatedTotalAmount": 7100,
			          "obliDistributableAmount": 7100,
			          "anticipatedChange": 2000,
			          "finalExpirationDate": "2017-12-31",
			          "anticipatedTotalIndirect": 0,
			          "antDistributableAmount": 7100,
			          "timeAndMoneyDocumentNumber": "4434"
			        },
			        {
			          "obligatedChangeIndirect": 0,
			          "anticipatedChangeIndirect": 0,
			          "anticipatedChangeDirect": 0,
			          "awardAmountInfoId": 2606,
			          "obligatedChange": -250,
			          "originatingAwardVersion": 2,
			          "amountObligatedToDate": 6850,
			          "obligatedChangeDirect": 0,
			          "currentFundEffectiveDate": "2016-01-01",
			          "anticipatedTotalDirect": 0,
			          "obligatedTotalIndirect": 0,
			          "transactionId": 5,
			          "obligationExpirationDate": "2017-12-31",
			          "obligatedTotalDirect": 0,
			          "anticipatedTotalAmount": 6850,
			          "obliDistributableAmount": 6850,
			          "anticipatedChange": -250,
			          "finalExpirationDate": "2017-12-31",
			          "anticipatedTotalIndirect": 0,
			          "antDistributableAmount": 6850,
			          "timeAndMoneyDocumentNumber": "4434"
			        }
			      ],
			      "fAndARates": [
			        {
			          "applicableFandaRate": 5,
			          "rateTypeCode": "1",
			          "fiscalYear": "2015",
			          "rateClassCode": "13",
			          "awardFandaRateId": 2601,
			          "underrecoveryOfIndirectCost": null,
			          "sourceAccount": null,
			          "startDate": "2014-07-01",
			          "onCampusFlag": "N",
			          "destinationAccount": null,
			          "endDate": "2015-06-30"
			        },
			        {
			          "applicableFandaRate": 10,
			          "rateTypeCode": "18",
			          "fiscalYear": "2016",
			          "rateClassCode": "13",
			          "awardFandaRateId": 2602,
			          "underrecoveryOfIndirectCost": 5,
			          "sourceAccount": "1234",
			          "startDate": "2015-07-01",
			          "onCampusFlag": "N",
			          "destinationAccount": "12345",
			          "endDate": "2016-06-30"
			        },
			        {
			          "applicableFandaRate": 5,
			          "rateTypeCode": "1",
			          "fiscalYear": "2015",
			          "rateClassCode": "13",
			          "awardFandaRateId": 2603,
			          "underrecoveryOfIndirectCost": null,
			          "sourceAccount": null,
			          "startDate": "2014-07-01",
			          "onCampusFlag": "F",
			          "destinationAccount": null,
			          "endDate": "2015-06-30"
			        },
			        {
			          "applicableFandaRate": 10,
			          "rateTypeCode": "18",
			          "fiscalYear": "2016",
			          "rateClassCode": "13",
			          "awardFandaRateId": 2604,
			          "underrecoveryOfIndirectCost": 5,
			          "sourceAccount": "1234",
			          "startDate": "2015-07-01",
			          "onCampusFlag": "F",
			          "destinationAccount": "12345",
			          "endDate": "2016-06-30"
			        }
			      ],
			    }
			  ],
			  "totalFound": 1
			}

### Update Account [PUT /award/api/v1/accounts/878787]

Set various fields on an account. Fields not provided will not be updated.

+ Request
    + Headers

            Authorization:Basic
            Content-Type:application/json

    + Body

            {"available":5.5, "budgeted":6.688, "pending":7.7, "expense":8.8, "income":999.99, "status":"CLOSED"}


    + Attributes
      + available (number) - available amount (max integral digits = 12, max fractional digits = 2)
      + budgeted (number) - budgeted amount (max integral digits = 12, max fractional digits = 2)
      + pending (number) - pending amount (max integral digits = 12, max fractional digits = 2)
      + expense (number) - expense amount (max integral digits = 12, max fractional digits = 2)
      + income (number) - income amount (max integral digits = 12, max fractional digits = 2)
      + status (string) - status (min size = 1, max size = 15], pattern [a-zA-Z]+)

+ Response 200
    + Headers

            Content-Length:0

    + Body





