
CREATE INDEX idx_room_price ON room(price);

CREATE INDEX idx_hotel_categorization ON hotel(categorization);


CREATE INDEX idx_booking_check_in_date ON booking(check_in_date);


CREATE INDEX idx_booking_check_out_date ON booking(check_out_date);


CREATE INDEX idx_hotel_name ON hotel(name);


CREATE VIEW available_rooms_per_area AS
SELECT 
    h.city AS area,
    COUNT(r.room_number) AS available_rooms
FROM 
    hotel h
JOIN 
    room r ON h.hotel_id = r.hotel_id
GROUP BY 
    h.city;


CREATE VIEW hotel_room_capacity AS
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
