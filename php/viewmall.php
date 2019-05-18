<?php
include "connection.php";
$state=$_POST['state'];
$district=$_POST['district'];
$query="select * from tbl_mall where state='$state' and district='$district'";

$result=mysqli_query($connect,$query);
while($row=mysqli_fetch_assoc($result))
{
$image="upload/". $row['image'];

$data[]=array("id"=>$row['id'],"name"=>$row['mall_name'],"place"=>$row['mall_place'],"image"=>$image);


}
echo json_encode($data);
?>