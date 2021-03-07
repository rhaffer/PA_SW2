package model;

import java.time.LocalDateTime;

public class Appointment {
    private int appointment_id; // PK INT(10)
    private String title; // VARCHAR (50)
    private String description; // VARCHAR (50)
    private String location; // VARCHAR(50)
    private String type; // VARCHAR(50)
    private LocalDateTime start; // DATETIME
    private LocalDateTime end; // DATETIME
    private LocalDateTime create_date; // DATETIME
    private String created_by; // VARCHAR(50)
    private String last_update; // TIMESTAMP
    private String last_updated_by; // VARCHAR(50)
    private int customer_id; // FK INT(10)
    private int user_id; // FK INT(10)
    private int contact_id; // FK INT(10)

    // Constructor to create object from non-populating fields
    // Ex: Sending an insert to DB
    public Appointment(String new_title, String new_description, String new_location, String new_type,
                       LocalDateTime new_start, LocalDateTime new_end, LocalDateTime new_create_date, String new_created_by,
                       String new_last_update, String new_last_updated_by, int new_customer_id, int new_user_id,
                       int new_contact_id){
        title = new_title;
        description = new_description;
        location = new_location;
        type = new_type;
        start = new_start;
        end = new_end;
        create_date = new_create_date;
        created_by = new_created_by;
        last_update = new_last_update;
        last_updated_by = new_last_updated_by;
        customer_id = new_customer_id;
        user_id = new_user_id;
        contact_id = new_contact_id;
    }

    // Constructor to create object from all fields
    // Ex: Retrieving data from DB
    public Appointment(int new_appointment_id, String new_title, String new_description, String new_location, String new_type,
                       LocalDateTime new_start, LocalDateTime new_end, LocalDateTime new_create_date, String new_created_by,
                       String new_last_update, String new_last_updated_by, int new_customer_id, int new_user_id,
                       int new_contact_id){
        appointment_id = new_appointment_id;
        title = new_title;
        description = new_description;
        location = new_location;
        type = new_type;
        start = new_start;
        end = new_end;
        create_date = new_create_date;
        created_by = new_created_by;
        last_update = new_last_update;
        last_updated_by = new_last_updated_by;
        customer_id = new_customer_id;
        user_id = new_user_id;
        contact_id = new_contact_id;
    }

    // Getters
    public int getAppointmentID() { return appointment_id; }
    public String getTitle() { return title;}
    public String getDescription(){ return description; }
    public String getLocation(){ return location; }
    public String getType(){ return type; }
    public LocalDateTime getStartTime(){ return start; }
    public LocalDateTime getEndTime(){ return end; }
    public LocalDateTime getCreateDate() { return create_date; }
    public String getCreatedBy() { return created_by; }
    public String getLastUpdate() { return last_update; }
    public String getLastUpdatedBy() { return last_updated_by; }
    public int getCustomerID() { return customer_id; }
    public int getUserID() { return user_id; }
    public int getContactID() { return contact_id; }
    // Setters
    public void setAppointmentID(int id){ appointment_id = id;}
    public void setTitle(String newTitle) { title = newTitle; }
    public void setDescription(String newDesc){ description = newDesc; }
    public void setLocation(String newLocation){ location = newLocation; }
    public void setType(String newType){ type = newType;}
    public void setStartTime(LocalDateTime newStart) { start = newStart; }
    public void setEndTime(LocalDateTime newEnd){ end = newEnd;}
    public void setCreateDate(LocalDateTime newCreateDate){ create_date = newCreateDate; }
    public void setCreatedBy(String newCreatedBy){ created_by = newCreatedBy; }
    public void setLastUpdate(String newUpdate){ last_update = newUpdate; }
    public void setLastUpdatedby(String newUser){ last_updated_by = newUser; }
    public void setCustomerID (int newCust){ customer_id = newCust; }
    public void setUserID (int newUser){ user_id = newUser; }
    public void setContactID (int newContact) { contact_id = newContact; }
}
