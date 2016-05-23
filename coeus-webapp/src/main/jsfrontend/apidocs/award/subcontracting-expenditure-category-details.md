## Subcontracting Expenditure Category Details [/award/api/v1/subcontracting-expenditure-category-details/]

### Get Subcontracting Expenditure Category Details by Key [GET /award/api/v1/subcontracting-expenditure-category-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","awardNumber": "(val)","amount": "(val)","fiscalPeriod": "(val)","largeBusiness": "(val)","smallBusiness": "(val)","womanOwned": "(val)","eightADisadvantage": "(val)","hubZone": "(val)","veteranOwned": "(val)","serviceDisabledVeteranOwned": "(val)","historicalBlackCollege": "(val)","_primaryKey": "(val)"}

### Get All Subcontracting Expenditure Category Details [GET /award/api/v1/subcontracting-expenditure-category-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","awardNumber": "(val)","amount": "(val)","fiscalPeriod": "(val)","largeBusiness": "(val)","smallBusiness": "(val)","womanOwned": "(val)","eightADisadvantage": "(val)","hubZone": "(val)","veteranOwned": "(val)","serviceDisabledVeteranOwned": "(val)","historicalBlackCollege": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","awardNumber": "(val)","amount": "(val)","fiscalPeriod": "(val)","largeBusiness": "(val)","smallBusiness": "(val)","womanOwned": "(val)","eightADisadvantage": "(val)","hubZone": "(val)","veteranOwned": "(val)","serviceDisabledVeteranOwned": "(val)","historicalBlackCollege": "(val)","_primaryKey": "(val)"}
            ]

### Get All Subcontracting Expenditure Category Details with Filtering [GET /award/api/v1/subcontracting-expenditure-category-details/]
    
