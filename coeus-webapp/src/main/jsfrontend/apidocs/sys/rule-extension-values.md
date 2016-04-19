## Rule Extension Values [/research-sys/api/v1/rule-extension-values/]

### Get Rule Extension Values by Key [GET /research-sys/api/v1/rule-extension-values/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"ruleExtensionValueId": "(val)","value": "(val)","key": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}

### Get All Rule Extension Values [GET /research-sys/api/v1/rule-extension-values/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"ruleExtensionValueId": "(val)","value": "(val)","key": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"ruleExtensionValueId": "(val)","value": "(val)","key": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]

### Get All Rule Extension Values with Filtering [GET /research-sys/api/v1/rule-extension-values/]
    
+ Parameters

    + ruleExtensionValueId (optional) - 
    + value (optional) - 
    + key (optional) - 
    + lockVerNbr (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"ruleExtensionValueId": "(val)","value": "(val)","key": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"ruleExtensionValueId": "(val)","value": "(val)","key": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Rule Extension Values [GET /research-sys/api/v1/rule-extension-values/]
	                                          
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
    
            {"columns":["ruleExtensionValueId","value","key","lockVerNbr"],"primaryKey":"ruleExtensionValueId"}
		
### Get Blueprint API specification for Rule Extension Values [GET /research-sys/api/v1/rule-extension-values/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Rule Extension Values.md"
            transfer-encoding:chunked


### Update Rule Extension Values [PUT /research-sys/api/v1/rule-extension-values/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"ruleExtensionValueId": "(val)","value": "(val)","key": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Rule Extension Values [PUT /research-sys/api/v1/rule-extension-values/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"ruleExtensionValueId": "(val)","value": "(val)","key": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"ruleExtensionValueId": "(val)","value": "(val)","key": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Rule Extension Values [POST /research-sys/api/v1/rule-extension-values/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"ruleExtensionValueId": "(val)","value": "(val)","key": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"ruleExtensionValueId": "(val)","value": "(val)","key": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Rule Extension Values [POST /research-sys/api/v1/rule-extension-values/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"ruleExtensionValueId": "(val)","value": "(val)","key": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"ruleExtensionValueId": "(val)","value": "(val)","key": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"ruleExtensionValueId": "(val)","value": "(val)","key": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"ruleExtensionValueId": "(val)","value": "(val)","key": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Rule Extension Values by Key [DELETE /research-sys/api/v1/rule-extension-values/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rule Extension Values [DELETE /research-sys/api/v1/rule-extension-values/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rule Extension Values with Matching [DELETE /research-sys/api/v1/rule-extension-values/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + ruleExtensionValueId (optional) - 
    + value (optional) - 
    + key (optional) - 
    + lockVerNbr (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
