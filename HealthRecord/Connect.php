<?php

	$name = $_POST['Name'];
	$email = $_POST['Email'];
	$userpassword = $_POST['Password'];

	require_once (dirname(__FILE__)."/getConnection.php");

	 $object = new Connection();
	 $conn = $object->connect();

	require_once (dirname(__FILE__)."/sqlCommand.php");
	require_once (dirname(__FILE__)."/userSignup.php");

	$obj = new SqlCommand();
	$obj->createTableUserLognin($conn);
	$ob = new UserSignup();
	$ob->signup($conn,$name,$email,$userpassword);

	$conn->close();
?>