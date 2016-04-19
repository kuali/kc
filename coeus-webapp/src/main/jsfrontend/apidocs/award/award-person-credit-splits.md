## Award Person Credit Splits [/award/api/v1/award-person-credit-splits/]

### Get Award Person Credit Splits by Key [GET /award/api/v1/award-person-credit-splits/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardPersonCreditSplitId": "(val)","awardContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}

### Get All Award Person Credit Splits [GET /award/api/v1/award-person-credit-splits/]
	 
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

### Get All Award Person Credit Splits with Filtering [GET /award/api/v1/award-person-credit-splits/]
    
+ Parameters

    + awardPersonCreditSplitId (optional) - 
    + awardContactId (optional) - 
    + invCreditTypeCode (optional) - 
    + credit (optional) - Credit. Maximum length is 6.

            
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
			
### Get Schema for Award Person Credit Splits [GET /award/api/v1/award-person-credit-splits/]
	                                          
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
    
            {"columns":["awardPersonCreditSplitId","awardContactId","invCreditTypeCode","credit"],"primaryKey":"awardPersonCreditSplitId"}
		
### Get Blueprint API specification for Award Person Credit Splits [GET /award/api/v1/award-person-credit-splits/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Person Credit Splits.md"
            transfer-encoding:chunked


### Update Award Person Credit Splits [PUT /award/api/v1/award-person-credit-splits/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardPersonCreditSplitId": "(val)","awardContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Person Credit Splits [PUT /award/api/v1/award-person-credit-splits/]

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

### Insert Award Person Credit Splits [POST /award/api/v1/award-person-credit-splits/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardPersonCreditSplitId": "(val)","awardContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardPersonCreditSplitId": "(val)","awardContactId": "(val)","invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Person Credit Splits [POST /award/api/v1/award-person-credit-splits/]

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
            
### Delete Award Person Credit Splits by Key [DELETE /award/api/v1/award-person-credit-splits/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Person Credit Splits [DELETE /award/api/v1/award-person-credit-splits/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Person Credit Splits with Matching [DELETE /award/api/v1/award-person-credit-splits/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + awardPersonCreditSplitId (optional) - 
    + awardContactId (optional) - 
    + invCreditTypeCode (optional) - 
    + credit (optional) - Credit. Maximum length is 6.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
