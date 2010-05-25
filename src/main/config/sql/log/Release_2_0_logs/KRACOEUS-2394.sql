--DROP TYPE TABLE_PERS_SAL_SUMMARY;
--DROP TYPE TYPE_PERS_SAL_SUMMARY;

CREATE OR REPLACE TYPE TYPE_PERS_SAL_SUMMARY  AS OBJECT (
	PROPOSAL_NUMBER		VARCHAR2(12), 
	VERSION_NUMBER		NUMBER(3), 
	BUDGET_PERIOD		NUMBER(3), 
	LINE_ITEM_NUMBER	NUMBER(3), 
	START_DATE		DATE, 
	END_DATE		DATE, 
	CATEGORY		VARCHAR2(200),
	PERSON_NAME		VARCHAR2(90),
	PERCENT_EFFORT		NUMBER(5,2), 
	PERCENT_CHARGED		NUMBER(5,2), 
	EB_RATE			VARCHAR2(10), 
	VAC_RATE		VARCHAR2(10), 
	CATEGORY_CODE		NUMBER(3), 
	SALARY			NUMBER(12,2), 
	COST_SHARING_AMOUNT	NUMBER(12,2), 
	FRINGE			NUMBER(12,2),
        FRINGE_COST_SHARING	NUMBER(12,2), 
	PRINCIPAL_INVESTIGATOR  CHAR(1), 
	COST_ELEMENT		VARCHAR2(8), 
	COST_ELEMENT_DESC	VARCHAR2(200)
);

CREATE OR REPLACE TYPE TABLE_PERS_SAL_SUMMARY IS TABLE OF TYPE_PERS_SAL_SUMMARY;

CREATE OR REPLACE VIEW OSP$BUDGET_CONS_SALARY_SUMMARY AS
select
       proposal_number, version_number, budget_period, line_item_number, 
       start_date, end_date, fdesc as category, full_name as person_name,
       percent_effort, percent_charged,
       max(decode(rate_class_type, 'E', applied_rate, 0) ) || '%' eb_rate,
       max(decode(rate_class_type, 'V', applied_rate, 0) ) || '%' vac_rate,
       budget_category_code as category_code, 
       max(salary_requested) as salary,
       max(cost_sharing_amount) as cost_sharing_amount,
       max(calculated_cost) as fringe,
       max(calculated_cost_sharing) as fringe_cost_sharing,
       PRINCIPAL_INVESTIGATOR,
       cost_element,
       gdesc as cost_element_desc
