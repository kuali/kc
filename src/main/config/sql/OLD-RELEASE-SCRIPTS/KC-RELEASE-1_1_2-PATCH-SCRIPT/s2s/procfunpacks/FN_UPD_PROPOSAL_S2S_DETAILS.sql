CREATE OR REPLACE function FN_UPD_PROPOSAL_S2S_DETAILS
	(as_proposal_number IN OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
	 as_cfda_number IN OSP$EPS_PROPOSAL.CFDA_NUMBER%TYPE,
	 as_program_number IN OSP$EPS_PROPOSAL.PROGRAM_ANNOUNCEMENT_NUMBER%TYPE,
	 as_program_title IN OSP$EPS_PROPOSAL.PROGRAM_ANNOUNCEMENT_TITLE%TYPE )
return number IS
	li_rc	number;

BEGIN

	UPDATE OSP$EPS_PROPOSAL
     	SET CFDA_NUMBER = as_cfda_number,
			PROGRAM_ANNOUNCEMENT_NUMBER = as_program_number,
			PROGRAM_ANNOUNCEMENT_TITLE = as_program_title
	WHERE PROPOSAL_NUMBER = as_proposal_number ;
	return 1;

EXCEPTION
    WHEN OTHERS THEN
		raise_application_error(-20100, 'Error Updating S2S Details in OSP$EPS_PROPOSAL table - ' || SQLERRM);
		return -1;
END;
/

