<?php

require_once __DIR__.'/../../vendor/autoload.php';

use Kreait\Firebase\Factory;

$factory = (new Factory)->withServiceAccount(
    __DIR__.'/../../config/googleServiceAccount.json'
);
$firebaseAuth = $factory->createAuth();
$firebaseMessaging = $factory->createMessaging();
