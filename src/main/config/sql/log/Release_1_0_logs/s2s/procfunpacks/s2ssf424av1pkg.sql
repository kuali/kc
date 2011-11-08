CREATE OR REPLACE package s2sSF424AV1Pkg as


procedure getOpportunityInfo
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) ;
           
procedure getBudgetTotals
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic);

procedure getProgramIncome (as_proposal_number in osp$budget.proposal_number%type,
                                              cur_type IN OUT result_sets.cur_generic);
           
procedure  getPersonnelAmt  ( as_proposal_number in osp$budget.proposal_number%type,
															 cur_type IN OUT result_sets.cur_generic);

procedure  getFringes  ( as_proposal_number in osp$budget.proposal_number%type,
															 cur_type IN OUT result_sets.cur_generic);

procedure  getTravel  ( as_proposal_number in osp$budget.proposal_number%type,
															 cur_type IN OUT result_sets.cur_generic);

procedure  getEquipment  ( as_proposal_number in osp$budget.proposal_number%type,
															 cur_type IN OUT result_sets.cur_generic);

procedure  getSupplies  ( as_proposal_number in osp$budget.proposal_number%type,
															 cur_type IN OUT result_sets.cur_generic);

procedure  getContractual  ( as_proposal_number in osp$budget.proposal_number%type,
															 cur_type IN OUT result_sets.cur_generic);

procedure  getConstruction ( as_proposal_number in osp$budget.proposal_number%type,
															 cur_type IN OUT result_sets.cur_generic);

procedure  getOtherAmt  ( as_proposal_number in osp$budget.proposal_number%type,
															 cur_type IN OUT result_sets.cur_generic);

procedure  getBalanceOfFunds  ( as_proposal_number in osp$budget.proposal_number%type,
															 cur_type IN OUT result_sets.cur_generic);

procedure  getForecast  ( as_proposal_number in osp$budget.proposal_number%type,
															 cur_type IN OUT result_sets.cur_generic);

end;
/

CREATE OR REPLACE package body s2sSF424AV1Pkg as

-------------------------------
-- procedure getOpportunityInfo
-------------------------------
procedure getOpportunityInfo
	(as_proposal_number in osp$eps_proposal.proposal_number%type,
	 cur_type IN OUT result_sets.cur_generic)


is
     
 begin
	GET_S2S_OPPORTUNITY(as_proposal_number, cur_type);

 end;


-------------------------------
-- procedure getBudgetTotals
-------------------------------

procedure getBudgetTotals
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic) 
is
     

 li_cost				number := 0;

 li_direct_cost	number := 0;
 li_total_direct  number := 0;
 li_idc				number := 0;
 li_cs				number := 0;
 li_training_cost	number := 0;
 li_training_cs	number := 0;

begin

open cur_type for
/* get total costs */
 
 SELECT   decode(TOTAL_COST,null,0,TOTAL_COST) as TOTAL_COST,
     				decode(TOTAL_DIRECT_COST,null,0,TOTAL_DIRECT_COST) as TOTAL_DIRECT_COST,
     				decode(TOTAL_INDIRECT_COST,null,0,TOTAL_INDIRECT_COST)  as TOTAL_INDIRECT_COST  ,
               decode(COST_SHARING_AMOUNT,null,0,COST_SHARING_AMOUNT) as COST_SHARING_AMOUNT 	,
               decode(TOTAL_COST,null,0,TOTAL_COST) - decode(COST_SHARING_AMOUNT,null,0,COST_SHARING_AMOUNT) as TOT_FED_COST
     		FROM  OSP$BUDGET
    		WHERE PROPOSAL_NUMBER = AS_PROPOSAL_NUMBER
    		AND   VERSION_NUMBER = fn_Get_Version(as_proposal_number);
     

end;

---------------------------------
-- procedure  getForecast - by quarter of first year
---------------------------------
procedure  getForecast  ( as_proposal_number in osp$budget.proposal_number%type,
															 cur_type IN OUT result_sets.cur_generic)
is

li_CostShare number ;
li_CostshareEst number ;
li_TotFedCost number;
li_TotFedEst number ;
li_TotCost  number ;
li_TotEst number ;
li_count number;

begin

li_CostShare := 0; 
li_CostshareEst := 0; 
li_TotFedCost := 0; 
li_TotFedEst := 0; 
li_TotCost  := 0; 
li_TotEst := 0; 
li_count := 0;
 
