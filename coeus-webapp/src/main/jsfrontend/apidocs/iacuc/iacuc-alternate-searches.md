## Iacuc Alternate Searches [/research-sys/api/v1/iacuc-alternate-searches/]

### Get Iacuc Alternate Searches by Key [GET /research-sys/api/v1/iacuc-alternate-searches/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"iacucAltSearchId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","searchDate": "(val)","yearsSearched": "(val)","keywords": "(val)","comments": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Alternate Searches [GET /research-sys/api/v1/iacuc-alternate-searches/]
	 
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

### Get All Iacuc Alternate Searches with Filtering [GET /research-sys/api/v1/iacuc-alternate-searches/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + iacucAltSearchId
            + protocolId
            + protocolNumber
            + sequenceNumber
            + searchDate
            + yearsSearched
            + keywords
            + comments
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucAltSearchId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","searchDate": "(val)","yearsSearched": "(val)","keywords": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"iacucAltSearchId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","searchDate": "(val)","yearsSearched": "(val)","keywords": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Alternate Searches [GET /research-sys/api/v1/iacuc-alternate-searches/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters

            + _schema (required) - will instruct the endpoint to return a schema data structure for the resource

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            ${sampleSchema}
		
### Get Blueprint API specification for Iacuc Alternate Searches [GET /research-sys/api/v1/iacuc-alternate-searches/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Alternate Searches.md"
            transfer-encoding:chunked


### Update Iacuc Alternate Searches [PUT /research-sys/api/v1/iacuc-alternate-searches/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucAltSearchId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","searchDate": "(val)","yearsSearched": "(val)","keywords": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Alternate Searches [PUT /research-sys/api/v1/iacuc-alternate-searches/]

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

### Insert Iacuc Alternate Searches [POST /research-sys/api/v1/iacuc-alternate-searches/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucAltSearchId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","searchDate": "(val)","yearsSearched": "(val)","keywords": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"iacucAltSearchId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","searchDate": "(val)","yearsSearched": "(val)","keywords": "(val)","comments": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Alternate Searches [POST /research-sys/api/v1/iacuc-alternate-searches/]

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
            
### Delete Iacuc Alternate Searches by Key [DELETE /research-sys/api/v1/iacuc-alternate-searches/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Alternate Searches [DELETE /research-sys/api/v1/iacuc-alternate-searches/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Alternate Searches with Matching [DELETE /research-sys/api/v1/iacuc-alternate-searches/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + iacucAltSearchId
            + protocolId
            + protocolNumber
            + sequenceNumber
            + searchDate
            + yearsSearched
            + keywords
            + comments


+ Response 204
