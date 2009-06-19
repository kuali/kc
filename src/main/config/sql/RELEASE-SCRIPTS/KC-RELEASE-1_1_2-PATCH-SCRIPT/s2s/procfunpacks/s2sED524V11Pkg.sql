
CREATE OR REPLACE package s2sED524V11Pkg as

function fn_Get_numPeriods  (as_proposal_number in osp$eps_proposal.proposal_number%type,
				                  ai_version_number  in osp$budget.version_number%type)
   return number;

function fn_get_org (as_proposal_number in osp$eps_proposal.proposal_number%type)
  return varchar2 ;

procedure getsimpleinfo (as_proposal_number in osp$eps_proposal.proposal_number%type,
                           cur_type IN OUT result_sets.cur_generic);


procedure getIDCInfo (as_proposal_number in osp$budget.proposal_number%type,
									cur_type IN OUT result_sets.cur_generic);

procedure get_idc_cost_Sharing (as_proposal_number in osp$budget.proposal_number%type,
									ai_version in osp$budget.version_number%type,
                           ai_period in osp$budget_periods.budget_period%type,
									cur_type IN OUT result_sets.cur_generic);

procedure  getPersonnelCosts ( as_proposal_number in osp$budget.proposal_number%type,
								ai_version_number  in osp$budget.version_number%type,
								ai_period in osp$budget_periods.budget_period%type,
								 cur_type IN OUT result_sets.cur_generic);

procedure get_fringe (as_proposal_number in osp$budget.proposal_number%type,
							ai_version_number IN OSP$BUDGET.VERSION_NUMBER%TYPE,
							ai_period in osp$budget_details.budget_period%type,
							cur_type IN OUT result_sets.cur_generic) ;

procedure getBudgetYear (as_proposal_number in osp$budget.proposal_number%type,
									ai_version_number  in osp$budget.version_number%type,
                           ai_period in osp$budget_periods.budget_period%type,
									cur_type IN OUT result_sets.cur_generic);

procedure  getTravel  ( as_proposal_number in osp$budget.proposal_number%type,
								ai_version_number  in osp$budget.version_number%type,
								ai_period in osp$budget_periods.budget_period%type,
								 cur_type IN OUT result_sets.cur_generic);

procedure  getEquipment  ( as_proposal_number in osp$budget.proposal_number%type,
								ai_version_number  in osp$budget.version_number%type,
								ai_period in osp$budget_periods.budget_period%type,
								 cur_type IN OUT result_sets.cur_generic);

procedure  getSupplies  ( as_proposal_number in osp$budget.proposal_number%type,
								ai_version_number  in osp$budget.version_number%type,
								ai_period in osp$budget_periods.budget_period%type,
								 cur_type IN OUT result_sets.cur_generic);

procedure  getContractual  ( as_proposal_number in osp$budget.proposal_number%type,
								ai_version_number  in osp$budget.version_number%type,
								ai_period in osp$budget_periods.budget_period%type,
								 cur_type IN OUT result_sets.cur_generic);

procedure  getConstruction ( as_proposal_number in osp$budget.proposal_number%type,
								ai_version_number  in osp$budget.version_number%type,
								ai_period in osp$budget_periods.budget_period%type,
								 cur_type IN OUT result_sets.cur_generic);

procedure  getOther ( as_proposal_number in osp$budget.proposal_number%type,
								ai_version_number  in osp$budget.version_number%type,
								ai_period in osp$budget_periods.budget_period%type,
								 cur_type IN OUT result_sets.cur_generic);

procedure  getTraining ( as_proposal_number in osp$budget.proposal_number%type,
								ai_version_number  in osp$budget.version_number%type,
								ai_period in osp$budget_periods.budget_period%type,
								 cur_type IN OUT result_sets.cur_generic);

end;


/




CREATE OR REPLACE package body s2sED524V11Pkg as