select count(*)
into  li_count
from osp$budget
where proposal_number = as_proposal_number
AND   VERSION_NUMBER = fn_Get_Version(as_proposal_number);

if (li_count > 0) then
 select   decode(TOTAL_COST,null,0,TOTAL_COST) ,
          decode(COST_SHARING_AMOUNT,null,0,COST_SHARING_AMOUNT) ,
          decode(TOTAL_COST,null,0,TOTAL_COST) - decode(COST_SHARING_AMOUNT,null,0,COST_SHARING_AMOUNT)
 INTO     li_totcost, li_CostShare, li_totFedCost
 FROM  OSP$BUDGET_PERIODS
 WHERE PROPOSAL_NUMBER = AS_PROPOSAL_NUMBER
 AND   VERSION_NUMBER = fn_Get_Version(as_proposal_number)
 AND   BUDGET_PERIOD = 1;
end if;

 if li_totcost >0 then
    li_TOTEst := round(li_totcost/4, 2);
 end if;

 if li_CostShare > 0 then
    li_costshareEst := round(li_CostShare/4,2);
 end if;

 if li_TotFedCost > 0 then
    li_TotFedEst := round(li_TotFedCost/4,2);
 end if;

open cur_type for
  SELECT li_totcost as TOT_COST,
         li_totFedCost as TOT_FED_COST,
         li_CostShare as COSTSHARE,
         li_CostShareEst as COSTSHARE_EST,
         li_TotFedEst as FED_COST_EST,
         li_TOTEst as TOT_EST
  FROM DUAL;


end;

---------------------------------
-- procedure  getBalanceOfFunds 
---------------------------------
procedure  getBalanceOfFunds  ( as_proposal_number in osp$budget.proposal_number%type,
															 cur_type IN OUT result_sets.cur_generic) 
is

begin
open cur_type for
--	select sum(round(total_cost)) COST,
select sum(round (total_cost - cost_sharing_amount) ) COST,
          budget_period PERIOD
	from osp$budget_periods
	where proposal_number = as_proposal_number
    and budget_period > 1
	and version_number = fn_get_version(as_proposal_number)
   group by budget_period;

end;

---------------------------------
-- procedure  getProgramIncome 
---------------------------------
procedure getProgramIncome (as_proposal_number in osp$budget.proposal_number%type,
                                              cur_type IN OUT result_sets.cur_generic)
is

begin
open cur_type for
  SELECT DECODE(SUM(AMOUNT),null,0,SUM(AMOUNT)) COST
  FROM   OSP$BUDGET_PROJECT_INCOME
  WHERE  PROPOSAL_NUMBER=as_proposal_number
  AND    VERSION_NUMBER = fn_get_version(as_proposal_number);

end;


---------------------------------
-- procedure  getTravel 
---------------------------------
procedure  getTravel  ( as_proposal_number in osp$budget.proposal_number%type,
								 cur_type IN OUT result_sets.cur_generic)

is

begin
open cur_type for
	select	decode(sum(bd.line_item_cost),null,0,sum(bd.line_item_cost))  COST,
				decode(sum(bd.COST_SHARING_AMOUNT),null,0,sum(bd.cost_sharing_amount)) COST_SHARING
	from   	osp$budget_details bd,
       		OSP$BUDGET_CATEGORY_MAPPING mapping,
       		osp$budget_category_maps maps,
       		osp$budget b
	where  	bd.proposal_number = as_proposal_number
  	AND  		B.PROPOSAL_NUMBER = BD.PROPOSAL_NUMBER
  	AND  		B.VERSION_NUMBER = BD.VERSION_NUMBER
  	AND  		B.VERSION_NUMBER = fn_Get_Version(as_proposal_number)
  	AND  		bd.budget_category_code =  mapping.COEUS_CATEGORY_CODE
  	and  		MAPPING.mapping_name =  maps.mapping_name
  and  		MAPPING.target_category_code =  maps.target_category_code
  AND  		MAPS.target_Category_code in ('73','74')
  AND  		MAPPING.MAPPING_NAME = 'S2S';


END;

---------------------------------
-- procedure  getEquipment 
---------------------------------
procedure  getEquipment  ( as_proposal_number in osp$budget.proposal_number%type,
								 cur_type IN OUT result_sets.cur_generic)

is

