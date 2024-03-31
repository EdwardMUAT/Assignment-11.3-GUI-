import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class VirtualPetGUI extends JFrame implements ActionListener {
    private JLabel petLabel, statusLabel;
    private JButton feedButton, playButton, groomButton;
    private int hunger, happiness, cleanliness;

    public VirtualPetGUI() {
        setTitle("Virtual Pet");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize attributes
        hunger = 50;
        happiness = 50;
        cleanliness = 50;

        // Create pet label
        petLabel = new JLabel();
        petLabel.setHorizontalAlignment(SwingConstants.CENTER);
        petLabel.setIcon(new ImageIcon("virtualpet.jpeg"));
        add(petLabel, BorderLayout.CENTER);

        // Create status label
        statusLabel = new JLabel();
        updateStatus();
        add(statusLabel, BorderLayout.SOUTH);

        // Create buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        feedButton = new JButton("Feed");
        feedButton.addActionListener(this);
        buttonPanel.add(feedButton);

        playButton = new JButton("Play");
        playButton.addActionListener(this);
        buttonPanel.add(playButton);

        groomButton = new JButton("Groom");
        groomButton.addActionListener(this);
        buttonPanel.add(groomButton);

        add(buttonPanel, BorderLayout.NORTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == feedButton) {
            hunger += 10;
            cleanliness -= 5;
        } else if (e.getSource() == playButton) {
            happiness += 10;
            hunger -= 5;
        } else if (e.getSource() == groomButton) {
            cleanliness += 15;
            happiness += 5;
        }

        // Ensure attributes stay within bounds
        hunger = Math.max(0, Math.min(100, hunger));
        happiness = Math.max(0, Math.min(100, happiness));
        cleanliness = Math.max(0, Math.min(100, cleanliness));

        // Update status label
        updateStatus();

        // Check if pet has died
        if (hunger <= 0 || happiness <= 0 || cleanliness <= 0) {
            JOptionPane.showMessageDialog(this, "Your pet has died!");
            System.exit(0);
        }
    }

    private void updateStatus() {
        statusLabel.setText("Hunger: " + hunger + ", Happiness: " + happiness + ", Cleanliness: " + cleanliness);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VirtualPetGUI petGUI = new VirtualPetGUI();
            petGUI.setVisible(true);
        });
    }
}
