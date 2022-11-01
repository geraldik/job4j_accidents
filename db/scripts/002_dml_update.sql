INSERT INTO accident_type(name)
VALUES ('type1'),
       ('type2'),
       ('type3');
INSERT INTO accident(name, description, address, accident_type_id)
VALUES ('name1', 'text1', 'address1', 1),
       ('name2', 'text2', 'address2', 2),
       ('name3', 'text3', 'address3', 3);
INSERT INTO rule(name)
VALUES ('rule1'),
       ('rule2'),
       ('rule3');
INSERT INTO accident_rule(accident_id, rule_id)
VALUES (1, 1),
       (2, 1),
       (2, 2),
       (3, 1),
       (3, 2),
       (3, 3);