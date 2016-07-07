/**
 * Created by Qouer on 07.07.2016.
 */

//Обработка поля для ввода имени
$('#nameField').on('input', function() {
    var value = $(this).val();
    if ($.trim(value) == '' || $.trim(value) == 'Введите значение'){
        $('#inputNameStatusBox').removeClass("form-group has-success");
        $('#inputNameStatusBox').addClass("form-group has-error");
    }else{
        $('#inputNameStatusBox').removeClass("form-group has-error");
        $('#inputNameStatusBox').addClass("form-group has-success");
    }
});

//Обработка поля для ввода текса сообщения
$('#messageField').on('input', function() {
    var value = $(this).val();
    if ($.trim(value) == '' || $.trim(value) == 'Введите значение'){
        $('#inputTextStatusBox').removeClass("form-group has-success");
        $('#inputTextStatusBox').addClass("form-group has-error");
    }else{
        $('#inputTextStatusBox').removeClass("form-group has-error");
        $('#inputTextStatusBox').addClass("form-group has-success");
    }
});