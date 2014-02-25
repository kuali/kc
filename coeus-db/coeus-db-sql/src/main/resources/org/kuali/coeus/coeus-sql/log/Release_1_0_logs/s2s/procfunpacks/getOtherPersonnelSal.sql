------------------------------------------------------------------
-- procedure GetOtherPersonnelSal
-- this procedure replaces function fnGetOtherPersonnel
-- this is to print cents rather than rounding on nsf budget forms in proposal printing
-- created march 13, 2006
------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE GetOtherPersonnelSal
 					(AS_PROPOSAL_NUMBER OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
					AI_VERSION_NUMBER osp$budget.version_number%type,
					AI_BUDGET_PERIOD osp$budget_periods.budget_period%type,
               AS_CATEGORY_CODE osp$budget_category_mapping.target_category_code%type,
               AS_SPONSOR OSP$SPONSOR.SPONSOR_CODE%TYPE,
                cur_generic IN OUT result_sets.cur_generic) IS 
       

 


li_salary	number;
li_cost     number;
sponsor_map varchar2(20);
li_count    number;   -- case 2691

BEGIN

 
--case 2691 - replaced checking parameter table with check of Sponsor Groups in sponsor hierarchy
--to determine if sponsor or prime sponsor is NIH

li_count := fn_in_sponsor_group (as_proposal_number ,'NIH');
if (li_count = 1) then 
	sponsor_map := 'NIH_PRINTING';
else
	sponsor_map := 'NSF_PRINTING';
end if;



li_cost := 0;
li_salary := 0;

  select   DECODE (SUM(salary_requested), NULL ,0, SUM(salary_requested))
  INTO     li_salary
  from     OSP$BUDGET_PERSONNEL_DETAILS,OSP$BUDGET_DETAILS,OSP$BUDGET_CATEGORY_MAPPING
  where	  OSP$BUDGET_DETAILS.proposal_number = AS_PROPOSAL_NUMBER
		AND  OSP$BUDGET_DETAILS.proposal_number = OSP$BUDGET_PERSONNEL_DETAILS.proposal_number
		AND  OSP$BUDGET_DETAILS.VERSION_NUMBER = OSP$BUDGET_PERSONNEL_DETAILS.VERSION_NUMBER
		AND  OSP$BUDGET_DETAILS.VERSION_NUMBER = AI_VERSION_NUMBER
		AND  OSP$BUDGET_DETAILS.BUDGET_PERIOD  = OSP$BUDGET_PERSONNEL_DETAILS.BUDGET_PERIOD 
		AND  OSP$BUDGET_PERSONNEL_DETAILS.BUDGET_PERIOD = AI_BUDGET_PERIOD 
		AND  OSP$BUDGET_PERSONNEL_DETAILS.LINE_ITEM_NUMBER =  OSP$BUDGET_DETAILS.LINE_ITEM_NUMBER
		AND OSP$BUDGET_DETAILS.BUDGET_CATEGORY_CODE = OSP$BUDGET_CATEGORY_MAPPING.COEUS_CATEGORY_CODE
		AND OSP$BUDGET_CATEGORY_MAPPING.MAPPING_NAME = sponsor_map
      AND OSP$BUDGET_CATEGORY_MAPPING.TARGET_CATEGORY_CODE = AS_CATEGORY_CODE
      AND OSP$BUDGET_PERSONNEL_DETAILS.person_id  not in (select person_id
																			from osp$eps_prop_person
																			where proposal_number = as_proposal_number
																	);
      
		
-- get costs for this budget category that do not have persons attached to the cost element 


	select DECODE	(sum(OSP$BUDGET_DETAILS.line_item_cost), NULL, 0, SUM(OSP$BUDGET_DETAILS.line_item_cost))
   into  li_cost
	from	OSP$BUDGET_DETAILS, OSP$BUDGET_CATEGORY_MAPPING
	where OSP$BUDGET_DETAILS.proposal_number = AS_PROPOSAL_NUMBER
	and	OSP$BUDGET_DETAILS.VERSION_NUMBER = AI_VERSION_NUMBER
	and   osp$budget_details.budget_period = AI_BUDGET_PERIOD 
	and   OSP$BUDGET_DETAILS.BUDGET_CATEGORY_CODE = OSP$BUDGET_CATEGORY_MAPPING.COEUS_CATEGORY_CODE
	AND     OSP$BUDGET_CATEGORY_MAPPING.MAPPING_NAME = sponsor_map
	AND     OSP$BUDGET_CATEGORY_MAPPING.TARGET_CATEGORY_CODE = AS_CATEGORY_CODE
	AND   OSP$BUDGET_DETAILS.line_item_number not in
		(select line_item_number
       from  osp$budget_personnel_details
       where proposal_number = AS_PROPOSAL_NUMBER
       and   version_number  = AI_VERSION_NUMBER
       and   budget_period  = AI_BUDGET_PERIOD );

--li_salary := ROUND(li_salary + li_cost);
  li_salary := li_salary + li_cost;


	open cur_generic for
		SELECT li_salary as SALARY
      FROM   DUAL;
END;
/



