CREATE OR REPLACE package s2sPackage as

procedure get_bud_periods (as_proposal_number  IN osp$budget.PROPOSAL_NUMBER%TYPE,
		  					   ai_version_number  IN osp$budget.VERSION_NUMBER%TYPE,
	  							cur_type IN OUT result_sets.cur_generic);

procedure get_tot_bud_info  ( as_proposal_number IN OSP$BUDGET.PROPOSAL_NUMBER%TYPE,
   							      ai_version_number IN OSP$BUDGET.VERSION_NUMBER%TYPE,
  										 cur_type IN OUT result_sets.cur_generic) ;

procedure get_salary_and_months (as_proposal_number in osp$budget.proposal_number%type,
											 ai_version_number IN OSP$BUDGET.VERSION_NUMBER%TYPE,
											ai_budget_period in osp$budget_details.budget_period%type,
											as_person_id in osp$budget_personnel_details.person_id%type,
									      cur_type IN OUT result_sets.cur_generic);


procedure get_key_persons ( as_proposal_number in osp$budget.proposal_number%type,
									ai_period	in OSP$BUDGET_PERIODS.BUDGET_PERIOD%TYPE,
									ai_version in osp$budget.version_number%type,
									cur_type IN OUT result_sets.cur_generic);

procedure get_inv_and_key_persons ( as_proposal_number in osp$budget.proposal_number%type,
									 cur_type IN OUT result_sets.cur_generic);


procedure  getCostsForPrinting  ( as_proposal_number in osp$budget.proposal_number%type,
									ai_version_number  in osp$budget.version_number%type,
									ai_budget_period in osp$budget_periods.budget_period%type,
									as_sponsor in varchar2,
									cur_type IN OUT result_sets.cur_generic);

procedure  getEquipment  ( as_proposal_number in osp$budget.proposal_number%type,
								ai_version_number  in osp$budget.version_number%type,
								ai_budget_period in osp$budget_periods.budget_period%type,
								as_sponsor in varchar2,
								 cur_type IN OUT result_sets.cur_generic);

procedure getCFDA (as_proposal_number in osp$eps_proposal.proposal_number%type,
						 cur_type IN OUT result_sets.cur_generic);

procedure getCategoryType(as_category_type in osp$budget_category_maps.category_type%type,
									cur_type IN OUT result_sets.cur_generic);

procedure getSubmissionType(as_proposal_number in osp$eps_proposal.proposal_number%type,
									cur_type IN OUT result_sets.cur_generic);

procedure getSF424SubmissionType(as_proposal_number in osp$eps_proposal.proposal_number%type,
									cur_type IN OUT result_sets.cur_generic);

procedure getApplicationType (as_proposal_number IN osp$eps_proposal.proposal_number%type,
                           cur_type IN OUT result_sets.cur_generic) ;

procedure getSF424ApplicationType (as_proposal_number IN osp$eps_proposal.proposal_number%type,
                           cur_type IN OUT result_sets.cur_generic) ;

procedure getSF424RevisionType(as_proposal_number in osp$eps_proposal.proposal_number%type,
				cur_type IN OUT result_sets.cur_generic);


procedure getNarrativeType (as_proposal_number in osp$eps_proposal.proposal_number%type,
									 ai_module_number in osp$narrative.module_number%type,
									 cur_type IN OUT result_sets.cur_generic);

procedure UPD_PROP_NARR_AUTO_GEN_PDF (AV_PROPOSAL_NUMBER IN OSP$NARRATIVE.PROPOSAL_NUMBER%TYPE,
		  							 AV_MODULE_TITLE IN OSP$NARRATIVE.MODULE_TITLE%TYPE,
									 AV_MODULE_STATUS_CODE IN OSP$NARRATIVE.MODULE_STATUS_CODE%TYPE,
									 AV_CONTACT_NAME IN OSP$NARRATIVE.CONTACT_NAME%TYPE,
									 AV_PHONE_NUMBER IN OSP$NARRATIVE.PHONE_NUMBER%TYPE,
									 AV_EMAIL_ADDRESS IN OSP$NARRATIVE.EMAIL_ADDRESS%TYPE,
									 AV_COMMENTS IN OSP$NARRATIVE.COMMENTS%TYPE,
									 AV_NARRATIVE_TYPE_CODE IN OSP$NARRATIVE.NARRATIVE_TYPE_CODE%TYPE,
									 AV_UPDATE_USER IN OSP$NARRATIVE.UPDATE_USER%TYPE,
									 AV_UPDATE_TIMESTAMP IN OSP$NARRATIVE.UPDATE_TIMESTAMP%TYPE,
									 AC_TYPE IN CHAR,
									 lo_module_number IN OUT OSP$NARRATIVE.MODULE_NUMBER%TYPE,
									 lo_module_sequence_number IN OUT OSP$NARRATIVE.MODULE_SEQUENCE_NUMBER%TYPE);

function FN_DELETE_NARR_AUTO_GEN_ATTS (
		  AV_PROPOSAL_NUMBER IN OSP$NARRATIVE.PROPOSAL_NUMBER%TYPE)
		  return number;

function FN_CHECK_ERRORS_FOR_S2S (
		  AV_PROPOSAL_NUMBER IN OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE)
		  return varchar2;

function get_federal_id (av_proposal_number in osp$eps_proposal.proposal_number%type)
          return varchar2 ;

function fn_get_Narrative_Type (as_proposal_number in osp$eps_proposal.proposal_number%type,
									  ai_module_number in osp$narrative.module_number%type)
 			return number;



-- these functions were replaced by procedures to take care of rounding issue
-- fn_get_fringe 	, fn_get_tot_person_funds	, fnGetOtherPersonnelSal	,fnGetOtherPersonnelFringe

procedure get_fringe (as_proposal_number in osp$budget.proposal_number%type,
							ai_version_number IN OSP$BUDGET.VERSION_NUMBER%TYPE,
							ai_budget_period in osp$budget_details.budget_period%type,
							as_person_id in osp$budget_personnel_details.person_id%type,
							cur_type IN OUT result_sets.cur_generic);


procedure get_tot_person_funds (as_proposal_number OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
											ai_version_number osp$budget.version_number%type,
											cur_type IN OUT result_sets.cur_generic);



procedure GetOtherPersonnelSal (as_proposal_number OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
            				    ai_version_number osp$budget.version_number%type,
                				 ai_budget_period osp$budget_periods.budget_period%type,
                            as_category_code osp$budget_category_mapping.target_category_code%type,
									 cur_type IN OUT result_sets.cur_generic);


--  GetOtherPersonnelMonths added  for case 1924
procedure GetOtherPersonnelMonths (as_proposal_number OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
            				    ai_version_number osp$budget.version_number%type,
                				 ai_budget_period osp$budget_periods.budget_period%type,
                            as_category_code osp$budget_category_mapping.target_category_code%type,
									 cur_type IN OUT result_sets.cur_generic) ;


procedure Get_other_Pers_Fringe (as_proposal_number IN OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
            				    ai_version_number IN osp$budget.version_number%type,
                				 ai_budget_period IN osp$budget_periods.budget_period%type,
                            as_category_code IN osp$budget_category_mapping.target_category_code%type,
									 cur_type IN OUT result_sets.cur_generic);



function fnGetCountOtherPersonnel (AS_PROPOSAL_NUMBER OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
                AI_VERSION_NUMBER osp$budget.version_number%type,
                AI_BUDGET_PERIOD osp$budget_periods.budget_period%type,
                                AS_CATEGORY_CODE osp$budget_category_mapping.target_category_code%type)
     return number;

function fn_get_project_income (as_proposal_number IN osp$eps_proposal.proposal_number%type) return number;

function fn_get_version (as_proposal_number IN osp$budget.proposal_number%type)
		return number;

function fn_get_division(as_department in OSP$PERSON.HOME_UNIT%TYPE)
		return varchar2;
function fn_get_cog_agency(as_proposal_number osp$eps_proposal.proposal_number%type)
		return varchar2;

procedure get_eo_state_review(as_proposal_number osp$eps_proposal.proposal_number%type,
				cur_type IN OUT result_sets.cur_generic);

function fn_get_pre_dir_costsharing(as_proposal_number OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
				ai_version_number osp$budget.version_number%type,
				ai_budget_period osp$budget_periods.budget_period%type )
				return number;

function fn_get_pre_indir_costsharing(as_proposal_number OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
				ai_version_number osp$budget.version_number%type,
				ai_budget_period osp$budget_periods.budget_period%type )
				return number;


-----------------------------------------
-- following procedures added for modular budget amount fix . 9/25/06
-----------------------------------------
procedure getModBudIDC (as_proposal_number OSP$EPS_PROPOSAL.proposal_number%type,
                        ai_version_number OSP$BUDGET.version_number%type,
                          cur_type IN OUT result_sets.cur_generic) ;
procedure getModBudTot (as_proposal_number OSP$EPS_PROPOSAL.proposal_number%type,
                        ai_version_number OSP$BUDGET.version_number%type,
                          cur_type IN OUT result_sets.cur_generic) ;
procedure getModBudCostShare (as_proposal_number OSP$EPS_PROPOSAL.proposal_number%type,
                        ai_version_number OSP$BUDGET.version_number%type,
                          cur_type IN OUT result_sets.cur_generic) ;
--------------------------------------------------------
-- following procedures added for change to box 5 in rrsf424
--------------------------------------------------------
procedure getContactPerson (as_proposal_number OSP$EPS_PROPOSAL.proposal_number%type,
                            as_contact_Type  osp$parameter.value%type,
 								    cur_type IN OUT result_sets.cur_generic) ;
procedure getContactType (as_proposal_number OSP$EPS_PROPOSAL.proposal_number%type,
 								cur_type IN OUT result_sets.cur_generic) ;
---------------------------------------
-- following procedure added for change to box15 in rrsf424
---------------------------------------
procedure getLeadUnit (as_proposal_number OSP$EPS_PROPOSAL.proposal_number%type,
 								cur_type IN OUT result_sets.cur_generic) ;


FUNCTION fn_Get_base_salary (AS_PROPOSAL_NUMBER OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
                             AI_VERSION_NUMBER osp$budget_persons.version_number%TYPE,
                             AS_PERSON_ID osp$budget_persons.person_id%TYPE)
RETURN  NUMBER ;

end;

/


CREATE OR REPLACE package body s2sPackage as

-------------------------
-- internal functions
-------------------------
function fn_format_award_number (as_award_number in varchar2,
                                 as_proposal_number in OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE)
         return varchar2;

function fn_is_nih (as_proposal_number OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE)
   return varchar2;

function fn_is_numeric(as_string varchar2 ) return boolean;

-------------------------------
--procedure get_tot_bud_info
------------------------------

procedure get_tot_bud_info
   ( as_proposal_number IN OSP$BUDGET.PROPOSAL_NUMBER%TYPE,
     ai_version_number IN OSP$BUDGET.VERSION_NUMBER%TYPE,
     cur_type IN OUT result_sets.cur_generic) is

	begin
	open cur_type for

		SELECT PROPOSAL_NUMBER,
      		 VERSION_NUMBER,
   			 TOTAL_COST,
   			 TOTAL_DIRECT_COST,
   			 TOTAL_INDIRECT_COST,
   			 COST_SHARING_AMOUNT
		FROM   OSP$BUDGET
		WHERE  PROPOSAL_NUMBER = as_proposal_number
		AND    VERSION_NUMBER = ai_version_number;

end;


---------------------------------------------------
-- procedure getcostsforprinting
-- Get  costs for  direct costs for a periods
-- grouped by budget category
-- includes la
-----------------------------------------------------


procedure  getCostsForPrinting  ( as_proposal_number in osp$budget.proposal_number%type,
									ai_version_number  in osp$budget.version_number%type,
									ai_budget_period in osp$budget_periods.budget_period%type,
									as_sponsor in varchar2,
								 cur_type IN OUT result_sets.cur_generic)

is
begin
  --case#2628
