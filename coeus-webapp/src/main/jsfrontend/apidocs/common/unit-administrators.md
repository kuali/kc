## Unit Administrators [/research-common/api/v1/unit-administrators/]

### Get Unit Administrators by Key [GET /research-common/api/v1/unit-administrators/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"personId": "(val)","unitAdministratorTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}

### Get All Unit Administrators [GET /research-common/api/v1/unit-administrators/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"personId": "(val)","unitAdministratorTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"},
              {"personId": "(val)","unitAdministratorTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Unit Administrators with Filtering [GET /research-common/api/v1/unit-administrators/]
    
+ Parameters

    + personId (optional) - KC Person. Maximum length is 40.
    + unitAdministratorTypeCode (optional) - Unit Administrator Type Code. Maximum length is 3.
    + unitNumber (optional) - Unit Number. Maximum length is 8.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"personId": "(val)","unitAdministratorTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"},
              {"personId": "(val)","unitAdministratorTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Unit Administrators [GET /research-common/api/v1/unit-administrators/]
	                                          
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
    
            {"columns":["personId","unitAdministratorTypeCode","unitNumber"],"primaryKey":"personId:unitAdministratorTypeCode:unitNumber"}
		
### Get Blueprint API specification for Unit Administrators [GET /research-common/api/v1/unit-administrators/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Unit Administrators.md"
            transfer-encoding:chunked
### Update Unit Administrators [PUT /research-common/api/v1/unit-administrators/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personId": "(val)","unitAdministratorTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Unit Administrators [PUT /research-common/api/v1/unit-administrators/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"personId": "(val)","unitAdministratorTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"},
              {"personId": "(val)","unitAdministratorTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Unit Administrators [POST /research-common/api/v1/unit-administrators/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personId": "(val)","unitAdministratorTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"personId": "(val)","unitAdministratorTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Unit Administrators [POST /research-common/api/v1/unit-administrators/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"personId": "(val)","unitAdministratorTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"},
              {"personId": "(val)","unitAdministratorTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"personId": "(val)","unitAdministratorTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"},
              {"personId": "(val)","unitAdministratorTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
            ]
### Delete Unit Administrators by Key [DELETE /research-common/api/v1/unit-administrators/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Unit Administrators [DELETE /research-common/api/v1/unit-administrators/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Unit Administrators with Matching [DELETE /research-common/api/v1/unit-administrators/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + personId (optional) - KC Person. Maximum length is 40.
    + unitAdministratorTypeCode (optional) - Unit Administrator Type Code. Maximum length is 3.
    + unitNumber (optional) - Unit Number. Maximum length is 8.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
