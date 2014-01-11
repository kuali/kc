set define off;

CREATE OR REPLACE package s2sNasaPIandAORSmtDtShtPkg as

procedure get_aor_name
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) ;
           
procedure get_pi_name
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) ;
           
procedure get_us_gove_participation
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic);  
           
procedure get_pi_salary
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) ;   

procedure get_international_partici
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic);
           
procedure get_ERA_COMMONS_USER_NAME
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic);
		  					 
end;
/

CREATE OR REPLACE package body s2sNasaPIandAORSmtDtShtPkg as

-------------------------------
-- procedure get_aor_name
-------------------------------
procedure get_aor_name
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) 
is
     
 begin
	GET_AUTHORIZED_SIGNER(as_proposal_number,cur_type);

 end;
 
 -------------------------------
 -- procedure get_pi_name
 -------------------------------
 procedure get_pi_name
           (as_proposal_number IN osp$eps_proposal.proposal_number%type,
            cur_type IN OUT result_sets.cur_generic) is
      
 begin
 	s2sPHS398CoverPageSupplemtPkg.get_Pi_name(as_proposal_number,cur_type);
 end;

-------------------------------
-- procedure get_us_gove_participation
-------------------------------
procedure get_us_gove_participation
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) is	
ls_answer varchar2(10);
ls_explanation varchar2(30);
ls_string varchar2(200);
begin
		BEGIN
			select decode(answer, 'Y','Y: Yes','N','N: No'), 
			       explanation

			into	ls_answer,ls_explanation
			from osp$eps_prop_ynq
			where proposal_number = as_proposal_number
			and 	question_id = '24'; 
				
			CASE   	ls_explanation
				WHEN '101' THEN ls_string := '101: Agency for International Development';
				WHEN '102' THEN ls_string := '102: Air Force Research Laboratory';
				WHEN '103' THEN ls_string := '103: Army Research Laboratory';
				WHEN '104' THEN ls_string := '104: Center for Disease Control and Prevention';
				WHEN '105' THEN ls_string := '105: Coast Guard';
				WHEN '106' THEN ls_string := '106: Customs Service';
				WHEN '107' THEN ls_string := '107: Defense Advanced Research Projects Agency';
				WHEN '108' THEN ls_string := '108: Department of Agriculture (USDA)';
				WHEN '109' THEN ls_string := '109: Department of Commerce (DOC)';
				WHEN '110' THEN ls_string := '110: Department of Defense (DOD)';
				WHEN '111' THEN ls_string := '111: Department of Education (ED)';
				WHEN '112' THEN ls_string := '112: Department of Energy (DOE)';
				WHEN '113' THEN ls_string := '113: Department of Health and Human Services (HHS)';
				WHEN '114' THEN ls_string := '114: Department of Homeland Security (DHS)';
				WHEN '115' THEN ls_string := '115: Department of Justice (DOJ)';
				WHEN '116' THEN ls_string := '116: Department of State (DOS)';
				WHEN '117' THEN ls_string := '117: Department of the Air Force';
				WHEN '118' THEN ls_string := '118: Department of the Army';
				WHEN '119' THEN ls_string := '119: Department of the Interior (DOI)';
				WHEN '120' THEN ls_string := '120: Department of the Navy';
				WHEN '121' THEN ls_string := '121: Department of Transportation (DOT)';
				WHEN '122' THEN ls_string := '122: Department of Veterans Affairs (VA)';
				WHEN '123' THEN ls_string := '123: Environmental Protection Agency (EPA)';
				WHEN '124' THEN ls_string := '124: Federal Aviation Administration (FAA)';
				WHEN '125' THEN ls_string := '125: Federal Emergency Management Agency (FEMA)';
				WHEN '126' THEN ls_string := '126: Federal Maritime Commission';
				WHEN '127' THEN ls_string := '127: Fish and Wildlife Service';
				WHEN '128' THEN ls_string := '128: Forest Service';
				WHEN '129' THEN ls_string := '129: NASA Ames Research Center';
				WHEN '130' THEN ls_string := '130: NASA Dryden Flight Research Center';
				WHEN '131' THEN ls_string := '131: NASA Glenn Research Center';
				WHEN '132' THEN ls_string := '132: NASA Goddard Space Flight Center';
				WHEN '133' THEN ls_string := '133: NASA Headquarters';
				WHEN '134' THEN ls_string := '134: NASA Johnson Space Center';
				WHEN '135' THEN ls_string := '135: NASA Kennedy Space Center';
				WHEN '136' THEN ls_string := '136: NASA Langley Research Center';
				WHEN '137' THEN ls_string := '137: NASA Marshall Space Flight Center';
				WHEN '138' THEN ls_string := '138: NASA Stennis Space Center';
				WHEN '139' THEN ls_string := '139: National Institute of Standards &amp; Technology (NIST)';
				WHEN '140' THEN ls_string := '140: National Institutes of Health (NIH)';
				WHEN '141' THEN ls_string := '141: National Oceanic and Atmospheric Administration (NOAA)';
				WHEN '142' THEN ls_string := '142: National Park Service';
				WHEN '143' THEN ls_string := '143: National Science Foundation (NSF)';
				WHEN '144' THEN ls_string := '144: Naval Observatory';
				WHEN '145' THEN ls_string := '145: Naval Research Laboratory';
				WHEN '146' THEN ls_string := '146: Other';
				WHEN '147' THEN ls_string := '147: Smithsonian Institution';
				WHEN '148' THEN ls_string := '148: United States Geological Survey (USGS)';
				WHEN '149' THEN ls_string := '149: United States Marine Corps';
				WHEN '150' THEN ls_string := '150: Walter Reed Army Institute Research';
				ELSE ls_string := null;
			END CASE	;
		EXCEPTION
			When NO_DATA_FOUND then
			ls_answer := null;
			ls_string := null;
		END;
		open cur_type for
		select ls_answer as us_government_participation,
			ls_string as federal_agency_dollar
		from dual;
		
	
