## Organization Audits [/research-sys/api/v1/organization-audits/]

### Get Organization Audits by Key [GET /research-sys/api/v1/organization-audits/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"fiscalYear": "(val)","organizationId": "(val)","auditAcceptedCode": "(val)","auditComment": "(val)","_primaryKey": "(val)"}

### Get All Organization Audits [GET /research-sys/api/v1/organization-audits/]
	 
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

### Get All Organization Audits with Filtering [GET /research-sys/api/v1/organization-audits/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + fiscalYear
            + organizationId
            + auditAcceptedCode
            + auditComment
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"fiscalYear": "(val)","organizationId": "(val)","auditAcceptedCode": "(val)","auditComment": "(val)","_primaryKey": "(val)"},
              {"fiscalYear": "(val)","organizationId": "(val)","auditAcceptedCode": "(val)","auditComment": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Organization Audits [GET /research-sys/api/v1/organization-audits/]
	 
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
		
### Get Blueprint API specification for Organization Audits [GET /research-sys/api/v1/organization-audits/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Organization Audits.md"
            transfer-encoding:chunked


### Update Organization Audits [PUT /research-sys/api/v1/organization-audits/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"fiscalYear": "(val)","organizationId": "(val)","auditAcceptedCode": "(val)","auditComment": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Organization Audits [PUT /research-sys/api/v1/organization-audits/]

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

### Insert Organization Audits [POST /research-sys/api/v1/organization-audits/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"fiscalYear": "(val)","organizationId": "(val)","auditAcceptedCode": "(val)","auditComment": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"fiscalYear": "(val)","organizationId": "(val)","auditAcceptedCode": "(val)","auditComment": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Organization Audits [POST /research-sys/api/v1/organization-audits/]

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
            
### Delete Organization Audits by Key [DELETE /research-sys/api/v1/organization-audits/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Organization Audits [DELETE /research-sys/api/v1/organization-audits/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Organization Audits with Matching [DELETE /research-sys/api/v1/organization-audits/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + fiscalYear
            + organizationId
            + auditAcceptedCode
            + auditComment


+ Response 204
