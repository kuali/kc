## Kc Krms Term Functions [/research-common/api/v1/kc-krms-term-functions/]

### Get Kc Krms Term Functions by Key [GET /research-common/api/v1/kc-krms-term-functions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"kcKrmsTermFunctionId": "(val)","krmsTermName": "(val)","description": "(val)","returnType": "(val)","functionType": "(val)","_primaryKey": "(val)"}

### Get All Kc Krms Term Functions [GET /research-common/api/v1/kc-krms-term-functions/]
	 
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

### Get All Kc Krms Term Functions with Filtering [GET /research-common/api/v1/kc-krms-term-functions/]
    
+ Parameters

    + kcKrmsTermFunctionId (optional) - Kc Krms Term Function Id. Maximum length is 22.
    + krmsTermName (optional) - Krms Term Id. Maximum length is 20.
    + description (optional) - Description. Maximum length is 200.
    + returnType (optional) - Return Type. Maximum length is 100.
    + functionType (optional) - Function Type. Maximum length is 3.

            
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
			
### Get Schema for Kc Krms Term Functions [GET /research-common/api/v1/kc-krms-term-functions/]
	                                          
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
    
            {"columns":["kcKrmsTermFunctionId","krmsTermName","description","returnType","functionType"],"primaryKey":"kcKrmsTermFunctionId"}
		
### Get Blueprint API specification for Kc Krms Term Functions [GET /research-common/api/v1/kc-krms-term-functions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Kc Krms Term Functions.md"
            transfer-encoding:chunked


### Update Kc Krms Term Functions [PUT /research-common/api/v1/kc-krms-term-functions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"kcKrmsTermFunctionId": "(val)","krmsTermName": "(val)","description": "(val)","returnType": "(val)","functionType": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Kc Krms Term Functions [PUT /research-common/api/v1/kc-krms-term-functions/]

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

### Insert Kc Krms Term Functions [POST /research-common/api/v1/kc-krms-term-functions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"kcKrmsTermFunctionId": "(val)","krmsTermName": "(val)","description": "(val)","returnType": "(val)","functionType": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"kcKrmsTermFunctionId": "(val)","krmsTermName": "(val)","description": "(val)","returnType": "(val)","functionType": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Kc Krms Term Functions [POST /research-common/api/v1/kc-krms-term-functions/]

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
            
### Delete Kc Krms Term Functions by Key [DELETE /research-common/api/v1/kc-krms-term-functions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kc Krms Term Functions [DELETE /research-common/api/v1/kc-krms-term-functions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kc Krms Term Functions with Matching [DELETE /research-common/api/v1/kc-krms-term-functions/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + kcKrmsTermFunctionId (optional) - Kc Krms Term Function Id. Maximum length is 22.
    + krmsTermName (optional) - Krms Term Id. Maximum length is 20.
    + description (optional) - Description. Maximum length is 200.
    + returnType (optional) - Return Type. Maximum length is 100.
    + functionType (optional) - Function Type. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