from (
  select
         proposal_number,
         version_number,
         budget_period,
         line_item_number,
         budget_category_code,
         cost_element,
         fdesc,
         gdesc,
         person_number,
         person_id,
         full_name,
         percent_charged,
         percent_effort,
         job_code,
         start_date,
         end_date,
         SALARY_REQUESTED,
         cost_sharing_amount,
         decode(rate_class_code,
                null,
                sRATE_CLASS_CODE,
                rate_class_code) rate_class_code,
         decode(rate_type_code, null, srate_type_code, rate_type_code) rate_type_code,
         decode(rate_class_type, null, srate_class_type, rate_class_type) rate_class_type,
         decode(calculated_cost,
                null,
                scalculated_cost,
                calculated_cost) calculated_cost,
         decode(calculated_cost_sharing,
                null,
                scalculated_cost_sharing,
                calculated_cost_sharing) calculated_cost_sharing,
         decode(applied_rate, null, sAPPLIED_RATE, applied_rate) applied_rate,
         PRINCIPAL_INVESTIGATOR
    from (
         select A1.proposal_number proposal_number,
                 A1.version_number version_number,
                 A1.budget_period budget_period,
                 A1.line_item_number line_item_number,
                 A1.budget_category_code budget_category_code,
                 A1.cost_element cost_element,
                 A1.fdesc fdesc,
                 A1.gdesc gdesc,
                 A1.person_number person_number,
                 A1.person_id person_id,
                 A1.full_name full_name,
                 A1.percent_charged percent_charged,
                 A1.percent_effort percent_effort,
                 A1.job_code job_code,
                 A1.start_date start_date,
                 A1.end_date end_date,
                 A1.SALARY_REQUESTED SALARY_REQUESTED,
                 a1.cost_sharing_amount cost_sharing_amount,
                 A1.rate_class_code rate_class_code,
                 A1.rate_type_code rate_type_code,
                 a1.rate_class_type rate_class_type,
                 a1.calculated_cost calculated_cost,
                 A1.calculated_cost_sharing calculated_cost_sharing,
                 a1.applied_rate applied_rate,
                 a1.PRINCIPAL_INVESTIGATOR PRINCIPAL_INVESTIGATOR,
                 a2.RATE_CLASS_CODE sRATE_CLASS_CODE,
                 a2.RATE_TYPE_CODE sRATE_TYPE_CODE,
                 d.RATE_CLASS_TYPE sRATE_CLASS_TYPE,
                 rb.APPLIED_RATE sAPPLIED_RATE,
                 a2.CALCULATED_COST sCALCULATED_COST,
                 a2.CALCULATED_COST_SHARING sCALCULATED_COST_SHARING
            from OSP$BUDGET_SALARY_SUMMARY   A1,
                 osp$budget_details_cal_amts a2,
                 osp$budget_rate_and_base    rb,
                 osp$rate_class              d,
                 osp$rate_type               e
           where a1.proposal_number = a2.PROPOSAL_NUMBER(+)
             AND A2.PROPOSAL_NUMBER = RB.PROPOSAL_NUMBER
             and a1.version_number = a2.VERSION_NUMBER(+)
             AND A2.VERSION_NUMBER = RB.VERSION_NUMBER
             and a1.budget_period = a2.BUDGET_PERIOD(+)
             AND A2.BUDGET_PERIOD = RB.BUDGET_PERIOD
             and a1.line_item_number = a2.LINE_ITEM_NUMBER(+)
             AND A2.LINE_ITEM_NUMBER = RB.LINE_ITEM_NUMBER
             AND A2.RATE_CLASS_CODE = RB.RATE_CLASS_CODE
             AND A2.RATE_TYPE_CODE = RB.RATE_TYPE_CODE
             AND RB.RATE_CLASS_CODE = D.RATE_CLASS_CODE
             AND D.RATE_CLASS_CODE = E.RATE_CLASS_CODE
             AND E.RATE_CLASS_CODE = RB.RATE_CLASS_CODE
             AND E.RATE_TYPE_CODE = RB.RATE_TYPE_CODE
             AND d.rate_class_type in ('E', 'V')

          MINUS

          select A1.proposal_number proposal_number,
                 A1.version_number version_number,
                 A1.budget_period budget_period,
                 A1.line_item_number line_item_number,
                 A1.budget_category_code budget_category_code,
                 A1.cost_element cost_element,
                 A1.fdesc fdesc,
                 A1.gdesc gdesc,
                 A1.person_number person_number,
                 A1.person_id person_id,
                 A1.full_name full_name,
                 A1.percent_charged percent_charged,
                 A1.percent_effort percent_effort,
                 A1.job_code job_code,
                 A1.start_date start_date,
                 A1.end_date end_date,
                 A1.SALARY_REQUESTED SALARY_REQUESTED,
                 a1.cost_sharing_amount cost_sharing_amount,
                 A1.rate_class_code rate_class_code,
                 A1.rate_type_code rate_type_code,
                 a1.rate_class_type rate_class_type,
                 a1.calculated_cost calculated_cost,
                 A1.calculated_cost_sharing calculated_cost_sharing,
                 a1.applied_rate applied_rate,
                 a1.PRINCIPAL_INVESTIGATOR PRINCIPAL_INVESTIGATOR,
                 a2.RATE_CLASS_CODE sRATE_CLASS_CODE,
                 a2.RATE_TYPE_CODE sRATE_TYPE_CODE,
                 d.RATE_CLASS_TYPE sRATE_CLASS_TYPE,
                 rb.APPLIED_RATE sAPPLIED_RATE,
                 a2.CALCULATED_COST sCALCULATED_COST,
                 a2.CALCULATED_COST_SHARING sCALCULATED_COST_SHARING

            from OSP$BUDGET_SALARY_SUMMARY A1,
                 osp$budget_details_cal_amts a2,
                 osp$budget_rate_and_base rb,
                 osp$rate_class d,
                 osp$rate_type e,
                 (SELECT RATE_CLASS_CODE, RATE_TYPE_CODE
                    FROM osp$VALID_CALC_TYPES
                   WHERE RATE_CLASS_TYPE IN ('E', 'V')) R
           where a1.proposal_number = a2.PROPOSAL_NUMBER(+)
             AND A2.PROPOSAL_NUMBER = RB.PROPOSAL_NUMBER
             and a1.version_number = a2.VERSION_NUMBER(+)
             AND A2.VERSION_NUMBER = RB.VERSION_NUMBER
             and a1.budget_period = a2.BUDGET_PERIOD(+)
             AND A2.BUDGET_PERIOD = RB.BUDGET_PERIOD
             and a1.line_item_number = a2.LINE_ITEM_NUMBER(+)
             AND A2.LINE_ITEM_NUMBER = RB.LINE_ITEM_NUMBER
             AND A2.RATE_CLASS_CODE = RB.RATE_CLASS_CODE
             AND A2.RATE_TYPE_CODE = RB.RATE_TYPE_CODE
             AND RB.RATE_CLASS_CODE = D.RATE_CLASS_CODE
             AND D.RATE_CLASS_CODE = E.RATE_CLASS_CODE
             AND E.RATE_CLASS_CODE = RB.RATE_CLASS_CODE
             AND E.RATE_TYPE_CODE = RB.RATE_TYPE_CODE
             AND A2.rate_class_code = R.rate_class_code
             and A2.rate_type_code = R.rate_type_code
             AND d.rate_class_type in ('E', 'V'))
)

