## Iacuc Protocol Project Types [/research-sys/api/v1/iacuc-protocol-project-types/]

### Get Iacuc Protocol Project Types by Key [GET /research-sys/api/v1/iacuc-protocol-project-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"projectTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Project Types [GET /research-sys/api/v1/iacuc-protocol-project-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"projectTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"projectTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Project Types with Filtering [GET /research-sys/api/v1/iacuc-protocol-project-types/]
    
+ Parameters

        + projectTypeCode
            + description

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"projectTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"projectTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Project Types [GET /research-sys/api/v1/iacuc-protocol-project-types/]
	                                          
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
    
            {"columns":["projectTypeCode","description"],"primaryKey":"projectTypeCode"}
		
### Get Blueprint API specification for Iacuc Protocol Project Types [GET /research-sys/api/v1/iacuc-protocol-project-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Project Types.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Project Types [PUT /research-sys/api/v1/iacuc-protocol-project-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"projectTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Project Types [PUT /research-sys/api/v1/iacuc-protocol-project-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"projectTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"projectTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Protocol Project Types [POST /research-sys/api/v1/iacuc-protocol-project-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"projectTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"projectTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Project Types [POST /research-sys/api/v1/iacuc-protocol-project-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"projectTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"projectTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"projectTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"projectTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Protocol Project Types by Key [DELETE /research-sys/api/v1/iacuc-protocol-project-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Project Types [DELETE /research-sys/api/v1/iacuc-protocol-project-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Project Types with Matching [DELETE /research-sys/api/v1/iacuc-protocol-project-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + projectTypeCode
            + description

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
