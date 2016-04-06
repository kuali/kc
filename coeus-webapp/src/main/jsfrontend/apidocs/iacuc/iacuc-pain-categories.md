## Iacuc Pain Categories [/research-sys/api/v1/iacuc-pain-categories/]

### Get Iacuc Pain Categories by Key [GET /research-sys/api/v1/iacuc-pain-categories/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"painCategoryCode": "(val)","painCategory": "(val)","painLevel": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Pain Categories [GET /research-sys/api/v1/iacuc-pain-categories/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"painCategoryCode": "(val)","painCategory": "(val)","painLevel": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"painCategoryCode": "(val)","painCategory": "(val)","painLevel": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Pain Categories with Filtering [GET /research-sys/api/v1/iacuc-pain-categories/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + painCategoryCode
            + painCategory
            + painLevel
            + active
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"painCategoryCode": "(val)","painCategory": "(val)","painLevel": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"painCategoryCode": "(val)","painCategory": "(val)","painLevel": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Pain Categories [GET /research-sys/api/v1/iacuc-pain-categories/]
	 
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
		
### Get Blueprint API specification for Iacuc Pain Categories [GET /research-sys/api/v1/iacuc-pain-categories/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Pain Categories.md"
            transfer-encoding:chunked


### Update Iacuc Pain Categories [PUT /research-sys/api/v1/iacuc-pain-categories/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"painCategoryCode": "(val)","painCategory": "(val)","painLevel": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Pain Categories [PUT /research-sys/api/v1/iacuc-pain-categories/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"painCategoryCode": "(val)","painCategory": "(val)","painLevel": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"painCategoryCode": "(val)","painCategory": "(val)","painLevel": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Pain Categories [POST /research-sys/api/v1/iacuc-pain-categories/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"painCategoryCode": "(val)","painCategory": "(val)","painLevel": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"painCategoryCode": "(val)","painCategory": "(val)","painLevel": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Pain Categories [POST /research-sys/api/v1/iacuc-pain-categories/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"painCategoryCode": "(val)","painCategory": "(val)","painLevel": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"painCategoryCode": "(val)","painCategory": "(val)","painLevel": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"painCategoryCode": "(val)","painCategory": "(val)","painLevel": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"painCategoryCode": "(val)","painCategory": "(val)","painLevel": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Pain Categories by Key [DELETE /research-sys/api/v1/iacuc-pain-categories/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Pain Categories [DELETE /research-sys/api/v1/iacuc-pain-categories/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Pain Categories with Matching [DELETE /research-sys/api/v1/iacuc-pain-categories/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + painCategoryCode
            + painCategory
            + painLevel
            + active


+ Response 204
