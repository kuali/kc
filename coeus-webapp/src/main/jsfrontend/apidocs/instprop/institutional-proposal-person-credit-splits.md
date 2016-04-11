## Institutional Proposal Person Credit Splits [/research-sys/api/v1/institutional-proposal-person-credit-splits/]

### Get Institutional Proposal Person Credit Splits by Key [GET /research-sys/api/v1/institutional-proposal-person-credit-splits/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"institutionalProposalPersonCreditSplitId": "(val)","institutionalProposalContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}

### Get All Institutional Proposal Person Credit Splits [GET /research-sys/api/v1/institutional-proposal-person-credit-splits/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"institutionalProposalPersonCreditSplitId": "(val)","institutionalProposalContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalPersonCreditSplitId": "(val)","institutionalProposalContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            ]

### Get All Institutional Proposal Person Credit Splits with Filtering [GET /research-sys/api/v1/institutional-proposal-person-credit-splits/]
    
+ Parameters

        + institutionalProposalPersonCreditSplitId
            + institutionalProposalContactId
            + invCreditTypeCode
            + credit

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"institutionalProposalPersonCreditSplitId": "(val)","institutionalProposalContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalPersonCreditSplitId": "(val)","institutionalProposalContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Institutional Proposal Person Credit Splits [GET /research-sys/api/v1/institutional-proposal-person-credit-splits/]
	                                          
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
    
            {"columns":["institutionalProposalPersonCreditSplitId","institutionalProposalContactId","invCreditTypeCode","credit"],"primaryKey":"institutionalProposalPersonCreditSplitId"}
		
### Get Blueprint API specification for Institutional Proposal Person Credit Splits [GET /research-sys/api/v1/institutional-proposal-person-credit-splits/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Institutional Proposal Person Credit Splits.md"
            transfer-encoding:chunked


### Update Institutional Proposal Person Credit Splits [PUT /research-sys/api/v1/institutional-proposal-person-credit-splits/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"institutionalProposalPersonCreditSplitId": "(val)","institutionalProposalContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Institutional Proposal Person Credit Splits [PUT /research-sys/api/v1/institutional-proposal-person-credit-splits/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"institutionalProposalPersonCreditSplitId": "(val)","institutionalProposalContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalPersonCreditSplitId": "(val)","institutionalProposalContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Institutional Proposal Person Credit Splits [POST /research-sys/api/v1/institutional-proposal-person-credit-splits/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"institutionalProposalPersonCreditSplitId": "(val)","institutionalProposalContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"institutionalProposalPersonCreditSplitId": "(val)","institutionalProposalContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Institutional Proposal Person Credit Splits [POST /research-sys/api/v1/institutional-proposal-person-credit-splits/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"institutionalProposalPersonCreditSplitId": "(val)","institutionalProposalContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalPersonCreditSplitId": "(val)","institutionalProposalContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"institutionalProposalPersonCreditSplitId": "(val)","institutionalProposalContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalPersonCreditSplitId": "(val)","institutionalProposalContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Institutional Proposal Person Credit Splits by Key [DELETE /research-sys/api/v1/institutional-proposal-person-credit-splits/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Person Credit Splits [DELETE /research-sys/api/v1/institutional-proposal-person-credit-splits/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Person Credit Splits with Matching [DELETE /research-sys/api/v1/institutional-proposal-person-credit-splits/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + institutionalProposalPersonCreditSplitId
            + institutionalProposalContactId
            + invCreditTypeCode
            + credit

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
