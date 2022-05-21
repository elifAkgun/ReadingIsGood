  CREATE TABLE `BOOK` (
  `ID` varchar(45) NOT NULL,
  `NAME` varchar(45) NOT NULL,
  `AMOUNT` decimal(9,2) NOT NULL,
  `STOCK` int NOT NULL,
  `VERSION` int NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `CUSTOMER` (
  `ID` varchar(45) NOT NULL,
  `FIRST_NAME` varchar(45) NOT NULL,
  `LAST_NAME` varchar(45) NOT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EMAIL_UNIQUE` (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `ORDER_ITEM` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `BOOK_ID` varchar(45) NOT NULL,
  `ORDER_ID` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `BOOK_ID_idx` (`BOOK_ID`),
  KEY `ORDER_ID_idx` (`ORDER_ID`),
  CONSTRAINT `BOOK_ID` FOREIGN KEY (`BOOK_ID`) REFERENCES `BOOK` (`ID`),
  CONSTRAINT `ORDER_ID` FOREIGN KEY (`ORDER_ID`) REFERENCES `ORDERS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `ORDERS` (
  `ID` varchar(45) NOT NULL,
  `CUSTOMER_ID` varchar(45) NOT NULL,
  `DATE` datetime NOT NULL,
  `AMOUNT` decimal(9,2) NOT NULL,
  `BOOK_QUANTITY` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `CUSTOMER_ID_idx` (`CUSTOMER_ID`),
  CONSTRAINT `CUSTOMER_ID` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `CUSTOMER` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

create VIEW ReadingIsGood.STATISTIC_VIEW (id, order_year, order_month, total_order_count, book_quantitiy, total_purchased_amount, customer_id)
AS
  SELECT  ROW_NUMBER() OVER(PARTITION BY customer_id) AS id  , year(date) order_year, MONTH(date) order_month,
  COUNT(*) total_order_count, SUM(BOOK_QUANTITY) book_quantitiy,
  SUM(AMOUNT) total_purchased_amount,  customer_id
  FROM ReadingIsGood.ORDERS orders
  GROUP BY MONTH(date), year(date), customer_id
  order by order_year desc, order_month desc;