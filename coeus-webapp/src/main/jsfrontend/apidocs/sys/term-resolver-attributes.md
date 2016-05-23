## Term Resolver Attributes [/research-sys/api/v1/term-resolver-attributes/]

### Get Term Resolver Attributes by Key [GET /research-sys/api/v1/term-resolver-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","termResolverId": "(val)","value": "(val)","_primaryKey": "(val)"}

### Get All Term Resolver Attributes [GET /research-sys/api/v1/term-resolver-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","termResolverId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","termResolverId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]

### Get All Term Resolver Attributes with Filtering [GET /research-sys/api/v1/term-resolver-attributes/]
    
+ Parameters

    + id (optional) - 
    + termResolverId (optional) - 
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
              {"id": "(val)","termResolverId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","termResolverId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Term Resolver Attributes [GET /research-sys/api/v1/term-resolver-attributes/]
	                                          
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
    
            {"columns":["id","termResolverId","value"],"primaryKey":"id"}
		
### Get Blueprint API specification for Term Resolver Attributes [GET /research-sys/api/v1/term-resolver-attributes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Term Resolver Attributes.md"
            transfer-encoding:chunked
### Update Term Resolver Attributes [PUT /research-sys/api/v1/term-resolver-attributes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","termResolverId": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Term Resolver Attributes [PUT /research-sys/api/v1/term-resolver-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","termResolverId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","termResolverId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Term Resolver Attributes [POST /research-sys/api/v1/term-resolver-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","termResolverId": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","termResolverId": "(val)","value": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Term Resolver Attributes [POST /research-sys/api/v1/term-resolver-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","termResolverId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","termResolverId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","termResolverId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","termResolverId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
### Delete Term Resolver Attributes by Key [DELETE /research-sys/api/v1/term-resolver-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Term Resolver Attributes [DELETE /research-sys/api/v1/term-resolver-attributes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Term Resolver Attributes with Matching [DELETE /research-sys/api/v1/term-resolver-attributes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + termResolverId (optional) - 
    + value (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
