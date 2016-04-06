## Iacuc Organization Correspondents [/research-sys/api/v1/iacuc-organization-correspondents/]

### Get Iacuc Organization Correspondents by Key [GET /research-sys/api/v1/iacuc-organization-correspondents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"correspondentId": "(val)","organizationId": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Organization Correspondents [GET /research-sys/api/v1/iacuc-organization-correspondents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"correspondentId": "(val)","organizationId": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"correspondentId": "(val)","organizationId": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Organization Correspondents with Filtering [GET /research-sys/api/v1/iacuc-organization-correspondents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + correspondentId
            + organizationId
            + correspondentTypeCode
            + personId
            + nonEmployeeFlag
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"correspondentId": "(val)","organizationId": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"correspondentId": "(val)","organizationId": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Organization Correspondents [GET /research-sys/api/v1/iacuc-organization-correspondents/]
	 
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
		
### Get Blueprint API specification for Iacuc Organization Correspondents [GET /research-sys/api/v1/iacuc-organization-correspondents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Organization Correspondents.md"
            transfer-encoding:chunked


### Update Iacuc Organization Correspondents [PUT /research-sys/api/v1/iacuc-organization-correspondents/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"correspondentId": "(val)","organizationId": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Organization Correspondents [PUT /research-sys/api/v1/iacuc-organization-correspondents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"correspondentId": "(val)","organizationId": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"correspondentId": "(val)","organizationId": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Organization Correspondents [POST /research-sys/api/v1/iacuc-organization-correspondents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"correspondentId": "(val)","organizationId": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"correspondentId": "(val)","organizationId": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Organization Correspondents [POST /research-sys/api/v1/iacuc-organization-correspondents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"correspondentId": "(val)","organizationId": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"correspondentId": "(val)","organizationId": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"correspondentId": "(val)","organizationId": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"correspondentId": "(val)","organizationId": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Organization Correspondents by Key [DELETE /research-sys/api/v1/iacuc-organization-correspondents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Organization Correspondents [DELETE /research-sys/api/v1/iacuc-organization-correspondents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Organization Correspondents with Matching [DELETE /research-sys/api/v1/iacuc-organization-correspondents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + correspondentId
            + organizationId
            + correspondentTypeCode
            + personId
            + nonEmployeeFlag
            + description


+ Response 204
