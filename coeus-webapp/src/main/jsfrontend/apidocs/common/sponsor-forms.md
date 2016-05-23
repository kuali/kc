## Sponsor Forms [/research-common/api/v1/sponsor-forms/]

### Get Sponsor Forms by Key [GET /research-common/api/v1/sponsor-forms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"sponsorFormId": "(val)","packageName": "(val)","packageNumber": "(val)","sponsorCode": "(val)","sponsorHierarchyName": "(val)","_primaryKey": "(val)"}

### Get All Sponsor Forms [GET /research-common/api/v1/sponsor-forms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"sponsorFormId": "(val)","packageName": "(val)","packageNumber": "(val)","sponsorCode": "(val)","sponsorHierarchyName": "(val)","_primaryKey": "(val)"},
              {"sponsorFormId": "(val)","packageName": "(val)","packageNumber": "(val)","sponsorCode": "(val)","sponsorHierarchyName": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sponsor Forms with Filtering [GET /research-common/api/v1/sponsor-forms/]
    
+ Parameters

    + sponsorFormId (optional) - Sponsor Form Id. Maximum length is 12.
    + packageName (optional) - Package Name. Maximum length is 200.
    + packageNumber (optional) - Package Number. Maximum length is 3.
    + sponsorCode (optional) - Sponsor Code. Maximum length is 6.
    + sponsorHierarchyName (optional) - Sponsor Hierarchy Group Name. Maximum length is 50.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"sponsorFormId": "(val)","packageName": "(val)","packageNumber": "(val)","sponsorCode": "(val)","sponsorHierarchyName": "(val)","_primaryKey": "(val)"},
              {"sponsorFormId": "(val)","packageName": "(val)","packageNumber": "(val)","sponsorCode": "(val)","sponsorHierarchyName": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sponsor Forms [GET /research-common/api/v1/sponsor-forms/]
	                                          
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
    
            {"columns":["sponsorFormId","packageName","packageNumber","sponsorCode","sponsorHierarchyName"],"primaryKey":"sponsorFormId"}
		
### Get Blueprint API specification for Sponsor Forms [GET /research-common/api/v1/sponsor-forms/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sponsor Forms.md"
            transfer-encoding:chunked
### Update Sponsor Forms [PUT /research-common/api/v1/sponsor-forms/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"sponsorFormId": "(val)","packageName": "(val)","packageNumber": "(val)","sponsorCode": "(val)","sponsorHierarchyName": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sponsor Forms [PUT /research-common/api/v1/sponsor-forms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"sponsorFormId": "(val)","packageName": "(val)","packageNumber": "(val)","sponsorCode": "(val)","sponsorHierarchyName": "(val)","_primaryKey": "(val)"},
              {"sponsorFormId": "(val)","packageName": "(val)","packageNumber": "(val)","sponsorCode": "(val)","sponsorHierarchyName": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Sponsor Forms [POST /research-common/api/v1/sponsor-forms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"sponsorFormId": "(val)","packageName": "(val)","packageNumber": "(val)","sponsorCode": "(val)","sponsorHierarchyName": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"sponsorFormId": "(val)","packageName": "(val)","packageNumber": "(val)","sponsorCode": "(val)","sponsorHierarchyName": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sponsor Forms [POST /research-common/api/v1/sponsor-forms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"sponsorFormId": "(val)","packageName": "(val)","packageNumber": "(val)","sponsorCode": "(val)","sponsorHierarchyName": "(val)","_primaryKey": "(val)"},
              {"sponsorFormId": "(val)","packageName": "(val)","packageNumber": "(val)","sponsorCode": "(val)","sponsorHierarchyName": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"sponsorFormId": "(val)","packageName": "(val)","packageNumber": "(val)","sponsorCode": "(val)","sponsorHierarchyName": "(val)","_primaryKey": "(val)"},
              {"sponsorFormId": "(val)","packageName": "(val)","packageNumber": "(val)","sponsorCode": "(val)","sponsorHierarchyName": "(val)","_primaryKey": "(val)"}
            ]
### Delete Sponsor Forms by Key [DELETE /research-common/api/v1/sponsor-forms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sponsor Forms [DELETE /research-common/api/v1/sponsor-forms/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sponsor Forms with Matching [DELETE /research-common/api/v1/sponsor-forms/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + sponsorFormId (optional) - Sponsor Form Id. Maximum length is 12.
    + packageName (optional) - Package Name. Maximum length is 200.
    + packageNumber (optional) - Package Number. Maximum length is 3.
    + sponsorCode (optional) - Sponsor Code. Maximum length is 6.
    + sponsorHierarchyName (optional) - Sponsor Hierarchy Group Name. Maximum length is 50.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