function fn_get_cog_agency(as_proposal_number osp$eps_proposal.proposal_number%type)
		return varchar2;

--------------------------------------------
-- fn_numPeriods 
--    return number of budget periods 
--------------------------------------------
function fn_Get_numPeriods  (as_proposal_number in osp$eps_proposal.proposal_number%type,
				     ai_version_number  in osp$budget.version_number%type)
   return number is

li_numperiods number;

begin


   	select max(budget_period)
   	into   li_numperiods
   	from   osp$budget_periods
   	where  proposal_number = as_proposal_number
   	and    version_number = ai_version_number;


return li_numperiods;

 	Exception
  		when no_data_found then
       dbms_output.put_line ('exception');
 		li_numperiods:= 0;
      return li_numperiods;

	
end;

--------------------------
-- function fn_get_org 
-- changed for case 2406 
--------------------------
function fn_get_org (as_proposal_number in osp$eps_proposal.proposal_number%type)
  return varchar2 is

ls_org  varchar2(60);

begin
	select o.organization_name
      into   ls_org
 --     from   osp$organization o, osp$eps_proposal p 
    from   osp$organization o, osp$eps_prop_sites p 
	where  p.proposal_number = as_proposal_number
	and    p.organization_id = o.organization_id
	and    p.location_type_code = 1 ;

	
	return ls_org;

      EXCEPTION
		when no_data_found then
		ls_org := 'NONE';
      return ls_org;
end;

---------------------------------
-- procedure getsimpleinfo
---------------------------------
procedure getsimpleinfo (as_proposal_number in osp$eps_proposal.proposal_number%type,
                           cur_type IN OUT result_sets.cur_generic)
is

 liversion number;
 liperiods number;
 lsorg varchar2(60);

  begin


 select s2spackage.fn_get_version(as_proposal_number ) 
 into liversion from dual;


 select fn_get_org(as_proposal_number)
 into lsorg from dual;

 select fn_Get_numPeriods (as_proposal_number, s2spackage.fn_get_version(as_proposal_number ))
 into liperiods from dual;


    open cur_type for
		SELECT  s2spackage.fn_get_version(as_proposal_number ) as VERSION,
			 fn_Get_numPeriods (as_proposal_number, s2spackage.fn_get_version(as_proposal_number ) ) as NUMPERIODS,
		       fn_get_org (as_proposal_number) as  LEGALNAME
            from dual;
            

  end;





-------------------------
--procedure getBudgetYear
--changed for case 2777
--------------------------
procedure getBudgetYear (as_proposal_number in osp$budget.proposal_number%type,
									ai_version_number  in osp$budget.version_number%type,
                           ai_period in osp$budget_periods.budget_period%type,
									cur_type IN OUT result_sets.cur_generic) is

 li_cost				number := 0;
 li_direct_cost	number := 0;
 li_total_direct  number := 0;
 li_idc				number := 0;
 li_cs				number := 0;
 li_training_cost	number := 0;
 li_training_cs	number := 0;

begin

/* get total costs */

 SELECT   decode(BP.TOTAL_COST,null,0,BP.TOTAL_COST) ,
     				decode(BP.TOTAL_DIRECT_COST,null,0,BP.TOTAL_DIRECT_COST) ,
     				decode(BP.TOTAL_INDIRECT_COST,null,0,BP.TOTAL_INDIRECT_COST)    ,
               decode(BP.COST_SHARING_AMOUNT,null,0,BP.COST_SHARING_AMOUNT)  	
         INTO  li_cost, li_direct_cost, li_idc, li_cs	
    		FROM  OSP$BUDGET_PERIODS BP
    		WHERE BP.PROPOSAL_NUMBER = AS_PROPOSAL_NUMBER
    		AND   BP.VERSION_NUMBER = AI_VERSION_NUMBER
     		AND   BP.BUDGET_PERIOD = ai_period;

