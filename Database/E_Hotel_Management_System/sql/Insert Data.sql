INSERT INTO hotel_chain (hotelChain_id, name, street, city ,province , zipcode,country, number_hotels, contact_email, contact_phone)
VALUES
(1, 'Hotel Chain A', '456 Oak St ','Los Angeles','CA', '90001', 'USA', 8, 'contactChainA@chain-a.com', '555-111-1111'),
(2, 'Hotel Chain B', '123 Pine St', 'Toronto', 'ON', 'M4E1A1', 'Canada', 8, 'contactChianB@chain-b.com', '555-222-2222'),
(3, 'Hotel Chain C', '789 Maple St', 'New York', 'NY', '10001', 'USA', 8, 'contactChianC@chain-c.com', '555-333-3333'),
(4, 'Hotel Chain D', '159 Elm St', 'Vancouver', 'BC', 'V5K0A1', 'Canada', 8, 'contactChianD@chain-d.com', '555-444-4444'),
(5, 'Hotel Chain E', '246 Birch St', 'Chicago', 'IL', '60601', 'USA', 8, 'contactChainE@chain-e.com', '555-555-5555');


INSERT INTO hotel (hotelChain_id, hotel_id, name, number_rooms, street, city ,province , zipcode,country, categorization, contact_email, contact_phone) 
VALUES
     
      (1, 181, 'Hotel A1', 5, '17 Red Maple Drive','Los Angeles','CA',' 90028', 'USA', 3, 'contactA1@chain-a.com', '555-111-1001'),
      (1, 182, 'Hotel A2', 5, '862 Red Maple Drivet','Los Angeles','CA', '90174', 'USA', 4, 'contactA2@chain-a.com', '555-111-2002'),
      (1, 183, 'Hotel A3', 5, '4104 Atha Drive', 'San Diego', 'CA', '​​93534', 'USA', 5, 'contactA3@chain-a.com', '555-111-3003'),
      (1, 184, 'Hotel A4', 5, '4832 Cherry Tree Drive', 'Jacksonville', 'FL', '32202', 'USA', 3, 'contactA4@chain-a.com', '555-111-4004'),
      (1, 185, 'Hotel A5', 5, '2352 Boundary Street', 'Jacksonville', 'FL', '31980', 'USA', 3, 'contactA5@chain-a.com', '555-111-5005'),
      (1, 186, 'Hotel A6', 5, '705 Stratford Drive', 'Waipahu', 'HI' ,'96797', 'USA', 4, 'contactA6@chain-a.com', '555-111-6006'),
      (1, 187, 'Hotel A7', 5, '3293 Carriage Court', 'San Diego', 'CA' ,'92262', 'USA', 5, 'contactA7@chain-a.com', '555-111-7007'),
      (1, 188, 'Hotel A8',  5,'3493 Thompson Drive', 'San Jose', 'CA', '94538', 'USA', 5, 'contactA8@chain-a.com', '555-111-8008'),
(2, 281, 'Hotel B1', 5, '1116 Papineau Avenue','Montreal','QC',' H2K4J5', 'Canada', 3, 'contactB1@chain-b.com', '555-222-1001'),
(2, 282, 'Hotel B2', 5, '2529 Victoria Park Ave', 'Toronto', 'ON', 'M4A2M5', 'Canada', 4, 'contactB2@chain-bl.com', '555-222-2002'),
(2, 283, 'Hotel B3', 5, '1128 Victoria Park Ave', 'Toronto', 'ON', 'M4A2M5', 'Canada', 5, 'contactB3@chain-b.com', '555-222-3003'),
(2, 284, 'Hotel B4', 5, '1384 Queens Bay', 'Rossland', 'BC', 'V0G1Y0', 'Canada', 4, 'contactB4@chain-b.com', '555-222-4004'),
(2, 285, 'Hotel B5', 5, '400 Mill Street', 'Sebringville', 'ON', 'N0K1X0', 'Canada', 3, 'contactB5@chain-b.com', '555-222-5005'),
(2, 286, 'Hotel B6', 5, '4002 Eglinton Avenue', 'Toronto', 'ON', 'M4P1A6', 'Canada', 4, 'contactB6@chain-b.com', '555-222-6006'),
(2, 287, 'Hotel B7',  5,'1928 Adelaide St', 'Toronto', 'ON', 'M5H1P6', 'Canada', 5, 'contactB7@chain-b.com', '555-222-7007'),
(2, 288, 'Hotel B8', 5, '1536 Blanshard', 'Victoria', 'BC', 'J0H1M0', 'Canada', 3, 'contactB8@chain-b.com', '555-222-8008'),
(3, 381, 'Hotel C1',  5,'4651 Geraldine Lane', 'New York', 'NY', '10013', 'USA', 5, 'contactC1@chain-c.com', '555-333-1001'),
(3, 382, 'Hotel C2', 5, '3717 Bottom Lane', 'East Aurora', 'NY', '14052', 'USA', 4, 'contactC2@chain-c.com', '555-333-2002'),
(3, 383, 'Hotel C3', 5, '852 Small Street', 'New York', 'NY', '10019', 'USA', 5, 'contactC3@chain-c.com', '555-333-1001'),
(3, 384, 'Hotel C4', 5,'1390 Jones Street', 'Fort Worth', 'TX', '76102', 'USA', 3, 'contactC4@chain-c.com', '555-333-2002'),
(3, 385, 'Hotel C5', 5, '847 Farnum Road', 'New York', 'NY', '10016', 'USA', 4, 'contactC5@chain-c.com', '555-333-1001'),
(3, 386, 'Hotel C6', 5, '1953 Nutters Barn Lane', 'Ames', 'IA', '50010', 'USA', 3, 'contactC6@chain-c.com', '555-333-2002'),
(3, 387, 'Hotel C7', 5, '4816 Larry Street', 'Oakland', 'CA', '94612', 'USA', 4, 'contactC7@chain-c.com', '555-333-1001'),
(3, 388, 'Hotel C8', 5, '360 Union Street', 'Seattle', 'WA', '98105', 'USA', 4, 'contactC8@chain-c.com', '555-333-2002'),
 (4, 481, 'Hotel D1', 5, '2955 Carling Avenue', 'Ottawa', 'ON', 'K1Z7B5', 'Canada', 4, 'contactD1@chain-d.com', '555-444-1001'),
