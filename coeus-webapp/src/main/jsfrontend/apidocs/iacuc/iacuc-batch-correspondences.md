## Iacuc Batch Correspondences [/iacuc/api/v1/iacuc-batch-correspondences/]

### Get Iacuc Batch Correspondences by Key [GET /iacuc/api/v1/iacuc-batch-correspondences/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"batchCorrespondenceTypeCode": "(val)","description": "(val)","daysToEventUiText": "(val)","sendCorrespondence": "(val)","finalActionDay": "(val)","finalActionTypeCode": "(val)","finalActionCorrespType": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Batch Correspondences [GET /iacuc/api/v1/iacuc-batch-correspondences/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"batchCorrespondenceTypeCode": "(val)","description": "(val)","daysToEventUiText": "(val)","sendCorrespondence": "(val)","finalActionDay": "(val)","finalActionTypeCode": "(val)","finalActionCorrespType": "(val)","_primaryKey": "(val)"},
              {"batchCorrespondenceTypeCode": "(val)","description": "(val)","daysToEventUiText": "(val)","sendCorrespondence": "(val)","finalActionDay": "(val)","finalActionTypeCode": "(val)","finalActionCorrespType": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Batch Correspondences with Filtering [GET /iacuc/api/v1/iacuc-batch-correspondences/]
    
+ Parameters

    + batchCorrespondenceTypeCode (optional) - Batch Correspondence Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + daysToEventUiText (optional) - Days to Event UI Text. Maximum length is 400.
    + sendCorrespondence (optional) - Send correspondence before/after event. Maximum length is 10.
    + finalActionDay (optional) - Final Action Day. Maximum length is 3.
    + finalActionTypeCode (optional) - Final Action Type Code. Maximum length is 3.
    + finalActionCorrespType (optional) - Final Action Corresp Type. Maximum length is 3.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"batchCorrespondenceTypeCode": "(val)","description": "(val)","daysToEventUiText": "(val)","sendCorrespondence": "(val)","finalActionDay": "(val)","finalActionTypeCode": "(val)","finalActionCorrespType": "(val)","_primaryKey": "(val)"},
              {"batchCorrespondenceTypeCode": "(val)","description": "(val)","daysToEventUiText": "(val)","sendCorrespondence": "(val)","finalActionDay": "(val)","finalActionTypeCode": "(val)","finalActionCorrespType": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Batch Correspondences [GET /iacuc/api/v1/iacuc-batch-correspondences/]
	                                          
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
    
            {"columns":["batchCorrespondenceTypeCode","description","daysToEventUiText","sendCorrespondence","finalActionDay","finalActionTypeCode","finalActionCorrespType"],"primaryKey":"batchCorrespondenceTypeCode"}
		
### Get Blueprint API specification for Iacuc Batch Correspondences [GET /iacuc/api/v1/iacuc-batch-correspondences/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Batch Correspondences.md"
            transfer-encoding:chunked
### Update Iacuc Batch Correspondences [PUT /iacuc/api/v1/iacuc-batch-correspondences/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"batchCorrespondenceTypeCode": "(val)","description": "(val)","daysToEventUiText": "(val)","sendCorrespondence": "(val)","finalActionDay": "(val)","finalActionTypeCode": "(val)","finalActionCorrespType": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Batch Correspondences [PUT /iacuc/api/v1/iacuc-batch-correspondences/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"batchCorrespondenceTypeCode": "(val)","description": "(val)","daysToEventUiText": "(val)","sendCorrespondence": "(val)","finalActionDay": "(val)","finalActionTypeCode": "(val)","finalActionCorrespType": "(val)","_primaryKey": "(val)"},
              {"batchCorrespondenceTypeCode": "(val)","description": "(val)","daysToEventUiText": "(val)","sendCorrespondence": "(val)","finalActionDay": "(val)","finalActionTypeCode": "(val)","finalActionCorrespType": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Iacuc Batch Correspondences [POST /iacuc/api/v1/iacuc-batch-correspondences/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"batchCorrespondenceTypeCode": "(val)","description": "(val)","daysToEventUiText": "(val)","sendCorrespondence": "(val)","finalActionDay": "(val)","finalActionTypeCode": "(val)","finalActionCorrespType": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"batchCorrespondenceTypeCode": "(val)","description": "(val)","daysToEventUiText": "(val)","sendCorrespondence": "(val)","finalActionDay": "(val)","finalActionTypeCode": "(val)","finalActionCorrespType": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Batch Correspondences [POST /iacuc/api/v1/iacuc-batch-correspondences/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"batchCorrespondenceTypeCode": "(val)","description": "(val)","daysToEventUiText": "(val)","sendCorrespondence": "(val)","finalActionDay": "(val)","finalActionTypeCode": "(val)","finalActionCorrespType": "(val)","_primaryKey": "(val)"},
              {"batchCorrespondenceTypeCode": "(val)","description": "(val)","daysToEventUiText": "(val)","sendCorrespondence": "(val)","finalActionDay": "(val)","finalActionTypeCode": "(val)","finalActionCorrespType": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"batchCorrespondenceTypeCode": "(val)","description": "(val)","daysToEventUiText": "(val)","sendCorrespondence": "(val)","finalActionDay": "(val)","finalActionTypeCode": "(val)","finalActionCorrespType": "(val)","_primaryKey": "(val)"},
              {"batchCorrespondenceTypeCode": "(val)","description": "(val)","daysToEventUiText": "(val)","sendCorrespondence": "(val)","finalActionDay": "(val)","finalActionTypeCode": "(val)","finalActionCorrespType": "(val)","_primaryKey": "(val)"}
            ]
### Delete Iacuc Batch Correspondences by Key [DELETE /iacuc/api/v1/iacuc-batch-correspondences/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Batch Correspondences [DELETE /iacuc/api/v1/iacuc-batch-correspondences/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Batch Correspondences with Matching [DELETE /iacuc/api/v1/iacuc-batch-correspondences/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + batchCorrespondenceTypeCode (optional) - Batch Correspondence Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + daysToEventUiText (optional) - Days to Event UI Text. Maximum length is 400.
    + sendCorrespondence (optional) - Send correspondence before/after event. Maximum length is 10.
    + finalActionDay (optional) - Final Action Day. Maximum length is 3.
    + finalActionTypeCode (optional) - Final Action Type Code. Maximum length is 3.
    + finalActionCorrespType (optional) - Final Action Corresp Type. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
