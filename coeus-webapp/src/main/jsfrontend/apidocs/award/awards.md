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
    
+ Parameters

    + awardNumber (optional) - Award ID. Maximum length is 12.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.
    + sponsorCode (optional) - The identification number of the organization or agency that is providing support for the sponsored project. Maximum length is 6.
    + statusCode (optional) - Award Status. Maximum length is 3.
    + templateCode (optional) - Pre-defined Sponsor Template to sync. Maximum length is 6.
    + accountNumber (optional) - Account ID. Maximum length is 16.
    + approvedEquipmentIndicator (optional) - 
    + approvedForeignTripIndicator (optional) - 
    + subContractIndicator (optional) - 
    + awardEffectiveDate (optional) - Project Start Date. Maximum length is 10.
    + awardExecutionDate (optional) - The Execution Date of the Budget. Maximum length is 10.
    + beginDate (optional) - Modification Date. Maximum length is 10.
    + costSharingIndicator (optional) - 
    + indirectCostIndicator (optional) - 
    + modificationNumber (optional) - Modification ID. Maximum length is 50.
    + nsfCode (optional) - A number used to indicate the type of research activity as defined by the National Science Foundation. NSF codes are not specific for NSF proposals and/or awards. Maximum length is 15.
    + paymentScheduleIndicator (optional) - 
    + scienceCodeIndicator (optional) - 
    + specialReviewIndicator (optional) - 
    + sponsorAwardNumber (optional) - Sponsor Award ID. Maximum length is 70.
    + transferSponsorIndicator (optional) - 
    + accountTypeCode (optional) - Account Type. Maximum length is 3.
    + activityTypeCode (optional) - The type of activity proposed, e.g. organized research, instruction, etc. Maximum length is 3.
    + awardTypeCode (optional) - Award Type. Maximum length is 3.
    + primeSponsorCode (optional) - The identification number of the organization or agency that is providing support for the sponsored project. Maximum length is 6.
    + awardId (optional) - Award Id. Maximum length is 12.
    + basisOfPaymentCode (optional) - Payment Basis. Maximum length is 3.
    + cfdaNumber (optional) - A unique identifier for the sponsor and the funding opportunity announcement.  AKA "Catalogue of Federal Domestic Assistance Number." The format for this CFDA Number is XX.XXX. Maximum length is 7.
    + documentFundingId (optional) - Document Funding ID. Maximum length is 20.
    + methodOfPaymentCode (optional) - Payment Method. Maximum length is 3.
    + preAwardAuthorizedAmount (optional) - Pre Award Sponsor Authorized Amount. Maximum length is 12.
    + preAwardEffectiveDate (optional) - Pre Award Sponsor Effective Date. Maximum length is 21.
    + preAwardInstitutionalAuthorizedAmount (optional) - Pre Award Institutional Authorized Amount. Maximum length is 12.
    + preAwardInstitutionalEffectiveDate (optional) - Pre Award Institutional Effective Date. Maximum length is 21.
    + procurementPriorityCode (optional) - Competing Renewal. Maximum length is 6.
    + proposalNumber (optional) - 
    + specialEbRateOffCampus (optional) - Special EB Rate Off CampusContract. Maximum length is 10.
    + specialEbRateOnCampus (optional) - Special EB Rate On CampusContract. Maximum length is 10.
    + subPlanFlag (optional) - Small Business Subcontracting Plan. Maximum length is 1.
    + title (optional) - The proposed title of the project. Maximum length is 200.
    + archiveLocation (optional) - Archive Location. Maximum length is 500.
    + closeoutDate (optional) - Archive Date. Maximum length is 21.
    + awardTransactionTypeCode (optional) - Transaction Type. Maximum length is 3.
    + noticeDate (optional) - Notice Date. Maximum length is 21.
    + unitNumber (optional) - The lead unit number for the Award. Maximum length is 150.
    + financialAccountDocumentNumber (optional) - Document number returned by financial service when an account is created. Maximum length is 20.
    + financialAccountCreationDate (optional) - Financial account creation date. Maximum length is 20.
    + financialChartOfAccountsCode (optional) - Chart of accounts for a Financial Account. Maximum length is 45.
    + syncChild (optional) - 
    + awardSequenceStatus (optional) - 
    + fainId (optional) - FAIN ID. Maximum length is 32.
    + fedAwardYear (optional) - Federal Award Year. Maximum length is 4.
    + fedAwardDate (optional) - Federal Award Date. Maximum length is 21.

            
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
			
### Get Schema for Awards [GET /award/api/v1/awards/]
	                                          
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
    
            {"columns":["awardNumber","sequenceNumber","sponsorCode","statusCode","templateCode","accountNumber","approvedEquipmentIndicator","approvedForeignTripIndicator","subContractIndicator","awardEffectiveDate","awardExecutionDate","beginDate","costSharingIndicator","indirectCostIndicator","modificationNumber","nsfCode","paymentScheduleIndicator","scienceCodeIndicator","specialReviewIndicator","sponsorAwardNumber","transferSponsorIndicator","accountTypeCode","activityTypeCode","awardTypeCode","primeSponsorCode","awardId","basisOfPaymentCode","cfdaNumber","documentFundingId","methodOfPaymentCode","preAwardAuthorizedAmount","preAwardEffectiveDate","preAwardInstitutionalAuthorizedAmount","preAwardInstitutionalEffectiveDate","procurementPriorityCode","proposalNumber","specialEbRateOffCampus","specialEbRateOnCampus","subPlanFlag","title","archiveLocation","closeoutDate","awardTransactionTypeCode","noticeDate","unitNumber","financialAccountDocumentNumber","financialAccountCreationDate","financialChartOfAccountsCode","syncChild","awardSequenceStatus","fainId","fedAwardYear","fedAwardDate"],"primaryKey":"awardId"}
		
