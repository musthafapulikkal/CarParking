<?php
include("connection.php");
$email=$_POST['email'];
//

$query="select * from payment where email='$email'";
$res=mysqli_query($connect,$query);
 while($row=mysqli_fetch_assoc($res))
 {
	

$data[]=array("email"=>$row['email'],"name"=>$row['mall_name'],"slot"=>$row['slot'],"area"=>$row['area'],"time"=>$row['time'],"am_pm"=>$row['am_pm'],"hour"=>$row['hour'],"date"=>$row['c_date']);

}
echo json_encode($data);
?>
