## Award Transaction Types [/research-sys/api/v1/award-transaction-types/]

### Get Award Transaction Types by Key [GET /research-sys/api/v1/award-transaction-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardTransactionTypeCode": "(val)","description": "(val)","showInActionSummary": "(val)","_primaryKey": "(val)"}

### Get All Award Transaction Types [GET /research-sys/api/v1/award-transaction-types/]
	 
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

### Get All Award Transaction Types with Filtering [GET /research-sys/api/v1/award-transaction-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + awardTransactionTypeCode
            + description
            + showInActionSummary
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardTransactionTypeCode": "(val)","description": "(val)","showInActionSummary": "(val)","_primaryKey": "(val)"},
              {"awardTransactionTypeCode": "(val)","description": "(val)","showInActionSummary": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Transaction Types [GET /research-sys/api/v1/award-transaction-types/]
	 
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
		
### Get Blueprint API specification for Award Transaction Types [GET /research-sys/api/v1/award-transaction-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Transaction Types.md"
            transfer-encoding:chunked


### Update Award Transaction Types [PUT /research-sys/api/v1/award-transaction-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardTransactionTypeCode": "(val)","description": "(val)","showInActionSummary": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Transaction Types [PUT /research-sys/api/v1/award-transaction-types/]

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

### Insert Award Transaction Types [POST /research-sys/api/v1/award-transaction-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardTransactionTypeCode": "(val)","description": "(val)","showInActionSummary": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardTransactionTypeCode": "(val)","description": "(val)","showInActionSummary": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Transaction Types [POST /research-sys/api/v1/award-transaction-types/]

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
            
### Delete Award Transaction Types by Key [DELETE /research-sys/api/v1/award-transaction-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Transaction Types [DELETE /research-sys/api/v1/award-transaction-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Award Transaction Types with Matching [DELETE /research-sys/api/v1/award-transaction-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + awardTransactionTypeCode
            + description
            + showInActionSummary


+ Response 204
