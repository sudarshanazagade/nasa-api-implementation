# How to Run PasswordUtility.java in Cursor IDE

## Method 1: Using Terminal in Cursor (RECOMMENDED) ⭐

1. **Open Terminal in Cursor:**
   - Press `` Ctrl + ` `` (backtick) OR
   - Go to **Terminal** → **New Terminal** from the menu

2. **Run this command:**
   ```bash
   mvn compile exec:java -Dexec.mainClass="com.openapi.nasa.util.PasswordUtility"
   ```

3. **Copy the hash** from the output - it will look like:
   ```
   BCrypt Hash: $2a$10$ABC123XYZ...
   ```

4. **Use the UPDATE statement** that's printed in the output!

---

## Method 2: Using "Run Java" Button (If Java Extension Installed)

1. **Open** `src/main/java/com/openapi/nasa/util/PasswordUtility.java`

2. **Look for a "Run" or "▶" button** above the `main` method:
   - If you see a **▶ Run** or **▶ Debug** button, click it
   - If you see **Run | Debug** options, click **Run**

3. **If you don't see the button:**
   - Install **Extension Pack for Java** from Microsoft
   - Go to Extensions (Ctrl+Shift+X)
   - Search "Java Extension Pack"
   - Install it
   - Then you'll see the Run button

---

## Method 3: Right-Click Method

1. **Open** `PasswordUtility.java` in the editor

2. **Right-click anywhere in the file** (or on the `main` method)

3. **Look for these options:**
   - **"Run Java"** → Click it
   - OR **"Run Code"** → Click it (if Code Runner extension is installed)

---

## Method 4: Command Palette

1. **Press** `Ctrl+Shift+P` (or `Cmd+Shift+P` on Mac)

2. **Type:** `Java: Run`

3. **Select:** `Java: Run` or `Run Java`

4. **Select the file:** Choose `PasswordUtility.java`

---

## Method 5: Using Code Runner Extension

1. **Install Code Runner extension:**
   - Press `Ctrl+Shift+X`
   - Search "Code Runner"
   - Install by Jun Han

2. **Open** `PasswordUtility.java`

3. **Press** `Ctrl+Alt+N` OR click the **▶ Run Code** button at the top right

**Note:** This might not work with Spring dependencies. Use Method 1 instead.

---

## ✅ RECOMMENDED: Use Method 1 (Terminal with Maven)

This is the most reliable method because:
- ✅ It handles all Spring dependencies automatically
- ✅ Works on Windows/Mac/Linux
- ✅ No extensions needed
- ✅ You're already in the project directory

### Quick Steps:
```
1. Press Ctrl + ` (to open terminal)
2. Type: mvn compile exec:java -Dexec.mainClass="com.openapi.nasa.util.PasswordUtility"
3. Press Enter
4. Copy the hash from output
```

---

## Troubleshooting

**"mvn command not found":**
- Make sure Maven is installed and in PATH
- Or use: `./mvnw compile exec:java -Dexec.mainClass="com.openapi.nasa.util.PasswordUtility"` (uses Maven wrapper)

**"Cannot resolve BCryptPasswordEncoder":**
- Make sure you're using Method 1 (Maven), as it downloads dependencies
- Run `mvn clean install` first to download dependencies

**Nothing happens when clicking Run:**
- Use Method 1 (Terminal) instead
- It's the most reliable method

