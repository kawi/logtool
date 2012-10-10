$(function() {
    $(".radio").live('change', function(button) {
        $.ajax({
            url : "./setLogLevel",
            data : {
                pack : button.target.name,
                level : button.target.value
            }
        });

    });

    function getRadioButton(pack, level) {
        var id = level + '.' + pack;
        return $('<div class="radio"/>').html(
                '<label for="' + id + '">' + level
                        + '</label><input type="radio" name="' + pack
                        + '" id="' + id + '" value="' + level + '">');
    }

    function log(pack) {
        var divExists = document.getElementById(pack)!=undefined;
        if(divExists) {
           return; 
        }
        var group = $('<div class="radioGroup" />');
        getRadioButton(pack, "trace").prependTo(group);
        getRadioButton(pack, "debug").prependTo(group);
        getRadioButton(pack, "info").prependTo(group);
        getRadioButton(pack, "warn").prependTo(group);
        getRadioButton(pack, "error").prependTo(group);

        group.prependTo("#log");
        var row = $("<div id=\"" +pack + "\" />");
        
        $("<div class=\"gradient\" />").appendTo(row);
        $("<div class=\"packagelabel\" />").text(pack).appendTo(row);
        group.appendTo(row);
        row.prependTo("#log");
        $("#log").scrollTop(0);
    }
    
    function addPackage(selected) { 
        $.ajax({
            url : "./checkPackage",
            dataType : "text",
            data : {
                input : selected
            },
            success : function(data) {
                if(data != "") {
                    log(data);
                }
            }
        });
    }
    $(document).ready(function() {
        $("#packagefinder").on("keyup", function(e) {
            if (e.which === 13) {
               addPackage(this.value)
            }
        });

        var datalist = document.getElementById("packages");
        datalist.addEventListener("click", function() {
            alert("talking!");
        }, false);
    
        $("button").click(function() {
            $.ajax({
                url : "./doLog",
                data : {
                    level : this.value
                }
            });
        });
    });
});
