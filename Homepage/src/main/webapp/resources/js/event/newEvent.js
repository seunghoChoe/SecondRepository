$(function () {
    var ckeditor_config = {
        resize_enabled: true,
        height: 500,
        enterMode: CKEDITOR.ENTER_BR,
        shiftEnterMode: CKEDITOR.ENTER_P,
        filebrowserUploadUrl: "/imageUpload"
    };

    CKEDITOR.replace('eventContent', ckeditor_config);
});

$(function () {
    $('#eventStartDate').datetimepicker({format: 'YYYY-MM-DD HH:mm:ss'});
    $('#eventEndDate').datetimepicker({format: 'YYYY-MM-DD HH:mm:ss'});
});