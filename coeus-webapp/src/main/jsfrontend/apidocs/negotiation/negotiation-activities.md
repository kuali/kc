## Negotiation Activities [/research-sys/api/v1/negotiation-activities/]

### Get Negotiation Activities by Key [GET /research-sys/api/v1/negotiation-activities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"activityId": "(val)","negotiationId": "(val)","activityTypeId": "(val)","locationId": "(val)","startDate": "(val)","endDate": "(val)","createDate": "(val)","followupDate": "(val)","lastModifiedUsername": "(val)","lastModifiedDate": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"}

### Get All Negotiation Activities [GET /research-sys/api/v1/negotiation-activities/]
	 
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

### Get All Negotiation Activities with Filtering [GET /research-sys/api/v1/negotiation-activities/]
    
+ Parameters

        + activityId
            + negotiationId
            + activityTypeId
            + locationId
            + startDate
            + endDate
            + createDate
            + followupDate
            + lastModifiedUsername
            + lastModifiedDate
            + description
            + restricted

            
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
			
### Get Schema for Negotiation Activities [GET /research-sys/api/v1/negotiation-activities/]
	                                          
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
		
### Get Blueprint API specification for Negotiation Activities [GET /research-sys/api/v1/negotiation-activities/]
	 
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


### Update Negotiation Activities [PUT /research-sys/api/v1/negotiation-activities/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"activityId": "(val)","negotiationId": "(val)","activityTypeId": "(val)","locationId": "(val)","startDate": "(val)","endDate": "(val)","createDate": "(val)","followupDate": "(val)","lastModifiedUsername": "(val)","lastModifiedDate": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Negotiation Activities [PUT /research-sys/api/v1/negotiation-activities/]

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

### Insert Negotiation Activities [POST /research-sys/api/v1/negotiation-activities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"activityId": "(val)","negotiationId": "(val)","activityTypeId": "(val)","locationId": "(val)","startDate": "(val)","endDate": "(val)","createDate": "(val)","followupDate": "(val)","lastModifiedUsername": "(val)","lastModifiedDate": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"activityId": "(val)","negotiationId": "(val)","activityTypeId": "(val)","locationId": "(val)","startDate": "(val)","endDate": "(val)","createDate": "(val)","followupDate": "(val)","lastModifiedUsername": "(val)","lastModifiedDate": "(val)","description": "(val)","restricted": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Negotiation Activities [POST /research-sys/api/v1/negotiation-activities/]

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
            
### Delete Negotiation Activities by Key [DELETE /research-sys/api/v1/negotiation-activities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Negotiation Activities [DELETE /research-sys/api/v1/negotiation-activities/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Negotiation Activities with Matching [DELETE /research-sys/api/v1/negotiation-activities/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + activityId
            + negotiationId
            + activityTypeId
            + locationId
            + startDate
            + endDate
            + createDate
            + followupDate
            + lastModifiedUsername
            + lastModifiedDate
            + description
            + restricted

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
