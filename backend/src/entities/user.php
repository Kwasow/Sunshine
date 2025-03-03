<?php

class MissingYouRecipient implements jsonSerializable {
  protected $id;
  protected $firstName;
  protected $email;
  protected $icon;

  public function __construct($id, $firstName, $email, $icon) {
    $this->id = $id;
    $this->firstName = $firstName;
    $this->email = $email;
    $this->icon = $icon;
  }

  function getId() {
    return $this->id;
  }

  function getFirstName() {
    return $this->firstName;
  }

  function getUserTopic() {
    return str_replace('@', '.', $this->email);
  }

  public function jsonSerialize() {
    return [
      'id' => $this->id,
      'firstName' => $this->firstName,
      'icon' => $this->icon
    ];
  }
  
}

class User extends MissingYouRecipient implements JsonSerializable {
  private $lastName;
  private $missingYouRecipient;

  public function __construct($id, $firstName, $lastName, $email, $icon, $missingYouRecipient) {
    $this->id = $id;
    $this->firstName = $firstName;
    $this->lastName = $lastName;
    $this->email = $email;
    $this->icon = $icon;
    $this->missingYouRecipient = $missingYouRecipient;
  }

  function getMissingYouRecipient() {
    return $this->missingYouRecipient;
  }

  public function jsonSerialize() {
    return [
        'id' => $this->id,
        'firstName' => $this->firstName,
        'lastName' => $this->lastName,
        'email' => $this->email,
        'userTopic' => $this->getUserTopic(),
        'icon' => $this->icon,
        'missingYouRecipient' => $this->missingYouRecipient
    ];
  }
}

?>
