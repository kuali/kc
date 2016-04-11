## Note Types [/research-sys/api/v1/note-types/]

### Get Note Types by Key [GET /research-sys/api/v1/note-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"noteTypeCode": "(val)","noteTypeDescription": "(val)","noteTypeActiveIndicator": "(val)","_primaryKey": "(val)"}

### Get All Note Types [GET /research-sys/api/v1/note-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"noteTypeCode": "(val)","noteTypeDescription": "(val)","noteTypeActiveIndicator": "(val)","_primaryKey": "(val)"},
              {"noteTypeCode": "(val)","noteTypeDescription": "(val)","noteTypeActiveIndicator": "(val)","_primaryKey": "(val)"}
            ]

### Get All Note Types with Filtering [GET /research-sys/api/v1/note-types/]
    
+ Parameters

        + noteTypeCode
            + noteTypeDescription
            + noteTypeActiveIndicator

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"noteTypeCode": "(val)","noteTypeDescription": "(val)","noteTypeActiveIndicator": "(val)","_primaryKey": "(val)"},
              {"noteTypeCode": "(val)","noteTypeDescription": "(val)","noteTypeActiveIndicator": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Note Types [GET /research-sys/api/v1/note-types/]
	                                          
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
    
            {"columns":["noteTypeCode","noteTypeDescription","noteTypeActiveIndicator"],"primaryKey":"noteTypeCode"}
		
### Get Blueprint API specification for Note Types [GET /research-sys/api/v1/note-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Note Types.md"
            transfer-encoding:chunked


### Update Note Types [PUT /research-sys/api/v1/note-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"noteTypeCode": "(val)","noteTypeDescription": "(val)","noteTypeActiveIndicator": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Note Types [PUT /research-sys/api/v1/note-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"noteTypeCode": "(val)","noteTypeDescription": "(val)","noteTypeActiveIndicator": "(val)","_primaryKey": "(val)"},
              {"noteTypeCode": "(val)","noteTypeDescription": "(val)","noteTypeActiveIndicator": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Note Types [POST /research-sys/api/v1/note-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"noteTypeCode": "(val)","noteTypeDescription": "(val)","noteTypeActiveIndicator": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"noteTypeCode": "(val)","noteTypeDescription": "(val)","noteTypeActiveIndicator": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Note Types [POST /research-sys/api/v1/note-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"noteTypeCode": "(val)","noteTypeDescription": "(val)","noteTypeActiveIndicator": "(val)","_primaryKey": "(val)"},
              {"noteTypeCode": "(val)","noteTypeDescription": "(val)","noteTypeActiveIndicator": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"noteTypeCode": "(val)","noteTypeDescription": "(val)","noteTypeActiveIndicator": "(val)","_primaryKey": "(val)"},
              {"noteTypeCode": "(val)","noteTypeDescription": "(val)","noteTypeActiveIndicator": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Note Types by Key [DELETE /research-sys/api/v1/note-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Note Types [DELETE /research-sys/api/v1/note-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Note Types with Matching [DELETE /research-sys/api/v1/note-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + noteTypeCode
            + noteTypeDescription
            + noteTypeActiveIndicator

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
