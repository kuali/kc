## Frequency Bases [/award/api/v1/frequency-bases/]

### Get Frequency Bases by Key [GET /award/api/v1/frequency-bases/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Frequency Bases [GET /award/api/v1/frequency-bases/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Frequency Bases with Filtering [GET /award/api/v1/frequency-bases/]
    
+ Parameters

    + frequencyBaseCode (optional) - Frequency Base Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + regenerationTypeName (optional) - Regeneration Policy. Maximum length is 200.
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
              {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Frequency Bases [GET /award/api/v1/frequency-bases/]
	                                          
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
    
            {"columns":["frequencyBaseCode","description","regenerationTypeName","active"],"primaryKey":"frequencyBaseCode"}
		
### Get Blueprint API specification for Frequency Bases [GET /award/api/v1/frequency-bases/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Frequency Bases.md"
            transfer-encoding:chunked
### Update Frequency Bases [PUT /award/api/v1/frequency-bases/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Frequency Bases [PUT /award/api/v1/frequency-bases/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Frequency Bases [POST /award/api/v1/frequency-bases/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Frequency Bases [POST /award/api/v1/frequency-bases/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"frequencyBaseCode": "(val)","description": "(val)","regenerationTypeName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete Frequency Bases by Key [DELETE /award/api/v1/frequency-bases/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Frequency Bases [DELETE /award/api/v1/frequency-bases/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Frequency Bases with Matching [DELETE /award/api/v1/frequency-bases/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + frequencyBaseCode (optional) - Frequency Base Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + regenerationTypeName (optional) - Regeneration Policy. Maximum length is 200.
    + active (optional) - Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
