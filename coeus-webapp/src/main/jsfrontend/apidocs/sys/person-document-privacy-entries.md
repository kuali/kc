## Person Document Privacy Entries [/research-sys/api/v1/person-document-privacy-entries/]

### Get Person Document Privacy Entries by Key [GET /research-sys/api/v1/person-document-privacy-entries/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}

### Get All Person Document Privacy Entries [GET /research-sys/api/v1/person-document-privacy-entries/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Person Document Privacy Entries with Filtering [GET /research-sys/api/v1/person-document-privacy-entries/]
    
+ Parameters

    + suppressName (optional) - Suppress Name. Maximum length is 1.
    + suppressEmail (optional) - Suppress Email. Maximum length is 1.
    + suppressAddress (optional) - Suppress Address. Maximum length is 1.
    + suppressPhone (optional) - Suppress Phone. Maximum length is 1.
    + suppressPersonal (optional) - Suppress Personal. Maximum length is 1.
    + edit (optional) - Edit.
    + documentNumber (optional) - Document Number.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Person Document Privacy Entries [GET /research-sys/api/v1/person-document-privacy-entries/]
	                                          
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
    
            {"columns":["suppressName","suppressEmail","suppressAddress","suppressPhone","suppressPersonal","edit","documentNumber"],"primaryKey":"documentNumber"}
		
### Get Blueprint API specification for Person Document Privacy Entries [GET /research-sys/api/v1/person-document-privacy-entries/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Person Document Privacy Entries.md"
            transfer-encoding:chunked
### Update Person Document Privacy Entries [PUT /research-sys/api/v1/person-document-privacy-entries/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Person Document Privacy Entries [PUT /research-sys/api/v1/person-document-privacy-entries/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Person Document Privacy Entries [POST /research-sys/api/v1/person-document-privacy-entries/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Person Document Privacy Entries [POST /research-sys/api/v1/person-document-privacy-entries/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"suppressName": "(val)","suppressEmail": "(val)","suppressAddress": "(val)","suppressPhone": "(val)","suppressPersonal": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
### Delete Person Document Privacy Entries by Key [DELETE /research-sys/api/v1/person-document-privacy-entries/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Document Privacy Entries [DELETE /research-sys/api/v1/person-document-privacy-entries/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Document Privacy Entries with Matching [DELETE /research-sys/api/v1/person-document-privacy-entries/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + suppressName (optional) - Suppress Name. Maximum length is 1.
    + suppressEmail (optional) - Suppress Email. Maximum length is 1.
    + suppressAddress (optional) - Suppress Address. Maximum length is 1.
    + suppressPhone (optional) - Suppress Phone. Maximum length is 1.
    + suppressPersonal (optional) - Suppress Personal. Maximum length is 1.
    + edit (optional) - Edit.
    + documentNumber (optional) - Document Number.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
