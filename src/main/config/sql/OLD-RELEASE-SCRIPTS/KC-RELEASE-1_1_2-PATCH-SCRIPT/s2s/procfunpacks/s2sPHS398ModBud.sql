
CREATE OR REPLACE package s2sPHS398ModBud as

procedure get_direct_cost (as_proposal_number in osp$budget.proposal_number%type,
									ai_version_number  in osp$budget.version_number%type,
                           ai_period in osp$budget_periods.budget_period%type,
									cur_type IN OUT result_sets.cur_generic);

procedure get_total_cost (as_proposal_number in osp$budget.proposal_number%type,
									ai_version_number  in osp$budget.version_number%type,
                           ai_period in osp$budget_periods.budget_period%type,
									cur_type IN OUT result_sets.cur_generic);

procedure get_indirect_cost (as_proposal_number in osp$budget.proposal_number%type,
									ai_version_number  in osp$budget.version_number%type,
                           ai_period in osp$budget_periods.budget_period%type,
									cur_type IN OUT result_sets.cur_generic);

procedure get_dates (as_proposal_number in osp$budget.proposal_number%type,
									ai_version_number  in osp$budget.version_number%type,
 								   ai_budget_period   in osp$budget_periods.budget_period%type,
                        	cur_type IN OUT result_sets.cur_generic);

procedure get_cognizant_agency (as_proposal_number in osp$eps_proposal.proposal_number%type,
                                	cur_type IN OUT result_sets.cur_generic);

function fn_get_version (as_proposal_number IN osp$budget.proposal_number%type)
		return number;

function fn_Get_numPeriods  (as_proposal_number in osp$eps_proposal.proposal_number%type,
				                  ai_version_number  in osp$budget.version_number%type)
   return number;
end; 


/




CREATE OR REPLACE package body s2sPHS398ModBud as

----------------------------------
-- procedure get_direct_cost
----------------------------------

procedure get_direct_cost (as_proposal_number in osp$budget.proposal_number%type,
									ai_version_number  in osp$budget.version_number%type,
                           ai_period in osp$budget_periods.budget_period%type,
									cur_type IN OUT result_sets.cur_generic)
is
  begin
  open cur_type for
			SELECT DIRECT_COST_LESS_CONSOR_FNA,
                CONSORTIUM_FNA,
					 TOTAL_DIRECT_COST
    		FROM   OSP$BUDGET_MODULAR
    		WHERE  PROPOSAL_NUMBER = as_proposal_number
         AND    VERSION_NUMBER = ai_version_number
    		AND    BUDGET_PERIOD = ai_period;

  end;


---------------------------------
-- procedure get_indirect_cost
---------------------------------
procedure get_indirect_cost (as_proposal_number in osp$budget.proposal_number%type,
									ai_version_number  in osp$budget.version_number%type,
                           ai_period in osp$budget_periods.budget_period%type,
									cur_type IN OUT result_sets.cur_generic)

is

li_count	number;



	begin
	select count(*)
  	into   li_count
  	from   osp$budget_modular_idc
  	where  proposal_number =as_proposal_number
  	and    version_number= ai_version_number
  	and    budget_period = ai_period;

  	if li_count > 0 then

		open cur_type for
		SELECT   RATE_NUMBER,
					DESCRIPTION,
					IDC_RATE,
					IDC_BASE,
					FUNDS_REQUESTED
		FROM		OSP$BUDGET_MODULAR_IDC
		WHERE    PROPOSAL_NUMBER = as_proposal_number
         	AND    VERSION_NUMBER = ai_version_number
    		AND    BUDGET_PERIOD = ai_period
      	ORDER BY RATE_NUMBER;

	else
            open cur_type for
		SELECT   0 as RATE_NUMBER,
			   ' ' as DESCRIPTION,
			   0 as IDC_RATE,
			   0 as	IDC_BASE,
			   0 as	FUNDS_REQUESTED
		FROM		OSP$BUDGET_MODULAR_IDC
		WHERE    PROPOSAL_NUMBER = as_proposal_number
         	AND    VERSION_NUMBER = ai_version_number
    		AND    BUDGET_PERIOD = ai_period
      	ORDER BY RATE_NUMBER;

      end if;

      end;



