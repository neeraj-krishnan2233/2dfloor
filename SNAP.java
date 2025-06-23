import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.File;
import javax.sound.sampled.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
public class SNAP implements Runnable {
    private JFrame frame;
    private JPanel floorPlanPanel;

    public SNAP() {
        frame = new JFrame("SNAP - 2D Floor Plan App");
        floorPlanPanel = new JPanel(null);  
        floorPlanPanel.setBackground(Color.BLACK);
        try {
            
            Image icon = ImageIO.read(new File("snap.png")); 
            frame.setIconImage(icon); 
        } catch (IOException e) {
            System.out.println("Icon image not found!");
        }
        ControlPanel controlPanel = new ControlPanel(floorPlanPanel);

        
        frame.setLayout(new BorderLayout());
        frame.add(controlPanel.getPanel(), BorderLayout.WEST);  
        frame.add(new JScrollPane(floorPlanPanel), BorderLayout.CENTER);  
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void run(){
        SwingUtilities.invokeLater(SNAP::new);
    }
}

class ControlPanel {
    private JPanel buttonPanel;
    private List<JComponent> layoutComponents;

    public ControlPanel(JPanel floorPlanPanel) {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setPreferredSize(new Dimension(150, 100));
        buttonPanel.setBackground(Color.darkGray);
        layoutComponents = new ArrayList<>();

        
        JButton bedroomButton = createRoomButton("Add Bedroom", Color.PINK, floorPlanPanel);
        bedroomButton.setBackground(Color.BLACK);
        bedroomButton.setForeground(Color.GREEN);
        JButton bathroomButton = createRoomButton("Add Bathroom", Color.RED, floorPlanPanel);
        bathroomButton.setBackground(Color.BLACK);
        bathroomButton.setForeground(Color.GREEN);
        JButton kitchenButton = createRoomButton("Add Kitchen", Color.BLUE, floorPlanPanel);
        kitchenButton.setBackground(Color.BLACK);
        kitchenButton.setForeground(Color.GREEN);
        JButton livingButton = createRoomButton("Add Living Room", Color.ORANGE, floorPlanPanel);
        livingButton.setBackground(Color.BLACK);
        livingButton.setForeground(Color.GREEN);
        buttonPanel.add(createRigidArea());
        buttonPanel.add(bedroomButton);
        buttonPanel.add(createRigidArea());
        buttonPanel.add(bathroomButton);
        buttonPanel.add(createRigidArea());
        buttonPanel.add(kitchenButton);
        buttonPanel.add(createRigidArea());
        buttonPanel.add(livingButton);
        buttonPanel.add(createRigidArea());
        JButton doorButton = createFurnitureButton("Add Door", "Dooropen.png", floorPlanPanel);
        doorButton.setBackground(Color.BLACK);
        doorButton.setForeground(Color.GREEN);
        JButton sofaButton = createFurnitureButton("Add Sofa", "sofa.png", floorPlanPanel);
        sofaButton.setBackground(Color.BLACK);
        sofaButton.setForeground(Color.GREEN);
        JButton bathtubButton = createFurnitureButton("Add Bathtub", "bathtub.png", floorPlanPanel);
        bathtubButton.setBackground(Color.BLACK);
        bathtubButton.setForeground(Color.GREEN);
        JButton commodeButton = createFurnitureButton("Add Commode", "poop.png", floorPlanPanel);
        commodeButton.setBackground(Color.BLACK);
        commodeButton.setForeground(Color.GREEN);
        JButton bedButton = createFurnitureButton("Add Bed", "bed.png", floorPlanPanel);
        bedButton.setBackground(Color.BLACK);
        bedButton.setForeground(Color.GREEN);
        JButton diningtableButton = createFurnitureButton("Add Dining table", "d table.png", floorPlanPanel);
        diningtableButton.setBackground(Color.BLACK);
        diningtableButton.setForeground(Color.GREEN);
        JButton TV = createFurnitureButton("Add TV", "TV.png", floorPlanPanel);
        TV.setBackground(Color.BLACK);
        TV.setForeground(Color.GREEN);
        JButton cb = createFurnitureButton("Add Cupboard", "cb.png", floorPlanPanel);
        cb.setBackground(Color.BLACK);
        cb.setForeground(Color.GREEN);
        JButton kitchen = createFurnitureButton("Add Stove", "kitchen.png", floorPlanPanel);
        kitchen.setBackground(Color.BLACK);
        kitchen.setForeground(Color.GREEN);
        JButton piano = createFurnitureButton("Add -----", "piano.png", floorPlanPanel);
        piano.setBackground(Color.BLACK);
        piano.setForeground(Color.GREEN);
        

        buttonPanel.add(doorButton);
        buttonPanel.add(createRigidArea());
        buttonPanel.add(sofaButton);
        buttonPanel.add(createRigidArea());
        buttonPanel.add(bathtubButton);
        buttonPanel.add(createRigidArea());
        buttonPanel.add(commodeButton);
        buttonPanel.add(createRigidArea());
        buttonPanel.add(bedButton);
        buttonPanel.add(createRigidArea());
        buttonPanel.add(diningtableButton);
        buttonPanel.add(createRigidArea());
        buttonPanel.add(TV);
        buttonPanel.add(createRigidArea());
        buttonPanel.add(cb);
        buttonPanel.add(createRigidArea());
        buttonPanel.add(kitchen);
        buttonPanel.add(createRigidArea());
        buttonPanel.add(piano);
        
        buttonPanel.add(createRigidArea());
        JButton saveAsPngButton = new JButton("Save as PNG");
        saveAsPngButton.setBackground(Color.BLACK);
        saveAsPngButton.setForeground(Color.GREEN);
        saveAsPngButton.addActionListener(e -> saveAsPng(floorPlanPanel));
        buttonPanel.add(createRigidArea());
        buttonPanel.add(saveAsPngButton);
        JButton loadButton = new JButton("Load Layout");
        loadButton.setBackground(Color.BLACK);
        loadButton.setForeground(Color.GREEN);
        loadButton.addActionListener(e -> loadLayout(floorPlanPanel));
        buttonPanel.add(createRigidArea());
        buttonPanel.add(loadButton);
    }
    private void saveAsPng(JPanel floorPlanPanel) {
        BufferedImage image = new BufferedImage(floorPlanPanel.getWidth(), floorPlanPanel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        floorPlanPanel.paint(g2d);
        g2d.dispose();
        try {
            ImageIO.write(image, "png", new File("floorplan.png"));
            JOptionPane.showMessageDialog(null, "Layout saved as PNG!", "Save as PNG", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error saving as PNG: " + ex.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Load layout method
    private void loadLayout(JPanel floorPlanPanel) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Layout Files", "txt"));
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                floorPlanPanel.removeAll();
                layoutComponents.clear();
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts[0].equals("Room")) {
                        int x = Integer.parseInt(parts[1]);
                        int y = Integer.parseInt(parts[2]);
                        int width = Integer.parseInt(parts[3]);
                        int height = Integer.parseInt(parts[4]);
                        Color color = new Color(Integer.parseInt(parts[5]));
                        Rooms room = new Rooms(color, width, height, layoutComponents, floorPlanPanel);
                        room.setBounds(x, y, width, height);
                        floorPlanPanel.add(room);
                        layoutComponents.add(room);
                    } else if (parts[0].equals("Furniture")) {
                        int x = Integer.parseInt(parts[1]);
                        int y = Integer.parseInt(parts[2]);
                        int width = Integer.parseInt(parts[3]);
                        int height = Integer.parseInt(parts[4]);
                        Furniture furniture = new Furniture(null, width, height, layoutComponents, floorPlanPanel);
                        furniture.setBounds(x, y, width, height);
                        floorPlanPanel.add(furniture);
                        layoutComponents.add(furniture);
                    }
                }
                floorPlanPanel.revalidate();
                floorPlanPanel.repaint();
                JOptionPane.showMessageDialog(null, "Layout loaded successfully!", "Load Layout", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error loading layout: " + ex.getMessage(), "Load Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private JButton createRoomButton(String label, Color color, JPanel floorPlanPanel) {
        JButton button = new JButton(label);
        button.addActionListener(e -> {
            Dimension dimension = getDimensionsFromUser("Enter dimensions for " + label);
            if (dimension != null) {
                // Calculate position for the new room
                int x = 10; // Default X position
                int y = 10; // Default Y position
    
                if (!layoutComponents.isEmpty()) {
                    JComponent lastComponent = layoutComponents.get(layoutComponents.size() - 1);
                    java.awt.Rectangle lastBounds = lastComponent.getBounds();
                    x = lastBounds.x + lastBounds.width + 10; // Place to the right with a margin
                    y = lastBounds.y; // Align vertically with the last room
                }
    
                Rooms newRoom = new Rooms(color, dimension.width, dimension.height, layoutComponents, floorPlanPanel);
                newRoom.setBounds(x, y, dimension.width, dimension.height);
                floorPlanPanel.add(newRoom);
                floorPlanPanel.setComponentZOrder(newRoom, floorPlanPanel.getComponentCount() - 1);
                layoutComponents.add(newRoom);
                floorPlanPanel.repaint();
            }
        });
        return button;
    }
    private JButton createFurnitureButton(String label, String iconPath, JPanel floorPlanPanel) {
        JButton button = new JButton(label);
        button.addActionListener(e -> {
            Dimension dimension = getDimensionsFromUser("Enter dimensions for " + label);
            if (dimension != null) {
                ImageIcon icon = new ImageIcon(iconPath);
                Furniture furniture = new Furniture(icon, dimension.width, dimension.height, layoutComponents, floorPlanPanel);
                floorPlanPanel.add(furniture);
                floorPlanPanel.setComponentZOrder(furniture, 0);
                layoutComponents.add(furniture);
                floorPlanPanel.repaint();
    
                // Play music if the piano is added
                if (label.equals("Add -----")) {  // Replace "Add -----" with your button label for the piano
                    playPianoMusic("pianomusic.wav");
                }
            }
        });
        return button;
    }
    
    private void playPianoMusic(String audioFilePath) {
        try {
            File audioFile = new File(audioFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error playing music: " + ex.getMessage(), "Audio Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Dimension getDimensionsFromUser(String message) {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JTextField widthField = new JTextField();
        JTextField heightField = new JTextField();

        panel.add(new JLabel("Width:"));
        panel.add(widthField);
        panel.add(new JLabel("Height:"));
        panel.add(heightField);

        int result = JOptionPane.showConfirmDialog(null, panel, message, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int width = Integer.parseInt(widthField.getText().trim());
                int height = Integer.parseInt(heightField.getText().trim());
                if (width > 0 && height > 0) {
                    return new Dimension(width, height);
                } else {
                    JOptionPane.showMessageDialog(null, "Dimensions must be positive integers.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numbers for dimensions.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    private Component createRigidArea() {
        return Box.createRigidArea(new Dimension(0, 15));
    }

    private void saveLayout() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("layout.txt"))) {
            for (JComponent comp : layoutComponents) {
                if (comp instanceof Rooms) {
                    Rooms room = (Rooms) comp;
                    writer.write("Room," + room.getBounds().x + "," + room.getBounds().y + "," + room.getWidth() + "," + room.getHeight() + "," + room.getBackground().getRGB());
                } else if (comp instanceof Furniture) {
                    Furniture furniture = (Furniture) comp;
                    writer.write("Furniture," + furniture.getBounds().x + "," + furniture.getBounds().y + "," + furniture.getWidth() + "," + furniture.getHeight());
                }
                writer.newLine();
            }
            JOptionPane.showMessageDialog(null, "Layout saved successfully!", "Save Layout", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error saving layout: " + ex.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public JPanel getPanel() {
        return buttonPanel;
    }
}

class Furniture extends JLabel {
    private int width, height;
    private Point initialClick;
    private List<JComponent> layoutComponents;
    private JPanel parentPanel;
    private int rotationAngle = 0; // Rotation state in degrees

    public Furniture(ImageIcon icon, int width, int height, List<JComponent> layoutComponents, JPanel parentPanel) {
        super(icon);
        this.width = width;
        this.height = height;
        this.layoutComponents = layoutComponents;
        this.parentPanel = parentPanel;
        this.setBounds(50, 50, width, height);
        initializeMouseListeners();
        initializeContextMenu();
    }

    private void initializeMouseListeners() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int thisX = Furniture.this.getLocation().x;
                int thisY = Furniture.this.getLocation().y;

                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                int nextX = thisX + xMoved;
                int nextY = thisY + yMoved;

                Furniture.this.setLocation(nextX, nextY);
            }
        });
    }

    private void initializeContextMenu() {
        JPopupMenu contextMenu = new JPopupMenu();

        // Rotate Option
        JMenuItem rotateItem = new JMenuItem("Rotate 90°");
        rotateItem.addActionListener(e -> rotate());
        contextMenu.add(rotateItem);

        // Remove Option
        JMenuItem removeItem = new JMenuItem("Remove");
        removeItem.addActionListener(e -> remove());
        contextMenu.add(removeItem);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    contextMenu.show(Furniture.this, e.getX(), e.getY());
                }
            }
        });
    }

