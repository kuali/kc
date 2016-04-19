## Iacuc Protocol Online Review Statuses [/iacuc/api/v1/iacuc-protocol-online-review-statuses/]

### Get Iacuc Protocol Online Review Statuses by Key [GET /iacuc/api/v1/iacuc-protocol-online-review-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Online Review Statuses [GET /iacuc/api/v1/iacuc-protocol-online-review-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Online Review Statuses with Filtering [GET /iacuc/api/v1/iacuc-protocol-online-review-statuses/]
    
+ Parameters

    + statusCode (optional) - Status Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 300.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Online Review Statuses [GET /iacuc/api/v1/iacuc-protocol-online-review-statuses/]
	                                          
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
    
            {"columns":["statusCode","description"],"primaryKey":"statusCode"}
		
### Get Blueprint API specification for Iacuc Protocol Online Review Statuses [GET /iacuc/api/v1/iacuc-protocol-online-review-statuses/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Online Review Statuses.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Online Review Statuses [PUT /iacuc/api/v1/iacuc-protocol-online-review-statuses/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Online Review Statuses [PUT /iacuc/api/v1/iacuc-protocol-online-review-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Protocol Online Review Statuses [POST /iacuc/api/v1/iacuc-protocol-online-review-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Online Review Statuses [POST /iacuc/api/v1/iacuc-protocol-online-review-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"statusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Protocol Online Review Statuses by Key [DELETE /iacuc/api/v1/iacuc-protocol-online-review-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Online Review Statuses [DELETE /iacuc/api/v1/iacuc-protocol-online-review-statuses/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Online Review Statuses with Matching [DELETE /iacuc/api/v1/iacuc-protocol-online-review-statuses/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + statusCode (optional) - Status Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 300.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
