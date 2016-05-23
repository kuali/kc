## Award Person Units [/award/api/v1/award-person-units/]

### Get Award Person Units by Key [GET /award/api/v1/award-person-units/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardPersonUnitId": "(val)","awardContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}

### Get All Award Person Units [GET /award/api/v1/award-person-units/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardPersonUnitId": "(val)","awardContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"},
              {"awardPersonUnitId": "(val)","awardContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Person Units with Filtering [GET /award/api/v1/award-person-units/]
    
+ Parameters

    + awardPersonUnitId (optional) - AwardPersonUnit ID. Maximum length is 8.
    + awardContactId (optional) - 
    + unitNumber (optional) - Unit Number. Maximum length is 8.
    + leadUnit (optional) - Lead Unit flag. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardPersonUnitId": "(val)","awardContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"},
              {"awardPersonUnitId": "(val)","awardContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Person Units [GET /award/api/v1/award-person-units/]
	                                          
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
    
            {"columns":["awardPersonUnitId","awardContactId","unitNumber","leadUnit"],"primaryKey":"awardPersonUnitId"}
		
### Get Blueprint API specification for Award Person Units [GET /award/api/v1/award-person-units/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Person Units.md"
            transfer-encoding:chunked
### Update Award Person Units [PUT /award/api/v1/award-person-units/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardPersonUnitId": "(val)","awardContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Person Units [PUT /award/api/v1/award-person-units/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardPersonUnitId": "(val)","awardContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"},
              {"awardPersonUnitId": "(val)","awardContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Award Person Units [POST /award/api/v1/award-person-units/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardPersonUnitId": "(val)","awardContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardPersonUnitId": "(val)","awardContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Person Units [POST /award/api/v1/award-person-units/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardPersonUnitId": "(val)","awardContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"},
              {"awardPersonUnitId": "(val)","awardContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardPersonUnitId": "(val)","awardContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"},
              {"awardPersonUnitId": "(val)","awardContactId": "(val)","unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}
            ]
### Delete Award Person Units by Key [DELETE /award/api/v1/award-person-units/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Person Units [DELETE /award/api/v1/award-person-units/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Person Units with Matching [DELETE /award/api/v1/award-person-units/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + awardPersonUnitId (optional) - AwardPersonUnit ID. Maximum length is 8.
    + awardContactId (optional) - 
    + unitNumber (optional) - Unit Number. Maximum length is 8.
    + leadUnit (optional) - Lead Unit flag. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
