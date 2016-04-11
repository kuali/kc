## Role Responsibilities [/research-sys/api/v1/role-responsibilities/]

### Get Role Responsibilities by Key [GET /research-sys/api/v1/role-responsibilities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Role Responsibilities [GET /research-sys/api/v1/role-responsibilities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Role Responsibilities with Filtering [GET /research-sys/api/v1/role-responsibilities/]
    
+ Parameters

        + roleResponsibilityId
            + roleId
            + responsibilityId
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
              {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Role Responsibilities [GET /research-sys/api/v1/role-responsibilities/]
	                                          
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
    
            {"columns":["roleResponsibilityId","roleId","responsibilityId","active"],"primaryKey":"roleResponsibilityId"}
		
### Get Blueprint API specification for Role Responsibilities [GET /research-sys/api/v1/role-responsibilities/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Role Responsibilities.md"
            transfer-encoding:chunked


### Update Role Responsibilities [PUT /research-sys/api/v1/role-responsibilities/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Role Responsibilities [PUT /research-sys/api/v1/role-responsibilities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Role Responsibilities [POST /research-sys/api/v1/role-responsibilities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Role Responsibilities [POST /research-sys/api/v1/role-responsibilities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"roleResponsibilityId": "(val)","roleId": "(val)","responsibilityId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Role Responsibilities by Key [DELETE /research-sys/api/v1/role-responsibilities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Role Responsibilities [DELETE /research-sys/api/v1/role-responsibilities/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Role Responsibilities with Matching [DELETE /research-sys/api/v1/role-responsibilities/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + roleResponsibilityId
            + roleId
            + responsibilityId
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
