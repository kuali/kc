## Protocol Units [/irb/api/v1/protocol-units/]

### Get Protocol Units by Key [GET /irb/api/v1/protocol-units/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"}

### Get All Protocol Units [GET /irb/api/v1/protocol-units/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"},
              {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Units with Filtering [GET /irb/api/v1/protocol-units/]
    
+ Parameters

    + protocolUnitsId (optional) - Protocol Units Id. Maximum length is 12.
    + protocolPersonId (optional) - Protocol Person Id. Maximum length is 9.
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.
    + unitNumber (optional) - Unit Number. Maximum length is 8.
    + leadUnitFlag (optional) - Lead Unit Flag. Maximum length is 1.
    + personId (optional) - Person Id. Maximum length is 40.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"},
              {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Units [GET /irb/api/v1/protocol-units/]
	                                          
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
    
            {"columns":["protocolUnitsId","protocolPersonId","protocolNumber","sequenceNumber","unitNumber","leadUnitFlag","personId"],"primaryKey":"protocolUnitsId"}
		
### Get Blueprint API specification for Protocol Units [GET /irb/api/v1/protocol-units/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Units.md"
            transfer-encoding:chunked


### Update Protocol Units [PUT /irb/api/v1/protocol-units/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Units [PUT /irb/api/v1/protocol-units/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"},
              {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Protocol Units [POST /irb/api/v1/protocol-units/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Units [POST /irb/api/v1/protocol-units/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"},
              {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"},
              {"protocolUnitsId": "(val)","protocolPersonId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","unitNumber": "(val)","leadUnitFlag": "(val)","personId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Protocol Units by Key [DELETE /irb/api/v1/protocol-units/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Units [DELETE /irb/api/v1/protocol-units/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Units with Matching [DELETE /irb/api/v1/protocol-units/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolUnitsId (optional) - Protocol Units Id. Maximum length is 12.
    + protocolPersonId (optional) - Protocol Person Id. Maximum length is 9.
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.
    + unitNumber (optional) - Unit Number. Maximum length is 8.
    + leadUnitFlag (optional) - Lead Unit Flag. Maximum length is 1.
    + personId (optional) - Person Id. Maximum length is 40.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
