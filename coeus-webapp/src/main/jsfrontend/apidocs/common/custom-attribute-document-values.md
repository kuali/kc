## Custom Attribute Document Values [/research-common/api/v1/custom-attribute-document-values/]

### Get Custom Attribute Document Values by Key [GET /research-common/api/v1/custom-attribute-document-values/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","documentNumber": "(val)","value": "(val)","_primaryKey": "(val)"}

### Get All Custom Attribute Document Values [GET /research-common/api/v1/custom-attribute-document-values/]
	 
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

### Get All Custom Attribute Document Values with Filtering [GET /research-common/api/v1/custom-attribute-document-values/]
    
+ Parameters

    + id (optional) - 
    + documentNumber (optional) - 
    + value (optional) - 

            
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
			
### Get Schema for Custom Attribute Document Values [GET /research-common/api/v1/custom-attribute-document-values/]
	                                          
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
    
            {"columns":["id","documentNumber","value"],"primaryKey":"documentNumber:id"}
		
### Get Blueprint API specification for Custom Attribute Document Values [GET /research-common/api/v1/custom-attribute-document-values/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Custom Attribute Document Values.md"
            transfer-encoding:chunked


### Update Custom Attribute Document Values [PUT /research-common/api/v1/custom-attribute-document-values/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","documentNumber": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Custom Attribute Document Values [PUT /research-common/api/v1/custom-attribute-document-values/]

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

### Insert Custom Attribute Document Values [POST /research-common/api/v1/custom-attribute-document-values/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","documentNumber": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","documentNumber": "(val)","value": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Custom Attribute Document Values [POST /research-common/api/v1/custom-attribute-document-values/]

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
            
### Delete Custom Attribute Document Values by Key [DELETE /research-common/api/v1/custom-attribute-document-values/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Custom Attribute Document Values [DELETE /research-common/api/v1/custom-attribute-document-values/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Custom Attribute Document Values with Matching [DELETE /research-common/api/v1/custom-attribute-document-values/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + documentNumber (optional) - 
    + value (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
