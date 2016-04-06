## Kew Rule Attributes [/research-sys/api/v1/kew-rule-attributes/]

### Get Kew Rule Attributes by Key [GET /research-sys/api/v1/kew-rule-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","name": "(val)","label": "(val)","type": "(val)","resourceDescriptor": "(val)","description": "(val)","xmlConfigData": "(val)","applicationId": "(val)","_primaryKey": "(val)"}

### Get All Kew Rule Attributes [GET /research-sys/api/v1/kew-rule-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","label": "(val)","type": "(val)","resourceDescriptor": "(val)","description": "(val)","xmlConfigData": "(val)","applicationId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","label": "(val)","type": "(val)","resourceDescriptor": "(val)","description": "(val)","xmlConfigData": "(val)","applicationId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Kew Rule Attributes with Filtering [GET /research-sys/api/v1/kew-rule-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + name
            + label
            + type
            + resourceDescriptor
            + description
            + xmlConfigData
            + applicationId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","label": "(val)","type": "(val)","resourceDescriptor": "(val)","description": "(val)","xmlConfigData": "(val)","applicationId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","label": "(val)","type": "(val)","resourceDescriptor": "(val)","description": "(val)","xmlConfigData": "(val)","applicationId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Kew Rule Attributes [GET /research-sys/api/v1/kew-rule-attributes/]
	 
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
		
### Get Blueprint API specification for Kew Rule Attributes [GET /research-sys/api/v1/kew-rule-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Kew Rule Attributes.md"
            transfer-encoding:chunked


### Update Kew Rule Attributes [PUT /research-sys/api/v1/kew-rule-attributes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","label": "(val)","type": "(val)","resourceDescriptor": "(val)","description": "(val)","xmlConfigData": "(val)","applicationId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Kew Rule Attributes [PUT /research-sys/api/v1/kew-rule-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","label": "(val)","type": "(val)","resourceDescriptor": "(val)","description": "(val)","xmlConfigData": "(val)","applicationId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","label": "(val)","type": "(val)","resourceDescriptor": "(val)","description": "(val)","xmlConfigData": "(val)","applicationId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Kew Rule Attributes [POST /research-sys/api/v1/kew-rule-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","label": "(val)","type": "(val)","resourceDescriptor": "(val)","description": "(val)","xmlConfigData": "(val)","applicationId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","name": "(val)","label": "(val)","type": "(val)","resourceDescriptor": "(val)","description": "(val)","xmlConfigData": "(val)","applicationId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Kew Rule Attributes [POST /research-sys/api/v1/kew-rule-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","label": "(val)","type": "(val)","resourceDescriptor": "(val)","description": "(val)","xmlConfigData": "(val)","applicationId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","label": "(val)","type": "(val)","resourceDescriptor": "(val)","description": "(val)","xmlConfigData": "(val)","applicationId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","name": "(val)","label": "(val)","type": "(val)","resourceDescriptor": "(val)","description": "(val)","xmlConfigData": "(val)","applicationId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","label": "(val)","type": "(val)","resourceDescriptor": "(val)","description": "(val)","xmlConfigData": "(val)","applicationId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Kew Rule Attributes by Key [DELETE /research-sys/api/v1/kew-rule-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kew Rule Attributes [DELETE /research-sys/api/v1/kew-rule-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Kew Rule Attributes with Matching [DELETE /research-sys/api/v1/kew-rule-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + name
            + label
            + type
            + resourceDescriptor
            + description
            + xmlConfigData
            + applicationId


+ Response 204
