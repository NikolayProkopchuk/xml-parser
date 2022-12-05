Simle REST API allows upload xml files, validate, parse, store their content in db and represent it with paginating and sorting.  
### The workflow looks like this:
1. A user sends an XML file via REST API.
2. The file is parsed and validated, its content goes to the table in the DB.
3. The content of the DB can be presented by the GUI, in the form of a table with possibility of sorting, paging & filtering
   (creating a REST API for "front-end team" to prepare such GUI is enough ).

XML files have the following format:

    <?xml version="1.0" encoding="UTF-8"?>
    <epaperRequest>
        <deviceInfo name="Browser" id="test@comp">
            <screenInfo width="1280" height="752" dpi="160" />
            <osInfo name="Browser" version="1.0" />
            <appInfo>
                <newspaperName>abb</newspaperName>
                <version>1.0</version>
            </appInfo>
        </deviceInfo>
        <getPages editionDefId="11" publicationDate="2017-06-06" />
    </epaperRequest>

### Technology stack:
Java 17, Maven, Spring Boot, Postgres

### How to start:
1. Clone project.
2. Create db in postgres and set datasource params in application.properties (here is used db 'epapers' with username and password 'postgres')
3. Run command from terminal  
``` 
mvn clean install
```
4. Start XmlParserApplication.java from IDE or run
``` 
mvn spring-boot:run
```
5. To upload file use POST endpoint '/epaper'.
You need upload it as multipart form data with 'file' request parameter name
6. To get db content use GET endpoint like this '/epaper?size=2&page=2sort=appInfo.newspaperName' where parameters are optional