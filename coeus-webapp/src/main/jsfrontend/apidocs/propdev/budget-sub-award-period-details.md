## Budget Sub Award Period Details [/propdev/api/v1/budget-sub-award-period-details/]

### Get Budget Sub Award Period Details by Key [GET /propdev/api/v1/budget-sub-award-period-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","budgetPeriod": "(val)","directCost": "(val)","indirectCost": "(val)","costShare": "(val)","totalCost": "(val)","subAwardNumber": "(val)","_primaryKey": "(val)"}

### Get All Budget Sub Award Period Details [GET /propdev/api/v1/budget-sub-award-period-details/]
	 
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

### Get All Budget Sub Award Period Details with Filtering [GET /propdev/api/v1/budget-sub-award-period-details/]
    
+ Parameters

    + id (optional) - id. Maximum length is 3.
    + budgetPeriod (optional) - Budget Period. Maximum length is 3.
    + directCost (optional) - Direct Cost. Maximum length is 15.
    + indirectCost (optional) - F&A Cost. Maximum length is 15.
    + costShare (optional) - Cost Sharing. Maximum length is 15.
    + totalCost (optional) - Total Cost. Maximum length is 15.
    + subAwardNumber (optional) - subAwardNumber. Maximum length is 3.

            
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
			
### Get Schema for Budget Sub Award Period Details [GET /propdev/api/v1/budget-sub-award-period-details/]
	                                          
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
    
            {"columns":["id","budgetPeriod","directCost","indirectCost","costShare","totalCost","subAwardNumber"],"primaryKey":"id"}
		
### Get Blueprint API specification for Budget Sub Award Period Details [GET /propdev/api/v1/budget-sub-award-period-details/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Sub Award Period Details.md"
            transfer-encoding:chunked
### Update Budget Sub Award Period Details [PUT /propdev/api/v1/budget-sub-award-period-details/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","budgetPeriod": "(val)","directCost": "(val)","indirectCost": "(val)","costShare": "(val)","totalCost": "(val)","subAwardNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Sub Award Period Details [PUT /propdev/api/v1/budget-sub-award-period-details/]

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
### Insert Budget Sub Award Period Details [POST /propdev/api/v1/budget-sub-award-period-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","budgetPeriod": "(val)","directCost": "(val)","indirectCost": "(val)","costShare": "(val)","totalCost": "(val)","subAwardNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","budgetPeriod": "(val)","directCost": "(val)","indirectCost": "(val)","costShare": "(val)","totalCost": "(val)","subAwardNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Sub Award Period Details [POST /propdev/api/v1/budget-sub-award-period-details/]

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
### Delete Budget Sub Award Period Details by Key [DELETE /propdev/api/v1/budget-sub-award-period-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Sub Award Period Details [DELETE /propdev/api/v1/budget-sub-award-period-details/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Sub Award Period Details with Matching [DELETE /propdev/api/v1/budget-sub-award-period-details/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - id. Maximum length is 3.
    + budgetPeriod (optional) - Budget Period. Maximum length is 3.
    + directCost (optional) - Direct Cost. Maximum length is 15.
    + indirectCost (optional) - F&A Cost. Maximum length is 15.
    + costShare (optional) - Cost Sharing. Maximum length is 15.
    + totalCost (optional) - Total Cost. Maximum length is 15.
    + subAwardNumber (optional) - subAwardNumber. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
