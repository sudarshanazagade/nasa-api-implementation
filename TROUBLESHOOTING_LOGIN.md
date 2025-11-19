# Troubleshooting Login Issues

## Issue: "Invalid username and password" after updating password

### Step 1: Verify Database Update Was Committed

Run this SQL in MySQL to check if the password was actually updated:

```sql
USE nasa-db;
SELECT user_id, pw FROM nasa_members WHERE user_id = 'guest';
```

**Expected Output:**
The `pw` column should show: `{bcrypt}$2a$10$TTn4KJAW6FVtwdWmYt8bdOqXJgve20YQZXAbtkxIX9.CqJ4C/sRmi`

**If it doesn't match:**
- The UPDATE statement might not have been executed properly
- You might need to commit the transaction
- Try running the UPDATE again

### Step 2: Check Column Size Issue

The `pw` column is `char(68)`. A BCrypt hash with `{bcrypt}` prefix is exactly 68 characters.
Make sure there's no truncation:

```sql
USE nasa-db;
SELECT user_id, LENGTH(pw) as password_length, pw FROM nasa_members WHERE user_id = 'guest';
```

The length should be **68**. If it's less, the hash was truncated and won't work.

### Step 3: Restart Your Spring Boot Application

**IMPORTANT:** After updating the database, you MUST restart your Spring Boot application!

1. **Stop the application:**
   - If running in terminal: Press `Ctrl+C`
   - If running in IDE: Stop the running application

2. **Start it again:**
   ```bash
   mvn spring-boot:run
   ```
   OR run it from your IDE again

3. **Wait for it to fully start** (look for "Started NasaApiApplication")

4. **Try logging in again**

### Step 4: Verify Hash Format

Make sure the password hash in the database has:
- The `{bcrypt}` prefix at the start
- No extra spaces or characters
- The complete hash

Run this to see exactly what's stored:

```sql
USE nasa-db;
SELECT user_id, 
       pw, 
       LENGTH(pw) as length,
       LEFT(pw, 8) as prefix
FROM nasa_members 
WHERE user_id = 'guest';
```

The prefix should be exactly `{bcrypt}` (8 characters).

### Step 5: Re-run the UPDATE with COMMIT

If the UPDATE didn't work, try this:

```sql
USE nasa-db;

-- Check current value
SELECT user_id, pw FROM nasa_members WHERE user_id = 'guest';

-- Update with explicit commit
START TRANSACTION;
UPDATE nasa_members 
SET pw = '{bcrypt}$2a$10$TTn4KJAW6FVtwdWmYt8bdOqXJgve20YQZXAbtkxIX9.CqJ4C/sRmi' 
WHERE user_id = 'guest';
COMMIT;

-- Verify
SELECT user_id, pw FROM nasa_members WHERE user_id = 'guest';
```

### Step 6: Check Application Logs

When you try to login, check the terminal/console where your Spring Boot app is running.
Look for any error messages or warnings that might indicate:
- Database connection issues
- Authentication failures
- Password encoding problems

### Step 7: Alternative - Check if BCrypt Hash is Correct

Test if the hash matches your password programmatically:

```java
// Quick test in PasswordUtility
BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
String storedHash = "$2a$10$TTn4KJAW6FVtwdWmYt8bdOqXJgve20YQZXAbtkxIX9.CqJ4C/sRmi";
boolean matches = encoder.matches("Sush69", storedHash);
System.out.println("Password matches: " + matches); // Should be true
```

## Common Issues:

1. **"I updated but still can't login"**
   - Did you restart the application? ← Most common issue!
   - Did you commit the transaction?
   - Is the hash complete (68 characters)?

2. **"Hash seems truncated"**
   - Increase column size: `ALTER TABLE nasa_members MODIFY pw VARCHAR(100);`

3. **"Connection refused"**
   - Make sure MySQL is running
   - Check `application.properties` database settings

4. **"Application won't start"**
   - Check MySQL connection
   - Verify database `nasa-db` exists

