
  CREATE OR REPLACE PACKAGE S2SNASASENKEYPERSMTDTSHTPKG as

procedure getRolodexInfo (as_proposal_number in osp$eps_proposal.proposal_number%type,
                           as_person_id IN osp$eps_prop_person.person_id%type,
                           cur_type IN OUT result_sets.cur_generic);

procedure get_sponsor_admini_hierarchy(as_sponsor_code IN osp$sponsor.sponsor_code%type,
 			cur_type IN OUT result_sets.cur_generic);

procedure get_sponsor_gov_agen(as_sponsor_code IN osp$sponsor.sponsor_code%type,
   			cur_type IN OUT result_sets.cur_generic);

procedure get_coinvestigators ( as_proposal_number in osp$eps_proposal.proposal_number%type,
									 cur_type IN OUT result_sets.cur_generic);

end;

/
CREATE OR REPLACE PACKAGE BODY  S2SNASASENKEYPERSMTDTSHTPKG as

----------------------------------
-- procedure getrolodexInfo
----------------------------------

procedure getrolodexinfo (as_proposal_number in osp$eps_proposal.proposal_number%type,
                           as_person_id IN osp$eps_prop_person.person_id%type,
                           cur_type IN OUT result_sets.cur_generic)
is

 ls_foreign varchar2(20) := 'NO';
 ls_country_code  osp$rolodex.country_code%type;
 ls_sponsor_type number;
 ls_sponsor_code osp$sponsor.sponsor_code%type;
 ls_us_gov  varchar2(10) := 'NO';

 begin
   --use sponsor from rolodex to determine if foreign or us

   begin
		SELECT   s.sponsor_type_code,
               s.sponsor_code
		INTO     ls_sponsor_type,
               ls_sponsor_code
		FROM   	osp$rolodex r, OSP$sponsor s
		WHERE  	r.rolodex_id = as_person_id
		AND 	   r.sponsor_code = s.sponsor_code;

      if (ls_sponsor_type = 0) then
          ls_us_gov := 'YES';
      elsif (ls_sponsor_type > 9) then
          ls_foreign := 'YES';
      end if;




			EXCEPTION
				when no_data_found then
				ls_sponsor_type := -1;

    end;



    if ls_sponsor_type = -1 then
         --if no sponsor in rolodex then use person's country - default to usa
         begin

            select   country_code
		   	INTO     ls_country_code
		   	FROM   	OSP$rolodex
		     	WHERE    rolodex_id = as_person_id;

            if (ls_country_code in ( 'USA','PRI','VIR')) then
               ls_foreign := 'NO';
            else
               ls_foreign := 'YES';
            end if;

		     	EXCEPTION
		   		when no_data_found then
		   		ls_foreign := 'NO';
   		end;



      end if;


      begin
 		open cur_type for

 		select  ls_foreign as FOREIGN,
              ls_us_gov     as USGOV,
              ls_sponsor_code as SPONSOR_CODE
		from 	 dual;
      end;
 end;

 ----------------------------------
 -- procedure get_sponsor_admini_hierarchy
 ----------------------------------

 procedure get_sponsor_admini_hierarchy(as_sponsor_code IN osp$sponsor.sponsor_code%type,
 			cur_type IN OUT result_sets.cur_generic)
 is

  begin
  	 open cur_type for
  		SELECT 	LEVEL1
  		FROM 	OSP$SPONSOR_HIERARCHY
  		WHERE	UPPER(HIERARCHY_NAME) = 'ADMINISTERING ACTIVITY'
  		AND	SPONSOR_CODE = as_sponsor_code;


  end;


  ----------------------------------
   -- procedure get_sponsor_gov_agen
   ----------------------------------

   procedure get_sponsor_gov_agen(as_sponsor_code IN osp$sponsor.sponsor_code%type,
   			cur_type IN OUT result_sets.cur_generic)
   is

    begin
    	 open cur_type for
    		SELECT 	LEVEL1 as AGENCY_NAME
    		FROM 	OSP$SPONSOR_HIERARCHY
    		WHERE	UPPER(HIERARCHY_NAME) = 'GOVERNMENT AGENCY'
    		AND	SPONSOR_CODE = as_sponsor_code;


  end;

 ----------------------------------
 -- procedure get_coinvestigators
 -- this gets co-invs and any key persons who are collaborators
 ----------------------------------
procedure get_coinvestigators ( as_proposal_number in osp$eps_proposal.proposal_number%type,
									 cur_type IN OUT result_sets.cur_generic)
is
	begin
   open cur_type for

 		SELECT 	PP.PERSON_ID , pp.sort_id ,
   				PP.LAST_NAME,
   				DECODE(PP.FIRST_NAME,NULL,' ',PP.FIRST_NAME) FIRST_NAME,
   				DECODE( PP.MIDDLE_NAME,NULL,' ',PP.MIDDLE_NAME) MIDDLE_NAME,
     				'Co-PD/PI' as PROJECT_ROLE,
                 	PI.NON_MIT_PERSON_FLAG as NON_MIT_PERSON
		FROM  	OSP$EPS_PROP_PERSON PP, OSP$eps_PROP_INVESTIGATORS PI
		WHERE 	PP.PROPOSAL_NUMBER = as_proposal_number
		AND 		PP.PERSON_ID = PI.PERSON_ID
		AND 		PP.PROPOSAL_NUMBER = PI.PROPOSAL_NUMBER
      AND      PI.PRINCIPAL_INVESTIGATOR_FLAG = 'N'

      UNION

      SELECT 	PP.PERSON_ID, pp.sort_id ,
   				PP.LAST_NAME ,
   				DECODE(PP.FIRST_NAME, NULL,' ',PP.FIRST_NAME) FIRST_NAME,
   				DECODE(PP.MIDDLE_NAME,NULL,' ',PP.MIDDLE_NAME) MIDDLE_NAME,
   				upper(K.PROJECT_ROLE),
                K.NON_MIT_PERSON_FLAG as NON_MIT_PERSON
		FROM  	OSP$EPS_PROP_PERSON PP, OSP$EPS_PROP_KEY_PERSONS K
		WHERE 	PP.PROPOSAL_NUMBER = as_proposal_number
		AND 		PP.PERSON_ID = K.PERSON_ID
		AND		PP.PROPOSAL_NUMBER = K.PROPOSAL_NUMBER
      AND      UPPER(K.PROJECT_ROLE) = 'COLLABORATOR'


		order by sort_ID;


end;




end;
/
 