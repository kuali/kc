## Sponsor Form Template Lists [/research-common/api/v1/sponsor-form-template-lists/]

### Get Sponsor Form Template Lists by Key [GET /research-common/api/v1/sponsor-form-template-lists/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","pageNumber": "(val)","pageDescription": "(val)","_primaryKey": "(val)"}

### Get All Sponsor Form Template Lists [GET /research-common/api/v1/sponsor-form-template-lists/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","pageNumber": "(val)","pageDescription": "(val)","_primaryKey": "(val)"},
              {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","pageNumber": "(val)","pageDescription": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sponsor Form Template Lists with Filtering [GET /research-common/api/v1/sponsor-form-template-lists/]
    
+ Parameters

    + sponsorFormTemplateId (optional) - 
    + sponsorFormId (optional) - 
    + pageNumber (optional) - 
    + pageDescription (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","pageNumber": "(val)","pageDescription": "(val)","_primaryKey": "(val)"},
              {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","pageNumber": "(val)","pageDescription": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sponsor Form Template Lists [GET /research-common/api/v1/sponsor-form-template-lists/]
	                                          
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
    
            {"columns":["sponsorFormTemplateId","sponsorFormId","pageNumber","pageDescription"],"primaryKey":"sponsorFormTemplateId"}
		
### Get Blueprint API specification for Sponsor Form Template Lists [GET /research-common/api/v1/sponsor-form-template-lists/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sponsor Form Template Lists.md"
            transfer-encoding:chunked
### Update Sponsor Form Template Lists [PUT /research-common/api/v1/sponsor-form-template-lists/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","pageNumber": "(val)","pageDescription": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sponsor Form Template Lists [PUT /research-common/api/v1/sponsor-form-template-lists/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","pageNumber": "(val)","pageDescription": "(val)","_primaryKey": "(val)"},
              {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","pageNumber": "(val)","pageDescription": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Sponsor Form Template Lists [POST /research-common/api/v1/sponsor-form-template-lists/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","pageNumber": "(val)","pageDescription": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","pageNumber": "(val)","pageDescription": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sponsor Form Template Lists [POST /research-common/api/v1/sponsor-form-template-lists/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","pageNumber": "(val)","pageDescription": "(val)","_primaryKey": "(val)"},
              {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","pageNumber": "(val)","pageDescription": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","pageNumber": "(val)","pageDescription": "(val)","_primaryKey": "(val)"},
              {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","pageNumber": "(val)","pageDescription": "(val)","_primaryKey": "(val)"}
            ]
### Delete Sponsor Form Template Lists by Key [DELETE /research-common/api/v1/sponsor-form-template-lists/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sponsor Form Template Lists [DELETE /research-common/api/v1/sponsor-form-template-lists/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sponsor Form Template Lists with Matching [DELETE /research-common/api/v1/sponsor-form-template-lists/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + sponsorFormTemplateId (optional) - 
    + sponsorFormId (optional) - 
    + pageNumber (optional) - 
    + pageDescription (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
