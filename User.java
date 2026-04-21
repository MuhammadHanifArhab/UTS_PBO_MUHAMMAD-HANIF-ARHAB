// CLASS USER (PARENT)
public class User {

    protected String nama;
    protected String password;

    // Constructor
    public User(String nama, String password) {
        this.nama = nama;
        this.password = password;
    }

    // Method login sederhana
    public boolean login(String nama, String password) {
        return this.nama.equals(nama) && this.password.equals(password);
    }

    public String getNama() {
        return nama;
    }
}