group by
      proposal_number, version_number, budget_period, line_item_number, budget_category_code, cost_element,
      fdesc, gdesc, person_number, person_id, full_name, job_code, principal_investigator, percent_charged, percent_effort,
      start_date, end_date
order by
      proposal_number, version_number, budget_period, line_item_number, person_number;


CREATE OR REPLACE VIEW OSP$V_SALARY_SUMMARY AS 
                       select 
                            a.proposal_number, 
                            a.version_number, 
                            a.budget_period, 
                            a.line_item_number,
                            decode(b.start_date, null, a.START_DATE, b.START_DATE) START_DATE,
                            decode(b.end_date, null, a.END_DATE, b.END_DATE) END_DATE,
                            f.description category, 
                            p.full_name person_name, 
                            b.percent_effort, 
                            b.percent_charged, 
                            null eb_rate, 
                            null vac_rate, 
                            a.BUDGET_CATEGORY_CODE, 
                            decode(b.SALARY_REQUESTED, null, a.LINE_ITEM_cost, b.SALARY_REQUESTED) salary , 
                            b.cost_sharing_amount cost_sharing_amount,
                            null fringe, 
                            null fringe_cost_sharing,
                            DECODE(inv.PRINCIPAL_INVESTIGATOR_FLAG, 'Y', '1', 'N', '2', NULL, '3', '4') AS PRINCIPAL_INVESTIGATOR, 
                            g.COST_ELEMENT, 
                            g.description cost_element_desc
                   
                    from osp$budget_details a,
                         osp$budget_personnel_details b,
                         osp$person p ,
                         osp$budget_category f,
                         osp$cost_element g ,
                         osp$eps_prop_investigators inv

                    where
                         a.proposal_number = b.proposal_number (+) and
                         a.version_number = b.version_number (+) and
                         a.budget_period = b.budget_period (+) and
                         a.line_item_number = b.line_item_number (+) and
                         b.person_id = p.person_id (+) and
                         a.budget_category_code = f.budget_category_code and
                         a.budget_category_code = g.budget_category_code and
                         a.cost_element = g.cost_element and
                         b.proposal_number = inv.proposal_number (+) and
                         b.person_id = inv.person_id (+)
                         and f.CATEGORY_TYPE = 'P' ;


CREATE OR REPLACE PROCEDURE get_salary_summary
   ( as_proposal_number IN osp$budget_per_details_for_edi.PROPOSAL_NUMBER%TYPE,
     ai_version      IN osp$budget_details.version_number%TYPE,
     ai_period        IN osp$budget_per_details_for_edi.budget_period%TYPE,
     cur_summary   IN OUT result_sets.cur_generic) is


li_cost_element varchar2(50);
view_result_count number;
v_tmp_ce_result      TABLE_PERS_SAL_SUMMARY;
v_result       TABLE_PERS_SAL_SUMMARY;


begin

v_tmp_ce_result := TABLE_PERS_SAL_SUMMARY(); 
v_result := TABLE_PERS_SAL_SUMMARY();

