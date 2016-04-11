## Institutional Proposal Person Unit Credit Splits [/research-sys/api/v1/institutional-proposal-person-unit-credit-splits/]

### Get Institutional Proposal Person Unit Credit Splits by Key [GET /research-sys/api/v1/institutional-proposal-person-unit-credit-splits/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"institutionalProposalPersonUnitCreditSplitId": "(val)","institutionalProposalPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}

### Get All Institutional Proposal Person Unit Credit Splits [GET /research-sys/api/v1/institutional-proposal-person-unit-credit-splits/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"institutionalProposalPersonUnitCreditSplitId": "(val)","institutionalProposalPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalPersonUnitCreditSplitId": "(val)","institutionalProposalPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            ]

### Get All Institutional Proposal Person Unit Credit Splits with Filtering [GET /research-sys/api/v1/institutional-proposal-person-unit-credit-splits/]
    
+ Parameters

        + institutionalProposalPersonUnitCreditSplitId
            + institutionalProposalPersonUnitId
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
              {"institutionalProposalPersonUnitCreditSplitId": "(val)","institutionalProposalPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalPersonUnitCreditSplitId": "(val)","institutionalProposalPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Institutional Proposal Person Unit Credit Splits [GET /research-sys/api/v1/institutional-proposal-person-unit-credit-splits/]
	                                          
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
    
            {"columns":["institutionalProposalPersonUnitCreditSplitId","institutionalProposalPersonUnitId","invCreditTypeCode","credit"],"primaryKey":"institutionalProposalPersonUnitCreditSplitId"}
		
### Get Blueprint API specification for Institutional Proposal Person Unit Credit Splits [GET /research-sys/api/v1/institutional-proposal-person-unit-credit-splits/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Institutional Proposal Person Unit Credit Splits.md"
            transfer-encoding:chunked


### Update Institutional Proposal Person Unit Credit Splits [PUT /research-sys/api/v1/institutional-proposal-person-unit-credit-splits/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"institutionalProposalPersonUnitCreditSplitId": "(val)","institutionalProposalPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Institutional Proposal Person Unit Credit Splits [PUT /research-sys/api/v1/institutional-proposal-person-unit-credit-splits/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"institutionalProposalPersonUnitCreditSplitId": "(val)","institutionalProposalPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalPersonUnitCreditSplitId": "(val)","institutionalProposalPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Institutional Proposal Person Unit Credit Splits [POST /research-sys/api/v1/institutional-proposal-person-unit-credit-splits/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"institutionalProposalPersonUnitCreditSplitId": "(val)","institutionalProposalPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"institutionalProposalPersonUnitCreditSplitId": "(val)","institutionalProposalPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Institutional Proposal Person Unit Credit Splits [POST /research-sys/api/v1/institutional-proposal-person-unit-credit-splits/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"institutionalProposalPersonUnitCreditSplitId": "(val)","institutionalProposalPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalPersonUnitCreditSplitId": "(val)","institutionalProposalPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"institutionalProposalPersonUnitCreditSplitId": "(val)","institutionalProposalPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalPersonUnitCreditSplitId": "(val)","institutionalProposalPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Institutional Proposal Person Unit Credit Splits by Key [DELETE /research-sys/api/v1/institutional-proposal-person-unit-credit-splits/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Person Unit Credit Splits [DELETE /research-sys/api/v1/institutional-proposal-person-unit-credit-splits/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Person Unit Credit Splits with Matching [DELETE /research-sys/api/v1/institutional-proposal-person-unit-credit-splits/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + institutionalProposalPersonUnitCreditSplitId
            + institutionalProposalPersonUnitId
            + invCreditTypeCode
            + credit

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
