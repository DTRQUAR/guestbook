/**
 * Created by Qouer on 04.07.2016.
 */
var ajaxUrl = 'ajax/messages';

$(document).ready(function() {

    $.ajax({
        type: "GET",
        url: ajaxUrl,
        success: function (messageList) {
            drawMessageList(messageList);
        }
    });


});

//Отрисовка всего списка существующих сообщений
function drawMessageList(messageList){

    for (i = 0; i < messageList.length; i++){
        var divCurrentMessage = document.createElement('div');
        divCurrentMessage.id = 'message_' + messageList[i].id;
        $(divCurrentMessage).addClass('messageBox');

        if (i == 0) {
            var mArea = document.getElementsByClassName('messagesArea');
            mArea[0].appendChild(divCurrentMessage);
        }else{
            var messageBoxList = document.getElementsByClassName('messageBox');
            $(divCurrentMessage).insertAfter(messageBoxList[messageBoxList.length-1]);
        }

        //Вывод имени автора сообщения
        var textareaName = document.createElement('textarea');
        $(textareaName).attr('readonly','readonly');
        $(textareaName).attr('disabled','disabled');
        $(textareaName).addClass('nameOfMessage');
        if (messageList[i].defaultName == null){
            textareaName.innerHTML = messageList[i].userName;
        }else if (messageList[i].defaultName != null){
            textareaName.innerHTML = messageList[i].defaultName;
        }
        $("#inputField").val(messageList[i].userName);
        divCurrentMessage.appendChild(textareaName);

        //Вывод текста сообщения
        var textareaText = document.createElement('textarea');
        $(textareaText).attr('readonly','readonly');
        $(textareaText).attr('disabled','disabled');
        $(textareaText).addClass('textOfMessage');
        textareaText.innerHTML = messageList[i].text;
        $(textareaText).insertAfter(textareaName);

        var like;
        var likeCount;
        var notlike;
        var notlikeCount;

        //Вывод лайков и дизлайков
        //Если страницу просматривает НЕАВТОРИЗИРОВАННЫЙ пользователь
        if (messageList[i].authRate == '-1'){
            //Вставляет иконку лайка
            like = document.createElement('button');
            $(like).addClass('rateButton');
            $(like).prop('disabled', true);
            like.id = 'likeButton';
            like.innerHTML = '&nbsp&nbsp';
            $(like).insertAfter(textareaText);

            //Вставляет количество лайков
            likeCount = document.createElement('a');
            likeCount.innerHTML = messageList[i].plusCount + '&nbsp&nbsp';
            $(likeCount).insertAfter(like);

            //Вставляет иконку дизлайка
            notlike = document.createElement('button');
            $(notlike).addClass('rateButton');
            $(notlike).prop('disabled', true);
            notlike.id = 'notLikeButton';
            notlike.innerHTML = '&nbsp&nbsp';
            $(notlike).insertAfter(likeCount);

            //Вставляет количество дизлайков
            notlikeCount = document.createElement('a');
            notlikeCount.innerHTML = messageList[i].minusCount + '&nbsp&nbsp';
            $(notlikeCount).insertAfter(notlike);


        }
        //Если страницу просматривает АВТОРИЗИРОВАННЫЙ, НО НЕ ОЦЕНИВШИЙ СООБЩЕНИЕ пользователь
        else if (messageList[i].authRate == '0'){
            //Вставляет иконку лайка
            like = document.createElement('button');
            $(like).addClass('rateButton');
            like.id = 'likeButton';
            like.innerHTML = '&nbsp&nbsp';
            $(like).insertAfter(textareaText);

            //Вставляет количество лайков
            likeCount = document.createElement('a');
            likeCount.innerHTML = messageList[i].plusCount + '&nbsp&nbsp';
            $(likeCount).insertAfter(like);

            //Вставляет иконку дизлайка
            notlike = document.createElement('button');
            $(notlike).addClass('rateButton');
            notlike.id = 'notLikeButton';
            notlike.innerHTML = '&nbsp&nbsp';
            $(notlike).insertAfter(likeCount);

            //Вставляет количество дизлайков
            notlikeCount = document.createElement('a');
            notlikeCount.innerHTML = messageList[i].minusCount + '&nbsp&nbsp';
            $(notlikeCount).insertAfter(notlike);
        }
        //Если страницу просматривает АВТОРИЗИРОВАННЫЙ И ПОСТАВИВШИЙ ЛАЙК пользователь
        else if (messageList[i].authRate == '1'){
            //Вставляет иконку лайка
            like = document.createElement('button');
            $(like).addClass('rateButton');
            like.id = 'selectLikeButton';
            like.innerHTML = '&nbsp&nbsp';
            $(like).insertAfter(textareaText);

            //Вставляет количество лайков
            likeCount = document.createElement('a');
            likeCount.innerHTML = messageList[i].plusCount + '&nbsp&nbsp';
            $(likeCount).insertAfter(like);

            //Вставляет иконку дизлайка
            notlike = document.createElement('button');
            $(notlike).addClass('rateButton');
            notlike.id = 'notLikeButton';
            notlike.innerHTML = '&nbsp&nbsp';
            $(notlike).insertAfter(likeCount);

            //Вставляет количество дизлайков
            notlikeCount = document.createElement('a');
            notlikeCount.innerHTML = messageList[i].minusCount + '&nbsp&nbsp';
            $(notlikeCount).insertAfter(notlike);
        }
        //Если страницу просматривает АВТОРИЗИРОВАННЫЙ И ПОСТАВИВШИЙ ДИЗЛАЙК пользователь
        else if (messageList[i].authRate == '2'){
            //Вставляет иконку лайка
            like = document.createElement('button');
            $(like).addClass('rateButton');
            like.id = 'likeButton';
            like.innerHTML = '&nbsp&nbsp';
            $(like).insertAfter(textareaText);

            //Вставляет количество лайков
            likeCount = document.createElement('a');
            likeCount.innerHTML = messageList[i].plusCount + '&nbsp&nbsp';
            $(likeCount).insertAfter(like);

            //Вставляет иконку дизлайка
            notlike = document.createElement('button');
            $(notlike).addClass('rateButton');
            notlike.id = 'selectNotLikeButton';
            notlike.innerHTML = '&nbsp&nbsp';
            $(notlike).insertAfter(likeCount);

            //Вставляет количество дизлайков
            notlikeCount = document.createElement('a');
            notlikeCount.innerHTML = messageList[i].minusCount + '&nbsp&nbsp';
            $(notlikeCount).insertAfter(notlike);
        }

        ////Вывод даты сообщения
        var dateTime = document.createElement('div');
        $(dateTime).addClass('dateOfMessage');
        dateTime.innerHTML = messageList[i].data +'&nbsp&nbsp' + messageList[i].time;
        $(dateTime).insertAfter(notlikeCount);

        var br = document.createElement('br');
        $(br).insertAfter(dateTime);
        $(br).insertAfter(dateTime);
    }

    $('.textOfMessage').autosize();
    $('.nameOfMessage').autosize();

}

