
CREATE OR REPLACE package s2sNASASenKeyPerSmtDtShtPkg as

procedure get_person_info (as_proposal_number in osp$budget.proposal_number%type,
			as_person_id  in osp$budget_persons.person_id%type,
			cur_type IN OUT result_sets.cur_generic);
			
procedure get_sponsor_admini_hierarchy(as_sponsor_code IN osp$sponsor.sponsor_code%type,
 			cur_type IN OUT result_sets.cur_generic);

procedure get_sponsor_gov_agen(as_sponsor_code IN osp$sponsor.sponsor_code%type,
   			cur_type IN OUT result_sets.cur_generic);


end; 


/




CREATE OR REPLACE package body s2sNASASenKeyPerSmtDtShtPkg as

----------------------------------
-- procedure get_person_info
----------------------------------

procedure get_person_info(as_proposal_number IN osp$budget.proposal_number%type,
			as_person_id  in osp$budget_persons.person_id%type,
			cur_type IN OUT result_sets.cur_generic)
is
 li_version number;
 li_in_budget	number;
 ls_citizenship varchar2(30);
 ls_sponsor_type number;
 ls_sponsor_code varchar2(10);
 begin
 	
		li_version := fn_get_version(as_proposal_number);
		if li_version > 0 then
		    begin
			SELECT   count (person_id)
			INTO     li_in_budget
	   		FROM   	OSP$budget_persons
  			WHERE  	PROPOSAL_NUMBER = as_proposal_number
  			AND 	version_number = li_version
  			AND 	person_id = as_person_id;
  		
  			EXCEPTION
				when no_data_found then
		  		li_in_budget := 0;
                   end;
		else
			li_in_budget := 0;
		end if;
		
		begin
		   	SELECT   country_of_citizenship
		   	INTO     ls_citizenship
		   	FROM   	OSP$eps_prop_person
		     	WHERE  	PROPOSAL_NUMBER = as_proposal_number
		     	AND 	person_id = as_person_id;
		     		
		     	EXCEPTION
		   		when no_data_found then
		   		ls_citizenship := null;
   		end;
   		begin
			SELECT   s.sponsor_type_code,s.sponsor_code
			INTO     ls_sponsor_type, ls_sponsor_code
			FROM   	osp$rolodex r, OSP$sponsor s
			WHERE  	r.rolodex_id = as_person_id
			AND 	r.sponsor_code = s.sponsor_code;
				     		
			EXCEPTION
				when no_data_found then
				ls_sponsor_type := -1;
				ls_sponsor_code := null;
   		end;
	
 		open cur_type for

 		select li_in_budget as PERSON_IN_BUDGET,
 		       ls_citizenship as CITIZENSHIP,
 		       ls_sponsor_type as SPONSOR_TYPE,
 		       ls_sponsor_code as SPONSOR_CODE
		from 	 dual;

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
 

end;

/

