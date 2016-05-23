## Protocol Organization Types [/irb/api/v1/protocol-organization-types/]

### Get Protocol Organization Types by Key [GET /irb/api/v1/protocol-organization-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolOrganizationTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Protocol Organization Types [GET /irb/api/v1/protocol-organization-types/]
	 
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

### Get All Protocol Organization Types with Filtering [GET /irb/api/v1/protocol-organization-types/]
    
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
			
### Get Schema for Protocol Organization Types [GET /irb/api/v1/protocol-organization-types/]
	                                          
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
		
### Get Blueprint API specification for Protocol Organization Types [GET /irb/api/v1/protocol-organization-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Organization Types.md"
            transfer-encoding:chunked
### Update Protocol Organization Types [PUT /irb/api/v1/protocol-organization-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolOrganizationTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Organization Types [PUT /irb/api/v1/protocol-organization-types/]

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
### Insert Protocol Organization Types [POST /irb/api/v1/protocol-organization-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolOrganizationTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolOrganizationTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Organization Types [POST /irb/api/v1/protocol-organization-types/]

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
### Delete Protocol Organization Types by Key [DELETE /irb/api/v1/protocol-organization-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Organization Types [DELETE /irb/api/v1/protocol-organization-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Organization Types with Matching [DELETE /irb/api/v1/protocol-organization-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolOrganizationTypeCode (optional) - Protocol Organization Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
