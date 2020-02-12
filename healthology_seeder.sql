USE healthology_db;
# DROP database healthology_db;

#
# insert into client (agreed_to_terms, user_id)
#  VALUES
#     (1, 2);
#
# INSERT INTO journal (date, entry, rating, title, client_id)
# VALUES
#
#     ('2001-12-11', 'This has been an eventful week for me', 10, 'Week One', 1);


insert into users (first_name, last_name, password, phone_number, profile_img, email, username, about_me) value (
    'adminFirst', 'adminLast', 'adminPassword', '8030947827', '', 'admin@gmail.com','admin', 'My name is Molly, I am excited to go on this journey with you. I hope that all the resources and groups will give you a positive experience!'
 );

INSERT INTO admins (qualifications, user_id) VALUES ('I graduated from UT Austin inb 2008, with my doctorate in clinical psychology. I did my residency at the 59th Medical Wing, which is clinical psychology intership with the Airforce. I make my goal in life to understanding human behaviour and providing psychological services. I typically have worked with people that are in the military or are veterans. I work with individuals that are suffering from depression, PTSD and anxiety. I believe that having a support group can be beneficial and brighten your day to day life.', 1);


INSERT INTO `groups` (category_name, group_name, admin_id) VALUES
    ('Depression','Depression support group', 1),
    ('PTSD', 'PTSD support group', 1),
    ('Anxiety', 'Anxiety support group', 1),
    ('OCD', 'OCD support group', 1),
    ('Eating disorders', 'Eating disorders support group', 1),
    ('Insomnia','Insomnia support group', 1),
    ('Postpartum', 'Postpartum support group', 1);


# INSERT INTO group_client (client_id, group_id) VALUES (1,1);
