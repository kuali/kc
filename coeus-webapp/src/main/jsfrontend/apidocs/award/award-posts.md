## Award Posts [/award/api/v1/award-posts/]

### Get All Active Award Posts [GET /award/api/v1/award-posts/{?accountNumber=55555}]


+ Parameters
  	+ accountNumber: `55555` (number, optional) - Account number of the posts to be returned. If not included, will return all active award posts.

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
              "id": 16,
              "accountNumber": "55555",
              "posted": true,
              "active": true,
              "documentNumber": "4469",
              "updateUser": "quickstart",
              "updateTimestamp": 1468434837000,
              "awardId": 2799,
              "awardDto": {
                "docNbr": null,
                "docStatus": null,
                "awardId": 2799,
                "awardNumber": "000023-00001",
                "sequenceNumber": 9,
                "sponsorCode": "055075",
                "statusCode": 1,
                "accountNumber": "55555",
                "awardEffectiveDate": "2014-01-01",
                "awardExecutionDate": null,
                "beginDate": "2014-01-01",
                "projectEndDate": "2016-06-30",
                "costSharingIndicator": "N",
                "indirectCostIndicator": "Y",
                "nsfSequenceNumber": null,
                "paymentScheduleIndicator": "N",
                "scienceCodeIndicator": "N",
                "specialReviewIndicator": "N",
                "sponsorAwardNumber": null,
                "transferSponsorIndicator": "N",
                "accountTypeCode": 1,
                "activityTypeCode": "3",
                "primeSponsorCode": null,
                "awardTypeCode": 1,
                "cfdaNumber": "00.000",
                "methodOfPaymentCode": "1",
                "proposalNumber": null,
                "title": "LOC - by cc acct",
                "basisOfPaymentCode": "1",
                "awardTransactionTypeCode": 4,
                "noticeDate": null,
                "leadUnitNumber": "000001",
                "awardSequenceStatus": "ACTIVE",
                "unitNumber": "000001",
                "projectPersons": [
                  {
                    "awardContactId": 2800,
                    "personId": "10000000002",
                    "roleCode": "PI",
                    "keyPersonRole": null,
                    "rolodexId": null,
                    "kcPerson.emailAddress": "kcnotification+quickstart@gmail.com",
                    "kcPerson.phoneNumber": "321-321-1197"
                  }
                ],
                "awardCustomDataList": [
                  {
                    "customAttributeId": 1,
                    "value": "05"
                  },
                  {
                    "customAttributeId": 4,
                    "value": "5"
                  },
                  {
                    "customAttributeId": 2,
                    "value": null
                  },
                  {
                    "customAttributeId": 3,
                    "value": null
                  },
                  {
                    "customAttributeId": 5,
                    "value": null
                  },
                  {
                    "customAttributeId": 6,
                    "value": null
                  },
                  {
                    "customAttributeId": 7,
                    "value": null
                  }
                ],
                "awardSponsorTerms": [
                  {
                    "sponsorTermId": 325
                  },
                  {
                    "sponsorTermId": 505
                  },
                  {
                    "sponsorTermId": 353
                  },
                  {
                    "sponsorTermId": 371
                  },
                  {
                    "sponsorTermId": 538
                  },
                  {
                    "sponsorTermId": 318
                  },
                  {
                    "sponsorTermId": 354
                  },
                  {
                    "sponsorTermId": 372
                  },
                  {
                    "sponsorTermId": 417
                  },
                  {
                    "sponsorTermId": 544
                  },
                  {
                    "sponsorTermId": 556
                  },
                  {
                    "sponsorTermId": 567
                  },
                  {
                    "sponsorTermId": 569
                  },
                  {
                    "sponsorTermId": 493
                  },
                  {
                    "sponsorTermId": 500
                  },
                  {
                    "sponsorTermId": 410
                  },
                  {
                    "sponsorTermId": 437
                  },
                  {
                    "sponsorTermId": 383
                  },
                  {
                    "sponsorTermId": 578
                  },
                  {
                    "sponsorTermId": 601
                  },
                  {
                    "sponsorTermId": 322
                  },
                  {
                    "sponsorTermId": 367
                  },
                  {
                    "sponsorTermId": 448
                  },
                  {
                    "sponsorTermId": 480
                  },
                  {
                    "sponsorTermId": 333
                  },
                  {
                    "sponsorTermId": 369
                  }
                ],
                "awardReportTerms": [
                  {
                    "reportClassCode": "1",
                    "reportCode": "7",
                    "frequencyCode": "7",
                    "frequencyBaseCode": "5",
                    "ospDistributionCode": "1",
                    "dueDate": null
                  }
                ],
                "approvedEquipmentIndicator": "N",
                "approvedForeignTripIndicator": "N",
                "subContractIndicator": "N",
                "modificationNumber": null,
                "documentFundingId": null,
                "preAwardAuthorizedAmount": null,
                "preAwardEffectiveDate": null,
                "preAwardInstitutionalAuthorizedAmount": null,
                "preAwardInstitutionalEffectiveDate": null,
                "procurementPriorityCode": null,
                "specialEbRateOffCampus": null,
                "specialEbRateOnCampus": null,
                "subPlanFlag": null,
                "archiveLocation": null,
                "closeoutDate": null,
                "currentActionComments": "",
                "awardSequenceStatusResult": "Final",
                "templateCode": null,
                "awardBasisOfPayment": null,
                "awardMethodOfPayment": null,
                "awardComments": [
                  {
                    "awardCommentId": 337,
                    "commentTypeCode": "21",
                    "checklistPrintFlag": false,
                    "comments": null
                  },
                  {
                    "awardCommentId": 338,
                    "commentTypeCode": "9",
                    "checklistPrintFlag": true,
                    "comments": null
                  },
                  {
                    "awardCommentId": 339,
                    "commentTypeCode": "8",
                    "checklistPrintFlag": true,
                    "comments": null
                  },
                  {
                    "awardCommentId": 340,
                    "commentTypeCode": "20",
                    "checklistPrintFlag": true,
                    "comments": null
                  },
                  {
                    "awardCommentId": 341,
                    "commentTypeCode": "18",
                    "checklistPrintFlag": false,
                    "comments": null
                  },
                  {
                    "awardCommentId": 342,
                    "commentTypeCode": "19",
                    "checklistPrintFlag": false,
                    "comments": null
                  }
                ],
                "awardCostShares": [],
                "awardFandaRate": [
                  {
                    "awardFandaRateId": 2810,
                    "applicableFandaRate": 10,
                    "fandaRateTypeCode": "1",
                    "fiscalYear": "2016",
                    "onCampusFlag": "N",
                    "underrecoveryOfIndirectCost": null,
                    "sourceAccount": null,
                    "destinationAccount": null,
                    "startDate": "2015-07-01",
                    "endDate": "2016-06-30"
                  },
                  {
                    "awardFandaRateId": 2811,
                    "applicableFandaRate": 10,
                    "fandaRateTypeCode": "1",
                    "fiscalYear": "2016",
                    "onCampusFlag": "F",
                    "underrecoveryOfIndirectCost": null,
                    "sourceAccount": null,
                    "destinationAccount": null,
                    "startDate": "2015-07-01",
                    "endDate": "2016-06-30"
                  }
                ],
                "awardDirectFandADistributions": [],
                "awardUnitContacts": [
                  {
                    "unitAdministratorType": {
                      "code": "9",
                      "description": "Fund Manager"
                    },
                    "unitNumber": "000001",
                    "person": {
                      "personId": "10000000018",
                      "firstName": "ALAN",
                      "lastName": "MCAFEE",
                      "middleName": "",
                      "fullName": "ALAN MCAFEE",
                      "userName": "aemcafee",
                      "emailAddress": "kcnotification+aemcafee@gmail.com",
                      "addressLine1": "1135 Kuali Drive",
                      "addressLine2": "",
                      "addressLine3": "",
                      "city": "Coeus",
                      "state": "MA",
                      "postalCode": "53421"
                    }
                  }
                ],
                "approvedEquipmentItems": [],
                "approvedForeignTravelTrips": [],
                "paymentScheduleItems": [],
                "awardTransferringSponsors": [],
                "awardAmountInfos": [
                  {
                    "awardAmountInfoId": 2813,
                    "transactionId": null,
                    "timeAndMoneyDocumentNumber": null,
                    "anticipatedTotalAmount": 100000,
                    "antDistributableAmount": 100000,
                    "finalExpirationDate": "2016-06-30",
                    "currentFundEffectiveDate": "2016-06-30",
                    "amountObligatedToDate": 100000,
                    "obliDistributableAmount": 100000,
                    "obligationExpirationDate": "2016-06-30",
                    "anticipatedChange": 0,
                    "obligatedChange": 0,
                    "obligatedChangeDirect": 0,
                    "obligatedChangeIndirect": 0,
                    "anticipatedChangeDirect": 0,
                    "anticipatedChangeIndirect": 0,
                    "anticipatedTotalDirect": 0,
                    "anticipatedTotalIndirect": 0,
                    "obligatedTotalDirect": 0,
                    "obligatedTotalIndirect": 0,
                    "originatingAwardVersion": 9
                  },
                  {
                    "awardAmountInfoId": 2818,
                    "transactionId": null,
                    "timeAndMoneyDocumentNumber": "4478",
                    "anticipatedTotalAmount": 100000,
                    "antDistributableAmount": 100000,
                    "finalExpirationDate": "2016-06-30",
                    "currentFundEffectiveDate": "2016-06-30",
                    "amountObligatedToDate": 100000,
                    "obliDistributableAmount": 100000,
                    "obligationExpirationDate": "2016-07-29",
                    "anticipatedChange": 0,
                    "obligatedChange": 0,
                    "obligatedChangeDirect": 0,
                    "obligatedChangeIndirect": 0,
                    "anticipatedChangeDirect": 0,
                    "anticipatedChangeIndirect": 0,
                    "anticipatedTotalDirect": 0,
                    "anticipatedTotalIndirect": 0,
                    "obligatedTotalDirect": 0,
                    "obligatedTotalIndirect": 0,
                    "originatingAwardVersion": 9
                  },
                  {
                    "awardAmountInfoId": 2819,
                    "transactionId": null,
                    "timeAndMoneyDocumentNumber": "4478",
                    "anticipatedTotalAmount": 100000,
                    "antDistributableAmount": 100000,
                    "finalExpirationDate": "2016-07-29",
                    "currentFundEffectiveDate": "2016-06-30",
                    "amountObligatedToDate": 100000,
                    "obliDistributableAmount": 100000,
                    "obligationExpirationDate": "2016-07-29",
                    "anticipatedChange": 0,
                    "obligatedChange": 0,
                    "obligatedChangeDirect": 0,
                    "obligatedChangeIndirect": 0,
                    "anticipatedChangeDirect": 0,
                    "anticipatedChangeIndirect": 0,
                    "anticipatedTotalDirect": 0,
                    "anticipatedTotalIndirect": 0,
                    "obligatedTotalDirect": 0,
                    "obligatedTotalIndirect": 0,
                    "originatingAwardVersion": 9
                  }
                ],
                "awardCloseoutItems": [
                  {
                    "awardCloseoutId": 120,
                    "finalSubmissionDate": null,
                    "dueDate": null,
                    "closeoutReportCode": "1",
                    "closeoutReportName": "Financial",
                    "multiple": false
                  },
                  {
                    "awardCloseoutId": 121,
                    "finalSubmissionDate": null,
                    "dueDate": null,
                    "closeoutReportCode": "4",
                    "closeoutReportName": "Technical",
                    "multiple": false
                  },
                  {
                    "awardCloseoutId": 122,
                    "finalSubmissionDate": null,
                    "dueDate": null,
                    "closeoutReportCode": "3",
                    "closeoutReportName": "Patent",
                    "multiple": false
                  },
                  {
                    "awardCloseoutId": 123,
                    "finalSubmissionDate": null,
                    "dueDate": null,
                    "closeoutReportCode": "2",
                    "closeoutReportName": "Property",
                    "multiple": false
                  },
                  {
                    "awardCloseoutId": 124,
                    "finalSubmissionDate": null,
                    "dueDate": null,
                    "closeoutReportCode": "6",
                    "closeoutReportName": "Invoice",
                    "multiple": false
                  }
                ],
                "awardCloseoutNewItems": [],
                "fundingProposals": [],
                "allFundingProposals": [],
                "awardBudgetLimits": [
                  {
                    "budgetLimitId": 19,
                    "awardId": null,
                    "budgetId": null,
                    "limitTypeCode": "directCost",
                    "limit": null
                  },
                  {
                    "budgetLimitId": 20,
                    "awardId": null,
                    "budgetId": null,
                    "limitTypeCode": "indirectCost",
                    "limit": null
                  },
                  {
                    "budgetLimitId": 21,
                    "awardId": null,
                    "budgetId": null,
                    "limitTypeCode": "totalCost",
                    "limit": null
                  }
                ],
                "budgets": [
                  {
                    "awardId": 2710,
                    "budgetId": 1,
                    "budgetVersionNumber": 1,
                    "budgetInitiator": "10000000001",
                    "awardBudgetStatusCode": "9",
                    "awardBudgetStatus": {
                      "awardBudgetStatusCode": "9",
                      "description": "Posted"
                    },
                    "awardBudgetTypeCode": "1",
                    "awardBudgetType": {
                      "awardBudgetTypeCode": "1",
                      "description": "New"
                    },
                    "obligatedTotal": 100000,
                    "obligatedAmount": 0,
                    "description": "New",
                    "onOffCampusFlag": "D",
                    "endDate": "2016-06-29",
                    "startDate": "2016-06-29",
                    "totalCost": 100000,
                    "totalDirectCost": 100000,
                    "totalIndirectCost": 0,
                    "costSharingAmount": 0,
                    "underrecoveryAmount": 0,
                    "totalCostLimit": 100000,
                    "residualFunds": null,
                    "ohRateClassCode": "13",
                    "ohRateClass": {
                      "code": "13",
                      "description": "MTDC - AWARD"
                    },
                    "ohRateTypeCode": "1",
                    "comments": null,
                    "modularBudgetFlag": false,
                    "urRateClassCode": "13",
                    "totalDirectCostLimit": 0,
                    "totalDirectCostInclPrev": 100000,
                    "totalIndirectCostInclPrev": 0,
                    "totalCostInclPrev": 100000,
                    "budgetRates": [
                      {
                        "activityTypeCode": "3",
                        "applicableRate": 10,
                        "fiscalYear": "2016",
                        "onOffCampusFlag": false,
                        "rateClassCode": "13",
                        "rateClass": {
                          "code": "13",
                          "description": "MTDC - AWARD"
                        },
                        "rateTypeCode": "1",
                        "rateType": {
                          "rateClassCode": "13",
                          "rateTypeCode": "1",
                          "description": "MTDC"
                        },
                        "startDate": "2015-07-01",
                        "instituteRate": 9
                      },
                      {
                        "activityTypeCode": "3",
                        "applicableRate": 10,
                        "fiscalYear": "2016",
                        "onOffCampusFlag": true,
                        "rateClassCode": "13",
                        "rateClass": {
                          "code": "13",
                          "description": "MTDC - AWARD"
                        },
                        "rateTypeCode": "1",
                        "rateType": {
                          "rateClassCode": "13",
                          "rateTypeCode": "1",
                          "description": "MTDC"
                        },
                        "startDate": "2015-07-01",
                        "instituteRate": 62
                      },
                      {
                        "activityTypeCode": "3",
                        "applicableRate": 25,
                        "fiscalYear": "2006",
                        "onOffCampusFlag": false,
                        "rateClassCode": "5",
                        "rateClass": {
                          "code": "5",
                          "description": "Employee Benefits"
                        },
                        "rateTypeCode": "1",
                        "rateType": {
                          "rateClassCode": "5",
                          "rateTypeCode": "1",
                          "description": "Research Rate"
                        },
                        "startDate": "2005-07-01",
                        "instituteRate": 25
                      },
                      {
                        "activityTypeCode": "3",
                        "applicableRate": 27,
                        "fiscalYear": "2006",
                        "onOffCampusFlag": true,
                        "rateClassCode": "5",
                        "rateClass": {
                          "code": "5",
                          "description": "Employee Benefits"
                        },
                        "rateTypeCode": "1",
                        "rateType": {
                          "rateClassCode": "5",
                          "rateTypeCode": "1",
                          "description": "Research Rate"
                        },
                        "startDate": "2005-07-01",
                        "instituteRate": 27
                      },
                      {
                        "activityTypeCode": "3",
                        "applicableRate": 0,
                        "fiscalYear": "2004",
                        "onOffCampusFlag": false,
                        "rateClassCode": "5",
                        "rateClass": {
                          "code": "5",
                          "description": "Employee Benefits"
                        },
                        "rateTypeCode": "2",
                        "rateType": {
                          "rateClassCode": "5",
                          "rateTypeCode": "2",
                          "description": "UROP Rate"
                        },
                        "startDate": "2003-07-01",
                        "instituteRate": 0
                      },
                      {
                        "activityTypeCode": "3",
                        "applicableRate": 0,
                        "fiscalYear": "2004",
                        "onOffCampusFlag": true,
                        "rateClassCode": "5",
                        "rateClass": {
                          "code": "5",
                          "description": "Employee Benefits"
                        },
                        "rateTypeCode": "2",
                        "rateType": {
                          "rateClassCode": "5",
                          "rateTypeCode": "2",
                          "description": "UROP Rate"
                        },
                        "startDate": "2003-07-01",
                        "instituteRate": 0
                      },
                      {
                        "activityTypeCode": "3",
                        "applicableRate": 25,
                        "fiscalYear": "2006",
                        "onOffCampusFlag": false,
                        "rateClassCode": "5",
                        "rateClass": {
                          "code": "5",
                          "description": "Employee Benefits"
                        },
                        "rateTypeCode": "3",
                        "rateType": {
                          "rateClassCode": "5",
                          "rateTypeCode": "3",
                          "description": "EB on LA"
                        },
                        "startDate": "2005-07-01",
                        "instituteRate": 25
                      },
                      {
                        "activityTypeCode": "3",
                        "applicableRate": 27,
                        "fiscalYear": "2006",
                        "onOffCampusFlag": true,
                        "rateClassCode": "5",
                        "rateClass": {
                          "code": "5",
                          "description": "Employee Benefits"
                        },
                        "rateTypeCode": "3",
                        "rateType": {
                          "rateClassCode": "5",
                          "rateTypeCode": "3",
                          "description": "EB on LA"
                        },
                        "startDate": "2005-07-01",
                        "instituteRate": 27
                      },
                      {
                        "activityTypeCode": "3",
                        "applicableRate": 3,
                        "fiscalYear": "2016",
                        "onOffCampusFlag": false,
                        "rateClassCode": "7",
                        "rateClass": {
                          "code": "7",
                          "description": "Inflation"
                        },
                        "rateTypeCode": "1",
                        "rateType": {
                          "rateClassCode": "7",
                          "rateTypeCode": "1",
                          "description": "Faculty Salaries (6/1)"
                        },
                        "startDate": "2016-01-07",
                        "instituteRate": 3
                      },
                      {
                        "activityTypeCode": "3",
                        "applicableRate": 3,
                        "fiscalYear": "2016",
                        "onOffCampusFlag": true,
                        "rateClassCode": "7",
                        "rateClass": {
                          "code": "7",
                          "description": "Inflation"
                        },
                        "rateTypeCode": "1",
                        "rateType": {
                          "rateClassCode": "7",
                          "rateTypeCode": "1",
                          "description": "Faculty Salaries (6/1)"
                        },
                        "startDate": "2016-01-07",
                        "instituteRate": 3
                      },
                      {
                        "activityTypeCode": "3",
                        "applicableRate": 3,
                        "fiscalYear": "2016",
                        "onOffCampusFlag": false,
                        "rateClassCode": "7",
                        "rateClass": {
                          "code": "7",
                          "description": "Inflation"
                        },
                        "rateTypeCode": "2",
                        "rateType": {
                          "rateClassCode": "7",
                          "rateTypeCode": "2",
                          "description": "Administrative Salaries (7/1)"
                        },
                        "startDate": "2016-01-07",
                        "instituteRate": 3
                      },
                      {
                        "activityTypeCode": "3",
                        "applicableRate": 3,
                        "fiscalYear": "2016",
                        "onOffCampusFlag": true,
                        "rateClassCode": "7",
                        "rateClass": {
                          "code": "7",
                          "description": "Inflation"
                        },
                        "rateTypeCode": "2",
                        "rateType": {
                          "rateClassCode": "7",
                          "rateTypeCode": "2",
                          "description": "Administrative Salaries (7/1)"
                        },
                        "startDate": "2016-01-07",
                        "instituteRate": 3
                      },
                      {
                        "activityTypeCode": "3",
                        "applicableRate": 3,
                        "fiscalYear": "2016",
                        "onOffCampusFlag": false,
                        "rateClassCode": "7",
                        "rateClass": {
                          "code": "7",
                          "description": "Inflation"
                        },
                        "rateTypeCode": "3",
                        "rateType": {
                          "rateClassCode": "7",
                          "rateTypeCode": "3",
                          "description": "Support Staff Salaries (4/1)"
                        },
                        "startDate": "2016-01-04",
                        "instituteRate": 3
                      },
                      {
                        "activityTypeCode": "3",
                        "applicableRate": 3,
                        "fiscalYear": "2016",
                        "onOffCampusFlag": true,
                        "rateClassCode": "7",
                        "rateClass": {
                          "code": "7",
                          "description": "Inflation"
                        },
                        "rateTypeCode": "3",
                        "rateType": {
                          "rateClassCode": "7",
                          "rateTypeCode": "3",
                          "description": "Support Staff Salaries (4/1)"
                        },
                        "startDate": "2016-01-04",
                        "instituteRate": 3
                      },
                      {
                        "activityTypeCode": "3",
                        "applicableRate": 3,
                        "fiscalYear": "2016",
                        "onOffCampusFlag": false,
                        "rateClassCode": "7",
                        "rateClass": {
                          "code": "7",
                          "description": "Inflation"
                        },
                        "rateTypeCode": "4",
                        "rateType": {
                          "rateClassCode": "7",
                          "rateTypeCode": "4",
                          "description": "Materials and Services"
                        },
                        "startDate": "2016-01-07",
                        "instituteRate": 3
                      },
                      {
                        "activityTypeCode": "3",
                        "applicableRate": 3,
                        "fiscalYear": "2016",
                        "onOffCampusFlag": true,
                        "rateClassCode": "7",
                        "rateClass": {
                          "code": "7",
                          "description": "Inflation"
                        },
                        "rateTypeCode": "4",
                        "rateType": {
                          "rateClassCode": "7",
                          "rateTypeCode": "4",
                          "description": "Materials and Services"
                        },
                        "startDate": "2016-01-07",
                        "instituteRate": 3
                      },
                      {
                        "activityTypeCode": "3",
                        "applicableRate": 3,
                        "fiscalYear": "2016",
                        "onOffCampusFlag": false,
                        "rateClassCode": "7",
                        "rateClass": {
                          "code": "7",
                          "description": "Inflation"
                        },
                        "rateTypeCode": "5",
                        "rateType": {
                          "rateClassCode": "7",
                          "rateTypeCode": "5",
                          "description": "Research Staff (1/1)"
                        },
                        "startDate": "2016-01-01",
                        "instituteRate": 3
                      },
                      {
                        "activityTypeCode": "3",
                        "applicableRate": 3,
                        "fiscalYear": "2016",
                        "onOffCampusFlag": true,
                        "rateClassCode": "7",
                        "rateClass": {
                          "code": "7",
                          "description": "Inflation"
                        },
                        "rateTypeCode": "5",
                        "rateType": {
                          "rateClassCode": "7",
                          "rateTypeCode": "5",
                          "description": "Research Staff (1/1)"
                        },
                        "startDate": "2016-01-01",
                        "instituteRate": 3
                      },
                      {
                        "activityTypeCode": "3",
                        "applicableRate": 3,
                        "fiscalYear": "2011",
                        "onOffCampusFlag": false,
                        "rateClassCode": "7",
                        "rateClass": {
                          "code": "7",
                          "description": "Inflation"
                        },
                        "rateTypeCode": "6",
                        "rateType": {
                          "rateClassCode": "7",
                          "rateTypeCode": "6",
                          "description": "Students (6/1)"
                        },
                        "startDate": "2011-06-01",
                        "instituteRate": 3
                      },
                      {
                        "activityTypeCode": "3",
                        "applicableRate": 3,
                        "fiscalYear": "2011",
                        "onOffCampusFlag": true,
                        "rateClassCode": "7",
                        "rateClass": {
                          "code": "7",
                          "description": "Inflation"
                        },
                        "rateTypeCode": "6",
                        "rateType": {
                          "rateClassCode": "7",
                          "rateTypeCode": "6",
                          "description": "Students (6/1)"
                        },
                        "startDate": "2011-06-01",
                        "instituteRate": 3
                      },
                      {
                        "activityTypeCode": "3",
                        "applicableRate": 9.5,
                        "fiscalYear": "2006",
                        "onOffCampusFlag": false,
                        "rateClassCode": "8",
                        "rateClass": {
                          "code": "8",
                          "description": "Vacation"
                        },
                        "rateTypeCode": "1",
                        "rateType": {
                          "rateClassCode": "8",
                          "rateTypeCode": "1",
                          "description": "Vacation "
                        },
                        "startDate": "2005-07-01",
                        "instituteRate": 9.5
                      },
                      {
                        "activityTypeCode": "3",
                        "applicableRate": 10.5,
                        "fiscalYear": "2006",
                        "onOffCampusFlag": true,
                        "rateClassCode": "8",
                        "rateClass": {
                          "code": "8",
                          "description": "Vacation"
                        },
                        "rateTypeCode": "1",
                        "rateType": {
                          "rateClassCode": "8",
                          "rateTypeCode": "1",
                          "description": "Vacation "
                        },
                        "startDate": "2005-07-01",
                        "instituteRate": 10.5
                      },
                      {
                        "activityTypeCode": "3",
                        "applicableRate": 8,
                        "fiscalYear": "2006",
                        "onOffCampusFlag": false,
                        "rateClassCode": "8",
                        "rateClass": {
                          "code": "8",
                          "description": "Vacation"
                        },
                        "rateTypeCode": "2",
                        "rateType": {
                          "rateClassCode": "8",
                          "rateTypeCode": "2",
                          "description": "Vacation on LA"
                        },
                        "startDate": "2005-07-01",
                        "instituteRate": 8
                      },
                      {
                        "activityTypeCode": "3",
                        "applicableRate": 8,
                        "fiscalYear": "2006",
                        "onOffCampusFlag": true,
                        "rateClassCode": "8",
                        "rateClass": {
                          "code": "8",
                          "description": "Vacation"
                        },
                        "rateTypeCode": "2",
                        "rateType": {
                          "rateClassCode": "8",
                          "rateTypeCode": "2",
                          "description": "Vacation on LA"
                        },
                        "startDate": "2005-07-01",
                        "instituteRate": 8
                      },
                      {
                        "activityTypeCode": "3",
                        "applicableRate": 0,
                        "fiscalYear": "2000",
                        "onOffCampusFlag": false,
                        "rateClassCode": "9",
                        "rateClass": {
                          "code": "9",
                          "description": "Other"
                        },
                        "rateTypeCode": "1",
                        "rateType": {
                          "rateClassCode": "9",
                          "rateTypeCode": "1",
                          "description": "Other"
                        },
                        "startDate": "1999-07-01",
                        "instituteRate": 0
                      },
                      {
                        "activityTypeCode": "3",
                        "applicableRate": 0,
                        "fiscalYear": "2000",
                        "onOffCampusFlag": true,
                        "rateClassCode": "9",
                        "rateClass": {
                          "code": "9",
                          "description": "Other"
                        },
                        "rateTypeCode": "1",
                        "rateType": {
                          "rateClassCode": "9",
                          "rateTypeCode": "1",
                          "description": "Other"
                        },
                        "startDate": "1999-07-01",
                        "instituteRate": 0
                      }
                    ],
                    "budgetLaRates": [
                      {
                        "activityTypeCode": null,
                        "applicableRate": 8,
                        "fiscalYear": "2005",
                        "onOffCampusFlag": false,
                        "rateClassCode": "12",
                        "rateClass": {
                          "code": "12",
                          "description": "Lab Allocation - Utilities"
                        },
                        "rateTypeCode": "1",
                        "rateType": {
                          "rateClassCode": "12",
                          "rateTypeCode": "1",
                          "description": "Lab Allocation - Utilities"
                        },
                        "startDate": "2004-07-01",
                        "instituteRate": 8
                      },
                      {
                        "activityTypeCode": null,
                        "applicableRate": 8,
                        "fiscalYear": "2005",
                        "onOffCampusFlag": true,
                        "rateClassCode": "12",
                        "rateClass": {
                          "code": "12",
                          "description": "Lab Allocation - Utilities"
                        },
                        "rateTypeCode": "1",
                        "rateType": {
                          "rateClassCode": "12",
                          "rateTypeCode": "1",
                          "description": "Lab Allocation - Utilities"
                        },
                        "startDate": "2004-07-01",
                        "instituteRate": 8
                      },
                      {
                        "activityTypeCode": null,
                        "applicableRate": 1,
                        "fiscalYear": "2011",
                        "onOffCampusFlag": false,
                        "rateClassCode": "11",
                        "rateClass": {
                          "code": "11",
                          "description": "Lab Allocation - M&S"
                        },
                        "rateTypeCode": "1",
                        "rateType": {
                          "rateClassCode": "11",
                          "rateTypeCode": "1",
                          "description": "Lab Allocation - M&S"
                        },
                        "startDate": "2010-07-01",
                        "instituteRate": 1
                      },
                      {
                        "activityTypeCode": null,
                        "applicableRate": 1,
                        "fiscalYear": "2011",
                        "onOffCampusFlag": true,
                        "rateClassCode": "11",
                        "rateClass": {
                          "code": "11",
                          "description": "Lab Allocation - M&S"
                        },
                        "rateTypeCode": "1",
                        "rateType": {
                          "rateClassCode": "11",
                          "rateTypeCode": "1",
                          "description": "Lab Allocation - M&S"
                        },
                        "startDate": "2010-07-01",
                        "instituteRate": 1
                      },
                      {
                        "activityTypeCode": null,
                        "applicableRate": 8,
                        "fiscalYear": "2012",
                        "onOffCampusFlag": false,
                        "rateClassCode": "10",
                        "rateClass": {
                          "code": "10",
                          "description": "Lab Allocation - Salaries"
                        },
                        "rateTypeCode": "1",
                        "rateType": {
                          "rateClassCode": "10",
                          "rateTypeCode": "1",
                          "description": "Lab Allocation - Salaries"
                        },
                        "startDate": "2011-07-01",
                        "instituteRate": 8
                      },
                      {
                        "activityTypeCode": null,
                        "applicableRate": 8,
                        "fiscalYear": "2012",
                        "onOffCampusFlag": true,
                        "rateClassCode": "10",
                        "rateClass": {
                          "code": "10",
                          "description": "Lab Allocation - Salaries"
                        },
                        "rateTypeCode": "1",
                        "rateType": {
                          "rateClassCode": "10",
                          "rateTypeCode": "1",
                          "description": "Lab Allocation - Salaries"
                        },
                        "startDate": "2011-07-01",
                        "instituteRate": 8
                      }
                    ],
                    "budgetPeriods": [
                      {
                        "comments": null,
                        "costSharingAmount": 0,
                        "endDate": "2016-06-30",
                        "startDate": "2016-06-30",
                        "totalCost": 100000,
                        "totalCostLimit": 100000,
                        "totalDirectCost": 100000,
                        "totalIndirectCost": 0,
                        "underrecoveryAmount": 0,
                        "budgetLineItems": [
                          {
                            "costSharingAmount": 0,
                            "budgetLineItemId": 9,
                            "lineItemNumber": 1,
                            "applyInRateFlag": true,
                            "basedOnLineItem": null,
                            "budgetJustification": null,
                            "groupName": null,
                            "endDate": "2016-06-30",
                            "lineItemCost": 100000,
                            "obligatedAmount": 0,
                            "lineItemDescription": null,
                            "lineItemSequence": 1,
                            "onOffCampusFlag": true,
                            "quantity": 1,
                            "startDate": "2016-06-30",
                            "underrecoveryAmount": 0,
                            "formulatedCostElementFlag": false,
                            "costElement": "421818",
                            "costElementBO": {
                              "costElement": "421818",
                              "description": "Equipment - Not MTDC",
                              "onOffCampusFlag": true,
                              "active": true
                            },
                            "budgetCategoryCode": "20",
                            "budgetCategory": {
                              "code": "20",
                              "description": "Equipment"
                            },
                            "budgetPersonnelDetailsList": [],
                            "budgetLineItemCalculatedAmounts": []
                          }
                        ],
                        "numberOfParticipants": null,
                        "directCostLimit": 0,
                        "expenseTotal": null,
                        "budgetId": 1
                      }
                    ],
                    "awardBudgetLimits": [
                      {
                        "budgetLimitId": 4,
                        "awardId": null,
                        "budgetId": 1,
                        "limitTypeCode": "directCost",
                        "limit": null
                      },
                      {
                        "budgetLimitId": 5,
                        "awardId": null,
                        "budgetId": 1,
                        "limitTypeCode": "indirectCost",
                        "limit": null
                      },
                      {
                        "budgetLimitId": 6,
                        "awardId": null,
                        "budgetId": 1,
                        "limitTypeCode": "totalCost",
                        "limit": null
                      }
                    ],
                    "budgetPersons": [
                      {
                        "effectiveDate": "2016-06-30",
                        "jobCode": "AA000",
                        "nonEmployeeFlag": false,
                        "personId": "10000000002",
                        "rolodexId": null,
                        "appointmentTypeCode": "6",
                        "calculationBase": 0,
                        "personName": "Joe Tester",
                        "salaryAnniversaryDate": null
                      }
                    ]
                  }
                ],
                "fainId": null,
                "fedAwardYear": null,
                "fedAwardDate": null,
                "posted": true,
                "awardSponsorContacts": [
                  {
                    "rolodexId": 1736,
                    "roleCode": "1",
                    "awardContactId": 4263,
                    "orgName": "Office of Naval Research"
                  }
                ]
              }
            }
          ]

### Update Award Post [PUT /award/api/v1/award-posts/16]

Set various fields on an the award-post. Fields not provided will not be updated.

+ Parameters
	+ awardPostId: `16` (number, required) - Returns the award post record associated with the awardPostId.

+ Request
    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

    + Body

            {
              "id": 16,
              "accountNumber": "55555",
              "posted": true,
              "active": false,
              "documentNumber": "4469",
              "updateUser": "quickstart",
              "updateTimestamp": 1468434837000,
              "awardId": 2799,
              "awardDto": null
            }

+ Response 200
    + Headers

            Content-Length:0

    + Body

                {
                  "id": 16,
                  "accountNumber": "55555",
                  "posted": true,
                  "active": false,
                  "documentNumber": "4469",
                  "updateUser": "quickstart",
                  "updateTimestamp": 1468434837000,
                  "awardId": 2799,
                  "awardDto": null
                }


