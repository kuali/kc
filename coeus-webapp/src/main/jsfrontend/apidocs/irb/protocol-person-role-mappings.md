## Protocol Person Role Mappings [/research-sys/api/v1/protocol-person-role-mappings/]

### Get Protocol Person Role Mappings by Key [GET /research-sys/api/v1/protocol-person-role-mappings/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"roleMappingId": "(val)","sourceRoleId": "(val)","targetRoleId": "(val)","_primaryKey": "(val)"}

### Get All Protocol Person Role Mappings [GET /research-sys/api/v1/protocol-person-role-mappings/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"roleMappingId": "(val)","sourceRoleId": "(val)","targetRoleId": "(val)","_primaryKey": "(val)"},
              {"roleMappingId": "(val)","sourceRoleId": "(val)","targetRoleId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Person Role Mappings with Filtering [GET /research-sys/api/v1/protocol-person-role-mappings/]
    
+ Parameters

        + roleMappingId
            + sourceRoleId
            + targetRoleId

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"roleMappingId": "(val)","sourceRoleId": "(val)","targetRoleId": "(val)","_primaryKey": "(val)"},
              {"roleMappingId": "(val)","sourceRoleId": "(val)","targetRoleId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Person Role Mappings [GET /research-sys/api/v1/protocol-person-role-mappings/]
	                                          
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
    
            {"columns":["roleMappingId","sourceRoleId","targetRoleId"],"primaryKey":"roleMappingId"}
		
### Get Blueprint API specification for Protocol Person Role Mappings [GET /research-sys/api/v1/protocol-person-role-mappings/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Person Role Mappings.md"
            transfer-encoding:chunked


### Update Protocol Person Role Mappings [PUT /research-sys/api/v1/protocol-person-role-mappings/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"roleMappingId": "(val)","sourceRoleId": "(val)","targetRoleId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Person Role Mappings [PUT /research-sys/api/v1/protocol-person-role-mappings/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"roleMappingId": "(val)","sourceRoleId": "(val)","targetRoleId": "(val)","_primaryKey": "(val)"},
              {"roleMappingId": "(val)","sourceRoleId": "(val)","targetRoleId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Protocol Person Role Mappings [POST /research-sys/api/v1/protocol-person-role-mappings/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"roleMappingId": "(val)","sourceRoleId": "(val)","targetRoleId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"roleMappingId": "(val)","sourceRoleId": "(val)","targetRoleId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Person Role Mappings [POST /research-sys/api/v1/protocol-person-role-mappings/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"roleMappingId": "(val)","sourceRoleId": "(val)","targetRoleId": "(val)","_primaryKey": "(val)"},
              {"roleMappingId": "(val)","sourceRoleId": "(val)","targetRoleId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"roleMappingId": "(val)","sourceRoleId": "(val)","targetRoleId": "(val)","_primaryKey": "(val)"},
              {"roleMappingId": "(val)","sourceRoleId": "(val)","targetRoleId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Protocol Person Role Mappings by Key [DELETE /research-sys/api/v1/protocol-person-role-mappings/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Person Role Mappings [DELETE /research-sys/api/v1/protocol-person-role-mappings/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Person Role Mappings with Matching [DELETE /research-sys/api/v1/protocol-person-role-mappings/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + roleMappingId
            + sourceRoleId
            + targetRoleId

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
