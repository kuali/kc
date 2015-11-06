## Awards [/award/api/v1/awards]

Kuali Research Awards providing Post Award management

### Awards Summary [GET /award/api/v1/awards?summary{&updatedSince=1446630596000&limit=50&page=8}]

Summary of Awards

+ Parameters
	+ summary: (boolean, required) - Renders the awards in a summary view instead of the full data from the award. Currently the only option and required. 
	+ updatedSince: `1446630596000` (date, optional) - Criteria to restrict the awards returned to those updated since the date specified. Formatted as milliseconds since epoch.
	+ limit: `50` (number, optional) - Number of results to return. If not included, all awards matching the date, if specified, are returned.
	+ page: `8` (number, optional) - If a limit is specified this determines what page of the result set will be returned on this request.

+ Request
    + Headers

            Authorization: Bearer {api-key}

+ Response 200
    + Headers

            Content-Type:application/json;charset=UTF-8

    + Body

			{
			   "count" : 1,
			   "awards" : [
			   	{
			         "title" : "LOC - by award",
			         "sponsorAwardNumber" : null,
			         "fundingProposals" : [
			            {
			               "awardSequenceNumber" : 1,
			               "awardNumber" : "999901-00001",
			               "proposalNumber" : "80070",
			               "proposalSequenceNumber" : 1
			            }
			         ],
			         "modificationNumber" : null,
			         "activityType" : {
			            "code" : "3",
			            "description" : "Public Service"
			         },
			         "accountNumber" : "0211401",
			         "awardId" : 2238,
			         "awardNumber" : "999901-00001",
			         "sponsor" : {
			            "sponsorCode" : "055125",
			            "sponsorName" : "Kuali LOC Testing3",
			            "acronym" : null
			         },
			         "cfdaNumber" : "00.000",
			         "awardStatus" : {
			            "code" : "1",
			            "description" : "Active"
			         },
			         "principalInvestigator" : {
			            "emailAddress" : "kcnotification@gmail.com",
			            "personId" : "10000000002",
			            "roleCode" : "PI",
			            "projectRole" : "Principal Investigator",
			            "fullName" : "Joe  Tester",
			            "userName" : "jtester"
			         },
			         "sequenceNumber" : 1
			      },
			   ],
			   "totalFound" : 112
			}

