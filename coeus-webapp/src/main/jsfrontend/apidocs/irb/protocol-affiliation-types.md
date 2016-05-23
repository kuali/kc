## Protocol Affiliation Types [/irb/api/v1/protocol-affiliation-types/]

### Get Protocol Affiliation Types by Key [GET /irb/api/v1/protocol-affiliation-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Protocol Affiliation Types [GET /irb/api/v1/protocol-affiliation-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Affiliation Types with Filtering [GET /irb/api/v1/protocol-affiliation-types/]
    
+ Parameters

    + affiliationTypeCode (optional) - Affiliation Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + active (optional) - Active. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Affiliation Types [GET /irb/api/v1/protocol-affiliation-types/]
	                                          
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
    
            {"columns":["affiliationTypeCode","description","active"],"primaryKey":"affiliationTypeCode"}
		
### Get Blueprint API specification for Protocol Affiliation Types [GET /irb/api/v1/protocol-affiliation-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Affiliation Types.md"
            transfer-encoding:chunked
### Update Protocol Affiliation Types [PUT /irb/api/v1/protocol-affiliation-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Affiliation Types [PUT /irb/api/v1/protocol-affiliation-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Protocol Affiliation Types [POST /irb/api/v1/protocol-affiliation-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Affiliation Types [POST /irb/api/v1/protocol-affiliation-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete Protocol Affiliation Types by Key [DELETE /irb/api/v1/protocol-affiliation-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Affiliation Types [DELETE /irb/api/v1/protocol-affiliation-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Affiliation Types with Matching [DELETE /irb/api/v1/protocol-affiliation-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + affiliationTypeCode (optional) - Affiliation Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + active (optional) - Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
