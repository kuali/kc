## Development Proposals [/research-sys/api/v1/development-proposals/]

### Get Development Proposals by Key [GET /research-sys/api/v1/development-proposals/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalNumber": "(val)","proposalTypeCode": "(val)","continuedFrom": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","ownedByUnitNumber": "(val)","requestedStartDateInitial": "(val)","requestedEndDateInitial": "(val)","title": "(val)","currentAwardNumber": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","noticeOfOpportunityCode": "(val)","deadlineType": "(val)","anticipatedAwardTypeCode": "(val)","cfdaNumber": "(val)","programAnnouncementNumber": "(val)","primeSponsorCode": "(val)","sponsorProposalNumber": "(val)","nsfCode": "(val)","subcontracts": "(val)","agencyDivisionCode": "(val)","agencyProgramCode": "(val)","programAnnouncementTitle": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","mailingAddressId": "(val)","numberOfCopies": "(val)","proposalStateTypeCode": "(val)","creationStatusCode": "(val)","submitFlag": "(val)","hierarchyStatus": "(val)","hierarchyOriginatingChildProposalNumber": "(val)","hierarchyParentProposalNumber": "(val)","hierarchyLastSyncHashCode": "(val)","hierarchyBudgetType": "(val)","proposalNumberForGG": "(val)","opportunityIdForGG": "(val)","agencyRoutingIdentifier": "(val)","prevGrantsGovTrackingID": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}

### Get All Development Proposals [GET /research-sys/api/v1/development-proposals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalNumber": "(val)","proposalTypeCode": "(val)","continuedFrom": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","ownedByUnitNumber": "(val)","requestedStartDateInitial": "(val)","requestedEndDateInitial": "(val)","title": "(val)","currentAwardNumber": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","noticeOfOpportunityCode": "(val)","deadlineType": "(val)","anticipatedAwardTypeCode": "(val)","cfdaNumber": "(val)","programAnnouncementNumber": "(val)","primeSponsorCode": "(val)","sponsorProposalNumber": "(val)","nsfCode": "(val)","subcontracts": "(val)","agencyDivisionCode": "(val)","agencyProgramCode": "(val)","programAnnouncementTitle": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","mailingAddressId": "(val)","numberOfCopies": "(val)","proposalStateTypeCode": "(val)","creationStatusCode": "(val)","submitFlag": "(val)","hierarchyStatus": "(val)","hierarchyOriginatingChildProposalNumber": "(val)","hierarchyParentProposalNumber": "(val)","hierarchyLastSyncHashCode": "(val)","hierarchyBudgetType": "(val)","proposalNumberForGG": "(val)","opportunityIdForGG": "(val)","agencyRoutingIdentifier": "(val)","prevGrantsGovTrackingID": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","proposalTypeCode": "(val)","continuedFrom": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","ownedByUnitNumber": "(val)","requestedStartDateInitial": "(val)","requestedEndDateInitial": "(val)","title": "(val)","currentAwardNumber": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","noticeOfOpportunityCode": "(val)","deadlineType": "(val)","anticipatedAwardTypeCode": "(val)","cfdaNumber": "(val)","programAnnouncementNumber": "(val)","primeSponsorCode": "(val)","sponsorProposalNumber": "(val)","nsfCode": "(val)","subcontracts": "(val)","agencyDivisionCode": "(val)","agencyProgramCode": "(val)","programAnnouncementTitle": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","mailingAddressId": "(val)","numberOfCopies": "(val)","proposalStateTypeCode": "(val)","creationStatusCode": "(val)","submitFlag": "(val)","hierarchyStatus": "(val)","hierarchyOriginatingChildProposalNumber": "(val)","hierarchyParentProposalNumber": "(val)","hierarchyLastSyncHashCode": "(val)","hierarchyBudgetType": "(val)","proposalNumberForGG": "(val)","opportunityIdForGG": "(val)","agencyRoutingIdentifier": "(val)","prevGrantsGovTrackingID": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]