begin
open cur_type for
	select	decode(sum(bd.line_item_cost),null,0,sum(bd.line_item_cost))  COST,
				decode(sum(bd.COST_SHARING_AMOUNT),null,0,sum(bd.cost_sharing_amount)) COST_SHARING
	from   	osp$budget_details bd,
       		OSP$BUDGET_CATEGORY_MAPPING mapping,
       		osp$budget_category_maps maps,
       		osp$budget b
	where  	bd.proposal_number = as_proposal_number
  	AND  		B.PROPOSAL_NUMBER = BD.PROPOSAL_NUMBER
  	AND  		B.VERSION_NUMBER = BD.VERSION_NUMBER
  	AND  		B.VERSION_NUMBER = fn_Get_Version(as_proposal_number)
  	AND  		bd.budget_category_code =  mapping.COEUS_CATEGORY_CODE
  	and  		MAPPING.mapping_name =  maps.mapping_name
  and  		MAPPING.target_category_code =  maps.target_category_code
  AND  		MAPS.target_Category_code = '42'
  AND  		MAPPING.MAPPING_NAME = 'S2S';


END;

---------------------------------
-- procedure  getSupplies
---------------------------------
procedure  getSupplies  ( as_proposal_number in osp$budget.proposal_number%type,
								 cur_type IN OUT result_sets.cur_generic)

is

begin
open cur_type for
	select	decode(sum(bd.line_item_cost),null,0,sum(bd.line_item_cost))  COST,
				decode(sum(bd.COST_SHARING_AMOUNT),null,0,sum(bd.cost_sharing_amount)) COST_SHARING
	from   	osp$budget_details bd,
       		OSP$BUDGET_CATEGORY_MAPPING mapping,
       		osp$budget_category_maps maps,
       		osp$budget b
	where  	bd.proposal_number = as_proposal_number
  	AND  		B.PROPOSAL_NUMBER = BD.PROPOSAL_NUMBER
  	AND  		B.VERSION_NUMBER = BD.VERSION_NUMBER
  	AND  		B.VERSION_NUMBER = fn_Get_Version(as_proposal_number)
  	AND  		bd.budget_category_code =  mapping.COEUS_CATEGORY_CODE
  	and  		MAPPING.mapping_name =  maps.mapping_name
  and  		MAPPING.target_category_code =  maps.target_category_code
  AND  		MAPS.target_Category_code = '43'
  AND  		MAPPING.MAPPING_NAME = 'S2S';


END;

---------------------------------
-- procedure  getContractual
---------------------------------
procedure  getContractual  ( as_proposal_number in osp$budget.proposal_number%type,
								 cur_type IN OUT result_sets.cur_generic)

is

begin
open cur_type for
	select	decode(sum(bd.line_item_cost),null,0,sum(bd.line_item_cost))  COST,
				decode(sum(bd.COST_SHARING_AMOUNT),null,0,sum(bd.cost_sharing_amount)) COST_SHARING
	from   	osp$budget_details bd,
       		OSP$BUDGET_CATEGORY_MAPPING mapping,
       		osp$budget_category_maps maps,
       		osp$budget b
	where  	bd.proposal_number = as_proposal_number
  	AND  		B.PROPOSAL_NUMBER = BD.PROPOSAL_NUMBER
  	AND  		B.VERSION_NUMBER = BD.VERSION_NUMBER
  	AND  		B.VERSION_NUMBER = fn_Get_Version(as_proposal_number)
  	AND  		bd.budget_category_code =  mapping.COEUS_CATEGORY_CODE
  	and  		MAPPING.mapping_name =  maps.mapping_name
  and  		MAPPING.target_category_code =  maps.target_category_code
  AND  		MAPS.target_Category_code = '04'
  AND  		MAPPING.MAPPING_NAME = 'S2S';


END;

---------------------------------
-- procedure  getConstruction
---------------------------------
procedure  getConstruction ( as_proposal_number in osp$budget.proposal_number%type,
								 cur_type IN OUT result_sets.cur_generic)

is

