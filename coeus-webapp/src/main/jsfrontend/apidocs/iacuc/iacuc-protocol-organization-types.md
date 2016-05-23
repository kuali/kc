## Iacuc Protocol Organization Types [/iacuc/api/v1/iacuc-protocol-organization-types/]

### Get Iacuc Protocol Organization Types by Key [GET /iacuc/api/v1/iacuc-protocol-organization-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolOrganizationTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Organization Types [GET /iacuc/api/v1/iacuc-protocol-organization-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolOrganizationTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolOrganizationTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Organization Types with Filtering [GET /iacuc/api/v1/iacuc-protocol-organization-types/]
    
+ Parameters

    + protocolOrganizationTypeCode (optional) - Protocol Organization Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolOrganizationTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolOrganizationTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Organization Types [GET /iacuc/api/v1/iacuc-protocol-organization-types/]
	                                          
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
    
            {"columns":["protocolOrganizationTypeCode","description"],"primaryKey":"protocolOrganizationTypeCode"}
		
### Get Blueprint API specification for Iacuc Protocol Organization Types [GET /iacuc/api/v1/iacuc-protocol-organization-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Organization Types.md"
            transfer-encoding:chunked
### Update Iacuc Protocol Organization Types [PUT /iacuc/api/v1/iacuc-protocol-organization-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolOrganizationTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Organization Types [PUT /iacuc/api/v1/iacuc-protocol-organization-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolOrganizationTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolOrganizationTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Iacuc Protocol Organization Types [POST /iacuc/api/v1/iacuc-protocol-organization-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolOrganizationTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolOrganizationTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Organization Types [POST /iacuc/api/v1/iacuc-protocol-organization-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolOrganizationTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolOrganizationTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolOrganizationTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolOrganizationTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Iacuc Protocol Organization Types by Key [DELETE /iacuc/api/v1/iacuc-protocol-organization-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Organization Types [DELETE /iacuc/api/v1/iacuc-protocol-organization-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Organization Types with Matching [DELETE /iacuc/api/v1/iacuc-protocol-organization-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolOrganizationTypeCode (optional) - Protocol Organization Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
