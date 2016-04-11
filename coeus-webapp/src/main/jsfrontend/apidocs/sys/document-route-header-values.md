## Document Route Header Values [/research-sys/api/v1/document-route-header-values/]

### Get Document Route Header Values by Key [GET /research-sys/api/v1/document-route-header-values/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"documentId": "(val)","documentTypeId": "(val)","docRouteStatus": "(val)","docRouteLevel": "(val)","dateModified": "(val)","createDate": "(val)","approvedDate": "(val)","finalizedDate": "(val)","docTitle": "(val)","appDocId": "(val)","docVersion": "(val)","initiatorWorkflowId": "(val)","routedByUserWorkflowId": "(val)","routeStatusDate": "(val)","appDocStatus": "(val)","appDocStatusDate": "(val)","_primaryKey": "(val)"}

### Get All Document Route Header Values [GET /research-sys/api/v1/document-route-header-values/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"documentId": "(val)","documentTypeId": "(val)","docRouteStatus": "(val)","docRouteLevel": "(val)","dateModified": "(val)","createDate": "(val)","approvedDate": "(val)","finalizedDate": "(val)","docTitle": "(val)","appDocId": "(val)","docVersion": "(val)","initiatorWorkflowId": "(val)","routedByUserWorkflowId": "(val)","routeStatusDate": "(val)","appDocStatus": "(val)","appDocStatusDate": "(val)","_primaryKey": "(val)"},
              {"documentId": "(val)","documentTypeId": "(val)","docRouteStatus": "(val)","docRouteLevel": "(val)","dateModified": "(val)","createDate": "(val)","approvedDate": "(val)","finalizedDate": "(val)","docTitle": "(val)","appDocId": "(val)","docVersion": "(val)","initiatorWorkflowId": "(val)","routedByUserWorkflowId": "(val)","routeStatusDate": "(val)","appDocStatus": "(val)","appDocStatusDate": "(val)","_primaryKey": "(val)"}
            ]

### Get All Document Route Header Values with Filtering [GET /research-sys/api/v1/document-route-header-values/]
    
