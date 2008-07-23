CREATE OR REPLACE PROCEDURE          DW_GET_PROPOSAL_KEY_PERSONS (
						as_proposal_num in OSP$EPS_PROP_KEY_PERSONS.proposal_number%type,
						acur_proposal_key_persons IN OUT result_sets.cur_generic) is
begin
open acur_proposal_key_persons for
	select * from OSP$EPS_PROP_KEY_PERSONS
	where proposal_number = as_proposal_num
	ORDER BY OSP$EPS_PROP_KEY_PERSONS.PERSON_NAME ASC ; -- Added for Case # 2405
end;
/