DECLARE CURSOR tmp_cur is
    select distinct t.cost_element from budget_details t, budget_category t1
    where
         t.budget_category_code = t1.budget_category_code and
         t.proposal_number = as_proposal_number and
         t.version_number = ai_version          and
         t.budget_period = ai_period            AND
         t1.category_type = 'P'                 ;


BEGIN

    OPEN TMP_CUR;
    LOOP
         FETCH TMP_CUR INTO li_cost_element;
         EXIT WHEN TMP_CUR%NOTFOUND;

         select count(1) into view_result_count
         from Osp$budget_Salary_Summary v1
         where
                v1.proposal_number = as_proposal_number and
                v1.version_number = ai_version          and
                v1.budget_period = ai_period            and
                v1.cost_element = li_cost_element;

               v_tmp_ce_result := TABLE_PERS_SAL_SUMMARY(); 
              
              if view_result_count > 0
              then
                     select TYPE_PERS_SAL_SUMMARY(
                           proposal_number, 
                           version_number, 
                           budget_period, 
                           line_item_number, 
                           start_date, 
                           end_date, 
                           category, 
                           person_name,
                           percent_effort, 
                           percent_charged,
                           eb_rate,
                           vac_rate,
                           category_code, 
                           salary,
                           cost_sharing_amount,
                           fringe,
                           fringe_cost_sharing,
                           PRINCIPAL_INVESTIGATOR,
                           cost_element,
                           cost_element_desc
                    ) 
                    BULK COLLECT INTO v_tmp_ce_result 
                    
                    from OSP$BUDGET_CONS_SALARY_SUMMARY  v 

                    WHERE
                         v.proposal_number = as_proposal_number and
                         v.version_number = ai_version          and
                         v.budget_period = ai_period            and
                         v.cost_element = li_cost_element;

                     dbms_output.put_line('li_cost_element: case 1: ' || li_cost_element);
                     DBMS_OUTPUT.PUT_LINE('Number of rows: case 1: ' || SQL%ROWCOUNT);
               else
                    -- OPEN CUR_SUMMARY FOR
                    select 
                        TYPE_PERS_SAL_SUMMARY(
                            a.proposal_number, 
                            a.version_number, 
                            a.budget_period, 
                            a.line_item_number,
                            A.START_DATE,
                            A.END_DATE,
                            A.category, 
                            A.person_name, 
                            A.percent_effort, 
                            A.percent_charged, 
                            A.eb_rate, 
                            A.vac_rate, 
                            A.BUDGET_CATEGORY_CODE, 
                            A.salary , 
                            A.cost_sharing_amount,
                            A.fringe, 
                            A.fringe_cost_sharing,
                            A.PRINCIPAL_INVESTIGATOR, 
                            A.COST_ELEMENT, 
                            A.cost_element_desc
                    )  
                    BULK COLLECT INTO v_tmp_ce_result  
                    from OSP$V_SALARY_SUMMARY A 
                    where
                         a.proposal_number = as_proposal_number and
                         a.version_number = ai_version          and
                         a.budget_period = ai_period            and
                         a.cost_element = li_cost_element;

                     dbms_output.put_line('li_cost_element: case 2: ' || li_cost_element);
                     DBMS_OUTPUT.PUT_LINE('Number of rows: case 2: ' || SQL%ROWCOUNT);
                  end if;
                 

                   FOR i IN v_tmp_ce_result.FIRST .. v_tmp_ce_result.LAST
                   LOOP
                       v_result.EXTEND;
                       v_result ( v_result.LAST ) := v_tmp_ce_result(i);
                       dbms_output.put_line(li_cost_element || i);
                   END LOOP;  

        ENd loop;
END;

OPEN CUR_SUMMARY FOR

select 
       start_date, 
       end_date, 
       category, 
       person_name,
       percent_effort, 
       percent_charged,
       eb_rate,
       vac_rate,
       salary,
       cost_sharing_amount,
       fringe,
       fringe_cost_sharing,
       PRINCIPAL_INVESTIGATOR,
       cost_element_desc from TABLE ( v_result );


end;
/

CREATE OR REPLACE PROCEDURE get_cum_salary_summary
   ( as_proposal_number IN osp$budget_per_details_for_edi.PROPOSAL_NUMBER%TYPE,
     ai_version      IN osp$budget_details.version_number%TYPE,
     cur_summary   IN OUT result_sets.cur_generic) is


