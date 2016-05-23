## Rate Types [/research-common/api/v1/rate-types/]

### Get Rate Types by Key [GET /research-common/api/v1/rate-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Rate Types [GET /research-common/api/v1/rate-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Rate Types with Filtering [GET /research-common/api/v1/rate-types/]
    
+ Parameters

    + rateClassCode (optional) - Rate Class Code. Maximum length is 3.
    + rateTypeCode (optional) - Rate Type Code. Maximum length is 3.
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
              {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Rate Types [GET /research-common/api/v1/rate-types/]
	                                          
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
    
            {"columns":["rateClassCode","rateTypeCode","description"],"primaryKey":"rateClassCode:rateTypeCode"}
		
### Get Blueprint API specification for Rate Types [GET /research-common/api/v1/rate-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Rate Types.md"
            transfer-encoding:chunked
### Update Rate Types [PUT /research-common/api/v1/rate-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Rate Types [PUT /research-common/api/v1/rate-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Rate Types [POST /research-common/api/v1/rate-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Rate Types [POST /research-common/api/v1/rate-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"rateClassCode": "(val)","rateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Rate Types by Key [DELETE /research-common/api/v1/rate-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rate Types [DELETE /research-common/api/v1/rate-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rate Types with Matching [DELETE /research-common/api/v1/rate-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + rateClassCode (optional) - Rate Class Code. Maximum length is 3.
    + rateTypeCode (optional) - Rate Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
