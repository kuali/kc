## Pending Transactions [/award/api/v1/pending-transactions/]

### Get Pending Transactions by Key [GET /award/api/v1/pending-transactions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"transactionId": "(val)","documentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","processedFlag": "(val)","singleNodeTransaction": "(val)","_primaryKey": "(val)"}

### Get All Pending Transactions [GET /award/api/v1/pending-transactions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"transactionId": "(val)","documentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","processedFlag": "(val)","singleNodeTransaction": "(val)","_primaryKey": "(val)"},
              {"transactionId": "(val)","documentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","processedFlag": "(val)","singleNodeTransaction": "(val)","_primaryKey": "(val)"}
            ]

### Get All Pending Transactions with Filtering [GET /award/api/v1/pending-transactions/]
    
+ Parameters

    + transactionId (optional) - Transaction. Maximum length is 22.
    + documentNumber (optional) - 
    + sourceAwardNumber (optional) - Source Award. Maximum length is 12.
    + destinationAwardNumber (optional) - Destination Award. Maximum length is 12.
    + obligatedAmount (optional) - Obligated Change. Maximum length is 22.
    + obligatedDirectAmount (optional) - Obligated Direct. Maximum length is 22.
    + obligatedIndirectAmount (optional) - Obligated F&A Change. Maximum length is 22.
    + anticipatedAmount (optional) - Anticipated Change. Maximum length is 22.
    + anticipatedDirectAmount (optional) - Anticipated Direct Change. Maximum length is 22.
    + anticipatedIndirectAmount (optional) - Anticipated F&A Change. Maximum length is 22.
    + comments (optional) - Comments. Maximum length is 200.
    + processedFlag (optional) - Processed Flag. Maximum length is 1.
    + singleNodeTransaction (optional) - Single Node Transaction. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"transactionId": "(val)","documentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","processedFlag": "(val)","singleNodeTransaction": "(val)","_primaryKey": "(val)"},
              {"transactionId": "(val)","documentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","processedFlag": "(val)","singleNodeTransaction": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Pending Transactions [GET /award/api/v1/pending-transactions/]
	                                          
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
    
            {"columns":["transactionId","documentNumber","sourceAwardNumber","destinationAwardNumber","obligatedAmount","obligatedDirectAmount","obligatedIndirectAmount","anticipatedAmount","anticipatedDirectAmount","anticipatedIndirectAmount","comments","processedFlag","singleNodeTransaction"],"primaryKey":"transactionId"}
		
### Get Blueprint API specification for Pending Transactions [GET /award/api/v1/pending-transactions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Pending Transactions.md"
            transfer-encoding:chunked


### Update Pending Transactions [PUT /award/api/v1/pending-transactions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"transactionId": "(val)","documentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","processedFlag": "(val)","singleNodeTransaction": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Pending Transactions [PUT /award/api/v1/pending-transactions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"transactionId": "(val)","documentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","processedFlag": "(val)","singleNodeTransaction": "(val)","_primaryKey": "(val)"},
              {"transactionId": "(val)","documentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","processedFlag": "(val)","singleNodeTransaction": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Pending Transactions [POST /award/api/v1/pending-transactions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"transactionId": "(val)","documentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","processedFlag": "(val)","singleNodeTransaction": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"transactionId": "(val)","documentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","processedFlag": "(val)","singleNodeTransaction": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Pending Transactions [POST /award/api/v1/pending-transactions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"transactionId": "(val)","documentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","processedFlag": "(val)","singleNodeTransaction": "(val)","_primaryKey": "(val)"},
              {"transactionId": "(val)","documentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","processedFlag": "(val)","singleNodeTransaction": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"transactionId": "(val)","documentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","processedFlag": "(val)","singleNodeTransaction": "(val)","_primaryKey": "(val)"},
              {"transactionId": "(val)","documentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","processedFlag": "(val)","singleNodeTransaction": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Pending Transactions by Key [DELETE /award/api/v1/pending-transactions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Pending Transactions [DELETE /award/api/v1/pending-transactions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Pending Transactions with Matching [DELETE /award/api/v1/pending-transactions/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + transactionId (optional) - Transaction. Maximum length is 22.
    + documentNumber (optional) - 
    + sourceAwardNumber (optional) - Source Award. Maximum length is 12.
    + destinationAwardNumber (optional) - Destination Award. Maximum length is 12.
    + obligatedAmount (optional) - Obligated Change. Maximum length is 22.
    + obligatedDirectAmount (optional) - Obligated Direct. Maximum length is 22.
    + obligatedIndirectAmount (optional) - Obligated F&A Change. Maximum length is 22.
    + anticipatedAmount (optional) - Anticipated Change. Maximum length is 22.
    + anticipatedDirectAmount (optional) - Anticipated Direct Change. Maximum length is 22.
    + anticipatedIndirectAmount (optional) - Anticipated F&A Change. Maximum length is 22.
    + comments (optional) - Comments. Maximum length is 200.
    + processedFlag (optional) - Processed Flag. Maximum length is 1.
    + singleNodeTransaction (optional) - Single Node Transaction. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
