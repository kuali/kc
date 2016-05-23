## Document Types [/research-sys/api/v1/document-types/]

### Get Document Types by Key [GET /research-sys/api/v1/document-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"documentTypeId": "(val)","docTypeParentId": "(val)","name": "(val)","version": "(val)","active": "(val)","currentInd": "(val)","description": "(val)","label": "(val)","previousVersionId": "(val)","documentId": "(val)","unresolvedHelpDefinitionUrl": "(val)","unresolvedDocSearchHelpUrl": "(val)","unresolvedDocHandlerUrl": "(val)","postProcessorName": "(val)","workgroupId": "(val)","blanketApproveWorkgroupId": "(val)","blanketApprovePolicy": "(val)","reportingWorkgroupId": "(val)","actualApplicationId": "(val)","authorizer": "(val)","routingVersion": "(val)","actualNotificationFromAddress": "(val)","documentTypeSecurityXml": "(val)","customEmailStylesheet": "(val)","_primaryKey": "(val)"}

### Get All Document Types [GET /research-sys/api/v1/document-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"documentTypeId": "(val)","docTypeParentId": "(val)","name": "(val)","version": "(val)","active": "(val)","currentInd": "(val)","description": "(val)","label": "(val)","previousVersionId": "(val)","documentId": "(val)","unresolvedHelpDefinitionUrl": "(val)","unresolvedDocSearchHelpUrl": "(val)","unresolvedDocHandlerUrl": "(val)","postProcessorName": "(val)","workgroupId": "(val)","blanketApproveWorkgroupId": "(val)","blanketApprovePolicy": "(val)","reportingWorkgroupId": "(val)","actualApplicationId": "(val)","authorizer": "(val)","routingVersion": "(val)","actualNotificationFromAddress": "(val)","documentTypeSecurityXml": "(val)","customEmailStylesheet": "(val)","_primaryKey": "(val)"},
              {"documentTypeId": "(val)","docTypeParentId": "(val)","name": "(val)","version": "(val)","active": "(val)","currentInd": "(val)","description": "(val)","label": "(val)","previousVersionId": "(val)","documentId": "(val)","unresolvedHelpDefinitionUrl": "(val)","unresolvedDocSearchHelpUrl": "(val)","unresolvedDocHandlerUrl": "(val)","postProcessorName": "(val)","workgroupId": "(val)","blanketApproveWorkgroupId": "(val)","blanketApprovePolicy": "(val)","reportingWorkgroupId": "(val)","actualApplicationId": "(val)","authorizer": "(val)","routingVersion": "(val)","actualNotificationFromAddress": "(val)","documentTypeSecurityXml": "(val)","customEmailStylesheet": "(val)","_primaryKey": "(val)"}
            ]

### Get All Document Types with Filtering [GET /research-sys/api/v1/document-types/]
    