(4, 482, 'Hotel D2',  5,'2166 Jasper Avenue', 'Edmonton', 'AB', 'T5J3N2', 'Canada', 4, 'contactD2@chain-d.com', '555-444-2002'),
(4, 483, 'Hotel D3',  5,'3594 Tanner Street', 'Vancouver', 'BC', 'V5R2T4', 'Canada', 5, 'contactD3@chain-d.com', '555-444-3003'),
(4, 484, 'Hotel D4',  5,'4784 Robson St', 'Vancouver', 'BC', 'V6B3K9', 'Canada', 5, 'contactD4@chain-d.com', '555-444-4004'),
(4, 485, 'Hotel D5', 5, '260 rue Levy', 'Montreal', 'QC', 'H3C5K4', 'Canada', 4, 'contactD5@chain-d.comm', '555-444-5005'),
(4, 486, 'Hotel D6', 5, '362 Maynard Rd', 'Calgary', 'AB', 'T2E6J8', 'Canada', 3, 'contactD6@chain-d.com', '555-444-6006'),
(4, 487, 'Hotel D7',  5,'366 Hastings Street', 'Vancouver', 'BC', 'V6C1B4', 'Canada', 3, 'contactD7@chain-d.com', '555-444-7007'),
(4, 488, 'Hotel D8', 5, '2220 Tycos Dr', 'Toronto', 'ON', 'M5T1T4', 'Canada', 5, 'contactD8@chain-d.com', '555-444-8008'),
 (5, 581, 'Hotel E1', 5, '1388 Rebecca Street','Chicago','IL',' 60607', 'USA', 5, 'contactE1@chain-e.com', '555-555-1001'),
(5, 582, 'Hotel E2', 5, '1871 Edgewood Avenue', 'Fresno', 'CA', '93721', 'USA', 4, 'contactE2@chain-e.com', '555-555-2002'),
(5, 583, 'Hotel E3', 5, '3204 Cherry Camp Road','Chicago', 'IL', '60718', 'USA', 3, 'contactE3@chain-e.com', '555-555-3003'),
(5, 584, 'Hotel E4', 5, '2995 Bird Street', 'Gallup', 'NM', '87301', 'USA', 4, 'contactE4@chain-e.com', '555-555-4004 '),
(5, 585, 'Hotel E5', 5, '527 Jadewood Drive', 'Chicago', 'IL', '60431', 'USA', 5, 'contactE5@chain-e.com', '555-555-5005'),
(5, 586, 'Hotel E6', 5, '1815 Cedarstone Drive', 'Findlay', 'OH', '45840', 'USA', 5, 'contactE6@chain-e.com', '555-555-6006'),
(5, 587, 'Hotel E7',  5,'3609 Owagner Lane', 'Seattle', 'WA', '98121', 'USA', 3, 'contactE7@chain-e.com', '555-555-7007'),
(5, 588, 'Hotel E8', 5, '3093 Chestnut Street','Tampa','FL',' 33602', 'USA', 4, 'contactE8@chain-e.com', '555-555-8008');

