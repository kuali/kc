CREATE TABLE BUDGET_DOCUMENT(	
		DOCUMENT_NUMBER VARCHAR2(10 BYTE) NOT NULL ENABLE, 
		PARENT_DOCUMENT_KEY VARCHAR2(10 BYTE), 
		VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL ENABLE, 
		OBJ_ID VARCHAR2(36 BYTE) DEFAULT SYS_GUID() NOT NULL ENABLE, 
		UPDATE_TIMESTAMP DATE NOT NULL ENABLE, 
		UPDATE_USER VARCHAR2(10 BYTE) NOT NULL ENABLE, 
	 	CONSTRAINT PK_BUDGET_DOCUMENT PRIMARY KEY (DOCUMENT_NUMBER)
   ) ;

alter table BUDGET add BUDGET_ID NUMBER(12, 0);
CREATE SEQUENCE SEQ_BUDGET_ID INCREMENT BY 1 START WITH 1;
update BUDGET set BUDGET_ID=SEQ_BUDGET_ID.nextVal;
commit;
alter table BUDGET_MODULAR_IDC drop CONSTRAINT FK_BUDGET_MODULAR_IDC_KRA;
alter table BUDGET_MODULAR drop constraint FK_BUDGET_MODULAR_BP_KRA;
alter table BUDGET_MODULAR drop constraint FK_PROPOSAL_MODULAR_KRA;
alter table BUDGET_PROJECT_INCOME drop constraint FK_BUDGET_PROJ_INC_BUDGET_KRA;
ALTER TABLE EPS_PROP_COST_SHARING drop CONSTRAINT FK_EPS_PROP_COST_SHARING_KRA;
ALTER TABLE EPS_PROP_IDC_RATE drop CONSTRAINT FK_EPS_PROP_IDC_RATE_KRA;
alter table BUDGET drop primary key;
alter table BUDGET add CONSTRAINT PK_BUDGET PRIMARY KEY (BUDGET_ID);


--adding budget_id to all child tables and refer it from budget as foreign key
alter table BUDGET_DETAILS add BUDGET_ID NUMBER(12, 0);
alter table BUDGET_DETAILS_CAL_AMTS add BUDGET_ID NUMBER(12, 0);
alter table BUDGET_MODULAR add BUDGET_ID NUMBER(12, 0);
alter table BUDGET_MODULAR_IDC add BUDGET_ID NUMBER(12, 0);
alter table BUDGET_PER_DET_RATE_AND_BASE add BUDGET_ID NUMBER(12, 0);
alter table BUDGET_PERIODS add BUDGET_ID NUMBER(12, 0);
alter table BUDGET_PERSONNEL_CAL_AMTS add BUDGET_ID NUMBER(12, 0);
alter table BUDGET_PERSONNEL_DETAILS add BUDGET_ID NUMBER(12, 0);
alter table BUDGET_PERSONS add BUDGET_ID NUMBER(12, 0);
alter table BUDGET_PROJECT_INCOME add BUDGET_ID NUMBER(12, 0);
alter table BUDGET_RATE_AND_BASE add BUDGET_ID NUMBER(12, 0);
alter table BUDGET_SUB_AWARD_ATT add BUDGET_ID NUMBER(12, 0);
alter table BUDGET_SUB_AWARD_FILES add BUDGET_ID NUMBER(12, 0);
alter table BUDGET_SUB_AWARDS add BUDGET_ID NUMBER(12, 0);
alter table EPS_PROP_COST_SHARING add BUDGET_ID NUMBER(12, 0);
alter table EPS_PROP_IDC_RATE add BUDGET_ID NUMBER(12, 0);
alter table EPS_PROP_RATES add BUDGET_ID NUMBER(12, 0);
alter table EPS_PROP_LA_RATES add BUDGET_ID NUMBER(12, 0);

--add foreign keys to refer budget_id