begin
open cur_type for
	select	decode(sum(bd.line_item_cost),null,0,sum(bd.line_item_cost))  COST,
				decode(sum(bd.COST_SHARING_AMOUNT),null,0,sum(bd.cost_sharing_amount)) COST_SHARING
	from   	osp$budget_details bd,
       		OSP$BUDGET_CATEGORY_MAPPING mapping,
       		osp$budget_category_maps maps,
       		osp$budget b
	where  	bd.proposal_number = as_proposal_number
  	AND  		B.PROPOSAL_NUMBER = BD.PROPOSAL_NUMBER
  	AND  		B.VERSION_NUMBER = BD.VERSION_NUMBER
  	AND  		B.VERSION_NUMBER = fn_Get_Version(as_proposal_number)
  	AND  		bd.budget_category_code =  mapping.COEUS_CATEGORY_CODE
  	and  		MAPPING.mapping_name =  maps.mapping_name
  and  		MAPPING.target_category_code =  maps.target_category_code
  AND  		MAPS.target_Category_code = '40'
  AND  		MAPPING.MAPPING_NAME = 'S2S';


END;

---------------------------------
-- procedure  getOtherAmt
---------------------------------
procedure  getOtherAmt ( as_proposal_number in osp$budget.proposal_number%type,
								 cur_type IN OUT result_sets.cur_generic)

is

begin
open cur_type for
	select	decode(sum(bd.line_item_cost),null,0,sum(bd.line_item_cost))  COST,
				decode(sum(bd.COST_SHARING_AMOUNT),null,0,sum(bd.cost_sharing_amount)) COST_SHARING
	from   	osp$budget_details bd,
       		OSP$BUDGET_CATEGORY_MAPPING mapping,
       		osp$budget_category_maps maps,
       		osp$budget b
	where  	bd.proposal_number = as_proposal_number
  	AND  		B.PROPOSAL_NUMBER = BD.PROPOSAL_NUMBER
  	AND  		B.VERSION_NUMBER = BD.VERSION_NUMBER
  	AND  		B.VERSION_NUMBER = fn_Get_Version(as_proposal_number)
  	AND  		bd.budget_category_code =  mapping.COEUS_CATEGORY_CODE
  	and  		MAPPING.mapping_name =  maps.mapping_name
  and  		MAPPING.target_category_code =  maps.target_category_code
  AND  		MAPS.target_Category_code in ('39','45','75','76','77','78','79','80','81','82')
  AND  		MAPPING.MAPPING_NAME = 'S2S';


END;

---------------------------------
-- procedure  getPersonnelAmt
---------------------------------
procedure  getPersonnelAmt ( as_proposal_number in osp$budget.proposal_number%type,
								 cur_type IN OUT result_sets.cur_generic)

is

begin
open cur_type for
	select	decode(sum(bd.line_item_cost),null,0,sum(bd.line_item_cost))  COST,
				decode(sum(bd.COST_SHARING_AMOUNT),null,0,sum(bd.cost_sharing_amount)) COST_SHARING
	from   	osp$budget_details bd,
       		OSP$BUDGET_CATEGORY_MAPPING mapping,
       		osp$budget_category_maps maps,
       		osp$budget b
	where  	bd.proposal_number = as_proposal_number
  	AND  		B.PROPOSAL_NUMBER = BD.PROPOSAL_NUMBER
  	AND  		B.VERSION_NUMBER = BD.VERSION_NUMBER
  	AND  		B.VERSION_NUMBER = fn_Get_Version(as_proposal_number)
  	AND  		bd.budget_category_code =  mapping.COEUS_CATEGORY_CODE
  	and  		MAPPING.mapping_name =  maps.mapping_name
  and  		MAPPING.target_category_code =  maps.target_category_code
  AND  		MAPS.category_type = 'P' 
  AND  		MAPPING.MAPPING_NAME = 'S2S';


END;

---------------------------------
-- procedure  getFringes
---------------------------------
procedure  getFringes( as_proposal_number in osp$budget.proposal_number%type,
								 cur_type IN OUT result_sets.cur_generic)

is

begin
open cur_type for
			select 	DECODE( sum(osp$budget_details_cal_amts.calculated_cost ),NULL,0,
                    sum(osp$budget_details_cal_amts.calculated_cost ) ) as COST
			from  	osp$budget_details_cal_amts,
						OSP$RATE_CLASS	 	
			where 	osp$budget_details_cal_amts.proposal_number= as_proposal_number
			and   	osp$budget_details_cal_amts.version_number = fn_Get_Version(as_proposal_number)
			and   	osp$budget_details_cal_amts.rate_class_code = osp$rate_class.rate_class_code
			and  	(( osp$RATE_CLASS.rate_class_TYPE = 'E' )
				   OR
					(osp$RATE_CLASS.rate_class_TYPE = 'V' ));


END;

end;
/
