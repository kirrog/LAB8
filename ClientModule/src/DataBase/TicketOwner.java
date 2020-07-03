package DataBase;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class TicketOwner implements Externalizable {

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] bytes){
        password = bytes;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setId(int id){this.id = id;}

    public String getSalt(){
        return salt;
    }

    private int id;
    private String name;
    private byte[] password;
    private String mail;
    private String salt;

    private static final long serialVersionUID = 1L;

    public TicketOwner(String n, byte[] p, String m) {
        name = n;
        password = p;
        mail = m;
        salt = CriptoMaker.generateSalt(n);
    }

    public TicketOwner() {

    }

    public TicketOwner(int i, String n, byte[] p, String m) {
        id = i;
        name = n;
        password = p;
        mail = m;
    }

    public TicketOwner(int i, String n, byte[] p, String m, String s) {
        id = i;
        name = n;
        password = p;
        mail = m;
        salt = s;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(id);
        out.writeObject(name);
        out.writeObject(mail);
        out.writeObject(password);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
       id = (Integer) in.readObject();
       name = (String) in.readObject();
       mail = (String) in.readObject();
       password = (byte[]) in.readObject();
    }

    public int compareTo(TicketOwner sec) {
        return this.getName().compareTo(sec.getName());
    }
}
