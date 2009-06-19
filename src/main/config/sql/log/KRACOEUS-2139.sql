create or replace view osp$budget_per_cost_summary as
select        DISTINCT
               a.proposal_number,
               a.version_number,
               a.budget_period,
               a.line_item_number,
               a.budget_category_code,
               a.cost_element,
               f.description fdesc,
               g.description gdesc,
               b.person_number,
               b.person_id,
               p.full_name,
               b.percent_charged,
               b.percent_effort,
               b.job_code,
               decode(b.start_date, null, a.START_DATE, b.START_DATE) as START_DATE,
               decode(b.end_date, null, a.END_DATE, b.END_DATE) as END_DATE,
               decode(b.SALARY_REQUESTED,
                      null,
                      a.LINE_ITEM_cost,
                      b.SALARY_REQUESTED) as SALARY_REQUESTED,
               c.rate_class_code,
               c.rate_type_code,
               d.rate_class_type,
               c.calculated_cost,
               DECODE(inv.PRINCIPAL_INVESTIGATOR_FLAG, 'Y', '1', 'N', '2', NULL, '3', '4') PRINCIPAL_INVESTIGATOR

          from osp$budget_details            a,
               osp$budget_personnel_details  b,
               osp$person                    p,
               osp$budget_rate_and_base      rb,
               osp$budget_personnel_cal_amts c,
               osp$rate_class                d,
               osp$rate_type                 e,
               osp$budget_category           f,
               osp$cost_element              g,
               osp$eps_prop_investigators inv
         where a.proposal_number = b.proposal_number(+)
           and b.PROPOSAL_NUMBER = rb.PROPOSAL_NUMBER(+)
           and rb.PROPOSAL_NUMBER = c.PROPOSAL_NUMBER(+)
           and a.version_number = b.version_number(+)
           and b.version_number = rb.version_number(+)
           and rb.VERSION_NUMBER = c.VERSION_NUMBER(+)
           and a.budget_period = b.budget_period(+)
           and b.budget_period = rb.budget_period(+)
           and rb.BUDGET_PERIOD = c.BUDGET_PERIOD(+)
           and a.line_item_number = b.line_item_number(+)
           and b.line_item_number = rb.line_item_number(+)
           and rb.LINE_ITEM_NUMBER = c.LINE_ITEM_NUMBER(+)
           and b.person_id = p.person_id(+)
           and (b.PERSON_NUMBER is null or b.PERSON_NUMBER = c.PERSON_NUMBER)
           and rb.RATE_CLASS_CODE = c.RATE_CLASS_CODE(+)
           and c.RATE_CLASS_CODE = d.RATE_CLASS_CODE(+)
           and c.RATE_CLASS_CODE = e.RATE_CLASS_CODE(+)
           and c.RATE_TYPE_CODE = e.RATE_TYPE_CODE(+)
           and rb.RATE_TYPE_CODE = c.RATE_TYPE_CODE(+)
           and a.budget_category_code = f.budget_category_code
           and a.budget_category_code = g.budget_category_code
           and a.cost_element = g.cost_element
           and b.proposal_number = inv.proposal_number (+)
           and b.person_id = inv.person_id (+)

ORDER by  proposal_number,
          version_number,
          budget_period,
          line_item_number,
          budget_category_code,
          cost_element,
          fdesc,
          gdesc,
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
;

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