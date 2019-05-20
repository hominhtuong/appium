$(document).ready(function() {

    $(document.body).on('click', 'a.order', function() {
        var element = $(this);
        $('.screenshots:visible').each(function() {
            if (!~$(this).parent().text().indexOf(element.text())) {
                $(this).slideToggle("fast");
            }
        });
        $(this).siblings(".screenshots").slideToggle("fast");
    });
});