alter table BUDGET_DETAILS add constraint FK_BUDGET_DETAILS FOREIGN KEY(BUDGET_ID) references BUDGET;
alter table BUDGET_DETAILS_CAL_AMTS add constraint FK_BUDGET_DETAILS_CAL_AMTS FOREIGN KEY(BUDGET_ID) references BUDGET;
alter table BUDGET_MODULAR add constraint FK_BUDGET_MODULAR FOREIGN KEY(BUDGET_ID) references BUDGET;
alter table BUDGET_MODULAR_IDC add constraint FK_BUDGET_MODULAR_IDC FOREIGN KEY(BUDGET_ID) references BUDGET;
alter table BUDGET_PER_DET_RATE_AND_BASE add constraint FK_PER_DET_RATE_AND_BASE FOREIGN KEY(BUDGET_ID) references BUDGET;
alter table BUDGET_PERIODS add constraint FK_BUDGET_PERIODS FOREIGN KEY(BUDGET_ID) references BUDGET;
alter table BUDGET_PERSONNEL_CAL_AMTS add constraint FK_BUDGET_PERSONNEL_CAL_AMTS FOREIGN KEY(BUDGET_ID) references BUDGET;
alter table BUDGET_PERSONNEL_DETAILS add constraint FK_BUDGET_PERSONNEL_DETAILS FOREIGN KEY(BUDGET_ID) references BUDGET;
alter table BUDGET_PERSONS add constraint FK_BUDGET_PERSONS FOREIGN KEY(BUDGET_ID) references BUDGET;
alter table BUDGET_PROJECT_INCOME add constraint FK_PROJECT_INCOME FOREIGN KEY(BUDGET_ID) references BUDGET;
alter table BUDGET_RATE_AND_BASE add constraint FK_BUDGET_RATE_AND_BASE FOREIGN KEY(BUDGET_ID) references BUDGET;
alter table BUDGET_SUB_AWARD_ATT add constraint FK_BUDGET_SUB_AWARD_ATT FOREIGN KEY(BUDGET_ID) references BUDGET;
alter table BUDGET_SUB_AWARD_FILES add constraint FK_BUDGET_SUB_AWARD_FILES FOREIGN KEY(BUDGET_ID) references BUDGET;
alter table BUDGET_SUB_AWARDS add constraint FK_BUDGET_SUB_AWARDS FOREIGN KEY(BUDGET_ID) references BUDGET;
alter table EPS_PROP_COST_SHARING add constraint FK_EPS_PROP_COST_SHARING FOREIGN KEY(BUDGET_ID) references BUDGET;
alter table EPS_PROP_IDC_RATE add constraint FK_EPS_PROP_IDC_RATE FOREIGN KEY(BUDGET_ID) references BUDGET;
alter table EPS_PROP_RATES add constraint FK_EPS_PROP_RATES FOREIGN KEY(BUDGET_ID) references BUDGET;
alter table EPS_PROP_LA_RATES add constraint FK_EPS_PROP_LA_RATES FOREIGN KEY(BUDGET_ID) references BUDGET;


alter table BUDGET modify PROPOSAL_NUMBER null;
alter table BUDGET modify VERSION_NUMBER null;

alter table BUDGET_PERIODS modify PROPOSAL_NUMBER null;
alter table BUDGET_PERIODS modify VERSION_NUMBER null;
update BUDGET_PERIODS set BUDGET_ID=
	(select budget_id from budget 
		where budget.proposal_number=BUDGET_PERIODS.proposal_number 
		and budget.version_number=BUDGET_PERIODS.version_number);

alter table BUDGET_DETAILS drop primary key;
alter table BUDGET_DETAILS modify PROPOSAL_NUMBER null;
alter table BUDGET_DETAILS modify VERSION_NUMBER null;
update BUDGET_DETAILS set BUDGET_ID=
	(select budget_id from budget 
		where budget.proposal_number=budget_details.proposal_number 
		and budget.version_number=budget_details.version_number);
alter table BUDGET_DETAILS add CONSTRAINT PK_BUDGET_DETAILS PRIMARY KEY (BUDGET_PERIOD_NUMBER,LINE_ITEM_NUMBER);
alter table BUDGET_DETAILS add constraint FK1_BUDGET_DETAILS FOREIGN KEY(BUDGET_PERIOD_NUMBER) references BUDGET_PERIODS;

