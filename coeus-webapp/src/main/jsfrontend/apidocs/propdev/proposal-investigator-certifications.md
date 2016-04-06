## Proposal Investigator Certifications [/research-sys/api/v1/proposal-investigator-certifications/]

### Get Proposal Investigator Certifications by Key [GET /research-sys/api/v1/proposal-investigator-certifications/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalPersonNumber": "(val)","proposalNumber": "(val)","certified": "(val)","dateCertified": "(val)","dateReceivedByOsp": "(val)","_primaryKey": "(val)"}

### Get All Proposal Investigator Certifications [GET /research-sys/api/v1/proposal-investigator-certifications/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalPersonNumber": "(val)","proposalNumber": "(val)","certified": "(val)","dateCertified": "(val)","dateReceivedByOsp": "(val)","_primaryKey": "(val)"},
              {"proposalPersonNumber": "(val)","proposalNumber": "(val)","certified": "(val)","dateCertified": "(val)","dateReceivedByOsp": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Investigator Certifications with Filtering [GET /research-sys/api/v1/proposal-investigator-certifications/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + proposalPersonNumber
            + proposalNumber
            + certified
            + dateCertified
            + dateReceivedByOsp
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalPersonNumber": "(val)","proposalNumber": "(val)","certified": "(val)","dateCertified": "(val)","dateReceivedByOsp": "(val)","_primaryKey": "(val)"},
              {"proposalPersonNumber": "(val)","proposalNumber": "(val)","certified": "(val)","dateCertified": "(val)","dateReceivedByOsp": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Investigator Certifications [GET /research-sys/api/v1/proposal-investigator-certifications/]
	 
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
		
### Get Blueprint API specification for Proposal Investigator Certifications [GET /research-sys/api/v1/proposal-investigator-certifications/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Investigator Certifications.md"
            transfer-encoding:chunked


### Update Proposal Investigator Certifications [PUT /research-sys/api/v1/proposal-investigator-certifications/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalPersonNumber": "(val)","proposalNumber": "(val)","certified": "(val)","dateCertified": "(val)","dateReceivedByOsp": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Investigator Certifications [PUT /research-sys/api/v1/proposal-investigator-certifications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalPersonNumber": "(val)","proposalNumber": "(val)","certified": "(val)","dateCertified": "(val)","dateReceivedByOsp": "(val)","_primaryKey": "(val)"},
              {"proposalPersonNumber": "(val)","proposalNumber": "(val)","certified": "(val)","dateCertified": "(val)","dateReceivedByOsp": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Proposal Investigator Certifications [POST /research-sys/api/v1/proposal-investigator-certifications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalPersonNumber": "(val)","proposalNumber": "(val)","certified": "(val)","dateCertified": "(val)","dateReceivedByOsp": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalPersonNumber": "(val)","proposalNumber": "(val)","certified": "(val)","dateCertified": "(val)","dateReceivedByOsp": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Investigator Certifications [POST /research-sys/api/v1/proposal-investigator-certifications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalPersonNumber": "(val)","proposalNumber": "(val)","certified": "(val)","dateCertified": "(val)","dateReceivedByOsp": "(val)","_primaryKey": "(val)"},
              {"proposalPersonNumber": "(val)","proposalNumber": "(val)","certified": "(val)","dateCertified": "(val)","dateReceivedByOsp": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalPersonNumber": "(val)","proposalNumber": "(val)","certified": "(val)","dateCertified": "(val)","dateReceivedByOsp": "(val)","_primaryKey": "(val)"},
              {"proposalPersonNumber": "(val)","proposalNumber": "(val)","certified": "(val)","dateCertified": "(val)","dateReceivedByOsp": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Proposal Investigator Certifications by Key [DELETE /research-sys/api/v1/proposal-investigator-certifications/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Investigator Certifications [DELETE /research-sys/api/v1/proposal-investigator-certifications/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Proposal Investigator Certifications with Matching [DELETE /research-sys/api/v1/proposal-investigator-certifications/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + proposalPersonNumber
            + proposalNumber
            + certified
            + dateCertified
            + dateReceivedByOsp


+ Response 204
