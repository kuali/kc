## Prop Award Person Roles [/research-sys/api/v1/prop-award-person-roles/]

### Get Prop Award Person Roles by Key [GET /research-sys/api/v1/prop-award-person-roles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","code": "(val)","sponsorHierarchyName": "(val)","description": "(val)","certificationRequired": "(val)","readOnly": "(val)","unitDetailsRequired": "(val)","_primaryKey": "(val)"}

### Get All Prop Award Person Roles [GET /research-sys/api/v1/prop-award-person-roles/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","code": "(val)","sponsorHierarchyName": "(val)","description": "(val)","certificationRequired": "(val)","readOnly": "(val)","unitDetailsRequired": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","code": "(val)","sponsorHierarchyName": "(val)","description": "(val)","certificationRequired": "(val)","readOnly": "(val)","unitDetailsRequired": "(val)","_primaryKey": "(val)"}
            ]

### Get All Prop Award Person Roles with Filtering [GET /research-sys/api/v1/prop-award-person-roles/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + code
            + sponsorHierarchyName
            + description
            + certificationRequired
            + readOnly
            + unitDetailsRequired
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","code": "(val)","sponsorHierarchyName": "(val)","description": "(val)","certificationRequired": "(val)","readOnly": "(val)","unitDetailsRequired": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","code": "(val)","sponsorHierarchyName": "(val)","description": "(val)","certificationRequired": "(val)","readOnly": "(val)","unitDetailsRequired": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Prop Award Person Roles [GET /research-sys/api/v1/prop-award-person-roles/]
	 
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
		
### Get Blueprint API specification for Prop Award Person Roles [GET /research-sys/api/v1/prop-award-person-roles/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Prop Award Person Roles.md"
            transfer-encoding:chunked


### Update Prop Award Person Roles [PUT /research-sys/api/v1/prop-award-person-roles/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","code": "(val)","sponsorHierarchyName": "(val)","description": "(val)","certificationRequired": "(val)","readOnly": "(val)","unitDetailsRequired": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Prop Award Person Roles [PUT /research-sys/api/v1/prop-award-person-roles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","code": "(val)","sponsorHierarchyName": "(val)","description": "(val)","certificationRequired": "(val)","readOnly": "(val)","unitDetailsRequired": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","code": "(val)","sponsorHierarchyName": "(val)","description": "(val)","certificationRequired": "(val)","readOnly": "(val)","unitDetailsRequired": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Prop Award Person Roles [POST /research-sys/api/v1/prop-award-person-roles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","code": "(val)","sponsorHierarchyName": "(val)","description": "(val)","certificationRequired": "(val)","readOnly": "(val)","unitDetailsRequired": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","code": "(val)","sponsorHierarchyName": "(val)","description": "(val)","certificationRequired": "(val)","readOnly": "(val)","unitDetailsRequired": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Prop Award Person Roles [POST /research-sys/api/v1/prop-award-person-roles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","code": "(val)","sponsorHierarchyName": "(val)","description": "(val)","certificationRequired": "(val)","readOnly": "(val)","unitDetailsRequired": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","code": "(val)","sponsorHierarchyName": "(val)","description": "(val)","certificationRequired": "(val)","readOnly": "(val)","unitDetailsRequired": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","code": "(val)","sponsorHierarchyName": "(val)","description": "(val)","certificationRequired": "(val)","readOnly": "(val)","unitDetailsRequired": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","code": "(val)","sponsorHierarchyName": "(val)","description": "(val)","certificationRequired": "(val)","readOnly": "(val)","unitDetailsRequired": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Prop Award Person Roles by Key [DELETE /research-sys/api/v1/prop-award-person-roles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Prop Award Person Roles [DELETE /research-sys/api/v1/prop-award-person-roles/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Prop Award Person Roles with Matching [DELETE /research-sys/api/v1/prop-award-person-roles/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + code
            + sponsorHierarchyName
            + description
            + certificationRequired
            + readOnly
            + unitDetailsRequired


+ Response 204
