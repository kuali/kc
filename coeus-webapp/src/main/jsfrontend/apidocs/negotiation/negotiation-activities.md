## Negotiation Activities [/negotiation/api/v1/negotiation-activities/]

### Get Negotiation Activities by Key [GET /negotiation/api/v1/negotiation-activities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"activityId": "(val)","negotiationId": "(val)","activityTypeId": "(val)","locationId": "(val)","startDate": "(val)","endDate": "(val)","createDate": "(val)","followupDate": "(val)","lastModifiedUsername": "(val)","lastModifiedDate": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"}

### Get All Negotiation Activities [GET /negotiation/api/v1/negotiation-activities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"activityId": "(val)","negotiationId": "(val)","activityTypeId": "(val)","locationId": "(val)","startDate": "(val)","endDate": "(val)","createDate": "(val)","followupDate": "(val)","lastModifiedUsername": "(val)","lastModifiedDate": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"},
              {"activityId": "(val)","negotiationId": "(val)","activityTypeId": "(val)","locationId": "(val)","startDate": "(val)","endDate": "(val)","createDate": "(val)","followupDate": "(val)","lastModifiedUsername": "(val)","lastModifiedDate": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"}
            ]

### Get All Negotiation Activities with Filtering [GET /negotiation/api/v1/negotiation-activities/]
    
+ Parameters

    + activityId (optional) - negotiationActivityId. Maximum length is 22.
    + negotiationId (optional) - 
    + activityTypeId (optional) - Activity Type. Maximum length is 22.
    + locationId (optional) - Location. Maximum length is 22.
    + startDate (optional) - Activity Start Date. Maximum length is 21.
    + endDate (optional) - Activity End Date. Maximum length is 21.
    + createDate (optional) - Create Date. Maximum length is 21.
    + followupDate (optional) - Follow-up Date. Maximum length is 21.
    + lastModifiedUsername (optional) - Last Update By. Maximum length is 40.
    + lastModifiedDate (optional) - Last Update. Maximum length is 21.
    + description (optional) - Activity Description. Maximum length is 2000.
    + restricted (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"activityId": "(val)","negotiationId": "(val)","activityTypeId": "(val)","locationId": "(val)","startDate": "(val)","endDate": "(val)","createDate": "(val)","followupDate": "(val)","lastModifiedUsername": "(val)","lastModifiedDate": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"},
              {"activityId": "(val)","negotiationId": "(val)","activityTypeId": "(val)","locationId": "(val)","startDate": "(val)","endDate": "(val)","createDate": "(val)","followupDate": "(val)","lastModifiedUsername": "(val)","lastModifiedDate": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Negotiation Activities [GET /negotiation/api/v1/negotiation-activities/]
	                                          
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
    
            {"columns":["activityId","negotiationId","activityTypeId","locationId","startDate","endDate","createDate","followupDate","lastModifiedUsername","lastModifiedDate","description","restricted"],"primaryKey":"activityId"}
		
### Get Blueprint API specification for Negotiation Activities [GET /negotiation/api/v1/negotiation-activities/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Negotiation Activities.md"
            transfer-encoding:chunked
### Update Negotiation Activities [PUT /negotiation/api/v1/negotiation-activities/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"activityId": "(val)","negotiationId": "(val)","activityTypeId": "(val)","locationId": "(val)","startDate": "(val)","endDate": "(val)","createDate": "(val)","followupDate": "(val)","lastModifiedUsername": "(val)","lastModifiedDate": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Negotiation Activities [PUT /negotiation/api/v1/negotiation-activities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"activityId": "(val)","negotiationId": "(val)","activityTypeId": "(val)","locationId": "(val)","startDate": "(val)","endDate": "(val)","createDate": "(val)","followupDate": "(val)","lastModifiedUsername": "(val)","lastModifiedDate": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"},
              {"activityId": "(val)","negotiationId": "(val)","activityTypeId": "(val)","locationId": "(val)","startDate": "(val)","endDate": "(val)","createDate": "(val)","followupDate": "(val)","lastModifiedUsername": "(val)","lastModifiedDate": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Negotiation Activities [POST /negotiation/api/v1/negotiation-activities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"activityId": "(val)","negotiationId": "(val)","activityTypeId": "(val)","locationId": "(val)","startDate": "(val)","endDate": "(val)","createDate": "(val)","followupDate": "(val)","lastModifiedUsername": "(val)","lastModifiedDate": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"activityId": "(val)","negotiationId": "(val)","activityTypeId": "(val)","locationId": "(val)","startDate": "(val)","endDate": "(val)","createDate": "(val)","followupDate": "(val)","lastModifiedUsername": "(val)","lastModifiedDate": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Negotiation Activities [POST /negotiation/api/v1/negotiation-activities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"activityId": "(val)","negotiationId": "(val)","activityTypeId": "(val)","locationId": "(val)","startDate": "(val)","endDate": "(val)","createDate": "(val)","followupDate": "(val)","lastModifiedUsername": "(val)","lastModifiedDate": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"},
              {"activityId": "(val)","negotiationId": "(val)","activityTypeId": "(val)","locationId": "(val)","startDate": "(val)","endDate": "(val)","createDate": "(val)","followupDate": "(val)","lastModifiedUsername": "(val)","lastModifiedDate": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"activityId": "(val)","negotiationId": "(val)","activityTypeId": "(val)","locationId": "(val)","startDate": "(val)","endDate": "(val)","createDate": "(val)","followupDate": "(val)","lastModifiedUsername": "(val)","lastModifiedDate": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"},
              {"activityId": "(val)","negotiationId": "(val)","activityTypeId": "(val)","locationId": "(val)","startDate": "(val)","endDate": "(val)","createDate": "(val)","followupDate": "(val)","lastModifiedUsername": "(val)","lastModifiedDate": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"}
            ]
### Delete Negotiation Activities by Key [DELETE /negotiation/api/v1/negotiation-activities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Negotiation Activities [DELETE /negotiation/api/v1/negotiation-activities/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Negotiation Activities with Matching [DELETE /negotiation/api/v1/negotiation-activities/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + activityId (optional) - negotiationActivityId. Maximum length is 22.
    + negotiationId (optional) - 
    + activityTypeId (optional) - Activity Type. Maximum length is 22.
    + locationId (optional) - Location. Maximum length is 22.
    + startDate (optional) - Activity Start Date. Maximum length is 21.
    + endDate (optional) - Activity End Date. Maximum length is 21.
    + createDate (optional) - Create Date. Maximum length is 21.
    + followupDate (optional) - Follow-up Date. Maximum length is 21.
    + lastModifiedUsername (optional) - Last Update By. Maximum length is 40.
    + lastModifiedDate (optional) - Last Update. Maximum length is 21.
    + description (optional) - Activity Description. Maximum length is 2000.
    + restricted (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
