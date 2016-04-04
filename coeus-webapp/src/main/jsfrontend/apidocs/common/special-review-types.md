## Special Review Types [/research-sys/api/v1/special-review-types/]

### Get Special Review Types by Key [GET /research-sys/api/v1/special-review-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"specialReviewTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}

### Get All Special Review Types [GET /research-sys/api/v1/special-review-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"specialReviewTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"specialReviewTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Special Review Types with Filtering [GET /research-sys/api/v1/special-review-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + specialReviewTypeCode
            + description
            + sortId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"specialReviewTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"specialReviewTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Special Review Types [GET /research-sys/api/v1/special-review-types/]
	 
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
		
### Get Blueprint API specification for Special Review Types [GET /research-sys/api/v1/special-review-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Special Review Types.md"
            transfer-encoding:chunked


### Update Special Review Types [PUT /research-sys/api/v1/special-review-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"specialReviewTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Special Review Types [PUT /research-sys/api/v1/special-review-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"specialReviewTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"specialReviewTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Special Review Types [POST /research-sys/api/v1/special-review-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"specialReviewTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"specialReviewTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Special Review Types [POST /research-sys/api/v1/special-review-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"specialReviewTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"specialReviewTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"specialReviewTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"specialReviewTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Special Review Types by Key [DELETE /research-sys/api/v1/special-review-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Special Review Types [DELETE /research-sys/api/v1/special-review-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Special Review Types with Matching [DELETE /research-sys/api/v1/special-review-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + specialReviewTypeCode
            + description
            + sortId


+ Response 204
