import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class floorplan {
    private JFrame frame;
    private JPanel floorPlanPanel;

    public floorplan() {
        frame = new JFrame("2D Floor Plan App");
        floorPlanPanel = new JPanel(null);  // Use null layout to allow manual positioning
        floorPlanPanel.setBackground(Color.BLACK);
        JPanel buttonPanel = new JPanel(new FlowLayout());

        // Create a button for adding rooms
        JButton bedroomButton=new JButton("Add Bedroom");
        JButton bathroomButton=new JButton("Add bathroom");
        JButton kitchenButton=new JButton("Add kitchen");
        JButton sofa=new JButton("Add sofa");
        JButton bathtub=new JButton("Add bathtub");
        JButton commode=new JButton("Add Commode");
        JButton bed=new JButton("Add bed");
        JButton livingButton=new JButton("Add Living Room");
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        
        // Set a preferred size to control the width of the button panel
        buttonPanel.setPreferredSize(new Dimension(150, 100));
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(bedroomButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(bathroomButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(kitchenButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(livingButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(sofa);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(bathtub);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(commode);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(bed);
        
            bedroomButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Create a new room with specific color and size
                        Rooms newRoom = new Rooms(Color.ORANGE, 480, 270);
                        floorPlanPanel.add(newRoom);
                        floorPlanPanel.repaint();
                    }
                });
                
                livingButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Create a new room with specific color and size
                        Rooms newRoom = new Rooms(Color.GREEN, 480, 270);
                        floorPlanPanel.add(newRoom);
                        floorPlanPanel.repaint();
                    }
                });
                bathroomButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Create a new room with specific color and size
                        Rooms newRoom = new Rooms(Color.RED, 480, 270);
                        floorPlanPanel.add(newRoom);
                        floorPlanPanel.repaint();
                    }
                });
                kitchenButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Create a new room with specific color and size
                        Rooms newRoom = new Rooms(Color.BLUE, 480, 270);
                        floorPlanPanel.add(newRoom);
                        floorPlanPanel.repaint();
                    }
                });
                sofa.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Load furniture icon (replace with the path to your image)
                        ImageIcon sofaIcon = new ImageIcon("bed.png");
                        
                        // Create a new Furniture item (e.g., sofa)
                        Furniture newSofa = new Furniture(sofaIcon, 100, 100);  // Set size accordingly
                        
                        // Add the furniture to the floorPlanPanel
                        floorPlanPanel.add(newSofa);
                        floorPlanPanel.repaint();
                    }
                });
                bathtub.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Load furniture icon (replace with the path to your image)
                        ImageIcon sofaIcon = new ImageIcon("bathtub.png");
                        
                        // Create a new Furniture item (e.g., sofa)
                        Furniture newSofa = new Furniture(sofaIcon, 150, 100);  // Set size accordingly
                        
                        // Add the furniture to the floorPlanPanel
                        floorPlanPanel.add(newSofa);
                        floorPlanPanel.repaint();
                    }
                });
                commode.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Load furniture icon (replace with the path to your image)
                        ImageIcon sofaIcon = new ImageIcon("commode.png");
                        
                        // Create a new Furniture item (e.g., sofa)
                        Furniture newSofa = new Furniture(sofaIcon, 80, 80);  // Set size accordingly
                        
                        // Add the furniture to the floorPlanPanel
                        floorPlanPanel.add(newSofa);
                        floorPlanPanel.repaint();
                    }
                });
                bed.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Load furniture icon (replace with the path to your image)
                        ImageIcon sofaIcon = new ImageIcon("bed1.png");
                        
                        // Create a new Furniture item (e.g., sofa)
                        Furniture newSofa = new Furniture(sofaIcon, 100, 100);  // Set size accordingly
                        
                        // Add the furniture to the floorPlanPanel
                        floorPlanPanel.add(newSofa);
                        floorPlanPanel.repaint();
                    }
                });

                
            
            

        // Layout the frame
        frame.setLayout(new BorderLayout());
        frame.add(buttonPanel, BorderLayout.WEST);  // Add the button panel to the top
        frame.add(new JScrollPane(floorPlanPanel), BorderLayout.CENTER);  // Add the floor plan panel in the center
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
            }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(floorplan::new);
    }
}
class Furniture extends JLabel {
    private int width, height;
    private Point initialClick;
    public Furniture(ImageIcon icon, int width, int height) {
        super(icon);
        this.width = width;
        this.height = height;
        this.setBounds(50, 50, width, height);
        initializeMouseListeners();
    }
    private void initializeMouseListeners() {
        // MouseListener for detecting mouse press and release
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

                // Determine how much the mouse moved since the initial click
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                // Move the furniture to this position
                int nextX = thisX + xMoved;
                int nextY = thisY + yMoved;

                // Reposition the furniture
                Furniture.this.setLocation(nextX, nextY);
            }
        });
    }

}

 
class Rooms extends JPanel
{
    private int width, height;
    private Point initialClick;  // Stores initial click point
    private boolean dragged;
    Rooms(Color color,int width,int height)
    {
        this.width=width;
        this.height=height;
        this.setBackground(color);
        this.setBounds(50,50,width,height);
        initialmouseListeners();
    }
    public void addFurniture(Furniture furniture) {
        this.add(furniture);
        this.repaint();
    }
    
    private void initialmouseListeners() {
        // MouseListener for detecting mouse press and release
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                dragged = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                dragged = false;
            }
        });

this.addMouseMotionListener(new MouseMotionAdapter()
{
    @Override
    public void mouseDragged(MouseEvent e)
    {
        if(dragged)
        {
            int x=Rooms.this.getLocation().x;
            int y = Rooms.this.getLocation().y;
            int motion_x=e.getX()-initialClick.x;
            int motion_y=e.getY()-initialClick.y;
            int final_x=x+motion_x;
            int final_y=y+motion_y;
            Rooms.this.setLocation(final_x,final_y);
        }
    }
});
}
@Override
protected void paintComponent(Graphics g)
{
    super.paintComponent(g);
    g.drawRect(0, 0, width - 1, height - 1);
}

}