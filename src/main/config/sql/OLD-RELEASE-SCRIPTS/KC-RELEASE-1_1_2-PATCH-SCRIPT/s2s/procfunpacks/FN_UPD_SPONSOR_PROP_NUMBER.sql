CREATE OR REPLACE function FN_UPD_SPONSOR_PROP_NUMBER
	(as_proposal_number IN OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
	 as_sponsor_proposal_number IN OSP$EPS_PROPOSAL.SPONSOR_PROPOSAL_NUMBER%TYPE)
return number IS
	li_rc	number;
BEGIN

-- 	UPDATE OSP$EPS_PROPOSAL
--      	SET SPONSOR_PROPOSAL_NUMBER = as_sponsor_proposal_number
-- 	WHERE PROPOSAL_NUMBER = as_proposal_number ;
--  	UPDATE OSP$PROPOSAL a
--       	SET a.SPONSOR_PROPOSAL_NUMBER = as_sponsor_proposal_number
--  	WHERE a.PROPOSAL_NUMBER = (select INST_PROPOSAL_NUMBER
-- 						  		 from OSP$PROPOSAL_ADMIN_DETAILS b
-- 								 where DEV_PROPOSAL_NUMBER = as_proposal_number
-- 								 and INST_PROP_SEQUENCE_NUMBER =
-- 								 	 (select max(INST_PROP_SEQUENCE_NUMBER) from OSP$PROPOSAL_ADMIN_DETAILS
-- 									 									   where INST_PROPOSAL_NUMBER = b.INST_PROPOSAL_NUMBER) )
-- 		  and a.sequence_number = (select max(SEQUENCE_NUMBER) from OSP$PROPOSAL
-- 									 									   where PROPOSAL_NUMBER = a.proposal_number);
	return 1;

EXCEPTION
    WHEN OTHERS THEN
		raise_application_error(-20100, 'Error Updating Sponsor Proposal Number in OSP$EPS_PROPOSAL/OSP$PROPOSAL table - ' || SQLERRM);
		return -1;
END;
/

