## Pending Transactions [/research-sys/api/v1/pending-transactions/]

### Get Pending Transactions by Key [GET /research-sys/api/v1/pending-transactions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"transactionId": "(val)","documentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","processedFlag": "(val)","singleNodeTransaction": "(val)","_primaryKey": "(val)"}

### Get All Pending Transactions [GET /research-sys/api/v1/pending-transactions/]
	 
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

### Get All Pending Transactions with Filtering [GET /research-sys/api/v1/pending-transactions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + transactionId
            + documentNumber
            + sourceAwardNumber
            + destinationAwardNumber
            + obligatedAmount
            + obligatedDirectAmount
            + obligatedIndirectAmount
            + anticipatedAmount
            + anticipatedDirectAmount
            + anticipatedIndirectAmount
            + comments
            + processedFlag
            + singleNodeTransaction
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"transactionId": "(val)","documentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","processedFlag": "(val)","singleNodeTransaction": "(val)","_primaryKey": "(val)"},
              {"transactionId": "(val)","documentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","processedFlag": "(val)","singleNodeTransaction": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Pending Transactions [GET /research-sys/api/v1/pending-transactions/]
	 
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
		
### Get Blueprint API specification for Pending Transactions [GET /research-sys/api/v1/pending-transactions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Pending Transactions.md"
            transfer-encoding:chunked


### Update Pending Transactions [PUT /research-sys/api/v1/pending-transactions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"transactionId": "(val)","documentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","processedFlag": "(val)","singleNodeTransaction": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Pending Transactions [PUT /research-sys/api/v1/pending-transactions/]

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

### Insert Pending Transactions [POST /research-sys/api/v1/pending-transactions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"transactionId": "(val)","documentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","processedFlag": "(val)","singleNodeTransaction": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"transactionId": "(val)","documentNumber": "(val)","sourceAwardNumber": "(val)","destinationAwardNumber": "(val)","obligatedAmount": "(val)","obligatedDirectAmount": "(val)","obligatedIndirectAmount": "(val)","anticipatedAmount": "(val)","anticipatedDirectAmount": "(val)","anticipatedIndirectAmount": "(val)","comments": "(val)","processedFlag": "(val)","singleNodeTransaction": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Pending Transactions [POST /research-sys/api/v1/pending-transactions/]

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
            
### Delete Pending Transactions by Key [DELETE /research-sys/api/v1/pending-transactions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Pending Transactions [DELETE /research-sys/api/v1/pending-transactions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Pending Transactions with Matching [DELETE /research-sys/api/v1/pending-transactions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + transactionId
            + documentNumber
            + sourceAwardNumber
            + destinationAwardNumber
            + obligatedAmount
            + obligatedDirectAmount
            + obligatedIndirectAmount
            + anticipatedAmount
            + anticipatedDirectAmount
            + anticipatedIndirectAmount
            + comments
            + processedFlag
            + singleNodeTransaction


+ Response 204
