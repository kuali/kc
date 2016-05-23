## Kew Attribute Definitions [/research-sys/api/v1/kew-attribute-definitions/]

### Get Kew Attribute Definitions by Key [GET /research-sys/api/v1/kew-attribute-definitions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","active": "(val)","componentName": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Kew Attribute Definitions [GET /research-sys/api/v1/kew-attribute-definitions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","active": "(val)","componentName": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","active": "(val)","componentName": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Kew Attribute Definitions with Filtering [GET /research-sys/api/v1/kew-attribute-definitions/]
    
+ Parameters

    + id (optional) - 
    + name (optional) - 
    + namespace (optional) - 
    + label (optional) - 
    + active (optional) - 
    + componentName (optional) - 
    + description (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","active": "(val)","componentName": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","active": "(val)","componentName": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Kew Attribute Definitions [GET /research-sys/api/v1/kew-attribute-definitions/]
	                                          
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
    
            {"columns":["id","name","namespace","label","active","componentName","description"],"primaryKey":"id"}
		
### Get Blueprint API specification for Kew Attribute Definitions [GET /research-sys/api/v1/kew-attribute-definitions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Kew Attribute Definitions.md"
            transfer-encoding:chunked
### Update Kew Attribute Definitions [PUT /research-sys/api/v1/kew-attribute-definitions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","active": "(val)","componentName": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Kew Attribute Definitions [PUT /research-sys/api/v1/kew-attribute-definitions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","active": "(val)","componentName": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","active": "(val)","componentName": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Kew Attribute Definitions [POST /research-sys/api/v1/kew-attribute-definitions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","active": "(val)","componentName": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","active": "(val)","componentName": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Kew Attribute Definitions [POST /research-sys/api/v1/kew-attribute-definitions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","active": "(val)","componentName": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","active": "(val)","componentName": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","active": "(val)","componentName": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespace": "(val)","label": "(val)","active": "(val)","componentName": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Kew Attribute Definitions by Key [DELETE /research-sys/api/v1/kew-attribute-definitions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kew Attribute Definitions [DELETE /research-sys/api/v1/kew-attribute-definitions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kew Attribute Definitions with Matching [DELETE /research-sys/api/v1/kew-attribute-definitions/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + name (optional) - 
    + namespace (optional) - 
    + label (optional) - 
    + active (optional) - 
    + componentName (optional) - 
    + description (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
