<?php

include ("db_info.php");


$recipe_name = $_POST["recipe_name"];
$rating = $_POST["rating"];
$dificulty = $_POST["dificulty"];
$cook_time = $_POST["cook_time"];

$response = [];
$response["status"] = "Completed!";

$json_response = json_encode($response);
echo($json_response);


$query = $mysqli->prepare("INSERT INTO cookbook (recipe_name, rating, dificulty, cook_time) VALUES (?, ?, ?, ?)");
$query->bind_param("ssss", $recipe_name, $rating,$dificulty,$cook_time);
$query->execute();

?>