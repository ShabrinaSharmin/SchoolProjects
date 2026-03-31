-- 1. Hotel Chain
CREATE TABLE hotel_chain (
    hotelChain_id INTEGER PRIMARY KEY,
    name VARCHAR(20),
    street VARCHAR(50),
    city VARCHAR(50),
    province VARCHAR(50),
    zipcode VARCHAR(50),
    country VARCHAR(50),
    number_hotels INT,
    contact_email VARCHAR(50),
    contact_phone VARCHAR (50)
);

-- 2. Hotel
CREATE TABLE hotel (
    hotel_id INTEGER PRIMARY KEY,
    name VARCHAR (20),
   street VARCHAR(50),
    city VARCHAR(50),
    province VARCHAR(50),
    zipcode VARCHAR(50),
    country VARCHAR(50),
    number_rooms INT,
    categorization INT CHECK (categorization >= 1 AND categorization <= 5),
    contact_email VARCHAR (50),
    contact_phone VARCHAR (50),
    manager_id INT,
    hotelChain_id INT,
    FOREIGN KEY (hotelChain_id) REFERENCES hotel_chain(hotelChain_id) ON DELETE CASCADE
);

-- 3. Room
CREATE TABLE room (
    room_number INT PRIMARY KEY,
    price NUMERIC(6,2) NOT NULL,
    amenities VARCHAR(200),
    capacity VARCHAR(100),
    has_sea_view BOOLEAN,
    has_mountain_view BOOLEAN,
    extendable BOOLEAN,
    damages VARCHAR(50),
    hotel_id INT,
    FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id) ON DELETE CASCADE
);

-- 4. Customer
CREATE TABLE customer (
    customer_id INT PRIMARY KEY,
    first_name VARCHAR (20),
    last_name VARCHAR(20),
   street VARCHAR(50),
    city VARCHAR(50),
    province VARCHAR(50),
    zipcode VARCHAR(50),
    country VARCHAR(50),
    ssn VARCHAR (20) UNIQUE,
    registration_date DATE
);

-- 5. Employee
CREATE TABLE employee (
    employee_id INT NOT NULL,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    street VARCHAR(50),
    city VARCHAR(50),
    province VARCHAR(50),
    zipcode VARCHAR(50),
    country VARCHAR(50),
    ssn VARCHAR(20) UNIQUE,
    hotel_id INT,
    PRIMARY KEY( employee_id),
    FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id)
);

-- 6. Role
CREATE TABLE role (
    role_id INT,
    role_name VARCHAR(20),
     PRIMARY KEY (role_id),
    );

-- 7. Employee_Role table
CREATE TABLE employee_role (
    employee_id INT,
    role_id INT,
    PRIMARY KEY (employee_id, role_id),
    FOREIGN KEY (employee_id) REFERENCES employee(employee_id),
    FOREIGN KEY (role_id) REFERENCES role(role_id)
);

-- 8. Booking
CREATE TABLE booking (
    booking_id INT PRIMARY KEYAUTO_INCREMENT ,
    booking_date DATE,
    check_in_date DATE,
    check_out_date DATE,
    customer_id INT,
    room_number INT,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (room_number) REFERENCES room(room_number)
);

-- 9. Renting
CREATE TABLE renting (
    renting_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    check_in_date DATE,
    check_out_date DATE,
    payment NUMERIC(9,2) NOT NULL,
    payment_date DATE,
    customer_id INTEGER,
    room_number INTEGER,
    booking_id INTEGER,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (room_number) REFERENCES room(room_number),
    FOREIGN KEY (booking_id) REFERENCES booking(booking_id)
);

-- 10. Archive
CREATE TABLE archive (
    archive_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    check_in_date DATE,
    check_out_date DATE,
    customer_id INTEGER,
    room_number INTEGER,
    room_price NUMERIC(9,2),
    room_amenities VARCHAR(20),
    room_capacity VARCHAR(100),
    room_has_sea_view BOOLEAN,
    room_has_mountain_view BOOLEAN,
    room_extendable BOOLEAN,
    renting_id INTEGER,
    booking_id INTEGER,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
       );

ALTER TABLE hotel
ADD FOREIGN KEY (manager_id) REFERENCES employee(employee_id);
