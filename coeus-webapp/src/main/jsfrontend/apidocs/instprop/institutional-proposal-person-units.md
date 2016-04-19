## Institutional Proposal Person Units [/instprop/api/v1/institutional-proposal-person-units/]

### Get Institutional Proposal Person Units by Key [GET /instprop/api/v1/institutional-proposal-person-units/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"institutionalProposalPersonUnitId": "(val)","institutionalProposalContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}

### Get All Institutional Proposal Person Units [GET /instprop/api/v1/institutional-proposal-person-units/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"institutionalProposalPersonUnitId": "(val)","institutionalProposalContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalPersonUnitId": "(val)","institutionalProposalContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}
            ]

### Get All Institutional Proposal Person Units with Filtering [GET /instprop/api/v1/institutional-proposal-person-units/]
    
+ Parameters

    + institutionalProposalPersonUnitId (optional) - InstitutionalProposalPersonUnit ID. Maximum length is 8.
    + institutionalProposalContactId (optional) - 
    + unitNumber (optional) - Unit Number. Maximum length is 8.
    + leadUnit (optional) - Lead Unit flag. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"institutionalProposalPersonUnitId": "(val)","institutionalProposalContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalPersonUnitId": "(val)","institutionalProposalContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Institutional Proposal Person Units [GET /instprop/api/v1/institutional-proposal-person-units/]
	                                          
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
    
            {"columns":["institutionalProposalPersonUnitId","institutionalProposalContactId","unitNumber","leadUnit"],"primaryKey":"institutionalProposalPersonUnitId"}
		
### Get Blueprint API specification for Institutional Proposal Person Units [GET /instprop/api/v1/institutional-proposal-person-units/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Institutional Proposal Person Units.md"
            transfer-encoding:chunked


### Update Institutional Proposal Person Units [PUT /instprop/api/v1/institutional-proposal-person-units/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"institutionalProposalPersonUnitId": "(val)","institutionalProposalContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Institutional Proposal Person Units [PUT /instprop/api/v1/institutional-proposal-person-units/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"institutionalProposalPersonUnitId": "(val)","institutionalProposalContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalPersonUnitId": "(val)","institutionalProposalContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Institutional Proposal Person Units [POST /instprop/api/v1/institutional-proposal-person-units/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"institutionalProposalPersonUnitId": "(val)","institutionalProposalContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"institutionalProposalPersonUnitId": "(val)","institutionalProposalContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Institutional Proposal Person Units [POST /instprop/api/v1/institutional-proposal-person-units/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"institutionalProposalPersonUnitId": "(val)","institutionalProposalContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalPersonUnitId": "(val)","institutionalProposalContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"institutionalProposalPersonUnitId": "(val)","institutionalProposalContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalPersonUnitId": "(val)","institutionalProposalContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Institutional Proposal Person Units by Key [DELETE /instprop/api/v1/institutional-proposal-person-units/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Person Units [DELETE /instprop/api/v1/institutional-proposal-person-units/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Person Units with Matching [DELETE /instprop/api/v1/institutional-proposal-person-units/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + institutionalProposalPersonUnitId (optional) - InstitutionalProposalPersonUnit ID. Maximum length is 8.
    + institutionalProposalContactId (optional) - 
    + unitNumber (optional) - Unit Number. Maximum length is 8.
    + leadUnit (optional) - Lead Unit flag. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
