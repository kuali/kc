TRUNCATE TABLE KREW_EDL_DEF_T DROP STORAGE
/
INSERT INTO KREW_EDL_DEF_T (ACTV_IND,EDOCLT_DEF_ID,NM,OBJ_ID,VER_NBR,XML)
  VALUES (1,2008,'eDoc.Example1.Form','6166CBA1BC0B644DE0404F8189D86C09',1,'<edl name="eDoc.Example1.Form" title="Example 1">
      <security/>
      <createInstructions>** Questions with an asterisk are required.</createInstructions>
      <instructions>** Questions with an asterisk are required.</instructions>
      <validations/>
      <attributes/>
      <fieldDef name="userName" title="Full Name">
        <display>
          <type>text</type>
          <meta>
            <name>size</name>
            <value>40</value>
          </meta>
        </display>
        <validation required="true">
          <message>Please enter your full name</message>
        </validation>
      </fieldDef>
      <fieldDef name="rqstDate" title="Requested Date of Implementation:">
        <display>
          <type>text</type>
        </display>
        <validation required="true">
          <regex>^[0-1]?[0-9](/|-)[0-3]?[0-9](/|-)[1-2][0-9][0-9][0-9]$</regex>
          <message>Enter a valid date in the format mm/dd/yyyy.</message>
        </validation>
      </fieldDef>
      <fieldDef name="requestType" title="Request Type:">
        <display>
          <type>radio</type>
          <values title="New">New</values>
          <values title="Modification">Modification</values>
        </display>
        <validation required="true">
          <message>Please select a request type.</message>
        </validation>
      </fieldDef>
      <fieldDef attributeName="EDL.Campus.Example" name="campus" title="Campus:">
        <display>
          <type>select</type>
          <values title="IUB">IUB</values>
          <values title="IUPUI">IUPUI</values>
        </display>
        <validation required="true">
          <message>Please select a campus.</message>
        </validation>
      </fieldDef>
      <fieldDef name="description" title="Description of Request:">
        <display>
          <type>textarea</type>
          <meta>
            <name>rows</name>
            <value>5</value>
          </meta>
          <meta>
            <name>cols</name>
            <value>60</value>
          </meta>
          <meta>
            <name>wrap</name>
            <value>hard</value>
          </meta>
        </display>
        <validation required="false"/>
      </fieldDef>
      <fieldDef name="fundedBy" title="My research/sponsored program work is funded by NIH or NSF.">
        <display>
          <type>checkbox</type>
          <values title="My research/sponsored program work is funded by NIH or NSF.">nihnsf</values>
        </display>
      </fieldDef>
      <fieldDef name="researchHumans" title="My research/sponsored program work involves human subjects.">
        <display>
          <type>checkbox</type>
          <values title="My research/sponsored program work involves human subjects.">humans</values>
        </display>
      </fieldDef>
    </edl>
')
/
