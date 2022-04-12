<?php

include("db_info.php");

$item = $_POST["item"];
$query = $mysqli->prepare("SELECT * FROM pantry Where item_name = item ;");
$query->execute();

$array = $query->get_result();

$response = [];

while($course = $array->fetch_assoc()){
    $response[] = $course;
}

$json_response = json_encode($response);
echo $json_response;

?>