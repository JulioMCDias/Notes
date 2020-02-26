<?php
include_once '../data/Conexao.php';

class Login {
    public $name;
    public $password;
    public $email;

    public function __construct( $name="", $password="", $email=""){
	$this->name = $name;
	$this->password = $password;
	$this->email = $email;
    }

    public function search()
    {
	$cnn = new Conexao();
        $sql = "SELECT * FROM users WHERE Name='".$this->name."' && Password='".$this->password."'";

        $result = $cnn->Conexao()->prepare($sql);
        $result->execute();

            $response = new LoginResponse();
        if($result->rowCount()>=1){
	    $row = $result->fetch(PDO::FETCH_ASSOC);

	    $response->id = $row['UsersID'];
	    $response->name = $row['Name'];
	    $response->email = $row['Email'];
	    return $response;
	    
        }else{
            return $response;
        }
    }



    public function create()
    {
	$cnn = new Conexao();
        $sql = "INSERT INTO users(Name, Email, Password) VALUES (:name, :email, :password)";

        $result = $cnn->Conexao()->prepare($sql);
	$result->bindValue(":name", $this->name);
	$result->bindValue(":email", $this->email);
	$result->bindValue(":password", $this->password);
        $result->execute();
    }

}
