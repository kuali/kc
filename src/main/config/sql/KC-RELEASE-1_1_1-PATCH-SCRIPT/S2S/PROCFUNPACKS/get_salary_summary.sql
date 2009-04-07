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
