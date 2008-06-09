
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
 


  CREATE OR REPLACE PACKAGE  S2SNASAOTHERPROJECTINFORPKG as

procedure get_historic
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic);

procedure get_pro_internation_partici
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic);

procedure get_person_info (as_proposal_number in osp$eps_proposal.proposal_number%type,
                           as_person_id IN osp$eps_prop_person.person_id%type,
                           as_mit_person in varchar2,
                           cur_type IN OUT result_sets.cur_generic);

procedure get_persons
     (as_proposal_number in osp$eps_proposal.proposal_number%type,
		 cur_type IN OUT result_sets.cur_generic);

end;
/
CREATE OR REPLACE PACKAGE BODY  S2SNASAOTHERPROJECTINFORPKG as
-------------------------------
-- procedure get_historic
-------------------------------
procedure get_historic
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) is

begin
	open cur_type for

			select decode(answer, 'Y','Y: Yes','N','N: No') as HISTORIC,
			       explanation as EXPLAIN
			from osp$eps_prop_ynq
			where proposal_number = as_proposal_number
			and 	question_id = 'G6';



end;



-------------------------------
-- procedure get_pro_internation_partici
-------------------------------
procedure get_pro_internation_partici
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) is

begin
	open cur_type for
		select decode(answer,'Y','Y: Yes','N','N: No') as INTERNATIONAL_PARTICIPATION,
			explanation as EXPLAIN
		from osp$eps_prop_ynq
		where proposal_number = as_proposal_number
		and 	question_id = 'H1';
end;



----------------------------------
-- procedure get_person_info
----------------------------------

procedure get_person_info (as_proposal_number in osp$eps_proposal.proposal_number%type,
                           as_person_id IN osp$eps_prop_person.person_id%type,
                           as_mit_person in varchar2,
                           cur_type IN OUT result_sets.cur_generic)
is

ls_foreign   varchar2(3);
ls_citizenship  osp$eps_prop_person.country_of_citizenship%type;
ls_country_code  osp$rolodex.country_code%type;
ls_sponsor_type number;
ls_sponsor_code osp$sponsor.sponsor_code%type;

 begin --1

   --if person is from person table (not rolodex), check citizenship
   if (as_mit_person = 'TRUE') then
		SELECT decode(COUNTRY_OF_CITIZENSHIP,null,'USA',COUNTRY_OF_CITIZENSHIP)
      INTO   ls_citizenship
      FROM   OSP$EPS_PROP_PERSON
      WHERE  PROPOSAL_NUMBER = as_proposal_number
      AND    PERSON_ID = as_person_id;

      if (ls_citizenship in ( 'USA','PRI','VIR')) then
           ls_foreign := 'NO';
      else
           ls_foreign := 'YES';
      end if;

   else
     --rolodex person
     --check organization from rolodex record to see if it is foreign
     -- If there is no sponsor in rolodex record, check country from rolodex address
     BEGIN
       begin
		  SELECT   s.sponsor_type_code,
                 s.sponsor_code
		  INTO     ls_sponsor_type,
                 ls_sponsor_code
		  FROM   	osp$rolodex r, OSP$sponsor s
		  WHERE  	r.rolodex_id = as_person_id
		  AND 	   r.sponsor_code = s.sponsor_code;

        if (ls_sponsor_type > 9) then
          ls_foreign := 'YES';
        else
          ls_foreign := 'NO';
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
      END;
      end if;



 		open cur_type for
 		select  ls_foreign as FOREIGN
		from 	 dual;

 end; --1


 ---------------------------------------------------------------------
--added for case 3135
 -- procedure get_persons
 -- this gets pi and co-invs and any key persons who are collaborators
 ---------------------------------------------------------------------
procedure get_persons ( as_proposal_number in osp$eps_proposal.proposal_number%type,
							   cur_type IN OUT result_sets.cur_generic)
is
	begin
   open cur_type for

 		SELECT 	PP.PERSON_ID , pp.sort_id ,
   				PP.LAST_NAME,
   				DECODE(PP.FIRST_NAME,NULL,' ',PP.FIRST_NAME) FIRST_NAME,
   				DECODE( PP.MIDDLE_NAME,NULL,' ',PP.MIDDLE_NAME) MIDDLE_NAME,
     				'PD/PI' as PROJECT_ROLE,
              	PI.NON_MIT_PERSON_FLAG as NON_MIT_PERSON
		FROM  	OSP$EPS_PROP_PERSON PP, OSP$eps_PROP_INVESTIGATORS PI
		WHERE 	PP.PROPOSAL_NUMBER = as_proposal_number
		AND 		PP.PERSON_ID = PI.PERSON_ID
		AND 		PP.PROPOSAL_NUMBER = PI.PROPOSAL_NUMBER
      AND      PI.PRINCIPAL_INVESTIGATOR_FLAG = 'Y'

      UNION

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
 