## Award Budget Periods [/research-sys/api/v1/award-budget-periods/]

### Get Award Budget Periods by Key [GET /research-sys/api/v1/award-budget-periods/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetPeriodId": "(val)","obligatedAmount": "(val)","totalFringeAmount": "(val)","rateOverrideFlag": "(val)","_primaryKey": "(val)"}

### Get All Award Budget Periods [GET /research-sys/api/v1/award-budget-periods/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetPeriodId": "(val)","obligatedAmount": "(val)","totalFringeAmount": "(val)","rateOverrideFlag": "(val)","_primaryKey": "(val)"},
              {"budgetPeriodId": "(val)","obligatedAmount": "(val)","totalFringeAmount": "(val)","rateOverrideFlag": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Budget Periods with Filtering [GET /research-sys/api/v1/award-budget-periods/]
    
+ Parameters

        + budgetPeriodId
            + obligatedAmount
            + totalFringeAmount
            + rateOverrideFlag

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetPeriodId": "(val)","obligatedAmount": "(val)","totalFringeAmount": "(val)","rateOverrideFlag": "(val)","_primaryKey": "(val)"},
              {"budgetPeriodId": "(val)","obligatedAmount": "(val)","totalFringeAmount": "(val)","rateOverrideFlag": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Budget Periods [GET /research-sys/api/v1/award-budget-periods/]
	                                          
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
    
            {"columns":["budgetPeriodId","obligatedAmount","totalFringeAmount","rateOverrideFlag"],"primaryKey":"budgetPeriodId"}
		
### Get Blueprint API specification for Award Budget Periods [GET /research-sys/api/v1/award-budget-periods/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Budget Periods.md"
            transfer-encoding:chunked


### Update Award Budget Periods [PUT /research-sys/api/v1/award-budget-periods/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetPeriodId": "(val)","obligatedAmount": "(val)","totalFringeAmount": "(val)","rateOverrideFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Budget Periods [PUT /research-sys/api/v1/award-budget-periods/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetPeriodId": "(val)","obligatedAmount": "(val)","totalFringeAmount": "(val)","rateOverrideFlag": "(val)","_primaryKey": "(val)"},
              {"budgetPeriodId": "(val)","obligatedAmount": "(val)","totalFringeAmount": "(val)","rateOverrideFlag": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Budget Periods [POST /research-sys/api/v1/award-budget-periods/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetPeriodId": "(val)","obligatedAmount": "(val)","totalFringeAmount": "(val)","rateOverrideFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetPeriodId": "(val)","obligatedAmount": "(val)","totalFringeAmount": "(val)","rateOverrideFlag": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Budget Periods [POST /research-sys/api/v1/award-budget-periods/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetPeriodId": "(val)","obligatedAmount": "(val)","totalFringeAmount": "(val)","rateOverrideFlag": "(val)","_primaryKey": "(val)"},
              {"budgetPeriodId": "(val)","obligatedAmount": "(val)","totalFringeAmount": "(val)","rateOverrideFlag": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"budgetPeriodId": "(val)","obligatedAmount": "(val)","totalFringeAmount": "(val)","rateOverrideFlag": "(val)","_primaryKey": "(val)"},
              {"budgetPeriodId": "(val)","obligatedAmount": "(val)","totalFringeAmount": "(val)","rateOverrideFlag": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Budget Periods by Key [DELETE /research-sys/api/v1/award-budget-periods/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budget Periods [DELETE /research-sys/api/v1/award-budget-periods/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budget Periods with Matching [DELETE /research-sys/api/v1/award-budget-periods/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + budgetPeriodId
            + obligatedAmount
            + totalFringeAmount
            + rateOverrideFlag

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
