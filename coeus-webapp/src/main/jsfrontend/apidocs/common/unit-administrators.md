## Unit Administrators [/research-sys/api/v1/unit-administrators/]

### Get Unit Administrators by Key [GET /research-sys/api/v1/unit-administrators/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"personId": "(val)","unitAdministratorTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}

### Get All Unit Administrators [GET /research-sys/api/v1/unit-administrators/]
	 
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

### Get All Unit Administrators with Filtering [GET /research-sys/api/v1/unit-administrators/]
    
+ Parameters

        + personId
            + unitAdministratorTypeCode
            + unitNumber

            
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
			
### Get Schema for Unit Administrators [GET /research-sys/api/v1/unit-administrators/]
	                                          
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
		
### Get Blueprint API specification for Unit Administrators [GET /research-sys/api/v1/unit-administrators/]
	 
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


### Update Unit Administrators [PUT /research-sys/api/v1/unit-administrators/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personId": "(val)","unitAdministratorTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Unit Administrators [PUT /research-sys/api/v1/unit-administrators/]

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

### Insert Unit Administrators [POST /research-sys/api/v1/unit-administrators/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personId": "(val)","unitAdministratorTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"personId": "(val)","unitAdministratorTypeCode": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Unit Administrators [POST /research-sys/api/v1/unit-administrators/]

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
            
### Delete Unit Administrators by Key [DELETE /research-sys/api/v1/unit-administrators/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Unit Administrators [DELETE /research-sys/api/v1/unit-administrators/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Unit Administrators with Matching [DELETE /research-sys/api/v1/unit-administrators/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + personId
            + unitAdministratorTypeCode
            + unitNumber

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
