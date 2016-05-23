## Person Document Phones [/research-sys/api/v1/person-document-phones/]

### Get Person Document Phones by Key [GET /research-sys/api/v1/person-document-phones/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"entityPhoneId": "(val)","entityTypeCode": "(val)","phoneTypeCode": "(val)","phoneNumber": "(val)","extensionNumber": "(val)","countryCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Person Document Phones [GET /research-sys/api/v1/person-document-phones/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"entityPhoneId": "(val)","entityTypeCode": "(val)","phoneTypeCode": "(val)","phoneNumber": "(val)","extensionNumber": "(val)","countryCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityPhoneId": "(val)","entityTypeCode": "(val)","phoneTypeCode": "(val)","phoneNumber": "(val)","extensionNumber": "(val)","countryCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Person Document Phones with Filtering [GET /research-sys/api/v1/person-document-phones/]
    
+ Parameters

    + entityPhoneId (optional) - Entity Phone Id.
    + entityTypeCode (optional) - Entity Type Code.
    + phoneTypeCode (optional) - Phone Type. Maximum length is 40.
    + phoneNumber (optional) - Phone Number. Maximum length is 20.
    + extensionNumber (optional) - Extension. Maximum length is 8.
    + countryCode (optional) - Country. Maximum length is 2.
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
              {"entityPhoneId": "(val)","entityTypeCode": "(val)","phoneTypeCode": "(val)","phoneNumber": "(val)","extensionNumber": "(val)","countryCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityPhoneId": "(val)","entityTypeCode": "(val)","phoneTypeCode": "(val)","phoneNumber": "(val)","extensionNumber": "(val)","countryCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Person Document Phones [GET /research-sys/api/v1/person-document-phones/]
	                                          
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
    
            {"columns":["entityPhoneId","entityTypeCode","phoneTypeCode","phoneNumber","extensionNumber","countryCode","dflt","edit","documentNumber","active"],"primaryKey":"entityPhoneId"}
		
### Get Blueprint API specification for Person Document Phones [GET /research-sys/api/v1/person-document-phones/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Person Document Phones.md"
            transfer-encoding:chunked
### Update Person Document Phones [PUT /research-sys/api/v1/person-document-phones/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"entityPhoneId": "(val)","entityTypeCode": "(val)","phoneTypeCode": "(val)","phoneNumber": "(val)","extensionNumber": "(val)","countryCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Person Document Phones [PUT /research-sys/api/v1/person-document-phones/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"entityPhoneId": "(val)","entityTypeCode": "(val)","phoneTypeCode": "(val)","phoneNumber": "(val)","extensionNumber": "(val)","countryCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityPhoneId": "(val)","entityTypeCode": "(val)","phoneTypeCode": "(val)","phoneNumber": "(val)","extensionNumber": "(val)","countryCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Person Document Phones [POST /research-sys/api/v1/person-document-phones/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"entityPhoneId": "(val)","entityTypeCode": "(val)","phoneTypeCode": "(val)","phoneNumber": "(val)","extensionNumber": "(val)","countryCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"entityPhoneId": "(val)","entityTypeCode": "(val)","phoneTypeCode": "(val)","phoneNumber": "(val)","extensionNumber": "(val)","countryCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Person Document Phones [POST /research-sys/api/v1/person-document-phones/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"entityPhoneId": "(val)","entityTypeCode": "(val)","phoneTypeCode": "(val)","phoneNumber": "(val)","extensionNumber": "(val)","countryCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityPhoneId": "(val)","entityTypeCode": "(val)","phoneTypeCode": "(val)","phoneNumber": "(val)","extensionNumber": "(val)","countryCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"entityPhoneId": "(val)","entityTypeCode": "(val)","phoneTypeCode": "(val)","phoneNumber": "(val)","extensionNumber": "(val)","countryCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityPhoneId": "(val)","entityTypeCode": "(val)","phoneTypeCode": "(val)","phoneNumber": "(val)","extensionNumber": "(val)","countryCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete Person Document Phones by Key [DELETE /research-sys/api/v1/person-document-phones/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Document Phones [DELETE /research-sys/api/v1/person-document-phones/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Document Phones with Matching [DELETE /research-sys/api/v1/person-document-phones/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + entityPhoneId (optional) - Entity Phone Id.
    + entityTypeCode (optional) - Entity Type Code.
    + phoneTypeCode (optional) - Phone Type. Maximum length is 40.
    + phoneNumber (optional) - Phone Number. Maximum length is 20.
    + extensionNumber (optional) - Extension. Maximum length is 8.
    + countryCode (optional) - Country. Maximum length is 2.
    + dflt (optional) - Default. Maximum length is 1.
    + edit (optional) - Edit.
    + documentNumber (optional) - Document Number.
    + active (optional) - Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
