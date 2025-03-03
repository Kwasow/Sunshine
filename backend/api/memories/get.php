<?php

require_once __DIR__.'/../../config/config.php';
require_once __DIR__.'/../../src/database.php';
require_once __DIR__.'/../../src/entities/memory.php';
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

# Parse memories CSV to JSON
header('Content-Type: application/json; charset=utf-8');

$result = mysqli_query(
  $conn,
  'SELECT * FROM Memories'
);

$memories = [];
while ($row = mysqli_fetch_assoc($result)) {
  $memories[] = new Memory(
    intval($row['id']),
    $row['startDate'],
    $row['endDate'],
    $row['title'],
    $row['memory_description'],
    $row['photo']
  );
}

echo json_encode($memories);

mysqli_close($conn);
exit();

?>
