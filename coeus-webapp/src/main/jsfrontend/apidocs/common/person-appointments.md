## Person Appointments [/research-sys/api/v1/person-appointments/]

### Get Person Appointments by Key [GET /research-sys/api/v1/person-appointments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"appointmentId": "(val)","personId": "(val)","unitNumber": "(val)","startDate": "(val)","endDate": "(val)","typeCode": "(val)","jobTitle": "(val)","preferedJobTitle": "(val)","jobCode": "(val)","salary": "(val)","_primaryKey": "(val)"}

### Get All Person Appointments [GET /research-sys/api/v1/person-appointments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"appointmentId": "(val)","personId": "(val)","unitNumber": "(val)","startDate": "(val)","endDate": "(val)","typeCode": "(val)","jobTitle": "(val)","preferedJobTitle": "(val)","jobCode": "(val)","salary": "(val)","_primaryKey": "(val)"},
              {"appointmentId": "(val)","personId": "(val)","unitNumber": "(val)","startDate": "(val)","endDate": "(val)","typeCode": "(val)","jobTitle": "(val)","preferedJobTitle": "(val)","jobCode": "(val)","salary": "(val)","_primaryKey": "(val)"}
            ]

### Get All Person Appointments with Filtering [GET /research-sys/api/v1/person-appointments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + appointmentId
            + personId
            + unitNumber
            + startDate
            + endDate
            + typeCode
            + jobTitle
            + preferedJobTitle
            + jobCode
            + salary
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"appointmentId": "(val)","personId": "(val)","unitNumber": "(val)","startDate": "(val)","endDate": "(val)","typeCode": "(val)","jobTitle": "(val)","preferedJobTitle": "(val)","jobCode": "(val)","salary": "(val)","_primaryKey": "(val)"},
              {"appointmentId": "(val)","personId": "(val)","unitNumber": "(val)","startDate": "(val)","endDate": "(val)","typeCode": "(val)","jobTitle": "(val)","preferedJobTitle": "(val)","jobCode": "(val)","salary": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Person Appointments [GET /research-sys/api/v1/person-appointments/]
	 
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
		
### Get Blueprint API specification for Person Appointments [GET /research-sys/api/v1/person-appointments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Person Appointments.md"
            transfer-encoding:chunked


### Update Person Appointments [PUT /research-sys/api/v1/person-appointments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"appointmentId": "(val)","personId": "(val)","unitNumber": "(val)","startDate": "(val)","endDate": "(val)","typeCode": "(val)","jobTitle": "(val)","preferedJobTitle": "(val)","jobCode": "(val)","salary": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Person Appointments [PUT /research-sys/api/v1/person-appointments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"appointmentId": "(val)","personId": "(val)","unitNumber": "(val)","startDate": "(val)","endDate": "(val)","typeCode": "(val)","jobTitle": "(val)","preferedJobTitle": "(val)","jobCode": "(val)","salary": "(val)","_primaryKey": "(val)"},
              {"appointmentId": "(val)","personId": "(val)","unitNumber": "(val)","startDate": "(val)","endDate": "(val)","typeCode": "(val)","jobTitle": "(val)","preferedJobTitle": "(val)","jobCode": "(val)","salary": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Person Appointments [POST /research-sys/api/v1/person-appointments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"appointmentId": "(val)","personId": "(val)","unitNumber": "(val)","startDate": "(val)","endDate": "(val)","typeCode": "(val)","jobTitle": "(val)","preferedJobTitle": "(val)","jobCode": "(val)","salary": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"appointmentId": "(val)","personId": "(val)","unitNumber": "(val)","startDate": "(val)","endDate": "(val)","typeCode": "(val)","jobTitle": "(val)","preferedJobTitle": "(val)","jobCode": "(val)","salary": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Person Appointments [POST /research-sys/api/v1/person-appointments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"appointmentId": "(val)","personId": "(val)","unitNumber": "(val)","startDate": "(val)","endDate": "(val)","typeCode": "(val)","jobTitle": "(val)","preferedJobTitle": "(val)","jobCode": "(val)","salary": "(val)","_primaryKey": "(val)"},
              {"appointmentId": "(val)","personId": "(val)","unitNumber": "(val)","startDate": "(val)","endDate": "(val)","typeCode": "(val)","jobTitle": "(val)","preferedJobTitle": "(val)","jobCode": "(val)","salary": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"appointmentId": "(val)","personId": "(val)","unitNumber": "(val)","startDate": "(val)","endDate": "(val)","typeCode": "(val)","jobTitle": "(val)","preferedJobTitle": "(val)","jobCode": "(val)","salary": "(val)","_primaryKey": "(val)"},
              {"appointmentId": "(val)","personId": "(val)","unitNumber": "(val)","startDate": "(val)","endDate": "(val)","typeCode": "(val)","jobTitle": "(val)","preferedJobTitle": "(val)","jobCode": "(val)","salary": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Person Appointments by Key [DELETE /research-sys/api/v1/person-appointments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Appointments [DELETE /research-sys/api/v1/person-appointments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Person Appointments with Matching [DELETE /research-sys/api/v1/person-appointments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + appointmentId
            + personId
            + unitNumber
            + startDate
            + endDate
            + typeCode
            + jobTitle
            + preferedJobTitle
            + jobCode
            + salary


+ Response 204