open cur_type for
	select	b.version_number,
        		bd.budget_period  BUDGETPERIOD,
        		mapping.TARGET_CATEGORY_CODE  BUDGETCATEGORYCODE ,
      		maps.description  BUDGETCATEGORYDESC,
        		MAPS.CATEGORY_TYPE  CATEGORYTYPE,
            ce.description,
			   decode(bd.quantity,null,0,bd.quantity) QUANTITY,
        		decode(sum(bd.line_item_cost),null,0,sum(bd.line_item_cost))  COST,
				decode(sum(bd.COST_SHARING_AMOUNT),null,0,sum(bd.COST_SHARING_AMOUNT)) COST_SHARING
	from   	osp$budget_details bd,
       		osp$cost_element  ce,
       		OSP$BUDGET_CATEGORY_MAPPING mapping,
       		osp$budget_category_maps maps,
       		osp$budget b
	where  	bd.proposal_number = AS_PROPOSAL_NUMBER
  	AND  		B.PROPOSAL_NUMBER = BD.PROPOSAL_NUMBER
  	AND  		B.VERSION_NUMBER = BD.VERSION_NUMBER
  	AND  		B.VERSION_NUMBER = ai_version_number
	and		Bd.budget_period = ai_budget_period
 	and  		bd.cost_element = ce.cost_element
  	AND  		bd.budget_category_code =  mapping.COEUS_CATEGORY_CODE
  	and  		MAPPING.mapping_name =  maps.mapping_name
  and  		MAPPING.target_category_code =  maps.target_category_code
  AND  		MAPS.CATEGORY_TYPE <> 'P'
  and 		maps.target_category_code not in ('42')
  AND  		MAPPING.MAPPING_NAME = as_sponsor
  group by  b.version_number, bd.budget_period, mapping.target_category_code, maps.description,maps.category_type,
            ce.description , bd.quantity
 UNION
  select  	a.version_number as version_number,
         	 a.budget_period as BUDGETPERIOD,
          	'39' as BUDGETCATEGORYCODE,
          	'Other Direct Costs' AS BUDGETCATEGORYDESC,
          	'O' AS CATEGORYTYPE,
          	'LA ' || TO_CHAR(SUM(A.CALCULATED_COST),'$9,999,999,990') || '; ' AS DESCRIPTION,
				1 as quantity,
          	sum(a.calculated_cost) AS COST,
          	sum(a.calculated_cost_sharing) AS COST_SHARING
 from   		osp$budget_details_cal_amts a, osp$budget_details d , osp$rate_class r, osp$budget b
 where  		a.proposal_number = AS_PROPOSAL_NUMBER
   and      a.proposal_number = b.proposal_number
   and      b.version_number = ai_version_number
 and   		b.version_number = a.version_number
 and   		a.rate_class_code = r.rate_class_code
 and   		r.rate_class_type ='L'
 and   		a.proposal_number = d.proposal_number
 and   		a.budget_period = d.budget_period
 and			a.budget_period = ai_budget_period
 and   		a.version_number = d.version_number
 and   		a.line_item_number = d.line_item_number
group by 		a.version_number, a.budget_period, d.quantity;


end;


---------------------------------------------------
--procedure  getEquipment
-----------------------------------------------------


procedure  getEquipment  ( as_proposal_number in osp$budget.proposal_number%type,
								ai_version_number  in osp$budget.version_number%type,
								ai_budget_period in osp$budget_periods.budget_period%type,
								as_sponsor in varchar2,
								 cur_type IN OUT result_sets.cur_generic)

is
begin
  --case#2628
open cur_type for
	select	b.version_number,
        		bd.budget_period  BUDGETPERIOD,
        		mapping.TARGET_CATEGORY_CODE  BUDGETCATEGORYCODE ,
      		maps.description  BUDGETCATEGORYDESC,
        		MAPS.CATEGORY_TYPE  CATEGORYTYPE,
            bd.line_item_description DESCRIPTION,
        		sum(bd.line_item_cost)  COST,
				sum(bd.COST_SHARING_AMOUNT) COST_SHARING
	from   	osp$budget_details bd,
       		osp$cost_element  ce,
       		OSP$BUDGET_CATEGORY_MAPPING mapping,
       		osp$budget_category_maps maps,
       		osp$budget b
	where  	bd.proposal_number = as_proposal_number
  	AND  		B.PROPOSAL_NUMBER = BD.PROPOSAL_NUMBER
  	AND  		B.VERSION_NUMBER = BD.VERSION_NUMBER
  	AND  		B.VERSION_NUMBER = ai_version_number
	and		Bd.budget_period = ai_budget_period
 	and  		bd.cost_element = ce.cost_element
--   and      bd.line_item_cost > 5000
  	AND  		bd.budget_category_code =  mapping.COEUS_CATEGORY_CODE
  	and  		MAPPING.mapping_name =  maps.mapping_name
  and  		MAPPING.target_category_code =  maps.target_category_code
  AND  		MAPS.target_Category_code = '42'
  AND  		MAPPING.MAPPING_NAME = as_sponsor
  group by  b.version_number, bd.budget_period, mapping.target_category_code, maps.description,maps.category_type,
            bd.line_item_description ;


end;


-------------------------
-- procedure get_bud_periods
-------------------------

procedure get_bud_periods (as_proposal_number in osp$budget.proposal_number%type,
									ai_version_number  in osp$budget.version_number%type,
									cur_type IN OUT result_sets.cur_generic)
is
  begin
  open cur_type for
			SELECT BP.PROPOSAL_NUMBER,
     				BP.VERSION_NUMBER,
     				B.FINAL_VERSION_FLAG ,
           		BP.BUDGET_PERIOD,
     				BP.START_DATE,
     				BP.END_DATE,
    			   BP.TOTAL_COST,
     				BP.TOTAL_DIRECT_COST,
     				BP.TOTAL_INDIRECT_COST,
     				BP.COST_SHARING_AMOUNT,
     				BP.UNDERRECOVERY_AMOUNT,
     				fn_get_pre_dir_costsharing(AS_PROPOSAL_NUMBER,AI_VERSION_NUMBER,BP.BUDGET_PERIOD) as TOTAL_DIRECT_COST_SHARING,
				fn_get_pre_indir_costsharing(AS_PROPOSAL_NUMBER,AI_VERSION_NUMBER,BP.BUDGET_PERIOD) as TOTAL_INDIRECT_COST_SHARING
    		FROM OSP$BUDGET_PERIODS BP, OSP$BUDGET B
    		WHERE BP.PROPOSAL_NUMBER = AS_PROPOSAL_NUMBER
    		AND   BP.PROPOSAL_NUMBER = B.PROPOSAL_NUMBER
    		AND   BP.VERSION_NUMBER = B.VERSION_NUMBER
    		AND   B.VERSION_NUMBER = AI_VERSION_NUMBER
     		ORDER BY BP.BUDGET_PERIOD;

  end;



-------------------------------------------------------------------------------------
-- procedure get_key_persons - gets investigators , key persons, and people in budget
-- in senior personnel category

-------------------------------------------------------------------------------------

procedure get_key_persons ( as_proposal_number in osp$budget.proposal_number%type,
										ai_period	in OSP$BUDGET_PERIODS.BUDGET_PERIOD%TYPE,
										ai_version in osp$budget.version_number%type,
									 cur_type IN OUT result_sets.cur_generic)

is
	begin





open cur_type for
      -- pi
		SELECT 	PP.PERSON_ID , 0 as sort_ID,
   				PP.LAST_NAME,
   				DECODE(PP.FIRST_NAME,NULL,' ',PP.FIRST_NAME) FIRST_NAME,
   				DECODE( PP.MIDDLE_NAME,NULL,' ',PP.MIDDLE_NAME) MIDDLE_NAME,
     				'PD/PI' as PROJECT_ROLE,
					PI.NON_MIT_PERSON_FLAG as NON_MIT_PERSON
		FROM  	OSP$EPS_PROP_PERSON PP, OSP$eps_PROP_INVESTIGATORS PI
		WHERE 	PP.PROPOSAL_NUMBER = as_proposal_number
		AND 		PP.PERSON_ID = PI.PERSON_ID
		AND 		PP.PROPOSAL_NUMBER = PI.PROPOSAL_NUMBER
      AND      PI.PRINCIPAL_INVESTIGATOR_FLAG = 'Y'

		UNION

      --co-investigators
 		SELECT 	PP.PERSON_ID , pp.sort_id,
   				PP.LAST_NAME,
   				DECODE(PP.FIRST_NAME,NULL,' ',PP.FIRST_NAME) FIRST_NAME,
   				DECODE( PP.MIDDLE_NAME,NULL,' ',PP.MIDDLE_NAME) MIDDLE_NAME,
     				'Co-PD/PI' as PROJECT_ROLE,
					PI.NON_MIT_PERSON_FLAG as NON_MIT_PERSON
		FROM  	OSP$EPS_PROP_PERSON PP, OSP$eps_PROP_INVESTIGATORS PI,osp$budget_personnel_details pd
		WHERE 	PP.PROPOSAL_NUMBER = as_proposal_number
		AND 		PP.PERSON_ID = PI.PERSON_ID
		AND 		PP.PROPOSAL_NUMBER = PI.PROPOSAL_NUMBER
      AND      PI.PRINCIPAL_INVESTIGATOR_FLAG = 'N'
      and      pd.proposal_number = as_proposal_number
      and      pd.person_id = pp.person_id

       UNION
--KEY PERSONS
		SELECT 	PP.PERSON_ID, pp.sort_ID,
   				PP.LAST_NAME ,
   				DECODE(PP.FIRST_NAME, NULL,' ',PP.FIRST_NAME) FIRST_NAME,
   				DECODE(PP.MIDDLE_NAME,NULL,' ',PP.MIDDLE_NAME) MIDDLE_NAME,
   				K.PROJECT_ROLE,
               K.NON_MIT_PERSON_FLAG as NON_MIT_PERSON
		FROM  	OSP$EPS_PROP_PERSON PP, OSP$EPS_PROP_KEY_PERSONS K,osp$budget_personnel_details pd
		WHERE 	PP.PROPOSAL_NUMBER = as_proposal_number
		AND 		PP.PERSON_ID = K.PERSON_ID
		AND		PP.PROPOSAL_NUMBER = K.PROPOSAL_NUMBER
      and      pd.proposal_number = as_proposal_number
      and      pd.person_id = pp.person_id


    	UNION

--sr people in budget who are in person table but not in proposal
		select person_id, 999 as sort_ID,
		    	last_name,
			 	decode(first_name,null,' ',first_name) FIRST_NAME,
			 	decode(middle_name,null,' ',middle_name) MIDDLE_NAME,
          	primary_title as PROJECT_ROLE,
				'N' AS NON_MIT_PERSON
		from  osp$person
		where person_id in
				(select  PD.person_id
		 		from    	OSP$BUDGET_PERSONNEL_DETAILS PD,
		  					OSP$BUDGET_DETAILS BD,
		  					OSP$BUDGET_CATEGORY_MAPPING MAPPING
		 		where		BD.proposal_number = as_proposal_number
 			 	AND 	   BD.VERSION_NUMBER = ai_version
 		 		AND     BD.BUDGET_PERIOD = ai_period
 	    		AND 	   BD.VERSION_NUMBER = PD.VERSION_NUMBER
		 		AND     BD.proposal_number = PD.proposal_number
		 		AND  	BD.BUDGET_PERIOD  = PD.BUDGET_PERIOD
		 		AND     PD.LINE_ITEM_NUMBER =  BD.LINE_ITEM_NUMBER
		 		AND     BD.BUDGET_CATEGORY_CODE = MAPPING.COEUS_CATEGORY_CODE
		 		AND     MAPPING.MAPPING_NAME = 'S2S'
		 		AND     MAPPING.TARGET_CATEGORY_CODE = '01'
		 		and 		pd.person_id not in (select person_id
								 				from osp$eps_prop_person
								 				where proposal_number = as_proposal_number))


		order by sort_ID;


end;

-------------------------------------------------------------------------------------
-- procedure get_inv_and_key_persons - gets investigators , and key persons from key person tab
-------------------------------------------------------------------------------------

procedure get_inv_and_key_persons ( as_proposal_number in osp$budget.proposal_number%type,
									 cur_type IN OUT result_sets.cur_generic)

is
	begin
