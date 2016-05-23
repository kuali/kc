## Protocol Correspondence Templates [/irb/api/v1/protocol-correspondence-templates/]

### Get Protocol Correspondence Templates by Key [GET /irb/api/v1/protocol-correspondence-templates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protoCorrespTemplId": "(val)","protoCorrespTypeCode": "(val)","committeeId": "(val)","fileName": "(val)","correspondenceTemplate": "(val)","_primaryKey": "(val)"}

### Get All Protocol Correspondence Templates [GET /irb/api/v1/protocol-correspondence-templates/]
	 
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

### Get All Protocol Correspondence Templates with Filtering [GET /irb/api/v1/protocol-correspondence-templates/]
    
+ Parameters

    + protoCorrespTemplId (optional) - Proto Corresp Templ Id. Maximum length is 12.
    + protoCorrespTypeCode (optional) - Proto Corresp Type Code. Maximum length is 3.
    + committeeId (optional) - Committee. Maximum length is 15.
    + fileName (optional) - File. Maximum length is 150.
    + correspondenceTemplate (optional) - Correspondence Template. Maximum length is 4000.

            
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
			
### Get Schema for Protocol Correspondence Templates [GET /irb/api/v1/protocol-correspondence-templates/]
	                                          
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
		
### Get Blueprint API specification for Protocol Correspondence Templates [GET /irb/api/v1/protocol-correspondence-templates/]
	 
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
### Update Protocol Correspondence Templates [PUT /irb/api/v1/protocol-correspondence-templates/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protoCorrespTemplId": "(val)","protoCorrespTypeCode": "(val)","committeeId": "(val)","fileName": "(val)","correspondenceTemplate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Correspondence Templates [PUT /irb/api/v1/protocol-correspondence-templates/]

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
### Insert Protocol Correspondence Templates [POST /irb/api/v1/protocol-correspondence-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protoCorrespTemplId": "(val)","protoCorrespTypeCode": "(val)","committeeId": "(val)","fileName": "(val)","correspondenceTemplate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protoCorrespTemplId": "(val)","protoCorrespTypeCode": "(val)","committeeId": "(val)","fileName": "(val)","correspondenceTemplate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Correspondence Templates [POST /irb/api/v1/protocol-correspondence-templates/]

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
### Delete Protocol Correspondence Templates by Key [DELETE /irb/api/v1/protocol-correspondence-templates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Correspondence Templates [DELETE /irb/api/v1/protocol-correspondence-templates/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Correspondence Templates with Matching [DELETE /irb/api/v1/protocol-correspondence-templates/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protoCorrespTemplId (optional) - Proto Corresp Templ Id. Maximum length is 12.
    + protoCorrespTypeCode (optional) - Proto Corresp Type Code. Maximum length is 3.
    + committeeId (optional) - Committee. Maximum length is 15.
    + fileName (optional) - File. Maximum length is 150.
    + correspondenceTemplate (optional) - Correspondence Template. Maximum length is 4000.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
