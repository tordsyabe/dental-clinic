const setImage = (event) => {


	const file = document.querySelector('#for-upload');

	file.src = URL.createObjectURL(event.target.files[0]);

	$("#filename").val(event.target.files[0].name);
	openSideProcedures();


};