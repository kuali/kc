create or replace procedure dw_get_prop_person_for_one ( 
						as_proposal_num in osp$eps_prop_person.proposal_number%type, 
						as_person_id in osp$eps_prop_person.person_id%type,
						acur_prop_person IN OUT result_sets.cur_generic) is 
begin 
open acur_prop_person for 
	select * from osp$eps_prop_person
	where proposal_number = as_proposal_num
		and person_id = as_person_id; 
end; 

/
  
