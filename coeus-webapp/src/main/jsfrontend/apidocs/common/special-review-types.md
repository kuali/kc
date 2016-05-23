## Special Review Types [/research-common/api/v1/special-review-types/]

### Get Special Review Types by Key [GET /research-common/api/v1/special-review-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"specialReviewTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}

### Get All Special Review Types [GET /research-common/api/v1/special-review-types/]
	 
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

### Get All Special Review Types with Filtering [GET /research-common/api/v1/special-review-types/]
    
+ Parameters

    + specialReviewTypeCode (optional) - Special Review Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + sortId (optional) - Sort ID. Maximum length is 12.

            
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
			
### Get Schema for Special Review Types [GET /research-common/api/v1/special-review-types/]
	                                          
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
    
            {"columns":["specialReviewTypeCode","description","sortId"],"primaryKey":"specialReviewTypeCode"}
		
### Get Blueprint API specification for Special Review Types [GET /research-common/api/v1/special-review-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Special Review Types.md"
            transfer-encoding:chunked
### Update Special Review Types [PUT /research-common/api/v1/special-review-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"specialReviewTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Special Review Types [PUT /research-common/api/v1/special-review-types/]

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
### Insert Special Review Types [POST /research-common/api/v1/special-review-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"specialReviewTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"specialReviewTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Special Review Types [POST /research-common/api/v1/special-review-types/]

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
### Delete Special Review Types by Key [DELETE /research-common/api/v1/special-review-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Special Review Types [DELETE /research-common/api/v1/special-review-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Special Review Types with Matching [DELETE /research-common/api/v1/special-review-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + specialReviewTypeCode (optional) - Special Review Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + sortId (optional) - Sort ID. Maximum length is 12.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