---------------------------------
-- procedure get_total_cost
---------------------------------

procedure get_total_cost (as_proposal_number in osp$budget.proposal_number%type,
									ai_version_number  in osp$budget.version_number%type,
                           ai_period in osp$budget_periods.budget_period%type,
									cur_type IN OUT result_sets.cur_generic)
is

li_total_direct		number;
li_total_indirect		number;
li_total					number;

BEGIN

   begin

		select 	sum(total_direct_cost)
   	into   	li_total_direct
		from 	 	osp$budget_modular
		where 	proposal_number = as_proposal_number
		and 	 	version_number = ai_version_number
      and      budget_period = ai_period;

    	EXCEPTION
	 	  when no_data_found then
		  	   li_total_direct := 0;
   end;

   begin
		select 	decode(sum(funds_requested),null,0,sum(funds_requested))
   	into		li_total_indirect
		from 		osp$budget_modular_idc
		where 	proposal_number = as_proposal_number
		and 		version_number = ai_version_number
      and      budget_period = ai_period;

  		EXCEPTION
	 	  when no_data_found then
		  	   li_total_indirect := 0;
   end;

   li_total := li_total_direct + li_total_indirect;

  	open cur_type for
		SELECT 	li_total as total_cost
   	FROM  	dual;
   

  end;


--------------------------------------
-- procedure get_dates
--------------------------------------

procedure get_dates (as_proposal_number in osp$budget.proposal_number%type,
							ai_version_number  in osp$budget.version_number%type,
                     ai_budget_period   in osp$budget_periods.budget_period%type,
                    	cur_type IN OUT result_sets.cur_generic)
is
	begin
    OPEN cur_type for
 		SELECT 	START_DATE,
			 		END_DATE 
  		FROM		OSP$BUDGET_PERIODS
   	WHERE    PROPOSAL_NUMBER = as_proposal_number
   	AND		VERSION_NUMBER = Ai_version_number
      AND      BUDGET_PERIOD = ai_budget_period;
   end;



-------------------------------------------
-- get cognizant agency
-------------------------------------------
procedure get_cognizant_agency (as_proposal_number in osp$eps_proposal.proposal_number%type,
                                	cur_type IN OUT result_sets.cur_generic)
is


begin
-- case 4223 change to get from eps prop sites 
	open cur_type for
	select	r.organization || ', ' || r.FIRST_NAME || ' ' || r.LAST_NAME
         || ' ' || r.PHONE_NUMBER  as AGENCY,
         	o.indirect_cost_rate_agreement as indirect_cost_rate_agreement
	from	OSP$EPS_PROP_SITES p,	osp$rolodex r, osp$organization o
	where	p.proposal_number = as_proposal_number
    AND     p.LOCATION_TYPE_CODE = 1
    and 	p.organization_id = o.organization_id
	and		o.cognizant_auditor = r.rolodex_id;



	 

end;


--------------------------------------------
-- fn_get_version 
--    calls function in s2spackage
--------------------------------------------
function fn_get_version (as_proposal_number IN osp$budget.proposal_number%type)
		return number is

li_version	number;

begin
  li_version := s2spackage.fn_get_version(as_proposal_number);
  return li_version;

end;

--------------------------------------------
-- fn_numPeriods 
--    return number of budget periods in modular budget
--    if modular budget flag is not set, return 0
--------------------------------------------
function fn_Get_numPeriods  (as_proposal_number in osp$eps_proposal.proposal_number%type,
				                  ai_version_number  in osp$budget.version_number%type)
   return number is

li_numperiods number;
ls_flag varchar2(5);

begin

   select decode(modular_budget_flag,null,'N',modular_budget_flag)
   into   ls_flag
   from   osp$budget
   where  proposal_number = as_proposal_number 
   and    version_number = ai_version_number;


   if ls_flag = 'N' then
     li_numperiods := 0;
   
   else


   	select max(budget_period)
   	into   li_numperiods
   	from osp$budget_modular
   	where  proposal_number = as_proposal_number
   	and    version_number = ai_version_number;
   end if;

	return li_numperiods;

	EXCEPTION
	when no_data_found then
		return 0;
end;



end;

/

