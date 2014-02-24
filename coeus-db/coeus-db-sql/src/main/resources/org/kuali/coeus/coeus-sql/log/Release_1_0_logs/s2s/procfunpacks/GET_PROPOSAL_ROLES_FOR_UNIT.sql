create or replace procedure get_proposal_roles_for_unit
   ( AV_UNIT_NUMBER IN osp$role.owned_by_unit%TYPE,
     cur_generic IN OUT result_sets.cur_generic) is

begin

open cur_generic for
   select *
     from osp$role
 	  where role_type = 'P'
    and owned_by_unit IN (AV_UNIT_NUMBER, '000001')
	order by ROLE_NAME asc;
end;

/

