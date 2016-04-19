## Parameters [/research-sys/api/v1/parameters/]

### Get Parameters by Key [GET /research-sys/api/v1/parameters/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"namespaceCode": "(val)","componentCode": "(val)","name": "(val)","applicationId": "(val)","value": "(val)","description": "(val)","parameterTypeCode": "(val)","evaluationOperatorCode": "(val)","_primaryKey": "(val)"}

### Get All Parameters [GET /research-sys/api/v1/parameters/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"namespaceCode": "(val)","componentCode": "(val)","name": "(val)","applicationId": "(val)","value": "(val)","description": "(val)","parameterTypeCode": "(val)","evaluationOperatorCode": "(val)","_primaryKey": "(val)"},
              {"namespaceCode": "(val)","componentCode": "(val)","name": "(val)","applicationId": "(val)","value": "(val)","description": "(val)","parameterTypeCode": "(val)","evaluationOperatorCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Parameters with Filtering [GET /research-sys/api/v1/parameters/]
    
+ Parameters

    + namespaceCode (optional) - This value is used to categorize parameters by namespace. Maximum length is 20.
    + componentCode (optional) - Code identifying the Component. Maximum length is 100.
    + name (optional) - This will be used as the identifier for the parameter. Parameter                     values will be accessed using this field and the namespace as the key. Maximum length is 100.
    + applicationId (optional) - This will be used as the identifier to determine which application the parameter is used by. Maximum length is 20.
    + value (optional) - This field houses the actual value associated with the parameter. This                     is what's returned by the KualiConfigurationService. Maximum length is 4000.
    + description (optional) - This field houses the purpose of this parameter. Maximum length is 4000.
    + parameterTypeCode (optional) - Code identifying the parameter type. Maximum length is 5.
    + evaluationOperatorCode (optional) - evaluationOperatorCode description... Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"namespaceCode": "(val)","componentCode": "(val)","name": "(val)","applicationId": "(val)","value": "(val)","description": "(val)","parameterTypeCode": "(val)","evaluationOperatorCode": "(val)","_primaryKey": "(val)"},
              {"namespaceCode": "(val)","componentCode": "(val)","name": "(val)","applicationId": "(val)","value": "(val)","description": "(val)","parameterTypeCode": "(val)","evaluationOperatorCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Parameters [GET /research-sys/api/v1/parameters/]
	                                          
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
    
            {"columns":["namespaceCode","componentCode","name","applicationId","value","description","parameterTypeCode","evaluationOperatorCode"],"primaryKey":"applicationId:componentCode:name:namespaceCode"}
		
### Get Blueprint API specification for Parameters [GET /research-sys/api/v1/parameters/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Parameters.md"
            transfer-encoding:chunked


### Update Parameters [PUT /research-sys/api/v1/parameters/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"namespaceCode": "(val)","componentCode": "(val)","name": "(val)","applicationId": "(val)","value": "(val)","description": "(val)","parameterTypeCode": "(val)","evaluationOperatorCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Parameters [PUT /research-sys/api/v1/parameters/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"namespaceCode": "(val)","componentCode": "(val)","name": "(val)","applicationId": "(val)","value": "(val)","description": "(val)","parameterTypeCode": "(val)","evaluationOperatorCode": "(val)","_primaryKey": "(val)"},
              {"namespaceCode": "(val)","componentCode": "(val)","name": "(val)","applicationId": "(val)","value": "(val)","description": "(val)","parameterTypeCode": "(val)","evaluationOperatorCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Parameters [POST /research-sys/api/v1/parameters/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"namespaceCode": "(val)","componentCode": "(val)","name": "(val)","applicationId": "(val)","value": "(val)","description": "(val)","parameterTypeCode": "(val)","evaluationOperatorCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"namespaceCode": "(val)","componentCode": "(val)","name": "(val)","applicationId": "(val)","value": "(val)","description": "(val)","parameterTypeCode": "(val)","evaluationOperatorCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Parameters [POST /research-sys/api/v1/parameters/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"namespaceCode": "(val)","componentCode": "(val)","name": "(val)","applicationId": "(val)","value": "(val)","description": "(val)","parameterTypeCode": "(val)","evaluationOperatorCode": "(val)","_primaryKey": "(val)"},
              {"namespaceCode": "(val)","componentCode": "(val)","name": "(val)","applicationId": "(val)","value": "(val)","description": "(val)","parameterTypeCode": "(val)","evaluationOperatorCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"namespaceCode": "(val)","componentCode": "(val)","name": "(val)","applicationId": "(val)","value": "(val)","description": "(val)","parameterTypeCode": "(val)","evaluationOperatorCode": "(val)","_primaryKey": "(val)"},
              {"namespaceCode": "(val)","componentCode": "(val)","name": "(val)","applicationId": "(val)","value": "(val)","description": "(val)","parameterTypeCode": "(val)","evaluationOperatorCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Parameters by Key [DELETE /research-sys/api/v1/parameters/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Parameters [DELETE /research-sys/api/v1/parameters/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Parameters with Matching [DELETE /research-sys/api/v1/parameters/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + namespaceCode (optional) - This value is used to categorize parameters by namespace. Maximum length is 20.
    + componentCode (optional) - Code identifying the Component. Maximum length is 100.
    + name (optional) - This will be used as the identifier for the parameter. Parameter                     values will be accessed using this field and the namespace as the key. Maximum length is 100.
    + applicationId (optional) - This will be used as the identifier to determine which application the parameter is used by. Maximum length is 20.
    + value (optional) - This field houses the actual value associated with the parameter. This                     is what's returned by the KualiConfigurationService. Maximum length is 4000.
    + description (optional) - This field houses the purpose of this parameter. Maximum length is 4000.
    + parameterTypeCode (optional) - Code identifying the parameter type. Maximum length is 5.
    + evaluationOperatorCode (optional) - evaluationOperatorCode description... Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
