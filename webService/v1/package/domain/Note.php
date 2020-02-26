<?php

class Note {
    public $noteID;
    public $title;
    public $description;
    public $userID;

    public function __construct( $noteID=-1, $title="", $description=null, $userID=-1){
        $this->noteID = $noteID;
        $this->title = $title;
        $this->description = $description;
        $this->userID = $userID;
    }





    public function create()
    {
	$cnn = new Conexao();
        $sql = "INSERT INTO Notes(Title, Description, UserID) VALUES (:title, :description, :userID)";

        $result = $cnn->Conexao()->prepare($sql);
	$result->bindValue(":title", $this->title);
	$result->bindValue(":description", $this->description);
	$result->bindValue(":userID", $this->userID);
        $result->execute();
    }


    public function update()
    {
	$cnn = new Conexao();
        $sql = "UPDATE Notes SET Title=:title, Description=:description WHERE NotesID=:noteID";

        $result = $cnn->Conexao()->prepare($sql);
	$result->bindValue(":title", $this->title);
	$result->bindValue(":description", $this->description);
	$result->bindValue(":noteID", $this->noteID);
        $result->execute();
    }

    public function delete()
    {
	$cnn = new Conexao();
        $sql = "DELETE FROM Notes WHERE NotesID=:noteID";

        $result = $cnn->Conexao()->prepare($sql);
	$result->bindValue(":noteID", $this->noteID);
        $result->execute();
    }

    public function searchAll()
    {
	$cnn = new Conexao();
        $sql = "SELECT * FROM Notes WHERE UserID=".$this->userID;

        $result = $cnn->Conexao()->prepare($sql);
        $result->execute();

	$res = [];
        if($result->rowCount()>=1){
	    while($row = $result->fetch(PDO::FETCH_ASSOC)){
		$response = new Note();
	        $response->noteID = $row['NotesID'];
	        $response->title = $row['Title'];
	        $response->description = $row['Description'];
    	        $response->userID = $row['UserID'];
		$res[] = $response;
	    }
	    return $res;
        }else{
            return $res;
        }
    }

    public function search()
    {
	$cnn = new Conexao();
        $sql = "SELECT * FROM Notes WHERE Title=:title && Description=:description && UserID=:userID";

        $result = $cnn->Conexao()->prepare($sql);
	$result->bindValue(":title", $this->title);
	$result->bindValue(":description", $this->description);
	$result->bindValue(":userID", $this->userID);
        $result->execute();

        $response = new Note();
        if($result->rowCount()>=1){
	    $row = $result->fetch(PDO::FETCH_ASSOC);

	    $response->noteID = $row['NotesID'];
	    $response->title = $row['Title'];
	    $response->description = $row['Description'];
	    $response->userID = $row['UserID'];
	    return $response;
	    
        }else{
            return $response;
        }
    }
}
