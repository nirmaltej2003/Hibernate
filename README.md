# Hibernate Overview

**Hibernate** is an **Object-Relational Mapping (ORM) framework** for Java. It simplifies database operations by mapping Java objects to database tables and vice versa, reducing the need for complex SQL queries.

## Key Features

- **ORM Framework:** Maps Java classes to database tables.
- **Database Independent:** Works with any relational database.
- **Automatic Table Creation:** Can generate tables automatically based on Java classes.
- **HQL (Hibernate Query Language):** Object-oriented query language for database operations.
- **Caching:** Supports first-level and second-level caching for performance.
- **Transaction Management:** Integrates with JTA for declarative transactions.
- **Lazy Loading:** Loads data only when needed, improving performance.
- **Annotations Support:** Can configure mappings using annotations or XML files.

## Hibernate Architecture

1. **Configuration:** Defines database connection properties and Hibernate settings (`hibernate.cfg.xml` or annotations).  
2. **SessionFactory:** Creates `Session` objects for interacting with the database.  
3. **Session:** Represents a single unit of work with the database (CRUD operations).  
4. **Transaction:** Ensures data integrity during create, update, delete operations.  
5. **Query:** Allows executing HQL, SQL, or Criteria queries.  
6. **Persistent Objects:** Java objects mapped to database tables.

## Basic Workflow

1. Configure Hibernate (`hibernate.cfg.xml`)  
2. Create Java entity class and map it to a table  
3. Obtain `SessionFactory` and `Session`  
4. Begin `Transaction`  
5. Perform CRUD operations  
6. Commit `Transaction` and close `Session`

## Example

```java
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

// Entity class
import javax.persistence.*;

@Entity
@Table(name = "Employee")
class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double salary;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
}

public class HibernateDemo {
    public static void main(String[] args) {
        // Load configuration and build session factory
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class);
        SessionFactory factory = cfg.buildSessionFactory();

        // Obtain session
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // Create an employee object
        Employee emp = new Employee();
        emp.setName("Nirmal");
        emp.setSalary(50000);

        // Save object
        session.save(emp);

        // Commit transaction
        tx.commit();

        // Close session
        session.close();
        factory.close();

        System.out.println("Employee saved successfully!");
    }
}
