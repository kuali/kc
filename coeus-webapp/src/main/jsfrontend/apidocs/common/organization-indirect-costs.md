## Organization Indirect Costs [/research-common/api/v1/organization-indirect-costs/]

### Get Organization Indirect Costs by Key [GET /research-common/api/v1/organization-indirect-costs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"idcNumber": "(val)","organizationId": "(val)","applicableIndirectcostRate": "(val)","endDate": "(val)","idcComment": "(val)","idcRateTypeCode": "(val)","requestedDate": "(val)","startDate": "(val)","_primaryKey": "(val)"}

### Get All Organization Indirect Costs [GET /research-common/api/v1/organization-indirect-costs/]
	 
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

### Get All Organization Indirect Costs with Filtering [GET /research-common/api/v1/organization-indirect-costs/]
    
+ Parameters

    + idcNumber (optional) - Idc Number. Maximum length is 3.
    + organizationId (optional) - Organization Id. Maximum length is 8.
    + applicableIndirectcostRate (optional) - Applicable Indirectcost Rate. Maximum length is 8.
    + endDate (optional) - End Date. Maximum length is 21.
    + idcComment (optional) - Idc Comment. Maximum length is 300.
    + idcRateTypeCode (optional) - Idc Rate Type Code. Maximum length is 3.
    + requestedDate (optional) - Requested Date. Maximum length is 10.
    + startDate (optional) - Start Date. Maximum length is 10.

            
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
			
### Get Schema for Organization Indirect Costs [GET /research-common/api/v1/organization-indirect-costs/]
	                                          
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
    
            {"columns":["idcNumber","organizationId","applicableIndirectcostRate","endDate","idcComment","idcRateTypeCode","requestedDate","startDate"],"primaryKey":"idcNumber:organizationId"}
		
### Get Blueprint API specification for Organization Indirect Costs [GET /research-common/api/v1/organization-indirect-costs/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Organization Indirect Costs.md"
            transfer-encoding:chunked


### Update Organization Indirect Costs [PUT /research-common/api/v1/organization-indirect-costs/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"idcNumber": "(val)","organizationId": "(val)","applicableIndirectcostRate": "(val)","endDate": "(val)","idcComment": "(val)","idcRateTypeCode": "(val)","requestedDate": "(val)","startDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Organization Indirect Costs [PUT /research-common/api/v1/organization-indirect-costs/]

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

### Insert Organization Indirect Costs [POST /research-common/api/v1/organization-indirect-costs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"idcNumber": "(val)","organizationId": "(val)","applicableIndirectcostRate": "(val)","endDate": "(val)","idcComment": "(val)","idcRateTypeCode": "(val)","requestedDate": "(val)","startDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"idcNumber": "(val)","organizationId": "(val)","applicableIndirectcostRate": "(val)","endDate": "(val)","idcComment": "(val)","idcRateTypeCode": "(val)","requestedDate": "(val)","startDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Organization Indirect Costs [POST /research-common/api/v1/organization-indirect-costs/]

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
            
### Delete Organization Indirect Costs by Key [DELETE /research-common/api/v1/organization-indirect-costs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Organization Indirect Costs [DELETE /research-common/api/v1/organization-indirect-costs/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Organization Indirect Costs with Matching [DELETE /research-common/api/v1/organization-indirect-costs/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + idcNumber (optional) - Idc Number. Maximum length is 3.
    + organizationId (optional) - Organization Id. Maximum length is 8.
    + applicableIndirectcostRate (optional) - Applicable Indirectcost Rate. Maximum length is 8.
    + endDate (optional) - End Date. Maximum length is 21.
    + idcComment (optional) - Idc Comment. Maximum length is 300.
    + idcRateTypeCode (optional) - Idc Rate Type Code. Maximum length is 3.
    + requestedDate (optional) - Requested Date. Maximum length is 10.
    + startDate (optional) - Start Date. Maximum length is 10.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
