function dateFormat(dateString) {
	var date = new Date(dateString);
	return (date.getMonth() + 1) + "/" + date.getDate();
}