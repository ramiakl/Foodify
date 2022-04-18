<?php

include ("db_info.php");

$name = $_POST["name"];
$password = $_POST["password"];

$query = $mysqli->prepare("SELECT * FROM user Where username = $name AND password = $password;");
$query->execute();

$array = $query->get_result();

