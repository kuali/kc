## Investigator Credit Types [/research-sys/api/v1/investigator-credit-types/]

### Get Investigator Credit Types by Key [GET /research-sys/api/v1/investigator-credit-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Investigator Credit Types [GET /research-sys/api/v1/investigator-credit-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Investigator Credit Types with Filtering [GET /research-sys/api/v1/investigator-credit-types/]
    
+ Parameters

        + code
            + addsToHundred
            + active
            + description

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Investigator Credit Types [GET /research-sys/api/v1/investigator-credit-types/]
	                                          
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
    
            {"columns":["code","addsToHundred","active","description"],"primaryKey":"code"}
		
### Get Blueprint API specification for Investigator Credit Types [GET /research-sys/api/v1/investigator-credit-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Investigator Credit Types.md"
            transfer-encoding:chunked


### Update Investigator Credit Types [PUT /research-sys/api/v1/investigator-credit-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Investigator Credit Types [PUT /research-sys/api/v1/investigator-credit-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Investigator Credit Types [POST /research-sys/api/v1/investigator-credit-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Investigator Credit Types [POST /research-sys/api/v1/investigator-credit-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","addsToHundred": "(val)","active": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Investigator Credit Types by Key [DELETE /research-sys/api/v1/investigator-credit-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Investigator Credit Types [DELETE /research-sys/api/v1/investigator-credit-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Investigator Credit Types with Matching [DELETE /research-sys/api/v1/investigator-credit-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + code
            + addsToHundred
            + active
            + description

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
