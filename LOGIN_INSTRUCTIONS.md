# Login Instructions for NASA API Implementation

## Default Users

Based on the SQL script (`nasaSqlScripts/01.sql`), the following users exist in the database:

1. **Username**: `guest`
   - **Roles**: ROLE_EMPLOYEE, ROLE_MANAGER

2. **Username**: `admin`
   - **Roles**: ROLE_EMPLOYEE, ROLE_MANAGER, ROLE_ADMIN

## Important Note About Passwords

The passwords are stored as **BCrypt hashes** in the database, which means:
- The original plain text passwords **cannot be recovered** from the hashes
- BCrypt is a one-way encryption algorithm designed for security
- You cannot "decode" or "reverse" a BCrypt hash

## How to Login

### Option 1: Try Common Passwords

Try these common default passwords that might have been used:
- `password`
- `admin`
- `guest`
- `1234`
- `root`
- `test`

**Login Steps:**
1. Start your application
2. Navigate to: `http://localhost:5000/show-login-page`
3. Try username: `guest` or `admin` with the common passwords above

### Option 2: Generate a New Password Hash

If you can't log in with common passwords, you can set a new password:

1. **Run the Password Utility:**
   ```bash
   # Compile and run the utility
   mvn compile exec:java -Dexec.mainClass="com.openapi.nasa.util.PasswordUtility"
   ```

2. **Or manually create a hash** by running the `PasswordUtility.java` main method in your IDE

3. **Update the database** with your new password hash:
   ```sql
   UPDATE nasa_members 
   SET pw = '{bcrypt}YOUR_GENERATED_HASH_HERE' 
   WHERE user_id = 'guest';
   ```
   
   Or for admin:
   ```sql
   UPDATE nasa_members 
   SET pw = '{bcrypt}YOUR_GENERATED_HASH_HERE' 
   WHERE user_id = 'admin';
   ```

### Option 3: Check Database Directly

You can check if the database is set up correctly:
```sql
USE `nasa-db`;
SELECT user_id, pw, active FROM nasa_members;
SELECT user_id, role FROM nasa_roles;
```

## Login URL

- **Custom Login Page**: `http://localhost:5000/show-login-page`
- **Login Processing URL**: `/authenticateTheUser` (POST request)
- **Home Page** (after login): `http://localhost:5000/nasa/home-page`

## Password Utility

A utility class has been created at:
- `src/main/java/com/openapi/nasa/util/PasswordUtility.java`

This utility allows you to:
- Generate BCrypt hashes for any password
- Get the SQL update statements ready to use

To use it:
1. Open `PasswordUtility.java`
2. Change the `password` variable to your desired password
3. Run the `main` method
4. Copy the generated hash and SQL statement
5. Execute the SQL statement in your MySQL database

## Troubleshooting

1. **Make sure your database is running** and the connection is correct in `application.properties`
2. **Verify the SQL script has been executed** - run `nasaSqlScripts/01.sql` in your MySQL database
3. **Check database name** - Ensure your database is named `nasa-db` (as specified in application.properties)
4. **Verify users exist** - Run: `SELECT * FROM nasa_members;` to see if users are present

## Quick Setup SQL

If you need to reset or create users, use this SQL script:
```sql
USE `nasa-db`;

-- Clear existing data (if needed)
DELETE FROM nasa_roles;
DELETE FROM nasa_members;

-- Insert users with new password hashes
-- Replace {bcrypt}... with hashes generated from PasswordUtility
INSERT INTO nasa_members VALUES
('guest','{bcrypt}$2a$10$4cnbKbq2zROx3zBZ9xMh6uRNHuSvDpWVmwz17PmvogpWxb1uUh18m',1),
('admin','{bcrypt}$2a$10$uAu7BPIIZpXC.H1hJp453.Bsr/iJ7pYOP3GkXbA7m04Gc69nMc.n6',1);

INSERT INTO nasa_roles VALUES
('guest','ROLE_EMPLOYEE'),
('guest','ROLE_MANAGER'),
('admin','ROLE_EMPLOYEE'),
('admin','ROLE_MANAGER'),
('admin','ROLE_ADMIN');
```