/* get training costs - need to exclude from totals */
BEGIN
   select	decode(sum(bd.line_item_cost),null,0,sum(bd.line_item_cost)),
            decode(sum(bd.COST_SHARING_AMOUNT) ,null,0,sum(bd.COST_SHARING_AMOUNT))
   into     li_training_cost, li_training_cs
	from   	osp$budget_details bd,
       		OSP$BUDGET_CATEGORY_MAPPING mapping,
       		osp$budget_category_maps maps,
       		osp$budget b
	where  	bd.proposal_number = as_proposal_number
  	AND  		B.PROPOSAL_NUMBER = BD.PROPOSAL_NUMBER
  	AND  		B.VERSION_NUMBER = BD.VERSION_NUMBER
  	AND  		B.VERSION_NUMBER = ai_version_number
	and		Bd.budget_period = ai_period
  	AND  		bd.budget_category_code =  mapping.COEUS_CATEGORY_CODE
  	and  		MAPPING.mapping_name =  maps.mapping_name
  and  		MAPPING.target_category_code =  maps.target_category_code
  AND  		MAPS.target_Category_code = '75'
  AND  		MAPPING.MAPPING_NAME = 'S2S'  ;

EXCEPTION
	WHEN OTHERS THEN
   dbms_output.put_line('in exception');
	li_training_cost := 0;
   li_training_cs := 0;
END;

li_cs := nvl(li_cs,0) - nvl(li_training_cs,0);
li_total_direct := nvl(li_direct_cost,0) - nvl(li_training_cost,0);


open cur_type for
  SELECT nvl(li_cost,0) as total_cost,
         nvl(li_total_direct,0)  as total_direct_cost,
         nvl(li_idc,0)  as total_indirect_cost,
         nvl(li_cs,0)   as cost_sharing		
    		FROM  dual;

end;
---------------------------------
-- procedure  getEquipment 
---------------------------------
procedure  getEquipment  ( as_proposal_number in osp$budget.proposal_number%type,
								ai_version_number  in osp$budget.version_number%type,
								ai_period in osp$budget_periods.budget_period%type,
								 cur_type IN OUT result_sets.cur_generic)

is

begin
open cur_type for
	select	bd.budget_period  as BUDGET_PERIOD,
        		decode(sum(bd.line_item_cost),null,0,sum(bd.line_item_cost))  COST,
				decode(sum(bd.COST_SHARING_AMOUNT),null,0,sum(bd.cost_sharing_amount)) COST_SHARING
	from   	osp$budget_details bd,
       		OSP$BUDGET_CATEGORY_MAPPING mapping,
       		osp$budget_category_maps maps,
       		osp$budget b
	where  	bd.proposal_number = as_proposal_number
  	AND  		B.PROPOSAL_NUMBER = BD.PROPOSAL_NUMBER
  	AND  		B.VERSION_NUMBER = BD.VERSION_NUMBER
  	AND  		B.VERSION_NUMBER = ai_version_number
	and		Bd.budget_period = ai_period
  	AND  		bd.budget_category_code =  mapping.COEUS_CATEGORY_CODE
  	and  		MAPPING.mapping_name =  maps.mapping_name
  and  		MAPPING.target_category_code =  maps.target_category_code
  AND  		MAPS.target_Category_code = '42'
  AND  		MAPPING.MAPPING_NAME = 'S2S'
  GROUP BY  bd.budget_period;


END;


---------------------------------------
-- procedure  getTravel 
-----------------------------------------
procedure  getTravel  ( as_proposal_number in osp$budget.proposal_number%type,
								ai_version_number  in osp$budget.version_number%type,
								ai_period in osp$budget_periods.budget_period%type,
								 cur_type IN OUT result_sets.cur_generic)

