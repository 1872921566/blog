$('#showmenu').click(function () {
    $('.m-menu').toggleClass('m-mobile-hide');
});

$('#payButton').popup({
    popup : $('.pay.popup'),
    on : 'click',
    position : 'bottom center',
});

