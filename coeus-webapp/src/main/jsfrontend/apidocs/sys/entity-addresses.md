## Entity Addresses [/research-sys/api/v1/entity-addresses/]

### Get Entity Addresses by Key [GET /research-sys/api/v1/entity-addresses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","city": "(val)","defaultValue": "(val)","addressFormat": "(val)","postalCode": "(val)","active": "(val)","entityId": "(val)","attentionLine": "(val)","noteMessage": "(val)","stateProvinceCode": "(val)","addressTypeCode": "(val)","validated": "(val)","countryCode": "(val)","entityTypeCode": "(val)","validatedDate": "(val)","modifiedDate": "(val)","line3": "(val)","line2": "(val)","line1": "(val)","_primaryKey": "(val)"}

### Get All Entity Addresses [GET /research-sys/api/v1/entity-addresses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","city": "(val)","defaultValue": "(val)","addressFormat": "(val)","postalCode": "(val)","active": "(val)","entityId": "(val)","attentionLine": "(val)","noteMessage": "(val)","stateProvinceCode": "(val)","addressTypeCode": "(val)","validated": "(val)","countryCode": "(val)","entityTypeCode": "(val)","validatedDate": "(val)","modifiedDate": "(val)","line3": "(val)","line2": "(val)","line1": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","city": "(val)","defaultValue": "(val)","addressFormat": "(val)","postalCode": "(val)","active": "(val)","entityId": "(val)","attentionLine": "(val)","noteMessage": "(val)","stateProvinceCode": "(val)","addressTypeCode": "(val)","validated": "(val)","countryCode": "(val)","entityTypeCode": "(val)","validatedDate": "(val)","modifiedDate": "(val)","line3": "(val)","line2": "(val)","line1": "(val)","_primaryKey": "(val)"}
            ]

### Get All Entity Addresses with Filtering [GET /research-sys/api/v1/entity-addresses/]
    
