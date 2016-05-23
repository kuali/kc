## Proposal Person Biographies [/propdev/api/v1/proposal-person-biographies/]

### Get Proposal Person Biographies by Key [GET /propdev/api/v1/proposal-person-biographies/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalPersonNumber": "(val)","personId": "(val)","biographyNumber": "(val)","rolodexId": "(val)","description": "(val)","documentTypeCode": "(val)","name": "(val)","type": "(val)","_primaryKey": "(val)"}

### Get All Proposal Person Biographies [GET /propdev/api/v1/proposal-person-biographies/]
	 
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

### Get All Proposal Person Biographies with Filtering [GET /propdev/api/v1/proposal-person-biographies/]
    
+ Parameters

    + proposalPersonNumber (optional) - This field is auto-populated with the names of the individuals listed in the key personnel section of the proposal. Maximum length is 12.
    + personId (optional) - KcPerson Id. Maximum length is 40.
    + biographyNumber (optional) - Biography Number. Maximum length is 3.
    + rolodexId (optional) - Rolodex Id. Maximum length is 6.
    + description (optional) - Description. Maximum length is 200.
    + documentTypeCode (optional) - This field contains a list of personnel attachment types. Maximum length is 3.
    + name (optional) - This is the name of the file path and name that the user is uploading; can be typed in or the user can use the “browse�? feature to find the file on their computer or attached mass storage device. Maximum length is 150.
    + type (optional) - Type.

            
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
			
### Get Schema for Proposal Person Biographies [GET /propdev/api/v1/proposal-person-biographies/]
	                                          
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
		
### Get Blueprint API specification for Proposal Person Biographies [GET /propdev/api/v1/proposal-person-biographies/]
	 
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
### Update Proposal Person Biographies [PUT /propdev/api/v1/proposal-person-biographies/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalPersonNumber": "(val)","personId": "(val)","biographyNumber": "(val)","rolodexId": "(val)","description": "(val)","documentTypeCode": "(val)","name": "(val)","type": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Person Biographies [PUT /propdev/api/v1/proposal-person-biographies/]

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
### Insert Proposal Person Biographies [POST /propdev/api/v1/proposal-person-biographies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalPersonNumber": "(val)","personId": "(val)","biographyNumber": "(val)","rolodexId": "(val)","description": "(val)","documentTypeCode": "(val)","name": "(val)","type": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalPersonNumber": "(val)","personId": "(val)","biographyNumber": "(val)","rolodexId": "(val)","description": "(val)","documentTypeCode": "(val)","name": "(val)","type": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Person Biographies [POST /propdev/api/v1/proposal-person-biographies/]

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
### Delete Proposal Person Biographies by Key [DELETE /propdev/api/v1/proposal-person-biographies/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Person Biographies [DELETE /propdev/api/v1/proposal-person-biographies/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Person Biographies with Matching [DELETE /propdev/api/v1/proposal-person-biographies/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + proposalPersonNumber (optional) - This field is auto-populated with the names of the individuals listed in the key personnel section of the proposal. Maximum length is 12.
    + personId (optional) - KcPerson Id. Maximum length is 40.
    + biographyNumber (optional) - Biography Number. Maximum length is 3.
    + rolodexId (optional) - Rolodex Id. Maximum length is 6.
    + description (optional) - Description. Maximum length is 200.
    + documentTypeCode (optional) - This field contains a list of personnel attachment types. Maximum length is 3.
    + name (optional) - This is the name of the file path and name that the user is uploading; can be typed in or the user can use the “browse�? feature to find the file on their computer or attached mass storage device. Maximum length is 150.
    + type (optional) - Type.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
