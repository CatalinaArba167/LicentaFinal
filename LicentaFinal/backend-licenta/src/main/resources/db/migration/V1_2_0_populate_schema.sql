

INSERT INTO user_table (id, password, email, first_name, last_name)
VALUES
    ('80deaa2b-aa09-49f8-a1b8-cad9c0d587cd', '$2b$12$7QzQFzjz/W5H7G8gH3EvS.x/A9EuFZ9Hz8C8R5d1UlFg4p9D4/1/K', 'user1@yahoo.com', 'User', 'One'),
    ('ff10009a-6e35-428b-bb99-e4cb4eabe8f4', '$2b$12$4dq56Z6U9J5zOzBf8jHoVOUpE5RbvIbTcYaB1jGsneT4OMj6W.b32', 'user2@yahoo.com', 'User', 'Two');


INSERT INTO location (id, country, county, city)
VALUES
    ('1ed7128e-82e4-4034-8bf7-dc8cb46d8517', 'Romania', 'Cluj', 'Cluj-Napoca'),
    ('a136fd26-d061-43e7-93ec-41740c4133af', 'Romania', 'Cluj', 'Campia Turzii');


-- Insert car data into the car table
INSERT INTO car (id, location_id, price, predicted_price, manufacturer, model, prod_year, category, leather_interior, fuel_type, engine_volume, mileage, cylinders, gear_box_type, drive_wheels, doors, wheel, color, airbags, is_turbo)
VALUES
    ('7942e51c-47ff-4db5-8f36-11000cd84916', '1ed7128e-82e4-4034-8bf7-dc8cb46d8517', 15053, 0, 'MERCEDES', 'E 320', 2004, 'Sedan', TRUE, 'Petrol', 3.2, 214000, 6, 'Tiptronic', 'Rear', '4-May', 'Left wheel', 'Grey', 8, FALSE),
    ('a4aca235-e00a-478c-aced-b85073ae8ec2', '1ed7128e-82e4-4034-8bf7-dc8cb46d8517', 15458, 0, 'HYUNDAI', 'Genesis', 2009, 'Coupe', TRUE, 'Petrol', 3.8, 123346, 6, 'Automatic', 'Front', '4-May', 'Left wheel', 'Black', 4, FALSE),
    ('1767f998-a21c-4083-b970-ce9c71259c79', '1ed7128e-82e4-4034-8bf7-dc8cb46d8517', 2901, 0, 'CHEVROLET', 'Cruze', 2014, 'Sedan', TRUE, 'Petrol', 1.4, 80827, 4, 'Automatic', 'Front', '4-May', 'Left wheel', 'Green', 12, FALSE),
    ('4edaf7ab-ab2f-41c0-9c31-d1abbdb755cd', '1ed7128e-82e4-4034-8bf7-dc8cb46d8517', 706, 0, 'PORSCHE', '911', 2013, 'Coupe', TRUE, 'Petrol', 3.8, 64464, 6, 'Automatic', 'Rear', '2-Mar', 'Left wheel', 'Black', 12, FALSE),
    ('c7dc5b27-e515-4595-9a57-aac481c9e1e3', '1ed7128e-82e4-4034-8bf7-dc8cb46d8517', 314, 0, 'FORD', 'Fusion', 2015, 'Sedan', TRUE, 'Hybrid', 2.0, 91760, 4, 'Automatic', 'Front', '4-May', 'Left wheel', 'Black', 12, FALSE),
    ('7700cfb8-8162-4eca-b2f8-5357f05b86b8', '1ed7128e-82e4-4034-8bf7-dc8cb46d8517', 15681, 0, 'LEXUS', 'GX 460', 2010, 'Jeep', TRUE, 'Petrol', 4.6, 275240, 8, 'Automatic', '4x4', '4-May', 'Left wheel', 'Silver', 0, FALSE),
    ('1b58af83-509b-4e57-b3d7-0f5609653164', 'a136fd26-d061-43e7-93ec-41740c4133af', 26657, 0, 'MERCEDES', 'Sprinter', 2004, 'Microbus', FALSE, 'Diesel', 2.2, 300000, 4, 'Manual', 'Rear', '4-May', 'Left wheel', 'White', 1, TRUE),
    ('504c58ec-7355-4ed3-a57a-acfd6381eb76', 'a136fd26-d061-43e7-93ec-41740c4133af', 10663, 0, 'TOYOTA', 'Prius', 2007, 'Hatchback', FALSE, 'Petrol', 1.5, 318400, 4, 'Automatic', 'Front', '4-May', 'Left wheel', 'Silver', 6, FALSE),
    ('0aa3f2ba-0c44-4710-bc9b-38db90545b53', 'a136fd26-d061-43e7-93ec-41740c4133af', 12858, 0, 'FORD', 'Focus SE', 2015, 'Sedan', FALSE, 'Petrol', 2.0, 91200, 4, 'Automatic', 'Front', '4-May', 'Left wheel', 'Sky blue', 10, FALSE),
    ('52546be9-e200-488e-910f-67605bf8e192', 'a136fd26-d061-43e7-93ec-41740c4133af', 1019, 0, 'BMW', 'X5', 2013, 'Jeep', TRUE, 'Diesel', 3.0, 137802, 6, 'Automatic', '4x4', '4-May', 'Left wheel', 'Black', 12, FALSE),
    ('5f7ed09c-91ab-40cf-923e-fc1592da60f3', 'a136fd26-d061-43e7-93ec-41740c4133af', 549, 0, 'BMW', '535', 2013, 'Sedan', TRUE, 'Hybrid', 3.0, 151586, 6, 'Automatic', 'Rear', '4-May', 'Left wheel', 'White', 12, FALSE),
    ('20bc382b-d7ef-4730-ada3-d51861d47407', 'a136fd26-d061-43e7-93ec-41740c4133af', 12858, 0, 'KIA', 'Picanto', 2016, 'Hatchback', FALSE, 'Petrol', 1.2, 107000, 4, 'Manual', 'Front', '4-May', 'Left wheel', 'Silver', 2, FALSE),
    ('7307aa8e-6ca2-4271-93c9-fb05592da3c2', 'a136fd26-d061-43e7-93ec-41740c4133af', 15000, 0, 'BMW', '328', 2008, 'Sedan', TRUE, 'CNG', 3.0, 210408, 6, 'Tiptronic', 'Rear', '4-May', 'Left wheel', 'White', 12, FALSE);


