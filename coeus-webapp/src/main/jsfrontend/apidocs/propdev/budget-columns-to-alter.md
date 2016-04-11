## Budget Columns To Alter [/research-sys/api/v1/budget-columns-to-alter/]

### Get Budget Columns To Alter by Key [GET /research-sys/api/v1/budget-columns-to-alter/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"columnName": "(val)","columnLabel": "(val)","dataLength": "(val)","dataType": "(val)","hasLookup": "(val)","lookupClass": "(val)","lookupReturn": "(val)","_primaryKey": "(val)"}

### Get All Budget Columns To Alter [GET /research-sys/api/v1/budget-columns-to-alter/]
	 
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

### Get All Budget Columns To Alter with Filtering [GET /research-sys/api/v1/budget-columns-to-alter/]
    
+ Parameters

        + columnName
            + columnLabel
            + dataLength
            + dataType
            + hasLookup
            + lookupClass
            + lookupReturn

            
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
			
### Get Schema for Budget Columns To Alter [GET /research-sys/api/v1/budget-columns-to-alter/]
	                                          
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
		
### Get Blueprint API specification for Budget Columns To Alter [GET /research-sys/api/v1/budget-columns-to-alter/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Columns To Alter.md"
            transfer-encoding:chunked


### Update Budget Columns To Alter [PUT /research-sys/api/v1/budget-columns-to-alter/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"columnName": "(val)","columnLabel": "(val)","dataLength": "(val)","dataType": "(val)","hasLookup": "(val)","lookupClass": "(val)","lookupReturn": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Columns To Alter [PUT /research-sys/api/v1/budget-columns-to-alter/]

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

### Insert Budget Columns To Alter [POST /research-sys/api/v1/budget-columns-to-alter/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"columnName": "(val)","columnLabel": "(val)","dataLength": "(val)","dataType": "(val)","hasLookup": "(val)","lookupClass": "(val)","lookupReturn": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"columnName": "(val)","columnLabel": "(val)","dataLength": "(val)","dataType": "(val)","hasLookup": "(val)","lookupClass": "(val)","lookupReturn": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Columns To Alter [POST /research-sys/api/v1/budget-columns-to-alter/]

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
            
### Delete Budget Columns To Alter by Key [DELETE /research-sys/api/v1/budget-columns-to-alter/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Columns To Alter [DELETE /research-sys/api/v1/budget-columns-to-alter/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Columns To Alter with Matching [DELETE /research-sys/api/v1/budget-columns-to-alter/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + columnName
            + columnLabel
            + dataLength
            + dataType
            + hasLookup
            + lookupClass
            + lookupReturn

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
