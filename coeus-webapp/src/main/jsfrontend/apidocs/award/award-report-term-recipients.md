## Award Report Term Recipients [/research-sys/api/v1/award-report-term-recipients/]

### Get Award Report Term Recipients by Key [GET /research-sys/api/v1/award-report-term-recipients/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardReportTermRecipientId": "(val)","awardReportTermId": "(val)","contactId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"}

### Get All Award Report Term Recipients [GET /research-sys/api/v1/award-report-term-recipients/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardReportTermRecipientId": "(val)","awardReportTermId": "(val)","contactId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"},
              {"awardReportTermRecipientId": "(val)","awardReportTermId": "(val)","contactId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Report Term Recipients with Filtering [GET /research-sys/api/v1/award-report-term-recipients/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + awardReportTermRecipientId
            + awardReportTermId
            + contactId
            + contactTypeCode
            + rolodexId
            + numberOfCopies
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardReportTermRecipientId": "(val)","awardReportTermId": "(val)","contactId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"},
              {"awardReportTermRecipientId": "(val)","awardReportTermId": "(val)","contactId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Report Term Recipients [GET /research-sys/api/v1/award-report-term-recipients/]
	 
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
		
### Get Blueprint API specification for Award Report Term Recipients [GET /research-sys/api/v1/award-report-term-recipients/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Report Term Recipients.md"
            transfer-encoding:chunked


### Update Award Report Term Recipients [PUT /research-sys/api/v1/award-report-term-recipients/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardReportTermRecipientId": "(val)","awardReportTermId": "(val)","contactId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Report Term Recipients [PUT /research-sys/api/v1/award-report-term-recipients/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardReportTermRecipientId": "(val)","awardReportTermId": "(val)","contactId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"},
              {"awardReportTermRecipientId": "(val)","awardReportTermId": "(val)","contactId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Report Term Recipients [POST /research-sys/api/v1/award-report-term-recipients/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardReportTermRecipientId": "(val)","awardReportTermId": "(val)","contactId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardReportTermRecipientId": "(val)","awardReportTermId": "(val)","contactId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Report Term Recipients [POST /research-sys/api/v1/award-report-term-recipients/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardReportTermRecipientId": "(val)","awardReportTermId": "(val)","contactId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"},
              {"awardReportTermRecipientId": "(val)","awardReportTermId": "(val)","contactId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardReportTermRecipientId": "(val)","awardReportTermId": "(val)","contactId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"},
              {"awardReportTermRecipientId": "(val)","awardReportTermId": "(val)","contactId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Report Term Recipients by Key [DELETE /research-sys/api/v1/award-report-term-recipients/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Report Term Recipients [DELETE /research-sys/api/v1/award-report-term-recipients/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Award Report Term Recipients with Matching [DELETE /research-sys/api/v1/award-report-term-recipients/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + awardReportTermRecipientId
            + awardReportTermId
            + contactId
            + contactTypeCode
            + rolodexId
            + numberOfCopies


+ Response 204