is
begin
open cur_type for
	select	bd.budget_period  as BUDGET_PERIOD,
        		sum(nvl(bd.line_item_cost,0))  COST,
				sum(nvl(bd.COST_SHARING_AMOUNT,0)) COST_SHARING
	from   	osp$budget_details bd,
       		OSP$BUDGET_CATEGORY_MAPPING mapping,
       		osp$budget_category_maps maps,
       		osp$budget b
	where  	bd.proposal_number = as_proposal_number
  	AND  		B.PROPOSAL_NUMBER = BD.PROPOSAL_NUMBER
  	AND  		B.VERSION_NUMBER = BD.VERSION_NUMBER
  	AND  		B.VERSION_NUMBER = ai_version_number
	and		Bd.budget_period = ai_period
  	AND  		bd.budget_category_code =  mapping.COEUS_CATEGORY_CODE
  	and  		MAPPING.mapping_name =  maps.mapping_name
  and  		MAPPING.target_category_code =  maps.target_category_code
  AND  		MAPS.target_Category_code in ('73','74')
  AND  		MAPPING.MAPPING_NAME = 'S2S'
  GROUP BY  bd.budget_period;

end;

---------------------------------------
-- procedure  getSupplies 
-----------------------------------------

procedure  getSupplies  ( as_proposal_number in osp$budget.proposal_number%type,
								ai_version_number  in osp$budget.version_number%type,
								ai_period in osp$budget_periods.budget_period%type,
								 cur_type IN OUT result_sets.cur_generic)

is
begin
open cur_type for
	select	bd.budget_period  as BUDGET_PERIOD,
        		decode(sum(bd.line_item_cost), null, 0, sum(bd.line_item_cost))  COST,
				decode(sum(bd.COST_SHARING_AMOUNT), null, 0, sum(bd.COST_SHARING_AMOUNT)) COST_SHARING
	from   	osp$budget_details bd,
       		OSP$BUDGET_CATEGORY_MAPPING mapping,
       		osp$budget_category_maps maps,
       		osp$budget b
	where  	bd.proposal_number = as_proposal_number
  	AND  		B.PROPOSAL_NUMBER = BD.PROPOSAL_NUMBER
  	AND  		B.VERSION_NUMBER = BD.VERSION_NUMBER
  	AND  		B.VERSION_NUMBER = ai_version_number
	and		Bd.budget_period = ai_period
  	AND  		bd.budget_category_code =  mapping.COEUS_CATEGORY_CODE
  	and  		MAPPING.mapping_name =  maps.mapping_name
  and  		MAPPING.target_category_code =  maps.target_category_code
  AND  		MAPS.target_Category_code = '43'
  AND  		MAPPING.MAPPING_NAME = 'S2S'
  GROUP BY  bd.budget_period;

end;

---------------------------------------
-- procedure  getContractual
-----------------------------------------

procedure  getContractual  ( as_proposal_number in osp$budget.proposal_number%type,
								ai_version_number  in osp$budget.version_number%type,
								ai_period in osp$budget_periods.budget_period%type,
								 cur_type IN OUT result_sets.cur_generic)

is
begin
open cur_type for
	select	bd.budget_period  as BUDGET_PERIOD,
        		sum(nvl(bd.line_item_cost,0))  COST,
				sum(nvl(bd.COST_SHARING_AMOUNT,0)) COST_SHARING
	from   	osp$budget_details bd,
       		OSP$BUDGET_CATEGORY_MAPPING mapping,
       		osp$budget_category_maps maps,
       		osp$budget b
	where  	bd.proposal_number = as_proposal_number
  	AND  		B.PROPOSAL_NUMBER = BD.PROPOSAL_NUMBER
  	AND  		B.VERSION_NUMBER = BD.VERSION_NUMBER
  	AND  		B.VERSION_NUMBER = ai_version_number
	and		Bd.budget_period = ai_period
  	AND  		bd.budget_category_code =  mapping.COEUS_CATEGORY_CODE
  	and  		MAPPING.mapping_name =  maps.mapping_name
  and  		MAPPING.target_category_code =  maps.target_category_code
  AND  		MAPS.target_Category_code = '04'
  AND  		MAPPING.MAPPING_NAME = 'S2S'
  GROUP BY  bd.budget_period;

