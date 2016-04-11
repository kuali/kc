## Countries [/research-sys/api/v1/countries/]

### Get Countries by Key [GET /research-sys/api/v1/countries/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","alternateCode": "(val)","name": "(val)","restricted": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Countries [GET /research-sys/api/v1/countries/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","alternateCode": "(val)","name": "(val)","restricted": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","alternateCode": "(val)","name": "(val)","restricted": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Countries with Filtering [GET /research-sys/api/v1/countries/]
    
+ Parameters

        + code
            + alternateCode
            + name
            + restricted
            + active

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","alternateCode": "(val)","name": "(val)","restricted": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","alternateCode": "(val)","name": "(val)","restricted": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Countries [GET /research-sys/api/v1/countries/]
	                                          
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
    
            {"columns":["code","alternateCode","name","restricted","active"],"primaryKey":"code"}
		
### Get Blueprint API specification for Countries [GET /research-sys/api/v1/countries/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Countries.md"
            transfer-encoding:chunked


### Update Countries [PUT /research-sys/api/v1/countries/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","alternateCode": "(val)","name": "(val)","restricted": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Countries [PUT /research-sys/api/v1/countries/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","alternateCode": "(val)","name": "(val)","restricted": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","alternateCode": "(val)","name": "(val)","restricted": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Countries [POST /research-sys/api/v1/countries/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","alternateCode": "(val)","name": "(val)","restricted": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","alternateCode": "(val)","name": "(val)","restricted": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Countries [POST /research-sys/api/v1/countries/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","alternateCode": "(val)","name": "(val)","restricted": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","alternateCode": "(val)","name": "(val)","restricted": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"code": "(val)","alternateCode": "(val)","name": "(val)","restricted": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","alternateCode": "(val)","name": "(val)","restricted": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Countries by Key [DELETE /research-sys/api/v1/countries/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Countries [DELETE /research-sys/api/v1/countries/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Countries with Matching [DELETE /research-sys/api/v1/countries/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + code
            + alternateCode
            + name
            + restricted
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
