CREATE OR REPLACE FUNCTION fn_check_right_to_print_s2s (
	AS_USER_ID IN OSP$S2S_OPP_FORMS.UPDATE_USER%TYPE,
    AS_PROPOSAL_NUMBER   IN OSP$S2S_OPP_FORMS.PROPOSAL_NUMBER%TYPE,
	AS_FORM_NAME IN OSP$S2S_OPP_FORMS.FORM_NAME%TYPE)

RETURN INTEGER IS

	has_right integer := 0;

BEGIN
  has_right := FN_USER_HAS_PROP_RIGHT(AS_USER_ID,AS_PROPOSAL_NUMBER,'MODIFY_BUDGET');
  if(has_right=0) then
  	     has_right := FN_USER_HAS_PROP_RIGHT(AS_USER_ID,AS_PROPOSAL_NUMBER,'VIEW_BUDGET');
  end if;
  return has_right;
  exception
  		  when others then
		  	   return 0;
END ;
/