open cur_type for
   -- PI
      SELECT 	PP.PERSON_ID , 0 as sort_id,
   				PP.LAST_NAME,
   				DECODE(PP.FIRST_NAME,NULL,' ',PP.FIRST_NAME) FIRST_NAME,
   				DECODE( PP.MIDDLE_NAME,NULL,' ',PP.MIDDLE_NAME) MIDDLE_NAME,
     				'PD/PI' as PROJECT_ROLE,
                	PI.NON_MIT_PERSON_FLAG as NON_MIT_PERSON
		FROM  	OSP$EPS_PROP_PERSON PP, OSP$eps_PROP_INVESTIGATORS PI
		WHERE 	PP.PROPOSAL_NUMBER = as_proposal_number
		AND 		PP.PERSON_ID = PI.PERSON_ID
		AND 		PP.PROPOSAL_NUMBER = PI.PROPOSAL_NUMBER
      AND      PI.PRINCIPAL_INVESTIGATOR_FLAG = 'Y'

		UNION

	--co-investigators
 		SELECT 	PP.PERSON_ID , pp.sort_id ,
   				PP.LAST_NAME,
   				DECODE(PP.FIRST_NAME,NULL,' ',PP.FIRST_NAME) FIRST_NAME,
   				DECODE( PP.MIDDLE_NAME,NULL,' ',PP.MIDDLE_NAME) MIDDLE_NAME,
     				'Co-PD/PI' as PROJECT_ROLE,
                 	PI.NON_MIT_PERSON_FLAG as NON_MIT_PERSON
		FROM  	OSP$EPS_PROP_PERSON PP, OSP$eps_PROP_INVESTIGATORS PI
		WHERE 	PP.PROPOSAL_NUMBER = as_proposal_number
		AND 		PP.PERSON_ID = PI.PERSON_ID
		AND 		PP.PROPOSAL_NUMBER = PI.PROPOSAL_NUMBER
      AND      PI.PRINCIPAL_INVESTIGATOR_FLAG = 'N'

		UNION


--KEY PERSONS
		SELECT 	PP.PERSON_ID, pp.sort_id ,
   				PP.LAST_NAME ,
   				DECODE(PP.FIRST_NAME, NULL,' ',PP.FIRST_NAME) FIRST_NAME,
   				DECODE(PP.MIDDLE_NAME,NULL,' ',PP.MIDDLE_NAME) MIDDLE_NAME,
   				K.PROJECT_ROLE,
                K.NON_MIT_PERSON_FLAG as NON_MIT_PERSON
		FROM  	OSP$EPS_PROP_PERSON PP, OSP$EPS_PROP_KEY_PERSONS K
		WHERE 	PP.PROPOSAL_NUMBER = as_proposal_number
		AND 		PP.PERSON_ID = K.PERSON_ID
		AND		PP.PROPOSAL_NUMBER = K.PROPOSAL_NUMBER


		order by sort_ID;


end;


-------------------------------------------------------------------------------------
-- procedure get_salary_and_months gets salary requested and months of
--          effort for each period type (e.g. summer, calendar)
--  added call to fn_get_number_of_months for case 2265
-------------------------------------------------------------------------------------

procedure get_salary_and_months (as_proposal_number in osp$budget.proposal_number%type,
											ai_version_number in osp$budget.version_number%type,
											ai_budget_period in osp$budget_details.budget_period%type,
											as_person_id in osp$budget_personnel_details.person_id%type,
									      cur_type IN OUT result_sets.cur_generic)

is
begin
open cur_type for
-- for rounding problem
	SELECT  period_type  ,
			  Decode(sum(salary_requested),null,0,sum(salary_requested)) SALARY_REQUESTED,
       --	  round(decode(sum(.01 * percent_effort *  round(months_between(PD.end_date,PD.start_date),2)),null,0,
       --              sum(.01 * percent_effort *  round(months_between(PD.end_date,PD.start_date),2))), 2)  MONTHS,
       --  sum(FN_GET_NUMBER_OF_MONTHS(pd.start_date,pd.end_date)) as MONTHS,
        --Case #2737 Added round on the line below
           round(sum(.01 * PD.percent_effort * FN_GET_NUMBER_OF_MONTHS(pd.start_date,pd.end_date)), 2) as MONTHS,
			  MAX(P.CALCULATION_BASE) BASE_SALARY,
			  Decode(sum(pd.cost_sharing_amount),null,0,sum(pd.cost_sharing_amount)) COST_SHARING_AMOUNT
	   FROM   	OSP$BUDGET_PERSONNEL_DETAILS PD, OSP$BUDGET B, OSP$BUDGET_PERSONS P
  		WHERE  	PD.proposal_number = as_proposal_number
      AND      PD.PROPOSAL_NUMBER = B.PROPOSAL_NUMBER
  		AND      B.VERSION_NUMBER = ai_version_number
  		and   	PD.VERSION_NUMBER = B.VERSION_NUMBER
  		and   	PD.budget_period = ai_budget_period
  		and   	PD.person_id =  as_person_id
		AND     PD.PROPOSAL_NUMBER = P.PROPOSAL_NUMBER
		AND     PD.VERSION_NUMBER = P.VERSION_NUMBER
		AND     PD.PERSON_ID = P.PERSON_ID
  		group by period_type;

end;



