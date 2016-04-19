## Award Report Term Recipients [/award/api/v1/award-report-term-recipients/]

### Get Award Report Term Recipients by Key [GET /award/api/v1/award-report-term-recipients/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardReportTermRecipientId": "(val)","awardReportTermId": "(val)","contactId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"}

### Get All Award Report Term Recipients [GET /award/api/v1/award-report-term-recipients/]
	 
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

### Get All Award Report Term Recipients with Filtering [GET /award/api/v1/award-report-term-recipients/]
    
+ Parameters

    + awardReportTermRecipientId (optional) - Award Report Terms Recipient Id. Maximum length is 22.
    + awardReportTermId (optional) - Award Report Terms Id. Maximum length is 22.
    + contactId (optional) - Contact Type. Maximum length is 12.
    + contactTypeCode (optional) - Contact Type. Maximum length is 22.
    + rolodexId (optional) - Name/Organization. Maximum length is 22.
    + numberOfCopies (optional) - Number of Copies. Maximum length is 22.

            
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
			
### Get Schema for Award Report Term Recipients [GET /award/api/v1/award-report-term-recipients/]
	                                          
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
    
            {"columns":["awardReportTermRecipientId","awardReportTermId","contactId","contactTypeCode","rolodexId","numberOfCopies"],"primaryKey":"awardReportTermRecipientId"}
		
### Get Blueprint API specification for Award Report Term Recipients [GET /award/api/v1/award-report-term-recipients/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Report Term Recipients.md"
            transfer-encoding:chunked


### Update Award Report Term Recipients [PUT /award/api/v1/award-report-term-recipients/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardReportTermRecipientId": "(val)","awardReportTermId": "(val)","contactId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Report Term Recipients [PUT /award/api/v1/award-report-term-recipients/]

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

### Insert Award Report Term Recipients [POST /award/api/v1/award-report-term-recipients/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardReportTermRecipientId": "(val)","awardReportTermId": "(val)","contactId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardReportTermRecipientId": "(val)","awardReportTermId": "(val)","contactId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Report Term Recipients [POST /award/api/v1/award-report-term-recipients/]

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
            
### Delete Award Report Term Recipients by Key [DELETE /award/api/v1/award-report-term-recipients/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Report Term Recipients [DELETE /award/api/v1/award-report-term-recipients/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Report Term Recipients with Matching [DELETE /award/api/v1/award-report-term-recipients/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + awardReportTermRecipientId (optional) - Award Report Terms Recipient Id. Maximum length is 22.
    + awardReportTermId (optional) - Award Report Terms Id. Maximum length is 22.
    + contactId (optional) - Contact Type. Maximum length is 12.
    + contactTypeCode (optional) - Contact Type. Maximum length is 22.
    + rolodexId (optional) - Name/Organization. Maximum length is 22.
    + numberOfCopies (optional) - Number of Copies. Maximum length is 22.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
