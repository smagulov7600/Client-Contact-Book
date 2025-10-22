This application provides a user-friendly interface for managing client information in a MySQL database.

Dependencies:
- **JDK 25**

Libraries and APIs used:
- Java Database Connectivity API connects the application to a database.
- UI is built with the Java Swing library.

Functionality and structure:
- The application firstly creates an Authorization panel, where users must provide valid MySQL credentials.
- The credentials are passed to the MySQL application for validation and a connection is established if the credentials are correct.
- After successful authentication, users are met with two buttons in the Main Menu panel: **Add Clients** and **List All Clients**
- The Add Client button creates a new menu that allows users to input client details and insert them into the database. 
- The List All Clients button displays a table of all clients, with search functionality for filtering results by name and surname.
- If an issue occurs, the application will provide a user-friendly error message.

Note: the application will only connect to a local database named "clientlist"
