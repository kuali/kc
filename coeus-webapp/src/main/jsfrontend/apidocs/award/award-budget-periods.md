## Award Budget Periods [/award/api/v1/award-budget-periods/]

### Get Award Budget Periods by Key [GET /award/api/v1/award-budget-periods/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetPeriodId": "(val)","obligatedAmount": "(val)","totalFringeAmount": "(val)","rateOverrideFlag": "(val)","_primaryKey": "(val)"}

### Get All Award Budget Periods [GET /award/api/v1/award-budget-periods/]
	 
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

### Get All Award Budget Periods with Filtering [GET /award/api/v1/award-budget-periods/]
    
+ Parameters

    + budgetPeriodId (optional) - 
    + obligatedAmount (optional) - Obligated amount from award amount info. Maximum length is 15.
    + totalFringeAmount (optional) - Total Fringe amount from award amount info. Maximum length is 15.
    + rateOverrideFlag (optional) - Rate Override Flag. Maximum length is 1.

            
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
			
### Get Schema for Award Budget Periods [GET /award/api/v1/award-budget-periods/]
	                                          
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
		
### Get Blueprint API specification for Award Budget Periods [GET /award/api/v1/award-budget-periods/]
	 
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


### Update Award Budget Periods [PUT /award/api/v1/award-budget-periods/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetPeriodId": "(val)","obligatedAmount": "(val)","totalFringeAmount": "(val)","rateOverrideFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Budget Periods [PUT /award/api/v1/award-budget-periods/]

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

### Insert Award Budget Periods [POST /award/api/v1/award-budget-periods/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetPeriodId": "(val)","obligatedAmount": "(val)","totalFringeAmount": "(val)","rateOverrideFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetPeriodId": "(val)","obligatedAmount": "(val)","totalFringeAmount": "(val)","rateOverrideFlag": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Budget Periods [POST /award/api/v1/award-budget-periods/]

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
            
### Delete Award Budget Periods by Key [DELETE /award/api/v1/award-budget-periods/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budget Periods [DELETE /award/api/v1/award-budget-periods/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budget Periods with Matching [DELETE /award/api/v1/award-budget-periods/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + budgetPeriodId (optional) - 
    + obligatedAmount (optional) - Obligated amount from award amount info. Maximum length is 15.
    + totalFringeAmount (optional) - Total Fringe amount from award amount info. Maximum length is 15.
    + rateOverrideFlag (optional) - Rate Override Flag. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
