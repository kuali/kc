## Iacuc Protocol Affiliation Types [/research-sys/api/v1/iacuc-protocol-affiliation-types/]

### Get Iacuc Protocol Affiliation Types by Key [GET /research-sys/api/v1/iacuc-protocol-affiliation-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Affiliation Types [GET /research-sys/api/v1/iacuc-protocol-affiliation-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Affiliation Types with Filtering [GET /research-sys/api/v1/iacuc-protocol-affiliation-types/]
    
+ Parameters

        + affiliationTypeCode
            + description
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
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Affiliation Types [GET /research-sys/api/v1/iacuc-protocol-affiliation-types/]
	                                          
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
    
            {"columns":["affiliationTypeCode","description","active"],"primaryKey":"affiliationTypeCode"}
		
### Get Blueprint API specification for Iacuc Protocol Affiliation Types [GET /research-sys/api/v1/iacuc-protocol-affiliation-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Affiliation Types.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Affiliation Types [PUT /research-sys/api/v1/iacuc-protocol-affiliation-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Affiliation Types [PUT /research-sys/api/v1/iacuc-protocol-affiliation-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Protocol Affiliation Types [POST /research-sys/api/v1/iacuc-protocol-affiliation-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Affiliation Types [POST /research-sys/api/v1/iacuc-protocol-affiliation-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"affiliationTypeCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Protocol Affiliation Types by Key [DELETE /research-sys/api/v1/iacuc-protocol-affiliation-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Affiliation Types [DELETE /research-sys/api/v1/iacuc-protocol-affiliation-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Affiliation Types with Matching [DELETE /research-sys/api/v1/iacuc-protocol-affiliation-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + affiliationTypeCode
            + description
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
