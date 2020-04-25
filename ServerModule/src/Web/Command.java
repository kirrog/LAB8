package Web;

import java.io.Serializable;

public class Command implements Serializable {
    public String getNameOfCommand() {
        return nameOfCommand;
    }

    public void setNameOfCommand(String nameOfCommand) {
        this.nameOfCommand = nameOfCommand;
    }

    public String getFirstArgument() {
        return firstArgument;
    }

    public void setFirstArgument(String firstArgument) {
        this.firstArgument = firstArgument;
    }

    public Number getSecondArgument() {
        return secondArgument;
    }

    public void setSecondArgument(Number secondArgument) {
        this.secondArgument = secondArgument;
    }

    public Serializable getThirdArgument() {
        return thirdArgument;
    }

    public void setThirdArgument(Serializable thirdArgument) {
        this.thirdArgument = thirdArgument;
    }

    private String nameOfCommand;
    private String firstArgument;
    private Number secondArgument;
    private Serializable thirdArgument;

    @Override
    public String toString() {
        String str = getNameOfCommand() + getFirstArgument() + getSecondArgument() + getThirdArgument();
        return str;
    }
}
