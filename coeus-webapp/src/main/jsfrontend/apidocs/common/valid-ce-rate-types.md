## Valid Ce Rate Types [/research-common/api/v1/valid-ce-rate-types/]

### Get Valid Ce Rate Types by Key [GET /research-common/api/v1/valid-ce-rate-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Valid Ce Rate Types [GET /research-common/api/v1/valid-ce-rate-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Valid Ce Rate Types with Filtering [GET /research-common/api/v1/valid-ce-rate-types/]
    
+ Parameters

    + costElement (optional) - Cost Element. Maximum length is 8.
    + rateClassCode (optional) - Rate Class Code. Maximum length is 3.
    + rateTypeCode (optional) - Rate Type Code. Maximum length is 3.
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
              {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Valid Ce Rate Types [GET /research-common/api/v1/valid-ce-rate-types/]
	                                          
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
    
            {"columns":["costElement","rateClassCode","rateTypeCode","active"],"primaryKey":"costElement:rateClassCode:rateTypeCode"}
		
### Get Blueprint API specification for Valid Ce Rate Types [GET /research-common/api/v1/valid-ce-rate-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Valid Ce Rate Types.md"
            transfer-encoding:chunked
### Update Valid Ce Rate Types [PUT /research-common/api/v1/valid-ce-rate-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Valid Ce Rate Types [PUT /research-common/api/v1/valid-ce-rate-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Valid Ce Rate Types [POST /research-common/api/v1/valid-ce-rate-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Valid Ce Rate Types [POST /research-common/api/v1/valid-ce-rate-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"costElement": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete Valid Ce Rate Types by Key [DELETE /research-common/api/v1/valid-ce-rate-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Ce Rate Types [DELETE /research-common/api/v1/valid-ce-rate-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Ce Rate Types with Matching [DELETE /research-common/api/v1/valid-ce-rate-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + costElement (optional) - Cost Element. Maximum length is 8.
    + rateClassCode (optional) - Rate Class Code. Maximum length is 3.
    + rateTypeCode (optional) - Rate Type Code. Maximum length is 3.
    + active (optional) - Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
