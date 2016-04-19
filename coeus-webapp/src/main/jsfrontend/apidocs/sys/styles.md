## Styles [/research-sys/api/v1/styles/]

### Get Styles by Key [GET /research-sys/api/v1/styles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","name": "(val)","xmlContent": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Styles [GET /research-sys/api/v1/styles/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","xmlContent": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","xmlContent": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Styles with Filtering [GET /research-sys/api/v1/styles/]
    
+ Parameters

    + id (optional) - Id. Maximum length is 30.
    + name (optional) - Style Name. Maximum length is 2000.
    + xmlContent (optional) - XML Content. Maximum length is 250.
    + active (optional) - active. Maximum length is 50.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","xmlContent": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","xmlContent": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Styles [GET /research-sys/api/v1/styles/]
	                                          
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
    
            {"columns":["id","name","xmlContent","active"],"primaryKey":"id"}
		
### Get Blueprint API specification for Styles [GET /research-sys/api/v1/styles/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Styles.md"
            transfer-encoding:chunked


### Update Styles [PUT /research-sys/api/v1/styles/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","xmlContent": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Styles [PUT /research-sys/api/v1/styles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","xmlContent": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","xmlContent": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Styles [POST /research-sys/api/v1/styles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","xmlContent": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","name": "(val)","xmlContent": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Styles [POST /research-sys/api/v1/styles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","xmlContent": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","xmlContent": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","name": "(val)","xmlContent": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","xmlContent": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Styles by Key [DELETE /research-sys/api/v1/styles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Styles [DELETE /research-sys/api/v1/styles/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Styles with Matching [DELETE /research-sys/api/v1/styles/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Id. Maximum length is 30.
    + name (optional) - Style Name. Maximum length is 2000.
    + xmlContent (optional) - XML Content. Maximum length is 250.
    + active (optional) - active. Maximum length is 50.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
