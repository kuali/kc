## Person Appointments [/research-common/api/v1/person-appointments/]

### Get Person Appointments by Key [GET /research-common/api/v1/person-appointments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"appointmentId": "(val)","personId": "(val)","unitNumber": "(val)","startDate": "(val)","endDate": "(val)","typeCode": "(val)","jobTitle": "(val)","preferedJobTitle": "(val)","jobCode": "(val)","salary": "(val)","_primaryKey": "(val)"}

### Get All Person Appointments [GET /research-common/api/v1/person-appointments/]
	 
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

### Get All Person Appointments with Filtering [GET /research-common/api/v1/person-appointments/]
    
+ Parameters

    + appointmentId (optional) - Person Appointment Primary Key. Maximum length is 40.
    + personId (optional) - KcPersonExtendedAttributes Id. Maximum length is 40.
    + unitNumber (optional) - Unit. Maximum length is 8.
    + startDate (optional) - Start Date. Maximum length is 10.
    + endDate (optional) - End Date. Maximum length is 10.
    + typeCode (optional) - Appointment Type. Maximum length is 3.
    + jobTitle (optional) - Job Title. Maximum length is 50.
    + preferedJobTitle (optional) - Prefered Job Title. Maximum length is 51.
    + jobCode (optional) - Job Code. Maximum length is 6.
    + salary (optional) - Salary. Maximum length is 15.

            
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
			
### Get Schema for Person Appointments [GET /research-common/api/v1/person-appointments/]
	                                          
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
    
            {"columns":["appointmentId","personId","unitNumber","startDate","endDate","typeCode","jobTitle","preferedJobTitle","jobCode","salary"],"primaryKey":"appointmentId"}
		
### Get Blueprint API specification for Person Appointments [GET /research-common/api/v1/person-appointments/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Person Appointments.md"
            transfer-encoding:chunked


### Update Person Appointments [PUT /research-common/api/v1/person-appointments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"appointmentId": "(val)","personId": "(val)","unitNumber": "(val)","startDate": "(val)","endDate": "(val)","typeCode": "(val)","jobTitle": "(val)","preferedJobTitle": "(val)","jobCode": "(val)","salary": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Person Appointments [PUT /research-common/api/v1/person-appointments/]

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

### Insert Person Appointments [POST /research-common/api/v1/person-appointments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"appointmentId": "(val)","personId": "(val)","unitNumber": "(val)","startDate": "(val)","endDate": "(val)","typeCode": "(val)","jobTitle": "(val)","preferedJobTitle": "(val)","jobCode": "(val)","salary": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"appointmentId": "(val)","personId": "(val)","unitNumber": "(val)","startDate": "(val)","endDate": "(val)","typeCode": "(val)","jobTitle": "(val)","preferedJobTitle": "(val)","jobCode": "(val)","salary": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Person Appointments [POST /research-common/api/v1/person-appointments/]

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
            
### Delete Person Appointments by Key [DELETE /research-common/api/v1/person-appointments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Appointments [DELETE /research-common/api/v1/person-appointments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Appointments with Matching [DELETE /research-common/api/v1/person-appointments/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + appointmentId (optional) - Person Appointment Primary Key. Maximum length is 40.
    + personId (optional) - KcPersonExtendedAttributes Id. Maximum length is 40.
    + unitNumber (optional) - Unit. Maximum length is 8.
    + startDate (optional) - Start Date. Maximum length is 10.
    + endDate (optional) - End Date. Maximum length is 10.
    + typeCode (optional) - Appointment Type. Maximum length is 3.
    + jobTitle (optional) - Job Title. Maximum length is 50.
    + preferedJobTitle (optional) - Prefered Job Title. Maximum length is 51.
    + jobCode (optional) - Job Code. Maximum length is 6.
    + salary (optional) - Salary. Maximum length is 15.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
