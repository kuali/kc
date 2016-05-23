## Iacuc Protocol Person Role Mappings [/iacuc/api/v1/iacuc-protocol-person-role-mappings/]

### Get Iacuc Protocol Person Role Mappings by Key [GET /iacuc/api/v1/iacuc-protocol-person-role-mappings/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"roleMappingId": "(val)","sourceRoleId": "(val)","targetRoleId": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Person Role Mappings [GET /iacuc/api/v1/iacuc-protocol-person-role-mappings/]
	 
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

### Get All Iacuc Protocol Person Role Mappings with Filtering [GET /iacuc/api/v1/iacuc-protocol-person-role-mappings/]
    
+ Parameters

    + roleMappingId (optional) - 
    + sourceRoleId (optional) - 
    + targetRoleId (optional) - 

            
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
			
### Get Schema for Iacuc Protocol Person Role Mappings [GET /iacuc/api/v1/iacuc-protocol-person-role-mappings/]
	                                          
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
		
### Get Blueprint API specification for Iacuc Protocol Person Role Mappings [GET /iacuc/api/v1/iacuc-protocol-person-role-mappings/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Person Role Mappings.md"
            transfer-encoding:chunked
### Update Iacuc Protocol Person Role Mappings [PUT /iacuc/api/v1/iacuc-protocol-person-role-mappings/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"roleMappingId": "(val)","sourceRoleId": "(val)","targetRoleId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Person Role Mappings [PUT /iacuc/api/v1/iacuc-protocol-person-role-mappings/]

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
### Insert Iacuc Protocol Person Role Mappings [POST /iacuc/api/v1/iacuc-protocol-person-role-mappings/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"roleMappingId": "(val)","sourceRoleId": "(val)","targetRoleId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"roleMappingId": "(val)","sourceRoleId": "(val)","targetRoleId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Person Role Mappings [POST /iacuc/api/v1/iacuc-protocol-person-role-mappings/]

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
### Delete Iacuc Protocol Person Role Mappings by Key [DELETE /iacuc/api/v1/iacuc-protocol-person-role-mappings/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Person Role Mappings [DELETE /iacuc/api/v1/iacuc-protocol-person-role-mappings/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Person Role Mappings with Matching [DELETE /iacuc/api/v1/iacuc-protocol-person-role-mappings/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + roleMappingId (optional) - 
    + sourceRoleId (optional) - 
    + targetRoleId (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
