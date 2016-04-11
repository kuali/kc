## Award Person Unit Credit Splits [/research-sys/api/v1/award-person-unit-credit-splits/]

### Get Award Person Unit Credit Splits by Key [GET /research-sys/api/v1/award-person-unit-credit-splits/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardPersonUnitCreditSplitId": "(val)","awardPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}

### Get All Award Person Unit Credit Splits [GET /research-sys/api/v1/award-person-unit-credit-splits/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardPersonUnitCreditSplitId": "(val)","awardPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"},
              {"awardPersonUnitCreditSplitId": "(val)","awardPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Person Unit Credit Splits with Filtering [GET /research-sys/api/v1/award-person-unit-credit-splits/]
    
+ Parameters

        + awardPersonUnitCreditSplitId
            + awardPersonUnitId
            + invCreditTypeCode
            + credit

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardPersonUnitCreditSplitId": "(val)","awardPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"},
              {"awardPersonUnitCreditSplitId": "(val)","awardPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Person Unit Credit Splits [GET /research-sys/api/v1/award-person-unit-credit-splits/]
	                                          
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
    
            {"columns":["awardPersonUnitCreditSplitId","awardPersonUnitId","invCreditTypeCode","credit"],"primaryKey":"awardPersonUnitCreditSplitId"}
		
### Get Blueprint API specification for Award Person Unit Credit Splits [GET /research-sys/api/v1/award-person-unit-credit-splits/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Person Unit Credit Splits.md"
            transfer-encoding:chunked


### Update Award Person Unit Credit Splits [PUT /research-sys/api/v1/award-person-unit-credit-splits/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardPersonUnitCreditSplitId": "(val)","awardPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Person Unit Credit Splits [PUT /research-sys/api/v1/award-person-unit-credit-splits/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardPersonUnitCreditSplitId": "(val)","awardPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"},
              {"awardPersonUnitCreditSplitId": "(val)","awardPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Person Unit Credit Splits [POST /research-sys/api/v1/award-person-unit-credit-splits/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardPersonUnitCreditSplitId": "(val)","awardPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardPersonUnitCreditSplitId": "(val)","awardPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Person Unit Credit Splits [POST /research-sys/api/v1/award-person-unit-credit-splits/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardPersonUnitCreditSplitId": "(val)","awardPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"},
              {"awardPersonUnitCreditSplitId": "(val)","awardPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardPersonUnitCreditSplitId": "(val)","awardPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"},
              {"awardPersonUnitCreditSplitId": "(val)","awardPersonUnitId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Person Unit Credit Splits by Key [DELETE /research-sys/api/v1/award-person-unit-credit-splits/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Person Unit Credit Splits [DELETE /research-sys/api/v1/award-person-unit-credit-splits/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Person Unit Credit Splits with Matching [DELETE /research-sys/api/v1/award-person-unit-credit-splits/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + awardPersonUnitCreditSplitId
            + awardPersonUnitId
            + invCreditTypeCode
            + credit

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
