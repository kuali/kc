## Award Accounts [/award/api/v1/award-accounts/]

### Get Award Accounts by Key [GET /award/api/v1/award-accounts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","accountNumber": "(val)","createdByAwardId": "(val)","status": "(val)","budgeted": "(val)","pending": "(val)","income": "(val)","expense": "(val)","available": "(val)","_primaryKey": "(val)"}

### Get All Award Accounts [GET /award/api/v1/award-accounts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","accountNumber": "(val)","createdByAwardId": "(val)","status": "(val)","budgeted": "(val)","pending": "(val)","income": "(val)","expense": "(val)","available": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","accountNumber": "(val)","createdByAwardId": "(val)","status": "(val)","budgeted": "(val)","pending": "(val)","income": "(val)","expense": "(val)","available": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Accounts with Filtering [GET /award/api/v1/award-accounts/]
    
+ Parameters

    + id (optional) - 
    + accountNumber (optional) - 
    + createdByAwardId (optional) - 
    + status (optional) - 
    + budgeted (optional) - 
    + pending (optional) - 
    + income (optional) - 
    + expense (optional) - 
    + available (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","accountNumber": "(val)","createdByAwardId": "(val)","status": "(val)","budgeted": "(val)","pending": "(val)","income": "(val)","expense": "(val)","available": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","accountNumber": "(val)","createdByAwardId": "(val)","status": "(val)","budgeted": "(val)","pending": "(val)","income": "(val)","expense": "(val)","available": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Accounts [GET /award/api/v1/award-accounts/]
	                                          
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
    
            {"columns":["id","accountNumber","createdByAwardId","status","budgeted","pending","income","expense","available"],"primaryKey":"id"}
		
### Get Blueprint API specification for Award Accounts [GET /award/api/v1/award-accounts/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Accounts.md"
            transfer-encoding:chunked


### Update Award Accounts [PUT /award/api/v1/award-accounts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","accountNumber": "(val)","createdByAwardId": "(val)","status": "(val)","budgeted": "(val)","pending": "(val)","income": "(val)","expense": "(val)","available": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Accounts [PUT /award/api/v1/award-accounts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","accountNumber": "(val)","createdByAwardId": "(val)","status": "(val)","budgeted": "(val)","pending": "(val)","income": "(val)","expense": "(val)","available": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","accountNumber": "(val)","createdByAwardId": "(val)","status": "(val)","budgeted": "(val)","pending": "(val)","income": "(val)","expense": "(val)","available": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Accounts [POST /award/api/v1/award-accounts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","accountNumber": "(val)","createdByAwardId": "(val)","status": "(val)","budgeted": "(val)","pending": "(val)","income": "(val)","expense": "(val)","available": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","accountNumber": "(val)","createdByAwardId": "(val)","status": "(val)","budgeted": "(val)","pending": "(val)","income": "(val)","expense": "(val)","available": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Accounts [POST /award/api/v1/award-accounts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","accountNumber": "(val)","createdByAwardId": "(val)","status": "(val)","budgeted": "(val)","pending": "(val)","income": "(val)","expense": "(val)","available": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","accountNumber": "(val)","createdByAwardId": "(val)","status": "(val)","budgeted": "(val)","pending": "(val)","income": "(val)","expense": "(val)","available": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","accountNumber": "(val)","createdByAwardId": "(val)","status": "(val)","budgeted": "(val)","pending": "(val)","income": "(val)","expense": "(val)","available": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","accountNumber": "(val)","createdByAwardId": "(val)","status": "(val)","budgeted": "(val)","pending": "(val)","income": "(val)","expense": "(val)","available": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Accounts by Key [DELETE /award/api/v1/award-accounts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Accounts [DELETE /award/api/v1/award-accounts/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Accounts with Matching [DELETE /award/api/v1/award-accounts/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + accountNumber (optional) - 
    + createdByAwardId (optional) - 
    + status (optional) - 
    + budgeted (optional) - 
    + pending (optional) - 
    + income (optional) - 
    + expense (optional) - 
    + available (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
