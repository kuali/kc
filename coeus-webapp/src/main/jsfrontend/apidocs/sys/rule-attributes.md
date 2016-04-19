## Rule Attributes [/research-sys/api/v1/rule-attributes/]

### Get Rule Attributes by Key [GET /research-sys/api/v1/rule-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","value": "(val)","_primaryKey": "(val)"}

### Get All Rule Attributes [GET /research-sys/api/v1/rule-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]

### Get All Rule Attributes with Filtering [GET /research-sys/api/v1/rule-attributes/]
    
+ Parameters

    + id (optional) - Rule Attribute Id.
    + value (optional) - Attribute Value.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Rule Attributes [GET /research-sys/api/v1/rule-attributes/]
	                                          
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
    
            {"columns":["id","value"],"primaryKey":"id"}
		
### Get Blueprint API specification for Rule Attributes [GET /research-sys/api/v1/rule-attributes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Rule Attributes.md"
            transfer-encoding:chunked


### Update Rule Attributes [PUT /research-sys/api/v1/rule-attributes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Rule Attributes [PUT /research-sys/api/v1/rule-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Rule Attributes [POST /research-sys/api/v1/rule-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","value": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Rule Attributes [POST /research-sys/api/v1/rule-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Rule Attributes by Key [DELETE /research-sys/api/v1/rule-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rule Attributes [DELETE /research-sys/api/v1/rule-attributes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rule Attributes with Matching [DELETE /research-sys/api/v1/rule-attributes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Rule Attribute Id.
    + value (optional) - Attribute Value.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
