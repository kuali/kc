 CREATE or REPLACE PROCEDURE getNSFTotalSalAndFringe ( 
       AS_PROPOSAL_NUMBER IN OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE, 
       AI_VERSION_NUMBER IN OSP$BUDGET.VERSION_NUMBER%TYPE,        
           cur_generic IN OUT result_sets.cur_generic) IS 

liSalary number;
liFringe number;

BEGIN 


Begin
   -- get the total salary and total fringe

	select ROUND(DECODE(sum(BD.line_item_cost),NULL,0,SUM(BD.LINE_ITEM_COST))),
   	    fnGetFringe(AS_PROPOSAL_NUMBER, BD.VERSION_NUMBER)
	into   liSalary, liFringe
	from	OSP$BUDGET_DETAILS BD, 
   	   OSP$BUDGET_CATEGORY_MAPPING,
      	osp$budget_category_maps
	where bd.proposal_number = AS_PROPOSAL_NUMBER
	and	bd.VERSION_NUMBER = AI_VERSION_NUMBER
	and   bd.BUDGET_CATEGORY_CODE = OSP$BUDGET_CATEGORY_MAPPING.COEUS_CATEGORY_CODE
	AND   OSP$BUDGET_CATEGORY_MAPPING.MAPPING_NAME = 'NSF_PRINTING'
	AND   OSP$BUDGET_CATEGORY_maps.MAPPING_NAME = 'NSF_PRINTING'
	AND   OSP$BUDGET_CATEGORY_MAPPING.TARGET_CATEGORY_CODE = osp$budget_category_maps.target_category_code
	and  osp$budget_category_maps.category_type = 'P' 
 GROUP BY bD.PROPOSAL_NUMBER, bD.VERSION_NUMBER;

 EXCEPTION
    WHEN OTHERS THEN
       liSalary := 0;
       liFringe := 0;


end;

-- now add the LA for salaries

open cur_generic for 

select 	DECODE(sum(a.calculated_cost),null,0,sum(a.calculated_cost)) + liSalary SALARY, 
         liFringe as FRINGE
	from 		osp$budget_details_cal_amts a, osp$budget_details d, osp$rate_class r
	where 	a.proposal_number = AS_PROPOSAL_NUMBER
	and 		a.version_number = AI_VERSION_NUMBER
	and 		a.rate_class_code = r.rate_class_code
	and      r.rate_class_type = 'Y'
	and 		a.proposal_number = d.proposal_number
	and 		a.budget_period = d.budget_period
	and	 	a.version_number = d.version_number
	and 		a.line_item_number = d.line_item_number;
END;

/


