
--
-- Структура таблицы `album`
--

CREATE TABLE `Album` (
                         `Album_id` int NOT NULL,
                         `Name` varchar(40) NOT NULL,
    `Release_date` date NOT NULL,
    `Style` varchar(20) DEFAULT NULL,
    `Artist_id` int DEFAULT NULL,
    `Cover_file` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `album`
--

INSERT INTO `Album` (`Album_id`, `Name`, `Release_date`, `Style`, `Artist_id`, `Cover_file`) VALUES
    (1, 'To Pimp a Butterfly', '2015-03-14', 'Hip-hop/Rap', 3, 'd5bff904-c126-46f1-8843-ffff782503c4.PNG'),
    (2, 'DAMN.', '2017-04-13', 'Hip-hop/Rap', 3, '111acd93-8364-45e5-81f3-9219b360b3e1.PNG'),
    (3, 'good kid, m.A.A.d city', '2012-10-22', 'Hip-hop/Rap', 3, 'dfgr6tgf-3457-26y1-45h6-dkfkkrloggh2.PNG'),
    (4, 'ANTI', '2016-01-26', 'Pop', 1, '0fa3a2f6-b04f-4dcf-bc30-d86c6b7ce9f7.PNG'),
    (5, 'Views', '2011-11-14', 'Hip-hop/Rap', 2, 'jkjgfbkl-9754-68l4-58d4-jhkgfdtrtkk5.PNG'),
    (6, 'Loud', '2010-11-11', 'Pop', 1, '126e3305-c2f1-45db-9f91-c1c4884d3f26.PNG'),
    (7, 'Flower Boy', '2017-07-20', 'Hip-hop/Rap', 4, '3e484890-3253-478a-b89a-1eac1605b20d.PNG'),
    (8, '2014 Forest Hills Drive', '2014-12-08', 'Hip-hop/Rap', 5, '5a98d6f3-34ac-458b-9fad-02c8f0a019ea.PNG'),
    (9, '4 Your Eyez Only', '2016-12-09', 'Hip-hop/Rap', 5, 'e2254e46-44fd-4953-968f-f238cf1d5fad.PNG'),
    (10, 'Starboy', '2016-09-20', 'R&B/Soul', 6, '232630c8-4219-4168-922a-266673861b02.PNG'),
    (11, 'Beauty Behind the Madness', '2015-08-28', 'R&B/Soul', 6, '571c2880-3d6c-4408-8d5f-e2ec71d2b79f.PNG'),
    (12, 'Graduation', '2007-09-04', 'Hip-hop/Rap', 7, '38a2e359-2fa5-4c26-9dfd-10bb38cb66a4.PNG'),
    (14, 'The Heart Part 4 - Single', '2017-03-23', 'Hip-hop/Rap', 3, 'd34bca88-cf24-4ac3-a8c4-a21c547b15b0.PNG'),
    (15, 'Чёрный альбом', '1990-01-01', 'Rock', 9, '6d7aad58-0d7a-4679-865d-573fef05fab7.PNG'),
    (16, 'Группа крови', '1988-01-01', 'Rock', 9, 'e93d6294-2894-49fa-8103-633eeacca1ee.PNG'),
    (17, 'The Heart Part 5 - Single', '2022-05-08', 'Hip-hop/rap', 3, '98f97380-c8dd-47d2-b2d9-e369bf8cb669.PNG'),
    (18, 'Songs of Innocence', '2014-09-09', 'Rock', 8, '68a52df4-3db2-42af-b93a-f28431780241.PNG'),
    (19, 'Mr. Morale & The Big Steppers', '2022-05-13', 'Hip-hop/rap', 3, '92abe2ff-3fa4-44f0-ac5e-824a3d104900.PNG');

# --
# -- Триггеры `album`
# --
# DELIMITER $$
# CREATE TRIGGER `delete_song_triger` BEFORE DELETE ON `album` FOR EACH ROW BEGIN
# 	DELETE song
# 	FROM song
# 	INNER JOIN song_in_album ON song_in_album.Song_id = song.Song_id
#     WHERE song_in_album.Album_id = OLD.Album_id;
# END
# $$
# DELIMITER ;

-- --------------------------------------------------------

--
-- Структура таблицы `artist`
--

CREATE TABLE `Artist` (
                          `Artist_id` int NOT NULL,
                          `Nickname` varchar(40) NOT NULL,
    `Email` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `Phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `Avatar_file` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `Page_photo_file` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `artist`
--

INSERT INTO `Artist` (`Artist_id`, `Nickname`, `Email`, `Phone`, `Avatar_file`, `Page_photo_file`) VALUES
    (1, 'Rihanna', 'rihanna@mail.com', '+2956475867', 'ef6def6a-5966-4b45-ab3c-563e1ee24367.PNG', 'd9facdaf-38a3-4bcb-b8ae-bc439bcd8823.PNG'),
    (2, 'Drake', 'drake@mail.com', '+2968498165', '1e78e568-fedb-428a-b670-71377dcf4da2.PNG', '2c0437d0-7a74-4f5c-afc0-4b802baedd85.PNG'),
    (3, 'Kendrick Lamar', 'kendrick@mail.com', '+2968494432', '9d401646-8663-4d73-a0d7-2863d7e03330.PNG', 'ab14d462-2144-41de-bc47-931df8715547.PNG'),
    (4, 'Tyler, The Creator', 'tyler@mail.com', '+2968497140', '022d10a1-dc18-4072-8dee-30af49ba823b.PNG', '1eafc45a-7da1-4d0d-a6ce-d20ec44bcd52.PNG'),
    (5, 'J. Cole', 'jcole@mail.com', '+2968493041', '3d364615-936a-4991-af27-f72abe69f91f.PNG', '32f47ce3-24ae-4b22-93ac-a8d635acb924.PNG'),
    (6, 'The Weeknd', 'weeknd@mail.com', '', 'ea85d0a9-09db-4202-a481-9bef42672c0d.PNG', '1f3dfd5e-f0ef-4b0d-807a-f640ff626eb6.PNG'),
    (7, 'Kanye West', 'kanye@mail.com', '+2968499868', '85be66af-4be7-4eff-b884-c2e8407a7365.PNG', '85a95848-521d-442f-8a24-14c90d3078bc.PNG'),
    (8, 'U2', 'u2@mail.com', '+2956474004', 'a546e16f-6c79-49c2-8a05-b521f9614766.PNG', '46df068c-74b4-4cad-b33c-806636e80988.PNG'),
    (9, 'Кино', '', '', '8215bf82-e73c-4afe-b4fd-7b8fcab0d8be.PNG', '789d4a4f-4736-4f1e-88a8-5df3891061a7.PNG');

-- --------------------------------------------------------

--
-- Структура таблицы `chart`
--

CREATE TABLE `Chart` (
                         `Chart_id` int NOT NULL,
                         `Name` varchar(30) NOT NULL,
                         `Cover_file` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `chart`
--

INSERT INTO `Chart` (`Chart_id`, `Name`, `Cover_file`) VALUES
    (2, 'A-LIST Pop', '9c8b4e77-7fc2-4acb-a34b-bc9a6add9be6.PNG'),
    (3, 'Rap Life', '1046f196-b80e-4ca0-9a0c-7aaaeb664262.PNG');

-- --------------------------------------------------------

--
-- Структура таблицы `featuring_artist`
--

CREATE TABLE `Featuring_artist` (
                                    `Song_id` int NOT NULL,
                                    `Feat_artist_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `featuring_artist`
--

INSERT INTO `Featuring_artist` (`Song_id`, `Feat_artist_id`) VALUES
    (4, 1),
    (5, 1),
    (3, 2),
    (46, 2),
    (35, 8);

-- --------------------------------------------------------

--
-- Структура таблицы `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
    (4);

-- --------------------------------------------------------

--
-- Структура таблицы `song`
--

CREATE TABLE `Song` (
                        `Song_id` int NOT NULL,
                        `Name` varchar(40) NOT NULL,
                        `Main_artist_id` int DEFAULT NULL,
                        `Song_file` varchar(60) DEFAULT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `song`
--

INSERT INTO `Song` (`Song_id`, `Name`, `Main_artist_id`, `Song_file`) VALUES
    (1, 'u', 3, NULL),
    (2, 'Alright', 3, NULL),
    (3, 'Poetic Justice (feat. Drake)', 3, NULL),
    (4, 'LOYALTY. (FEAT. RIHANNA.)', 3, NULL),
    (5, 'Too Good (feat. Rihanna)', 2, NULL),
    (6, 'Hype', 2, NULL),
    (11, 'January 28th', 5, NULL),
    (12, '03\' Adolescence', 5, NULL),
    (13, 'No Role Modelz', 5, NULL),
    (14, 'Immortal', 5, NULL),
    (15, 'Ville Mentality', 5, NULL),
    (16, 'Starboy (feat. Daft Punk)', 6, NULL),
    (17, 'Party Monster', 6, NULL),
    (18, 'False Alarm', 6, NULL),
    (19, 'Reminder', 6, NULL),
    (20, 'Rockin\'', 6, NULL),
    (21, 'Real Life', 6, NULL),
    (22, 'Losers (feat. Labrinth)', 6, NULL),
    (23, 'Often', 6, NULL),
    (24, 'The Hills', 6, NULL),
    (25, 'King Kunta', 3, NULL),
    (26, 'BLOOD.', 3, NULL),
    (27, 'DNA.', 3, 'Kendrick_Lamar_-_DNA.mp3'),
    (28, 'YAH.', 3, NULL),
    (29, 'ELEMENT.', 3, NULL),
    (30, 'FEEL.', 3, NULL),
    (31, 'PRIDE.', 3, NULL),
    (32, 'HUMBLE.', 3, 'Kendrick_Lamar_-_HUMBLE.mp3'),
    (33, 'LUST.', 3, NULL),
    (34, 'LOVE. (FEAT. ZACARI.)', 3, NULL),
    (35, 'XXX. (FEAT. U2.)', 3, NULL),
    (36, 'FEAR.', 3, 'Kendrick_Lamar_-_FEAR_47921131.mp3'),
    (37, 'GOD.', 3, NULL),
    (38, 'DUCKWORTH.', 3, NULL),
    (39, 'Good Morning', 7, NULL),
    (40, 'Champion', 7, NULL),
    (41, 'Stronger', 7, NULL),
    (42, 'I Wonder', 7, NULL),
    (43, 'S&M', 1, NULL),
    (44, 'Man Down', 1, NULL),
    (45, 'James Joint', 1, NULL),
    (46, 'Work (feat. Drake)', 1, '5e87fce8-d54d-4a8e-9b38-e5982eb9595f.mp3');

-- --------------------------------------------------------

INSERT INTO `Song` (`Song_id`, `Name`, `Main_artist_id`, `Song_file`) VALUES
    (53, 'November', 4, NULL),
    (54, 'Glitter', 4, NULL),
    (55, 'The Heart Part 4', 3, 'Kendrick_Lamar_-_The_Heart_Part_4_48247991.mp3'),
    (56, 'Лето', 9, 'Kino_-_Konchitsya_leto_47828973.mp3'),
    (57, 'Кукушка', 9, 'Viktor_Cojj_-_Kukushka_54697834.mp3'),
    (58, 'Следи за собой', 9, 'Kino_-_Sledi_za_sobojj_47828981.mp3'),
    (59, 'Группа крови', 9, 'Kino_-_Gruppa_krovi_47829944.mp3'),
    (60, 'Закрой за мной дверь, я ухожу', 9, 'Kino_-_Zakrojj_za_mnojj_dver_ya_ukhozhu_47829945.mp3'),
    (61, 'Спокойная ночь', 9, 'Kino_-_Spokojjnaya_noch_48614890.mp3'),
    (62, 'Легенда', 9, 'Kino_-_Legenda_47829957.mp3'),
    (64, 'The Miracle (Of Joey Ramone)', 8, '08ed58c9-de99-4dc5-9c2a-79f1da6d72b5.mp3'),
    (65, 'Every Breaking Wave', 8, '4560b213-1711-4d02-ba8a-19eaded9788b.mp3'),
    (66, 'California (There Is No End to Love)', 8, '2c2c2948-840c-40f6-9280-c5c9367542e4.mp3'),
    (67, 'Song for Someone', 8, 'a8daeeb6-866e-4f1a-a4a9-3fc49404c697.mp3'),
    (68, 'Iris (Hold Me Close)', 8, '10b7f5ca-ad18-4deb-ab54-d3022143dcb9.mp3'),
    (69, 'Volcano', 8, '90daa5f1-aef6-41d4-8ea6-e78c06fca168.mp3'),
    (70, 'Raised By Wolves', 8, '76b67b3a-85bb-4bd1-8c63-2eeb132e7bc5.mp3'),
    (71, 'Cedarwood Road', 8, '128ed50d-133d-435e-9c17-d34f16f85fca.mp3'),
    (72, 'Sleep Like A Baby Tonight', 8, '81d75fcc-7c8f-470b-ad3f-84b39ff49a59.mp3'),
    (73, 'This Is Where You Can Reach Me Now', 8, '8215f53f-ddfc-4997-9960-3743c375d3d8.mp3'),
    (74, 'The Troubles (feat. Lykke Li)', 8, '359a7f37-86fb-4327-b3e8-169b1835bebc.mp3'),
    (75, 'The Heart Part 5', 3, 'd5dd13ab-5677-43b1-9f63-23d41d885201.mp3'),
    (76, 'United In Grief', 3, 'ef65485b-7ad8-4c41-8cb3-7b9fabb3c228.mp3'),
    (77, 'N95', 3, '481aa753-6e8e-4fb5-b6e0-a33b35176fd0.mp3'),
    (78, 'Worldwide Steppers', 3, '12035476-b485-42f0-af27-86ff3ad85852.mp3'),
    (79, 'Die Hard', 3, 'adf808d3-d6c9-4dbf-88a9-125b781f9d5b.mp3'),
    (80, 'Father Time (feat. Sampha)', 3, '6eb2580f-b5e8-425b-bd46-d0e990e39d97.mp3'),
    (81, 'Rich (Interlude)', 3, 'c81bf8e6-05e4-4572-bb53-8f803113b2a8.mp3'),
    (82, 'Rich Spirit', 3, '64a7da06-177b-40c7-8b15-214d57e87e23.mp3'),
    (83, 'We Cry Together', 3, '59176d09-acd0-4091-85f1-a5b8f4696c4a.mp3'),
    (84, 'Purple Hearts', 3, 'd172f467-90b5-47f4-8af4-ba0125a03949.mp3'),
    (85, 'Count Me Out', 3, 'a7100e20-8b76-42c5-a45f-f0140fa66e56.mp3'),
    (86, 'Crown', 3, '969e32c6-66e5-4e48-b6c2-2c2a28983013.mp3'),
    (87, 'Silent Hill', 3, '4d28c538-d7d8-4bc9-aeb6-0c657f5c561f.mp3'),
    (88, 'Savior (Interlude)', 3, '282b3137-d3d9-4078-b135-31db56b48a1d.mp3'),
    (89, 'Savior', 3, 'd3e5984a-fb4f-4d52-97bb-d60cf7be48af.mp3'),
    (90, 'Auntie Diaries', 3, '060057fa-958b-49ba-ad4c-98d94911f057.mp3'),
    (91, 'Mr. Morale', 3, '8e3c1bdb-4737-49cf-8982-15a527f8333e.mp3'),
    (92, 'Mother I Sober (feat. Beth Gibbons)', 3, 'a3418841-a094-4864-9cbc-3df25311652f.mp3'),
    (93, 'Mirror', 3, '255b683f-a937-4046-bd23-82a6670d3159.mp3');
-- --------------------------------------------------------

--
-- Структура таблицы `song_in_album`
--

CREATE TABLE `Song_in_album` (
                                `Album_id` int NOT NULL,
                                `Song_id` int NOT NULL,
                                `Is_lead_song` tinyint(1) NOT NULL,
                                `Serial_number` int NOT NULL,
                                `Order_in_album_number` int NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `song_in_album`
--

INSERT INTO `Song_in_album` (`Album_id`, `Song_id`, `Is_lead_song`, `Serial_number`, `Order_in_album_number`) VALUES
    (1, 1, 0, 6, 6),
    (1, 2, 1, 7, 7),
    (3, 3, 0, 6, 6),
    (2, 4, 0, 6, 6),
    (5, 5, 0, 16, 16),
    (5, 6, 0, 5, 5),
    (8, 11, 0, 2, 2),
    (8, 12, 0, 4, 4),
    (8, 13, 1, 9, 9),
    (9, 14, 1, 2, 2),
    (9, 15, 0, 4, 4),
    (10, 16, 1, 1, 1),
    (10, 17, 0, 2, 2),
    (10, 18, 0, 3, 3),
    (10, 19, 0, 4, 4),
    (10, 20, 0, 5, 5),
    (11, 21, 0, 1, 1),
    (11, 22, 0, 2, 2),
    (11, 23, 0, 4, 4),
    (11, 24, 1, 5, 5),
    (1, 25, 1, 3, 3),
    (2, 26, 0, 1, 1),
    (2, 27, 1, 2, 2),
    (2, 28, 0, 3, 3),
    (2, 29, 0, 4, 4),
    (2, 30, 0, 5, 5),
    (2, 31, 0, 7, 7),
    (2, 32, 1, 8, 8),
    (2, 33, 0, 9, 9),
    (2, 34, 0, 10, 10),
    (2, 35, 0, 11, 11),
    (2, 36, 0, 12, 12),
    (2, 37, 0, 13, 13),
    (2, 38, 0, 14, 14),
    (12, 39, 0, 1, 1),
    (12, 40, 0, 2, 2),
    (12, 41, 1, 3, 3),
    (12, 42, 0, 4, 4),
    (6, 43, 1, 1, 1),
    (6, 44, 0, 7, 7),
    (4, 45, 0, 2, 2),
    (4, 46, 1, 4, 4),
    (7, 53, 0, 12, 12),
    (7, 54, 0, 13, 13),
    (14, 55, 1, 1, 1),
    (15, 56, 0, 1, 1),
    (15, 57, 1, 5, 5),
    (15, 58, 0, 8, 8),
    (16, 59, 1, 1, 1),
    (16, 60, 1, 2, 2),
    (16, 61, 0, 4, 4),
    (16, 62, 0, 11, 11),
    (18, 64, 0, 1, 1),
    (18, 65, 1, 2, 2),
    (18, 66, 1, 3, 3),
    (18, 67, 0, 4, 4),
    (18, 68, 1, 5, 5),
    (18, 69, 0, 6, 6),
    (18, 70, 0, 7, 7),
    (18, 71, 0, 8, 8),
    (18, 72, 0, 9, 9),
    (18, 73, 0, 10, 10),
    (18, 74, 0, 11, 11),
    (17, 75, 1, 1, 1),
    (19, 76, 0, 1, 1),
    (19, 77, 1, 2, 2),
    (19, 78, 0, 3, 3),
    (19, 79, 1, 4, 4),
    (19, 80, 1, 5, 5),
    (19, 81, 0, 6, 6),
    (19, 82, 1, 7, 7),
    (19, 83, 0, 8, 8),
    (19, 84, 0, 9, 9),
    (19, 85, 0, 1, 10),
    (19, 86, 0, 2, 11),
    (19, 87, 1, 3, 12),
    (19, 88, 0, 4, 13),
    (19, 89, 0, 5, 14),
    (19, 90, 0, 6, 15),
    (19, 91, 0, 7, 16),
    (19, 92, 0, 8, 17),
    (19, 93, 0, 9, 18);

-- --------------------------------------------------------

--
-- Структура таблицы `song_in_chart`
--

CREATE TABLE `Song_in_chart` (
                                 `Chart_id` int NOT NULL,
                                 `Song_id` int NOT NULL,
                                 `Serial_number` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `song_in_chart`
--

INSERT INTO `Song_in_chart` (`Chart_id`, `Song_id`, `Serial_number`) VALUES
    (3, 2, 3),
    (2, 6, 4),
    (3, 6, 4),
    (3, 13, 1),
    (2, 16, 1),
    (2, 24, 2),
    (3, 32, 5),
    (3, 41, 6),
    (2, 44, 5);

-- --------------------------------------------------------

--
-- Структура таблицы `song_in_library`
--

CREATE TABLE `Song_in_library` (
                                   `User_id` int NOT NULL,
                                   `Song_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `song_in_library`
--

INSERT INTO `Song_in_library` (`User_id`, `Song_id`) VALUES
    (1, 1),
    (2, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (1, 6),
    (1, 13),
    (1, 16),
    (1, 26),
    (1, 30),
    (1, 31),
    (1, 32),
    (1, 38),
    (1, 43),
    (1, 55),
    (1, 59);

-- --------------------------------------------------------

--
-- Дублирующая структура для представления `song_in_library_with_name`
-- (См. Ниже фактическое представление)
--
CREATE TABLE `Song_in_library_with_name` (
    `Name` varchar(40)
    ,`Song_id` int
    ,`User_id` int
    );

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE `User` (
                        `user_id` int NOT NULL,
                        `is_active` tinyint(1) NOT NULL DEFAULT '0',
    `username` varchar(40) NOT NULL,
    `password` varchar(20) NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `User` (`user_id`, `is_active`, `username`, `password`) VALUES
    (1, 1, 'admin', '1'),
    (2, 1, 'user2', '2'),
    (3, 1, 'user3', '3');

-- --------------------------------------------------------

--
-- Структура таблицы `user_role`
--

CREATE TABLE `User_role` (
                             `user_id` int NOT NULL,
                             `roles` varchar(20) NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `user_role`
--

INSERT INTO `User_role` (`user_id`, `roles`) VALUES
    (1, 'USER'),
    (2, 'USER'),
    (1, 'ADMIN'),
    (3, 'USER');

-- --------------------------------------------------------
#
# --
# -- Структура для представления `song_in_library_with_name`
# --
# DROP TABLE IF EXISTS `song_in_library_with_name`;
#
# CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`127.0.0.1` SQL SECURITY DEFINER VIEW `song_in_library_with_name`  AS SELECT `song_in_library`.`User_id` AS `User_id`, `song_in_library`.`Song_id` AS `Song_id`, `song`.`Name` AS `Name` FROM (`song_in_library` join `song` on((`song_in_library`.`Song_id` = `song`.`Song_id`))) ;

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `album`
--
ALTER TABLE `Album`
    ADD PRIMARY KEY (`Album_id`),
    ADD KEY `album_ibfk_1` (`Artist_id`);

--
-- Индексы таблицы `artist`
--
ALTER TABLE `Artist`
    ADD PRIMARY KEY (`Artist_id`);

--
-- Индексы таблицы `chart`
--
ALTER TABLE `Chart`
    ADD PRIMARY KEY (`Chart_id`);

--
-- Индексы таблицы `featuring_artist`
--
ALTER TABLE `Featuring_artist`
    ADD PRIMARY KEY (`Song_id`,`Feat_artist_id`),
    ADD KEY `featuring_artist_ibfk_2` (`Feat_artist_id`);


--
-- Индексы таблицы `song`
--
ALTER TABLE `Song`
    ADD PRIMARY KEY (`Song_id`),
    ADD KEY `song_ibfk_1` (`Main_artist_id`);

--
-- Индексы таблицы `song_in_album`
--
ALTER TABLE `Song_in_album`
    ADD PRIMARY KEY (`Song_id`,`Album_id`),
    ADD KEY `song_in_album_ibfk_2` (`Album_id`);

--
-- Индексы таблицы `song_in_chart`
--
ALTER TABLE `Song_in_chart`
    ADD PRIMARY KEY (`Song_id`,`Chart_id`),
    ADD KEY `song_in_chart_ibfk_2` (`Chart_id`);

--
-- Индексы таблицы `song_in_library`
--
ALTER TABLE `Song_in_library`
    ADD PRIMARY KEY (`User_id`,`Song_id`),
    ADD KEY `R_4` (`Song_id`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `User`
    ADD PRIMARY KEY (`user_id`),
    ADD UNIQUE KEY `users_username_uindex` (`username`);

--
-- Индексы таблицы `user_role`
--
ALTER TABLE `User_role`
    ADD KEY `users_roles_users_id_fk` (`user_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `User`
    MODIFY `user_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `album`
--
ALTER TABLE `Album`
    ADD CONSTRAINT `album_ibfk_1` FOREIGN KEY (`Artist_id`) REFERENCES `Artist` (`Artist_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `featuring_artist`
--
ALTER TABLE `Featuring_artist`
    ADD CONSTRAINT `featuring_artist_ibfk_1` FOREIGN KEY (`Song_id`) REFERENCES `Song` (`Song_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `featuring_artist_ibfk_2` FOREIGN KEY (`Feat_artist_id`) REFERENCES `Artist` (`Artist_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `song`
--
ALTER TABLE `Song`
    ADD CONSTRAINT `song_ibfk_1` FOREIGN KEY (`Main_artist_id`) REFERENCES `Artist` (`Artist_id`) ON DELETE CASCADE ON UPDATE SET NULL;

--
-- Ограничения внешнего ключа таблицы `song_in_album`
--
ALTER TABLE `Song_in_album`
    ADD CONSTRAINT `song_in_album_ibfk_1` FOREIGN KEY (`Song_id`) REFERENCES `Song` (`Song_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `song_in_album_ibfk_2` FOREIGN KEY (`Album_id`) REFERENCES `Album` (`Album_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `song_in_chart`
--
ALTER TABLE `Song_in_chart`
    ADD CONSTRAINT `song_in_chart_ibfk_1` FOREIGN KEY (`Song_id`) REFERENCES `Song` (`Song_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    ADD CONSTRAINT `song_in_chart_ibfk_2` FOREIGN KEY (`Chart_id`) REFERENCES `Chart` (`Chart_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `song_in_library`
--
ALTER TABLE `Song_in_library`
    ADD CONSTRAINT `song_in_library_ibfk_1` FOREIGN KEY (`User_id`) REFERENCES `User` (`User_id`),
    ADD CONSTRAINT `song_in_library_ibfk_2` FOREIGN KEY (`Song_id`) REFERENCES `Song` (`Song_id`);

--
-- Ограничения внешнего ключа таблицы `user_role`
--
ALTER TABLE `User_role`
    ADD CONSTRAINT `users_roles_users_id_fk` FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;


# --
# -- Триггеры `song`
# --
# DELIMITER $$
# CREATE TRIGGER `insert_triger` BEFORE INSERT ON `song` FOR EACH ROW BEGIN
#     CALL FixIncorrectArtistId(NEW.Main_artist_id, @newId);
#     SET NEW.Main_artist_id = @newId;
# END
# $$
# DELIMITER ;