li_cost_element varchar2(50);
view_result_count number;
v_tmp_ce_result      TABLE_PERS_SAL_SUMMARY;
v_result       TABLE_PERS_SAL_SUMMARY;


begin

v_tmp_ce_result := TABLE_PERS_SAL_SUMMARY(); 
v_result := TABLE_PERS_SAL_SUMMARY();

DECLARE CURSOR tmp_cur is
    select distinct t.cost_element from budget_details t, budget_category t1
    where
         t.budget_category_code = t1.budget_category_code and
         t.proposal_number = as_proposal_number and
         t.version_number = ai_version          and
         t1.category_type = 'P'                 ;


BEGIN

    OPEN TMP_CUR;
    LOOP
         FETCH TMP_CUR INTO li_cost_element;
         EXIT WHEN TMP_CUR%NOTFOUND;

         select count(1) into view_result_count
         from Osp$budget_Salary_Summary v1
         where
                v1.proposal_number = as_proposal_number and
                v1.version_number = ai_version          and
                v1.cost_element = li_cost_element;

               v_tmp_ce_result := TABLE_PERS_SAL_SUMMARY(); 
              
              if view_result_count > 0
              then
                     select TYPE_PERS_SAL_SUMMARY(
                           proposal_number, 
                           version_number, 
                           budget_period, 
                           line_item_number, 
                           start_date, 
                           end_date, 
                           category, 
                           person_name,
                           percent_effort, 
                           percent_charged,
                           eb_rate,
                           vac_rate,
                           category_code, 
                           salary,
                           cost_sharing_amount,
                           fringe,
                           fringe_cost_sharing,
                           PRINCIPAL_INVESTIGATOR,
                           cost_element,
                           cost_element_desc
                    ) 
                    BULK COLLECT INTO v_tmp_ce_result 
                    
                    from OSP$BUDGET_CONS_SALARY_SUMMARY  v 

                    WHERE
                         v.proposal_number = as_proposal_number and
                         v.version_number = ai_version          and
                         v.cost_element = li_cost_element;

                     dbms_output.put_line('li_cost_element: case 1: ' || li_cost_element);
                     DBMS_OUTPUT.PUT_LINE('Number of rows: case 1: ' || SQL%ROWCOUNT);
               else
                    select 
                        TYPE_PERS_SAL_SUMMARY(
                            a.proposal_number, 
                            a.version_number, 
                            a.budget_period, 
                            a.line_item_number,
                            A.START_DATE,
                            A.END_DATE,
                            A.category, 
                            A.person_name, 
                            A.percent_effort, 
                            A.percent_charged, 
                            A.eb_rate, 
                            A.vac_rate, 
                            A.BUDGET_CATEGORY_CODE, 
                            A.salary , 
                            A.cost_sharing_amount,
                            A.fringe, 
                            A.fringe_cost_sharing,
                            A.PRINCIPAL_INVESTIGATOR, 
                            A.COST_ELEMENT, 
                            A.cost_element_desc
                    )  
                    BULK COLLECT INTO v_tmp_ce_result  
                    from OSP$V_SALARY_SUMMARY A 
                    where
                         a.proposal_number = as_proposal_number and
                         a.version_number = ai_version          and
                         a.cost_element = li_cost_element;

                     dbms_output.put_line('li_cost_element: case 2: ' || li_cost_element);
                     DBMS_OUTPUT.PUT_LINE('Number of rows: case 2: ' || SQL%ROWCOUNT);
                  end if;
                 

                   FOR i IN v_tmp_ce_result.FIRST .. v_tmp_ce_result.LAST
                   LOOP
                       v_result.EXTEND;
                       v_result ( v_result.LAST ) := v_tmp_ce_result(i);
                       dbms_output.put_line(li_cost_element || i);
                   END LOOP;  

        ENd loop;
END;

OPEN CUR_SUMMARY FOR

select 
       start_date, 
       end_date, 
       category, 
       person_name,
       percent_effort, 
       percent_charged,
       eb_rate,
       vac_rate,
       category_code, 
       salary,
       cost_sharing_amount,
       fringe,
       fringe_cost_sharing,
       PRINCIPAL_INVESTIGATOR,
       cost_element_desc from TABLE ( v_result );


end;
/