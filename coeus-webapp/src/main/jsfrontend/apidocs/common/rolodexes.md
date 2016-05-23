## Rolodexes [/research-common/api/v1/rolodexes/]

### Get Rolodexes by Key [GET /research-common/api/v1/rolodexes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","createUser": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Rolodexes [GET /research-common/api/v1/rolodexes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","createUser": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","createUser": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Rolodexes with Filtering [GET /research-common/api/v1/rolodexes/]
    
+ Parameters

    + rolodexId (optional) - Rolodex Id. Maximum length is 6.
    + addressLine1 (optional) - Address Line 1. Maximum length is 80.
    + addressLine2 (optional) - Address Line 2. Maximum length is 80.
    + addressLine3 (optional) - Address Line 3. Maximum length is 80.
    + city (optional) - City. Maximum length is 30.
    + comments (optional) - Comments. Maximum length is 300.
    + countryCode (optional) - Country Code. Maximum length is 3.
    + county (optional) - County. Maximum length is 30.
    + deleteFlag (optional) - Delete Flag. Maximum length is 1.
    + emailAddress (optional) - Email Address. Maximum length is 60.
    + faxNumber (optional) - Fax Number. Maximum length is 20.
    + firstName (optional) - First Name. Maximum length is 20.
    + lastName (optional) - Last Name. Maximum length is 20.
    + middleName (optional) - Middle Name. Maximum length is 20.
    + organization (optional) - Organization. Maximum length is 200.
    + ownedByUnit (optional) - Owned By Unit. Maximum length is 8.
    + phoneNumber (optional) - Phone Number. Maximum length is 20.
    + postalCode (optional) - Postal Code. Maximum length is 15.
    + prefix (optional) - Prefix. Maximum length is 10.
    + sponsorAddressFlag (optional) - Sponsor Address Flag. Maximum length is 3.
    + sponsorCode (optional) - Sponsor Code. Maximum length is 6.
    + state (optional) - State. Maximum length is 30.
    + suffix (optional) - Suffix. Maximum length is 10.
    + title (optional) - Title. Maximum length is 35.
    + createUser (optional) - Create User.
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
              {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","createUser": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","createUser": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Rolodexes [GET /research-common/api/v1/rolodexes/]
	                                          
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
    
            {"columns":["rolodexId","addressLine1","addressLine2","addressLine3","city","comments","countryCode","county","deleteFlag","emailAddress","faxNumber","firstName","lastName","middleName","organization","ownedByUnit","phoneNumber","postalCode","prefix","sponsorAddressFlag","sponsorCode","state","suffix","title","createUser","active"],"primaryKey":"rolodexId"}
		
### Get Blueprint API specification for Rolodexes [GET /research-common/api/v1/rolodexes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Rolodexes.md"
            transfer-encoding:chunked
### Update Rolodexes [PUT /research-common/api/v1/rolodexes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","createUser": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Rolodexes [PUT /research-common/api/v1/rolodexes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","createUser": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","createUser": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Rolodexes [POST /research-common/api/v1/rolodexes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","createUser": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","createUser": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Rolodexes [POST /research-common/api/v1/rolodexes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","createUser": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","createUser": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","createUser": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"rolodexId": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","comments": "(val)","countryCode": "(val)","county": "(val)","deleteFlag": "(val)","emailAddress": "(val)","faxNumber": "(val)","firstName": "(val)","lastName": "(val)","middleName": "(val)","organization": "(val)","ownedByUnit": "(val)","phoneNumber": "(val)","postalCode": "(val)","prefix": "(val)","sponsorAddressFlag": "(val)","sponsorCode": "(val)","state": "(val)","suffix": "(val)","title": "(val)","createUser": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete Rolodexes by Key [DELETE /research-common/api/v1/rolodexes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rolodexes [DELETE /research-common/api/v1/rolodexes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rolodexes with Matching [DELETE /research-common/api/v1/rolodexes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + rolodexId (optional) - Rolodex Id. Maximum length is 6.
    + addressLine1 (optional) - Address Line 1. Maximum length is 80.
    + addressLine2 (optional) - Address Line 2. Maximum length is 80.
    + addressLine3 (optional) - Address Line 3. Maximum length is 80.
    + city (optional) - City. Maximum length is 30.
    + comments (optional) - Comments. Maximum length is 300.
    + countryCode (optional) - Country Code. Maximum length is 3.
    + county (optional) - County. Maximum length is 30.
    + deleteFlag (optional) - Delete Flag. Maximum length is 1.
    + emailAddress (optional) - Email Address. Maximum length is 60.
    + faxNumber (optional) - Fax Number. Maximum length is 20.
    + firstName (optional) - First Name. Maximum length is 20.
    + lastName (optional) - Last Name. Maximum length is 20.
    + middleName (optional) - Middle Name. Maximum length is 20.
    + organization (optional) - Organization. Maximum length is 200.
    + ownedByUnit (optional) - Owned By Unit. Maximum length is 8.
    + phoneNumber (optional) - Phone Number. Maximum length is 20.
    + postalCode (optional) - Postal Code. Maximum length is 15.
    + prefix (optional) - Prefix. Maximum length is 10.
    + sponsorAddressFlag (optional) - Sponsor Address Flag. Maximum length is 3.
    + sponsorCode (optional) - Sponsor Code. Maximum length is 6.
    + state (optional) - State. Maximum length is 30.
    + suffix (optional) - Suffix. Maximum length is 10.
    + title (optional) - Title. Maximum length is 35.
    + createUser (optional) - Create User.
    + active (optional) - Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
