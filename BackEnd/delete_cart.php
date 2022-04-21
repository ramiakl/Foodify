<?php

include("db_info.php");

$query = $mysqli->prepare("DELETE FROM cart;");
$query->execute();

$response = [];
$response["status"] = "Completed!";

$json_response = json_encode($response);
echo $json_response;

?>