## Watermarks [/research-common/api/v1/watermarks/]

### Get Watermarks by Key [GET /research-common/api/v1/watermarks/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"watermarkId": "(val)","statusCode": "(val)","watermarkText": "(val)","watermarkStatus": "(val)","fontSize": "(val)","fontColor": "(val)","watermarkType": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","watermarkPosition": "(val)","watermarkAlignment": "(val)","positionFontSize": "(val)","groupName": "(val)","_primaryKey": "(val)"}

### Get All Watermarks [GET /research-common/api/v1/watermarks/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"watermarkId": "(val)","statusCode": "(val)","watermarkText": "(val)","watermarkStatus": "(val)","fontSize": "(val)","fontColor": "(val)","watermarkType": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","watermarkPosition": "(val)","watermarkAlignment": "(val)","positionFontSize": "(val)","groupName": "(val)","_primaryKey": "(val)"},
              {"watermarkId": "(val)","statusCode": "(val)","watermarkText": "(val)","watermarkStatus": "(val)","fontSize": "(val)","fontColor": "(val)","watermarkType": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","watermarkPosition": "(val)","watermarkAlignment": "(val)","positionFontSize": "(val)","groupName": "(val)","_primaryKey": "(val)"}
            ]

### Get All Watermarks with Filtering [GET /research-common/api/v1/watermarks/]
    
+ Parameters

    + watermarkId (optional) - Watermark Code. Maximum length is 12.
    + statusCode (optional) - Status Code. Maximum length is 8.
    + watermarkText (optional) - Watermark Text. Maximum length is 200.
    + watermarkStatus (optional) - Validate Watermark Status is Active or not. Maximum length is 1.
    + fontSize (optional) - Font Size. Maximum length is 5.
    + fontColor (optional) - Font Color. Maximum length is 40.
    + watermarkType (optional) - Watermark Type. Maximum length is 20.
    + fileName (optional) - 
    + contentType (optional) - 
    + attachmentContent (optional) - 
    + watermarkPosition (optional) - Position. Maximum length is 40.
    + watermarkAlignment (optional) - Alignment. Maximum length is 40.
    + positionFontSize (optional) - Font Size. Maximum length is 5.
    + groupName (optional) - Group Name. Maximum length is 8.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"watermarkId": "(val)","statusCode": "(val)","watermarkText": "(val)","watermarkStatus": "(val)","fontSize": "(val)","fontColor": "(val)","watermarkType": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","watermarkPosition": "(val)","watermarkAlignment": "(val)","positionFontSize": "(val)","groupName": "(val)","_primaryKey": "(val)"},
              {"watermarkId": "(val)","statusCode": "(val)","watermarkText": "(val)","watermarkStatus": "(val)","fontSize": "(val)","fontColor": "(val)","watermarkType": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","watermarkPosition": "(val)","watermarkAlignment": "(val)","positionFontSize": "(val)","groupName": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Watermarks [GET /research-common/api/v1/watermarks/]
	                                          
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
    
            {"columns":["watermarkId","statusCode","watermarkText","watermarkStatus","fontSize","fontColor","watermarkType","fileName","contentType","attachmentContent","watermarkPosition","watermarkAlignment","positionFontSize","groupName"],"primaryKey":"watermarkId"}
		
### Get Blueprint API specification for Watermarks [GET /research-common/api/v1/watermarks/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Watermarks.md"
            transfer-encoding:chunked
### Update Watermarks [PUT /research-common/api/v1/watermarks/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"watermarkId": "(val)","statusCode": "(val)","watermarkText": "(val)","watermarkStatus": "(val)","fontSize": "(val)","fontColor": "(val)","watermarkType": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","watermarkPosition": "(val)","watermarkAlignment": "(val)","positionFontSize": "(val)","groupName": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Watermarks [PUT /research-common/api/v1/watermarks/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"watermarkId": "(val)","statusCode": "(val)","watermarkText": "(val)","watermarkStatus": "(val)","fontSize": "(val)","fontColor": "(val)","watermarkType": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","watermarkPosition": "(val)","watermarkAlignment": "(val)","positionFontSize": "(val)","groupName": "(val)","_primaryKey": "(val)"},
              {"watermarkId": "(val)","statusCode": "(val)","watermarkText": "(val)","watermarkStatus": "(val)","fontSize": "(val)","fontColor": "(val)","watermarkType": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","watermarkPosition": "(val)","watermarkAlignment": "(val)","positionFontSize": "(val)","groupName": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Watermarks [POST /research-common/api/v1/watermarks/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"watermarkId": "(val)","statusCode": "(val)","watermarkText": "(val)","watermarkStatus": "(val)","fontSize": "(val)","fontColor": "(val)","watermarkType": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","watermarkPosition": "(val)","watermarkAlignment": "(val)","positionFontSize": "(val)","groupName": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"watermarkId": "(val)","statusCode": "(val)","watermarkText": "(val)","watermarkStatus": "(val)","fontSize": "(val)","fontColor": "(val)","watermarkType": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","watermarkPosition": "(val)","watermarkAlignment": "(val)","positionFontSize": "(val)","groupName": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Watermarks [POST /research-common/api/v1/watermarks/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"watermarkId": "(val)","statusCode": "(val)","watermarkText": "(val)","watermarkStatus": "(val)","fontSize": "(val)","fontColor": "(val)","watermarkType": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","watermarkPosition": "(val)","watermarkAlignment": "(val)","positionFontSize": "(val)","groupName": "(val)","_primaryKey": "(val)"},
              {"watermarkId": "(val)","statusCode": "(val)","watermarkText": "(val)","watermarkStatus": "(val)","fontSize": "(val)","fontColor": "(val)","watermarkType": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","watermarkPosition": "(val)","watermarkAlignment": "(val)","positionFontSize": "(val)","groupName": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"watermarkId": "(val)","statusCode": "(val)","watermarkText": "(val)","watermarkStatus": "(val)","fontSize": "(val)","fontColor": "(val)","watermarkType": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","watermarkPosition": "(val)","watermarkAlignment": "(val)","positionFontSize": "(val)","groupName": "(val)","_primaryKey": "(val)"},
              {"watermarkId": "(val)","statusCode": "(val)","watermarkText": "(val)","watermarkStatus": "(val)","fontSize": "(val)","fontColor": "(val)","watermarkType": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","watermarkPosition": "(val)","watermarkAlignment": "(val)","positionFontSize": "(val)","groupName": "(val)","_primaryKey": "(val)"}
            ]
### Delete Watermarks by Key [DELETE /research-common/api/v1/watermarks/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Watermarks [DELETE /research-common/api/v1/watermarks/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Watermarks with Matching [DELETE /research-common/api/v1/watermarks/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + watermarkId (optional) - Watermark Code. Maximum length is 12.
    + statusCode (optional) - Status Code. Maximum length is 8.
    + watermarkText (optional) - Watermark Text. Maximum length is 200.
    + watermarkStatus (optional) - Validate Watermark Status is Active or not. Maximum length is 1.
    + fontSize (optional) - Font Size. Maximum length is 5.
    + fontColor (optional) - Font Color. Maximum length is 40.
    + watermarkType (optional) - Watermark Type. Maximum length is 20.
    + fileName (optional) - 
    + contentType (optional) - 
    + attachmentContent (optional) - 
    + watermarkPosition (optional) - Position. Maximum length is 40.
    + watermarkAlignment (optional) - Alignment. Maximum length is 40.
    + positionFontSize (optional) - Font Size. Maximum length is 5.
    + groupName (optional) - Group Name. Maximum length is 8.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
