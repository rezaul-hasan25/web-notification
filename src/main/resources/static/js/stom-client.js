function StompClientJS(serverURL) {

    var socket = new SockJS(serverURL);
    this.stompClient = Stomp.over(socket);
    this.eventHandlers = {};
    const allowedEvents = ["receive", "connect", "error"];

    this.on = (eventName, func) => {
        if (!allowedEvents.includes(eventName)) {
            console.error(eventName + "is not an event");
            return;
        }
        this.eventHandlers[eventName] = func;
    };

    this.trigger = (eventName, data) => {
        if (this.eventHandlers[eventName]) {
            this.eventHandlers[eventName](data);
        }
    };

    this.stompClient.connect({}, (frame) => {
        console.log('Connected: ' + frame);
        this.trigger("connect", frame);

        this.stompClient.subscribe('/user/queue/notifications', (message) => {
            console.log('Received: ' + message.body);
            this.trigger("receive", JSON.parse(message.body));
        });

        this.stompClient.subscribe('/topic/public', (message) => {
            console.log('Received: ' + message.body);
            this.trigger("receive", JSON.parse(message.body));
        });
        this.stompClient.subscribe('/user/queue/private', (message) => {
            console.log('Received: ' + message.body);
            this.trigger("receive", JSON.parse(message.body));
        });

    }, (error) => {
        console.error('Connection error:', error);
        this.trigger("error", error);
    });


    //
    this.send = (message) =>
    {
        this.stompClient.send("/app/private", {}, message);
    }


}
