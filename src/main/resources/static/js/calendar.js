document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      plugins: [ 'dayGrid', 'timeGrid', 'list', 'bootstrap', 'darkly' ],
      timeZone: 'UTC',
      themeSystem: 'bootstrap',
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'
      },
      weekNumbers: true,
      eventLimit: true, // allow "more" link when too many events
      events: function(info, successCallback, failureCallback) {
            $.ajax({
                type: "GET",
                url: "/api/procedures",
                dataType: "json",
                success: function(doc) {
                    console.log(doc);
                    let events = [];
                    doc.forEach(procedure => {
                       events.push({
                            title: procedure.description,
                            start: procedure.dateCreated
                       });
                    });
                    successCallback(events);
                }
            });
      },
      eventColor: 'red',

    });

    calendar.render();
});