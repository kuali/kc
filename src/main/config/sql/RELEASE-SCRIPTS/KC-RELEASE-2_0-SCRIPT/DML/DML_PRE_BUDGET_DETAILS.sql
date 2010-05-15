CREATE TABLE TEMP_B as select * from BUDGET;
CREATE TABLE TEMP_BD as select * from BUDGET_DETAILS;
CREATE TABLE TEMP_BRAB as select * from BUDGET_RATE_AND_BASE;
CREATE TABLE TEMP_BDCA as select * from BUDGEt_details_cal_amts;
Create table temp_BPD as select * from BUDGET_PERSONNEL_DETAILS;
CREATE table temp_bpca as select * from budget_personnel_cal_amts;	
CREATE TABLE TEMP_BPDRAB AS SELECT * FROM BUDGET_PER_DET_RATE_AND_BASE;

TRUNCATE TABLE BUDGET_RATE_AND_BASE;
TRUNCATE TABLE BUDGET_PER_DET_RATE_AND_BASE;
truncate table budget_details_cal_amts;
truncate table budget_personnel_cal_amts;
TRUNcate table budget_personnel_details;
TRUNCATE TABLE BUDGET_DETAILS;
TRUNCATE TABLE BUDGET;
