var stompClient = null;

function setConnected(connected) {
	$("#connect").prop("disabled", connected);
	$("#disconnect").prop("disabled", !connected);
	if (connected) {
		$("#conversation").show();
	}
	else {
		$("#conversation").hide();
	}
	$("#responses").html("");
}

function connect() {
	var socket = new SockJS('/cf-java-client');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function (frame) {
		setConnected(true);
		console.log('Connected: ' + frame);
		stompClient.subscribe('/topic/messages', function (message) {
			showResponse(message.body);
		});
	});
}

function disconnect() {
	if (stompClient != null) {
		stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
}

function sendRequest() {
	stompClient.send("/app/request", {});
	showResponse("pending...")
}

function showResponse(response) {
	$("#responses").text(response);
}

$(function () {
	$("form").on('submit', function (e) {
		e.preventDefault();
	});
	$("#connect").click(function () { connect(); });
	$("#disconnect").click(function () { disconnect(); });
	$("#send").click(function() { sendRequest(); });
});
