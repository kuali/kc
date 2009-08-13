alter table EPS_PROP_SPECIAL_REVIEW 
    add (
        HIERARCHY_PROPOSAL_NUMBER VARCHAR(12),
        HIDE_IN_HIERARCHY CHAR(1) default 'N' not null);

alter table EPS_PROP_SPECIAL_REVIEW
    add constraint FK_EPS_PROP_SPEC_RVW_HIER foreign key (HIERARCHY_PROPOSAL_NUMBER) references EPS_PROPOSAL(PROPOSAL_NUMBER);

alter table EPS_PROP_PERSON 
    add (
        HIERARCHY_PROPOSAL_NUMBER VARCHAR(12),
        HIDE_IN_HIERARCHY CHAR(1) default 'N' not null);
        
alter table EPS_PROP_PERSON
    add constraint FK_EPS_PROP_PERSON_HIER foreign key (HIERARCHY_PROPOSAL_NUMBER) references EPS_PROPOSAL(PROPOSAL_NUMBER);

alter table EPS_PROP_SCIENCE_KEYWORD 
    add (
        HIERARCHY_PROPOSAL_NUMBER VARCHAR(12),
        HIDE_IN_HIERARCHY CHAR(1) default 'N' not null);
        
alter table EPS_PROP_SCIENCE_KEYWORD
    add constraint FK_EPS_PROP_SCI_KWD_HIER foreign key (HIERARCHY_PROPOSAL_NUMBER) references EPS_PROPOSAL(PROPOSAL_NUMBER);

alter table NARRATIVE 
    add (
        HIERARCHY_PROPOSAL_NUMBER VARCHAR(12),
        HIDE_IN_HIERARCHY CHAR(1) default 'N' not null);

alter table NARRATIVE
    add constraint FK_NARRATIVE_HIER foreign key (HIERARCHY_PROPOSAL_NUMBER) references EPS_PROPOSAL(PROPOSAL_NUMBER);

create table EPS_HIERARCHY_CHILD (
	PROPOSAL_NUMBER				VARCHAR(12) constraint EPS_HIERARCHY_CHILD_N1 not null,
	HIERARCHY_PROPOSAL_NUMBER	VARCHAR(12) constraint EPS_HIERARCHY_CHILD_N2 not null,
	PROPOSAL_HASH_CODE			NUMBER(10,0),
	PROPOSAL_UPDATE_TIMESTAMP	DATE,
	UPDATE_TIMESTAMP			DATE constraint EPS_HIERARCHY_CHILD_N3 not null,
	UPDATE_USER					VARCHAR2(60) constraint EPS_HIERARCHY_CHILD_N4 not null,
	VER_NBR						NUMBER(8,0) DEFAULT 1 constraint EPS_HIERARCHY_CHILD_N5 not null,
	OBJ_ID						VARCHAR2(36) DEFAULT SYS_GUID() constraint EPS_HIERARCHY_CHILD_N6 not null,
	constraint EPS_HIERARCHY_CHILD_PK primary key (PROPOSAL_NUMBER)
);

alter table EPS_PROPOSAL add (IS_HIERARCHY char(1) default 'N' not null);
