<?php 
include("connection.php");
$name="musthafa";
$card="1234";
$date="05/05/2019";
$cvv="123";
$amount="40";
$query="select * from tbl_bank where name='$name' and card_no='$card' and e_date='$date' and cvv='$cvv'";
$res=mysqli_query($connect,$query);
$row=mysqli_fetch_assoc($res);
echo $old_amount=$row['balance'];
$bank_id=$row['id'];
$new_amount=$old_amount - $amount;
		$update="update tbl_bank set balance='$new_amount' where id='$bank_id'";
		$result=mysqli_query($connect,$update);



?>