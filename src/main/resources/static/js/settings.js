(function() {
    $(".settings-item-list").click(function(){
        $(this).siblings().removeClass("active");
        console.log($(this).siblings());
    });
})();



(function(){

    $("#settings-lists .settings-item-list").first().addClass("active");
    $("#categories-lists .settings-item-list").first().addClass("active");
    $(".categories-tab-pane").first().addClass("active show");



})();