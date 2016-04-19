## Person Document Emails [/research-sys/api/v1/person-document-emails/]

### Get Person Document Emails by Key [GET /research-sys/api/v1/person-document-emails/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"entityEmailId": "(val)","entityTypeCode": "(val)","emailTypeCode": "(val)","emailAddress": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Person Document Emails [GET /research-sys/api/v1/person-document-emails/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"entityEmailId": "(val)","entityTypeCode": "(val)","emailTypeCode": "(val)","emailAddress": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityEmailId": "(val)","entityTypeCode": "(val)","emailTypeCode": "(val)","emailAddress": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Person Document Emails with Filtering [GET /research-sys/api/v1/person-document-emails/]
    
+ Parameters

    + entityEmailId (optional) - Entity Email Id.
    + entityTypeCode (optional) - Entity Type Code.
    + emailTypeCode (optional) - Type. Maximum length is 10.
    + emailAddress (optional) - The email address of the university user. Maximum length is 200.
    + dflt (optional) - Default. Maximum length is 1.
    + edit (optional) - Edit.
    + documentNumber (optional) - Document Number.
    + active (optional) - Active. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"entityEmailId": "(val)","entityTypeCode": "(val)","emailTypeCode": "(val)","emailAddress": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityEmailId": "(val)","entityTypeCode": "(val)","emailTypeCode": "(val)","emailAddress": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Person Document Emails [GET /research-sys/api/v1/person-document-emails/]
	                                          
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
    
            {"columns":["entityEmailId","entityTypeCode","emailTypeCode","emailAddress","dflt","edit","documentNumber","active"],"primaryKey":"entityEmailId"}
		
### Get Blueprint API specification for Person Document Emails [GET /research-sys/api/v1/person-document-emails/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Person Document Emails.md"
            transfer-encoding:chunked


### Update Person Document Emails [PUT /research-sys/api/v1/person-document-emails/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"entityEmailId": "(val)","entityTypeCode": "(val)","emailTypeCode": "(val)","emailAddress": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Person Document Emails [PUT /research-sys/api/v1/person-document-emails/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"entityEmailId": "(val)","entityTypeCode": "(val)","emailTypeCode": "(val)","emailAddress": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityEmailId": "(val)","entityTypeCode": "(val)","emailTypeCode": "(val)","emailAddress": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Person Document Emails [POST /research-sys/api/v1/person-document-emails/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"entityEmailId": "(val)","entityTypeCode": "(val)","emailTypeCode": "(val)","emailAddress": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"entityEmailId": "(val)","entityTypeCode": "(val)","emailTypeCode": "(val)","emailAddress": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Person Document Emails [POST /research-sys/api/v1/person-document-emails/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"entityEmailId": "(val)","entityTypeCode": "(val)","emailTypeCode": "(val)","emailAddress": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityEmailId": "(val)","entityTypeCode": "(val)","emailTypeCode": "(val)","emailAddress": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"entityEmailId": "(val)","entityTypeCode": "(val)","emailTypeCode": "(val)","emailAddress": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityEmailId": "(val)","entityTypeCode": "(val)","emailTypeCode": "(val)","emailAddress": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Person Document Emails by Key [DELETE /research-sys/api/v1/person-document-emails/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Document Emails [DELETE /research-sys/api/v1/person-document-emails/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Document Emails with Matching [DELETE /research-sys/api/v1/person-document-emails/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + entityEmailId (optional) - Entity Email Id.
    + entityTypeCode (optional) - Entity Type Code.
    + emailTypeCode (optional) - Type. Maximum length is 10.
    + emailAddress (optional) - The email address of the university user. Maximum length is 200.
    + dflt (optional) - Default. Maximum length is 1.
    + edit (optional) - Edit.
    + documentNumber (optional) - Document Number.
    + active (optional) - Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
