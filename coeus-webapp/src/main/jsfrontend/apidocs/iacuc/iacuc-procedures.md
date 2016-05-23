## Iacuc Procedures [/iacuc/api/v1/iacuc-procedures/]

### Get Iacuc Procedures by Key [GET /iacuc/api/v1/iacuc-procedures/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"procedureCode": "(val)","procedureDescription": "(val)","procedureCategoryCode": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Procedures [GET /iacuc/api/v1/iacuc-procedures/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"procedureCode": "(val)","procedureDescription": "(val)","procedureCategoryCode": "(val)","_primaryKey": "(val)"},
              {"procedureCode": "(val)","procedureDescription": "(val)","procedureCategoryCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Procedures with Filtering [GET /iacuc/api/v1/iacuc-procedures/]
    
+ Parameters

    + procedureCode (optional) - Procedure Code. Maximum length is 3.
    + procedureDescription (optional) - Procedure Description. Maximum length is 2000.
    + procedureCategoryCode (optional) - Procedure Category Code. Maximum length is 3.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"procedureCode": "(val)","procedureDescription": "(val)","procedureCategoryCode": "(val)","_primaryKey": "(val)"},
              {"procedureCode": "(val)","procedureDescription": "(val)","procedureCategoryCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Procedures [GET /iacuc/api/v1/iacuc-procedures/]
	                                          
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
    
            {"columns":["procedureCode","procedureDescription","procedureCategoryCode"],"primaryKey":"procedureCode"}
		
### Get Blueprint API specification for Iacuc Procedures [GET /iacuc/api/v1/iacuc-procedures/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Procedures.md"
            transfer-encoding:chunked
### Update Iacuc Procedures [PUT /iacuc/api/v1/iacuc-procedures/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"procedureCode": "(val)","procedureDescription": "(val)","procedureCategoryCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Procedures [PUT /iacuc/api/v1/iacuc-procedures/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"procedureCode": "(val)","procedureDescription": "(val)","procedureCategoryCode": "(val)","_primaryKey": "(val)"},
              {"procedureCode": "(val)","procedureDescription": "(val)","procedureCategoryCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Iacuc Procedures [POST /iacuc/api/v1/iacuc-procedures/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"procedureCode": "(val)","procedureDescription": "(val)","procedureCategoryCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"procedureCode": "(val)","procedureDescription": "(val)","procedureCategoryCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Procedures [POST /iacuc/api/v1/iacuc-procedures/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"procedureCode": "(val)","procedureDescription": "(val)","procedureCategoryCode": "(val)","_primaryKey": "(val)"},
              {"procedureCode": "(val)","procedureDescription": "(val)","procedureCategoryCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"procedureCode": "(val)","procedureDescription": "(val)","procedureCategoryCode": "(val)","_primaryKey": "(val)"},
              {"procedureCode": "(val)","procedureDescription": "(val)","procedureCategoryCode": "(val)","_primaryKey": "(val)"}
            ]
### Delete Iacuc Procedures by Key [DELETE /iacuc/api/v1/iacuc-procedures/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Procedures [DELETE /iacuc/api/v1/iacuc-procedures/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Procedures with Matching [DELETE /iacuc/api/v1/iacuc-procedures/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + procedureCode (optional) - Procedure Code. Maximum length is 3.
    + procedureDescription (optional) - Procedure Description. Maximum length is 2000.
    + procedureCategoryCode (optional) - Procedure Category Code. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
