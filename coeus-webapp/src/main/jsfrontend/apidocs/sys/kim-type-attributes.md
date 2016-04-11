## Kim Type Attributes [/research-sys/api/v1/kim-type-attributes/]

### Get Kim Type Attributes by Key [GET /research-sys/api/v1/kim-type-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","sortCode": "(val)","kimAttributeId": "(val)","kimTypeId": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Kim Type Attributes [GET /research-sys/api/v1/kim-type-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","sortCode": "(val)","kimAttributeId": "(val)","kimTypeId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","sortCode": "(val)","kimAttributeId": "(val)","kimTypeId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Kim Type Attributes with Filtering [GET /research-sys/api/v1/kim-type-attributes/]
    
+ Parameters

        + id
            + sortCode
            + kimAttributeId
            + kimTypeId
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
              {"id": "(val)","sortCode": "(val)","kimAttributeId": "(val)","kimTypeId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","sortCode": "(val)","kimAttributeId": "(val)","kimTypeId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Kim Type Attributes [GET /research-sys/api/v1/kim-type-attributes/]
	                                          
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
    
            {"columns":["id","sortCode","kimAttributeId","kimTypeId","active"],"primaryKey":"id"}
		
### Get Blueprint API specification for Kim Type Attributes [GET /research-sys/api/v1/kim-type-attributes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Kim Type Attributes.md"
            transfer-encoding:chunked


### Update Kim Type Attributes [PUT /research-sys/api/v1/kim-type-attributes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","sortCode": "(val)","kimAttributeId": "(val)","kimTypeId": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Kim Type Attributes [PUT /research-sys/api/v1/kim-type-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","sortCode": "(val)","kimAttributeId": "(val)","kimTypeId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","sortCode": "(val)","kimAttributeId": "(val)","kimTypeId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Kim Type Attributes [POST /research-sys/api/v1/kim-type-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","sortCode": "(val)","kimAttributeId": "(val)","kimTypeId": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","sortCode": "(val)","kimAttributeId": "(val)","kimTypeId": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Kim Type Attributes [POST /research-sys/api/v1/kim-type-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","sortCode": "(val)","kimAttributeId": "(val)","kimTypeId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","sortCode": "(val)","kimAttributeId": "(val)","kimTypeId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","sortCode": "(val)","kimAttributeId": "(val)","kimTypeId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","sortCode": "(val)","kimAttributeId": "(val)","kimTypeId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Kim Type Attributes by Key [DELETE /research-sys/api/v1/kim-type-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kim Type Attributes [DELETE /research-sys/api/v1/kim-type-attributes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kim Type Attributes with Matching [DELETE /research-sys/api/v1/kim-type-attributes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + sortCode
            + kimAttributeId
            + kimTypeId
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
