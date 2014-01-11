CREATE OR REPLACE PROCEDURE  getCostsForPrinting  (

    AS_PROPOSAL_NUMBER IN     OSP$BUDGET.PROPOSAL_NUMBER%TYPE ,  
    AI_VERSION			  IN		OSP$BUDGET.VERSION_NUMBER%type,
    AS_SPONSOR         IN     VARCHAR2,
    CUR_GENERIC        IN OUT RESULT_SETS.CUR_GENERIC ) 

IS 
    sponsor_map varchar2(20);
    li_count number;
BEGIN 
 
 
--case 2691 - replaced checking parameter table with check of Sponsor Groups in sponsor hierarchy
--to determine if sponsor or prime is NIH

li_count := fn_in_sponsor_group (as_proposal_number ,'NIH');
if (li_count = 1) then 
	sponsor_map := 'NIH_PRINTING';
else
	sponsor_map := 'NSF_PRINTING';
end if;


  OPEN CUR_GENERIC FOR 


---------------------------------------------------
--Get  costs for  direct costs for all periods IN VERSION
-- grouped by budget category
-----------------------------------------------------



select  B.version_number as VERSION_NUMBER,
        budget_period AS BUDGETPERIOD,
        mapping.TARGET_CATEGORY_CODE AS BUDGETCATEGORYCODE ,
   	  maps.description AS BUDGETCATEGORYDESC,
        ltrim(rtrim(MAPS.CATEGORY_TYPE)) AS CATEGORYTYPE,
   	  fn_get_category_desc(AS_PROPOSAL_NUMBER ,sponsor_map,b.version_number,budget_period,mapping.target_category_code) as DESCRIPTION,
        sum(bd.line_item_cost) aS COST     
from   osp$budget_details bd,  
       osp$cost_element  ce,  
       OSP$BUDGET_CATEGORY_MAPPING mapping, 
       osp$budget_category_maps maps, 
       OSP$BUDGET B 
where  bd.proposal_number = AS_PROPOSAL_NUMBER 
  AND  B.PROPOSAL_NUMBER = BD.PROPOSAL_NUMBER 
  AND  B.VERSION_NUMBER = BD.VERSION_NUMBER 
  AND  B.VERSION_NUMBER = AI_VERSION
  and  bd.cost_element = ce.cost_element  
  AND  bd.budget_category_code =  mapping.COEUS_CATEGORY_CODE 
  and  MAPPING.mapping_name =  maps.mapping_name 
  and  MAPPING.target_category_code =  maps.target_category_code 
  AND  MAPS.CATEGORY_TYPE <> 'P'
  AND  MAPPING.MAPPING_NAME = SPONSOR_MAP
group by B.VERSION_NUMBER, budget_period, mapping.target_category_code, maps.description,maps.category_type

UNION

select 	a.version_number as version_number,
          a.budget_period as BUDGETPERIOD,
          '39' as BUDGETCATEGORYCODE,
          'Other Direct Costs' AS BUDGETCATEGORYDESC,
          'O' AS CATEGORYTYPE,
          'LA ' || TO_CHAR(SUM(A.CALCULATED_COST),'$9,999,999,990') || '; ' AS DESCRIPTION,
          sum(a.calculated_cost) AS COST
	from 		osp$budget_details_cal_amts a, osp$budget_details d , osp$rate_class r, osp$budget b
	where 	a.proposal_number = AS_PROPOSAL_NUMBER
   and      a.proposal_number = b.proposal_number
   and      b.version_number = ai_version
	and 		b.version_number = a.version_number 
	and 		a.rate_class_code = r.rate_class_code
	and 		r.rate_class_type = 'L'
	and 		a.proposal_number = d.proposal_number
	and 		a.budget_period = d.budget_period
	and	 	a.version_number = d.version_number
	and 		a.line_item_number = d.line_item_number
group by a.version_number, a.budget_period;



END;
/


	