Structure of the project

tree
.
├── pom.xml
├── readme.txt
└── src
    └── main
        ├── resources
        └── webapp
            ├── index.jsp
            └── WEB-INF
                └── web.xml

                
-run with tomcat 7 server plugin

>>mvn clean tomcat7:run


-run with jetty server plugin

>>mvn clean jetty:run

local server
http://localhost:8080