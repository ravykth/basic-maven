# Building For Different Environments with Maven 3

The webapp project structure
```java
pom.xml
src/
  main/
    java/
    resources/
  test/
    java/
```

Under src/main/resources there are three files:
```java
    application.properties - This is the default configuration and will be packaged in the artifact by default.
    ENV.test.properties - This is the variant for the test environment.
    ENV.prod.properties - This is basically the same as the test variant and will be used in the production environment.

```
    In the project descriptor, you need to configure the different profiles.
   ```java 
   <properties>
    <filename.env.properties>application.properties</filename.env.properties>    
  </properties>
  
   <build>
     <plugins>
         <plugin>
           <artifactId>maven-antrun-plugin</artifactId>
           <executions>
             <execution>
               <phase>test</phase>
               <goals>
                 <goal>run</goal>
               </goals>
               <configuration>
                 <tasks>
                   <delete file="${project.build.outputDirectory}/application.properties"/>
                   <copy file="src/main/resources/${filename.env.properties}"
                         tofile="${project.build.outputDirectory}/application.properties"/>
                 </tasks>
               </configuration>
             </execution>
           </executions>
         </plugin>
         </build>
          
  <profiles>
   <profile>
     <id>test</id>
     <properties>
    <filename.env.properties>ENV.TEST.properties</filename.env.properties>    
  </properties> 
   </profile>
  <profile>
     <id>stage</id>
    <properties>
   	 <filename.env.properties>ENV.STAGE.properties</filename.env.properties>    
     </properties>    
   </profile>
   
  <profile>
     <id>prod</id>
    <properties>
   	 <filename.env.properties>ENV.PROD.properties</filename.env.properties>    
     </properties>    
   </profile>
</profiles>

      ```

mvn clean install -Ptest|stage|prod
