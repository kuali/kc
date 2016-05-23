## Budget Periods [/research-common/api/v1/budget-periods/]

### Get Budget Periods by Key [GET /research-common/api/v1/budget-periods/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetPeriodId": "(val)","budgetPeriod": "(val)","comments": "(val)","costSharingAmount": "(val)","endDate": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","numberOfParticipants": "(val)","directCostLimit": "(val)","_primaryKey": "(val)"}

### Get All Budget Periods [GET /research-common/api/v1/budget-periods/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetPeriodId": "(val)","budgetPeriod": "(val)","comments": "(val)","costSharingAmount": "(val)","endDate": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","numberOfParticipants": "(val)","directCostLimit": "(val)","_primaryKey": "(val)"},
              {"budgetPeriodId": "(val)","budgetPeriod": "(val)","comments": "(val)","costSharingAmount": "(val)","endDate": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","numberOfParticipants": "(val)","directCostLimit": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budget Periods with Filtering [GET /research-common/api/v1/budget-periods/]
    
+ Parameters

    + budgetPeriodId (optional) - Budget Period Id. Maximum length is 12.
    + budgetPeriod (optional) - Budget Period. Maximum length is 3.
    + comments (optional) - Comments. Maximum length is 2000.
    + costSharingAmount (optional) - Cost Sharing. Maximum length is 15.
    + endDate (optional) - Period End Date. Maximum length is 21.
    + startDate (optional) - Period Start Date. Maximum length is 21.
    + totalCost (optional) - Total Sponsor Cost. Maximum length is 15.
    + totalCostLimit (optional) - Cost Limit. Maximum length is 15.
    + totalDirectCost (optional) - Direct Cost. Maximum length is 15.
    + totalIndirectCost (optional) - F&A Cost. Maximum length is 15.
    + underrecoveryAmount (optional) - Unrecovered F&A. Maximum length is 15.
    + numberOfParticipants (optional) - Number of Participants. Maximum length is 6.
    + directCostLimit (optional) - Direct Cost Limit. Maximum length is 15.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetPeriodId": "(val)","budgetPeriod": "(val)","comments": "(val)","costSharingAmount": "(val)","endDate": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","numberOfParticipants": "(val)","directCostLimit": "(val)","_primaryKey": "(val)"},
              {"budgetPeriodId": "(val)","budgetPeriod": "(val)","comments": "(val)","costSharingAmount": "(val)","endDate": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","numberOfParticipants": "(val)","directCostLimit": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Periods [GET /research-common/api/v1/budget-periods/]
	                                          
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
    
            {"columns":["budgetPeriodId","budgetPeriod","comments","costSharingAmount","endDate","startDate","totalCost","totalCostLimit","totalDirectCost","totalIndirectCost","underrecoveryAmount","numberOfParticipants","directCostLimit"],"primaryKey":"budgetPeriodId"}
		
### Get Blueprint API specification for Budget Periods [GET /research-common/api/v1/budget-periods/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Periods.md"
            transfer-encoding:chunked
