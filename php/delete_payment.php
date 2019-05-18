<?php
include("connection.php");
$id=$_POST['id'];
$sel="select * from payment where id='$id'";
$res_sel=mysqli_query($connect,$sel);
$row=mysqli_fetch_assoc($res_sel);
$mall_id=$row['mall_id'];
$area=$row['area'];
$update="update area set status='0' where mall_id='$mall_id' and area='$area'";
$res_update=mysqli_query($connect,$update);
if ($res_update) 
{
	$query="delete from payment where id='$id'";
	$res=mysqli_query($connect,$query);
	if ($res)
	{
	echo "success";
	}
	else
	{
	echo "faild";
	}
}

?>