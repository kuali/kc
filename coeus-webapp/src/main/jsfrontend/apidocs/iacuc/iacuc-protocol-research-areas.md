## Iacuc Protocol Research Areas [/iacuc/api/v1/iacuc-protocol-research-areas/]

### Get Iacuc Protocol Research Areas by Key [GET /iacuc/api/v1/iacuc-protocol-research-areas/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Research Areas [GET /iacuc/api/v1/iacuc-protocol-research-areas/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Research Areas with Filtering [GET /iacuc/api/v1/iacuc-protocol-research-areas/]
    
+ Parameters

    + id (optional) - 
    + protocolId (optional) - 
    + protocolNumber (optional) - 
    + sequenceNumber (optional) - 
    + researchAreaCode (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Research Areas [GET /iacuc/api/v1/iacuc-protocol-research-areas/]
	                                          
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
    
            {"columns":["id","protocolId","protocolNumber","sequenceNumber","researchAreaCode"],"primaryKey":"id"}
		
### Get Blueprint API specification for Iacuc Protocol Research Areas [GET /iacuc/api/v1/iacuc-protocol-research-areas/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Research Areas.md"
            transfer-encoding:chunked
### Update Iacuc Protocol Research Areas [PUT /iacuc/api/v1/iacuc-protocol-research-areas/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Research Areas [PUT /iacuc/api/v1/iacuc-protocol-research-areas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Iacuc Protocol Research Areas [POST /iacuc/api/v1/iacuc-protocol-research-areas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Research Areas [POST /iacuc/api/v1/iacuc-protocol-research-areas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}
            ]
### Delete Iacuc Protocol Research Areas by Key [DELETE /iacuc/api/v1/iacuc-protocol-research-areas/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Research Areas [DELETE /iacuc/api/v1/iacuc-protocol-research-areas/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Research Areas with Matching [DELETE /iacuc/api/v1/iacuc-protocol-research-areas/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + protocolId (optional) - 
    + protocolNumber (optional) - 
    + sequenceNumber (optional) - 
    + researchAreaCode (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
