package edu.alonso.studyhelper.model
/**
 * Entities
 * A SQLite table is created for each entity class, and the entity's fields define the table columns. The figure below defines the Subject entity. Several annotations are used:
 *
 * @Entity designates the entity class. The class name is used to name the table unless the optional tableName property specifies a different table name.
 *
 * @PrimaryKey designates which field is the table's primary key. An entity must have at least one field annotated with @PrimaryKey. Typically the primary key is an integer or long field. Setting the autoGenerate property to true makes SQLite automatically generate unique numbers for the primary key.
 *
 * @NonNull indicates the field should not be null. SQLite does not allow a primary key to be null.
 *
 * @ColumnInfo with the name property specifies a column name for a field. If @ColumnInfo is not present, the field's name is used to name the column.
 */

// Import for @NonNull annotation to indicate a field cannot be null
import androidx.annotation.NonNull
// Import for @ColumnInfo annotation to specify a column name for a field
import androidx.room.ColumnInfo
// Import for @Entity annotation to designate a class as a Room entity (table)
import androidx.room.Entity
// Import for @PrimaryKey annotation to designate a field as the primary key
import androidx.room.PrimaryKey

// Marks the class as an entity, creating a corresponding table in the database
@Entity
// Defines a data class named Subject, which will be our table
data class Subject (
    // Specifies this field as the primary key, with auto-generating values
    @PrimaryKey(autoGenerate = true)
    
    // Indicates that the 'id' field cannot be null
    @NonNull
    // Defines the 'id' field of type Long as the primary key, with a default value
    var id: Long = 0,
    // Defines a 'text' field of type String for the subject's name/description
    var text: String,
    
    // Specifies that the 'updateTime' field should be named "updated" in the table
    @ColumnInfo(name = "updated")
    // Defines a field to store the last update timestamp, defaulting to the current time
    var updateTime: Long = System.currentTimeMillis()
)