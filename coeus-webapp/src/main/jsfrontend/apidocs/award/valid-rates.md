## Valid Rates [/research-sys/api/v1/valid-rates/]

### Get Valid Rates by Key [GET /research-sys/api/v1/valid-rates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"validRatesId": "(val)","onCampusRate": "(val)","offCampusRate": "(val)","rateClassType": "(val)","adjustmentKey": "(val)","icrRateCode": "(val)","_primaryKey": "(val)"}

### Get All Valid Rates [GET /research-sys/api/v1/valid-rates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"validRatesId": "(val)","onCampusRate": "(val)","offCampusRate": "(val)","rateClassType": "(val)","adjustmentKey": "(val)","icrRateCode": "(val)","_primaryKey": "(val)"},
              {"validRatesId": "(val)","onCampusRate": "(val)","offCampusRate": "(val)","rateClassType": "(val)","adjustmentKey": "(val)","icrRateCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Valid Rates with Filtering [GET /research-sys/api/v1/valid-rates/]
    
+ Parameters

        + validRatesId
            + onCampusRate
            + offCampusRate
            + rateClassType
            + adjustmentKey
            + icrRateCode

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"validRatesId": "(val)","onCampusRate": "(val)","offCampusRate": "(val)","rateClassType": "(val)","adjustmentKey": "(val)","icrRateCode": "(val)","_primaryKey": "(val)"},
              {"validRatesId": "(val)","onCampusRate": "(val)","offCampusRate": "(val)","rateClassType": "(val)","adjustmentKey": "(val)","icrRateCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Valid Rates [GET /research-sys/api/v1/valid-rates/]
	                                          
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
    
            {"columns":["validRatesId","onCampusRate","offCampusRate","rateClassType","adjustmentKey","icrRateCode"],"primaryKey":"validRatesId"}
		
### Get Blueprint API specification for Valid Rates [GET /research-sys/api/v1/valid-rates/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Valid Rates.md"
            transfer-encoding:chunked


### Update Valid Rates [PUT /research-sys/api/v1/valid-rates/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validRatesId": "(val)","onCampusRate": "(val)","offCampusRate": "(val)","rateClassType": "(val)","adjustmentKey": "(val)","icrRateCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Valid Rates [PUT /research-sys/api/v1/valid-rates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"validRatesId": "(val)","onCampusRate": "(val)","offCampusRate": "(val)","rateClassType": "(val)","adjustmentKey": "(val)","icrRateCode": "(val)","_primaryKey": "(val)"},
              {"validRatesId": "(val)","onCampusRate": "(val)","offCampusRate": "(val)","rateClassType": "(val)","adjustmentKey": "(val)","icrRateCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Valid Rates [POST /research-sys/api/v1/valid-rates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validRatesId": "(val)","onCampusRate": "(val)","offCampusRate": "(val)","rateClassType": "(val)","adjustmentKey": "(val)","icrRateCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"validRatesId": "(val)","onCampusRate": "(val)","offCampusRate": "(val)","rateClassType": "(val)","adjustmentKey": "(val)","icrRateCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Valid Rates [POST /research-sys/api/v1/valid-rates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"validRatesId": "(val)","onCampusRate": "(val)","offCampusRate": "(val)","rateClassType": "(val)","adjustmentKey": "(val)","icrRateCode": "(val)","_primaryKey": "(val)"},
              {"validRatesId": "(val)","onCampusRate": "(val)","offCampusRate": "(val)","rateClassType": "(val)","adjustmentKey": "(val)","icrRateCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"validRatesId": "(val)","onCampusRate": "(val)","offCampusRate": "(val)","rateClassType": "(val)","adjustmentKey": "(val)","icrRateCode": "(val)","_primaryKey": "(val)"},
              {"validRatesId": "(val)","onCampusRate": "(val)","offCampusRate": "(val)","rateClassType": "(val)","adjustmentKey": "(val)","icrRateCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Valid Rates by Key [DELETE /research-sys/api/v1/valid-rates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Rates [DELETE /research-sys/api/v1/valid-rates/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Rates with Matching [DELETE /research-sys/api/v1/valid-rates/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + validRatesId
            + onCampusRate
            + offCampusRate
            + rateClassType
            + adjustmentKey
            + icrRateCode

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
