## Budget Sub Awards [/propdev/api/v1/budget-sub-awards/]

### Get Budget Sub Awards by Key [GET /propdev/api/v1/budget-sub-awards/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetId": "(val)","subAwardNumber": "(val)","comments": "(val)","organizationId": "(val)","subAwardStatusCode": "(val)","fileDataId": "(val)","subAwardXfdFileName": "(val)","xmlDataId": "(val)","translationComments": "(val)","xfdUpdateTimestamp": "(val)","xfdUpdateUser": "(val)","xmlUpdateTimestamp": "(val)","xmlUpdateUser": "(val)","namespace": "(val)","formName": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}

### Get All Budget Sub Awards [GET /propdev/api/v1/budget-sub-awards/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetId": "(val)","subAwardNumber": "(val)","comments": "(val)","organizationId": "(val)","subAwardStatusCode": "(val)","fileDataId": "(val)","subAwardXfdFileName": "(val)","xmlDataId": "(val)","translationComments": "(val)","xfdUpdateTimestamp": "(val)","xfdUpdateUser": "(val)","xmlUpdateTimestamp": "(val)","xmlUpdateUser": "(val)","namespace": "(val)","formName": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"budgetId": "(val)","subAwardNumber": "(val)","comments": "(val)","organizationId": "(val)","subAwardStatusCode": "(val)","fileDataId": "(val)","subAwardXfdFileName": "(val)","xmlDataId": "(val)","translationComments": "(val)","xfdUpdateTimestamp": "(val)","xfdUpdateUser": "(val)","xmlUpdateTimestamp": "(val)","xmlUpdateUser": "(val)","namespace": "(val)","formName": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budget Sub Awards with Filtering [GET /propdev/api/v1/budget-sub-awards/]
    
