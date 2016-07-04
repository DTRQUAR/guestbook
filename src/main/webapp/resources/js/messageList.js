/**
 * Created by Qouer on 04.07.2016.
 */
var ajaxUrl = 'ajax/messages';
var message;

$("document").ready(function() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        success: function (data) {
            message = data;
            //$("#nameField").val(message[0].id);
            messageList(data);
        }
    })
});
//

function messageList(data){

    var textarea = document.createElement('textarea');
    $(textarea).attr('readonly','readonly');
    $(textarea).attr('disabled','disabled');
    textarea.innerHTML = message[0].dateTime;
    alert(message[0].name);
    $("#nameField").val(message[0].id);
    var mArea = document.getElementsByClassName('messagesArea');
    mArea[0].appendChild(textarea);

    //$('#messageAreaId').appendChild(textarea);
    //$(".messagesArea").appendChild(textarea);
    //$(".messagesArea").append(textarea);
    //textarea.innerHTML = data[0].name;
    //textarea.className("nameOfMessage");
}
