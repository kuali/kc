create or replace function fnGetCountOtherPersonnel (AS_PROPOSAL_NUMBER OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
																AI_VERSION_NUMBER osp$budget.version_number%type,
																AI_BUDGET_PERIOD osp$budget_periods.budget_period%type,
                                                AS_CATEGORY_CODE osp$budget_category_mapping.target_category_code%type,
                                                AS_SPONSOR OSP$SPONSOR.SPONSOR_CODE%TYPE)
 return  NUMBER is

li_count		number;
li_count2   number;
sponsor_map varchar2(20);


Begin

--case 2691 - replaced checking parameter table with check of Sponsor Groups in sponsor hierarchy
--to determine if sponsor or prime sponsor is NIH

li_count := fn_in_sponsor_group (as_proposal_number ,'NIH');
if (li_count = 1) then 
	sponsor_map := 'NIH_PRINTING';
else
	sponsor_map := 'NSF_PRINTING';
end if;


li_count := 0;


      select  count(distinct person_id || job_code)
  		INTO     li_count
  		from     OSP$BUDGET_PERSONNEL_DETAILS,OSP$BUDGET_DETAILS,OSP$BUDGET_CATEGORY_MAPPING
  		where   OSP$BUDGET_DETAILS.proposal_number = AS_PROPOSAL_NUMBER
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

/* handle case where there are no person details for line item*/

	SELECT  decode(sum(quantity),null,0,sum(quantity))
	INTO   li_count2
	FROM 	osp$budget_details,OSP$BUDGET_CATEGORY_MAPPING
	WHERE OSP$BUDGET_DETAILS.proposal_number = AS_PROPOSAL_NUMBER
	AND 	OSP$BUDGET_DETAILS.VERSION_NUMBER = AI_VERSION_NUMBER
	AND  	OSP$BUDGET_DETAILS.BUDGET_PERIOD = AI_BUDGET_PERIOD
	AND 	OSP$BUDGET_DETAILS.BUDGET_CATEGORY_CODE = OSP$BUDGET_CATEGORY_MAPPING.COEUS_CATEGORY_CODE
  	AND 	OSP$BUDGET_CATEGORY_MAPPING.MAPPING_NAME = sponsor_map
 	AND 	OSP$BUDGET_CATEGORY_MAPPING.TARGET_CATEGORY_CODE = AS_CATEGORY_CODE
and OSP$BUDGET_DETAILS.line_item_number not in (select line_item_number from
 osp$budget_personnel_details
where proposal_number = AS_PROPOSAL_NUMBER and VERSION_NUMBER = AI_VERSION_NUMBER
 and BUDGET_PERIOD = AI_BUDGET_PERIOD);




li_count := li_count + li_count2;

return li_count;

END;

/

