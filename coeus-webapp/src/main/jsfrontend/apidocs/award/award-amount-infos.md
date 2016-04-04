## Award Amount Infos [/research-sys/api/v1/award-amount-infos/]

### Get Award Amount Infos by Key [GET /research-sys/api/v1/award-amount-infos/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardAmountInfoId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","anticipatedTotalAmount": "(val)","antDistributableAmount": "(val)","finalExpirationDate": "(val)","currentFundEffectiveDate": "(val)","amountObligatedToDate": "(val)","obliDistributableAmount": "(val)","obligationExpirationDate": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","entryType": "(val)","eomProcessFlag": "(val)","anticipatedChange": "(val)","obligatedChange": "(val)","obligatedChangeDirect": "(val)","obligatedChangeIndirect": "(val)","anticipatedChangeDirect": "(val)","anticipatedChangeIndirect": "(val)","anticipatedTotalDirect": "(val)","anticipatedTotalIndirect": "(val)","obligatedTotalDirect": "(val)","obligatedTotalIndirect": "(val)","originatingAwardVersion": "(val)","_primaryKey": "(val)"}

### Get All Award Amount Infos [GET /research-sys/api/v1/award-amount-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardAmountInfoId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","anticipatedTotalAmount": "(val)","antDistributableAmount": "(val)","finalExpirationDate": "(val)","currentFundEffectiveDate": "(val)","amountObligatedToDate": "(val)","obliDistributableAmount": "(val)","obligationExpirationDate": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","entryType": "(val)","eomProcessFlag": "(val)","anticipatedChange": "(val)","obligatedChange": "(val)","obligatedChangeDirect": "(val)","obligatedChangeIndirect": "(val)","anticipatedChangeDirect": "(val)","anticipatedChangeIndirect": "(val)","anticipatedTotalDirect": "(val)","anticipatedTotalIndirect": "(val)","obligatedTotalDirect": "(val)","obligatedTotalIndirect": "(val)","originatingAwardVersion": "(val)","_primaryKey": "(val)"},
              {"awardAmountInfoId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","anticipatedTotalAmount": "(val)","antDistributableAmount": "(val)","finalExpirationDate": "(val)","currentFundEffectiveDate": "(val)","amountObligatedToDate": "(val)","obliDistributableAmount": "(val)","obligationExpirationDate": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","entryType": "(val)","eomProcessFlag": "(val)","anticipatedChange": "(val)","obligatedChange": "(val)","obligatedChangeDirect": "(val)","obligatedChangeIndirect": "(val)","anticipatedChangeDirect": "(val)","anticipatedChangeIndirect": "(val)","anticipatedTotalDirect": "(val)","anticipatedTotalIndirect": "(val)","obligatedTotalDirect": "(val)","obligatedTotalIndirect": "(val)","originatingAwardVersion": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Amount Infos with Filtering [GET /research-sys/api/v1/award-amount-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + awardAmountInfoId
            + awardId
            + awardNumber
            + sequenceNumber
            + anticipatedTotalAmount
            + antDistributableAmount
            + finalExpirationDate
            + currentFundEffectiveDate
            + amountObligatedToDate
            + obliDistributableAmount
            + obligationExpirationDate
            + transactionId
            + timeAndMoneyDocumentNumber
            + entryType
            + eomProcessFlag
            + anticipatedChange
            + obligatedChange
            + obligatedChangeDirect
            + obligatedChangeIndirect
            + anticipatedChangeDirect
            + anticipatedChangeIndirect
            + anticipatedTotalDirect
            + anticipatedTotalIndirect
            + obligatedTotalDirect
            + obligatedTotalIndirect
            + originatingAwardVersion
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardAmountInfoId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","anticipatedTotalAmount": "(val)","antDistributableAmount": "(val)","finalExpirationDate": "(val)","currentFundEffectiveDate": "(val)","amountObligatedToDate": "(val)","obliDistributableAmount": "(val)","obligationExpirationDate": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","entryType": "(val)","eomProcessFlag": "(val)","anticipatedChange": "(val)","obligatedChange": "(val)","obligatedChangeDirect": "(val)","obligatedChangeIndirect": "(val)","anticipatedChangeDirect": "(val)","anticipatedChangeIndirect": "(val)","anticipatedTotalDirect": "(val)","anticipatedTotalIndirect": "(val)","obligatedTotalDirect": "(val)","obligatedTotalIndirect": "(val)","originatingAwardVersion": "(val)","_primaryKey": "(val)"},
              {"awardAmountInfoId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","anticipatedTotalAmount": "(val)","antDistributableAmount": "(val)","finalExpirationDate": "(val)","currentFundEffectiveDate": "(val)","amountObligatedToDate": "(val)","obliDistributableAmount": "(val)","obligationExpirationDate": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","entryType": "(val)","eomProcessFlag": "(val)","anticipatedChange": "(val)","obligatedChange": "(val)","obligatedChangeDirect": "(val)","obligatedChangeIndirect": "(val)","anticipatedChangeDirect": "(val)","anticipatedChangeIndirect": "(val)","anticipatedTotalDirect": "(val)","anticipatedTotalIndirect": "(val)","obligatedTotalDirect": "(val)","obligatedTotalIndirect": "(val)","originatingAwardVersion": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Amount Infos [GET /research-sys/api/v1/award-amount-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters

            + _schema (required) - will instruct the endpoint to return a schema data structure for the resource

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            ${sampleSchema}
		
### Get Blueprint API specification for Award Amount Infos [GET /research-sys/api/v1/award-amount-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Amount Infos.md"
            transfer-encoding:chunked


### Update Award Amount Infos [PUT /research-sys/api/v1/award-amount-infos/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardAmountInfoId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","anticipatedTotalAmount": "(val)","antDistributableAmount": "(val)","finalExpirationDate": "(val)","currentFundEffectiveDate": "(val)","amountObligatedToDate": "(val)","obliDistributableAmount": "(val)","obligationExpirationDate": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","entryType": "(val)","eomProcessFlag": "(val)","anticipatedChange": "(val)","obligatedChange": "(val)","obligatedChangeDirect": "(val)","obligatedChangeIndirect": "(val)","anticipatedChangeDirect": "(val)","anticipatedChangeIndirect": "(val)","anticipatedTotalDirect": "(val)","anticipatedTotalIndirect": "(val)","obligatedTotalDirect": "(val)","obligatedTotalIndirect": "(val)","originatingAwardVersion": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Amount Infos [PUT /research-sys/api/v1/award-amount-infos/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardAmountInfoId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","anticipatedTotalAmount": "(val)","antDistributableAmount": "(val)","finalExpirationDate": "(val)","currentFundEffectiveDate": "(val)","amountObligatedToDate": "(val)","obliDistributableAmount": "(val)","obligationExpirationDate": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","entryType": "(val)","eomProcessFlag": "(val)","anticipatedChange": "(val)","obligatedChange": "(val)","obligatedChangeDirect": "(val)","obligatedChangeIndirect": "(val)","anticipatedChangeDirect": "(val)","anticipatedChangeIndirect": "(val)","anticipatedTotalDirect": "(val)","anticipatedTotalIndirect": "(val)","obligatedTotalDirect": "(val)","obligatedTotalIndirect": "(val)","originatingAwardVersion": "(val)","_primaryKey": "(val)"},
              {"awardAmountInfoId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","anticipatedTotalAmount": "(val)","antDistributableAmount": "(val)","finalExpirationDate": "(val)","currentFundEffectiveDate": "(val)","amountObligatedToDate": "(val)","obliDistributableAmount": "(val)","obligationExpirationDate": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","entryType": "(val)","eomProcessFlag": "(val)","anticipatedChange": "(val)","obligatedChange": "(val)","obligatedChangeDirect": "(val)","obligatedChangeIndirect": "(val)","anticipatedChangeDirect": "(val)","anticipatedChangeIndirect": "(val)","anticipatedTotalDirect": "(val)","anticipatedTotalIndirect": "(val)","obligatedTotalDirect": "(val)","obligatedTotalIndirect": "(val)","originatingAwardVersion": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Amount Infos [POST /research-sys/api/v1/award-amount-infos/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardAmountInfoId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","anticipatedTotalAmount": "(val)","antDistributableAmount": "(val)","finalExpirationDate": "(val)","currentFundEffectiveDate": "(val)","amountObligatedToDate": "(val)","obliDistributableAmount": "(val)","obligationExpirationDate": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","entryType": "(val)","eomProcessFlag": "(val)","anticipatedChange": "(val)","obligatedChange": "(val)","obligatedChangeDirect": "(val)","obligatedChangeIndirect": "(val)","anticipatedChangeDirect": "(val)","anticipatedChangeIndirect": "(val)","anticipatedTotalDirect": "(val)","anticipatedTotalIndirect": "(val)","obligatedTotalDirect": "(val)","obligatedTotalIndirect": "(val)","originatingAwardVersion": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardAmountInfoId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","anticipatedTotalAmount": "(val)","antDistributableAmount": "(val)","finalExpirationDate": "(val)","currentFundEffectiveDate": "(val)","amountObligatedToDate": "(val)","obliDistributableAmount": "(val)","obligationExpirationDate": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","entryType": "(val)","eomProcessFlag": "(val)","anticipatedChange": "(val)","obligatedChange": "(val)","obligatedChangeDirect": "(val)","obligatedChangeIndirect": "(val)","anticipatedChangeDirect": "(val)","anticipatedChangeIndirect": "(val)","anticipatedTotalDirect": "(val)","anticipatedTotalIndirect": "(val)","obligatedTotalDirect": "(val)","obligatedTotalIndirect": "(val)","originatingAwardVersion": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Amount Infos [POST /research-sys/api/v1/award-amount-infos/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardAmountInfoId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","anticipatedTotalAmount": "(val)","antDistributableAmount": "(val)","finalExpirationDate": "(val)","currentFundEffectiveDate": "(val)","amountObligatedToDate": "(val)","obliDistributableAmount": "(val)","obligationExpirationDate": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","entryType": "(val)","eomProcessFlag": "(val)","anticipatedChange": "(val)","obligatedChange": "(val)","obligatedChangeDirect": "(val)","obligatedChangeIndirect": "(val)","anticipatedChangeDirect": "(val)","anticipatedChangeIndirect": "(val)","anticipatedTotalDirect": "(val)","anticipatedTotalIndirect": "(val)","obligatedTotalDirect": "(val)","obligatedTotalIndirect": "(val)","originatingAwardVersion": "(val)","_primaryKey": "(val)"},
              {"awardAmountInfoId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","anticipatedTotalAmount": "(val)","antDistributableAmount": "(val)","finalExpirationDate": "(val)","currentFundEffectiveDate": "(val)","amountObligatedToDate": "(val)","obliDistributableAmount": "(val)","obligationExpirationDate": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","entryType": "(val)","eomProcessFlag": "(val)","anticipatedChange": "(val)","obligatedChange": "(val)","obligatedChangeDirect": "(val)","obligatedChangeIndirect": "(val)","anticipatedChangeDirect": "(val)","anticipatedChangeIndirect": "(val)","anticipatedTotalDirect": "(val)","anticipatedTotalIndirect": "(val)","obligatedTotalDirect": "(val)","obligatedTotalIndirect": "(val)","originatingAwardVersion": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardAmountInfoId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","anticipatedTotalAmount": "(val)","antDistributableAmount": "(val)","finalExpirationDate": "(val)","currentFundEffectiveDate": "(val)","amountObligatedToDate": "(val)","obliDistributableAmount": "(val)","obligationExpirationDate": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","entryType": "(val)","eomProcessFlag": "(val)","anticipatedChange": "(val)","obligatedChange": "(val)","obligatedChangeDirect": "(val)","obligatedChangeIndirect": "(val)","anticipatedChangeDirect": "(val)","anticipatedChangeIndirect": "(val)","anticipatedTotalDirect": "(val)","anticipatedTotalIndirect": "(val)","obligatedTotalDirect": "(val)","obligatedTotalIndirect": "(val)","originatingAwardVersion": "(val)","_primaryKey": "(val)"},
              {"awardAmountInfoId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","anticipatedTotalAmount": "(val)","antDistributableAmount": "(val)","finalExpirationDate": "(val)","currentFundEffectiveDate": "(val)","amountObligatedToDate": "(val)","obliDistributableAmount": "(val)","obligationExpirationDate": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","entryType": "(val)","eomProcessFlag": "(val)","anticipatedChange": "(val)","obligatedChange": "(val)","obligatedChangeDirect": "(val)","obligatedChangeIndirect": "(val)","anticipatedChangeDirect": "(val)","anticipatedChangeIndirect": "(val)","anticipatedTotalDirect": "(val)","anticipatedTotalIndirect": "(val)","obligatedTotalDirect": "(val)","obligatedTotalIndirect": "(val)","originatingAwardVersion": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Amount Infos by Key [DELETE /research-sys/api/v1/award-amount-infos/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Amount Infos [DELETE /research-sys/api/v1/award-amount-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Award Amount Infos with Matching [DELETE /research-sys/api/v1/award-amount-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + awardAmountInfoId
            + awardId
            + awardNumber
            + sequenceNumber
            + anticipatedTotalAmount
            + antDistributableAmount
            + finalExpirationDate
            + currentFundEffectiveDate
            + amountObligatedToDate
            + obliDistributableAmount
            + obligationExpirationDate
            + transactionId
            + timeAndMoneyDocumentNumber
            + entryType
            + eomProcessFlag
            + anticipatedChange
            + obligatedChange
            + obligatedChangeDirect
            + obligatedChangeIndirect
            + anticipatedChangeDirect
            + anticipatedChangeIndirect
            + anticipatedTotalDirect
            + anticipatedTotalIndirect
            + obligatedTotalDirect
            + obligatedTotalIndirect
            + originatingAwardVersion


+ Response 204
