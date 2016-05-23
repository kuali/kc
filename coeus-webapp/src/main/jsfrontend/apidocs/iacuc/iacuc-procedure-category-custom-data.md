## Iacuc Procedure Category Custom Data [/iacuc/api/v1/iacuc-procedure-category-custom-data/]

### Get Iacuc Procedure Category Custom Data by Key [GET /iacuc/api/v1/iacuc-procedure-category-custom-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Procedure Category Custom Data [GET /iacuc/api/v1/iacuc-procedure-category-custom-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Procedure Category Custom Data with Filtering [GET /iacuc/api/v1/iacuc-procedure-category-custom-data/]
    
+ Parameters

    + id (optional) - Id. Maximum length is 12.
    + procedureCategoryCode (optional) - Procedure Category Code. Maximum length is 3.
    + dataLength (optional) - Data Length. Maximum length is 4.
    + dataTypeCode (optional) - Data Type Code. Maximum length is 3.
    + defaultValue (optional) - Default Value. Maximum length is 2000.
    + label (optional) - Label. Maximum length is 30.
    + lookupClass (optional) - Lookup Class. Maximum length is 100.
    + lookupReturn (optional) - Lookup Return. Maximum length is 30.
    + name (optional) - Name. Maximum length is 30.
    + active (optional) - Active. Maximum length is 1.
    + sortId (optional) - Sort Id. Maximum length is 22.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Procedure Category Custom Data [GET /iacuc/api/v1/iacuc-procedure-category-custom-data/]
	                                          
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
    
            {"columns":["id","procedureCategoryCode","dataLength","dataTypeCode","defaultValue","label","lookupClass","lookupReturn","name","active","sortId"],"primaryKey":"id"}
		
### Get Blueprint API specification for Iacuc Procedure Category Custom Data [GET /iacuc/api/v1/iacuc-procedure-category-custom-data/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Procedure Category Custom Data.md"
            transfer-encoding:chunked
### Update Iacuc Procedure Category Custom Data [PUT /iacuc/api/v1/iacuc-procedure-category-custom-data/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Procedure Category Custom Data [PUT /iacuc/api/v1/iacuc-procedure-category-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Iacuc Procedure Category Custom Data [POST /iacuc/api/v1/iacuc-procedure-category-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Procedure Category Custom Data [POST /iacuc/api/v1/iacuc-procedure-category-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
### Delete Iacuc Procedure Category Custom Data by Key [DELETE /iacuc/api/v1/iacuc-procedure-category-custom-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Procedure Category Custom Data [DELETE /iacuc/api/v1/iacuc-procedure-category-custom-data/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Procedure Category Custom Data with Matching [DELETE /iacuc/api/v1/iacuc-procedure-category-custom-data/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Id. Maximum length is 12.
    + procedureCategoryCode (optional) - Procedure Category Code. Maximum length is 3.
    + dataLength (optional) - Data Length. Maximum length is 4.
    + dataTypeCode (optional) - Data Type Code. Maximum length is 3.
    + defaultValue (optional) - Default Value. Maximum length is 2000.
    + label (optional) - Label. Maximum length is 30.
    + lookupClass (optional) - Lookup Class. Maximum length is 100.
    + lookupReturn (optional) - Lookup Return. Maximum length is 30.
    + name (optional) - Name. Maximum length is 30.
    + active (optional) - Active. Maximum length is 1.
    + sortId (optional) - Sort Id. Maximum length is 22.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
