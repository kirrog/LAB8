package DataBase;

import java.io.Serializable;

public class TicketOwner implements Serializable {

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte[] getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setId(int id){this.id = id;}

    private int id = 0;
    private String name;
    private byte[] password;
    private String mail;

    public TicketOwner(String n, byte[] p, String m) {
        name = n;
        password = p;
        mail = m;
    }

    public TicketOwner(int i, String n, byte[] p, String m) {
        id = i;
        name = n;
        password = p;
        mail = m;
    }

    @Override
    public String toString() {
        return (id + " " + name + " " + password.toString() + " " + mail);
    }
}
