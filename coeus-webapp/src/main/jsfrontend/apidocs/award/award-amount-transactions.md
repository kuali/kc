## Award Amount Transactions [/research-sys/api/v1/award-amount-transactions/]

### Get Award Amount Transactions by Key [GET /research-sys/api/v1/award-amount-transactions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardAmountTransactionId": "(val)","awardNumber": "(val)","documentNumber": "(val)","transactionTypeCode": "(val)","noticeDate": "(val)","comments": "(val)","_primaryKey": "(val)"}

### Get All Award Amount Transactions [GET /research-sys/api/v1/award-amount-transactions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardAmountTransactionId": "(val)","awardNumber": "(val)","documentNumber": "(val)","transactionTypeCode": "(val)","noticeDate": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"awardAmountTransactionId": "(val)","awardNumber": "(val)","documentNumber": "(val)","transactionTypeCode": "(val)","noticeDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Amount Transactions with Filtering [GET /research-sys/api/v1/award-amount-transactions/]
    
+ Parameters

        + awardAmountTransactionId
            + awardNumber
            + documentNumber
            + transactionTypeCode
            + noticeDate
            + comments

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardAmountTransactionId": "(val)","awardNumber": "(val)","documentNumber": "(val)","transactionTypeCode": "(val)","noticeDate": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"awardAmountTransactionId": "(val)","awardNumber": "(val)","documentNumber": "(val)","transactionTypeCode": "(val)","noticeDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Amount Transactions [GET /research-sys/api/v1/award-amount-transactions/]
	                                          
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
    
            {"columns":["awardAmountTransactionId","awardNumber","documentNumber","transactionTypeCode","noticeDate","comments"],"primaryKey":"awardAmountTransactionId"}
		
### Get Blueprint API specification for Award Amount Transactions [GET /research-sys/api/v1/award-amount-transactions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Amount Transactions.md"
            transfer-encoding:chunked


### Update Award Amount Transactions [PUT /research-sys/api/v1/award-amount-transactions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardAmountTransactionId": "(val)","awardNumber": "(val)","documentNumber": "(val)","transactionTypeCode": "(val)","noticeDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Amount Transactions [PUT /research-sys/api/v1/award-amount-transactions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardAmountTransactionId": "(val)","awardNumber": "(val)","documentNumber": "(val)","transactionTypeCode": "(val)","noticeDate": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"awardAmountTransactionId": "(val)","awardNumber": "(val)","documentNumber": "(val)","transactionTypeCode": "(val)","noticeDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Amount Transactions [POST /research-sys/api/v1/award-amount-transactions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardAmountTransactionId": "(val)","awardNumber": "(val)","documentNumber": "(val)","transactionTypeCode": "(val)","noticeDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardAmountTransactionId": "(val)","awardNumber": "(val)","documentNumber": "(val)","transactionTypeCode": "(val)","noticeDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Amount Transactions [POST /research-sys/api/v1/award-amount-transactions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardAmountTransactionId": "(val)","awardNumber": "(val)","documentNumber": "(val)","transactionTypeCode": "(val)","noticeDate": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"awardAmountTransactionId": "(val)","awardNumber": "(val)","documentNumber": "(val)","transactionTypeCode": "(val)","noticeDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardAmountTransactionId": "(val)","awardNumber": "(val)","documentNumber": "(val)","transactionTypeCode": "(val)","noticeDate": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"awardAmountTransactionId": "(val)","awardNumber": "(val)","documentNumber": "(val)","transactionTypeCode": "(val)","noticeDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Amount Transactions by Key [DELETE /research-sys/api/v1/award-amount-transactions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Amount Transactions [DELETE /research-sys/api/v1/award-amount-transactions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Amount Transactions with Matching [DELETE /research-sys/api/v1/award-amount-transactions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + awardAmountTransactionId
            + awardNumber
            + documentNumber
            + transactionTypeCode
            + noticeDate
            + comments

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
