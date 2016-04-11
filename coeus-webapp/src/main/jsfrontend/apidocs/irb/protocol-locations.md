## Protocol Locations [/research-sys/api/v1/protocol-locations/]

### Get Protocol Locations by Key [GET /research-sys/api/v1/protocol-locations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolLocationId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolOrganizationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}

### Get All Protocol Locations [GET /research-sys/api/v1/protocol-locations/]
	 
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

### Get All Protocol Locations with Filtering [GET /research-sys/api/v1/protocol-locations/]
    
+ Parameters

        + protocolLocationId
            + protocolId
            + protocolNumber
            + sequenceNumber
            + protocolOrganizationTypeCode
            + organizationId
            + rolodexId

            
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
			
### Get Schema for Protocol Locations [GET /research-sys/api/v1/protocol-locations/]
	                                          
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
		
### Get Blueprint API specification for Protocol Locations [GET /research-sys/api/v1/protocol-locations/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Locations.md"
            transfer-encoding:chunked


### Update Protocol Locations [PUT /research-sys/api/v1/protocol-locations/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolLocationId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolOrganizationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Locations [PUT /research-sys/api/v1/protocol-locations/]

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

### Insert Protocol Locations [POST /research-sys/api/v1/protocol-locations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolLocationId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolOrganizationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolLocationId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","protocolOrganizationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Locations [POST /research-sys/api/v1/protocol-locations/]

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
            
### Delete Protocol Locations by Key [DELETE /research-sys/api/v1/protocol-locations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Locations [DELETE /research-sys/api/v1/protocol-locations/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Locations with Matching [DELETE /research-sys/api/v1/protocol-locations/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + protocolLocationId
            + protocolId
            + protocolNumber
            + sequenceNumber
            + protocolOrganizationTypeCode
            + organizationId
            + rolodexId

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