INSERT INTO Room (room_number, price, amenities, capacity, Has_Sea_View, Has_Mountain_View, extendable, damages, hotel_id)
VALUES

  (1811, 100, 'TV, Air Condition', 'Single', false, true, false, null,  181),
  (1812, 250, 'TV, Air Condition, Fridge', 'Double', true, false, true, null,  181),
  (1813, 200, 'TV, Air Condition, Fridge', 'Single', false, true, true, null,  181),
  (1814, 400, 'TV, Air Condition, Fridge, Bar', 'Double', true, false, false, null,  181),
  (1815, 250, 'TV, Air Condition, Fridge', 'Double', true, true, false, null,  181),

  (1821, 110, 'TV, Fridge', 'Single', false, true, false, null,  182),
  (1822, 400, 'TV, Air Condition, Fridge, Bar', 'Double', true, false, true, null, 182),
  (1823, 210, 'TV, Air Condition, Fridge', 'Double', false, true, true, null, 182),
  (1824, 260, 'TV, Air Condition, Fridge, Bar', 'Single', true, false, false, null,182),
  (1825, 160, 'TV, Air Condition, Fridge', 'Single', true, true, false, null, 182),

  (1831, 200, 'TV, Air Condition, Fridge', 'Double', false, true, false, null, 183),
  (1832, 200, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 183),
  (1833, 180, 'Air Condition, Fridge', 'Double', false, true, true, null, 183),
  (1834, 200, 'TV, Air Condition, Fridge', 'Double', true, false, false, null,183),
  (1835, 160, 'TV, Air Condition, Fridge', 'Single', true, true, false, null, 183),

  (1841, 160, 'TV, Air Condition, Fridge', 'Single', false, true, false, null, 184),
  (1842, 160, 'TV, Air Condition, Fridge', 'Single', true, false, true, null, 184),
  (1843, 300, 'TV, Air Condition, Fridge, Bar', 'Single', false, true, true, null, 184),
  (1844, 120, 'TV, Air Condition', 'Single', true, false, false, null,184),
  (1845, 160, 'TV, Air Condition, Fridge', 'Single', true, true, false, null, 184),

  (1851, 160, 'TV, Air Condition, Fridge', 'Single', false, true, false, null, 185),
  (1852, 250, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 185),
  (1853, 200, 'TV, Air Condition', 'Double', false, true, true, null, 185),
  (1854, 350, 'TV, Air Condition, Fridge, Bar', 'Double', true, false, false, null, 185),
  (1855, 160, 'TV, Air Condition, Fridge', 'Single', true, true, false, null, 185),

  (1861, 250, 'TV, Air Condition, Fridge', 'Double', false, true, false, null, 186),
  (1862, 250, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 186),
  (1863, 250, 'TV, Air Condition, Fridge', 'Double', false, true, true, null, 186),
  (1864, 250, 'TV, Air Condition, Fridge', 'Double', true, false, false, null,186),
  (1865, 250, 'TV, Air Condition, Fridge', 'Double', true, true, false, null, 186),

  (1871, 150, 'Air Condition, Fridge', 'Single', false, true, false, null, 187),
  (1872, 300, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 187),
  (1873, 300, 'TV, Air Condition, Fridge', 'Double', false, true, true, null, 187),
  (1874, 120, 'TV, Air Condition', 'Single', true, false, false, null,187),
  (1875, 300, 'TV, Air Condition, Fridge', 'Double', true, true, false, null, 187),

  (1881, 250, 'TV, Air Condition, Fridge', 'Double', false, true, false, null, 188),
  (1882, 250, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 188),
  (1883, 180, 'TV, Air Condition', 'Single', false, true, true, null, 188),
  (1884, 250, 'TV, Air Condition, Fridge', 'Double', true, false, false, null, 188),
  (1885, 500, 'TV, Air Condition, Fridge, Bar, Gym', 'Double', true, true, false, null, 188),

 (2811, 210, 'TV, Air Condition, Fridge', 'Double', false, true, false, null, 281),
  (2812, 160, 'TV, Air Condition, Fridge', 'Single', true, false, true, null, 281),
  (2813, 160, 'TV, Air Condition, Fridge', 'Single', false, true, true, null, 281),
  (2814, 180, 'TV,Air Condition', 'Double', true, false, false, null, 281),
  (2815, 210, 'TV, Air Condition, Fridge', 'Double', true, true, false, null, 281),

  (2821, 110, 'TV, Fridge', 'Single', false, true, false, null, 282),
  (2822, 200, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 282),
  (2823, 200, 'TV, Air Condition, Fridge', 'Double', false, true, true, null, 282),
  (2824, 180, 'TV, Air Condition, Fridge', 'Single', true, false, false, null, 282),
  (2825, 180, 'TV, Air Condition, Fridge', 'Single', true, true, false, null, 282),

  (2831, 200, 'TV, Air Condition, Fridge', 'Double', false, true, false, null, 283),
  (2832, 200, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 283),
  (2833, 180, 'Air Condition, Fridge', 'Double', false, true, true, null, 283),
  (2834, 200, 'TV, Air Condition, Fridge', 'Double', true, false, false, null, 283),
  (2835, 160, 'TV, Air Condition, Fridge', 'Single', true, true, false, null, 283),

  (2841, 400, 'TV, Air Condition, Fridge, Bar, Sana', 'Single', false, true, false, null, 284),
  (2842, 200, 'TV, Air Condition, Fridge', 'Single', true, false, true, null, 284),
  (2843, 200, 'TV, Air Condition, Fridge', 'Single', false, true, true, null, 284),
  (2844, 250, 'TV, Air Condition, Fridge', 'Double', true, false, false, null, 284),
  (2845, 500, 'TV, Air Condition, Fridge, Bar, Sana', 'Single', true, true, false, null, 284),

  (2851, 150, 'TV, Air Condition, Fridge', 'Single', false, true, false, null, 285),
  (2852, 250, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 285),
  (2853, 200, 'TV, Fridge', 'Double', false, true, true, null, 285),
  (2854, 250, 'TV, Air Condition, Fridge', 'Double', true, false, false, null, 285),
  (2855, 150, 'TV, Air Condition, Fridge', 'Single', true, true, false, null, 285),

  (2861, 250, 'TV, Air Condition, Fridge', 'Double', false, true, false, null, 286),
  (2862, 250, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 286),
  (2863, 250, 'TV, Air Condition, Fridge', 'Double', false, true, true, null, 286),
  (2864, 250, 'TV, Air Condition, Fridge', 'Double', true, false, false, null, 286),
  (2865, 250, 'TV, Air Condition, Fridge', 'Double', true, true, false, null, 286),

  (2871, 180, 'Air Condition, Fridge', 'Single', false, true, false, null, 287),
  (2872, 300, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 287),
  (2873, 300, 'TV, Air Condition, Fridge', 'Double', false, true, true, null, 287),
  (2874, 200, 'TV, Air Condition', 'Single', true, false, false, null, 287),
  (2875, 300, 'TV, Air Condition, Fridge', 'Double', true, true, false, null, 287),

  (2881, 200, 'TV, Air Condition, Fridge', 'Single', false, true, false, null, 288),
  (2882, 260, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 288),
  (2883, 200, 'TV, Air Condition, Fridge', 'Single', false, true, true, null, 288),
  (2884, 260, 'TV, Air Condition, Fridge', 'Double', true, false, false, null,288),
  (2885, 260, 'TV, Air Condition, Fridge', 'Double', true, true, false, null, 288),


 (3811, 100, 'Air Condition', 'Single', false, true, false, null, 381),
  (3812, 250, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 381),
  (3813, 200, 'TV, Air Condition, Fridge', 'Single', false, true, true, null, 381),
  (3814, 250, 'TV, Air Condition, Fridge', 'Double', true, false, false, null, 381),
  (3815, 300, 'TV, Air Condition, Fridge, Bar', 'Double', true, true, false, null, 381),

  (3821, 110, 'TV, Fridge', 'Single', false, true, false, null, 382),
  (3822, 160, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 382),
  (3823, 210, 'TV, Air Condition, Fridge', 'Double', false, true, true, null, 382),
  (3824, 260, 'TV, Air Condition, Fridge', 'Single', true, false, false, null,382),
  (3825, 310, 'TV, Air Condition, Fridge', 'Single', true, true, false, null, 382),

  (3831, 200, 'TV, Air Condition, Fridge', 'Double', false, true, false, null, 383),
  (3832, 200, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 383),
  (3833, 180, 'Air Condition, Fridge', 'Double', false, true, true, null, 383),
  (3834, 200, 'TV, Air Condition, Fridge', 'Double', true, false, false, null,383),
  (3835, 160, 'TV, Air Condition, Fridge', 'Single', true, true, false, null, 383),

  (3841, 160, 'TV, Air Condition, Fridge', 'Single', false, true, false, null, 384),
  (3842, 160, 'TV, Air Condition, Fridge', 'Single', true, false, true, null, 384),
  (3843, 230, 'TV, Air Condition, Fridge, Bar', 'Single', false, true, true, null, 384),
  (3844, 160, 'TV, Air Condition, Fridge', 'Single', true, false, false, null,384),
  (3845, 200, 'TV, Air Condition, Fridge', 'Double', true, true, false, null, 384),

  (3851, 160, 'TV, Air Condition, Fridge', 'Single', false, true, false, null, 385),
  (3852, 250, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 385),
  (3853, 200, 'TV, Fridge', 'Double', false, true, true, null, 385),
  (3854, 250, 'TV, Air Condition, Fridge', 'Double', true, false, false, null, 385),
  (3855, 160, 'TV, Air Condition, Fridge', 'Single', true, true, false, null, 385),

  (3861, 250, 'TV, Air Condition, Fridge', 'Double', false, true, false, null, 386),
  (3862, 250, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 386),
  (3863, 250, 'TV, Air Condition, Fridge', 'Double', false, true, true, null, 386),
  (3864, 250, 'TV, Air Condition, Fridge', 'Double', true, false, false, null,386),
  (3865, 250, 'TV, Air Condition, Fridge', 'Double', true, true, false, null, 386),

  (3871, 150, 'Air Condition, Fridge', 'Single', false, true, false, null, 387),
  (3872, 400, 'TV, Air Condition, Fridge, Bar, Gym', 'Double', true, false, true, null, 387),
  (3873, 200, 'TV, Air Condition, Fridge', 'Double', false, true, true, null, 387),
  (3874, 300, 'TV, Air Condition, Fridge, Bar', 'Single', true, false, false, null,387),
  (3875, 200, 'TV, Air Condition, Fridge', 'Double', true, true, false, null, 387),

  (3881, 300, 'TV, Air Condition, Fridge, Bar', 'Single', false, true, false, null, 388),
  (3882, 260, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 388),
  (3883, 180, 'TV, Air Condition', 'Single', false, true, true, null, 388),
  (3884, 260, 'TV, Air Condition, Fridge', 'Double', true, false, false, null, 388),
  (3885, 260, 'TV, Air Condition, Fridge', 'Double', true, true, false, null, 388),


 (4811, 100, 'TV, Air Condition', 'Single', false, true, false, null, 481),
  (4812, 200, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 481),
  (4813, 150, 'TV, Air Condition, Fridge', 'Single', false, true, true, null, 481),
  (4814, 120, 'TV', 'Double', true, false, false, null, 481),
  (4815, 200, 'TV, Air Condition, Fridge', 'Double', true, true, false, null, 481),

  (4821, 120, 'TV, Air Condition', 'Single', false, true, false, null, 482),
  (4822, 250, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 482),
  (4823, 250, 'TV, Air Condition, Fridge', 'Double', false, true, true, null, 482),
  (4824, 150, 'TV, Air Condition, Fridge', 'Single', true, false, false, null,482),
  (4825, 150, 'TV, Air Condition, Fridge', 'Single', true, true, false, null, 482),

  (4831, 200, 'TV, Air Condition, Fridge', 'Double', false, true, false, null, 483),
  (4832, 200, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 483),
  (4833, 180, 'Air Condition, Fridge', 'Double', false, true, true, null, 483),
  (4834, 200, 'TV, Air Condition, Fridge', 'Double', true, false, false, null, 483),
  (4835, 160, 'TV, Air Condition, Fridge', 'Single', true, true, false, null, 483),

  (4841, 160, 'TV, Air Condition, Fridge', 'Single', false, true, false, null, 484),
  (4842, 160, 'TV, Air Condition, Fridge', 'Single', true, false, true, null, 484),
  (4843, 210, 'TV, Air Condition, Fridge, Bar', 'Single', false, true, true, null, 484),
  (4844, 160, 'TV, Air Condition, Fridge', 'Single', true, false, false, null, 484),
  (4845, 160, 'TV, Air Condition, Fridge', 'Single', true, true, false, null, 484),

  (4851, 130, 'TV, Air Condition, Fridge', 'Single', false, true, false, null, 485),
  (4852, 250, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 485),
  (4853, 200, 'TV, Air Condition', 'Double', false, true, true, null, 485),
  (4854, 250, 'TV, Air Condition, Fridge', 'Double', true, false, false, null,485),
  (4855, 130, 'TV, Air Condition, Fridge', 'Single', true, true, false, null, 485),

  (4861, 250, 'TV, Air Condition, Fridge', 'Double', false, true, false, null, 486),
  (4862, 250, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 486),
  (4863, 300, 'TV, Air Condition, Fridge, Bar', 'Double', false, true, true, null, 486),
  (4864, 250, 'TV, Air Condition, Fridge', 'Double', true, false, false, null,486),
  (4865, 350, 'TV, Air Condition, Fridge, Sana', 'Double', true, true, false, null, 486),

  (4871, 200, 'TV, Air Condition', 'Single', false, true, false, null, 487),
  (4872, 300, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 487),
  (4873, 300, 'TV, Air Condition, Fridge', 'Double', false, true, true, null, 487),
  (4874, 200, 'TV, Air Condition', 'Single', true, false, false, null, 487),
  (4875, 300, 'TV, Air Condition, Fridge', 'Double', true, true, false, null, 487),

  (4881, 210, 'TV, Air Condition, Fridge', 'Single', false, true, false, null, 488),
  (4882, 300, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 488),
  (4883, 180, 'TV, Air Condition', 'Single', false, true, true, null, 488),
  (4884, 300, 'TV, Air Condition, Fridge', 'Double', true, false, false, null, 488),
  (4885, 300, 'TV, Air Condition, Fridge', 'Double', true, true, false, null, 488),

 (5811, 130, 'TV, Air Condition', 'Single', false, true, false, null, 581),
  (5812, 250, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 581),
  (5813, 150, 'TV, Air Condition, Fridge', 'Single', false, true, true, null, 581),
  (5814, 120, 'Air Condition', 'Double', true, false, false, null, 581),
  (5815, 250, 'TV, Air Condition, Fridge', 'Double', true, true, false, null, 581),

  (5821, 110, 'TV, Fridge', 'Single', false, true, false, null, 582),
  (5822, 200, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 582),
  (5823, 200, 'TV, Air Condition, Fridge', 'Double', false, true, true, null, 582),
  (5824, 160, 'TV, Air Condition, Fridge', 'Single', true, false, false, null,582),
  (5825, 160, 'TV, Air Condition, Fridge', 'Single', true, true, false, null, 582),

  (5831, 200, 'TV, Air Condition, Fridge', 'Double', false, true, false, null, 583),
  (5832, 200, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 583),
  (5833, 180, 'Air Condition, Fridge', 'Double', false, true, true, null, 583),
  (5834, 300, 'TV, Air Condition, Fridge, Bar', 'Double', true, false, false, null, 583),
  (5835, 160, 'TV, Air Condition, Fridge', 'Single', true, true, false, null, 583),

  (5841, 160, 'TV, Air Condition, Fridge', 'Single', false, true, false, null, 584),
  (5842, 160, 'TV, Air Condition, Fridge', 'Single', true, false, true, null, 584),
  (5843, 100, 'Air Condition', 'Single', false, true, true, null, 584),
  (5844, 160, 'TV, Air Condition, Fridge', 'Single', true, false, false, null, 584),
  (5845, 200, 'TV, Air Condition, Fridge', 'Double', true, true, false, null, 584),

  (5851, 130, 'TV, Air Condition, Fridge', 'Single', false, true, false, null, 585),
  (5852, 200, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 585),
  (5853, 150, 'TV, Air Condition', 'Double', false, true, true, null, 585),
  (5854, 200, 'TV, Air Condition, Fridge', 'Double', true, false, false, null, 585),
  (5855, 130, 'TV, Air Condition, Fridge', 'Single', true, true, false, null, 585),

  (5861, 400, 'TV, Air Condition, Fridge, Bar, Gym', 'Double', false, true, false, null, 586),
  (5862, 300, 'TV, Air Condition, Fridge, Bar', 'Single', true, false, true, null, 586),
  (5863, 250, 'TV, Air Condition, Fridge', 'Double', false, true, true, null, 586),
  (5864, 250, 'TV, Air Condition, Fridge', 'Double', true, false, false, null,586),
  (5865, 200, 'TV, Air Condition, Fridge', 'Single', true, true, false, null, 586),

  (5871, 150, 'Air Condition, Fridge', 'Single', false, true, false, null, 587),
  (5872, 300, 'TV, Air Condition, Fridge', 'Double', true, false, true, null, 587),
  (5873, 300, 'TV, Air Condition, Fridge', 'Double', false, true, true, null, 587),
  (5874, 200, 'TV, Air Condition, Fridge', 'Single', true, false, false, null, 587),
  (5875, 300, 'TV, Air Condition, Fridge', 'Double', true, true, false, null, 587),

  (5881, 200, 'TV, Air Condition, Fridge', 'Single', false, true, false, null, 588),
  (5882, 300, 'TV, Air Condition, Fridge, Bar', 'Double', true, false, true, null, 588),
  (5883, 200, 'TV, Air Condition, Fridge', 'Single', false, true, true, null, 588),
  (5884, 250, 'TV, Air Condition, Fridge', 'Double', true, false, false, null, 588),
  (5885, 250, 'TV, Air Condition, Fridge', 'Double', true, true, false, null, 588);

