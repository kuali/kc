ALTER TABLE eps_prop_cong_district DROP PRIMARY KEY;

ALTER TABLE eps_prop_cong_district DROP CONSTRAINT FK_EPS_PROP_CONG_DISTRICT1;

--(might need to do 'TRUNCATE TABLE eps_prop_cong_district' here)
ALTER TABLE eps_prop_cong_district ADD (cong_district_id NUMBER PRIMARY KEY);

/* sequence for EPS_PROP_CONG_DISTRICT table */
CREATE SEQUENCE SEQ_CONG_DISTRICT_ID_KRA;
