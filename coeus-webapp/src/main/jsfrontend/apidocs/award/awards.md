## Awards [/award/api/v1/awards]

Kuali Research Awards providing Post Award management

### Awards Summary [GET /award/api/v1/awards/?summary{&updatedSince=1446630596000&limit=50&page=8}]

Summary of Awards

+ Parameters
	+ summary: (boolean, required) - Renders the awards in a summary view instead of the full data from the award. Currently the only option and required. 
	+ updatedSince: `1446630596000` (date, optional) - Criteria to restrict the awards returned to those updated since the date specified. Formatted as milliseconds since epoch.
	+ limit: `50` (number, optional) - Number of results to return. If not included, all awards matching the date, if specified, are returned.
	+ page: `8` (number, optional) - If a limit is specified this determines what page of the result set will be returned on this request.

+ Request
    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body

			{
			   "count" : 1,
			   "awards" : [
			   	{
			         "awardId" : 2238,
			         "awardNumber" : "999901-00001",
			         "sequenceNumber": 1,
			         "accountNumber" : "0211401",
			         "title" : "LOC - by award",
			         "sponsorAwardNumber" : null,
			         "modificationNumber" : null,
			         "cfdaNumber" : "00.000",
			         "fundingProposals" : [
				        {
				          "proposalSequenceNumber": 1,
				          "awardNumber": "999908-00001",
				          "awardSequenceNumber": 1,
				          "awardId": 2511,
				          "proposalId": 7,
				          "proposalNumber": "80068"
				        }
			         ],
			         "activityType" : {
			            "code" : "3",
			            "description" : "Public Service"
			         },
			         "sponsor" : {
			            "sponsorCode" : "055125",
			            "sponsorName" : "Kuali LOC Testing3",
			            "acronym" : null
			         },
			         "leadUnit": {
				        "unitNumber": "000001",
				        "unitName": "University",
				        "parentUnitNumber": null,
				        "organizationId": "000001"
				      },
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
			            "userName" : "jtester",
				        "person": {
				          "state": "MA",
				          "emailAddress": "kcnotification+jtester@gmail.com",
				          "addressLine2": "",
				          "firstName": "Joe",
				          "addressLine3": "",
				          "lastName": "Tester",
				          "personId": "10000000002",
				          "middleName": "",
				          "userName": "jtester",
				          "fullName": "Joe Tester",
				          "city": "Coeus",
				          "postalCode": "53421",
				          "addressLine1": "1119 Kuali Drive"
				        },
			         }
			      },
			   ],
			   "totalFound" : 112
			}

