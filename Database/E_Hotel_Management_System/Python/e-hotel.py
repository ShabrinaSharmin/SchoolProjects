import streamlit as st
import mysql.connector
from mysql.connector import Error
import pandas as pd
import datetime

def create_views(conn):
    cursor = conn.cursor()
    
    view1_query = """
    CREATE OR REPLACE VIEW available_rooms_per_area AS
    SELECT
        h.city AS area,
        COUNT(r.room_number) AS available_rooms
    FROM
        hotel h
    JOIN
        room r ON h.hotel_id = r.hotel_id
    GROUP BY
        h.city;
    """


    view2_query = """
    CREATE OR REPLACE VIEW hotel_room_capacity AS
    SELECT 
        h.hotel_id,
        h.name AS hotel_name,
        SUM(CASE
                WHEN r.capacity = 'Single' THEN 1
                WHEN r.capacity = 'Double' THEN 2
                ELSE 0
            END) AS total_capacity
    FROM 
        hotel h
    JOIN 
        room r ON h.hotel_id = r.hotel_id
    GROUP BY 
        h.hotel_id, h.name;
    """


    try:
        cursor.execute(view1_query)
        cursor.execute(view2_query)
        conn.commit()
        cursor.close()
    except Exception as e:
        print(f"Error creating views: {e}")


# MySQL connection information
schema = "e-hotel"
user = "root"
password = "myrootpassword"
host = "localhost"
port = 3306

# Connect to the MySQL server
try:
    conn = mysql.connector.connect(
        user=user,
        password=password,
        host=host,
        port=port,
        database=schema
    )
    cursor = conn.cursor()
except Error as e:
    st.error(f"Error connecting to MySQL database: {e}")
create_views(conn)




# Streamlit UI
st.title("E-Hotel Management System")

sidebar = st.sidebar
sidebar.header("Menu")

# Define user types
user_types = ["Customer", "Employee"]
user_type = sidebar.selectbox("Select User Type", user_types)

# Define menu options for customer and employee
customer_menu_options = [
    "Manage Your Information",
    "Search and Filter Rooms",
    "Manage Bookings",
]

employee_menu_options = [
    "Hotel Chains",
    "Manage Hotels",
    "Manage Rooms",
    "Search Rooms",
    "Manage Employees",
    "Manage Customers",
    "Manage Bookings",
    "Manage Rentings",
    "Manage Archives",
    "View Reports",
]


# Display relevant menu options based on user type
if user_type == "Customer" :
    menu_choice = sidebar.selectbox("Select an option", customer_menu_options)
else:  # user_type == "Employee"
    menu_choice = sidebar.selectbox("Select an option", employee_menu_options)


    if menu_choice == "View Reports" and user_type == "Employee":
        st.subheader("View Reports")
        report_options = ["Available Rooms Per Area", "Hotel Room Capacity"]
        selected_report = st.selectbox("Select a report", report_options)

        if selected_report == "Available Rooms Per Area":
            query = "SELECT * FROM available_rooms_per_area"
            cursor.execute(query)
            result = cursor.fetchall()

            if result:
                df = pd.DataFrame(result, columns=["Area", "Available Rooms"])
                st.write(df)
            else:
                st.warning("No data found.")

        elif selected_report == "Hotel Room Capacity":
            query = "SELECT * FROM hotel_room_capacity"
            cursor.execute(query)
            result = cursor.fetchall()

            if result:
                df = pd.DataFrame(result, columns=["Hotel ID", "Hotel Name", "Total Capacity"])
                st.write(df)
            else:
                st.warning("No data found.")


if menu_choice == "Manage Your Information" and user_type == "Customer":
    st.subheader("Manage Your Information")
    action = st.selectbox("Action", ["Create", "Update", "Delete"])

    customer_id = st.number_input("Customer ID", min_value=0)
    ssn = st.text_input("SSN")

    # Create new customer information
    if action == "Create":
        first_name = st.text_input("First Name")
        last_name = st.text_input("Last Name")
        street = st.text_input("Street")
        city = st.text_input("City")
        province = st.text_input("Province")
        zipcode = st.text_input("Zipcode")
        country = st.text_input("Country")
        registration_date = st.date_input("Registration Date", datetime.datetime.now().date())

        if st.button("Submit"):
            try:
                query = f"INSERT INTO customer (customer_id, first_name, last_name, street, city, province, zipcode, country, ssn, registration_date) VALUES ({customer_id}, '{first_name}', '{last_name}', '{street}', '{city}', '{province}', '{zipcode}', '{country}', '{ssn}', '{registration_date}')"
                cursor.execute(query)
                conn.commit()
                st.success("Customer information created successfully!")
            except Exception as e:
                st.error(f"Error: {e}")

    # Update customer information
    elif action == "Update":
        if customer_id and ssn:
            query = f"SELECT * FROM customer WHERE customer_id={customer_id} AND ssn='{ssn}'"
            cursor.execute(query)
            user_data = cursor.fetchone()

            if not user_data:
                st.error("User does not exist!")
            else:
                first_name = st.text_input("First Name")
                last_name = st.text_input("Last Name")
                street = st.text_input("Street")
                city = st.text_input("City")
                province = st.text_input("Province")
                zipcode = st.text_input("Zipcode")
                country = st.text_input("Country")

                if st.button("Update"):
                    try:
                        query = f"UPDATE customer SET first_name='{first_name}', last_name='{last_name}', street='{street}', city='{city}', province='{province}', zipcode='{zipcode}', country='{country}' WHERE customer_id={customer_id} AND ssn='{ssn}'"
                        cursor.execute(query)
                        conn.commit()

                        if cursor.rowcount:
                            st.success("Customer information updated successfully!")
                        else:
                            st.warning("No matching record found.")
                    except Exception as e:
                        st.error(f"Error: {e}")

    # Delete customer information
    elif action == "Delete":
        if customer_id and ssn:
            query = f"SELECT * FROM customer WHERE customer_id={customer_id} AND ssn='{ssn}'"
            cursor.execute(query)
            user_data = cursor.fetchone()

            if not user_data:
                st.error("User does not exist!")
            else:
                if st.button("Delete"):
                    try:
                        query = f"DELETE FROM customer WHERE customer_id={customer_id} AND ssn='{ssn}'"
                        cursor.execute(query)
                        conn.commit()

                        if cursor.rowcount:
                            st.success("Customer information deleted successfully!")
                        else:
                            st.warning("No matching record found.")
                    except Exception as e:
                        st.error(f"Error: {e}")

