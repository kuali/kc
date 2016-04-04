## Institutional Proposal [/instprop/api/v1/institutional-proposals/]

### Proposal Summary [GET /instprop/api/v1/institutional-proposals/?summary{&updatedSince=&page=1&limit=5}]

Summary of all Institutional Proposals in the system

+ Parameters
	+ summary: (boolean, required) - Renders the proposals in a summary view instead of the full data from the proposal. Currently the only option and required.
	+ updatedSince: `1452126023000` (date, optional) - Criteria to restrict the proposals returned to those updated since the date specified. Formatted as milliseconds since epoch.
	+ limit: `50` (number, optional) - Number of results to return. If not included, all proposals matching the date,if specified, are returned.
	+ page: `8` (number, optional) - If a numberPerPage is specified this determines what page of the result set will be returned on this request.

+ Request
    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body

			{
			   "totalFound" : 598,
			   "count" : 1,
			   "institutionalProposals" : [
			      {
			      	 "proposalId": 10,
			         "proposalNumber" : "80070",
			         "sequenceNumber": 1
			         "proposalLogProposalNumber": "00000009",
				      "leadUnit": {
				        "unitNumber": "000001",
				        "unitName": "University",
				        "parentUnitNumber": null,
				        "organizationId": "000001"
				      },
			         "fundingProposals" : [
				        {
				          "proposalSequenceNumber": 1,
				          "awardNumber": "999901-00001",
				          "awardSequenceNumber": 1,
				          "awardId": 2238,
				          "proposalId": 1,
				          "proposalNumber": "80070"
				        },
				        {
				          "proposalSequenceNumber": 1,
				          "awardNumber": "999901-00002",
				          "awardSequenceNumber": 1,
				          "awardId": 2251,
				          "proposalId": 1,
				          "proposalNumber": "80070"
				        },
				        {
				          "proposalSequenceNumber": 1,
				          "awardNumber": "999901-00003",
				          "awardSequenceNumber": 1,
				          "awardId": 2264,
				          "proposalId": 1,
				          "proposalNumber": "80070"
				        }
			         ],
			         "principalInvestigator" : {
			            "fullName" : "Inez  Chew",
			            "personId" : "10000000005",
			            "roleCode" : "PI",
			            "projectRole" : "Principal Investigator",
			            "userName" : "chew",
			            "emailAddress" : "kcnotification@gmail.com"
			         },
			         "sponsor" : {
			            "sponsorCode" : "055075",
			            "acronym" : null,
			            "sponsorName" : "Kuali LOC Testing"
			         }
			      },
			   ]
			}
            
            

