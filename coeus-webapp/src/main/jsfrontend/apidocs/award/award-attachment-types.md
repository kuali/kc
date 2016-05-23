## Award Attachment Types [/award/api/v1/award-attachment-types/]

### Get Award Attachment Types by Key [GET /award/api/v1/award-attachment-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"typeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Award Attachment Types [GET /award/api/v1/award-attachment-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"typeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"typeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Attachment Types with Filtering [GET /award/api/v1/award-attachment-types/]
    
+ Parameters

    + typeCode (optional) - Code. Maximum length is 3.
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
              {"typeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"typeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Attachment Types [GET /award/api/v1/award-attachment-types/]
	                                          
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
    
            {"columns":["typeCode","description"],"primaryKey":"typeCode"}
		
### Get Blueprint API specification for Award Attachment Types [GET /award/api/v1/award-attachment-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Attachment Types.md"
            transfer-encoding:chunked
### Update Award Attachment Types [PUT /award/api/v1/award-attachment-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"typeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Attachment Types [PUT /award/api/v1/award-attachment-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"typeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"typeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Award Attachment Types [POST /award/api/v1/award-attachment-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"typeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"typeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Attachment Types [POST /award/api/v1/award-attachment-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"typeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"typeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"typeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"typeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Award Attachment Types by Key [DELETE /award/api/v1/award-attachment-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Attachment Types [DELETE /award/api/v1/award-attachment-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Attachment Types with Matching [DELETE /award/api/v1/award-attachment-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + typeCode (optional) - Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
