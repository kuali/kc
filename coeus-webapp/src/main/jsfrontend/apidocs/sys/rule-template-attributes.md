## Rule Template Attributes [/research-sys/api/v1/rule-template-attributes/]

### Get Rule Template Attributes by Key [GET /research-sys/api/v1/rule-template-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","ruleAttributeId": "(val)","required": "(val)","active": "(val)","displayOrder": "(val)","defaultValue": "(val)","_primaryKey": "(val)"}

### Get All Rule Template Attributes [GET /research-sys/api/v1/rule-template-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","ruleAttributeId": "(val)","required": "(val)","active": "(val)","displayOrder": "(val)","defaultValue": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","ruleAttributeId": "(val)","required": "(val)","active": "(val)","displayOrder": "(val)","defaultValue": "(val)","_primaryKey": "(val)"}
            ]

### Get All Rule Template Attributes with Filtering [GET /research-sys/api/v1/rule-template-attributes/]
    
+ Parameters

    + id (optional) - Id. Maximum length is 30.
    + ruleAttributeId (optional) - Rule Attribute Id.
    + required (optional) - Required. Maximum length is 1.
    + active (optional) - Active Indicator. Maximum length is 1.
    + displayOrder (optional) - Display Order.
    + defaultValue (optional) - Default Value.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","ruleAttributeId": "(val)","required": "(val)","active": "(val)","displayOrder": "(val)","defaultValue": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","ruleAttributeId": "(val)","required": "(val)","active": "(val)","displayOrder": "(val)","defaultValue": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Rule Template Attributes [GET /research-sys/api/v1/rule-template-attributes/]
	                                          
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
    
            {"columns":["id","ruleAttributeId","required","active","displayOrder","defaultValue"],"primaryKey":"id"}
		
### Get Blueprint API specification for Rule Template Attributes [GET /research-sys/api/v1/rule-template-attributes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Rule Template Attributes.md"
            transfer-encoding:chunked


### Update Rule Template Attributes [PUT /research-sys/api/v1/rule-template-attributes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","ruleAttributeId": "(val)","required": "(val)","active": "(val)","displayOrder": "(val)","defaultValue": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Rule Template Attributes [PUT /research-sys/api/v1/rule-template-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","ruleAttributeId": "(val)","required": "(val)","active": "(val)","displayOrder": "(val)","defaultValue": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","ruleAttributeId": "(val)","required": "(val)","active": "(val)","displayOrder": "(val)","defaultValue": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Rule Template Attributes [POST /research-sys/api/v1/rule-template-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","ruleAttributeId": "(val)","required": "(val)","active": "(val)","displayOrder": "(val)","defaultValue": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","ruleAttributeId": "(val)","required": "(val)","active": "(val)","displayOrder": "(val)","defaultValue": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Rule Template Attributes [POST /research-sys/api/v1/rule-template-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","ruleAttributeId": "(val)","required": "(val)","active": "(val)","displayOrder": "(val)","defaultValue": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","ruleAttributeId": "(val)","required": "(val)","active": "(val)","displayOrder": "(val)","defaultValue": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","ruleAttributeId": "(val)","required": "(val)","active": "(val)","displayOrder": "(val)","defaultValue": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","ruleAttributeId": "(val)","required": "(val)","active": "(val)","displayOrder": "(val)","defaultValue": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Rule Template Attributes by Key [DELETE /research-sys/api/v1/rule-template-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rule Template Attributes [DELETE /research-sys/api/v1/rule-template-attributes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rule Template Attributes with Matching [DELETE /research-sys/api/v1/rule-template-attributes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Id. Maximum length is 30.
    + ruleAttributeId (optional) - Rule Attribute Id.
    + required (optional) - Required. Maximum length is 1.
    + active (optional) - Active Indicator. Maximum length is 1.
    + displayOrder (optional) - Display Order.
    + defaultValue (optional) - Default Value.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
