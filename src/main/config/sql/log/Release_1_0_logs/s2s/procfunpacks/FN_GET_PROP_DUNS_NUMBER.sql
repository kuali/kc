CREATE OR REPLACE function FN_GET_PROP_DUNS_NUMBER(
		as_proposal_number in OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE)
return varchar2
is
ls_duns_number osp$organization.DUNS_NUMBER%type;
begin
  SELECT DUNS_NUMBER
    INTO ls_duns_number
    FROM OSP$ORGANIZATION ORG,OSP$EPS_PROPOSAL PROP
	 WHERE PROP.PROPOSAL_NUMBER = as_proposal_number
		AND PROP.ORGANIZATION_ID = ORG.ORGANIZATION_ID;
return ls_duns_number;
exception
	when others then
		RETURN '';
end;
/

