//Common script
$(document).ready(function() {
	// Responsive menu begin
	// Responsive menu end
});

function removeEmployee(selected) {
	$(selected).parent().parent().remove();
}