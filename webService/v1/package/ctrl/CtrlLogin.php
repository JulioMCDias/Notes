<?php

    require_once('../autoload.php');
    // HACK CODE
        $_SG = empty($_POST) ? $_GET : $_POST;
	$postdata = file_get_contents('php://input');		//body push

        $_SG = isset($_SG['method']) ? $_SG : $postdata;
        $_SG['method'] = trim($_SG['method'], '"');
        if( !is_array( $_SG ) ){
            $_SG = json_decode($_SG, true);
        }




    if( strcasecmp( $_SG['method'], 'setLogin' ) == 0 ){
	$data = json_decode($postdata);

	$login = new Login();
	foreach ($data as $key => $value) $login->{$key} = $value;
	$response = $login->search();
        echo json_encode($response);
    }




    else if( strcasecmp( $_SG['method'], 'createUser' ) == 0 ){
	$data = json_decode($postdata);

	$login = new Login();
	foreach ($data as $key => $value) $login->{$key} = $value;
	$login->create();
	$response = $login->search();
        echo json_encode($response);
    }
?>
