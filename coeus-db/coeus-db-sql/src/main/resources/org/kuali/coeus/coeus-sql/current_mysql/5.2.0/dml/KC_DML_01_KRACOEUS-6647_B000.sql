DELIMITER /
delete from CUST_REPORT_DETAILS
/
commit
/
INSERT INTO SEQ_REPORT_ID VALUES(NULL)
/
INSERT INTO CUST_REPORT_DETAILS(REPORT_ID,REPORT_LABEL,REPORT_DESCRIPTION,REPORT_TYPE_CODE,RIGHT_REQUIRED,FILE_NAME,CONTENT_TYPE,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID,REPORT_DESIGN) values ((SELECT (MAX(ID)) FROM SEQ_REPORT_ID),'Proposal By College','Proposal By College',(SELECT REPORT_TYPE_CODE FROM CUST_REPORT_TYPE WHERE REPORT_TYPE_DESC = 'Global'),'RUN GLOBAL REPORTS','proposalbycollege.rptdesign','application/octet-stream',NOW(),'admin',1,UUID(),
'<?xml version="1.0" encoding="UTF-8"?>
<report xmlns="http://www.eclipse.org/birt/2005/design" version="3.2.20" id="1">
    <property name="createdBy">Eclipse BIRT Designer Version 2.5.0.v20090603 Build &lt;2.5.0.v20090617-0630></property>
    <property name="units">in</property>
    <property name="iconFile">/templates/blank_report.gif</property>
    <property name="layoutPreference">auto layout</property>
    <property name="bidiLayoutOrientation">ltr</property>
    <parameters>
        <scalar-parameter name="startDate" id="7">
            <text-property name="promptText">Enter Start Date</text-property>
            <property name="valueType">static</property>
            <property name="dataType">dateTime</property>
            <property name="paramType">simple</property>
            <property name="controlType">text-box</property>
            <property name="distinct">true</property>
            <structure name="format">
                <property name="category">Unformatted</property>
            </structure>
        </scalar-parameter>
        <scalar-parameter name="endDate" id="8">
            <text-property name="promptText">Enter End Date</text-property>
            <property name="valueType">static</property>
            <property name="dataType">dateTime</property>
            <property name="paramType">simple</property>
            <property name="controlType">text-box</property>
            <property name="distinct">true</property>
            <structure name="format">
                <property name="category">Unformatted</property>
            </structure>
        </scalar-parameter>
    </parameters>
    <data-sources>
    </data-sources>
    <data-sets>
        <oda-data-set extensionID="org.eclipse.birt.report.data.oda.jdbc.JdbcSelectDataSet" name="Data Set" id="102">
            <list-property name="parameters">
                <structure>
                    <property name="name">param_1</property>
                    <property name="paramName">startDate</property>
                    <property name="dataType">dateTime</property>
                    <property name="position">1</property>
                    <property name="isInput">true</property>
                    <property name="isOutput">false</property>
                </structure>
                <structure>
                    <property name="name">param_2</property>
                    <property name="paramName">endDate</property>
                    <property name="dataType">dateTime</property>
                    <property name="position">2</property>
                    <property name="isInput">true</property>
                    <property name="isOutput">false</property>
                </structure>
            </list-property>
            <structure name="cachedMetaData">
                <list-property name="resultSet">
                    <structure>
                        <property name="position">1</property>
                        <property name="name">PROPOSAL_NUMBER</property>
                        <property name="dataType">string</property>
                    </structure>
                    <structure>
                        <property name="position">2</property>
                        <property name="name">PERSON_NAME</property>
                        <property name="dataType">string</property>
                    </structure>
                    <structure>
                        <property name="position">3</property>
                        <property name="name">TITLE</property>
                        <property name="dataType">string</property>
                    </structure>
                    <structure>
                        <property name="position">4</property>
                        <property name="name">UNIT_NAME</property>
                        <property name="dataType">string</property>
                    </structure>
                    <structure>
                        <property name="position">5</property>
                      <property name="name">COLLEGE_NAME</property>
                        <property name="dataType">string</property>
                    </structure>
                    <structure>
                        <property name="position">6</property>
                        <property name="name">PROJECT_SHARE</property>
                        <property name="dataType">string</property>
                    </structure>
                    <structure>
                        <property name="position">7</property>
                        <property name="name">TOTAL_DIRECT_COST_TOTAL</property>
                        <property name="dataType">decimal</property>
                    </structure>
                    <structure>
                        <property name="position">8</property>
                        <property name="name">TOTAL_INDIRECT_COST_TOTAL</property>
                        <property name="dataType">decimal</property>
                    </structure>
                    <structure>
                        <property name="position">9</property>
                        <property name="name">SPONSOR_NAME</property>
                        <property name="dataType">string</property>
                    </structure>
                    <structure>
                        <property name="position">10</property>
                        <property name="name">REQUESTED_START_DATE_INITIAL</property>
                        <property name="dataType">date-time</property>
                    </structure>
                    <structure>
                        <property name="position">11</property>
                        <property name="name">REQUESTED_END_DATE_INITIAL</property>
                        <property name="dataType">date-time</property>
                    </structure>
                    <structure>
                        <property name="position">12</property>
                        <property name="name">TOTAL_AMOUNT</property>
                        <property name="dataType">decimal</property>
                    </structure>
                    <structure>
                        <property name="position">13</property>
                        <property name="name">PI_SHARE</property>
                        <property name="dataType">decimal</property>
                    </structure>
                </list-property>
            </structure>
            <property name="dataSource">Data Source</property>
            <list-property name="resultSet">
                <structure>
                    <property name="position">1</property>
                    <property name="name">PROPOSAL_NUMBER</property>
                    <property name="nativeName">PROPOSAL_NUMBER</property>
                    <property name="dataType">string</property>
                </structure>
                <structure>
                    <property name="position">2</property>
                    <property name="name">PERSON_NAME</property>
                    <property name="nativeName">PERSON_NAME</property>
                    <property name="dataType">string</property>
                </structure>
                <structure>
                    <property name="position">3</property>
                    <property name="name">TITLE</property>
                    <property name="nativeName">TITLE</property>
                    <property name="dataType">string</property>
                </structure>
                <structure>
                    <property name="position">4</property>
                    <property name="name">UNIT_NAME</property>
                    <property name="nativeName">UNIT_NAME</property>
                    <property name="dataType">string</property>
                </structure>
                <structure>
                    <property name="position">5</property>
                    <property name="name">COLLEGE_NAME</property>
                    <property name="nativeName">COLLEGE_NAME</property>
                    <property name="dataType">string</property>
                </structure>
                <structure>
                    <property name="position">6</property>
                    <property name="name">PROJECT_SHARE</property>
                    <property name="nativeName">PROJECT_SHARE</property>
                    <property name="dataType">string</property>
                </structure>
                <structure>
                    <property name="position">7</property>
                    <property name="name">TOTAL_DIRECT_COST_TOTAL</property>
                    <property name="nativeName">TOTAL_DIRECT_COST_TOTAL</property>
                    <property name="dataType">decimal</property>
                </structure>
                <structure>
                    <property name="position">8</property>
                    <property name="name">TOTAL_INDIRECT_COST_TOTAL</property>
                    <property name="nativeName">TOTAL_INDIRECT_COST_TOTAL</property>
                    <property name="dataType">decimal</property>
                </structure>
                <structure>
                    <property name="position">9</property>
                    <property name="name">SPONSOR_NAME</property>
                    <property name="nativeName">SPONSOR_NAME</property>
                    <property name="dataType">string</property>
                </structure>
                <structure>
                    <property name="position">10</property>
                    <property name="name">REQUESTED_START_DATE_INITIAL</property>
                    <property name="nativeName">REQUESTED_START_DATE_INITIAL</property>
                    <property name="dataType">date-time</property>
                </structure>
                <structure>
                    <property name="position">11</property>
                    <property name="name">REQUESTED_END_DATE_INITIAL</property>
                    <property name="nativeName">REQUESTED_END_DATE_INITIAL</property>
                    <property name="dataType">date-time</property>
                </structure>
                <structure>
                    <property name="position">12</property>
                    <property name="name">TOTAL_AMOUNT</property>
                    <property name="nativeName">TOTAL_AMOUNT</property>
                    <property name="dataType">decimal</property>
                </structure>
                <structure>
                    <property name="position">13</property>
                    <property name="name">PI_SHARE</property>
                    <property name="nativeName">PI_SHARE</property>
                    <property name="dataType">decimal</property>
                </structure>
            </list-property>
            <xml-property name="queryText"><![CDATA[select distinct 
        p.PROPOSAL_NUMBER,
        pi.FULL_NAME,
        p.TITLE,
        u.UNIT_NAME, 
        NULL COLLEGE_NAME,
        NULL PROJECT_SHARE,
        p.TOTAL_DIRECT_COST_TOTAL,
        p.TOTAL_INDIRECT_COST_TOTAL, 
        s.SPONSOR_NAME,
        p.REQUESTED_START_DATE_INITIAL,
        p.REQUESTED_END_DATE_INITIAL,
        (p.TOTAL_DIRECT_COST_TOTAL + p.TOTAL_INDIRECT_COST_TOTAL) TOTAL_AMOUNT,
        (pc.CREDIT) PI_SHARE	
        from PROPOSAL p inner join PROPOSAL_PERSONS pi on p.PROPOSAL_NUMBER= pi.PROPOSAL_NUMBER and pi.SEQUENCE_NUMBER=(select max(SEQUENCE_NUMBER) from PROPOSAL where proposal_number=p.PROPOSAL_NUMBER)
                            left join PROPOSAL_PERSON_UNITS pu on p.PROPOSAL_NUMBER=(select PROPOSAL_NUMBER from PROPOSAL_PERSONS pp where pp.proposal_person_id = pu.proposal_person_id) and (select SEQUENCE_NUMBER from PROPOSAL_PERSONS pp where pp.proposal_person_id = pu.proposal_person_id)=p.SEQUENCE_NUMBER and (select PERSON_ID from PROPOSAL_PERSONS pp where pp.proposal_person_id = pu.proposal_person_id)=pi.PERSON_ID
                            inner join UNIT u on u.UNIT_NUMBER=pu.UNIT_NUMBER 
                            inner join SPONSOR s ON s.SPONSOR_CODE = p.SPONSOR_CODE
                            left join PROPOSAL_PER_CREDIT_SPLIT pc on p.PROPOSAL_NUMBER= (select PROPOSAL_NUMBER from PROPOSAL_PERSONS pp where pp.proposal_person_id = pc.proposal_person_id) and (select SEQUENCE_NUMBER from PROPOSAL_PERSONS pp where pp.proposal_person_id = pc.proposal_person_id)=p.SEQUENCE_NUMBER and (select PERSON_ID from PROPOSAL_PERSONS pp where pp.proposal_person_id = pc.proposal_person_id)=pi.PERSON_ID
          where    p.REQUESTED_START_DATE_INITIAL >= ? and p.REQUESTED_END_DATE_INITIAL <= ? ]]></xml-property>
            <xml-property name="designerValues"><![CDATA[<?xml version="1.0" encoding="UTF-8"?>
<model:DesignValues xmlns:design="http://www.eclipse.org/datatools/connectivity/oda/design" xmlns:model="http://www.eclipse.org/birt/report/model/adapter/odaModel">
  <Version>1.0</Version>
  <design:ResultSets derivedMetaData="true">
    <design:resultSetDefinitions>
      <design:resultSetColumns>
        <design:resultColumnDefinitions>
          <design:attributes>
            <design:name>PROPOSAL_NUMBER</design:name>
            <design:position>1</design:position>
            <design:nativeDataTypeCode>12</design:nativeDataTypeCode>
            <design:precision>8</design:precision>
            <design:scale>0</design:scale>
            <design:nullability>NotNullable</design:nullability>
            <design:uiHints>
              <design:displayName>PROPOSAL_NUMBER</design:displayName>
            </design:uiHints>
          </design:attributes>
          <design:usageHints>
            <design:label>PROPOSAL_NUMBER</design:label>
            <design:formattingHints>
              <design:displaySize>8</design:displaySize>
            </design:formattingHints>
          </design:usageHints>
        </design:resultColumnDefinitions>
        <design:resultColumnDefinitions>
          <design:attributes>
            <design:name>PERSON_NAME</design:name>
            <design:position>2</design:position>
            <design:nativeDataTypeCode>12</design:nativeDataTypeCode>
            <design:precision>90</design:precision>
            <design:scale>0</design:scale>
            <design:nullability>NotNullable</design:nullability>
            <design:uiHints>
              <design:displayName>PERSON_NAME</design:displayName>
            </design:uiHints>
          </design:attributes>
          <design:usageHints>
            <design:label>PERSON_NAME</design:label>
            <design:formattingHints>
              <design:displaySize>90</design:displaySize>
            </design:formattingHints>
          </design:usageHints>
        </design:resultColumnDefinitions>
        <design:resultColumnDefinitions>
          <design:attributes>
            <design:name>TITLE</design:name>
            <design:position>3</design:position>
            <design:nativeDataTypeCode>12</design:nativeDataTypeCode>
            <design:precision>200</design:precision>
            <design:scale>0</design:scale>
            <design:nullability>NotNullable</design:nullability>
            <design:uiHints>
              <design:displayName>TITLE</design:displayName>
            </design:uiHints>
          </design:attributes>
          <design:usageHints>
            <design:label>TITLE</design:label>
            <design:formattingHints>
              <design:displaySize>200</design:displaySize>
            </design:formattingHints>
          </design:usageHints>
        </design:resultColumnDefinitions>
        <design:resultColumnDefinitions>
          <design:attributes>
            <design:name>UNIT_NAME</design:name>
            <design:position>4</design:position>
            <design:nativeDataTypeCode>12</design:nativeDataTypeCode>
            <design:precision>60</design:precision>
            <design:scale>0</design:scale>
            <design:nullability>Nullable</design:nullability>
            <design:uiHints>
              <design:displayName>UNIT_NAME</design:displayName>
            </design:uiHints>
          </design:attributes>
          <design:usageHints>
            <design:label>UNIT_NAME</design:label>
            <design:formattingHints>
              <design:displaySize>60</design:displaySize>
            </design:formattingHints>
          </design:usageHints>
        </design:resultColumnDefinitions>
        <design:resultColumnDefinitions>
          <design:attributes>
            <design:name>COLLEGE_NAME</design:name>
            <design:position>5</design:position>
            <design:nativeDataTypeCode>12</design:nativeDataTypeCode>
            <design:precision>0</design:precision>
            <design:scale>0</design:scale>
            <design:nullability>Nullable</design:nullability>
            <design:uiHints>
              <design:displayName>COLLEGE_NAME</design:displayName>
            </design:uiHints>
          </design:attributes>
          <design:usageHints>
            <design:label>COLLEGE_NAME</design:label>
            <design:formattingHints>
              <design:displaySize>0</design:displaySize>
            </design:formattingHints>
          </design:usageHints>
        </design:resultColumnDefinitions>
        <design:resultColumnDefinitions>
          <design:attributes>
            <design:name>PROJECT_SHARE</design:name>
            <design:position>6</design:position>
            <design:nativeDataTypeCode>12</design:nativeDataTypeCode>
            <design:precision>0</design:precision>
            <design:scale>0</design:scale>
            <design:nullability>Nullable</design:nullability>
            <design:uiHints>
              <design:displayName>PROJECT_SHARE</design:displayName>
            </design:uiHints>
          </design:attributes>
          <design:usageHints>
            <design:label>PROJECT_SHARE</design:label>
            <design:formattingHints>
              <design:displaySize>0</design:displaySize>
            </design:formattingHints>
          </design:usageHints>
        </design:resultColumnDefinitions>
        <design:resultColumnDefinitions>
          <design:attributes>
            <design:name>TOTAL_DIRECT_COST_TOTAL</design:name>
            <design:position>7</design:position>
            <design:nativeDataTypeCode>2</design:nativeDataTypeCode>
            <design:precision>12</design:precision>
            <design:scale>2</design:scale>
            <design:nullability>Nullable</design:nullability>
            <design:uiHints>
              <design:displayName>TOTAL_DIRECT_COST_TOTAL</design:displayName>
            </design:uiHints>
          </design:attributes>
          <design:usageHints>
            <design:label>TOTAL_DIRECT_COST_TOTAL</design:label>
            <design:formattingHints>
              <design:displaySize>22</design:displaySize>
            </design:formattingHints>
          </design:usageHints>
        </design:resultColumnDefinitions>
        <design:resultColumnDefinitions>
          <design:attributes>
            <design:name>TOTAL_INDIRECT_COST_TOTAL</design:name>
            <design:position>8</design:position>
            <design:nativeDataTypeCode>2</design:nativeDataTypeCode>
            <design:precision>12</design:precision>
            <design:scale>2</design:scale>
            <design:nullability>Nullable</design:nullability>
            <design:uiHints>
              <design:displayName>TOTAL_INDIRECT_COST_TOTAL</design:displayName>
            </design:uiHints>
          </design:attributes>
          <design:usageHints>
            <design:label>TOTAL_INDIRECT_COST_TOTAL</design:label>
            <design:formattingHints>
              <design:displaySize>22</design:displaySize>
            </design:formattingHints>
          </design:usageHints>
        </design:resultColumnDefinitions>
        <design:resultColumnDefinitions>
          <design:attributes>
            <design:name>SPONSOR_NAME</design:name>
            <design:position>9</design:position>
            <design:nativeDataTypeCode>12</design:nativeDataTypeCode>
            <design:precision>200</design:precision>
            <design:scale>0</design:scale>
            <design:nullability>NotNullable</design:nullability>
            <design:uiHints>
              <design:displayName>SPONSOR_NAME</design:displayName>
            </design:uiHints>
          </design:attributes>
          <design:usageHints>
            <design:label>SPONSOR_NAME</design:label>
            <design:formattingHints>
              <design:displaySize>200</design:displaySize>
            </design:formattingHints>
          </design:usageHints>
        </design:resultColumnDefinitions>
        <design:resultColumnDefinitions>
          <design:attributes>
            <design:name>TOTAL_AMOUNT</design:name>
            <design:position>12</design:position>
            <design:nativeDataTypeCode>2</design:nativeDataTypeCode>
            <design:precision>0</design:precision>
            <design:scale>0</design:scale>
            <design:nullability>Nullable</design:nullability>
            <design:uiHints>
              <design:displayName>TOTAL_AMOUNT</design:displayName>
            </design:uiHints>
          </design:attributes>
          <design:usageHints>
            <design:label>TOTAL_AMOUNT</design:label>
            <design:formattingHints>
              <design:displaySize>22</design:displaySize>
            </design:formattingHints>
          </design:usageHints>
        </design:resultColumnDefinitions>
        <design:resultColumnDefinitions>
          <design:attributes>
            <design:name>PI_SHARE</design:name>
            <design:position>13</design:position>
            <design:nativeDataTypeCode>12</design:nativeDataTypeCode>
            <design:precision>0</design:precision>
            <design:scale>0</design:scale>
            <design:nullability>Nullable</design:nullability>
            <design:uiHints>
              <design:displayName>PI_SHARE</design:displayName>
            </design:uiHints>
          </design:attributes>
          <design:usageHints>
            <design:label>PI_SHARE</design:label>
            <design:formattingHints>
              <design:displaySize>0</design:displaySize>
            </design:formattingHints>
          </design:usageHints>
        </design:resultColumnDefinitions>
      </design:resultSetColumns>
    </design:resultSetDefinitions>
  </design:ResultSets>
</model:DesignValues>]]></xml-property>
        </oda-data-set>
    </data-sets>
    <styles>
        <style name="report" id="4">
            <property name="fontFamily">"Verdana"</property>
            <property name="fontSize">10pt</property>
        </style>
        <style name="crosstab-cell" id="5">
            <property name="borderBottomColor">#CCCCCC</property>
            <property name="borderBottomStyle">solid</property>
            <property name="borderBottomWidth">1pt</property>
            <property name="borderLeftColor">#CCCCCC</property>
            <property name="borderLeftStyle">solid</property>
            <property name="borderLeftWidth">1pt</property>
            <property name="borderRightColor">#CCCCCC</property>
            <property name="borderRightStyle">solid</property>
            <property name="borderRightWidth">1pt</property>
            <property name="borderTopColor">#CCCCCC</property>
            <property name="borderTopStyle">solid</property>
            <property name="borderTopWidth">1pt</property>
        </style>
        <style name="crosstab" id="6">
            <property name="borderBottomColor">#CCCCCC</property>
            <property name="borderBottomStyle">solid</property>
            <property name="borderBottomWidth">1pt</property>
            <property name="borderLeftColor">#CCCCCC</property>
            <property name="borderLeftStyle">solid</property>
            <property name="borderLeftWidth">1pt</property>
            <property name="borderRightColor">#CCCCCC</property>
            <property name="borderRightStyle">solid</property>
            <property name="borderRightWidth">1pt</property>
            <property name="borderTopColor">#CCCCCC</property>
            <property name="borderTopStyle">solid</property>
            <property name="borderTopWidth">1pt</property>
        </style>
    </styles>
    <page-setup>
        <simple-master-page name="Simple MasterPage" id="2">
            <property name="orientation">landscape</property>
            <page-footer>
                <text id="3">
                    <property name="contentType">html</property>
                    <text-property name="content"><![CDATA[<value-of>new Date()</value-of>]]></text-property>
                </text>
            </page-footer>
        </simple-master-page>
    </page-setup>
    <body>
        <grid id="9">
            <property name="height">4.385416666666667in</property>
            <property name="width">12.166666666666666in</property>
            <column id="10">
                <property name="width">12.166666666666666in</property>
            </column>
            <row id="11">
                <property name="height">0.6875in</property>
                <cell id="12">
                    <grid id="31">
                        <property name="width">12.135416666666666in</property>
                        <column id="32">
                            <property name="width">3.65625in</property>
                        </column>
                        <column id="33">
                            <property name="width">1.2291666666666667in</property>
                        </column>
                        <column id="84">
                            <property name="width">0.53125in</property>
                        </column>
                        <column id="34">
                            <property name="width">1.5833333333333333in</property>
                        </column>
                        <column id="477">
                            <property name="width">5.135416666666667in</property>
                        </column>
                        <row id="35">
                            <cell id="36">
                                <property name="colSpan">4</property>
                                <property name="rowSpan">1</property>
                                <label id="39">
                                    <property name="fontSize">12pt</property>
                                    <property name="fontWeight">bold</property>
                                    <property name="color">#CD2027</property>
                                    <property name="textAlign">center</property>
                                    <text-property name="text">Proposal Submitted</text-property>
                                </label>
                            </cell>
                            <cell id="476"/>
                        </row>
                    </grid>
                    <grid id="29">
                        <property name="width">7.28125in</property>
                        <column id="30">
                            <property name="width">3.1458333333333335in</property>
                        </column>
                        <column id="478">
                            <property name="width">0.6145833333333334in</property>
                        </column>
                        <column id="479">
                            <property name="width">3.5208333333333335in</property>
                        </column>
                        <row id="480">
                            <cell id="481">
                                <data id="484">
                                    <property name="color">#800000</property>
                                    <property name="textAlign">right</property>
                                    <list-property name="boundDataColumns">
                                        <structure>
                                            <property name="name">startDate</property>
                                            <expression name="expression">params["startDate"].value</expression>
                                            <property name="dataType">string</property>
                                        </structure>
                                    </list-property>
                                    <property name="resultSetColumn">startDate</property>
                                </data>
                            </cell>
                            <cell id="482">
                                <label id="100">
                                    <property name="fontWeight">bold</property>
                                    <property name="color">#E0192C</property>
                                    <property name="textAlign">center</property>
                                    <text-property name="text">To</text-property>
                                </label>
                            </cell>
                            <cell id="483">
                                <data id="485">
                                    <property name="color">#800000</property>
                                    <property name="textAlign">left</property>
                                    <list-property name="boundDataColumns">
                                        <structure>
                                            <property name="name">endDate</property>
                                            <expression name="expression">params["endDate"].value</expression>
                                            <property name="dataType">string</property>
                                        </structure>
                                    </list-property>
                                    <property name="resultSetColumn">endDate</property>
                                </data>
                            </cell>
                        </row>
                    </grid>
                </cell>
            </row>
            <row id="44">
                <property name="height">2.40625in</property>
                <cell id="45">
                    <table id="363">
                        <property name="height">100%</property>
                        <property name="width">12.260416666666666in</property>
                        <property name="dataSet">Data Set</property>
                        <list-property name="boundDataColumns">
                            <structure>
                                <property name="name">PROPOSAL_NUMBER</property>
                                <property name="displayName">PROPOSAL_NUMBER</property>
                                <expression name="expression">dataSetRow["PROPOSAL_NUMBER"]</expression>
                                <property name="dataType">string</property>
                            </structure>
                            <structure>
                                <property name="name">PERSON_NAME</property>
                                <property name="displayName">PNAME</property>
                                <expression name="expression">dataSetRow["FULL_NAME"]</expression>
                                <property name="dataType">string</property>
                            </structure>
                            <structure>
                                <property name="name">UNIT_NAME</property>
                                <property name="displayName">UNAME</property>
                                <expression name="expression">dataSetRow["UNIT_NAME"]</expression>
                                <property name="dataType">string</property>
                            </structure>
                            <structure>
                                <property name="name">SPONSOR_NAME</property>
                                <property name="displayName">SNAME</property>
                                <expression name="expression">dataSetRow["SPONSOR_NAME"]</expression>
                                <property name="dataType">string</property>
                            </structure>
                            <structure>
                                <property name="name">TOTAL_DIRECT_COST_TOTAL</property>
                                <property name="displayName">DCOST</property>
                                <expression name="expression">dataSetRow["TOTAL_DIRECT_COST_TOTAL"]</expression>
                                <property name="dataType">decimal</property>
                            </structure>
                            <structure>
                                <property name="name">TOTAL_INDIRECT_COST_TOTAL</property>
                                <property name="displayName">ICOST</property>
                                <expression name="expression">dataSetRow["TOTAL_INDIRECT_COST_TOTAL"]</expression>
                                <property name="dataType">decimal</property>
                            </structure>
                            <structure>
                                <property name="name">TOTAL_AMOUNT</property>
                                <property name="displayName">TAMOUNT</property>
                                <expression name="expression">dataSetRow["TOTAL_AMOUNT"]</expression>
                                <property name="dataType">decimal</property>
                            </structure>
                            <structure>
                                <property name="name">TITLE</property>
                                <property name="displayName">TITLE</property>
                                <expression name="expression">dataSetRow["TITLE"]</expression>
                                <property name="dataType">string</property>
                            </structure>
                            <structure>
                                <property name="name">REQUESTED_START_DATE_INITIAL</property>
                                <property name="displayName">REQUESTED_START_DATE_INITIAL</property>
                                <expression name="expression">dataSetRow["REQUESTED_START_DATE_INITIAL"]</expression>
                                <property name="dataType">date-time</property>
                            </structure>
                            <structure>
                                <property name="name">REQUESTED_END_DATE_INITIAL</property>
                                <property name="displayName">REQUESTED_END_DATE_INITIAL</property>
                                <expression name="expression">dataSetRow["REQUESTED_END_DATE_INITIAL"]</expression>
                                <property name="dataType">date-time</property>
                            </structure>
                            <structure>
                                <property name="name">PI_SHARE</property>
                                <property name="displayName">PI_SHARE</property>
                                <expression name="expression">dataSetRow["PI_SHARE"]</expression>
                                <property name="dataType">decimal</property>
                            </structure>
                            <structure>
                                <property name="name">Aggregation</property>
                                <property name="dataType">float</property>
                                <property name="aggregateFunction">AVE</property>
                                <list-property name="arguments">
                                    <structure>
                                        <property name="name">Expression</property>
                                        <expression name="value">dataSetRow["TOTAL_AMOUNT"]*dataSetRow["PI_SHARE"]/100</expression>
                                    </structure>
                                </list-property>
                            </structure>
                            <structure>
                                <property name="name">Aggregation_1</property>
                                <property name="dataType">float</property>
                                <simple-property-list name="aggregateOn">
                                    <value>NewTableGroup1</value>
                                </simple-property-list>
                                <property name="aggregateFunction">SUM</property>
                                <list-property name="arguments">
                                    <structure>
                                        <property name="name">Expression</property>
                                        <expression name="value">row["Aggregation"]</expression>
                                    </structure>
                                </list-property>
                            </structure>
                            <structure>
                                <property name="name">Aggregation_2</property>
                                <property name="dataType">float</property>
                                <simple-property-list name="aggregateOn">
                                    <value>NewTableGroup1</value>
                                </simple-property-list>
                                <property name="aggregateFunction">SUM</property>
                                <list-property name="arguments">
                                    <structure>
                                        <property name="name">Expression</property>
                                        <expression name="value">dataSetRow["PI_SHARE"]/100</expression>
                                    </structure>
                                </list-property>
                            </structure>
                            <structure>
                                <property name="name">Aggregation_3</property>
                                <property name="dataType">float</property>
                                <simple-property-list name="aggregateOn">
                                    <value>NewTableGroup1</value>
                                </simple-property-list>
                                <property name="aggregateFunction">SUM</property>
                                <list-property name="arguments">
                                    <structure>
                                        <property name="name">Expression</property>
                                        <expression name="value">row["TOTAL_AMOUNT"]</expression>
                                    </structure>
                                </list-property>
                            </structure>
                            <structure>
                                <property name="name">Aggregation_4</property>
                                <property name="dataType">float</property>
                                <property name="aggregateFunction">SUM</property>
                                <list-property name="arguments">
                                    <structure>
                                        <property name="name">Expression</property>
                                        <expression name="value">row["Aggregation_1"]</expression>
                                    </structure>
                                </list-property>
                            </structure>
                            <structure>
                                <property name="name">Aggregation_5</property>
                                <property name="dataType">float</property>
                                <property name="aggregateFunction">SUM</property>
                                <list-property name="arguments">
                                    <structure>
                                        <property name="name">Expression</property>
                                        <expression name="value">row["Aggregation_2"]</expression>
                                    </structure>
                                </list-property>
                            </structure>
                        </list-property>
                        <column id="468">
                            <property name="width">0.9583333333333334in</property>
                        </column>
                        <column id="469">
                            <property name="width">1.46875in</property>
                        </column>
                        <column id="470">
                            <property name="width">1.5208333333333333in</property>
                        </column>
                        <column id="471">
                            <property name="width">1.3125in</property>
                        </column>
                        <column id="473">
                            <property name="width">1.4375in</property>
                        </column>
                        <column id="474">
                            <property name="width">0.8125in</property>
                        </column>
                        <column id="540">
                            <property name="width">1.4375in</property>
                        </column>
                        <column id="475">
                            <property name="width">1.3229166666666667in</property>
                        </column>
                        <header>
                            <row id="364">
                                <property name="backgroundColor">white</property>
                                <cell id="367"/>
                                <cell id="368"/>
                                <cell id="369"/>
                                <cell id="370"/>
                                <cell id="372"/>
                                <cell id="373"/>
                                <cell id="532"/>
                                <cell id="374"/>
                            </row>
                        </header>
                        <group id="396">
                            <property name="groupName">NewTableGroup1</property>
                            <property name="interval">none</property>
                            <property name="sortDirection">asc</property>
                            <expression name="keyExpr">row["UNIT_NAME"]</expression>
                            <structure name="toc">
                                <expression name="expressionValue">row["UNIT_NAME"]</expression>
                            </structure>
                            <property name="repeatHeader">true</property>
                            <property name="hideDetail">false</property>
                            <property name="pageBreakAfter">auto</property>
                            <property name="pageBreakBefore">auto</property>
                            <property name="pageBreakInside">auto</property>
                            <header>
                                <row id="397">
                                    <property name="backgroundColor">#FFFFFF</property>
                                    <cell id="398">
                                        <property name="colSpan">4</property>
                                        <property name="rowSpan">1</property>
                                        <data id="401">
                                            <property name="fontWeight">bold</property>
                                            <property name="color">#FF0000</property>
                                            <property name="resultSetColumn">UNIT_NAME</property>
                                        </data>
                                    </cell>
                                    <cell id="403"/>
                                    <cell id="404"/>
                                    <cell id="533"/>
                                    <cell id="405"/>
                                </row>
                            </header>
                            <footer>
                                <row id="409">
                                    <cell id="412">
                                        <label id="413">
                                            <property name="color">#FF0000</property>
                                            <property name="textAlign">right</property>
                                            <text-property name="text">Total #</text-property>
                                        </label>
                                    </cell>
                                    <cell id="414">
                                        <property name="colSpan">2</property>
                                        <property name="rowSpan">1</property>
                                        <data id="415">
                                            <property name="fontWeight">normal</property>
                                            <property name="color">#FF0000</property>
                                            <property name="resultSetColumn">UNIT_NAME</property>
                                        </data>
                                    </cell>
                                    <cell id="416">
                                        <data id="417">
                                            <property name="color">#FF0000</property>
                                            <property name="resultSetColumn">Aggregation_2</property>
                                        </data>
                                    </cell>
                                    <cell id="419">
                                        <property name="colSpan">1</property>
                                        <property name="rowSpan">1</property>
                                        <label id="420">
                                            <property name="color">#FF0000</property>
                                            <property name="textAlign">right</property>
                                            <text-property name="text">Total $s</text-property>
                                        </label>
                                    </cell>
                                    <cell id="542">
                                        <property name="colSpan">2</property>
                                        <property name="rowSpan">1</property>
                                        <data id="541">
                                            <property name="fontWeight">normal</property>
                                            <property name="color">#FF0000</property>
                                            <property name="resultSetColumn">UNIT_NAME</property>
                                        </data>
                                    </cell>
                                    <cell id="421">
                                        <data id="422">
                                            <property name="color">#FF0000</property>
                                            <structure name="numberFormat">
                                                <property name="category">Currency</property>
                                                <property name="pattern">$###0.00</property>
                                            </structure>
                                            <property name="resultSetColumn">Aggregation_1</property>
                                        </data>
                                    </cell>
                                </row>
                                <row id="423">
                                    <cell id="426"/>
                                    <cell id="427"/>
                                    <cell id="428"/>
                                    <cell id="429"/>
                                    <cell id="431"/>
                                    <cell id="432"/>
                                    <cell id="535"/>
                                    <cell id="433"/>
                                </row>
                            </footer>
                        </group>
                        <footer>
                            <row id="455">
                                <property name="height">0.23958333333333334in</property>
                                <cell id="456">
                                    <property name="colSpan">8</property>
                                    <property name="rowSpan">1</property>
                                    <label id="507">
                                        <text-property name="text">_________________________________________________________________________________________________________________________________________________</text-property>
                                    </label>
                                </cell>
                            </row>
                            <row id="497">
                                <property name="height">0.23958333333333334in</property>
                                <cell id="500"/>
                                <cell id="501"/>
                                <cell id="502"/>
                                <cell id="503"/>
                                <cell id="504"/>
                                <cell id="505"/>
                                <cell id="537"/>
                                <cell id="506"/>
                            </row>
                            <row id="518">
                                <property name="height">0.23958333333333334in</property>
                                <cell id="521"/>
                                <cell id="522"/>
                                <cell id="523"/>
                                <cell id="524">
                                    <property name="colSpan">2</property>
                                    <property name="rowSpan">1</property>
                                    <label id="528">
                                        <property name="fontWeight">bold</property>
                                        <property name="color">#FF0000</property>
                                        <text-property name="text">Total $s Requested:</text-property>
                                    </label>
                                </cell>
                                <cell id="526">
                                    <property name="colSpan">3</property>
                                    <property name="rowSpan">1</property>
                                    <data id="529">
                                        <property name="fontWeight">bold</property>
                                        <structure name="numberFormat">
                                            <property name="category">Currency</property>
                                            <property name="pattern">$###0.00</property>
                                        </structure>
                                        <property name="resultSetColumn">Aggregation_4</property>
                                    </data>
                                </cell>
                            </row>
                            <row id="508">
                                <property name="height">0.23958333333333334in</property>
                                <cell id="511"/>
                                <cell id="512"/>
                                <cell id="513"/>
                                <cell id="514">
                                    <property name="colSpan">2</property>
                                    <property name="rowSpan">1</property>
                                    <label id="530">
                                        <property name="fontWeight">bold</property>
                                        <property name="color">#FF0000</property>
                                        <text-property name="text">Total Proposals Submitted:</text-property>
                                    </label>
                                </cell>
                                <cell id="516">
                                    <property name="colSpan">3</property>
                                    <property name="rowSpan">1</property>
                                    <data id="531">
                                        <property name="fontWeight">bold</property>
                                        <property name="resultSetColumn">Aggregation_5</property>
                                    </data>
                                </cell>
                            </row>
                        </footer>
                    </table>
                </cell>
            </row>
        </grid>
    </body>
</report>
');
commit
/

DELIMITER ;
