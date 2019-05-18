<?php
include("connection.php");
$lat=$_POST['latitude'];
$longt=$_POST['longitude'];
$location=$_POST['location'];
$mall_id=$_POST['mall_id'];

$sql="update tbl_mall set latitude='$lat',longtitude='$longt' where id='$mall_id'";
$exe=mysqli_query($connect,$sql);
if ($exe) {
	echo $location;
}
?>