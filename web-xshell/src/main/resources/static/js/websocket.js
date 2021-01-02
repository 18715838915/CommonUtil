let socket;
let websocketUrl = 'ws://localhost/webSocket/';
function openSocket() {
    if (typeof (WebSocket) == "undefined"){
        alert("您当前的浏览器不支持websocket");
        return;
    }

    let userName = document.getElementById("username").value;
    let sendUrl = websocketUrl + userName;

    if (socket != null){
        socket.close();
        socket = null;
    }
    socket = new WebSocket(sendUrl);

    socket.onopen = function () {
        alert("websocket已经打开");
    }

    socket.onmessage = function (msg) {
        alert(msg);
    }

    socket.onclose = function () {
        alert("关闭");
    }

    socket.onerror = function () {
        alert("发生错误");
    }
}

openSocket();


function sendMsg() {
    console.log("发送消息")
    if (typeof(WebSocket) == "undefined"){
        alert("您当前的浏览器不支持websocket");
        return;
    }

    let msg = document.getElementById("msg").value;
    socket.send(msg);
}







