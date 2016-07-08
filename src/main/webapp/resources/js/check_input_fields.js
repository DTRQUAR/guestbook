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

//var isSuccessRegisterInput = false;
//var isSuccessRegisterName = false;
//var isSuccessRegisterPassword = false;
////var isSuccessRegistername = false;
//
//$('#registerNameField').on('input', function() {
//    var value = $.trim($(this).val());
//    if (value.length > 0) {
//        $('#registerNameArea').removeClass("form-group has-error");
//        $('#registerNameArea').addClass("form-group has-success");
//        isSuccessRegisterName = true;
//        checkSuccessRegisterInput();
//    }else{
//        $('#registerNameArea').removeClass("form-group has-success");
//        $('#registerNameArea').addClass("form-group has-error");
//        isSuccessRegisterName = false;
//        checkSuccessRegisterInput();
//    }
//});
//
//$('#registerPasswordField').on('input', function() {
//    var value = $.trim($(this).val());
//    if (value.length > 5) {
//        $('#registerPasswordArea').removeClass("form-group has-error");
//        $('#registerPasswordArea').addClass("form-group has-success");
//        isSuccessRegisterPassword = true;
//        checkSuccessRegisterInput();
//    }else{
//        $('#registerPasswordArea').removeClass("form-group has-success");
//        $('#registerPasswordArea').addClass("form-group has-error");
//        isSuccessRegisterPassword = false;
//        checkSuccessRegisterInput();
//    }
//});
//
//function checkSuccessRegisterInput(){
//    isSuccessRegisterInput = isSuccessRegisterName && isSuccessRegisterPassword;
//    if (isSuccessRegisterInput == true){
//        $('#submitRegisterButton').prop('disabled', false);
//    }else{
//        $('#submitRegisterButton').prop('disabled', true);
//    }
//}


