# Film_Datenbank_Projekt
#### 1) Before you can run it you will need to install the **mysql connector** without it you cant connect to the database.

Link to download the sql connector:

https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.0.33/mysql-connector-j-8.0.33.jar

#### 2) After Downloading you will need to add it to the Projekt structure. 

Structure **-->** Modules **-->** add using the **+** then press **Apply**


#### 3) the SQL command needed to create the Database table.
```sql
CREATE TABLE films (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    director VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL,
    releaseYear INT NOT NULL,
    runTime INT NOT NULL
);

```