end;

-------------------------------
 -- procedure get_pi_salary
 -------------------------------
 procedure get_pi_salary
           (as_proposal_number IN osp$eps_proposal.proposal_number%type,
            cur_type IN OUT result_sets.cur_generic) is
 li_version number;
 li_amount	number;
 begin
		li_version := fn_get_version(as_proposal_number);
		if li_version > 0 then 
		SELECT   Decode(sum(salary_requested),null,0,sum(salary_requested)) 
		into     li_amount 
	   FROM   	OSP$eps_PROP_INVESTIGATORS PI,
			OSP$BUDGET_PERSONNEL_DETAILS PD
  		WHERE  	PD.PROPOSAL_NUMBER = as_proposal_number
  		AND 	PD.PROPOSAL_NUMBER = PI.PROPOSAL_NUMBER
		AND 	PD.PERSON_ID = PI.PERSON_ID
		AND     PI.PRINCIPAL_INVESTIGATOR_FLAG = 'Y'
      		AND     PD.VERSION_NUMBER= li_version;
  		
		else
			li_amount := null;
		end if;

 		open cur_type for
 	
 		select li_amount as SALARY_REQUESTED
		from 	 dual;
 	
 end;
 
-------------------------------
-- procedure get_international_partici
-------------------------------
procedure get_international_partici
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) is
     
begin
	open cur_type for
		select decode(answer,'Y','Y: Yes','N','N: No') as international_participation
		from osp$eps_prop_ynq
		where proposal_number = as_proposal_number
		and 	question_id = '25';	
end;


-------------------------------
-- procedure get_ERA_COMMONS_USER_NAME
-------------------------------
procedure get_ERA_COMMONS_USER_NAME
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) is
     
begin
	open cur_type for
		SELECT ERA_COMMONS_USER_NAME
            	FROM   OSP$EPS_PROP_PERSON P, OSP$EPS_PROP_INVESTIGATORS I
            	WHERE  P.PROPOSAL_NUMBER = as_proposal_number
		AND    P.PROPOSAL_NUMBER = I.PROPOSAL_NUMBER
            	AND    P.PERSON_ID = I.PERSON_ID
            	AND    I.PRINCIPAL_INVESTIGATOR_FLAG ='Y';
end;

end;
/
