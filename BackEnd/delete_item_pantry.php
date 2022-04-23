<?php

include("db_info.php");

$name = $_POST["name"];

$query = $mysqli->prepare("DELETE FROM pantry Where item_name = '$name';");
$query->execute();

$response = [];
$response["status"] = "Completed!";

$json_response = json_encode($response);
echo $json_response;

?>