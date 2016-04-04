## Financial Indirect Cost Recovery Type Codes [/research-sys/api/v1/financial-indirect-cost-recovery-type-codes/]

### Get Financial Indirect Cost Recovery Type Codes by Key [GET /research-sys/api/v1/financial-indirect-cost-recovery-type-codes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Financial Indirect Cost Recovery Type Codes [GET /research-sys/api/v1/financial-indirect-cost-recovery-type-codes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"},
              {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Financial Indirect Cost Recovery Type Codes with Filtering [GET /research-sys/api/v1/financial-indirect-cost-recovery-type-codes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + icrTypeCode
            + rateClassCode
            + rateTypeCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"},
              {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Financial Indirect Cost Recovery Type Codes [GET /research-sys/api/v1/financial-indirect-cost-recovery-type-codes/]
	 
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
		
### Get Blueprint API specification for Financial Indirect Cost Recovery Type Codes [GET /research-sys/api/v1/financial-indirect-cost-recovery-type-codes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Financial Indirect Cost Recovery Type Codes.md"
            transfer-encoding:chunked


### Update Financial Indirect Cost Recovery Type Codes [PUT /research-sys/api/v1/financial-indirect-cost-recovery-type-codes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Financial Indirect Cost Recovery Type Codes [PUT /research-sys/api/v1/financial-indirect-cost-recovery-type-codes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"},
              {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Financial Indirect Cost Recovery Type Codes [POST /research-sys/api/v1/financial-indirect-cost-recovery-type-codes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Financial Indirect Cost Recovery Type Codes [POST /research-sys/api/v1/financial-indirect-cost-recovery-type-codes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"},
              {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"},
              {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Financial Indirect Cost Recovery Type Codes by Key [DELETE /research-sys/api/v1/financial-indirect-cost-recovery-type-codes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Financial Indirect Cost Recovery Type Codes [DELETE /research-sys/api/v1/financial-indirect-cost-recovery-type-codes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Financial Indirect Cost Recovery Type Codes with Matching [DELETE /research-sys/api/v1/financial-indirect-cost-recovery-type-codes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + icrTypeCode
            + rateClassCode
            + rateTypeCode


+ Response 204
