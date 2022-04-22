<?php

include("db_info.php");


$query = $mysqli->prepare("SELECT weight, item_name FROM cart;");
$query->execute();

$array = $query->get_result();

$response = [];

while($result = $array->fetch_assoc()){
    $response[] = $result;
}
$name = mysql_fetch_assoc($result)['item_name'];
$weight = mysql_fetch_assoc($result)['weight'];

$location = "not defined";
$exp = "not defined";

$query = $mysqli->prepare("INSERT INTO pantry (item_name, date_of_expiration, location, weight) VALUES (?, ?, ?,?)");
$query->bind_param("ssss", $name, $exp , $location,$weight);
$query->execute();

$array = $query->get_result();

$response = [];
$response["status"] = "Completed!";

$json_response = json_encode($response);
echo $json_response;


?>