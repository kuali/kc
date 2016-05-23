## Award Amount Transactions [/award/api/v1/award-amount-transactions/]

### Get Award Amount Transactions by Key [GET /award/api/v1/award-amount-transactions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardAmountTransactionId": "(val)","awardNumber": "(val)","documentNumber": "(val)","transactionTypeCode": "(val)","noticeDate": "(val)","comments": "(val)","_primaryKey": "(val)"}

### Get All Award Amount Transactions [GET /award/api/v1/award-amount-transactions/]
	 
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

### Get All Award Amount Transactions with Filtering [GET /award/api/v1/award-amount-transactions/]
    
+ Parameters

    + awardAmountTransactionId (optional) - Award Amount Transaction Id. Maximum length is 22.
    + awardNumber (optional) - Award ID. Maximum length is 12.
    + documentNumber (optional) - Transaction Id. Maximum length is 10.
    + transactionTypeCode (optional) - Transaction Type Code. Maximum length is 22.
    + noticeDate (optional) - Notice Date. Maximum length is 10.
    + comments (optional) - Comments. Maximum length is 2000.

            
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
			
### Get Schema for Award Amount Transactions [GET /award/api/v1/award-amount-transactions/]
	                                          
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
		
### Get Blueprint API specification for Award Amount Transactions [GET /award/api/v1/award-amount-transactions/]
	 
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
### Update Award Amount Transactions [PUT /award/api/v1/award-amount-transactions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardAmountTransactionId": "(val)","awardNumber": "(val)","documentNumber": "(val)","transactionTypeCode": "(val)","noticeDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Amount Transactions [PUT /award/api/v1/award-amount-transactions/]

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
### Insert Award Amount Transactions [POST /award/api/v1/award-amount-transactions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardAmountTransactionId": "(val)","awardNumber": "(val)","documentNumber": "(val)","transactionTypeCode": "(val)","noticeDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardAmountTransactionId": "(val)","awardNumber": "(val)","documentNumber": "(val)","transactionTypeCode": "(val)","noticeDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Amount Transactions [POST /award/api/v1/award-amount-transactions/]

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
### Delete Award Amount Transactions by Key [DELETE /award/api/v1/award-amount-transactions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Amount Transactions [DELETE /award/api/v1/award-amount-transactions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Amount Transactions with Matching [DELETE /award/api/v1/award-amount-transactions/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + awardAmountTransactionId (optional) - Award Amount Transaction Id. Maximum length is 22.
    + awardNumber (optional) - Award ID. Maximum length is 12.
    + documentNumber (optional) - Transaction Id. Maximum length is 10.
    + transactionTypeCode (optional) - Transaction Type Code. Maximum length is 22.
    + noticeDate (optional) - Notice Date. Maximum length is 10.
    + comments (optional) - Comments. Maximum length is 2000.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