end;

---------------------------------------
-- procedure  getTraining
-----------------------------------------

procedure  getTraining  ( as_proposal_number in osp$budget.proposal_number%type,
								ai_version_number  in osp$budget.version_number%type,
								ai_period in osp$budget_periods.budget_period%type,
								 cur_type IN OUT result_sets.cur_generic)

is
begin
open cur_type for
	select	bd.budget_period  as BUDGET_PERIOD,
        		sum(nvl(bd.line_item_cost,0))  COST,
				sum(nvl(bd.COST_SHARING_AMOUNT,0)) COST_SHARING
	from   	osp$budget_details bd,
       		OSP$BUDGET_CATEGORY_MAPPING mapping,
       		osp$budget_category_maps maps,
       		osp$budget b
	where  	bd.proposal_number = as_proposal_number
  	AND  		B.PROPOSAL_NUMBER = BD.PROPOSAL_NUMBER
  	AND  		B.VERSION_NUMBER = BD.VERSION_NUMBER
  	AND  		B.VERSION_NUMBER = ai_version_number
	and		Bd.budget_period = ai_period
  	AND  		bd.budget_category_code =  mapping.COEUS_CATEGORY_CODE
  	and  		MAPPING.mapping_name =  maps.mapping_name
  and  		MAPPING.target_category_code =  maps.target_category_code
  AND  		MAPS.target_Category_code = '75'
  AND  		MAPPING.MAPPING_NAME = 'S2S'
  GROUP BY  bd.budget_period;

end;

-------------------------------
-- procedure getPersonnelCosts
-------------------------------
procedure  getPersonnelCosts ( as_proposal_number in osp$budget.proposal_number%type,
								ai_version_number  in osp$budget.version_number%type,
								ai_period in osp$budget_periods.budget_period%type,
								 cur_type IN OUT result_sets.cur_generic)


is
begin
open cur_type for
	select	bd.budget_period  BUDGET_PERIOD,
        		sum(nvl(bd.line_item_cost,0))  COST,
				sum(nvl(bd.COST_SHARING_AMOUNT,0)) COST_SHARING
	from   	osp$budget_details bd,
       		OSP$BUDGET_CATEGORY_MAPPING mapping,
       		osp$budget_category_maps maps,
       		osp$budget b
	where  	bd.proposal_number = as_proposal_number
  	AND  		B.PROPOSAL_NUMBER = BD.PROPOSAL_NUMBER
  	AND  		B.VERSION_NUMBER = BD.VERSION_NUMBER
  	AND  		B.VERSION_NUMBER = ai_version_number
	and		Bd.budget_period = ai_period
  	AND  		bd.budget_category_code =  mapping.COEUS_CATEGORY_CODE
  	and  		MAPPING.mapping_name =  maps.mapping_name
  and  		MAPPING.target_category_code =  maps.target_category_code
  AND  		MAPS.Category_type = 'P'
  AND  		MAPPING.MAPPING_NAME = 'S2S'
  group by  bd.budget_period;


end;






-------------------------------
-- procedure getOther
--  direct costs for budget categories other than subcontracts, equipment, travel, materials and supplies,
--  training, and personnel
--  includes LA, but not fringe
-------------------------------

procedure  getOther ( as_proposal_number in osp$budget.proposal_number%type,
								ai_version_number  in osp$budget.version_number%type,
								ai_period in osp$budget_periods.budget_period%type,
								 cur_type IN OUT result_sets.cur_generic)

is

li_fringe	number;
li_fringe_cs number;
li_cost number;
li_cost_cs number;
li_cost1 number;
li_cost1_cs number;
li_total_cost number;
li_total_cs number;
li_period number;

