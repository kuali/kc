## Iacuc Exception Categories [/iacuc/api/v1/iacuc-exception-categories/]

### Get Iacuc Exception Categories by Key [GET /iacuc/api/v1/iacuc-exception-categories/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"exceptionCategoryCode": "(val)","exceptionCategoryDesc": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Exception Categories [GET /iacuc/api/v1/iacuc-exception-categories/]
	 
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

### Get All Iacuc Exception Categories with Filtering [GET /iacuc/api/v1/iacuc-exception-categories/]
    
+ Parameters

    + exceptionCategoryCode (optional) - Exception Category Code. Maximum length is 3.
    + exceptionCategoryDesc (optional) - Exception Category. Maximum length is 200.
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
              {"exceptionCategoryCode": "(val)","exceptionCategoryDesc": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"exceptionCategoryCode": "(val)","exceptionCategoryDesc": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Exception Categories [GET /iacuc/api/v1/iacuc-exception-categories/]
	                                          
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
    
            {"columns":["exceptionCategoryCode","exceptionCategoryDesc","active"],"primaryKey":"exceptionCategoryCode"}
		
### Get Blueprint API specification for Iacuc Exception Categories [GET /iacuc/api/v1/iacuc-exception-categories/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Exception Categories.md"
            transfer-encoding:chunked


### Update Iacuc Exception Categories [PUT /iacuc/api/v1/iacuc-exception-categories/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"exceptionCategoryCode": "(val)","exceptionCategoryDesc": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Exception Categories [PUT /iacuc/api/v1/iacuc-exception-categories/]

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

### Insert Iacuc Exception Categories [POST /iacuc/api/v1/iacuc-exception-categories/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"exceptionCategoryCode": "(val)","exceptionCategoryDesc": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"exceptionCategoryCode": "(val)","exceptionCategoryDesc": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Exception Categories [POST /iacuc/api/v1/iacuc-exception-categories/]

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
            
### Delete Iacuc Exception Categories by Key [DELETE /iacuc/api/v1/iacuc-exception-categories/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Exception Categories [DELETE /iacuc/api/v1/iacuc-exception-categories/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Exception Categories with Matching [DELETE /iacuc/api/v1/iacuc-exception-categories/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + exceptionCategoryCode (optional) - Exception Category Code. Maximum length is 3.
    + exceptionCategoryDesc (optional) - Exception Category. Maximum length is 200.
    + active (optional) - Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