+ Parameters

    + id (optional) - 
    + city (optional) - 
    + defaultValue (optional) - 
    + addressFormat (optional) - 
    + postalCode (optional) - 
    + active (optional) - 
    + entityId (optional) - 
    + attentionLine (optional) - 
    + noteMessage (optional) - 
    + stateProvinceCode (optional) - 
    + addressTypeCode (optional) - 
    + validated (optional) - 
    + countryCode (optional) - 
    + entityTypeCode (optional) - 
    + validatedDate (optional) - 
    + modifiedDate (optional) - 
    + line3 (optional) - 
    + line2 (optional) - 
    + line1 (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","city": "(val)","defaultValue": "(val)","addressFormat": "(val)","postalCode": "(val)","active": "(val)","entityId": "(val)","attentionLine": "(val)","noteMessage": "(val)","stateProvinceCode": "(val)","addressTypeCode": "(val)","validated": "(val)","countryCode": "(val)","entityTypeCode": "(val)","validatedDate": "(val)","modifiedDate": "(val)","line3": "(val)","line2": "(val)","line1": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","city": "(val)","defaultValue": "(val)","addressFormat": "(val)","postalCode": "(val)","active": "(val)","entityId": "(val)","attentionLine": "(val)","noteMessage": "(val)","stateProvinceCode": "(val)","addressTypeCode": "(val)","validated": "(val)","countryCode": "(val)","entityTypeCode": "(val)","validatedDate": "(val)","modifiedDate": "(val)","line3": "(val)","line2": "(val)","line1": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Entity Addresses [GET /research-sys/api/v1/entity-addresses/]
	                                          
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
    
            {"columns":["id","city","defaultValue","addressFormat","postalCode","active","entityId","attentionLine","noteMessage","stateProvinceCode","addressTypeCode","validated","countryCode","entityTypeCode","validatedDate","modifiedDate","line3","line2","line1"],"primaryKey":"id"}
		
### Get Blueprint API specification for Entity Addresses [GET /research-sys/api/v1/entity-addresses/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Entity Addresses.md"
            transfer-encoding:chunked


### Update Entity Addresses [PUT /research-sys/api/v1/entity-addresses/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","city": "(val)","defaultValue": "(val)","addressFormat": "(val)","postalCode": "(val)","active": "(val)","entityId": "(val)","attentionLine": "(val)","noteMessage": "(val)","stateProvinceCode": "(val)","addressTypeCode": "(val)","validated": "(val)","countryCode": "(val)","entityTypeCode": "(val)","validatedDate": "(val)","modifiedDate": "(val)","line3": "(val)","line2": "(val)","line1": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Entity Addresses [PUT /research-sys/api/v1/entity-addresses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","city": "(val)","defaultValue": "(val)","addressFormat": "(val)","postalCode": "(val)","active": "(val)","entityId": "(val)","attentionLine": "(val)","noteMessage": "(val)","stateProvinceCode": "(val)","addressTypeCode": "(val)","validated": "(val)","countryCode": "(val)","entityTypeCode": "(val)","validatedDate": "(val)","modifiedDate": "(val)","line3": "(val)","line2": "(val)","line1": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","city": "(val)","defaultValue": "(val)","addressFormat": "(val)","postalCode": "(val)","active": "(val)","entityId": "(val)","attentionLine": "(val)","noteMessage": "(val)","stateProvinceCode": "(val)","addressTypeCode": "(val)","validated": "(val)","countryCode": "(val)","entityTypeCode": "(val)","validatedDate": "(val)","modifiedDate": "(val)","line3": "(val)","line2": "(val)","line1": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Entity Addresses [POST /research-sys/api/v1/entity-addresses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","city": "(val)","defaultValue": "(val)","addressFormat": "(val)","postalCode": "(val)","active": "(val)","entityId": "(val)","attentionLine": "(val)","noteMessage": "(val)","stateProvinceCode": "(val)","addressTypeCode": "(val)","validated": "(val)","countryCode": "(val)","entityTypeCode": "(val)","validatedDate": "(val)","modifiedDate": "(val)","line3": "(val)","line2": "(val)","line1": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","city": "(val)","defaultValue": "(val)","addressFormat": "(val)","postalCode": "(val)","active": "(val)","entityId": "(val)","attentionLine": "(val)","noteMessage": "(val)","stateProvinceCode": "(val)","addressTypeCode": "(val)","validated": "(val)","countryCode": "(val)","entityTypeCode": "(val)","validatedDate": "(val)","modifiedDate": "(val)","line3": "(val)","line2": "(val)","line1": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Entity Addresses [POST /research-sys/api/v1/entity-addresses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","city": "(val)","defaultValue": "(val)","addressFormat": "(val)","postalCode": "(val)","active": "(val)","entityId": "(val)","attentionLine": "(val)","noteMessage": "(val)","stateProvinceCode": "(val)","addressTypeCode": "(val)","validated": "(val)","countryCode": "(val)","entityTypeCode": "(val)","validatedDate": "(val)","modifiedDate": "(val)","line3": "(val)","line2": "(val)","line1": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","city": "(val)","defaultValue": "(val)","addressFormat": "(val)","postalCode": "(val)","active": "(val)","entityId": "(val)","attentionLine": "(val)","noteMessage": "(val)","stateProvinceCode": "(val)","addressTypeCode": "(val)","validated": "(val)","countryCode": "(val)","entityTypeCode": "(val)","validatedDate": "(val)","modifiedDate": "(val)","line3": "(val)","line2": "(val)","line1": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","city": "(val)","defaultValue": "(val)","addressFormat": "(val)","postalCode": "(val)","active": "(val)","entityId": "(val)","attentionLine": "(val)","noteMessage": "(val)","stateProvinceCode": "(val)","addressTypeCode": "(val)","validated": "(val)","countryCode": "(val)","entityTypeCode": "(val)","validatedDate": "(val)","modifiedDate": "(val)","line3": "(val)","line2": "(val)","line1": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","city": "(val)","defaultValue": "(val)","addressFormat": "(val)","postalCode": "(val)","active": "(val)","entityId": "(val)","attentionLine": "(val)","noteMessage": "(val)","stateProvinceCode": "(val)","addressTypeCode": "(val)","validated": "(val)","countryCode": "(val)","entityTypeCode": "(val)","validatedDate": "(val)","modifiedDate": "(val)","line3": "(val)","line2": "(val)","line1": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Entity Addresses by Key [DELETE /research-sys/api/v1/entity-addresses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Addresses [DELETE /research-sys/api/v1/entity-addresses/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Addresses with Matching [DELETE /research-sys/api/v1/entity-addresses/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + city (optional) - 
    + defaultValue (optional) - 
    + addressFormat (optional) - 
    + postalCode (optional) - 
    + active (optional) - 
    + entityId (optional) - 
    + attentionLine (optional) - 
    + noteMessage (optional) - 
    + stateProvinceCode (optional) - 
    + addressTypeCode (optional) - 
    + validated (optional) - 
    + countryCode (optional) - 
    + entityTypeCode (optional) - 
    + validatedDate (optional) - 
    + modifiedDate (optional) - 
    + line3 (optional) - 
    + line2 (optional) - 
    + line1 (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
