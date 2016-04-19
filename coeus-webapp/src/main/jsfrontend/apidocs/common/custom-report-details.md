## Custom Report Details [/research-common/api/v1/custom-report-details/]

### Get Custom Report Details by Key [GET /research-common/api/v1/custom-report-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"reportId": "(val)","reportLabel": "(val)","reportDescription": "(val)","reportTypeCode": "(val)","permissionName": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}

### Get All Custom Report Details [GET /research-common/api/v1/custom-report-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"reportId": "(val)","reportLabel": "(val)","reportDescription": "(val)","reportTypeCode": "(val)","permissionName": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"},
              {"reportId": "(val)","reportLabel": "(val)","reportDescription": "(val)","reportTypeCode": "(val)","permissionName": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            ]

### Get All Custom Report Details with Filtering [GET /research-common/api/v1/custom-report-details/]
    
+ Parameters

    + reportId (optional) - Report Id. Maximum length is 6.
    + reportLabel (optional) - Report Label. Maximum length is 50.
    + reportDescription (optional) - Report Description. Maximum length is 50.
    + reportTypeCode (optional) - Report Type Code. Maximum length is 3.
    + permissionName (optional) - Permission Name. Maximum length is 30.
    + attachmentContent (optional) - 
    + fileName (optional) - 
    + contentType (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"reportId": "(val)","reportLabel": "(val)","reportDescription": "(val)","reportTypeCode": "(val)","permissionName": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"},
              {"reportId": "(val)","reportLabel": "(val)","reportDescription": "(val)","reportTypeCode": "(val)","permissionName": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Custom Report Details [GET /research-common/api/v1/custom-report-details/]
	                                          
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
    
            {"columns":["reportId","reportLabel","reportDescription","reportTypeCode","permissionName","attachmentContent","fileName","contentType"],"primaryKey":"reportId"}
		
### Get Blueprint API specification for Custom Report Details [GET /research-common/api/v1/custom-report-details/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Custom Report Details.md"
            transfer-encoding:chunked


### Update Custom Report Details [PUT /research-common/api/v1/custom-report-details/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reportId": "(val)","reportLabel": "(val)","reportDescription": "(val)","reportTypeCode": "(val)","permissionName": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Custom Report Details [PUT /research-common/api/v1/custom-report-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"reportId": "(val)","reportLabel": "(val)","reportDescription": "(val)","reportTypeCode": "(val)","permissionName": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"},
              {"reportId": "(val)","reportLabel": "(val)","reportDescription": "(val)","reportTypeCode": "(val)","permissionName": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Custom Report Details [POST /research-common/api/v1/custom-report-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reportId": "(val)","reportLabel": "(val)","reportDescription": "(val)","reportTypeCode": "(val)","permissionName": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"reportId": "(val)","reportLabel": "(val)","reportDescription": "(val)","reportTypeCode": "(val)","permissionName": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Custom Report Details [POST /research-common/api/v1/custom-report-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"reportId": "(val)","reportLabel": "(val)","reportDescription": "(val)","reportTypeCode": "(val)","permissionName": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"},
              {"reportId": "(val)","reportLabel": "(val)","reportDescription": "(val)","reportTypeCode": "(val)","permissionName": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"reportId": "(val)","reportLabel": "(val)","reportDescription": "(val)","reportTypeCode": "(val)","permissionName": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"},
              {"reportId": "(val)","reportLabel": "(val)","reportDescription": "(val)","reportTypeCode": "(val)","permissionName": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Custom Report Details by Key [DELETE /research-common/api/v1/custom-report-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Custom Report Details [DELETE /research-common/api/v1/custom-report-details/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Custom Report Details with Matching [DELETE /research-common/api/v1/custom-report-details/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + reportId (optional) - Report Id. Maximum length is 6.
    + reportLabel (optional) - Report Label. Maximum length is 50.
    + reportDescription (optional) - Report Description. Maximum length is 50.
    + reportTypeCode (optional) - Report Type Code. Maximum length is 3.
    + permissionName (optional) - Permission Name. Maximum length is 30.
    + attachmentContent (optional) - 
    + fileName (optional) - 
    + contentType (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
