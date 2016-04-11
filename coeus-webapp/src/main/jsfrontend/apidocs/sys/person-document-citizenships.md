## Person Document Citizenships [/research-sys/api/v1/person-document-citizenships/]

### Get Person Document Citizenships by Key [GET /research-sys/api/v1/person-document-citizenships/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"entityCitizenshipId": "(val)","entityId": "(val)","countryCode": "(val)","citizenshipStatusCode": "(val)","startDate": "(val)","endDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Person Document Citizenships [GET /research-sys/api/v1/person-document-citizenships/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"entityCitizenshipId": "(val)","entityId": "(val)","countryCode": "(val)","citizenshipStatusCode": "(val)","startDate": "(val)","endDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityCitizenshipId": "(val)","entityId": "(val)","countryCode": "(val)","citizenshipStatusCode": "(val)","startDate": "(val)","endDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Person Document Citizenships with Filtering [GET /research-sys/api/v1/person-document-citizenships/]
    
+ Parameters

        + entityCitizenshipId
            + entityId
            + countryCode
            + citizenshipStatusCode
            + startDate
            + endDate
            + edit
            + documentNumber
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
              {"entityCitizenshipId": "(val)","entityId": "(val)","countryCode": "(val)","citizenshipStatusCode": "(val)","startDate": "(val)","endDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityCitizenshipId": "(val)","entityId": "(val)","countryCode": "(val)","citizenshipStatusCode": "(val)","startDate": "(val)","endDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Person Document Citizenships [GET /research-sys/api/v1/person-document-citizenships/]
	                                          
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
    
            {"columns":["entityCitizenshipId","entityId","countryCode","citizenshipStatusCode","startDate","endDate","edit","documentNumber","active"],"primaryKey":"entityCitizenshipId"}
		
### Get Blueprint API specification for Person Document Citizenships [GET /research-sys/api/v1/person-document-citizenships/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Person Document Citizenships.md"
            transfer-encoding:chunked


### Update Person Document Citizenships [PUT /research-sys/api/v1/person-document-citizenships/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"entityCitizenshipId": "(val)","entityId": "(val)","countryCode": "(val)","citizenshipStatusCode": "(val)","startDate": "(val)","endDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Person Document Citizenships [PUT /research-sys/api/v1/person-document-citizenships/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"entityCitizenshipId": "(val)","entityId": "(val)","countryCode": "(val)","citizenshipStatusCode": "(val)","startDate": "(val)","endDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityCitizenshipId": "(val)","entityId": "(val)","countryCode": "(val)","citizenshipStatusCode": "(val)","startDate": "(val)","endDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Person Document Citizenships [POST /research-sys/api/v1/person-document-citizenships/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"entityCitizenshipId": "(val)","entityId": "(val)","countryCode": "(val)","citizenshipStatusCode": "(val)","startDate": "(val)","endDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"entityCitizenshipId": "(val)","entityId": "(val)","countryCode": "(val)","citizenshipStatusCode": "(val)","startDate": "(val)","endDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Person Document Citizenships [POST /research-sys/api/v1/person-document-citizenships/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"entityCitizenshipId": "(val)","entityId": "(val)","countryCode": "(val)","citizenshipStatusCode": "(val)","startDate": "(val)","endDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityCitizenshipId": "(val)","entityId": "(val)","countryCode": "(val)","citizenshipStatusCode": "(val)","startDate": "(val)","endDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"entityCitizenshipId": "(val)","entityId": "(val)","countryCode": "(val)","citizenshipStatusCode": "(val)","startDate": "(val)","endDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityCitizenshipId": "(val)","entityId": "(val)","countryCode": "(val)","citizenshipStatusCode": "(val)","startDate": "(val)","endDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Person Document Citizenships by Key [DELETE /research-sys/api/v1/person-document-citizenships/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Document Citizenships [DELETE /research-sys/api/v1/person-document-citizenships/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Document Citizenships with Matching [DELETE /research-sys/api/v1/person-document-citizenships/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + entityCitizenshipId
            + entityId
            + countryCode
            + citizenshipStatusCode
            + startDate
            + endDate
            + edit
            + documentNumber
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