begin

begin
/* get fringe */
select  decode ( sum(osp$budget_personnel_cal_amts.calculated_cost ),NULL, 0,
					   sum(osp$budget_personnel_cal_amts.calculated_cost ) ) ,
			  decode ( sum(osp$budget_personnel_cal_amts.calculated_cost_sharing ),NULL, 0,
					   sum(osp$budget_personnel_cal_amts.calculated_cost_sharing ) ) 
into li_fringe, li_fringe_cs
   from   	osp$budget_personnel_cal_amts,  osp$budget_personnel_details,  OSP$RATE_CLASS
   where  	osp$budget_personnel_cal_amts.proposal_number = as_proposal_number
   and    	osp$budget_personnel_cal_amts.version_number = ai_version_number
   and    	osp$budget_personnel_cal_amts.budget_period  = ai_period
   and    	osp$budget_personnel_cal_amts.rate_class_code = osp$rate_class.rate_class_code
   and   ( (osp$RATE_CLASS.rate_class_TYPE = 'E' AND
      osp$budget_personnel_cal_amts.RATE_TYPE_CODE <> 3)
       OR
     (osp$RATE_CLASS.rate_class_TYPE = 'V' AND
       osp$budget_personnel_cal_amts.RATE_TYPE_CODE <> 2))
   and   osp$budget_personnel_cal_Amts.person_number = osp$budget_personnel_details.person_number
   and   osp$budget_personnel_details.proposal_number =  osp$budget_personnel_cal_amts.proposal_number
   and   osp$budget_personnel_details.line_item_number = osp$budget_personnel_cal_amts.line_item_number
   and   osp$budget_personnel_details.version_number = ai_version_number
   and   osp$budget_personnel_details.budget_period = ai_period;

exception when OTHERS then
   li_fringe := 0;
   li_fringe_Cs := 0;
end;

begin
	select	bd.budget_period  ,
        		decode(sum(bd.line_item_cost),null,0,sum(bd.line_item_cost))  ,
				decode(sum(bd.COST_SHARING_AMOUNT),null,0,sum(bd.cost_sharing_amount)) 
   into     li_period, li_cost, li_cost_cs
	from   	osp$budget_details bd,
       		OSP$BUDGET_CATEGORY_MAPPING mapping,
       		osp$budget_category_maps maps,
       		osp$budget b
	where  	bd.proposal_number = as_proposal_number
  	AND  		B.PROPOSAL_NUMBER = BD.PROPOSAL_NUMBER
  	AND  		B.VERSION_NUMBER = BD.VERSION_NUMBER
  	AND  		B.VERSION_NUMBER = ai_version_number
	and		Bd.budget_period = ai_period
  	AND  		bd.budget_category_code =  mapping.COEUS_CATEGORY_CODE
  	and  		MAPPING.mapping_name =  maps.mapping_name
  and  		MAPPING.target_category_code =  maps.target_category_code
  AND  		MAPS.target_Category_code NOT IN ('04','42', '43','73','74','75')
  AND       MAPS.category_type = 'O'
  AND  		MAPPING.MAPPING_NAME = 'S2S'
  group by  bd.budget_period;

exception WHEN OTHERS then
	li_period :=0;
   li_cost := 0;
   li_cost_cs := 0;
end;

begin
select  	 a.budget_period,
          decode(sum(a.calculated_cost) ,null,0,sum(a.calculated_cost)),
          decode(sum(a.calculated_cost_sharing) , null, 0, sum(a.calculated_cost_sharing))
  into    li_period, li_cost1, li_cost1_cs
 from   		osp$budget_details_cal_amts a, osp$budget_details d , osp$rate_class r, osp$budget b
 where  		a.proposal_number = as_proposal_number
   and      a.proposal_number = b.proposal_number
   and      b.version_number = ai_version_number
 and   		b.version_number = a.version_number
 and   		a.rate_class_code = r.rate_class_code
 and 		   r.rate_class_type != 'O'
 and   		a.proposal_number = d.proposal_number
 and   		a.budget_period = d.budget_period
 and			a.budget_period = ai_period
 and   		a.version_number = d.version_number
 and   		a.line_item_number = d.line_item_number
