## Development Proposals [/propdev/api/v1/development-proposals/]

### Get Development Proposals by Key [GET /propdev/api/v1/development-proposals/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalNumber": "(val)","proposalTypeCode": "(val)","continuedFrom": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","ownedByUnitNumber": "(val)","requestedStartDateInitial": "(val)","requestedEndDateInitial": "(val)","title": "(val)","currentAwardNumber": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","noticeOfOpportunityCode": "(val)","deadlineType": "(val)","anticipatedAwardTypeCode": "(val)","cfdaNumber": "(val)","programAnnouncementNumber": "(val)","primeSponsorCode": "(val)","sponsorProposalNumber": "(val)","nsfSequenceNumber": "(val)","subcontracts": "(val)","agencyDivisionCode": "(val)","agencyProgramCode": "(val)","programAnnouncementTitle": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","mailingAddressId": "(val)","numberOfCopies": "(val)","proposalStateTypeCode": "(val)","creationStatusCode": "(val)","submitFlag": "(val)","hierarchyStatus": "(val)","hierarchyOriginatingChildProposalNumber": "(val)","hierarchyParentProposalNumber": "(val)","hierarchyLastSyncHashCode": "(val)","hierarchyBudgetType": "(val)","proposalNumberForGG": "(val)","opportunityIdForGG": "(val)","agencyRoutingIdentifier": "(val)","prevGrantsGovTrackingID": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}

### Get All Development Proposals [GET /propdev/api/v1/development-proposals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalNumber": "(val)","proposalTypeCode": "(val)","continuedFrom": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","ownedByUnitNumber": "(val)","requestedStartDateInitial": "(val)","requestedEndDateInitial": "(val)","title": "(val)","currentAwardNumber": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","noticeOfOpportunityCode": "(val)","deadlineType": "(val)","anticipatedAwardTypeCode": "(val)","cfdaNumber": "(val)","programAnnouncementNumber": "(val)","primeSponsorCode": "(val)","sponsorProposalNumber": "(val)","nsfSequenceNumber": "(val)","subcontracts": "(val)","agencyDivisionCode": "(val)","agencyProgramCode": "(val)","programAnnouncementTitle": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","mailingAddressId": "(val)","numberOfCopies": "(val)","proposalStateTypeCode": "(val)","creationStatusCode": "(val)","submitFlag": "(val)","hierarchyStatus": "(val)","hierarchyOriginatingChildProposalNumber": "(val)","hierarchyParentProposalNumber": "(val)","hierarchyLastSyncHashCode": "(val)","hierarchyBudgetType": "(val)","proposalNumberForGG": "(val)","opportunityIdForGG": "(val)","agencyRoutingIdentifier": "(val)","prevGrantsGovTrackingID": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","proposalTypeCode": "(val)","continuedFrom": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","ownedByUnitNumber": "(val)","requestedStartDateInitial": "(val)","requestedEndDateInitial": "(val)","title": "(val)","currentAwardNumber": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","noticeOfOpportunityCode": "(val)","deadlineType": "(val)","anticipatedAwardTypeCode": "(val)","cfdaNumber": "(val)","programAnnouncementNumber": "(val)","primeSponsorCode": "(val)","sponsorProposalNumber": "(val)","nsfSequenceNumber": "(val)","subcontracts": "(val)","agencyDivisionCode": "(val)","agencyProgramCode": "(val)","programAnnouncementTitle": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","mailingAddressId": "(val)","numberOfCopies": "(val)","proposalStateTypeCode": "(val)","creationStatusCode": "(val)","submitFlag": "(val)","hierarchyStatus": "(val)","hierarchyOriginatingChildProposalNumber": "(val)","hierarchyParentProposalNumber": "(val)","hierarchyLastSyncHashCode": "(val)","hierarchyBudgetType": "(val)","proposalNumberForGG": "(val)","opportunityIdForGG": "(val)","agencyRoutingIdentifier": "(val)","prevGrantsGovTrackingID": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]

### Get All Development Proposals with Filtering [GET /propdev/api/v1/development-proposals/]
    
