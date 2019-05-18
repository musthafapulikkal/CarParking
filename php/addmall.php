<?php
include("connection.php");
$state=$_POST['state'];
$district=$_POST['district'];
// $lat=$_POST['latitude'];
// $longt=$_POST['longtitude'];
$name=$_POST['mall'];
$place=$_POST['place'];
$image=$_POST['image'];
$target_dir = "upload/";
   

   
    $target_dir = $target_dir."/".$name.".JPEG";
    if(file_exists($target_dir)){
        unlink($target_dir);
    }
	$upload_image=$name.".JPEG";
    if(file_put_contents($target_dir, base64_decode($image))){
		$query="insert into tbl_mall(mall_name,state,district,latitude,longtitude,mall_place,image)values('$name','$state','$district','0','0','$place','$upload_image')";
		$result=mysqli_query($connect,$query);
	if($result)
	{
	echo("success");
	}
	else
	{
		"invalid";
	}
}
?>