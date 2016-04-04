## Kc Krms Term Function Params [/research-sys/api/v1/kc-krms-term-function-params/]

### Get Kc Krms Term Function Params by Key [GET /research-sys/api/v1/kc-krms-term-function-params/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"kcKrmsTermFunctionParamId": "(val)","kcKrmsTermFunctionId": "(val)","paramName": "(val)","paramType": "(val)","paramOrder": "(val)","_primaryKey": "(val)"}

### Get All Kc Krms Term Function Params [GET /research-sys/api/v1/kc-krms-term-function-params/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"kcKrmsTermFunctionParamId": "(val)","kcKrmsTermFunctionId": "(val)","paramName": "(val)","paramType": "(val)","paramOrder": "(val)","_primaryKey": "(val)"},
              {"kcKrmsTermFunctionParamId": "(val)","kcKrmsTermFunctionId": "(val)","paramName": "(val)","paramType": "(val)","paramOrder": "(val)","_primaryKey": "(val)"}
            ]

### Get All Kc Krms Term Function Params with Filtering [GET /research-sys/api/v1/kc-krms-term-function-params/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + kcKrmsTermFunctionParamId
            + kcKrmsTermFunctionId
            + paramName
            + paramType
            + paramOrder
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"kcKrmsTermFunctionParamId": "(val)","kcKrmsTermFunctionId": "(val)","paramName": "(val)","paramType": "(val)","paramOrder": "(val)","_primaryKey": "(val)"},
              {"kcKrmsTermFunctionParamId": "(val)","kcKrmsTermFunctionId": "(val)","paramName": "(val)","paramType": "(val)","paramOrder": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Kc Krms Term Function Params [GET /research-sys/api/v1/kc-krms-term-function-params/]
	 
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
		
### Get Blueprint API specification for Kc Krms Term Function Params [GET /research-sys/api/v1/kc-krms-term-function-params/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Kc Krms Term Function Params.md"
            transfer-encoding:chunked


### Update Kc Krms Term Function Params [PUT /research-sys/api/v1/kc-krms-term-function-params/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"kcKrmsTermFunctionParamId": "(val)","kcKrmsTermFunctionId": "(val)","paramName": "(val)","paramType": "(val)","paramOrder": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Kc Krms Term Function Params [PUT /research-sys/api/v1/kc-krms-term-function-params/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"kcKrmsTermFunctionParamId": "(val)","kcKrmsTermFunctionId": "(val)","paramName": "(val)","paramType": "(val)","paramOrder": "(val)","_primaryKey": "(val)"},
              {"kcKrmsTermFunctionParamId": "(val)","kcKrmsTermFunctionId": "(val)","paramName": "(val)","paramType": "(val)","paramOrder": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Kc Krms Term Function Params [POST /research-sys/api/v1/kc-krms-term-function-params/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"kcKrmsTermFunctionParamId": "(val)","kcKrmsTermFunctionId": "(val)","paramName": "(val)","paramType": "(val)","paramOrder": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"kcKrmsTermFunctionParamId": "(val)","kcKrmsTermFunctionId": "(val)","paramName": "(val)","paramType": "(val)","paramOrder": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Kc Krms Term Function Params [POST /research-sys/api/v1/kc-krms-term-function-params/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"kcKrmsTermFunctionParamId": "(val)","kcKrmsTermFunctionId": "(val)","paramName": "(val)","paramType": "(val)","paramOrder": "(val)","_primaryKey": "(val)"},
              {"kcKrmsTermFunctionParamId": "(val)","kcKrmsTermFunctionId": "(val)","paramName": "(val)","paramType": "(val)","paramOrder": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"kcKrmsTermFunctionParamId": "(val)","kcKrmsTermFunctionId": "(val)","paramName": "(val)","paramType": "(val)","paramOrder": "(val)","_primaryKey": "(val)"},
              {"kcKrmsTermFunctionParamId": "(val)","kcKrmsTermFunctionId": "(val)","paramName": "(val)","paramType": "(val)","paramOrder": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Kc Krms Term Function Params by Key [DELETE /research-sys/api/v1/kc-krms-term-function-params/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kc Krms Term Function Params [DELETE /research-sys/api/v1/kc-krms-term-function-params/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Kc Krms Term Function Params with Matching [DELETE /research-sys/api/v1/kc-krms-term-function-params/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + kcKrmsTermFunctionParamId
            + kcKrmsTermFunctionId
            + paramName
            + paramType
            + paramOrder


+ Response 204
