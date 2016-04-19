## Iacuc Protocol Amend Renew Modules [/iacuc/api/v1/iacuc-protocol-amend-renew-modules/]

### Get Iacuc Protocol Amend Renew Modules by Key [GET /iacuc/api/v1/iacuc-protocol-amend-renew-modules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolAmendRenewModuleId": "(val)","protocolAmendRenewalNumber": "(val)","protocolAmendRenewalId": "(val)","protocolNumber": "(val)","protocolModuleTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Amend Renew Modules [GET /iacuc/api/v1/iacuc-protocol-amend-renew-modules/]
	 
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

### Get All Iacuc Protocol Amend Renew Modules with Filtering [GET /iacuc/api/v1/iacuc-protocol-amend-renew-modules/]
    
+ Parameters

    + protocolAmendRenewModuleId (optional) - 
    + protocolAmendRenewalNumber (optional) - 
    + protocolAmendRenewalId (optional) - 
    + protocolNumber (optional) - 
    + protocolModuleTypeCode (optional) - 

            
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
			
### Get Schema for Iacuc Protocol Amend Renew Modules [GET /iacuc/api/v1/iacuc-protocol-amend-renew-modules/]
	                                          
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
    
            {"columns":["protocolAmendRenewModuleId","protocolAmendRenewalNumber","protocolAmendRenewalId","protocolNumber","protocolModuleTypeCode"],"primaryKey":"protocolAmendRenewModuleId"}
		
### Get Blueprint API specification for Iacuc Protocol Amend Renew Modules [GET /iacuc/api/v1/iacuc-protocol-amend-renew-modules/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Amend Renew Modules.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Amend Renew Modules [PUT /iacuc/api/v1/iacuc-protocol-amend-renew-modules/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolAmendRenewModuleId": "(val)","protocolAmendRenewalNumber": "(val)","protocolAmendRenewalId": "(val)","protocolNumber": "(val)","protocolModuleTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Amend Renew Modules [PUT /iacuc/api/v1/iacuc-protocol-amend-renew-modules/]

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

### Insert Iacuc Protocol Amend Renew Modules [POST /iacuc/api/v1/iacuc-protocol-amend-renew-modules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolAmendRenewModuleId": "(val)","protocolAmendRenewalNumber": "(val)","protocolAmendRenewalId": "(val)","protocolNumber": "(val)","protocolModuleTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolAmendRenewModuleId": "(val)","protocolAmendRenewalNumber": "(val)","protocolAmendRenewalId": "(val)","protocolNumber": "(val)","protocolModuleTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Amend Renew Modules [POST /iacuc/api/v1/iacuc-protocol-amend-renew-modules/]

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
            
### Delete Iacuc Protocol Amend Renew Modules by Key [DELETE /iacuc/api/v1/iacuc-protocol-amend-renew-modules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Amend Renew Modules [DELETE /iacuc/api/v1/iacuc-protocol-amend-renew-modules/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Amend Renew Modules with Matching [DELETE /iacuc/api/v1/iacuc-protocol-amend-renew-modules/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolAmendRenewModuleId (optional) - 
    + protocolAmendRenewalNumber (optional) - 
    + protocolAmendRenewalId (optional) - 
    + protocolNumber (optional) - 
    + protocolModuleTypeCode (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