--------------------------------------------------------------
-- procedure get_fringe
--        gets fringe for a person in a budget period
--------------------------------------------------------------
procedure get_fringe (as_proposal_number in osp$budget.proposal_number%type,
							ai_version_number IN OSP$BUDGET.VERSION_NUMBER%TYPE,
							ai_budget_period in osp$budget_details.budget_period%type,
							as_person_id in osp$budget_personnel_details.person_id%type,
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
   and    	osp$budget_personnel_cal_amts.budget_period  = ai_budget_period
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
   and   osp$budget_personnel_details.budget_period = ai_budget_period
   and   osp$budget_personnel_details.person_id = as_person_id;


end;

-----------------------------------------------------------
-- procedure get_tot_person_funds
--         gets total funds (and fringes) for all line items that are type Personnel
-----------------------------------------------------------
procedure get_tot_person_funds (as_proposal_number OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
											ai_version_number osp$budget.version_number%type,
											cur_type IN OUT result_sets.cur_generic) is

	li_sum 			number;
   li_fringe 		number;
   li_MrLA   		number;
   li_MrLAFringe 	number;
	li_tot_funds 	number;

	li_sum_costSharing 			number;
   li_fringe_costSharing 		number;
   li_MrLA_costSharing   		number;
   li_MrLAFringe_costSharing 	number;
	li_tot_non_funds 	number;
	begin
		li_sum := 0;
		li_sum_costSharing := 0;

      --sum of costs (not fringe) for personnel

		select 	decode(sum(bd.line_item_cost),null,0,sum(bd.line_item_cost)),
				   decode(sum(bd.cost_sharing_amount),null,0,sum(bd.cost_sharing_amount))
		into     li_sum, li_sum_costSharing
		from 		osp$budget_details bd, osp$budget_category bc
		where 	proposal_number = as_proposal_number
	   and 		version_number = ai_version_number
		and 		bd.budget_category_code = bc.budget_category_code
		and 		category_type = 'P';


      --sum of fringes for personnel
      select decode(sum(c.calculated_cost),null,0,sum(c.calculated_cost)),
				 decode(sum(c.calculated_cost_sharing),null,0,sum(c.calculated_cost_sharing))
		into li_fringe, li_fringe_costSharing
		from osp$budget_details_cal_amts c,
			  osp$budget_details bd,
			  osp$budget_category bc
		where c.proposal_number = as_proposal_number
		and  c.version_number = ai_version_number
		and c.proposal_number = bd.proposal_number
		and c.version_number =  bd.version_number
		and c.budget_period = bd.budget_period
		and c.line_item_number = bd.line_item_number
		and bd.budget_category_code = bc.budget_category_code
		and   		( (c.rate_class_code = 5  and  c.rate_type_code <> 3)
                      or
        			     (c.rate_class_code = 8  and  c.rate_type_code <> 2) )
		and bc.category_type = 'P';

		 select  decode(sum(a.calculated_cost),null,0,sum(a.calculated_cost)),
					decode(sum(a.calculated_cost_sharing),null,0,sum(a.calculated_cost_sharing))
		 into li_MrLA,li_MrLA_costSharing
		 from   		osp$budget_details_cal_amts a, osp$rate_class r
		 where  		a.proposal_number = AS_PROPOSAL_NUMBER
		 and      a.version_number = AI_VERSION_NUMBER
		 and   		a.rate_class_code = r.rate_class_code
		 and 		r.rate_class_type in ('Y');


		select  	decode(sum(a.calculated_cost),null,0,sum(a.calculated_cost)),
					decode(sum(a.calculated_cost_sharing),null,0,sum(a.calculated_cost_sharing))
		into li_MrLAFringe,li_MrLAFringe_costSharing
		 from   		osp$budget_details_cal_amts a, osp$rate_class r
		 where  		a.proposal_number = as_proposal_number
		 and      a.version_number = ai_version_number
		 and   		a.rate_class_code = r.rate_class_code
		 and   		(((r.rate_class_type = 'E') and (a.rate_type_code = 3)) or ((r.rate_class_type = 'V') and (a.rate_type_code = 2)));


		li_tot_funds :=  li_sum + li_fringe + li_MrLA + li_MrLAFringe;
		li_tot_non_funds := li_sum_costSharing + li_fringe_costSharing + li_MrLA_costSharing + li_MrLAFringe_costSharing;

   open cur_type for

	SELECT li_tot_funds as TOTAL_FUNDS,
			 li_tot_non_funds as TOTAL_NON_FUNDS
	FROM DUAL;

   end;





procedure getCFDA (as_proposal_number in osp$eps_proposal.proposal_number%type,
						 cur_type IN OUT result_sets.cur_generic)
is
	begin
	open cur_type for
		SELECT cfda_number as CFDA,
             substr(program_announcement_title,1,119) as ACTIVITY_TITLE   --case 2283
		--		 program_announcement_title as ACTIVITY_TITLE  --case 2283
		FROM   osp$eps_proposal
		WHERE  proposal_number = as_proposal_number;


end;


procedure getCategoryType (as_category_type in osp$budget_category_maps.category_type%type,
								  cur_type IN OUT result_sets.cur_generic)
is
	begin
	open cur_type for
			SELECT TARGET_CATEGORY_CODE,
					 DESCRIPTION
			     	 FROM   OSP$BUDGET_CATEGORY_MAPS
				    WHERE	MAPPING_NAME = 'S2S'
					 AND		CATEGORY_TYPE = as_category_type;
end;


procedure getNarrativeType (as_proposal_number in osp$eps_proposal.proposal_number%type,
									 ai_module_number in osp$narrative.module_number%type,
									 cur_type IN OUT result_sets.cur_generic)
is
	begin
	open cur_type for
		SELECT N.narrative_type_code,
				 NT.description,
				 N.module_title
		FROM   OSP$NARRATIVE N,
				 OSP$NARRATIVE_TYPE NT
		WHERE  N.PROPOSAL_NUMBER = as_proposal_number
		AND    N.MODULE_NUMBER = ai_module_number
      AND    N.NARRATIVE_TYPE_CODE = NT.NARRATIVE_TYPE_CODE;

end;




function fn_get_Narrative_Type (as_proposal_number in osp$eps_proposal.proposal_number%type,
									  ai_module_number in osp$narrative.module_number%type)
 			return number is
narrativeType osp$narrative.narrative_type_code%type;
Begin

SELECT narrative_type_code
INTO	 narrativeType
FROM	 OSP$NARRATIVE
WHERE  PROPOSAL_NUMBER = 	as_proposal_number
AND    MODULE_NUMBER =     ai_module_number;

return narrativeType;

end;







------------------------------------------------------------------
-- procedure GetOtherPersonnelMonths
--  added  for case 1924
-- get months for other personnel (excluding key persons and investigators)
--  added call to fn_get_number_of_months for case 2265
------------------------------------------------------------------
procedure GetOtherPersonnelMonths (as_proposal_number OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
            				    ai_version_number osp$budget.version_number%type,
                				 ai_budget_period osp$budget_periods.budget_period%type,
                            as_category_code osp$budget_category_mapping.target_category_code%type,
									 cur_type IN OUT result_sets.cur_generic) is

BEGIN

  	open cur_type for
	SELECT PERIOD_TYPE,
 			-- round(decode(sum(.01 * PERCENT_EFFORT *  round(months_between(PD.END_DATE,PD.START_DATE))),null,0,
         --            sum(.01 * PERCENT_EFFORT *  round(months_between(PD.END_DATE,PD.START_DATE)))), 2)  MONTHS
         --  sum(FN_GET_NUMBER_OF_MONTHS(pd.start_date,pd.end_date)) as MONTHS
		 ----Case #2737 Added round on the line below
           round(sum(.01 * PD.percent_effort * FN_GET_NUMBER_OF_MONTHS(pd.start_date,pd.end_date)), 2) as MONTHS
	FROM  OSP$BUDGET_PERSONNEL_DETAILS PD,
         OSP$BUDGET_DETAILS,
         OSP$BUDGET_CATEGORY_MAPPING
   WHERE OSP$BUDGET_DETAILS.proposal_number = as_proposal_number
  	AND  OSP$BUDGET_DETAILS.proposal_number = pd.proposal_number
  	AND  OSP$BUDGET_DETAILS.VERSION_NUMBER = pd.VERSION_NUMBER
 	AND  OSP$BUDGET_DETAILS.VERSION_NUMBER = ai_version_number
  	AND  OSP$BUDGET_DETAILS.BUDGET_PERIOD  = pd.BUDGET_PERIOD
  	AND  PD.BUDGET_PERIOD = ai_budget_period
  	AND  PD.LINE_ITEM_NUMBER =  OSP$BUDGET_DETAILS.LINE_ITEM_NUMBER
  	AND  OSP$BUDGET_DETAILS.BUDGET_CATEGORY_CODE = OSP$BUDGET_CATEGORY_MAPPING.COEUS_CATEGORY_CODE
  	AND  OSP$BUDGET_CATEGORY_MAPPING.MAPPING_NAME = 'S2S'
   AND  OSP$BUDGET_CATEGORY_MAPPING.TARGET_CATEGORY_CODE = as_category_code
   AND  PD.PERSON_ID  not in (select person_id
									from osp$eps_prop_person
									where proposal_number = as_proposal_number
						       	)
   GROUP BY PERIOD_TYPE;
END;



------------------------------------------------------------------
-- procedure GetOtherPersonnelSal
------------------------------------------------------------------
procedure GetOtherPersonnelSal (as_proposal_number OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
            				    ai_version_number osp$budget.version_number%type,
                				 ai_budget_period osp$budget_periods.budget_period%type,
                            as_category_code osp$budget_category_mapping.target_category_code%type,
									 cur_type IN OUT result_sets.cur_generic) is

	li_salary number;
	li_cost     number;
	li_MrLA		number;

	li_salary_costsharing  number;
	li_cost_sharing     number;
	li_MrLA_costsharing		number;


	BEGIN

	--get sum of salary of other personnel, but exclude the key persons and investigators
	li_cost := 0;
	li_salary := 0;

	li_salary_costsharing := 0 ;
	li_cost_sharing := 0;

  		select   DECODE (SUM(salary_requested), NULL ,0, SUM(salary_requested)),
			 DECODE (SUM(OSP$BUDGET_PERSONNEL_DETAILS.cost_sharing_amount), NULL ,0, SUM(OSP$BUDGET_PERSONNEL_DETAILS.COST_SHARING_AMOUNT))
  		INTO     li_salary,li_salary_costsharing
  		from     OSP$BUDGET_PERSONNEL_DETAILS,OSP$BUDGET_DETAILS,OSP$BUDGET_CATEGORY_MAPPING
  		where   OSP$BUDGET_DETAILS.proposal_number = AS_PROPOSAL_NUMBER
  		AND  OSP$BUDGET_DETAILS.proposal_number = OSP$BUDGET_PERSONNEL_DETAILS.proposal_number
  		AND  OSP$BUDGET_DETAILS.VERSION_NUMBER = OSP$BUDGET_PERSONNEL_DETAILS.VERSION_NUMBER
 		 AND  OSP$BUDGET_DETAILS.VERSION_NUMBER = AI_VERSION_NUMBER
  		AND  OSP$BUDGET_DETAILS.BUDGET_PERIOD  = OSP$BUDGET_PERSONNEL_DETAILS.BUDGET_PERIOD
  		AND  OSP$BUDGET_PERSONNEL_DETAILS.BUDGET_PERIOD = AI_BUDGET_PERIOD
  		AND  OSP$BUDGET_PERSONNEL_DETAILS.LINE_ITEM_NUMBER =  OSP$BUDGET_DETAILS.LINE_ITEM_NUMBER
  		AND OSP$BUDGET_DETAILS.BUDGET_CATEGORY_CODE = OSP$BUDGET_CATEGORY_MAPPING.COEUS_CATEGORY_CODE
  		AND OSP$BUDGET_CATEGORY_MAPPING.MAPPING_NAME = 'S2S'
      AND OSP$BUDGET_CATEGORY_MAPPING.TARGET_CATEGORY_CODE = AS_CATEGORY_CODE
      AND OSP$BUDGET_PERSONNEL_DETAILS.person_id  not in (select person_id
																			from osp$eps_prop_person
																			where proposal_number = as_proposal_number
																       	);

-- get costs for this budget category that do not have persons attached to the cost element

 	select  DECODE (sum(OSP$BUDGET_DETAILS.line_item_cost), NULL, 0,
                  SUM(OSP$BUDGET_DETAILS.line_item_cost) ),
			  DECODE (sum(OSP$BUDGET_DETAILS.cost_sharing_amount), NULL, 0,
                  SUM(OSP$BUDGET_DETAILS.cost_sharing_amount) )
   into  li_cost,li_cost_sharing
 	from OSP$BUDGET_DETAILS, OSP$BUDGET_CATEGORY_MAPPING
 	where OSP$BUDGET_DETAILS.proposal_number = AS_PROPOSAL_NUMBER
 	and OSP$BUDGET_DETAILS.VERSION_NUMBER = AI_VERSION_NUMBER
 	and   osp$budget_details.budget_period = AI_BUDGET_PERIOD
 	and   OSP$BUDGET_DETAILS.BUDGET_CATEGORY_CODE = OSP$BUDGET_CATEGORY_MAPPING.COEUS_CATEGORY_CODE
 	AND     OSP$BUDGET_CATEGORY_MAPPING.MAPPING_NAME = 'S2S'
 	AND     OSP$BUDGET_CATEGORY_MAPPING.TARGET_CATEGORY_CODE = AS_CATEGORY_CODE
 	AND   OSP$BUDGET_DETAILS.line_item_number not in
  		(select line_item_number
       from  osp$budget_personnel_details
       where proposal_number = AS_PROPOSAL_NUMBER
       and   version_number  = AI_VERSION_NUMBER
       and   budget_period  = AI_BUDGET_PERIOD );

    if upper(as_category_code) = 'LASALARIES' then

		 select  	sum(a.calculated_cost), sum(a.calculated_cost_sharing)
		 into li_MrLA,li_MrLA_costsharing
		 from   		osp$budget_details_cal_amts a, osp$rate_class r
		 where  		a.proposal_number = AS_PROPOSAL_NUMBER
		   and      a.version_number = AI_VERSION_NUMBER
		   and 		a.budget_period = AI_BUDGET_PERIOD
		 and   		a.rate_class_code = r.rate_class_code
		 and 		r.rate_class_type in ('Y');
     else
	 	 li_MrLA := 0;
		 li_MrLA_costsharing := 0 ;
	 end if;

	li_salary := li_salary + li_cost + li_MrLa;
	li_salary_costsharing := li_salary_costsharing + li_cost_sharing + li_MrLA_costsharing;

	open cur_type for
	SELECT li_salary as SALARY,
			 li_salary_costsharing as SALARY_COST_SHARING
	FROM DUAL;

end;




--------------------------------------------------
-- procedure Get_other_Pers_Fringe
-- changed from a function (fnGetOtherPersonnelFringe) for rounding issues
-- added li_fringe2 to get fringe for line items that are do not have person details
--------------------------------------------
procedure Get_other_Pers_Fringe (as_proposal_number IN OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
            				    ai_version_number IN osp$budget.version_number%type,
                				 ai_budget_period IN osp$budget_periods.budget_period%type,
                            as_category_code IN osp$budget_category_mapping.target_category_code%type,
									 cur_type IN OUT result_sets.cur_generic) is


	li_fringe number;
   li_fringe2 number;
	li_MrLAFringe number;
   li_totFringe number;

	li_fringe_costSharing number;
   li_fringe2_costSharing number;
	li_MrLAFringe_costSharing number;
   li_totFringe_costSharing number;
begin
	li_fringe := 0;
	li_fringe_costSharing := 0;
	li_fringe2_costSharing := 0;

   select decode(sum(pa.calculated_cost), null, 0, sum(pa.calculated_cost)),
			 decode(sum(pa.calculated_cost_sharing), null, 0, sum(pa.calculated_cost_sharing))
   into     li_fringe,li_fringe_costSharing
   from    	OSP$BUDGET_DETAILS bd,
       		OSP$BUDGET_CATEGORY  bc,
       		osp$budget_Category_mapping m,
            OSP$BUDGET_PERSONNEL_DETAILS P,
            OSP$BUDGET_PERSONNEL_CAL_AMTS PA
   where  	bd.proposal_number = as_proposal_number
   and  		bd.VERSION_NUMBER = ai_version_number
  	and    	bd.budget_period  = ai_budget_period
   and    	bd.BUDGET_CATEGORY_CODE = bc.budget_CATEGORY_CODE
   and    	m.mapping_name = 'S2S'
  	and    	m.coeus_category_code = bc.budget_category_code
   and  	   m.target_category_code = as_category_code
   AND      P.proposal_number = bd.proposal_number
   and   	P.version_number = bd.version_number
   AND      P.budget_period = bd.budget_period
   and      p.line_item_number = bd.line_item_number
   and      pa.proposal_number = p.proposal_number
   and      pa.version_number = p.version_number
	and      pa.budget_period = p.budget_period
	and      pa.line_item_number = p.line_item_number
	and      pa.person_number = p.person_number
   and      p.person_id not in (select distinct person_id
											from osp$eps_prop_person
											where proposal_number = as_proposal_number)
 	and   		( (pa.rate_class_code = 5  and  pa.rate_type_code <> 3)
                      or
        			     (pa.rate_class_code = 8  and  pa.rate_type_code <> 2) );


   SELECT  decode(sum(ca.calculated_cost),null,0,sum(ca.calculated_cost)),
			  decode(sum(ca.calculated_cost_sharing),null,0,sum(ca.calculated_cost_sharing))
	INTO    li_fringe2,li_fringe2_costSharing
	FROM 	  osp$budget_details_cal_amts ca,
           OSP$BUDGET_CATEGORY_MAPPING m,
           OSP$BUDGET_DETAILS bd
	WHERE   ca.proposal_number = as_proposal_number
	AND 	  ca.VERSION_NUMBER = ai_version_number
	AND  	  ca.BUDGET_PERIOD = ai_budget_period
   AND     bd.proposal_number= ca.proposal_number
   AND     bd.VERSION_NUMBER = ca.VERSION_NUMBER
   AND     bd.BUDGET_PERIOD = ca.BUDGET_PERIOD
   AND     bd.line_item_number = ca.line_item_number
	AND 	  bd.BUDGET_CATEGORY_CODE = m.COEUS_CATEGORY_CODE
  	AND 	  m.MAPPING_NAME = 'S2S'
 	AND 	  m.TARGET_CATEGORY_CODE = as_category_code
   AND     bd.line_item_number not in (select line_item_number
                                       from   osp$budget_personnel_details
                                       where proposal_number = as_proposal_number
                                       and VERSION_NUMBER = ai_version_number
                                       and BUDGET_PERIOD = ai_budget_period)
   AND      ( (ca.rate_class_code = 5  and  ca.rate_type_code <> 3)
                      or
              (ca.rate_class_code = 8  and  ca.rate_type_code <> 2) );



	if upper(as_category_code) = 'LASALARIES' then
		select  	sum(a.calculated_cost),sum(a.calculated_cost_sharing)
		into li_MrLAFringe, li_MrLAFringe_costSharing
		 from   		osp$budget_details_cal_amts a, osp$rate_class r
		 where  		a.proposal_number = as_proposal_number
		   and      a.version_number = ai_version_number
		   and 		a.budget_period = ai_budget_period
		 and   		a.rate_class_code = r.rate_class_code
		  and   		(((r.rate_class_type = 'E') and (a.rate_type_code = 3)) or ((r.rate_class_type = 'V') and (a.rate_type_code = 2)));
	else
		li_MrLAFringe := 0;
		li_MrLAFringe_costSharing := 0;
	end if;


   li_totFringe := li_fringe + li_fringe2 + li_MrLAFringe;
	li_totFringe_costSharing := li_fringe_costSharing + li_fringe2_costSharing + li_MrLAFringe_costSharing;


	open cur_type for

     SELECT 	li_totFringe as OTHER_FRINGE,
					li_totFringe_costSharing as OTHER_FRINGE_COSTSHARING
     FROM      DUAL;

	end;


------------------------
--function fnGetCountOtherPersonnel
--------------------------

function fnGetCountOtherPersonnel (AS_PROPOSAL_NUMBER OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
                AI_VERSION_NUMBER osp$budget.version_number%type,
                AI_BUDGET_PERIOD osp$budget_periods.budget_period%type,
                                                AS_CATEGORY_CODE osp$budget_category_mapping.target_category_code%type
                                               )
 return  NUMBER is
li_count  number;
li_count2 number;

Begin

li_count := 0;


--    select  count(distinct person_id)
       select count ( distinct  person_id || job_code)
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
  		AND OSP$BUDGET_CATEGORY_MAPPING.MAPPING_NAME = 'S2S'
      AND OSP$BUDGET_CATEGORY_MAPPING.TARGET_CATEGORY_CODE = AS_CATEGORY_CODE
      AND OSP$BUDGET_PERSONNEL_DETAILS.person_id  not in (select person_id
																			from osp$eps_prop_person
																			where proposal_number = as_proposal_number
																       	);


-- handle case where there are no person details for line item

	SELECT  decode(sum(quantity),null,0,sum(quantity))
	INTO   li_count2
	FROM 	osp$budget_details,OSP$BUDGET_CATEGORY_MAPPING
	WHERE OSP$BUDGET_DETAILS.proposal_number = AS_PROPOSAL_NUMBER
	AND 	OSP$BUDGET_DETAILS.VERSION_NUMBER = AI_VERSION_NUMBER
	AND  	OSP$BUDGET_DETAILS.BUDGET_PERIOD = AI_BUDGET_PERIOD
	AND 	OSP$BUDGET_DETAILS.BUDGET_CATEGORY_CODE = OSP$BUDGET_CATEGORY_MAPPING.COEUS_CATEGORY_CODE
  	AND 	OSP$BUDGET_CATEGORY_MAPPING.MAPPING_NAME = 'S2S'
 	AND 	OSP$BUDGET_CATEGORY_MAPPING.TARGET_CATEGORY_CODE = AS_CATEGORY_CODE
   and   OSP$BUDGET_DETAILS.line_item_number not in (select line_item_number
                                                     from   osp$budget_personnel_details
                                                     where proposal_number = AS_PROPOSAL_NUMBER
                                                     and VERSION_NUMBER = AI_VERSION_NUMBER
                                                     and BUDGET_PERIOD = AI_BUDGET_PERIOD);




   li_count := li_count + li_count2;

   return li_count;
END;


--------------------------
--function fn_get_project_income
---------------------------
function fn_get_project_income (as_proposal_number in osp$eps_proposal.proposal_number%type)
return number

is

li_amount	number;
li_version  number;

begin

li_version := fn_get_version(as_proposal_number);

	select	sum(amount)
	into		li_amount
	from		osp$budget_project_income
	where		proposal_number = as_proposal_number
	and		version_number = li_version;

return li_amount;

EXCEPTION
	When NO_DATA_FOUND then
		return 0;

end;

--------------------------------
-- fn_get_version
--------------------------------

function fn_get_version (as_proposal_number in osp$budget.PROPOSAL_NUMBER%type)
			 return number
is

li_version	number;
li_count		number;

begin

select	count(*)
into		li_count
from		osp$budget
where		proposal_number = as_proposal_number;

if li_count = 0 then
	-- no budget
	return 0;
end if;

select	version_number
into		li_Version
from		osp$budget
where		proposal_number = as_proposal_number
and 		final_Version_Flag = 'Y';

return	li_version;

EXCEPTION
	When NO_DATA_FOUND then
		SELECT max(version_number)
		into		li_version
		from		osp$budget
		where		proposal_number = as_proposal_number;
		return li_version;
end;

--------------------
-- fn get division
--
--------------------
function fn_get_division(as_department in OSP$PERSON.HOME_UNIT%TYPE)
		return varchar2
is
   ls_ret  		varchar2(60);
   ls_unitnumber osp$unit.unit_number%type;
	li_rc 		number;
   ls_unit     osp$unit.unit_number%type;

	li_index    number;
	li_count		number;
   ls_parent   osp$unit.unit_number%type;
   TYPE r_units IS RECORD  (unit_number  osp$unit.unit_number%type);
	TYPE t_units_list IS TABLE OF r_units INDEX BY BINARY_INTEGER;
	unit_list      t_units_list;
	is_found		boolean;

begin


	unit_list.delete;
	li_index := 0;

	-- build a table with all the level 4 units

	DECLARE CURSOR c_units IS

		SELECT UNIT_NUMBER
		FROM 	 osp$unit_hierarchy
		WHERE  LEVEL = 4
		start with unit_number = '000001'
		connect by prior unit_number = parent_unit_number;

	BEGIN
			OPEN c_units;
         LOOP
		      FETCH c_units INTO ls_unit;
			   EXIT WHEN c_units%NOTFOUND;

				li_index := li_index + 1;
				unit_list(li_index).unit_number := ls_unit;

			END LOOP;
			CLOSE c_units;

		-- now we have a table of units at level 4 in the heirarchy.
		-- go up hierarchy from DEPARTMENT and see if we get to a level 4
		-- if not , return the DEPARTMENT

			is_found := false;
         ls_unit := as_department;
			li_count := li_index;

			ls_parent := as_department;

       if li_count > 0 then            --IF condition added by Sabari

         while (is_found = false and ls_parent <> '0000001') loop
				li_index := li_count;

				SELECT PARENT_UNIT_NUMBER
				INTO  ls_parent
				FROM  OSP$UNIT_hierarchy
				WHERE UNIT_NUMBER = ls_unit;

					while (li_index > 0  and is_found = false ) loop
						if ls_parent = unit_list(li_index).unit_number then
							is_found := true;
						else
							li_index := li_index - 1 ;
						end if;

						ls_unit := ls_parent;

					end loop;

			end loop;

      end if;

		if is_found = false then
		    ls_unitnumber := as_department;
		else
		    ls_unitnumber := ls_parent;
		end if;

		EXCEPTION
			WHEN NO_DATA_FOUND THEN
				ls_unitnumber := as_department;
	END;

	SELECT UNIT_NAME
	INTO   ls_ret
	FROM	 OSP$UNIT
	WHERE  UNIT_NUMBER = ls_unitnumber;

	return ls_ret;


EXCEPTION
	When NO_DATA_FOUND then
		return 'Unknown';



end;







function fn_get_cog_agency(as_proposal_number osp$eps_proposal.proposal_number%type)
		return varchar2
is

--change for case 2762; max agency field on rr budget schema is 180.
--ls_agency		osp$rolodex.organization%type;
ls_agency      varchar(180);
li_rolodex		osp$rolodex.rolodex_id%type;

begin

select	SUBSTR(r.organization || ', ' || r.FIRST_NAME || ' ' || r.LAST_NAME
         || ' ' || r.PHONE_NUMBER, 1, 180)
into		ls_agency
from		osp$rolodex r, osp$organization o, osp$eps_proposal p
where		p.organization_id = o.organization_id
and		o.cognizant_auditor = r.rolodex_id
and      p.proposal_number = as_proposal_number;

return	ls_agency;

EXCEPTION
	When NO_DATA_FOUND then
		return 'Unknown';


end;




-------------------------------
-- procedure getSF424SubmissionType
-------------------------------

procedure getSF424SubmissionType(as_proposal_number in osp$eps_proposal.proposal_number%type,
									cur_type IN OUT result_sets.cur_generic)
is
li_submissionTypeCode	osp$s2s_submission_type.s2s_submission_type_code%type;
ls_submissionTypeDesc   osp$s2s_submission_type.description%type;
li_activity_type_code	osp$eps_proposal.activity_type_code%type;
li_activity_param       osp$parameter.value%type;
li_sub_code             osp$s2s_opportunity.s2s_submission_type_code%type;
ls_suffix		varchar2(3);
ls_type        varchar2(3);

begin

begin
	select value
	into   li_activity_param
	from   osp$parameter
	where  parameter = 'CONSTRUCTION_ACTIVITY_TYPE_CODE';


EXCEPTION
	When NO_DATA_FOUND then
     -- this should throw a validation error against the schema
     open cur_type for
	    select 'XX' as SUBMISSION_TYPE_CODE
	    from   dual;
     return;
end;


select ACTIVITY_TYPE_CODE
INTO   li_activity_type_code
FROM   OSP$EPS_PROPOSAL
WHERE  PROPOSAL_NUMBER = as_proposal_number;

if li_activity_type_code = li_activity_param then
   ls_suffix := 'C';
else
   ls_suffix := 'N';
end if;

select  s2s_submission_type_code
into    li_sub_code
from    osp$s2s_opportunity
where   proposal_number = as_proposal_number;

if li_sub_code = 1 then
		ls_type := 'P' || ls_suffix;
else
      ls_type  := 'A' || ls_suffix;
end if;

open cur_type for
	select ls_type as SUBMISSION_TYPE_CODE
	from   dual;
end;




-------------------------------
-- procedure getSubmissionType
-------------------------------

procedure getSubmissionType(as_proposal_number in osp$eps_proposal.proposal_number%type,
									cur_type IN OUT result_sets.cur_generic)
is
li_submissionTypeCode	osp$s2s_submission_type.s2s_submission_type_code%type;
ls_submissionTypeDesc   osp$s2s_submission_type.description%type;

begin

open cur_type for
select o.s2s_submission_type_code as SUBMISSION_TYPE_CODE,
		 s.description AS SUBMISSION_TYPE_DESC,
       o.revision_code AS REVISION_CODE,
       o.revision_other_description AS REVISION_DESC
from   osp$s2s_opportunity o,
       osp$s2s_submission_type s
where  o.proposal_number = as_proposal_number
and    s.s2s_submission_type_code = o.s2s_submission_type_code;

end;

-------------------------------
-- procedure getSF424RevisionType
-------------------------------

procedure getSF424RevisionType(as_proposal_number in osp$eps_proposal.proposal_number%type,
									cur_type IN OUT result_sets.cur_generic)
is
li_revisionTypeCode	osp$s2s_submission_type.s2s_submission_type_code%type;
ls_submissionTypeDesc   osp$s2s_submission_type.description%type;

begin

open cur_type for
select revision_code AS REVISION_CODE,
       revision_other_description AS REVISION_DESC
from   osp$s2s_opportunity
where  proposal_number = as_proposal_number;

end;

-------------------------------
-- procedure getApplicationType
-------------------------------

procedure getApplicationType
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic)
is

 begin
	open cur_type for
