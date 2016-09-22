<?php
$host="localhost"; 
$username="root"; 
$password="";  
$db_name="travelexperts"; 

$con=mysqli_connect($host, $username, $password)or die("Cannot Connect..."); 
mysqli_select_db($con,$db_name)or die("Cannot Select Database");

$sql = "select * from agencies";

$result = mysqli_query($con,$sql);
$json = array();


if(mysqli_num_rows($result))
{
	while($row=mysqli_fetch_assoc($result))
	{
		$json['agencies'][]=$row;

	}

}

mysqli_close($con);
echo json_encode($json); 
?> 