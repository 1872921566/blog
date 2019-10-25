$('.ui.dropdown').dropdown({
    on : 'click',
})

$('#showmenu').click(function () {
    $('.m-menu').toggleClass('m-mobile-hide');
});


$('.ui.form').form({
    fields :{
        title : {
            identifier :'title',
            rules:[{
                type:'empty',
                prompt:'请输入博客标题',
            }]
        }
    }
});