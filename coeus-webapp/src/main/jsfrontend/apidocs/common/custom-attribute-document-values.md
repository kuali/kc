## Custom Attribute Document Values [/research-sys/api/v1/custom-attribute-document-values/]

### Get Custom Attribute Document Values by Key [GET /research-sys/api/v1/custom-attribute-document-values/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","documentNumber": "(val)","value": "(val)","_primaryKey": "(val)"}

### Get All Custom Attribute Document Values [GET /research-sys/api/v1/custom-attribute-document-values/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","documentNumber": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]

### Get All Custom Attribute Document Values with Filtering [GET /research-sys/api/v1/custom-attribute-document-values/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + documentNumber
            + value
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","documentNumber": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Custom Attribute Document Values [GET /research-sys/api/v1/custom-attribute-document-values/]
	 
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
		
### Get Blueprint API specification for Custom Attribute Document Values [GET /research-sys/api/v1/custom-attribute-document-values/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Custom Attribute Document Values.md"
            transfer-encoding:chunked


### Update Custom Attribute Document Values [PUT /research-sys/api/v1/custom-attribute-document-values/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","documentNumber": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Custom Attribute Document Values [PUT /research-sys/api/v1/custom-attribute-document-values/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","documentNumber": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Custom Attribute Document Values [POST /research-sys/api/v1/custom-attribute-document-values/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","documentNumber": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","documentNumber": "(val)","value": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Custom Attribute Document Values [POST /research-sys/api/v1/custom-attribute-document-values/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","documentNumber": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","documentNumber": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Custom Attribute Document Values by Key [DELETE /research-sys/api/v1/custom-attribute-document-values/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Custom Attribute Document Values [DELETE /research-sys/api/v1/custom-attribute-document-values/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Custom Attribute Document Values with Matching [DELETE /research-sys/api/v1/custom-attribute-document-values/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + documentNumber
            + value


+ Response 204
