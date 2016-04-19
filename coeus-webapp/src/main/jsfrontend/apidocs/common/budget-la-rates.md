## Budget La Rates [/research-common/api/v1/budget-la-rates/]

### Get Budget La Rates by Key [GET /research-common/api/v1/budget-la-rates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"applicableRate": "(val)","instituteRate": "(val)","onOffCampusFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"}

### Get All Budget La Rates [GET /research-common/api/v1/budget-la-rates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"applicableRate": "(val)","instituteRate": "(val)","onOffCampusFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"},
              {"applicableRate": "(val)","instituteRate": "(val)","onOffCampusFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budget La Rates with Filtering [GET /research-common/api/v1/budget-la-rates/]
    
+ Parameters

    + applicableRate (optional) - 
    + instituteRate (optional) - 
    + onOffCampusFlag (optional) - 
    + rateTypeCode (optional) - 
    + budgetId (optional) - 
    + rateClassCode (optional) - 
    + fiscalYear (optional) - 
    + startDate (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"applicableRate": "(val)","instituteRate": "(val)","onOffCampusFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"},
              {"applicableRate": "(val)","instituteRate": "(val)","onOffCampusFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget La Rates [GET /research-common/api/v1/budget-la-rates/]
	                                          
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
    
            {"columns":["applicableRate","instituteRate","onOffCampusFlag","rateTypeCode","budgetId","rateClassCode","fiscalYear","startDate"],"primaryKey":"budget:budgetId"}
		
### Get Blueprint API specification for Budget La Rates [GET /research-common/api/v1/budget-la-rates/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget La Rates.md"
            transfer-encoding:chunked


### Update Budget La Rates [PUT /research-common/api/v1/budget-la-rates/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"applicableRate": "(val)","instituteRate": "(val)","onOffCampusFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget La Rates [PUT /research-common/api/v1/budget-la-rates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"applicableRate": "(val)","instituteRate": "(val)","onOffCampusFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"},
              {"applicableRate": "(val)","instituteRate": "(val)","onOffCampusFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Budget La Rates [POST /research-common/api/v1/budget-la-rates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"applicableRate": "(val)","instituteRate": "(val)","onOffCampusFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"applicableRate": "(val)","instituteRate": "(val)","onOffCampusFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget La Rates [POST /research-common/api/v1/budget-la-rates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"applicableRate": "(val)","instituteRate": "(val)","onOffCampusFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"},
              {"applicableRate": "(val)","instituteRate": "(val)","onOffCampusFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"applicableRate": "(val)","instituteRate": "(val)","onOffCampusFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"},
              {"applicableRate": "(val)","instituteRate": "(val)","onOffCampusFlag": "(val)","rateTypeCode": "(val)","budgetId": "(val)","rateClassCode": "(val)","fiscalYear": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Budget La Rates by Key [DELETE /research-common/api/v1/budget-la-rates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget La Rates [DELETE /research-common/api/v1/budget-la-rates/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget La Rates with Matching [DELETE /research-common/api/v1/budget-la-rates/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + applicableRate (optional) - 
    + instituteRate (optional) - 
    + onOffCampusFlag (optional) - 
    + rateTypeCode (optional) - 
    + budgetId (optional) - 
    + rateClassCode (optional) - 
    + fiscalYear (optional) - 
    + startDate (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
