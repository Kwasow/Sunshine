<?php

require_once __DIR__.'/../src/database.php';
require_once __DIR__.'/../src/helpers/authorization.php';

// Open database connection
$conn = openConnection();

// Check if user is authorized
$user = checkAuthorization($conn);
if ($user !== null) {
    http_response_code(200);
} else {
    http_response_code(401);
}

echo json_encode($user);

mysqli_close($conn);
exit();
