<?php


	class SqlCommand{

		function createDatabase($conn){

			    $sql = "Create database if not exists ". $DB_NAME;
			    if($conn->query($sql)== TRUE){
			 	    echo "Database has been crated successfully\n";
			    }else{
			 	    echo "Error creating database: ".$conn->error."\n";
			    }
		}


		function createTableUserLognin($conn){
			
			    $sqlCreateTable = "Create table User_login(
		        Name char(30),
		        Email char(30) UNIQUE,
		        Password char(30),
		        primary key(Email));";
		        $conn->query($sqlCreateTable);
		}

		
	}


?>