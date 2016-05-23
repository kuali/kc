## Committee Decision Motion Types [/research-common/api/v1/committee-decision-motion-types/]

### Get Committee Decision Motion Types by Key [GET /research-common/api/v1/committee-decision-motion-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"motionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Committee Decision Motion Types [GET /research-common/api/v1/committee-decision-motion-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"motionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"motionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Committee Decision Motion Types with Filtering [GET /research-common/api/v1/committee-decision-motion-types/]
    
+ Parameters

    + motionTypeCode (optional) - Motion Type. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"motionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"motionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Committee Decision Motion Types [GET /research-common/api/v1/committee-decision-motion-types/]
	                                          
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
    
            {"columns":["motionTypeCode","description"],"primaryKey":"motionTypeCode"}
		
### Get Blueprint API specification for Committee Decision Motion Types [GET /research-common/api/v1/committee-decision-motion-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Committee Decision Motion Types.md"
            transfer-encoding:chunked
### Update Committee Decision Motion Types [PUT /research-common/api/v1/committee-decision-motion-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"motionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Committee Decision Motion Types [PUT /research-common/api/v1/committee-decision-motion-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"motionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"motionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Committee Decision Motion Types [POST /research-common/api/v1/committee-decision-motion-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"motionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"motionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Committee Decision Motion Types [POST /research-common/api/v1/committee-decision-motion-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"motionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"motionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"motionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"motionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Committee Decision Motion Types by Key [DELETE /research-common/api/v1/committee-decision-motion-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Decision Motion Types [DELETE /research-common/api/v1/committee-decision-motion-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Decision Motion Types with Matching [DELETE /research-common/api/v1/committee-decision-motion-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + motionTypeCode (optional) - Motion Type. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
