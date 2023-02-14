# Accomplishments & Reflections

### Week 1 
* 2/10/23  
Researched ideas for an SQL database project.   
Decided on a book database based on Goodreads so that I can implement custom search filters.  
Created README in word  
Created Design Doc in word  


* 2/11/23
Created an accomplishment tracking   
Installed plugin to create builder pattern  
Created Book entity  
Created BookRepository  
Created BookService with basic CRUD operations (Still need to work on custom filtering)  
Created BookController  
Working on configuration for Postgres and Docker. It currently runs into an error related to 'authors' in the table.  

2/12/23
* The JSON format for Book has 2 lists of objects; popular_shelves and authors. I incorrectly coded the attributes as List<List<>> instead of List\<?>. I updated and created classes, however I am still getting JPA/Hibernate errors for mapping these columns. I am researching JPA fields and annotations.  
  * If a persistent field is a Collection type, it must include the annotation *@ElementCollection*
    * These attributes are optional for Java:
      * targetClass = Example.class -> optional because it is defined with generics
      * fetch = FetchType.LAZY -> Lazy is default fetch type 
  * List\<Role> and List\<Shelf> are embedded classes:
    * Embeddable classes are used to represent the state of an entity but don’t have a persistent identity of their own.  
    * @Embeddable annotation is used above the class declaration.
    * @Embedded can be used in the @Entity class but is not required.
  

  Now I'm getting error *org.hibernate.exception.GenericJDBCException: Unable to open JDBC Connection for DDL execution*  


### Week 2
* 2/13/23  
  Researched JSON objects and libraries.
    
  * Gson is easy to use, standardized, efficient, optimized, and does not require libraries apart from JDK.
  * Added the Gson dependency in the gradle build.
  * Successfully deserialized a JSON string to a Book object


* 2/14/23  
  Imports stopped working in this project.
    ![](images/import-errors-image.png)
  * I spent hours trying to figure this out with no solution. It seems to be related to either an IDE/IntelliJ or Gradle issue.
    * Older projects were unaffected.
    * I tried invalidating caches and restarting IntelliJ
    * I tried deleting the .idea and build files to rebuild the project.
    * Another recommendation was to uninstall/reinstall IntelliJ. (Did not do this.)
  * After starting a new Gradle project with Spring Initializr and copying the packages/files, the imports are working agian.



## TODO
* Create architecture diagram 
* Figure out how to seed the database from the JSON files  
* create the data model classes for book, author, and genres  
* create the JPA repos  
* create the service  
* create the controllers with working endpoints  
* Improve scalability:
  * Implement logging to document performance before and after
  * Implement pagination
  * Implement caching

