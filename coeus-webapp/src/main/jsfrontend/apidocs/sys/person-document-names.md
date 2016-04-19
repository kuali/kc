## Person Document Names [/research-sys/api/v1/person-document-names/]

### Get Person Document Names by Key [GET /research-sys/api/v1/person-document-names/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"entityNameId": "(val)","nameCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","namePrefix": "(val)","nameTitle": "(val)","nameSuffix": "(val)","noteMessage": "(val)","nameChangedDate": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Person Document Names [GET /research-sys/api/v1/person-document-names/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"entityNameId": "(val)","nameCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","namePrefix": "(val)","nameTitle": "(val)","nameSuffix": "(val)","noteMessage": "(val)","nameChangedDate": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityNameId": "(val)","nameCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","namePrefix": "(val)","nameTitle": "(val)","nameSuffix": "(val)","noteMessage": "(val)","nameChangedDate": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Person Document Names with Filtering [GET /research-sys/api/v1/person-document-names/]
    
+ Parameters

    + entityNameId (optional) - Entity Name Id.
    + nameCode (optional) - Name Code. Maximum length is 40.
    + firstName (optional) - First Name. Maximum length is 40.
    + middleName (optional) - Middle Name. Maximum length is 40.
    + lastName (optional) - Last Name. Maximum length is 80.
    + namePrefix (optional) - Name Prefix. Maximum length is 20.
    + nameTitle (optional) - Name Title.
    + nameSuffix (optional) - Name Suffix. Maximum length is 20.
    + noteMessage (optional) - Note Message.
    + nameChangedDate (optional) - Name Changed Date.
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
              {"entityNameId": "(val)","nameCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","namePrefix": "(val)","nameTitle": "(val)","nameSuffix": "(val)","noteMessage": "(val)","nameChangedDate": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityNameId": "(val)","nameCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","namePrefix": "(val)","nameTitle": "(val)","nameSuffix": "(val)","noteMessage": "(val)","nameChangedDate": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Person Document Names [GET /research-sys/api/v1/person-document-names/]
	                                          
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
    
            {"columns":["entityNameId","nameCode","firstName","middleName","lastName","namePrefix","nameTitle","nameSuffix","noteMessage","nameChangedDate","dflt","edit","documentNumber","active"],"primaryKey":"entityNameId"}
		
### Get Blueprint API specification for Person Document Names [GET /research-sys/api/v1/person-document-names/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Person Document Names.md"
            transfer-encoding:chunked


### Update Person Document Names [PUT /research-sys/api/v1/person-document-names/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"entityNameId": "(val)","nameCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","namePrefix": "(val)","nameTitle": "(val)","nameSuffix": "(val)","noteMessage": "(val)","nameChangedDate": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Person Document Names [PUT /research-sys/api/v1/person-document-names/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"entityNameId": "(val)","nameCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","namePrefix": "(val)","nameTitle": "(val)","nameSuffix": "(val)","noteMessage": "(val)","nameChangedDate": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityNameId": "(val)","nameCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","namePrefix": "(val)","nameTitle": "(val)","nameSuffix": "(val)","noteMessage": "(val)","nameChangedDate": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Person Document Names [POST /research-sys/api/v1/person-document-names/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"entityNameId": "(val)","nameCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","namePrefix": "(val)","nameTitle": "(val)","nameSuffix": "(val)","noteMessage": "(val)","nameChangedDate": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"entityNameId": "(val)","nameCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","namePrefix": "(val)","nameTitle": "(val)","nameSuffix": "(val)","noteMessage": "(val)","nameChangedDate": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Person Document Names [POST /research-sys/api/v1/person-document-names/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"entityNameId": "(val)","nameCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","namePrefix": "(val)","nameTitle": "(val)","nameSuffix": "(val)","noteMessage": "(val)","nameChangedDate": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityNameId": "(val)","nameCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","namePrefix": "(val)","nameTitle": "(val)","nameSuffix": "(val)","noteMessage": "(val)","nameChangedDate": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"entityNameId": "(val)","nameCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","namePrefix": "(val)","nameTitle": "(val)","nameSuffix": "(val)","noteMessage": "(val)","nameChangedDate": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityNameId": "(val)","nameCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","namePrefix": "(val)","nameTitle": "(val)","nameSuffix": "(val)","noteMessage": "(val)","nameChangedDate": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Person Document Names by Key [DELETE /research-sys/api/v1/person-document-names/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Document Names [DELETE /research-sys/api/v1/person-document-names/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Document Names with Matching [DELETE /research-sys/api/v1/person-document-names/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + entityNameId (optional) - Entity Name Id.
    + nameCode (optional) - Name Code. Maximum length is 40.
    + firstName (optional) - First Name. Maximum length is 40.
    + middleName (optional) - Middle Name. Maximum length is 40.
    + lastName (optional) - Last Name. Maximum length is 80.
    + namePrefix (optional) - Name Prefix. Maximum length is 20.
    + nameTitle (optional) - Name Title.
    + nameSuffix (optional) - Name Suffix. Maximum length is 20.
    + noteMessage (optional) - Note Message.
    + nameChangedDate (optional) - Name Changed Date.
    + dflt (optional) - Default. Maximum length is 1.
    + edit (optional) - Edit.
    + documentNumber (optional) - Document Number.
    + active (optional) - Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