+ Parameters

    + documentTypeId (optional) - Id. Maximum length is 19.
    + docTypeParentId (optional) - Parent Id. Maximum length is 19.
    + name (optional) - Name. Maximum length is 64.
    + version (optional) - Version.
    + active (optional) - Active Indicator. Maximum length is 1.
    + currentInd (optional) - Current Ind.
    + description (optional) - Description. Maximum length is 4000.
    + label (optional) - Label. Maximum length is 128.
    + previousVersionId (optional) - Previous Version Id.
    + documentId (optional) - Document Id.
    + unresolvedHelpDefinitionUrl (optional) - Help Definition URL. Maximum length is 255.
    + unresolvedDocSearchHelpUrl (optional) - Document Search Help URL. Maximum length is 255.
    + unresolvedDocHandlerUrl (optional) - Document Handler URL. Maximum length is 255.
    + postProcessorName (optional) - Post Processor Name.
    + workgroupId (optional) - Workgroup Id.
    + blanketApproveWorkgroupId (optional) - Blanket Approve Workgroup Id.
    + blanketApprovePolicy (optional) - Blanket Approve Policy.
    + reportingWorkgroupId (optional) - Reporting Workgroup Id.
    + actualApplicationId (optional) - Actual Application Id.
    + authorizer (optional) - Authorizer.
    + routingVersion (optional) - Routing Version.
    + actualNotificationFromAddress (optional) - Notification From Address. Maximum length is 255.
    + documentTypeSecurityXml (optional) - Document Type Security Xml.
    + customEmailStylesheet (optional) - Custom Email Stylesheet.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"documentTypeId": "(val)","docTypeParentId": "(val)","name": "(val)","version": "(val)","active": "(val)","currentInd": "(val)","description": "(val)","label": "(val)","previousVersionId": "(val)","documentId": "(val)","unresolvedHelpDefinitionUrl": "(val)","unresolvedDocSearchHelpUrl": "(val)","unresolvedDocHandlerUrl": "(val)","postProcessorName": "(val)","workgroupId": "(val)","blanketApproveWorkgroupId": "(val)","blanketApprovePolicy": "(val)","reportingWorkgroupId": "(val)","actualApplicationId": "(val)","authorizer": "(val)","routingVersion": "(val)","actualNotificationFromAddress": "(val)","documentTypeSecurityXml": "(val)","customEmailStylesheet": "(val)","_primaryKey": "(val)"},
              {"documentTypeId": "(val)","docTypeParentId": "(val)","name": "(val)","version": "(val)","active": "(val)","currentInd": "(val)","description": "(val)","label": "(val)","previousVersionId": "(val)","documentId": "(val)","unresolvedHelpDefinitionUrl": "(val)","unresolvedDocSearchHelpUrl": "(val)","unresolvedDocHandlerUrl": "(val)","postProcessorName": "(val)","workgroupId": "(val)","blanketApproveWorkgroupId": "(val)","blanketApprovePolicy": "(val)","reportingWorkgroupId": "(val)","actualApplicationId": "(val)","authorizer": "(val)","routingVersion": "(val)","actualNotificationFromAddress": "(val)","documentTypeSecurityXml": "(val)","customEmailStylesheet": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Document Types [GET /research-sys/api/v1/document-types/]
	                                          
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
    
            {"columns":["documentTypeId","docTypeParentId","name","version","active","currentInd","description","label","previousVersionId","documentId","unresolvedHelpDefinitionUrl","unresolvedDocSearchHelpUrl","unresolvedDocHandlerUrl","postProcessorName","workgroupId","blanketApproveWorkgroupId","blanketApprovePolicy","reportingWorkgroupId","actualApplicationId","authorizer","routingVersion","actualNotificationFromAddress","documentTypeSecurityXml","customEmailStylesheet"],"primaryKey":"documentTypeId"}
		
### Get Blueprint API specification for Document Types [GET /research-sys/api/v1/document-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Document Types.md"
            transfer-encoding:chunked
### Update Document Types [PUT /research-sys/api/v1/document-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentTypeId": "(val)","docTypeParentId": "(val)","name": "(val)","version": "(val)","active": "(val)","currentInd": "(val)","description": "(val)","label": "(val)","previousVersionId": "(val)","documentId": "(val)","unresolvedHelpDefinitionUrl": "(val)","unresolvedDocSearchHelpUrl": "(val)","unresolvedDocHandlerUrl": "(val)","postProcessorName": "(val)","workgroupId": "(val)","blanketApproveWorkgroupId": "(val)","blanketApprovePolicy": "(val)","reportingWorkgroupId": "(val)","actualApplicationId": "(val)","authorizer": "(val)","routingVersion": "(val)","actualNotificationFromAddress": "(val)","documentTypeSecurityXml": "(val)","customEmailStylesheet": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Document Types [PUT /research-sys/api/v1/document-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"documentTypeId": "(val)","docTypeParentId": "(val)","name": "(val)","version": "(val)","active": "(val)","currentInd": "(val)","description": "(val)","label": "(val)","previousVersionId": "(val)","documentId": "(val)","unresolvedHelpDefinitionUrl": "(val)","unresolvedDocSearchHelpUrl": "(val)","unresolvedDocHandlerUrl": "(val)","postProcessorName": "(val)","workgroupId": "(val)","blanketApproveWorkgroupId": "(val)","blanketApprovePolicy": "(val)","reportingWorkgroupId": "(val)","actualApplicationId": "(val)","authorizer": "(val)","routingVersion": "(val)","actualNotificationFromAddress": "(val)","documentTypeSecurityXml": "(val)","customEmailStylesheet": "(val)","_primaryKey": "(val)"},
              {"documentTypeId": "(val)","docTypeParentId": "(val)","name": "(val)","version": "(val)","active": "(val)","currentInd": "(val)","description": "(val)","label": "(val)","previousVersionId": "(val)","documentId": "(val)","unresolvedHelpDefinitionUrl": "(val)","unresolvedDocSearchHelpUrl": "(val)","unresolvedDocHandlerUrl": "(val)","postProcessorName": "(val)","workgroupId": "(val)","blanketApproveWorkgroupId": "(val)","blanketApprovePolicy": "(val)","reportingWorkgroupId": "(val)","actualApplicationId": "(val)","authorizer": "(val)","routingVersion": "(val)","actualNotificationFromAddress": "(val)","documentTypeSecurityXml": "(val)","customEmailStylesheet": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Document Types [POST /research-sys/api/v1/document-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentTypeId": "(val)","docTypeParentId": "(val)","name": "(val)","version": "(val)","active": "(val)","currentInd": "(val)","description": "(val)","label": "(val)","previousVersionId": "(val)","documentId": "(val)","unresolvedHelpDefinitionUrl": "(val)","unresolvedDocSearchHelpUrl": "(val)","unresolvedDocHandlerUrl": "(val)","postProcessorName": "(val)","workgroupId": "(val)","blanketApproveWorkgroupId": "(val)","blanketApprovePolicy": "(val)","reportingWorkgroupId": "(val)","actualApplicationId": "(val)","authorizer": "(val)","routingVersion": "(val)","actualNotificationFromAddress": "(val)","documentTypeSecurityXml": "(val)","customEmailStylesheet": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"documentTypeId": "(val)","docTypeParentId": "(val)","name": "(val)","version": "(val)","active": "(val)","currentInd": "(val)","description": "(val)","label": "(val)","previousVersionId": "(val)","documentId": "(val)","unresolvedHelpDefinitionUrl": "(val)","unresolvedDocSearchHelpUrl": "(val)","unresolvedDocHandlerUrl": "(val)","postProcessorName": "(val)","workgroupId": "(val)","blanketApproveWorkgroupId": "(val)","blanketApprovePolicy": "(val)","reportingWorkgroupId": "(val)","actualApplicationId": "(val)","authorizer": "(val)","routingVersion": "(val)","actualNotificationFromAddress": "(val)","documentTypeSecurityXml": "(val)","customEmailStylesheet": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Document Types [POST /research-sys/api/v1/document-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"documentTypeId": "(val)","docTypeParentId": "(val)","name": "(val)","version": "(val)","active": "(val)","currentInd": "(val)","description": "(val)","label": "(val)","previousVersionId": "(val)","documentId": "(val)","unresolvedHelpDefinitionUrl": "(val)","unresolvedDocSearchHelpUrl": "(val)","unresolvedDocHandlerUrl": "(val)","postProcessorName": "(val)","workgroupId": "(val)","blanketApproveWorkgroupId": "(val)","blanketApprovePolicy": "(val)","reportingWorkgroupId": "(val)","actualApplicationId": "(val)","authorizer": "(val)","routingVersion": "(val)","actualNotificationFromAddress": "(val)","documentTypeSecurityXml": "(val)","customEmailStylesheet": "(val)","_primaryKey": "(val)"},
              {"documentTypeId": "(val)","docTypeParentId": "(val)","name": "(val)","version": "(val)","active": "(val)","currentInd": "(val)","description": "(val)","label": "(val)","previousVersionId": "(val)","documentId": "(val)","unresolvedHelpDefinitionUrl": "(val)","unresolvedDocSearchHelpUrl": "(val)","unresolvedDocHandlerUrl": "(val)","postProcessorName": "(val)","workgroupId": "(val)","blanketApproveWorkgroupId": "(val)","blanketApprovePolicy": "(val)","reportingWorkgroupId": "(val)","actualApplicationId": "(val)","authorizer": "(val)","routingVersion": "(val)","actualNotificationFromAddress": "(val)","documentTypeSecurityXml": "(val)","customEmailStylesheet": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"documentTypeId": "(val)","docTypeParentId": "(val)","name": "(val)","version": "(val)","active": "(val)","currentInd": "(val)","description": "(val)","label": "(val)","previousVersionId": "(val)","documentId": "(val)","unresolvedHelpDefinitionUrl": "(val)","unresolvedDocSearchHelpUrl": "(val)","unresolvedDocHandlerUrl": "(val)","postProcessorName": "(val)","workgroupId": "(val)","blanketApproveWorkgroupId": "(val)","blanketApprovePolicy": "(val)","reportingWorkgroupId": "(val)","actualApplicationId": "(val)","authorizer": "(val)","routingVersion": "(val)","actualNotificationFromAddress": "(val)","documentTypeSecurityXml": "(val)","customEmailStylesheet": "(val)","_primaryKey": "(val)"},
              {"documentTypeId": "(val)","docTypeParentId": "(val)","name": "(val)","version": "(val)","active": "(val)","currentInd": "(val)","description": "(val)","label": "(val)","previousVersionId": "(val)","documentId": "(val)","unresolvedHelpDefinitionUrl": "(val)","unresolvedDocSearchHelpUrl": "(val)","unresolvedDocHandlerUrl": "(val)","postProcessorName": "(val)","workgroupId": "(val)","blanketApproveWorkgroupId": "(val)","blanketApprovePolicy": "(val)","reportingWorkgroupId": "(val)","actualApplicationId": "(val)","authorizer": "(val)","routingVersion": "(val)","actualNotificationFromAddress": "(val)","documentTypeSecurityXml": "(val)","customEmailStylesheet": "(val)","_primaryKey": "(val)"}
            ]
### Delete Document Types by Key [DELETE /research-sys/api/v1/document-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Document Types [DELETE /research-sys/api/v1/document-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Document Types with Matching [DELETE /research-sys/api/v1/document-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + documentTypeId (optional) - Id. Maximum length is 19.
    + docTypeParentId (optional) - Parent Id. Maximum length is 19.
    + name (optional) - Name. Maximum length is 64.
    + version (optional) - Version.
    + active (optional) - Active Indicator. Maximum length is 1.
    + currentInd (optional) - Current Ind.
    + description (optional) - Description. Maximum length is 4000.
    + label (optional) - Label. Maximum length is 128.
    + previousVersionId (optional) - Previous Version Id.
    + documentId (optional) - Document Id.
    + unresolvedHelpDefinitionUrl (optional) - Help Definition URL. Maximum length is 255.
    + unresolvedDocSearchHelpUrl (optional) - Document Search Help URL. Maximum length is 255.
    + unresolvedDocHandlerUrl (optional) - Document Handler URL. Maximum length is 255.
    + postProcessorName (optional) - Post Processor Name.
    + workgroupId (optional) - Workgroup Id.
    + blanketApproveWorkgroupId (optional) - Blanket Approve Workgroup Id.
    + blanketApprovePolicy (optional) - Blanket Approve Policy.
    + reportingWorkgroupId (optional) - Reporting Workgroup Id.
    + actualApplicationId (optional) - Actual Application Id.
    + authorizer (optional) - Authorizer.
    + routingVersion (optional) - Routing Version.
    + actualNotificationFromAddress (optional) - Notification From Address. Maximum length is 255.
    + documentTypeSecurityXml (optional) - Document Type Security Xml.
    + customEmailStylesheet (optional) - Custom Email Stylesheet.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
