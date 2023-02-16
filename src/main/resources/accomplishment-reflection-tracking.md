# Accomplishments & Reflections

### Week 1 
* 2/10/23  
  * Researched ideas for an **SQL database** project.   
  * Decided on a book database based on Goodreads so that I can implement custom search filters.
  * Initialized a repo and project using **Spring Initializr**.
  * Created README in word  
  * Created Design Doc in word  


* 2/11/23
  * Created an accomplishment tracking   
  * Installed plugin to create builder pattern  
  * Created Book entity  
  * Created BookRepository  
  * Created BookService with basic **CRUD operations** (Still need to work on custom filtering)  
  * Created BookController  
  * Working on configuration for **Postgres** and **Docker**. It currently runs into an error related to 'authors' in the table.  

2/12/23
* The **JSON** format for Book has 2 lists of objects; popular_shelves and authors. 
  I incorrectly coded the attributes as List<List<>> instead of List\<Object>. 
  I updated and created classes, however I am still getting **JPA/Hibernate** errors for mapping these columns. 
  I am researching JPA fields and annotations.  
  * If a persistent field is a Collection type, it must include the annotation _**@ElementCollection**_
  * List\<Role> and List\<Shelf> are embedded classes:
    * **Embeddable** classes are used to represent the state of an entity but don’t have a persistent identity of their own.  
    * **_@Embeddable_** annotation is used above the class declaration.
    * **_@Embedded_** can be used in the @Entity class but is not required.
  

  * Now I'm getting error org.hibernate.exception.GenericJDBCException: Unable to open **JDBC** Connection for DDL execution
    * My **Docker** configuration previously ran with template variables and saved it to the data/pg folder.
      I corrected the variables, deleted the pg data, and re-ran docker setup to fix the issue.  


### Week 2
* 2/13/23  
  Researched **JSON** objects and libraries.
    
  * **Gson** is easy to use, standardized, efficient, optimized, and does not require libraries apart from JDK.
  * Added the Gson dependency in the gradle build.
  * Successfully deserialized a JSON string to a Book object


* 2/14/23  
  Imports stopped working in this project.
    ![](images/import-errors-image.png)
  * I spent hours trying to figure this out with no solution. It seems to be related to either an IDE(IntelliJ) or **Gradle** issue.
    * Older projects were unaffected.
    * I tried invalidating caches and restarting IntelliJ
    * I tried deleting the .idea and build directories to rebuild the project.
    * Another recommendation was to uninstall/reinstall IntelliJ. (Did not do this since other projects were unaffected.)
  * After starting a new Gradle project with **Spring Initializr** and copying the packages/files, the imports are working again.


* 2/15/23  
  * **Seeding** the Book database:
    * Initial issue is the zipped file is a list of JSON strings separated by lines. So, I need to figure out how to iterate the file by line, convert to a Book object, and save to the DB.  
      * I first tried to unzip the file and use a buffered reader which resulted in a very large file.
      * After referencing the Goodreads dataset **project documentation**, I found a function in **Python** on how to load the data.  
        * https://github.com/MengtingWan/goodreads/blob/master/samples.ipynb
        * Used this as the basis to create my BookSeeder class.

    * Issue with the **primary key**/id:
      * Failed to execute CommandLineRunner...Unknown integral data type for ids : java.lang.String;
      * ISBNs are unique numeric identifiers, so I thought I could use them as the primary key. 
        The issue is they are all strings in the JSON data set. **Hibernate** does not like this since it is a String.
        * Should I convert these to Integers or use a generated **UUID** as the primary key?
        * ISBN is a numeric identifier assigned by publishers. -> I do not want to auto-generate these.
        * For purposes of this project (return a recommendations list of filtered results), there is no need to lookup a book by a single ISBN or key.
      * Solution is to modify code base to use **UUID** as a **primary key** and make ISBN a normal field/column. 

    * Application now runs with error Caused by: **org.postgresql.util.PSQLException**: ERROR: value too long for type **VARCHAR(255)**
      * bookDescription is too long for default varchar 255. Updated the @Column(length = 10000);

* 2/16/23
  * Problem: BookSeeder runs but does not populate all columns. Some have null data.
    * **Deserializing** using gson.fromJson() to a Book object is not working for all fields due to the use of **camel case** in my code vs **snake case** in the JSON file.  
    * Solution:
      * In the **Gson documentation**, you can use **@SerializedName**("custom_naming"), however since this is a common convention it also provides **FieldNamingPolicy** to auto convert all instances!
      * ```builder.setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);```
  
  * All numbers and booleans are stored as Strings in the JSON dataset. For better practice and a learning opportunity, determine how to convert these while seeding the data.
    * Will need to convert all field types to Integer
      1. ~~isbn Integer~~  Some ISBNs have a trailing letter.
      2. text_reviews_count Integer
      3. series List<Integer>
      4. popular_shelves -> Shelf count;
      5. isEbook -> Boolean
      6. average_rating Double
      7. similar_books List<Integer>
      8. authors -> Role authorId Integer
      9. num_pages Integer
      10. publication_day Integer
      11. publication_month Integer
      12. publication_year Integer
      14. ratings_count Integer
    * Use Gson documentation for a strategy or tool to convert String to Integer
      * Implement a **custom deserializer** _public class BookDeserializer implements **JsonDeserializer**_ to convert specific fields. This will render the FieldNamingPolicy redundant.  
      * The purpose of converting to Integer in the database is for **scalable** querying. 
        By converting the Strings to Integer at the initial seeding, these types will not need to be converted every time a filter is imposed. 
        For example, published between the year 1990 and 2000.


## TODO
* I think it would be better practice and a learning opportunity to convert and store Integers in the database for appropriate fields.
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

