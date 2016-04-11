## Budget Person Salary Details [/research-sys/api/v1/budget-person-salary-details/]

### Get Budget Person Salary Details by Key [GET /research-sys/api/v1/budget-person-salary-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetPersonSalaryDetailId": "(val)","personSequenceNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","personId": "(val)","baseSalary": "(val)","_primaryKey": "(val)"}

### Get All Budget Person Salary Details [GET /research-sys/api/v1/budget-person-salary-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetPersonSalaryDetailId": "(val)","personSequenceNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","personId": "(val)","baseSalary": "(val)","_primaryKey": "(val)"},
              {"budgetPersonSalaryDetailId": "(val)","personSequenceNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","personId": "(val)","baseSalary": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budget Person Salary Details with Filtering [GET /research-sys/api/v1/budget-person-salary-details/]
    
+ Parameters

        + budgetPersonSalaryDetailId
            + personSequenceNumber
            + budgetId
            + budgetPeriod
            + personId
            + baseSalary

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetPersonSalaryDetailId": "(val)","personSequenceNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","personId": "(val)","baseSalary": "(val)","_primaryKey": "(val)"},
              {"budgetPersonSalaryDetailId": "(val)","personSequenceNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","personId": "(val)","baseSalary": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Person Salary Details [GET /research-sys/api/v1/budget-person-salary-details/]
	                                          
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
    
            {"columns":["budgetPersonSalaryDetailId","personSequenceNumber","budgetId","budgetPeriod","personId","baseSalary"],"primaryKey":"budgetPersonSalaryDetailId"}
		
### Get Blueprint API specification for Budget Person Salary Details [GET /research-sys/api/v1/budget-person-salary-details/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Person Salary Details.md"
            transfer-encoding:chunked


### Update Budget Person Salary Details [PUT /research-sys/api/v1/budget-person-salary-details/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetPersonSalaryDetailId": "(val)","personSequenceNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","personId": "(val)","baseSalary": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Person Salary Details [PUT /research-sys/api/v1/budget-person-salary-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetPersonSalaryDetailId": "(val)","personSequenceNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","personId": "(val)","baseSalary": "(val)","_primaryKey": "(val)"},
              {"budgetPersonSalaryDetailId": "(val)","personSequenceNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","personId": "(val)","baseSalary": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Budget Person Salary Details [POST /research-sys/api/v1/budget-person-salary-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetPersonSalaryDetailId": "(val)","personSequenceNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","personId": "(val)","baseSalary": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetPersonSalaryDetailId": "(val)","personSequenceNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","personId": "(val)","baseSalary": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Person Salary Details [POST /research-sys/api/v1/budget-person-salary-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetPersonSalaryDetailId": "(val)","personSequenceNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","personId": "(val)","baseSalary": "(val)","_primaryKey": "(val)"},
              {"budgetPersonSalaryDetailId": "(val)","personSequenceNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","personId": "(val)","baseSalary": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"budgetPersonSalaryDetailId": "(val)","personSequenceNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","personId": "(val)","baseSalary": "(val)","_primaryKey": "(val)"},
              {"budgetPersonSalaryDetailId": "(val)","personSequenceNumber": "(val)","budgetId": "(val)","budgetPeriod": "(val)","personId": "(val)","baseSalary": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Budget Person Salary Details by Key [DELETE /research-sys/api/v1/budget-person-salary-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Person Salary Details [DELETE /research-sys/api/v1/budget-person-salary-details/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Person Salary Details with Matching [DELETE /research-sys/api/v1/budget-person-salary-details/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + budgetPersonSalaryDetailId
            + personSequenceNumber
            + budgetId
            + budgetPeriod
            + personId
            + baseSalary

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
