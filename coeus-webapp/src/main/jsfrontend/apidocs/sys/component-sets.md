## Component Sets [/research-sys/api/v1/component-sets/]

### Get Component Sets by Key [GET /research-sys/api/v1/component-sets/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"componentSetId": "(val)","lastUpdateTimestamp": "(val)","checksum": "(val)","_primaryKey": "(val)"}

### Get All Component Sets [GET /research-sys/api/v1/component-sets/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"componentSetId": "(val)","lastUpdateTimestamp": "(val)","checksum": "(val)","_primaryKey": "(val)"},
              {"componentSetId": "(val)","lastUpdateTimestamp": "(val)","checksum": "(val)","_primaryKey": "(val)"}
            ]

### Get All Component Sets with Filtering [GET /research-sys/api/v1/component-sets/]
    
+ Parameters

    + componentSetId (optional) - 
    + lastUpdateTimestamp (optional) - 
    + checksum (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"componentSetId": "(val)","lastUpdateTimestamp": "(val)","checksum": "(val)","_primaryKey": "(val)"},
              {"componentSetId": "(val)","lastUpdateTimestamp": "(val)","checksum": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Component Sets [GET /research-sys/api/v1/component-sets/]
	                                          
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
    
            {"columns":["componentSetId","lastUpdateTimestamp","checksum"],"primaryKey":"componentSetId"}
		
### Get Blueprint API specification for Component Sets [GET /research-sys/api/v1/component-sets/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Component Sets.md"
            transfer-encoding:chunked


### Update Component Sets [PUT /research-sys/api/v1/component-sets/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"componentSetId": "(val)","lastUpdateTimestamp": "(val)","checksum": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Component Sets [PUT /research-sys/api/v1/component-sets/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"componentSetId": "(val)","lastUpdateTimestamp": "(val)","checksum": "(val)","_primaryKey": "(val)"},
              {"componentSetId": "(val)","lastUpdateTimestamp": "(val)","checksum": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Component Sets [POST /research-sys/api/v1/component-sets/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"componentSetId": "(val)","lastUpdateTimestamp": "(val)","checksum": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"componentSetId": "(val)","lastUpdateTimestamp": "(val)","checksum": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Component Sets [POST /research-sys/api/v1/component-sets/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"componentSetId": "(val)","lastUpdateTimestamp": "(val)","checksum": "(val)","_primaryKey": "(val)"},
              {"componentSetId": "(val)","lastUpdateTimestamp": "(val)","checksum": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"componentSetId": "(val)","lastUpdateTimestamp": "(val)","checksum": "(val)","_primaryKey": "(val)"},
              {"componentSetId": "(val)","lastUpdateTimestamp": "(val)","checksum": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Component Sets by Key [DELETE /research-sys/api/v1/component-sets/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Component Sets [DELETE /research-sys/api/v1/component-sets/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Component Sets with Matching [DELETE /research-sys/api/v1/component-sets/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + componentSetId (optional) - 
    + lastUpdateTimestamp (optional) - 
    + checksum (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
