package UI;

import UI.Commandes.*;

public class CommandMaker {

    public static boolean chooseMethod(String nameOfCommand, String arguments){
        switch(nameOfCommand){
            case "clear":{
                if(!Terminal.logined) return false;
                Terminal.logined = false;
                Clear clr = new Clear();
                clr.check(nameOfCommand, arguments);
                return true;
            }
            case "execute_script":{
                if(!Terminal.logined) return false;
                Terminal.logined = false;
                ExecuteScript es = new ExecuteScript();
                es.check(nameOfCommand, arguments);
                return true;
            }
            case "filter_by_venue":{
                FilterByVenue fbv = new FilterByVenue();
                fbv.check(nameOfCommand, arguments);
                return true;
            }
            case "help":{
                Help fbv = new Help();
                fbv.check(nameOfCommand, arguments);
                return true;
            }
            case "info":{
                Info fbv = new Info();
                fbv.check(nameOfCommand, arguments);
                return true;
            }
            case "insert":{
                if(!Terminal.logined) return false;
                Terminal.logined = false;
                Insert fbv = new Insert();
                fbv.check(nameOfCommand, arguments);
                return true;
            }
            case "print_descending":{
                PrintDescending fbv = new PrintDescending();
                fbv.check(nameOfCommand, arguments);
                return true;
            }
            case "print_field_descending":{
                PrintFieldDescending fbv = new PrintFieldDescending();
                fbv.check(nameOfCommand, arguments);
                return true;
            }
            case "remove_greater_key":{
                if(!Terminal.logined) return false;
                Terminal.logined = false;
                RemoveGreaterKey fbv = new RemoveGreaterKey();
                fbv.check(nameOfCommand, arguments);
                return true;
            }
            case "remove_lower":{
                if(!Terminal.logined) return false;
                Terminal.logined = false;
                RemoveLower fbv = new RemoveLower();
                fbv.check(nameOfCommand, arguments);
                return true;
            }
            case "replace_if_lower":{
                if(!Terminal.logined) return false;
                Terminal.logined = false;
                ReplaceIfLower fbv = new ReplaceIfLower();
                fbv.check(nameOfCommand, arguments);
                return true;
            }
            case "show":{
                Show fbv = new Show();
                fbv.check(nameOfCommand, arguments);;
                return true;
            }
            case "update":{
                if(!Terminal.logined) return false;
                Terminal.logined = false;
                Update fbv = new Update();
                fbv.check(nameOfCommand, arguments);
                return true;
            }
            case "remove_key":{
                if(!Terminal.logined) return false;
                Terminal.logined = false;
                RemoveKey fbv = new RemoveKey();
                fbv.check(nameOfCommand, arguments);
                return true;
            }
            case "login":{
                Login fbv = new Login();
                fbv.check(nameOfCommand, arguments);
                return true;
            }
            case "register":{
                Register fbv = new Register();
                fbv.check(nameOfCommand, arguments);
                return true;
            }
            case "change_register":{
                if(!Terminal.logined) return false;
                Terminal.logined = false;
                ChangeRegister fbv = new ChangeRegister();
                fbv.check(nameOfCommand, arguments);
                return true;
            }
            default:return false;
        }
    }
}