-- If proposal_type_code is 1 (New) , return 'New'
-- If proposal_type_code is 3 (Continuation) , return 'Continuation'
-- If proposal_type_code is 4 (Supplement) , return 'Revision'
-- If proposal_type_code is 5 (Renewal) , return 'Renewal'
-- If proposal_type_code is 6 (Revision) , return 'Resubmission'
-- If proposal_type_code is 7 (Pre-Proposal) , return 'New'


	select   decode(proposal_type_code, 1,'New',
                                       3,'Continuation',
                                       4,'Revision',
                                       5,'Renewal',
                                       6,'Resubmission',
                                       7,'New','New') as APPLICATIONTYPE
		from		osp$eps_proposal
		where		proposal_number = as_proposal_number;

 end;


-------------------------------
-- procedure getSF424ApplicationType
-- valid values are N (new), C (Continuation), R (revision)
-------------------------------

procedure getSF424ApplicationType
          (as_proposal_number IN osp$eps_proposal.proposal_number%type,
           cur_type IN OUT result_sets.cur_generic)
is

 begin
	open cur_type for
-- If proposal_type_code is 1 (New) , return 'New'
-- If proposal_type_code is 3 (Continuation) , return 'Continuation'
-- If proposal_type_code is 4 (Revision) , return 'Revision'

