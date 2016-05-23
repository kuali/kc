## Institutional Proposal Special Review Exemptions [/instprop/api/v1/institutional-proposal-special-review-exemptions/]

### Get Institutional Proposal Special Review Exemptions by Key [GET /instprop/api/v1/institutional-proposal-special-review-exemptions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalSpecialReviewExemptionId": "(val)","exemptionTypeCode": "(val)","institutionalProposalSpecialReview.proposalSpecialReviewId": "(val)","_primaryKey": "(val)"}

### Get All Institutional Proposal Special Review Exemptions [GET /instprop/api/v1/institutional-proposal-special-review-exemptions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalSpecialReviewExemptionId": "(val)","exemptionTypeCode": "(val)","institutionalProposalSpecialReview.proposalSpecialReviewId": "(val)","_primaryKey": "(val)"},
              {"proposalSpecialReviewExemptionId": "(val)","exemptionTypeCode": "(val)","institutionalProposalSpecialReview.proposalSpecialReviewId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Institutional Proposal Special Review Exemptions with Filtering [GET /instprop/api/v1/institutional-proposal-special-review-exemptions/]
    
+ Parameters

    + proposalSpecialReviewExemptionId (optional) - Proposal Special Review Exemption Id. Maximum length is 22.
    + exemptionTypeCode (optional) - Exemption #. Maximum length is 3.
    + institutionalProposalSpecialReview.proposalSpecialReviewId (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalSpecialReviewExemptionId": "(val)","exemptionTypeCode": "(val)","institutionalProposalSpecialReview.proposalSpecialReviewId": "(val)","_primaryKey": "(val)"},
              {"proposalSpecialReviewExemptionId": "(val)","exemptionTypeCode": "(val)","institutionalProposalSpecialReview.proposalSpecialReviewId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Institutional Proposal Special Review Exemptions [GET /instprop/api/v1/institutional-proposal-special-review-exemptions/]
	                                          
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
    
            {"columns":["proposalSpecialReviewExemptionId","exemptionTypeCode","institutionalProposalSpecialReview.proposalSpecialReviewId"],"primaryKey":"proposalSpecialReviewExemptionId"}
		
### Get Blueprint API specification for Institutional Proposal Special Review Exemptions [GET /instprop/api/v1/institutional-proposal-special-review-exemptions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Institutional Proposal Special Review Exemptions.md"
            transfer-encoding:chunked
