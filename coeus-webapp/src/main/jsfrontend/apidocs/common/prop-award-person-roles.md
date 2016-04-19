## Prop Award Person Roles [/research-common/api/v1/prop-award-person-roles/]

### Get Prop Award Person Roles by Key [GET /research-common/api/v1/prop-award-person-roles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","code": "(val)","sponsorHierarchyName": "(val)","description": "(val)","certificationRequired": "(val)","readOnly": "(val)","unitDetailsRequired": "(val)","_primaryKey": "(val)"}

### Get All Prop Award Person Roles [GET /research-common/api/v1/prop-award-person-roles/]
	 
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

### Get All Prop Award Person Roles with Filtering [GET /research-common/api/v1/prop-award-person-roles/]
    
+ Parameters

    + id (optional) - Proposal Person Role Id. Maximum length is 12.
    + code (optional) - Proposal Person Role Id. Maximum length is 12.
    + sponsorHierarchyName (optional) - Sponsor Hierarchy Name. Maximum length is 50.
    + description (optional) - Description. Maximum length is 25.
    + certificationRequired (optional) - Certification Required. Maximum length is 1.
    + readOnly (optional) - Read Only. Maximum length is 1.
    + unitDetailsRequired (optional) - Unit Details Required. Maximum length is 1.

            
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
			
### Get Schema for Prop Award Person Roles [GET /research-common/api/v1/prop-award-person-roles/]
	                                          
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
    
            {"columns":["id","code","sponsorHierarchyName","description","certificationRequired","readOnly","unitDetailsRequired"],"primaryKey":"id"}
		
### Get Blueprint API specification for Prop Award Person Roles [GET /research-common/api/v1/prop-award-person-roles/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Prop Award Person Roles.md"
            transfer-encoding:chunked


### Update Prop Award Person Roles [PUT /research-common/api/v1/prop-award-person-roles/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","code": "(val)","sponsorHierarchyName": "(val)","description": "(val)","certificationRequired": "(val)","readOnly": "(val)","unitDetailsRequired": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Prop Award Person Roles [PUT /research-common/api/v1/prop-award-person-roles/]

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

### Insert Prop Award Person Roles [POST /research-common/api/v1/prop-award-person-roles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","code": "(val)","sponsorHierarchyName": "(val)","description": "(val)","certificationRequired": "(val)","readOnly": "(val)","unitDetailsRequired": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","code": "(val)","sponsorHierarchyName": "(val)","description": "(val)","certificationRequired": "(val)","readOnly": "(val)","unitDetailsRequired": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Prop Award Person Roles [POST /research-common/api/v1/prop-award-person-roles/]

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
            
### Delete Prop Award Person Roles by Key [DELETE /research-common/api/v1/prop-award-person-roles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Prop Award Person Roles [DELETE /research-common/api/v1/prop-award-person-roles/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Prop Award Person Roles with Matching [DELETE /research-common/api/v1/prop-award-person-roles/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Proposal Person Role Id. Maximum length is 12.
    + code (optional) - Proposal Person Role Id. Maximum length is 12.
    + sponsorHierarchyName (optional) - Sponsor Hierarchy Name. Maximum length is 50.
    + description (optional) - Description. Maximum length is 25.
    + certificationRequired (optional) - Certification Required. Maximum length is 1.
    + readOnly (optional) - Read Only. Maximum length is 1.
    + unitDetailsRequired (optional) - Unit Details Required. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
