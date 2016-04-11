## Valid Iacuc Protocol Action Correspondence [/research-sys/api/v1/valid-iacuc-protocol-action-correspondence/]

### Get Valid Iacuc Protocol Action Correspondence by Key [GET /research-sys/api/v1/valid-iacuc-protocol-action-correspondence/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"validProtoActionCorespId": "(val)","protocolActionTypeCode": "(val)","protoCorrespTypeCode": "(val)","finalFlag": "(val)","_primaryKey": "(val)"}

### Get All Valid Iacuc Protocol Action Correspondence [GET /research-sys/api/v1/valid-iacuc-protocol-action-correspondence/]
	 
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

### Get All Valid Iacuc Protocol Action Correspondence with Filtering [GET /research-sys/api/v1/valid-iacuc-protocol-action-correspondence/]
    
+ Parameters

        + validProtoActionCorespId
            + protocolActionTypeCode
            + protoCorrespTypeCode
            + finalFlag

            
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
			
### Get Schema for Valid Iacuc Protocol Action Correspondence [GET /research-sys/api/v1/valid-iacuc-protocol-action-correspondence/]
	                                          
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
    
            {"columns":["validProtoActionCorespId","protocolActionTypeCode","protoCorrespTypeCode","finalFlag"],"primaryKey":"validProtoActionCorespId"}
		
### Get Blueprint API specification for Valid Iacuc Protocol Action Correspondence [GET /research-sys/api/v1/valid-iacuc-protocol-action-correspondence/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Valid Iacuc Protocol Action Correspondence.md"
            transfer-encoding:chunked


### Update Valid Iacuc Protocol Action Correspondence [PUT /research-sys/api/v1/valid-iacuc-protocol-action-correspondence/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validProtoActionCorespId": "(val)","protocolActionTypeCode": "(val)","protoCorrespTypeCode": "(val)","finalFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Valid Iacuc Protocol Action Correspondence [PUT /research-sys/api/v1/valid-iacuc-protocol-action-correspondence/]

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

### Insert Valid Iacuc Protocol Action Correspondence [POST /research-sys/api/v1/valid-iacuc-protocol-action-correspondence/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validProtoActionCorespId": "(val)","protocolActionTypeCode": "(val)","protoCorrespTypeCode": "(val)","finalFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"validProtoActionCorespId": "(val)","protocolActionTypeCode": "(val)","protoCorrespTypeCode": "(val)","finalFlag": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Valid Iacuc Protocol Action Correspondence [POST /research-sys/api/v1/valid-iacuc-protocol-action-correspondence/]

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
            
### Delete Valid Iacuc Protocol Action Correspondence by Key [DELETE /research-sys/api/v1/valid-iacuc-protocol-action-correspondence/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Iacuc Protocol Action Correspondence [DELETE /research-sys/api/v1/valid-iacuc-protocol-action-correspondence/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Iacuc Protocol Action Correspondence with Matching [DELETE /research-sys/api/v1/valid-iacuc-protocol-action-correspondence/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + validProtoActionCorespId
            + protocolActionTypeCode
            + protoCorrespTypeCode
            + finalFlag

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
