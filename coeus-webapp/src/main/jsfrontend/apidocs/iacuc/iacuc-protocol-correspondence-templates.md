## Iacuc Protocol Correspondence Templates [/research-sys/api/v1/iacuc-protocol-correspondence-templates/]

### Get Iacuc Protocol Correspondence Templates by Key [GET /research-sys/api/v1/iacuc-protocol-correspondence-templates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protoCorrespTemplId": "(val)","protoCorrespTypeCode": "(val)","committeeId": "(val)","fileName": "(val)","correspondenceTemplate": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Correspondence Templates [GET /research-sys/api/v1/iacuc-protocol-correspondence-templates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protoCorrespTemplId": "(val)","protoCorrespTypeCode": "(val)","committeeId": "(val)","fileName": "(val)","correspondenceTemplate": "(val)","_primaryKey": "(val)"},
              {"protoCorrespTemplId": "(val)","protoCorrespTypeCode": "(val)","committeeId": "(val)","fileName": "(val)","correspondenceTemplate": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Correspondence Templates with Filtering [GET /research-sys/api/v1/iacuc-protocol-correspondence-templates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + protoCorrespTemplId
            + protoCorrespTypeCode
            + committeeId
            + fileName
            + correspondenceTemplate
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protoCorrespTemplId": "(val)","protoCorrespTypeCode": "(val)","committeeId": "(val)","fileName": "(val)","correspondenceTemplate": "(val)","_primaryKey": "(val)"},
              {"protoCorrespTemplId": "(val)","protoCorrespTypeCode": "(val)","committeeId": "(val)","fileName": "(val)","correspondenceTemplate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Correspondence Templates [GET /research-sys/api/v1/iacuc-protocol-correspondence-templates/]
	 
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
		
### Get Blueprint API specification for Iacuc Protocol Correspondence Templates [GET /research-sys/api/v1/iacuc-protocol-correspondence-templates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Correspondence Templates.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Correspondence Templates [PUT /research-sys/api/v1/iacuc-protocol-correspondence-templates/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protoCorrespTemplId": "(val)","protoCorrespTypeCode": "(val)","committeeId": "(val)","fileName": "(val)","correspondenceTemplate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Correspondence Templates [PUT /research-sys/api/v1/iacuc-protocol-correspondence-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protoCorrespTemplId": "(val)","protoCorrespTypeCode": "(val)","committeeId": "(val)","fileName": "(val)","correspondenceTemplate": "(val)","_primaryKey": "(val)"},
              {"protoCorrespTemplId": "(val)","protoCorrespTypeCode": "(val)","committeeId": "(val)","fileName": "(val)","correspondenceTemplate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Protocol Correspondence Templates [POST /research-sys/api/v1/iacuc-protocol-correspondence-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protoCorrespTemplId": "(val)","protoCorrespTypeCode": "(val)","committeeId": "(val)","fileName": "(val)","correspondenceTemplate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protoCorrespTemplId": "(val)","protoCorrespTypeCode": "(val)","committeeId": "(val)","fileName": "(val)","correspondenceTemplate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Correspondence Templates [POST /research-sys/api/v1/iacuc-protocol-correspondence-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protoCorrespTemplId": "(val)","protoCorrespTypeCode": "(val)","committeeId": "(val)","fileName": "(val)","correspondenceTemplate": "(val)","_primaryKey": "(val)"},
              {"protoCorrespTemplId": "(val)","protoCorrespTypeCode": "(val)","committeeId": "(val)","fileName": "(val)","correspondenceTemplate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protoCorrespTemplId": "(val)","protoCorrespTypeCode": "(val)","committeeId": "(val)","fileName": "(val)","correspondenceTemplate": "(val)","_primaryKey": "(val)"},
              {"protoCorrespTemplId": "(val)","protoCorrespTypeCode": "(val)","committeeId": "(val)","fileName": "(val)","correspondenceTemplate": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Protocol Correspondence Templates by Key [DELETE /research-sys/api/v1/iacuc-protocol-correspondence-templates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Correspondence Templates [DELETE /research-sys/api/v1/iacuc-protocol-correspondence-templates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Protocol Correspondence Templates with Matching [DELETE /research-sys/api/v1/iacuc-protocol-correspondence-templates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + protoCorrespTemplId
            + protoCorrespTypeCode
            + committeeId
            + fileName
            + correspondenceTemplate


+ Response 204
