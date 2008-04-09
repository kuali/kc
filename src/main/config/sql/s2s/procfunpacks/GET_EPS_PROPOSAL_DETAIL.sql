CREATE OR REPLACE procedure GET_EPS_PROPOSAL_DETAIL
   ( as_proposal_num IN osp$eps_proposal.proposal_number%TYPE,
     cur_proposal IN OUT result_sets.cur_generic) is

begin
open cur_proposal for

SELECT osp$eps_proposal.PROPOSAL_NUMBER,
         osp$eps_proposal.PROPOSAL_TYPE_CODE,
         osp$eps_proposal.STATUS_CODE,
         osp$eps_proposal.CREATION_STATUS_CODE,
		 osp$eps_proposal_status.description CREATION_STATUS_DESC,
         osp$eps_proposal.BASE_PROPOSAL_NUMBER,
         osp$eps_proposal.CONTINUED_FROM,
         osp$eps_proposal.TEMPLATE_FLAG,
         osp$eps_proposal.ORGANIZATION_ID,
         osp$eps_proposal.PERFORMING_ORGANIZATION_ID,
         osp$eps_proposal.CURRENT_ACCOUNT_NUMBER,
         osp$eps_proposal.CURRENT_AWARD_NUMBER,
         osp$eps_proposal.TITLE,
		 osp$eps_proposal.CFDA_NUMBER,
         osp$eps_proposal.SPONSOR_CODE,
			osp$eps_proposal.PRIME_SPONSOR_CODE,    --ENH013
         osp$eps_proposal.SPONSOR_PROPOSAL_NUMBER,
         osp$eps_proposal.INTR_COOP_ACTIVITIES_FLAG,
         osp$eps_proposal.INTR_COUNTRY_LIST,
         osp$eps_proposal.OTHER_AGENCY_FLAG,
         osp$eps_proposal.NOTICE_OF_OPPORTUNITY_CODE,
         osp$eps_proposal.PROGRAM_ANNOUNCEMENT_NUMBER,
         osp$eps_proposal.PROGRAM_ANNOUNCEMENT_TITLE,
         osp$eps_proposal.ACTIVITY_TYPE_CODE,
         osp$eps_proposal.REQUESTED_START_DATE_INITIAL,
         osp$eps_proposal.REQUESTED_START_DATE_TOTAL,
         osp$eps_proposal.REQUESTED_END_DATE_INITIAL,
         osp$eps_proposal.REQUESTED_END_DATE_TOTAL,
         osp$eps_proposal.DURATION_MONTHS,
         osp$eps_proposal.NUMBER_OF_COPIES,
         osp$eps_proposal.DEADLINE_DATE,
         osp$eps_proposal.DEADLINE_TYPE,
         osp$eps_proposal.MAILING_ADDRESS_ID,
         osp$eps_proposal.MAIL_BY,
         osp$eps_proposal.MAIL_TYPE,
         osp$eps_proposal.CARRIER_CODE_TYPE,
         osp$eps_proposal.CARRIER_CODE,
         osp$eps_proposal.MAIL_DESCRIPTION,
         osp$eps_proposal.MAIL_ACCOUNT_NUMBER,
         osp$eps_proposal.SUBCONTRACT_FLAG,
         osp$eps_proposal.NARRATIVE_STATUS,
         osp$eps_proposal.BUDGET_STATUS,
         osp$eps_proposal.OWNED_BY_UNIT,
         osp$eps_proposal.CREATE_TIMESTAMP,
         osp$eps_proposal.CREATE_USER,
         osp$eps_proposal.UPDATE_TIMESTAMP,
         osp$eps_proposal.UPDATE_USER ,
			osp$eps_proposal.NSF_CODE,  --ENH012
			'' ORGANIZATION_NAME,
			'' PERFORMING_ORGANIZATION_NAME,
			'' MAILING_ADDRESS,
		osp$proposal_type.DESCRIPTION as PROPOSAL_TYPE_DESC,
		osp$nsf_codes.DESCRIPTION as NSF_CODES_DESCRIPTION,
		OSP$NOTICE_OF_OPPORTUNITY.DESCRIPTION as NOTICE_OF_OPPOR_DESCRIPTION,
		OSP$ACTIVITY_TYPE.DESCRIPTION as ACTIVITY_TYPE_DESCRIPTION,
		osp$eps_proposal.AGENCY_PROGRAM_CODE ,--- For Proposal Development Enhancement Case#1621
		osp$eps_proposal.AGENCY_DIVISION_CODE --- For Proposal Development Enhancement Case#1621
    FROM osp$eps_proposal, osp$proposal_type, osp$nsf_codes, OSP$NOTICE_OF_OPPORTUNITY, OSP$ACTIVITY_TYPE, osp$eps_proposal_status
	WHERE osp$eps_proposal.PROPOSAL_TYPE_CODE = osp$proposal_type.PROPOSAL_TYPE_CODE
	and osp$eps_proposal.NSF_CODE = osp$nsf_codes.NSF_CODE(+)
	and osp$eps_proposal.NOTICE_OF_OPPORTUNITY_CODE = OSP$NOTICE_OF_OPPORTUNITY.NOTICE_OF_OPPORTUNITY_CODE(+)
	and osp$eps_proposal.ACTIVITY_TYPE_CODE = OSP$ACTIVITY_TYPE.ACTIVITY_TYPE_CODE
	and osp$eps_proposal.CREATION_STATUS_CODE = osp$eps_proposal_status.status_code(+)
	and osp$eps_proposal.PROPOSAL_NUMBER = as_proposal_num ;

end;
/

