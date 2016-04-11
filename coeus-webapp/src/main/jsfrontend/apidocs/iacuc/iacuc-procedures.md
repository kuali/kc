## Iacuc Procedures [/research-sys/api/v1/iacuc-procedures/]

### Get Iacuc Procedures by Key [GET /research-sys/api/v1/iacuc-procedures/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"procedureCode": "(val)","procedureDescription": "(val)","procedureCategoryCode": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Procedures [GET /research-sys/api/v1/iacuc-procedures/]
	 
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

### Get All Iacuc Procedures with Filtering [GET /research-sys/api/v1/iacuc-procedures/]
    
+ Parameters

        + procedureCode
            + procedureDescription
            + procedureCategoryCode

            
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
			
### Get Schema for Iacuc Procedures [GET /research-sys/api/v1/iacuc-procedures/]
	                                          
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
		
### Get Blueprint API specification for Iacuc Procedures [GET /research-sys/api/v1/iacuc-procedures/]
	 
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


### Update Iacuc Procedures [PUT /research-sys/api/v1/iacuc-procedures/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"procedureCode": "(val)","procedureDescription": "(val)","procedureCategoryCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Procedures [PUT /research-sys/api/v1/iacuc-procedures/]

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

### Insert Iacuc Procedures [POST /research-sys/api/v1/iacuc-procedures/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"procedureCode": "(val)","procedureDescription": "(val)","procedureCategoryCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"procedureCode": "(val)","procedureDescription": "(val)","procedureCategoryCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Procedures [POST /research-sys/api/v1/iacuc-procedures/]

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
            
### Delete Iacuc Procedures by Key [DELETE /research-sys/api/v1/iacuc-procedures/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Procedures [DELETE /research-sys/api/v1/iacuc-procedures/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Procedures with Matching [DELETE /research-sys/api/v1/iacuc-procedures/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + procedureCode
            + procedureDescription
            + procedureCategoryCode

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
