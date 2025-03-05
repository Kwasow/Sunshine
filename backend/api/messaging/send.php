<?php

require_once __DIR__.'/../../src/database.php';
require_once __DIR__.'/../../src/helpers/authorization.php';

use Kreait\Firebase\Exception\MessagingException;
use Kreait\Firebase\Messaging\CloudMessage;

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
$type = $postData['type'];

// Send message
$messaging = $GLOBALS['firebaseMessaging'];
$message = null;

switch ($type) {
    case 'missing_you':
        $topic = $user->getMissingYouRecipient()->getUserTopic();
        $data = [
        'type' => 'missing_you',
        'name' => $user->getFirstName()
        ];

        $message = CloudMessage::withTarget('topic', $topic)
        ->withData($data);
        break;
    case 'request_location':
        $topic = $user->getMissingYouRecipient()->getUserTopic();
        $data = [
        'type' => 'request_location'
        ];

        $message = CloudMessage::withTarget('topic', $topic)
        ->withData($data);
        break;
    default:
        break;
}

if ($message == null) {
    http_response_code(400);
    error_log("[ERROR] Unknown message type: " . $type);
} else {
    try {
        $result = $messaging->send($message);
    } catch (MessagingException $e) {
        http_response_code(500);
        error_log("[EXCEPTION]: " . $e->getMessage());
    }
}

mysqli_close($conn);
exit();
