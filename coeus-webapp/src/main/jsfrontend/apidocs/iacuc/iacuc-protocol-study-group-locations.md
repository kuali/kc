## Iacuc Protocol Study Group Locations [/research-sys/api/v1/iacuc-protocol-study-group-locations/]

### Get Iacuc Protocol Study Group Locations by Key [GET /research-sys/api/v1/iacuc-protocol-study-group-locations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"iacucProtocolStudyGroupLocationId": "(val)","iacucProtocolStudyGroupId": "(val)","locationTypeCode": "(val)","studyGroupLocationId": "(val)","locationId": "(val)","locationRoom": "(val)","studyGroupLocationDescription": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Study Group Locations [GET /research-sys/api/v1/iacuc-protocol-study-group-locations/]
	 
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

### Get All Iacuc Protocol Study Group Locations with Filtering [GET /research-sys/api/v1/iacuc-protocol-study-group-locations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + iacucProtocolStudyGroupLocationId
            + iacucProtocolStudyGroupId
            + locationTypeCode
            + studyGroupLocationId
            + locationId
            + locationRoom
            + studyGroupLocationDescription
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucProtocolStudyGroupLocationId": "(val)","iacucProtocolStudyGroupId": "(val)","locationTypeCode": "(val)","studyGroupLocationId": "(val)","locationId": "(val)","locationRoom": "(val)","studyGroupLocationDescription": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolStudyGroupLocationId": "(val)","iacucProtocolStudyGroupId": "(val)","locationTypeCode": "(val)","studyGroupLocationId": "(val)","locationId": "(val)","locationRoom": "(val)","studyGroupLocationDescription": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Study Group Locations [GET /research-sys/api/v1/iacuc-protocol-study-group-locations/]
	 
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
		
### Get Blueprint API specification for Iacuc Protocol Study Group Locations [GET /research-sys/api/v1/iacuc-protocol-study-group-locations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Study Group Locations.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Study Group Locations [PUT /research-sys/api/v1/iacuc-protocol-study-group-locations/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolStudyGroupLocationId": "(val)","iacucProtocolStudyGroupId": "(val)","locationTypeCode": "(val)","studyGroupLocationId": "(val)","locationId": "(val)","locationRoom": "(val)","studyGroupLocationDescription": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Study Group Locations [PUT /research-sys/api/v1/iacuc-protocol-study-group-locations/]

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

### Insert Iacuc Protocol Study Group Locations [POST /research-sys/api/v1/iacuc-protocol-study-group-locations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolStudyGroupLocationId": "(val)","iacucProtocolStudyGroupId": "(val)","locationTypeCode": "(val)","studyGroupLocationId": "(val)","locationId": "(val)","locationRoom": "(val)","studyGroupLocationDescription": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"iacucProtocolStudyGroupLocationId": "(val)","iacucProtocolStudyGroupId": "(val)","locationTypeCode": "(val)","studyGroupLocationId": "(val)","locationId": "(val)","locationRoom": "(val)","studyGroupLocationDescription": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Study Group Locations [POST /research-sys/api/v1/iacuc-protocol-study-group-locations/]

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
            
### Delete Iacuc Protocol Study Group Locations by Key [DELETE /research-sys/api/v1/iacuc-protocol-study-group-locations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Study Group Locations [DELETE /research-sys/api/v1/iacuc-protocol-study-group-locations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Protocol Study Group Locations with Matching [DELETE /research-sys/api/v1/iacuc-protocol-study-group-locations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + iacucProtocolStudyGroupLocationId
            + iacucProtocolStudyGroupId
            + locationTypeCode
            + studyGroupLocationId
            + locationId
            + locationRoom
            + studyGroupLocationDescription


+ Response 204
