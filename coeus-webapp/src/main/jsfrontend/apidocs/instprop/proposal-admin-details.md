## Proposal Admin Details [/instprop/api/v1/proposal-admin-details/]

### Get Proposal Admin Details by Key [GET /instprop/api/v1/proposal-admin-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalAdminDetailId": "(val)","dateSubmittedByDept": "(val)","dateReturnedToDept": "(val)","dateApprovedByOsp": "(val)","dateSubmittedToAgency": "(val)","instPropCreateDate": "(val)","instPropCreateUser": "(val)","signedBy": "(val)","submissionType": "(val)","devProposalNumber": "(val)","instProposalId": "(val)","_primaryKey": "(val)"}

### Get All Proposal Admin Details [GET /instprop/api/v1/proposal-admin-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalAdminDetailId": "(val)","dateSubmittedByDept": "(val)","dateReturnedToDept": "(val)","dateApprovedByOsp": "(val)","dateSubmittedToAgency": "(val)","instPropCreateDate": "(val)","instPropCreateUser": "(val)","signedBy": "(val)","submissionType": "(val)","devProposalNumber": "(val)","instProposalId": "(val)","_primaryKey": "(val)"},
              {"proposalAdminDetailId": "(val)","dateSubmittedByDept": "(val)","dateReturnedToDept": "(val)","dateApprovedByOsp": "(val)","dateSubmittedToAgency": "(val)","instPropCreateDate": "(val)","instPropCreateUser": "(val)","signedBy": "(val)","submissionType": "(val)","devProposalNumber": "(val)","instProposalId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Admin Details with Filtering [GET /instprop/api/v1/proposal-admin-details/]
    
+ Parameters

    + proposalAdminDetailId (optional) - 
    + dateSubmittedByDept (optional) - 
    + dateReturnedToDept (optional) - 
    + dateApprovedByOsp (optional) - 
    + dateSubmittedToAgency (optional) - 
    + instPropCreateDate (optional) - 
    + instPropCreateUser (optional) - 
    + signedBy (optional) - 
    + submissionType (optional) - 
    + devProposalNumber (optional) - 
    + instProposalId (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalAdminDetailId": "(val)","dateSubmittedByDept": "(val)","dateReturnedToDept": "(val)","dateApprovedByOsp": "(val)","dateSubmittedToAgency": "(val)","instPropCreateDate": "(val)","instPropCreateUser": "(val)","signedBy": "(val)","submissionType": "(val)","devProposalNumber": "(val)","instProposalId": "(val)","_primaryKey": "(val)"},
              {"proposalAdminDetailId": "(val)","dateSubmittedByDept": "(val)","dateReturnedToDept": "(val)","dateApprovedByOsp": "(val)","dateSubmittedToAgency": "(val)","instPropCreateDate": "(val)","instPropCreateUser": "(val)","signedBy": "(val)","submissionType": "(val)","devProposalNumber": "(val)","instProposalId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Admin Details [GET /instprop/api/v1/proposal-admin-details/]
	                                          
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
    
            {"columns":["proposalAdminDetailId","dateSubmittedByDept","dateReturnedToDept","dateApprovedByOsp","dateSubmittedToAgency","instPropCreateDate","instPropCreateUser","signedBy","submissionType","devProposalNumber","instProposalId"],"primaryKey":"proposalAdminDetailId"}
		
### Get Blueprint API specification for Proposal Admin Details [GET /instprop/api/v1/proposal-admin-details/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Admin Details.md"
            transfer-encoding:chunked
### Update Proposal Admin Details [PUT /instprop/api/v1/proposal-admin-details/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalAdminDetailId": "(val)","dateSubmittedByDept": "(val)","dateReturnedToDept": "(val)","dateApprovedByOsp": "(val)","dateSubmittedToAgency": "(val)","instPropCreateDate": "(val)","instPropCreateUser": "(val)","signedBy": "(val)","submissionType": "(val)","devProposalNumber": "(val)","instProposalId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Admin Details [PUT /instprop/api/v1/proposal-admin-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalAdminDetailId": "(val)","dateSubmittedByDept": "(val)","dateReturnedToDept": "(val)","dateApprovedByOsp": "(val)","dateSubmittedToAgency": "(val)","instPropCreateDate": "(val)","instPropCreateUser": "(val)","signedBy": "(val)","submissionType": "(val)","devProposalNumber": "(val)","instProposalId": "(val)","_primaryKey": "(val)"},
              {"proposalAdminDetailId": "(val)","dateSubmittedByDept": "(val)","dateReturnedToDept": "(val)","dateApprovedByOsp": "(val)","dateSubmittedToAgency": "(val)","instPropCreateDate": "(val)","instPropCreateUser": "(val)","signedBy": "(val)","submissionType": "(val)","devProposalNumber": "(val)","instProposalId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Proposal Admin Details [POST /instprop/api/v1/proposal-admin-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalAdminDetailId": "(val)","dateSubmittedByDept": "(val)","dateReturnedToDept": "(val)","dateApprovedByOsp": "(val)","dateSubmittedToAgency": "(val)","instPropCreateDate": "(val)","instPropCreateUser": "(val)","signedBy": "(val)","submissionType": "(val)","devProposalNumber": "(val)","instProposalId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalAdminDetailId": "(val)","dateSubmittedByDept": "(val)","dateReturnedToDept": "(val)","dateApprovedByOsp": "(val)","dateSubmittedToAgency": "(val)","instPropCreateDate": "(val)","instPropCreateUser": "(val)","signedBy": "(val)","submissionType": "(val)","devProposalNumber": "(val)","instProposalId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Admin Details [POST /instprop/api/v1/proposal-admin-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalAdminDetailId": "(val)","dateSubmittedByDept": "(val)","dateReturnedToDept": "(val)","dateApprovedByOsp": "(val)","dateSubmittedToAgency": "(val)","instPropCreateDate": "(val)","instPropCreateUser": "(val)","signedBy": "(val)","submissionType": "(val)","devProposalNumber": "(val)","instProposalId": "(val)","_primaryKey": "(val)"},
              {"proposalAdminDetailId": "(val)","dateSubmittedByDept": "(val)","dateReturnedToDept": "(val)","dateApprovedByOsp": "(val)","dateSubmittedToAgency": "(val)","instPropCreateDate": "(val)","instPropCreateUser": "(val)","signedBy": "(val)","submissionType": "(val)","devProposalNumber": "(val)","instProposalId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalAdminDetailId": "(val)","dateSubmittedByDept": "(val)","dateReturnedToDept": "(val)","dateApprovedByOsp": "(val)","dateSubmittedToAgency": "(val)","instPropCreateDate": "(val)","instPropCreateUser": "(val)","signedBy": "(val)","submissionType": "(val)","devProposalNumber": "(val)","instProposalId": "(val)","_primaryKey": "(val)"},
              {"proposalAdminDetailId": "(val)","dateSubmittedByDept": "(val)","dateReturnedToDept": "(val)","dateApprovedByOsp": "(val)","dateSubmittedToAgency": "(val)","instPropCreateDate": "(val)","instPropCreateUser": "(val)","signedBy": "(val)","submissionType": "(val)","devProposalNumber": "(val)","instProposalId": "(val)","_primaryKey": "(val)"}
            ]
### Delete Proposal Admin Details by Key [DELETE /instprop/api/v1/proposal-admin-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Admin Details [DELETE /instprop/api/v1/proposal-admin-details/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Admin Details with Matching [DELETE /instprop/api/v1/proposal-admin-details/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + proposalAdminDetailId (optional) - 
    + dateSubmittedByDept (optional) - 
    + dateReturnedToDept (optional) - 
    + dateApprovedByOsp (optional) - 
    + dateSubmittedToAgency (optional) - 
    + instPropCreateDate (optional) - 
    + instPropCreateUser (optional) - 
    + signedBy (optional) - 
    + submissionType (optional) - 
    + devProposalNumber (optional) - 
    + instProposalId (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
