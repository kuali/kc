## Protocol Amend Renew Modules [/research-sys/api/v1/protocol-amend-renew-modules/]

### Get Protocol Amend Renew Modules by Key [GET /research-sys/api/v1/protocol-amend-renew-modules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolAmendRenewModuleId": "(val)","protocolAmendRenewalNumber": "(val)","protocolAmendRenewalId": "(val)","protocolNumber": "(val)","protocolModuleTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Protocol Amend Renew Modules [GET /research-sys/api/v1/protocol-amend-renew-modules/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolAmendRenewModuleId": "(val)","protocolAmendRenewalNumber": "(val)","protocolAmendRenewalId": "(val)","protocolNumber": "(val)","protocolModuleTypeCode": "(val)","_primaryKey": "(val)"},
              {"protocolAmendRenewModuleId": "(val)","protocolAmendRenewalNumber": "(val)","protocolAmendRenewalId": "(val)","protocolNumber": "(val)","protocolModuleTypeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Amend Renew Modules with Filtering [GET /research-sys/api/v1/protocol-amend-renew-modules/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + protocolAmendRenewModuleId
            + protocolAmendRenewalNumber
            + protocolAmendRenewalId
            + protocolNumber
            + protocolModuleTypeCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolAmendRenewModuleId": "(val)","protocolAmendRenewalNumber": "(val)","protocolAmendRenewalId": "(val)","protocolNumber": "(val)","protocolModuleTypeCode": "(val)","_primaryKey": "(val)"},
              {"protocolAmendRenewModuleId": "(val)","protocolAmendRenewalNumber": "(val)","protocolAmendRenewalId": "(val)","protocolNumber": "(val)","protocolModuleTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Amend Renew Modules [GET /research-sys/api/v1/protocol-amend-renew-modules/]
	 
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
		
### Get Blueprint API specification for Protocol Amend Renew Modules [GET /research-sys/api/v1/protocol-amend-renew-modules/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Amend Renew Modules.md"
            transfer-encoding:chunked


### Update Protocol Amend Renew Modules [PUT /research-sys/api/v1/protocol-amend-renew-modules/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolAmendRenewModuleId": "(val)","protocolAmendRenewalNumber": "(val)","protocolAmendRenewalId": "(val)","protocolNumber": "(val)","protocolModuleTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Amend Renew Modules [PUT /research-sys/api/v1/protocol-amend-renew-modules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolAmendRenewModuleId": "(val)","protocolAmendRenewalNumber": "(val)","protocolAmendRenewalId": "(val)","protocolNumber": "(val)","protocolModuleTypeCode": "(val)","_primaryKey": "(val)"},
              {"protocolAmendRenewModuleId": "(val)","protocolAmendRenewalNumber": "(val)","protocolAmendRenewalId": "(val)","protocolNumber": "(val)","protocolModuleTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Protocol Amend Renew Modules [POST /research-sys/api/v1/protocol-amend-renew-modules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolAmendRenewModuleId": "(val)","protocolAmendRenewalNumber": "(val)","protocolAmendRenewalId": "(val)","protocolNumber": "(val)","protocolModuleTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolAmendRenewModuleId": "(val)","protocolAmendRenewalNumber": "(val)","protocolAmendRenewalId": "(val)","protocolNumber": "(val)","protocolModuleTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Amend Renew Modules [POST /research-sys/api/v1/protocol-amend-renew-modules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolAmendRenewModuleId": "(val)","protocolAmendRenewalNumber": "(val)","protocolAmendRenewalId": "(val)","protocolNumber": "(val)","protocolModuleTypeCode": "(val)","_primaryKey": "(val)"},
              {"protocolAmendRenewModuleId": "(val)","protocolAmendRenewalNumber": "(val)","protocolAmendRenewalId": "(val)","protocolNumber": "(val)","protocolModuleTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolAmendRenewModuleId": "(val)","protocolAmendRenewalNumber": "(val)","protocolAmendRenewalId": "(val)","protocolNumber": "(val)","protocolModuleTypeCode": "(val)","_primaryKey": "(val)"},
              {"protocolAmendRenewModuleId": "(val)","protocolAmendRenewalNumber": "(val)","protocolAmendRenewalId": "(val)","protocolNumber": "(val)","protocolModuleTypeCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Protocol Amend Renew Modules by Key [DELETE /research-sys/api/v1/protocol-amend-renew-modules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Amend Renew Modules [DELETE /research-sys/api/v1/protocol-amend-renew-modules/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Protocol Amend Renew Modules with Matching [DELETE /research-sys/api/v1/protocol-amend-renew-modules/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + protocolAmendRenewModuleId
            + protocolAmendRenewalNumber
            + protocolAmendRenewalId
            + protocolNumber
            + protocolModuleTypeCode


+ Response 204
