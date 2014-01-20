--  rule to GET owned_by_unit for a proposal
-------------------------------------------------------
create or replace function FN_GET_OWNED_BY_UNIT
    ( as_proposal_num in osp$eps_proposal.proposal_number%type)

	return varchar2 is
	
	ls_unit	osp$eps_proposal.owned_by_unit%type;
 
	begin
	
	SELECT owned_by_unit
	into   ls_unit
	from   osp$eps_proposal
	where  proposal_number = as_proposal_num;

	return ls_unit;

	exception
		when  no_data_found then
		raise_application_error(-20100, 'No proposal found for ' || as_proposal_num);
		return 'ERROR';

	end;
/








