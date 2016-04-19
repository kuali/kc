## Award Cgbs [/award/api/v1/award-cgbs/]

### Get Award Cgbs by Key [GET /award/api/v1/award-cgbs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","additionalFormsRequired": "(val)","autoApproveInvoice": "(val)","stopWork": "(val)","minInvoiceAmount": "(val)","invoicingOption": "(val)","dunningCampaignId": "(val)","lastBilledDate": "(val)","previousLastBilledDate": "(val)","finalBill": "(val)","amountToDraw": "(val)","letterOfCreditReviewIndicator": "(val)","invoiceDocumentStatus": "(val)","locCreationType": "(val)","suspendInvoicing": "(val)","_primaryKey": "(val)"}

### Get All Award Cgbs [GET /award/api/v1/award-cgbs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","additionalFormsRequired": "(val)","autoApproveInvoice": "(val)","stopWork": "(val)","minInvoiceAmount": "(val)","invoicingOption": "(val)","dunningCampaignId": "(val)","lastBilledDate": "(val)","previousLastBilledDate": "(val)","finalBill": "(val)","amountToDraw": "(val)","letterOfCreditReviewIndicator": "(val)","invoiceDocumentStatus": "(val)","locCreationType": "(val)","suspendInvoicing": "(val)","_primaryKey": "(val)"},
              {"awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","additionalFormsRequired": "(val)","autoApproveInvoice": "(val)","stopWork": "(val)","minInvoiceAmount": "(val)","invoicingOption": "(val)","dunningCampaignId": "(val)","lastBilledDate": "(val)","previousLastBilledDate": "(val)","finalBill": "(val)","amountToDraw": "(val)","letterOfCreditReviewIndicator": "(val)","invoiceDocumentStatus": "(val)","locCreationType": "(val)","suspendInvoicing": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Cgbs with Filtering [GET /award/api/v1/award-cgbs/]
    
+ Parameters

    + awardId (optional) - 
    + awardNumber (optional) - 
    + sequenceNumber (optional) - 
    + additionalFormsRequired (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 1.
    + autoApproveInvoice (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 1.
    + stopWork (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 1.
    + minInvoiceAmount (optional) - Minimum Invoice Amount. Maximum length is 22.
    + invoicingOption (optional) - Invoicing Option. Maximum length is 120.
    + dunningCampaignId (optional) - Dunning Campaign. Maximum length is 4.
    + lastBilledDate (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 21.
    + previousLastBilledDate (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 21.
    + finalBill (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 1.
    + amountToDraw (optional) - Amount To Draw. Maximum length is 22.
    + letterOfCreditReviewIndicator (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 1.
    + invoiceDocumentStatus (optional) - Invoice Document Status. Maximum length is 45.
    + locCreationType (optional) - Letter Of Credit Creation Type. Maximum length is 45.
    + suspendInvoicing (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","additionalFormsRequired": "(val)","autoApproveInvoice": "(val)","stopWork": "(val)","minInvoiceAmount": "(val)","invoicingOption": "(val)","dunningCampaignId": "(val)","lastBilledDate": "(val)","previousLastBilledDate": "(val)","finalBill": "(val)","amountToDraw": "(val)","letterOfCreditReviewIndicator": "(val)","invoiceDocumentStatus": "(val)","locCreationType": "(val)","suspendInvoicing": "(val)","_primaryKey": "(val)"},
              {"awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","additionalFormsRequired": "(val)","autoApproveInvoice": "(val)","stopWork": "(val)","minInvoiceAmount": "(val)","invoicingOption": "(val)","dunningCampaignId": "(val)","lastBilledDate": "(val)","previousLastBilledDate": "(val)","finalBill": "(val)","amountToDraw": "(val)","letterOfCreditReviewIndicator": "(val)","invoiceDocumentStatus": "(val)","locCreationType": "(val)","suspendInvoicing": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Cgbs [GET /award/api/v1/award-cgbs/]
	                                          
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
    
            {"columns":["awardId","awardNumber","sequenceNumber","additionalFormsRequired","autoApproveInvoice","stopWork","minInvoiceAmount","invoicingOption","dunningCampaignId","lastBilledDate","previousLastBilledDate","finalBill","amountToDraw","letterOfCreditReviewIndicator","invoiceDocumentStatus","locCreationType","suspendInvoicing"],"primaryKey":"awardId"}
		
### Get Blueprint API specification for Award Cgbs [GET /award/api/v1/award-cgbs/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Cgbs.md"
            transfer-encoding:chunked


### Update Award Cgbs [PUT /award/api/v1/award-cgbs/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","additionalFormsRequired": "(val)","autoApproveInvoice": "(val)","stopWork": "(val)","minInvoiceAmount": "(val)","invoicingOption": "(val)","dunningCampaignId": "(val)","lastBilledDate": "(val)","previousLastBilledDate": "(val)","finalBill": "(val)","amountToDraw": "(val)","letterOfCreditReviewIndicator": "(val)","invoiceDocumentStatus": "(val)","locCreationType": "(val)","suspendInvoicing": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Cgbs [PUT /award/api/v1/award-cgbs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","additionalFormsRequired": "(val)","autoApproveInvoice": "(val)","stopWork": "(val)","minInvoiceAmount": "(val)","invoicingOption": "(val)","dunningCampaignId": "(val)","lastBilledDate": "(val)","previousLastBilledDate": "(val)","finalBill": "(val)","amountToDraw": "(val)","letterOfCreditReviewIndicator": "(val)","invoiceDocumentStatus": "(val)","locCreationType": "(val)","suspendInvoicing": "(val)","_primaryKey": "(val)"},
              {"awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","additionalFormsRequired": "(val)","autoApproveInvoice": "(val)","stopWork": "(val)","minInvoiceAmount": "(val)","invoicingOption": "(val)","dunningCampaignId": "(val)","lastBilledDate": "(val)","previousLastBilledDate": "(val)","finalBill": "(val)","amountToDraw": "(val)","letterOfCreditReviewIndicator": "(val)","invoiceDocumentStatus": "(val)","locCreationType": "(val)","suspendInvoicing": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Cgbs [POST /award/api/v1/award-cgbs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","additionalFormsRequired": "(val)","autoApproveInvoice": "(val)","stopWork": "(val)","minInvoiceAmount": "(val)","invoicingOption": "(val)","dunningCampaignId": "(val)","lastBilledDate": "(val)","previousLastBilledDate": "(val)","finalBill": "(val)","amountToDraw": "(val)","letterOfCreditReviewIndicator": "(val)","invoiceDocumentStatus": "(val)","locCreationType": "(val)","suspendInvoicing": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","additionalFormsRequired": "(val)","autoApproveInvoice": "(val)","stopWork": "(val)","minInvoiceAmount": "(val)","invoicingOption": "(val)","dunningCampaignId": "(val)","lastBilledDate": "(val)","previousLastBilledDate": "(val)","finalBill": "(val)","amountToDraw": "(val)","letterOfCreditReviewIndicator": "(val)","invoiceDocumentStatus": "(val)","locCreationType": "(val)","suspendInvoicing": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Cgbs [POST /award/api/v1/award-cgbs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","additionalFormsRequired": "(val)","autoApproveInvoice": "(val)","stopWork": "(val)","minInvoiceAmount": "(val)","invoicingOption": "(val)","dunningCampaignId": "(val)","lastBilledDate": "(val)","previousLastBilledDate": "(val)","finalBill": "(val)","amountToDraw": "(val)","letterOfCreditReviewIndicator": "(val)","invoiceDocumentStatus": "(val)","locCreationType": "(val)","suspendInvoicing": "(val)","_primaryKey": "(val)"},
              {"awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","additionalFormsRequired": "(val)","autoApproveInvoice": "(val)","stopWork": "(val)","minInvoiceAmount": "(val)","invoicingOption": "(val)","dunningCampaignId": "(val)","lastBilledDate": "(val)","previousLastBilledDate": "(val)","finalBill": "(val)","amountToDraw": "(val)","letterOfCreditReviewIndicator": "(val)","invoiceDocumentStatus": "(val)","locCreationType": "(val)","suspendInvoicing": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","additionalFormsRequired": "(val)","autoApproveInvoice": "(val)","stopWork": "(val)","minInvoiceAmount": "(val)","invoicingOption": "(val)","dunningCampaignId": "(val)","lastBilledDate": "(val)","previousLastBilledDate": "(val)","finalBill": "(val)","amountToDraw": "(val)","letterOfCreditReviewIndicator": "(val)","invoiceDocumentStatus": "(val)","locCreationType": "(val)","suspendInvoicing": "(val)","_primaryKey": "(val)"},
              {"awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","additionalFormsRequired": "(val)","autoApproveInvoice": "(val)","stopWork": "(val)","minInvoiceAmount": "(val)","invoicingOption": "(val)","dunningCampaignId": "(val)","lastBilledDate": "(val)","previousLastBilledDate": "(val)","finalBill": "(val)","amountToDraw": "(val)","letterOfCreditReviewIndicator": "(val)","invoiceDocumentStatus": "(val)","locCreationType": "(val)","suspendInvoicing": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Cgbs by Key [DELETE /award/api/v1/award-cgbs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Cgbs [DELETE /award/api/v1/award-cgbs/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Cgbs with Matching [DELETE /award/api/v1/award-cgbs/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + awardId (optional) - 
    + awardNumber (optional) - 
    + sequenceNumber (optional) - 
    + additionalFormsRequired (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 1.
    + autoApproveInvoice (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 1.
    + stopWork (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 1.
    + minInvoiceAmount (optional) - Minimum Invoice Amount. Maximum length is 22.
    + invoicingOption (optional) - Invoicing Option. Maximum length is 120.
    + dunningCampaignId (optional) - Dunning Campaign. Maximum length is 4.
    + lastBilledDate (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 21.
    + previousLastBilledDate (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 21.
    + finalBill (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 1.
    + amountToDraw (optional) - Amount To Draw. Maximum length is 22.
    + letterOfCreditReviewIndicator (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 1.
    + invoiceDocumentStatus (optional) - Invoice Document Status. Maximum length is 45.
    + locCreationType (optional) - Letter Of Credit Creation Type. Maximum length is 45.
    + suspendInvoicing (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