#implement search room function
if menu_choice == "Search and Filter Rooms" and user_type == "Customer":
    st.subheader("Search and Filter Rooms")

    # Search and filter input fields
    check_in_date = st.date_input("Check-in Date")
    check_out_date = st.date_input("Check-out Date", check_in_date + datetime.timedelta(days=1))
    room_capacity = st.selectbox("Room Capacity", ["Single", "Double"])
    country = st.selectbox("Country", [None, "USA", "Canada"])
    city = st.selectbox("City", [None, "Los Angeles", "Toronto", "New York", "Vancouver", "Chicago", "San Diego", "Jacksonville", "Waipahu", "San Jose", "Montreal", "Rossland", "Sebringville", "Victoria", "East Aurora", "Fort Worth", "Ames", "Oakland", "Seattle", "Ottawa", "Edmonton", "Calgary"])
    hotel_chain = st.selectbox("Hotel Chain", [None, "Hotel Chain A", "Hotel Chain B", "Hotel Chain C", "Hotel Chain D", "Hotel Chain E"])
    hotel_category = st.selectbox("Hotel Category (1-5)", [None, 1, 2, 3, 4, 5])
    max_price = st.number_input("Maximum Room Price", min_value=1, value=100)

    if st.button("Search"):
        # Build the query based on input fields
        query = f"""SELECT r.room_number, r.price, r.amenities, r.capacity, r.has_sea_view, r.has_mountain_view, r.extendable, r.damages, r.hotel_id, h.name, h.city, h.province, h.categorization, hc.name as hotel_chain
                    FROM room r
                    JOIN hotel h ON r.hotel_id = h.hotel_id
                    JOIN hotel_chain hc ON h.hotelChain_id = hc.hotelChain_id
                    WHERE r.capacity = '{room_capacity}'
                    AND r.price <= {max_price}
                 """

        if country is not None:
            query += f" AND h.country = '{country}'"
        if city is not None:
            query += f" AND h.city = '{city}'"
        if hotel_chain is not None:
            query += f" AND hc.name = '{hotel_chain}'"
        if hotel_category is not None:
            query += f" AND h.categorization = {hotel_category}"

        cursor.execute(query)
        rooms = cursor.fetchall()

        rooms_df = pd.DataFrame(rooms, columns=["Room Number", "Price", "Amenities", "Capacity", "Sea View", "Mountain View", "Extendable", "Damages", "Hotel ID", "Hotel Name", "City", "Province", "Category", "Hotel Chain"])
        st.dataframe(rooms_df)


#manage booking for customer
if menu_choice == "Manage Bookings" and user_type == "Customer":
    st.subheader("Manage Bookings")

    # Get the customer_id and ssn from the user
    customer_id = st.number_input("Enter your Customer ID", min_value=1)
    ssn = st.text_input("Enter your SSN")

    query = f"SELECT * FROM customer WHERE customer_id={customer_id} AND ssn='{ssn}'"
    cursor.execute(query)
    user_data = cursor.fetchone()

    if not user_data:
        st.error("User does not exist!")
    else:
        action = st.selectbox("Action", ["Create Booking", "Update Booking", "Delete Booking"])

        # Create a new booking
        if action == "Create Booking":
            check_in_date = st.date_input("Check-in Date")
            check_out_date = st.date_input("Check-out Date", check_in_date + datetime.timedelta(days=1))
            room_number = st.number_input("Room Number", min_value=1)

            if st.button("Create Booking"):
                query = f"SELECT COUNT(*) FROM customer WHERE customer_id = {customer_id} AND ssn = '{ssn}'"
                cursor.execute(query)
                count = cursor.fetchone()[0]

                if count == 1:
                    query = f"SELECT COUNT(*) FROM room WHERE room_number = {room_number}"
                    cursor.execute(query)
                    room_count = cursor.fetchone()[0]

                    if room_count == 0:
                        st.error("Room number not found.")
                    else:
                        query = f"""SELECT COUNT(*) FROM booking WHERE room_number = {room_number} AND (
                                    (check_in_date >= '{check_in_date}' AND check_in_date < '{check_out_date}') OR
                                    (check_out_date > '{check_in_date}' AND check_out_date <= '{check_out_date}') OR
                                    (check_in_date < '{check_in_date}' AND check_out_date > '{check_out_date}'))"""
                        cursor.execute(query)
                        booked_count = cursor.fetchone()[0]

                        if booked_count > 0:
                            st.error("Room is not available.")
                        else:
                            query = f"""INSERT INTO booking (booking_date, check_in_date, check_out_date, customer_id, room_number)
                                        VALUES (CURRENT_DATE, '{check_in_date}', '{check_out_date}', {customer_id}, {room_number})"""
                            cursor.execute(query)
                            conn.commit()
                            st.success("Booking created successfully.")
                else:
                    st.error("Invalid SSN or Customer ID.")

            # Update an existing booking
        if action == "Update Booking":
            if customer_id and ssn:
                check_in_date = st.date_input("New Check-in Date")
                check_out_date = st.date_input("New Check-out Date", check_in_date + datetime.timedelta(days=1))
                room_number = st.number_input("New Room Number", min_value=1)

                if st.button("Update Booking"):
                    query = f"SELECT COUNT(*) FROM customer WHERE customer_id = {customer_id} AND ssn = '{ssn}'"
                    cursor.execute(query)
                    count = cursor.fetchone()[0]
                    if count == 1:
                        query = f"SELECT COUNT(*) FROM room WHERE room_number = {room_number}"
                        cursor.execute(query)
                        room_count = cursor.fetchone()[0]

                        if room_count == 0:
                            st.error("Room number not found.")
                        else:
                            query = f"""SELECT COUNT(*) FROM booking WHERE room_number = {room_number} AND (
                                        (check_in_date >= '{check_in_date}' AND check_in_date < '{check_out_date}') OR
                                        (check_out_date > '{check_in_date}' AND check_out_date <= '{check_out_date}') OR
                                        (check_in_date < '{check_in_date}' AND check_out_date > '{check_out_date}')) AND
                                        customer_id != {customer_id}"""
                            cursor.execute(query)
                            booked_count = cursor.fetchone()[0]

                            if booked_count > 0:
                                st.error("Room is not available.")
                            else:
                                query = f"""UPDATE booking SET check_in_date = '{check_in_date}', check_out_date = '{check_out_date}', room_number = {room_number}
                                            WHERE customer_id = {customer_id}"""
                                cursor.execute(query)
                                conn.commit()
                                if cursor.rowcount:
                                    st.success("Booking updated successfully.")
                                else:
                                    st.warning("No matching record found.")

                    else:
                        st.error("Invalid SSN or Customer ID.")

            # Delete a booking
        if action == "Delete Booking":
            if st.button("Delete Booking"):
                query = f"""DELETE FROM booking WHERE customer_id = {customer_id}"""
                cursor.execute(query)
                conn.commit()

                if cursor.rowcount:
                    st.success("Booking(s) deleted successfully.")
                else:
                    st.warning("No matching record found.")