    private void rotate() {
        rotationAngle = (rotationAngle + 90) % 360;
        this.setBounds(getX(), getY(), height, width); // Swap width and height
        parentPanel.repaint();
    }

    private void remove() {
        parentPanel.remove(this);
        layoutComponents.remove(this);
        parentPanel.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.rotate(Math.toRadians(rotationAngle), getWidth() / 2.0, getHeight() / 2.0);
        g2d.drawImage(((ImageIcon) getIcon()).getImage(), 0, 0, getWidth(), getHeight(), null);
        g2d.dispose();
    }
}
class Rooms extends JPanel {
    private int width, height;
    private Point initialClick;
    private List<JComponent> layoutComponents;
    private JPanel parentPanel;

    public Rooms(Color color, int width, int height, List<JComponent> layoutComponents, JPanel parentPanel) {
        this.width = width;
        this.height = height;
        this.layoutComponents = layoutComponents;
        this.parentPanel = parentPanel;
        this.setBackground(color);
        this.setBounds(50, 50, width, height);
        initializeMouseListeners();
        initializeContextMenu();
    }

    private void initializeMouseListeners() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int thisX = Rooms.this.getLocation().x;
                int thisY = Rooms.this.getLocation().y;

                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                int nextX = thisX + xMoved;
                int nextY = thisY + yMoved;

