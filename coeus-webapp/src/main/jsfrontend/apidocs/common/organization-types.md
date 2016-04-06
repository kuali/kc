## Organization Types [/research-sys/api/v1/organization-types/]

### Get Organization Types by Key [GET /research-sys/api/v1/organization-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Organization Types [GET /research-sys/api/v1/organization-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"},
              {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Organization Types with Filtering [GET /research-sys/api/v1/organization-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + organizationId
            + organizationTypeCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"},
              {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Organization Types [GET /research-sys/api/v1/organization-types/]
	 
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
		
### Get Blueprint API specification for Organization Types [GET /research-sys/api/v1/organization-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Organization Types.md"
            transfer-encoding:chunked


### Update Organization Types [PUT /research-sys/api/v1/organization-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Organization Types [PUT /research-sys/api/v1/organization-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"},
              {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Organization Types [POST /research-sys/api/v1/organization-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Organization Types [POST /research-sys/api/v1/organization-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"},
              {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"},
              {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Organization Types by Key [DELETE /research-sys/api/v1/organization-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Organization Types [DELETE /research-sys/api/v1/organization-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Organization Types with Matching [DELETE /research-sys/api/v1/organization-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + organizationId
            + organizationTypeCode


+ Response 204