# Manage Hotel Chains
if menu_choice == "Hotel Chains" and user_type == "Employee":
    st.subheader("Hotel Chains")
    action = st.selectbox("Action", ["View", "Insert", "Update", "Delete"])

    if action == "View":
        query = "SELECT hotelChain_id, name, street, city, province, zipcode, country, number_hotels, contact_email, contact_phone FROM hotel_chain"
        cursor.execute(query)
        hotel_chains = cursor.fetchall()

        hotel_chains_df = pd.DataFrame(hotel_chains, columns=["hotel_chain_id", "name", "street", "city", "province", "zipcode", "country", "number_of_hotels", "contact_email", "contact_phone"])
        st.dataframe(hotel_chains_df)

    elif action == "Insert":
        hotel_chain_id = st.number_input("Hotel Chain ID", min_value=0)
        name = st.text_input("Name")
        street = st.text_input("Street")
        city = st.text_input("City")
        province = st.text_input("Province")
        zipcode = st.text_input("Zipcode")
        country = st.text_input("Country")
        number_hotels = st.number_input("Number of Hotels", min_value=0)
        contact_email = st.text_input("Contact Email")
        contact_phone = st.text_input("Contact Phone")

        if st.button("Insert"):
            # Check if hotelChain_id already exists
            query = f"SELECT hotelChain_id FROM hotel_chain WHERE hotelChain_id = {hotel_chain_id}"
            cursor.execute(query)
            result = cursor.fetchone()

            if result:
                st.error("The hotelChain_id already exists. Please choose a different ID.")
            else:
                query = f"""INSERT INTO hotel_chain (hotelChain_id, name, street, city, province, zipcode, country, number_hotels, contact_email, contact_phone)
                            VALUES ({hotel_chain_id}, '{name}', '{street}', '{city}', '{province}', '{zipcode}', '{country}', {number_hotels}, '{contact_email}', '{contact_phone}')"""
                cursor.execute(query)
                conn.commit()
                st.success("Hotel chain inserted successfully!")


    elif action == "Update":
        hotelChain_id = st.number_input("Enter Hotel Chain ID", min_value=0)
        name = st.text_input("Name")
        street = st.text_input("Street")
        city = st.text_input("City")
        province = st.text_input("Province")
        zipcode = st.text_input("Zipcode")
        country = st.text_input("Country")
        number_hotels = st.number_input("Number of Hotels", min_value=0)
        contact_email = st.text_input("Contact Email")
        contact_phone = st.text_input("Contact Phone")

        if st.button("Update"):
            query = f"SELECT hotel_id FROM hotel WHERE hotel_id = {hotel_id}"
            cursor.execute(query)
            result = cursor.fetchone()

            # Check if hotelChain_id exists
            query = f"SELECT hotelChain_id FROM hotel_chain WHERE hotelChain_id = {hotelChain_id}"
            cursor.execute(query)
            hotel_chain_result = cursor.fetchone()

            if not result:
                st.error("The hotel_id does not exist. Please enter a valid hotel ID.")
            elif not hotel_chain_result:
                st.error("The hotelChain_id does not exist. Please enter a valid hotel chain ID.")
            else:
                query = f"""UPDATE hotel_chain
                            SET name = '{name}', street = '{street}', city = '{city}', province = '{province}', zipcode = '{zipcode}', country = '{country}', number_hotels = {number_hotels}, contact_email = '{contact_email}', contact_phone = '{contact_phone}'
                            WHERE hotelChain_id = {hotel_chain_id}"""
                cursor.execute(query)
                conn.commit()
                st.success("Hotel chain updated successfully!")

    elif action == "Delete":
        hotel_chain_id = st.number_input("Enter Hotel Chain ID", min_value=0)
        if hotel_chain_id:
            delete_button = st.button("Delete")
            if delete_button:
                # Check if hotel_chain_id exists
                query = f"SELECT hotelChain_id FROM hotel_chain WHERE hotelChain_id = {hotel_chain_id}"
                cursor.execute(query)
                result = cursor.fetchone()

                if not result:
                    st.error("Hotel chain not found. The hotel chain ID may be incorrect or the hotel chain has already been deleted.")
                else:
                    query = f"DELETE FROM hotel_chain WHERE hotelChain_id={hotel_chain_id}"
                    cursor.execute(query)
                    conn.commit()
                    st.success("Hotel chain deleted successfully!")

    

