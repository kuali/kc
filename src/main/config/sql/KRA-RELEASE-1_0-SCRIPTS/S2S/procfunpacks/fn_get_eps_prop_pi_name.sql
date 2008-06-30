
  CREATE OR REPLACE FUNCTION FN_GET_EPS_PROP_PI_NAME (
		as_proposal_number in OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE)
return varchar2
is
ls_pi_name osp$eps_prop_investigators.PERSON_NAME%type := '';
begin
  SELECT person_name
    INTO ls_pi_name
    FROM OSP$EPS_PROP_INVESTIGATORS
	 WHERE OSP$EPS_PROP_INVESTIGATORS.PROPOSAL_NUMBER = as_proposal_number
		AND OSP$EPS_PROP_INVESTIGATORS.PRINCIPAL_INVESTIGATOR_FLAG = 'Y';
return ls_pi_name;
exception
	when others then
		RETURN '';
end;
/
 