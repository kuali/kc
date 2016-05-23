## Iacuc Protocol Locations [/iacuc/api/v1/iacuc-protocol-locations/]

### Get Iacuc Protocol Locations by Key [GET /iacuc/api/v1/iacuc-protocol-locations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolLocationId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolOrganizationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Locations [GET /iacuc/api/v1/iacuc-protocol-locations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolLocationId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolOrganizationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"protocolLocationId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolOrganizationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Locations with Filtering [GET /iacuc/api/v1/iacuc-protocol-locations/]
    
+ Parameters

    + protocolLocationId (optional) - Protocol Location Id. Maximum length is 22.
    + protocolId (optional) - 
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + protocolOrganizationTypeCode (optional) - Protocol Organization Type Code. Maximum length is 22.
    + organizationId (optional) - Organization Id. Maximum length is 8.
    + rolodexId (optional) - Contact - Rolodex Id. Maximum length is 22.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolLocationId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolOrganizationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"protocolLocationId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolOrganizationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Locations [GET /iacuc/api/v1/iacuc-protocol-locations/]
	                                          
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
    
            {"columns":["protocolLocationId","protocolId","protocolNumber","sequenceNumber","protocolOrganizationTypeCode","organizationId","rolodexId"],"primaryKey":"protocolLocationId"}
		
### Get Blueprint API specification for Iacuc Protocol Locations [GET /iacuc/api/v1/iacuc-protocol-locations/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Locations.md"
            transfer-encoding:chunked
### Update Iacuc Protocol Locations [PUT /iacuc/api/v1/iacuc-protocol-locations/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolLocationId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolOrganizationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Locations [PUT /iacuc/api/v1/iacuc-protocol-locations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolLocationId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolOrganizationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"protocolLocationId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolOrganizationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Iacuc Protocol Locations [POST /iacuc/api/v1/iacuc-protocol-locations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolLocationId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolOrganizationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolLocationId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolOrganizationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Locations [POST /iacuc/api/v1/iacuc-protocol-locations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolLocationId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolOrganizationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"protocolLocationId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolOrganizationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolLocationId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolOrganizationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"protocolLocationId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolOrganizationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]
### Delete Iacuc Protocol Locations by Key [DELETE /iacuc/api/v1/iacuc-protocol-locations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Locations [DELETE /iacuc/api/v1/iacuc-protocol-locations/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Locations with Matching [DELETE /iacuc/api/v1/iacuc-protocol-locations/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolLocationId (optional) - Protocol Location Id. Maximum length is 22.
    + protocolId (optional) - 
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + protocolOrganizationTypeCode (optional) - Protocol Organization Type Code. Maximum length is 22.
    + organizationId (optional) - Organization Id. Maximum length is 8.
    + rolodexId (optional) - Contact - Rolodex Id. Maximum length is 22.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
