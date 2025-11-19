# How to Update Password in Database

## Step 1: Generate the Password Hash

First, run the `PasswordUtility.java` to get the BCrypt hash for "Sush69":

**In your IDE:**
1. Right-click on `PasswordUtility.java`
2. Select "Run" or "Run 'PasswordUtility.main()'"
3. Copy the generated hash from the console output

**OR using command line:**
```bash
cd "C:\Users\naika\Downloads\Nasa-API-Implementation-main"
mvn compile exec:java -Dexec.mainClass="com.openapi.nasa.util.PasswordUtility"
```

## Step 2: Connect to MySQL Database

Based on your `application.properties`, your database settings are:
- **Host**: localhost:3306
- **Database**: nasa-db
- **Username**: root
- **Password**: Root4mysql

### Method A: Using MySQL Command Line

1. Open Command Prompt or PowerShell
2. Navigate to MySQL bin directory (if MySQL is in PATH, skip to step 3):
   ```cmd
   cd "C:\Program Files\MySQL\MySQL Server 8.0\bin"
   ```
   (Adjust path based on your MySQL installation)

3. Connect to MySQL:
   ```cmd
   mysql -u root -p
   ```
   When prompted, enter password: `Root4mysql`

4. Select the database:
   ```sql
   USE nasa-db;
   ```

5. Update the password (replace `YOUR_HASH_HERE` with the hash from Step 1):
   ```sql
   UPDATE nasa_members 
   SET pw = '{bcrypt}YOUR_HASH_HERE' 
   WHERE user_id = 'guest';
   ```

   Or for admin:
   ```sql
   UPDATE nasa_members 
   SET pw = '{bcrypt}YOUR_HASH_HERE' 
   WHERE user_id = 'admin';
   ```

6. Verify the update:
   ```sql
   SELECT user_id, LEFT(pw, 20) as password_preview FROM nasa_members;
   ```

7. Exit MySQL:
   ```sql
   EXIT;
   ```

### Method B: Using MySQL Workbench

1. Open **MySQL Workbench**
2. Click on the connection (or create a new connection):
   - Host: `localhost`
   - Port: `3306`
   - Username: `root`
   - Password: `Root4mysql`
3. Click "Connect"
4. In the query window, select the database:
   ```sql
   USE nasa-db;
   ```
5. Run the UPDATE statement:
   ```sql
   UPDATE nasa_members 
   SET pw = '{bcrypt}YOUR_HASH_HERE' 
   WHERE user_id = 'guest';
   ```
   (Replace `YOUR_HASH_HERE` with the hash from Step 1)
6. Click the execute button (⚡) or press Ctrl+Enter
7. You should see "1 row(s) affected"

### Method C: Using Command Line with Direct Connection

You can also do it in one command:

```cmd
mysql -u root -pRoot4mysql nasa-db -e "UPDATE nasa_members SET pw = '{bcrypt}YOUR_HASH_HERE' WHERE user_id = 'guest';"
```

(Replace `YOUR_HASH_HERE` with the actual hash)

## Step 3: Test Login

After updating:
1. Start your Spring Boot application
2. Go to: `http://localhost:5000/show-login-page`
3. Login with:
   - Username: `guest` (or `admin`)
   - Password: `Sush69`

## Quick Example

If the generated hash is: `$2a$10$ABC123...XYZ789`

Then your SQL would be:
```sql
UPDATE nasa_members 
SET pw = '{bcrypt}$2a$10$ABC123...XYZ789' 
WHERE user_id = 'guest';
```

## Troubleshooting

**If you get "Access denied":**
- Make sure MySQL is running
- Verify the password `Root4mysql` is correct
- Check if MySQL service is started: `services.msc` → find "MySQL"

**If database doesn't exist:**
- Create it: `CREATE DATABASE `nasa-db`;`
- Then run the SQL script: `nasaSqlScripts/01.sql`

**If you can't find MySQL command line:**
- Install MySQL Command Line Client separately, OR
- Use MySQL Workbench (easier GUI option)

