## Proposal Person Units [/research-sys/api/v1/proposal-person-units/]

### Get Proposal Person Units by Key [GET /research-sys/api/v1/proposal-person-units/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}

### Get All Proposal Person Units [GET /research-sys/api/v1/proposal-person-units/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"},
              {"unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Person Units with Filtering [GET /research-sys/api/v1/proposal-person-units/]
    
+ Parameters

        + unitNumber
            + leadUnit

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"},
              {"unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Person Units [GET /research-sys/api/v1/proposal-person-units/]
	                                          
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
    
            {"columns":["unitNumber","leadUnit"],"primaryKey":"proposalPerson:unitNumber"}
		
### Get Blueprint API specification for Proposal Person Units [GET /research-sys/api/v1/proposal-person-units/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Person Units.md"
            transfer-encoding:chunked


### Update Proposal Person Units [PUT /research-sys/api/v1/proposal-person-units/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Person Units [PUT /research-sys/api/v1/proposal-person-units/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"},
              {"unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Proposal Person Units [POST /research-sys/api/v1/proposal-person-units/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Person Units [POST /research-sys/api/v1/proposal-person-units/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"},
              {"unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"},
              {"unitNumber": "(val)","leadUnit": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Proposal Person Units by Key [DELETE /research-sys/api/v1/proposal-person-units/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Person Units [DELETE /research-sys/api/v1/proposal-person-units/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Person Units with Matching [DELETE /research-sys/api/v1/proposal-person-units/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + unitNumber
            + leadUnit

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
