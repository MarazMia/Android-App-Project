<?php
	
	 $email = $_POST['Email'];
	 $year = $_POST['Year'];
	 $month = $_POST['Month'];
	 $date = $_POST['Date'];
	 $sugar = $_POST['Sugar_level'];
	 $cc = $_POST['CC'];
	 $sr = $_POST['SR'];
	 $dr = $_POST['DR'];
	 $weight = $_POST['Weight'];

	 require_once (dirname(__FILE__)."/getConnection.php");
	 require_once (dirname(__FILE__)."/createTable.php");

	 $ob = new Connection();
	 $conn = $ob->connect();

	 $object = new CreateTableUser_details();
	 $object->TableUser_details($conn);

	 $updateList = "REPLACE INTO User_details(Email, Year, Month, Date_ , Sugar_level, Consumed_Calory, Systolic_Rate, Diastolic_Rate, Weight)
		 		VALUES('$email', '$year', '$month', '$date','$sugar', '$cc','$sr', '$dr', '$weight');";

	if($conn->query($updateList) == true){
		echo "Updated successfully";
		
	}else{
		echo $conn->error;
	}	 		

?>