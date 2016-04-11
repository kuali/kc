## Components [/research-sys/api/v1/components/]

### Get Components by Key [GET /research-sys/api/v1/components/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"namespaceCode": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Components [GET /research-sys/api/v1/components/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"namespaceCode": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"namespaceCode": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Components with Filtering [GET /research-sys/api/v1/components/]
    
+ Parameters

        + namespaceCode
            + code
            + name
            + active

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"namespaceCode": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"namespaceCode": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Components [GET /research-sys/api/v1/components/]
	                                          
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
    
            {"columns":["namespaceCode","code","name","active"],"primaryKey":"code:namespaceCode"}
		
### Get Blueprint API specification for Components [GET /research-sys/api/v1/components/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Components.md"
            transfer-encoding:chunked


### Update Components [PUT /research-sys/api/v1/components/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"namespaceCode": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Components [PUT /research-sys/api/v1/components/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"namespaceCode": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"namespaceCode": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Components [POST /research-sys/api/v1/components/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"namespaceCode": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"namespaceCode": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Components [POST /research-sys/api/v1/components/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"namespaceCode": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"namespaceCode": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"namespaceCode": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"namespaceCode": "(val)","code": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Components by Key [DELETE /research-sys/api/v1/components/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Components [DELETE /research-sys/api/v1/components/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Components with Matching [DELETE /research-sys/api/v1/components/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + namespaceCode
            + code
            + name
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
