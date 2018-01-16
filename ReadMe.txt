To run the project, following are the requirements
-------------------------------------------------------------------------------------------

- MongoDB
- LingPipe toolkit
- Java
- Maven for building and creating dependencies in Java projects
- Spring Tool Suite
- Hadoop
- Eclipse IDE
- Eclipse Hadoop plugin

------------------------------------------------------------------------------------------

TRAINING DATA: Insert training data into trdr folder in the form of 2 files,
	-Positive
	-Negative
 
------------------------------------------------------------------------------------------
To run the first part of project (Data retrieve),following are the steps:

- Obtain twitter's unique keys via registering with Twitter's Developers Access
- Specify the values for following parameters in TwitterConf.java
    - Consumer Key
    - Consumer Secret Key
    - Access Tokem key
    - Access Secret Key
- Specify the database name and collection name in MongodbConf.java
- Start Mongo DB
- Execute Main.java from eclipse or Spring Tool Suite
-----------------------------------------------------------------------------------------

To run the second part of project (Polarity),following are the steps:
- Add all the Hadoop/share jar files in the project.
- Set Java and Hadoop class path for LingPipe jar.
- Set appropriate database name and collection name in mongo.xml
- In ClassificationHandler.java, specify the path of the directory having that file.
- Update the project using Maven Update.
- Build the project using Maven Build.
- Copy the SNAPSHOT.jar and paste into classes folder.
- Edit the permissions of SNAPSHOT.jar as 'Read & Write'.
- Execute the project from Spring Tool Suite using Main.java