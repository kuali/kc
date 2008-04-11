CREATE OR REPLACE FUNCTION FN_CHECK_S2S_CANDIDATE (

    AS_PROPOSAL_NUMBER   IN OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE)

RETURN INTEGER IS

    LL_COUNT NUMBER;
	li_Ret number;
	
	ls_ProgramAnnNumber osp$eps_proposal.PROGRAM_ANNOUNCEMENT_NUMBER%type;
	ls_cfda				osp$eps_proposal.CFDA_NUMBER%type;
	li_SponsorType  	osp$sponsor_type.SPONSOR_TYPE_CODE%type;
BEGIN


  select p.PROGRAM_ANNOUNCEMENT_NUMBER, p.CFDA_NUMBER, s.SPONSOR_TYPE_CODE
  into ls_ProgramAnnNumber, ls_cfda, li_SponsorType 
  from osp$eps_proposal p, osp$sponsor s
  where p.proposal_number = AS_PROPOSAL_NUMBER and
   		upper(ltrim(rtrim(p.sponsor_code))) = upper(ltrim(rtrim(s.sponsor_code)));
		

  if (((ls_ProgramAnnNumber is null) or (length(rtrim(ls_ProgramAnnNumber)) = 0)) and
     ((ls_cfda is null) or (length(rtrim(ls_cfda)) = 0)))   then
  	 li_Ret := -1;
  elsif li_SponsorType <> 0 then
  	 li_Ret := -1;
  else
  	  li_Ret := 1;
	  
  end if;
  
  return li_Ret;
  
  exception
  		  when others then
		  	   return -1;
END ;
/