# Display search form if "Search Rooms" is selected
if menu_choice == "Search Rooms" and user_type == "Employee":
    st.subheader("Search Rooms")

    # Search and filter input fields
    room_capacity = st.selectbox("Room Capacity", ["Single", "Double"])
    country = st.selectbox("Country", [None, "USA", "Canada"])
    city = st.selectbox("City", [None, "Los Angeles", "Toronto", "New York", "Vancouver", "Chicago", "San Diego", "Jacksonville", "Waipahu", "San Jose", "Montreal", "Rossland", "Sebringville", "Victoria", "East Aurora", "Fort Worth", "Ames", "Oakland", "Seattle", "Ottawa", "Edmonton", "Calgary"])
    hotel_chain = st.selectbox("Hotel Chain", [None, "Hotel Chain A", "Hotel Chain B", "Hotel Chain C", "Hotel Chain D", "Hotel Chain E"])
    hotel_category = st.selectbox("Hotel Category (1-5)", [None, 1, 2, 3, 4, 5])
    max_price = st.number_input("Maximum Room Price", min_value=1, value=100)

    if st.button("Search"):
        # Build the query based on input fields
        query = f"""SELECT r.room_number, r.price, r.amenities, r.capacity, r.has_sea_view, r.has_mountain_view, r.extendable, r.damages, r.hotel_id, h.name, h.city, h.province, h.categorization, hc.name as hotel_chain,
                           CASE WHEN b.room_number IS NOT NULL OR rt.room_number IS NOT NULL THEN 'Not Available' ELSE 'Available' END AS room_status
                    FROM room r
                    LEFT JOIN booking b ON r.room_number = b.room_number
                    LEFT JOIN renting rt ON r.room_number = rt.room_number
                    JOIN hotel h ON r.hotel_id = h.hotel_id
                    JOIN hotel_chain hc ON h.hotelChain_id = hc.hotelChain_id
                    WHERE r.capacity = '{room_capacity}'
                    AND r.price <= {max_price}
                 """

        if country is not None:
            query += f" AND h.country = '{country}'"
        if city is not None:
            query += f" AND h.city = '{city}'"
        if hotel_chain is not None:
            query += f" AND hc.name = '{hotel_chain}'"
        if hotel_category is not None:
            query += f" AND h.categorization = {hotel_category}"

        cursor.execute(query)
        rooms = cursor.fetchall()

        rooms_df = pd.DataFrame(rooms, columns=["Room Number", "Price", "Amenities", "Capacity", "Sea View", "Mountain View", "Extendable", "Damages", "Hotel ID", "Hotel Name", "City", "Province", "Category", "Hotel Chain", "Room Status"])
        st.dataframe(rooms_df)


# Manage Customers
if menu_choice == "Manage Customers" and user_type == "Employee":
    st.subheader("Manage Customers")
    action = st.selectbox("Action", ["View", "Insert", "Update", "Delete"])

    if action == "Insert":
        with st.form(key="customer_insert"):
            customer_id = st.number_input("Customer ID", min_value=1, value=1)
            first_name = st.text_input("First Name")
            last_name = st.text_input("Last Name")
            street = st.text_input("Street")
            city = st.text_input("City")
            province = st.text_input("Province")
            zipcode = st.text_input("Zip Code")
            country = st.text_input("Country")
            ssn = st.text_input("SSN")
            registration_date = st.date_input("Registration Date")
            insert_button = st.form_submit_button(label="Insert")

        if insert_button:
            query = f"SELECT customer_id FROM customer WHERE customer_id = {customer_id}"
            cursor.execute(query)
            result = cursor.fetchone()

            if result:
                st.error("The customer_id already exists. Please choose a different customer ID.")
            else:
                query = f"""
                INSERT INTO customer (customer_id, first_name, last_name, street, city, province, zipcode, country, ssn, registration_date)
                VALUES ({customer_id}, '{first_name}', '{last_name}', '{street}', '{city}', '{province}', '{zipcode}', '{country}', '{ssn}', '{registration_date}')
                """
                cursor.execute(query)
                conn.commit()
                st.success("Customer inserted successfully!")

    elif action == "Update":
        customer_id = st.number_input("Enter Customer ID to Update", min_value=0, value=0)
        if customer_id:
            query = f"SELECT * FROM customer WHERE customer_id = {customer_id}"
            cursor.execute(query)
            customer_data = cursor.fetchone()

            if not customer_data:
                st.error("Customer ID not found. Please check the Customer ID.")
            else:
                with st.form(key="customer_update"):
                    first_name = st.text_input("First Name")
                    last_name = st.text_input("Last Name")
                    street = st.text_input("Street")
                    city = st.text_input("City")
                    province = st.text_input("Province")
                    zipcode = st.text_input("Zip Code")
                    country = st.text_input("Country")
                    ssn = st.text_input("SSN")
                    registration_date = st.date_input("Registration Date")
                    update_button = st.form_submit_button(label="Update")

                if update_button:
                    query = f"""
                    UPDATE customer
                    SET first_name='{first_name}', last_name='{last_name}', street='{street}', city='{city}', province='{province}', zipcode='{zipcode}', country='{country}', ssn='{ssn}', registration_date='{registration_date}'
                    WHERE customer_id={customer_id}
                    """
                    cursor.execute(query)
                    conn.commit()
                    st.success("Customer updated successfully!")

    elif action == "Delete":
        customer_id = st.number_input("Enter Customer ID", min_value=0, value=0)
        if customer_id:
            delete_button = st.button("Delete")
            if delete_button:
                query = f"SELECT customer_id FROM customer WHERE customer_id = {customer_id}"
                cursor.execute(query)
                result = cursor.fetchone()

                if not result:
                    st.error("Customer not found. The customer ID may be incorrect or the customer has already been deleted.")
                else:
                    query = f"DELETE FROM customer WHERE customer_id={customer_id}"
                    cursor.execute(query)
                    conn.commit()
                    st.success("Customer deleted successfully!")
                
    elif action == "View":
        query = "SELECT * FROM customer"
        cursor.execute(query)
        customers = cursor.fetchall()
        
        customers_df = pd.DataFrame(customers, columns=["customer_id", "first_name", "last_name", "street", "city", "province", "zipcode", "country", "ssn", "registration_date"])
        st.dataframe(customers_df)
