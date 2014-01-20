CREATE OR REPLACE package s2sPHS398CoverPageSupplemtPkg as

procedure get_Pi_name
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) ;

procedure get_is_new_investigator
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) ;

procedure get_pi_degrees
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) ;

procedure get_clinical_trial
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) ;

procedure get_contact_person_info
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) ;

procedure get_stem_cells
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) ;
           
procedure getStateName
          (as_state_code IN osp$state_code.state_code%type,
           cur_type IN OUT result_sets.cur_generic);
           
procedure getCountryName
          (as_country_code IN osp$country_code.country_code%type,
           cur_type IN OUT result_sets.cur_generic) ;             
		  					 
end;
/

CREATE OR REPLACE package body s2sPHS398CoverPageSupplemtPkg as

-------------------------------
-- procedure get_Pi_name
-------------------------------
procedure get_Pi_name
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) is
     
 begin
	open cur_type for
	 SELECT decode(P.FIRST_NAME,null,P.LAST_NAME,P.FIRST_NAME) as FIRST_NAME,
      	  P.MIDDLE_NAME,
     		  decode(P.LAST_NAME,null,P.FIRST_NAME,P.LAST_NAME) as LAST_NAME
    FROM   osp$eps_prop_person p,
           osp$eps_prop_investigators i
    WHERE  i.proposal_number = as_proposal_number
	 AND	  p.proposal_number = i.proposal_number
    AND    i.principal_investigator_flag='Y'
    AND    p.person_id = i.person_id;
 end;

-------------------------------
-- procedure get_is_new_investigator
-------------------------------
procedure get_is_new_investigator
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) is
     
begin
	open cur_type for
		select decode(answer,'Y','Yes','N','No') as is_new_investigator
		from osp$eps_prop_ynq
		where proposal_number = as_proposal_number
		and 	question_id = '13';		
end;

-------------------------------
-- procedure get_pi_degrees
-------------------------------
procedure get_pi_degrees
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) is

     
begin
	open cur_type for
		SELECT   PP.DEGREE_CODE
		FROM     OSP$EPS_PROP_PERSON_DEGREE PP,
               OSP$DEGREE_TYPE D,
               OSP$EPS_PROP_INVESTIGATORS I
      WHERE    PP.PROPOSAL_NUMBER = as_proposal_number
      AND      D.DEGREE_CODE = PP.DEGREE_CODE
      AND      I.PERSON_ID = PP.PERSON_ID
      AND      I.PRINCIPAL_INVESTIGATOR_FLAG = 'Y'
      AND      I.PROPOSAL_NUMBER = PP.PROPOSAL_NUMBER
      ORDER BY D.DEGREE_LEVEL DESC,
               PP.GRADUATION_DATE DESC;		
end;

-------------------------------
-- procedure get_clinical_trial
-------------------------------
procedure get_clinical_trial
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) is
--activity_type 	osp$eps_proposal.activity_type_code%type;
--phaseIIIClinicalTrial			osp$eps_prop_ynq.answer%type; 
activity_type 					varchar2(3);
phaseIII_clinical_trial		varchar2(3);   		
begin
		BEGIN
		select decode(activity_type_code,8,'Yes','No')
		into	activity_type		
		from 	osp$eps_proposal
		where proposal_number = as_proposal_number;
		EXCEPTION
				When NO_DATA_FOUND then
					activity_type := null;
		END;

		BEGIN
		select decode(answer,'Y','Yes','N','No')
		into	phaseIII_clinical_trial		
		from osp$eps_prop_ynq
		where proposal_number = as_proposal_number
		and 	question_id = '17';
		EXCEPTION
				When NO_DATA_FOUND then
					phaseIII_clinical_trial := null;
		END;

	open cur_type for
		--select decode(activity_type,8,'Yes','No') as is_clinical_trial,
		--select decode(phaseIII_clinical_trial,'Y','Yes','N','No') as is_phaseIII_clinical_trial
		select activity_type as is_clinical_trial,
		 		 phaseIII_clinical_trial as is_phaseIII_clinical_trial
		from 	 dual;		
end;

-------------------------------
-- procedure get_contact_person_info
-------------------------------
procedure get_contact_person_info
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) is	
begin
	open cur_type for
		SELECT decode(OSP$PERSON.FIRST_NAME, null, OSP$PERSON.LAST_NAME, OSP$PERSON.FIRST_NAME) as FIRST_NAME,
         	OSP$PERSON.MIDDLE_NAME,   
         	decode(OSP$PERSON.LAST_NAME,null,OSP$PERSON.FIRST_NAME, OSP$PERSON.LAST_NAME) as LAST_NAME,
				OSP$PERSON.OFFICE_PHONE, --fn_format_phone_number(OSP$PERSON.OFFICE_PHONE), 
				OSP$PERSON.FAX_NUMBER, --fn_format_phnoe_number(OSP$PERSON.FAX_NUMBER), 
				OSP$PERSON.EMAIL_ADDRESS,
				OSP$PERSON.PRIMARY_TITLE,
				decode(OSP$PERSON.ADDRESS_LINE_1, null, decode(OSP$PERSON.ADDRESS_LINE_2, null, OSP$PERSON.ADDRESS_LINE_3,OSP$PERSON.ADDRESS_LINE_2) ,OSP$PERSON.ADDRESS_LINE_1)as ADDRESS_LINE_1,
         	OSP$PERSON.ADDRESS_LINE_2,  
         	OSP$PERSON.CITY,   
         	OSP$PERSON.COUNTY,   
         	OSP$PERSON.STATE,   
         	OSP$PERSON.POSTAL_CODE,   
         	OSP$PERSON.COUNTRY_CODE
		FROM	OSP$PERSON, OSP$UNIT, OSP$EPS_PROP_UNITS
		WHERE	OSP$EPS_PROP_UNITS.PROPOSAL_NUMBER = as_proposal_number
		AND	OSP$EPS_PROP_UNITS.LEAD_UNIT_FLAG = 'Y'
		AND	OSP$EPS_PROP_UNITS.UNIT_NUMBER = OSP$UNIT.UNIT_NUMBER
		AND	OSP$UNIT.OSP_ADMINISTRATOR = OSP$PERSON.PERSON_ID;		
end;

-------------------------------
-- procedure get_stem_cells
-------------------------------
procedure get_stem_cells
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) is	
begin
	open cur_type for	

		select decode(answer, 'Y','Yes','N','No') as is_human_stem_cells_involved,
				explanation	
		from osp$eps_prop_ynq
		where proposal_number = as_proposal_number
		and 	question_id = '18';
	
end;

-------------------------------
-- procedure getStateName
-------------------------------
procedure getStateName
          (as_state_code IN osp$state_code.state_code%type,
           cur_type IN OUT result_sets.cur_generic) is

begin
	s2sSF424V2Pkg.getStateName(as_state_code,cur_type);
end;


-------------------------------
-- procedure getCountryName
-------------------------------
procedure getCountryName
          (as_country_code IN osp$country_code.country_code%type,
           cur_type IN OUT result_sets.cur_generic) is

begin
	s2sSF424V2Pkg.getCountryName(as_country_code,cur_type);
end;

end;
/
