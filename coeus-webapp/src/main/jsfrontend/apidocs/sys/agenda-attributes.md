## Agenda Attributes [/research-sys/api/v1/agenda-attributes/]

### Get Agenda Attributes by Key [GET /research-sys/api/v1/agenda-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","value": "(val)","_primaryKey": "(val)"}

### Get All Agenda Attributes [GET /research-sys/api/v1/agenda-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]

### Get All Agenda Attributes with Filtering [GET /research-sys/api/v1/agenda-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + value
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Agenda Attributes [GET /research-sys/api/v1/agenda-attributes/]
	 
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
		
### Get Blueprint API specification for Agenda Attributes [GET /research-sys/api/v1/agenda-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Agenda Attributes.md"
            transfer-encoding:chunked


### Update Agenda Attributes [PUT /research-sys/api/v1/agenda-attributes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Agenda Attributes [PUT /research-sys/api/v1/agenda-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Agenda Attributes [POST /research-sys/api/v1/agenda-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","value": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Agenda Attributes [POST /research-sys/api/v1/agenda-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Agenda Attributes by Key [DELETE /research-sys/api/v1/agenda-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Agenda Attributes [DELETE /research-sys/api/v1/agenda-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Agenda Attributes with Matching [DELETE /research-sys/api/v1/agenda-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + value


+ Response 204
