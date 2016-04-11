## Budget Changed Data [/research-sys/api/v1/budget-changed-data/]

### Get Budget Changed Data by Key [GET /research-sys/api/v1/budget-changed-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"}

### Get All Budget Changed Data [GET /research-sys/api/v1/budget-changed-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"},
              {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budget Changed Data with Filtering [GET /research-sys/api/v1/budget-changed-data/]
    
+ Parameters

        + changeNumber
            + columnName
            + proposalNumber
            + changedValue
            + comments
            + displayValue
            + oldDisplayValue

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"},
              {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Changed Data [GET /research-sys/api/v1/budget-changed-data/]
	                                          
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
    
            {"columns":["changeNumber","columnName","proposalNumber","changedValue","comments","displayValue","oldDisplayValue"],"primaryKey":"changeNumber:columnName:proposalNumber"}
		
### Get Blueprint API specification for Budget Changed Data [GET /research-sys/api/v1/budget-changed-data/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Changed Data.md"
            transfer-encoding:chunked


### Update Budget Changed Data [PUT /research-sys/api/v1/budget-changed-data/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Changed Data [PUT /research-sys/api/v1/budget-changed-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"},
              {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Budget Changed Data [POST /research-sys/api/v1/budget-changed-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Changed Data [POST /research-sys/api/v1/budget-changed-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"},
              {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"},
              {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Budget Changed Data by Key [DELETE /research-sys/api/v1/budget-changed-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Changed Data [DELETE /research-sys/api/v1/budget-changed-data/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Changed Data with Matching [DELETE /research-sys/api/v1/budget-changed-data/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + changeNumber
            + columnName
            + proposalNumber
            + changedValue
            + comments
            + displayValue
            + oldDisplayValue

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
