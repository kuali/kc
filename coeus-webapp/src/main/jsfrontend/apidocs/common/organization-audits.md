## Organization Audits [/research-common/api/v1/organization-audits/]

### Get Organization Audits by Key [GET /research-common/api/v1/organization-audits/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"fiscalYear": "(val)","organizationId": "(val)","auditAcceptedCode": "(val)","auditComment": "(val)","_primaryKey": "(val)"}

### Get All Organization Audits [GET /research-common/api/v1/organization-audits/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"fiscalYear": "(val)","organizationId": "(val)","auditAcceptedCode": "(val)","auditComment": "(val)","_primaryKey": "(val)"},
              {"fiscalYear": "(val)","organizationId": "(val)","auditAcceptedCode": "(val)","auditComment": "(val)","_primaryKey": "(val)"}
            ]

### Get All Organization Audits with Filtering [GET /research-common/api/v1/organization-audits/]
    
+ Parameters

    + fiscalYear (optional) - Fiscal Year. Maximum length is 4.
    + organizationId (optional) - Organization Id. Maximum length is 8.
    + auditAcceptedCode (optional) - Audit Accepted Type. Maximum length is 3.
    + auditComment (optional) - Audit Comment. Maximum length is 300.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"fiscalYear": "(val)","organizationId": "(val)","auditAcceptedCode": "(val)","auditComment": "(val)","_primaryKey": "(val)"},
              {"fiscalYear": "(val)","organizationId": "(val)","auditAcceptedCode": "(val)","auditComment": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Organization Audits [GET /research-common/api/v1/organization-audits/]
	                                          
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
    
            {"columns":["fiscalYear","organizationId","auditAcceptedCode","auditComment"],"primaryKey":"fiscalYear:organizationId"}
		
### Get Blueprint API specification for Organization Audits [GET /research-common/api/v1/organization-audits/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Organization Audits.md"
            transfer-encoding:chunked


### Update Organization Audits [PUT /research-common/api/v1/organization-audits/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"fiscalYear": "(val)","organizationId": "(val)","auditAcceptedCode": "(val)","auditComment": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Organization Audits [PUT /research-common/api/v1/organization-audits/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"fiscalYear": "(val)","organizationId": "(val)","auditAcceptedCode": "(val)","auditComment": "(val)","_primaryKey": "(val)"},
              {"fiscalYear": "(val)","organizationId": "(val)","auditAcceptedCode": "(val)","auditComment": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Organization Audits [POST /research-common/api/v1/organization-audits/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"fiscalYear": "(val)","organizationId": "(val)","auditAcceptedCode": "(val)","auditComment": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"fiscalYear": "(val)","organizationId": "(val)","auditAcceptedCode": "(val)","auditComment": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Organization Audits [POST /research-common/api/v1/organization-audits/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"fiscalYear": "(val)","organizationId": "(val)","auditAcceptedCode": "(val)","auditComment": "(val)","_primaryKey": "(val)"},
              {"fiscalYear": "(val)","organizationId": "(val)","auditAcceptedCode": "(val)","auditComment": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"fiscalYear": "(val)","organizationId": "(val)","auditAcceptedCode": "(val)","auditComment": "(val)","_primaryKey": "(val)"},
              {"fiscalYear": "(val)","organizationId": "(val)","auditAcceptedCode": "(val)","auditComment": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Organization Audits by Key [DELETE /research-common/api/v1/organization-audits/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Organization Audits [DELETE /research-common/api/v1/organization-audits/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Organization Audits with Matching [DELETE /research-common/api/v1/organization-audits/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + fiscalYear (optional) - Fiscal Year. Maximum length is 4.
    + organizationId (optional) - Organization Id. Maximum length is 8.
    + auditAcceptedCode (optional) - Audit Accepted Type. Maximum length is 3.
    + auditComment (optional) - Audit Comment. Maximum length is 300.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
