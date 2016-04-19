## Proposal Columns To Alter [/propdev/api/v1/proposal-columns-to-alter/]

### Get Proposal Columns To Alter by Key [GET /propdev/api/v1/proposal-columns-to-alter/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"columnName": "(val)","columnLabel": "(val)","dataLength": "(val)","dataType": "(val)","hasLookup": "(val)","lookupClass": "(val)","lookupReturn": "(val)","_primaryKey": "(val)"}

### Get All Proposal Columns To Alter [GET /propdev/api/v1/proposal-columns-to-alter/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"columnName": "(val)","columnLabel": "(val)","dataLength": "(val)","dataType": "(val)","hasLookup": "(val)","lookupClass": "(val)","lookupReturn": "(val)","_primaryKey": "(val)"},
              {"columnName": "(val)","columnLabel": "(val)","dataLength": "(val)","dataType": "(val)","hasLookup": "(val)","lookupClass": "(val)","lookupReturn": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Columns To Alter with Filtering [GET /propdev/api/v1/proposal-columns-to-alter/]
    
+ Parameters

    + columnName (optional) - Column Name. Maximum length is 30.
    + columnLabel (optional) - Column Label. Maximum length is 30.
    + dataLength (optional) - Data Length. Maximum length is 4.
    + dataType (optional) - Data Type. Maximum length is 9.
    + hasLookup (optional) - Has Lookup. Maximum length is 1.
    + lookupClass (optional) - Lookup Argument. Maximum length is 100.
    + lookupReturn (optional) - Lookup Return. Maximum length is 50.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"columnName": "(val)","columnLabel": "(val)","dataLength": "(val)","dataType": "(val)","hasLookup": "(val)","lookupClass": "(val)","lookupReturn": "(val)","_primaryKey": "(val)"},
              {"columnName": "(val)","columnLabel": "(val)","dataLength": "(val)","dataType": "(val)","hasLookup": "(val)","lookupClass": "(val)","lookupReturn": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Columns To Alter [GET /propdev/api/v1/proposal-columns-to-alter/]
	                                          
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
    
            {"columns":["columnName","columnLabel","dataLength","dataType","hasLookup","lookupClass","lookupReturn"],"primaryKey":"columnName"}
		
### Get Blueprint API specification for Proposal Columns To Alter [GET /propdev/api/v1/proposal-columns-to-alter/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Columns To Alter.md"
            transfer-encoding:chunked


### Update Proposal Columns To Alter [PUT /propdev/api/v1/proposal-columns-to-alter/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"columnName": "(val)","columnLabel": "(val)","dataLength": "(val)","dataType": "(val)","hasLookup": "(val)","lookupClass": "(val)","lookupReturn": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Columns To Alter [PUT /propdev/api/v1/proposal-columns-to-alter/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"columnName": "(val)","columnLabel": "(val)","dataLength": "(val)","dataType": "(val)","hasLookup": "(val)","lookupClass": "(val)","lookupReturn": "(val)","_primaryKey": "(val)"},
              {"columnName": "(val)","columnLabel": "(val)","dataLength": "(val)","dataType": "(val)","hasLookup": "(val)","lookupClass": "(val)","lookupReturn": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Proposal Columns To Alter [POST /propdev/api/v1/proposal-columns-to-alter/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"columnName": "(val)","columnLabel": "(val)","dataLength": "(val)","dataType": "(val)","hasLookup": "(val)","lookupClass": "(val)","lookupReturn": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"columnName": "(val)","columnLabel": "(val)","dataLength": "(val)","dataType": "(val)","hasLookup": "(val)","lookupClass": "(val)","lookupReturn": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Columns To Alter [POST /propdev/api/v1/proposal-columns-to-alter/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"columnName": "(val)","columnLabel": "(val)","dataLength": "(val)","dataType": "(val)","hasLookup": "(val)","lookupClass": "(val)","lookupReturn": "(val)","_primaryKey": "(val)"},
              {"columnName": "(val)","columnLabel": "(val)","dataLength": "(val)","dataType": "(val)","hasLookup": "(val)","lookupClass": "(val)","lookupReturn": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"columnName": "(val)","columnLabel": "(val)","dataLength": "(val)","dataType": "(val)","hasLookup": "(val)","lookupClass": "(val)","lookupReturn": "(val)","_primaryKey": "(val)"},
              {"columnName": "(val)","columnLabel": "(val)","dataLength": "(val)","dataType": "(val)","hasLookup": "(val)","lookupClass": "(val)","lookupReturn": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Proposal Columns To Alter by Key [DELETE /propdev/api/v1/proposal-columns-to-alter/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Columns To Alter [DELETE /propdev/api/v1/proposal-columns-to-alter/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Columns To Alter with Matching [DELETE /propdev/api/v1/proposal-columns-to-alter/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + columnName (optional) - Column Name. Maximum length is 30.
    + columnLabel (optional) - Column Label. Maximum length is 30.
    + dataLength (optional) - Data Length. Maximum length is 4.
    + dataType (optional) - Data Type. Maximum length is 9.
    + hasLookup (optional) - Has Lookup. Maximum length is 1.
    + lookupClass (optional) - Lookup Argument. Maximum length is 100.
    + lookupReturn (optional) - Lookup Return. Maximum length is 50.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
