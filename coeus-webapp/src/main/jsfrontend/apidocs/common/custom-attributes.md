## Custom Attributes [/research-sys/api/v1/custom-attributes/]

### Get Custom Attributes by Key [GET /research-sys/api/v1/custom-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"}

### Get All Custom Attributes [GET /research-sys/api/v1/custom-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"}
            ]

### Get All Custom Attributes with Filtering [GET /research-sys/api/v1/custom-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + dataLength
            + dataTypeCode
            + defaultValue
            + groupName
            + label
            + lookupClass
            + lookupReturn
            + name
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Custom Attributes [GET /research-sys/api/v1/custom-attributes/]
	 
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
		
### Get Blueprint API specification for Custom Attributes [GET /research-sys/api/v1/custom-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Custom Attributes.md"
            transfer-encoding:chunked


### Update Custom Attributes [PUT /research-sys/api/v1/custom-attributes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Custom Attributes [PUT /research-sys/api/v1/custom-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Custom Attributes [POST /research-sys/api/v1/custom-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Custom Attributes [POST /research-sys/api/v1/custom-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Custom Attributes by Key [DELETE /research-sys/api/v1/custom-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Custom Attributes [DELETE /research-sys/api/v1/custom-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Custom Attributes with Matching [DELETE /research-sys/api/v1/custom-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + dataLength
            + dataTypeCode
            + defaultValue
            + groupName
            + label
            + lookupClass
            + lookupReturn
            + name


+ Response 204
