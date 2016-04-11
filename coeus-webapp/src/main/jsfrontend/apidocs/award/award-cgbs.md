## Award Cgbs [/research-sys/api/v1/award-cgbs/]

### Get Award Cgbs by Key [GET /research-sys/api/v1/award-cgbs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","additionalFormsRequired": "(val)","autoApproveInvoice": "(val)","stopWork": "(val)","minInvoiceAmount": "(val)","invoicingOption": "(val)","dunningCampaignId": "(val)","lastBilledDate": "(val)","previousLastBilledDate": "(val)","finalBill": "(val)","amountToDraw": "(val)","letterOfCreditReviewIndicator": "(val)","invoiceDocumentStatus": "(val)","locCreationType": "(val)","suspendInvoicing": "(val)","_primaryKey": "(val)"}

### Get All Award Cgbs [GET /research-sys/api/v1/award-cgbs/]
	 
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

### Get All Award Cgbs with Filtering [GET /research-sys/api/v1/award-cgbs/]
    
+ Parameters

        + awardId
            + awardNumber
            + sequenceNumber
            + additionalFormsRequired
            + autoApproveInvoice
            + stopWork
            + minInvoiceAmount
            + invoicingOption
            + dunningCampaignId
            + lastBilledDate
            + previousLastBilledDate
            + finalBill
            + amountToDraw
            + letterOfCreditReviewIndicator
            + invoiceDocumentStatus
            + locCreationType
            + suspendInvoicing

            
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
			
### Get Schema for Award Cgbs [GET /research-sys/api/v1/award-cgbs/]
	                                          
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
		
### Get Blueprint API specification for Award Cgbs [GET /research-sys/api/v1/award-cgbs/]
	 
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


### Update Award Cgbs [PUT /research-sys/api/v1/award-cgbs/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","additionalFormsRequired": "(val)","autoApproveInvoice": "(val)","stopWork": "(val)","minInvoiceAmount": "(val)","invoicingOption": "(val)","dunningCampaignId": "(val)","lastBilledDate": "(val)","previousLastBilledDate": "(val)","finalBill": "(val)","amountToDraw": "(val)","letterOfCreditReviewIndicator": "(val)","invoiceDocumentStatus": "(val)","locCreationType": "(val)","suspendInvoicing": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Cgbs [PUT /research-sys/api/v1/award-cgbs/]

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

### Insert Award Cgbs [POST /research-sys/api/v1/award-cgbs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","additionalFormsRequired": "(val)","autoApproveInvoice": "(val)","stopWork": "(val)","minInvoiceAmount": "(val)","invoicingOption": "(val)","dunningCampaignId": "(val)","lastBilledDate": "(val)","previousLastBilledDate": "(val)","finalBill": "(val)","amountToDraw": "(val)","letterOfCreditReviewIndicator": "(val)","invoiceDocumentStatus": "(val)","locCreationType": "(val)","suspendInvoicing": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","additionalFormsRequired": "(val)","autoApproveInvoice": "(val)","stopWork": "(val)","minInvoiceAmount": "(val)","invoicingOption": "(val)","dunningCampaignId": "(val)","lastBilledDate": "(val)","previousLastBilledDate": "(val)","finalBill": "(val)","amountToDraw": "(val)","letterOfCreditReviewIndicator": "(val)","invoiceDocumentStatus": "(val)","locCreationType": "(val)","suspendInvoicing": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Cgbs [POST /research-sys/api/v1/award-cgbs/]

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
            
### Delete Award Cgbs by Key [DELETE /research-sys/api/v1/award-cgbs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Cgbs [DELETE /research-sys/api/v1/award-cgbs/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Cgbs with Matching [DELETE /research-sys/api/v1/award-cgbs/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + awardId
            + awardNumber
            + sequenceNumber
            + additionalFormsRequired
            + autoApproveInvoice
            + stopWork
            + minInvoiceAmount
            + invoicingOption
            + dunningCampaignId
            + lastBilledDate
            + previousLastBilledDate
            + finalBill
            + amountToDraw
            + letterOfCreditReviewIndicator
            + invoiceDocumentStatus
            + locCreationType
            + suspendInvoicing

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
