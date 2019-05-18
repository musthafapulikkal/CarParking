<?php
include("connection.php");
$email=$_POST['email'];
$name=$_POST['name'];
$card=$_POST['card_no'];
$edate=$_POST['edate'];
$cvv=$_POST['cvv'];
$time=$_POST['time'];
$am_pm=$_POST['am_pm'];
$hour=$_POST['hour'];
$slot=$_POST['slot'];
$area=$_POST['area'];
$mall_id=$_POST['mall_id'];
$amount=$_POST['amount'];
$date=$_POST['date'];
$sqlphn="select * from users where email='$email'";
$resphn=mysqli_query($connect,$sqlphn);
$rowphn=mysqli_fetch_assoc($resphn);
$number=$rowphn['phone'];

$querybank="select * from tbl_bank where name='$name' and card_no='$card' and e_date='$edate' and cvv='$cvv'";
$resbank=mysqli_query($connect,$querybank);
$row=mysqli_fetch_assoc($resbank);
$old_amount=$row['balance'];
$bank_id=$row['id'];
if ($resbank) 
{
  $n="select mall_name from tbl_mall where id='$mall_id'";
  $r=mysqli_query($connect,$n);
  $ro=mysqli_fetch_assoc($r);
  $mall_name=$ro['mall_name'];
	$sql="insert into payment(mall_id,mall_name,email,name,slot,area,amount,time,am_pm,hour,c_date)values('$mall_id','$mall_name','$email','$name','$slot','$area','$amount','$time','$am_pm','$hour','$date')";
		$exe=mysqli_query($connect,$sql);
	if ($exe) 
	{
		$new_amount=$old_amount - $amount;
		$update="update tbl_bank set balance='$new_amount' where id='$bank_id'";
		$result=mysqli_query($connect,$update);
		if ($update) 
		{
      echo "success";
      $mall_query=mysqli_query($connect,"update area set status='1' where area='$area' and mall_id='$mall_id'");
     
			$message="Dear Customer,Your slot is:".$slot."\n"."area is:".$area."/n";
	        $sms = new FabSMS();
 	        $sms->SendSms($number,$message);
	
			
		}
		else
		{
		echo "failed";
		}
}

}
else
{
echo "failed";
}
class FabSMS
{


 function SendSms($number,$message)
  {
  $username = "varunvalsa@gmail.com";
  $hash = "684fd135f185941cdc0b67db13d624f6e1c9d20b1329784058529455742683bb";

  // Config variables. Consult http://api.textlocal.in/docs for more info.
  $test = "0";

  // Data for text message. This is the text message data.
  $sender = "TXTLCL"; // This is who the message appears to be from.
  $numbers = "$number"; // A single number or a comma-seperated list of numbers
  $message = "$message";
  // 612 chars or less
  // A single number or a comma-seperated list of numbers
  $message = urlencode($message);
  $data = "username=".$username."&hash=".$hash."&message=".$message."&sender=".$sender."&numbers=".$numbers."&test=".$test;
  $ch = curl_init('http://api.textlocal.in/send/?');
  curl_setopt($ch, CURLOPT_POST, true);
  curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
  curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
  $result = curl_exec($ch); // This is the result from the API
  curl_close($ch);
  //echo $result;
  }
}
?>