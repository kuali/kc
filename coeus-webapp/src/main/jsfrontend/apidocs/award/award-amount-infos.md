## Award Amount Infos [/award/api/v1/award-amount-infos/]

### Get Award Amount Infos by Key [GET /award/api/v1/award-amount-infos/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardAmountInfoId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","anticipatedTotalAmount": "(val)","antDistributableAmount": "(val)","finalExpirationDate": "(val)","currentFundEffectiveDate": "(val)","amountObligatedToDate": "(val)","obliDistributableAmount": "(val)","obligationExpirationDate": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","entryType": "(val)","eomProcessFlag": "(val)","anticipatedChange": "(val)","obligatedChange": "(val)","obligatedChangeDirect": "(val)","obligatedChangeIndirect": "(val)","anticipatedChangeDirect": "(val)","anticipatedChangeIndirect": "(val)","anticipatedTotalDirect": "(val)","anticipatedTotalIndirect": "(val)","obligatedTotalDirect": "(val)","obligatedTotalIndirect": "(val)","originatingAwardVersion": "(val)","award.awardId": "(val)","_primaryKey": "(val)"}

### Get All Award Amount Infos [GET /award/api/v1/award-amount-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardAmountInfoId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","anticipatedTotalAmount": "(val)","antDistributableAmount": "(val)","finalExpirationDate": "(val)","currentFundEffectiveDate": "(val)","amountObligatedToDate": "(val)","obliDistributableAmount": "(val)","obligationExpirationDate": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","entryType": "(val)","eomProcessFlag": "(val)","anticipatedChange": "(val)","obligatedChange": "(val)","obligatedChangeDirect": "(val)","obligatedChangeIndirect": "(val)","anticipatedChangeDirect": "(val)","anticipatedChangeIndirect": "(val)","anticipatedTotalDirect": "(val)","anticipatedTotalIndirect": "(val)","obligatedTotalDirect": "(val)","obligatedTotalIndirect": "(val)","originatingAwardVersion": "(val)","award.awardId": "(val)","_primaryKey": "(val)"},
              {"awardAmountInfoId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","anticipatedTotalAmount": "(val)","antDistributableAmount": "(val)","finalExpirationDate": "(val)","currentFundEffectiveDate": "(val)","amountObligatedToDate": "(val)","obliDistributableAmount": "(val)","obligationExpirationDate": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","entryType": "(val)","eomProcessFlag": "(val)","anticipatedChange": "(val)","obligatedChange": "(val)","obligatedChangeDirect": "(val)","obligatedChangeIndirect": "(val)","anticipatedChangeDirect": "(val)","anticipatedChangeIndirect": "(val)","anticipatedTotalDirect": "(val)","anticipatedTotalIndirect": "(val)","obligatedTotalDirect": "(val)","obligatedTotalIndirect": "(val)","originatingAwardVersion": "(val)","award.awardId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Amount Infos with Filtering [GET /award/api/v1/award-amount-infos/]
    
+ Parameters

    + awardAmountInfoId (optional) - Award Amount Info Id. Maximum length is 22.
    + awardNumber (optional) - 
    + sequenceNumber (optional) - Amount Sequence Number. Maximum length is 22.
    + anticipatedTotalAmount (optional) - Anticipated Cumulative. Maximum length is 22.
    + antDistributableAmount (optional) - Anticipated Distributable. Maximum length is 22.
    + finalExpirationDate (optional) - Final Expiration Date. Maximum length is 10.
    + currentFundEffectiveDate (optional) - Obligation Start Date. Maximum length is 10.
    + amountObligatedToDate (optional) - Obligated Cumulative. Maximum length is 22.
    + obliDistributableAmount (optional) - Obligated Distributable. Maximum length is 22.
    + obligationExpirationDate (optional) - Obligation End Date. Maximum length is 10.
    + transactionId (optional) - Transaction Id. Maximum length is 10.
    + timeAndMoneyDocumentNumber (optional) - Document Number. Maximum length is 10.
    + entryType (optional) - Entry Type. Maximum length is 1.
    + eomProcessFlag (optional) - Eom ProcessDefinitionDefinition Flag. Maximum length is 1.
    + anticipatedChange (optional) - Anticipated Change. Maximum length is 22.
    + obligatedChange (optional) - Obligated Change. Maximum length is 22.
    + obligatedChangeDirect (optional) - Obligated Change Direct. Maximum length is 22.
    + obligatedChangeIndirect (optional) - Obligated Change Indirect. Maximum length is 22.
    + anticipatedChangeDirect (optional) - Anticipated Change Direct. Maximum length is 22.
    + anticipatedChangeIndirect (optional) - Anticipated Change Indirect. Maximum length is 22.
    + anticipatedTotalDirect (optional) - Anticipated Total Direct. Maximum length is 12.
    + anticipatedTotalIndirect (optional) - Anticipated Total Indirect. Maximum length is 12.
    + obligatedTotalDirect (optional) - Obligated Total Direct. Maximum length is 12.
    + obligatedTotalIndirect (optional) - Obligated Total Indirect. Maximum length is 12.
    + originatingAwardVersion (optional) - 
    + award.awardId (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardAmountInfoId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","anticipatedTotalAmount": "(val)","antDistributableAmount": "(val)","finalExpirationDate": "(val)","currentFundEffectiveDate": "(val)","amountObligatedToDate": "(val)","obliDistributableAmount": "(val)","obligationExpirationDate": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","entryType": "(val)","eomProcessFlag": "(val)","anticipatedChange": "(val)","obligatedChange": "(val)","obligatedChangeDirect": "(val)","obligatedChangeIndirect": "(val)","anticipatedChangeDirect": "(val)","anticipatedChangeIndirect": "(val)","anticipatedTotalDirect": "(val)","anticipatedTotalIndirect": "(val)","obligatedTotalDirect": "(val)","obligatedTotalIndirect": "(val)","originatingAwardVersion": "(val)","award.awardId": "(val)","_primaryKey": "(val)"},
              {"awardAmountInfoId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","anticipatedTotalAmount": "(val)","antDistributableAmount": "(val)","finalExpirationDate": "(val)","currentFundEffectiveDate": "(val)","amountObligatedToDate": "(val)","obliDistributableAmount": "(val)","obligationExpirationDate": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","entryType": "(val)","eomProcessFlag": "(val)","anticipatedChange": "(val)","obligatedChange": "(val)","obligatedChangeDirect": "(val)","obligatedChangeIndirect": "(val)","anticipatedChangeDirect": "(val)","anticipatedChangeIndirect": "(val)","anticipatedTotalDirect": "(val)","anticipatedTotalIndirect": "(val)","obligatedTotalDirect": "(val)","obligatedTotalIndirect": "(val)","originatingAwardVersion": "(val)","award.awardId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Amount Infos [GET /award/api/v1/award-amount-infos/]
	                                          
+ Parameters

      + _schema (required) - will instruct the endpoint to return a schema data structure for the resource
      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"columns":["awardAmountInfoId","awardNumber","sequenceNumber","anticipatedTotalAmount","antDistributableAmount","finalExpirationDate","currentFundEffectiveDate","amountObligatedToDate","obliDistributableAmount","obligationExpirationDate","transactionId","timeAndMoneyDocumentNumber","entryType","eomProcessFlag","anticipatedChange","obligatedChange","obligatedChangeDirect","obligatedChangeIndirect","anticipatedChangeDirect","anticipatedChangeIndirect","anticipatedTotalDirect","anticipatedTotalIndirect","obligatedTotalDirect","obligatedTotalIndirect","originatingAwardVersion","award.awardId"],"primaryKey":"awardAmountInfoId"}
		
### Get Blueprint API specification for Award Amount Infos [GET /award/api/v1/award-amount-infos/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Amount Infos.md"
            transfer-encoding:chunked
