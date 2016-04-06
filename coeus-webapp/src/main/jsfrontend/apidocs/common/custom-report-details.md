## Custom Report Details [/research-sys/api/v1/custom-report-details/]

### Get Custom Report Details by Key [GET /research-sys/api/v1/custom-report-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"reportId": "(val)","reportLabel": "(val)","reportDescription": "(val)","reportTypeCode": "(val)","permissionName": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}

### Get All Custom Report Details [GET /research-sys/api/v1/custom-report-details/]
	 
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

### Get All Custom Report Details with Filtering [GET /research-sys/api/v1/custom-report-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + reportId
            + reportLabel
            + reportDescription
            + reportTypeCode
            + permissionName
            + attachmentContent
            + fileName
            + contentType
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"reportId": "(val)","reportLabel": "(val)","reportDescription": "(val)","reportTypeCode": "(val)","permissionName": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"},
              {"reportId": "(val)","reportLabel": "(val)","reportDescription": "(val)","reportTypeCode": "(val)","permissionName": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Custom Report Details [GET /research-sys/api/v1/custom-report-details/]
	 
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
		
### Get Blueprint API specification for Custom Report Details [GET /research-sys/api/v1/custom-report-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Custom Report Details.md"
            transfer-encoding:chunked


### Update Custom Report Details [PUT /research-sys/api/v1/custom-report-details/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reportId": "(val)","reportLabel": "(val)","reportDescription": "(val)","reportTypeCode": "(val)","permissionName": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Custom Report Details [PUT /research-sys/api/v1/custom-report-details/]

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

### Insert Custom Report Details [POST /research-sys/api/v1/custom-report-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reportId": "(val)","reportLabel": "(val)","reportDescription": "(val)","reportTypeCode": "(val)","permissionName": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"reportId": "(val)","reportLabel": "(val)","reportDescription": "(val)","reportTypeCode": "(val)","permissionName": "(val)","attachmentContent": "(val)","fileName": "(val)","contentType": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Custom Report Details [POST /research-sys/api/v1/custom-report-details/]

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
            
### Delete Custom Report Details by Key [DELETE /research-sys/api/v1/custom-report-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Custom Report Details [DELETE /research-sys/api/v1/custom-report-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Custom Report Details with Matching [DELETE /research-sys/api/v1/custom-report-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + reportId
            + reportLabel
            + reportDescription
            + reportTypeCode
            + permissionName
            + attachmentContent
            + fileName
            + contentType


+ Response 204
