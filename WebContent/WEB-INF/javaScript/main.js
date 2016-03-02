/**
 * @params{string} https method
 * @params{object} data fot sending
 * @params{function} callBack on succes
 */
function sendRequest(method, data, callBack, url) {
	var xhr = new XMLHttpRequest();
	xhr.open(method, url, false);
	xhr.setRequestHeader("Accept", "application/json");
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(data);

	if (xhr.status != 200) {
		console.warn(xhr.status + ': ' + xhr.statusText);
	} else {
		callBack(xhr.responseText);
	}
}
function printCompanies(companies) {
	$('#compList').empty();
	$('#compList').append(companies);
}

function getAllCompanies() {
	sendRequest('GET', {}, printCompanies, window.location.origin +"/company/tree");
}

function addNewCompany() {
	var company = '{' + '"estimatedEarnings":' + $('#estEarn').val()
			+ ', "name":' + '"' + $('#compName').val() + '"}';
	sendRequest('POST', company, function(msg) {
	}, window.location.origin +"/company/add/" + $('#compId').val());
}

function deleteComp() {
	sendRequest('DELETE', null, function(msg) {
	}, window.location.origin +"/company/delete/" + $('#delId').val());
}