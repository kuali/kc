## Institutional Proposals [/instprop/api/v1/institutional-proposals/]

### Get Institutional Proposals by Key [GET /instprop/api/v1/institutional-proposals/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"primeSponsorCode": "(val)","initialContractAdmin": "(val)","ipReviewActivityIndicator": "(val)","currentAwardNumber": "(val)","cfdaNumber": "(val)","opportunity": "(val)","proposalSequenceStatus": "(val)","createTimeStamp": "(val)","awardTypeCode": "(val)","unitNumber": "(val)","proposalId": "(val)","proposalNumber": "(val)","sponsorProposalNumber": "(val)","sequenceNumber": "(val)","proposalTypeCode": "(val)","currentAccountNumber": "(val)","title": "(val)","sponsorCode": "(val)","rolodexId": "(val)","noticeOfOpportunityCode": "(val)","gradStudHeadcount": "(val)","gradStudPersonMonths": "(val)","typeOfAccount": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","numberOfCopies": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","deadlineType": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","subcontractFlag": "(val)","costSharingIndicator": "(val)","idcRateIndicator": "(val)","specialReviewIndicator": "(val)","statusCode": "(val)","scienceCodeIndicator": "(val)","nsfCode": "(val)","_primaryKey": "(val)"}

### Get All Institutional Proposals [GET /instprop/api/v1/institutional-proposals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"primeSponsorCode": "(val)","initialContractAdmin": "(val)","ipReviewActivityIndicator": "(val)","currentAwardNumber": "(val)","cfdaNumber": "(val)","opportunity": "(val)","proposalSequenceStatus": "(val)","createTimeStamp": "(val)","awardTypeCode": "(val)","unitNumber": "(val)","proposalId": "(val)","proposalNumber": "(val)","sponsorProposalNumber": "(val)","sequenceNumber": "(val)","proposalTypeCode": "(val)","currentAccountNumber": "(val)","title": "(val)","sponsorCode": "(val)","rolodexId": "(val)","noticeOfOpportunityCode": "(val)","gradStudHeadcount": "(val)","gradStudPersonMonths": "(val)","typeOfAccount": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","numberOfCopies": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","deadlineType": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","subcontractFlag": "(val)","costSharingIndicator": "(val)","idcRateIndicator": "(val)","specialReviewIndicator": "(val)","statusCode": "(val)","scienceCodeIndicator": "(val)","nsfCode": "(val)","_primaryKey": "(val)"},
              {"primeSponsorCode": "(val)","initialContractAdmin": "(val)","ipReviewActivityIndicator": "(val)","currentAwardNumber": "(val)","cfdaNumber": "(val)","opportunity": "(val)","proposalSequenceStatus": "(val)","createTimeStamp": "(val)","awardTypeCode": "(val)","unitNumber": "(val)","proposalId": "(val)","proposalNumber": "(val)","sponsorProposalNumber": "(val)","sequenceNumber": "(val)","proposalTypeCode": "(val)","currentAccountNumber": "(val)","title": "(val)","sponsorCode": "(val)","rolodexId": "(val)","noticeOfOpportunityCode": "(val)","gradStudHeadcount": "(val)","gradStudPersonMonths": "(val)","typeOfAccount": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","numberOfCopies": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","deadlineType": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","subcontractFlag": "(val)","costSharingIndicator": "(val)","idcRateIndicator": "(val)","specialReviewIndicator": "(val)","statusCode": "(val)","scienceCodeIndicator": "(val)","nsfCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Institutional Proposals with Filtering [GET /instprop/api/v1/institutional-proposals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + primeSponsorCode
            + initialContractAdmin
            + ipReviewActivityIndicator
            + currentAwardNumber
            + cfdaNumber
            + opportunity
            + proposalSequenceStatus
            + createTimeStamp
            + awardTypeCode
            + unitNumber
            + proposalId
            + proposalNumber
            + sponsorProposalNumber
            + sequenceNumber
            + proposalTypeCode
            + currentAccountNumber
            + title
            + sponsorCode
            + rolodexId
            + noticeOfOpportunityCode
            + gradStudHeadcount
            + gradStudPersonMonths
            + typeOfAccount
            + activityTypeCode
            + requestedStartDateInitial
            + requestedStartDateTotal
            + requestedEndDateInitial
            + requestedEndDateTotal
            + fiscalMonth
            + fiscalYear
            + totalDirectCostInitial
            + totalDirectCostTotal
            + totalIndirectCostInitial
            + totalIndirectCostTotal
            + numberOfCopies
            + deadlineDate
            + deadlineTime
            + deadlineType
            + mailBy
            + mailType
            + mailAccountNumber
            + mailDescription
            + subcontractFlag
            + costSharingIndicator
            + idcRateIndicator
            + specialReviewIndicator
            + statusCode
            + scienceCodeIndicator
            + nsfCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"primeSponsorCode": "(val)","initialContractAdmin": "(val)","ipReviewActivityIndicator": "(val)","currentAwardNumber": "(val)","cfdaNumber": "(val)","opportunity": "(val)","proposalSequenceStatus": "(val)","createTimeStamp": "(val)","awardTypeCode": "(val)","unitNumber": "(val)","proposalId": "(val)","proposalNumber": "(val)","sponsorProposalNumber": "(val)","sequenceNumber": "(val)","proposalTypeCode": "(val)","currentAccountNumber": "(val)","title": "(val)","sponsorCode": "(val)","rolodexId": "(val)","noticeOfOpportunityCode": "(val)","gradStudHeadcount": "(val)","gradStudPersonMonths": "(val)","typeOfAccount": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","numberOfCopies": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","deadlineType": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","subcontractFlag": "(val)","costSharingIndicator": "(val)","idcRateIndicator": "(val)","specialReviewIndicator": "(val)","statusCode": "(val)","scienceCodeIndicator": "(val)","nsfCode": "(val)","_primaryKey": "(val)"},
              {"primeSponsorCode": "(val)","initialContractAdmin": "(val)","ipReviewActivityIndicator": "(val)","currentAwardNumber": "(val)","cfdaNumber": "(val)","opportunity": "(val)","proposalSequenceStatus": "(val)","createTimeStamp": "(val)","awardTypeCode": "(val)","unitNumber": "(val)","proposalId": "(val)","proposalNumber": "(val)","sponsorProposalNumber": "(val)","sequenceNumber": "(val)","proposalTypeCode": "(val)","currentAccountNumber": "(val)","title": "(val)","sponsorCode": "(val)","rolodexId": "(val)","noticeOfOpportunityCode": "(val)","gradStudHeadcount": "(val)","gradStudPersonMonths": "(val)","typeOfAccount": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","numberOfCopies": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","deadlineType": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","subcontractFlag": "(val)","costSharingIndicator": "(val)","idcRateIndicator": "(val)","specialReviewIndicator": "(val)","statusCode": "(val)","scienceCodeIndicator": "(val)","nsfCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Institutional Proposals [GET /instprop/api/v1/institutional-proposals/]
	 
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
		
### Get Blueprint API specification for Institutional Proposals [GET /instprop/api/v1/institutional-proposals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Institutional Proposals.md"
            transfer-encoding:chunked


### Update Institutional Proposals [PUT /instprop/api/v1/institutional-proposals/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"primeSponsorCode": "(val)","initialContractAdmin": "(val)","ipReviewActivityIndicator": "(val)","currentAwardNumber": "(val)","cfdaNumber": "(val)","opportunity": "(val)","proposalSequenceStatus": "(val)","createTimeStamp": "(val)","awardTypeCode": "(val)","unitNumber": "(val)","proposalId": "(val)","proposalNumber": "(val)","sponsorProposalNumber": "(val)","sequenceNumber": "(val)","proposalTypeCode": "(val)","currentAccountNumber": "(val)","title": "(val)","sponsorCode": "(val)","rolodexId": "(val)","noticeOfOpportunityCode": "(val)","gradStudHeadcount": "(val)","gradStudPersonMonths": "(val)","typeOfAccount": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","numberOfCopies": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","deadlineType": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","subcontractFlag": "(val)","costSharingIndicator": "(val)","idcRateIndicator": "(val)","specialReviewIndicator": "(val)","statusCode": "(val)","scienceCodeIndicator": "(val)","nsfCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Institutional Proposals [PUT /instprop/api/v1/institutional-proposals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"primeSponsorCode": "(val)","initialContractAdmin": "(val)","ipReviewActivityIndicator": "(val)","currentAwardNumber": "(val)","cfdaNumber": "(val)","opportunity": "(val)","proposalSequenceStatus": "(val)","createTimeStamp": "(val)","awardTypeCode": "(val)","unitNumber": "(val)","proposalId": "(val)","proposalNumber": "(val)","sponsorProposalNumber": "(val)","sequenceNumber": "(val)","proposalTypeCode": "(val)","currentAccountNumber": "(val)","title": "(val)","sponsorCode": "(val)","rolodexId": "(val)","noticeOfOpportunityCode": "(val)","gradStudHeadcount": "(val)","gradStudPersonMonths": "(val)","typeOfAccount": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","numberOfCopies": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","deadlineType": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","subcontractFlag": "(val)","costSharingIndicator": "(val)","idcRateIndicator": "(val)","specialReviewIndicator": "(val)","statusCode": "(val)","scienceCodeIndicator": "(val)","nsfCode": "(val)","_primaryKey": "(val)"},
              {"primeSponsorCode": "(val)","initialContractAdmin": "(val)","ipReviewActivityIndicator": "(val)","currentAwardNumber": "(val)","cfdaNumber": "(val)","opportunity": "(val)","proposalSequenceStatus": "(val)","createTimeStamp": "(val)","awardTypeCode": "(val)","unitNumber": "(val)","proposalId": "(val)","proposalNumber": "(val)","sponsorProposalNumber": "(val)","sequenceNumber": "(val)","proposalTypeCode": "(val)","currentAccountNumber": "(val)","title": "(val)","sponsorCode": "(val)","rolodexId": "(val)","noticeOfOpportunityCode": "(val)","gradStudHeadcount": "(val)","gradStudPersonMonths": "(val)","typeOfAccount": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","numberOfCopies": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","deadlineType": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","subcontractFlag": "(val)","costSharingIndicator": "(val)","idcRateIndicator": "(val)","specialReviewIndicator": "(val)","statusCode": "(val)","scienceCodeIndicator": "(val)","nsfCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Institutional Proposals [POST /instprop/api/v1/institutional-proposals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"primeSponsorCode": "(val)","initialContractAdmin": "(val)","ipReviewActivityIndicator": "(val)","currentAwardNumber": "(val)","cfdaNumber": "(val)","opportunity": "(val)","proposalSequenceStatus": "(val)","createTimeStamp": "(val)","awardTypeCode": "(val)","unitNumber": "(val)","proposalId": "(val)","proposalNumber": "(val)","sponsorProposalNumber": "(val)","sequenceNumber": "(val)","proposalTypeCode": "(val)","currentAccountNumber": "(val)","title": "(val)","sponsorCode": "(val)","rolodexId": "(val)","noticeOfOpportunityCode": "(val)","gradStudHeadcount": "(val)","gradStudPersonMonths": "(val)","typeOfAccount": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","numberOfCopies": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","deadlineType": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","subcontractFlag": "(val)","costSharingIndicator": "(val)","idcRateIndicator": "(val)","specialReviewIndicator": "(val)","statusCode": "(val)","scienceCodeIndicator": "(val)","nsfCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"primeSponsorCode": "(val)","initialContractAdmin": "(val)","ipReviewActivityIndicator": "(val)","currentAwardNumber": "(val)","cfdaNumber": "(val)","opportunity": "(val)","proposalSequenceStatus": "(val)","createTimeStamp": "(val)","awardTypeCode": "(val)","unitNumber": "(val)","proposalId": "(val)","proposalNumber": "(val)","sponsorProposalNumber": "(val)","sequenceNumber": "(val)","proposalTypeCode": "(val)","currentAccountNumber": "(val)","title": "(val)","sponsorCode": "(val)","rolodexId": "(val)","noticeOfOpportunityCode": "(val)","gradStudHeadcount": "(val)","gradStudPersonMonths": "(val)","typeOfAccount": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","numberOfCopies": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","deadlineType": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","subcontractFlag": "(val)","costSharingIndicator": "(val)","idcRateIndicator": "(val)","specialReviewIndicator": "(val)","statusCode": "(val)","scienceCodeIndicator": "(val)","nsfCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Institutional Proposals [POST /instprop/api/v1/institutional-proposals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"primeSponsorCode": "(val)","initialContractAdmin": "(val)","ipReviewActivityIndicator": "(val)","currentAwardNumber": "(val)","cfdaNumber": "(val)","opportunity": "(val)","proposalSequenceStatus": "(val)","createTimeStamp": "(val)","awardTypeCode": "(val)","unitNumber": "(val)","proposalId": "(val)","proposalNumber": "(val)","sponsorProposalNumber": "(val)","sequenceNumber": "(val)","proposalTypeCode": "(val)","currentAccountNumber": "(val)","title": "(val)","sponsorCode": "(val)","rolodexId": "(val)","noticeOfOpportunityCode": "(val)","gradStudHeadcount": "(val)","gradStudPersonMonths": "(val)","typeOfAccount": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","numberOfCopies": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","deadlineType": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","subcontractFlag": "(val)","costSharingIndicator": "(val)","idcRateIndicator": "(val)","specialReviewIndicator": "(val)","statusCode": "(val)","scienceCodeIndicator": "(val)","nsfCode": "(val)","_primaryKey": "(val)"},
              {"primeSponsorCode": "(val)","initialContractAdmin": "(val)","ipReviewActivityIndicator": "(val)","currentAwardNumber": "(val)","cfdaNumber": "(val)","opportunity": "(val)","proposalSequenceStatus": "(val)","createTimeStamp": "(val)","awardTypeCode": "(val)","unitNumber": "(val)","proposalId": "(val)","proposalNumber": "(val)","sponsorProposalNumber": "(val)","sequenceNumber": "(val)","proposalTypeCode": "(val)","currentAccountNumber": "(val)","title": "(val)","sponsorCode": "(val)","rolodexId": "(val)","noticeOfOpportunityCode": "(val)","gradStudHeadcount": "(val)","gradStudPersonMonths": "(val)","typeOfAccount": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","numberOfCopies": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","deadlineType": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","subcontractFlag": "(val)","costSharingIndicator": "(val)","idcRateIndicator": "(val)","specialReviewIndicator": "(val)","statusCode": "(val)","scienceCodeIndicator": "(val)","nsfCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"primeSponsorCode": "(val)","initialContractAdmin": "(val)","ipReviewActivityIndicator": "(val)","currentAwardNumber": "(val)","cfdaNumber": "(val)","opportunity": "(val)","proposalSequenceStatus": "(val)","createTimeStamp": "(val)","awardTypeCode": "(val)","unitNumber": "(val)","proposalId": "(val)","proposalNumber": "(val)","sponsorProposalNumber": "(val)","sequenceNumber": "(val)","proposalTypeCode": "(val)","currentAccountNumber": "(val)","title": "(val)","sponsorCode": "(val)","rolodexId": "(val)","noticeOfOpportunityCode": "(val)","gradStudHeadcount": "(val)","gradStudPersonMonths": "(val)","typeOfAccount": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","numberOfCopies": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","deadlineType": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","subcontractFlag": "(val)","costSharingIndicator": "(val)","idcRateIndicator": "(val)","specialReviewIndicator": "(val)","statusCode": "(val)","scienceCodeIndicator": "(val)","nsfCode": "(val)","_primaryKey": "(val)"},
              {"primeSponsorCode": "(val)","initialContractAdmin": "(val)","ipReviewActivityIndicator": "(val)","currentAwardNumber": "(val)","cfdaNumber": "(val)","opportunity": "(val)","proposalSequenceStatus": "(val)","createTimeStamp": "(val)","awardTypeCode": "(val)","unitNumber": "(val)","proposalId": "(val)","proposalNumber": "(val)","sponsorProposalNumber": "(val)","sequenceNumber": "(val)","proposalTypeCode": "(val)","currentAccountNumber": "(val)","title": "(val)","sponsorCode": "(val)","rolodexId": "(val)","noticeOfOpportunityCode": "(val)","gradStudHeadcount": "(val)","gradStudPersonMonths": "(val)","typeOfAccount": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","numberOfCopies": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","deadlineType": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","subcontractFlag": "(val)","costSharingIndicator": "(val)","idcRateIndicator": "(val)","specialReviewIndicator": "(val)","statusCode": "(val)","scienceCodeIndicator": "(val)","nsfCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Institutional Proposals by Key [DELETE /instprop/api/v1/institutional-proposals/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposals [DELETE /instprop/api/v1/institutional-proposals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Institutional Proposals with Matching [DELETE /instprop/api/v1/institutional-proposals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + primeSponsorCode
            + initialContractAdmin
            + ipReviewActivityIndicator
            + currentAwardNumber
            + cfdaNumber
            + opportunity
            + proposalSequenceStatus
            + createTimeStamp
            + awardTypeCode
            + unitNumber
            + proposalId
            + proposalNumber
            + sponsorProposalNumber
            + sequenceNumber
            + proposalTypeCode
            + currentAccountNumber
            + title
            + sponsorCode
            + rolodexId
            + noticeOfOpportunityCode
            + gradStudHeadcount
            + gradStudPersonMonths
            + typeOfAccount
            + activityTypeCode
            + requestedStartDateInitial
            + requestedStartDateTotal
            + requestedEndDateInitial
            + requestedEndDateTotal
            + fiscalMonth
            + fiscalYear
            + totalDirectCostInitial
            + totalDirectCostTotal
            + totalIndirectCostInitial
            + totalIndirectCostTotal
            + numberOfCopies
            + deadlineDate
            + deadlineTime
            + deadlineType
            + mailBy
            + mailType
            + mailAccountNumber
            + mailDescription
            + subcontractFlag
            + costSharingIndicator
            + idcRateIndicator
            + specialReviewIndicator
            + statusCode
            + scienceCodeIndicator
            + nsfCode


+ Response 204
