$("document").ready(function(){
    $('.textOfMessage').autosize();
    $('.nameOfMessage').autosize();
    //$('#messagesArea').autosize();
    $(".button").click(function(){
        var error = false;
        $(".inputField").each(function(){
            var text = $.trim($(this).val());
            if(text == ""){
                error=true;
            }
        })
        if(error == true){
            $(".inputField").each(function(){
                var text = $(this).val("Введите значение");
                if(text == ""){
                    error=true;
                }
            })
            return false;
        }
    });
});