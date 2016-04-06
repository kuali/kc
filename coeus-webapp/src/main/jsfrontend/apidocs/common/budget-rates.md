## Budget Rates [/research-sys/api/v1/budget-rates/]

### Get Budget Rates by Key [GET /research-sys/api/v1/budget-rates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"activityTypeCode": "(val)","applicableRate": "(val)","instituteRate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"}

### Get All Budget Rates [GET /research-sys/api/v1/budget-rates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"activityTypeCode": "(val)","applicableRate": "(val)","instituteRate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"},
              {"activityTypeCode": "(val)","applicableRate": "(val)","instituteRate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budget Rates with Filtering [GET /research-sys/api/v1/budget-rates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + activityTypeCode
            + applicableRate
            + instituteRate
            + rateTypeCode
            + budgetId
            + onOffCampusFlag
            + rateClassCode
            + fiscalYear
            + startDate
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"activityTypeCode": "(val)","applicableRate": "(val)","instituteRate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"},
              {"activityTypeCode": "(val)","applicableRate": "(val)","instituteRate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Rates [GET /research-sys/api/v1/budget-rates/]
	 
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
		
### Get Blueprint API specification for Budget Rates [GET /research-sys/api/v1/budget-rates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Rates.md"
            transfer-encoding:chunked


### Update Budget Rates [PUT /research-sys/api/v1/budget-rates/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"activityTypeCode": "(val)","applicableRate": "(val)","instituteRate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Rates [PUT /research-sys/api/v1/budget-rates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"activityTypeCode": "(val)","applicableRate": "(val)","instituteRate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"},
              {"activityTypeCode": "(val)","applicableRate": "(val)","instituteRate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Budget Rates [POST /research-sys/api/v1/budget-rates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"activityTypeCode": "(val)","applicableRate": "(val)","instituteRate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"activityTypeCode": "(val)","applicableRate": "(val)","instituteRate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Rates [POST /research-sys/api/v1/budget-rates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"activityTypeCode": "(val)","applicableRate": "(val)","instituteRate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"},
              {"activityTypeCode": "(val)","applicableRate": "(val)","instituteRate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"activityTypeCode": "(val)","applicableRate": "(val)","instituteRate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"},
              {"activityTypeCode": "(val)","applicableRate": "(val)","instituteRate": "(val)","rateTypeCode": "(val)","budgetId": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Budget Rates by Key [DELETE /research-sys/api/v1/budget-rates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Rates [DELETE /research-sys/api/v1/budget-rates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Budget Rates with Matching [DELETE /research-sys/api/v1/budget-rates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + activityTypeCode
            + applicableRate
            + instituteRate
            + rateTypeCode
            + budgetId
            + onOffCampusFlag
            + rateClassCode
            + fiscalYear
            + startDate


+ Response 204