+ Parameters

    + budgetId (optional) - Budget Id.
    + subAwardNumber (optional) - subAwardNumber. Maximum length is 3.
    + comments (optional) - Comments. Maximum length is 2000.
    + organizationId (optional) - Organization Id. Maximum length is 8.
    + subAwardStatusCode (optional) - Subaward Status Code. Maximum length is 3.
    + fileDataId (optional) - File Data Id.
    + subAwardXfdFileName (optional) - Subaward PDF File Name. Maximum length is 256.
    + xmlDataId (optional) - Xml Data Id.
    + translationComments (optional) - Translation Comments. Maximum length is 2000.
    + xfdUpdateTimestamp (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 21.
    + xfdUpdateUser (optional) - xfdUpdateUser. Maximum length is 60.
    + xmlUpdateTimestamp (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 21.
    + xmlUpdateUser (optional) - xmlUpdateUser. Maximum length is 60.
    + namespace (optional) - Namespace. Maximum length is 200.
    + formName (optional) - Form Name. Maximum length is 100.
    + hierarchyProposalNumber (optional) - Hierarchy Proposal Number.
    + hiddenInHierarchy (optional) - Hidden In Hierarchy.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetId": "(val)","subAwardNumber": "(val)","comments": "(val)","organizationId": "(val)","subAwardStatusCode": "(val)","fileDataId": "(val)","subAwardXfdFileName": "(val)","xmlDataId": "(val)","translationComments": "(val)","xfdUpdateTimestamp": "(val)","xfdUpdateUser": "(val)","xmlUpdateTimestamp": "(val)","xmlUpdateUser": "(val)","namespace": "(val)","formName": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"budgetId": "(val)","subAwardNumber": "(val)","comments": "(val)","organizationId": "(val)","subAwardStatusCode": "(val)","fileDataId": "(val)","subAwardXfdFileName": "(val)","xmlDataId": "(val)","translationComments": "(val)","xfdUpdateTimestamp": "(val)","xfdUpdateUser": "(val)","xmlUpdateTimestamp": "(val)","xmlUpdateUser": "(val)","namespace": "(val)","formName": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Sub Awards [GET /propdev/api/v1/budget-sub-awards/]
	                                          
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
    
            {"columns":["budgetId","subAwardNumber","comments","organizationId","subAwardStatusCode","fileDataId","subAwardXfdFileName","xmlDataId","translationComments","xfdUpdateTimestamp","xfdUpdateUser","xmlUpdateTimestamp","xmlUpdateUser","namespace","formName","hierarchyProposalNumber","hiddenInHierarchy"],"primaryKey":"budget:subAwardNumber"}
		
### Get Blueprint API specification for Budget Sub Awards [GET /propdev/api/v1/budget-sub-awards/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Sub Awards.md"
            transfer-encoding:chunked


### Update Budget Sub Awards [PUT /propdev/api/v1/budget-sub-awards/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetId": "(val)","subAwardNumber": "(val)","comments": "(val)","organizationId": "(val)","subAwardStatusCode": "(val)","fileDataId": "(val)","subAwardXfdFileName": "(val)","xmlDataId": "(val)","translationComments": "(val)","xfdUpdateTimestamp": "(val)","xfdUpdateUser": "(val)","xmlUpdateTimestamp": "(val)","xmlUpdateUser": "(val)","namespace": "(val)","formName": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Sub Awards [PUT /propdev/api/v1/budget-sub-awards/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetId": "(val)","subAwardNumber": "(val)","comments": "(val)","organizationId": "(val)","subAwardStatusCode": "(val)","fileDataId": "(val)","subAwardXfdFileName": "(val)","xmlDataId": "(val)","translationComments": "(val)","xfdUpdateTimestamp": "(val)","xfdUpdateUser": "(val)","xmlUpdateTimestamp": "(val)","xmlUpdateUser": "(val)","namespace": "(val)","formName": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"budgetId": "(val)","subAwardNumber": "(val)","comments": "(val)","organizationId": "(val)","subAwardStatusCode": "(val)","fileDataId": "(val)","subAwardXfdFileName": "(val)","xmlDataId": "(val)","translationComments": "(val)","xfdUpdateTimestamp": "(val)","xfdUpdateUser": "(val)","xmlUpdateTimestamp": "(val)","xmlUpdateUser": "(val)","namespace": "(val)","formName": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Budget Sub Awards [POST /propdev/api/v1/budget-sub-awards/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetId": "(val)","subAwardNumber": "(val)","comments": "(val)","organizationId": "(val)","subAwardStatusCode": "(val)","fileDataId": "(val)","subAwardXfdFileName": "(val)","xmlDataId": "(val)","translationComments": "(val)","xfdUpdateTimestamp": "(val)","xfdUpdateUser": "(val)","xmlUpdateTimestamp": "(val)","xmlUpdateUser": "(val)","namespace": "(val)","formName": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetId": "(val)","subAwardNumber": "(val)","comments": "(val)","organizationId": "(val)","subAwardStatusCode": "(val)","fileDataId": "(val)","subAwardXfdFileName": "(val)","xmlDataId": "(val)","translationComments": "(val)","xfdUpdateTimestamp": "(val)","xfdUpdateUser": "(val)","xmlUpdateTimestamp": "(val)","xmlUpdateUser": "(val)","namespace": "(val)","formName": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Sub Awards [POST /propdev/api/v1/budget-sub-awards/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetId": "(val)","subAwardNumber": "(val)","comments": "(val)","organizationId": "(val)","subAwardStatusCode": "(val)","fileDataId": "(val)","subAwardXfdFileName": "(val)","xmlDataId": "(val)","translationComments": "(val)","xfdUpdateTimestamp": "(val)","xfdUpdateUser": "(val)","xmlUpdateTimestamp": "(val)","xmlUpdateUser": "(val)","namespace": "(val)","formName": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"budgetId": "(val)","subAwardNumber": "(val)","comments": "(val)","organizationId": "(val)","subAwardStatusCode": "(val)","fileDataId": "(val)","subAwardXfdFileName": "(val)","xmlDataId": "(val)","translationComments": "(val)","xfdUpdateTimestamp": "(val)","xfdUpdateUser": "(val)","xmlUpdateTimestamp": "(val)","xmlUpdateUser": "(val)","namespace": "(val)","formName": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"budgetId": "(val)","subAwardNumber": "(val)","comments": "(val)","organizationId": "(val)","subAwardStatusCode": "(val)","fileDataId": "(val)","subAwardXfdFileName": "(val)","xmlDataId": "(val)","translationComments": "(val)","xfdUpdateTimestamp": "(val)","xfdUpdateUser": "(val)","xmlUpdateTimestamp": "(val)","xmlUpdateUser": "(val)","namespace": "(val)","formName": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"budgetId": "(val)","subAwardNumber": "(val)","comments": "(val)","organizationId": "(val)","subAwardStatusCode": "(val)","fileDataId": "(val)","subAwardXfdFileName": "(val)","xmlDataId": "(val)","translationComments": "(val)","xfdUpdateTimestamp": "(val)","xfdUpdateUser": "(val)","xmlUpdateTimestamp": "(val)","xmlUpdateUser": "(val)","namespace": "(val)","formName": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Budget Sub Awards by Key [DELETE /propdev/api/v1/budget-sub-awards/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Sub Awards [DELETE /propdev/api/v1/budget-sub-awards/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Sub Awards with Matching [DELETE /propdev/api/v1/budget-sub-awards/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + budgetId (optional) - Budget Id.
    + subAwardNumber (optional) - subAwardNumber. Maximum length is 3.
    + comments (optional) - Comments. Maximum length is 2000.
    + organizationId (optional) - Organization Id. Maximum length is 8.
    + subAwardStatusCode (optional) - Subaward Status Code. Maximum length is 3.
    + fileDataId (optional) - File Data Id.
    + subAwardXfdFileName (optional) - Subaward PDF File Name. Maximum length is 256.
    + xmlDataId (optional) - Xml Data Id.
    + translationComments (optional) - Translation Comments. Maximum length is 2000.
    + xfdUpdateTimestamp (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 21.
    + xfdUpdateUser (optional) - xfdUpdateUser. Maximum length is 60.
    + xmlUpdateTimestamp (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 21.
    + xmlUpdateUser (optional) - xmlUpdateUser. Maximum length is 60.
    + namespace (optional) - Namespace. Maximum length is 200.
    + formName (optional) - Form Name. Maximum length is 100.
    + hierarchyProposalNumber (optional) - Hierarchy Proposal Number.
    + hiddenInHierarchy (optional) - Hidden In Hierarchy.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
