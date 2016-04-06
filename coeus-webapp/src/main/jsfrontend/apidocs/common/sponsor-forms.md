## Sponsor Forms [/research-sys/api/v1/sponsor-forms/]

### Get Sponsor Forms by Key [GET /research-sys/api/v1/sponsor-forms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"sponsorFormId": "(val)","packageName": "(val)","packageNumber": "(val)","sponsorCode": "(val)","sponsorHierarchyName": "(val)","_primaryKey": "(val)"}

### Get All Sponsor Forms [GET /research-sys/api/v1/sponsor-forms/]
	 
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

### Get All Sponsor Forms with Filtering [GET /research-sys/api/v1/sponsor-forms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + sponsorFormId
            + packageName
            + packageNumber
            + sponsorCode
            + sponsorHierarchyName
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"sponsorFormId": "(val)","packageName": "(val)","packageNumber": "(val)","sponsorCode": "(val)","sponsorHierarchyName": "(val)","_primaryKey": "(val)"},
              {"sponsorFormId": "(val)","packageName": "(val)","packageNumber": "(val)","sponsorCode": "(val)","sponsorHierarchyName": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sponsor Forms [GET /research-sys/api/v1/sponsor-forms/]
	 
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
		
### Get Blueprint API specification for Sponsor Forms [GET /research-sys/api/v1/sponsor-forms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sponsor Forms.md"
            transfer-encoding:chunked


### Update Sponsor Forms [PUT /research-sys/api/v1/sponsor-forms/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"sponsorFormId": "(val)","packageName": "(val)","packageNumber": "(val)","sponsorCode": "(val)","sponsorHierarchyName": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sponsor Forms [PUT /research-sys/api/v1/sponsor-forms/]

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

### Insert Sponsor Forms [POST /research-sys/api/v1/sponsor-forms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"sponsorFormId": "(val)","packageName": "(val)","packageNumber": "(val)","sponsorCode": "(val)","sponsorHierarchyName": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"sponsorFormId": "(val)","packageName": "(val)","packageNumber": "(val)","sponsorCode": "(val)","sponsorHierarchyName": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sponsor Forms [POST /research-sys/api/v1/sponsor-forms/]

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
            
### Delete Sponsor Forms by Key [DELETE /research-sys/api/v1/sponsor-forms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sponsor Forms [DELETE /research-sys/api/v1/sponsor-forms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Sponsor Forms with Matching [DELETE /research-sys/api/v1/sponsor-forms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + sponsorFormId
            + packageName
            + packageNumber
            + sponsorCode
            + sponsorHierarchyName


+ Response 204
