<?php

class UserLocation implements jsonSerializable {
  protected $userId;
  protected $userName;
  protected $latitude;
  protected $longitude;
  protected $accuracy;
  protected $timestamp;

  public function __construct($userId, $userName, $latitude, $longitude, $accuracy, $timestamp) {
    $this->userId = $userId;
    $this->userName = $userName;
    $this->latitude = $latitude;
    $this->longitude = $longitude;
    $this->accuracy = $accuracy;
    $this->timestamp = $timestamp;
  }

  function getFirstName() {
    return $this->firstName;
  }

  function getUserTopic() {
    return str_replace('@', '.', $this->email);
  }

  public function jsonSerialize() {
    return [
      'userId' => $this->userId,
      'userName' => $this->userName,
      'latitude' => $this->latitude,
      'longitude' => $this->longitude,
      'accuracy' => $this->accuracy,
      'timestamp' => $this->timestamp,
      'isCached' => FALSE
    ];
  }
  
}

?>
