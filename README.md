**Kuali Coeus**
==
Kuali Coeus (KC) for Research Administration is a comprehensive system to manage the complexities of research administration needs from the faculty researcher through grants administration to federal funding agencies. KC is using MIT’s proven COEUS system as its baseline design, updating its technical architecture for vendor independence and integration with other administration systems.

----------
##**Installation**
**Prerequisites**
[Maven 3.3.x][1]
[Java 1.8.x][2]
[Tomcat 7.x][3]
[MySQL 5.6.x][4]
[Git 2.4.x][5]

**Instructions**
The Kuali Coeus application uses Apache Maven as it's build tool.  Inside of Kuali Coeus's Maven configuration is a list of dependencies.  Some of these dependencies may not be available in a publicly available Maven repository.  At the very least you will need to install the following projects into your maven repo.  These projects may have more than one submodule.

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

**Cross Project Build Instructions**
We provide several maven build profiles that may be useful.  Some of these profiles are specific to a project while others are available in all projects.
When a project specific profile is available, it will be documented in the build step.  The following are a list of profiles available in all projects:

> **GRM Profile:** When building Kuali Coeus Projects you should turn the grm maven profile off as it is on by default.  You can do this by commenting out the activation section of the profile or sending the following system parameter grm.off on the command line. 
> 
*mvn clean install -Dgrm.off=true*

> **Enforcer Profile:** When building Kuali Coeus Projects you can turn the enforcer profile on as it is off by default.  This will turn on the maven enforcer plugin and fail the build if certain project quality rules are violated. 
> 
*mvn clean install -Penforcer*

> **jdeps Profile:** When building Kuali Coeus Projects you can turn the jdeps profile on as it is off by default.  This will turn on the jdk jdeps tool and fail the build if internal jdk apis are detected. 
> 
*mvn clean install -Pjdeps*

> **Error Prone Profile:** When building Kuali Coeus Projects you can turn the error-prone profile on as it is off by default.  This will turn on the strict error prone compiler and fail the compile step if certain source code errors are detected. 
> 
*mvn clean install -Perror-prone*

> **enforce-project-quality Property:** This property turns on the enforcer, jdeps, and error prone profiles.  These profiles are off by default.
> 
*mvn clean install -Denforce-project-quality*

> **Dev Profile:**When developing with KC there are some features that are useful only for development purposes. In order to enable these features you should enable the dev profile. Currently the dev profile only provides the p6spy dependency. See the section below on Configuration for how to use this feature.
>
*mvn clean install -Pdev*

> **Node Clean:**When building our api documentation, our build process will download node.js and various node dependencies.  By default, these artifacts are deleted on every mvn clean execution.  You can avoid this clean step by sending the following system parameter clean-jsfrontend-node.off on the command line.  This is useful to speed up project builds by avoiding the installation node.js on subsequent clean install iterations. 
>
*mvn clean install -Dclean-jsfrontend-node.off*

All Kuali Coeus projects use standard maven conventions to build and install artifacts.  The following documents how to install source, javadoc, and primary artifacts for each maven projects.

> **Source and Javadoc jars:** When building Kuali Coeus Projects it may be helpful to also build source and javadoc jars.  These jars can be consumed by tools such as debuggers.  Note: due to changes in the javadoc tool in Java 8, you may need to execute the compile phase before attempting to create a javadoc jar. 
>
*mvn clean compile source:jar javadoc:jar install*


**Step 3: Build SchemaSpy**
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

Install without Oracle support
```
cd ../kc
mvn clean compile source:jar javadoc:jar install -Dgrm.off=true
```

> **Oracle Profile:** If using an Oracle database make sure oracle profile is used to insure Oracle specific jars are added to the classpath.  Application will fail to start up if the Oracle jar is not added.
```
mvn clean install -Dgrm.off=true -Poracle
```

> **Integration Tests:** This runs the integration tests.  This profile requires a properly configured integration test database and configuration.
```
mvn clean install -Dgrm.off=true -Pitests
```

