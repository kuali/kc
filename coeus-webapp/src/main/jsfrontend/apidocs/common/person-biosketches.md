## Person Biosketches [/research-common/api/v1/person-biosketches/]

### Get Person Biosketches by Key [GET /research-common/api/v1/person-biosketches/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"}

### Get All Person Biosketches [GET /research-common/api/v1/person-biosketches/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"},
              {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"}
            ]

### Get All Person Biosketches with Filtering [GET /research-common/api/v1/person-biosketches/]
    
+ Parameters

    + personBiosketchId (optional) - Person Biosketch Id. Maximum length is 22.
    + personId (optional) - 
    + description (optional) - Description. Maximum length is 4000.
    + fileName (optional) - 
    + contentType (optional) - 
    + attachmentContent (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"},
              {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Person Biosketches [GET /research-common/api/v1/person-biosketches/]
	                                          
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
    
            {"columns":["personBiosketchId","personId","description","fileName","contentType","attachmentContent"],"primaryKey":"personBiosketchId"}
		
### Get Blueprint API specification for Person Biosketches [GET /research-common/api/v1/person-biosketches/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Person Biosketches.md"
            transfer-encoding:chunked
### Update Person Biosketches [PUT /research-common/api/v1/person-biosketches/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Person Biosketches [PUT /research-common/api/v1/person-biosketches/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"},
              {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Person Biosketches [POST /research-common/api/v1/person-biosketches/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Person Biosketches [POST /research-common/api/v1/person-biosketches/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"},
              {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"},
              {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"}
            ]
### Delete Person Biosketches by Key [DELETE /research-common/api/v1/person-biosketches/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Biosketches [DELETE /research-common/api/v1/person-biosketches/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Biosketches with Matching [DELETE /research-common/api/v1/person-biosketches/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + personBiosketchId (optional) - Person Biosketch Id. Maximum length is 22.
    + personId (optional) - 
    + description (optional) - Description. Maximum length is 4000.
    + fileName (optional) - 
    + contentType (optional) - 
    + attachmentContent (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