-- Insert selling posts into the selling_post table with detailed descriptions
INSERT INTO selling_post (id, owner_id, car_id, available, title, description, post_date)
VALUES
    (gen_random_uuid(), '80deaa2b-aa09-49f8-a1b8-cad9c0d587cd', '7942e51c-47ff-4db5-8f36-11000cd84916', 'Y', 'Luxury Sedan: Mercedes E 320', 'A luxury 2004 Mercedes E 320 sedan with a smooth Tiptronic gearbox and a powerful 3.2L petrol engine. The car features a grey exterior, left-hand drive, and leather interior. It has been well-maintained with 214,000 km on the odometer and 8 airbags for safety.', NOW()),
    (gen_random_uuid(), '80deaa2b-aa09-49f8-a1b8-cad9c0d587cd', 'a4aca235-e00a-478c-aced-b85073ae8ec2', 'Y', 'Sporty Hyundai Genesis Coupe', 'This 2009 Hyundai Genesis Coupe is a sporty and stylish vehicle with a 3.8L petrol engine and an automatic transmission. It offers a sleek black exterior and leather seats, perfect for those who enjoy driving with flair. The car has 123,346 km and comes equipped with 4 airbags.', NOW()),
    (gen_random_uuid(), '80deaa2b-aa09-49f8-a1b8-cad9c0d587cd', '1767f998-a21c-4083-b970-ce9c71259c79', 'Y', 'Reliable Chevrolet Cruze Sedan', 'The 2014 Chevrolet Cruze is a reliable sedan with a 1.4L petrol engine and automatic transmission. It boasts a green exterior and comfortable leather interior. With 80,827 km on the odometer, this car is ideal for daily commuting. It also features 12 airbags for enhanced safety.', NOW()),
    (gen_random_uuid(), '80deaa2b-aa09-49f8-a1b8-cad9c0d587cd', '4edaf7ab-ab2f-41c0-9c31-d1abbdb755cd', 'Y', 'Classic Porsche 911 Coupe', 'This classic 2013 Porsche 911 is a coupe with a 3.8L petrol engine and an automatic transmission. It features a stunning black exterior, leather interior, and a 2-door configuration. The car has been well-kept with only 64,464 km. It is equipped with 12 airbags for safety.', NOW()),
    (gen_random_uuid(), '80deaa2b-aa09-49f8-a1b8-cad9c0d587cd', 'c7dc5b27-e515-4595-9a57-aac481c9e1e3', 'Y', 'Eco-Friendly Ford Fusion Hybrid', 'An eco-friendly 2015 Ford Fusion with hybrid technology, perfect for environmentally conscious drivers. It features a sleek black exterior, leather interior, and a 2.0L engine. This sedan has 91,760 km and is equipped with an automatic transmission and 12 airbags.', NOW()),
    (gen_random_uuid(), '80deaa2b-aa09-49f8-a1b8-cad9c0d587cd', '7700cfb8-8162-4eca-b2f8-5357f05b86b8', 'Y', 'Luxurious Lexus GX 460', 'A luxurious 2010 Lexus GX 460, ideal for families. This Jeep features a 4.6L petrol engine, an automatic transmission, and a spacious silver exterior. The car is equipped with a 4x4 drive system and leather interior. With 275,240 km, it is a reliable and comfortable ride.', NOW()),
    (gen_random_uuid(), 'ff10009a-6e35-428b-bb99-e4cb4eabe8f4', '1b58af83-509b-4e57-b3d7-0f5609653164', 'Y', 'Spacious Mercedes Sprinter Microbus', 'This versatile 2004 Mercedes Sprinter is perfect for businesses and large families. It comes with a 2.2L diesel engine, a manual gearbox, and a spacious white exterior. The microbus configuration makes it suitable for various commercial purposes. It has 300,000 km and is equipped with 1 airbag.', NOW()),
    (gen_random_uuid(), 'ff10009a-6e35-428b-bb99-e4cb4eabe8f4', '504c58ec-7355-4ed3-a57a-acfd6381eb76', 'Y', 'Fuel-Efficient Toyota Prius Hatchback', 'The 2007 Toyota Prius is a hatchback that combines fuel efficiency with practicality. It features a 1.5L petrol engine, an automatic transmission, and a silver exterior. The car has 318,400 km and offers a comfortable driving experience with 6 airbags.', NOW()),
    (gen_random_uuid(), 'ff10009a-6e35-428b-bb99-e4cb4eabe8f4', '0aa3f2ba-0c44-4710-bc9b-38db90545b53', 'Y', 'Reliable Ford Focus SE Sedan', 'A reliable 2015 Ford Focus SE sedan, perfect for everyday use. It comes with a 2.0L petrol engine, automatic transmission, and a sky blue exterior. The car has 91,200 km and is equipped with 10 airbags, making it a safe and practical choice.', NOW()),
    (gen_random_uuid(), 'ff10009a-6e35-428b-bb99-e4cb4eabe8f4', '52546be9-e200-488e-910f-67605bf8e192', 'Y', 'Luxury BMW X5 Jeep', 'Experience luxury with the 2013 BMW X5 Jeep. It features a 3.0L diesel engine, an automatic transmission, and a sleek black exterior. The car is equipped with a 4x4 drive system and leather interior. With 137,802 km, it offers a powerful and comfortable ride with 12 airbags.', NOW()),
    (gen_random_uuid(), 'ff10009a-6e35-428b-bb99-e4cb4eabe8f4', '5f7ed09c-91ab-40cf-923e-fc1592da60f3', 'Y', 'Hybrid BMW 535 Sedan', 'The 2013 BMW 535 is a hybrid sedan that offers a perfect blend of performance and efficiency. It has a 3.0L engine, an automatic transmission, and a white exterior. With 151,586 km, this car is well-maintained and comes with 12 airbags.', NOW()),
    (gen_random_uuid(), 'ff10009a-6e35-428b-bb99-e4cb4eabe8f4', '20bc382b-d7ef-4730-ada3-d51861d47407', 'Y', 'Compact KIA Picanto Hatchback', 'The 2016 KIA Picanto is a compact hatchback, ideal for city driving. It features a 1.2L petrol engine, manual transmission, and a silver exterior. The car has 107,000 km and offers a convenient and economical driving experience. It is equipped with 2 airbags.', NOW()),
    (gen_random_uuid(), 'ff10009a-6e35-428b-bb99-e4cb4eabe8f4', '7307aa8e-6ca2-4271-93c9-fb05592da3c2', 'Y', 'CNG-Powered BMW 328 Sedan', 'A rare find, this 2008 BMW 328 sedan is powered by CNG. It has a 3.0L engine, Tiptronic transmission, and a white exterior. The car offers a unique and eco-friendly driving experience. With 210,408 km, it is a great choice for those looking for a stylish and sustainable car.', NOW());
