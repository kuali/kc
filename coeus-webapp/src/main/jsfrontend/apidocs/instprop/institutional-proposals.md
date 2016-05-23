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
    
            {"primeSponsorCode": "(val)","initialContractAdmin": "(val)","ipReviewActivityIndicator": "(val)","currentAwardNumber": "(val)","cfdaNumber": "(val)","opportunity": "(val)","proposalSequenceStatus": "(val)","createTimestamp": "(val)","awardTypeCode": "(val)","unitNumber": "(val)","proposalId": "(val)","proposalNumber": "(val)","sponsorProposalNumber": "(val)","sequenceNumber": "(val)","proposalTypeCode": "(val)","currentAccountNumber": "(val)","title": "(val)","sponsorCode": "(val)","rolodexId": "(val)","noticeOfOpportunityCode": "(val)","gradStudHeadcount": "(val)","gradStudPersonMonths": "(val)","typeOfAccount": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","numberOfCopies": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","deadlineType": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","subcontractFlag": "(val)","costSharingIndicator": "(val)","idcRateIndicator": "(val)","specialReviewIndicator": "(val)","statusCode": "(val)","scienceCodeIndicator": "(val)","nsfCode": "(val)","institutionalProposalDocument.documentNumber": "(val)","_primaryKey": "(val)"}

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
              {"primeSponsorCode": "(val)","initialContractAdmin": "(val)","ipReviewActivityIndicator": "(val)","currentAwardNumber": "(val)","cfdaNumber": "(val)","opportunity": "(val)","proposalSequenceStatus": "(val)","createTimestamp": "(val)","awardTypeCode": "(val)","unitNumber": "(val)","proposalId": "(val)","proposalNumber": "(val)","sponsorProposalNumber": "(val)","sequenceNumber": "(val)","proposalTypeCode": "(val)","currentAccountNumber": "(val)","title": "(val)","sponsorCode": "(val)","rolodexId": "(val)","noticeOfOpportunityCode": "(val)","gradStudHeadcount": "(val)","gradStudPersonMonths": "(val)","typeOfAccount": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","numberOfCopies": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","deadlineType": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","subcontractFlag": "(val)","costSharingIndicator": "(val)","idcRateIndicator": "(val)","specialReviewIndicator": "(val)","statusCode": "(val)","scienceCodeIndicator": "(val)","nsfCode": "(val)","institutionalProposalDocument.documentNumber": "(val)","_primaryKey": "(val)"},
              {"primeSponsorCode": "(val)","initialContractAdmin": "(val)","ipReviewActivityIndicator": "(val)","currentAwardNumber": "(val)","cfdaNumber": "(val)","opportunity": "(val)","proposalSequenceStatus": "(val)","createTimestamp": "(val)","awardTypeCode": "(val)","unitNumber": "(val)","proposalId": "(val)","proposalNumber": "(val)","sponsorProposalNumber": "(val)","sequenceNumber": "(val)","proposalTypeCode": "(val)","currentAccountNumber": "(val)","title": "(val)","sponsorCode": "(val)","rolodexId": "(val)","noticeOfOpportunityCode": "(val)","gradStudHeadcount": "(val)","gradStudPersonMonths": "(val)","typeOfAccount": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","numberOfCopies": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","deadlineType": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","subcontractFlag": "(val)","costSharingIndicator": "(val)","idcRateIndicator": "(val)","specialReviewIndicator": "(val)","statusCode": "(val)","scienceCodeIndicator": "(val)","nsfCode": "(val)","institutionalProposalDocument.documentNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Institutional Proposals with Filtering [GET /instprop/api/v1/institutional-proposals/]
    
+ Parameters

    + primeSponsorCode (optional) - Prime Sponsor Code. Maximum length is 6.
    + initialContractAdmin (optional) - Initial Contract Admin. Maximum length is 30.
    + ipReviewActivityIndicator (optional) - Ip Review Activity Indicator. Maximum length is 2.
    + currentAwardNumber (optional) - Current Award Number. Maximum length is 12.
    + cfdaNumber (optional) - CFDA Number. Maximum length is 7.
    + opportunity (optional) - Opportunity. Maximum length is 50.
    + proposalSequenceStatus (optional) - 
    + createTimestamp (optional) - Proposal Create Date. Maximum length is 10.
    + awardTypeCode (optional) - Award Type Code. Maximum length is 15.
    + unitNumber (optional) - 
    + proposalId (optional) - Institutional Proposal ID. Maximum length is 22.
    + proposalNumber (optional) - Institutional Proposal Number. Maximum length is 8.
    + sponsorProposalNumber (optional) - Sponsor Proposal ID. Maximum length is 70.
    + sequenceNumber (optional) - Institutional Proposal Version. Maximum length is 22.
    + proposalTypeCode (optional) - Proposal Type Code. Maximum length is 3.
    + currentAccountNumber (optional) - Current Account Number. Maximum length is 7.
    + title (optional) - Title. Maximum length is 200.
    + sponsorCode (optional) - Sponsor ID. Maximum length is 6.
    + rolodexId (optional) - Rolodex ID. Maximum length is 22.
    + noticeOfOpportunityCode (optional) - Notice Of Opportunity Code. Maximum length is 3.
    + gradStudHeadcount (optional) - Grad Stud Headcount. Maximum length is 3.
    + gradStudPersonMonths (optional) - Grad Stud Person Months. Maximum length is 6.
    + typeOfAccount (optional) - Type Of Account. Maximum length is 1.
    + activityTypeCode (optional) - Activity Type Code. Maximum length is 3.
    + requestedStartDateInitial (optional) - Requested Start Date Initial. Maximum length is 10.
    + requestedStartDateTotal (optional) - Requested Start Date Total. Maximum length is 10.
    + requestedEndDateInitial (optional) - Requested End Date Initial. Maximum length is 10.
    + requestedEndDateTotal (optional) - Requested End Date Total. Maximum length is 10.
    + fiscalMonth (optional) - Fiscal Month. Maximum length is 2.
    + fiscalYear (optional) - Fiscal Year. Maximum length is 4.
    + totalDirectCostInitial (optional) - Total Direct Cost Initial. Maximum length is 22.
    + totalDirectCostTotal (optional) - Total Direct Cost Total. Maximum length is 22.
    + totalIndirectCostInitial (optional) - Total Indirect Cost Initial. Maximum length is 22.
    + totalIndirectCostTotal (optional) - Total Indirect Cost Total. Maximum length is 22.
    + numberOfCopies (optional) - The number of paper copies of the proposal that should be mailed to the agency/sponsor. Maximum length is 2.
    + deadlineDate (optional) - The date in which the proposal is due to the sponsoring agency.  Additional deadlines may be set by the office authorized to submit proposals on behalf of the institution (e.g. sponsored programs, etc.). Maximum length is 21.
    + deadlineTime (optional) - The time in which the proposal is due to the sponsoring agency.  Additional deadlines may be set by the office authorized to submit proposals on behalf of the institution (e.g. sponsored programs, etc.). Maximum length is 15.
    + deadlineType (optional) - Indicates the type of proposal deadline. Maximum length is 3.
    + mailBy (optional) - This field determines whether the Sponsored Programs Office or the department will be mailing the proposal to the agency/sponsor. Maximum length is 3.
    + mailType (optional) - The method by which the proposal will be delivered to the agency/sponsor. Maximum length is 1.
    + mailAccountNumber (optional) - The internal school account number that should be charged the mailing costs. Maximum length is 7.
    + mailDescription (optional) - Any information that will assist in the delivery of the proposal. Maximum length is 80.
    + subcontractFlag (optional) - Subcontract Flag. Maximum length is 3.
    + costSharingIndicator (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 1.
    + idcRateIndicator (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 1.
    + specialReviewIndicator (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 1.
    + statusCode (optional) - Status Code. Maximum length is 3.
    + scienceCodeIndicator (optional) - 
    + nsfCode (optional) - NSF Code. Maximum length is 15.
    + institutionalProposalDocument.documentNumber (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"primeSponsorCode": "(val)","initialContractAdmin": "(val)","ipReviewActivityIndicator": "(val)","currentAwardNumber": "(val)","cfdaNumber": "(val)","opportunity": "(val)","proposalSequenceStatus": "(val)","createTimestamp": "(val)","awardTypeCode": "(val)","unitNumber": "(val)","proposalId": "(val)","proposalNumber": "(val)","sponsorProposalNumber": "(val)","sequenceNumber": "(val)","proposalTypeCode": "(val)","currentAccountNumber": "(val)","title": "(val)","sponsorCode": "(val)","rolodexId": "(val)","noticeOfOpportunityCode": "(val)","gradStudHeadcount": "(val)","gradStudPersonMonths": "(val)","typeOfAccount": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","numberOfCopies": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","deadlineType": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","subcontractFlag": "(val)","costSharingIndicator": "(val)","idcRateIndicator": "(val)","specialReviewIndicator": "(val)","statusCode": "(val)","scienceCodeIndicator": "(val)","nsfCode": "(val)","institutionalProposalDocument.documentNumber": "(val)","_primaryKey": "(val)"},
              {"primeSponsorCode": "(val)","initialContractAdmin": "(val)","ipReviewActivityIndicator": "(val)","currentAwardNumber": "(val)","cfdaNumber": "(val)","opportunity": "(val)","proposalSequenceStatus": "(val)","createTimestamp": "(val)","awardTypeCode": "(val)","unitNumber": "(val)","proposalId": "(val)","proposalNumber": "(val)","sponsorProposalNumber": "(val)","sequenceNumber": "(val)","proposalTypeCode": "(val)","currentAccountNumber": "(val)","title": "(val)","sponsorCode": "(val)","rolodexId": "(val)","noticeOfOpportunityCode": "(val)","gradStudHeadcount": "(val)","gradStudPersonMonths": "(val)","typeOfAccount": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","numberOfCopies": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","deadlineType": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","subcontractFlag": "(val)","costSharingIndicator": "(val)","idcRateIndicator": "(val)","specialReviewIndicator": "(val)","statusCode": "(val)","scienceCodeIndicator": "(val)","nsfCode": "(val)","institutionalProposalDocument.documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Institutional Proposals [GET /instprop/api/v1/institutional-proposals/]
	                                          
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
    
            {"columns":["primeSponsorCode","initialContractAdmin","ipReviewActivityIndicator","currentAwardNumber","cfdaNumber","opportunity","proposalSequenceStatus","createTimestamp","awardTypeCode","unitNumber","proposalId","proposalNumber","sponsorProposalNumber","sequenceNumber","proposalTypeCode","currentAccountNumber","title","sponsorCode","rolodexId","noticeOfOpportunityCode","gradStudHeadcount","gradStudPersonMonths","typeOfAccount","activityTypeCode","requestedStartDateInitial","requestedStartDateTotal","requestedEndDateInitial","requestedEndDateTotal","fiscalMonth","fiscalYear","totalDirectCostInitial","totalDirectCostTotal","totalIndirectCostInitial","totalIndirectCostTotal","numberOfCopies","deadlineDate","deadlineTime","deadlineType","mailBy","mailType","mailAccountNumber","mailDescription","subcontractFlag","costSharingIndicator","idcRateIndicator","specialReviewIndicator","statusCode","scienceCodeIndicator","nsfCode","institutionalProposalDocument.documentNumber"],"primaryKey":"proposalId"}
		
### Get Blueprint API specification for Institutional Proposals [GET /instprop/api/v1/institutional-proposals/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Institutional Proposals.md"
            transfer-encoding:chunked
