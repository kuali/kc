create or replace FUNCTION FN_GET_CONG_DIST(

    AS_PROPOSAL_NUMBER      OSP$EPS_PROP_SITES.PROPOSAL_NUMBER%TYPE ,
    AS_SITE_NUMBER          osp$eps_prop_cong_district.site_number%type )
return varchar2 is

ls_cong_Dist  OSP$EPS_PROP_CONG_DISTRICT.CONG_DISTRICT%TYPE;
cong_Dist     varchar2(50) := ' ';
li_count      number;

CURSOR c_Cong_Dist is
   SELECT  d.cong_district 
    FROM   OSP$EPS_PROP_SITES S,
           osp$eps_prop_cong_district d
    WHERE  S.PROPOSAL_NUMBER =  as_proposal_number
	AND    S.PROPOSAL_NUMBER = D.PROPOSAL_NUMBER
    AND    S.SITE_NUMBER = as_site_number
	AND    D.SITE_NUMBER = S.SITE_NUMBER;

	
BEGIN

	  li_count := 0;
	  OPEN c_Cong_Dist;
	  LOOP
	  FETCH c_Cong_Dist INTO ls_cong_Dist;
	  EXIT WHEN  c_Cong_Dist%NOTFOUND;   
	  
	  li_count := li_count + 1;
	  if (li_count = 1)	 then
	     cong_Dist := ls_cong_dist;
	  else
	     cong_Dist := cong_Dist || ', ' || ls_cong_dist;
	  end if; 
	  end loop;
	  close c_cong_dist;
	 
	
return cong_Dist;
END;
/