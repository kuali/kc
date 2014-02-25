CREATE OR REPLACE FUNCTION FN_CHECK_S2S_ATTR_MATCH (
    AS_PROPOSAL_NUMBER   IN OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE)
RETURN INTEGER IS
    LL_COUNT NUMBER;
	li_cfda_number osp$s2s_opportunity.cfda_number%type;
BEGIN

	 select nvl(count(p.proposal_number),0)
	 		into LL_COUNT
			from osp$eps_proposal p, osp$s2s_opportunity o
	 		where p.PROPOSAL_NUMBER = AS_PROPOSAL_NUMBER and
				  p.PROPOSAL_NUMBER = o.PROPOSAL_NUMBER and
				  p.PROGRAM_ANNOUNCEMENT_NUMBER = o.OPPORTUNITY_ID and
				  nvl(p.CFDA_NUMBER,0) = nvl(substr(o.CFDA_NUMBER,0,2)||substr(o.CFDA_NUMBER,4,6),0);--remove char '.'
	return LL_COUNT;
END ;
/

