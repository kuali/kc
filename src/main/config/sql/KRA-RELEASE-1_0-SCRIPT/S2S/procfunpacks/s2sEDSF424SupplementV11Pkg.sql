CREATE OR REPLACE package s2sEDSF424SupplementV11Pkg as

         
procedure getStateName
          (as_state_code IN osp$state_code.state_code%type,
           cur_type IN OUT result_sets.cur_generic);
           
procedure getCountryName
          (as_country_code IN osp$country_code.country_code%type,
           cur_type IN OUT result_sets.cur_generic) ;
           
procedure getHumanAssurance 
	  (as_proposal_number in osp$eps_proposal.proposal_number%type,
	   cur_proposal IN OUT result_sets.cur_generic) ;    

procedure getApplicantExp
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic);                
           

end;
/

CREATE OR REPLACE package body s2sEDSF424SupplementV11Pkg as

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
	s2sSF424V2Pkg.getCountryName(as_country_code,	cur_type);			
				
end;

----------------------------------
-- procedure getHumanAssurance
----------------------------------

procedure getHumanAssurance (as_proposal_number in osp$eps_proposal.proposal_number%type,
			cur_proposal IN OUT result_sets.cur_generic)
is
 begin
 open cur_proposal for
 	
     SELECT   osp$organization.HUMAN_SUB_ASSURANCE
     FROM     osp$eps_proposal, osp$organization
     WHERE    osp$eps_proposal.PROPOSAL_NUMBER = as_proposal_number 
     and      osp$eps_proposal.organization_id = osp$organization.organization_id;
 	
end;

-------------------------------
-- procedure getApplicantExp
-------------------------------
procedure getApplicantExp
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) is	

begin
	open cur_type for
		
			select decode(answer, 'Y','Y: Yes','N','N: No','X','NA: Not Applicable') as APPLICANT_EXPERIENCE, 
			       explanation as EXPLAIN
			from osp$eps_prop_ynq
			where proposal_number = as_proposal_number
			and 	question_id = '26'; 
		
		
	
end;

end;
/
