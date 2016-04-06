## Award Person Credit Splits [/research-sys/api/v1/award-person-credit-splits/]

### Get Award Person Credit Splits by Key [GET /research-sys/api/v1/award-person-credit-splits/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardPersonCreditSplitId": "(val)","awardContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}

### Get All Award Person Credit Splits [GET /research-sys/api/v1/award-person-credit-splits/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardPersonCreditSplitId": "(val)","awardContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"},
              {"awardPersonCreditSplitId": "(val)","awardContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Person Credit Splits with Filtering [GET /research-sys/api/v1/award-person-credit-splits/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + awardPersonCreditSplitId
            + awardContactId
            + invCreditTypeCode
            + credit
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardPersonCreditSplitId": "(val)","awardContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"},
              {"awardPersonCreditSplitId": "(val)","awardContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Person Credit Splits [GET /research-sys/api/v1/award-person-credit-splits/]
	 
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
		
### Get Blueprint API specification for Award Person Credit Splits [GET /research-sys/api/v1/award-person-credit-splits/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Person Credit Splits.md"
            transfer-encoding:chunked


### Update Award Person Credit Splits [PUT /research-sys/api/v1/award-person-credit-splits/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardPersonCreditSplitId": "(val)","awardContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Person Credit Splits [PUT /research-sys/api/v1/award-person-credit-splits/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardPersonCreditSplitId": "(val)","awardContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"},
              {"awardPersonCreditSplitId": "(val)","awardContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Person Credit Splits [POST /research-sys/api/v1/award-person-credit-splits/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardPersonCreditSplitId": "(val)","awardContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardPersonCreditSplitId": "(val)","awardContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Person Credit Splits [POST /research-sys/api/v1/award-person-credit-splits/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardPersonCreditSplitId": "(val)","awardContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"},
              {"awardPersonCreditSplitId": "(val)","awardContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardPersonCreditSplitId": "(val)","awardContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"},
              {"awardPersonCreditSplitId": "(val)","awardContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Person Credit Splits by Key [DELETE /research-sys/api/v1/award-person-credit-splits/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Person Credit Splits [DELETE /research-sys/api/v1/award-person-credit-splits/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Award Person Credit Splits with Matching [DELETE /research-sys/api/v1/award-person-credit-splits/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + awardPersonCreditSplitId
            + awardContactId
            + invCreditTypeCode
            + credit


+ Response 204
