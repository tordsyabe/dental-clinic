document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      plugins: [ 'dayGrid', 'timeGrid', 'list', 'bootstrap', 'interaction' ],
      selectable: true,
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
      dateClick: (info) => {
          console.log(info.dateStr);
      },
      eventClick: function(info){
        console.log(info.event.title);
      },
      eventColor: 'var(--secondary)',

    });

    calendar.render();
});
