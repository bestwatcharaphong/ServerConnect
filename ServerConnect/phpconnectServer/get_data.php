<?php 
header("content-type:text/javascript;charset=utf-8");
define('HOST','localhost'); //ª×èÍ host
define('USER','root'); //username
define('PASS',''); //password
define('DB','example'); // ª×èÍ database ·Õè¨ÐµÔ´µèÍ

 if($_SERVER['REQUEST_METHOD']=='GET'){
 
  $status  = $_GET['status'];
 
  $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect'); //µèÍ°Ò¹¢éÍÁÙÅ
  
  mysqli_set_charset($con,"utf8");
 
  $sql = "SELECT * FROM contend WHERE status='".$status."'";
 
  $r = mysqli_query($con,$sql);
 
  $result = array();

    while($row = mysqli_fetch_array($r))
      {
        array_push($result,array("comment"=>$row['comment']));
    }

  echo json_encode(array('result'=>$result));
 
  mysqli_close($con);
 
 }
