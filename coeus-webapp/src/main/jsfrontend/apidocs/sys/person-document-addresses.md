## Person Document Addresses [/research-sys/api/v1/person-document-addresses/]

### Get Person Document Addresses by Key [GET /research-sys/api/v1/person-document-addresses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"entityAddressId": "(val)","addressTypeCode": "(val)","city": "(val)","stateProvinceCode": "(val)","postalCode": "(val)","countryCode": "(val)","attentionLine": "(val)","line1": "(val)","line2": "(val)","line3": "(val)","addressFormat": "(val)","modifiedDate": "(val)","validatedDate": "(val)","validated": "(val)","noteMessage": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Person Document Addresses [GET /research-sys/api/v1/person-document-addresses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"entityAddressId": "(val)","addressTypeCode": "(val)","city": "(val)","stateProvinceCode": "(val)","postalCode": "(val)","countryCode": "(val)","attentionLine": "(val)","line1": "(val)","line2": "(val)","line3": "(val)","addressFormat": "(val)","modifiedDate": "(val)","validatedDate": "(val)","validated": "(val)","noteMessage": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityAddressId": "(val)","addressTypeCode": "(val)","city": "(val)","stateProvinceCode": "(val)","postalCode": "(val)","countryCode": "(val)","attentionLine": "(val)","line1": "(val)","line2": "(val)","line3": "(val)","addressFormat": "(val)","modifiedDate": "(val)","validatedDate": "(val)","validated": "(val)","noteMessage": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Person Document Addresses with Filtering [GET /research-sys/api/v1/person-document-addresses/]
    