# Manage Employees

if menu_choice == "Manage Employees" and user_type == "Employee":
    st.subheader("Manage Employees")
    action = st.selectbox("Action", ["View", "Insert", "Update", "Delete"])

    if action == "View":
        query = """
        SELECT employee.employee_id, first_name, last_name, street, city, province, zipcode, country, ssn, hotel_id, GROUP_CONCAT(role_name) as roles
        FROM employee
        INNER JOIN employee_role ON employee.employee_id = employee_role.employee_id
        INNER JOIN role ON employee_role.role_id = role.role_id
        GROUP BY employee.employee_id;
        """
        cursor.execute(query)
        employees = cursor.fetchall()
        
        employees_df = pd.DataFrame(employees, columns=["employee_id", "first_name", "last_name", "street", "city", "province", "zipcode", "country", "ssn", "hotel_id", "roles"])
        st.dataframe(employees_df)

    elif action == "Insert":
        with st.form(key="employee_insert"):
            employee_id = st.number_input("Employee ID", min_value=0, step=1)
            first_name = st.text_input("First Name")
            last_name = st.text_input("Last Name")
            street = st.text_input("Street")
            city = st.text_input("City")
            province = st.text_input("Province")
            zipcode = st.text_input("Zipcode")
            country = st.text_input("Country")
            ssn = st.text_input("SSN")
            hotel_id = st.number_input("Hotel ID", min_value=0, step=1)
            role = st.multiselect("Role(s)", options=["Manager", "Receptionist", "Housekeeper", "Event Planner"])

            insert_button = st.form_submit_button(label="Insert")

        if insert_button:
            query = f"SELECT employee_id, ssn FROM employee WHERE employee_id = {employee_id} OR ssn = '{ssn}'"
            cursor.execute(query)
            result = cursor.fetchone()
            if result:
                if result[0] == employee_id:
                    st.error("The employee_id already exists. Please choose a different employee ID.")
                if result[1] == ssn:
                    st.error("The SSN already exists. Please choose a different SSN.")
            else:
                query = f"""
                INSERT INTO employee (employee_id, first_name, last_name, street, city, province, zipcode, country, ssn, hotel_id)
                VALUES ({employee_id}, '{first_name}', '{last_name}', '{street}', '{city}', '{province}', '{zipcode}', '{country}', '{ssn}', {hotel_id})
                """
                cursor.execute(query)
                conn.commit()

                for r in role:
                    query_role = f"SELECT role_id FROM role WHERE role_name = '{r}'"
                    cursor.execute(query_role)
                    role_id = cursor.fetchone()[0]

                    query_employee_role = f"""
                    INSERT INTO employee_role (employee_id, role_id)
                    VALUES ({employee_id}, {role_id})
                    """
                    cursor.execute(query_employee_role)
                    conn.commit()

                st.success("Employee inserted successfully!")

    elif action == "Update":
        employee_id = st.number_input("Enter Employee ID to Update", min_value=0, step=1)
        if employee_id:
            query = f"SELECT * FROM employee WHERE employee_id = {employee_id}"
            cursor.execute(query)
            employee_data = cursor.fetchone()

            if not employee_data:
                st.error("Employee ID not found. Please check the Employee ID.")
            else:
                first_name = st.text_input("First Name")
                last_name = st.text_input("Last Name")
                street = st.text_input("Street")
                city = st.text_input("City")
                province = st.text_input("Province")
                zipcode = st.text_input("Zipcode")
                country = st.text_input("Country")
                ssn = st.text_input("SSN")
                hotel_id = st.number_input("Hotel ID", min_value=0, step=1)
                role = st.multiselect("Role(s)", options=["Manager", "Receptionist", "Housekeeper", "Event Planner"])

                if st.button("Update"):
                    query = f"""
                    UPDATE employee SET
                    first_name = '{first_name}',
                    last_name = '{last_name}',
                    street = '{street}',
                    city = '{city}',
                    province = '{province}',
                    zipcode = '{zipcode}',
                    country = '{country}',
                    ssn = '{ssn}',
                    hotel_id = {hotel_id}
                    WHERE employee_id = {employee_id}
                    """
                    cursor.execute(query)
                    conn.commit()

                    # Clear existing roles
                    query_clear_roles = f"DELETE FROM employee_role WHERE employee_id = {employee_id}"
                    cursor.execute(query_clear_roles)
                    conn.commit()

                    # Update roles
                    for r in role:
                        query_role = f"SELECT role_id FROM role WHERE role_name = '{r}'"
                        cursor.execute(query_role)
                        role_id = cursor.fetchone()[0]

                        query_employee_role = f"""
                        INSERT INTO employee_role (employee_id, role_id)
                        VALUES ({employee_id}, {role_id})
                        """
                        cursor.execute(query_employee_role)
                        conn.commit()

                    st.success("Employee updated successfully!")



    elif action == "Delete":
        employee_id = st.number_input("Enter Employee ID to Delete", min_value=0, step=1)
        if st.button("Delete Employee"):
            # Fetch the employee details
            query = f"SELECT * FROM employee WHERE employee_id = {employee_id}"
            cursor.execute(query)
            employee_data = cursor.fetchone()

            if employee_data:
                st.write(f"Employee: {employee_data[1]} {employee_data[2]}")
                
                # Delete associated records in the employee_role table
                query = f"DELETE FROM employee_role WHERE employee_id = {employee_id}"
                cursor.execute(query)
                conn.commit()

                # Delete the employee record
                query = f"DELETE FROM employee WHERE employee_id = {employee_id}"
                cursor.execute(query)
                conn.commit()
                st.success("Employee deleted successfully!")
            else:
                st.warning("Employee not found. Please check the Employee ID.")





