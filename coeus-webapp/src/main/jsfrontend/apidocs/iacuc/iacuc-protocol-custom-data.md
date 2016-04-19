## Iacuc Protocol Custom Data [/iacuc/api/v1/iacuc-protocol-custom-data/]

### Get Iacuc Protocol Custom Data by Key [GET /iacuc/api/v1/iacuc-protocol-custom-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"iacucProtocolCustomDataId": "(val)","protocolId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Custom Data [GET /iacuc/api/v1/iacuc-protocol-custom-data/]
	 
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

### Get All Iacuc Protocol Custom Data with Filtering [GET /iacuc/api/v1/iacuc-protocol-custom-data/]
    
+ Parameters

    + iacucProtocolCustomDataId (optional) - IACUC Protocol Custom Data Id. Maximum length is 22.
    + protocolId (optional) - IACUC Protocol Id. Maximum length is 22.
    + customAttributeId (optional) - Custom Attribute Id. Maximum length is 22.
    + value (optional) - Value. Maximum length is 2000.

            
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
			
### Get Schema for Iacuc Protocol Custom Data [GET /iacuc/api/v1/iacuc-protocol-custom-data/]
	                                          
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
    
            {"columns":["iacucProtocolCustomDataId","protocolId","customAttributeId","value"],"primaryKey":"iacucProtocolCustomDataId"}
		
### Get Blueprint API specification for Iacuc Protocol Custom Data [GET /iacuc/api/v1/iacuc-protocol-custom-data/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Custom Data.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Custom Data [PUT /iacuc/api/v1/iacuc-protocol-custom-data/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolCustomDataId": "(val)","protocolId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Custom Data [PUT /iacuc/api/v1/iacuc-protocol-custom-data/]

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

### Insert Iacuc Protocol Custom Data [POST /iacuc/api/v1/iacuc-protocol-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolCustomDataId": "(val)","protocolId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"iacucProtocolCustomDataId": "(val)","protocolId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Custom Data [POST /iacuc/api/v1/iacuc-protocol-custom-data/]

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
            
### Delete Iacuc Protocol Custom Data by Key [DELETE /iacuc/api/v1/iacuc-protocol-custom-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Custom Data [DELETE /iacuc/api/v1/iacuc-protocol-custom-data/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Custom Data with Matching [DELETE /iacuc/api/v1/iacuc-protocol-custom-data/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + iacucProtocolCustomDataId (optional) - IACUC Protocol Custom Data Id. Maximum length is 22.
    + protocolId (optional) - IACUC Protocol Id. Maximum length is 22.
    + customAttributeId (optional) - Custom Attribute Id. Maximum length is 22.
    + value (optional) - Value. Maximum length is 2000.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
