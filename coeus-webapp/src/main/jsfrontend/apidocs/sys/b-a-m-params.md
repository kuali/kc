## B A M Params [/research-sys/api/v1/b-a-m-params/]

### Get B A M Params by Key [GET /research-sys/api/v1/b-a-m-params/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"bamParamId": "(val)","param": "(val)","_primaryKey": "(val)"}

### Get All B A M Params [GET /research-sys/api/v1/b-a-m-params/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"bamParamId": "(val)","param": "(val)","_primaryKey": "(val)"},
              {"bamParamId": "(val)","param": "(val)","_primaryKey": "(val)"}
            ]

### Get All B A M Params with Filtering [GET /research-sys/api/v1/b-a-m-params/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + bamParamId
            + param
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"bamParamId": "(val)","param": "(val)","_primaryKey": "(val)"},
              {"bamParamId": "(val)","param": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for B A M Params [GET /research-sys/api/v1/b-a-m-params/]
	 
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
		
### Get Blueprint API specification for B A M Params [GET /research-sys/api/v1/b-a-m-params/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="B A M Params.md"
            transfer-encoding:chunked


### Update B A M Params [PUT /research-sys/api/v1/b-a-m-params/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"bamParamId": "(val)","param": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple B A M Params [PUT /research-sys/api/v1/b-a-m-params/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"bamParamId": "(val)","param": "(val)","_primaryKey": "(val)"},
              {"bamParamId": "(val)","param": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert B A M Params [POST /research-sys/api/v1/b-a-m-params/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"bamParamId": "(val)","param": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"bamParamId": "(val)","param": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple B A M Params [POST /research-sys/api/v1/b-a-m-params/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"bamParamId": "(val)","param": "(val)","_primaryKey": "(val)"},
              {"bamParamId": "(val)","param": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"bamParamId": "(val)","param": "(val)","_primaryKey": "(val)"},
              {"bamParamId": "(val)","param": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete B A M Params by Key [DELETE /research-sys/api/v1/b-a-m-params/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All B A M Params [DELETE /research-sys/api/v1/b-a-m-params/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All B A M Params with Matching [DELETE /research-sys/api/v1/b-a-m-params/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + bamParamId
            + param


+ Response 204
