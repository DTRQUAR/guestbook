/**
 * Created by Qouer on 04.07.2016.
 */
var ajaxUrl = 'ajax/messages';

$("document").ready(function() {
    $.ajax({
        type: "GET",
        url: ajaxUrl,
        success: function (messageList) {
            updateMessageList(messageList);
        }
    })
});
//

function updateMessageList(messageList){

    for (i = 0; i < messageList.length; i++){
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

        //Вывод текста сообщения
        var mArea = document.getElementsByClassName('messagesArea');
        mArea[0].appendChild(textareaName);

        var textareaText = document.createElement('textarea');
        $(textareaText).attr('readonly','readonly');
        $(textareaText).attr('disabled','disabled');
        $(textareaText).addClass('textOfMessage');
        textareaText.innerHTML = messageList[i].text;

        $(textareaText).insertAfter(textareaName);

        //Вывод лайков и дизлайков
        //Если страницу просматривает НЕАВТОРИЗИРОВАННЫЙ пользователь
        var like;
        var likeCount;
        var notlike;
        var notlikeCount;
        if (messageList[i].authRate == '-1'){
            //Вставляет иконку лайка
            like = document.createElement('a');
            $(like).addClass('rateButton');
            like.id = 'likeButton';
            like.innerHTML = '&nbsp&nbsp';
            $(like).insertAfter(textareaText);

            //Вставляет количество лайков
            likeCount = document.createElement('a');
            likeCount.innerHTML = messageList[i].plusCount + '&nbsp&nbsp';
            $(likeCount).insertAfter(like);

            //Вставляет иконку дизлайка
            notlike = document.createElement('a');
            $(notlike).addClass('rateButton');
            notlike.id = 'notlikeButton';
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
            like = document.createElement('a');
            $(like).addClass('rateButton');
            like.href = 'main/rate?action=like&message=' + messageList[i].id;
            like.id = 'likeButton';
            like.innerHTML = '&nbsp&nbsp';
            $(like).insertAfter(textareaText);

            //Вставляет количество лайков
            likeCount = document.createElement('a');
            likeCount.innerHTML = messageList[i].plusCount + '&nbsp&nbsp';
            $(likeCount).insertAfter(like);

            //Вставляет иконку дизлайка
            notlike = document.createElement('a');
            $(notlike).addClass('rateButton');
            notlike.href = 'main/rate?action=notlike&message=' + messageList[i].id;
            notlike.id = 'notlikeButton';
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
            like = document.createElement('a');
            $(like).addClass('rateButton');
            like.href = 'main/rate?action=like&message=' + messageList[i].id;
            like.id = 'selectLikeButton';
            like.innerHTML = '&nbsp&nbsp';
            $(like).insertAfter(textareaText);

            //Вставляет количество лайков
            likeCount = document.createElement('a');
            likeCount.innerHTML = messageList[i].plusCount + '&nbsp&nbsp';
            $(likeCount).insertAfter(like);

            //Вставляет иконку дизлайка
            notlike = document.createElement('a');
            $(notlike).addClass('rateButton');
            notlike.href = 'main/rate?action=notlike&message=' + messageList[i].id;
            notlike.id = 'notlikeButton';
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
            like = document.createElement('a');
            $(like).addClass('rateButton');
            like.href = 'main/rate?action=like&message=' + messageList[i].id;
            like.id = 'likeButton';
            like.innerHTML = '&nbsp&nbsp';
            $(like).insertAfter(textareaText);

            //Вставляет количество лайков
            likeCount = document.createElement('a');
            likeCount.innerHTML = messageList[i].plusCount + '&nbsp&nbsp';
            $(likeCount).insertAfter(like);

            //Вставляет иконку дизлайка
            notlike = document.createElement('a');
            $(notlike).addClass('rateButton');
            notlike.href = 'main/rate?action=notlike&message=' + messageList[i].id;
            notlike.id = 'selectNotlikeButton';
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
    }

}

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

function insertNewMessage(message){
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
    var mArea = document.getElementsByClassName('messagesArea');
    mArea[0].appendChild(textareaName);

    var nameOfMessage = document.getElementsByClassName('nameOfMessage');
    $(textareaName).insertBefore(nameOfMessage[0]);

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
        like = document.createElement('a');
        $(like).addClass('rateButton');
        like.id = 'likeButton';
        like.innerHTML = '&nbsp&nbsp';
        $(like).insertAfter(textareaText);

        //Вставляет количество лайков
        likeCount = document.createElement('a');
        likeCount.innerHTML = message.plusCount + '&nbsp&nbsp';
        $(likeCount).insertAfter(like);

        //Вставляет иконку дизлайка
        notlike = document.createElement('a');
        $(notlike).addClass('rateButton');
        notlike.id = 'notlikeButton';
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
        like = document.createElement('a');
        $(like).addClass('rateButton');
        like.href = 'main/rate?action=like&message=' + message.id;
        like.id = 'likeButton';
        like.innerHTML = '&nbsp&nbsp';
        $(like).insertAfter(textareaText);

        //Вставляет количество лайков
        likeCount = document.createElement('a');
        likeCount.innerHTML = message.plusCount + '&nbsp&nbsp';
        $(likeCount).insertAfter(like);

        //Вставляет иконку дизлайка
        notlike = document.createElement('a');
        $(notlike).addClass('rateButton');
        notlike.href = 'main/rate?action=notlike&message=' + message.id;
        notlike.id = 'notlikeButton';
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
        like = document.createElement('a');
        $(like).addClass('rateButton');
        like.href = 'main/rate?action=like&message=' + message.id;
        like.id = 'selectLikeButton';
        like.innerHTML = '&nbsp&nbsp';
        $(like).insertAfter(textareaText);

        //Вставляет количество лайков
        likeCount = document.createElement('a');
        likeCount.innerHTML = message.plusCount + '&nbsp&nbsp';
        $(likeCount).insertAfter(like);

        //Вставляет иконку дизлайка
        notlike = document.createElement('a');
        $(notlike).addClass('rateButton');
        notlike.href = 'main/rate?action=notlike&message=' + message.id;
        notlike.id = 'notlikeButton';
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
        like = document.createElement('a');
        $(like).addClass('rateButton');
        like.href = 'main/rate?action=like&message=' + message.id;
        like.id = 'likeButton';
        like.innerHTML = '&nbsp&nbsp';
        $(like).insertAfter(textareaText);

        //Вставляет количество лайков
        likeCount = document.createElement('a');
        likeCount.innerHTML = message.plusCount + '&nbsp&nbsp';
        $(likeCount).insertAfter(like);

        //Вставляет иконку дизлайка
        notlike = document.createElement('a');
        $(notlike).addClass('rateButton');
        notlike.href = 'main/rate?action=notlike&message=' + message.id;
        notlike.id = 'selectNotlikeButton';
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
}
