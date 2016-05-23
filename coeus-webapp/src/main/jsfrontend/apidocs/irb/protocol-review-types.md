## Protocol Review Types [/irb/api/v1/protocol-review-types/]

### Get Protocol Review Types by Key [GET /irb/api/v1/protocol-review-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}

### Get All Protocol Review Types [GET /irb/api/v1/protocol-review-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"},
              {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Review Types with Filtering [GET /irb/api/v1/protocol-review-types/]
    
+ Parameters

    + reviewTypeCode (optional) - Protocol Review Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + globalFlag (optional) - Global Flag. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"},
              {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Review Types [GET /irb/api/v1/protocol-review-types/]
	                                          
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
    
            {"columns":["reviewTypeCode","description","globalFlag"],"primaryKey":"reviewTypeCode"}
		
### Get Blueprint API specification for Protocol Review Types [GET /irb/api/v1/protocol-review-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Review Types.md"
            transfer-encoding:chunked
### Update Protocol Review Types [PUT /irb/api/v1/protocol-review-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Review Types [PUT /irb/api/v1/protocol-review-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"},
              {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Protocol Review Types [POST /irb/api/v1/protocol-review-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Review Types [POST /irb/api/v1/protocol-review-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"},
              {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"},
              {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
            ]
### Delete Protocol Review Types by Key [DELETE /irb/api/v1/protocol-review-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Review Types [DELETE /irb/api/v1/protocol-review-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Review Types with Matching [DELETE /irb/api/v1/protocol-review-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + reviewTypeCode (optional) - Protocol Review Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + globalFlag (optional) - Global Flag. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