                Rooms.this.setLocation(nextX, nextY);

                if (!isValidPosition(Rooms.this)) {
                    Rooms.this.setLocation(thisX, thisY);
                }
            }
        });
    }
    private void initializeContextMenu() {
        JPopupMenu contextMenu = new JPopupMenu();

        // Rotate Option
        JMenuItem rotateItem = new JMenuItem("Rotate 90°");
        rotateItem.addActionListener(e -> rotate());
        contextMenu.add(rotateItem);

        // Remove Option
        JMenuItem removeItem = new JMenuItem("Remove");
        removeItem.addActionListener(e -> remove());
        contextMenu.add(removeItem);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    contextMenu.show(Rooms.this, e.getX(), e.getY());
                }
            }
        });
    }

    private void rotate() {
        int tempWidth = getWidth();
        int tempHeight = getHeight();
        setSize(tempHeight, tempWidth);
        revalidate();
        repaint();
    }

    private void remove() {
        layoutComponents.remove(this);
        parentPanel.remove(this);
        parentPanel.repaint();
    }

    private boolean isValidPosition(JComponent comp) {
        for (JComponent other : layoutComponents) {
            if (other != comp && comp.getBounds().intersects(other.getBounds())) {
                return false;
            }
        }
        java.awt.Rectangle bounds = new java.awt.Rectangle(comp.getBounds());
        return bounds.x >= 0 && bounds.y >= 0 && bounds.x + bounds.width <= parentPanel.getWidth() && bounds.y + bounds.height <= parentPanel.getHeight();
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GREEN);
        g2d.setStroke(new BasicStroke(7)); // Wall thickness
        g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1); // Draw border
    }
}
