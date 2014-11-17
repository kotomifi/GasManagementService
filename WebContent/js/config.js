(function($){
    $.URL = {
        "appuser":{
            "add":"rest/userService/addUser",
            "update":"rs/user/update",
            "delete":"rs/user/delete",
            "list":"rest/userService/getAllUser",
            "getId":"rs/user/getIdByName" ,
            "currentUserId": "rs/user/currentUserId"
        },
        "app" :{
            "add" :"rs/app/add" ,
            "list":"rs/app/list",
            "update":"rs/app/update",
            "delete":"rs/app/delete"
        },
        "menu" :{
            "addParentMenu" :"rs/menu/addParentMenu" ,
            "addSonMenu" :"rs/menu/addSonMenu" ,
            /*"list":"rs/menu/list",*/
            "update":"rs/menu/update",
            "fsupdate":"rs/menu/fsupdate",
            "delete":"rs/menu/delete",
            "list":"rs/menu/list",
            "getParentInfoById": "rs/menu/getParentInfoById"  ,
            "getMenuByUserId": "rs/menu/getMenuByUserId"

        }
    }
})(jQuery);