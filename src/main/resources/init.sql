--
-- Структура таблицы `album`
--

CREATE TABLE `album` (
                         `Album_id` int NOT NULL,
                         `Name` varchar(40) NOT NULL,
    `Release_date` date NOT NULL,
    `Style` varchar(20) DEFAULT NULL,
    `Artist_id` int DEFAULT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `album`
--

INSERT INTO `album` (`Album_id`, `Name`, `Release_date`, `Style`, `Artist_id`) VALUES
    (1, 'To Pimp a Butterfly', '2015-03-14', 'hip-hop/rap', 3),
    (2, 'DAMN.', '2017-04-13', 'hip-hop/rap', 3),
    (3, 'good kid, m.A.A.d city', '2012-10-22', 'hip-hop/rap', 3),
    (4, 'ANTI', '2016-01-26', 'pop', 1),
    (5, 'Views', '2011-11-14', 'hip-hop/rap', 2);

-- --------------------------------------------------------

--
-- Структура таблицы `artist`
--

CREATE TABLE `artist` (
                          `Artist_id` int NOT NULL,
                          `Nickname` varchar(40) NOT NULL,
    `Email` varchar(40) NOT NULL,
    `Phone` varchar(20) NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `artist`
--

INSERT INTO `artist` (`Artist_id`, `Nickname`, `Email`, `Phone`) VALUES
    (1, 'Rihanna', 'rihanna@mail.com', '+2956475867'),
    (2, 'Drake', 'drake@mail.com', '+2968498165'),
    (3, 'Kendrick Lamar', 'klamar@mail.com', '+2954654684');

-- --------------------------------------------------------

--
-- Структура таблицы `chart`
--

CREATE TABLE `chart` (
                         `Chart_id` int NOT NULL,
                         `Name` varchar(30) NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `chart`
--

INSERT INTO `chart` (`Chart_id`, `Name`) VALUES
    (1, 'hip-hop chart'),
    (2, 'pop chart');

-- --------------------------------------------------------

--
-- Структура таблицы `featuring_artist`
--

CREATE TABLE `featuring_artist` (
                                    `Song_id` int NOT NULL,
                                    `Feat_artist_id` int NOT NULL,
                                    `Is_in_name_of_song` tinyint(1) NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `featuring_artist`
--

INSERT INTO `featuring_artist` (`Song_id`, `Feat_artist_id`, `Is_in_name_of_song`) VALUES
    (3, 2, 1),
    (4, 1, 1),
    (5, 1, 1),
    (7, 2, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `listener`
--

CREATE TABLE `listener` (
                            `Listener_id` int NOT NULL,
                            `role` varchar(20) DEFAULT NULL,
    `Surname` varchar(30) NOT NULL,
    `Phone` varchar(20) DEFAULT NULL,
    `Name` varchar(30) NOT NULL,
    `Email` varchar(50) NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `listener`
--

INSERT INTO `listener` (`Listener_id`, `role`, `Surname`, `Phone`, `Name`, `Email`) VALUES
    (1, 'role', 'Kireev', '89298888888', 'Artem', 'tema@yandex.ru'),
    (2, 'role', 'Kireev', '89297777777', 'Oleg', 'oleg@yandex.ru'),
    (3, 'role', 'Larin', '89256666666', 'Alexei', 'alexei@yandex.ru');

-- --------------------------------------------------------

--
-- Структура таблицы `song`
--

CREATE TABLE `song` (
                        `Song_id` int NOT NULL,
                        `Name` varchar(40) NOT NULL,
    `Main_artist_id` int DEFAULT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `song`
--

INSERT INTO `song` (`Song_id`, `Name`, `Main_artist_id`) VALUES
    (1, 'u', 3),
    (2, 'Alright', 3),
    (3, 'Poetic Justice (feat. Drake)', 3),
    (4, 'LOYALTY. (FEAT. RIHANNA.)', 3),
    (5, 'Too Good (feat. Rihanna)', 2),
    (6, 'Hype', 2),
    (7, 'Work (feat. Drake)', 1),
    (8, 'James Joint', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `song_in_album`
--

CREATE TABLE `song_in_album` (
                                 `Album_id` int NOT NULL,
                                 `Song_id` int NOT NULL,
                                 `Is_lead_song` tinyint(1) NOT NULL,
    `Serial_number` int NOT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `song_in_album`
--

INSERT INTO `song_in_album` (`Album_id`, `Song_id`, `Is_lead_song`, `Serial_number`) VALUES
    (1, 1, 0, 6),
    (1, 2, 1, 7),
    (3, 3, 0, 6),
    (2, 4, 0, 6),
    (5, 5, 0, 16),
    (5, 6, 0, 5),
    (4, 7, 1, 4),
    (4, 8, 0, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `song_in_chart`
--

CREATE TABLE `song_in_chart` (
                                 `Chart_id` int NOT NULL,
                                 `Song_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `song_in_chart`
--

INSERT INTO `song_in_chart` (`Chart_id`, `Song_id`) VALUES
    (1, 2),
    (1, 3),
    (2, 5),
    (2, 7);

-- --------------------------------------------------------

--
-- Структура таблицы `song_in_library`
--

CREATE TABLE `song_in_library` (
                                   `Listener_id` int NOT NULL,
                                   `Song_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Дамп данных таблицы `song_in_library`
--

INSERT INTO `song_in_library` (`Listener_id`, `Song_id`) VALUES
    (1, 1),
    (1, 2),
    (1, 4),
    (3, 4),
    (3, 5),
    (3, 6),
    (1, 7),
    (2, 7),
    (1, 8),
    (2, 8);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `album`
--
ALTER TABLE `album`
    ADD PRIMARY KEY (`Album_id`),
    ADD KEY `R_9` (`Artist_id`);

--
-- Индексы таблицы `artist`
--
ALTER TABLE `artist`
    ADD PRIMARY KEY (`Artist_id`);

--
-- Индексы таблицы `chart`
--
ALTER TABLE `chart`
    ADD PRIMARY KEY (`Chart_id`);

--
-- Индексы таблицы `featuring_artist`
--
ALTER TABLE `featuring_artist`
    ADD PRIMARY KEY (`Song_id`,`Feat_artist_id`),
    ADD KEY `R_18` (`Feat_artist_id`);

--
-- Индексы таблицы `listener`
--
ALTER TABLE `listener`
    ADD PRIMARY KEY (`Listener_id`),
    ADD KEY `XIE1Listener` (`Surname`);

--
-- Индексы таблицы `song`
--
ALTER TABLE `song`
    ADD PRIMARY KEY (`Song_id`),
    ADD KEY `R_13` (`Main_artist_id`);

--
-- Индексы таблицы `song_in_album`
--
ALTER TABLE `song_in_album`
    ADD PRIMARY KEY (`Song_id`,`Album_id`),
    ADD KEY `R_7` (`Album_id`);

--
-- Индексы таблицы `song_in_chart`
--
ALTER TABLE `song_in_chart`
    ADD PRIMARY KEY (`Song_id`,`Chart_id`),
    ADD KEY `R_8` (`Chart_id`);

--
-- Индексы таблицы `song_in_library`
--
ALTER TABLE `song_in_library`
    ADD PRIMARY KEY (`Listener_id`,`Song_id`),
    ADD KEY `R_4` (`Song_id`);

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `album`
--
ALTER TABLE `album`
    ADD CONSTRAINT `album_ibfk_1` FOREIGN KEY (`Artist_id`) REFERENCES `artist` (`Artist_id`);

--
-- Ограничения внешнего ключа таблицы `featuring_artist`
--
ALTER TABLE `featuring_artist`
    ADD CONSTRAINT `featuring_artist_ibfk_1` FOREIGN KEY (`Song_id`) REFERENCES `song` (`Song_id`),
    ADD CONSTRAINT `featuring_artist_ibfk_2` FOREIGN KEY (`Feat_artist_id`) REFERENCES `artist` (`Artist_id`);

--
-- Ограничения внешнего ключа таблицы `song`
--
ALTER TABLE `song`
    ADD CONSTRAINT `song_ibfk_1` FOREIGN KEY (`Main_artist_id`) REFERENCES `artist` (`Artist_id`);

--
-- Ограничения внешнего ключа таблицы `song_in_album`
--
ALTER TABLE `song_in_album`
    ADD CONSTRAINT `song_in_album_ibfk_1` FOREIGN KEY (`Song_id`) REFERENCES `song` (`Song_id`),
    ADD CONSTRAINT `song_in_album_ibfk_2` FOREIGN KEY (`Album_id`) REFERENCES `album` (`Album_id`);

--
-- Ограничения внешнего ключа таблицы `song_in_chart`
--
ALTER TABLE `song_in_chart`
    ADD CONSTRAINT `song_in_chart_ibfk_1` FOREIGN KEY (`Song_id`) REFERENCES `song` (`Song_id`),
    ADD CONSTRAINT `song_in_chart_ibfk_2` FOREIGN KEY (`Chart_id`) REFERENCES `chart` (`Chart_id`);

--
-- Ограничения внешнего ключа таблицы `song_in_library`
--
ALTER TABLE `song_in_library`
    ADD CONSTRAINT `song_in_library_ibfk_1` FOREIGN KEY (`Listener_id`) REFERENCES `listener` (`Listener_id`),
    ADD CONSTRAINT `song_in_library_ibfk_2` FOREIGN KEY (`Song_id`) REFERENCES `song` (`Song_id`);