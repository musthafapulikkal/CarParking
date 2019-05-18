<?php
include("connection.php");
$email=$_POST['email'];
$username=$_POST['name'];
$phone=$_POST['phone'];
$password=$_POST['password'];
$q="select * from users where email='$email'";
$res=mysqli_query($connect,$q);
$check=mysqli_num_rows($res);
if($check>0)
{
 echo"invalid";
}
else
{
$query="insert into users(email,username,phone,password)values('$email','$username','$phone','$password')";
$result=mysqli_query($connect,$query);
if($result)
{
echo $username;
}
}