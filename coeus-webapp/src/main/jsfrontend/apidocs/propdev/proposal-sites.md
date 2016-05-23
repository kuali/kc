## Proposal Sites [/propdev/api/v1/proposal-sites/]

### Get Proposal Sites by Key [GET /propdev/api/v1/proposal-sites/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}

### Get All Proposal Sites [GET /propdev/api/v1/proposal-sites/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Sites with Filtering [GET /propdev/api/v1/proposal-sites/]
    
+ Parameters

    + siteNumber (optional) - The site number within the proposal. Maximum length is 3.
    + locationName (optional) - The name to display for the Proposal Site; may differ from the name in the rolodex. Maximum length is 60.
    + locationTypeCode (optional) - The type of location (applicant org., performing org., etc.). Maximum length is 3.
    + organizationId (optional) - This is the foreign key into the Organization. Maximum length is 8.
    + rolodexId (optional) - Rolodex Id. Maximum length is 6.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Sites [GET /propdev/api/v1/proposal-sites/]
	                                          
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
    
            {"columns":["siteNumber","locationName","locationTypeCode","organizationId","rolodexId"],"primaryKey":"developmentProposal:siteNumber"}
		
### Get Blueprint API specification for Proposal Sites [GET /propdev/api/v1/proposal-sites/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Sites.md"
            transfer-encoding:chunked
### Update Proposal Sites [PUT /propdev/api/v1/proposal-sites/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Sites [PUT /propdev/api/v1/proposal-sites/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Proposal Sites [POST /propdev/api/v1/proposal-sites/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Sites [POST /propdev/api/v1/proposal-sites/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"},
              {"siteNumber": "(val)","locationName": "(val)","locationTypeCode": "(val)","organizationId": "(val)","rolodexId": "(val)","_primaryKey": "(val)"}
            ]
### Delete Proposal Sites by Key [DELETE /propdev/api/v1/proposal-sites/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Sites [DELETE /propdev/api/v1/proposal-sites/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Sites with Matching [DELETE /propdev/api/v1/proposal-sites/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + siteNumber (optional) - The site number within the proposal. Maximum length is 3.
    + locationName (optional) - The name to display for the Proposal Site; may differ from the name in the rolodex. Maximum length is 60.
    + locationTypeCode (optional) - The type of location (applicant org., performing org., etc.). Maximum length is 3.
    + organizationId (optional) - This is the foreign key into the Organization. Maximum length is 8.
    + rolodexId (optional) - Rolodex Id. Maximum length is 6.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
