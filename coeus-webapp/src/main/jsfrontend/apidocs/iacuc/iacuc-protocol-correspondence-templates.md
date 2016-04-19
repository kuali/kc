## Iacuc Protocol Correspondence Templates [/iacuc/api/v1/iacuc-protocol-correspondence-templates/]

### Get Iacuc Protocol Correspondence Templates by Key [GET /iacuc/api/v1/iacuc-protocol-correspondence-templates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protoCorrespTemplId": "(val)","protoCorrespTypeCode": "(val)","committeeId": "(val)","fileName": "(val)","correspondenceTemplate": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Correspondence Templates [GET /iacuc/api/v1/iacuc-protocol-correspondence-templates/]
	 
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

### Get All Iacuc Protocol Correspondence Templates with Filtering [GET /iacuc/api/v1/iacuc-protocol-correspondence-templates/]
    
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
			
### Get Schema for Iacuc Protocol Correspondence Templates [GET /iacuc/api/v1/iacuc-protocol-correspondence-templates/]
	                                          
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
		
### Get Blueprint API specification for Iacuc Protocol Correspondence Templates [GET /iacuc/api/v1/iacuc-protocol-correspondence-templates/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Correspondence Templates.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Correspondence Templates [PUT /iacuc/api/v1/iacuc-protocol-correspondence-templates/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protoCorrespTemplId": "(val)","protoCorrespTypeCode": "(val)","committeeId": "(val)","fileName": "(val)","correspondenceTemplate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Correspondence Templates [PUT /iacuc/api/v1/iacuc-protocol-correspondence-templates/]

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

### Insert Iacuc Protocol Correspondence Templates [POST /iacuc/api/v1/iacuc-protocol-correspondence-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protoCorrespTemplId": "(val)","protoCorrespTypeCode": "(val)","committeeId": "(val)","fileName": "(val)","correspondenceTemplate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protoCorrespTemplId": "(val)","protoCorrespTypeCode": "(val)","committeeId": "(val)","fileName": "(val)","correspondenceTemplate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Correspondence Templates [POST /iacuc/api/v1/iacuc-protocol-correspondence-templates/]

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
            
### Delete Iacuc Protocol Correspondence Templates by Key [DELETE /iacuc/api/v1/iacuc-protocol-correspondence-templates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Correspondence Templates [DELETE /iacuc/api/v1/iacuc-protocol-correspondence-templates/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Correspondence Templates with Matching [DELETE /iacuc/api/v1/iacuc-protocol-correspondence-templates/]

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
