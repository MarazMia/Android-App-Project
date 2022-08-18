<?php


	        $email = $_POST['Email'];
	        $userpassword = $_POST['Password'];

	     	require_once (dirname(__FILE__)."/getConnection.php");

	        $object = new Connection();
	        $conn = $object->connect();

			$sql = "SELECT * FROM User_login WHERE Email ='$email' and Password = '$userpassword';";
			$result = $conn->query($sql);

			if($result->num_rows == 1){
				$row = $result->fetch_assoc();
				if($row['Email'] == $email && $row['Password'] == $userpassword)
				   echo "Successfully login"."\n".$row['Name'];
			}else{
				echo "please insert valid email and password";
			}

?>