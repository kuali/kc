insert into eps_proposal_document (document_number, obj_id, update_timestamp, update_user, ver_nbr)
values(1, sys_guid(), sysdate, 'jhulslander', 1);

insert into eps_prop_post_sub_status (definition, description, obj_id, status_code, update_timestamp, update_user, ver_nbr)
values ('defin', 'description', sys_guid(), 1, sysdate, 'jhulslander', 1);

insert into eps_proposal (document_number, post_sub_status_code, obj_id, proposal_number, is_hierarchy, update_user, update_timestamp, ver_nbr, submit_flag)
values ((select document_number from eps_proposal_document where document_number = 1), (select status_code from eps_prop_post_sub_status where description = 'description'), sys_guid(), 1, 'Y', 'jhulslander', sysdate, 1, 'N');


insert into budget (budget_id, comments, cost_sharing_amount, document_number, end_date, final_version_flag, oh_rate_class_code, oh_rate_type_code, proposal_number, residual_funds, start_date, 
  total_cost, total_cost_limit, total_direct_cost, total_indirect_cost, underrecovery_amount, update_timestamp, update_user, ur_rate_class_code, version_number)
values (1, 'this is a comment', 500.23, (select document_number from eps_proposal_document where document_number = 1), to_date('12/31/2050', 'MM/DD/YYYY'), 'N', 'clc', 'rtc', ( select proposal_number from eps_proposal where proposal_number = 1 ), 50, sysdate,
  50000, 250, 120, 59, 19, sysdate, 'jhulslander', 'urc', 1);
  
insert into appointment_type (appointment_type_code, description, duration, obj_id, update_timestamp, update_user, ver_nbr)
values ('tst', 'testing is fun', 36, sys_guid(), sysdate, 'jhulslander', 1);


insert into budget_persons (appointment_type_code, budget_id, calculation_base, effective_date, job_code, non_employee_flag, obj_id, person_id, 
	person_name, person_sequence_number, proposal_number, rolodex_id, tbn_id, update_timestamp, update_user, ver_nbr, version_number)
values ('tst', 1, 50, sysdate, 'jobCD', 'N', sys_guid(), 1,
	'Jay Hulslander', 1, 1243, 1, 1, sysdate, 'jhulslander', 1, 1);

  
COMMIT;
