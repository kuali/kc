## Iacuc Exception Categories [/research-sys/api/v1/iacuc-exception-categories/]

### Get Iacuc Exception Categories by Key [GET /research-sys/api/v1/iacuc-exception-categories/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"exceptionCategoryCode": "(val)","exceptionCategoryDesc": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Exception Categories [GET /research-sys/api/v1/iacuc-exception-categories/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"exceptionCategoryCode": "(val)","exceptionCategoryDesc": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"exceptionCategoryCode": "(val)","exceptionCategoryDesc": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Exception Categories with Filtering [GET /research-sys/api/v1/iacuc-exception-categories/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + exceptionCategoryCode
            + exceptionCategoryDesc
            + active
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"exceptionCategoryCode": "(val)","exceptionCategoryDesc": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"exceptionCategoryCode": "(val)","exceptionCategoryDesc": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Exception Categories [GET /research-sys/api/v1/iacuc-exception-categories/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters

            + _schema (required) - will instruct the endpoint to return a schema data structure for the resource

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            ${sampleSchema}
		
### Get Blueprint API specification for Iacuc Exception Categories [GET /research-sys/api/v1/iacuc-exception-categories/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Exception Categories.md"
            transfer-encoding:chunked


### Update Iacuc Exception Categories [PUT /research-sys/api/v1/iacuc-exception-categories/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"exceptionCategoryCode": "(val)","exceptionCategoryDesc": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Exception Categories [PUT /research-sys/api/v1/iacuc-exception-categories/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"exceptionCategoryCode": "(val)","exceptionCategoryDesc": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"exceptionCategoryCode": "(val)","exceptionCategoryDesc": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Exception Categories [POST /research-sys/api/v1/iacuc-exception-categories/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"exceptionCategoryCode": "(val)","exceptionCategoryDesc": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"exceptionCategoryCode": "(val)","exceptionCategoryDesc": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Exception Categories [POST /research-sys/api/v1/iacuc-exception-categories/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"exceptionCategoryCode": "(val)","exceptionCategoryDesc": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"exceptionCategoryCode": "(val)","exceptionCategoryDesc": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"exceptionCategoryCode": "(val)","exceptionCategoryDesc": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"exceptionCategoryCode": "(val)","exceptionCategoryDesc": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Exception Categories by Key [DELETE /research-sys/api/v1/iacuc-exception-categories/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Exception Categories [DELETE /research-sys/api/v1/iacuc-exception-categories/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Exception Categories with Matching [DELETE /research-sys/api/v1/iacuc-exception-categories/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + exceptionCategoryCode
            + exceptionCategoryDesc
            + active


+ Response 204