+ Parameters

    + id (optional) - 
    + awardNumber (optional) - 
    + amount (optional) - 
    + fiscalPeriod (optional) - 
    + largeBusiness (optional) - 
    + smallBusiness (optional) - 
    + womanOwned (optional) - 
    + eightADisadvantage (optional) - 
    + hubZone (optional) - 
    + veteranOwned (optional) - 
    + serviceDisabledVeteranOwned (optional) - 
    + historicalBlackCollege (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","awardNumber": "(val)","amount": "(val)","fiscalPeriod": "(val)","largeBusiness": "(val)","smallBusiness": "(val)","womanOwned": "(val)","eightADisadvantage": "(val)","hubZone": "(val)","veteranOwned": "(val)","serviceDisabledVeteranOwned": "(val)","historicalBlackCollege": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","awardNumber": "(val)","amount": "(val)","fiscalPeriod": "(val)","largeBusiness": "(val)","smallBusiness": "(val)","womanOwned": "(val)","eightADisadvantage": "(val)","hubZone": "(val)","veteranOwned": "(val)","serviceDisabledVeteranOwned": "(val)","historicalBlackCollege": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Subcontracting Expenditure Category Details [GET /award/api/v1/subcontracting-expenditure-category-details/]
	                                          
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
    
            {"columns":["id","awardNumber","amount","fiscalPeriod","largeBusiness","smallBusiness","womanOwned","eightADisadvantage","hubZone","veteranOwned","serviceDisabledVeteranOwned","historicalBlackCollege"],"primaryKey":"id"}
		
### Get Blueprint API specification for Subcontracting Expenditure Category Details [GET /award/api/v1/subcontracting-expenditure-category-details/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Subcontracting Expenditure Category Details.md"
            transfer-encoding:chunked
### Update Subcontracting Expenditure Category Details [PUT /award/api/v1/subcontracting-expenditure-category-details/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","awardNumber": "(val)","amount": "(val)","fiscalPeriod": "(val)","largeBusiness": "(val)","smallBusiness": "(val)","womanOwned": "(val)","eightADisadvantage": "(val)","hubZone": "(val)","veteranOwned": "(val)","serviceDisabledVeteranOwned": "(val)","historicalBlackCollege": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Subcontracting Expenditure Category Details [PUT /award/api/v1/subcontracting-expenditure-category-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","awardNumber": "(val)","amount": "(val)","fiscalPeriod": "(val)","largeBusiness": "(val)","smallBusiness": "(val)","womanOwned": "(val)","eightADisadvantage": "(val)","hubZone": "(val)","veteranOwned": "(val)","serviceDisabledVeteranOwned": "(val)","historicalBlackCollege": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","awardNumber": "(val)","amount": "(val)","fiscalPeriod": "(val)","largeBusiness": "(val)","smallBusiness": "(val)","womanOwned": "(val)","eightADisadvantage": "(val)","hubZone": "(val)","veteranOwned": "(val)","serviceDisabledVeteranOwned": "(val)","historicalBlackCollege": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Subcontracting Expenditure Category Details [POST /award/api/v1/subcontracting-expenditure-category-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","awardNumber": "(val)","amount": "(val)","fiscalPeriod": "(val)","largeBusiness": "(val)","smallBusiness": "(val)","womanOwned": "(val)","eightADisadvantage": "(val)","hubZone": "(val)","veteranOwned": "(val)","serviceDisabledVeteranOwned": "(val)","historicalBlackCollege": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","awardNumber": "(val)","amount": "(val)","fiscalPeriod": "(val)","largeBusiness": "(val)","smallBusiness": "(val)","womanOwned": "(val)","eightADisadvantage": "(val)","hubZone": "(val)","veteranOwned": "(val)","serviceDisabledVeteranOwned": "(val)","historicalBlackCollege": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Subcontracting Expenditure Category Details [POST /award/api/v1/subcontracting-expenditure-category-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","awardNumber": "(val)","amount": "(val)","fiscalPeriod": "(val)","largeBusiness": "(val)","smallBusiness": "(val)","womanOwned": "(val)","eightADisadvantage": "(val)","hubZone": "(val)","veteranOwned": "(val)","serviceDisabledVeteranOwned": "(val)","historicalBlackCollege": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","awardNumber": "(val)","amount": "(val)","fiscalPeriod": "(val)","largeBusiness": "(val)","smallBusiness": "(val)","womanOwned": "(val)","eightADisadvantage": "(val)","hubZone": "(val)","veteranOwned": "(val)","serviceDisabledVeteranOwned": "(val)","historicalBlackCollege": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","awardNumber": "(val)","amount": "(val)","fiscalPeriod": "(val)","largeBusiness": "(val)","smallBusiness": "(val)","womanOwned": "(val)","eightADisadvantage": "(val)","hubZone": "(val)","veteranOwned": "(val)","serviceDisabledVeteranOwned": "(val)","historicalBlackCollege": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","awardNumber": "(val)","amount": "(val)","fiscalPeriod": "(val)","largeBusiness": "(val)","smallBusiness": "(val)","womanOwned": "(val)","eightADisadvantage": "(val)","hubZone": "(val)","veteranOwned": "(val)","serviceDisabledVeteranOwned": "(val)","historicalBlackCollege": "(val)","_primaryKey": "(val)"}
            ]
### Delete Subcontracting Expenditure Category Details by Key [DELETE /award/api/v1/subcontracting-expenditure-category-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Subcontracting Expenditure Category Details [DELETE /award/api/v1/subcontracting-expenditure-category-details/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Subcontracting Expenditure Category Details with Matching [DELETE /award/api/v1/subcontracting-expenditure-category-details/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + awardNumber (optional) - 
    + amount (optional) - 
    + fiscalPeriod (optional) - 
    + largeBusiness (optional) - 
    + smallBusiness (optional) - 
    + womanOwned (optional) - 
    + eightADisadvantage (optional) - 
    + hubZone (optional) - 
    + veteranOwned (optional) - 
    + serviceDisabledVeteranOwned (optional) - 
    + historicalBlackCollege (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
