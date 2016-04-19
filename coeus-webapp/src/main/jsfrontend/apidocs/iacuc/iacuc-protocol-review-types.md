## Iacuc Protocol Review Types [/iacuc/api/v1/iacuc-protocol-review-types/]

### Get Iacuc Protocol Review Types by Key [GET /iacuc/api/v1/iacuc-protocol-review-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Review Types [GET /iacuc/api/v1/iacuc-protocol-review-types/]
	 
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

### Get All Iacuc Protocol Review Types with Filtering [GET /iacuc/api/v1/iacuc-protocol-review-types/]
    
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
			
### Get Schema for Iacuc Protocol Review Types [GET /iacuc/api/v1/iacuc-protocol-review-types/]
	                                          
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
		
### Get Blueprint API specification for Iacuc Protocol Review Types [GET /iacuc/api/v1/iacuc-protocol-review-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Review Types.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Review Types [PUT /iacuc/api/v1/iacuc-protocol-review-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Review Types [PUT /iacuc/api/v1/iacuc-protocol-review-types/]

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

### Insert Iacuc Protocol Review Types [POST /iacuc/api/v1/iacuc-protocol-review-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"reviewTypeCode": "(val)","description": "(val)","globalFlag": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Review Types [POST /iacuc/api/v1/iacuc-protocol-review-types/]

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
            
### Delete Iacuc Protocol Review Types by Key [DELETE /iacuc/api/v1/iacuc-protocol-review-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Review Types [DELETE /iacuc/api/v1/iacuc-protocol-review-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Review Types with Matching [DELETE /iacuc/api/v1/iacuc-protocol-review-types/]

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
