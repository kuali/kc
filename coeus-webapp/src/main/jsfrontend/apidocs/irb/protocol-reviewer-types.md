## Protocol Reviewer Types [/irb/api/v1/protocol-reviewer-types/]

### Get Protocol Reviewer Types by Key [GET /irb/api/v1/protocol-reviewer-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"reviewerTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Protocol Reviewer Types [GET /irb/api/v1/protocol-reviewer-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"reviewerTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"reviewerTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Reviewer Types with Filtering [GET /irb/api/v1/protocol-reviewer-types/]
    
+ Parameters

    + reviewerTypeCode (optional) - Reviewer Type. Maximum length is 3.
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
              {"reviewerTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"reviewerTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Reviewer Types [GET /irb/api/v1/protocol-reviewer-types/]
	                                          
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
    
            {"columns":["reviewerTypeCode","description"],"primaryKey":"reviewerTypeCode"}
		
### Get Blueprint API specification for Protocol Reviewer Types [GET /irb/api/v1/protocol-reviewer-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Reviewer Types.md"
            transfer-encoding:chunked
### Update Protocol Reviewer Types [PUT /irb/api/v1/protocol-reviewer-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reviewerTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Reviewer Types [PUT /irb/api/v1/protocol-reviewer-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"reviewerTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"reviewerTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Protocol Reviewer Types [POST /irb/api/v1/protocol-reviewer-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reviewerTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"reviewerTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Reviewer Types [POST /irb/api/v1/protocol-reviewer-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"reviewerTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"reviewerTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"reviewerTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"reviewerTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Protocol Reviewer Types by Key [DELETE /irb/api/v1/protocol-reviewer-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Reviewer Types [DELETE /irb/api/v1/protocol-reviewer-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Reviewer Types with Matching [DELETE /irb/api/v1/protocol-reviewer-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + reviewerTypeCode (optional) - Reviewer Type. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
