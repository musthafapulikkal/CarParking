<?php
include("connection.php");
$id=$_POST['mall_id'];
//
$sql="select * from area where mall_id='$id' and status='1'";
$exe=mysqli_query($connect,$sql);
while($row=mysqli_fetch_assoc($exe))
{

$data[]=array("area"=>$row['area']);
}
echo json_encode($data);
?>