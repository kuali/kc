## Awards [/award/api/v1/awards/]

### Get Awards by Key [GET /award/api/v1/awards/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","statusCode": "(val)","templateCode": "(val)","accountNumber": "(val)","approvedEquipmentIndicator": "(val)","approvedForeignTripIndicator": "(val)","subContractIndicator": "(val)","awardEffectiveDate": "(val)","awardExecutionDate": "(val)","beginDate": "(val)","costSharingIndicator": "(val)","indirectCostIndicator": "(val)","modificationNumber": "(val)","nsfCode": "(val)","paymentScheduleIndicator": "(val)","scienceCodeIndicator": "(val)","specialReviewIndicator": "(val)","sponsorAwardNumber": "(val)","transferSponsorIndicator": "(val)","accountTypeCode": "(val)","activityTypeCode": "(val)","awardTypeCode": "(val)","primeSponsorCode": "(val)","awardId": "(val)","basisOfPaymentCode": "(val)","cfdaNumber": "(val)","documentFundingId": "(val)","methodOfPaymentCode": "(val)","preAwardAuthorizedAmount": "(val)","preAwardEffectiveDate": "(val)","preAwardInstitutionalAuthorizedAmount": "(val)","preAwardInstitutionalEffectiveDate": "(val)","procurementPriorityCode": "(val)","proposalNumber": "(val)","specialEbRateOffCampus": "(val)","specialEbRateOnCampus": "(val)","subPlanFlag": "(val)","title": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","awardTransactionTypeCode": "(val)","noticeDate": "(val)","unitNumber": "(val)","financialAccountDocumentNumber": "(val)","financialAccountCreationDate": "(val)","financialChartOfAccountsCode": "(val)","syncChild": "(val)","awardSequenceStatus": "(val)","fainId": "(val)","fedAwardYear": "(val)","fedAwardDate": "(val)","_primaryKey": "(val)"}

### Get All Awards [GET /award/api/v1/awards/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","statusCode": "(val)","templateCode": "(val)","accountNumber": "(val)","approvedEquipmentIndicator": "(val)","approvedForeignTripIndicator": "(val)","subContractIndicator": "(val)","awardEffectiveDate": "(val)","awardExecutionDate": "(val)","beginDate": "(val)","costSharingIndicator": "(val)","indirectCostIndicator": "(val)","modificationNumber": "(val)","nsfCode": "(val)","paymentScheduleIndicator": "(val)","scienceCodeIndicator": "(val)","specialReviewIndicator": "(val)","sponsorAwardNumber": "(val)","transferSponsorIndicator": "(val)","accountTypeCode": "(val)","activityTypeCode": "(val)","awardTypeCode": "(val)","primeSponsorCode": "(val)","awardId": "(val)","basisOfPaymentCode": "(val)","cfdaNumber": "(val)","documentFundingId": "(val)","methodOfPaymentCode": "(val)","preAwardAuthorizedAmount": "(val)","preAwardEffectiveDate": "(val)","preAwardInstitutionalAuthorizedAmount": "(val)","preAwardInstitutionalEffectiveDate": "(val)","procurementPriorityCode": "(val)","proposalNumber": "(val)","specialEbRateOffCampus": "(val)","specialEbRateOnCampus": "(val)","subPlanFlag": "(val)","title": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","awardTransactionTypeCode": "(val)","noticeDate": "(val)","unitNumber": "(val)","financialAccountDocumentNumber": "(val)","financialAccountCreationDate": "(val)","financialChartOfAccountsCode": "(val)","syncChild": "(val)","awardSequenceStatus": "(val)","fainId": "(val)","fedAwardYear": "(val)","fedAwardDate": "(val)","_primaryKey": "(val)"},
              {"awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","statusCode": "(val)","templateCode": "(val)","accountNumber": "(val)","approvedEquipmentIndicator": "(val)","approvedForeignTripIndicator": "(val)","subContractIndicator": "(val)","awardEffectiveDate": "(val)","awardExecutionDate": "(val)","beginDate": "(val)","costSharingIndicator": "(val)","indirectCostIndicator": "(val)","modificationNumber": "(val)","nsfCode": "(val)","paymentScheduleIndicator": "(val)","scienceCodeIndicator": "(val)","specialReviewIndicator": "(val)","sponsorAwardNumber": "(val)","transferSponsorIndicator": "(val)","accountTypeCode": "(val)","activityTypeCode": "(val)","awardTypeCode": "(val)","primeSponsorCode": "(val)","awardId": "(val)","basisOfPaymentCode": "(val)","cfdaNumber": "(val)","documentFundingId": "(val)","methodOfPaymentCode": "(val)","preAwardAuthorizedAmount": "(val)","preAwardEffectiveDate": "(val)","preAwardInstitutionalAuthorizedAmount": "(val)","preAwardInstitutionalEffectiveDate": "(val)","procurementPriorityCode": "(val)","proposalNumber": "(val)","specialEbRateOffCampus": "(val)","specialEbRateOnCampus": "(val)","subPlanFlag": "(val)","title": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","awardTransactionTypeCode": "(val)","noticeDate": "(val)","unitNumber": "(val)","financialAccountDocumentNumber": "(val)","financialAccountCreationDate": "(val)","financialChartOfAccountsCode": "(val)","syncChild": "(val)","awardSequenceStatus": "(val)","fainId": "(val)","fedAwardYear": "(val)","fedAwardDate": "(val)","_primaryKey": "(val)"}
            ]

