if ( window.history.replaceState ) {
	window.history.replaceState( null, null, window.location.href );
}

function backToIndex() {
	location.href = './';
}

function goToAuth() {
	location.href = './auth';
}

async function removeItem(id) {
	var url = "?id="+id;
	console.log(url);
	let response = await fetch(url, { method: "DELETE" });

	if (response.status === 200) {
		let element = document.getElementById(id);
		element.remove();
		setTimeout(function() {
			location.reload();
		}, 500)
	} else {
		alert("Jotain meni pieleen..");
	}
}

async function removeProduct(id) {
	let response = await fetch(`/list?id=${id}`, { method: "DELETE" });

	if (response.status === 200) {
		let element = document.getElementById(`${id}`);
		element.remove();
	} else {
		alert("Something went wrong");
	}
}