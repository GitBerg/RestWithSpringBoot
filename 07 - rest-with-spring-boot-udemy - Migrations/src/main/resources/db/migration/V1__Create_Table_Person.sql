CREATE TABLE `person` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `andress` varchar(100) NOT NULL,
                          `first_name` varchar(80) NOT NULL,
                          `gender` varchar(6) NOT NULL,
                          `last_name` varchar(80) NOT NULL,
                          PRIMARY KEY (`id`),
                          KEY `idx_person_andress` (`andress`)
)