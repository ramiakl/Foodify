<?php

include("db_info.php");

$name = $_POST["name"];

$query = $mysqli->prepare("SELECT * FROM recipe Where Recipe_name = '$name';");
$query->execute();

$array = $query->get_result();

$response = [];

while($course = $array->fetch_assoc()){
    $response[] = $course;
}

$json_response = json_encode($response);
echo $json_response;

?>