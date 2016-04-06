## Sub Award Template Infos [/research-sys/api/v1/sub-award-template-infos/]

### Get Sub Award Template Infos by Key [GET /research-sys/api/v1/sub-award-template-infos/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","sowOrSubProposalBudget": "(val)","subProposalDate": "(val)","invoiceOrPaymentContact": "(val)","finalStmtOfCostscontact": "(val)","changeRequestsContact": "(val)","terminationContact": "(val)","noCostExtensionContact": "(val)","perfSiteDiffFromOrgAddr": "(val)","perfSiteSameAsSubPiAddr": "(val)","subRegisteredInCcr": "(val)","subExemptFromReportingComp": "(val)","parentDunsNumber": "(val)","parentCongressionalDistrict": "(val)","exemptFromRprtgExecComp": "(val)","copyRightType": "(val)","automaticCarryForward": "(val)","carryForwardRequestsSentTo": "(val)","treatmentPrgmIncomeAdditive": "(val)","applicableProgramRegulations": "(val)","applicableProgramRegsDate": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Template Infos [GET /research-sys/api/v1/sub-award-template-infos/]
	 
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

### Get All Sub Award Template Infos with Filtering [GET /research-sys/api/v1/sub-award-template-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + subAwardId
            + subAwardCode
            + sequenceNumber
            + sowOrSubProposalBudget
            + subProposalDate
            + invoiceOrPaymentContact
            + finalStmtOfCostscontact
            + changeRequestsContact
            + terminationContact
            + noCostExtensionContact
            + perfSiteDiffFromOrgAddr
            + perfSiteSameAsSubPiAddr
            + subRegisteredInCcr
            + subExemptFromReportingComp
            + parentDunsNumber
            + parentCongressionalDistrict
            + exemptFromRprtgExecComp
            + copyRightType
            + automaticCarryForward
            + carryForwardRequestsSentTo
            + treatmentPrgmIncomeAdditive
            + applicableProgramRegulations
            + applicableProgramRegsDate
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","sowOrSubProposalBudget": "(val)","subProposalDate": "(val)","invoiceOrPaymentContact": "(val)","finalStmtOfCostscontact": "(val)","changeRequestsContact": "(val)","terminationContact": "(val)","noCostExtensionContact": "(val)","perfSiteDiffFromOrgAddr": "(val)","perfSiteSameAsSubPiAddr": "(val)","subRegisteredInCcr": "(val)","subExemptFromReportingComp": "(val)","parentDunsNumber": "(val)","parentCongressionalDistrict": "(val)","exemptFromRprtgExecComp": "(val)","copyRightType": "(val)","automaticCarryForward": "(val)","carryForwardRequestsSentTo": "(val)","treatmentPrgmIncomeAdditive": "(val)","applicableProgramRegulations": "(val)","applicableProgramRegsDate": "(val)","_primaryKey": "(val)"},
              {"subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","sowOrSubProposalBudget": "(val)","subProposalDate": "(val)","invoiceOrPaymentContact": "(val)","finalStmtOfCostscontact": "(val)","changeRequestsContact": "(val)","terminationContact": "(val)","noCostExtensionContact": "(val)","perfSiteDiffFromOrgAddr": "(val)","perfSiteSameAsSubPiAddr": "(val)","subRegisteredInCcr": "(val)","subExemptFromReportingComp": "(val)","parentDunsNumber": "(val)","parentCongressionalDistrict": "(val)","exemptFromRprtgExecComp": "(val)","copyRightType": "(val)","automaticCarryForward": "(val)","carryForwardRequestsSentTo": "(val)","treatmentPrgmIncomeAdditive": "(val)","applicableProgramRegulations": "(val)","applicableProgramRegsDate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sub Award Template Infos [GET /research-sys/api/v1/sub-award-template-infos/]
	 
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
		
### Get Blueprint API specification for Sub Award Template Infos [GET /research-sys/api/v1/sub-award-template-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Award Template Infos.md"
            transfer-encoding:chunked


### Update Sub Award Template Infos [PUT /research-sys/api/v1/sub-award-template-infos/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","sowOrSubProposalBudget": "(val)","subProposalDate": "(val)","invoiceOrPaymentContact": "(val)","finalStmtOfCostscontact": "(val)","changeRequestsContact": "(val)","terminationContact": "(val)","noCostExtensionContact": "(val)","perfSiteDiffFromOrgAddr": "(val)","perfSiteSameAsSubPiAddr": "(val)","subRegisteredInCcr": "(val)","subExemptFromReportingComp": "(val)","parentDunsNumber": "(val)","parentCongressionalDistrict": "(val)","exemptFromRprtgExecComp": "(val)","copyRightType": "(val)","automaticCarryForward": "(val)","carryForwardRequestsSentTo": "(val)","treatmentPrgmIncomeAdditive": "(val)","applicableProgramRegulations": "(val)","applicableProgramRegsDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Template Infos [PUT /research-sys/api/v1/sub-award-template-infos/]

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

### Insert Sub Award Template Infos [POST /research-sys/api/v1/sub-award-template-infos/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","sowOrSubProposalBudget": "(val)","subProposalDate": "(val)","invoiceOrPaymentContact": "(val)","finalStmtOfCostscontact": "(val)","changeRequestsContact": "(val)","terminationContact": "(val)","noCostExtensionContact": "(val)","perfSiteDiffFromOrgAddr": "(val)","perfSiteSameAsSubPiAddr": "(val)","subRegisteredInCcr": "(val)","subExemptFromReportingComp": "(val)","parentDunsNumber": "(val)","parentCongressionalDistrict": "(val)","exemptFromRprtgExecComp": "(val)","copyRightType": "(val)","automaticCarryForward": "(val)","carryForwardRequestsSentTo": "(val)","treatmentPrgmIncomeAdditive": "(val)","applicableProgramRegulations": "(val)","applicableProgramRegsDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","sowOrSubProposalBudget": "(val)","subProposalDate": "(val)","invoiceOrPaymentContact": "(val)","finalStmtOfCostscontact": "(val)","changeRequestsContact": "(val)","terminationContact": "(val)","noCostExtensionContact": "(val)","perfSiteDiffFromOrgAddr": "(val)","perfSiteSameAsSubPiAddr": "(val)","subRegisteredInCcr": "(val)","subExemptFromReportingComp": "(val)","parentDunsNumber": "(val)","parentCongressionalDistrict": "(val)","exemptFromRprtgExecComp": "(val)","copyRightType": "(val)","automaticCarryForward": "(val)","carryForwardRequestsSentTo": "(val)","treatmentPrgmIncomeAdditive": "(val)","applicableProgramRegulations": "(val)","applicableProgramRegsDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Template Infos [POST /research-sys/api/v1/sub-award-template-infos/]

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
            
### Delete Sub Award Template Infos by Key [DELETE /research-sys/api/v1/sub-award-template-infos/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Template Infos [DELETE /research-sys/api/v1/sub-award-template-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Sub Award Template Infos with Matching [DELETE /research-sys/api/v1/sub-award-template-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + subAwardId
            + subAwardCode
            + sequenceNumber
            + sowOrSubProposalBudget
            + subProposalDate
            + invoiceOrPaymentContact
            + finalStmtOfCostscontact
            + changeRequestsContact
            + terminationContact
            + noCostExtensionContact
            + perfSiteDiffFromOrgAddr
            + perfSiteSameAsSubPiAddr
            + subRegisteredInCcr
            + subExemptFromReportingComp
            + parentDunsNumber
            + parentCongressionalDistrict
            + exemptFromRprtgExecComp
            + copyRightType
            + automaticCarryForward
            + carryForwardRequestsSentTo
            + treatmentPrgmIncomeAdditive
            + applicableProgramRegulations
            + applicableProgramRegsDate


+ Response 204
