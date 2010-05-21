delimiter /
TRUNCATE TABLE KREW_RTE_NODE_CFG_PARM_T
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2012,2004,'<start name="placeholder"><activationType>S</activationType></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',2013,2004,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2014,2004,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2015,2006,'<start name="placeholder"><activationType>S</activationType></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',2016,2006,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2017,2006,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2118,2039,'<start name="Adhoc Routing"><activationType>S</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>true</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',2119,2039,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',2120,2039,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',2121,2039,'true')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2122,2039,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2123,2041,'<start name="Initiated"><activationType>S</activationType></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',2124,2041,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2125,2041,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2126,2042,'<requests name="ReviewersNode"><ruleTemplate>ReviewersRouting</ruleTemplate></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',2127,2042,'ReviewersRouting')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2128,2042,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2129,2043,'<requests name="RequestsNode"><ruleTemplate>NotificationRouting</ruleTemplate></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',2130,2043,'NotificationRouting')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2131,2043,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2147,2051,'<start name="Initiated"><activationType>P</activationType></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',2148,2051,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2149,2051,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2150,2052,'<requests name="DestinationApproval"><ruleTemplate>TravelRequest-DestinationRouting</ruleTemplate></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',2151,2052,'TravelRequest-DestinationRouting')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2152,2052,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2153,2053,'<requests name="TravelerApproval"><ruleTemplate>TravelRequest-TravelerRouting</ruleTemplate></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',2154,2053,'TravelRequest-TravelerRouting')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2155,2053,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2156,2054,'<requests name="SupervisorApproval"><ruleTemplate>TravelRequest-SupervisorRouting</ruleTemplate></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',2157,2054,'TravelRequest-SupervisorRouting')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2158,2054,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2159,2055,'<requests name="AccountApproval"><ruleTemplate>TravelRequest-AccountRouting</ruleTemplate></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',2160,2055,'TravelRequest-AccountRouting')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2161,2055,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2162,2057,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',2163,2057,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',2164,2057,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',2165,2057,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2166,2057,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2167,2059,'<start name="Initiated"><activationType>P</activationType></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',2168,2059,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2169,2059,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2170,2061,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',2171,2061,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',2172,2061,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',2173,2061,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2174,2061,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2175,2063,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',2176,2063,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',2177,2063,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',2178,2063,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2179,2063,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2180,2065,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',2181,2065,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',2182,2065,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',2183,2065,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2184,2065,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2185,2067,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',2186,2067,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',2187,2067,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',2188,2067,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2189,2067,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2200,2240,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',2201,2240,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',2202,2240,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',2203,2240,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2204,2240,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2205,2241,'<requests name="eDoc.Example1.Node1"><activationType>P</activationType><ruleTemplate>eDoc.Example1.Node1</ruleTemplate><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',2206,2241,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',2207,2241,'eDoc.Example1.Node1')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',2208,2241,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',2209,2241,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2210,2241,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2211,2259,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',2212,2259,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',2213,2259,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',2214,2259,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2215,2259,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2216,2260,'<requests name="eDoc.Example1.Node1"><activationType>P</activationType><ruleTemplate>eDoc.Example1.Node1</ruleTemplate><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',2217,2260,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',2218,2260,'eDoc.Example1.Node1')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',2219,2260,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',2220,2260,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2221,2260,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2240,2340,'<start name="AdHoc"><activationType>P</activationType></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',2241,2340,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2242,2340,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2243,2344,'<start name="AdHoc"><activationType>P</activationType></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',2244,2344,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2245,2344,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2260,2380,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',2261,2380,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',2262,2380,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',2263,2380,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2264,2380,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2265,2381,'<requests name="eDoc.Example1.Node1"><activationType>P</activationType><ruleTemplate>eDoc.Example1.Node1</ruleTemplate><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',2266,2381,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',2267,2381,'eDoc.Example1.Node1')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',2268,2381,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',2269,2381,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2270,2381,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2360,2580,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',2361,2580,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',2362,2580,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',2363,2580,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2364,2580,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2365,2581,'<requests name="eDoc.Example1.Node1"><activationType>P</activationType><ruleTemplate>eDoc.Example1.Node1</ruleTemplate><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',2366,2581,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',2367,2581,'eDoc.Example1.Node1')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',2368,2581,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',2369,2581,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2370,2581,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2380,2840,'<start name="PreRoute"><activationType>S</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',2381,2840,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',2382,2840,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',2383,2840,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2384,2840,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2397,2846,'<start name="PreRoute"><activationType>P</activationType></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',2398,2846,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2399,2846,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2400,2847,'<requests name="DestinationApproval"><ruleTemplate>TravelRequest-DestinationRouting</ruleTemplate></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',2401,2847,'TravelRequest-DestinationRouting')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2402,2847,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2403,2848,'<requests name="TravelerApproval"><ruleTemplate>TravelRequest-TravelerRouting</ruleTemplate></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',2404,2848,'TravelRequest-TravelerRouting')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2405,2848,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2406,2849,'<requests name="SupervisorApproval"><ruleTemplate>TravelRequest-SupervisorRouting</ruleTemplate></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',2407,2849,'TravelRequest-SupervisorRouting')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2408,2849,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',2409,2850,'<requests name="AccountApproval"><ruleTemplate>TravelRequest-AccountRouting</ruleTemplate></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',2410,2850,'TravelRequest-AccountRouting')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',2411,2850,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3287,3233,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3288,3233,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3289,3233,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3290,3233,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3291,3233,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3292,3235,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3293,3235,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3294,3235,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3295,3235,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3296,3235,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3297,3237,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3298,3237,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3299,3237,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3300,3237,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3301,3237,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3302,3239,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3303,3239,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3304,3239,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3305,3239,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3306,3239,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3307,3241,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3308,3241,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3309,3241,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3310,3241,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3311,3241,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3312,3242,'<requests name="OSPInitial"><activationType>S</activationType><ruleTemplate>OSPInitialApproval</ruleTemplate><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3313,3242,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',3314,3242,'OSPInitialApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3315,3242,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3316,3242,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3317,3242,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3318,3243,'<requests name="ProposalPersons"><activationType>P</activationType><ruleTemplate>ProposalPersonsApproval</ruleTemplate><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3319,3243,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',3320,3243,'ProposalPersonsApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3321,3243,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3322,3243,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3323,3243,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3324,3244,'<requests name="UnitRouting"><activationType>P</activationType><ruleTemplate>CustomApproval</ruleTemplate><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3325,3244,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',3326,3244,'CustomApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3327,3244,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3328,3244,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3329,3244,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3330,3245,'<requests name="DepartmentalRouting"><activationType>P</activationType><ruleTemplate>DepartmentalApproval</ruleTemplate><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3331,3245,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',3332,3245,'DepartmentalApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3333,3245,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3334,3245,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3335,3245,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3336,3246,'<requests name="OSPOfficeRouting"><activationType>S</activationType><ruleTemplate>OSPOfficeApproval</ruleTemplate><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3337,3246,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',3338,3246,'OSPOfficeApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3339,3246,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3340,3246,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3341,3246,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3872,3460,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3873,3460,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3874,3460,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3875,3460,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3876,3460,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3877,3462,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3878,3462,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3879,3462,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3880,3462,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3881,3462,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3882,3464,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3883,3464,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3884,3464,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3885,3464,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3886,3464,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3887,3466,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3888,3466,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3889,3466,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3890,3466,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3891,3466,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3892,3468,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3893,3468,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3894,3468,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3895,3468,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3896,3468,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3897,3470,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3898,3470,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3899,3470,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3900,3470,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3901,3470,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3902,3472,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3903,3472,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3904,3472,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3905,3472,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3906,3472,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3907,3474,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3908,3474,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3909,3474,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3910,3474,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3911,3474,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3912,3476,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3913,3476,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3914,3476,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3915,3476,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3916,3476,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3917,3478,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3918,3478,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3919,3478,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3920,3478,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3921,3478,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3922,3480,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3923,3480,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3924,3480,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3925,3480,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3926,3480,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3927,3482,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3928,3482,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3929,3482,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3930,3482,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3931,3482,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3932,3484,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3933,3484,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3934,3484,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3935,3484,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3936,3484,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3937,3486,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3938,3486,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3939,3486,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3940,3486,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3941,3486,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3942,3488,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3943,3488,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3944,3488,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3945,3488,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3946,3488,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3947,3490,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3948,3490,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3949,3490,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3950,3490,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3951,3490,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3952,3492,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3953,3492,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3954,3492,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3955,3492,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3956,3492,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3957,3494,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3958,3494,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3959,3494,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3960,3494,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3961,3494,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3962,3496,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3963,3496,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3964,3496,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3965,3496,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3966,3496,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3967,3498,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3968,3498,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3969,3498,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3970,3498,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3971,3498,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3972,3500,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3973,3500,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3974,3500,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3975,3500,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3976,3500,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3977,3502,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3978,3502,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3979,3502,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3980,3502,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3981,3502,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3982,3504,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3983,3504,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3984,3504,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3985,3504,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3986,3504,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3987,3506,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3988,3506,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3989,3506,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3990,3506,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3991,3506,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3992,3508,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3993,3508,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3994,3508,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',3995,3508,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',3996,3508,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',3997,3510,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',3998,3510,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',3999,3510,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4000,3510,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4001,3510,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4002,3512,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4003,3512,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4004,3512,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4005,3512,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4006,3512,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4007,3514,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4008,3514,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4009,3514,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4010,3514,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4011,3514,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4012,3516,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4013,3516,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4014,3516,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4015,3516,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4016,3516,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4017,3518,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4018,3518,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4019,3518,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4020,3518,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4021,3518,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4022,3520,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4023,3520,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4024,3520,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4025,3520,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4026,3520,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4027,3522,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4028,3522,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4029,3522,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4030,3522,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4031,3522,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4032,3524,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4033,3524,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4034,3524,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4035,3524,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4036,3524,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4037,3526,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4038,3526,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4039,3526,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4040,3526,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4041,3526,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4042,3528,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4043,3528,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4044,3528,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4045,3528,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4046,3528,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4047,3530,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4048,3530,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4049,3530,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4050,3530,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4051,3530,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4052,3532,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4053,3532,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4054,3532,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4055,3532,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4056,3532,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4057,3534,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4058,3534,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4059,3534,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4060,3534,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4061,3534,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4062,3536,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4063,3536,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4064,3536,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4065,3536,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4066,3536,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4067,3538,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4068,3538,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4069,3538,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4070,3538,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4071,3538,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4072,3540,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4073,3540,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4074,3540,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4075,3540,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4076,3540,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4077,3542,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4078,3542,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4079,3542,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4080,3542,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4081,3542,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4082,3544,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4083,3544,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4084,3544,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4085,3544,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4086,3544,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4087,3546,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4088,3546,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4089,3546,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4090,3546,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4091,3546,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4092,3548,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4093,3548,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4094,3548,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4095,3548,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4096,3548,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4097,3550,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4098,3550,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4099,3550,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4100,3550,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4101,3550,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4102,3552,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4103,3552,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4104,3552,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4105,3552,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4106,3552,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4107,3554,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4108,3554,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4109,3554,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4110,3554,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4111,3554,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4112,3556,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4113,3556,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4114,3556,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4115,3556,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4116,3556,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4117,3558,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4118,3558,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4119,3558,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4120,3558,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4121,3558,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4122,3560,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4123,3560,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4124,3560,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4125,3560,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4126,3560,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4127,3562,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4128,3562,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4129,3562,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4130,3562,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4131,3562,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4132,3564,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4133,3564,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4134,3564,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4135,3564,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4136,3564,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4137,3566,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4138,3566,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4139,3566,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4140,3566,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4141,3566,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4142,3568,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4143,3568,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4144,3568,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4145,3568,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4146,3568,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4147,3570,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4148,3570,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4149,3570,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4150,3570,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4151,3570,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4152,3572,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4153,3572,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4154,3572,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4155,3572,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4156,3572,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4157,3574,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4158,3574,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4159,3574,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4160,3574,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4161,3574,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4162,3576,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4163,3576,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4164,3576,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4165,3576,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4166,3576,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4167,3578,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4168,3578,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4169,3578,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4170,3578,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4171,3578,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4172,3580,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4173,3580,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4174,3580,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4175,3580,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4176,3580,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4177,3582,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4178,3582,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4179,3582,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4180,3582,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4181,3582,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4182,3584,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4183,3584,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4184,3584,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4185,3584,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4186,3584,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4187,3586,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4188,3586,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4189,3586,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4190,3586,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4191,3586,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4192,3588,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4193,3588,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4194,3588,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4195,3588,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4196,3588,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4197,3590,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4198,3590,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4199,3590,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4200,3590,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4201,3590,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4202,3592,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4203,3592,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4204,3592,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4205,3592,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4206,3592,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4207,3594,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4208,3594,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4209,3594,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4210,3594,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4211,3594,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4212,3596,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4213,3596,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4214,3596,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4215,3596,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4216,3596,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4217,3598,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4218,3598,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4219,3598,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4220,3598,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4221,3598,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4222,3600,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4223,3600,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4224,3600,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4225,3600,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4226,3600,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4227,3602,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4228,3602,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4229,3602,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4230,3602,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4231,3602,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4232,3604,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4233,3604,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4234,3604,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4235,3604,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4236,3604,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4237,3606,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4238,3606,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4239,3606,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4240,3606,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4241,3606,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4242,3608,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4243,3608,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4244,3608,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4245,3608,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4246,3608,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4247,3610,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4248,3610,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4249,3610,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4250,3610,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4251,3610,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4252,3612,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4253,3612,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4254,3612,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4255,3612,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4256,3612,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4257,3614,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4258,3614,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4259,3614,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4260,3614,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4261,3614,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4262,3616,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4263,3616,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4264,3616,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4265,3616,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4266,3616,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4267,3618,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4268,3618,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4269,3618,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4270,3618,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4271,3618,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4272,3620,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4273,3620,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4274,3620,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4275,3620,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4276,3620,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4277,3622,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4278,3622,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4279,3622,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4280,3622,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4281,3622,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4282,3624,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4283,3624,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4284,3624,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4285,3624,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4286,3624,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4287,3626,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4288,3626,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4289,3626,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4290,3626,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4291,3626,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4292,3628,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4293,3628,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4294,3628,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4295,3628,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4296,3628,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4297,3630,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4298,3630,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4299,3630,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4300,3630,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4301,3630,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4302,3632,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4303,3632,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4304,3632,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4305,3632,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4306,3632,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4307,3634,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4308,3634,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4309,3634,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4310,3634,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4311,3634,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4312,3636,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4313,3636,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4314,3636,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4315,3636,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4316,3636,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4317,3638,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4318,3638,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4319,3638,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4320,3638,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4321,3638,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4322,3640,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4323,3640,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4324,3640,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4325,3640,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4326,3640,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4327,3642,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4328,3642,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4329,3642,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4330,3642,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4331,3642,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4332,3644,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4333,3644,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4334,3644,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4335,3644,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4336,3644,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4337,3646,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4338,3646,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4339,3646,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4340,3646,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4341,3646,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4342,3648,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4343,3648,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4344,3648,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4345,3648,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4346,3648,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4347,3650,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4348,3650,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4349,3650,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4350,3650,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4351,3650,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4352,3652,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4353,3652,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4354,3652,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4355,3652,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4356,3652,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4357,3654,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4358,3654,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4359,3654,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4360,3654,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4361,3654,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4362,3656,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4363,3656,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4364,3656,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4365,3656,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4366,3656,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4367,3658,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4368,3658,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4369,3658,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4370,3658,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4371,3658,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4372,3660,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4373,3660,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4374,3660,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4375,3660,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4376,3660,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4377,3662,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4378,3662,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4379,3662,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4380,3662,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4381,3662,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4382,3664,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4383,3664,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4384,3664,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4385,3664,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4386,3664,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4387,3668,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4388,3668,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4389,3668,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4390,3668,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4391,3668,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4392,3670,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4393,3670,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4394,3670,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4395,3670,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4396,3670,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4397,3672,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4398,3672,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4399,3672,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4400,3672,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4401,3672,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4402,3674,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4403,3674,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4404,3674,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4405,3674,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4406,3674,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4407,3676,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4408,3676,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4409,3676,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4410,3676,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4411,3676,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4412,3678,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4413,3678,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4414,3678,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4415,3678,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4416,3678,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4417,3680,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4418,3680,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4419,3680,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4420,3680,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4421,3680,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4422,3682,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4423,3682,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4424,3682,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4425,3682,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4426,3682,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4427,3684,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4428,3684,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4429,3684,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4430,3684,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4431,3684,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4432,3686,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4433,3686,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4434,3686,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4435,3686,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4436,3686,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4437,3688,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4438,3688,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4439,3688,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4440,3688,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4441,3688,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4442,3690,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4443,3690,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4444,3690,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4445,3690,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4446,3690,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4447,3692,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4448,3692,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4449,3692,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4450,3692,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4451,3692,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4452,3694,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4453,3694,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4454,3694,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4455,3694,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4456,3694,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4457,3696,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4458,3696,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4459,3696,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4460,3696,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4461,3696,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4462,3698,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4463,3698,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4464,3698,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4465,3698,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4466,3698,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4467,3700,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4468,3700,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4469,3700,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4470,3700,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4471,3700,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4472,3702,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4473,3702,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4474,3702,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4475,3702,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4476,3702,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4477,3704,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4478,3704,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4479,3704,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4480,3704,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4481,3704,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4482,3706,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4483,3706,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4484,3706,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4485,3706,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4486,3706,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4487,3708,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4488,3708,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4489,3708,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4490,3708,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4491,3708,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4492,3710,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4493,3710,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4494,3710,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4495,3710,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4496,3710,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4497,3712,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4498,3712,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4499,3712,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4500,3712,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4501,3712,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4502,3714,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4503,3714,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4504,3714,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4505,3714,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4506,3714,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4507,3716,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4508,3716,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4509,3716,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4510,3716,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4511,3716,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4512,3717,'<requests name="OSPInitial"><activationType>S</activationType><ruleTemplate>OSPInitialApproval</ruleTemplate><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4513,3717,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4514,3717,'OSPInitialApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4515,3717,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4516,3717,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4517,3717,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4518,3718,'<requests name="ProposalPersons"><activationType>P</activationType><ruleTemplate>ProposalPersonsApproval</ruleTemplate><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4519,3718,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4520,3718,'ProposalPersonsApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4521,3718,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4522,3718,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4523,3718,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4524,3719,'<requests name="UnitRouting"><activationType>P</activationType><ruleTemplate>CustomApproval</ruleTemplate><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4525,3719,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4526,3719,'CustomApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4527,3719,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4528,3719,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4529,3719,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4530,3720,'<requests name="DepartmentalRouting"><activationType>P</activationType><ruleTemplate>DepartmentalApproval</ruleTemplate><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4531,3720,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4532,3720,'DepartmentalApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4533,3720,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4534,3720,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4535,3720,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4536,3721,'<requests name="OSPOfficeRouting"><activationType>S</activationType><ruleTemplate>OSPOfficeApproval</ruleTemplate><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4537,3721,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4538,3721,'OSPOfficeApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4539,3721,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4540,3721,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4541,3721,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4542,3723,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4543,3723,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4544,3723,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4545,3723,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4546,3723,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4547,3725,'<start name="Initiated"><activationType>S</activationType></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4548,3725,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4549,3725,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4550,3726,'<requests name="IRBReceipt"><activationType>S</activationType><ruleTemplate>IRBReceipt</ruleTemplate></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4551,3726,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4552,3726,'IRBReceipt')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4553,3726,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4554,3727,'<requests name="IRBApproved"><activationType>S</activationType><ruleTemplate>IRBApproved</ruleTemplate></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4555,3727,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4556,3727,'IRBApproved')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4557,3727,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4558,3728,'<requests name="AssignedToCommittee"><activationType>S</activationType><ruleTemplate>AssignedToCommittee</ruleTemplate></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4559,3728,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4560,3728,'AssignedToCommittee')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4561,3728,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4562,3729,'<requests name="AssignedToAgenda"><activationType>S</activationType><ruleTemplate>AssignedToAgenda</ruleTemplate></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4563,3729,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4564,3729,'AssignedToAgenda')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4565,3729,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4566,3730,'<requests name="IRBReviewComplete"><activationType>S</activationType><ruleTemplate>IRBReviewComplete</ruleTemplate></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4567,3730,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4568,3730,'IRBReviewComplete')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4569,3730,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4570,3732,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4571,3732,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4572,3732,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4573,3732,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4574,3732,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4575,3733,'<requests name="OSPInitial"><activationType>S</activationType><ruleTemplate>OSPInitialApproval</ruleTemplate><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4576,3733,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4577,3733,'OSPInitialApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4578,3733,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4579,3733,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4580,3733,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4581,3734,'<requests name="ProposalPersons"><activationType>P</activationType><ruleTemplate>ProposalPersonsApproval</ruleTemplate><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4582,3734,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4583,3734,'ProposalPersonsApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4584,3734,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4585,3734,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4586,3734,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4587,3735,'<requests name="UnitRouting"><activationType>P</activationType><ruleTemplate>CustomApproval</ruleTemplate><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4588,3735,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4589,3735,'CustomApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4590,3735,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4591,3735,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4592,3735,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4593,3736,'<requests name="DepartmentalRouting"><activationType>P</activationType><ruleTemplate>DepartmentalApproval</ruleTemplate><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4594,3736,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4595,3736,'DepartmentalApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4596,3736,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4597,3736,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4598,3736,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4599,3737,'<requests name="OSPOfficeRouting"><activationType>S</activationType><ruleTemplate>OSPOfficeApproval</ruleTemplate><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4600,3737,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4601,3737,'OSPOfficeApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4602,3737,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4603,3737,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4604,3737,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4605,3739,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4606,3739,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4607,3739,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4608,3739,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4609,3739,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4610,3741,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4611,3741,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4612,3741,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4613,3741,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4614,3741,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4615,3743,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4616,3743,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4617,3743,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4618,3743,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4619,3743,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4620,3745,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4621,3745,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4622,3745,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4623,3745,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4624,3745,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4625,3747,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4626,3747,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4627,3747,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4628,3747,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4629,3747,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4630,3749,'<start name="Initiated"><activationType>S</activationType></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4631,3749,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4632,3749,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4633,3750,'<requests name="IRBReceipt"><activationType>S</activationType><ruleTemplate>IRBReceipt</ruleTemplate></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4634,3750,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4635,3750,'IRBReceipt')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4636,3750,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4637,3751,'<requests name="IRBApproved"><activationType>S</activationType><ruleTemplate>IRBApproved</ruleTemplate></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4638,3751,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4639,3751,'IRBApproved')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4640,3751,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4641,3752,'<requests name="AssignedToCommittee"><activationType>S</activationType><ruleTemplate>AssignedToCommittee</ruleTemplate></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4642,3752,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4643,3752,'AssignedToCommittee')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4644,3752,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4645,3753,'<requests name="AssignedToAgenda"><activationType>S</activationType><ruleTemplate>AssignedToAgenda</ruleTemplate></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4646,3753,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4647,3753,'AssignedToAgenda')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4648,3753,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4649,3754,'<requests name="IRBReviewComplete"><activationType>S</activationType><ruleTemplate>IRBReviewComplete</ruleTemplate></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4650,3754,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4651,3754,'IRBReviewComplete')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4652,3754,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4653,3756,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4654,3756,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4655,3756,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4656,3756,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4657,3756,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4658,3758,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4659,3758,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4660,3758,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4661,3758,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4662,3758,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4663,3760,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4664,3760,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4665,3760,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4666,3760,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4667,3760,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4668,3762,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4669,3762,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4670,3762,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4671,3762,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4672,3762,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4699,3778,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4700,3778,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4701,3778,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4702,3778,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4703,3778,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4704,3780,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4705,3780,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4706,3780,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4707,3780,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4708,3780,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4709,3782,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4710,3782,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4711,3782,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4712,3782,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4713,3782,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4714,3784,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4715,3784,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4716,3784,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4717,3784,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4718,3784,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4719,3786,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4720,3786,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4721,3786,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4722,3786,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4723,3786,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4724,3788,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4725,3788,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4726,3788,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4727,3788,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4728,3788,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4729,3790,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4730,3790,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4731,3790,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4732,3790,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4733,3790,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4734,3792,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4735,3792,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4736,3792,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4737,3792,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4738,3792,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4739,3794,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4740,3794,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4741,3794,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4742,3794,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4743,3794,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4744,3796,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4745,3796,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4746,3796,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4747,3796,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4748,3796,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4749,3798,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4750,3798,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4751,3798,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4752,3798,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4753,3798,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4754,3800,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4755,3800,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4756,3800,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4757,3800,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4758,3800,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4759,3802,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4760,3802,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4761,3802,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4762,3802,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4763,3802,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4764,3804,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4765,3804,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4766,3804,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4767,3804,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4768,3804,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4769,3805,'<role name="OSPInitial"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',4770,3805,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4771,3805,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4772,3805,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4773,3805,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4774,3806,'<role name="ProposalPersons"><qualifierResolver>ProposalPersons-XPathQualifierResolver</qualifierResolver><activationType>P</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolver',4775,3806,'ProposalPersons-XPathQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4776,3806,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4777,3806,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4778,3806,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4779,3807,'<role name="UnitRouting"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><ruleTemplate>CustomApproval</ruleTemplate><activationType>P</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',4780,3807,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4781,3807,'CustomApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4782,3807,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4783,3807,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4784,3807,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4785,3808,'<role name="DepartmentalRouting"><qualifierResolver>DepartmentRouting-XPathQualifierResolver</qualifierResolver><ruleTemplate>DepartmentalApproval</ruleTemplate><activationType>P</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolver',4786,3808,'DepartmentRouting-XPathQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4787,3808,'DepartmentalApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4788,3808,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4789,3808,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4790,3808,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4791,3809,'<role name="OSPOfficeRouting"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',4792,3809,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4793,3809,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4794,3809,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4795,3809,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4796,3811,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4797,3811,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4798,3811,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4799,3811,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4800,3811,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4801,3813,'<start name="Initiated"><activationType>S</activationType></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4802,3813,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4803,3813,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4804,3814,'<role name="IRBApprove"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><requests name="IRBReceipt"><activationType>S</activationType><ruleTemplate>IRBReceipt</ruleTemplate></requests><requests name="IRBApproved"><activationType>S</activationType><ruleTemplate>IRBApproved</ruleTemplate></requests><requests name="AssignedToCommittee"><activationType>S</activationType><ruleTemplate>IRBApproved</ruleTemplate></requests></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',4805,3814,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID)
  VALUES ('requests',4806,3814)
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID)
  VALUES ('requests',4807,3814)
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID)
  VALUES ('requests',4808,3814)
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4809,3814,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4810,3815,'<requests name="AssignedToCommittee"><activationType>S</activationType><ruleTemplate>IRBApproved</ruleTemplate></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4811,3815,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4812,3815,'IRBApproved')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4813,3815,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4814,3816,'<role name="AssignedToAgenda"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><ruleTemplate>IRBApproved</ruleTemplate></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',4815,3816,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4816,3816,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4817,3816,'IRBApproved')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4818,3816,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4819,3817,'<role name="IRBReview"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><ruleTemplate>IRBApproved</ruleTemplate></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',4820,3817,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4821,3817,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4822,3817,'IRBApproved')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4823,3817,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4824,3819,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4825,3819,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4826,3819,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4827,3819,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4828,3819,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4829,3821,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4830,3821,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4831,3821,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4832,3821,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4833,3821,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4834,3822,'<role name="OSPInitial"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',4835,3822,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4836,3822,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4837,3822,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4838,3822,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4839,3823,'<role name="ProposalPersons"><qualifierResolver>ProposalPersons-XPathQualifierResolver</qualifierResolver><activationType>P</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolver',4840,3823,'ProposalPersons-XPathQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4841,3823,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4842,3823,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4843,3823,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4844,3824,'<role name="UnitRouting"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><ruleTemplate>CustomApproval</ruleTemplate><activationType>P</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',4845,3824,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4846,3824,'CustomApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4847,3824,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4848,3824,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4849,3824,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4850,3825,'<role name="DepartmentalRouting"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><ruleTemplate>DepartmentalApproval</ruleTemplate><activationType>P</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',4851,3825,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4852,3825,'DepartmentalApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4853,3825,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4854,3825,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4855,3825,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4856,3826,'<role name="OSPOfficeRouting"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',4857,3826,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4858,3826,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4859,3826,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4860,3826,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4861,3828,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4862,3828,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4863,3828,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4864,3828,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4865,3828,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4866,3830,'<start name="AdHoc"/>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4867,3830,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4868,3832,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4869,3832,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4870,3832,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4871,3832,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4872,3832,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4873,3833,'<split name="isHierarchyChild"><type>org.kuali.kra.kew.SimpleBooleanSplitNode</type></split>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('type',4874,3833,'org.kuali.kra.kew.SimpleBooleanSplitNode')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4875,3833,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4876,3835,'<role name="OSPInitial"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',4877,3835,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4878,3835,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4879,3835,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4880,3835,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4881,3836,'<role name="ProposalPersons"><qualifierResolver>ProposalPersons-XPathQualifierResolver</qualifierResolver><activationType>P</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolver',4882,3836,'ProposalPersons-XPathQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4883,3836,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4884,3836,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4885,3836,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4886,3837,'<role name="UnitRouting"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><ruleTemplate>CustomApproval</ruleTemplate><activationType>P</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',4887,3837,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4888,3837,'CustomApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4889,3837,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4890,3837,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4891,3837,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4892,3838,'<role name="DepartmentalRouting"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><ruleTemplate>DepartmentalApproval</ruleTemplate><activationType>P</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',4893,3838,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4894,3838,'DepartmentalApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4895,3838,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4896,3838,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4897,3838,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4898,3839,'<role name="OSPOfficeRouting"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',4899,3839,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4900,3839,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4901,3839,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4902,3839,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4903,3840,'<join name="Join"/>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4904,3840,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4905,3842,'<requests name="WaitForHierarchyDisposition"><activationType>S</activationType><ruleTemplate>HierarchyParentDispositionApproval</ruleTemplate><mandatoryRoute>true</mandatoryRoute><ignorePrevious>true</ignorePrevious><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4906,3842,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',4907,3842,'HierarchyParentDispositionApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4908,3842,'true')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ignorePrevious',4909,3842,'true')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4910,3842,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4911,3842,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4912,3844,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4913,3844,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4914,3844,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4915,3844,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4916,3844,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4917,3846,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4918,3846,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4919,3846,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4920,3846,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4921,3846,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4932,3852,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4933,3852,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4934,3852,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4935,3852,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4936,3852,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4937,3854,'<start name="AdHoc"/>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4938,3854,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4939,3856,'<start name="AdHoc"/>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4940,3856,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',4941,3858,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',4942,3858,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',4943,3858,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',4944,3858,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',4945,3858,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5034,3884,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5035,3884,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5036,3884,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5037,3884,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5038,3884,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5039,3886,'<start name="Initiated"><activationType>S</activationType></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5040,3886,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5041,3886,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5042,3887,'<role name="IRBApprove"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><requests name="IRBReceipt"><activationType>S</activationType><ruleTemplate>IRBReceipt</ruleTemplate></requests><requests name="IRBApproved"><activationType>S</activationType><ruleTemplate>IRBApproved</ruleTemplate></requests><requests name="AssignedToCommittee"><activationType>S</activationType><ruleTemplate>IRBApproved</ruleTemplate></requests></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5043,3887,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID)
  VALUES ('requests',5044,3887)
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID)
  VALUES ('requests',5045,3887)
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID)
  VALUES ('requests',5046,3887)
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5047,3887,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5048,3888,'<requests name="AssignedToCommittee"><activationType>S</activationType><ruleTemplate>IRBApproved</ruleTemplate></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5049,3888,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5050,3888,'IRBApproved')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5051,3888,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5052,3889,'<role name="AssignedToAgenda"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><ruleTemplate>IRBApproved</ruleTemplate></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5053,3889,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5054,3889,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5055,3889,'IRBApproved')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5056,3889,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5057,3890,'<role name="IRBReview"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><ruleTemplate>IRBApproved</ruleTemplate></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5058,3890,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5059,3890,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5060,3890,'IRBApproved')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5061,3890,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5062,3892,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5063,3892,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5064,3892,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5065,3892,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5066,3892,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5067,3894,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5068,3894,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5069,3894,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5070,3894,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5071,3894,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5072,3896,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5073,3896,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5074,3896,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5075,3896,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5076,3896,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5077,3897,'<split name="isHierarchyChild"><type>org.kuali.kra.kew.SimpleBooleanSplitNode</type></split>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('type',5078,3897,'org.kuali.kra.kew.SimpleBooleanSplitNode')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5079,3897,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5080,3899,'<role name="OSPInitial"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5081,3899,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5082,3899,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5083,3899,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5084,3899,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5085,3900,'<role name="ProposalPersons"><qualifierResolver>ProposalPersons-XPathQualifierResolver</qualifierResolver><activationType>P</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolver',5086,3900,'ProposalPersons-XPathQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5087,3900,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5088,3900,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5089,3900,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5090,3901,'<role name="UnitRouting"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><ruleTemplate>CustomApproval</ruleTemplate><activationType>P</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5091,3901,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5092,3901,'CustomApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5093,3901,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5094,3901,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5095,3901,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5096,3902,'<role name="DepartmentalRouting"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><ruleTemplate>DepartmentalApproval</ruleTemplate><activationType>P</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5097,3902,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5098,3902,'DepartmentalApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5099,3902,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5100,3902,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5101,3902,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5102,3903,'<role name="OSPOfficeRouting"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5103,3903,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5104,3903,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5105,3903,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5106,3903,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5107,3904,'<join name="Join"/>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5108,3904,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5109,3906,'<requests name="WaitForHierarchyDisposition"><activationType>S</activationType><ruleTemplate>HierarchyParentDispositionApproval</ruleTemplate><mandatoryRoute>true</mandatoryRoute><ignorePrevious>true</ignorePrevious><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5110,3906,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5111,3906,'HierarchyParentDispositionApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5112,3906,'true')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ignorePrevious',5113,3906,'true')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5114,3906,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5115,3906,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5116,3908,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5117,3908,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5118,3908,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5119,3908,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5120,3908,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5121,3910,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5122,3910,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5123,3910,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5124,3910,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5125,3910,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5126,3912,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5127,3912,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5128,3912,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5129,3912,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5130,3912,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5131,3914,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5132,3914,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5133,3914,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5134,3914,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5135,3914,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5136,3916,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5137,3916,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5138,3916,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5139,3916,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5140,3916,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5141,3918,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5142,3918,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5143,3918,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5144,3918,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5145,3918,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5146,3920,'<start name="Initiated"><activationType>S</activationType></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5147,3920,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5148,3920,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5149,3921,'<role name="IRBApprove"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><requests name="IRBReceipt"><activationType>S</activationType><ruleTemplate>IRBReceipt</ruleTemplate></requests><requests name="IRBApproved"><activationType>S</activationType><ruleTemplate>IRBApproved</ruleTemplate></requests><requests name="AssignedToCommittee"><activationType>S</activationType><ruleTemplate>IRBApproved</ruleTemplate></requests></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5150,3921,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID)
  VALUES ('requests',5151,3921)
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID)
  VALUES ('requests',5152,3921)
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID)
  VALUES ('requests',5153,3921)
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5154,3921,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5155,3922,'<requests name="AssignedToCommittee"><activationType>S</activationType><ruleTemplate>IRBApproved</ruleTemplate></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5156,3922,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5157,3922,'IRBApproved')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5158,3922,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5159,3923,'<role name="AssignedToAgenda"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><ruleTemplate>IRBApproved</ruleTemplate></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5160,3923,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5161,3923,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5162,3923,'IRBApproved')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5163,3923,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5164,3924,'<role name="IRBReview"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><ruleTemplate>IRBApproved</ruleTemplate></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5165,3924,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5166,3924,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5167,3924,'IRBApproved')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5168,3924,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5226,3942,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5227,3942,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5228,3942,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5229,3942,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5230,3942,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5231,3943,'<split name="isHierarchyChild"><type>org.kuali.kra.kew.SimpleBooleanSplitNode</type></split>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('type',5232,3943,'org.kuali.kra.kew.SimpleBooleanSplitNode')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5233,3943,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5234,3945,'<role name="OSPInitial"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5235,3945,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5236,3945,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5237,3945,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5238,3945,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5239,3946,'<role name="ProposalPersons"><qualifierResolver>ProposalPersons-XPathQualifierResolver</qualifierResolver><activationType>P</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolver',5240,3946,'ProposalPersons-XPathQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5241,3946,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5242,3946,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5243,3946,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5244,3947,'<role name="UnitRouting"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><ruleTemplate>CustomApproval</ruleTemplate><activationType>P</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5245,3947,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5246,3947,'CustomApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5247,3947,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5248,3947,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5249,3947,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5250,3948,'<role name="DepartmentalRouting"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><ruleTemplate>DepartmentalApproval</ruleTemplate><activationType>P</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5251,3948,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5252,3948,'DepartmentalApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5253,3948,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5254,3948,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5255,3948,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5256,3949,'<role name="OSPOfficeRouting"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5257,3949,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5258,3949,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5259,3949,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5260,3949,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5261,3950,'<join name="Join"/>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5262,3950,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5263,3952,'<requests name="WaitForHierarchyDisposition"><activationType>S</activationType><ruleTemplate>HierarchyParentDispositionApproval</ruleTemplate><mandatoryRoute>true</mandatoryRoute><ignorePrevious>true</ignorePrevious><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5264,3952,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5265,3952,'HierarchyParentDispositionApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5266,3952,'true')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ignorePrevious',5267,3952,'true')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5268,3952,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5269,3952,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5270,3954,'<start name="Initiated"><activationType>S</activationType></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5271,3954,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5272,3954,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5273,3955,'<role name="AssignedToAgenda"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><ruleTemplate>AssignedToAgenda</ruleTemplate></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5274,3955,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5275,3955,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5276,3955,'AssignedToAgenda')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5277,3955,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5278,3956,'<role name="IRBReview"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><ruleTemplate>IRBReview</ruleTemplate></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5279,3956,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5280,3956,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5281,3956,'IRBReview')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5282,3956,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5302,3970,'<start name="Initiated"><activationType>S</activationType></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5303,3970,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5304,3970,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5305,3971,'<role name="AssignedToAgenda"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><ruleTemplate>AssignedToAgenda</ruleTemplate></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5306,3971,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5307,3971,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5308,3971,'AssignedToAgenda')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5309,3971,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5310,3972,'<role name="IRBReview"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><ruleTemplate>IRBReview</ruleTemplate></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5311,3972,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5312,3972,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5313,3972,'IRBReview')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5314,3972,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5315,3974,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5316,3974,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5317,3974,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5318,3974,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5319,3974,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5320,3975,'<split name="isHierarchyChild"><type>org.kuali.kra.kew.SimpleBooleanSplitNode</type></split>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('type',5321,3975,'org.kuali.kra.kew.SimpleBooleanSplitNode')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5322,3975,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5323,3977,'<role name="OSPInitial"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5324,3977,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5325,3977,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5326,3977,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5327,3977,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5328,3978,'<role name="ProposalPersons"><qualifierResolver>ProposalPersons-XPathQualifierResolver</qualifierResolver><activationType>P</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolver',5329,3978,'ProposalPersons-XPathQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5330,3978,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5331,3978,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5332,3978,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5333,3979,'<role name="UnitRouting"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><ruleTemplate>CustomApproval</ruleTemplate><activationType>P</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5334,3979,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5335,3979,'CustomApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5336,3979,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5337,3979,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5338,3979,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5339,3980,'<role name="DepartmentalRouting"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><ruleTemplate>DepartmentalApproval</ruleTemplate><activationType>P</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5340,3980,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5341,3980,'DepartmentalApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5342,3980,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5343,3980,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5344,3980,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5345,3981,'<role name="OSPOfficeRouting"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5346,3981,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5347,3981,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5348,3981,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5349,3981,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5350,3982,'<join name="Join"/>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5351,3982,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5352,3984,'<requests name="WaitForHierarchyDisposition"><activationType>S</activationType><ruleTemplate>HierarchyParentDispositionApproval</ruleTemplate><mandatoryRoute>true</mandatoryRoute><ignorePrevious>true</ignorePrevious><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5353,3984,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5354,3984,'HierarchyParentDispositionApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5355,3984,'true')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ignorePrevious',5356,3984,'true')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5357,3984,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5358,3984,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5359,3986,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5360,3986,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5361,3986,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5362,3986,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5363,3986,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5364,3988,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5365,3988,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5366,3988,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5367,3988,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5368,3988,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5382,3990,'<start name="Initiated"><activationType>S</activationType></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5383,3990,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5384,3990,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5385,3991,'<role name="AwardBudgetOSPApproval"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><finalApproval>true</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5386,3991,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5387,3991,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5388,3991,'true')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5389,3991,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5402,4010,'<start name="AdHoc"/>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5403,4010,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5404,4011,'<role name="RoleType"><qualifierResolverClass>org.kuali.rice.kim.workflow.attribute.KimTypeQualifierResolver</qualifierResolverClass><activationType>P</activationType></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5405,4011,'org.kuali.rice.kim.workflow.attribute.KimTypeQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5406,4011,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5407,4011,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5408,4013,'<start name="AdHoc"/>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5409,4013,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5410,4014,'<role name="GroupType"><qualifierResolverClass>org.kuali.rice.kim.workflow.attribute.KimTypeQualifierResolver</qualifierResolverClass><activationType>P</activationType></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5411,4014,'org.kuali.rice.kim.workflow.attribute.KimTypeQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5412,4014,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5413,4014,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5414,4016,'<start name="AdHoc"/>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5415,4016,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5416,4017,'<role name="GroupType"><qualifierResolverClass>org.kuali.rice.kim.workflow.attribute.KimTypeQualifierResolver</qualifierResolverClass><activationType>P</activationType></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5417,4017,'org.kuali.rice.kim.workflow.attribute.KimTypeQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5418,4017,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5419,4017,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5420,4018,'<role name="RoleType"><qualifierResolverClass>org.kuali.rice.kim.workflow.attribute.KimTypeQualifierResolver</qualifierResolverClass><activationType>P</activationType></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5421,4018,'org.kuali.rice.kim.workflow.attribute.KimTypeQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5422,4018,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5423,4018,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5424,4020,'<start name="AdHoc"/>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5425,4020,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5426,4022,'<start name="AdHoc"/>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5427,4022,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5442,4030,'<start name="AdHoc"/>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5443,4030,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5444,4031,'<role name="RoleType"><qualifierResolverClass>org.kuali.rice.kim.workflow.attribute.KimTypeQualifierResolver</qualifierResolverClass><activationType>P</activationType></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5445,4031,'org.kuali.rice.kim.workflow.attribute.KimTypeQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5446,4031,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5447,4031,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5448,4033,'<start name="AdHoc"/>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5449,4033,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5450,4034,'<role name="GroupType"><qualifierResolverClass>org.kuali.rice.kim.workflow.attribute.KimTypeQualifierResolver</qualifierResolverClass><activationType>P</activationType></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5451,4034,'org.kuali.rice.kim.workflow.attribute.KimTypeQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5452,4034,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5453,4034,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5454,4036,'<start name="AdHoc"/>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5455,4036,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5456,4037,'<role name="GroupType"><qualifierResolverClass>org.kuali.rice.kim.workflow.attribute.KimTypeQualifierResolver</qualifierResolverClass><activationType>P</activationType></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5457,4037,'org.kuali.rice.kim.workflow.attribute.KimTypeQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5458,4037,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5459,4037,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5460,4038,'<role name="RoleType"><qualifierResolverClass>org.kuali.rice.kim.workflow.attribute.KimTypeQualifierResolver</qualifierResolverClass><activationType>P</activationType></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5461,4038,'org.kuali.rice.kim.workflow.attribute.KimTypeQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5462,4038,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5463,4038,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5464,4040,'<start name="AdHoc"/>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5465,4040,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5466,4042,'<start name="AdHoc"/>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5467,4042,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5482,4050,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5483,4050,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5484,4050,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5485,4050,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5486,4050,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5487,4051,'<split name="isHierarchyChild"><type>org.kuali.kra.kew.SimpleBooleanSplitNode</type></split>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('type',5488,4051,'org.kuali.kra.kew.SimpleBooleanSplitNode')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5489,4051,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5490,4053,'<role name="OSPInitial"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5491,4053,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5492,4053,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5493,4053,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5494,4053,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5495,4054,'<role name="ProposalPersons"><qualifierResolver>ProposalPersons-XPathQualifierResolver</qualifierResolver><activationType>P</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolver',5496,4054,'ProposalPersons-XPathQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5497,4054,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5498,4054,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5499,4054,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5500,4055,'<requests name="UnitRouting"><qualifierResolver>DepartmentRouting-XPathQualifierResolver</qualifierResolver><!--  <qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass> --><ruleTemplate>CustomApproval</ruleTemplate><activationType>P</activationType><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolver',5501,4055,'DepartmentRouting-XPathQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5502,4055,'CustomApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5503,4055,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5504,4055,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5505,4055,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5506,4056,'<requests name="DepartmentalRouting"><qualifierResolver>DepartmentRouting-XPathQualifierResolver</qualifierResolver><!--  <qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass> --><ruleTemplate>DepartmentalApproval</ruleTemplate><activationType>P</activationType><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolver',5507,4056,'DepartmentRouting-XPathQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5508,4056,'DepartmentalApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5509,4056,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5510,4056,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5511,4056,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5512,4057,'<role name="OSPOfficeRouting"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5513,4057,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5514,4057,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5515,4057,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5516,4057,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5517,4058,'<join name="Join"/>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5518,4058,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5519,4060,'<requests name="WaitForHierarchyDisposition"><activationType>S</activationType><ruleTemplate>HierarchyParentDispositionApproval</ruleTemplate><mandatoryRoute>true</mandatoryRoute><ignorePrevious>true</ignorePrevious><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5520,4060,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5521,4060,'HierarchyParentDispositionApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5522,4060,'true')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ignorePrevious',5523,4060,'true')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5524,4060,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5525,4060,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5662,4130,'<start name="AdHoc"/>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5663,4130,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5664,4132,'<start name="AdHoc"/>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5665,4132,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5666,4134,'<start name="Initiated"><activationType>S</activationType></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5667,4134,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5668,4134,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5669,4135,'<role name="AwardBudgetOSPApproval"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><finalApproval>true</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5670,4135,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5671,4135,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5672,4135,'true')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5673,4135,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5674,4137,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5675,4137,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5676,4137,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5677,4137,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5678,4137,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5679,4139,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5680,4139,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5681,4139,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5682,4139,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5683,4139,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5684,4141,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5685,4141,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5686,4141,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5687,4141,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5688,4141,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5689,4143,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5690,4143,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5691,4143,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5692,4143,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5693,4143,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5694,4145,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5695,4145,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5696,4145,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5697,4145,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5698,4145,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5699,4146,'<split name="isHierarchyChild"><type>org.kuali.kra.kew.SimpleBooleanSplitNode</type></split>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('type',5700,4146,'org.kuali.kra.kew.SimpleBooleanSplitNode')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5701,4146,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5702,4148,'<role name="OSPInitial"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5703,4148,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5704,4148,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5705,4148,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5706,4148,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5707,4149,'<role name="ProposalPersons"><qualifierResolver>ProposalPersons-XPathQualifierResolver</qualifierResolver><activationType>P</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolver',5708,4149,'ProposalPersons-XPathQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5709,4149,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5710,4149,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5711,4149,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5712,4150,'<requests name="UnitRouting"><qualifierResolver>DepartmentRouting-XPathQualifierResolver</qualifierResolver><!--  <qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass> --><ruleTemplate>CustomApproval</ruleTemplate><activationType>P</activationType><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolver',5713,4150,'DepartmentRouting-XPathQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5714,4150,'CustomApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5715,4150,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5716,4150,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5717,4150,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5718,4151,'<requests name="DepartmentalRouting"><qualifierResolver>DepartmentRouting-XPathQualifierResolver</qualifierResolver><!--  <qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass> --><ruleTemplate>DepartmentalApproval</ruleTemplate><activationType>P</activationType><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolver',5719,4151,'DepartmentRouting-XPathQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5720,4151,'DepartmentalApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5721,4151,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5722,4151,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5723,4151,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5724,4152,'<role name="OSPOfficeRouting"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><finalApproval>false</finalApproval></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5725,4152,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5726,4152,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5727,4152,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5728,4152,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5729,4153,'<join name="Join"/>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5730,4153,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5731,4155,'<requests name="WaitForHierarchyDisposition"><activationType>S</activationType><ruleTemplate>HierarchyParentDispositionApproval</ruleTemplate><mandatoryRoute>true</mandatoryRoute><ignorePrevious>true</ignorePrevious><finalApproval>false</finalApproval></requests>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5732,4155,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5733,4155,'HierarchyParentDispositionApproval')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5734,4155,'true')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ignorePrevious',5735,4155,'true')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5736,4155,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5737,4155,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5738,4157,'<start name="Initiated"><activationType>S</activationType></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5739,4157,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5740,4157,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5741,4158,'<role name="AssignedToAgenda"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><ruleTemplate>AssignedToAgenda</ruleTemplate></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5742,4158,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5743,4158,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5744,4158,'AssignedToAgenda')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5745,4158,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5746,4159,'<role name="IRBReview"><qualifierResolverClass>org.kuali.rice.kew.role.NullQualifierResolver</qualifierResolverClass><activationType>S</activationType><ruleTemplate>IRBReview</ruleTemplate></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5747,4159,'org.kuali.rice.kew.role.NullQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5748,4159,'S')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleTemplate',5749,4159,'IRBReview')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5750,4159,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5751,4161,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5752,4161,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5753,4161,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5754,4161,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5755,4161,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5756,4163,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5757,4163,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5758,4163,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5759,4163,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5760,4163,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5761,4165,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5762,4165,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5763,4165,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5764,4165,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5765,4165,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5766,4167,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5767,4167,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5768,4167,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5769,4167,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5770,4167,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5771,4169,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5772,4169,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5773,4169,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5774,4169,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5775,4169,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5776,4171,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5777,4171,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5778,4171,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5779,4171,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5780,4171,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5782,4190,'<start name="AdHoc"/>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5783,4190,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5784,4191,'<role name="RoleType"><qualifierResolverClass>org.kuali.rice.kim.workflow.attribute.KimTypeQualifierResolver</qualifierResolverClass><activationType>P</activationType></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5785,4191,'org.kuali.rice.kim.workflow.attribute.KimTypeQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5786,4191,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5787,4191,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5788,4193,'<start name="AdHoc"/>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5789,4193,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5790,4194,'<role name="GroupType"><qualifierResolverClass>org.kuali.rice.kim.workflow.attribute.KimTypeQualifierResolver</qualifierResolverClass><activationType>P</activationType></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5791,4194,'org.kuali.rice.kim.workflow.attribute.KimTypeQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5792,4194,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5793,4194,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5794,4196,'<start name="AdHoc"/>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5795,4196,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5796,4197,'<role name="GroupType"><qualifierResolverClass>org.kuali.rice.kim.workflow.attribute.KimTypeQualifierResolver</qualifierResolverClass><activationType>P</activationType></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5797,4197,'org.kuali.rice.kim.workflow.attribute.KimTypeQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5798,4197,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5799,4197,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5800,4198,'<role name="RoleType"><qualifierResolverClass>org.kuali.rice.kim.workflow.attribute.KimTypeQualifierResolver</qualifierResolverClass><activationType>P</activationType></role>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('qualifierResolverClass',5801,4198,'org.kuali.rice.kim.workflow.attribute.KimTypeQualifierResolver')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5802,4198,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5803,4198,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5804,4200,'<start name="AdHoc"/>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5805,4200,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5806,4202,'<start name="AdHoc"/>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5807,4202,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5808,4204,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5809,4204,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5810,4204,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5811,4204,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5812,4204,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5813,4206,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5814,4206,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5815,4206,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5816,4206,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5817,4206,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5818,4208,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5819,4208,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5820,4208,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5821,4208,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5822,4208,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5842,4210,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5843,4210,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5844,4210,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5845,4210,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5846,4210,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5847,4212,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5848,4212,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5849,4212,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5850,4212,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5851,4212,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5852,4214,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5853,4214,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5854,4214,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5855,4214,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5856,4214,'Template')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('contentFragment',5857,4216,'<start name="Initiated"><activationType>P</activationType><mandatoryRoute>false</mandatoryRoute><finalApproval>false</finalApproval></start>')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('activationType',5858,4216,'P')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('mandatoryRoute',5859,4216,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('finalApproval',5860,4216,'false')
/
INSERT INTO KREW_RTE_NODE_CFG_PARM_T (KEY_CD,RTE_NODE_CFG_PARM_ID,RTE_NODE_ID,VAL)
  VALUES ('ruleSelector',5861,4216,'Template')
/
delimiter ;
