<?php
include("connection.php");
$id=$_POST['id'];
$sql="select * from tbl_mall where id='$id'";
$exe=mysqli_query($connect,$sql);
while($row=mysqli_fetch_assoc($exe))
{

$data[]=array("latitude"=>$row['latitude'],"longtitude"=>$row['longtitude']);

}
echo json_encode($data);
?>
