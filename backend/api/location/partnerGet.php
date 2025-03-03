<?php

require_once __DIR__.'/../../src/database.php';
require_once __DIR__.'/../../src/entities/userLocation.php';
require_once __DIR__.'/../../src/helpers/authorization.php';

# Open database connection
$conn = openConnection();

# Check if user is authorized
$user = checkAuthorization($conn);
if ($user !== NULL) {
  http_response_code(200);
} else {
  http_response_code(401);

  mysqli_close($conn);
  exit();
}

# Get location from database and return as JSON
header('Content-Type: application/json; charset=utf-8');

$stmt = mysqli_prepare(
  $conn,
  'SELECT l.*, u.first_name AS name
  FROM Locations AS l
  LEFT JOIN Users AS u ON l.user_id = u.id
  WHERE l.user_id = ?'
);
mysqli_stmt_bind_param($stmt, 'i', $user->getMissingYouRecipient()->getId());
mysqli_stmt_execute($stmt);

$result = $stmt->get_result();
$stmt->close();

if (mysqli_num_rows($result) > 1) {
  http_response_code(500);
  return NULL;
}

if (mysqli_num_rows($result) == 0) {
  return NULL;
}


$location = mysqli_fetch_assoc($result);

echo json_encode(
  new UserLocation(
    $location['user_id'],
    $location['name'],
    $location['latitude'],
    $location['longitude'],
    $location['accuracy'],
    $location['time_stamp']
  )
);

mysqli_close($conn);
exit();

?>
