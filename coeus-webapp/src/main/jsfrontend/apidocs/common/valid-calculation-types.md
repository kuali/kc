## Valid Calculation Types [/research-sys/api/v1/valid-calculation-types/]

### Get Valid Calculation Types by Key [GET /research-sys/api/v1/valid-calculation-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"calcTypeId": "(val)","dependentSeqNumber": "(val)","rateClassType": "(val)","dependentRateClassType": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Valid Calculation Types [GET /research-sys/api/v1/valid-calculation-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"calcTypeId": "(val)","dependentSeqNumber": "(val)","rateClassType": "(val)","dependentRateClassType": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"},
              {"calcTypeId": "(val)","dependentSeqNumber": "(val)","rateClassType": "(val)","dependentRateClassType": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Valid Calculation Types with Filtering [GET /research-sys/api/v1/valid-calculation-types/]
    
+ Parameters

        + calcTypeId
            + dependentSeqNumber
            + rateClassType
            + dependentRateClassType
            + rateClassCode
            + rateTypeCode

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"calcTypeId": "(val)","dependentSeqNumber": "(val)","rateClassType": "(val)","dependentRateClassType": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"},
              {"calcTypeId": "(val)","dependentSeqNumber": "(val)","rateClassType": "(val)","dependentRateClassType": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Valid Calculation Types [GET /research-sys/api/v1/valid-calculation-types/]
	                                          
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
    
            {"columns":["calcTypeId","dependentSeqNumber","rateClassType","dependentRateClassType","rateClassCode","rateTypeCode"],"primaryKey":"calcTypeId:dependentSeqNumber:rateClassType"}
		
### Get Blueprint API specification for Valid Calculation Types [GET /research-sys/api/v1/valid-calculation-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Valid Calculation Types.md"
            transfer-encoding:chunked


### Update Valid Calculation Types [PUT /research-sys/api/v1/valid-calculation-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"calcTypeId": "(val)","dependentSeqNumber": "(val)","rateClassType": "(val)","dependentRateClassType": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Valid Calculation Types [PUT /research-sys/api/v1/valid-calculation-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"calcTypeId": "(val)","dependentSeqNumber": "(val)","rateClassType": "(val)","dependentRateClassType": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"},
              {"calcTypeId": "(val)","dependentSeqNumber": "(val)","rateClassType": "(val)","dependentRateClassType": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Valid Calculation Types [POST /research-sys/api/v1/valid-calculation-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"calcTypeId": "(val)","dependentSeqNumber": "(val)","rateClassType": "(val)","dependentRateClassType": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"calcTypeId": "(val)","dependentSeqNumber": "(val)","rateClassType": "(val)","dependentRateClassType": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Valid Calculation Types [POST /research-sys/api/v1/valid-calculation-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"calcTypeId": "(val)","dependentSeqNumber": "(val)","rateClassType": "(val)","dependentRateClassType": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"},
              {"calcTypeId": "(val)","dependentSeqNumber": "(val)","rateClassType": "(val)","dependentRateClassType": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"calcTypeId": "(val)","dependentSeqNumber": "(val)","rateClassType": "(val)","dependentRateClassType": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"},
              {"calcTypeId": "(val)","dependentSeqNumber": "(val)","rateClassType": "(val)","dependentRateClassType": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Valid Calculation Types by Key [DELETE /research-sys/api/v1/valid-calculation-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Calculation Types [DELETE /research-sys/api/v1/valid-calculation-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Calculation Types with Matching [DELETE /research-sys/api/v1/valid-calculation-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + calcTypeId
            + dependentSeqNumber
            + rateClassType
            + dependentRateClassType
            + rateClassCode
            + rateTypeCode

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
