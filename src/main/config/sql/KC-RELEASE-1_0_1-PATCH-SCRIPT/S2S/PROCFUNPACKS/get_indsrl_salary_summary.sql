create or replace procedure GET_INDSRL_SALARY_SUMMARY(as_proposal_number IN osp$budget_per_details_for_edi.PROPOSAL_NUMBER%TYPE,
                                                      ai_version         IN osp$budget_details.version_number%TYPE,
                                                      ai_period          IN osp$budget_per_details_for_edi.budget_period%TYPE,
                                                      cur_summary        IN OUT result_sets.cur_generic) is

begin
  open cur_summary for

    SELECT START_DATE,
           END_DATE,
           FDESC AS CATEGORY,
           FULL_NAME AS PERSON_NAME,
           PERCENT_EFFORT,
           PERCENT_CHARGED,
           BUDGET_CATEGORY_CODE AS CATEGORY_CODE,
           MAX(SALARY_REQUESTED) + SUM(calculated_cost) AS SALARY,
           PRINCIPAL_INVESTIGATOR,
           GDESC AS COST_ELEMENT_DESC

      FROM (

            select DISTINCT proposal_number,
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
                             decode(rate_class_code,
                                    null,
                                    sRATE_CLASS_CODE,
                                    rate_class_code) rate_class_code,
                             decode(rate_type_code,
                                    null,
                                    srate_type_code,
                                    rate_type_code) rate_type_code,
                             decode(rate_class_type,
                                    null,
                                    srate_class_type,
                                    rate_class_type) rate_class_type,
                             decode(calculated_cost,
                                    null,
                                    scalculated_cost,
                                    calculated_cost) calculated_cost,
                             PRINCIPAL_INVESTIGATOR
              from (

                     select A1.proposal_number        proposal_number,
                             A1.version_number         version_number,
                             A1.budget_period          budget_period,
                             A1.line_item_number       line_item_number,
                             A1.budget_category_code   budget_category_code,
                             A1.cost_element           cost_element,
                             A1.fdesc                  fdesc,
                             A1.gdesc                  gdesc,
                             A1.person_number          person_number,
                             A1.person_id              person_id,
                             A1.full_name              full_name,
                             A1.percent_charged        percent_charged,
                             A1.percent_effort         percent_effort,
                             A1.job_code               job_code,
                             A1.start_date             start_date,
                             A1.end_date               end_date,
                             A1.SALARY_REQUESTED       SALARY_REQUESTED,
                             A1.rate_class_code        rate_class_code,
                             A1.rate_type_code         rate_type_code,
                             a1.rate_class_type        rate_class_type,
                             a1.calculated_cost        calculated_cost,
                             a2.RATE_CLASS_CODE        sRATE_CLASS_CODE,
                             a2.RATE_TYPE_CODE         sRATE_TYPE_CODE,
                             d.RATE_CLASS_TYPE         sRATE_CLASS_TYPE,
                             rb.APPLIED_RATE           sAPPLIED_RATE,
                             a2.CALCULATED_COST        sCALCULATED_COST,
                             a1.PRINCIPAL_INVESTIGATOR PRINCIPAL_INVESTIGATOR

                       from OSP$BUDGET_PER_COST_SUMMARY A1,
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
                        AND a1.proposal_number = as_proposal_number
                        And a1.version_number = ai_version
                        and a1.budget_period = ai_period)

             ORDER by proposal_number,
                       version_number,
                       budget_period,
                       line_item_number,
                       budget_category_code,
                       cost_element,
                       fdesc,
                       gdesc,
                       PRINCIPAL_INVESTIGATOR,
                       PERSON_NUMBER,
                       person_id,
                       full_name,
                       percent_charged,
                       percent_effort,
                       job_code,
                       START_DATE,
                       END_DATE,
                       SALARY_REQUESTED,
                       rate_class_code,
                       rate_type_code,
                       rate_class_type,
                       calculated_cost

            )
     GROUP by budget_category_code,
              cost_element,
              fdesc,
              gdesc,
              PRINCIPAL_INVESTIGATOR,
              PERSON_NUMBER,
              person_id,
              full_name,
              percent_charged,
              percent_effort,
              job_code,
              START_DATE,
              END_DATE

     order by budget_category_code,
              PRINCIPAL_INVESTIGATOR,
              full_name,
              START_DATE;

end;
/