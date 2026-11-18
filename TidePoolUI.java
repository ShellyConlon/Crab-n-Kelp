import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class TidePoolUI extends JFrame {
    private TidePool pool;
    private JPanel gridPanel;
    private JTextField crabsInput;
    private JTextField kelpInput;
    private JLabel statusLabel;
    private final int GRID_SIZE = 8;

    // NEW: ImageIcon fields for visual graphics
    private ImageIcon kelpIcon;
    private ImageIcon crabIcon;
    private ImageIcon deadIcon;

    public TidePoolUI() {
        setTitle("Tide Pool Simulation - Crabs & Kelp");
        setSize(650, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // NEW: Load the 3 images from the Eclipse project folder
        kelpIcon = loadIcon("kelp.png");
        crabIcon = loadIcon("crab.png");
        deadIcon = loadIcon("dead.png");

        // Top control bar for user starting conditions
        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Start Crabs:"));
        crabsInput = new JTextField("5", 3);
        controlPanel.add(crabsInput);

        controlPanel.add(new JLabel("Kelp Spacing (1-4):"));
        kelpInput = new JTextField("2", 3);
        controlPanel.add(kelpInput);

        JButton startButton = new JButton("Start / Reset");
        JButton stepButton = new JButton("Next Step");
        controlPanel.add(startButton);
        controlPanel.add(stepButton);
        add(controlPanel, BorderLayout.NORTH);

        // Center 2D Grid Panel
        gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE, 2, 2));
        add(gridPanel, BorderLayout.CENTER);

        // Bottom status bar with key
        statusLabel = new JLabel(" Key: [Green = Kelp]  [Orange = Crab]  [Gray = Dead Crab]  [Blue = Water]");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        add(statusLabel, BorderLayout.SOUTH);

        // Button listeners
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetSimulation();
            }
        });

        stepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pool.stepSimulation();
                drawGrid();
            }
        });

        resetSimulation();
        setVisible(true);
    }

    // NEW: Helper method to load and scale images to fit grid squares
    private ImageIcon loadIcon(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            return null; // Uses text fallback if image is not found
        }
        ImageIcon rawIcon = new ImageIcon(fileName);
        Image scaled = rawIcon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    private void resetSimulation() {
        int numCrabs = Integer.parseInt(crabsInput.getText().trim());
        int spacing = Integer.parseInt(kelpInput.getText().trim());
        if (spacing < 1) {
            spacing = 1;
        }
        pool = new TidePool(GRID_SIZE, numCrabs, spacing);
        drawGrid();
    }

    private void drawGrid() {
        gridPanel.removeAll();

        for (int r = 0; r < pool.size; r++) {
            for (int c = 0; c < pool.size; c++) {
                JLabel cell = new JLabel("", SwingConstants.CENTER);
                cell.setOpaque(true);
                cell.setFont(new Font("SansSerif", Font.BOLD, 10));
                // Place text directly underneath the image icon
                cell.setHorizontalTextPosition(SwingConstants.CENTER);
                cell.setVerticalTextPosition(SwingConstants.BOTTOM);

                // 1. Draw Kelp or Water tile
                if (pool.kelpGrid[r][c]) {
                    cell.setBackground(new Color(102, 204, 102)); // Green Kelp
                    if (kelpIcon != null) {
                        cell.setIcon(kelpIcon);
                    } else {
                        cell.setText("KELP");
                    }
                } else {
                    cell.setBackground(new Color(179, 229, 252)); // Blue Water
                    cell.setText("");
                }

                // 2. Draw Crab if one is on this tile
                for (int i = 0; i < pool.crabs.size(); i++) {
                    Crab crab = pool.crabs.get(i);
                    if (crab.row == r && crab.col == c) {
                        if (crab.alive) {
                            cell.setBackground(new Color(255, 167, 38)); // Orange Live Crab
                            if (crabIcon != null) {
                                cell.setIcon(crabIcon);
                            }
                            cell.setText(crab.status + " (" + crab.energy + ")");
                        } else {
                            cell.setBackground(new Color(189, 189, 189)); // Gray Dead Crab
                            if (deadIcon != null) {
                                cell.setIcon(deadIcon);
                            }
                            cell.setText("DEAD");
                        }
                    }
                }
                gridPanel.add(cell);
            }
        }

        statusLabel.setText(" Step: " + pool.stepCount + " | Key: [Green = Kelp] [Orange = Live Crab (Energy)] [Gray = Dead]");
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    public static void main(String[] args) {
        new TidePoolUI();
    }
}