INSERT INTO role (role_id, role_name)
VALUES
(1, 'Manager'),
(2, 'Receptionist'),
(3, 'Housekeeper'),
(4, 'Event Planner');

INSERT INTO employee (employee_id, first_name, last_name, street, city, province, zipcode, country, ssn, hotel_id)
VALUES
(1001, 'John', 'Doe', '123 Main St', 'Los Angeles', 'CA', '90001', 'USA', '123-45-6789', 181),
(1002, 'Jane', 'Smith', '456 Oak St', 'New York', 'NY', '10001', 'USA', '987-65-4361', 182),
(1003, 'Bob', 'Johnson', '789 Maple Ave', 'Chicago', 'IL', '60601', 'USA', '456-77-9012', 183),
(1004, 'Sara', 'Lee', '159 Elm St', 'Toronto', 'ON', 'M4E1A1', 'Canada', '789-01-9045', 184),
(1005, 'David', 'Wong', '246 Birch St', 'Vancouver', 'BC', 'V5K0A1', 'Canada', '654-32-1098', 185),
(1006, 'Amy', 'Chang', '345 Pine St', 'Los Angeles', 'CA', '90001', 'USA', '274-56-7890', 186),
(1007, 'Tom', 'Wilson', '678 Maple Ave', 'New York', 'NY', '10001', 'USA', '876-54-3210', 187),
(1008, 'Chris', 'Brown', '910 Oak St', 'Chicago', 'IL', '60601', 'USA', '385-67-8901', 188),
(2001, 'Michelle', 'Liu', '246 Birch St', 'Vancouver', 'BC', 'V5K0A1', 'Canada', '012-34-5678', 281),
(2002, 'James', 'Park', '123 Main St', 'Toronto', 'ON', 'M4E1A1', 'Canada', '789-01-2145', 282),
(2003, 'Avery', 'Garcia', '456 Elm St', 'Los Angeles', 'CA', '90001', 'USA', '204-56-7891', 283),
(2004, 'Nathan', 'Lee', '789 Pine Ave', 'New York', 'NY', '10001', 'USA', '876-54-3211', 284),
(2005, 'Katie', 'Wang', '910 Maple St', 'Chicago', 'IL', '60601', 'USA', '335-67-8902', 285),
(2006, 'Hiro', 'Tanaka', '246 Oak St', 'Vancouver', 'BC', 'V5K0A1', 'Canada', '012-34-5679', 286),
(2007, 'Samantha', 'Kim', '123 Main St', 'Toronto', 'ON', 'M4E1A1', 'Canada', '789-01-2346', 287),
(2008, 'Brian', 'Garcia', '456 Elm St', 'Los Angeles', 'CA', '90001', 'USA', '234-56-7892', 288),
(3001, 'Sophia', 'Lee', '789 Pine Ave', 'New York', 'NY', '10001', 'USA', '876-54-3212', 381),
(3002, 'Eric', 'Wang', '910 Maple St', 'Chicago', 'IL', '60601', 'USA', '345-57-8903', 382),
(3003, 'Yuki', 'Nakamura', '246 Oak St', 'Vancouver', 'BC', 'V5K0A1', 'Canada', '012-34-5680', 383),
(3004, 'Emily', 'Choi', '123 Main St', 'Toronto', 'ON', 'M4E1A1', 'Canada', '789-01-2247', 384),
(3005, 'Jacob', 'Nguyen', '456 Elm St', 'Los Angeles', 'CA', '90001', 'USA', '234-50-7893', 385),
(3006, 'Isabella', 'Garcia', '789 Pine Ave', 'New York', 'NY', '10001', 'USA', '876-54-3219', 386),
(3007, 'Liam', 'Wang', '910 Maple St', 'Chicago', 'IL', '60601', 'USA', '345-17-8904', 387),
(3008, 'Ella', 'Nakamura', '246 Oak St', 'Vancouver', 'BC', 'V5K0A1', 'Canada', '012-04-5681', 388),
(4001, 'William', 'Choi', '123 Main St', 'Toronto', 'ON', 'M4E1A1', 'Canada', '789-01-2248', 481),
(4002, 'Olivia', 'Nguyen', '456 Elm St', 'Los Angeles', 'CA', '90001', 'USA', '234-56-7894',  482),
(4003, 'Lucas', 'Garcia', '789 Pine Ave', 'New York', 'NY', '10001', 'USA', '876-54-3214',  483),
(4004, 'Ethan', 'Wang', '910 Maple St', 'Chicago', 'IL', '60601', 'USA', '345-87-8905',  484),
(4005, 'Nina', 'Nakamura', '246 Oak St', 'Vancouver', 'BC', 'V5K0A1', 'Canada', '012-14-5682',  485),
(4006, 'Charlotte', 'Choi', '123 Main St', 'Toronto', 'ON', 'M4E1A1', 'Canada', '789-01-2349',  486),
(4007, 'Mason', 'Nguyen', '456 Elm St', 'Los Angeles', 'CA', '90001', 'USA', '234-56-7877',  487),
(4008, 'Aria', 'Garcia', '789 Pine Ave', 'New York', 'NY', '10001', 'USA', '876-54-3215',  488),
(5001, 'Alexander', 'Wang', '910 Maple St', 'Chicago', 'IL', '60601', 'USA', '345-67-8906', 581),
(5002, 'Sora', 'Nakamura', '246 Oak St', 'Vancouver', 'BC', 'V5K0A1', 'Canada', '012-34-5683', 582),
(5003, 'Avery', 'Choi', '123 Main St', 'Toronto', 'ON', 'M4E1A1', 'Canada', '789-01-2350', 583),
(5004, 'Ethan', 'Garcia', '456 Maple Ave', 'Vancouver', 'BC', 'V5K1W2', 'Canada', '567-89-0123', 584),
(5005, 'Lila', 'Nguyen', '789 Oak St', 'Montreal', 'QC', 'H2V2T8', 'Canada', '987-65-4821', 585),
(5006, 'Owen', 'Kim', '246 Pine St', 'Edmonton', 'AB', 'T6M1C1', 'Canada', '274-56-7830', 586),
(5007, 'Maya', 'Patel', '789 Cedar Rd', 'Ottawa', 'ON', 'K1V1H1', 'Canada', '305-67-8991', 587),
(5008, 'Natalie', 'Kim', '456 Queen St', 'Vancouver', 'BC', 'V5V2B5', 'Canada', '935-67-8901', 588),
(1011, 'Emily', 'Smith', '4808 Bay Street', 'Toronto', 'Ontario', 'M5J 2R8', 'Canada', '456-78-9012', 181),
(1012, 'Brandon', 'Johnson', '1457 New Street', 'Coos Bay', 'Oregon', '97420', 'United States', '789-01-2345', 182),
(1013, 'Adam', 'Williams', '1098 Rhapsody Street', 'Ocala', 'Florida', '34471', 'United States', '890-12-3456', 183),
(1014, 'Natalie', 'Brown', '4996 Braxton Street', 'Thomas (bureau)', 'Illinois', '61346', 'United States', '901-23-4567', 184),
(1015, 'Ryan', 'Lee', '4339 Monroe Street', 'Houston', 'Texas', '77055', 'United States', '123-45-6789', 185),
(1016, 'Jennifer', 'Wilson', '3006 Terra Street', 'Bellingham', 'Washington', '98225', 'United States', '234-56-7890', 186),
(1017, 'Alex', 'Nguyen', '3399 Jarvis Street', 'Buffalo', 'New York', '14202', 'United States', '345-67-8901', 187),
(1018, 'Olivia', 'Gonzalez', '3851 Peck Street', 'Manchester', 'New Hampshire', '03103', 'United States', '456-78-9012', 188),
(2011, 'Ethan', 'Taylor', '2877 Ripple Street', 'Maple Rapids', 'Michigan', '48853', 'United States', '567-89-0123', 281),
(3011, 'Grace', 'Lee', '2940 Maple Court', 'Vienna', 'Missouri', '65582', 'United States', '678-90-1234', 381),
(4011, 'Daniel', 'Kim', '3999 Lock Street', 'Guelph', 'Ontario', 'N1H 2T2', 'Canada', '234-56-7890', 481),
(5011, 'Rachel', 'Lee', '2938 Bay Street', 'Toronto', 'Ontario', 'M5J 2R8', 'Canada', '345-67-8901', 581),
(2012, 'David', 'Wang', '2709 Tolmie St', 'Vancouver', 'British Columbia', 'V6M 1Y8', 'Canada', '456-78-,9012', 282),
(3012, 'Julia', 'Lopez', '3971 Boulevard Cremazie', 'Quebec', 'Quebec', 'G1R 1B8', 'Canada', '567-89-0123', 382),
(4012, 'Michael', 'Chen', '3246 Rogers Road', 'Toronto', 'Ontario', 'M6E 1R1', 'Canada', '678-90-1234', 482),
(5012, 'Sarah', 'Garcia', '2938 Bay Street', 'Toronto', 'Ontario', 'M5J 2R8', 'Canada', '789-01-2345', 582);


