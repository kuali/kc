## Sponsor Form Template Lists [/research-sys/api/v1/sponsor-form-template-lists/]

### Get Sponsor Form Template Lists by Key [GET /research-sys/api/v1/sponsor-form-template-lists/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","pageNumber": "(val)","pageDescription": "(val)","_primaryKey": "(val)"}

### Get All Sponsor Form Template Lists [GET /research-sys/api/v1/sponsor-form-template-lists/]
	 
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

### Get All Sponsor Form Template Lists with Filtering [GET /research-sys/api/v1/sponsor-form-template-lists/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + sponsorFormTemplateId
            + sponsorFormId
            + pageNumber
            + pageDescription
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","pageNumber": "(val)","pageDescription": "(val)","_primaryKey": "(val)"},
              {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","pageNumber": "(val)","pageDescription": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sponsor Form Template Lists [GET /research-sys/api/v1/sponsor-form-template-lists/]
	 
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
		
### Get Blueprint API specification for Sponsor Form Template Lists [GET /research-sys/api/v1/sponsor-form-template-lists/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sponsor Form Template Lists.md"
            transfer-encoding:chunked


### Update Sponsor Form Template Lists [PUT /research-sys/api/v1/sponsor-form-template-lists/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","pageNumber": "(val)","pageDescription": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sponsor Form Template Lists [PUT /research-sys/api/v1/sponsor-form-template-lists/]

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

### Insert Sponsor Form Template Lists [POST /research-sys/api/v1/sponsor-form-template-lists/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","pageNumber": "(val)","pageDescription": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","pageNumber": "(val)","pageDescription": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sponsor Form Template Lists [POST /research-sys/api/v1/sponsor-form-template-lists/]

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
            
### Delete Sponsor Form Template Lists by Key [DELETE /research-sys/api/v1/sponsor-form-template-lists/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sponsor Form Template Lists [DELETE /research-sys/api/v1/sponsor-form-template-lists/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Sponsor Form Template Lists with Matching [DELETE /research-sys/api/v1/sponsor-form-template-lists/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + sponsorFormTemplateId
            + sponsorFormId
            + pageNumber
            + pageDescription


+ Response 204
