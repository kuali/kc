## Sub Award Copyright Types [/subaward/api/v1/sub-award-copyright-types/]

### Get Sub Award Copyright Types by Key [GET /subaward/api/v1/sub-award-copyright-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Copyright Types [GET /subaward/api/v1/sub-award-copyright-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"},
              {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sub Award Copyright Types with Filtering [GET /subaward/api/v1/sub-award-copyright-types/]
    
+ Parameters

    + copyRightTypeCode (optional) - CopyRightTypeCode. Maximum length is 60.
    + copyRightTypeDescription (optional) - copyRightTypeDescription. Maximum length is 60.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"},
              {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sub Award Copyright Types [GET /subaward/api/v1/sub-award-copyright-types/]
	                                          
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
    
            {"columns":["copyRightTypeCode","copyRightTypeDescription"],"primaryKey":"copyRightTypeCode"}
		
### Get Blueprint API specification for Sub Award Copyright Types [GET /subaward/api/v1/sub-award-copyright-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Award Copyright Types.md"
            transfer-encoding:chunked


### Update Sub Award Copyright Types [PUT /subaward/api/v1/sub-award-copyright-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Copyright Types [PUT /subaward/api/v1/sub-award-copyright-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"},
              {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Sub Award Copyright Types [POST /subaward/api/v1/sub-award-copyright-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Copyright Types [POST /subaward/api/v1/sub-award-copyright-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"},
              {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"},
              {"copyRightTypeCode": "(val)","copyRightTypeDescription": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Sub Award Copyright Types by Key [DELETE /subaward/api/v1/sub-award-copyright-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Copyright Types [DELETE /subaward/api/v1/sub-award-copyright-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Copyright Types with Matching [DELETE /subaward/api/v1/sub-award-copyright-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + copyRightTypeCode (optional) - CopyRightTypeCode. Maximum length is 60.
    + copyRightTypeDescription (optional) - copyRightTypeDescription. Maximum length is 60.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
