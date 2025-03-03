<?php

require_once __DIR__.'/../../src/database.php';
require_once __DIR__.'/../../src/entities/userLocation.php';
require_once __DIR__.'/../../src/helpers/authorization.php';

// Open database connection
$conn = openConnection();

// Check if user is authorized
$user = checkAuthorization($conn);
if ($user !== null) {
    http_response_code(200);
} else {
    http_response_code(401);

    mysqli_close($conn);
    exit();
}

// Get request details
$postData = json_decode(file_get_contents('php://input'), true);
$userId = $user->getId();
$latitude = $postData['latitude'];
$longitude = $postData['longitude'];
$accuracy = $postData['accuracy'];
$timestamp = $postData['timestamp'];

// Update location in database
$stmt = mysqli_prepare(
    $conn,
    'INSERT INTO Locations VALUES(?, ?, ?, ?, ?)
  ON DUPLICATE KEY
  UPDATE latitude=?, longitude=?, accuracy=?, time_stamp=?'
);
mysqli_stmt_bind_param(
    $stmt,
    'idddddddd',
    $postData, $latitude, $longitude, $accuracy, $timestamp,
    $latitude, $longitude, $accuracy, $timestamp
);
mysqli_stmt_execute($stmt);

mysqli_close($conn);
exit();

?>