//Обработка события нажатия на кнопку "Отправить" при отправке нового сообщения
$('#sendButton').click(function() {
    var error = false;
    $(".inputField").each(function(){
        var text = $.trim($(this).val());
        if(text == ""){
            error=true;
        }
    })
    if(error == true){
        $(".inputField").each(function(){
            var text = $.trim($(this).val());
            if (text == ""){
                $(this).val("Введите значение");
            }
        })
        return false;
    }

    var form = $('#messageForm');
    $.ajax({
        type: "POST",
        url: ajaxUrl + '/create',
        data: form.serialize(),
        success: function (message) {
            insertNewMessage(message);
        }
    })
});

//Вставка нового сообщения в список существующих сообщений
function insertNewMessage(message){

    var divCurrentMessage = document.createElement('div');
    divCurrentMessage.id = 'message_' + message.id;
    $(divCurrentMessage).addClass('messageBox');

    var messageBoxList = document.getElementsByClassName('messageBox');
    var mArea = document.getElementsByClassName('messagesArea');
    if (messageBoxList == null) {
        var mArea = document.getElementsByClassName('messagesArea');
        mArea[0].appendChild(divCurrentMessage);
    }else{
        $(divCurrentMessage).insertBefore(messageBoxList[0]);
    }

    //Вывод имени автора сообщения
    var textareaName = document.createElement('textarea');
    $(textareaName).attr('readonly','readonly');
    $(textareaName).attr('disabled','disabled');
    $(textareaName).addClass('nameOfMessage');
    if (message.defaultName == null){
        textareaName.innerHTML = message.userName;
    }else if (message.defaultName != null){
        textareaName.innerHTML = message.defaultName;
    }

    //Вывод текста сообщения
    divCurrentMessage.appendChild(textareaName);

    var textareaText = document.createElement('textarea');
    $(textareaText).attr('readonly','readonly');
    $(textareaText).attr('disabled','disabled');
    $(textareaText).addClass('textOfMessage');
    textareaText.innerHTML = message.text;

    $(textareaText).insertAfter(textareaName);

    //Вывод лайков и дизлайков
    //Если страницу просматривает НЕАВТОРИЗИРОВАННЫЙ пользователь
    var like;
    var likeCount;
    var notlike;
    var notlikeCount;
    if (message.authRate == '-1'){
        //Вставляет иконку лайка
        like = document.createElement('button');
        $(like).addClass('rateButton');
        $(like).prop('disabled', true);
        like.id = 'likeButton';
        like.innerHTML = '&nbsp&nbsp';
        $(like).insertAfter(textareaText);

        //Вставляет количество лайков
        likeCount = document.createElement('a');
        likeCount.innerHTML = message.plusCount + '&nbsp&nbsp';
        $(likeCount).insertAfter(like);

        //Вставляет иконку дизлайка
        notlike = document.createElement('button');
        $(notlike).addClass('rateButton');
        $(notlike).prop('disabled', true);
        notlike.id = 'notLikeButton';
        notlike.innerHTML = '&nbsp&nbsp';
        $(notlike).insertAfter(likeCount);

        //Вставляет количество дизлайков
        notlikeCount = document.createElement('a');
        notlikeCount.innerHTML = message.minusCount + '&nbsp&nbsp';
        $(notlikeCount).insertAfter(notlike);


    }
    //Если страницу просматривает АВТОРИЗИРОВАННЫЙ, НО НЕ ОЦЕНИВШИЙ СООБЩЕНИЕ пользователь
    else if (message.authRate == '0'){
        //Вставляет иконку лайка
        like = document.createElement('button');
        $(like).addClass('rateButton');
        like.id = 'likeButton';
        like.innerHTML = '&nbsp&nbsp';
        $(like).insertAfter(textareaText);

        //Вставляет количество лайков
        likeCount = document.createElement('a');
        likeCount.innerHTML = message.plusCount + '&nbsp&nbsp';
        $(likeCount).insertAfter(like);

        //Вставляет иконку дизлайка
        notlike = document.createElement('button');
        $(notlike).addClass('rateButton');
        notlike.id = 'notLikeButton';
        notlike.innerHTML = '&nbsp&nbsp';
        $(notlike).insertAfter(likeCount);

        //Вставляет количество дизлайков
        notlikeCount = document.createElement('a');
        notlikeCount.innerHTML = message.minusCount + '&nbsp&nbsp';
        $(notlikeCount).insertAfter(notlike);
    }
    //Если страницу просматривает АВТОРИЗИРОВАННЫЙ И ПОСТАВИВШИЙ ЛАЙК пользователь
    else if (message.authRate == '1'){
        //Вставляет иконку лайка
        like = document.createElement('button');
        $(like).addClass('rateButton');
        like.id = 'selectLikeButton';
        like.innerHTML = '&nbsp&nbsp';
        $(like).insertAfter(textareaText);

        //Вставляет количество лайков
        likeCount = document.createElement('a');
        likeCount.innerHTML = message.plusCount + '&nbsp&nbsp';
        $(likeCount).insertAfter(like);

        //Вставляет иконку дизлайка
        notlike = document.createElement('button');
        $(notlike).addClass('rateButton');
        notlike.id = 'notLikeButton';
        notlike.innerHTML = '&nbsp&nbsp';
        $(notlike).insertAfter(likeCount);

        //Вставляет количество дизлайков
        notlikeCount = document.createElement('a');
        notlikeCount.innerHTML = message.minusCount + '&nbsp&nbsp';
        $(notlikeCount).insertAfter(notlike);
    }
    //Если страницу просматривает АВТОРИЗИРОВАННЫЙ И ПОСТАВИВШИЙ ДИЗЛАЙК пользователь
    else if (message.authRate == '2'){
        //Вставляет иконку лайка
        like = document.createElement('button');
        $(like).addClass('rateButton');
        like.id = 'likeButton';
        like.innerHTML = '&nbsp&nbsp';
        $(like).insertAfter(textareaText);

        //Вставляет количество лайков
        likeCount = document.createElement('a');
        likeCount.innerHTML = message.plusCount + '&nbsp&nbsp';
        $(likeCount).insertAfter(like);

        //Вставляет иконку дизлайка
        notlike = document.createElement('button');
        $(notlike).addClass('rateButton');
        notlike.id = 'selectNotLikeButton';
        notlike.innerHTML = '&nbsp&nbsp';
        $(notlike).insertAfter(likeCount);

        //Вставляет количество дизлайков
        notlikeCount = document.createElement('a');
        notlikeCount.innerHTML = message.minusCount + '&nbsp&nbsp';
        $(notlikeCount).insertAfter(notlike);
    }

    ////Вывод даты сообщения
    var dateTime = document.createElement('div');
    $(dateTime).addClass('dateOfMessage');
    dateTime.innerHTML = message.data +'&nbsp&nbsp' + message.time;
    $(dateTime).insertAfter(notlikeCount);

    var br = document.createElement('br');
    $(br).insertAfter(dateTime);

    $('.textOfMessage').autosize();
    $('.nameOfMessage').autosize();
}

