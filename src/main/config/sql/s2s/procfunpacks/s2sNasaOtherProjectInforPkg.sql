CREATE OR REPLACE package s2sNasaOtherProjectInforPkg  as

procedure get_historic
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic);
           
procedure get_pro_internation_partici
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic);
           
procedure get_person_info 
	  (as_proposal_number in osp$budget.proposal_number%type,
	   as_person_id  in osp$budget_persons.person_id%type,
	   cur_type IN OUT result_sets.cur_generic);           
		  					 
end;
/

CREATE OR REPLACE package body s2sNasaOtherProjectInforPkg as
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

procedure get_person_info(as_proposal_number IN osp$budget.proposal_number%type,
			as_person_id  in osp$budget_persons.person_id%type,
			cur_type IN OUT result_sets.cur_generic) is
begin
	s2sNASASenKeyPerSmtDtShtPkg.get_person_info(as_proposal_number,as_person_id,cur_type);
end;

end;
/
