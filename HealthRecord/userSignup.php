<?php
	
	class UserSignup{

		function signup($conn,$name, $email,$password){

			 $sqlInsert = "INSERT INTO User_login(Name,Email,Password)
		 		VALUES('$name','$email','$password');";

		 		if($conn->query($sqlInsert) == true){
		 				echo "Registration successfull";
		 		}else{
		 				echo "Registration unsuccessfull\n".$conn->error."\n";
		 		}
		}

	}


?>