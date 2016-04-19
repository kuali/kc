## Action Attributes [/research-sys/api/v1/action-attributes/]

### Get Action Attributes by Key [GET /research-sys/api/v1/action-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","value": "(val)","_primaryKey": "(val)"}

### Get All Action Attributes [GET /research-sys/api/v1/action-attributes/]
	 
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

### Get All Action Attributes with Filtering [GET /research-sys/api/v1/action-attributes/]
    
+ Parameters

    + id (optional) - 
    + value (optional) - 

            
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
			
### Get Schema for Action Attributes [GET /research-sys/api/v1/action-attributes/]
	                                          
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
		
### Get Blueprint API specification for Action Attributes [GET /research-sys/api/v1/action-attributes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Action Attributes.md"
            transfer-encoding:chunked


### Update Action Attributes [PUT /research-sys/api/v1/action-attributes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Action Attributes [PUT /research-sys/api/v1/action-attributes/]

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

### Insert Action Attributes [POST /research-sys/api/v1/action-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","value": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Action Attributes [POST /research-sys/api/v1/action-attributes/]

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
            
### Delete Action Attributes by Key [DELETE /research-sys/api/v1/action-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Action Attributes [DELETE /research-sys/api/v1/action-attributes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Action Attributes with Matching [DELETE /research-sys/api/v1/action-attributes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + value (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
