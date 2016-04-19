## Iacuc Alternate Searches [/iacuc/api/v1/iacuc-alternate-searches/]

### Get Iacuc Alternate Searches by Key [GET /iacuc/api/v1/iacuc-alternate-searches/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"iacucAltSearchId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","searchDate": "(val)","yearsSearched": "(val)","keywords": "(val)","comments": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Alternate Searches [GET /iacuc/api/v1/iacuc-alternate-searches/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucAltSearchId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","searchDate": "(val)","yearsSearched": "(val)","keywords": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"iacucAltSearchId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","searchDate": "(val)","yearsSearched": "(val)","keywords": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Alternate Searches with Filtering [GET /iacuc/api/v1/iacuc-alternate-searches/]
    
+ Parameters

    + iacucAltSearchId (optional) - 
    + protocolId (optional) - 
    + protocolNumber (optional) - 
    + sequenceNumber (optional) - 
    + searchDate (optional) - Search Date. Maximum length is 10.
    + yearsSearched (optional) - Years Searched. Maximum length is 2000.
    + keywords (optional) - Keywords. Maximum length is 2000.
    + comments (optional) - Comments. Maximum length is 2000.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucAltSearchId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","searchDate": "(val)","yearsSearched": "(val)","keywords": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"iacucAltSearchId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","searchDate": "(val)","yearsSearched": "(val)","keywords": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Alternate Searches [GET /iacuc/api/v1/iacuc-alternate-searches/]
	                                          
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
    
            {"columns":["iacucAltSearchId","protocolId","protocolNumber","sequenceNumber","searchDate","yearsSearched","keywords","comments"],"primaryKey":"iacucAltSearchId"}
		
### Get Blueprint API specification for Iacuc Alternate Searches [GET /iacuc/api/v1/iacuc-alternate-searches/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Alternate Searches.md"
            transfer-encoding:chunked


### Update Iacuc Alternate Searches [PUT /iacuc/api/v1/iacuc-alternate-searches/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucAltSearchId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","searchDate": "(val)","yearsSearched": "(val)","keywords": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Alternate Searches [PUT /iacuc/api/v1/iacuc-alternate-searches/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"iacucAltSearchId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","searchDate": "(val)","yearsSearched": "(val)","keywords": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"iacucAltSearchId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","searchDate": "(val)","yearsSearched": "(val)","keywords": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Alternate Searches [POST /iacuc/api/v1/iacuc-alternate-searches/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucAltSearchId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","searchDate": "(val)","yearsSearched": "(val)","keywords": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"iacucAltSearchId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","searchDate": "(val)","yearsSearched": "(val)","keywords": "(val)","comments": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Alternate Searches [POST /iacuc/api/v1/iacuc-alternate-searches/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"iacucAltSearchId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","searchDate": "(val)","yearsSearched": "(val)","keywords": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"iacucAltSearchId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","searchDate": "(val)","yearsSearched": "(val)","keywords": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"iacucAltSearchId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","searchDate": "(val)","yearsSearched": "(val)","keywords": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"iacucAltSearchId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","searchDate": "(val)","yearsSearched": "(val)","keywords": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Alternate Searches by Key [DELETE /iacuc/api/v1/iacuc-alternate-searches/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Alternate Searches [DELETE /iacuc/api/v1/iacuc-alternate-searches/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Alternate Searches with Matching [DELETE /iacuc/api/v1/iacuc-alternate-searches/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + iacucAltSearchId (optional) - 
    + protocolId (optional) - 
    + protocolNumber (optional) - 
    + sequenceNumber (optional) - 
    + searchDate (optional) - Search Date. Maximum length is 10.
    + yearsSearched (optional) - Years Searched. Maximum length is 2000.
    + keywords (optional) - Keywords. Maximum length is 2000.
    + comments (optional) - Comments. Maximum length is 2000.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
