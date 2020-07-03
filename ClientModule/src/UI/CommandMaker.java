package UI;

public class CommandMaker {

    public static boolean chooseMethod(String nameOfCommand, String arguments){

        if(!AbstractCommand.replies.containsKey(nameOfCommand)){
            return false;
        }

        if(!Terminal.logined) return false;

        switch(nameOfCommand){
            case "clear":{
                //if(!Terminal.logined) return false;
                //Terminal.logined = false;
                AbstractCommand.replies.get("clear").check(nameOfCommand, arguments);
                return true;
            }
            case "execute_script":{
                //if(!Terminal.logined) return false;
                //Terminal.logined = false;
                AbstractCommand.replies.get("execute_script").check(nameOfCommand, arguments);
                return true;
            }
            case "filter_by_venue":{
                AbstractCommand.replies.get("filter_by_venue").check(nameOfCommand, arguments);
                return true;
            }
            case "help":{
                AbstractCommand.replies.get("help").check(nameOfCommand, arguments);
                return true;
            }
            case "info":{
                AbstractCommand.replies.get("info").check(nameOfCommand, arguments);
                return true;
            }
            case "insert":{
                //if(!Terminal.logined) return false;
                //Terminal.logined = false;
                AbstractCommand.replies.get("insert").check(nameOfCommand, arguments);
                return true;
            }
            case "print_descending":{
                AbstractCommand.replies.get("print_descending").check(nameOfCommand, arguments);
                return true;
            }
            case "print_field_descending":{
                AbstractCommand.replies.get("print_field_descending").check(nameOfCommand, arguments);
                return true;
            }
            case "remove_greater_key":{
                //if(!Terminal.logined) return false;
                //Terminal.logined = false;
                AbstractCommand.replies.get("remove_greater_key").check(nameOfCommand, arguments);
                return true;
            }
            case "remove_lower":{
                //if(!Terminal.logined) return false;
                //Terminal.logined = false;
                AbstractCommand.replies.get("remove_lower").check(nameOfCommand, arguments);
                return true;
            }
            case "replace_if_lower":{
                //if(!Terminal.logined) return false;
                //Terminal.logined = false;
                AbstractCommand.replies.get("replace_if_lower").check(nameOfCommand, arguments);
                return true;
            }
            case "show":{
                AbstractCommand.replies.get("show").check(nameOfCommand, arguments);
                return true;
            }
            case "update":{
                //if(!Terminal.logined) return false;
                //Terminal.logined = false;
                AbstractCommand.replies.get("update").check(nameOfCommand, arguments);
                return true;
            }
            case "remove_key":{
                //if(!Terminal.logined) return false;
                //Terminal.logined = false;
                AbstractCommand.replies.get("remove_key").check(nameOfCommand, arguments);
                return true;
            }
            case "login":{
                AbstractCommand.replies.get("login").check(nameOfCommand, arguments);
                return true;
            }
            case "register":{
                AbstractCommand.replies.get("register").check(nameOfCommand, arguments);
                return true;
            }
            case "change_register":{
                //if(!Terminal.logined) return false;
                //Terminal.logined = false;
                AbstractCommand.replies.get("change_register").check(nameOfCommand, arguments);
                return true;
            }
            default:return false;
        }
    }
}
