<?php
include("connection.php");
$sql="select * from tbl_mall";
$res=mysqli_query($connect,$sql);
while($row=mysqli_fetch_assoc($res))
{

$data[]=array("id"=>$row['id'],"name"=>$row['mall_name']);


}
echo json_encode($data);