create or replace function fn_Get_Version (AS_PROPOSAL_NUMBER OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE)
															
 return  NUMBER is

li_version	number;
li_count		number;

BEGIN

select count(*)
into   li_count
from   osp$budget
where  proposal_number = as_proposal_number;

if li_count = 0 then
   --no budget
	return 0;
end if;

select 	version_number
into		li_version
from     osp$budget
where    proposal_number = as_proposal_number
and      final_version_flag = 'Y';

return li_version;

EXCEPTION
	When NO_DATA_FOUND then
		SELECT MAX(VERSION_NUMBER)
		INTO   li_version
		FROM	 OSP$BUDGET
		WHERE  PROPOSAL_NUMBER = as_proposal_number;
		return li_version;
end;

/

