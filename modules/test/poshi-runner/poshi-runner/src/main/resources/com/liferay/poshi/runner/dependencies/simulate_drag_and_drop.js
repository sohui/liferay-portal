Simulate = {
    createEvent: function(eventName, options) {
        var event = document.createEvent("CustomEvent");

        event.initCustomEvent(eventName, true, true, null);

        event.dataTransfer = {
            data: {
            },
            getData: function (format) {
                return this.data[format];
            },
            setData: function (format, data) {
                this.data[format] = data;
            }
        };

        return event;
    },

    dragAndDrop: function(sourceElement, targetElement) {
        var dragStartEvent = this.createEvent("dragstart");

        sourceElement.dispatchEvent(dragStartEvent);

        var dropEvent = this.createEvent(
            "drop",
            {
                dataTransfer: dragStartEvent.dataTransfer
            }
        );

        targetElement.dispatchEvent(dropEvent);

        var dragEndEvent = this.createEvent(
            "dragend",
            {
                dataTransfer: dragStartEvent.dataTransfer
            }
        );

        sourceElement.dispatchEvent(dragEndEvent);
    }
};