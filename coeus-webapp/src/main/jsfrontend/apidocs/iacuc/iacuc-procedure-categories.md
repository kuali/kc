## Iacuc Procedure Categories [/iacuc/api/v1/iacuc-procedure-categories/]

### Get Iacuc Procedure Categories by Key [GET /iacuc/api/v1/iacuc-procedure-categories/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"procedureCategoryCode": "(val)","procedureCategory": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Procedure Categories [GET /iacuc/api/v1/iacuc-procedure-categories/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"procedureCategoryCode": "(val)","procedureCategory": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"procedureCategoryCode": "(val)","procedureCategory": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Procedure Categories with Filtering [GET /iacuc/api/v1/iacuc-procedure-categories/]
    
+ Parameters

    + procedureCategoryCode (optional) - Procedure Category Code. Maximum length is 3.
    + procedureCategory (optional) - Procedure Category. Maximum length is 200.
    + active (optional) - Active. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"procedureCategoryCode": "(val)","procedureCategory": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"procedureCategoryCode": "(val)","procedureCategory": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Procedure Categories [GET /iacuc/api/v1/iacuc-procedure-categories/]
	                                          
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
    
            {"columns":["procedureCategoryCode","procedureCategory","active"],"primaryKey":"procedureCategoryCode"}
		
### Get Blueprint API specification for Iacuc Procedure Categories [GET /iacuc/api/v1/iacuc-procedure-categories/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Procedure Categories.md"
            transfer-encoding:chunked


### Update Iacuc Procedure Categories [PUT /iacuc/api/v1/iacuc-procedure-categories/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"procedureCategoryCode": "(val)","procedureCategory": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Procedure Categories [PUT /iacuc/api/v1/iacuc-procedure-categories/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"procedureCategoryCode": "(val)","procedureCategory": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"procedureCategoryCode": "(val)","procedureCategory": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Procedure Categories [POST /iacuc/api/v1/iacuc-procedure-categories/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"procedureCategoryCode": "(val)","procedureCategory": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"procedureCategoryCode": "(val)","procedureCategory": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Procedure Categories [POST /iacuc/api/v1/iacuc-procedure-categories/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"procedureCategoryCode": "(val)","procedureCategory": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"procedureCategoryCode": "(val)","procedureCategory": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"procedureCategoryCode": "(val)","procedureCategory": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"procedureCategoryCode": "(val)","procedureCategory": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Procedure Categories by Key [DELETE /iacuc/api/v1/iacuc-procedure-categories/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Procedure Categories [DELETE /iacuc/api/v1/iacuc-procedure-categories/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Procedure Categories with Matching [DELETE /iacuc/api/v1/iacuc-procedure-categories/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + procedureCategoryCode (optional) - Procedure Category Code. Maximum length is 3.
    + procedureCategory (optional) - Procedure Category. Maximum length is 200.
    + active (optional) - Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
