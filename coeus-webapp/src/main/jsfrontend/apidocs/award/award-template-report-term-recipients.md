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
    
            {"templateReportTermRecipientId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","awardTemplateReportTerm.templateReportTermId": "(val)","_primaryKey": "(val)"}

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
              {"templateReportTermRecipientId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","awardTemplateReportTerm.templateReportTermId": "(val)","_primaryKey": "(val)"},
              {"templateReportTermRecipientId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","awardTemplateReportTerm.templateReportTermId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Template Report Term Recipients with Filtering [GET /award/api/v1/award-template-report-term-recipients/]
    
+ Parameters

    + templateReportTermRecipientId (optional) - Templ Rep Terms Recnt Id. Maximum length is 22.
    + contactTypeCode (optional) - Contact Type Code. Maximum length is 3.
    + rolodexId (optional) - Rolodex Id. Maximum length is 22.
    + numberOfCopies (optional) - Number Of Copies. Maximum length is 22.
    + awardTemplateReportTerm.templateReportTermId (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"templateReportTermRecipientId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","awardTemplateReportTerm.templateReportTermId": "(val)","_primaryKey": "(val)"},
              {"templateReportTermRecipientId": "(val)","contactTypeCode": "(val)","rolodexId": "(val)","numberOfCopies": "(val)","awardTemplateReportTerm.templateReportTermId": "(val)","_primaryKey": "(val)"}
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
    
            {"columns":["templateReportTermRecipientId","contactTypeCode","rolodexId","numberOfCopies","awardTemplateReportTerm.templateReportTermId"],"primaryKey":"templateReportTermRecipientId"}
		
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
