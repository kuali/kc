## Transaction Details [/research-sys/api/v1/transaction-details/]

### Get Transaction Details by Key [GET /research-sys/api/v1/transaction-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"transactionDetailId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","transactionDetailType": "(val)","_primaryKey": "(val)"}

### Get All Transaction Details [GET /research-sys/api/v1/transaction-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"transactionDetailId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","transactionDetailType": "(val)","_primaryKey": "(val)"},
              {"transactionDetailId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","transactionDetailType": "(val)","_primaryKey": "(val)"}
            ]

### Get All Transaction Details with Filtering [GET /research-sys/api/v1/transaction-details/]
    
+ Parameters

        + transactionDetailId
            + awardNumber
            + sequenceNumber
            + transactionId
            + timeAndMoneyDocumentNumber
            + sourceAwardNumber
            + destinationAwardNumber
            + obligatedAmount
            + obligatedDirectAmount
            + obligatedIndirectAmount
            + anticipatedAmount
            + anticipatedDirectAmount
            + anticipatedIndirectAmount
            + comments
            + transactionDetailType

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"transactionDetailId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","transactionDetailType": "(val)","_primaryKey": "(val)"},
              {"transactionDetailId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","transactionDetailType": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Transaction Details [GET /research-sys/api/v1/transaction-details/]
	                                          
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
    
            {"columns":["transactionDetailId","awardNumber","sequenceNumber","transactionId","timeAndMoneyDocumentNumber","sourceAwardNumber","destinationAwardNumber","obligatedAmount","obligatedDirectAmount","obligatedIndirectAmount","anticipatedAmount","anticipatedDirectAmount","anticipatedIndirectAmount","comments","transactionDetailType"],"primaryKey":"transactionDetailId"}
		
### Get Blueprint API specification for Transaction Details [GET /research-sys/api/v1/transaction-details/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Transaction Details.md"
            transfer-encoding:chunked


### Update Transaction Details [PUT /research-sys/api/v1/transaction-details/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"transactionDetailId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","transactionDetailType": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Transaction Details [PUT /research-sys/api/v1/transaction-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"transactionDetailId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","transactionDetailType": "(val)","_primaryKey": "(val)"},
              {"transactionDetailId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","transactionDetailType": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Transaction Details [POST /research-sys/api/v1/transaction-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"transactionDetailId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","transactionDetailType": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"transactionDetailId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","transactionDetailType": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Transaction Details [POST /research-sys/api/v1/transaction-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"transactionDetailId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","transactionDetailType": "(val)","_primaryKey": "(val)"},
              {"transactionDetailId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","transactionDetailType": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"transactionDetailId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","transactionDetailType": "(val)","_primaryKey": "(val)"},
              {"transactionDetailId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","transactionId": "(val)","timeAndMoneyDocumentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","transactionDetailType": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Transaction Details by Key [DELETE /research-sys/api/v1/transaction-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Transaction Details [DELETE /research-sys/api/v1/transaction-details/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Transaction Details with Matching [DELETE /research-sys/api/v1/transaction-details/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + transactionDetailId
            + awardNumber
            + sequenceNumber
            + transactionId
            + timeAndMoneyDocumentNumber
            + sourceAwardNumber
            + destinationAwardNumber
            + obligatedAmount
            + obligatedDirectAmount
            + obligatedIndirectAmount
            + anticipatedAmount
            + anticipatedDirectAmount
            + anticipatedIndirectAmount
            + comments
            + transactionDetailType

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