alter table BUDGET_DETAILS_CAL_AMTS drop primary key;
alter table BUDGET_DETAILS_CAL_AMTS modify PROPOSAL_NUMBER null;
alter table BUDGET_DETAILS_CAL_AMTS modify VERSION_NUMBER null;
update BUDGET_DETAILS_CAL_AMTS set BUDGET_ID=
	(select budget_id from budget 
		where budget.proposal_number=BUDGET_DETAILS_CAL_AMTS.proposal_number 
		and budget.version_number=BUDGET_DETAILS_CAL_AMTS.version_number);
alter table BUDGET_DETAILS_CAL_AMTS add CONSTRAINT PK_BUDGET_DETAILS_CAL_AMTS 
						PRIMARY KEY (BUDGET_PERIOD_NUMBER,LINE_ITEM_NUMBER,RATE_CLASS_CODE,RATE_TYPE_CODE);
alter table BUDGET_DETAILS_CAL_AMTS add constraint FK1_BUDGET_DETAILS_CAL_AMTS FOREIGN KEY(BUDGET_PERIOD_NUMBER,LINE_ITEM_NUMBER) references BUDGET_DETAILS;

alter table BUDGET_RATE_AND_BASE drop primary key;
alter table BUDGET_RATE_AND_BASE modify PROPOSAL_NUMBER null;
alter table BUDGET_RATE_AND_BASE modify VERSION_NUMBER null;
update BUDGET_RATE_AND_BASE set BUDGET_ID=
	(select budget_id from budget 
		where budget.proposal_number=BUDGET_RATE_AND_BASE.proposal_number 
		and budget.version_number=BUDGET_RATE_AND_BASE.version_number);
alter table BUDGET_RATE_AND_BASE add CONSTRAINT PK_BUDGET_RATE_AND_BASE 
						PRIMARY KEY (BUDGET_PERIOD_NUMBER,LINE_ITEM_NUMBER,RATE_NUMBER,RATE_CLASS_CODE,RATE_TYPE_CODE);
alter table BUDGET_RATE_AND_BASE add constraint FK1_BUDGET_RATE_AND_BASE 
						FOREIGN KEY(BUDGET_PERIOD_NUMBER,LINE_ITEM_NUMBER,RATE_CLASS_CODE,RATE_TYPE_CODE) references BUDGET_DETAILS_CAL_AMTS;

alter table BUDGET_PERSONNEL_DETAILS drop primary key;
alter table BUDGET_PERSONNEL_DETAILS modify PROPOSAL_NUMBER null;
alter table BUDGET_PERSONNEL_DETAILS modify VERSION_NUMBER null;
update BUDGET_PERSONNEL_DETAILS set BUDGET_ID=
	(select budget_id from budget 
		where budget.proposal_number=BUDGET_PERSONNEL_DETAILS.proposal_number 
		and budget.version_number=BUDGET_PERSONNEL_DETAILS.version_number);
alter table BUDGET_PERSONNEL_DETAILS add CONSTRAINT PK_BUDGET_PERSONNEL_DETAILS 
						PRIMARY KEY (BUDGET_PERIOD_NUMBER,LINE_ITEM_NUMBER,PERSON_NUMBER);
alter table BUDGET_PERSONNEL_DETAILS add constraint FK1_BUDGET_PERSONNEL_DETAILS 
						FOREIGN KEY(BUDGET_PERIOD_NUMBER,LINE_ITEM_NUMBER) references BUDGET_DETAILS;
alter table BUDGET_PERSONNEL_DETAILS drop constraint FK_BUDGET_PER_DET_BGT_PER_KRA;
						
alter table BUDGET_PERSONNEL_CAL_AMTS drop primary key;
alter table BUDGET_PERSONNEL_CAL_AMTS modify PROPOSAL_NUMBER null;
alter table BUDGET_PERSONNEL_CAL_AMTS modify VERSION_NUMBER null;
update BUDGET_PERSONNEL_CAL_AMTS set BUDGET_ID=
	(select budget_id from budget 
		where budget.proposal_number=BUDGET_PERSONNEL_CAL_AMTS.proposal_number 
		and budget.version_number=BUDGET_PERSONNEL_CAL_AMTS.version_number);
