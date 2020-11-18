SET FOREIGN_KEY_CHECKS  = 0;

truncate table pet;
truncate table store;

INSERT into store (`id`,`name`,`location`,`contact_no`)
VALUES (21,'super store','nasarrawa','09090');



-- INSERT into store(`id`,`name`,`colour`,`pet_sex`,`breed`,`age`,`store_id`)
-- VALUES(21, 'super store','nasarawa','09876543');

INSERT INTO pet(`id`,`name`,`colour`,`breed`,`age`,`pet_sex`,`store_id`)
VALUES (31,'jill','blue','parrot',6,'MALE',21),
(32,'jack','black','dog',2,'MALE',21),
(33,'blue','white','cat',6,'MALE',21),
(34,'Pill','Plue','parrot',6,'FEMALE',21);

SET FOREIGN_KEY_CHECKS  = 1;