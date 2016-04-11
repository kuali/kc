## Ynq Explanation Types [/research-sys/api/v1/ynq-explanation-types/]

### Get Ynq Explanation Types by Key [GET /research-sys/api/v1/ynq-explanation-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"explanationType": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Ynq Explanation Types [GET /research-sys/api/v1/ynq-explanation-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"explanationType": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"explanationType": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Ynq Explanation Types with Filtering [GET /research-sys/api/v1/ynq-explanation-types/]
    
+ Parameters

        + explanationType
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
              {"explanationType": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"explanationType": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Ynq Explanation Types [GET /research-sys/api/v1/ynq-explanation-types/]
	                                          
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
    
            {"columns":["explanationType","description"],"primaryKey":"explanationType"}
		
### Get Blueprint API specification for Ynq Explanation Types [GET /research-sys/api/v1/ynq-explanation-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Ynq Explanation Types.md"
            transfer-encoding:chunked


### Update Ynq Explanation Types [PUT /research-sys/api/v1/ynq-explanation-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"explanationType": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Ynq Explanation Types [PUT /research-sys/api/v1/ynq-explanation-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"explanationType": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"explanationType": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Ynq Explanation Types [POST /research-sys/api/v1/ynq-explanation-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"explanationType": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"explanationType": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Ynq Explanation Types [POST /research-sys/api/v1/ynq-explanation-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"explanationType": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"explanationType": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"explanationType": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"explanationType": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Ynq Explanation Types by Key [DELETE /research-sys/api/v1/ynq-explanation-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Ynq Explanation Types [DELETE /research-sys/api/v1/ynq-explanation-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Ynq Explanation Types with Matching [DELETE /research-sys/api/v1/ynq-explanation-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + explanationType
            + description

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
