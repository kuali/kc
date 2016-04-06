## Budget Periods [/research-sys/api/v1/budget-periods/]

### Get Budget Periods by Key [GET /research-sys/api/v1/budget-periods/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetPeriodId": "(val)","budgetPeriod": "(val)","comments": "(val)","costSharingAmount": "(val)","endDate": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","numberOfParticipants": "(val)","directCostLimit": "(val)","_primaryKey": "(val)"}

### Get All Budget Periods [GET /research-sys/api/v1/budget-periods/]
	 
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

### Get All Budget Periods with Filtering [GET /research-sys/api/v1/budget-periods/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + budgetPeriodId
            + budgetPeriod
            + comments
            + costSharingAmount
            + endDate
            + startDate
            + totalCost
            + totalCostLimit
            + totalDirectCost
            + totalIndirectCost
            + underrecoveryAmount
            + numberOfParticipants
            + directCostLimit
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetPeriodId": "(val)","budgetPeriod": "(val)","comments": "(val)","costSharingAmount": "(val)","endDate": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","numberOfParticipants": "(val)","directCostLimit": "(val)","_primaryKey": "(val)"},
              {"budgetPeriodId": "(val)","budgetPeriod": "(val)","comments": "(val)","costSharingAmount": "(val)","endDate": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","numberOfParticipants": "(val)","directCostLimit": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Periods [GET /research-sys/api/v1/budget-periods/]
	 
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
		
### Get Blueprint API specification for Budget Periods [GET /research-sys/api/v1/budget-periods/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Periods.md"
            transfer-encoding:chunked


### Update Budget Periods [PUT /research-sys/api/v1/budget-periods/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetPeriodId": "(val)","budgetPeriod": "(val)","comments": "(val)","costSharingAmount": "(val)","endDate": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","numberOfParticipants": "(val)","directCostLimit": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Periods [PUT /research-sys/api/v1/budget-periods/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetPeriodId": "(val)","budgetPeriod": "(val)","comments": "(val)","costSharingAmount": "(val)","endDate": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","numberOfParticipants": "(val)","directCostLimit": "(val)","_primaryKey": "(val)"},
              {"budgetPeriodId": "(val)","budgetPeriod": "(val)","comments": "(val)","costSharingAmount": "(val)","endDate": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","numberOfParticipants": "(val)","directCostLimit": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Budget Periods [POST /research-sys/api/v1/budget-periods/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetPeriodId": "(val)","budgetPeriod": "(val)","comments": "(val)","costSharingAmount": "(val)","endDate": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","numberOfParticipants": "(val)","directCostLimit": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetPeriodId": "(val)","budgetPeriod": "(val)","comments": "(val)","costSharingAmount": "(val)","endDate": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","numberOfParticipants": "(val)","directCostLimit": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Periods [POST /research-sys/api/v1/budget-periods/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetPeriodId": "(val)","budgetPeriod": "(val)","comments": "(val)","costSharingAmount": "(val)","endDate": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","numberOfParticipants": "(val)","directCostLimit": "(val)","_primaryKey": "(val)"},
              {"budgetPeriodId": "(val)","budgetPeriod": "(val)","comments": "(val)","costSharingAmount": "(val)","endDate": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","numberOfParticipants": "(val)","directCostLimit": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"budgetPeriodId": "(val)","budgetPeriod": "(val)","comments": "(val)","costSharingAmount": "(val)","endDate": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","numberOfParticipants": "(val)","directCostLimit": "(val)","_primaryKey": "(val)"},
              {"budgetPeriodId": "(val)","budgetPeriod": "(val)","comments": "(val)","costSharingAmount": "(val)","endDate": "(val)","startDate": "(val)","totalCost": "(val)","totalCostLimit": "(val)","totalDirectCost": "(val)","totalIndirectCost": "(val)","underrecoveryAmount": "(val)","numberOfParticipants": "(val)","directCostLimit": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Budget Periods by Key [DELETE /research-sys/api/v1/budget-periods/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Periods [DELETE /research-sys/api/v1/budget-periods/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Budget Periods with Matching [DELETE /research-sys/api/v1/budget-periods/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + budgetPeriodId
            + budgetPeriod
            + comments
            + costSharingAmount
            + endDate
            + startDate
            + totalCost
            + totalCostLimit
            + totalDirectCost
            + totalIndirectCost
            + underrecoveryAmount
            + numberOfParticipants
            + directCostLimit


+ Response 204
