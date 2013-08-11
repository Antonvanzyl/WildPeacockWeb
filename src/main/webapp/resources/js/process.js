$(function(){

    $("#submit-button").click(function(){
        
        $("#loading").fadeIn(100).show();
        
        var from = $("#Name").val();
        var to = $("#Email").val();
        
        var data = "Name=" + name + "&Email=" + email;
        
        if(name == ""){
            
            $("#error-name").fadeIn(700).show();
            $("#loading").fadeOut(100).hide();
            
        }else if(email == ""){
            
            $("#error-email").fadeIn(700).show();
            $("#loading").fadeOut(100).hide();
            
         }else{
            
            $.ajax({
                type: "POST",
                url: "sendSub.php",
                data: data,
                success: function(){
                    
                    $("#loading").fadeOut(100).hide();
                    $('#message-sent').fadeIn(500).show();
                    
                }
            });
            
        }
        
    });    
    
});