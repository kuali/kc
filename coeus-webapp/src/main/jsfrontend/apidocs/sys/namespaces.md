## Namespaces [/research-sys/api/v1/namespaces/]

### Get Namespaces by Key [GET /research-sys/api/v1/namespaces/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"applicationId": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Namespaces [GET /research-sys/api/v1/namespaces/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"applicationId": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"applicationId": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Namespaces with Filtering [GET /research-sys/api/v1/namespaces/]
    
+ Parameters

    + applicationId (optional) - The id of the application which owns this namespace. Maximum length is 20.
    + code (optional) - Code identifying the namespace. Maximum length is 20.
    + name (optional) - The name of the namespace. Maximum length is 40.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"applicationId": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"applicationId": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Namespaces [GET /research-sys/api/v1/namespaces/]
	                                          
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
    
            {"columns":["applicationId","code","name","active"],"primaryKey":"code"}
		
### Get Blueprint API specification for Namespaces [GET /research-sys/api/v1/namespaces/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Namespaces.md"
            transfer-encoding:chunked


### Update Namespaces [PUT /research-sys/api/v1/namespaces/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"applicationId": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Namespaces [PUT /research-sys/api/v1/namespaces/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"applicationId": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"applicationId": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Namespaces [POST /research-sys/api/v1/namespaces/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"applicationId": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"applicationId": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Namespaces [POST /research-sys/api/v1/namespaces/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"applicationId": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"applicationId": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"applicationId": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"applicationId": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Namespaces by Key [DELETE /research-sys/api/v1/namespaces/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Namespaces [DELETE /research-sys/api/v1/namespaces/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Namespaces with Matching [DELETE /research-sys/api/v1/namespaces/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + applicationId (optional) - The id of the application which owns this namespace. Maximum length is 20.
    + code (optional) - Code identifying the namespace. Maximum length is 20.
    + name (optional) - The name of the namespace. Maximum length is 40.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
