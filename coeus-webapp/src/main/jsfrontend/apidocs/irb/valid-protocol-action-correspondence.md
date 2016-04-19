## Valid Protocol Action Correspondence [/irb/api/v1/valid-protocol-action-correspondence/]

### Get Valid Protocol Action Correspondence by Key [GET /irb/api/v1/valid-protocol-action-correspondence/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"validProtoActionCorespId": "(val)","protocolActionTypeCode": "(val)","protoCorrespTypeCode": "(val)","finalFlag": "(val)","_primaryKey": "(val)"}

### Get All Valid Protocol Action Correspondence [GET /irb/api/v1/valid-protocol-action-correspondence/]
	 
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

### Get All Valid Protocol Action Correspondence with Filtering [GET /irb/api/v1/valid-protocol-action-correspondence/]
    
+ Parameters

    + validProtoActionCorespId (optional) - Valid Proto Action Coresp Id. Maximum length is 12.
    + protocolActionTypeCode (optional) - Protocol Action Type. Maximum length is 3.
    + protoCorrespTypeCode (optional) - Protocol Correspondence Type. Maximum length is 3.
    + finalFlag (optional) - Final Flag. Maximum length is 1.

            
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
			
### Get Schema for Valid Protocol Action Correspondence [GET /irb/api/v1/valid-protocol-action-correspondence/]
	                                          
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
		
### Get Blueprint API specification for Valid Protocol Action Correspondence [GET /irb/api/v1/valid-protocol-action-correspondence/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Valid Protocol Action Correspondence.md"
            transfer-encoding:chunked


### Update Valid Protocol Action Correspondence [PUT /irb/api/v1/valid-protocol-action-correspondence/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validProtoActionCorespId": "(val)","protocolActionTypeCode": "(val)","protoCorrespTypeCode": "(val)","finalFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Valid Protocol Action Correspondence [PUT /irb/api/v1/valid-protocol-action-correspondence/]

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

### Insert Valid Protocol Action Correspondence [POST /irb/api/v1/valid-protocol-action-correspondence/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validProtoActionCorespId": "(val)","protocolActionTypeCode": "(val)","protoCorrespTypeCode": "(val)","finalFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"validProtoActionCorespId": "(val)","protocolActionTypeCode": "(val)","protoCorrespTypeCode": "(val)","finalFlag": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Valid Protocol Action Correspondence [POST /irb/api/v1/valid-protocol-action-correspondence/]

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
            
### Delete Valid Protocol Action Correspondence by Key [DELETE /irb/api/v1/valid-protocol-action-correspondence/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Protocol Action Correspondence [DELETE /irb/api/v1/valid-protocol-action-correspondence/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Protocol Action Correspondence with Matching [DELETE /irb/api/v1/valid-protocol-action-correspondence/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + validProtoActionCorespId (optional) - Valid Proto Action Coresp Id. Maximum length is 12.
    + protocolActionTypeCode (optional) - Protocol Action Type. Maximum length is 3.
    + protoCorrespTypeCode (optional) - Protocol Correspondence Type. Maximum length is 3.
    + finalFlag (optional) - Final Flag. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
