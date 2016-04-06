## Intellectual Property Review Activity Types [/research-sys/api/v1/intellectual-property-review-activity-types/]

### Get Intellectual Property Review Activity Types by Key [GET /research-sys/api/v1/intellectual-property-review-activity-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"intellectualPropertyReviewActivityTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Intellectual Property Review Activity Types [GET /research-sys/api/v1/intellectual-property-review-activity-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"intellectualPropertyReviewActivityTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"intellectualPropertyReviewActivityTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Intellectual Property Review Activity Types with Filtering [GET /research-sys/api/v1/intellectual-property-review-activity-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + intellectualPropertyReviewActivityTypeCode
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"intellectualPropertyReviewActivityTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"intellectualPropertyReviewActivityTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Intellectual Property Review Activity Types [GET /research-sys/api/v1/intellectual-property-review-activity-types/]
	 
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
		
### Get Blueprint API specification for Intellectual Property Review Activity Types [GET /research-sys/api/v1/intellectual-property-review-activity-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Intellectual Property Review Activity Types.md"
            transfer-encoding:chunked


### Update Intellectual Property Review Activity Types [PUT /research-sys/api/v1/intellectual-property-review-activity-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"intellectualPropertyReviewActivityTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Intellectual Property Review Activity Types [PUT /research-sys/api/v1/intellectual-property-review-activity-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"intellectualPropertyReviewActivityTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"intellectualPropertyReviewActivityTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Intellectual Property Review Activity Types [POST /research-sys/api/v1/intellectual-property-review-activity-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"intellectualPropertyReviewActivityTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"intellectualPropertyReviewActivityTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Intellectual Property Review Activity Types [POST /research-sys/api/v1/intellectual-property-review-activity-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"intellectualPropertyReviewActivityTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"intellectualPropertyReviewActivityTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"intellectualPropertyReviewActivityTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"intellectualPropertyReviewActivityTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Intellectual Property Review Activity Types by Key [DELETE /research-sys/api/v1/intellectual-property-review-activity-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Intellectual Property Review Activity Types [DELETE /research-sys/api/v1/intellectual-property-review-activity-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Intellectual Property Review Activity Types with Matching [DELETE /research-sys/api/v1/intellectual-property-review-activity-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + intellectualPropertyReviewActivityTypeCode
            + description


+ Response 204
