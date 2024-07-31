CREATE OR REPLACE PROCEDURE insert_accommodation()
LANGUAGE plpgsql
AS $$
DECLARE
    v_hotel_id INT;
    v_room_quantity INT;
    v_max_capacity INT;
    v_daily_rate NUMERIC;
    v_is_available BOOLEAN;
BEGIN
    FOR v_hotel_id IN 1..135 LOOP
        -- Insert 6 accommodations for each hotel
        FOR i IN 1..(random() * 20 + 6)::INT LOOP
            -- Generate random values
            v_room_quantity := (random() * 4 + 1)::INT; -- ROOM_QUANTITY between 1 and 5
            v_max_capacity := (random() * 7 + 1)::INT; -- MAX_CAPACITY between 1 and 8
            v_daily_rate := ROUND((random() * 1440 + 60)::NUMERIC, 2); -- DAILY_RATE between 60.0 and 1500.0, rounded to 2 decimal places
            v_is_available := TRUE; -- Always set to TRUE

            -- Insert statement
            INSERT INTO ACCOMMODATION(ID, ROOM_QUANTITY, MAX_CAPACITY, DAILY_RATE, IS_AVAILABLE, ID_HOTEL)
            VALUES (NEXTVAL('SEQ_ACCOMMODATION'), v_room_quantity, v_max_capacity, v_daily_rate, v_is_available, v_hotel_id);
        END LOOP;
    END LOOP;
END;
$$;

CALL insert_accommodation();