alter table BUDGET_PERSONNEL_CAL_AMTS add CONSTRAINT PK_BUDGET_PERSONNEL_CAL_AMTS 
						PRIMARY KEY (BUDGET_PERIOD_NUMBER,LINE_ITEM_NUMBER,PERSON_NUMBER,RATE_CLASS_CODE,RATE_TYPE_CODE);
alter table BUDGET_PERSONNEL_CAL_AMTS add constraint FK1_BUDGET_PERSONNEL_CAL_AMTS 
						FOREIGN KEY(BUDGET_PERIOD_NUMBER,LINE_ITEM_NUMBER,PERSON_NUMBER) references BUDGET_PERSONNEL_DETAILS;

alter table BUDGET_PER_DET_RATE_AND_BASE modify PROPOSAL_NUMBER null;
alter table BUDGET_PER_DET_RATE_AND_BASE modify VERSION_NUMBER null;

create table TEMP_BPDRAB as select * from budget_per_det_rate_and_base;
truncate table BUDGET_PER_DET_RATE_AND_BASE;
alter table BUDGET_PER_DET_RATE_AND_BASE modify RATE_CLASS_CODE VARCHAR2(3);
alter table BUDGET_PER_DET_RATE_AND_BASE modify RATE_TYPE_CODE VARCHAR2(3);
insert into BUDGET_PER_DET_RATE_AND_BASE (Budget_ID, budget_period_number, line_item_number, 
    person_number, Rate_number, proposal_number, version_number, budget_period, person_id, 
    start_date, end_date, rate_class_code, rate_type_code, on_off_campus_flag,
    applied_rate, salary_requested, base_cost_sharing, calculated_cost, calculated_cost_sharing,
    update_timestamp, update_user, ver_nbr, obj_id, underrecovery_amount)
select b.Budget_ID, t.budget_period_number, t.line_item_number, 
    t.person_number, t.Rate_number, t.proposal_number, t.version_number, t.budget_period, t.person_id, 
    t.start_date, t.end_date, t.rate_class_code, t.rate_type_code, t.on_off_campus_flag,
    t.applied_rate, t.salary_requested, t.base_cost_sharing, t.calculated_cost, t.calculated_cost_sharing,
    t.update_timestamp, t.update_user, t.ver_nbr, t.obj_id, t.underrecovery_amount
    from temp_bpdrab t, budget b
    where t.proposal_number = b.proposal_number and t.version_number = b.version_number;
commit;
drop table temp_bpdrab;
alter table BUDGET_PER_DET_RATE_AND_BASE add constraint FK1_BGT_PER_DET_RATE_AND_BASE 
						FOREIGN KEY(BUDGET_PERIOD_NUMBER,LINE_ITEM_NUMBER,PERSON_NUMBER,RATE_CLASS_CODE,RATE_TYPE_CODE) references BUDGET_PERSONNEL_CAL_AMTS;
						
alter table BUDGET_MODULAR drop primary key;
alter table BUDGET_MODULAR modify PROPOSAL_NUMBER null;
alter table BUDGET_MODULAR modify VERSION_NUMBER null;
update BUDGET_MODULAR set BUDGET_ID=
	(select budget_id from budget 
		where budget.proposal_number=BUDGET_MODULAR.proposal_number 
		and budget.version_number=BUDGET_MODULAR.version_number);
alter table BUDGET_MODULAR add CONSTRAINT PK_BUDGET_MODULAR 
						PRIMARY KEY (BUDGET_PERIOD_NUMBER);
alter table BUDGET_MODULAR add constraint FK1_BUDGET_MODULAR FOREIGN KEY(BUDGET_PERIOD_NUMBER) references BUDGET_PERIODS;

alter table BUDGET_MODULAR_IDC drop primary key;
alter table BUDGET_MODULAR_IDC modify PROPOSAL_NUMBER null;
alter table BUDGET_MODULAR_IDC modify VERSION_NUMBER null;
update BUDGET_MODULAR_IDC set BUDGET_ID=
	(select budget_id from budget 
		where budget.proposal_number=BUDGET_MODULAR_IDC.proposal_number 
		and budget.version_number=BUDGET_MODULAR_IDC.version_number);		
