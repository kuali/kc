CREATE OR REPLACE function FN_DELETE_OPP_FORMS
	(as_proposal_number IN OSP$S2S_OPP_FORMS.PROPOSAL_NUMBER%TYPE)
return number IS
	li_rc	number;

BEGIN

	DELETE OSP$S2S_OPP_FORMS
	WHERE PROPOSAL_NUMBER = as_proposal_number;
	return 1;

EXCEPTION
    WHEN OTHERS THEN
		raise_application_error(-20100, 'Error Deleting Opportunity Forms from OSP$S2S_OPP_FORMS table - ' || SQLERRM);
		return -1;
END;
/

