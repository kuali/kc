## Expedited Review Checklist Items [/irb/api/v1/expedited-review-checklist-items/]

### Get Expedited Review Checklist Items by Key [GET /irb/api/v1/expedited-review-checklist-items/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"expeditedReviewCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Expedited Review Checklist Items [GET /irb/api/v1/expedited-review-checklist-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"expeditedReviewCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"expeditedReviewCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Expedited Review Checklist Items with Filtering [GET /irb/api/v1/expedited-review-checklist-items/]
    
+ Parameters

    + expeditedReviewCheckListCode (optional) - Expedited Review CheckList Code. Maximum length is 4.
    + description (optional) - Description. Maximum length is 2000.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"expeditedReviewCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"expeditedReviewCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Expedited Review Checklist Items [GET /irb/api/v1/expedited-review-checklist-items/]
	                                          
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
    
            {"columns":["expeditedReviewCheckListCode","description"],"primaryKey":"expeditedReviewCheckListCode"}
		
### Get Blueprint API specification for Expedited Review Checklist Items [GET /irb/api/v1/expedited-review-checklist-items/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Expedited Review Checklist Items.md"
            transfer-encoding:chunked
### Update Expedited Review Checklist Items [PUT /irb/api/v1/expedited-review-checklist-items/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"expeditedReviewCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Expedited Review Checklist Items [PUT /irb/api/v1/expedited-review-checklist-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"expeditedReviewCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"expeditedReviewCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Expedited Review Checklist Items [POST /irb/api/v1/expedited-review-checklist-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"expeditedReviewCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"expeditedReviewCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Expedited Review Checklist Items [POST /irb/api/v1/expedited-review-checklist-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"expeditedReviewCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"expeditedReviewCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"expeditedReviewCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"expeditedReviewCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Expedited Review Checklist Items by Key [DELETE /irb/api/v1/expedited-review-checklist-items/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Expedited Review Checklist Items [DELETE /irb/api/v1/expedited-review-checklist-items/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Expedited Review Checklist Items with Matching [DELETE /irb/api/v1/expedited-review-checklist-items/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + expeditedReviewCheckListCode (optional) - Expedited Review CheckList Code. Maximum length is 4.
    + description (optional) - Description. Maximum length is 2000.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
