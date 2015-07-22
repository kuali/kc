## Proposal Summary [/kc-dev/kc-ip/v1/institutionalProposalSummary?updatedSince=01/01/2015&page=1&numberPerPage=5]

## GET

Summary of all Institutional Proposals in the system

+ Parameters
	+ updatedSince: `31/10/2015` (date, optional) - Criteria to restrict the proposals returned to those updated since the date specified (DD/MM/YYYY)
	+ numberPerPage: `50` (number, optional) - Number of results to return. If not included, all proposals matching the date,if specified, are returned
	+ page: `8` (number, optional) - If a numberPerPage is specified this determines what page of the result set will be returned on this request

+ Request
    + Headers

            Authorization: `Basic HTTP authentication expected`           

+ Response 200
    + Headers

            Content-Type:application/json;charset=UTF-8

    + Body

			{
			   "totalFound" : 598,
			   "count" : 1,
			   "institutionalProposals" : [
			      {
			         "fundingProposals" : [
			            {
			               "proposalSequenceNumber" : 1,
			               "awardNumber" : "999901-00001",
			               "awardSequenceNumber" : 1,
			               "proposalNumber" : "80070"
			            },
			            {
			               "awardNumber" : "999901-00002",
			               "proposalSequenceNumber" : 1,
			               "awardSequenceNumber" : 1,
			               "proposalNumber" : "80070"
			            },
			            {
			               "proposalNumber" : "80070",
			               "awardSequenceNumber" : 1,
			               "proposalSequenceNumber" : 1,
			               "awardNumber" : "999901-00003"
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
			         },
			         "proposalNumber" : "80070"
			      },
			   ]
			}
            
            

