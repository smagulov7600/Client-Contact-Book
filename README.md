This Java application provides a user-friendly interface for managing client information in a MySQL database. 
The application uses JDBC for database connection, adding clients, listing all clients, and searching for specific clients based on their names. 

Functionality and structure:
- The application begins with an Authorization panel, where users must provide MySQL credentials to access an existing database.
- After successful authentication, users can navigate to the Main Menu panel, where they are met with two buttons: **Add Clients** and **List All Clients**
- The Add Client button creates a new menu that allows users to input client details and insert them into the database. 
- The List All Clients panel displays a table of all clients, with search functionality for filtering results by name and surname.
- The application provides user-friendly error messages and ensures data validation.