> **Precompile jsps:** This precompiles the Kuali Coeus jsps for Tomcat 7.  This is useful to verify the absence of compile errors in jsps while the Kuali Coeus Application is being built.  Precompilation also helps the initial page load time for all jsps.
```
mvn clean install -Dgrm.off=true -Pprecompile-jsp-tomcat-7
```

> **System Dependent Requirements:** Kuali Coeus is now using some node and npm dependencies as part of its build process. These dependencies have all been designed to be downloaded, installed and run without any additional system level requirements, but there are some system specific requirements that cannot be managed by our build process. This seems to primarily affect Windows, but additional systems may be affected depending on local configuration. If you are seeing errors attempting to build Kuali Coeus that relate to node or npm, please see the failing node project's documentation for what might be expected to be installed on your system.
For example, we currently have a dependency on a node project called drafter that builds our api documentation. From their [Windows specific documentation][10], drafter appears to require Visual Studio Express 2012 and Python 2.7. These types of dependencies are beyond our control, but we strive to make the build process as simple as possible.

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

Kuali Coeus supports MySQL 5.6 and Oracle. We recommend MySQL though as that is the database we develop and support internally and are more easily able to respond to problems with that database.  If you choose to use Oracle, please be sure to use the latest Oracle driver version 12 or higher.  The version 12 driver will work correctly with older Oracle database servers such as version 11.

* [Database Installation Instructions](coeus-db/coeus-db-sql/src/main/resources/co/kuali/coeus/data/migration/sql/README.md)

**Configuration Information**

This section contains some useful information about configuring the Kuali Coeus Application.  This is not an exhaustive list of configuration options or a tutorial on configuration.  For more options see coeus-impl/src/main/resources/META-INF/kc-config-defaults.xml inside the Kuali Coeus Application source or the Kuali Rice public documentation.
> **SchemaSpy:** The kc.schemaspy.enabled config param turns SchemaSpy on or off.  SchemaSpy is a great tool for visualizing the Kuali Coeus database and is generally useful on server instances.  It does create additional CPU upon application startup while gathering database information.  After information gathering is complete, CPU usage will go back to normal as all SchemaSpy information is cached as static content.  It is recommended to disable SchemaSpy on developer machines and during integration test runs. 
```
<param name="kc.schemaspy.enabled">false</param>
```

> **Monitoring** The kc.monitoring.enabled config param turns Monitoring on or off.  Monitoring is done through Java Melody and is great for learning about the runtime characteristics of the Kuali Coeus Application.  Java Melody has low overhead and in general can be left on.
```
<param name="kc.monitoring.enabled">false</param>
```

> **P6Spy** P6Spy can be a useful tool during development that will allow you to view sql statements that are generated and executed against the database in real time. In order to use it in KC you will need to enable the dev profile mentioned above as well as reconfigure your database connection string and driver similar to the below sample. All other kc-config.xml options should remain the same. Additionally you will need to configure the spy.properties file found in *coeus-webapp/src/main/resources/* to specify the correct original driver and potentially the appender method if StdOut is not sufficient.
```
<param name="datasource.url">jdbc:p6spy:mysql://localhost:3306/kcdev</param>
<param name="datasource.driver.name">com.p6spy.engine.spy.P6SpyDriver</param>
```

  [1]: http://maven.apache.org/download.cgi
  [2]: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
  [3]: https://tomcat.apache.org/download-70.cgi
  [4]: http://dev.mysql.com/downloads/mysql/
  [5]: http://git-scm.com/downloads
  [6]: http://mvnrepository.com/artifact/org.springframework/spring-instrument-tomcat/3.2.13.RELEASE
  [7]: http://mvnrepository.com/artifact/org.springframework/spring-instrument/3.2.13.RELEASE
  [8]: http://www.graphviz.org/Download..php
  [9]: https://github.com/google/error-prone
  [10]: https://github.com/apiaryio/drafter/wiki/Building-on-Windows