+ Parameters

    + proposalNumber (optional) - The unique proposal number identifying a proposal. This is a system generated, sequential number. Maximum length is 12.
    + proposalTypeCode (optional) - The type of the proposal. Maximum length is 3.
    + continuedFrom (optional) - A unique institutionally assigned proposal number of a previously submitted proposal. This is applicable when submitting a revised (changed/corrected) application. Maximum length is 8.
    + sponsorCode (optional) - The identification number of the organization or agency that is providing support for the sponsored project. Maximum length is 6.
    + activityTypeCode (optional) - The type of activity proposed, e.g. organized research, instruction, etc. Maximum length is 3.
    + ownedByUnitNumber (optional) - Unit Number. Maximum length is 8.
    + requestedStartDateInitial (optional) - The date the project period will begin. Maximum length is 10.
    + requestedEndDateInitial (optional) - The date the project period will terminate. Maximum length is 10.
    + title (optional) - The proposed title of the project. Maximum length is 200.
    + currentAwardNumber (optional) - A unique institutionally assigned number of a previously funded application. Maximum length is 12.
    + deadlineDate (optional) - The date in which the proposal is due to the sponsoring agency.  Additional deadlines may be set by the office authorized to submit proposals on behalf of the institution (e.g. sponsored programs, etc.). Maximum length is 21.
    + deadlineTime (optional) - The time in which the proposal is due to the sponsoring agency.  Additional deadlines may be set by the office authorized to submit proposals on behalf of the institution (e.g. sponsored programs, etc.). Maximum length is 15.
    + noticeOfOpportunityCode (optional) - Indicates if the proposal was in response to a solicitation, the type of solicitation or if it is unsolicited. Maximum length is 3.
    + deadlineType (optional) - Indicates the type of proposal deadline. Maximum length is 3.
    + anticipatedAwardTypeCode (optional) - Anticipated Award Type. Maximum length is 3.
    + cfdaNumber (optional) - A unique identifier for the sponsor and the funding opportunity announcement.  AKA "Catalogue of Federal Domestic Assistance Number." The format for this CFDA Number is XX.XXX. Maximum length is 7.
    + programAnnouncementNumber (optional) - A unique identifier associated with each sponsor's funding opportunity announcement. AKA "Funding Opportunity Announcement number" or "FOA number". Maximum length is 50.
    + primeSponsorCode (optional) - The sponsor that provides the original source of project funding. Maximum length is 6.
    + sponsorProposalNumber (optional) - The unique identifier assigned by the sponsor when the proposal was submitted. Maximum length is 70.
    + nsfSequenceNumber (optional) - A number used to indicate the type of research activity as defined by the National Science Foundation. NSF Code Sequence Numbers are not specific for NSF proposals and/or awards. 
    + subcontracts (optional) - The proposal will include a sub award with another institution. Maximum length is 1.
    + agencyDivisionCode (optional) - A code that is unique to each NSF division.  Currently specific to the NSF only. Maximum length is 50.
    + agencyProgramCode (optional) - A code that is unique to each NSF program.  Currently specific to the NSF only. Maximum length is 50.
    + programAnnouncementTitle (optional) - The title of a publicly available document, announcing a federal agency's intentions to award discretionary grants or cooperative agreements, usually as a result of competition for funds.  AKA  Funding opportunity announcements, notices of funding availability, or solicitations. Maximum length is 255.
    + mailBy (optional) - This field determines whether the Sponsored Programs Office or the department will be mailing the proposal to the agency/sponsor. Maximum length is 3.
    + mailType (optional) - The method by which the proposal will be delivered to the agency/sponsor. Maximum length is 1.
    + mailAccountNumber (optional) - The internal school account number that should be charged the mailing costs. Maximum length is 7.
    + mailDescription (optional) - Any information that will assist in the delivery of the proposal. Maximum length is 80.
    + mailingAddressId (optional) - The name and address to whom the proposal will be mailed. Maximum length is 6.
    + numberOfCopies (optional) - The number of paper copies of the proposal that should be mailed to the agency/sponsor. Maximum length is 2.
    + proposalStateTypeCode (optional) - The Proposal State for this Proposal Development Document. Maximum length is 3.
    + creationStatusCode (optional) - Creation Status Code.
    + submitFlag (optional) - Submit Flag.
    + hierarchyStatus (optional) - The status of the proposal in a hierarchy. Maximum length is 3.
    + hierarchyOriginatingChildProposalNumber (optional) - Hierarchy Originating Child Proposal Number.
    + hierarchyParentProposalNumber (optional) - Hierarchy Parent Proposal Number.
    + hierarchyLastSyncHashCode (optional) - Hierarchy Last Sync Hash Code.
    + hierarchyBudgetType (optional) - The type of the budget for this proposal in a hierarchy. Maximum length is 3.
    + proposalNumberForGG (optional) - Grants.gov Opportunity. Maximum length is 12.
    + opportunityIdForGG (optional) - Grants.gov Opportunity. Maximum length is 50.
    + agencyRoutingIdentifier (optional) - The Agency Routing Identifier. Maximum length is 50.
    + prevGrantsGovTrackingID (optional) - Prev Grants.Gov Tracking ID. Maximum length is 50.
    + createTimestamp (optional) - Create Timestamp.
    + createUser (optional) - Create User.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalNumber": "(val)","proposalTypeCode": "(val)","continuedFrom": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","ownedByUnitNumber": "(val)","requestedStartDateInitial": "(val)","requestedEndDateInitial": "(val)","title": "(val)","currentAwardNumber": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","noticeOfOpportunityCode": "(val)","deadlineType": "(val)","anticipatedAwardTypeCode": "(val)","cfdaNumber": "(val)","programAnnouncementNumber": "(val)","primeSponsorCode": "(val)","sponsorProposalNumber": "(val)","nsfSequenceNumber": "(val)","subcontracts": "(val)","agencyDivisionCode": "(val)","agencyProgramCode": "(val)","programAnnouncementTitle": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","mailingAddressId": "(val)","numberOfCopies": "(val)","proposalStateTypeCode": "(val)","creationStatusCode": "(val)","submitFlag": "(val)","hierarchyStatus": "(val)","hierarchyOriginatingChildProposalNumber": "(val)","hierarchyParentProposalNumber": "(val)","hierarchyLastSyncHashCode": "(val)","hierarchyBudgetType": "(val)","proposalNumberForGG": "(val)","opportunityIdForGG": "(val)","agencyRoutingIdentifier": "(val)","prevGrantsGovTrackingID": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","proposalTypeCode": "(val)","continuedFrom": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","ownedByUnitNumber": "(val)","requestedStartDateInitial": "(val)","requestedEndDateInitial": "(val)","title": "(val)","currentAwardNumber": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","noticeOfOpportunityCode": "(val)","deadlineType": "(val)","anticipatedAwardTypeCode": "(val)","cfdaNumber": "(val)","programAnnouncementNumber": "(val)","primeSponsorCode": "(val)","sponsorProposalNumber": "(val)","nsfSequenceNumber": "(val)","subcontracts": "(val)","agencyDivisionCode": "(val)","agencyProgramCode": "(val)","programAnnouncementTitle": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","mailingAddressId": "(val)","numberOfCopies": "(val)","proposalStateTypeCode": "(val)","creationStatusCode": "(val)","submitFlag": "(val)","hierarchyStatus": "(val)","hierarchyOriginatingChildProposalNumber": "(val)","hierarchyParentProposalNumber": "(val)","hierarchyLastSyncHashCode": "(val)","hierarchyBudgetType": "(val)","proposalNumberForGG": "(val)","opportunityIdForGG": "(val)","agencyRoutingIdentifier": "(val)","prevGrantsGovTrackingID": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Development Proposals [GET /propdev/api/v1/development-proposals/]
	                                          
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
    
            {"columns":["proposalNumber","proposalTypeCode","continuedFrom","sponsorCode","activityTypeCode","ownedByUnitNumber","requestedStartDateInitial","requestedEndDateInitial","title","currentAwardNumber","deadlineDate","deadlineTime","noticeOfOpportunityCode","deadlineType","anticipatedAwardTypeCode","cfdaNumber","programAnnouncementNumber","primeSponsorCode","sponsorProposalNumber","nsfSequenceNumber","subcontracts","agencyDivisionCode","agencyProgramCode","programAnnouncementTitle","mailBy","mailType","mailAccountNumber","mailDescription","mailingAddressId","numberOfCopies","proposalStateTypeCode","creationStatusCode","submitFlag","hierarchyStatus","hierarchyOriginatingChildProposalNumber","hierarchyParentProposalNumber","hierarchyLastSyncHashCode","hierarchyBudgetType","proposalNumberForGG","opportunityIdForGG","agencyRoutingIdentifier","prevGrantsGovTrackingID","createTimestamp","createUser"],"primaryKey":"proposalNumber"}
		
### Get Blueprint API specification for Development Proposals [GET /propdev/api/v1/development-proposals/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Development Proposals.md"
            transfer-encoding:chunked
