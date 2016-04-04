## Natural Language Template Attributes [/research-sys/api/v1/natural-language-template-attributes/]

### Get Natural Language Template Attributes by Key [GET /research-sys/api/v1/natural-language-template-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"naturalLanguageTemplateId": "(val)","value": "(val)","attributeDefinitionId": "(val)","id": "(val)","_primaryKey": "(val)"}

### Get All Natural Language Template Attributes [GET /research-sys/api/v1/natural-language-template-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"naturalLanguageTemplateId": "(val)","value": "(val)","attributeDefinitionId": "(val)","id": "(val)","_primaryKey": "(val)"},
              {"naturalLanguageTemplateId": "(val)","value": "(val)","attributeDefinitionId": "(val)","id": "(val)","_primaryKey": "(val)"}
            ]

### Get All Natural Language Template Attributes with Filtering [GET /research-sys/api/v1/natural-language-template-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + naturalLanguageTemplateId
            + value
            + attributeDefinitionId
            + id
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"naturalLanguageTemplateId": "(val)","value": "(val)","attributeDefinitionId": "(val)","id": "(val)","_primaryKey": "(val)"},
              {"naturalLanguageTemplateId": "(val)","value": "(val)","attributeDefinitionId": "(val)","id": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Natural Language Template Attributes [GET /research-sys/api/v1/natural-language-template-attributes/]
	 
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
		
### Get Blueprint API specification for Natural Language Template Attributes [GET /research-sys/api/v1/natural-language-template-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Natural Language Template Attributes.md"
            transfer-encoding:chunked


### Update Natural Language Template Attributes [PUT /research-sys/api/v1/natural-language-template-attributes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"naturalLanguageTemplateId": "(val)","value": "(val)","attributeDefinitionId": "(val)","id": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Natural Language Template Attributes [PUT /research-sys/api/v1/natural-language-template-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"naturalLanguageTemplateId": "(val)","value": "(val)","attributeDefinitionId": "(val)","id": "(val)","_primaryKey": "(val)"},
              {"naturalLanguageTemplateId": "(val)","value": "(val)","attributeDefinitionId": "(val)","id": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Natural Language Template Attributes [POST /research-sys/api/v1/natural-language-template-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"naturalLanguageTemplateId": "(val)","value": "(val)","attributeDefinitionId": "(val)","id": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"naturalLanguageTemplateId": "(val)","value": "(val)","attributeDefinitionId": "(val)","id": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Natural Language Template Attributes [POST /research-sys/api/v1/natural-language-template-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"naturalLanguageTemplateId": "(val)","value": "(val)","attributeDefinitionId": "(val)","id": "(val)","_primaryKey": "(val)"},
              {"naturalLanguageTemplateId": "(val)","value": "(val)","attributeDefinitionId": "(val)","id": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"naturalLanguageTemplateId": "(val)","value": "(val)","attributeDefinitionId": "(val)","id": "(val)","_primaryKey": "(val)"},
              {"naturalLanguageTemplateId": "(val)","value": "(val)","attributeDefinitionId": "(val)","id": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Natural Language Template Attributes by Key [DELETE /research-sys/api/v1/natural-language-template-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Natural Language Template Attributes [DELETE /research-sys/api/v1/natural-language-template-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Natural Language Template Attributes with Matching [DELETE /research-sys/api/v1/natural-language-template-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + naturalLanguageTemplateId
            + value
            + attributeDefinitionId
            + id


+ Response 204
