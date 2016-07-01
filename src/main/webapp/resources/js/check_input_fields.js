$("document").ready(function(){
    $('.textOfMessage').autosize();
    $('.nameOfMessage').autosize();
    $(".button").click(function(){
        var error=false;
        $(".inputField").each(function(){
            var text = $.trim($(this).val());
            if(text == ""){
                error=true;
            }
        })
        if(error===true){
            alert("Заполните все обязательные поля");
            return false;
        }
    });
});