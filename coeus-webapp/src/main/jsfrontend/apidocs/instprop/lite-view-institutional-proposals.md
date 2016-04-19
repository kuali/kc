## Lite View Institutional Proposals [/instprop/api/v1/lite-view-institutional-proposals/]

### Get Lite View Institutional Proposals by Key [GET /instprop/api/v1/lite-view-institutional-proposals/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","proposalSequenceStatus": "(val)","proposalTypeCode": "(val)","statusCode": "(val)","title": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}

### Get All Lite View Institutional Proposals [GET /instprop/api/v1/lite-view-institutional-proposals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","proposalSequenceStatus": "(val)","proposalTypeCode": "(val)","statusCode": "(val)","title": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","unitNumber": "(val)","_primaryKey": "(val)"},
              {"proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","proposalSequenceStatus": "(val)","proposalTypeCode": "(val)","statusCode": "(val)","title": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Lite View Institutional Proposals with Filtering [GET /instprop/api/v1/lite-view-institutional-proposals/]
    
+ Parameters

    + proposalId (optional) - 
    + proposalNumber (optional) - 
    + sequenceNumber (optional) - 
    + proposalSequenceStatus (optional) - 
    + proposalTypeCode (optional) - 
    + statusCode (optional) - 
    + title (optional) - 
    + sponsorCode (optional) - 
    + activityTypeCode (optional) - 
    + requestedStartDateInitial (optional) - 
    + requestedStartDateTotal (optional) - 
    + requestedEndDateInitial (optional) - 
    + requestedEndDateTotal (optional) - 
    + totalDirectCostInitial (optional) - 
    + totalDirectCostTotal (optional) - 
    + totalIndirectCostInitial (optional) - 
    + totalIndirectCostTotal (optional) - 
    + unitNumber (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","proposalSequenceStatus": "(val)","proposalTypeCode": "(val)","statusCode": "(val)","title": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","unitNumber": "(val)","_primaryKey": "(val)"},
              {"proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","proposalSequenceStatus": "(val)","proposalTypeCode": "(val)","statusCode": "(val)","title": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Lite View Institutional Proposals [GET /instprop/api/v1/lite-view-institutional-proposals/]
	                                          
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
    
            {"columns":["proposalId","proposalNumber","sequenceNumber","proposalSequenceStatus","proposalTypeCode","statusCode","title","sponsorCode","activityTypeCode","requestedStartDateInitial","requestedStartDateTotal","requestedEndDateInitial","requestedEndDateTotal","totalDirectCostInitial","totalDirectCostTotal","totalIndirectCostInitial","totalIndirectCostTotal","unitNumber"],"primaryKey":"proposalId"}
		
### Get Blueprint API specification for Lite View Institutional Proposals [GET /instprop/api/v1/lite-view-institutional-proposals/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Lite View Institutional Proposals.md"
            transfer-encoding:chunked


### Update Lite View Institutional Proposals [PUT /instprop/api/v1/lite-view-institutional-proposals/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","proposalSequenceStatus": "(val)","proposalTypeCode": "(val)","statusCode": "(val)","title": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Lite View Institutional Proposals [PUT /instprop/api/v1/lite-view-institutional-proposals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","proposalSequenceStatus": "(val)","proposalTypeCode": "(val)","statusCode": "(val)","title": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","unitNumber": "(val)","_primaryKey": "(val)"},
              {"proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","proposalSequenceStatus": "(val)","proposalTypeCode": "(val)","statusCode": "(val)","title": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Lite View Institutional Proposals [POST /instprop/api/v1/lite-view-institutional-proposals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","proposalSequenceStatus": "(val)","proposalTypeCode": "(val)","statusCode": "(val)","title": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","proposalSequenceStatus": "(val)","proposalTypeCode": "(val)","statusCode": "(val)","title": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Lite View Institutional Proposals [POST /instprop/api/v1/lite-view-institutional-proposals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","proposalSequenceStatus": "(val)","proposalTypeCode": "(val)","statusCode": "(val)","title": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","unitNumber": "(val)","_primaryKey": "(val)"},
              {"proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","proposalSequenceStatus": "(val)","proposalTypeCode": "(val)","statusCode": "(val)","title": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","proposalSequenceStatus": "(val)","proposalTypeCode": "(val)","statusCode": "(val)","title": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","unitNumber": "(val)","_primaryKey": "(val)"},
              {"proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","proposalSequenceStatus": "(val)","proposalTypeCode": "(val)","statusCode": "(val)","title": "(val)","sponsorCode": "(val)","activityTypeCode": "(val)","requestedStartDateInitial": "(val)","requestedStartDateTotal": "(val)","requestedEndDateInitial": "(val)","requestedEndDateTotal": "(val)","totalDirectCostInitial": "(val)","totalDirectCostTotal": "(val)","totalIndirectCostInitial": "(val)","totalIndirectCostTotal": "(val)","unitNumber": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Lite View Institutional Proposals by Key [DELETE /instprop/api/v1/lite-view-institutional-proposals/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Lite View Institutional Proposals [DELETE /instprop/api/v1/lite-view-institutional-proposals/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Lite View Institutional Proposals with Matching [DELETE /instprop/api/v1/lite-view-institutional-proposals/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + proposalId (optional) - 
    + proposalNumber (optional) - 
    + sequenceNumber (optional) - 
    + proposalSequenceStatus (optional) - 
    + proposalTypeCode (optional) - 
    + statusCode (optional) - 
    + title (optional) - 
    + sponsorCode (optional) - 
    + activityTypeCode (optional) - 
    + requestedStartDateInitial (optional) - 
    + requestedStartDateTotal (optional) - 
    + requestedEndDateInitial (optional) - 
    + requestedEndDateTotal (optional) - 
    + totalDirectCostInitial (optional) - 
    + totalDirectCostTotal (optional) - 
    + totalIndirectCostInitial (optional) - 
    + totalIndirectCostTotal (optional) - 
    + unitNumber (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
