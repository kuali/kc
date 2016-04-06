## Expedited Review Checklist Items [/research-sys/api/v1/expedited-review-checklist-items/]

### Get Expedited Review Checklist Items by Key [GET /research-sys/api/v1/expedited-review-checklist-items/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"expeditedReviewCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Expedited Review Checklist Items [GET /research-sys/api/v1/expedited-review-checklist-items/]
	 
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

### Get All Expedited Review Checklist Items with Filtering [GET /research-sys/api/v1/expedited-review-checklist-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + expeditedReviewCheckListCode
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"expeditedReviewCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"expeditedReviewCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Expedited Review Checklist Items [GET /research-sys/api/v1/expedited-review-checklist-items/]
	 
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
		
### Get Blueprint API specification for Expedited Review Checklist Items [GET /research-sys/api/v1/expedited-review-checklist-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Expedited Review Checklist Items.md"
            transfer-encoding:chunked


### Update Expedited Review Checklist Items [PUT /research-sys/api/v1/expedited-review-checklist-items/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"expeditedReviewCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Expedited Review Checklist Items [PUT /research-sys/api/v1/expedited-review-checklist-items/]

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

### Insert Expedited Review Checklist Items [POST /research-sys/api/v1/expedited-review-checklist-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"expeditedReviewCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"expeditedReviewCheckListCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Expedited Review Checklist Items [POST /research-sys/api/v1/expedited-review-checklist-items/]

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
            
### Delete Expedited Review Checklist Items by Key [DELETE /research-sys/api/v1/expedited-review-checklist-items/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Expedited Review Checklist Items [DELETE /research-sys/api/v1/expedited-review-checklist-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Expedited Review Checklist Items with Matching [DELETE /research-sys/api/v1/expedited-review-checklist-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + expeditedReviewCheckListCode
            + description


+ Response 204
