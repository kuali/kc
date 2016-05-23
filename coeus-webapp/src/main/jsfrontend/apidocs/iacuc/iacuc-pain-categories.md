## Iacuc Pain Categories [/iacuc/api/v1/iacuc-pain-categories/]

### Get Iacuc Pain Categories by Key [GET /iacuc/api/v1/iacuc-pain-categories/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"painCategoryCode": "(val)","painCategory": "(val)","painLevel": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Pain Categories [GET /iacuc/api/v1/iacuc-pain-categories/]
	 
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

### Get All Iacuc Pain Categories with Filtering [GET /iacuc/api/v1/iacuc-pain-categories/]
    
+ Parameters

    + painCategoryCode (optional) - Pain Category Code. Maximum length is 3.
    + painCategory (optional) - Pain Category. Maximum length is 200.
    + painLevel (optional) - Pain Level. Maximum length is 3.
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
              {"painCategoryCode": "(val)","painCategory": "(val)","painLevel": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"painCategoryCode": "(val)","painCategory": "(val)","painLevel": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Pain Categories [GET /iacuc/api/v1/iacuc-pain-categories/]
	                                          
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
    
            {"columns":["painCategoryCode","painCategory","painLevel","active"],"primaryKey":"painCategoryCode"}
		
### Get Blueprint API specification for Iacuc Pain Categories [GET /iacuc/api/v1/iacuc-pain-categories/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Pain Categories.md"
            transfer-encoding:chunked
### Update Iacuc Pain Categories [PUT /iacuc/api/v1/iacuc-pain-categories/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"painCategoryCode": "(val)","painCategory": "(val)","painLevel": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Pain Categories [PUT /iacuc/api/v1/iacuc-pain-categories/]

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
### Insert Iacuc Pain Categories [POST /iacuc/api/v1/iacuc-pain-categories/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"painCategoryCode": "(val)","painCategory": "(val)","painLevel": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"painCategoryCode": "(val)","painCategory": "(val)","painLevel": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Pain Categories [POST /iacuc/api/v1/iacuc-pain-categories/]

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
### Delete Iacuc Pain Categories by Key [DELETE /iacuc/api/v1/iacuc-pain-categories/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Pain Categories [DELETE /iacuc/api/v1/iacuc-pain-categories/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Pain Categories with Matching [DELETE /iacuc/api/v1/iacuc-pain-categories/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + painCategoryCode (optional) - Pain Category Code. Maximum length is 3.
    + painCategory (optional) - Pain Category. Maximum length is 200.
    + painLevel (optional) - Pain Level. Maximum length is 3.
    + active (optional) - Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
