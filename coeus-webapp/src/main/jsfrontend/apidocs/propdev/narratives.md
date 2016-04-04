## Narratives [/research-sys/api/v1/narratives/]

### Get Narratives by Key [GET /research-sys/api/v1/narratives/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}

### Get All Narratives [GET /research-sys/api/v1/narratives/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]

### Get All Narratives with Filtering [GET /research-sys/api/v1/narratives/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + moduleNumber
            + comments
            + contactName
            + emailAddress
            + moduleSequenceNumber
            + moduleStatusCode
            + moduleTitle
            + narrativeTypeCode
            + phoneNumber
            + name
            + type
            + hierarchyProposalNumber
            + hiddenInHierarchy
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Narratives [GET /research-sys/api/v1/narratives/]
	 
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
		
### Get Blueprint API specification for Narratives [GET /research-sys/api/v1/narratives/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Narratives.md"
            transfer-encoding:chunked


### Update Narratives [PUT /research-sys/api/v1/narratives/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Narratives [PUT /research-sys/api/v1/narratives/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Narratives [POST /research-sys/api/v1/narratives/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Narratives [POST /research-sys/api/v1/narratives/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"moduleNumber": "(val)","comments": "(val)","contactName": "(val)","emailAddress": "(val)","moduleSequenceNumber": "(val)","moduleStatusCode": "(val)","moduleTitle": "(val)","narrativeTypeCode": "(val)","phoneNumber": "(val)","name": "(val)","type": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Narratives by Key [DELETE /research-sys/api/v1/narratives/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Narratives [DELETE /research-sys/api/v1/narratives/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Narratives with Matching [DELETE /research-sys/api/v1/narratives/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + moduleNumber
            + comments
            + contactName
            + emailAddress
            + moduleSequenceNumber
            + moduleStatusCode
            + moduleTitle
            + narrativeTypeCode
            + phoneNumber
            + name
            + type
            + hierarchyProposalNumber
            + hiddenInHierarchy


+ Response 204
