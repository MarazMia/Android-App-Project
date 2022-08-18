<?php
	
	$email = $_POST['Email'];

	require_once (dirname(__FILE__)."/getConnection.php");
	$ob = new Connection();
	$conn = $ob->connect();
		
	$sqlSelect = "select * from User_details where Email = '$email'";
	$result = $conn->query($sqlSelect);
	if($result->num_rows > 0){
	while ($row = $result->fetch_assoc()) {
	echo $row['Year']."-".$row['Month']."-".$row['Date_']."-".$row['Sugar_level']."-".$row['Consumed_Calory']."-".$row['Systolic_Rate']."-".$row['Diastolic_Rate']."-".$row['Weight']."\n";
				}
			}
	else{
		echo "No";
	}
?>