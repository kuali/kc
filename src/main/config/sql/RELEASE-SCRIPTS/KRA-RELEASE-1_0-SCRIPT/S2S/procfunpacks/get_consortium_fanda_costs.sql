CREATE OR REPLACE PROCEDURE GET_CONSORTIUM_FANDA_COSTS (
   AW_PROPOSAL_NUMBER IN OSP$BUDGET_PERIODS.PROPOSAL_NUMBER%TYPE, 
   AI_VERSION_NUMBER IN OSP$BUDGET_PERIODS.VERSION_NUMBER%TYPE,
   AW_BUDGET_PERIOD IN OSP$BUDGET_PERIODS.BUDGET_PERIOD%TYPE, 
   aw_sponsor in varchar2,
   cur_generic IN OUT result_sets.cur_generic)
 is 
  sponsor_map varchar2(20);
  li_count               number;
  sub_f_and_a_gt_25k     osp$parameter.value%type;
  sub_f_and_a_lt_25k     osp$parameter.value%type;  -- case 2373
  broad_f_and_a          osp$parameter.value%type;
  modular_budget_flag    osp$budget.modular_budget_flag%type;  -- addition for modular budget

  begin 


/* addition for modular budget*/
  SELECT	MODULAR_BUDGET_FLAG
  INTO   modular_budget_flag
  FROM	OSP$BUDGET
  WHERE	PROPOSAL_NUMBER = aw_proposal_number
  AND    VERSION_NUMBER = ai_version_number;

  IF modular_budget_flag = 'Y' then
    OPEN cur_generic for
	 SELECT CONSORTIUM_FNA as CONSORTIUM
	 FROM   OSP$BUDGET_MODULAR
    WHERE  PROPOSAL_NUMBER = aw_proposal_number
    AND    VERSION_NUMBER = ai_version_number
    AND    BUDGET_PERIOD = aw_budget_period;

  ELSE
   -- not modular budget

  	sub_f_and_a_gt_25k := get_parameter_value('SUBCONTRACTOR_F_AND_A_GT_25K');
	sub_f_and_a_lt_25k := get_parameter_value('SUBCONTRACTOR_F_AND_A_LT_25K'); -- case 2373
  	broad_f_and_a := get_parameter_value('BROAD_F_AND_A');

  --case 2691 - replaced checking parameter table with check of Sponsor Groups in sponsor hierarchy
  --to determine if sponsor or prime sponsor is NIH

  li_count := fn_in_sponsor_group (aw_proposal_number ,'NIH');
  if (li_count = 1) then 
	  sponsor_map := 'NIH_PRINTING';
  else
	 sponsor_map := 'NSF_PRINTING';
  end if;

  
	open cur_generic for 
   select sum(ROUND(BD.line_item_cost )) AS CONSORTIUM 
   from  OSP$BUDGET_DETAILS BD, OSP$BUDGET_CATEGORY_MAPPING BC, 
       OSP$BUDGET_PERIODS BP, OSP$BUDGET B 
   where BD.proposal_number = AW_PROPOSAL_NUMBER 
   AND   BD.PROPOSAL_NUMBER = B.PROPOSAL_NUMBER 
   AND   BP.PROPOSAL_NUMBER = B.PROPOSAL_NUMBER     
   AND   BP.VERSION_NUMBER = B.VERSION_NUMBER  
   and   BD.VERSION_NUMBER = b.version_number 
   AND   B.VERSION_NUMBER = AI_VERSION_NUMBER  
   AND   BD.BUDGET_PERIOD = AW_BUDGET_PERIOD 
   and  BD.budget_period  = bp.budget_period 
   AND  BD.budget_category_code = BC.COEUS_CATEGORY_CODE 
   AND  BC.MAPPING_NAME = sponsor_map
   AND  BC.TARGET_CATEGORY_CODE = '04' 
   AND ( BD.COST_ELEMENT = sub_f_and_a_gt_25k or
         BD.COST_ELEMENT = sub_f_and_a_lt_25k or   -- case 2373
        BD.COST_ELEMENT = broad_f_and_a);  
	END IF;
end;

/

