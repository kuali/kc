## Sub Award Template Infos [/subaward/api/v1/sub-award-template-infos/]

### Get Sub Award Template Infos by Key [GET /subaward/api/v1/sub-award-template-infos/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","sowOrSubProposalBudget": "(val)","subProposalDate": "(val)","invoiceOrPaymentContact": "(val)","finalStmtOfCostscontact": "(val)","changeRequestsContact": "(val)","terminationContact": "(val)","noCostExtensionContact": "(val)","perfSiteDiffFromOrgAddr": "(val)","perfSiteSameAsSubPiAddr": "(val)","subRegisteredInCcr": "(val)","subExemptFromReportingComp": "(val)","parentDunsNumber": "(val)","parentCongressionalDistrict": "(val)","exemptFromRprtgExecComp": "(val)","copyRightType": "(val)","automaticCarryForward": "(val)","carryForwardRequestsSentTo": "(val)","treatmentPrgmIncomeAdditive": "(val)","applicableProgramRegulations": "(val)","applicableProgramRegsDate": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Template Infos [GET /subaward/api/v1/sub-award-template-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","sowOrSubProposalBudget": "(val)","subProposalDate": "(val)","invoiceOrPaymentContact": "(val)","finalStmtOfCostscontact": "(val)","changeRequestsContact": "(val)","terminationContact": "(val)","noCostExtensionContact": "(val)","perfSiteDiffFromOrgAddr": "(val)","perfSiteSameAsSubPiAddr": "(val)","subRegisteredInCcr": "(val)","subExemptFromReportingComp": "(val)","parentDunsNumber": "(val)","parentCongressionalDistrict": "(val)","exemptFromRprtgExecComp": "(val)","copyRightType": "(val)","automaticCarryForward": "(val)","carryForwardRequestsSentTo": "(val)","treatmentPrgmIncomeAdditive": "(val)","applicableProgramRegulations": "(val)","applicableProgramRegsDate": "(val)","_primaryKey": "(val)"},
              {"subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","sowOrSubProposalBudget": "(val)","subProposalDate": "(val)","invoiceOrPaymentContact": "(val)","finalStmtOfCostscontact": "(val)","changeRequestsContact": "(val)","terminationContact": "(val)","noCostExtensionContact": "(val)","perfSiteDiffFromOrgAddr": "(val)","perfSiteSameAsSubPiAddr": "(val)","subRegisteredInCcr": "(val)","subExemptFromReportingComp": "(val)","parentDunsNumber": "(val)","parentCongressionalDistrict": "(val)","exemptFromRprtgExecComp": "(val)","copyRightType": "(val)","automaticCarryForward": "(val)","carryForwardRequestsSentTo": "(val)","treatmentPrgmIncomeAdditive": "(val)","applicableProgramRegulations": "(val)","applicableProgramRegsDate": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sub Award Template Infos with Filtering [GET /subaward/api/v1/sub-award-template-infos/]
    
+ Parameters

    + subAwardId (optional) - 
    + subAwardCode (optional) - 
    + sequenceNumber (optional) - 
    + sowOrSubProposalBudget (optional) - SOW/Budget specified in proposal. Maximum length is 1.
    + subProposalDate (optional) - SubProposal Date. Maximum length is 21.
    + invoiceOrPaymentContact (optional) - Invoice / Payment Contact. Maximum length is 10.
    + finalStmtOfCostscontact (optional) - Final Statement of Costs Contact. Maximum length is 10.
    + changeRequestsContact (optional) - Change Requests Contact. Maximum length is 10.
    + terminationContact (optional) - Termination Contact. Maximum length is 10.
    + noCostExtensionContact (optional) - No Cost Extension Contact. Maximum length is 10.
    + perfSiteDiffFromOrgAddr (optional) - Performance Site same as Org address?. Maximum length is 10.
    + perfSiteSameAsSubPiAddr (optional) - Performance Site same as PI address?. Maximum length is 10.
    + subRegisteredInCcr (optional) - Sub Registered in CCR?. Maximum length is 10.
    + subExemptFromReportingComp (optional) - 
    + parentDunsNumber (optional) - Parent DUNS (if applicable). Maximum length is 20.
    + parentCongressionalDistrict (optional) - Parent Congressional District. Maximum length is 20.
    + exemptFromRprtgExecComp (optional) - Exempt from reporting exec compensation. Maximum length is 10.
    + copyRightType (optional) - Copyrights / Data Rights. Maximum length is 10.
    + automaticCarryForward (optional) - Automatic Carry Forward. Maximum length is 1.
    + carryForwardRequestsSentTo (optional) - Carry Forward Requests Sent To. Maximum length is 10.
    + treatmentPrgmIncomeAdditive (optional) - Treatment of Program Income Additive. Maximum length is 10.
    + applicableProgramRegulations (optional) - Applicable Program Regulations. Maximum length is 50.
    + applicableProgramRegsDate (optional) - Applicable Program Regulations Date. Maximum length is 21.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","sowOrSubProposalBudget": "(val)","subProposalDate": "(val)","invoiceOrPaymentContact": "(val)","finalStmtOfCostscontact": "(val)","changeRequestsContact": "(val)","terminationContact": "(val)","noCostExtensionContact": "(val)","perfSiteDiffFromOrgAddr": "(val)","perfSiteSameAsSubPiAddr": "(val)","subRegisteredInCcr": "(val)","subExemptFromReportingComp": "(val)","parentDunsNumber": "(val)","parentCongressionalDistrict": "(val)","exemptFromRprtgExecComp": "(val)","copyRightType": "(val)","automaticCarryForward": "(val)","carryForwardRequestsSentTo": "(val)","treatmentPrgmIncomeAdditive": "(val)","applicableProgramRegulations": "(val)","applicableProgramRegsDate": "(val)","_primaryKey": "(val)"},
              {"subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","sowOrSubProposalBudget": "(val)","subProposalDate": "(val)","invoiceOrPaymentContact": "(val)","finalStmtOfCostscontact": "(val)","changeRequestsContact": "(val)","terminationContact": "(val)","noCostExtensionContact": "(val)","perfSiteDiffFromOrgAddr": "(val)","perfSiteSameAsSubPiAddr": "(val)","subRegisteredInCcr": "(val)","subExemptFromReportingComp": "(val)","parentDunsNumber": "(val)","parentCongressionalDistrict": "(val)","exemptFromRprtgExecComp": "(val)","copyRightType": "(val)","automaticCarryForward": "(val)","carryForwardRequestsSentTo": "(val)","treatmentPrgmIncomeAdditive": "(val)","applicableProgramRegulations": "(val)","applicableProgramRegsDate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sub Award Template Infos [GET /subaward/api/v1/sub-award-template-infos/]
	                                          
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
    
            {"columns":["subAwardId","subAwardCode","sequenceNumber","sowOrSubProposalBudget","subProposalDate","invoiceOrPaymentContact","finalStmtOfCostscontact","changeRequestsContact","terminationContact","noCostExtensionContact","perfSiteDiffFromOrgAddr","perfSiteSameAsSubPiAddr","subRegisteredInCcr","subExemptFromReportingComp","parentDunsNumber","parentCongressionalDistrict","exemptFromRprtgExecComp","copyRightType","automaticCarryForward","carryForwardRequestsSentTo","treatmentPrgmIncomeAdditive","applicableProgramRegulations","applicableProgramRegsDate"],"primaryKey":"subAwardId"}
		
### Get Blueprint API specification for Sub Award Template Infos [GET /subaward/api/v1/sub-award-template-infos/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Award Template Infos.md"
            transfer-encoding:chunked


### Update Sub Award Template Infos [PUT /subaward/api/v1/sub-award-template-infos/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","sowOrSubProposalBudget": "(val)","subProposalDate": "(val)","invoiceOrPaymentContact": "(val)","finalStmtOfCostscontact": "(val)","changeRequestsContact": "(val)","terminationContact": "(val)","noCostExtensionContact": "(val)","perfSiteDiffFromOrgAddr": "(val)","perfSiteSameAsSubPiAddr": "(val)","subRegisteredInCcr": "(val)","subExemptFromReportingComp": "(val)","parentDunsNumber": "(val)","parentCongressionalDistrict": "(val)","exemptFromRprtgExecComp": "(val)","copyRightType": "(val)","automaticCarryForward": "(val)","carryForwardRequestsSentTo": "(val)","treatmentPrgmIncomeAdditive": "(val)","applicableProgramRegulations": "(val)","applicableProgramRegsDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Template Infos [PUT /subaward/api/v1/sub-award-template-infos/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","sowOrSubProposalBudget": "(val)","subProposalDate": "(val)","invoiceOrPaymentContact": "(val)","finalStmtOfCostscontact": "(val)","changeRequestsContact": "(val)","terminationContact": "(val)","noCostExtensionContact": "(val)","perfSiteDiffFromOrgAddr": "(val)","perfSiteSameAsSubPiAddr": "(val)","subRegisteredInCcr": "(val)","subExemptFromReportingComp": "(val)","parentDunsNumber": "(val)","parentCongressionalDistrict": "(val)","exemptFromRprtgExecComp": "(val)","copyRightType": "(val)","automaticCarryForward": "(val)","carryForwardRequestsSentTo": "(val)","treatmentPrgmIncomeAdditive": "(val)","applicableProgramRegulations": "(val)","applicableProgramRegsDate": "(val)","_primaryKey": "(val)"},
              {"subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","sowOrSubProposalBudget": "(val)","subProposalDate": "(val)","invoiceOrPaymentContact": "(val)","finalStmtOfCostscontact": "(val)","changeRequestsContact": "(val)","terminationContact": "(val)","noCostExtensionContact": "(val)","perfSiteDiffFromOrgAddr": "(val)","perfSiteSameAsSubPiAddr": "(val)","subRegisteredInCcr": "(val)","subExemptFromReportingComp": "(val)","parentDunsNumber": "(val)","parentCongressionalDistrict": "(val)","exemptFromRprtgExecComp": "(val)","copyRightType": "(val)","automaticCarryForward": "(val)","carryForwardRequestsSentTo": "(val)","treatmentPrgmIncomeAdditive": "(val)","applicableProgramRegulations": "(val)","applicableProgramRegsDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Sub Award Template Infos [POST /subaward/api/v1/sub-award-template-infos/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","sowOrSubProposalBudget": "(val)","subProposalDate": "(val)","invoiceOrPaymentContact": "(val)","finalStmtOfCostscontact": "(val)","changeRequestsContact": "(val)","terminationContact": "(val)","noCostExtensionContact": "(val)","perfSiteDiffFromOrgAddr": "(val)","perfSiteSameAsSubPiAddr": "(val)","subRegisteredInCcr": "(val)","subExemptFromReportingComp": "(val)","parentDunsNumber": "(val)","parentCongressionalDistrict": "(val)","exemptFromRprtgExecComp": "(val)","copyRightType": "(val)","automaticCarryForward": "(val)","carryForwardRequestsSentTo": "(val)","treatmentPrgmIncomeAdditive": "(val)","applicableProgramRegulations": "(val)","applicableProgramRegsDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","sowOrSubProposalBudget": "(val)","subProposalDate": "(val)","invoiceOrPaymentContact": "(val)","finalStmtOfCostscontact": "(val)","changeRequestsContact": "(val)","terminationContact": "(val)","noCostExtensionContact": "(val)","perfSiteDiffFromOrgAddr": "(val)","perfSiteSameAsSubPiAddr": "(val)","subRegisteredInCcr": "(val)","subExemptFromReportingComp": "(val)","parentDunsNumber": "(val)","parentCongressionalDistrict": "(val)","exemptFromRprtgExecComp": "(val)","copyRightType": "(val)","automaticCarryForward": "(val)","carryForwardRequestsSentTo": "(val)","treatmentPrgmIncomeAdditive": "(val)","applicableProgramRegulations": "(val)","applicableProgramRegsDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Template Infos [POST /subaward/api/v1/sub-award-template-infos/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","sowOrSubProposalBudget": "(val)","subProposalDate": "(val)","invoiceOrPaymentContact": "(val)","finalStmtOfCostscontact": "(val)","changeRequestsContact": "(val)","terminationContact": "(val)","noCostExtensionContact": "(val)","perfSiteDiffFromOrgAddr": "(val)","perfSiteSameAsSubPiAddr": "(val)","subRegisteredInCcr": "(val)","subExemptFromReportingComp": "(val)","parentDunsNumber": "(val)","parentCongressionalDistrict": "(val)","exemptFromRprtgExecComp": "(val)","copyRightType": "(val)","automaticCarryForward": "(val)","carryForwardRequestsSentTo": "(val)","treatmentPrgmIncomeAdditive": "(val)","applicableProgramRegulations": "(val)","applicableProgramRegsDate": "(val)","_primaryKey": "(val)"},
              {"subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","sowOrSubProposalBudget": "(val)","subProposalDate": "(val)","invoiceOrPaymentContact": "(val)","finalStmtOfCostscontact": "(val)","changeRequestsContact": "(val)","terminationContact": "(val)","noCostExtensionContact": "(val)","perfSiteDiffFromOrgAddr": "(val)","perfSiteSameAsSubPiAddr": "(val)","subRegisteredInCcr": "(val)","subExemptFromReportingComp": "(val)","parentDunsNumber": "(val)","parentCongressionalDistrict": "(val)","exemptFromRprtgExecComp": "(val)","copyRightType": "(val)","automaticCarryForward": "(val)","carryForwardRequestsSentTo": "(val)","treatmentPrgmIncomeAdditive": "(val)","applicableProgramRegulations": "(val)","applicableProgramRegsDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","sowOrSubProposalBudget": "(val)","subProposalDate": "(val)","invoiceOrPaymentContact": "(val)","finalStmtOfCostscontact": "(val)","changeRequestsContact": "(val)","terminationContact": "(val)","noCostExtensionContact": "(val)","perfSiteDiffFromOrgAddr": "(val)","perfSiteSameAsSubPiAddr": "(val)","subRegisteredInCcr": "(val)","subExemptFromReportingComp": "(val)","parentDunsNumber": "(val)","parentCongressionalDistrict": "(val)","exemptFromRprtgExecComp": "(val)","copyRightType": "(val)","automaticCarryForward": "(val)","carryForwardRequestsSentTo": "(val)","treatmentPrgmIncomeAdditive": "(val)","applicableProgramRegulations": "(val)","applicableProgramRegsDate": "(val)","_primaryKey": "(val)"},
              {"subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","sowOrSubProposalBudget": "(val)","subProposalDate": "(val)","invoiceOrPaymentContact": "(val)","finalStmtOfCostscontact": "(val)","changeRequestsContact": "(val)","terminationContact": "(val)","noCostExtensionContact": "(val)","perfSiteDiffFromOrgAddr": "(val)","perfSiteSameAsSubPiAddr": "(val)","subRegisteredInCcr": "(val)","subExemptFromReportingComp": "(val)","parentDunsNumber": "(val)","parentCongressionalDistrict": "(val)","exemptFromRprtgExecComp": "(val)","copyRightType": "(val)","automaticCarryForward": "(val)","carryForwardRequestsSentTo": "(val)","treatmentPrgmIncomeAdditive": "(val)","applicableProgramRegulations": "(val)","applicableProgramRegsDate": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Sub Award Template Infos by Key [DELETE /subaward/api/v1/sub-award-template-infos/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Template Infos [DELETE /subaward/api/v1/sub-award-template-infos/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Template Infos with Matching [DELETE /subaward/api/v1/sub-award-template-infos/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + subAwardId (optional) - 
    + subAwardCode (optional) - 
    + sequenceNumber (optional) - 
    + sowOrSubProposalBudget (optional) - SOW/Budget specified in proposal. Maximum length is 1.
    + subProposalDate (optional) - SubProposal Date. Maximum length is 21.
    + invoiceOrPaymentContact (optional) - Invoice / Payment Contact. Maximum length is 10.
    + finalStmtOfCostscontact (optional) - Final Statement of Costs Contact. Maximum length is 10.
    + changeRequestsContact (optional) - Change Requests Contact. Maximum length is 10.
    + terminationContact (optional) - Termination Contact. Maximum length is 10.
    + noCostExtensionContact (optional) - No Cost Extension Contact. Maximum length is 10.
    + perfSiteDiffFromOrgAddr (optional) - Performance Site same as Org address?. Maximum length is 10.
    + perfSiteSameAsSubPiAddr (optional) - Performance Site same as PI address?. Maximum length is 10.
    + subRegisteredInCcr (optional) - Sub Registered in CCR?. Maximum length is 10.
    + subExemptFromReportingComp (optional) - 
    + parentDunsNumber (optional) - Parent DUNS (if applicable). Maximum length is 20.
    + parentCongressionalDistrict (optional) - Parent Congressional District. Maximum length is 20.
    + exemptFromRprtgExecComp (optional) - Exempt from reporting exec compensation. Maximum length is 10.
    + copyRightType (optional) - Copyrights / Data Rights. Maximum length is 10.
    + automaticCarryForward (optional) - Automatic Carry Forward. Maximum length is 1.
    + carryForwardRequestsSentTo (optional) - Carry Forward Requests Sent To. Maximum length is 10.
    + treatmentPrgmIncomeAdditive (optional) - Treatment of Program Income Additive. Maximum length is 10.
    + applicableProgramRegulations (optional) - Applicable Program Regulations. Maximum length is 50.
    + applicableProgramRegsDate (optional) - Applicable Program Regulations Date. Maximum length is 21.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