# Manage Hotels
if menu_choice == "Manage Hotels" and user_type == "Employee":
    st.subheader("Manage Hotels")
    action = st.selectbox("Action", ["View", "Insert", "Update", "Delete"])

    if action == "Insert":
        with st.form(key="hotel_insert"):
            hotel_id = st.number_input("Hotel ID", min_value=0, step=1)
            name = st.text_input("Name")
            street = st.text_input("Street")
            city = st.text_input("City")
            province = st.text_input("Province")
            zipcode = st.text_input("Zipcode")
            country = st.text_input("Country")
            number_rooms = st.number_input("Number of Rooms", min_value=0)
            categorization = st.slider("Categorization", min_value=1, max_value=5)
            contact_email = st.text_input("Contact Email")
            contact_phone = st.text_input("Contact Phone")
            manager_id = st.number_input("Manager ID", min_value=0)
            hotelChain_id = st.number_input("Hotel Chain ID", min_value=0)
            insert_button = st.form_submit_button(label="Insert")
            
        if insert_button:
            # Check if hotel_id already exists
            query = f"SELECT hotel_id FROM hotel WHERE hotel_id = {hotel_id}"
            cursor.execute(query)
            result = cursor.fetchone()

        # Check if hotelChain_id exists
            query = f"SELECT hotelChain_id FROM hotel_chain WHERE hotelChain_id = {hotelChain_id}"
            cursor.execute(query)
            hotel_chain_result = cursor.fetchone()

            if result:
                st.error("The hotel_id already exists. Please choose a different ID.")
            elif not hotel_chain_result:
                st.error("The hotelChain_id does not exist. Please enter a valid hotel chain ID.")
            else:
                query = f"""
                INSERT INTO hotel (hotel_id, name, street, city, province, zipcode, country, number_rooms, categorization, contact_email, contact_phone, manager_id, hotelChain_id)
                VALUES ({hotel_id}, '{name}', '{street}', '{city}', '{province}', '{zipcode}', '{country}', {number_rooms}, {categorization}, '{contact_email}', '{contact_phone}', {manager_id}, {hotelChain_id})
                """
                cursor.execute(query)
                conn.commit()
                st.success("Hotel inserted successfully!")



    elif action == "Update":
        hotel_id = st.number_input("Enter Hotel ID", min_value=0)
        if hotel_id:
            with st.form(key="hotel_update"):
                name = st.text_input("Name")
                street = st.text_input("Street")
                city = st.text_input("City")
                province = st.text_input("Province")
                zipcode = st.text_input("Zipcode")
                country = st.text_input("Country")
                number_rooms = st.number_input("Number of Rooms", min_value=0)
                categorization = st.slider("Categorization", min_value=1, max_value=5)
                contact_email = st.text_input("Contact Email")
                contact_phone = st.text_input("Contact Phone")
                manager_id = st.number_input("Manager ID", min_value=0)
                hotelChain_id = st.number_input("Hotel Chain ID", min_value=0)
                update_button = st.form_submit_button(label="Update")

            if update_button:
                # Check if hotel_id exists
                query = f"SELECT hotel_id FROM hotel WHERE hotel_id = {hotel_id}"
                cursor.execute(query)
                result = cursor.fetchone()

                # Check if hotelChain_id exists
                query = f"SELECT hotelChain_id FROM hotel_chain WHERE hotelChain_id = {hotelChain_id}"
                cursor.execute(query)
                hotel_chain_result = cursor.fetchone()

                if not result:
                    st.error("The hotel_id does not exist. Please enter a valid hotel ID.")
                elif not hotel_chain_result:
                    st.error("The hotelChain_id does not exist. Please enter a valid hotel chain ID.")
                else:
                    query = f"""
                    UPDATE hotel
                    SET name='{name}', street='{street}', city='{city}', province='{province}', zipcode='{zipcode}', country='{country}', number_rooms={number_rooms}, categorization={categorization}, contact_email='{contact_email}', contact_phone='{contact_phone}', manager_id={manager_id}, hotelChain_id={hotelChain_id}
                    WHERE hotel_id={hotel_id}
                    """
                    cursor.execute(query)
                    conn.commit()
                    st.success("Hotel updated successfully!")

    elif action == "Delete":
        hotel_id = st.number_input("Enter Hotel ID", min_value=0)
        if hotel_id:
            delete_button = st.button("Delete")
            if delete_button:
                 # Check if hotel_id exists
                query = f"SELECT hotel_id FROM hotel WHERE hotel_id = {hotel_id}"
                cursor.execute(query)
                result = cursor.fetchone()

                if not result:
                    st.error("Hotel not found. The hotel ID may be incorrect or the hotel has already been deleted.")
                else:
                    query = f"""
                    DELETE FROM hotel
                    WHERE hotel_id={hotel_id}
                    """
                    cursor.execute(query)
                    conn.commit()
                    st.success("Hotel deleted successfully!")

                
    elif action == "View":
        query = """
        SELECT * FROM hotel
        """
        cursor.execute(query)
        hotels = cursor.fetchall()
        
        hotels_df = pd.DataFrame(hotels, columns=["hotel_id", "name", "street", "city", "province", "zipcode", "country", "number_rooms", "categorization", "contact_email", "contact_phone", "manager_id", "hotelChain_id"])
        st.dataframe(hotels_df)


