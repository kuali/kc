## Institutional Proposal Unrecovered F And As [/instprop/api/v1/institutional-proposal-unrecovered-f-and-as/]

### Get Institutional Proposal Unrecovered F And As by Key [GET /instprop/api/v1/institutional-proposal-unrecovered-f-and-as/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalUnrecoveredFandAId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","applicableIndirectcostRate": "(val)","indirectcostRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectcost": "(val)","sourceAccount": "(val)","_primaryKey": "(val)"}

### Get All Institutional Proposal Unrecovered F And As [GET /instprop/api/v1/institutional-proposal-unrecovered-f-and-as/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalUnrecoveredFandAId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","applicableIndirectcostRate": "(val)","indirectcostRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectcost": "(val)","sourceAccount": "(val)","_primaryKey": "(val)"},
              {"proposalUnrecoveredFandAId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","applicableIndirectcostRate": "(val)","indirectcostRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectcost": "(val)","sourceAccount": "(val)","_primaryKey": "(val)"}
            ]

### Get All Institutional Proposal Unrecovered F And As with Filtering [GET /instprop/api/v1/institutional-proposal-unrecovered-f-and-as/]
    
+ Parameters

    + proposalUnrecoveredFandAId (optional) - Proposal Unrecovered FNA Id. Maximum length is 22.
    + proposalId (optional) - 
    + proposalNumber (optional) - Proposal Number. Maximum length is 8.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + applicableIndirectcostRate (optional) - Applicable Indirectcost Rate. Maximum length is 10.
    + indirectcostRateTypeCode (optional) - IDC Rate Type Code. Maximum length is 3.
    + fiscalYear (optional) - Fiscal Year. Maximum length is 4.
    + onCampusFlag (optional) - On CampusContractContract Flag. Maximum length is 1.
    + underrecoveryOfIndirectcost (optional) - Underrecovery of Indirect Cost. Maximum length is 22.
    + sourceAccount (optional) - Source Account. Maximum length is 32.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalUnrecoveredFandAId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","applicableIndirectcostRate": "(val)","indirectcostRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectcost": "(val)","sourceAccount": "(val)","_primaryKey": "(val)"},
              {"proposalUnrecoveredFandAId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","applicableIndirectcostRate": "(val)","indirectcostRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectcost": "(val)","sourceAccount": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Institutional Proposal Unrecovered F And As [GET /instprop/api/v1/institutional-proposal-unrecovered-f-and-as/]
	                                          
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
    
            {"columns":["proposalUnrecoveredFandAId","proposalId","proposalNumber","sequenceNumber","applicableIndirectcostRate","indirectcostRateTypeCode","fiscalYear","onCampusFlag","underrecoveryOfIndirectcost","sourceAccount"],"primaryKey":"proposalUnrecoveredFandAId"}
		
### Get Blueprint API specification for Institutional Proposal Unrecovered F And As [GET /instprop/api/v1/institutional-proposal-unrecovered-f-and-as/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Institutional Proposal Unrecovered F And As.md"
            transfer-encoding:chunked


### Update Institutional Proposal Unrecovered F And As [PUT /instprop/api/v1/institutional-proposal-unrecovered-f-and-as/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalUnrecoveredFandAId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","applicableIndirectcostRate": "(val)","indirectcostRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectcost": "(val)","sourceAccount": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Institutional Proposal Unrecovered F And As [PUT /instprop/api/v1/institutional-proposal-unrecovered-f-and-as/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalUnrecoveredFandAId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","applicableIndirectcostRate": "(val)","indirectcostRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectcost": "(val)","sourceAccount": "(val)","_primaryKey": "(val)"},
              {"proposalUnrecoveredFandAId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","applicableIndirectcostRate": "(val)","indirectcostRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectcost": "(val)","sourceAccount": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Institutional Proposal Unrecovered F And As [POST /instprop/api/v1/institutional-proposal-unrecovered-f-and-as/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalUnrecoveredFandAId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","applicableIndirectcostRate": "(val)","indirectcostRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectcost": "(val)","sourceAccount": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalUnrecoveredFandAId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","applicableIndirectcostRate": "(val)","indirectcostRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectcost": "(val)","sourceAccount": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Institutional Proposal Unrecovered F And As [POST /instprop/api/v1/institutional-proposal-unrecovered-f-and-as/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalUnrecoveredFandAId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","applicableIndirectcostRate": "(val)","indirectcostRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectcost": "(val)","sourceAccount": "(val)","_primaryKey": "(val)"},
              {"proposalUnrecoveredFandAId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","applicableIndirectcostRate": "(val)","indirectcostRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectcost": "(val)","sourceAccount": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalUnrecoveredFandAId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","applicableIndirectcostRate": "(val)","indirectcostRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectcost": "(val)","sourceAccount": "(val)","_primaryKey": "(val)"},
              {"proposalUnrecoveredFandAId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","applicableIndirectcostRate": "(val)","indirectcostRateTypeCode": "(val)","fiscalYear": "(val)","onCampusFlag": "(val)","underrecoveryOfIndirectcost": "(val)","sourceAccount": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Institutional Proposal Unrecovered F And As by Key [DELETE /instprop/api/v1/institutional-proposal-unrecovered-f-and-as/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Unrecovered F And As [DELETE /instprop/api/v1/institutional-proposal-unrecovered-f-and-as/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Unrecovered F And As with Matching [DELETE /instprop/api/v1/institutional-proposal-unrecovered-f-and-as/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + proposalUnrecoveredFandAId (optional) - Proposal Unrecovered FNA Id. Maximum length is 22.
    + proposalId (optional) - 
    + proposalNumber (optional) - Proposal Number. Maximum length is 8.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + applicableIndirectcostRate (optional) - Applicable Indirectcost Rate. Maximum length is 10.
    + indirectcostRateTypeCode (optional) - IDC Rate Type Code. Maximum length is 3.
    + fiscalYear (optional) - Fiscal Year. Maximum length is 4.
    + onCampusFlag (optional) - On CampusContractContract Flag. Maximum length is 1.
    + underrecoveryOfIndirectcost (optional) - Underrecovery of Indirect Cost. Maximum length is 22.
    + sourceAccount (optional) - Source Account. Maximum length is 32.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
