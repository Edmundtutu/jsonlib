# JsonLib - A Simple Java JSON Library

A lightweight Java library that provides simplified JSON operations using Jackson ObjectMapper. This library offers easy-to-use methods for JSON serialization, deserialization, and manipulation with built-in null checks and exception handling.

## Features

- Simple JSON serialization and deserialization
- Stream-based JSON operations
- Pretty printing support
- Null safety checks
- Type-safe operations
- Custom date format support
- Inclusion strategy configuration

## Getting Started

### Option 1: Direct JAR Usage
1. Download one of the following JAR files:
   - `original-jsonlib-1.0-SNAPSHOT.jar` - Lite version (requires manual addition of dependencies)
   - `jsonlib-1.0-SNAPSHOT.jar` - Fat JAR with all dependencies included
2. Add the JAR to your project's dependencies:
   - **IntelliJ IDEA:**
     1. File → Project Structure → Modules
     2. Click '+' under Dependencies tab
     3. Select "JARs or directories"
     4. Navigate to and select the downloaded JAR
   - **Eclipse:**
     1. Right-click project → Build Path → Configure Build Path
     2. Under Libraries tab, click "Add External JARs"
     3. Navigate to and select the downloaded JAR
   - **Manual (Add to lib folder):**
     1. Create a `lib` folder in your project
     2. Copy the JAR file into the `lib` folder
     3. Add to classpath: `java -cp "lib/*" YourMainClass`

### Option 2: Local Maven Repository
If you have access to the source code:

1. Clone the repository:
```bash
git clone https://github.com/Edmundtutu/jsonlib.git
```

2. Install to local Maven repository:
```bash
cd jsonlib
mvn clean install
```

3. Add to your project's `pom.xml`:
```xml
<dependency>
    <groupId>com.app</groupId>
    <artifactId>jsonlib</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

### Option 3: Maven Central Repository (Coming Soon)
Once published to Maven Central, you'll be able to simply add this to your `pom.xml`:
```xml
<dependency>
    <groupId>com.app</groupId>
    <artifactId>jsonlib</artifactId>
    <version>1.0</version>
</dependency>
```
## Dependencies Required
If you're using the original JAR (non-fat version), make sure you have these dependencies. If you downloaded the fat JAR, you can skip this section as all dependencies are included:
```xml
<dependencies>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-core</artifactId>
        <version>2.15.0</version>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.15.0</version>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-annotations</artifactId>
        <version>2.15.0</version>
    </dependency>
</dependencies>
```

## Usage

### Example Event Class

```java
public class Event {
    private String id;
    private String title;
    private String description;
    private LocalDateTime dateTime;
    private String type;
    private String location;
    private int maxPatients;
    private boolean isVirtual;
    // Constructors, getters, and setters
}
```

### Basic Serialization

```java
// Create a gynecologic event
Event event = new Event();
event.setId("gyn-001");
event.setTitle("Patients Health Screening");
event.setDescription("Annual gynecologic examination and consultation");
event.setDateTime(LocalDateTime.now());
event.setType("SCREENING");
event.setLocation("Main Clinic - Room 105");
event.setMaxPatients(20);
event.setVirtual(false);

// Convert event to JSON string
String json = JsonUtil.toJson(event);
```

### Basic Deserialization

```java
// Convert JSON string to Event object
String json = """
    {
        "id": "gyn-001",
        "title": "Patient's Health Screening",
        "description": "Annual gynecologic examination and consultation",
        "dateTime": "2024-03-19T09:00:00",
        "type": "SCREENING",
        "location": "Main Clinic - Room 105",
        "maxPatients": 20,
        "isVirtual": false
    }
    """;
Event event = JsonUtil.fromJson(json, Event.class);
```

### Pretty Printing

```java
// Create write options with pretty printing enabled
WriteOptions options = new WriteOptions()
    .setPrettyPrint(true);

// Write event to file with pretty printing
try (FileOutputStream fos = new FileOutputStream("event.json")) {
    JsonUtil.toJsonFile(fos, event, options);
}
```

### Reading from File

```java
// Read event from file
try (FileInputStream fis = new FileInputStream("event.json")) {
    Event event = JsonUtil.fromJsonFile(fis, Event.class);
}
```

### Working with Collections

```java
// Convert list of events to JSON
List<Event> events = Arrays.asList(
    new Event("gyn-001", "Prenatal Consultation", LocalDateTime.now()),
    new Event("gyn-002", "Mammography Screening", LocalDateTime.now().plusDays(1))
);
String json = JsonUtil.toJson(events);

// Convert JSON to list of events
List<Event> eventList = JsonUtil.fromJson(json, 
    new TypeReference<List<Event>>() {});
```

### Sample Output

When running the serialization example above, the output JSON would look like this:

```json
{
    "id": "gyn-001",
    "title": "Patients Health Screening",
    "description": "Annual gynecologic examination and consultation",
    "dateTime": "2024-03-19T09:00:00",
    "type": "SCREENING",
    "location": "Main Clinic - Room 105",
    "maxPatients": 20,
    "isVirtual": false
}
```

When using pretty printing with `WriteOptions`:

```json
{
    "id": "gyn-001",
    "title": "Patients Health Screening",
    "description": "Annual gynecologic examination and consultation",
    "dateTime": "2024-03-19T09:00:00",
    "type": "SCREENING",
    "location": "Main Clinic - Room 105",
    "maxPatients": 20,
    "isVirtual": false
}
```

For the collection example, the output would be:

```json
[
    {
        "id": "gyn-001",
        "title": "Prenatal Consultation",
        "dateTime": "2024-03-19T09:00:00"
    },
    {
        "id": "gyn-002",
        "title": "Mammography Screening",
        "dateTime": "2024-03-20T09:00:00"
    }
]
```

## Configuration Options

The `WriteOptions` class provides several configuration options:

```java
WriteOptions options = new WriteOptions()
    .setPrettyPrint(true)          // Enable pretty printing
    .setIgnoreNull(true)           // Ignore null values
    .setDateFormat(dateFormat)     // Set custom date format
    .setInclusion(Include.NON_NULL); // Set inclusion strategy
```

## Error Handling

The library uses runtime exceptions for error handling:

```java
try {
    Event event = JsonUtil.fromJson(invalidJson, Event.class);
} catch (RuntimeException e) {
    // Handle JSON parsing error
    System.err.println("Error parsing JSON: " + e.getMessage());
}
```


## Requirements

- Java 17 or higher
- Jackson Core (2.15.0)
- Jackson Databind (2.15.0)
- Jackson Annotations (2.15.0)

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/your-amazing-feature`)
3. Commit your changes (`git commit -m 'Add some your amazing feature'`)
4. Push to the branch (`git push origin feature/your-amazing-feature`)
5. Open a Pull Request


## Author

Edmund Tutuma

## Version

1.0-SNAPSHOT (2024-03-19)