### Get Blueprint API specification for Awards [GET /award/api/v1/awards/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

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

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Awards with Matching [DELETE /award/api/v1/awards/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + awardNumber (optional) - Award ID. Maximum length is 12.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.
    + sponsorCode (optional) - The identification number of the organization or agency that is providing support for the sponsored project. Maximum length is 6.
    + statusCode (optional) - Award Status. Maximum length is 3.
    + templateCode (optional) - Pre-defined Sponsor Template to sync. Maximum length is 6.
    + accountNumber (optional) - Account ID. Maximum length is 16.
    + approvedEquipmentIndicator (optional) - 
    + approvedForeignTripIndicator (optional) - 
    + subContractIndicator (optional) - 
    + awardEffectiveDate (optional) - Project Start Date. Maximum length is 10.
    + awardExecutionDate (optional) - The Execution Date of the Budget. Maximum length is 10.
    + beginDate (optional) - Modification Date. Maximum length is 10.
    + costSharingIndicator (optional) - 
    + indirectCostIndicator (optional) - 
    + modificationNumber (optional) - Modification ID. Maximum length is 50.
    + nsfCode (optional) - A number used to indicate the type of research activity as defined by the National Science Foundation. NSF codes are not specific for NSF proposals and/or awards. Maximum length is 15.
    + paymentScheduleIndicator (optional) - 
    + scienceCodeIndicator (optional) - 
    + specialReviewIndicator (optional) - 
    + sponsorAwardNumber (optional) - Sponsor Award ID. Maximum length is 70.
    + transferSponsorIndicator (optional) - 
    + accountTypeCode (optional) - Account Type. Maximum length is 3.
    + activityTypeCode (optional) - The type of activity proposed, e.g. organized research, instruction, etc. Maximum length is 3.
    + awardTypeCode (optional) - Award Type. Maximum length is 3.
    + primeSponsorCode (optional) - The identification number of the organization or agency that is providing support for the sponsored project. Maximum length is 6.
    + awardId (optional) - Award Id. Maximum length is 12.
    + basisOfPaymentCode (optional) - Payment Basis. Maximum length is 3.
    + cfdaNumber (optional) - A unique identifier for the sponsor and the funding opportunity announcement.  AKA "Catalogue of Federal Domestic Assistance Number." The format for this CFDA Number is XX.XXX. Maximum length is 7.
    + documentFundingId (optional) - Document Funding ID. Maximum length is 20.
    + methodOfPaymentCode (optional) - Payment Method. Maximum length is 3.
    + preAwardAuthorizedAmount (optional) - Pre Award Sponsor Authorized Amount. Maximum length is 12.
    + preAwardEffectiveDate (optional) - Pre Award Sponsor Effective Date. Maximum length is 21.
    + preAwardInstitutionalAuthorizedAmount (optional) - Pre Award Institutional Authorized Amount. Maximum length is 12.
    + preAwardInstitutionalEffectiveDate (optional) - Pre Award Institutional Effective Date. Maximum length is 21.
    + procurementPriorityCode (optional) - Competing Renewal. Maximum length is 6.
    + proposalNumber (optional) - 
    + specialEbRateOffCampus (optional) - Special EB Rate Off CampusContract. Maximum length is 10.
    + specialEbRateOnCampus (optional) - Special EB Rate On CampusContract. Maximum length is 10.
    + subPlanFlag (optional) - Small Business Subcontracting Plan. Maximum length is 1.
    + title (optional) - The proposed title of the project. Maximum length is 200.
    + archiveLocation (optional) - Archive Location. Maximum length is 500.
    + closeoutDate (optional) - Archive Date. Maximum length is 21.
    + awardTransactionTypeCode (optional) - Transaction Type. Maximum length is 3.
    + noticeDate (optional) - Notice Date. Maximum length is 21.
    + unitNumber (optional) - The lead unit number for the Award. Maximum length is 150.
    + financialAccountDocumentNumber (optional) - Document number returned by financial service when an account is created. Maximum length is 20.
    + financialAccountCreationDate (optional) - Financial account creation date. Maximum length is 20.
    + financialChartOfAccountsCode (optional) - Chart of accounts for a Financial Account. Maximum length is 45.
    + syncChild (optional) - 
    + awardSequenceStatus (optional) - 
    + fainId (optional) - FAIN ID. Maximum length is 32.
    + fedAwardYear (optional) - Federal Award Year. Maximum length is 4.
    + fedAwardDate (optional) - Federal Award Date. Maximum length is 21.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
