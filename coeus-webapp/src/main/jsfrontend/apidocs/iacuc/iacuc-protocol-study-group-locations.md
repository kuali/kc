## Iacuc Protocol Study Group Locations [/iacuc/api/v1/iacuc-protocol-study-group-locations/]

### Get Iacuc Protocol Study Group Locations by Key [GET /iacuc/api/v1/iacuc-protocol-study-group-locations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"iacucProtocolStudyGroupLocationId": "(val)","iacucProtocolStudyGroupId": "(val)","locationTypeCode": "(val)","studyGroupLocationId": "(val)","locationId": "(val)","locationRoom": "(val)","studyGroupLocationDescription": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Study Group Locations [GET /iacuc/api/v1/iacuc-protocol-study-group-locations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucProtocolStudyGroupLocationId": "(val)","iacucProtocolStudyGroupId": "(val)","locationTypeCode": "(val)","studyGroupLocationId": "(val)","locationId": "(val)","locationRoom": "(val)","studyGroupLocationDescription": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolStudyGroupLocationId": "(val)","iacucProtocolStudyGroupId": "(val)","locationTypeCode": "(val)","studyGroupLocationId": "(val)","locationId": "(val)","locationRoom": "(val)","studyGroupLocationDescription": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Study Group Locations with Filtering [GET /iacuc/api/v1/iacuc-protocol-study-group-locations/]
    
+ Parameters

    + iacucProtocolStudyGroupLocationId (optional) - Protocol Study Group Location Id. Maximum length is 22.
    + iacucProtocolStudyGroupId (optional) - Protocol Study Group Id. Maximum length is 22.
    + locationTypeCode (optional) - Location Type Code. Maximum length is 3.
    + studyGroupLocationId (optional) - Study Group Location Id. Maximum length is 6.
    + locationId (optional) - Location Name Code. Maximum length is 3.
    + locationRoom (optional) - Room. Maximum length is 60.
    + studyGroupLocationDescription (optional) - Description. Maximum length is 2000.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucProtocolStudyGroupLocationId": "(val)","iacucProtocolStudyGroupId": "(val)","locationTypeCode": "(val)","studyGroupLocationId": "(val)","locationId": "(val)","locationRoom": "(val)","studyGroupLocationDescription": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolStudyGroupLocationId": "(val)","iacucProtocolStudyGroupId": "(val)","locationTypeCode": "(val)","studyGroupLocationId": "(val)","locationId": "(val)","locationRoom": "(val)","studyGroupLocationDescription": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Study Group Locations [GET /iacuc/api/v1/iacuc-protocol-study-group-locations/]
	                                          
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
    
            {"columns":["iacucProtocolStudyGroupLocationId","iacucProtocolStudyGroupId","locationTypeCode","studyGroupLocationId","locationId","locationRoom","studyGroupLocationDescription"],"primaryKey":"iacucProtocolStudyGroupLocationId"}
		
### Get Blueprint API specification for Iacuc Protocol Study Group Locations [GET /iacuc/api/v1/iacuc-protocol-study-group-locations/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Study Group Locations.md"
            transfer-encoding:chunked
### Update Iacuc Protocol Study Group Locations [PUT /iacuc/api/v1/iacuc-protocol-study-group-locations/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolStudyGroupLocationId": "(val)","iacucProtocolStudyGroupId": "(val)","locationTypeCode": "(val)","studyGroupLocationId": "(val)","locationId": "(val)","locationRoom": "(val)","studyGroupLocationDescription": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Study Group Locations [PUT /iacuc/api/v1/iacuc-protocol-study-group-locations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"iacucProtocolStudyGroupLocationId": "(val)","iacucProtocolStudyGroupId": "(val)","locationTypeCode": "(val)","studyGroupLocationId": "(val)","locationId": "(val)","locationRoom": "(val)","studyGroupLocationDescription": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolStudyGroupLocationId": "(val)","iacucProtocolStudyGroupId": "(val)","locationTypeCode": "(val)","studyGroupLocationId": "(val)","locationId": "(val)","locationRoom": "(val)","studyGroupLocationDescription": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Iacuc Protocol Study Group Locations [POST /iacuc/api/v1/iacuc-protocol-study-group-locations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolStudyGroupLocationId": "(val)","iacucProtocolStudyGroupId": "(val)","locationTypeCode": "(val)","studyGroupLocationId": "(val)","locationId": "(val)","locationRoom": "(val)","studyGroupLocationDescription": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"iacucProtocolStudyGroupLocationId": "(val)","iacucProtocolStudyGroupId": "(val)","locationTypeCode": "(val)","studyGroupLocationId": "(val)","locationId": "(val)","locationRoom": "(val)","studyGroupLocationDescription": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Study Group Locations [POST /iacuc/api/v1/iacuc-protocol-study-group-locations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"iacucProtocolStudyGroupLocationId": "(val)","iacucProtocolStudyGroupId": "(val)","locationTypeCode": "(val)","studyGroupLocationId": "(val)","locationId": "(val)","locationRoom": "(val)","studyGroupLocationDescription": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolStudyGroupLocationId": "(val)","iacucProtocolStudyGroupId": "(val)","locationTypeCode": "(val)","studyGroupLocationId": "(val)","locationId": "(val)","locationRoom": "(val)","studyGroupLocationDescription": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"iacucProtocolStudyGroupLocationId": "(val)","iacucProtocolStudyGroupId": "(val)","locationTypeCode": "(val)","studyGroupLocationId": "(val)","locationId": "(val)","locationRoom": "(val)","studyGroupLocationDescription": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolStudyGroupLocationId": "(val)","iacucProtocolStudyGroupId": "(val)","locationTypeCode": "(val)","studyGroupLocationId": "(val)","locationId": "(val)","locationRoom": "(val)","studyGroupLocationDescription": "(val)","_primaryKey": "(val)"}
            ]
### Delete Iacuc Protocol Study Group Locations by Key [DELETE /iacuc/api/v1/iacuc-protocol-study-group-locations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Study Group Locations [DELETE /iacuc/api/v1/iacuc-protocol-study-group-locations/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Study Group Locations with Matching [DELETE /iacuc/api/v1/iacuc-protocol-study-group-locations/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + iacucProtocolStudyGroupLocationId (optional) - Protocol Study Group Location Id. Maximum length is 22.
    + iacucProtocolStudyGroupId (optional) - Protocol Study Group Id. Maximum length is 22.
    + locationTypeCode (optional) - Location Type Code. Maximum length is 3.
    + studyGroupLocationId (optional) - Study Group Location Id. Maximum length is 6.
    + locationId (optional) - Location Name Code. Maximum length is 3.
    + locationRoom (optional) - Room. Maximum length is 60.
    + studyGroupLocationDescription (optional) - Description. Maximum length is 2000.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
