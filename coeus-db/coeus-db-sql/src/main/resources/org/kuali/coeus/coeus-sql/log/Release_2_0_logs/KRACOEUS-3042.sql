ALTER TABLE EPS_PROPOSAL ADD HIERARCHY_BUDGET_TYPE CHAR(1);

INSERT INTO KRNS_PARM_T
           (NMSPC_CD,
            PARM_DTL_TYP_CD,
            PARM_NM,
            PARM_TYP_CD,
            TXT,
            PARM_DESC_TXT,
            CONS_CD)
VALUES     ('KRA-B',
            'D',
            'proposalHierarchySubProjectDirectCostElement',
            'CONFG',
            'PHTD01',
            'The Cost Element to be used for the Direct Cost sub-project summary line items in a Proposal Hierarchy budget',
            'A');

INSERT INTO KRNS_PARM_T
           (NMSPC_CD,
            PARM_DTL_TYP_CD,
            PARM_NM,
            PARM_TYP_CD,
            TXT,
            PARM_DESC_TXT,
            CONS_CD)
VALUES     ('KRA-B',
            'D',
            'proposalHierarchySubProjectIndirectCostElement',
            'CONFG',
            'PHTID02',
            'The Cost Element to be used for the Indirect Cost sub-project summary line items in a Proposal Hierarchy budget',
            'A'); 