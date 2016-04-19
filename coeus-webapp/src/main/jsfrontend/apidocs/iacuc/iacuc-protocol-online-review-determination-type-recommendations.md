## Iacuc Protocol Online Review Determination Type Recommendations [/iacuc/api/v1/iacuc-protocol-online-review-determination-type-recommendations/]

### Get Iacuc Protocol Online Review Determination Type Recommendations by Key [GET /iacuc/api/v1/iacuc-protocol-online-review-determination-type-recommendations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"iacucProtocolReviewTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Online Review Determination Type Recommendations [GET /iacuc/api/v1/iacuc-protocol-online-review-determination-type-recommendations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucProtocolReviewTypeCode": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolReviewTypeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Online Review Determination Type Recommendations with Filtering [GET /iacuc/api/v1/iacuc-protocol-online-review-determination-type-recommendations/]
    
+ Parameters

    + iacucProtocolReviewTypeCode (optional) - Review Type. Maximum length is 3.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucProtocolReviewTypeCode": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolReviewTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Online Review Determination Type Recommendations [GET /iacuc/api/v1/iacuc-protocol-online-review-determination-type-recommendations/]
	                                          
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
    
            {"columns":["iacucProtocolReviewTypeCode"],"primaryKey":"iacucProtocolReviewTypeCode"}
		
### Get Blueprint API specification for Iacuc Protocol Online Review Determination Type Recommendations [GET /iacuc/api/v1/iacuc-protocol-online-review-determination-type-recommendations/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Online Review Determination Type Recommendations.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Online Review Determination Type Recommendations [PUT /iacuc/api/v1/iacuc-protocol-online-review-determination-type-recommendations/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolReviewTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Online Review Determination Type Recommendations [PUT /iacuc/api/v1/iacuc-protocol-online-review-determination-type-recommendations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"iacucProtocolReviewTypeCode": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolReviewTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Protocol Online Review Determination Type Recommendations [POST /iacuc/api/v1/iacuc-protocol-online-review-determination-type-recommendations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolReviewTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"iacucProtocolReviewTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Online Review Determination Type Recommendations [POST /iacuc/api/v1/iacuc-protocol-online-review-determination-type-recommendations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"iacucProtocolReviewTypeCode": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolReviewTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"iacucProtocolReviewTypeCode": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolReviewTypeCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Protocol Online Review Determination Type Recommendations by Key [DELETE /iacuc/api/v1/iacuc-protocol-online-review-determination-type-recommendations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Online Review Determination Type Recommendations [DELETE /iacuc/api/v1/iacuc-protocol-online-review-determination-type-recommendations/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Online Review Determination Type Recommendations with Matching [DELETE /iacuc/api/v1/iacuc-protocol-online-review-determination-type-recommendations/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + iacucProtocolReviewTypeCode (optional) - Review Type. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
