## Organization Indirect Costs [/research-sys/api/v1/organization-indirect-costs/]

### Get Organization Indirect Costs by Key [GET /research-sys/api/v1/organization-indirect-costs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"idcNumber": "(val)","organizationId": "(val)","applicableIndirectcostRate": "(val)","endDate": "(val)","idcComment": "(val)","idcRateTypeCode": "(val)","requestedDate": "(val)","startDate": "(val)","_primaryKey": "(val)"}

### Get All Organization Indirect Costs [GET /research-sys/api/v1/organization-indirect-costs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"idcNumber": "(val)","organizationId": "(val)","applicableIndirectcostRate": "(val)","endDate": "(val)","idcComment": "(val)","idcRateTypeCode": "(val)","requestedDate": "(val)","startDate": "(val)","_primaryKey": "(val)"},
              {"idcNumber": "(val)","organizationId": "(val)","applicableIndirectcostRate": "(val)","endDate": "(val)","idcComment": "(val)","idcRateTypeCode": "(val)","requestedDate": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            ]

### Get All Organization Indirect Costs with Filtering [GET /research-sys/api/v1/organization-indirect-costs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + idcNumber
            + organizationId
            + applicableIndirectcostRate
            + endDate
            + idcComment
            + idcRateTypeCode
            + requestedDate
            + startDate
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"idcNumber": "(val)","organizationId": "(val)","applicableIndirectcostRate": "(val)","endDate": "(val)","idcComment": "(val)","idcRateTypeCode": "(val)","requestedDate": "(val)","startDate": "(val)","_primaryKey": "(val)"},
              {"idcNumber": "(val)","organizationId": "(val)","applicableIndirectcostRate": "(val)","endDate": "(val)","idcComment": "(val)","idcRateTypeCode": "(val)","requestedDate": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Organization Indirect Costs [GET /research-sys/api/v1/organization-indirect-costs/]
	 
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
		
### Get Blueprint API specification for Organization Indirect Costs [GET /research-sys/api/v1/organization-indirect-costs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Organization Indirect Costs.md"
            transfer-encoding:chunked


### Update Organization Indirect Costs [PUT /research-sys/api/v1/organization-indirect-costs/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"idcNumber": "(val)","organizationId": "(val)","applicableIndirectcostRate": "(val)","endDate": "(val)","idcComment": "(val)","idcRateTypeCode": "(val)","requestedDate": "(val)","startDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Organization Indirect Costs [PUT /research-sys/api/v1/organization-indirect-costs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"idcNumber": "(val)","organizationId": "(val)","applicableIndirectcostRate": "(val)","endDate": "(val)","idcComment": "(val)","idcRateTypeCode": "(val)","requestedDate": "(val)","startDate": "(val)","_primaryKey": "(val)"},
              {"idcNumber": "(val)","organizationId": "(val)","applicableIndirectcostRate": "(val)","endDate": "(val)","idcComment": "(val)","idcRateTypeCode": "(val)","requestedDate": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Organization Indirect Costs [POST /research-sys/api/v1/organization-indirect-costs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"idcNumber": "(val)","organizationId": "(val)","applicableIndirectcostRate": "(val)","endDate": "(val)","idcComment": "(val)","idcRateTypeCode": "(val)","requestedDate": "(val)","startDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"idcNumber": "(val)","organizationId": "(val)","applicableIndirectcostRate": "(val)","endDate": "(val)","idcComment": "(val)","idcRateTypeCode": "(val)","requestedDate": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Organization Indirect Costs [POST /research-sys/api/v1/organization-indirect-costs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"idcNumber": "(val)","organizationId": "(val)","applicableIndirectcostRate": "(val)","endDate": "(val)","idcComment": "(val)","idcRateTypeCode": "(val)","requestedDate": "(val)","startDate": "(val)","_primaryKey": "(val)"},
              {"idcNumber": "(val)","organizationId": "(val)","applicableIndirectcostRate": "(val)","endDate": "(val)","idcComment": "(val)","idcRateTypeCode": "(val)","requestedDate": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"idcNumber": "(val)","organizationId": "(val)","applicableIndirectcostRate": "(val)","endDate": "(val)","idcComment": "(val)","idcRateTypeCode": "(val)","requestedDate": "(val)","startDate": "(val)","_primaryKey": "(val)"},
              {"idcNumber": "(val)","organizationId": "(val)","applicableIndirectcostRate": "(val)","endDate": "(val)","idcComment": "(val)","idcRateTypeCode": "(val)","requestedDate": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Organization Indirect Costs by Key [DELETE /research-sys/api/v1/organization-indirect-costs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Organization Indirect Costs [DELETE /research-sys/api/v1/organization-indirect-costs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Organization Indirect Costs with Matching [DELETE /research-sys/api/v1/organization-indirect-costs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + idcNumber
            + organizationId
            + applicableIndirectcostRate
            + endDate
            + idcComment
            + idcRateTypeCode
            + requestedDate
            + startDate


+ Response 204
