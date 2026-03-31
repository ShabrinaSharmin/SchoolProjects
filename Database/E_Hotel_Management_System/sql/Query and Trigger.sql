
DELIMITER //
CREATE TRIGGER archive_booking
AFTER DELETE ON booking
FOR EACH ROW
BEGIN
    INSERT INTO archive (check_in_date, check_out_date, customer_id, room_number, room_price, room_amenities, room_capacity, room_has_sea_view, room_has_mountain_view, room_extendable, booking_id)
    SELECT OLD.check_in_date, OLD.check_out_date, OLD.customer_id, r.room_number, r.price, r.amenities, r.capacity, r.has_sea_view, r.has_mountain_view, r.extendable, OLD.booking_id
    FROM room r
    WHERE r.room_number = OLD.room_number;
END;
//
DELIMITER ;


DELIMITER //
CREATE TRIGGER archive_renting
AFTER DELETE ON renting
FOR EACH ROW
BEGIN
    INSERT INTO archive (check_in_date, check_out_date, customer_id, room_number, room_price, room_amenities, room_capacity, room_has_sea_view, room_has_mountain_view, room_extendable, renting_id)
    SELECT OLD.check_in_date, OLD.check_out_date, OLD.customer_id, r.room_number, r.price, r.amenities, r.capacity, r.has_sea_view, r.has_mountain_view, r.extendable, OLD.renting_id
    FROM room r
    WHERE r.room_number = OLD.room_number;
END;
//
DELIMITER ;


DELIMITER //
CREATE TRIGGER ensure_checkout_after_checkin
BEFORE INSERT ON booking
FOR EACH ROW
BEGIN
    IF NEW.check_out_date <= NEW.check_in_date THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: Check-out date must be after the check-in date.';
    END IF;
END;
//
CREATE TRIGGER ensure_checkout_after_checkin_update
BEFORE UPDATE ON booking
FOR EACH ROW
BEGIN
    IF NEW.check_out_date <= NEW.check_in_date THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: Check-out date must be after the check-in date.';
    END IF;
END;
//
DELIMITER ;


SELECT * FROM hotel WHERE city = 'city_name';


SELECT SUM(re.price) AS total_revenue
FROM renting re
JOIN room r ON re.room_number = r.room_number
WHERE r.hotel_id = 181
AND re.payment_date BETWEEN 2023-06-07 AND 2023-06-10;


SELECT h.hotelChain_id, SUM(h.number_rooms) as total_rooms
FROM hotel h
JOIN hotel_chain hc ON h.hotelChain_id = hc.hotelChain_id
GROUP BY h.hotelChain_id;


SELECT * FROM customer WHERE registration_date < '2023-04-13';


SELECT * FROM employee e
JOIN hotel h ON e.hotel_id = h.hotel_id
JOIN hotel_chain hc ON h.hotelChain_id = hc.hotelChain_id
WHERE hc.hotelChain_id = 1;

