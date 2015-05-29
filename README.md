**Kuali Coeus**
==
Kuali Coeus (KC) for Research Administration is a comprehensive system to manage the complexities of research administration needs from the faculty researcher through grants administration to federal funding agencies. KC is using MIT’s proven COEUS system as its baseline design, updating its technical architecture for vendor independence and integration with other administration systems.

----------
##**Installation**
**Prerequisites**
[Maven 3.2.x][1]
[Java 1.8.x][2]
[Tomcat 7.x][3]
[MySQL 5.6.x][4]
[Git 2.4.x][5]

**Instructions**
The Kuali Coeus application uses maven as it's build tool.  Inside of Kuali Coeus's maven configuration is a list of dependencies.  Some of these dependencies may not be available in a publicly available maven repository.  At the very least you will need to install the following projects into your maven repo.  These projects may have more than one submodule.

* [SchemaSpy](https://github.com/kuali/schemaspy)
* [Kuali Coeus Rice](https://github.com/kuali/kc-rice)
* [Kuali Coeus API](https://github.com/kuali/kc-api)
* [Kuali Coeus S2S Gen](https://github.com/kuali/kc-s2sgen)


> **Build Order:** The projects required to install Kuali Coeus must be built in the following order:
 1. SchemaSpy
 2. Kuali Coeus Rice
 3. Kuali Coeus API
 4. Kuali Coeus S2s Generator
 5. Kuali Coeus 

**Step 1. clone all required projects**
```
cd workspace
git clone https://github.com/kuali/schemaspy
git clone https://github.com/kuali/kc-rice
git clone https://github.com/kuali/kc-api
git clone https://github.com/kuali/kc-s2sgen
git clone https://github.com/kuali/kc
```
**Step 2. determine correct tag versions for dependent projects.**
Manually search the pom.xml file in the root directory of the Kuali Coeus project, and find the version numbers for KC Rice, KC Api, Kc S2s Generator, and SchemaSpy

```
<coeus-api-all.version>x.x.x.x</coeus-api-all.version>
<coeus-s2sgen.version>x.x.x</coeus-s2sgen.version>
...
<rice.version>x.x.x.x</rice.version>
<schemaspy.version>x.x.x.x</schemaspy.version>
```

Then check out the correct tag before installing.

**Step 3: Build SchemaSpy**
> **GRM Profile:** When building Kuali Coeus Projects you should turn the grm maven profile off.  You can do this by commenting out the activation section of the profile or sending the following system parameter grm.off on the command line. 
> 
*mvn clean install -Dgrm.off=true*

> **Source and Javadoc jars:** When building Kuali Coeus Projects it may be helpful to also build source and javadoc jars.  These jars can be consumed by tools such as debuggers.  Note: due to changes in the javadoc tool in Java 8, you may need to execute the compile phase before attempting to create a javadoc jar. 
>
*mvn clean compile source:jar javadoc:jar install*

Check out the correct schemaspy version and run maven clean install.
```
cd schemaspy
git checkout tags/schemaspy-xxxx.xx
mvn clean compile source:jar javadoc:jar install -Dgrm.off=true
```

**Step 4: Build Kuali Coeus Rice**
Checkout the correct version of kuali coeus rice and install.
```
cd ../kc-rice
git checkout tags/rice-x.x.x.xxxxx.xx
mvn clean compile source:jar javadoc:jar install -Dgrm.off=true 
```

Wait until coeus-api has installed successfully before moving to the next step.

**Step 5: Build Kuali Coeus Api**
Checkout the correct version of coeus api and install.
```
cd ../kc-api
git checkout tags/coeus-api-xxxx.xx
mvn clean compile source:jar javadoc:jar install -Dgrm.off=true 
```

Wait until coeus-api has installed successfully before moving to the next step.

**Step 6: Build Kuali Coeus S2sGen**
Checkout the correct version of coeus-s2sgen and install
```
cd ../kc-s2sgen
git checkout tags/coeus-s2sgen-xxxx.xx
mvn clean compile source:jar javadoc:jar install -Dgrm.off=true 
```

Wait until coeus-s2sgen has installed successfully before moving to the next step.

**Step 7: Build Kuali Coeus**
Installing Kuali Coeus
> **Oracle Profile:** If using an Oracle database make sure oracle profile is used to insure Oracle specific jars are added to the classpath.  Application will fail to start up if the Oracle jar is not added.
```
mvn clean install -Dgrm.off=true -Poracle
```

Install without Oracle support
```
cd ../kc
mvn clean compile source:jar javadoc:jar install -Dgrm.off=true
```

**Step 8: Install Spring Instrumentation**

***For Tomcat***: 
	[Download][6] and install tomcat spring instrumentation in the tomcat lib directory
    Configure the tomcat spring instrumenting classloader in the tomcat context.xml file
    
```
<Context>
    <!-- ...snip... -->
    <Loader loaderClass="org.springframework.instrument.classloading.tomcat.TomcatInstrumentableClassLoader"/>
    <!-- ...snip... -->
</Context>
``` 

***Otherwise***:
	[Download][7] and configure java agent in at the jvm level.
```
	-javaagent:/Users/user/.m2/repository/org/springframework/spring-instrument/3.2.13.RELEASE/spring-instrument-3.2.13.RELEASE.jar
``` 

>**Note:** Application will fail to start up in spring instrumentation is not installed correctly.

**Step 9: Install Graphviz**

[Download][8] and install Graphviz.

>**Note:** In order for SchemaSpy to be fully functioning, graphviz must be installed and the dot binary accessible on the system path.

##**Creating DB**

Kuali Coeus supports MySQL 5.6 and Oracle. We recommend MySQL though as that is the database we develop and support internally and are more easily able to respond to problems with that database.

* [Mysql Installation Instructions](coeus-db/coeus-db-sql/src/main/resources/co/kuali/coeus/data/migration/sql/mysql/README)
* [Oracle Installation Instructions](coeus-db/coeus-db-sql/src/main/resources/co/kuali/coeus/data/migration/sql/oracle/README)




  [1]: http://maven.apache.org/download.cgi
  [2]: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
  [3]: https://tomcat.apache.org/download-70.cgi
  [4]: http://dev.mysql.com/downloads/mysql/
  [5]: http://git-scm.com/downloads
  [6]: http://mvnrepository.com/artifact/org.springframework/spring-instrument-tomcat/3.2.13.RELEASE
  [7]: http://mvnrepository.com/artifact/org.springframework/spring-instrument/3.2.13.RELEASE
  [8]: http://www.graphviz.org/Download..php
