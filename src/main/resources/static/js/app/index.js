let main = {
    init : function () {
        let _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-update').on('click', function () {
            _this.update();
        });
        $("#btn-delete").on('click', function () {
            _this.delete();
        });
    },
    save : function () {
        let data = {
            kinds : $("#kinds").val(),
            place : $("#place").val(),
            address : $("#address").val(),
            explanation : $("#explanation").val()
        };
 
        $.ajax({
            type : 'POST',
            url : '/api/v1/memories',
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },
    update : function () {
       var data = {
        kinds : $("#kinds").val(),
        place : $("#place").val(),
        address : $("#address").val(),
        explanation : $("#explanation").val()
       };

       var id = $("#id").val();

       $.ajax({
           type : 'PUT',
           url : '/api/v1/memories/'+ id,
           dataType : 'json',
           contentType : 'application/json; charset=utf-8',
           data : JSON.stringify(data)
       }).done(function () {
           alert('글이 수정되었습니다.');
           window.location.href = '/';
       }).fail(function (error) {
           alert(JSON.stringify(error));
       });
    },
    delete : function () {
        var id = $("#id").val();
 
        $.ajax({
            type : 'DELETE',
            url : '/api/v1/memories/'+ id,
            dataType : 'json',
            contentType : 'application/json; charset=utf-8'
        }).done(function () {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
 };
 
 main.init();