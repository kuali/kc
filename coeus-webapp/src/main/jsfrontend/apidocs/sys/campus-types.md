## Campus Types [/research-sys/api/v1/campus-types/]

### Get Campus Types by Key [GET /research-sys/api/v1/campus-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Campus Types [GET /research-sys/api/v1/campus-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Campus Types with Filtering [GET /research-sys/api/v1/campus-types/]
    
+ Parameters

    + code (optional) - campusTypeCode description... Maximum length is 1.
    + name (optional) - campusTypeName description... Maximum length is 40.
    + active (optional) - Active.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Campus Types [GET /research-sys/api/v1/campus-types/]
	                                          
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
    
            {"columns":["code","name","active"],"primaryKey":"code"}
		
### Get Blueprint API specification for Campus Types [GET /research-sys/api/v1/campus-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Campus Types.md"
            transfer-encoding:chunked
### Update Campus Types [PUT /research-sys/api/v1/campus-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Campus Types [PUT /research-sys/api/v1/campus-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Campus Types [POST /research-sys/api/v1/campus-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Campus Types [POST /research-sys/api/v1/campus-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete Campus Types by Key [DELETE /research-sys/api/v1/campus-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Campus Types [DELETE /research-sys/api/v1/campus-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Campus Types with Matching [DELETE /research-sys/api/v1/campus-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + code (optional) - campusTypeCode description... Maximum length is 1.
    + name (optional) - campusTypeName description... Maximum length is 40.
    + active (optional) - Active.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
