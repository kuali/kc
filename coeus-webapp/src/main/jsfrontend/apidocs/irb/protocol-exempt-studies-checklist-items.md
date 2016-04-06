## Protocol Exempt Studies Checklist Items [/research-sys/api/v1/protocol-exempt-studies-checklist-items/]

### Get Protocol Exempt Studies Checklist Items by Key [GET /research-sys/api/v1/protocol-exempt-studies-checklist-items/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolExemptCheckListId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","exemptStudiesCheckListCode": "(val)","_primaryKey": "(val)"}

### Get All Protocol Exempt Studies Checklist Items [GET /research-sys/api/v1/protocol-exempt-studies-checklist-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolExemptCheckListId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","exemptStudiesCheckListCode": "(val)","_primaryKey": "(val)"},
              {"protocolExemptCheckListId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","exemptStudiesCheckListCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Exempt Studies Checklist Items with Filtering [GET /research-sys/api/v1/protocol-exempt-studies-checklist-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + protocolExemptCheckListId
            + protocolId
            + submissionIdFk
            + protocolNumber
            + sequenceNumber
            + submissionNumber
            + exemptStudiesCheckListCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolExemptCheckListId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","exemptStudiesCheckListCode": "(val)","_primaryKey": "(val)"},
              {"protocolExemptCheckListId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","exemptStudiesCheckListCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Exempt Studies Checklist Items [GET /research-sys/api/v1/protocol-exempt-studies-checklist-items/]
	 
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
		
### Get Blueprint API specification for Protocol Exempt Studies Checklist Items [GET /research-sys/api/v1/protocol-exempt-studies-checklist-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Exempt Studies Checklist Items.md"
            transfer-encoding:chunked


### Update Protocol Exempt Studies Checklist Items [PUT /research-sys/api/v1/protocol-exempt-studies-checklist-items/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolExemptCheckListId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","exemptStudiesCheckListCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Exempt Studies Checklist Items [PUT /research-sys/api/v1/protocol-exempt-studies-checklist-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolExemptCheckListId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","exemptStudiesCheckListCode": "(val)","_primaryKey": "(val)"},
              {"protocolExemptCheckListId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","exemptStudiesCheckListCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Protocol Exempt Studies Checklist Items [POST /research-sys/api/v1/protocol-exempt-studies-checklist-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolExemptCheckListId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","exemptStudiesCheckListCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolExemptCheckListId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","exemptStudiesCheckListCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Exempt Studies Checklist Items [POST /research-sys/api/v1/protocol-exempt-studies-checklist-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolExemptCheckListId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","exemptStudiesCheckListCode": "(val)","_primaryKey": "(val)"},
              {"protocolExemptCheckListId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","exemptStudiesCheckListCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolExemptCheckListId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","exemptStudiesCheckListCode": "(val)","_primaryKey": "(val)"},
              {"protocolExemptCheckListId": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","submissionNumber": "(val)","exemptStudiesCheckListCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Protocol Exempt Studies Checklist Items by Key [DELETE /research-sys/api/v1/protocol-exempt-studies-checklist-items/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Exempt Studies Checklist Items [DELETE /research-sys/api/v1/protocol-exempt-studies-checklist-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Protocol Exempt Studies Checklist Items with Matching [DELETE /research-sys/api/v1/protocol-exempt-studies-checklist-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + protocolExemptCheckListId
            + protocolId
            + submissionIdFk
            + protocolNumber
            + sequenceNumber
            + submissionNumber
            + exemptStudiesCheckListCode


+ Response 204
