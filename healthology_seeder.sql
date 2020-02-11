USE healthology_db;
# DROP database healthology_db;

insert into users  (about_me, email, first_name, last_name, password, phone_number, profile_img, username)values(
    'This is about me',
    'user@email.com',
    'Billy',
    'Bob',
    'billybob',
    '2108031491',
    '',
    'billyFromStrangerThings'

);

insert into client (agreed_to_terms, user_id)
 VALUES
    (1, 2);

INSERT INTO journal (date, entry, rating, title, client_id)
VALUES

    ('2001-12-11', 'This has been an eventful week for me', 10, 'Week One', 1);

INSERT INTO admins (qualifications) VALUES ('Here are my qualifications');
INSERT INTO `groups` (category_name, group_name, admin_id) VALUES ('depression','Depression group', 1);
INSERT INTO `groups` (category_name, group_name, admin_id) VALUES ('ptsd','PTSD group', 1);
INSERT INTO group_client (client_id, group_id) VALUES (2,1);

