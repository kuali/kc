## Iacuc Species Count Types [/research-sys/api/v1/iacuc-species-count-types/]

### Get Iacuc Species Count Types by Key [GET /research-sys/api/v1/iacuc-species-count-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Species Count Types [GET /research-sys/api/v1/iacuc-species-count-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Species Count Types with Filtering [GET /research-sys/api/v1/iacuc-species-count-types/]
    
+ Parameters

        + speciesCountCode
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
              {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Species Count Types [GET /research-sys/api/v1/iacuc-species-count-types/]
	                                          
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
    
            {"columns":["speciesCountCode","description"],"primaryKey":"speciesCountCode"}
		
### Get Blueprint API specification for Iacuc Species Count Types [GET /research-sys/api/v1/iacuc-species-count-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Species Count Types.md"
            transfer-encoding:chunked


### Update Iacuc Species Count Types [PUT /research-sys/api/v1/iacuc-species-count-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Species Count Types [PUT /research-sys/api/v1/iacuc-species-count-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Species Count Types [POST /research-sys/api/v1/iacuc-species-count-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Species Count Types [POST /research-sys/api/v1/iacuc-species-count-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"speciesCountCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Species Count Types by Key [DELETE /research-sys/api/v1/iacuc-species-count-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Species Count Types [DELETE /research-sys/api/v1/iacuc-species-count-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Species Count Types with Matching [DELETE /research-sys/api/v1/iacuc-species-count-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + speciesCountCode
            + description

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
