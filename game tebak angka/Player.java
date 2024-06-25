import javax.swing.*;

public class Player {
    private String name;

    public Player() {
        this.name = JOptionPane.showInputDialog("Masukkan nama pemain:");
        if (name == null || name.trim().isEmpty()) {
            this.name = "Pemain";
        }
    }

    public String getName() {
        return name;
    }
}