alter table BUDGET_MODULAR_IDC add CONSTRAINT PK_BUDGET_MODULAR_IDC 
						PRIMARY KEY (BUDGET_PERIOD_NUMBER,RATE_NUMBER);
alter table BUDGET_MODULAR_IDC add constraint FK1_BUDGET_MODULAR_IDC FOREIGN KEY(BUDGET_PERIOD_NUMBER) references BUDGET_MODULAR;
						
alter table BUDGET_PERSONS drop primary key;
alter table BUDGET_PERSONS modify PROPOSAL_NUMBER null;
alter table BUDGET_PERSONS modify VERSION_NUMBER null;
update BUDGET_PERSONS set BUDGET_ID=
	(select budget_id from budget 
		where budget.proposal_number=BUDGET_PERSONS.proposal_number 
		and budget.version_number=BUDGET_PERSONS.version_number);
alter table BUDGET_PERSONS add CONSTRAINT PK_BUDGET_PERSONS 
						PRIMARY KEY (BUDGET_ID,PERSON_SEQUENCE_NUMBER);

alter table BUDGET_PROJECT_INCOME drop primary key;
alter table BUDGET_PROJECT_INCOME modify PROPOSAL_NUMBER null;
alter table BUDGET_PROJECT_INCOME modify BUDGET_VERSION_NUMBER null;
update BUDGET_PROJECT_INCOME set BUDGET_ID=
	(select budget_id from budget 
		where budget.proposal_number=BUDGET_PROJECT_INCOME.PROPOSAL_NUMBER 
		and budget.version_number=BUDGET_PROJECT_INCOME.BUDGET_VERSION_NUMBER);
alter table BUDGET_PROJECT_INCOME add CONSTRAINT PK_BUDGET_PROJECT_INCOME 
						PRIMARY KEY (BUDGET_ID,PROJECT_INCOME_ID);

alter table BUDGET_SUB_AWARD_FILES drop CONSTRAINT KRA_BUDGET_SUB_AWARDS_FK1;
alter table BUDGET_SUB_AWARD_ATT drop CONSTRAINT KRA_BUDGET_SUB_AWARD_ATT_FK1;

alter table BUDGET_SUB_AWARDS drop primary key;
alter table BUDGET_SUB_AWARDS modify PROPOSAL_NUMBER null;
alter table BUDGET_SUB_AWARDS modify VERSION_NUMBER null;
update BUDGET_SUB_AWARDS set BUDGET_ID=
	(select budget_id from budget 
		where budget.proposal_number=BUDGET_SUB_AWARDS.proposal_number 
		and budget.version_number=BUDGET_SUB_AWARDS.version_number);
alter table BUDGET_SUB_AWARDS add CONSTRAINT PK_BUDGET_SUB_AWARDS 
						PRIMARY KEY (BUDGET_ID,SUB_AWARD_NUMBER);
						
alter table BUDGET_SUB_AWARD_FILES drop primary key;
alter table BUDGET_SUB_AWARD_FILES modify PROPOSAL_NUMBER null;
alter table BUDGET_SUB_AWARD_FILES modify VERSION_NUMBER null;
update BUDGET_SUB_AWARD_FILES set BUDGET_ID=
	(select budget_id from budget 
		where budget.proposal_number=BUDGET_SUB_AWARD_FILES.proposal_number 
		and budget.version_number=BUDGET_SUB_AWARD_FILES.version_number);
alter table BUDGET_SUB_AWARD_FILES add CONSTRAINT PK_BUDGET_SUB_AWARD_FILES 
						PRIMARY KEY (BUDGET_ID,SUB_AWARD_NUMBER);
alter table BUDGET_SUB_AWARD_FILES add constraint FK1_BUDGET_SUB_AWARD_FILES 
						FOREIGN KEY(BUDGET_ID,SUB_AWARD_NUMBER) references BUDGET_SUB_AWARDS;
						
