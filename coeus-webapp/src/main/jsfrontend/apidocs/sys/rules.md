## Rules [/research-sys/api/v1/rules/]

### Get Rules by Key [GET /research-sys/api/v1/rules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","namespace": "(val)","description": "(val)","name": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Rules [GET /research-sys/api/v1/rules/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","namespace": "(val)","description": "(val)","name": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespace": "(val)","description": "(val)","name": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Rules with Filtering [GET /research-sys/api/v1/rules/]
    
+ Parameters

    + id (optional) - Rule Id.
    + namespace (optional) - Namespace.
    + description (optional) - Description.
    + name (optional) - Name.
    + typeId (optional) - Type Id.
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
              {"id": "(val)","namespace": "(val)","description": "(val)","name": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespace": "(val)","description": "(val)","name": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Rules [GET /research-sys/api/v1/rules/]
	                                          
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
    
            {"columns":["id","namespace","description","name","typeId","active"],"primaryKey":"id"}
		
### Get Blueprint API specification for Rules [GET /research-sys/api/v1/rules/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Rules.md"
            transfer-encoding:chunked
### Update Rules [PUT /research-sys/api/v1/rules/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","namespace": "(val)","description": "(val)","name": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Rules [PUT /research-sys/api/v1/rules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","namespace": "(val)","description": "(val)","name": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespace": "(val)","description": "(val)","name": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Rules [POST /research-sys/api/v1/rules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","namespace": "(val)","description": "(val)","name": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","namespace": "(val)","description": "(val)","name": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Rules [POST /research-sys/api/v1/rules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","namespace": "(val)","description": "(val)","name": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespace": "(val)","description": "(val)","name": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","namespace": "(val)","description": "(val)","name": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespace": "(val)","description": "(val)","name": "(val)","typeId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete Rules by Key [DELETE /research-sys/api/v1/rules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rules [DELETE /research-sys/api/v1/rules/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rules with Matching [DELETE /research-sys/api/v1/rules/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Rule Id.
    + namespace (optional) - Namespace.
    + description (optional) - Description.
    + name (optional) - Name.
    + typeId (optional) - Type Id.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
