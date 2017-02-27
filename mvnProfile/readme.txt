

When we are writing software that is deployed to different environments such as test stage and production, we often have to create different configuration files for each environment. If we are using Maven, we can do this in pom.xml by using build profiles.


pom.xml

<profiles>
    <profile>
      <id>test</id>
      <properties>
        <database.driver>com.mysql.jdbc.Driver</database.driver>
        <database.url>jdbc:mysql://test-qadb01:3306/database?autoReconnect=true</database.url>
        <database.username>test-user</database.username>
        <database.password>testpassword</database.password>
      </properties>
    </profile>
   
   <profile>
      <id>production</id>
      <properties>
        <database.driver>com.mysql.jdbc.Driver</database.driver>
        <database.url>jdbc:mysql://prod-db01:3306/database?autoReconnect=true</database.url>
        <database.username>produser</database.username>
        <database.password>prodpassword</database.password>
      </properties>
    </profile>
  </profiles>
  
// properties : /src/main/resources/application.properties
#sample content of application.properties
driverClassName=${database.driver}
#url=jdbc:mysql://localhost:3306/scholastic?autoReconnect=true
url=${database.url}
username=${database.username}
password=${database.password}


package war file with Test server
>> mvn clean install -Ptest

// check application.properties
cat target/classes/application.properties

package war type with production server

>> mvn clean install -Pproduction

// check application.properties
cat target/classes/application.properties


// run web jetty with test profile:
>> mvn clean jetty:run -Ptest
http://localhost:8080/properites

//jetty with production
>>mvn clean jetty:run -Pproduction
http://localhost:8080/properties 
