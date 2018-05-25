<?php
header("content-type:text/javascript;charset=utf-8");

$con=mysql_connect('localhost','root','')or die(mysql_error());   // เปลี่ยน localhost เป็น ip ของ mysql server
mysql_select_db('test')or die(mysql_error()); //ชื่อ database ที่ต้องการเชื่อมต่อ
mysql_query("SET NAMES UTF8");
		
if (isset($_POST)){
  if($_POST['isAdd']=='true'){

        // ตัวแปรต่างๆที่เก็บค่าไว้ ถ้าตัวไหนมี $_POST จะเปิดรับค่าจากข้างนอก 
        $comment = $_POST['comment'];
        $status = '0';
        // ตัวแปรต่างๆตัวแปรต่างๆที่เก็บค่าไว้ ถ้าตัวไหนมี $_POST จะเปิดรับค่าจากข้างนอก 

        // อัพเดทข้อมูลลง Database
		$sql = "INSERT INTO content (comment, status) VALUES ('" . $comment . "', '" . $status . "')";
         mysql_query($sql);
        // อัพเดทข้อมูลลง Database
    }
}

mysql_close();
