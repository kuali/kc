CREATE OR REPLACE package s2sPHS398ChecklistPkg as

procedure get_checklist_info (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           				   cur_type IN OUT result_sets.cur_generic) ;

procedure get_application_type (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           				   cur_type IN OUT result_sets.cur_generic) ;


procedure get_program_income (as_proposal_number IN osp$eps_proposal.proposal_number%type,
										ai_version IN osp$budget_project_income.version_number%type,
										ai_period IN osp$budget_project_income.budget_period%type,
           				   		cur_type IN OUT result_sets.cur_generic);


END;
/

CREATE OR REPLACE package body s2sPHS398ChecklistPkg as


-------------------------------
-- procedure get_checklist_info
-------------------------------



procedure get_checklist_info (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           				   cur_type IN OUT result_sets.cur_generic)

 IS

federal_id				varchar2(30);
is_change_pi			varchar2(3);

ls_former_name       osp$eps_prop_ynq.explanation%type := '';
is_inst_change			varchar2(3) := '';
ls_former_inst 		osp$eps_prop_ynq.explanation%type;
is_inventions			varchar2(3) := '';
is_prev_reported		varchar2(3) := '';
ls_program_income		varchar2(3);
str_version          varchar2(5);
str_num_periods      varchar2(5);
ls_answer				osp$eps_prop_ynq.answer%type;
ver                  number;
num_per             number;
li_count					number;

BEGIN

 
ver := s2spackage.fn_get_version(as_proposal_number);
if (ver > 0) then
   num_per := s2sPHS398ModBud.fn_Get_numPeriods(as_proposal_number,ver); 
end if;

str_version := to_char(ver);
str_num_periods := to_char(num_per);

-----------------------
-- project income
-----------------------
select 	count(*)
into 		li_count
from 		osp$budget_project_income
where		proposal_number = as_proposal_number
and		version_number = ver;

if li_count > 0 then
   ls_program_income := 'Yes';
else
	ls_program_income := 'No';
end if;

---------------------
-- inventions
---------------------
begin
	select  answer
	into    ls_answer
	from    osp$eps_prop_ynq
	where   proposal_number=as_proposal_number
	and     question_id = '16';

 EXCEPTION
	 When NO_DATA_FOUND then
		ls_answer := 'X';
end;

if ls_answer = 'Y' then
  is_inventions := 'Yes';
  is_prev_reported := 'Yes';
elsif ls_answer ='X' then
  is_inventions := 'No';
else
  is_inventions := 'Yes';    
  is_prev_reported := 'No';          
end if;

-----------------
-- pi change
-----------------
begin

  select  answer, explanation
  into    ls_answer, ls_former_name
  from 		osp$eps_prop_ynq
  where 	proposal_number = as_proposal_number
  and 		question_id = '22';

  EXCEPTION
	 When NO_DATA_FOUND then
		ls_answer := 'N';
end;

if ls_answer = 'Y' then
  is_change_pi := 'Yes';
   
else
  is_change_pi := 'No';
end if;


----------------------
--institution change
---------------------

begin

  select  answer,  explanation
  into    ls_answer, ls_former_inst
  from 		osp$eps_prop_ynq
  where 	proposal_number = as_proposal_number
  and 		question_id = '23';

  EXCEPTION
	 When NO_DATA_FOUND then
		ls_answer := 'N';
 end;

if ls_answer = 'Y' then
    is_inst_change := 'Yes';
else
  is_inst_change := 'No';
end if;

-------------------------
-- federal id
--  
-------------------------
--federal_id := fn_get_federal_id (as_proposal_number);
federal_id := s2sPackage.get_federal_id(as_proposal_number );

open cur_type for

select federal_id as FEDERAL_ID,
	 is_change_pi as IS_CHANGE_PI,
	 is_inst_change as IS_INST_CHANGE,
    ls_former_name as FORMER_NAME,
	 ls_former_inst as former_inst_name,
	 is_inventions AS IS_INVENTIONS,
    is_prev_reported as IS_PREV_REPORTED,
	 ls_program_income as program_income,
   '1.0' as form_version,
 	str_version as version,
 	str_num_periods as num_periods
from dual;


end;


---------------------------------
-- procedure get_application_type
----------------------------------
procedure get_application_type (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           				   cur_type IN OUT result_sets.cur_generic)

 IS


BEGIN

 s2spackage.getApplicationType(as_proposal_number,cur_type);

end;


-------------------------------------------------------
-- get_program_income
-------------------------------------------------------
procedure get_program_income (as_proposal_number IN osp$eps_proposal.proposal_number%type,
										ai_version IN osp$budget_project_income.version_number%type,
										ai_period IN osp$budget_project_income.budget_period%type,
           				   		cur_type IN OUT result_sets.cur_generic)

IS

ls_description			osp$budget_project_income.description%type;
ls_full_description	osp$budget_project_income.description%type;
li_amount				number;

BEGIN

  Begin

	select sum(amount)
	into	 li_amount
	FROM 		osp$budget_project_income
	WHERE 	proposal_number= as_proposal_number
	and      version_number = ai_version
   and      budget_period = ai_period;

   EXCEPTION
	 When NO_DATA_FOUND then
		li_amount := 0;
  end;

	DECLARE CURSOR c_description IS
		SELECT 	description
		FROM 		osp$budget_project_income
		WHERE 	proposal_number= as_proposal_number
		and      version_number = ai_version
      and      budget_period = ai_period
      group by description;


		BEGIN
			ls_full_description := '';
			

			open c_description;
			loop
				FETCH c_description INTO ls_description;
				EXIT WHEN c_description%NOTFOUND;

			   ls_full_description := ltrim(rtrim(ls_full_description)) || ';' || ltrim(rtrim(ls_description));	

			end loop;
			close c_description;
		END;

      --remove first ; and limit description size to 150
      ls_full_description := substr(ls_full_description,2,149);
       

open cur_type for
	Select li_amount as amount,
			 ls_full_description as description
	from dual;
end;




END;

/

