## Iacuc Protocol Person Role Mappings [/research-sys/api/v1/iacuc-protocol-person-role-mappings/]

### Get Iacuc Protocol Person Role Mappings by Key [GET /research-sys/api/v1/iacuc-protocol-person-role-mappings/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"roleMappingId": "(val)","sourceRoleId": "(val)","targetRoleId": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Person Role Mappings [GET /research-sys/api/v1/iacuc-protocol-person-role-mappings/]
	 
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

### Get All Iacuc Protocol Person Role Mappings with Filtering [GET /research-sys/api/v1/iacuc-protocol-person-role-mappings/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + roleMappingId
            + sourceRoleId
            + targetRoleId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"roleMappingId": "(val)","sourceRoleId": "(val)","targetRoleId": "(val)","_primaryKey": "(val)"},
              {"roleMappingId": "(val)","sourceRoleId": "(val)","targetRoleId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Person Role Mappings [GET /research-sys/api/v1/iacuc-protocol-person-role-mappings/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters

            + _schema (required) - will instruct the endpoint to return a schema data structure for the resource

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            ${sampleSchema}
		
### Get Blueprint API specification for Iacuc Protocol Person Role Mappings [GET /research-sys/api/v1/iacuc-protocol-person-role-mappings/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Person Role Mappings.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Person Role Mappings [PUT /research-sys/api/v1/iacuc-protocol-person-role-mappings/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"roleMappingId": "(val)","sourceRoleId": "(val)","targetRoleId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Person Role Mappings [PUT /research-sys/api/v1/iacuc-protocol-person-role-mappings/]

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

### Insert Iacuc Protocol Person Role Mappings [POST /research-sys/api/v1/iacuc-protocol-person-role-mappings/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"roleMappingId": "(val)","sourceRoleId": "(val)","targetRoleId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"roleMappingId": "(val)","sourceRoleId": "(val)","targetRoleId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Person Role Mappings [POST /research-sys/api/v1/iacuc-protocol-person-role-mappings/]

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
            
### Delete Iacuc Protocol Person Role Mappings by Key [DELETE /research-sys/api/v1/iacuc-protocol-person-role-mappings/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Person Role Mappings [DELETE /research-sys/api/v1/iacuc-protocol-person-role-mappings/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Protocol Person Role Mappings with Matching [DELETE /research-sys/api/v1/iacuc-protocol-person-role-mappings/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + roleMappingId
            + sourceRoleId
            + targetRoleId


+ Response 204
