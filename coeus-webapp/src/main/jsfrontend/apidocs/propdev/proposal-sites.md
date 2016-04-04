## Proposal Sites [/research-sys/api/v1/proposal-sites/]

### Get Proposal Sites by Key [GET /research-sys/api/v1/proposal-sites/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}

### Get All Proposal Sites [GET /research-sys/api/v1/proposal-sites/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Sites with Filtering [GET /research-sys/api/v1/proposal-sites/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + siteNumber
            + locationName
            + locationTypeCode
            + organizationId
            + rolodexId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Sites [GET /research-sys/api/v1/proposal-sites/]
	 
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
		
### Get Blueprint API specification for Proposal Sites [GET /research-sys/api/v1/proposal-sites/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Sites.md"
            transfer-encoding:chunked


### Update Proposal Sites [PUT /research-sys/api/v1/proposal-sites/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Sites [PUT /research-sys/api/v1/proposal-sites/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Proposal Sites [POST /research-sys/api/v1/proposal-sites/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Sites [POST /research-sys/api/v1/proposal-sites/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Proposal Sites by Key [DELETE /research-sys/api/v1/proposal-sites/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Sites [DELETE /research-sys/api/v1/proposal-sites/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Proposal Sites with Matching [DELETE /research-sys/api/v1/proposal-sites/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + siteNumber
            + locationName
            + locationTypeCode
            + organizationId
            + rolodexId


+ Response 204