alter table BUDGET_SUB_AWARD_ATT drop primary key;
alter table BUDGET_SUB_AWARD_ATT modify PROPOSAL_NUMBER null;
alter table BUDGET_SUB_AWARD_ATT modify VERSION_NUMBER null;
update BUDGET_SUB_AWARD_ATT set BUDGET_ID=
	(select budget_id from budget 
		where budget.proposal_number=BUDGET_SUB_AWARD_ATT.proposal_number 
		and budget.version_number=BUDGET_SUB_AWARD_ATT.version_number);
alter table BUDGET_SUB_AWARD_ATT add CONSTRAINT PK_BUDGET_SUB_AWARD_ATT 
						PRIMARY KEY (BUDGET_ID,SUB_AWARD_NUMBER,CONTENT_ID);
alter table BUDGET_SUB_AWARD_ATT add constraint FK1_BUDGET_SUB_AWARD_ATT 
						FOREIGN KEY(BUDGET_ID,SUB_AWARD_NUMBER) references BUDGET_SUB_AWARD_FILES;

alter table EPS_PROP_COST_SHARING drop primary key;
alter table EPS_PROP_COST_SHARING modify PROPOSAL_NUMBER null;
alter table EPS_PROP_COST_SHARING modify BUDGET_VERSION_NUMBER null;
update EPS_PROP_COST_SHARING set BUDGET_ID=
	(select budget_id from budget 
		where budget.proposal_number=EPS_PROP_COST_SHARING.proposal_number 
		and budget.version_number=EPS_PROP_COST_SHARING.BUDGET_VERSION_NUMBER);
alter table EPS_PROP_COST_SHARING add CONSTRAINT PK_EPS_PROP_COST_SHARING 
						PRIMARY KEY (BUDGET_ID,COST_SHARE_ID);
						
alter table EPS_PROP_IDC_RATE drop primary key;
alter table EPS_PROP_IDC_RATE modify PROPOSAL_NUMBER null;
alter table EPS_PROP_IDC_RATE modify BUDGET_VERSION_NUMBER null;
update EPS_PROP_IDC_RATE set BUDGET_ID=
	(select budget_id from budget 
		where budget.proposal_number=EPS_PROP_IDC_RATE.proposal_number 
		and budget.version_number=EPS_PROP_IDC_RATE.BUDGET_VERSION_NUMBER);
alter table EPS_PROP_IDC_RATE add CONSTRAINT PK_EPS_PROP_IDC_RATE 
						PRIMARY KEY (BUDGET_ID,UNRECOVERED_FNA_ID);
						
alter table EPS_PROP_RATES drop primary key;
alter table EPS_PROP_RATES modify PROPOSAL_NUMBER null;
alter table EPS_PROP_RATES modify VERSION_NUMBER null;
update EPS_PROP_RATES set BUDGET_ID=
	(select budget_id from budget 
		where budget.proposal_number=EPS_PROP_RATES.proposal_number 
		and budget.version_number=EPS_PROP_RATES.version_number);
alter table EPS_PROP_RATES add CONSTRAINT PK_EPS_PROP_RATES 
						PRIMARY KEY (BUDGET_ID,RATE_CLASS_CODE,RATE_TYPE_CODE,FISCAL_YEAR,START_DATE,ON_OFF_CAMPUS_FLAG);
						
alter table EPS_PROP_LA_RATES drop primary key;
alter table EPS_PROP_LA_RATES modify PROPOSAL_NUMBER null;
alter table EPS_PROP_LA_RATES modify VERSION_NUMBER null;
update EPS_PROP_LA_RATES set BUDGET_ID=
	(select budget_id from budget 
		where budget.proposal_number=EPS_PROP_LA_RATES.proposal_number 
		and budget.version_number=EPS_PROP_LA_RATES.version_number);
alter table EPS_PROP_LA_RATES add CONSTRAINT PK_EPS_PROP_LA_RATES 
						PRIMARY KEY (BUDGET_ID,RATE_CLASS_CODE,RATE_TYPE_CODE,FISCAL_YEAR,START_DATE,ON_OFF_CAMPUS_FLAG);
commit;
