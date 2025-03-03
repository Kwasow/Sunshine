<?php

// stringDate = YYYY-MM-DD
function findRelationshipYear($stringDate) {
  $year = intval(substr($stringDate, 0, 4));
  $month = intval(substr($stringDate, 5, 2));
  $day = intval(substr($stringDate, 8, 2));

  $config = $GLOBALS['sunshineConfig'];
  $anniversaryMonth = $config['anniversaryMonth'];
  $anniversaryDay = $config['anniversaryDay'];

  if ($month <= $anniversaryMonth && $day < $anniversaryDay) {
    return $year - 1;
  }

  return $year;
}

?>
