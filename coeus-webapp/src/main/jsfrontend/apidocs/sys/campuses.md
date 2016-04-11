## Campuses [/research-sys/api/v1/campuses/]

### Get Campuses by Key [GET /research-sys/api/v1/campuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","name": "(val)","shortName": "(val)","campusTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Campuses [GET /research-sys/api/v1/campuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","name": "(val)","shortName": "(val)","campusTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","name": "(val)","shortName": "(val)","campusTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Campuses with Filtering [GET /research-sys/api/v1/campuses/]
    
+ Parameters

        + code
            + name
            + shortName
            + campusTypeCode
            + active

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","name": "(val)","shortName": "(val)","campusTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","name": "(val)","shortName": "(val)","campusTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Campuses [GET /research-sys/api/v1/campuses/]
	                                          
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
    
            {"columns":["code","name","shortName","campusTypeCode","active"],"primaryKey":"code"}
		
### Get Blueprint API specification for Campuses [GET /research-sys/api/v1/campuses/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Campuses.md"
            transfer-encoding:chunked


### Update Campuses [PUT /research-sys/api/v1/campuses/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","name": "(val)","shortName": "(val)","campusTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Campuses [PUT /research-sys/api/v1/campuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","name": "(val)","shortName": "(val)","campusTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","name": "(val)","shortName": "(val)","campusTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Campuses [POST /research-sys/api/v1/campuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","name": "(val)","shortName": "(val)","campusTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","name": "(val)","shortName": "(val)","campusTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Campuses [POST /research-sys/api/v1/campuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","name": "(val)","shortName": "(val)","campusTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","name": "(val)","shortName": "(val)","campusTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"code": "(val)","name": "(val)","shortName": "(val)","campusTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","name": "(val)","shortName": "(val)","campusTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Campuses by Key [DELETE /research-sys/api/v1/campuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Campuses [DELETE /research-sys/api/v1/campuses/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Campuses with Matching [DELETE /research-sys/api/v1/campuses/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + code
            + name
            + shortName
            + campusTypeCode
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
