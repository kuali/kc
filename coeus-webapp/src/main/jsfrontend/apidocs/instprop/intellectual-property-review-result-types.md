## Intellectual Property Review Result Types [/instprop/api/v1/intellectual-property-review-result-types/]

### Get Intellectual Property Review Result Types by Key [GET /instprop/api/v1/intellectual-property-review-result-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"intellectualPropertyReviewResultTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Intellectual Property Review Result Types [GET /instprop/api/v1/intellectual-property-review-result-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"intellectualPropertyReviewResultTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"intellectualPropertyReviewResultTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Intellectual Property Review Result Types with Filtering [GET /instprop/api/v1/intellectual-property-review-result-types/]
    
+ Parameters

    + intellectualPropertyReviewResultTypeCode (optional) - Intellectual Property Review Result Type Code. Maximum length is 3.
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
              {"intellectualPropertyReviewResultTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"intellectualPropertyReviewResultTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Intellectual Property Review Result Types [GET /instprop/api/v1/intellectual-property-review-result-types/]
	                                          
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
    
            {"columns":["intellectualPropertyReviewResultTypeCode","description"],"primaryKey":"intellectualPropertyReviewResultTypeCode"}
		
### Get Blueprint API specification for Intellectual Property Review Result Types [GET /instprop/api/v1/intellectual-property-review-result-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Intellectual Property Review Result Types.md"
            transfer-encoding:chunked


### Update Intellectual Property Review Result Types [PUT /instprop/api/v1/intellectual-property-review-result-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"intellectualPropertyReviewResultTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Intellectual Property Review Result Types [PUT /instprop/api/v1/intellectual-property-review-result-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"intellectualPropertyReviewResultTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"intellectualPropertyReviewResultTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Intellectual Property Review Result Types [POST /instprop/api/v1/intellectual-property-review-result-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"intellectualPropertyReviewResultTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"intellectualPropertyReviewResultTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Intellectual Property Review Result Types [POST /instprop/api/v1/intellectual-property-review-result-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"intellectualPropertyReviewResultTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"intellectualPropertyReviewResultTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"intellectualPropertyReviewResultTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"intellectualPropertyReviewResultTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Intellectual Property Review Result Types by Key [DELETE /instprop/api/v1/intellectual-property-review-result-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Intellectual Property Review Result Types [DELETE /instprop/api/v1/intellectual-property-review-result-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Intellectual Property Review Result Types with Matching [DELETE /instprop/api/v1/intellectual-property-review-result-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + intellectualPropertyReviewResultTypeCode (optional) - Intellectual Property Review Result Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