group by 	a.budget_period;

exception when OTHERS then
	li_cost1 := 0;
   li_cost1_cs := 0;
end;

li_total_cost := 0;
li_total_cs := 0;
li_total_cost := li_cost + li_cost1 - li_fringe;
li_total_cs := li_cost_cs + li_cost1_cs - li_fringe_cs;

open cur_type for
select li_total_cost as COST,
       li_total_cs as COST_SHARING
from   dual;

end;

 -------------------------------
-- procedure getConstruction 
-------------------------------

procedure  getConstruction ( as_proposal_number in osp$budget.proposal_number%type,
								ai_version_number  in osp$budget.version_number%type,
								ai_period in osp$budget_periods.budget_period%type,
								 cur_type IN OUT result_sets.cur_generic)

is
begin
open cur_type for
	select 0 as COST,
          0 as COST_SHARING
    from dual;
end;
          
    

--------------------------------------------------------------
-- procedure get_fringe
--        gets fringe for a budget period
--------------------------------------------------------------
procedure get_fringe (as_proposal_number in osp$budget.proposal_number%type,
							ai_version_number IN OSP$BUDGET.VERSION_NUMBER%TYPE,
							ai_period in osp$budget_details.budget_period%type,
							cur_type IN OUT result_sets.cur_generic) is

begin

open cur_type for

	select  decode ( sum(osp$budget_personnel_cal_amts.calculated_cost ),NULL, 0,
					   sum(osp$budget_personnel_cal_amts.calculated_cost ) ) as FRINGE,
			  decode ( sum(osp$budget_personnel_cal_amts.calculated_cost_sharing ),NULL, 0,
					   sum(osp$budget_personnel_cal_amts.calculated_cost_sharing ) ) as FRINGE_COST_SHARING
   from   	osp$budget_personnel_cal_amts,  osp$budget_personnel_details,  OSP$RATE_CLASS
   where  	osp$budget_personnel_cal_amts.proposal_number = as_proposal_number
   and    	osp$budget_personnel_cal_amts.version_number = ai_version_number
   and    	osp$budget_personnel_cal_amts.budget_period  = ai_period
   and    	osp$budget_personnel_cal_amts.rate_class_code = osp$rate_class.rate_class_code
   and   ( (osp$RATE_CLASS.rate_class_TYPE = 'E' AND
      osp$budget_personnel_cal_amts.RATE_TYPE_CODE <> 3)
       OR
     (osp$RATE_CLASS.rate_class_TYPE = 'V' AND
       osp$budget_personnel_cal_amts.RATE_TYPE_CODE <> 2))
   and   osp$budget_personnel_cal_Amts.person_number = osp$budget_personnel_details.person_number
   and   osp$budget_personnel_details.proposal_number =  osp$budget_personnel_cal_amts.proposal_number
   and   osp$budget_personnel_details.line_item_number = osp$budget_personnel_cal_amts.line_item_number
   and   osp$budget_personnel_details.version_number = ai_version_number
   and   osp$budget_personnel_details.budget_period = ai_period;


end;



----------------------------------
-- procedure get_idc_cost_Sharing 
----------------------------------

procedure get_idc_cost_Sharing (as_proposal_number in osp$budget.proposal_number%type,
									ai_version in osp$budget.version_number%type,
                           ai_period in osp$budget_periods.budget_period%type,
									cur_type IN OUT result_sets.cur_generic)
is

