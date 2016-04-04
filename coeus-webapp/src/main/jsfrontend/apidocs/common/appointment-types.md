## Appointment Types [/research-sys/api/v1/appointment-types/]

### Get Appointment Types by Key [GET /research-sys/api/v1/appointment-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"appointmentTypeCode": "(val)","duration": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Appointment Types [GET /research-sys/api/v1/appointment-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"appointmentTypeCode": "(val)","duration": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"appointmentTypeCode": "(val)","duration": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Appointment Types with Filtering [GET /research-sys/api/v1/appointment-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + appointmentTypeCode
            + duration
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"appointmentTypeCode": "(val)","duration": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"appointmentTypeCode": "(val)","duration": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Appointment Types [GET /research-sys/api/v1/appointment-types/]
	 
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
		
### Get Blueprint API specification for Appointment Types [GET /research-sys/api/v1/appointment-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Appointment Types.md"
            transfer-encoding:chunked


### Update Appointment Types [PUT /research-sys/api/v1/appointment-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"appointmentTypeCode": "(val)","duration": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Appointment Types [PUT /research-sys/api/v1/appointment-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"appointmentTypeCode": "(val)","duration": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"appointmentTypeCode": "(val)","duration": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Appointment Types [POST /research-sys/api/v1/appointment-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"appointmentTypeCode": "(val)","duration": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"appointmentTypeCode": "(val)","duration": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Appointment Types [POST /research-sys/api/v1/appointment-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"appointmentTypeCode": "(val)","duration": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"appointmentTypeCode": "(val)","duration": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"appointmentTypeCode": "(val)","duration": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"appointmentTypeCode": "(val)","duration": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Appointment Types by Key [DELETE /research-sys/api/v1/appointment-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Appointment Types [DELETE /research-sys/api/v1/appointment-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Appointment Types with Matching [DELETE /research-sys/api/v1/appointment-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + appointmentTypeCode
            + duration
            + description


+ Response 204
