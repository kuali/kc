## Rule Expression Definitions [/research-sys/api/v1/rule-expression-definitions/]

### Get Rule Expression Definitions by Key [GET /research-sys/api/v1/rule-expression-definitions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","type": "(val)","expression": "(val)","_primaryKey": "(val)"}

### Get All Rule Expression Definitions [GET /research-sys/api/v1/rule-expression-definitions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","type": "(val)","expression": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","type": "(val)","expression": "(val)","_primaryKey": "(val)"}
            ]

### Get All Rule Expression Definitions with Filtering [GET /research-sys/api/v1/rule-expression-definitions/]
    
+ Parameters

        + id
            + type
            + expression

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","type": "(val)","expression": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","type": "(val)","expression": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Rule Expression Definitions [GET /research-sys/api/v1/rule-expression-definitions/]
	                                          
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
    
            {"columns":["id","type","expression"],"primaryKey":"id"}
		
### Get Blueprint API specification for Rule Expression Definitions [GET /research-sys/api/v1/rule-expression-definitions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Rule Expression Definitions.md"
            transfer-encoding:chunked


### Update Rule Expression Definitions [PUT /research-sys/api/v1/rule-expression-definitions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","type": "(val)","expression": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Rule Expression Definitions [PUT /research-sys/api/v1/rule-expression-definitions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","type": "(val)","expression": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","type": "(val)","expression": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Rule Expression Definitions [POST /research-sys/api/v1/rule-expression-definitions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","type": "(val)","expression": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","type": "(val)","expression": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Rule Expression Definitions [POST /research-sys/api/v1/rule-expression-definitions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","type": "(val)","expression": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","type": "(val)","expression": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","type": "(val)","expression": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","type": "(val)","expression": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Rule Expression Definitions by Key [DELETE /research-sys/api/v1/rule-expression-definitions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rule Expression Definitions [DELETE /research-sys/api/v1/rule-expression-definitions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rule Expression Definitions with Matching [DELETE /research-sys/api/v1/rule-expression-definitions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + type
            + expression

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
