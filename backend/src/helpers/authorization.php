<?php

require_once __DIR__.'/../entities/user.php';
require_once __DIR__.'/../firebase.php';

use Kreait\Firebase\Exception\Auth\FailedToVerifyToken;

function checkAuthorization($dbConnection)
{
    $token = getBearerToken();

    if ($token === null) {
        return null;
    }

    // Check firebase
    $verifiedIdToken = null;
    try {
        $verifiedIdToken = $GLOBALS['firebaseAuth']->verifyIdToken($token);
    } catch (FailedToVerifyToken $e) {
        return null;
    }

    // Check database
    $email = $verifiedIdToken->claims()->get('email');
    $stmt = mysqli_prepare(
        $dbConnection,
        'SELECT
        this.*,
        other.id AS other_id,
        other.first_name AS other_name,
        other.email AS other_email,
        other.icon AS other_icon
     FROM Users AS this
     LEFT JOIN Users AS other ON this.missing_you_recipient = other.id
     WHERE this.email = ?'
    );
    mysqli_stmt_bind_param($stmt, 's', $email);
    mysqli_stmt_execute($stmt);

    $result = $stmt->get_result();
    $stmt->close();

    if (mysqli_num_rows($result) != 1) {
        return null;
    }

    $user = mysqli_fetch_assoc($result);

    $otherUser = new MissingYouRecipient(
        $user['other_id'],
        $user['other_name'],
        $user['other_email'],
        $user['other_icon']
    );

    return new User(
        $user['id'],
        $user['first_name'],
        $user['last_name'],
        $user['email'],
        $user['icon'],
        $otherUser
    );
}

function getAuthorizationHeader()
{
    $headers = getallheaders();
  
    return $headers['Authorization'];
}

function getBearerToken()
{
    $header = getAuthorizationHeader();

    if (!empty($header)) {
        if (preg_match('/Bearer\s(\S+)/', $header, $matches)) {
            return $matches[1];
        }
    }

    return null;
}
