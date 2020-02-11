<?php

	class CreateTableUser_details{


		function TableUser_details($conn){
			$sqlCreateTable = "Create Table User_details(
			Id INT(5) AUTO_INCREMENT,
		    Email char(30) NOT NULL,
		    Year Char(5),
		    Month char(10),
		    Date_  char(15) NOT NULL,
		    Sugar_level double(5,2),
		    Consumed_Calory double(5,2),
		    Systolic_Rate double(5,2),
		    Diastolic_Rate double(5,2),
		    Weight double(5,2),
    		PRIMARY KEY(Id,Email,Date_)

		);";
		    $conn->query($sqlCreateTable);
		}
	}

?>