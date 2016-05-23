## Units [/research-common/api/v1/units/]

### Get Units by Key [GET /research-common/api/v1/units/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"unitNumber": "(val)","parentUnitNumber": "(val)","organizationId": "(val)","unitName": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Units [GET /research-common/api/v1/units/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"unitNumber": "(val)","parentUnitNumber": "(val)","organizationId": "(val)","unitName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"unitNumber": "(val)","parentUnitNumber": "(val)","organizationId": "(val)","unitName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Units with Filtering [GET /research-common/api/v1/units/]
    
+ Parameters

    + unitNumber (optional) - Unit Number. Maximum length is 8.
    + parentUnitNumber (optional) - Parent Unit Number. Maximum length is 8.
    + organizationId (optional) - Organization Id. Maximum length is 8.
    + unitName (optional) - Unit Name. Maximum length is 60.
    + active (optional) - Active. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"unitNumber": "(val)","parentUnitNumber": "(val)","organizationId": "(val)","unitName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"unitNumber": "(val)","parentUnitNumber": "(val)","organizationId": "(val)","unitName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Units [GET /research-common/api/v1/units/]
	                                          
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
    
            {"columns":["unitNumber","parentUnitNumber","organizationId","unitName","active"],"primaryKey":"unitNumber"}
		
### Get Blueprint API specification for Units [GET /research-common/api/v1/units/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Units.md"
            transfer-encoding:chunked
### Update Units [PUT /research-common/api/v1/units/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"unitNumber": "(val)","parentUnitNumber": "(val)","organizationId": "(val)","unitName": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Units [PUT /research-common/api/v1/units/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"unitNumber": "(val)","parentUnitNumber": "(val)","organizationId": "(val)","unitName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"unitNumber": "(val)","parentUnitNumber": "(val)","organizationId": "(val)","unitName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Units [POST /research-common/api/v1/units/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"unitNumber": "(val)","parentUnitNumber": "(val)","organizationId": "(val)","unitName": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"unitNumber": "(val)","parentUnitNumber": "(val)","organizationId": "(val)","unitName": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Units [POST /research-common/api/v1/units/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"unitNumber": "(val)","parentUnitNumber": "(val)","organizationId": "(val)","unitName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"unitNumber": "(val)","parentUnitNumber": "(val)","organizationId": "(val)","unitName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"unitNumber": "(val)","parentUnitNumber": "(val)","organizationId": "(val)","unitName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"unitNumber": "(val)","parentUnitNumber": "(val)","organizationId": "(val)","unitName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete Units by Key [DELETE /research-common/api/v1/units/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Units [DELETE /research-common/api/v1/units/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Units with Matching [DELETE /research-common/api/v1/units/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + unitNumber (optional) - Unit Number. Maximum length is 8.
    + parentUnitNumber (optional) - Parent Unit Number. Maximum length is 8.
    + organizationId (optional) - Organization Id. Maximum length is 8.
    + unitName (optional) - Unit Name. Maximum length is 60.
    + active (optional) - Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
