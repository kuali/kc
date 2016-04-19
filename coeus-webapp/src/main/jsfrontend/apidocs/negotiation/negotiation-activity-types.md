## Negotiation Activity Types [/negotiation/api/v1/negotiation-activity-types/]

### Get Negotiation Activity Types by Key [GET /negotiation/api/v1/negotiation-activity-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Negotiation Activity Types [GET /negotiation/api/v1/negotiation-activity-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Negotiation Activity Types with Filtering [GET /negotiation/api/v1/negotiation-activity-types/]
    
+ Parameters

    + id (optional) - Id. Maximum length is 22.
    + code (optional) - Activity Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 30.
    + active (optional) - Active. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Negotiation Activity Types [GET /negotiation/api/v1/negotiation-activity-types/]
	                                          
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
    
            {"columns":["id","code","description","active"],"primaryKey":"id"}
		
### Get Blueprint API specification for Negotiation Activity Types [GET /negotiation/api/v1/negotiation-activity-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Negotiation Activity Types.md"
            transfer-encoding:chunked


### Update Negotiation Activity Types [PUT /negotiation/api/v1/negotiation-activity-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Negotiation Activity Types [PUT /negotiation/api/v1/negotiation-activity-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Negotiation Activity Types [POST /negotiation/api/v1/negotiation-activity-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Negotiation Activity Types [POST /negotiation/api/v1/negotiation-activity-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","code": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Negotiation Activity Types by Key [DELETE /negotiation/api/v1/negotiation-activity-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Negotiation Activity Types [DELETE /negotiation/api/v1/negotiation-activity-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Negotiation Activity Types with Matching [DELETE /negotiation/api/v1/negotiation-activity-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Id. Maximum length is 22.
    + code (optional) - Activity Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 30.
    + active (optional) - Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
