## Special Review Usages [/research-sys/api/v1/special-review-usages/]

### Get Special Review Usages by Key [GET /research-sys/api/v1/special-review-usages/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"specialReviewUsageId": "(val)","specialReviewTypeCode": "(val)","moduleCode": "(val)","global": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Special Review Usages [GET /research-sys/api/v1/special-review-usages/]
	 
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

### Get All Special Review Usages with Filtering [GET /research-sys/api/v1/special-review-usages/]
    
+ Parameters

        + specialReviewUsageId
            + specialReviewTypeCode
            + moduleCode
            + global
            + active

            
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
			
### Get Schema for Special Review Usages [GET /research-sys/api/v1/special-review-usages/]
	                                          
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
		
### Get Blueprint API specification for Special Review Usages [GET /research-sys/api/v1/special-review-usages/]
	 
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


### Update Special Review Usages [PUT /research-sys/api/v1/special-review-usages/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"specialReviewUsageId": "(val)","specialReviewTypeCode": "(val)","moduleCode": "(val)","global": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Special Review Usages [PUT /research-sys/api/v1/special-review-usages/]

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

### Insert Special Review Usages [POST /research-sys/api/v1/special-review-usages/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"specialReviewUsageId": "(val)","specialReviewTypeCode": "(val)","moduleCode": "(val)","global": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"specialReviewUsageId": "(val)","specialReviewTypeCode": "(val)","moduleCode": "(val)","global": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Special Review Usages [POST /research-sys/api/v1/special-review-usages/]

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
            
### Delete Special Review Usages by Key [DELETE /research-sys/api/v1/special-review-usages/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Special Review Usages [DELETE /research-sys/api/v1/special-review-usages/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Special Review Usages with Matching [DELETE /research-sys/api/v1/special-review-usages/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + specialReviewUsageId
            + specialReviewTypeCode
            + moduleCode
            + global
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
