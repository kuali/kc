## Award Transaction Types [/award/api/v1/award-transaction-types/]

### Get Award Transaction Types by Key [GET /award/api/v1/award-transaction-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardTransactionTypeCode": "(val)","description": "(val)","showInActionSummary": "(val)","_primaryKey": "(val)"}

### Get All Award Transaction Types [GET /award/api/v1/award-transaction-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardTransactionTypeCode": "(val)","description": "(val)","showInActionSummary": "(val)","_primaryKey": "(val)"},
              {"awardTransactionTypeCode": "(val)","description": "(val)","showInActionSummary": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Transaction Types with Filtering [GET /award/api/v1/award-transaction-types/]
    
+ Parameters

    + awardTransactionTypeCode (optional) - Award Transaction Type Code. Maximum length is 22.
    + description (optional) - Description. Maximum length is 200.
    + showInActionSummary (optional) - Show In Action Summary. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardTransactionTypeCode": "(val)","description": "(val)","showInActionSummary": "(val)","_primaryKey": "(val)"},
              {"awardTransactionTypeCode": "(val)","description": "(val)","showInActionSummary": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Transaction Types [GET /award/api/v1/award-transaction-types/]
	                                          
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
    
            {"columns":["awardTransactionTypeCode","description","showInActionSummary"],"primaryKey":"awardTransactionTypeCode"}
		
### Get Blueprint API specification for Award Transaction Types [GET /award/api/v1/award-transaction-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Transaction Types.md"
            transfer-encoding:chunked
### Update Award Transaction Types [PUT /award/api/v1/award-transaction-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardTransactionTypeCode": "(val)","description": "(val)","showInActionSummary": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Transaction Types [PUT /award/api/v1/award-transaction-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardTransactionTypeCode": "(val)","description": "(val)","showInActionSummary": "(val)","_primaryKey": "(val)"},
              {"awardTransactionTypeCode": "(val)","description": "(val)","showInActionSummary": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Award Transaction Types [POST /award/api/v1/award-transaction-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardTransactionTypeCode": "(val)","description": "(val)","showInActionSummary": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardTransactionTypeCode": "(val)","description": "(val)","showInActionSummary": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Transaction Types [POST /award/api/v1/award-transaction-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardTransactionTypeCode": "(val)","description": "(val)","showInActionSummary": "(val)","_primaryKey": "(val)"},
              {"awardTransactionTypeCode": "(val)","description": "(val)","showInActionSummary": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardTransactionTypeCode": "(val)","description": "(val)","showInActionSummary": "(val)","_primaryKey": "(val)"},
              {"awardTransactionTypeCode": "(val)","description": "(val)","showInActionSummary": "(val)","_primaryKey": "(val)"}
            ]
### Delete Award Transaction Types by Key [DELETE /award/api/v1/award-transaction-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Transaction Types [DELETE /award/api/v1/award-transaction-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Transaction Types with Matching [DELETE /award/api/v1/award-transaction-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + awardTransactionTypeCode (optional) - Award Transaction Type Code. Maximum length is 22.
    + description (optional) - Description. Maximum length is 200.
    + showInActionSummary (optional) - Show In Action Summary. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