### Get All Development Proposals with Filtering [GET /research-sys/api/v1/development-proposals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + proposalNumber
            + proposalTypeCode
            + continuedFrom
            + sponsorCode
            + activityTypeCode
            + ownedByUnitNumber
            + requestedStartDateInitial
            + requestedEndDateInitial
            + title
            + currentAwardNumber
            + deadlineDate
            + deadlineTime
            + noticeOfOpportunityCode
            + deadlineType
            + anticipatedAwardTypeCode
            + cfdaNumber
            + programAnnouncementNumber
            + primeSponsorCode
            + sponsorProposalNumber
            + nsfCode
            + subcontracts
            + agencyDivisionCode
            + agencyProgramCode
            + programAnnouncementTitle
            + mailBy
            + mailType
            + mailAccountNumber
            + mailDescription
            + mailingAddressId
            + numberOfCopies
            + proposalStateTypeCode
            + creationStatusCode
            + submitFlag
            + hierarchyStatus
            + hierarchyOriginatingChildProposalNumber
            + hierarchyParentProposalNumber
            + hierarchyLastSyncHashCode
            + hierarchyBudgetType
            + proposalNumberForGG
            + opportunityIdForGG
            + agencyRoutingIdentifier
            + prevGrantsGovTrackingID
            + createTimestamp
            + createUser
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalNumber": "(val)","proposalTypeCode": "(val)","continuedFrom": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","ownedByUnitNumber": "(val)","requestedStartDateInitial": "(val)","requestedEndDateInitial": "(val)","title": "(val)","currentAwardNumber": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","noticeOfOpportunityCode": "(val)","deadlineType": "(val)","anticipatedAwardTypeCode": "(val)","cfdaNumber": "(val)","programAnnouncementNumber": "(val)","primeSponsorCode": "(val)","sponsorProposalNumber": "(val)","nsfCode": "(val)","subcontracts": "(val)","agencyDivisionCode": "(val)","agencyProgramCode": "(val)","programAnnouncementTitle": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","mailingAddressId": "(val)","numberOfCopies": "(val)","proposalStateTypeCode": "(val)","creationStatusCode": "(val)","submitFlag": "(val)","hierarchyStatus": "(val)","hierarchyOriginatingChildProposalNumber": "(val)","hierarchyParentProposalNumber": "(val)","hierarchyLastSyncHashCode": "(val)","hierarchyBudgetType": "(val)","proposalNumberForGG": "(val)","opportunityIdForGG": "(val)","agencyRoutingIdentifier": "(val)","prevGrantsGovTrackingID": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","proposalTypeCode": "(val)","continuedFrom": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","ownedByUnitNumber": "(val)","requestedStartDateInitial": "(val)","requestedEndDateInitial": "(val)","title": "(val)","currentAwardNumber": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","noticeOfOpportunityCode": "(val)","deadlineType": "(val)","anticipatedAwardTypeCode": "(val)","cfdaNumber": "(val)","programAnnouncementNumber": "(val)","primeSponsorCode": "(val)","sponsorProposalNumber": "(val)","nsfCode": "(val)","subcontracts": "(val)","agencyDivisionCode": "(val)","agencyProgramCode": "(val)","programAnnouncementTitle": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","mailingAddressId": "(val)","numberOfCopies": "(val)","proposalStateTypeCode": "(val)","creationStatusCode": "(val)","submitFlag": "(val)","hierarchyStatus": "(val)","hierarchyOriginatingChildProposalNumber": "(val)","hierarchyParentProposalNumber": "(val)","hierarchyLastSyncHashCode": "(val)","hierarchyBudgetType": "(val)","proposalNumberForGG": "(val)","opportunityIdForGG": "(val)","agencyRoutingIdentifier": "(val)","prevGrantsGovTrackingID": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Development Proposals [GET /research-sys/api/v1/development-proposals/]
	 
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
		
### Get Blueprint API specification for Development Proposals [GET /research-sys/api/v1/development-proposals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Development Proposals.md"
            transfer-encoding:chunked


