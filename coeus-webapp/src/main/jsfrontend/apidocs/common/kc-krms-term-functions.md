## Kc Krms Term Functions [/research-sys/api/v1/kc-krms-term-functions/]

### Get Kc Krms Term Functions by Key [GET /research-sys/api/v1/kc-krms-term-functions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"kcKrmsTermFunctionId": "(val)","krmsTermName": "(val)","description": "(val)","returnType": "(val)","functionType": "(val)","_primaryKey": "(val)"}

### Get All Kc Krms Term Functions [GET /research-sys/api/v1/kc-krms-term-functions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"kcKrmsTermFunctionId": "(val)","krmsTermName": "(val)","description": "(val)","returnType": "(val)","functionType": "(val)","_primaryKey": "(val)"},
              {"kcKrmsTermFunctionId": "(val)","krmsTermName": "(val)","description": "(val)","returnType": "(val)","functionType": "(val)","_primaryKey": "(val)"}
            ]

### Get All Kc Krms Term Functions with Filtering [GET /research-sys/api/v1/kc-krms-term-functions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + kcKrmsTermFunctionId
            + krmsTermName
            + description
            + returnType
            + functionType
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"kcKrmsTermFunctionId": "(val)","krmsTermName": "(val)","description": "(val)","returnType": "(val)","functionType": "(val)","_primaryKey": "(val)"},
              {"kcKrmsTermFunctionId": "(val)","krmsTermName": "(val)","description": "(val)","returnType": "(val)","functionType": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Kc Krms Term Functions [GET /research-sys/api/v1/kc-krms-term-functions/]
	 
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
		
### Get Blueprint API specification for Kc Krms Term Functions [GET /research-sys/api/v1/kc-krms-term-functions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Kc Krms Term Functions.md"
            transfer-encoding:chunked


### Update Kc Krms Term Functions [PUT /research-sys/api/v1/kc-krms-term-functions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"kcKrmsTermFunctionId": "(val)","krmsTermName": "(val)","description": "(val)","returnType": "(val)","functionType": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Kc Krms Term Functions [PUT /research-sys/api/v1/kc-krms-term-functions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"kcKrmsTermFunctionId": "(val)","krmsTermName": "(val)","description": "(val)","returnType": "(val)","functionType": "(val)","_primaryKey": "(val)"},
              {"kcKrmsTermFunctionId": "(val)","krmsTermName": "(val)","description": "(val)","returnType": "(val)","functionType": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Kc Krms Term Functions [POST /research-sys/api/v1/kc-krms-term-functions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"kcKrmsTermFunctionId": "(val)","krmsTermName": "(val)","description": "(val)","returnType": "(val)","functionType": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"kcKrmsTermFunctionId": "(val)","krmsTermName": "(val)","description": "(val)","returnType": "(val)","functionType": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Kc Krms Term Functions [POST /research-sys/api/v1/kc-krms-term-functions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"kcKrmsTermFunctionId": "(val)","krmsTermName": "(val)","description": "(val)","returnType": "(val)","functionType": "(val)","_primaryKey": "(val)"},
              {"kcKrmsTermFunctionId": "(val)","krmsTermName": "(val)","description": "(val)","returnType": "(val)","functionType": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"kcKrmsTermFunctionId": "(val)","krmsTermName": "(val)","description": "(val)","returnType": "(val)","functionType": "(val)","_primaryKey": "(val)"},
              {"kcKrmsTermFunctionId": "(val)","krmsTermName": "(val)","description": "(val)","returnType": "(val)","functionType": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Kc Krms Term Functions by Key [DELETE /research-sys/api/v1/kc-krms-term-functions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kc Krms Term Functions [DELETE /research-sys/api/v1/kc-krms-term-functions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Kc Krms Term Functions with Matching [DELETE /research-sys/api/v1/kc-krms-term-functions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + kcKrmsTermFunctionId
            + krmsTermName
            + description
            + returnType
            + functionType


+ Response 204
