
CREATE OR REPLACE package s2sPHSCareerDevpkg as

           
procedure getCitizenship
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) ;
           
procedure getApplicationType
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic);  

		  					 
end;
/

CREATE OR REPLACE package body s2sPHSCareerDevpkg as

-------------------------------
-- procedure get_citizenship
-- check parameter 'PI_CITIZENSHIP_FROM_CUSTOM_DATA'
-- if it is 1, get citizenship info from custom element (default) 
-- if it is 0, get citizenship from warehouse table 
-------------------------------
procedure getCitizenship
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) 
is
 ls_person_id OSP$EPS_PROP_INVESTIGATORS.PERSON_ID%type;
 ls_citizeninfo varchar2(50);
 ls_citizen_source osp$parameter.value%type;
 
 begin
 
  begin
   --check parameter 
    select value
	into   ls_citizen_source
	from   osp$parameter
	where  parameter = 'PI_CITIZENSHIP_FROM_CUSTOM_DATA';
    
  EXCEPTION
	When NO_DATA_FOUND then
     ls_citizen_source := 1;
  end;

  if (ls_citizen_source = 0) then
   begin 
	--get person id
     SELECT   PERSON_ID
     INTO     ls_person_id
	 FROM     OSP$eps_PROP_INVESTIGATORS 
	 WHERE 	  PROPOSAL_NUMBER = as_proposal_number
     AND      PRINCIPAL_INVESTIGATOR_FLAG = 'Y';
      
     
    ls_citizeninfo := FN_GET_CITIZENSHIP_INFO(ls_person_id);

   EXCEPTION
	  When NO_DATA_FOUND then
		ls_citizeninfo := null;
   end;
   
  else
    ls_citizeninfo := FN_GET_CITIZENSHIP_CUSTINFO(as_proposal_number);
   
  
  end if;
  
    open cur_type for
    SELECT ls_citizeninfo as CITIZENSHIP
    FROM dual;
 end;
 
 -------------------------------
 -- procedure get_application_type
 -------------------------------
 procedure getApplicationType
           (as_proposal_number IN osp$eps_proposal.proposal_number%type,
            cur_type IN OUT result_sets.cur_generic) is
      
 begin
 	
	open cur_type for


	select   decode(proposal_type_code, 1,'New',
                                       3,'Continuation',
                                       4,'Revision',
                                       5,'Renewal',
                                       6,'Resubmission',
                                       7,'New','New') as APPLICATIONTYPE
		from		osp$eps_proposal
		where		proposal_number = as_proposal_number;

 
 end;

end;

/
