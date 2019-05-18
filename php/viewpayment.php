<?php
include("connection.php");
$sql="select * from payment";
$result=mysqli_query($connect,$sql);
while($row=mysqli_fetch_assoc($result))
 {
	

$data[]=array("id"=>$row['id'],"email"=>$row['email'],"slot"=>$row['slot'],"area"=>$row['area'],"amount"=>$row['amount']);

}
echo json_encode($data);
?>