-- If proposal_type_code is 5 (Renewal) , return 'Continuation'
-- If proposal_type_code is 6 (Resubmision) , return 'Revision'


	select   decode(proposal_type_code, 1,'N',
                                       3,'C',
                                       4,'R',
                                       5,'C',
                                       6,'R') as APPLICATIONTYPE
		from		osp$eps_proposal
		where		proposal_number = as_proposal_number;

 end;


procedure get_eo_state_review(as_proposal_number osp$eps_proposal.proposal_number%type,
				cur_type IN OUT result_sets.cur_generic)
is

ls_status		osp$ynq.status%type;
ls_answer		osp$eps_prop_ynq.answer%type;

begin

select		ynq.status
into		ls_status
from		osp$eps_prop_ynq prop_ynq, osp$ynq ynq
where		prop_ynq.proposal_number = as_proposal_number
and		prop_ynq.question_id = 'EO'
and 		prop_ynq.question_id = ynq.question_id;

-- If question status is inactive, return 'Not Reviewed'
if ls_status = 'I'
	then	open cur_type for
	select 'Not Reviewed' as ANSWER,
	null as REVIEW_DATE
	from dual;

else
	open cur_type for
	select		answer,
			    review_date
	from		osp$eps_prop_ynq
	where		proposal_number = as_proposal_number
	and		question_id = 'EO';


end if;


EXCEPTION
	When NO_DATA_FOUND then
		open cur_type for
	--	select 'Not Reviewed' as ANSWER,
      select 'X' as ANSWER,
		null as REVIEW_DATE
		from dual;


end;
procedure UPD_PROP_NARR_AUTO_GEN_PDF (
AV_PROPOSAL_NUMBER IN OSP$NARRATIVE.PROPOSAL_NUMBER%TYPE,
AV_MODULE_TITLE IN OSP$NARRATIVE.MODULE_TITLE%TYPE,
AV_MODULE_STATUS_CODE IN OSP$NARRATIVE.MODULE_STATUS_CODE%TYPE,
AV_CONTACT_NAME IN OSP$NARRATIVE.CONTACT_NAME%TYPE,
AV_PHONE_NUMBER IN OSP$NARRATIVE.PHONE_NUMBER%TYPE,
AV_EMAIL_ADDRESS IN OSP$NARRATIVE.EMAIL_ADDRESS%TYPE,
AV_COMMENTS IN OSP$NARRATIVE.COMMENTS%TYPE,
AV_NARRATIVE_TYPE_CODE IN OSP$NARRATIVE.NARRATIVE_TYPE_CODE%TYPE,
AV_UPDATE_USER IN OSP$NARRATIVE.UPDATE_USER%TYPE,
AV_UPDATE_TIMESTAMP IN OSP$NARRATIVE.UPDATE_TIMESTAMP%TYPE,
AC_TYPE IN CHAR,
lo_module_number IN OUT OSP$NARRATIVE.MODULE_NUMBER%TYPE,
lo_module_sequence_number IN OUT OSP$NARRATIVE.MODULE_SEQUENCE_NUMBER%TYPE) is

begin
--delete the existing records for each narrative_type_code
--then insert a new module enrty for autogenerated document in osp$narrative table
--return module number and module sequence number
--commit the transaction only after inserting the auto generated document
-- for extra key persons or extra equipments into osp$narrative_pdf
IF AC_TYPE='D' THEN
 BEGIN
	DELETE FROM OSP$NARRATIVE_PDF
   	WHERE  PROPOSAL_NUMBER = AV_PROPOSAL_NUMBER  AND
          MODULE_NUMBER IN (SELECT MODULE_NUMBER FROM OSP$NARRATIVE N
		  				  		  WHERE N.PROPOSAL_NUMBER = AV_PROPOSAL_NUMBER AND
								  N.NARRATIVE_TYPE_CODE = AV_NARRATIVE_TYPE_CODE);
	DELETE FROM OSP$NARRATIVE_USER_RIGHTS
   	WHERE  PROPOSAL_NUMBER = AV_PROPOSAL_NUMBER  AND
          MODULE_NUMBER IN (SELECT MODULE_NUMBER FROM OSP$NARRATIVE N
		  				  		  WHERE N.PROPOSAL_NUMBER = AV_PROPOSAL_NUMBER AND
								  N.NARRATIVE_TYPE_CODE = AV_NARRATIVE_TYPE_CODE);
	DELETE from OSP$NARRATIVE where PROPOSAL_NUMBER = AV_PROPOSAL_NUMBER
	   and NARRATIVE_TYPE_CODE = AV_NARRATIVE_TYPE_CODE;
 END;
ELSIF AC_TYPE='I' THEN
 BEGIN
	select nvl(max(module_number),0)+1,nvl(max(module_sequence_number),0)+1
	   into lo_module_number,lo_module_sequence_number
	   FROM OSP$NARRATIVE
   	   WHERE  PROPOSAL_NUMBER = AV_PROPOSAL_NUMBER;

  INSERT INTO OSP$NARRATIVE
         ( PROPOSAL_NUMBER,
           MODULE_NUMBER,
           MODULE_SEQUENCE_NUMBER,
           MODULE_TITLE,
           MODULE_STATUS_CODE,
           CONTACT_NAME,
           PHONE_NUMBER,
           EMAIL_ADDRESS,
           COMMENTS,
		   NARRATIVE_TYPE_CODE,
           UPDATE_USER,
           UPDATE_TIMESTAMP)
  VALUES ( AV_PROPOSAL_NUMBER,
           lo_module_number,
           lo_module_sequence_number,
           AV_MODULE_TITLE,
           AV_MODULE_STATUS_CODE,
           AV_CONTACT_NAME,
           AV_PHONE_NUMBER,
           AV_EMAIL_ADDRESS,
           AV_COMMENTS,
		   AV_NARRATIVE_TYPE_CODE,
           AV_UPDATE_USER,
           AV_UPDATE_TIMESTAMP)  ;
  DECLARE CURSOR C_PROP_NARR_RIGHTS IS
  		  SELECT USER_ID, DECODE(RR.RIGHT_ID,'MODIFY_NARRATIVE','M',
		   						'VIEW_NARRATIVE','R') ls_access_type
		  FROM OSP$ROLE_RIGHTS RR,
									OSP$EPS_PROP_USER_ROLES P
		   					   where P.PROPOSAL_NUMBER = AV_PROPOSAL_NUMBER
							   		 AND RR.RIGHT_ID IN ('MODIFY_NARRATIVE','VIEW_NARRATIVE')
									 AND P.ROLE_ID = RR.ROLE_ID
						  		order by RR.RIGHT_ID;
   ls_user_id varchar2(8);
   ls_user_count number(1);
   ls_access_type char(1);
  BEGIN
   OPEN C_PROP_NARR_RIGHTS;
   LOOP FETCH C_PROP_NARR_RIGHTS INTO ls_user_id,ls_access_type;
   		EXIT WHEN C_PROP_NARR_RIGHTS%NOTFOUND;

		SELECT nvl(COUNT(USER_ID),0) INTO ls_user_count
		 FROM OSP$NARRATIVE_USER_RIGHTS
		WHERE USER_ID = ls_user_id
			  AND PROPOSAL_NUMBER=AV_PROPOSAL_NUMBER
			  AND MODULE_NUMBER=lo_module_number;

		IF ls_user_count=0 THEN
		   INSERT INTO OSP$NARRATIVE_USER_RIGHTS
           		  ( PROPOSAL_NUMBER,
           		  MODULE_NUMBER,
			  	  ACCESS_TYPE,
			  	  USER_ID,
           		  UPDATE_USER,
           		  UPDATE_TIMESTAMP)
		  VALUES ( AV_PROPOSAL_NUMBER,
			     lo_module_number,
				 ls_access_type,
				 ls_user_id,
           		 AV_UPDATE_USER,
           		 AV_UPDATE_TIMESTAMP);
	   END IF;
	END LOOP;
  END;
 END;
 END IF;

end;


function FN_DELETE_NARR_AUTO_GEN_ATTS (
		  AV_PROPOSAL_NUMBER IN OSP$NARRATIVE.PROPOSAL_NUMBER%TYPE)
		  return number is

begin
	DELETE FROM	OSP$NARRATIVE_PDF
   	WHERE  PROPOSAL_NUMBER = AV_PROPOSAL_NUMBER  AND
          MODULE_NUMBER IN (SELECT MODULE_NUMBER FROM OSP$NARRATIVE N,OSP$NARRATIVE_TYPE NT
		  				  		  WHERE N.PROPOSAL_NUMBER = AV_PROPOSAL_NUMBER AND
								  N.NARRATIVE_TYPE_CODE = NT.NARRATIVE_TYPE_CODE AND
								  NT.SYSTEM_GENERATED = 'Y');

	DELETE FROM OSP$NARRATIVE_USER_RIGHTS
   	WHERE  PROPOSAL_NUMBER = AV_PROPOSAL_NUMBER  AND
          MODULE_NUMBER IN (SELECT MODULE_NUMBER FROM OSP$NARRATIVE N,OSP$NARRATIVE_TYPE NT
		  				  		  WHERE N.PROPOSAL_NUMBER = AV_PROPOSAL_NUMBER AND
								  N.NARRATIVE_TYPE_CODE = NT.NARRATIVE_TYPE_CODE AND
								  NT.SYSTEM_GENERATED = 'Y');
	DELETE FROM OSP$NARRATIVE N
	   	WHERE  N.PROPOSAL_NUMBER = AV_PROPOSAL_NUMBER  AND
								  N.NARRATIVE_TYPE_CODE in (
								  		select NARRATIVE_TYPE_CODE from OSP$NARRATIVE_TYPE
								  		where SYSTEM_GENERATED = 'Y');
	return 1;
 end;
