## Kc Krms Term Function Params [/research-common/api/v1/kc-krms-term-function-params/]

### Get Kc Krms Term Function Params by Key [GET /research-common/api/v1/kc-krms-term-function-params/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"kcKrmsTermFunctionParamId": "(val)","kcKrmsTermFunctionId": "(val)","paramName": "(val)","paramType": "(val)","paramOrder": "(val)","_primaryKey": "(val)"}

### Get All Kc Krms Term Function Params [GET /research-common/api/v1/kc-krms-term-function-params/]
	 
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

### Get All Kc Krms Term Function Params with Filtering [GET /research-common/api/v1/kc-krms-term-function-params/]
    
+ Parameters

    + kcKrmsTermFunctionParamId (optional) - Kc Krms Term Fun Param Spec Id. Maximum length is 22.
    + kcKrmsTermFunctionId (optional) - Kc Krms Term Function Id. Maximum length is 22.
    + paramName (optional) - Param Name. Maximum length is 40.
    + paramType (optional) - Param Type. Maximum length is 100.
    + paramOrder (optional) - Param Order. Maximum length is 22.

            
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
			
### Get Schema for Kc Krms Term Function Params [GET /research-common/api/v1/kc-krms-term-function-params/]
	                                          
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
    
            {"columns":["kcKrmsTermFunctionParamId","kcKrmsTermFunctionId","paramName","paramType","paramOrder"],"primaryKey":"kcKrmsTermFunctionParamId"}
		
### Get Blueprint API specification for Kc Krms Term Function Params [GET /research-common/api/v1/kc-krms-term-function-params/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Kc Krms Term Function Params.md"
            transfer-encoding:chunked


### Update Kc Krms Term Function Params [PUT /research-common/api/v1/kc-krms-term-function-params/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"kcKrmsTermFunctionParamId": "(val)","kcKrmsTermFunctionId": "(val)","paramName": "(val)","paramType": "(val)","paramOrder": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Kc Krms Term Function Params [PUT /research-common/api/v1/kc-krms-term-function-params/]

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

### Insert Kc Krms Term Function Params [POST /research-common/api/v1/kc-krms-term-function-params/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"kcKrmsTermFunctionParamId": "(val)","kcKrmsTermFunctionId": "(val)","paramName": "(val)","paramType": "(val)","paramOrder": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"kcKrmsTermFunctionParamId": "(val)","kcKrmsTermFunctionId": "(val)","paramName": "(val)","paramType": "(val)","paramOrder": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Kc Krms Term Function Params [POST /research-common/api/v1/kc-krms-term-function-params/]

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
            
### Delete Kc Krms Term Function Params by Key [DELETE /research-common/api/v1/kc-krms-term-function-params/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kc Krms Term Function Params [DELETE /research-common/api/v1/kc-krms-term-function-params/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kc Krms Term Function Params with Matching [DELETE /research-common/api/v1/kc-krms-term-function-params/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + kcKrmsTermFunctionParamId (optional) - Kc Krms Term Fun Param Spec Id. Maximum length is 22.
    + kcKrmsTermFunctionId (optional) - Kc Krms Term Function Id. Maximum length is 22.
    + paramName (optional) - Param Name. Maximum length is 40.
    + paramType (optional) - Param Type. Maximum length is 100.
    + paramOrder (optional) - Param Order. Maximum length is 22.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
