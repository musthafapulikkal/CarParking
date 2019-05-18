<?php
include("connection.php");
$email=$_POST['username'];
$password=$_POST['password'];
$query="select email,password from users where email='$email' and password='$password'";
$res=mysqli_query($connect,$query);
if ($res) 
{
	while ($row=mysqli_fetch_assoc($res)) {
		echo $row['email'];
	}
}