---------------------------------------------------
-- function get_federal_id
-- returns federalIdentifier field
--        '0' if federal id is not required
--        '-1', if fails validation
-- called from java and also from the s2svalidation function in this package
--
--If proposal type is "New" (1)
--  if submission type is 'Application', federal id is not set
--  if submission type is 'Changed/Corrected', federal id is set to GG tracking id
--        of previous submission (based on continued_from column.)
--        Using institute proposal number in the continued_from column,
--        get the development proposal number from osp$proposal_admin_details.
--        Then get the gg_tracking_id from osp$s2s_app_submission for this development proposal.
--        (It is possible that there can be multiple previous submissions, i.e. more than one development proposal in
--        osp$proposal_admin_details for one institute proposal, and we don't know which one to use. So in this case, use the sponsor_proposal_numbr
--        of the current dev proposal.
--
--If proposal type is "ReSubmission" (6) - resubmitting a proposal that has previously been rejected.
--       This used to be called a Revision by NIH.  There is no award.
--   Federal_id is set to the sponsor_proposal_number of the development proposal.  If there is none,
--   it is set to the sponsor proposal number of the previously submitted proposal. (This is obtained
--   by getting sponsor proposal number on the institute proposal number that is in the continued_From column.
--     If no institute proposal found, or if no sponsor_proposal_number on the institute proposal,
--     return an error.
--
--If proposal type is "Renewal" (5) (renewal or expiring award - used to be called Competing continuation)
--or proposal type is "Revision" (4) (revision to existing award)
--or proposal type is "Continuation" (3) - (progress report; not used for NIH gg)
--   federal_id is set to the previously assigned grant number.  This will come from the
--   current_award_number of the development proposal.
--    If no current_award_number found, use the sponsor_proposal_number of the development proposal (change case 2700)
--   If the FEDERAL_ID_COMES_FROM_CURRENT_AWARD parameter is set to 0,
--   always use the sponsor_proposal_number of the development proposal.
---------------------------------------------------

 function get_federal_id(av_proposal_number in osp$eps_proposal.proposal_number%type)
           return varchar2 is

 ls_federal_id					varchar2(100);
 li_submission_type			osp$s2s_opportunity.s2s_submission_type_code%type;
 ls_gg_tracking_id	   	OSP$S2S_APP_SUBMISSION.GG_TRACKING_ID%type;
 ls_dev_proposal    	   	OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%type;
-- ls_sponsor_number      	OSP$EPS_PROPOSAL.SPONSOR_PROPOSAL_NUMBER%type;  --VARCHAR2(70)
 li_proposal_type     		OSP$EPS_PROPOSAL.PROPOSAL_TYPE_CODE%type;
 ls_sponsor_award_number	varchar2(70);  --varchar2(70)
 ls_award_source          	OSP$PARAMETER.PARAMETER%type;
 ls_continued_from         OSP$EPS_PROPOSAL.continued_from%TYPE;
 li_count						number;


 Begin

 ls_federal_id := -1;

 -- get submission type
    SELECT S2S_SUBMISSION_TYPE_CODE
    INTO   li_submission_type
    FROM	 OSP$S2S_OPPORTUNITY
    WHERE  PROPOSAL_NUMBER = av_proposal_number;

--  get proposal type
    SELECT proposal_type_code
    INTO  li_proposal_type
    FROM   OSP$EPS_PROPOSAL
    WHERE  PROPOSAL_NUMBER = av_proposal_number;


    IF li_proposal_type = 1 then
       IF li_submission_type = 3 then    --changed/corrected

          	   SELECT  count(*)
			      INTO	  li_count
               FROM    OSP$PROPOSAL_ADMIN_DETAILS
            	WHERE   INST_PROPOSAL_NUMBER = (SELECT CONTINUED_FROM
															  FROM OSP$EPS_PROPOSAL
                  	                          WHERE  PROPOSAL_NUMBER = av_proposal_number);
					if li_count = 1 then
               	BEGIN
            			SELECT  DEV_PROPOSAL_NUMBER
            			INTO    ls_dev_proposal
            			FROM    OSP$PROPOSAL_ADMIN_DETAILS
            			WHERE   INST_PROPOSAL_NUMBER = (SELECT CONTINUED_FROM
																	  FROM OSP$EPS_PROPOSAL
                  			                          WHERE  PROPOSAL_NUMBER = av_proposal_number);

 							SELECT GG_TRACKING_ID
 		  					INTO   ls_gg_tracking_id
 	  						FROM   OSP$S2S_APP_SUBMISSION
 	  						WHERE  PROPOSAL_NUMBER =  ls_dev_proposal
      					AND    SUBMISSION_NUMBER = (SELECT MAX (SUBMISSION_NUMBER)
               		                  		FROM   OSP$S2S_APP_SUBMISSION
                  		               		WHERE  PROPOSAL_NUMBER =  ls_dev_proposal);


                     ls_federal_id := ls_gg_tracking_id;

  							EXCEPTION
 								When OTHERS then
 		  						ls_federal_id := '-1';
      			 	END;

					else
							SELECT decode(SPONSOR_PROPOSAL_NUMBER,null,'-1',SPONSOR_PROPOSAL_NUMBER)
              			INTO   ls_federal_id
           	  			FROM   OSP$EPS_PROPOSAL
				  			WHERE  PROPOSAL_NUMBER = av_proposal_number;

					end if;

      END IF;


    ELSIF li_proposal_type = 6  THEN     --ReSubmission
           dbms_output.put_line('prop type is resubmission');

            SELECT SPONSOR_PROPOSAL_NUMBER
            INTO   ls_federal_id
            FROM   OSP$EPS_PROPOSAL
			   WHERE  PROPOSAL_NUMBER = av_proposal_number;

             if (ls_federal_id is null or trim (ls_federal_id) = '') then

               BEGIN
           		-- get the institute proposal in the continued_from column

           		SELECT CONTINUED_FROM
           		INTO   ls_continued_from
			  		FROM   OSP$EPS_PROPOSAL
           		WHERE  PROPOSAL_NUMBER = av_proposal_number;

           	-- get the sponsor_proposal_number on this institute proposal
           		SELECT P.SPONSOR_PROPOSAL_NUMBER
           		INTO   ls_federal_id
			 	 	FROM   OSP$PROPOSAL P
			  		WHERE  P.PROPOSAL_NUMBER = ls_continued_from
           		AND    P.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER)
														FROM   OSP$PROPOSAL
														WHERE  PROPOSAL_NUMBER = P.PROPOSAL_NUMBER);



             if (ls_federal_id is null or trim (ls_federal_id) = '') then
				        RETURN '-1';
              end if;

				 EXCEPTION
			 		When NO_DATA_FOUND then
					return '-1';

		  		 END;
           end if;

			-- parse the award number

      	ls_federal_id := fn_format_award_number(ls_federal_id, av_proposal_number);




    ELSE   -- proposal type is "Renewal" or "Revision" or "Continuation"

		  BEGIN
			 SELECT  VALUE
			 INTO    ls_award_source
			 FROM		OSP$PARAMETER
			 WHERE   PARAMETER = 'FEDERAL_ID_COMES_FROM_CURRENT_AWARD';

		  EXCEPTION
			 When NO_DATA_FOUND then
				-- MISSING PARAMENTER. assume default behavior
            ls_award_source := 1;

        END;

--         BEGIN
--         		IF ls_award_source = 1 then
--
--
-- 	      		SELECT	A.SPONSOR_AWARD_NUMBER
--  			  		INTO		ls_sponsor_award_number
--  					FROM		OSP$AWARD A, OSP$EPS_PROPOSAL P
--  					WHERE		P.PROPOSAL_NUMBER = av_proposal_number
--  					AND      P.CURRENT_AWARD_NUMBER = A.MIT_AWARD_NUMBER
--     				AND      A.SEQUENCE_NUMBER = (SELECT MAX(SEQUENCE_NUMBER) FROM OSP$AWARD WHERE
--  															MIT_AWARD_NUMBER = A.MIT_AWARD_NUMBER);
--                if ls_sponsor_award_number  is null then
-- 				        RETURN '-1';
--                end if;
--
--
--         		ELSE
-- 					SELECT SPONSOR_PROPOSAL_NUMBER
-- 		--			INTO   ls_sponsor_number
--                INTO   ls_sponsor_award_number
--            	 	FROM   OSP$EPS_PROPOSAL
-- 					WHERE  PROPOSAL_NUMBER = av_proposal_number;
--
--
--  			--	  if ls_sponsor_number  is null then
--               if ls_sponsor_award_number  is null then
-- 				     RETURN '-1';
--               end if;
--             END IF;
--         EXCEPTION
-- 				When NO_DATA_FOUND then
--               return '-1';
--
--         END;

		-- parse the award number
      ls_federal_id := fn_format_award_number(ls_sponsor_award_number, av_proposal_number);

    END IF;


    RETURN ls_federal_id;
  END;




 ----------------------------------------------------------------
 -- function fn_format_award_number
 --       called from within this package to parse the nih award number
 --  sponsor award number format is like this : 5-P01-ES05622-09
 --   remove the dashes
 ----------------------------------------------------------------
 function fn_format_award_number (as_award_number in varchar2,
                                  as_proposal_number in OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE)
 return varchar2 is

 ls_award      varchar2(200);
 ls_is_nih     varchar2(10);

 BEGIN
      ls_is_nih := fn_is_nih(as_proposal_number);

      if (ls_is_nih <> '1') then
			-- not nih
          RETURN as_award_number;


      else

 			SELECT replace(as_award_number,'-')
			INTO   ls_award
			FROM   dual;



			SELECT replace(ls_award,' ')
			INTO   ls_award
			FROM   dual;
                         --case#2628
			return ltrim(ls_Award);

     end if;


 end;

 ------------------------------------------------------------------------------------------
 -- FUNCTION fn_is_nih
 --   internal function ; '1' if NIH ; error code if missing nih SPONSOR group
 --------------------------------------------------------------------------------------------

 function fn_is_nih (as_proposal_number OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE)
   return varchar2
   is

  li_count     number;
  li_nih       number;

  BEGIN

      select  count(*)
      into    li_count
      FROM 	  OSP$SPONSOR_HIERARCHY
		WHERE 	UPPER(HIERARCHY_NAME) ='SPONSOR GROUPS';

      if li_count = 0 then
			RETURN '10004';
      end if;

	   li_nih :=	fn_in_sponsor_group(as_proposal_number,'NIH');

      return li_nih;


  END;

----------------------------------------------------------
-- FUNCTION fn_get_pre_dir_costsharing
----------------------------------------------------------
 function fn_get_pre_dir_costsharing(as_proposal_number OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
 				ai_version_number osp$budget.version_number%type,
 				ai_budget_period osp$budget_periods.budget_period%type )
   return  number
   is

   li_sumLine 	number := 0;
   li_sumNoOH 	number := 0;
   li_amount	number := 0;
   begin

   	select 	decode(sum(cost_sharing_amount),null,0,sum(cost_sharing_amount))
 	into     li_sumLine
 	from 	osp$budget_details
 	where 	proposal_number = as_proposal_number
 	and 	version_number = ai_version_number
 	and     budget_period = ai_budget_period;

 	select decode(sum(c.calculated_cost_sharing),null,0,sum(c.calculated_cost_sharing))
 	into li_sumNoOH
 	from osp$budget_details_cal_amts c,
 	     osp$rate_class rc
 	where c.proposal_number = as_proposal_number
 	and  c.version_number = ai_version_number
 	and  c.budget_period = ai_budget_period
 	and  c.rate_class_code = rc.rate_class_code
 	and  rc.Rate_class_type <> 'O';

 	li_amount :=   li_sumLine +  li_sumNoOH;

 	return li_amount;

   end;

   function fn_get_pre_indir_costsharing(as_proposal_number OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
   				ai_version_number osp$budget.version_number%type,
   				ai_budget_period osp$budget_periods.budget_period%type )
     return  number
     is

     li_amount	number := 0;
     begin

     	select decode(sum(c.calculated_cost_sharing),null,0,sum(c.calculated_cost_sharing))
 	into li_amount
 	from osp$budget_details_cal_amts c,
 	     osp$rate_class rc
 	where c.proposal_number = as_proposal_number
 	and  c.version_number = ai_version_number
 	and  c.budget_period = ai_budget_period
 	and  c.rate_class_code = rc.rate_class_code
 	and  rc.Rate_class_type = 'O';

   	return li_amount;

  end;

 -----------------------------------------
-- following procedures added for modular budget amount fix . 9/25/06
-----------------------------------------

procedure getModBudIDC(as_proposal_number OSP$EPS_PROPOSAL.proposal_number%type,
                        ai_version_number OSP$BUDGET.version_number%type,
                          cur_type IN OUT result_sets.cur_generic)
is

begin
open cur_type for

  select decode(sum(funds_requested),null,0,sum(funds_requested)) IDC
  from  osp$budget_modular_idc
  where proposal_number = as_proposal_number
  and   version_number = ai_version_number;

end;


procedure getModBudTot (as_proposal_number OSP$EPS_PROPOSAL.proposal_number%type,
                        ai_version_number OSP$BUDGET.version_number%type,
                          cur_type IN OUT result_sets.cur_generic) is
li_total  number;
li_idc    number;
li_tot_direct number;

begin

  select decode(sum(total_direct_cost),null,0,sum(total_direct_cost))
  into  li_tot_direct
  from  osp$budget_modular
  where proposal_number = as_proposal_number
  and   version_number = ai_version_number;

  select decode(sum(funds_requested),null,0,sum(funds_requested))
  into  li_idc
  from  osp$budget_modular_idc
  where proposal_number = as_proposal_number
  and   version_number = ai_version_number;

  li_total := li_tot_direct + li_idc;

open cur_type for
  select li_total as TOTAL_COST
  from dual;

end;


procedure getModBudCostShare (as_proposal_number OSP$EPS_PROPOSAL.proposal_number%type,
                        ai_version_number OSP$BUDGET.version_number%type,
                          cur_type IN OUT result_sets.cur_generic) is
li_cost_share number;

begin
li_cost_share := 0;

open cur_type for
  SELECT li_cost_share AS COST_SHARE
  FROM DUAL;

--    SELECT  COST_SHARING_AMOUNT
--    FROM   OSP$BUDGET
-- 	WHERE   PROPOSAL_NUMBER = AS_PROPOSAL_NUMBER
-- 	AND VERSION_NUMBER = AI_VERSION_NUMBER ;

end;

 --------------------------------------------------------------------
 -- validation checks