### Update Development Proposals [PUT /research-sys/api/v1/development-proposals/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalNumber": "(val)","proposalTypeCode": "(val)","continuedFrom": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","ownedByUnitNumber": "(val)","requestedStartDateInitial": "(val)","requestedEndDateInitial": "(val)","title": "(val)","currentAwardNumber": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","noticeOfOpportunityCode": "(val)","deadlineType": "(val)","anticipatedAwardTypeCode": "(val)","cfdaNumber": "(val)","programAnnouncementNumber": "(val)","primeSponsorCode": "(val)","sponsorProposalNumber": "(val)","nsfCode": "(val)","subcontracts": "(val)","agencyDivisionCode": "(val)","agencyProgramCode": "(val)","programAnnouncementTitle": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","mailingAddressId": "(val)","numberOfCopies": "(val)","proposalStateTypeCode": "(val)","creationStatusCode": "(val)","submitFlag": "(val)","hierarchyStatus": "(val)","hierarchyOriginatingChildProposalNumber": "(val)","hierarchyParentProposalNumber": "(val)","hierarchyLastSyncHashCode": "(val)","hierarchyBudgetType": "(val)","proposalNumberForGG": "(val)","opportunityIdForGG": "(val)","agencyRoutingIdentifier": "(val)","prevGrantsGovTrackingID": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Development Proposals [PUT /research-sys/api/v1/development-proposals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalNumber": "(val)","proposalTypeCode": "(val)","continuedFrom": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","ownedByUnitNumber": "(val)","requestedStartDateInitial": "(val)","requestedEndDateInitial": "(val)","title": "(val)","currentAwardNumber": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","noticeOfOpportunityCode": "(val)","deadlineType": "(val)","anticipatedAwardTypeCode": "(val)","cfdaNumber": "(val)","programAnnouncementNumber": "(val)","primeSponsorCode": "(val)","sponsorProposalNumber": "(val)","nsfCode": "(val)","subcontracts": "(val)","agencyDivisionCode": "(val)","agencyProgramCode": "(val)","programAnnouncementTitle": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","mailingAddressId": "(val)","numberOfCopies": "(val)","proposalStateTypeCode": "(val)","creationStatusCode": "(val)","submitFlag": "(val)","hierarchyStatus": "(val)","hierarchyOriginatingChildProposalNumber": "(val)","hierarchyParentProposalNumber": "(val)","hierarchyLastSyncHashCode": "(val)","hierarchyBudgetType": "(val)","proposalNumberForGG": "(val)","opportunityIdForGG": "(val)","agencyRoutingIdentifier": "(val)","prevGrantsGovTrackingID": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","proposalTypeCode": "(val)","continuedFrom": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","ownedByUnitNumber": "(val)","requestedStartDateInitial": "(val)","requestedEndDateInitial": "(val)","title": "(val)","currentAwardNumber": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","noticeOfOpportunityCode": "(val)","deadlineType": "(val)","anticipatedAwardTypeCode": "(val)","cfdaNumber": "(val)","programAnnouncementNumber": "(val)","primeSponsorCode": "(val)","sponsorProposalNumber": "(val)","nsfCode": "(val)","subcontracts": "(val)","agencyDivisionCode": "(val)","agencyProgramCode": "(val)","programAnnouncementTitle": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","mailingAddressId": "(val)","numberOfCopies": "(val)","proposalStateTypeCode": "(val)","creationStatusCode": "(val)","submitFlag": "(val)","hierarchyStatus": "(val)","hierarchyOriginatingChildProposalNumber": "(val)","hierarchyParentProposalNumber": "(val)","hierarchyLastSyncHashCode": "(val)","hierarchyBudgetType": "(val)","proposalNumberForGG": "(val)","opportunityIdForGG": "(val)","agencyRoutingIdentifier": "(val)","prevGrantsGovTrackingID": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Development Proposals [POST /research-sys/api/v1/development-proposals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalNumber": "(val)","proposalTypeCode": "(val)","continuedFrom": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","ownedByUnitNumber": "(val)","requestedStartDateInitial": "(val)","requestedEndDateInitial": "(val)","title": "(val)","currentAwardNumber": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","noticeOfOpportunityCode": "(val)","deadlineType": "(val)","anticipatedAwardTypeCode": "(val)","cfdaNumber": "(val)","programAnnouncementNumber": "(val)","primeSponsorCode": "(val)","sponsorProposalNumber": "(val)","nsfCode": "(val)","subcontracts": "(val)","agencyDivisionCode": "(val)","agencyProgramCode": "(val)","programAnnouncementTitle": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","mailingAddressId": "(val)","numberOfCopies": "(val)","proposalStateTypeCode": "(val)","creationStatusCode": "(val)","submitFlag": "(val)","hierarchyStatus": "(val)","hierarchyOriginatingChildProposalNumber": "(val)","hierarchyParentProposalNumber": "(val)","hierarchyLastSyncHashCode": "(val)","hierarchyBudgetType": "(val)","proposalNumberForGG": "(val)","opportunityIdForGG": "(val)","agencyRoutingIdentifier": "(val)","prevGrantsGovTrackingID": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalNumber": "(val)","proposalTypeCode": "(val)","continuedFrom": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","ownedByUnitNumber": "(val)","requestedStartDateInitial": "(val)","requestedEndDateInitial": "(val)","title": "(val)","currentAwardNumber": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","noticeOfOpportunityCode": "(val)","deadlineType": "(val)","anticipatedAwardTypeCode": "(val)","cfdaNumber": "(val)","programAnnouncementNumber": "(val)","primeSponsorCode": "(val)","sponsorProposalNumber": "(val)","nsfCode": "(val)","subcontracts": "(val)","agencyDivisionCode": "(val)","agencyProgramCode": "(val)","programAnnouncementTitle": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","mailingAddressId": "(val)","numberOfCopies": "(val)","proposalStateTypeCode": "(val)","creationStatusCode": "(val)","submitFlag": "(val)","hierarchyStatus": "(val)","hierarchyOriginatingChildProposalNumber": "(val)","hierarchyParentProposalNumber": "(val)","hierarchyLastSyncHashCode": "(val)","hierarchyBudgetType": "(val)","proposalNumberForGG": "(val)","opportunityIdForGG": "(val)","agencyRoutingIdentifier": "(val)","prevGrantsGovTrackingID": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Development Proposals [POST /research-sys/api/v1/development-proposals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalNumber": "(val)","proposalTypeCode": "(val)","continuedFrom": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","ownedByUnitNumber": "(val)","requestedStartDateInitial": "(val)","requestedEndDateInitial": "(val)","title": "(val)","currentAwardNumber": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","noticeOfOpportunityCode": "(val)","deadlineType": "(val)","anticipatedAwardTypeCode": "(val)","cfdaNumber": "(val)","programAnnouncementNumber": "(val)","primeSponsorCode": "(val)","sponsorProposalNumber": "(val)","nsfCode": "(val)","subcontracts": "(val)","agencyDivisionCode": "(val)","agencyProgramCode": "(val)","programAnnouncementTitle": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","mailingAddressId": "(val)","numberOfCopies": "(val)","proposalStateTypeCode": "(val)","creationStatusCode": "(val)","submitFlag": "(val)","hierarchyStatus": "(val)","hierarchyOriginatingChildProposalNumber": "(val)","hierarchyParentProposalNumber": "(val)","hierarchyLastSyncHashCode": "(val)","hierarchyBudgetType": "(val)","proposalNumberForGG": "(val)","opportunityIdForGG": "(val)","agencyRoutingIdentifier": "(val)","prevGrantsGovTrackingID": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","proposalTypeCode": "(val)","continuedFrom": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","ownedByUnitNumber": "(val)","requestedStartDateInitial": "(val)","requestedEndDateInitial": "(val)","title": "(val)","currentAwardNumber": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","noticeOfOpportunityCode": "(val)","deadlineType": "(val)","anticipatedAwardTypeCode": "(val)","cfdaNumber": "(val)","programAnnouncementNumber": "(val)","primeSponsorCode": "(val)","sponsorProposalNumber": "(val)","nsfCode": "(val)","subcontracts": "(val)","agencyDivisionCode": "(val)","agencyProgramCode": "(val)","programAnnouncementTitle": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","mailingAddressId": "(val)","numberOfCopies": "(val)","proposalStateTypeCode": "(val)","creationStatusCode": "(val)","submitFlag": "(val)","hierarchyStatus": "(val)","hierarchyOriginatingChildProposalNumber": "(val)","hierarchyParentProposalNumber": "(val)","hierarchyLastSyncHashCode": "(val)","hierarchyBudgetType": "(val)","proposalNumberForGG": "(val)","opportunityIdForGG": "(val)","agencyRoutingIdentifier": "(val)","prevGrantsGovTrackingID": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalNumber": "(val)","proposalTypeCode": "(val)","continuedFrom": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","ownedByUnitNumber": "(val)","requestedStartDateInitial": "(val)","requestedEndDateInitial": "(val)","title": "(val)","currentAwardNumber": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","noticeOfOpportunityCode": "(val)","deadlineType": "(val)","anticipatedAwardTypeCode": "(val)","cfdaNumber": "(val)","programAnnouncementNumber": "(val)","primeSponsorCode": "(val)","sponsorProposalNumber": "(val)","nsfCode": "(val)","subcontracts": "(val)","agencyDivisionCode": "(val)","agencyProgramCode": "(val)","programAnnouncementTitle": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","mailingAddressId": "(val)","numberOfCopies": "(val)","proposalStateTypeCode": "(val)","creationStatusCode": "(val)","submitFlag": "(val)","hierarchyStatus": "(val)","hierarchyOriginatingChildProposalNumber": "(val)","hierarchyParentProposalNumber": "(val)","hierarchyLastSyncHashCode": "(val)","hierarchyBudgetType": "(val)","proposalNumberForGG": "(val)","opportunityIdForGG": "(val)","agencyRoutingIdentifier": "(val)","prevGrantsGovTrackingID": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","proposalTypeCode": "(val)","continuedFrom": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","ownedByUnitNumber": "(val)","requestedStartDateInitial": "(val)","requestedEndDateInitial": "(val)","title": "(val)","currentAwardNumber": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","noticeOfOpportunityCode": "(val)","deadlineType": "(val)","anticipatedAwardTypeCode": "(val)","cfdaNumber": "(val)","programAnnouncementNumber": "(val)","primeSponsorCode": "(val)","sponsorProposalNumber": "(val)","nsfCode": "(val)","subcontracts": "(val)","agencyDivisionCode": "(val)","agencyProgramCode": "(val)","programAnnouncementTitle": "(val)","mailBy": "(val)","mailType": "(val)","mailAccountNumber": "(val)","mailDescription": "(val)","mailingAddressId": "(val)","numberOfCopies": "(val)","proposalStateTypeCode": "(val)","creationStatusCode": "(val)","submitFlag": "(val)","hierarchyStatus": "(val)","hierarchyOriginatingChildProposalNumber": "(val)","hierarchyParentProposalNumber": "(val)","hierarchyLastSyncHashCode": "(val)","hierarchyBudgetType": "(val)","proposalNumberForGG": "(val)","opportunityIdForGG": "(val)","agencyRoutingIdentifier": "(val)","prevGrantsGovTrackingID": "(val)","createTimestamp": "(val)","createUser": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Development Proposals by Key [DELETE /research-sys/api/v1/development-proposals/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Development Proposals [DELETE /research-sys/api/v1/development-proposals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Development Proposals with Matching [DELETE /research-sys/api/v1/development-proposals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + proposalNumber
            + proposalTypeCode
            + continuedFrom
            + sponsorCode
            + activityTypeCode
            + ownedByUnitNumber
            + requestedStartDateInitial
            + requestedEndDateInitial
            + title
            + currentAwardNumber
            + deadlineDate
            + deadlineTime
            + noticeOfOpportunityCode
            + deadlineType
            + anticipatedAwardTypeCode
            + cfdaNumber
            + programAnnouncementNumber
            + primeSponsorCode
            + sponsorProposalNumber
            + nsfCode
            + subcontracts
            + agencyDivisionCode
            + agencyProgramCode
            + programAnnouncementTitle
            + mailBy
            + mailType
            + mailAccountNumber
            + mailDescription
            + mailingAddressId
            + numberOfCopies
            + proposalStateTypeCode
            + creationStatusCode
            + submitFlag
            + hierarchyStatus
            + hierarchyOriginatingChildProposalNumber
            + hierarchyParentProposalNumber
            + hierarchyLastSyncHashCode
            + hierarchyBudgetType
            + proposalNumberForGG
            + opportunityIdForGG
            + agencyRoutingIdentifier
            + prevGrantsGovTrackingID
            + createTimestamp
            + createUser


+ Response 204
