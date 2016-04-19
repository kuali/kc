## Award Template Report Term Recipients [/award/api/v1/award-template-report-term-recipients/]

### Get Award Template Report Term Recipients by Key [GET /award/api/v1/award-template-report-term-recipients/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"templateReportTermRecipientId": "(val)","templateReportTermId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"}

### Get All Award Template Report Term Recipients [GET /award/api/v1/award-template-report-term-recipients/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"templateReportTermRecipientId": "(val)","templateReportTermId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"},
              {"templateReportTermRecipientId": "(val)","templateReportTermId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Template Report Term Recipients with Filtering [GET /award/api/v1/award-template-report-term-recipients/]
    
+ Parameters

    + templateReportTermRecipientId (optional) - Templ Rep Terms Recnt Id. Maximum length is 22.
    + templateReportTermId (optional) - Template Report Terms Id. Maximum length is 22.
    + contactTypeCode (optional) - Contact Type Code. Maximum length is 3.
    + rolodexId (optional) - Rolodex Id. Maximum length is 22.
    + numberOfCopies (optional) - Number Of Copies. Maximum length is 22.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"templateReportTermRecipientId": "(val)","templateReportTermId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"},
              {"templateReportTermRecipientId": "(val)","templateReportTermId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Template Report Term Recipients [GET /award/api/v1/award-template-report-term-recipients/]
	                                          
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
    
            {"columns":["templateReportTermRecipientId","templateReportTermId","contactTypeCode","rolodexId","numberOfCopies"],"primaryKey":"templateReportTermRecipientId"}
		
### Get Blueprint API specification for Award Template Report Term Recipients [GET /award/api/v1/award-template-report-term-recipients/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Template Report Term Recipients.md"
            transfer-encoding:chunked


### Update Award Template Report Term Recipients [PUT /award/api/v1/award-template-report-term-recipients/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"templateReportTermRecipientId": "(val)","templateReportTermId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Template Report Term Recipients [PUT /award/api/v1/award-template-report-term-recipients/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"templateReportTermRecipientId": "(val)","templateReportTermId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"},
              {"templateReportTermRecipientId": "(val)","templateReportTermId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Template Report Term Recipients [POST /award/api/v1/award-template-report-term-recipients/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"templateReportTermRecipientId": "(val)","templateReportTermId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"templateReportTermRecipientId": "(val)","templateReportTermId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Template Report Term Recipients [POST /award/api/v1/award-template-report-term-recipients/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"templateReportTermRecipientId": "(val)","templateReportTermId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"},
              {"templateReportTermRecipientId": "(val)","templateReportTermId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"templateReportTermRecipientId": "(val)","templateReportTermId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"},
              {"templateReportTermRecipientId": "(val)","templateReportTermId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Template Report Term Recipients by Key [DELETE /award/api/v1/award-template-report-term-recipients/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Template Report Term Recipients [DELETE /award/api/v1/award-template-report-term-recipients/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Template Report Term Recipients with Matching [DELETE /award/api/v1/award-template-report-term-recipients/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + templateReportTermRecipientId (optional) - Templ Rep Terms Recnt Id. Maximum length is 22.
    + templateReportTermId (optional) - Template Report Terms Id. Maximum length is 22.
    + contactTypeCode (optional) - Contact Type Code. Maximum length is 3.
    + rolodexId (optional) - Rolodex Id. Maximum length is 22.
    + numberOfCopies (optional) - Number Of Copies. Maximum length is 22.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
