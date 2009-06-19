CREATE OR REPLACE PROCEDURE          GET_PROPOSAL_INVESTIGATORS (
						as_proposal_num in OSP$EPS_PROP_INVESTIGATORS.proposal_number%type,
						acur_proposal_investigators IN OUT result_sets.cur_generic) is
begin
open acur_proposal_investigators for
	select * from OSP$EPS_PROP_INVESTIGATORS
	where proposal_number = as_proposal_num
	ORDER BY OSP$EPS_PROP_INVESTIGATORS.PRINCIPAL_INVESTIGATOR_FLAG DESC,
		OSP$EPS_PROP_INVESTIGATORS.PERSON_NAME ASC;-- Added for Case # 2405
end;
/

