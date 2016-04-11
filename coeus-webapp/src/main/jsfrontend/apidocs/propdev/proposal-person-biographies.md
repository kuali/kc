## Proposal Person Biographies [/research-sys/api/v1/proposal-person-biographies/]

### Get Proposal Person Biographies by Key [GET /research-sys/api/v1/proposal-person-biographies/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalPersonNumber": "(val)","personId": "(val)","biographyNumber": "(val)","rolodexId": "(val)","description": "(val)","documentTypeCode": "(val)","name": "(val)","type": "(val)","_primaryKey": "(val)"}

### Get All Proposal Person Biographies [GET /research-sys/api/v1/proposal-person-biographies/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalPersonNumber": "(val)","personId": "(val)","biographyNumber": "(val)","rolodexId": "(val)","description": "(val)","documentTypeCode": "(val)","name": "(val)","type": "(val)","_primaryKey": "(val)"},
              {"proposalPersonNumber": "(val)","personId": "(val)","biographyNumber": "(val)","rolodexId": "(val)","description": "(val)","documentTypeCode": "(val)","name": "(val)","type": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Person Biographies with Filtering [GET /research-sys/api/v1/proposal-person-biographies/]
    
+ Parameters

        + proposalPersonNumber
            + personId
            + biographyNumber
            + rolodexId
            + description
            + documentTypeCode
            + name
            + type

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalPersonNumber": "(val)","personId": "(val)","biographyNumber": "(val)","rolodexId": "(val)","description": "(val)","documentTypeCode": "(val)","name": "(val)","type": "(val)","_primaryKey": "(val)"},
              {"proposalPersonNumber": "(val)","personId": "(val)","biographyNumber": "(val)","rolodexId": "(val)","description": "(val)","documentTypeCode": "(val)","name": "(val)","type": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Person Biographies [GET /research-sys/api/v1/proposal-person-biographies/]
	                                          
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
    
            {"columns":["proposalPersonNumber","personId","biographyNumber","rolodexId","description","documentTypeCode","name","type"],"primaryKey":"biographyNumber:developmentProposal:proposalPersonNumber"}
		
### Get Blueprint API specification for Proposal Person Biographies [GET /research-sys/api/v1/proposal-person-biographies/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Person Biographies.md"
            transfer-encoding:chunked


### Update Proposal Person Biographies [PUT /research-sys/api/v1/proposal-person-biographies/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalPersonNumber": "(val)","personId": "(val)","biographyNumber": "(val)","rolodexId": "(val)","description": "(val)","documentTypeCode": "(val)","name": "(val)","type": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Person Biographies [PUT /research-sys/api/v1/proposal-person-biographies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalPersonNumber": "(val)","personId": "(val)","biographyNumber": "(val)","rolodexId": "(val)","description": "(val)","documentTypeCode": "(val)","name": "(val)","type": "(val)","_primaryKey": "(val)"},
              {"proposalPersonNumber": "(val)","personId": "(val)","biographyNumber": "(val)","rolodexId": "(val)","description": "(val)","documentTypeCode": "(val)","name": "(val)","type": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Proposal Person Biographies [POST /research-sys/api/v1/proposal-person-biographies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalPersonNumber": "(val)","personId": "(val)","biographyNumber": "(val)","rolodexId": "(val)","description": "(val)","documentTypeCode": "(val)","name": "(val)","type": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalPersonNumber": "(val)","personId": "(val)","biographyNumber": "(val)","rolodexId": "(val)","description": "(val)","documentTypeCode": "(val)","name": "(val)","type": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Person Biographies [POST /research-sys/api/v1/proposal-person-biographies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalPersonNumber": "(val)","personId": "(val)","biographyNumber": "(val)","rolodexId": "(val)","description": "(val)","documentTypeCode": "(val)","name": "(val)","type": "(val)","_primaryKey": "(val)"},
              {"proposalPersonNumber": "(val)","personId": "(val)","biographyNumber": "(val)","rolodexId": "(val)","description": "(val)","documentTypeCode": "(val)","name": "(val)","type": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalPersonNumber": "(val)","personId": "(val)","biographyNumber": "(val)","rolodexId": "(val)","description": "(val)","documentTypeCode": "(val)","name": "(val)","type": "(val)","_primaryKey": "(val)"},
              {"proposalPersonNumber": "(val)","personId": "(val)","biographyNumber": "(val)","rolodexId": "(val)","description": "(val)","documentTypeCode": "(val)","name": "(val)","type": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Proposal Person Biographies by Key [DELETE /research-sys/api/v1/proposal-person-biographies/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Person Biographies [DELETE /research-sys/api/v1/proposal-person-biographies/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Person Biographies with Matching [DELETE /research-sys/api/v1/proposal-person-biographies/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + proposalPersonNumber
            + personId
            + biographyNumber
            + rolodexId
            + description
            + documentTypeCode
            + name
            + type

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