INSERT INTO employee_role (employee_id, role_id)
VALUES
(1001, 1), (1002, 1), (1003, 1), (1004, 1), (1005, 1), (1006, 1), (1007, 1), (1008, 1),(2001, 1), (2002, 1), (2003, 1), (2004, 1), (2005, 1), (2006, 1), (2007, 1), (2008, 1),(3001, 1), (3002, 1), (3003, 1), (3004, 1), (3005, 1), (3006, 1), (3007, 1), (3008, 1),(4001, 1), (4002, 1), (4003, 1), (4004, 1), (4005, 1), (4006, 1), (4007, 1), (4008, 1),(5001, 1), (5002, 1), (5003, 1), (5004, 1), (5005, 1), (5006, 1), (5007, 1), (5008,1), (1011,4) ,(1012,2) ,(1013,4) ,(1013,2), (1014,3),(1015,2),(1016,4),(1017,2),(1018,3),(2011,2),(3011,4),(4011,4),(5011,3),(2012,3),(3012,2),(4012,2), (5012,4);

UPDATE hotel
JOIN employee ON hotel.hotel_id = employee.hotel_id
SET hotel.manager_id = employee.employee_id
WHERE hotel.hotel_id IN (181, 182, 183, 184, 185, 186,187,188,281, 282, 283, 284, 285, 286, 287, 288, 381, 382, 383, 384, 385, 386, 387, 388, 481, 482, 483, 484, 485, 486, 487, 488, 581, 582, 583, 584, 585, 586, 587, 588);