# Manage Rooms
if menu_choice == "Manage Rooms" and user_type == "Employee":
    st.subheader("Manage Rooms")
    action = st.selectbox("Action", ["View", "Insert", "Update", "Delete"])

    if action == "Insert":
        with st.form(key="room_insert"):
            room_number = st.number_input("Room Number", min_value=0)
            price = st.number_input("Price", min_value=0.0)
            amenities = st.text_input("Amenities (comma-separated)")
            capacity = st.selectbox("Capacity", ["Single", "Double"])
            has_sea_view = st.checkbox("Has Sea View")
            has_mountain_view = st.checkbox("Has Mountain View")
            extendable = st.checkbox("Extendable")
            damages = st.text_input("Damages")
            hotel_id = st.number_input("Hotel ID", min_value=0)
            insert_button = st.form_submit_button(label="Insert")

        if insert_button:
            query = f"SELECT room_number FROM room WHERE room_number = {room_number}"
            cursor.execute(query)
            result = cursor.fetchone()

            # Check if hotel_id exists
            query = f"SELECT hotel_id FROM hotel WHERE hotel_id = {hotel_id}"
            cursor.execute(query)
            hotel_result = cursor.fetchone()

            if result:
                st.error("The room number already exists. Please choose a different room number.")
            elif not hotel_result:
                st.error("The hotel_id does not exist. Please enter a valid hotel ID.")
            else:
                query = f"""
                INSERT INTO room (room_number, price, amenities, capacity, has_sea_view, has_mountain_view, extendable, damages, hotel_id)
                VALUES ({room_number}, {price}, '{amenities}', '{capacity}', {has_sea_view}, {has_mountain_view}, {extendable}, '{damages}', {hotel_id})
                """
                cursor.execute(query)
                conn.commit()
                st.success("Room inserted successfully!")

    elif action == "Update":
        room_number = st.number_input("Enter Room Number", min_value=0)
        if room_number:
            with st.form(key="room_update"):
                price = st.number_input("Price", min_value=0.0)
                amenities = st.text_input("Amenities (comma-separated)")
                capacity = st.selectbox("Capacity", ["Single", "Double"])
                has_sea_view = st.checkbox("Has Sea View")
                has_mountain_view = st.checkbox("Has Mountain View")
                extendable = st.checkbox("Extendable")
                damages = st.text_input("Damages")
                hotel_id = st.number_input("Hotel ID", min_value=0)
                update_button = st.form_submit_button(label="Update")

            if update_button:
                query = f"SELECT room_number FROM room WHERE room_number = {room_number}"
                cursor.execute(query)
                result = cursor.fetchone()

                # Check if hotel_id exists
                query = f"SELECT hotel_id FROM hotel WHERE hotel_id = {hotel_id}"
                cursor.execute(query)
                hotel_result = cursor.fetchone()

                if not result:
                    st.error("The room number does not exist. Please enter a valid room number.")
                elif not hotel_result:
                    st.error("The hotel_id does not exist. Please enter a valid hotel ID.")
                else:
                    query = f"""
                    UPDATE room
                    SET price={price}, amenities='{amenities}', capacity='{capacity}', has_sea_view={has_sea_view}, has_mountain_view={has_mountain_view}, extendable={extendable}, damages='{damages}', hotel_id={hotel_id}
                    WHERE room_number={room_number}
                    """
                    cursor.execute(query)
                    conn.commit()
                    st.success("Room updated successfully!")

    elif action == "Delete":
        room_number = st.number_input("Enter Room Number", min_value=0)
        if room_number:
            delete_button = st.button("Delete")
            if delete_button:
                # Check if room_number exists
                query = f"SELECT room_number FROM room WHERE room_number = {room_number}"
                cursor.execute(query)
                result = cursor.fetchone()

                if not result:
                    st.error("Room not found. The room number may be incorrect or the room has already been deleted.")
                else:
                    query = f"DELETE FROM room WHERE room_number={room_number}"
                    cursor.execute(query)
                    conn.commit()
                    st.success("Room deleted successfully!")


    elif action == "View":
        query = """
        SELECT * FROM room
        """
        cursor.execute(query)
        rooms = cursor.fetchall()
        
        rooms_df = pd.DataFrame(rooms, columns=["room_number", "price", "amenities", "capacity", "has_sea_view", "has_mountain_view", "extendable", "damages", "hotel_id"])
        st.dataframe(rooms_df)



# Manage Bookings
if menu_choice == "Manage Bookings" and user_type == "Employee":
    st.subheader("Manage Bookings")
    action = st.selectbox("Action", ["View","Delete"])

    if action == "Delete":
        booking_id = st.number_input("Enter Booking ID", min_value=0)
        customer_id = st.number_input("Enter Customer ID", min_value=0)
        if booking_id and customer_id:
            delete_button = st.button("Delete")
            if delete_button:
                query = f"DELETE FROM booking WHERE booking_id={booking_id} AND customer_id={customer_id}"
                cursor.execute(query)
                conn.commit()
                if cursor.rowcount:
                    st.success("Booking deleted successfully!")
                else:
                    st.warning("No matching record found.")

    elif action == "View":
        query = """SELECT b.booking_id, c.first_name, c.last_name, c.customer_id, b.room_number, b.booking_date, b.check_in_date, b.check_out_date
                   FROM booking b
                   JOIN customer c ON b.customer_id = c.customer_id"""
        cursor.execute(query)
        records = cursor.fetchall()
        if records:
            bookings_df = pd.DataFrame(records, columns=['Booking ID', 'First Name', 'Last Name', 'Customer ID', 'Room Number', 'Booking Date', 'Check-in Date', 'Check-out Date'])
            st.dataframe(bookings_df)
        else:
            st.warning("No records found in the booking table.")


