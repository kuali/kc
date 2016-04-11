## Protocol Risk Levels [/research-sys/api/v1/protocol-risk-levels/]

### Get Protocol Risk Levels by Key [GET /research-sys/api/v1/protocol-risk-levels/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolRiskLevelId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","riskLevelCode": "(val)","comments": "(val)","dateAssigned": "(val)","dateInactivated": "(val)","status": "(val)","_primaryKey": "(val)"}

### Get All Protocol Risk Levels [GET /research-sys/api/v1/protocol-risk-levels/]
	 
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

### Get All Protocol Risk Levels with Filtering [GET /research-sys/api/v1/protocol-risk-levels/]
    
+ Parameters

        + protocolRiskLevelId
            + protocolId
            + protocolNumber
            + sequenceNumber
            + riskLevelCode
            + comments
            + dateAssigned
            + dateInactivated
            + status

            
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
			
### Get Schema for Protocol Risk Levels [GET /research-sys/api/v1/protocol-risk-levels/]
	                                          
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
		
### Get Blueprint API specification for Protocol Risk Levels [GET /research-sys/api/v1/protocol-risk-levels/]
	 
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


### Update Protocol Risk Levels [PUT /research-sys/api/v1/protocol-risk-levels/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolRiskLevelId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","riskLevelCode": "(val)","comments": "(val)","dateAssigned": "(val)","dateInactivated": "(val)","status": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Risk Levels [PUT /research-sys/api/v1/protocol-risk-levels/]

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

### Insert Protocol Risk Levels [POST /research-sys/api/v1/protocol-risk-levels/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolRiskLevelId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","riskLevelCode": "(val)","comments": "(val)","dateAssigned": "(val)","dateInactivated": "(val)","status": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolRiskLevelId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","riskLevelCode": "(val)","comments": "(val)","dateAssigned": "(val)","dateInactivated": "(val)","status": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Risk Levels [POST /research-sys/api/v1/protocol-risk-levels/]

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
            
### Delete Protocol Risk Levels by Key [DELETE /research-sys/api/v1/protocol-risk-levels/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Risk Levels [DELETE /research-sys/api/v1/protocol-risk-levels/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Risk Levels with Matching [DELETE /research-sys/api/v1/protocol-risk-levels/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + protocolRiskLevelId
            + protocolId
            + protocolNumber
            + sequenceNumber
            + riskLevelCode
            + comments
            + dateAssigned
            + dateInactivated
            + status

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