+ Parameters

        + documentId
            + documentTypeId
            + docRouteStatus
            + docRouteLevel
            + dateModified
            + createDate
            + approvedDate
            + finalizedDate
            + docTitle
            + appDocId
            + docVersion
            + initiatorWorkflowId
            + routedByUserWorkflowId
            + routeStatusDate
            + appDocStatus
            + appDocStatusDate

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"documentId": "(val)","documentTypeId": "(val)","docRouteStatus": "(val)","docRouteLevel": "(val)","dateModified": "(val)","createDate": "(val)","approvedDate": "(val)","finalizedDate": "(val)","docTitle": "(val)","appDocId": "(val)","docVersion": "(val)","initiatorWorkflowId": "(val)","routedByUserWorkflowId": "(val)","routeStatusDate": "(val)","appDocStatus": "(val)","appDocStatusDate": "(val)","_primaryKey": "(val)"},
              {"documentId": "(val)","documentTypeId": "(val)","docRouteStatus": "(val)","docRouteLevel": "(val)","dateModified": "(val)","createDate": "(val)","approvedDate": "(val)","finalizedDate": "(val)","docTitle": "(val)","appDocId": "(val)","docVersion": "(val)","initiatorWorkflowId": "(val)","routedByUserWorkflowId": "(val)","routeStatusDate": "(val)","appDocStatus": "(val)","appDocStatusDate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Document Route Header Values [GET /research-sys/api/v1/document-route-header-values/]
	                                          
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
    
            {"columns":["documentId","documentTypeId","docRouteStatus","docRouteLevel","dateModified","createDate","approvedDate","finalizedDate","docTitle","appDocId","docVersion","initiatorWorkflowId","routedByUserWorkflowId","routeStatusDate","appDocStatus","appDocStatusDate"],"primaryKey":"documentId"}
		
### Get Blueprint API specification for Document Route Header Values [GET /research-sys/api/v1/document-route-header-values/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Document Route Header Values.md"
            transfer-encoding:chunked


### Update Document Route Header Values [PUT /research-sys/api/v1/document-route-header-values/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentId": "(val)","documentTypeId": "(val)","docRouteStatus": "(val)","docRouteLevel": "(val)","dateModified": "(val)","createDate": "(val)","approvedDate": "(val)","finalizedDate": "(val)","docTitle": "(val)","appDocId": "(val)","docVersion": "(val)","initiatorWorkflowId": "(val)","routedByUserWorkflowId": "(val)","routeStatusDate": "(val)","appDocStatus": "(val)","appDocStatusDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Document Route Header Values [PUT /research-sys/api/v1/document-route-header-values/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"documentId": "(val)","documentTypeId": "(val)","docRouteStatus": "(val)","docRouteLevel": "(val)","dateModified": "(val)","createDate": "(val)","approvedDate": "(val)","finalizedDate": "(val)","docTitle": "(val)","appDocId": "(val)","docVersion": "(val)","initiatorWorkflowId": "(val)","routedByUserWorkflowId": "(val)","routeStatusDate": "(val)","appDocStatus": "(val)","appDocStatusDate": "(val)","_primaryKey": "(val)"},
              {"documentId": "(val)","documentTypeId": "(val)","docRouteStatus": "(val)","docRouteLevel": "(val)","dateModified": "(val)","createDate": "(val)","approvedDate": "(val)","finalizedDate": "(val)","docTitle": "(val)","appDocId": "(val)","docVersion": "(val)","initiatorWorkflowId": "(val)","routedByUserWorkflowId": "(val)","routeStatusDate": "(val)","appDocStatus": "(val)","appDocStatusDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Document Route Header Values [POST /research-sys/api/v1/document-route-header-values/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentId": "(val)","documentTypeId": "(val)","docRouteStatus": "(val)","docRouteLevel": "(val)","dateModified": "(val)","createDate": "(val)","approvedDate": "(val)","finalizedDate": "(val)","docTitle": "(val)","appDocId": "(val)","docVersion": "(val)","initiatorWorkflowId": "(val)","routedByUserWorkflowId": "(val)","routeStatusDate": "(val)","appDocStatus": "(val)","appDocStatusDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"documentId": "(val)","documentTypeId": "(val)","docRouteStatus": "(val)","docRouteLevel": "(val)","dateModified": "(val)","createDate": "(val)","approvedDate": "(val)","finalizedDate": "(val)","docTitle": "(val)","appDocId": "(val)","docVersion": "(val)","initiatorWorkflowId": "(val)","routedByUserWorkflowId": "(val)","routeStatusDate": "(val)","appDocStatus": "(val)","appDocStatusDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Document Route Header Values [POST /research-sys/api/v1/document-route-header-values/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"documentId": "(val)","documentTypeId": "(val)","docRouteStatus": "(val)","docRouteLevel": "(val)","dateModified": "(val)","createDate": "(val)","approvedDate": "(val)","finalizedDate": "(val)","docTitle": "(val)","appDocId": "(val)","docVersion": "(val)","initiatorWorkflowId": "(val)","routedByUserWorkflowId": "(val)","routeStatusDate": "(val)","appDocStatus": "(val)","appDocStatusDate": "(val)","_primaryKey": "(val)"},
              {"documentId": "(val)","documentTypeId": "(val)","docRouteStatus": "(val)","docRouteLevel": "(val)","dateModified": "(val)","createDate": "(val)","approvedDate": "(val)","finalizedDate": "(val)","docTitle": "(val)","appDocId": "(val)","docVersion": "(val)","initiatorWorkflowId": "(val)","routedByUserWorkflowId": "(val)","routeStatusDate": "(val)","appDocStatus": "(val)","appDocStatusDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"documentId": "(val)","documentTypeId": "(val)","docRouteStatus": "(val)","docRouteLevel": "(val)","dateModified": "(val)","createDate": "(val)","approvedDate": "(val)","finalizedDate": "(val)","docTitle": "(val)","appDocId": "(val)","docVersion": "(val)","initiatorWorkflowId": "(val)","routedByUserWorkflowId": "(val)","routeStatusDate": "(val)","appDocStatus": "(val)","appDocStatusDate": "(val)","_primaryKey": "(val)"},
              {"documentId": "(val)","documentTypeId": "(val)","docRouteStatus": "(val)","docRouteLevel": "(val)","dateModified": "(val)","createDate": "(val)","approvedDate": "(val)","finalizedDate": "(val)","docTitle": "(val)","appDocId": "(val)","docVersion": "(val)","initiatorWorkflowId": "(val)","routedByUserWorkflowId": "(val)","routeStatusDate": "(val)","appDocStatus": "(val)","appDocStatusDate": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Document Route Header Values by Key [DELETE /research-sys/api/v1/document-route-header-values/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Document Route Header Values [DELETE /research-sys/api/v1/document-route-header-values/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Document Route Header Values with Matching [DELETE /research-sys/api/v1/document-route-header-values/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + documentId
            + documentTypeId
            + docRouteStatus
            + docRouteLevel
            + dateModified
            + createDate
            + approvedDate
            + finalizedDate
            + docTitle
            + appDocId
            + docVersion
            + initiatorWorkflowId
            + routedByUserWorkflowId
            + routeStatusDate
            + appDocStatus
            + appDocStatusDate

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