# Manage Rentings
if menu_choice == "Manage Rentings" and user_type == "Employee":
    st.subheader("Manage Rentings")
    action = st.selectbox("Action", ["Insert", "Update", "Delete", "View"])

    if action == "Insert":
        has_booking = st.checkbox("Customer has a booking?")

        with st.form(key="renting_insert"):
            price = st.number_input("Price", min_value=0.0)
            payment_date = st.date_input("Payment Date")

            if has_booking:
                booking_id = st.number_input("Booking ID", min_value=0)
            else:
                check_in_date = st.date_input("Check-in Date")
                check_out_date = st.date_input("Check-out Date")
                room_number = st.number_input("Room Number", min_value=0)

            insert_button = st.form_submit_button(label="Insert")

        if insert_button:
            if has_booking:
                cursor.execute(f"SELECT check_in_date, check_out_date, room_number FROM booking WHERE booking_id = {booking_id}")
                check_in_date, check_out_date, room_number = cursor.fetchone()

            # Skip the check for existing bookings when has_booking is selected
            if not has_booking:
                cursor.execute(f"SELECT room_number FROM renting WHERE room_number = {room_number}")
                room_rented = cursor.fetchone()

                if room_rented:
                    st.error("Room is not available due to existing bookings.")
                else:
                    # Insert query
                    query = f"""
                    INSERT INTO renting (check_in_date, check_out_date, price, payment_date, customer_id, room_number)
                    VALUES ('{check_in_date}', '{check_out_date}', {price}, '{payment_date}', {customer_id}, {room_number})
                    """

            # Insert query when has_booking is selected
            if has_booking:
                query = f"""
                INSERT INTO renting (check_in_date, check_out_date, price, payment_date, customer_id, room_number, booking_id)
                SELECT b.check_in_date, b.check_out_date, {price}, '{payment_date}', b.customer_id, b.room_number, b.booking_id
                FROM booking b
                JOIN room r ON b.room_number = r.room_number
                WHERE b.booking_id = {booking_id}
                """

            cursor.execute(query)
            conn.commit()
            st.success("Renting inserted successfully!")

    
    elif action == "Update":
        renting_id = st.number_input("Enter Renting ID", min_value=0)
        if renting_id:
            with st.form(key="renting_update"):
                check_in_date = st.date_input("Check-in Date")
                check_out_date = st.date_input("Check-out Date")
                price = st.number_input("Price", min_value=0.0)
                payment_date = st.date_input("Payment Date")
                customer_id = st.number_input("Customer ID", min_value=0)
                room_number = st.number_input("Room Number", min_value=0)
                booking_id = st.number_input("Booking ID", min_value=0)
                update_button = st.form_submit_button(label="Update")

            if update_button:
                query = f"""
                UPDATE renting
                SET check_in_date='{check_in_date}', check_out_date='{check_out_date}', price={price}, payment_date='{payment_date}', customer_id={customer_id}, room_number={room_number}, booking_id={booking_id}
                WHERE renting_id={renting_id}
                """
                cursor.execute(query)
                conn.commit()
                st.success("Renting updated successfully!")


    elif action == "Delete":
        renting_id = st.number_input("Enter Renting ID", min_value=0)
        if renting_id:
            delete_button = st.button("Delete")
            if delete_button:
                # Check if renting_id exists
                query = f"SELECT renting_id FROM renting WHERE renting_id = {renting_id}"
                cursor.execute(query)
                result = cursor.fetchone()

                if not result:
                    st.error("Renting not found. The renting ID may be incorrect or the renting has already been deleted.")
                else:
                    query = f"DELETE FROM renting WHERE renting_id={renting_id}"
                    cursor.execute(query)
                    conn.commit()
                    st.success("Renting deleted successfully!")


    elif action == "View":
        # Fetch records from the renting table
        query = "SELECT * FROM renting"
        cursor.execute(query)
        records = cursor.fetchall()
        if records:
            renting_df = pd.DataFrame(records, columns=['Renting ID', 'Check-in Date', 'Check-out Date', 'Price', 'Payment Date', 'Customer ID', 'Room Number', 'Booking ID'])
            st.dataframe(renting_df)
        else:
            st.warning("No records found in the renting table.")

    
# Manage Archives
if menu_choice == "Manage Archives" and user_type == "Employee":
    st.subheader("Manage Archives")
    action = st.selectbox("Action", ["View Archives"])

    if action == "View Archives":
        st.subheader("Archives")
        query = """
        SELECT
            archive_id,
            check_in_date,
            check_out_date,
            customer_id,
            room_number,
            room_price,
            room_amenities,
            room_capacity,
            room_has_sea_view,
            room_has_mountain_view,
            room_extendable,
            renting_id,
            booking_id
        FROM archive
        """
        cursor.execute(query)
        result = cursor.fetchall()

        if result:
            columns = [
                "Archive ID",
                "Check-In Date",
                "Check-Out Date",
                "Customer ID",
                "Room Number",
                "Room Price",
                "Room Amenities",
                "Room Capacity",
                "Room Has Sea View",
                "Room Has Mountain View",
                "Room Extendable",
                "Renting ID",
                "Booking ID",
            ]
            df = pd.DataFrame(result, columns=columns)
            st.dataframe(df)
        else:
            st.write("No archives found")


# Close the MySQL connection
conn.close()
