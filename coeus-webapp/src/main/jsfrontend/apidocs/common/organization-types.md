## Organization Types [/research-common/api/v1/organization-types/]

### Get Organization Types by Key [GET /research-common/api/v1/organization-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Organization Types [GET /research-common/api/v1/organization-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"},
              {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Organization Types with Filtering [GET /research-common/api/v1/organization-types/]
    
+ Parameters

    + organizationId (optional) - Organization Id. Maximum length is 8.
    + organizationTypeCode (optional) - Organization Type Code. Maximum length is 3.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"},
              {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Organization Types [GET /research-common/api/v1/organization-types/]
	                                          
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
    
            {"columns":["organizationId","organizationTypeCode"],"primaryKey":"organizationId:organizationTypeCode"}
		
### Get Blueprint API specification for Organization Types [GET /research-common/api/v1/organization-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Organization Types.md"
            transfer-encoding:chunked


### Update Organization Types [PUT /research-common/api/v1/organization-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Organization Types [PUT /research-common/api/v1/organization-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"},
              {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Organization Types [POST /research-common/api/v1/organization-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Organization Types [POST /research-common/api/v1/organization-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"},
              {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"},
              {"organizationId": "(val)","organizationTypeCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Organization Types by Key [DELETE /research-common/api/v1/organization-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Organization Types [DELETE /research-common/api/v1/organization-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Organization Types with Matching [DELETE /research-common/api/v1/organization-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + organizationId (optional) - Organization Id. Maximum length is 8.
    + organizationTypeCode (optional) - Organization Type Code. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