+ Parameters

    + entityAddressId (optional) - Entity Address Id.
    + addressTypeCode (optional) - Address Type. Maximum length is 40.
    + city (optional) - City. Maximum length is 30.
    + stateProvinceCode (optional) - State/Province. Maximum length is 2.
    + postalCode (optional) - Postal Code. Maximum length is 20.
    + countryCode (optional) - Country. Maximum length is 2.
    + attentionLine (optional) - Attention Line.
    + line1 (optional) - Line 1. Maximum length is 128.
    + line2 (optional) - Line 2. Maximum length is 128.
    + line3 (optional) - Line 3. Maximum length is 128.
    + addressFormat (optional) - Address Format.
    + modifiedDate (optional) - Modified Date.
    + validatedDate (optional) - Validated Date.
    + validated (optional) - Validated.
    + noteMessage (optional) - Note Message.
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
              {"entityAddressId": "(val)","addressTypeCode": "(val)","city": "(val)","stateProvinceCode": "(val)","postalCode": "(val)","countryCode": "(val)","attentionLine": "(val)","line1": "(val)","line2": "(val)","line3": "(val)","addressFormat": "(val)","modifiedDate": "(val)","validatedDate": "(val)","validated": "(val)","noteMessage": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityAddressId": "(val)","addressTypeCode": "(val)","city": "(val)","stateProvinceCode": "(val)","postalCode": "(val)","countryCode": "(val)","attentionLine": "(val)","line1": "(val)","line2": "(val)","line3": "(val)","addressFormat": "(val)","modifiedDate": "(val)","validatedDate": "(val)","validated": "(val)","noteMessage": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Person Document Addresses [GET /research-sys/api/v1/person-document-addresses/]
	                                          
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
    
            {"columns":["entityAddressId","addressTypeCode","city","stateProvinceCode","postalCode","countryCode","attentionLine","line1","line2","line3","addressFormat","modifiedDate","validatedDate","validated","noteMessage","dflt","edit","documentNumber","active"],"primaryKey":"entityAddressId"}
		
### Get Blueprint API specification for Person Document Addresses [GET /research-sys/api/v1/person-document-addresses/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Person Document Addresses.md"
            transfer-encoding:chunked
### Update Person Document Addresses [PUT /research-sys/api/v1/person-document-addresses/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"entityAddressId": "(val)","addressTypeCode": "(val)","city": "(val)","stateProvinceCode": "(val)","postalCode": "(val)","countryCode": "(val)","attentionLine": "(val)","line1": "(val)","line2": "(val)","line3": "(val)","addressFormat": "(val)","modifiedDate": "(val)","validatedDate": "(val)","validated": "(val)","noteMessage": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Person Document Addresses [PUT /research-sys/api/v1/person-document-addresses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"entityAddressId": "(val)","addressTypeCode": "(val)","city": "(val)","stateProvinceCode": "(val)","postalCode": "(val)","countryCode": "(val)","attentionLine": "(val)","line1": "(val)","line2": "(val)","line3": "(val)","addressFormat": "(val)","modifiedDate": "(val)","validatedDate": "(val)","validated": "(val)","noteMessage": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityAddressId": "(val)","addressTypeCode": "(val)","city": "(val)","stateProvinceCode": "(val)","postalCode": "(val)","countryCode": "(val)","attentionLine": "(val)","line1": "(val)","line2": "(val)","line3": "(val)","addressFormat": "(val)","modifiedDate": "(val)","validatedDate": "(val)","validated": "(val)","noteMessage": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Person Document Addresses [POST /research-sys/api/v1/person-document-addresses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"entityAddressId": "(val)","addressTypeCode": "(val)","city": "(val)","stateProvinceCode": "(val)","postalCode": "(val)","countryCode": "(val)","attentionLine": "(val)","line1": "(val)","line2": "(val)","line3": "(val)","addressFormat": "(val)","modifiedDate": "(val)","validatedDate": "(val)","validated": "(val)","noteMessage": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"entityAddressId": "(val)","addressTypeCode": "(val)","city": "(val)","stateProvinceCode": "(val)","postalCode": "(val)","countryCode": "(val)","attentionLine": "(val)","line1": "(val)","line2": "(val)","line3": "(val)","addressFormat": "(val)","modifiedDate": "(val)","validatedDate": "(val)","validated": "(val)","noteMessage": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Person Document Addresses [POST /research-sys/api/v1/person-document-addresses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"entityAddressId": "(val)","addressTypeCode": "(val)","city": "(val)","stateProvinceCode": "(val)","postalCode": "(val)","countryCode": "(val)","attentionLine": "(val)","line1": "(val)","line2": "(val)","line3": "(val)","addressFormat": "(val)","modifiedDate": "(val)","validatedDate": "(val)","validated": "(val)","noteMessage": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityAddressId": "(val)","addressTypeCode": "(val)","city": "(val)","stateProvinceCode": "(val)","postalCode": "(val)","countryCode": "(val)","attentionLine": "(val)","line1": "(val)","line2": "(val)","line3": "(val)","addressFormat": "(val)","modifiedDate": "(val)","validatedDate": "(val)","validated": "(val)","noteMessage": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"entityAddressId": "(val)","addressTypeCode": "(val)","city": "(val)","stateProvinceCode": "(val)","postalCode": "(val)","countryCode": "(val)","attentionLine": "(val)","line1": "(val)","line2": "(val)","line3": "(val)","addressFormat": "(val)","modifiedDate": "(val)","validatedDate": "(val)","validated": "(val)","noteMessage": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityAddressId": "(val)","addressTypeCode": "(val)","city": "(val)","stateProvinceCode": "(val)","postalCode": "(val)","countryCode": "(val)","attentionLine": "(val)","line1": "(val)","line2": "(val)","line3": "(val)","addressFormat": "(val)","modifiedDate": "(val)","validatedDate": "(val)","validated": "(val)","noteMessage": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete Person Document Addresses by Key [DELETE /research-sys/api/v1/person-document-addresses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Document Addresses [DELETE /research-sys/api/v1/person-document-addresses/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Document Addresses with Matching [DELETE /research-sys/api/v1/person-document-addresses/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + entityAddressId (optional) - Entity Address Id.
    + addressTypeCode (optional) - Address Type. Maximum length is 40.
    + city (optional) - City. Maximum length is 30.
    + stateProvinceCode (optional) - State/Province. Maximum length is 2.
    + postalCode (optional) - Postal Code. Maximum length is 20.
    + countryCode (optional) - Country. Maximum length is 2.
    + attentionLine (optional) - Attention Line.
    + line1 (optional) - Line 1. Maximum length is 128.
    + line2 (optional) - Line 2. Maximum length is 128.
    + line3 (optional) - Line 3. Maximum length is 128.
    + addressFormat (optional) - Address Format.
    + modifiedDate (optional) - Modified Date.
    + validatedDate (optional) - Validated Date.
    + validated (optional) - Validated.
    + noteMessage (optional) - Note Message.
    + dflt (optional) - Default. Maximum length is 1.
    + edit (optional) - Edit.
    + documentNumber (optional) - Document Number.
    + active (optional) - Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
