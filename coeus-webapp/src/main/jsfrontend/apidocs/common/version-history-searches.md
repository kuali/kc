## Version History Searches [/research-common/api/v1/version-history-searches/]

### Get Version History Searches by Key [GET /research-common/api/v1/version-history-searches/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"}

### Get All Version History Searches [GET /research-common/api/v1/version-history-searches/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"},
              {"sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"}
            ]

### Get All Version History Searches with Filtering [GET /research-common/api/v1/version-history-searches/]
    
+ Parameters

    + sequenceOwnerVersionNameValue (optional) - 
    + sequenceOwnerSequenceNumber (optional) - 
    + sequenceOwnerClassName (optional) - 
    + sequenceOwnerVersionNameField (optional) - 
    + statusForOjb (optional) - 
    + userId (optional) - 
    + versionDate (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"},
              {"sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Version History Searches [GET /research-common/api/v1/version-history-searches/]
	                                          
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
    
            {"columns":["sequenceOwnerVersionNameValue","sequenceOwnerSequenceNumber","sequenceOwnerClassName","sequenceOwnerVersionNameField","statusForOjb","userId","versionDate"],"primaryKey":"sequenceOwnerSequenceNumber:sequenceOwnerVersionNameValue"}
		
### Get Blueprint API specification for Version History Searches [GET /research-common/api/v1/version-history-searches/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Version History Searches.md"
            transfer-encoding:chunked
### Update Version History Searches [PUT /research-common/api/v1/version-history-searches/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Version History Searches [PUT /research-common/api/v1/version-history-searches/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"},
              {"sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Version History Searches [POST /research-common/api/v1/version-history-searches/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Version History Searches [POST /research-common/api/v1/version-history-searches/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"},
              {"sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"},
              {"sequenceOwnerVersionNameValue": "(val)","sequenceOwnerSequenceNumber": "(val)","sequenceOwnerClassName": "(val)","sequenceOwnerVersionNameField": "(val)","statusForOjb": "(val)","userId": "(val)","versionDate": "(val)","_primaryKey": "(val)"}
            ]
### Delete Version History Searches by Key [DELETE /research-common/api/v1/version-history-searches/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Version History Searches [DELETE /research-common/api/v1/version-history-searches/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Version History Searches with Matching [DELETE /research-common/api/v1/version-history-searches/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + sequenceOwnerVersionNameValue (optional) - 
    + sequenceOwnerSequenceNumber (optional) - 
    + sequenceOwnerClassName (optional) - 
    + sequenceOwnerVersionNameField (optional) - 
    + statusForOjb (optional) - 
    + userId (optional) - 
    + versionDate (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
