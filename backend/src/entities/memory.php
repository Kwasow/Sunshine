<?php

class Memory implements JsonSerializable {
  private $id;
  private $startDate;
  private $endDate;
  private $title;
  private $description;
  private $photo;

  public function __construct($id, $startDate, $endDate, $title, $description, $photo) {
    $this->id = $id;
    $this->startDate = $startDate;
    $this->endDate = $endDate;
    $this->title = $title;
    $this->description = $description;
    $this->photo = $photo;
  }

  public function jsonSerialize() {
    return [
        'id' => $this->id,
        'startDate' => $this->startDate,
        'endDate' => $this->endDate,
        'title' => $this->title,
        'description' => $this->description,
        'photo' => $this->photo
    ];
  }

}

?>