begin
 open cur_type for

	select	sum(bd.calculated_cost_sharing) as cost_sharing
	from   	osp$budget_details_cal_amts bd,
            osp$rate_class rc
	where  	bd.proposal_number = as_proposal_number
   and      rc.rate_class_code = bd.rate_class_code
   and      rc.rate_class_type = 'O'
  	AND  		bd.VERSION_NUMBER = ai_version
	and		bd.budget_period = ai_period
   group by bd.budget_period;
end;

 

-------------------------------------
-- getIDCInfo
-- form requires: (all optional)
--  is agreement approved
--  agreement from date
--  agreement to date
--  approving agency (yes or no)
--  other approving federal agency ('ED' or 'Other')
--
-- DDHS_AGREEMENT parameter - used to determine if the institution has an agreement with DHHS (1)  
-- or with another agency (0). 
--	DHHS Agreement date -  from osp$organization.indirect_cost_rate_agreement column
-- if	No DHHS agreement, but rate established with other agency - date comes from 
--   osp$organization.indirect_cost_rate_agreement column and agency from organization 
--   of the rolodex entry of the cognizant auditor
-- restricted values can be 'Is included in your approved Indirect Cost Rate Agreement?' or
--    'Complies with 34 CFR 76.564(c)(2)?'

-------------------------------------
procedure getIDCInfo (as_proposal_number in osp$budget.proposal_number%type,
									cur_type IN OUT result_sets.cur_generic)
is


ls_Dhhs 		varchar2(60);
ls_agency	varchar2(60);
ls_date     osp$organization.indirect_cost_rate_agreement%type;
ls_restricted	varchar2(60) ;

BEGIN

ls_restricted := ' ';
--ls_restricted := 'Complies with 34 CFR 76.564(c)(2)?';
--ls_restricted := 'Is included in your approved Indirect Cost Rate Agreement?';
--------------------------------
-- is agreement approved
--   check for an agreement date. osp$organization.indirect_cost_rate_agreement column.
--   if not date, assume no agreement
-- changed for case 2406 
---------------------------------

select decode(indirect_cost_rate_agreement,null,'NONE',indirect_cost_rate_agreement)
into   ls_date
from   osp$organization
where  organization_id = (select organization_id
                          --    from   osp$eps_proposal 
                         from   osp$eps_prop_sites
	                     where  proposal_number = as_proposal_number
	                     and location_type_code = 1);
	   
	     
	
	
--------------------------------------
-- get agency
---------------------------------------
--check parameter table to see if agreement is with DHHS

 ls_Dhhs := get_parameter_value('DHHS_AGREEMENT');

 if (length(ls_Dhhs) < 1 or ls_dhhs is null) then
    -- if parameter is missing, assume agreement is not with dhhs
    ls_dhhs := '0';
 end if;


 begin
    if (ls_dhhs = '0') then
     -- agreement is not with DHHS
     ls_agency := fn_get_Cog_Agency(as_proposal_number);

	 else
		-- agreement is with DHHS
     ls_agency := 'DHHS';
	 end if;
 end;


          
 open cur_type for
   SELECT ls_date as IDC_FROM_DATE,
          ls_agency as AGENCY,
          ls_restricted as RESTRICTED
   FROM   DUAL;
 
     


end;

-------------------------------------
-- function fn_get_cog_agency
-------------------------------------
function fn_get_cog_agency(as_proposal_number osp$eps_proposal.proposal_number%type)
		return varchar2
is

ls_agency		osp$rolodex.organization%type;
li_rolodex		osp$rolodex.rolodex_id%type;

begin

-- case 4223 
select	r.organization
into		ls_agency
from		osp$rolodex r, osp$organization o, OSP$EPS_PROP_SITES p
where		p.organization_id = o.organization_id
AND         p.LOCATION_TYPE_CODE = 1
and		    o.cognizant_auditor = r.rolodex_id
and         p.proposal_number = as_proposal_number;

return	ls_agency;

EXCEPTION
	When NO_DATA_FOUND then
		return 'Unknown';


end;

end;

/

