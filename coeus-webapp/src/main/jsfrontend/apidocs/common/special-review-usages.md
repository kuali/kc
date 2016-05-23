## Special Review Usages [/research-common/api/v1/special-review-usages/]

### Get Special Review Usages by Key [GET /research-common/api/v1/special-review-usages/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"specialReviewUsageId": "(val)","specialReviewTypeCode": "(val)","moduleCode": "(val)","global": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Special Review Usages [GET /research-common/api/v1/special-review-usages/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"specialReviewUsageId": "(val)","specialReviewTypeCode": "(val)","moduleCode": "(val)","global": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"specialReviewUsageId": "(val)","specialReviewTypeCode": "(val)","moduleCode": "(val)","global": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Special Review Usages with Filtering [GET /research-common/api/v1/special-review-usages/]
    
+ Parameters

    + specialReviewUsageId (optional) - Special Review Usage Id. Maximum length is 12.
    + specialReviewTypeCode (optional) - Special Review Type Code. Maximum length is 3.
    + moduleCode (optional) - Module Code. Maximum length is 4.
    + global (optional) - Global. Maximum length is 1.
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
              {"specialReviewUsageId": "(val)","specialReviewTypeCode": "(val)","moduleCode": "(val)","global": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"specialReviewUsageId": "(val)","specialReviewTypeCode": "(val)","moduleCode": "(val)","global": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Special Review Usages [GET /research-common/api/v1/special-review-usages/]
	                                          
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
    
            {"columns":["specialReviewUsageId","specialReviewTypeCode","moduleCode","global","active"],"primaryKey":"specialReviewUsageId"}
		
### Get Blueprint API specification for Special Review Usages [GET /research-common/api/v1/special-review-usages/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Special Review Usages.md"
            transfer-encoding:chunked
### Update Special Review Usages [PUT /research-common/api/v1/special-review-usages/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"specialReviewUsageId": "(val)","specialReviewTypeCode": "(val)","moduleCode": "(val)","global": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Special Review Usages [PUT /research-common/api/v1/special-review-usages/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"specialReviewUsageId": "(val)","specialReviewTypeCode": "(val)","moduleCode": "(val)","global": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"specialReviewUsageId": "(val)","specialReviewTypeCode": "(val)","moduleCode": "(val)","global": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Special Review Usages [POST /research-common/api/v1/special-review-usages/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"specialReviewUsageId": "(val)","specialReviewTypeCode": "(val)","moduleCode": "(val)","global": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"specialReviewUsageId": "(val)","specialReviewTypeCode": "(val)","moduleCode": "(val)","global": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Special Review Usages [POST /research-common/api/v1/special-review-usages/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"specialReviewUsageId": "(val)","specialReviewTypeCode": "(val)","moduleCode": "(val)","global": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"specialReviewUsageId": "(val)","specialReviewTypeCode": "(val)","moduleCode": "(val)","global": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"specialReviewUsageId": "(val)","specialReviewTypeCode": "(val)","moduleCode": "(val)","global": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"specialReviewUsageId": "(val)","specialReviewTypeCode": "(val)","moduleCode": "(val)","global": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete Special Review Usages by Key [DELETE /research-common/api/v1/special-review-usages/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Special Review Usages [DELETE /research-common/api/v1/special-review-usages/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Special Review Usages with Matching [DELETE /research-common/api/v1/special-review-usages/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + specialReviewUsageId (optional) - Special Review Usage Id. Maximum length is 12.
    + specialReviewTypeCode (optional) - Special Review Type Code. Maximum length is 3.
    + moduleCode (optional) - Module Code. Maximum length is 4.
    + global (optional) - Global. Maximum length is 1.
    + active (optional) - Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
