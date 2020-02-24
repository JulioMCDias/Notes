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




    if( strcasecmp( $_SG['method'], 'createNote' ) == 0 ){
	$data = json_decode($postdata);

	$note = new Note();
	foreach ($data as $key => $value) $note->{$key} = $value;
	$note->create();
	$response = $note->search();
        echo json_encode($response);
    } 


    else if( strcasecmp( $_SG['method'], 'updateNote' ) == 0 ){
	$data = json_decode($postdata);

	$note = new Note();
	foreach ($data as $key => $value) $note->{$key} = $value;
	$note->update();
	$response = $note->search();
        echo json_encode($response);
    }

    else if( strcasecmp( $_SG['method'], 'deleteNote' ) == 0 ){
	$data = json_decode($postdata);

	$note = new Note();
	foreach ($data as $key => $value) $note->{$key} = $value;
	$note->delete();
	$response = $note->search();
        echo json_encode($response);
    }


    else if( strcasecmp( $_SG['method'], 'getNotes' ) == 0 ){
	$data = json_decode($postdata);

	$response = [];
	$note = new Note();
	foreach ($data as $key => $value) $note->{$key} = $value;
	$note->userID = $data->id;
	$response = $note->searchAll();
        echo json_encode($response, true);
    }
?>
