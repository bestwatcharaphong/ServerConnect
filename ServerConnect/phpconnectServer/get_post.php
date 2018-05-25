<?php
header("content-type:text/javasript;charset=utf-8");
define('HOST', 'localhost');
define('USER','root' );
define('PASS', 'mysql');
define('DB','example');

  if($_SERVER['REQUEST_METHOD'] == 'GET'){
  	$staus = $_GET['staus'];
  	$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

  	mysqli_set_charset($con,"utf-8");

  	$sql = "SElECT * FROM contend WHERE staus='" .$staus. "'";
    
    $r = mysqli_query($con,$sql);

    $result = array();

    while ($row = mysqli_fetch_array($r)) {

    	array_push($result,array("comment" => $row['comment']));
    }
       echo json_encode(array('result' => $result));
       mysqli_close($con);
  }