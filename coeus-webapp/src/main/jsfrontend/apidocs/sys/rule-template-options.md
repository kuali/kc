## Rule Template Options [/research-sys/api/v1/rule-template-options/]

### Get Rule Template Options by Key [GET /research-sys/api/v1/rule-template-options/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","code": "(val)","value": "(val)","_primaryKey": "(val)"}

### Get All Rule Template Options [GET /research-sys/api/v1/rule-template-options/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","code": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","code": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]

### Get All Rule Template Options with Filtering [GET /research-sys/api/v1/rule-template-options/]
    
+ Parameters

        + id
            + code
            + value

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","code": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","code": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Rule Template Options [GET /research-sys/api/v1/rule-template-options/]
	                                          
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
    
            {"columns":["id","code","value"],"primaryKey":"id"}
		
### Get Blueprint API specification for Rule Template Options [GET /research-sys/api/v1/rule-template-options/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Rule Template Options.md"
            transfer-encoding:chunked


### Update Rule Template Options [PUT /research-sys/api/v1/rule-template-options/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","code": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Rule Template Options [PUT /research-sys/api/v1/rule-template-options/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","code": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","code": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Rule Template Options [POST /research-sys/api/v1/rule-template-options/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","code": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","code": "(val)","value": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Rule Template Options [POST /research-sys/api/v1/rule-template-options/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","code": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","code": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","code": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","code": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Rule Template Options by Key [DELETE /research-sys/api/v1/rule-template-options/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rule Template Options [DELETE /research-sys/api/v1/rule-template-options/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rule Template Options with Matching [DELETE /research-sys/api/v1/rule-template-options/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + code
            + value

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
