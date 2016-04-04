## Budget Sub Award Period Details [/research-sys/api/v1/budget-sub-award-period-details/]

### Get Budget Sub Award Period Details by Key [GET /research-sys/api/v1/budget-sub-award-period-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","budgetPeriod": "(val)","directCost": "(val)","indirectCost": "(val)","costShare": "(val)","totalCost": "(val)","subAwardNumber": "(val)","_primaryKey": "(val)"}

### Get All Budget Sub Award Period Details [GET /research-sys/api/v1/budget-sub-award-period-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","budgetPeriod": "(val)","directCost": "(val)","indirectCost": "(val)","costShare": "(val)","totalCost": "(val)","subAwardNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","budgetPeriod": "(val)","directCost": "(val)","indirectCost": "(val)","costShare": "(val)","totalCost": "(val)","subAwardNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budget Sub Award Period Details with Filtering [GET /research-sys/api/v1/budget-sub-award-period-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + budgetPeriod
            + directCost
            + indirectCost
            + costShare
            + totalCost
            + subAwardNumber
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","budgetPeriod": "(val)","directCost": "(val)","indirectCost": "(val)","costShare": "(val)","totalCost": "(val)","subAwardNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","budgetPeriod": "(val)","directCost": "(val)","indirectCost": "(val)","costShare": "(val)","totalCost": "(val)","subAwardNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Sub Award Period Details [GET /research-sys/api/v1/budget-sub-award-period-details/]
	 
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
		
### Get Blueprint API specification for Budget Sub Award Period Details [GET /research-sys/api/v1/budget-sub-award-period-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Sub Award Period Details.md"
            transfer-encoding:chunked


### Update Budget Sub Award Period Details [PUT /research-sys/api/v1/budget-sub-award-period-details/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","budgetPeriod": "(val)","directCost": "(val)","indirectCost": "(val)","costShare": "(val)","totalCost": "(val)","subAwardNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Sub Award Period Details [PUT /research-sys/api/v1/budget-sub-award-period-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","budgetPeriod": "(val)","directCost": "(val)","indirectCost": "(val)","costShare": "(val)","totalCost": "(val)","subAwardNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","budgetPeriod": "(val)","directCost": "(val)","indirectCost": "(val)","costShare": "(val)","totalCost": "(val)","subAwardNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Budget Sub Award Period Details [POST /research-sys/api/v1/budget-sub-award-period-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","budgetPeriod": "(val)","directCost": "(val)","indirectCost": "(val)","costShare": "(val)","totalCost": "(val)","subAwardNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","budgetPeriod": "(val)","directCost": "(val)","indirectCost": "(val)","costShare": "(val)","totalCost": "(val)","subAwardNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Sub Award Period Details [POST /research-sys/api/v1/budget-sub-award-period-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","budgetPeriod": "(val)","directCost": "(val)","indirectCost": "(val)","costShare": "(val)","totalCost": "(val)","subAwardNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","budgetPeriod": "(val)","directCost": "(val)","indirectCost": "(val)","costShare": "(val)","totalCost": "(val)","subAwardNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","budgetPeriod": "(val)","directCost": "(val)","indirectCost": "(val)","costShare": "(val)","totalCost": "(val)","subAwardNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","budgetPeriod": "(val)","directCost": "(val)","indirectCost": "(val)","costShare": "(val)","totalCost": "(val)","subAwardNumber": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Budget Sub Award Period Details by Key [DELETE /research-sys/api/v1/budget-sub-award-period-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Sub Award Period Details [DELETE /research-sys/api/v1/budget-sub-award-period-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Budget Sub Award Period Details with Matching [DELETE /research-sys/api/v1/budget-sub-award-period-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + budgetPeriod
            + directCost
            + indirectCost
            + costShare
            + totalCost
            + subAwardNumber


+ Response 204
