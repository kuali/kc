## Institute La Rates [/research-sys/api/v1/institute-la-rates/]

### Get Institute La Rates by Key [GET /research-sys/api/v1/institute-la-rates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Institute La Rates [GET /research-sys/api/v1/institute-la-rates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Institute La Rates with Filtering [GET /research-sys/api/v1/institute-la-rates/]
    
+ Parameters

        + fiscalYear
            + onOffCampusFlag
            + rateClassCode
            + rateTypeCode
            + startDate
            + unitNumber
            + instituteRate
            + active

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Institute La Rates [GET /research-sys/api/v1/institute-la-rates/]
	                                          
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
    
            {"columns":["fiscalYear","onOffCampusFlag","rateClassCode","rateTypeCode","startDate","unitNumber","instituteRate","active"],"primaryKey":"fiscalYear:onOffCampusFlag:rateClassCode:rateTypeCode:startDate:unitNumber"}
		
### Get Blueprint API specification for Institute La Rates [GET /research-sys/api/v1/institute-la-rates/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Institute La Rates.md"
            transfer-encoding:chunked


### Update Institute La Rates [PUT /research-sys/api/v1/institute-la-rates/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Institute La Rates [PUT /research-sys/api/v1/institute-la-rates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Institute La Rates [POST /research-sys/api/v1/institute-la-rates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Institute La Rates [POST /research-sys/api/v1/institute-la-rates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Institute La Rates by Key [DELETE /research-sys/api/v1/institute-la-rates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institute La Rates [DELETE /research-sys/api/v1/institute-la-rates/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institute La Rates with Matching [DELETE /research-sys/api/v1/institute-la-rates/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + fiscalYear
            + onOffCampusFlag
            + rateClassCode
            + rateTypeCode
            + startDate
            + unitNumber
            + instituteRate
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
