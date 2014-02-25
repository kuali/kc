CREATE OR REPLACE function fn_proposal_overrides_exists
	(as_source_proposal in OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE )
return number is

	ll_count	number;

BEGIN
	ll_count := 0;
-- 
-- 	SELECT  count(*)
-- 		INTO    ll_count
-- 	   FROM    OSP$EPS_PROP_CHANGED_DATA
-- 		WHERE   OSP$EPS_PROP_CHANGED_DATA.proposal_number = as_source_proposal;

--	return ll_count;
	return 0;

end;
/

