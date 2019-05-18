<?php 
include("connection.php");
$m_id=$_POST['mall_id'];
$m_name=$_POST['mall_name'];
$m_area=$_POST['mall_area'];

switch ($m_area) {
	case '1':
		$sql="insert into area(mall_id,mall_name,areas,status)values('$m_id','$m_name','area1','0')";
		$res=mysqli_query($connect,$sql);
		if ($res) {
			echo "success";
		}
		break;
	case '2':
		for ($i=1; $i <= 2; $i++) { 
			 $sql="insert into area(mall_id,mall_name,area,status)values('$m_id','$m_name','area$i','0')";
			$res=mysqli_query($connect,$sql);
			if ($res) {
				echo "success";
			}
		}
		break;
	case '3':
			for ($i=1; $i <= 3; $i++) { 
			 $sql="insert into area(mall_id,mall_name,area,status)values('$m_id','$m_name','area$i','0')";
			$res=mysqli_query($connect,$sql);
			if ($res) {
				echo "success";
			}
		}
			
		break;
		case '4':
			for ($i=1; $i <= 4; $i++) { 
			 $sql="insert into area(mall_id,mall_name,area,status)values('$m_id','$m_name','area$i','0')";
			 $res=mysqli_query($connect,$sql);
			if ($res) {
				 "success";
			}
		}
			break;
		case '5':
			for ($i=1; $i <= 5; $i++) { 
			  $sql="insert into area(mall_id,mall_name,area,status)values('$m_id','$m_name','area$i','0')";
			 $res=mysqli_query($connect,$sql);
			if ($res) {
				echo "success";
			}
		}
				break;
	default:
		echo "invalid";
		break;
}

?>