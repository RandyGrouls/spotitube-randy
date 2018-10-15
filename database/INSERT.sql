USE spotitube;

/*inserts into account table*/
INSERT INTO account (user, password , full_name)
VALUES ('randy', 'randypassword', 'Randy Grouls');

/*inserts into playlist table*/
INSERT INTO playlist (account_user, name, owner)
VALUES ('randy', 'Dubstep' , true),
       ('randy', 'Drumstep' , true),
       ('randy', 'Future House' , true),
       ('randy', 'Melbourne Bounce' , true),
       ('randy', 'Hard Dance' , true);

/*inserts into track table*/
INSERT INTO track (playlist_id, title, performer, duration, album, playcount, publication_date, description, offline_available)
VALUES (1, 'Hellfire', 'Vonikk', 234, 'Dubstep', 5, '2014-11-27', 'Hellfire by Vonikk', true),
       (1, 'Lost in Time', 'Extra Terra & Urbanstep', 278, 'Dubstep', 8, '2016-01-15', 'Lost in Time by Extra Terra & Urbanstep', true),
       (1, 'The Great Deception', 'PsoGnar', 328, 'Dubstep', 5, '2016-07-27', 'The Great Deception by PsoGnar', false),
       (2, 'Try This', 'Pegboard Nerds', 225, 'Drumstep', 12, '2015-05-28', 'Try This by Pegboard Nerds', false),
       (2, 'Colorblind', 'Panda Eyes', 289, 'Drumstep', 15, '2015-06-04', 'Colorblind by Panda Eyes', true),
       (2, 'Crystal Cave', 'Panda Eyes', 359, 'Drumstep', 10, '2015-05-13', 'Crystal Cave by Panda Eyes', true),
       (3, 'Steam Phunk', 'Easy', 231, 'Future House', 25, '2015-11-07', 'Steam Phunk by Easy', true),
       (3, 'Precious Time', 'Ollie Crowe', 251, 'Future House', 18, '2015-10-29', 'Precious Time by Ollie Crowe', true),
       (3, 'Back Again', 'Archie', 320, 'Future House', 30, '2015-07-27', 'Back Again by Archie', false),
       (4, '#Bounce', 'Worimi', 241, 'Melbourne Bounce', 19, '2014-11-14', '#Bounce by Worimi', true),
       (4, 'WOHO', 'Blackstripe', 255, 'Melbourne Bounce', 35, '2014-06-26', 'WOHO By Blackstripe', false),
       (4, 'Bounce That', 'Reece Low & New World Sound', 234, 'Melbourne Bounce', 40, '2015-07-19', 'Bounce That by Reece Low & New World Sound', true),
       (5, 'Party Rockin', 'DJ THT', 221, 'Hard Dance', 14, '2015-02-27', 'Party Rockin by DJ THT', true),
       (5, 'Lost in Space', 'Audio Paradyne', 328, 'Hard Dance', 28, '2014-12-12', 'Lost in Space by Audio Paradyne', false),
       (5, 'The Pressure', 'Stonebank', 249, 'Hard Dance', 26, '2015-05-31', 'The Pressure by Stonebank', true);