--------------------------------------------------------------------
function FN_CHECK_ERRORS_FOR_S2S (
		   AV_PROPOSAL_NUMBER IN OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE)
		  return varchar2 is

  li_submission_type	osp$s2s_opportunity.s2s_submission_type_code%type;
  li_proposal_type   OSP$EPS_PROPOSAL.PROPOSAL_TYPE_CODE%TYPE;
  ls_gg_tracking_id	OSP$S2S_APP_SUBMISSION.GG_TRACKING_ID%type;
  ls_continued_from   OSP$EPS_PROPOSAL.CONTINUED_FROM%type;
  ls_is_nih          varchar2(10);
  ls_name				OSP$EPS_PROP_PERSON.ERA_COMMONS_USER_NAME%TYPE;
  li_length          number;
   li_count   number;


  ls_ret  varchar2(100);
  BEGIN

   ----------------------------------------------------------------
   -- submission type checks
   ----------------------------------------------------------------

    SELECT S2S_SUBMISSION_TYPE_CODE
    INTO   li_submission_type
    FROM	 OSP$S2S_OPPORTUNITY
    WHERE  PROPOSAL_NUMBER = av_proposal_number;

    SELECT PROPOSAL_TYPE_CODE
    INTO   li_proposal_type
    FROM   OSP$EPS_PROPOSAL
    WHERE  PROPOSAL_NUMBER = av_proposal_number;



    ---------------------------------------------------------------------------------------------
    -- If submission type is 'Changed/Corrected' and proposal type is 'New',
    -- there must be an institute proposal in the continued_from column that has a gg tracking id.
    ---------------------------------------------------------------------------------------------
    if (li_submission_type = 3 and li_proposal_type = 1) then
 			 ls_ret := get_federal_id (av_proposal_number);
   		 if (ls_ret = '-1') then
			    return '10001';
          end if;
    end if;

     --------------------------------------------------------------------------------------------------------
     --	If proposal type is 'Resubmission', there must be an institute proposal in the continued_from column.
     ---------------------------------------------------------------------------------------------------------
     if (li_proposal_type = 6) then
 			ls_ret := get_federal_id (av_proposal_number);
        if (ls_ret = '-1') then
				return '10002';
        end if;
     end if;

    -------------------------------------------------------------------------------
    --	If proposal type is 'Renewal' or 'Revision' or 'Continuation',
    --   there must be a previous sponsor award number.
    --------------------------------------------------------------------------------
     if li_proposal_type in (3,4,5) then
			ls_ret := get_federal_id (av_proposal_number);
         if (ls_ret = '-1') then
				return '10003';
        end if;
     end if;

   -----------------------------------------------------------------------------
   -- if human subjects is Exempt, there must be an exemption number
   -----------------------------------------------------------------------------

    select count(*)
    into li_count
    from osp$eps_prop_special_review
    where proposal_number = av_proposal_number
    and   approval_type_code = 4
    and   comments is null;

    if li_count > 0 then
      return '10007';

    end if;


   ---------------------------------------------------------------------------------
   --  if sponsor is NIH then the PI must have a credential (nih commons name)
   --                        title must not exceed 81 characters
   ---------------------------------------------------------------------------------
      ls_is_nih := fn_is_nih(av_proposal_number);
		BEGIN
      	IF ls_is_nih = '0' THEN
           -- not NIH
           return '0';
         ELSIF ls_is_nih = '1' THEN
         	--NIH
              BEGIN
                 -- check for era commons name
			SELECT ERA_COMMONS_USER_NAME
            	INTO   ls_name
            	FROM   OSP$EPS_PROP_PERSON P, OSP$EPS_PROP_INVESTIGATORS I
            	WHERE  P.PROPOSAL_NUMBER = av_proposal_number
					AND    P.PROPOSAL_NUMBER = I.PROPOSAL_NUMBER
            	AND    P.PERSON_ID = I.PERSON_ID
            	AND    I.PRINCIPAL_INVESTIGATOR_FLAG ='Y';

               if ls_name is null then
						return '10005';
               end if;

				  EXCEPTION
					When NO_DATA_FOUND then
        			RETURN '10005';

              END;

			-- check for title length
		  BEGIN

			SELECT decode(length(TITLE),null,0,length(TITLE))
                  INTO   li_length
			FROM   OSP$EPS_PROPOSAL
			WHERE  PROPOSAL_NUMBER = av_proposal_number;

			if li_length > 81 then
				return '10006';
			end if;


		  END;


   	     ELSE  RETURN ls_is_nih;      -- error code

         END if;



       end;
   return '0';
 END;

--------------------------------------------------
-- getContactType
--------------------------------------------------
procedure getContactType (as_proposal_number OSP$EPS_PROPOSAL.proposal_number%type,
 								cur_type IN OUT result_sets.cur_generic) is

contact_type	varchar2(3);

begin

	contact_type := get_parameter_value('PROPOSAL_CONTACT_TYPE');
	if (length(contact_type) < 1) or (contact_type is null) then
		contact_type := 'O';
   end if;


   open cur_type for
	 select CONTACT_TYPE as contact_type
    from   dual;

 end;

-----------------------------------------
-- getContactPerson
-----------------------------------------

procedure getContactPerson (as_proposal_number OSP$EPS_PROPOSAL.proposal_number%type,
                            as_contact_Type  osp$parameter.value%type,
 								    cur_type IN OUT result_sets.cur_generic) is

 dummyNumber number;
 is_number   boolean := true;

BEGIN


-- check if contact type is a number
   is_number := fn_is_numeric(as_contact_type);



 if (is_number = true) then
     dbms_output.put_line('it is   a number');
     begin
			  open cur_type for

					select   p.last_name, p.first_name, p.middle_name, p.email_address,
			   				p.office_phone, p.directory_department, p.office_location,
			   				p.fax_number
   				from 		osp$eps_prop_units pu, osp$unit_administrators u, osp$person p
   				where    u.unit_number = pu.unit_number
   				and		pu.lead_unit_flag = 'Y'
   				and      p.person_id = u.administrator
               and      pu.proposal_number = as_proposal_number
               and      u.unit_administrator_type_code = as_contact_type;
     end;
 else
	begin

  		CASE   as_contact_type
				WHEN 'A' THEN
					-- AO for unit
					open cur_type for
   				select   p.last_name, p.first_name, p.middle_name, p.email_address,
			   				p.office_phone, p.directory_department, p.office_location,
			   				p.fax_number
   				from 		osp$eps_prop_units pu, osp$unit u, osp$person p
   				where    u.unit_number = pu.unit_number
   				and		pu.lead_unit_flag = 'Y'
   				and      p.person_id = u.administrative_officer
   				and		pu.proposal_number = as_proposal_number;

            WHEN  'O'  THEN
					 --OSP admin FOR LEAD UNIT
					open cur_type for
   				select   p.last_name, p.first_name, p.middle_name, p.email_address,
			   				p.office_phone, p.directory_department, p.office_location,
			   				p.fax_number
   				from 		osp$eps_prop_units pu, osp$unit u, osp$person p
   				where    u.unit_number = pu.unit_number
   				and		pu.lead_unit_flag = 'Y'
   				and      p.person_id = u.osp_administrator
   				and		pu.proposal_number = as_proposal_number;


				WHEN  'U'THEN
					-- unit head
					open cur_type for
   				select   p.last_name, p.first_name, p.middle_name, p.email_address,
			   				p.office_phone, p.directory_department, p.office_location,
			   				p.fax_number
   				from 		osp$eps_prop_units pu, osp$unit u, osp$person p
   				where    u.unit_number = pu.unit_number
   				and		pu.lead_unit_flag = 'Y'
   				and      p.person_id = u.unit_head
   				and		pu.proposal_number = as_proposal_number;

				WHEN 'D' THEN
					-- dean
               open cur_type for
					select   p.last_name, p.first_name, p.middle_name, p.email_address,
			   				p.office_phone, p.directory_department, p.office_location,
			   				p.fax_number
   				from 		osp$eps_prop_units pu, osp$unit u, osp$person p
   				where    u.unit_number = pu.unit_number
   				and		pu.lead_unit_flag = 'Y'
   				and      p.person_id = u.dean_vp
   				and		pu.proposal_number = as_proposal_number;

				WHEN 'H' THEN
					--other individual
               open cur_type for

               select   p.last_name, p.first_name, p.middle_name, p.email_address,
			   				p.office_phone, p.directory_department, p.office_location,
			   				p.fax_number
   				from 		osp$eps_prop_units pu, osp$unit u, osp$person p
   				where    u.unit_number = pu.unit_number
   				and		pu.lead_unit_flag = 'Y'
   				and      p.person_id = u.other_individual_to_notify
   				and		pu.proposal_number = as_proposal_number;

				ELSE
               -- default case
               open cur_type for
   				select   p.last_name, p.first_name, p.middle_name, p.email_address,
			   				p.office_phone, p.directory_department, p.office_location,
			   				p.fax_number
   				from 		osp$eps_prop_units pu, osp$unit u, osp$person p
   				where    u.unit_number = pu.unit_number
   				and		pu.lead_unit_flag = 'Y'
   				and      p.person_id = u.osp_administrator
   				and		pu.proposal_number = as_proposal_number;
			 END CASE;
    end;
   end if;


 end;


 ------------------------------------------------------------------------------------------
 -- FUNCTION fn_is_numeric
 --   internal function
 --------------------------------------------------------------------------------------------

 function fn_is_numeric (as_string varchar2)
   return boolean is
      dummy number;
  BEGIN
		dummy := to_number(as_string);
		return (true);
		exception
			when others then
			return (false);
  END;

----------------------------------
-- PROCEDURE getLeadUnit
----------------------------------
procedure getLeadUnit (as_proposal_number OSP$EPS_PROPOSAL.proposal_number%type,
 								cur_type IN OUT result_sets.cur_generic) is

begin

OPEN cur_type for
	SELECT   u.unit_name, u.unit_number
   FROM 		osp$eps_prop_units pu, osp$unit u
   WHERE    u.unit_number = pu.unit_number
   AND		pu.lead_unit_flag = 'Y'
   AND		pu.proposal_number = as_proposal_number;

end;


---------------------------------------------------------
-- function fn_get_base_salary
--  when there are two appointments (same person with two job codes) or person with
--  same job code but different effective dts: using the base salary that has earliest effective dt
---------------------------------------------------------

FUNCTION fn_Get_base_salary (AS_PROPOSAL_NUMBER OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
                             AI_VERSION_NUMBER osp$budget_persons.version_number%TYPE,
                             AS_PERSON_ID osp$budget_persons.person_id%TYPE)
RETURN  NUMBER IS
li_count  NUMBER;
li_count2 NUMBER;
li_effective_date  OSP$BUDGET_PERSONS.effective_date%TYPE;
li_calculation_base  OSP$BUDGET_PERSONS.calculation_base%TYPE;
li_base_salary       OSP$BUDGET_PERSONS.calculation_base%TYPE;
li_appointment_type  OSP$BUDGET_PERSONS.APPOINTMENT_TYPE%TYPE;

FIRST                BOOLEAN;

CURSOR c_budget_persons (prop_number    OSP$EPS_PROPOSAL.PROPOSAL_NUMBER%TYPE,
                         version        osp$budget_persons.version_number%TYPE,
                         personid       osp$budget_persons.person_id%TYPE) IS

SELECT DISTINCT bp.calculation_base,  bp.effective_date, bp.APPOINTMENT_TYPE
				FROM  OSP$BUDGET_PERSONS bp,  OSP$BUDGET_PERSONNEL_DETAILS pd
				WHERE bp.proposal_number = prop_number
				AND   bp.version_number = version
				AND	bp.person_id = personid
            AND   pd.proposal_number=bp.proposal_number
            AND   pd.version_number= bp.version_number
            AND   pd.person_id = bp.person_id
            AND   pd.JOB_CODE = bp.JOB_CODE
				ORDER BY bp.effective_date, bp.APPOINTMENT_TYPE;

BEGIN

   FIRST := TRUE;

	OPEN c_budget_persons (as_proposal_number, ai_version_number, as_person_id);

		LOOP
			FETCH c_budget_persons INTO li_calculation_base, li_effective_date, li_appointment_type;
			EXIT WHEN c_budget_persons%NOTFOUND;

         IF FIRST THEN
				li_base_salary := li_calculation_base;
				FIRST := FALSE;
         END IF;

         IF (trim(li_appointment_type) != 'SUM EMPLOYEE' AND trim(li_appointment_type) != 'TMP EMPLOYEE' ) THEN
             li_base_salary := li_calculation_base;

             EXIT;
         END IF;

      END LOOP;
   CLOSE c_budget_persons;

   RETURN li_base_Salary;


END;

end;

/
