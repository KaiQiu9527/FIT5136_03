public class AdminMain {
    static User user;
    static UI ui = new UI();

    public AdminMain(User user){
        this.user = user;
    }

    public static void main(String[] args) {
        ui.adminMainMenu(user.getUsername());
    }

}
