## Iacuc Protocol Correspondences [/iacuc/api/v1/iacuc-protocol-correspondences/]

### Get Iacuc Protocol Correspondences by Key [GET /iacuc/api/v1/iacuc-protocol-correspondences/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Correspondences [GET /iacuc/api/v1/iacuc-protocol-correspondences/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Correspondences with Filtering [GET /iacuc/api/v1/iacuc-protocol-correspondences/]
    
+ Parameters

    + id (optional) - 
    + protocolId (optional) - 
    + protocolNumber (optional) - 
    + sequenceNumber (optional) - 
    + actionId (optional) - 
    + actionIdFk (optional) - 
    + protoCorrespTypeCode (optional) - 
    + correspondence (optional) - 
    + finalFlag (optional) - 
    + createUser (optional) - 
    + createTimestamp (optional) - 
    + finalFlagTimestamp (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Correspondences [GET /iacuc/api/v1/iacuc-protocol-correspondences/]
	                                          
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
    
            {"columns":["id","protocolId","protocolNumber","sequenceNumber","actionId","actionIdFk","protoCorrespTypeCode","correspondence","finalFlag","createUser","createTimestamp","finalFlagTimestamp"],"primaryKey":"id"}
		
### Get Blueprint API specification for Iacuc Protocol Correspondences [GET /iacuc/api/v1/iacuc-protocol-correspondences/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Correspondences.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Correspondences [PUT /iacuc/api/v1/iacuc-protocol-correspondences/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Correspondences [PUT /iacuc/api/v1/iacuc-protocol-correspondences/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Protocol Correspondences [POST /iacuc/api/v1/iacuc-protocol-correspondences/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Correspondences [POST /iacuc/api/v1/iacuc-protocol-correspondences/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","actionId": "(val)","actionIdFk": "(val)","protoCorrespTypeCode": "(val)","correspondence": "(val)","finalFlag": "(val)","createUser": "(val)","createTimestamp": "(val)","finalFlagTimestamp": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Protocol Correspondences by Key [DELETE /iacuc/api/v1/iacuc-protocol-correspondences/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Correspondences [DELETE /iacuc/api/v1/iacuc-protocol-correspondences/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Correspondences with Matching [DELETE /iacuc/api/v1/iacuc-protocol-correspondences/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + protocolId (optional) - 
    + protocolNumber (optional) - 
    + sequenceNumber (optional) - 
    + actionId (optional) - 
    + actionIdFk (optional) - 
    + protoCorrespTypeCode (optional) - 
    + correspondence (optional) - 
    + finalFlag (optional) - 
    + createUser (optional) - 
    + createTimestamp (optional) - 
    + finalFlagTimestamp (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
