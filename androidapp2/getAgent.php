<?php
	$AgentId = $_GET['AgentId'];

	$host="localhost";
	$username="root";
	$password="";
	$db_name="travelexperts";

	$con=mysqli_connect($host, $username, $password)or die("Cannot Connect..." . mysql_error());
	mysqli_select_db($con,$db_name)or die("Cannot select database..");

	//$sql = "SELECT * FROM agents WHERE AgentId=$AgentId";
	//$r = mysqli_query($con,$sql);

	$result2 = array();
	$result = mysqli_query($con, "SELECT * FROM agents WHERE AgentId = " . $AgentId);
	//$row = mysqli_fetch_array($r);

	while($row = mysqli_fetch_array($result, MYSQL_BOTH))
	{
		array_push($result2,array(
			"AgentId"=>$row[0],
			"AgtFirstName"=>$row[1],
			"AgtLastName"=>$row[3],
			"AgtBusPhone"=>$row[4],
			"AgtEmail"=>$row[5],
			"AgtPosition"=>$row[6]
		));
	}
	echo json_encode(array('result'=>$result2));
	mysqli_close($con);
?>
