## Rule Extensions [/research-sys/api/v1/rule-extensions/]

### Get Rule Extensions by Key [GET /research-sys/api/v1/rule-extensions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"ruleExtensionId": "(val)","ruleTemplateAttributeId": "(val)","ruleBaseValuesId": "(val)","_primaryKey": "(val)"}

### Get All Rule Extensions [GET /research-sys/api/v1/rule-extensions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"ruleExtensionId": "(val)","ruleTemplateAttributeId": "(val)","ruleBaseValuesId": "(val)","_primaryKey": "(val)"},
              {"ruleExtensionId": "(val)","ruleTemplateAttributeId": "(val)","ruleBaseValuesId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Rule Extensions with Filtering [GET /research-sys/api/v1/rule-extensions/]
    
+ Parameters

        + ruleExtensionId
            + ruleTemplateAttributeId
            + ruleBaseValuesId

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"ruleExtensionId": "(val)","ruleTemplateAttributeId": "(val)","ruleBaseValuesId": "(val)","_primaryKey": "(val)"},
              {"ruleExtensionId": "(val)","ruleTemplateAttributeId": "(val)","ruleBaseValuesId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Rule Extensions [GET /research-sys/api/v1/rule-extensions/]
	                                          
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
    
            {"columns":["ruleExtensionId","ruleTemplateAttributeId","ruleBaseValuesId"],"primaryKey":"ruleExtensionId"}
		
### Get Blueprint API specification for Rule Extensions [GET /research-sys/api/v1/rule-extensions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Rule Extensions.md"
            transfer-encoding:chunked


### Update Rule Extensions [PUT /research-sys/api/v1/rule-extensions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"ruleExtensionId": "(val)","ruleTemplateAttributeId": "(val)","ruleBaseValuesId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Rule Extensions [PUT /research-sys/api/v1/rule-extensions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"ruleExtensionId": "(val)","ruleTemplateAttributeId": "(val)","ruleBaseValuesId": "(val)","_primaryKey": "(val)"},
              {"ruleExtensionId": "(val)","ruleTemplateAttributeId": "(val)","ruleBaseValuesId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Rule Extensions [POST /research-sys/api/v1/rule-extensions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"ruleExtensionId": "(val)","ruleTemplateAttributeId": "(val)","ruleBaseValuesId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"ruleExtensionId": "(val)","ruleTemplateAttributeId": "(val)","ruleBaseValuesId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Rule Extensions [POST /research-sys/api/v1/rule-extensions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"ruleExtensionId": "(val)","ruleTemplateAttributeId": "(val)","ruleBaseValuesId": "(val)","_primaryKey": "(val)"},
              {"ruleExtensionId": "(val)","ruleTemplateAttributeId": "(val)","ruleBaseValuesId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"ruleExtensionId": "(val)","ruleTemplateAttributeId": "(val)","ruleBaseValuesId": "(val)","_primaryKey": "(val)"},
              {"ruleExtensionId": "(val)","ruleTemplateAttributeId": "(val)","ruleBaseValuesId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Rule Extensions by Key [DELETE /research-sys/api/v1/rule-extensions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rule Extensions [DELETE /research-sys/api/v1/rule-extensions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rule Extensions with Matching [DELETE /research-sys/api/v1/rule-extensions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + ruleExtensionId
            + ruleTemplateAttributeId
            + ruleBaseValuesId

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
