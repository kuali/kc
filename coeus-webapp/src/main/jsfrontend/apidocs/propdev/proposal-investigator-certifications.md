## Proposal Investigator Certifications [/propdev/api/v1/proposal-investigator-certifications/]

### Get Proposal Investigator Certifications by Key [GET /propdev/api/v1/proposal-investigator-certifications/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalPersonNumber": "(val)","proposalNumber": "(val)","certified": "(val)","dateCertified": "(val)","dateReceivedByOsp": "(val)","_primaryKey": "(val)"}

### Get All Proposal Investigator Certifications [GET /propdev/api/v1/proposal-investigator-certifications/]
	 
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

### Get All Proposal Investigator Certifications with Filtering [GET /propdev/api/v1/proposal-investigator-certifications/]
    
+ Parameters

    + proposalPersonNumber (optional) - Proposal Person Number. Maximum length is 40.
    + proposalNumber (optional) - Proposal Number. Maximum length is 40.
    + certified (optional) - Is Certified. Maximum length is 1.
    + dateCertified (optional) - Date Certified. Maximum length is 21.
    + dateReceivedByOsp (optional) - Date Received by OSP. Maximum length is 21.

            
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
			
### Get Schema for Proposal Investigator Certifications [GET /propdev/api/v1/proposal-investigator-certifications/]
	                                          
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
    
            {"columns":["proposalPersonNumber","proposalNumber","certified","dateCertified","dateReceivedByOsp"],"primaryKey":"proposalNumber:proposalPersonNumber"}
		
### Get Blueprint API specification for Proposal Investigator Certifications [GET /propdev/api/v1/proposal-investigator-certifications/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Investigator Certifications.md"
            transfer-encoding:chunked


### Update Proposal Investigator Certifications [PUT /propdev/api/v1/proposal-investigator-certifications/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalPersonNumber": "(val)","proposalNumber": "(val)","certified": "(val)","dateCertified": "(val)","dateReceivedByOsp": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Investigator Certifications [PUT /propdev/api/v1/proposal-investigator-certifications/]

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

### Insert Proposal Investigator Certifications [POST /propdev/api/v1/proposal-investigator-certifications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalPersonNumber": "(val)","proposalNumber": "(val)","certified": "(val)","dateCertified": "(val)","dateReceivedByOsp": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalPersonNumber": "(val)","proposalNumber": "(val)","certified": "(val)","dateCertified": "(val)","dateReceivedByOsp": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Investigator Certifications [POST /propdev/api/v1/proposal-investigator-certifications/]

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
            
### Delete Proposal Investigator Certifications by Key [DELETE /propdev/api/v1/proposal-investigator-certifications/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Investigator Certifications [DELETE /propdev/api/v1/proposal-investigator-certifications/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Investigator Certifications with Matching [DELETE /propdev/api/v1/proposal-investigator-certifications/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + proposalPersonNumber (optional) - Proposal Person Number. Maximum length is 40.
    + proposalNumber (optional) - Proposal Number. Maximum length is 40.
    + certified (optional) - Is Certified. Maximum length is 1.
    + dateCertified (optional) - Date Certified. Maximum length is 21.
    + dateReceivedByOsp (optional) - Date Received by OSP. Maximum length is 21.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