//Обработка события нажатия на лайк или дизлайк
$(document).on('click', '.rateButton', function (event) {
    var parent = $(event.target).parent().get(0);

    var href;
    if (event.target.id == 'likeButton' || event.target.id == 'selectLikeButton'){
        href = ajaxUrl + '/rate?action=like&message=' + parent.id.toString().replace('message_', '');
    }else if (event.target.id == 'notLikeButton' || event.target.id == 'selectNotLikeButton'){
        href = ajaxUrl + '/rate?action=notlike&message=' + parent.id.toString().replace('message_', '');
    }

    $.ajax({
        type: "GET",
        url: href,
        success: function (message) {
            updateMessageRate(message);
        }
    })
});

//Функция обновления рейтинга сообщения
function updateMessageRate(message){
    var message_id = message.id;
    var divMessageBox = document.getElementById('message_' + message_id);
    var divTextOfMessage = divMessageBox.getElementsByClassName('textOfMessage');
    divTextOfMessage = divTextOfMessage[0];
    var divRateButton= divMessageBox.getElementsByClassName('rateButton');
    var aTag= divMessageBox.getElementsByTagName('a');
    $(divRateButton).remove();
    $(aTag).remove();

    var like;
    var likeCount;
    var notlike;
    var notlikeCount;

    //Вывод лайков и дизлайков
    //Если страницу просматривает НЕАВТОРИЗИРОВАННЫЙ пользователь
    if (message.authRate == '-1'){
        //Вставляет иконку лайка
        like = document.createElement('button');
        $(like).addClass('rateButton');
        like.prop('disabled', true);
        like.id = 'likeButton';
        like.innerHTML = '&nbsp&nbsp';
        $(like).insertAfter(divTextOfMessage);

        //Вставляет количество лайков
        likeCount = document.createElement('a');
        likeCount.innerHTML = message.plusCount + '&nbsp&nbsp';
        $(likeCount).insertAfter(like);

        //Вставляет иконку дизлайка
        notlike = document.createElement('button');
        $(notlike).addClass('rateButton');
        notlike.prop('disabled', true)
        notlike.id = 'notLikeButton';
        notlike.innerHTML = '&nbsp&nbsp';
        $(notlike).insertAfter(likeCount);

        //Вставляет количество дизлайков
        notlikeCount = document.createElement('a');
        notlikeCount.innerHTML = message.minusCount + '&nbsp&nbsp';
        $(notlikeCount).insertAfter(notlike);


    }
    //Если страницу просматривает АВТОРИЗИРОВАННЫЙ, НО НЕ ОЦЕНИВШИЙ СООБЩЕНИЕ пользователь
    else if (message.authRate == '0'){
        //Вставляет иконку лайка
        like = document.createElement('button');
        $(like).addClass('rateButton');
        like.id = 'likeButton';
        like.innerHTML = '&nbsp&nbsp';
        $(like).insertAfter(divTextOfMessage);

        //Вставляет количество лайков
        likeCount = document.createElement('a');
        likeCount.innerHTML = message.plusCount + '&nbsp&nbsp';
        $(likeCount).insertAfter(like);

        //Вставляет иконку дизлайка
        notlike = document.createElement('button');
        $(notlike).addClass('rateButton');
        notlike.id = 'notLikeButton';
        notlike.innerHTML = '&nbsp&nbsp';
        $(notlike).insertAfter(likeCount);

        //Вставляет количество дизлайков
        notlikeCount = document.createElement('a');
        notlikeCount.innerHTML = message.minusCount + '&nbsp&nbsp';
        $(notlikeCount).insertAfter(notlike);
    }
    //Если страницу просматривает АВТОРИЗИРОВАННЫЙ И ПОСТАВИВШИЙ ЛАЙК пользователь
    else if (message.authRate == '1'){
        //Вставляет иконку лайка
        like = document.createElement('button');
        $(like).addClass('rateButton');
        like.id = 'selectLikeButton';
        like.innerHTML = '&nbsp&nbsp';
        $(like).insertAfter(divTextOfMessage);

        //Вставляет количество лайков
        likeCount = document.createElement('a');
        likeCount.innerHTML = message.plusCount + '&nbsp&nbsp';
        $(likeCount).insertAfter(like);

        //Вставляет иконку дизлайка
        notlike = document.createElement('button');
        $(notlike).addClass('rateButton');
        notlike.id = 'notLikeButton';
        notlike.innerHTML = '&nbsp&nbsp';
        $(notlike).insertAfter(likeCount);

        //Вставляет количество дизлайков
        notlikeCount = document.createElement('a');
        notlikeCount.innerHTML = message.minusCount + '&nbsp&nbsp';
        $(notlikeCount).insertAfter(notlike);
    }
    //Если страницу просматривает АВТОРИЗИРОВАННЫЙ И ПОСТАВИВШИЙ ДИЗЛАЙК пользователь
    else if (message.authRate == '2'){
        //Вставляет иконку лайка
        like = document.createElement('button');
        $(like).addClass('rateButton');
        like.id = 'likeButton';
        like.innerHTML = '&nbsp&nbsp';
        $(like).insertAfter(divTextOfMessage);

        //Вставляет количество лайков
        likeCount = document.createElement('a');
        likeCount.innerHTML = message.plusCount + '&nbsp&nbsp';
        $(likeCount).insertAfter(like);

        //Вставляет иконку дизлайка
        notlike = document.createElement('button');
        $(notlike).addClass('rateButton');
        notlike.id = 'selectNotLikeButton';
        notlike.innerHTML = '&nbsp&nbsp';
        $(notlike).insertAfter(likeCount);

        //Вставляет количество дизлайков
        notlikeCount = document.createElement('a');
        notlikeCount.innerHTML = message.minusCount + '&nbsp&nbsp';
        $(notlikeCount).insertAfter(notlike);
    }



}

