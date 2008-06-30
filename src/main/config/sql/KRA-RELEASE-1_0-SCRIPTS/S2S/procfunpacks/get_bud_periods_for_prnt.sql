 CREATE OR REPLACE PROCEDURE GET_BUD_PERIODS_FOR_PRNT (
  AW_PROPOSAL_NUMBER IN OSP$BUDGET_PERIODS.PROPOSAL_NUMBER%TYPE, 
   AI_VERSION_NUMBER IN OSP$BUDGET.VERSION_NUMBER%TYPE,
   cur_generic IN OUT result_sets.cur_generic)
 is 

ls_flag varchar(3);


  begin 

  select decode(modular_budget_flag,null,'N',modular_budget_flag)
  into   ls_flag
  from   osp$budget
  where  PROPOSAL_NUMBER = AW_PROPOSAL_NUMBER 
  and    VERSION_NUMBER = ai_version_number;

  if ls_flag = 'N' then

  open cur_generic for 
    SELECT BP.PROPOSAL_NUMBER, 
     BP.VERSION_NUMBER, 
     B.FINAL_VERSION_FLAG , 
     BP.BUDGET_PERIOD, 
     BP.START_DATE, 
     BP.END_DATE, 
     round(BP.TOTAL_COST) as TOTAL_COST, 
     round(BP.TOTAL_DIRECT_COST) as TOTAL_DIRECT_COST, 
     round(BP.TOTAL_INDIRECT_COST) as TOTAL_INDIRECT_COST, 
     round(BP.COST_SHARING_AMOUNT) as COST_SHARING_AMOUNT, 
     round(BP.UNDERRECOVERY_AMOUNT) as UNDERRECOVERY_AMOUNT
    FROM OSP$BUDGET_PERIODS BP, OSP$BUDGET B 
    WHERE BP.PROPOSAL_NUMBER = AW_PROPOSAL_NUMBER 
    AND   BP.PROPOSAL_NUMBER = B.PROPOSAL_NUMBER 
    AND   BP.VERSION_NUMBER = B.VERSION_NUMBER 
    AND   B.VERSION_NUMBER = AI_VERSION_NUMBER 
     ORDER BY BP.BUDGET_PERIOD; 

  else

	
    


    open cur_generic for 
    SELECT BP.PROPOSAL_NUMBER, 
     BP.VERSION_NUMBER, 
     B.FINAL_VERSION_FLAG , 
     BP.BUDGET_PERIOD, 
     BP.START_DATE, 
     BP.END_DATE, 
     fn_get_mod_dc_for_per (aw_proposal_number,ai_version_number,bp.budget_period) +
     fn_get_mod_idc_for_per(aw_proposal_number,ai_version_number,bp.budget_period) as TOTAL_COST, 
     fn_get_mod_dc_for_per (aw_proposal_number,ai_version_number,bp.budget_period) as TOTAL_DIRECT_COST, 
     fn_get_mod_idc_for_per(aw_proposal_number,ai_version_number,bp.budget_period) as TOTAL_INDIRECT_COST, 
     0  as COST_SHARING_AMOUNT, 
     0 as UNDERRECOVERY_AMOUNT
    FROM OSP$BUDGET_PERIODS BP, OSP$BUDGET B 
    WHERE BP.PROPOSAL_NUMBER = AW_PROPOSAL_NUMBER 
    AND   BP.PROPOSAL_NUMBER = B.PROPOSAL_NUMBER 
    AND   BP.VERSION_NUMBER = B.VERSION_NUMBER 
    AND   B.VERSION_NUMBER = AI_VERSION_NUMBER 
     ORDER BY BP.BUDGET_PERIOD; 


  end if;
 end;

/
