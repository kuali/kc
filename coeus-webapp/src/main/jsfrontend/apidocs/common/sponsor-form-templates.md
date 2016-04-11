## Sponsor Form Templates [/research-sys/api/v1/sponsor-form-templates/]

### Get Sponsor Form Templates by Key [GET /research-sys/api/v1/sponsor-form-templates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","attachmentContent": "(val)","pageDescription": "(val)","pageNumber": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}

### Get All Sponsor Form Templates [GET /research-sys/api/v1/sponsor-form-templates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","attachmentContent": "(val)","pageDescription": "(val)","pageNumber": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"},
              {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","attachmentContent": "(val)","pageDescription": "(val)","pageNumber": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sponsor Form Templates with Filtering [GET /research-sys/api/v1/sponsor-form-templates/]
    
+ Parameters

        + sponsorFormTemplateId
            + sponsorFormId
            + attachmentContent
            + pageDescription
            + pageNumber
            + fileName
            + contentType

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","attachmentContent": "(val)","pageDescription": "(val)","pageNumber": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"},
              {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","attachmentContent": "(val)","pageDescription": "(val)","pageNumber": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sponsor Form Templates [GET /research-sys/api/v1/sponsor-form-templates/]
	                                          
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
    
            {"columns":["sponsorFormTemplateId","sponsorFormId","attachmentContent","pageDescription","pageNumber","fileName","contentType"],"primaryKey":"sponsorFormTemplateId"}
		
### Get Blueprint API specification for Sponsor Form Templates [GET /research-sys/api/v1/sponsor-form-templates/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sponsor Form Templates.md"
            transfer-encoding:chunked


### Update Sponsor Form Templates [PUT /research-sys/api/v1/sponsor-form-templates/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","attachmentContent": "(val)","pageDescription": "(val)","pageNumber": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sponsor Form Templates [PUT /research-sys/api/v1/sponsor-form-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","attachmentContent": "(val)","pageDescription": "(val)","pageNumber": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"},
              {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","attachmentContent": "(val)","pageDescription": "(val)","pageNumber": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Sponsor Form Templates [POST /research-sys/api/v1/sponsor-form-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","attachmentContent": "(val)","pageDescription": "(val)","pageNumber": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","attachmentContent": "(val)","pageDescription": "(val)","pageNumber": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sponsor Form Templates [POST /research-sys/api/v1/sponsor-form-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","attachmentContent": "(val)","pageDescription": "(val)","pageNumber": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"},
              {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","attachmentContent": "(val)","pageDescription": "(val)","pageNumber": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","attachmentContent": "(val)","pageDescription": "(val)","pageNumber": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"},
              {"sponsorFormTemplateId": "(val)","sponsorFormId": "(val)","attachmentContent": "(val)","pageDescription": "(val)","pageNumber": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Sponsor Form Templates by Key [DELETE /research-sys/api/v1/sponsor-form-templates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sponsor Form Templates [DELETE /research-sys/api/v1/sponsor-form-templates/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sponsor Form Templates with Matching [DELETE /research-sys/api/v1/sponsor-form-templates/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + sponsorFormTemplateId
            + sponsorFormId
            + attachmentContent
            + pageDescription
            + pageNumber
            + fileName
            + contentType

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
