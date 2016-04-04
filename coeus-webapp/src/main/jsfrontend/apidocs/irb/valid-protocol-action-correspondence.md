## Valid Protocol Action Correspondence [/research-sys/api/v1/valid-protocol-action-correspondence/]

### Get Valid Protocol Action Correspondence by Key [GET /research-sys/api/v1/valid-protocol-action-correspondence/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"validProtoActionCorespId": "(val)","protocolActionTypeCode": "(val)","protoCorrespTypeCode": "(val)","finalFlag": "(val)","_primaryKey": "(val)"}

### Get All Valid Protocol Action Correspondence [GET /research-sys/api/v1/valid-protocol-action-correspondence/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"validProtoActionCorespId": "(val)","protocolActionTypeCode": "(val)","protoCorrespTypeCode": "(val)","finalFlag": "(val)","_primaryKey": "(val)"},
              {"validProtoActionCorespId": "(val)","protocolActionTypeCode": "(val)","protoCorrespTypeCode": "(val)","finalFlag": "(val)","_primaryKey": "(val)"}
            ]

### Get All Valid Protocol Action Correspondence with Filtering [GET /research-sys/api/v1/valid-protocol-action-correspondence/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + validProtoActionCorespId
            + protocolActionTypeCode
            + protoCorrespTypeCode
            + finalFlag
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"validProtoActionCorespId": "(val)","protocolActionTypeCode": "(val)","protoCorrespTypeCode": "(val)","finalFlag": "(val)","_primaryKey": "(val)"},
              {"validProtoActionCorespId": "(val)","protocolActionTypeCode": "(val)","protoCorrespTypeCode": "(val)","finalFlag": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Valid Protocol Action Correspondence [GET /research-sys/api/v1/valid-protocol-action-correspondence/]
	 
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
		
### Get Blueprint API specification for Valid Protocol Action Correspondence [GET /research-sys/api/v1/valid-protocol-action-correspondence/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Valid Protocol Action Correspondence.md"
            transfer-encoding:chunked


### Update Valid Protocol Action Correspondence [PUT /research-sys/api/v1/valid-protocol-action-correspondence/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validProtoActionCorespId": "(val)","protocolActionTypeCode": "(val)","protoCorrespTypeCode": "(val)","finalFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Valid Protocol Action Correspondence [PUT /research-sys/api/v1/valid-protocol-action-correspondence/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"validProtoActionCorespId": "(val)","protocolActionTypeCode": "(val)","protoCorrespTypeCode": "(val)","finalFlag": "(val)","_primaryKey": "(val)"},
              {"validProtoActionCorespId": "(val)","protocolActionTypeCode": "(val)","protoCorrespTypeCode": "(val)","finalFlag": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Valid Protocol Action Correspondence [POST /research-sys/api/v1/valid-protocol-action-correspondence/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validProtoActionCorespId": "(val)","protocolActionTypeCode": "(val)","protoCorrespTypeCode": "(val)","finalFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"validProtoActionCorespId": "(val)","protocolActionTypeCode": "(val)","protoCorrespTypeCode": "(val)","finalFlag": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Valid Protocol Action Correspondence [POST /research-sys/api/v1/valid-protocol-action-correspondence/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"validProtoActionCorespId": "(val)","protocolActionTypeCode": "(val)","protoCorrespTypeCode": "(val)","finalFlag": "(val)","_primaryKey": "(val)"},
              {"validProtoActionCorespId": "(val)","protocolActionTypeCode": "(val)","protoCorrespTypeCode": "(val)","finalFlag": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"validProtoActionCorespId": "(val)","protocolActionTypeCode": "(val)","protoCorrespTypeCode": "(val)","finalFlag": "(val)","_primaryKey": "(val)"},
              {"validProtoActionCorespId": "(val)","protocolActionTypeCode": "(val)","protoCorrespTypeCode": "(val)","finalFlag": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Valid Protocol Action Correspondence by Key [DELETE /research-sys/api/v1/valid-protocol-action-correspondence/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Protocol Action Correspondence [DELETE /research-sys/api/v1/valid-protocol-action-correspondence/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Valid Protocol Action Correspondence with Matching [DELETE /research-sys/api/v1/valid-protocol-action-correspondence/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + validProtoActionCorespId
            + protocolActionTypeCode
            + protoCorrespTypeCode
            + finalFlag


+ Response 204
