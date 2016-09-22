<?php
require_once('db_connect.php');

$sql = "select * from agents where AgencyId = 1";

$res = mysqli_query($con,$sql);

$result = array();

while($row = mysqli_fetch_array($res, MYSQL_BOTH)){
array_push($result,
array('AgentId'=>$row["AgentId"],
'AgtFirstName'=>$row["AgtFirstName"],
'AgtLastName'=>$row["AgtLastName"]
));
}

echo json_encode(array("result"=>$result));

mysqli_close($con);

?>
