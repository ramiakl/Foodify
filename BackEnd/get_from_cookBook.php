<?php

include("db_info.php");

$id = $_POST["user_id"];

$query = $mysqli->prepare("SELECT Recipe_name,cooktime,image FROM recipe  Where user_id = '$id';");
$query->execute();

$array = $query->get_result();

$response = [];

while($course = $array->fetch_assoc()){
    $response[] = $course;
}

$json_response = json_encode($response);
echo $json_response;

?>