### Get All Awards with Filtering [GET /award/api/v1/awards/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + awardNumber
            + sequenceNumber
            + sponsorCode
            + statusCode
            + templateCode
            + accountNumber
            + approvedEquipmentIndicator
            + approvedForeignTripIndicator
            + subContractIndicator
            + awardEffectiveDate
            + awardExecutionDate
            + beginDate
            + costSharingIndicator
            + indirectCostIndicator
            + modificationNumber
            + nsfCode
            + paymentScheduleIndicator
            + scienceCodeIndicator
            + specialReviewIndicator
            + sponsorAwardNumber
            + transferSponsorIndicator
            + accountTypeCode
            + activityTypeCode
            + awardTypeCode
            + primeSponsorCode
            + awardId
            + basisOfPaymentCode
            + cfdaNumber
            + documentFundingId
            + methodOfPaymentCode
            + preAwardAuthorizedAmount
            + preAwardEffectiveDate
            + preAwardInstitutionalAuthorizedAmount
            + preAwardInstitutionalEffectiveDate
            + procurementPriorityCode
            + proposalNumber
            + specialEbRateOffCampus
            + specialEbRateOnCampus
            + subPlanFlag
            + title
            + archiveLocation
            + closeoutDate
            + awardTransactionTypeCode
            + noticeDate
            + unitNumber
            + financialAccountDocumentNumber
            + financialAccountCreationDate
            + financialChartOfAccountsCode
            + syncChild
            + awardSequenceStatus
            + fainId
            + fedAwardYear
            + fedAwardDate
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","statusCode": "(val)","templateCode": "(val)","accountNumber": "(val)","approvedEquipmentIndicator": "(val)","approvedForeignTripIndicator": "(val)","subContractIndicator": "(val)","awardEffectiveDate": "(val)","awardExecutionDate": "(val)","beginDate": "(val)","costSharingIndicator": "(val)","indirectCostIndicator": "(val)","modificationNumber": "(val)","nsfCode": "(val)","paymentScheduleIndicator": "(val)","scienceCodeIndicator": "(val)","specialReviewIndicator": "(val)","sponsorAwardNumber": "(val)","transferSponsorIndicator": "(val)","accountTypeCode": "(val)","activityTypeCode": "(val)","awardTypeCode": "(val)","primeSponsorCode": "(val)","awardId": "(val)","basisOfPaymentCode": "(val)","cfdaNumber": "(val)","documentFundingId": "(val)","methodOfPaymentCode": "(val)","preAwardAuthorizedAmount": "(val)","preAwardEffectiveDate": "(val)","preAwardInstitutionalAuthorizedAmount": "(val)","preAwardInstitutionalEffectiveDate": "(val)","procurementPriorityCode": "(val)","proposalNumber": "(val)","specialEbRateOffCampus": "(val)","specialEbRateOnCampus": "(val)","subPlanFlag": "(val)","title": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","awardTransactionTypeCode": "(val)","noticeDate": "(val)","unitNumber": "(val)","financialAccountDocumentNumber": "(val)","financialAccountCreationDate": "(val)","financialChartOfAccountsCode": "(val)","syncChild": "(val)","awardSequenceStatus": "(val)","fainId": "(val)","fedAwardYear": "(val)","fedAwardDate": "(val)","_primaryKey": "(val)"},
              {"awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","statusCode": "(val)","templateCode": "(val)","accountNumber": "(val)","approvedEquipmentIndicator": "(val)","approvedForeignTripIndicator": "(val)","subContractIndicator": "(val)","awardEffectiveDate": "(val)","awardExecutionDate": "(val)","beginDate": "(val)","costSharingIndicator": "(val)","indirectCostIndicator": "(val)","modificationNumber": "(val)","nsfCode": "(val)","paymentScheduleIndicator": "(val)","scienceCodeIndicator": "(val)","specialReviewIndicator": "(val)","sponsorAwardNumber": "(val)","transferSponsorIndicator": "(val)","accountTypeCode": "(val)","activityTypeCode": "(val)","awardTypeCode": "(val)","primeSponsorCode": "(val)","awardId": "(val)","basisOfPaymentCode": "(val)","cfdaNumber": "(val)","documentFundingId": "(val)","methodOfPaymentCode": "(val)","preAwardAuthorizedAmount": "(val)","preAwardEffectiveDate": "(val)","preAwardInstitutionalAuthorizedAmount": "(val)","preAwardInstitutionalEffectiveDate": "(val)","procurementPriorityCode": "(val)","proposalNumber": "(val)","specialEbRateOffCampus": "(val)","specialEbRateOnCampus": "(val)","subPlanFlag": "(val)","title": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","awardTransactionTypeCode": "(val)","noticeDate": "(val)","unitNumber": "(val)","financialAccountDocumentNumber": "(val)","financialAccountCreationDate": "(val)","financialChartOfAccountsCode": "(val)","syncChild": "(val)","awardSequenceStatus": "(val)","fainId": "(val)","fedAwardYear": "(val)","fedAwardDate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Awards [GET /award/api/v1/awards/]
	 
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
		
### Get Blueprint API specification for Awards [GET /award/api/v1/awards/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Awards.md"
            transfer-encoding:chunked


### Update Awards [PUT /award/api/v1/awards/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","statusCode": "(val)","templateCode": "(val)","accountNumber": "(val)","approvedEquipmentIndicator": "(val)","approvedForeignTripIndicator": "(val)","subContractIndicator": "(val)","awardEffectiveDate": "(val)","awardExecutionDate": "(val)","beginDate": "(val)","costSharingIndicator": "(val)","indirectCostIndicator": "(val)","modificationNumber": "(val)","nsfCode": "(val)","paymentScheduleIndicator": "(val)","scienceCodeIndicator": "(val)","specialReviewIndicator": "(val)","sponsorAwardNumber": "(val)","transferSponsorIndicator": "(val)","accountTypeCode": "(val)","activityTypeCode": "(val)","awardTypeCode": "(val)","primeSponsorCode": "(val)","awardId": "(val)","basisOfPaymentCode": "(val)","cfdaNumber": "(val)","documentFundingId": "(val)","methodOfPaymentCode": "(val)","preAwardAuthorizedAmount": "(val)","preAwardEffectiveDate": "(val)","preAwardInstitutionalAuthorizedAmount": "(val)","preAwardInstitutionalEffectiveDate": "(val)","procurementPriorityCode": "(val)","proposalNumber": "(val)","specialEbRateOffCampus": "(val)","specialEbRateOnCampus": "(val)","subPlanFlag": "(val)","title": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","awardTransactionTypeCode": "(val)","noticeDate": "(val)","unitNumber": "(val)","financialAccountDocumentNumber": "(val)","financialAccountCreationDate": "(val)","financialChartOfAccountsCode": "(val)","syncChild": "(val)","awardSequenceStatus": "(val)","fainId": "(val)","fedAwardYear": "(val)","fedAwardDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Awards [PUT /award/api/v1/awards/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","statusCode": "(val)","templateCode": "(val)","accountNumber": "(val)","approvedEquipmentIndicator": "(val)","approvedForeignTripIndicator": "(val)","subContractIndicator": "(val)","awardEffectiveDate": "(val)","awardExecutionDate": "(val)","beginDate": "(val)","costSharingIndicator": "(val)","indirectCostIndicator": "(val)","modificationNumber": "(val)","nsfCode": "(val)","paymentScheduleIndicator": "(val)","scienceCodeIndicator": "(val)","specialReviewIndicator": "(val)","sponsorAwardNumber": "(val)","transferSponsorIndicator": "(val)","accountTypeCode": "(val)","activityTypeCode": "(val)","awardTypeCode": "(val)","primeSponsorCode": "(val)","awardId": "(val)","basisOfPaymentCode": "(val)","cfdaNumber": "(val)","documentFundingId": "(val)","methodOfPaymentCode": "(val)","preAwardAuthorizedAmount": "(val)","preAwardEffectiveDate": "(val)","preAwardInstitutionalAuthorizedAmount": "(val)","preAwardInstitutionalEffectiveDate": "(val)","procurementPriorityCode": "(val)","proposalNumber": "(val)","specialEbRateOffCampus": "(val)","specialEbRateOnCampus": "(val)","subPlanFlag": "(val)","title": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","awardTransactionTypeCode": "(val)","noticeDate": "(val)","unitNumber": "(val)","financialAccountDocumentNumber": "(val)","financialAccountCreationDate": "(val)","financialChartOfAccountsCode": "(val)","syncChild": "(val)","awardSequenceStatus": "(val)","fainId": "(val)","fedAwardYear": "(val)","fedAwardDate": "(val)","_primaryKey": "(val)"},
              {"awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","statusCode": "(val)","templateCode": "(val)","accountNumber": "(val)","approvedEquipmentIndicator": "(val)","approvedForeignTripIndicator": "(val)","subContractIndicator": "(val)","awardEffectiveDate": "(val)","awardExecutionDate": "(val)","beginDate": "(val)","costSharingIndicator": "(val)","indirectCostIndicator": "(val)","modificationNumber": "(val)","nsfCode": "(val)","paymentScheduleIndicator": "(val)","scienceCodeIndicator": "(val)","specialReviewIndicator": "(val)","sponsorAwardNumber": "(val)","transferSponsorIndicator": "(val)","accountTypeCode": "(val)","activityTypeCode": "(val)","awardTypeCode": "(val)","primeSponsorCode": "(val)","awardId": "(val)","basisOfPaymentCode": "(val)","cfdaNumber": "(val)","documentFundingId": "(val)","methodOfPaymentCode": "(val)","preAwardAuthorizedAmount": "(val)","preAwardEffectiveDate": "(val)","preAwardInstitutionalAuthorizedAmount": "(val)","preAwardInstitutionalEffectiveDate": "(val)","procurementPriorityCode": "(val)","proposalNumber": "(val)","specialEbRateOffCampus": "(val)","specialEbRateOnCampus": "(val)","subPlanFlag": "(val)","title": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","awardTransactionTypeCode": "(val)","noticeDate": "(val)","unitNumber": "(val)","financialAccountDocumentNumber": "(val)","financialAccountCreationDate": "(val)","financialChartOfAccountsCode": "(val)","syncChild": "(val)","awardSequenceStatus": "(val)","fainId": "(val)","fedAwardYear": "(val)","fedAwardDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Awards [POST /award/api/v1/awards/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","statusCode": "(val)","templateCode": "(val)","accountNumber": "(val)","approvedEquipmentIndicator": "(val)","approvedForeignTripIndicator": "(val)","subContractIndicator": "(val)","awardEffectiveDate": "(val)","awardExecutionDate": "(val)","beginDate": "(val)","costSharingIndicator": "(val)","indirectCostIndicator": "(val)","modificationNumber": "(val)","nsfCode": "(val)","paymentScheduleIndicator": "(val)","scienceCodeIndicator": "(val)","specialReviewIndicator": "(val)","sponsorAwardNumber": "(val)","transferSponsorIndicator": "(val)","accountTypeCode": "(val)","activityTypeCode": "(val)","awardTypeCode": "(val)","primeSponsorCode": "(val)","awardId": "(val)","basisOfPaymentCode": "(val)","cfdaNumber": "(val)","documentFundingId": "(val)","methodOfPaymentCode": "(val)","preAwardAuthorizedAmount": "(val)","preAwardEffectiveDate": "(val)","preAwardInstitutionalAuthorizedAmount": "(val)","preAwardInstitutionalEffectiveDate": "(val)","procurementPriorityCode": "(val)","proposalNumber": "(val)","specialEbRateOffCampus": "(val)","specialEbRateOnCampus": "(val)","subPlanFlag": "(val)","title": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","awardTransactionTypeCode": "(val)","noticeDate": "(val)","unitNumber": "(val)","financialAccountDocumentNumber": "(val)","financialAccountCreationDate": "(val)","financialChartOfAccountsCode": "(val)","syncChild": "(val)","awardSequenceStatus": "(val)","fainId": "(val)","fedAwardYear": "(val)","fedAwardDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","statusCode": "(val)","templateCode": "(val)","accountNumber": "(val)","approvedEquipmentIndicator": "(val)","approvedForeignTripIndicator": "(val)","subContractIndicator": "(val)","awardEffectiveDate": "(val)","awardExecutionDate": "(val)","beginDate": "(val)","costSharingIndicator": "(val)","indirectCostIndicator": "(val)","modificationNumber": "(val)","nsfCode": "(val)","paymentScheduleIndicator": "(val)","scienceCodeIndicator": "(val)","specialReviewIndicator": "(val)","sponsorAwardNumber": "(val)","transferSponsorIndicator": "(val)","accountTypeCode": "(val)","activityTypeCode": "(val)","awardTypeCode": "(val)","primeSponsorCode": "(val)","awardId": "(val)","basisOfPaymentCode": "(val)","cfdaNumber": "(val)","documentFundingId": "(val)","methodOfPaymentCode": "(val)","preAwardAuthorizedAmount": "(val)","preAwardEffectiveDate": "(val)","preAwardInstitutionalAuthorizedAmount": "(val)","preAwardInstitutionalEffectiveDate": "(val)","procurementPriorityCode": "(val)","proposalNumber": "(val)","specialEbRateOffCampus": "(val)","specialEbRateOnCampus": "(val)","subPlanFlag": "(val)","title": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","awardTransactionTypeCode": "(val)","noticeDate": "(val)","unitNumber": "(val)","financialAccountDocumentNumber": "(val)","financialAccountCreationDate": "(val)","financialChartOfAccountsCode": "(val)","syncChild": "(val)","awardSequenceStatus": "(val)","fainId": "(val)","fedAwardYear": "(val)","fedAwardDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Awards [POST /award/api/v1/awards/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","statusCode": "(val)","templateCode": "(val)","accountNumber": "(val)","approvedEquipmentIndicator": "(val)","approvedForeignTripIndicator": "(val)","subContractIndicator": "(val)","awardEffectiveDate": "(val)","awardExecutionDate": "(val)","beginDate": "(val)","costSharingIndicator": "(val)","indirectCostIndicator": "(val)","modificationNumber": "(val)","nsfCode": "(val)","paymentScheduleIndicator": "(val)","scienceCodeIndicator": "(val)","specialReviewIndicator": "(val)","sponsorAwardNumber": "(val)","transferSponsorIndicator": "(val)","accountTypeCode": "(val)","activityTypeCode": "(val)","awardTypeCode": "(val)","primeSponsorCode": "(val)","awardId": "(val)","basisOfPaymentCode": "(val)","cfdaNumber": "(val)","documentFundingId": "(val)","methodOfPaymentCode": "(val)","preAwardAuthorizedAmount": "(val)","preAwardEffectiveDate": "(val)","preAwardInstitutionalAuthorizedAmount": "(val)","preAwardInstitutionalEffectiveDate": "(val)","procurementPriorityCode": "(val)","proposalNumber": "(val)","specialEbRateOffCampus": "(val)","specialEbRateOnCampus": "(val)","subPlanFlag": "(val)","title": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","awardTransactionTypeCode": "(val)","noticeDate": "(val)","unitNumber": "(val)","financialAccountDocumentNumber": "(val)","financialAccountCreationDate": "(val)","financialChartOfAccountsCode": "(val)","syncChild": "(val)","awardSequenceStatus": "(val)","fainId": "(val)","fedAwardYear": "(val)","fedAwardDate": "(val)","_primaryKey": "(val)"},
              {"awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","statusCode": "(val)","templateCode": "(val)","accountNumber": "(val)","approvedEquipmentIndicator": "(val)","approvedForeignTripIndicator": "(val)","subContractIndicator": "(val)","awardEffectiveDate": "(val)","awardExecutionDate": "(val)","beginDate": "(val)","costSharingIndicator": "(val)","indirectCostIndicator": "(val)","modificationNumber": "(val)","nsfCode": "(val)","paymentScheduleIndicator": "(val)","scienceCodeIndicator": "(val)","specialReviewIndicator": "(val)","sponsorAwardNumber": "(val)","transferSponsorIndicator": "(val)","accountTypeCode": "(val)","activityTypeCode": "(val)","awardTypeCode": "(val)","primeSponsorCode": "(val)","awardId": "(val)","basisOfPaymentCode": "(val)","cfdaNumber": "(val)","documentFundingId": "(val)","methodOfPaymentCode": "(val)","preAwardAuthorizedAmount": "(val)","preAwardEffectiveDate": "(val)","preAwardInstitutionalAuthorizedAmount": "(val)","preAwardInstitutionalEffectiveDate": "(val)","procurementPriorityCode": "(val)","proposalNumber": "(val)","specialEbRateOffCampus": "(val)","specialEbRateOnCampus": "(val)","subPlanFlag": "(val)","title": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","awardTransactionTypeCode": "(val)","noticeDate": "(val)","unitNumber": "(val)","financialAccountDocumentNumber": "(val)","financialAccountCreationDate": "(val)","financialChartOfAccountsCode": "(val)","syncChild": "(val)","awardSequenceStatus": "(val)","fainId": "(val)","fedAwardYear": "(val)","fedAwardDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","statusCode": "(val)","templateCode": "(val)","accountNumber": "(val)","approvedEquipmentIndicator": "(val)","approvedForeignTripIndicator": "(val)","subContractIndicator": "(val)","awardEffectiveDate": "(val)","awardExecutionDate": "(val)","beginDate": "(val)","costSharingIndicator": "(val)","indirectCostIndicator": "(val)","modificationNumber": "(val)","nsfCode": "(val)","paymentScheduleIndicator": "(val)","scienceCodeIndicator": "(val)","specialReviewIndicator": "(val)","sponsorAwardNumber": "(val)","transferSponsorIndicator": "(val)","accountTypeCode": "(val)","activityTypeCode": "(val)","awardTypeCode": "(val)","primeSponsorCode": "(val)","awardId": "(val)","basisOfPaymentCode": "(val)","cfdaNumber": "(val)","documentFundingId": "(val)","methodOfPaymentCode": "(val)","preAwardAuthorizedAmount": "(val)","preAwardEffectiveDate": "(val)","preAwardInstitutionalAuthorizedAmount": "(val)","preAwardInstitutionalEffectiveDate": "(val)","procurementPriorityCode": "(val)","proposalNumber": "(val)","specialEbRateOffCampus": "(val)","specialEbRateOnCampus": "(val)","subPlanFlag": "(val)","title": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","awardTransactionTypeCode": "(val)","noticeDate": "(val)","unitNumber": "(val)","financialAccountDocumentNumber": "(val)","financialAccountCreationDate": "(val)","financialChartOfAccountsCode": "(val)","syncChild": "(val)","awardSequenceStatus": "(val)","fainId": "(val)","fedAwardYear": "(val)","fedAwardDate": "(val)","_primaryKey": "(val)"},
              {"awardNumber": "(val)","sequenceNumber": "(val)","sponsorCode": "(val)","statusCode": "(val)","templateCode": "(val)","accountNumber": "(val)","approvedEquipmentIndicator": "(val)","approvedForeignTripIndicator": "(val)","subContractIndicator": "(val)","awardEffectiveDate": "(val)","awardExecutionDate": "(val)","beginDate": "(val)","costSharingIndicator": "(val)","indirectCostIndicator": "(val)","modificationNumber": "(val)","nsfCode": "(val)","paymentScheduleIndicator": "(val)","scienceCodeIndicator": "(val)","specialReviewIndicator": "(val)","sponsorAwardNumber": "(val)","transferSponsorIndicator": "(val)","accountTypeCode": "(val)","activityTypeCode": "(val)","awardTypeCode": "(val)","primeSponsorCode": "(val)","awardId": "(val)","basisOfPaymentCode": "(val)","cfdaNumber": "(val)","documentFundingId": "(val)","methodOfPaymentCode": "(val)","preAwardAuthorizedAmount": "(val)","preAwardEffectiveDate": "(val)","preAwardInstitutionalAuthorizedAmount": "(val)","preAwardInstitutionalEffectiveDate": "(val)","procurementPriorityCode": "(val)","proposalNumber": "(val)","specialEbRateOffCampus": "(val)","specialEbRateOnCampus": "(val)","subPlanFlag": "(val)","title": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","awardTransactionTypeCode": "(val)","noticeDate": "(val)","unitNumber": "(val)","financialAccountDocumentNumber": "(val)","financialAccountCreationDate": "(val)","financialChartOfAccountsCode": "(val)","syncChild": "(val)","awardSequenceStatus": "(val)","fainId": "(val)","fedAwardYear": "(val)","fedAwardDate": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Awards by Key [DELETE /award/api/v1/awards/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Awards [DELETE /award/api/v1/awards/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Awards with Matching [DELETE /award/api/v1/awards/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + awardNumber
            + sequenceNumber
            + sponsorCode
            + statusCode
            + templateCode
            + accountNumber
            + approvedEquipmentIndicator
            + approvedForeignTripIndicator
            + subContractIndicator
            + awardEffectiveDate
            + awardExecutionDate
            + beginDate
            + costSharingIndicator
            + indirectCostIndicator
            + modificationNumber
            + nsfCode
            + paymentScheduleIndicator
            + scienceCodeIndicator
            + specialReviewIndicator
            + sponsorAwardNumber
            + transferSponsorIndicator
            + accountTypeCode
            + activityTypeCode
            + awardTypeCode
            + primeSponsorCode
            + awardId
            + basisOfPaymentCode
            + cfdaNumber
            + documentFundingId
            + methodOfPaymentCode
            + preAwardAuthorizedAmount
            + preAwardEffectiveDate
            + preAwardInstitutionalAuthorizedAmount
            + preAwardInstitutionalEffectiveDate
            + procurementPriorityCode
            + proposalNumber
            + specialEbRateOffCampus
            + specialEbRateOnCampus
            + subPlanFlag
            + title
            + archiveLocation
            + closeoutDate
            + awardTransactionTypeCode
            + noticeDate
            + unitNumber
            + financialAccountDocumentNumber
            + financialAccountCreationDate
            + financialChartOfAccountsCode
            + syncChild
            + awardSequenceStatus
            + fainId
            + fedAwardYear
            + fedAwardDate


+ Response 204
