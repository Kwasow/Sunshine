<?php

class UserLocation implements jsonSerializable
{
    private $_userId;
    private $_userName;
    private $_latitude;
    private $_longitude;
    private $_accuracy;
    private $_timestamp;

    public function __construct(
        $userId,
        $userName,
        $latitude,
        $longitude,
        $accuracy,
        $timestamp
    ) {
        $this->_userId = $userId;
        $this->_userName = $userName;
        $this->_latitude = $latitude;
        $this->_longitude = $longitude;
        $this->_accuracy = $accuracy;
        $this->_timestamp = $timestamp;
    }

    function getFirstName()
    {
        return $this->_firstName;
    }

    function getUserTopic()
    {
        return str_replace('@', '.', $this->_email);
    }

    public function jsonSerialize()
    {
        return [
        'userId' => $this->_userId,
        'userName' => $this->_userName,
        'latitude' => $this->_latitude,
        'longitude' => $this->_longitude,
        'accuracy' => $this->_accuracy,
        'timestamp' => $this->_timestamp,
        'isCached' => false
        ];
    }
  
}

?>
