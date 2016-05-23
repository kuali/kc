## Protocol Risk Levels [/irb/api/v1/protocol-risk-levels/]

### Get Protocol Risk Levels by Key [GET /irb/api/v1/protocol-risk-levels/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolRiskLevelId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","riskLevelCode": "(val)","comments": "(val)","dateAssigned": "(val)","dateInactivated": "(val)","status": "(val)","_primaryKey": "(val)"}

### Get All Protocol Risk Levels [GET /irb/api/v1/protocol-risk-levels/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolRiskLevelId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","riskLevelCode": "(val)","comments": "(val)","dateAssigned": "(val)","dateInactivated": "(val)","status": "(val)","_primaryKey": "(val)"},
              {"protocolRiskLevelId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","riskLevelCode": "(val)","comments": "(val)","dateAssigned": "(val)","dateInactivated": "(val)","status": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Risk Levels with Filtering [GET /irb/api/v1/protocol-risk-levels/]
    
+ Parameters

    + protocolRiskLevelId (optional) - Protocol Risk Levels Id. Maximum length is 22.
    + protocolId (optional) - Protocol Id. Maximum length is 22.
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + riskLevelCode (optional) - Risk Level Code. Maximum length is 3.
    + comments (optional) - Comments. Maximum length is 2000.
    + dateAssigned (optional) - Date Assigned. Maximum length is 10.
    + dateInactivated (optional) - Date Inactivated. Maximum length is 10.
    + status (optional) - Status. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolRiskLevelId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","riskLevelCode": "(val)","comments": "(val)","dateAssigned": "(val)","dateInactivated": "(val)","status": "(val)","_primaryKey": "(val)"},
              {"protocolRiskLevelId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","riskLevelCode": "(val)","comments": "(val)","dateAssigned": "(val)","dateInactivated": "(val)","status": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Risk Levels [GET /irb/api/v1/protocol-risk-levels/]
	                                          
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
    
            {"columns":["protocolRiskLevelId","protocolId","protocolNumber","sequenceNumber","riskLevelCode","comments","dateAssigned","dateInactivated","status"],"primaryKey":"protocolRiskLevelId"}
		
### Get Blueprint API specification for Protocol Risk Levels [GET /irb/api/v1/protocol-risk-levels/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Risk Levels.md"
            transfer-encoding:chunked
### Update Protocol Risk Levels [PUT /irb/api/v1/protocol-risk-levels/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolRiskLevelId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","riskLevelCode": "(val)","comments": "(val)","dateAssigned": "(val)","dateInactivated": "(val)","status": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Risk Levels [PUT /irb/api/v1/protocol-risk-levels/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolRiskLevelId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","riskLevelCode": "(val)","comments": "(val)","dateAssigned": "(val)","dateInactivated": "(val)","status": "(val)","_primaryKey": "(val)"},
              {"protocolRiskLevelId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","riskLevelCode": "(val)","comments": "(val)","dateAssigned": "(val)","dateInactivated": "(val)","status": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Protocol Risk Levels [POST /irb/api/v1/protocol-risk-levels/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolRiskLevelId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","riskLevelCode": "(val)","comments": "(val)","dateAssigned": "(val)","dateInactivated": "(val)","status": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolRiskLevelId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","riskLevelCode": "(val)","comments": "(val)","dateAssigned": "(val)","dateInactivated": "(val)","status": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Risk Levels [POST /irb/api/v1/protocol-risk-levels/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolRiskLevelId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","riskLevelCode": "(val)","comments": "(val)","dateAssigned": "(val)","dateInactivated": "(val)","status": "(val)","_primaryKey": "(val)"},
              {"protocolRiskLevelId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","riskLevelCode": "(val)","comments": "(val)","dateAssigned": "(val)","dateInactivated": "(val)","status": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolRiskLevelId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","riskLevelCode": "(val)","comments": "(val)","dateAssigned": "(val)","dateInactivated": "(val)","status": "(val)","_primaryKey": "(val)"},
              {"protocolRiskLevelId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","riskLevelCode": "(val)","comments": "(val)","dateAssigned": "(val)","dateInactivated": "(val)","status": "(val)","_primaryKey": "(val)"}
            ]
### Delete Protocol Risk Levels by Key [DELETE /irb/api/v1/protocol-risk-levels/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Risk Levels [DELETE /irb/api/v1/protocol-risk-levels/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Risk Levels with Matching [DELETE /irb/api/v1/protocol-risk-levels/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolRiskLevelId (optional) - Protocol Risk Levels Id. Maximum length is 22.
    + protocolId (optional) - Protocol Id. Maximum length is 22.
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + riskLevelCode (optional) - Risk Level Code. Maximum length is 3.
    + comments (optional) - Comments. Maximum length is 2000.
    + dateAssigned (optional) - Date Assigned. Maximum length is 10.
    + dateInactivated (optional) - Date Inactivated. Maximum length is 10.
    + status (optional) - Status. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
