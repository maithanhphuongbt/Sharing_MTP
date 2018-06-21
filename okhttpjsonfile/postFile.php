<?php
	$file_path = "images/";
	$file_path = $file_path.($_FILES['upload_image']['name']);

	if (move_uploaded_file($_FILES['upload_image']['tmp_name'],$file_path)) {// file tạm, chỗ chứa
		echo "Success";
	}else {
		echo "Fail";
	}
?>


this PC -> properties -> Advanced system settings -> Advance -> Enviroment Variable -> New
cd keeytoo o cho cai android
chay keeytool roi cd toi thu muc chua server.cer.