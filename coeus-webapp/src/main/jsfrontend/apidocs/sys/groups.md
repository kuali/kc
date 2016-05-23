## Groups [/research-sys/api/v1/groups/]

### Get Groups by Key [GET /research-sys/api/v1/groups/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}

### Get All Groups [GET /research-sys/api/v1/groups/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Groups with Filtering [GET /research-sys/api/v1/groups/]
    
+ Parameters

    + id (optional) - Group Id. Maximum length is 70.
    + kimTypeId (optional) - Group Type. Maximum length is 40.
    + name (optional) - Group Name. Maximum length is 80.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.
    + description (optional) - Group Description. Maximum length is 4000.
    + namespaceCode (optional) - Nmspc Cd. Maximum length is 40.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Groups [GET /research-sys/api/v1/groups/]
	                                          
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
    
            {"columns":["id","kimTypeId","name","active","description","namespaceCode"],"primaryKey":"id"}
		
### Get Blueprint API specification for Groups [GET /research-sys/api/v1/groups/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Groups.md"
            transfer-encoding:chunked
### Update Groups [PUT /research-sys/api/v1/groups/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Groups [PUT /research-sys/api/v1/groups/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Groups [POST /research-sys/api/v1/groups/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Groups [POST /research-sys/api/v1/groups/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            ]
### Delete Groups by Key [DELETE /research-sys/api/v1/groups/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Groups [DELETE /research-sys/api/v1/groups/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Groups with Matching [DELETE /research-sys/api/v1/groups/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Group Id. Maximum length is 70.
    + kimTypeId (optional) - Group Type. Maximum length is 40.
    + name (optional) - Group Name. Maximum length is 80.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.
    + description (optional) - Group Description. Maximum length is 4000.
    + namespaceCode (optional) - Nmspc Cd. Maximum length is 40.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
