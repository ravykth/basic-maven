# Create Maven Project jar type:


run command :
```java
>> mvn archetype:generate -DgroupId=com.rupp.app -DartifactId=myFirstMavenApp -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

....
[INFO] ----------------------------------------------------------------------------
[INFO] Parameter: basedir, Value: /data/workspace_android
[INFO] Parameter: package, Value: com.rupp.app
[INFO] Parameter: groupId, Value: com.rupp.app
[INFO] Parameter: artifactId, Value: myFirstMavenApp
[INFO] Parameter: packageName, Value: com.rupp.app
[INFO] Parameter: version, Value: 1.0-SNAPSHOT
[INFO] project created from Old (1.x) Archetype in dir: /data/workspace_android/myFirstMavenApp
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 31.657 s
[INFO] Finished at: 2016-12-09T15:12:56+07:00
[INFO] Final Memory: 16M/300M
[INFO] ------------------------------------------------------------------------


>> cd myFirstMavenApp/

//see project structure :

>>[sophea@sophea-ThinkPad-Edge-E440 myFirstMavenApp]$ tree
.
├── pom.xml
└── src
    ├── main
    │   └── java
    │       └── com
    │           └── rupp
    │               └── app
    │                   └── App.java
    └── test
        └── java
            └── com
                └── rupp
                    └── app
                        └── AppTest.java

//build the project
>>mvn clean package

see success compile and build the project.

Here are the steps :
     * validate
     * generate-sources
     * process-sources
     *  generate-resources
     * process-resources
     * compile

You may test the newly compiled and packaged JAR with the following command:

>> java -cp target/myFirstMavenApp-1.0-SNAPSHOT.jar com.rupp.app.App 
Hello World!
```

Import the projects and view in IDE and add more class java-collection or other try it out



# Create project for web project
```java

mvn archetype:generate -DgroupId=com.rupp.sample -DartifactId=samplewebapp -DarchetypeArtifactId=maven-archetype-webapp	-DinteractiveMode=false

>> cd samplewebapp

//project structure
>> tree 
─ pom.xml
└── src
    └── main
        ├── resources
        └── webapp
            ├── index.jsp
            └── WEB-INF
                └── web.xml
```
- cat pom.xml

```java

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.rupp.sample</groupId>
  <artifactId>samplewebapp</artifactId>
  <packaging>war</packaging>
  <version>1.0-SNAPSHOT</version>
  <name>samplewebapp Maven Webapp</name>
  <url>http://maven.apache.org</url>
  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
  <build>
    <finalName>samplewebapp</finalName>
  </build>
</project>
```

- open with Eclipse and import
- mvn eclipse:eclipse

//add tomcat7 server plugin in build tag  with pom.xml

```java

<build>
    <finalName>samplewebapp</finalName>
   <plugins>
   <plugin>
	<artifactId>maven-compiler-plugin</artifactId>
         <version>2.3.2</version>
   </plugin>
   <!-- tomcat7 server-->
   <plugin>
	<groupId>org.apache.tomcat.maven</groupId>
	<artifactId>tomcat7-maven-plugin</artifactId>
	<version>2.2</version>
	<configuration>
<!--	<tomcatLoggingFile>tomcat_server.log</tomcatLoggingFile>-->
         <path>/</path>
	</configuration>
    </plugin>
 </plugins>

```

The pom.xml looks like :

```java
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.rupp.sample</groupId>
  <artifactId>samplewebapp</artifactId>
  <packaging>war</packaging>
  <version>1.0-SNAPSHOT</version>
  <name>samplewebapp Maven Webapp</name>
  <url>http://maven.apache.org</url>
  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
  <build>
    <finalName>samplewebapp</finalName>
   <plugins>
   <plugin>
	<artifactId>maven-compiler-plugin</artifactId>
         <version>2.3.2</version> 
	 <configuration>
                <source>1.7</source>
                <target>1.7</target>
            </configuration>
   </plugin>
   <!-- tomcat7 server-->
   <plugin>
	<groupId>org.apache.tomcat.maven</groupId>
	<artifactId>tomcat7-maven-plugin</artifactId>
	<version>2.2</version>
	<configuration>
<!--	<tomcatLoggingFile>tomcat_server.log</tomcatLoggingFile>-->
         <path>/</path>
	</configuration>
    </plugin>
   
   </plugins>
  </build>
</project>
```
- //run the following mvn
- mvn clean install tomcat7:run
- open browser : http://localhost:8080/

# Create pom file with properties filtering
see more detail : https://maven.apache.org/plugins/maven-resources-plugin/examples/filter.html

see the pom file : properties_pom.xml 
```java
<!-- properties -->
<properties>
    <database.driver>com.mysql.jdbc.Driver</database.driver>
    <database.url>jdbc:mysql://localhost:3306/database?autoReconnect=true</database.url>
    <database.username>myusername</database.username>
    <database.password>mypassword</database.password>
  </properties>
  
<build>
    <resources>
      <resource>
        <directory>src/main/resources</directory>
        <filtering>true</filtering>
      </resource>
    </resources>
  </build>
  
```
see file src/main/resources/application.properties
```java
driverClassName=${database.driver}
#url=jdbc:mysql://localhost:3306/scholastic?autoReconnect=true
url=${database.url}
username=${database.username}
password=${database.password}
```
Running this maven : mvn -f properties_pom.xml clean install

cat target/classes/application.properties 
```java
driverClassName=com.mysql.jdbc.Driver
#url=jdbc:mysql://localhost:3306/scholastic?autoReconnect=true
url=jdbc:mysql://localhost:3306/database?autoReconnect=true
username=myusername
password=mypassword
```

# Create pom file with different profile TESt | STAGE | Prod
see more details in this url : https://github.com/sophea/basic-maven/tree/master/mvnProfile
