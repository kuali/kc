INSERT INTO KRIM_PERM_T
           (PERM_ID,
            PERM_TMPL_ID,
            NMSPC_CD,
            NM,
            DESC_TXT,
            OBJ_ID)
VALUES     (KRIM_PERM_ID_S.NEXTVAL,
            1000,
            'KRA-PD',
            'Maintain ProposalHierarchy',
            'Create, modify and synchronize ProposalHierarchies',
            SYS_GUID());

INSERT INTO KRIM_ROLE_PERM_T
           (ROLE_PERM_ID,
            ROLE_ID,
            PERM_ID,
            OBJ_ID)
VALUES     (KRIM_ROLE_PERM_ID_S.NEXTVAL,
            1109,
            1055,
            SYS_GUID());

ALTER TABLE EPS_PROPOSAL
ADD (HIERARCHY_PROPOSAL_NUMBER VARCHAR2(12),
     HIERARCHY_HASH_CODE DECIMAL(10));

ALTER TABLE EPS_PROPOSAL
ADD CONSTRAINT EPS_PROPOSAL_FK1 FOREIGN KEY (HIERARCHY_PROPOSAL_NUMBER) REFERENCES EPS_PROPOSAL(PROPOSAL_NUMBER);

DROP TABLE EPS_HIERARCHY_CHILD; 