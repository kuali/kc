## Protocol Correspondence Templates [/research-sys/api/v1/protocol-correspondence-templates/]

### Get Protocol Correspondence Templates by Key [GET /research-sys/api/v1/protocol-correspondence-templates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protoCorrespTemplId": "(val)","protoCorrespTypeCode": "(val)","committeeId": "(val)","fileName": "(val)","correspondenceTemplate": "(val)","_primaryKey": "(val)"}

### Get All Protocol Correspondence Templates [GET /research-sys/api/v1/protocol-correspondence-templates/]
	 
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

### Get All Protocol Correspondence Templates with Filtering [GET /research-sys/api/v1/protocol-correspondence-templates/]
    
+ Parameters

        + protoCorrespTemplId
            + protoCorrespTypeCode
            + committeeId
            + fileName
            + correspondenceTemplate

            
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
			
### Get Schema for Protocol Correspondence Templates [GET /research-sys/api/v1/protocol-correspondence-templates/]
	                                          
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
    
            {"columns":["protoCorrespTemplId","protoCorrespTypeCode","committeeId","fileName","correspondenceTemplate"],"primaryKey":"protoCorrespTemplId"}
		
### Get Blueprint API specification for Protocol Correspondence Templates [GET /research-sys/api/v1/protocol-correspondence-templates/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Correspondence Templates.md"
            transfer-encoding:chunked


### Update Protocol Correspondence Templates [PUT /research-sys/api/v1/protocol-correspondence-templates/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protoCorrespTemplId": "(val)","protoCorrespTypeCode": "(val)","committeeId": "(val)","fileName": "(val)","correspondenceTemplate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Correspondence Templates [PUT /research-sys/api/v1/protocol-correspondence-templates/]

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

### Insert Protocol Correspondence Templates [POST /research-sys/api/v1/protocol-correspondence-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protoCorrespTemplId": "(val)","protoCorrespTypeCode": "(val)","committeeId": "(val)","fileName": "(val)","correspondenceTemplate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protoCorrespTemplId": "(val)","protoCorrespTypeCode": "(val)","committeeId": "(val)","fileName": "(val)","correspondenceTemplate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Correspondence Templates [POST /research-sys/api/v1/protocol-correspondence-templates/]

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
            
### Delete Protocol Correspondence Templates by Key [DELETE /research-sys/api/v1/protocol-correspondence-templates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Correspondence Templates [DELETE /research-sys/api/v1/protocol-correspondence-templates/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Correspondence Templates with Matching [DELETE /research-sys/api/v1/protocol-correspondence-templates/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + protoCorrespTemplId
            + protoCorrespTypeCode
            + committeeId
            + fileName
            + correspondenceTemplate

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
