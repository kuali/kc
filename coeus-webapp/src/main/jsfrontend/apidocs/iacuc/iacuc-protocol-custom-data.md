## Iacuc Protocol Custom Data [/research-sys/api/v1/iacuc-protocol-custom-data/]

### Get Iacuc Protocol Custom Data by Key [GET /research-sys/api/v1/iacuc-protocol-custom-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"iacucProtocolCustomDataId": "(val)","protocolId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Custom Data [GET /research-sys/api/v1/iacuc-protocol-custom-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucProtocolCustomDataId": "(val)","protocolId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolCustomDataId": "(val)","protocolId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Custom Data with Filtering [GET /research-sys/api/v1/iacuc-protocol-custom-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + iacucProtocolCustomDataId
            + protocolId
            + customAttributeId
            + value
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucProtocolCustomDataId": "(val)","protocolId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolCustomDataId": "(val)","protocolId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Custom Data [GET /research-sys/api/v1/iacuc-protocol-custom-data/]
	 
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
		
### Get Blueprint API specification for Iacuc Protocol Custom Data [GET /research-sys/api/v1/iacuc-protocol-custom-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Custom Data.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Custom Data [PUT /research-sys/api/v1/iacuc-protocol-custom-data/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolCustomDataId": "(val)","protocolId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Custom Data [PUT /research-sys/api/v1/iacuc-protocol-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"iacucProtocolCustomDataId": "(val)","protocolId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolCustomDataId": "(val)","protocolId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Protocol Custom Data [POST /research-sys/api/v1/iacuc-protocol-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolCustomDataId": "(val)","protocolId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"iacucProtocolCustomDataId": "(val)","protocolId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Custom Data [POST /research-sys/api/v1/iacuc-protocol-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"iacucProtocolCustomDataId": "(val)","protocolId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolCustomDataId": "(val)","protocolId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"iacucProtocolCustomDataId": "(val)","protocolId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolCustomDataId": "(val)","protocolId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Protocol Custom Data by Key [DELETE /research-sys/api/v1/iacuc-protocol-custom-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Custom Data [DELETE /research-sys/api/v1/iacuc-protocol-custom-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Protocol Custom Data with Matching [DELETE /research-sys/api/v1/iacuc-protocol-custom-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + iacucProtocolCustomDataId
            + protocolId
            + customAttributeId
            + value


+ Response 204
