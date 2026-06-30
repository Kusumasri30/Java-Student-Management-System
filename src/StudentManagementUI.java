import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class StudentManagementUI extends JFrame {

    private final StudentManager manager;

    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtAge;
    private JTextField txtCourse;

    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnSearch;
    private JButton btnClear;

    private JTable table;
    private DefaultTableModel model;
    private JLabel statusLabel;

    public StudentManagementUI() {
        manager = new StudentManager();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        setTitle("Student Management System");
        setSize(980, 680);
        setMinimumSize(new Dimension(900, 620));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(12, 12));

        getContentPane().setBackground(new Color(245, 247, 250));

        JLabel title = new JLabel("STUDENT MANAGEMENT SYSTEM", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(new Color(24, 90, 157));
        title.setBorder(BorderFactory.createEmptyBorder(18, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new BorderLayout(12, 12));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));
        contentPanel.setBackground(getContentPane().getBackground());
        contentPanel.add(createFormPanel(), BorderLayout.NORTH);
        contentPanel.add(createTablePanel(), BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);

        add(createBottomPanel(), BorderLayout.SOUTH);

        registerEvents();
        refreshTable();
        setVisible(true);
        SwingUtilities.invokeLater(() -> txtId.requestFocusInWindow());
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(217, 228, 240), 1),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font textFont = new Font("Segoe UI", Font.PLAIN, 14);

        JLabel lblId = new JLabel("Student ID");
        lblId.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblId, gbc);

        txtId = new JTextField(12);
        txtId.setFont(textFont);
        txtId.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 210, 220)),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        gbc.gridx = 1;
        panel.add(txtId, gbc);

        JLabel lblName = new JLabel("Name");
        lblName.setFont(labelFont);
        gbc.gridx = 2;
        panel.add(lblName, gbc);

        txtName = new JTextField(18);
        txtName.setFont(textFont);
        txtName.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 210, 220)),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        gbc.gridx = 3;
        panel.add(txtName, gbc);

        JLabel lblAge = new JLabel("Age");
        lblAge.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblAge, gbc);

        txtAge = new JTextField(12);
        txtAge.setFont(textFont);
        txtAge.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 210, 220)),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        gbc.gridx = 1;
        panel.add(txtAge, gbc);

        JLabel lblCourse = new JLabel("Course");
        lblCourse.setFont(labelFont);
        gbc.gridx = 2;
        panel.add(lblCourse, gbc);

        txtCourse = new JTextField(18);
        txtCourse.setFont(textFont);
        txtCourse.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 210, 220)),
                BorderFactory.createEmptyBorder(6, 8, 6, 8)));
        gbc.gridx = 3;
        panel.add(txtCourse, gbc);

        return panel;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBackground(getContentPane().getBackground());
        panel.setBorder(BorderFactory.createEmptyBorder(4, 16, 10, 16));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));
        buttonPanel.setBackground(getContentPane().getBackground());

        JLabel actionLabel = new JLabel("Actions");
        actionLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        actionLabel.setForeground(new Color(30, 70, 110));
        actionLabel.setBorder(BorderFactory.createEmptyBorder(0, 6, 6, 0));
        panel.add(actionLabel, BorderLayout.NORTH);

        Font buttonFont = new Font("Segoe UI", Font.BOLD, 13);

        btnAdd = new JButton("Add Student");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnSearch = new JButton("Search");
        btnClear = new JButton("Clear");

        JButton[] buttons = {btnAdd, btnUpdate, btnDelete, btnSearch, btnClear};
        for (JButton button : buttons) {
            button.setFont(buttonFont);
            button.setFocusPainted(false);
            button.setForeground(new Color(33, 33, 33));
            button.setPreferredSize(new Dimension(120, 40));
            button.setOpaque(true);
            button.setContentAreaFilled(true);
            button.setBorderPainted(true);
            button.setBorder(BorderFactory.createLineBorder(new Color(200, 210, 220), 1));
            button.setMargin(new Insets(6, 12, 6, 12));
        }

        btnAdd.setBackground(new Color(197, 232, 212));
        btnUpdate.setBackground(new Color(187, 222, 251));
        btnDelete.setBackground(new Color(255, 205, 210));
        btnSearch.setBackground(new Color(255, 224, 178));
        btnClear.setBackground(new Color(207, 216, 220));

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnClear);

        panel.add(buttonPanel, BorderLayout.CENTER);

        statusLabel = new JLabel(" Ready");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        statusLabel.setForeground(new Color(70, 70, 70));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(4, 4, 0, 4));
        panel.add(statusLabel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createTablePanel() {
        String[] columns = {"Student ID", "Name", "Age", "Course"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setSelectionBackground(new Color(187, 222, 251));
        table.setSelectionForeground(Color.BLACK);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setFillsViewportHeight(true);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setReorderingAllowed(false);
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 38));

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setFont(new Font("Segoe UI", Font.BOLD, 14));
        headerRenderer.setBackground(new Color(33, 150, 243));
        headerRenderer.setForeground(Color.WHITE);
        headerRenderer.setOpaque(true);
        header.setDefaultRenderer(headerRenderer);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setShowGrid(true);
        table.setGridColor(new Color(210, 225, 240));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(4, 4, 4, 4)));

        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));

        JLabel tableLabel = new JLabel("Student List");
        tableLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tableLabel.setForeground(new Color(30, 70, 110));
        tableLabel.setBorder(BorderFactory.createEmptyBorder(0, 4, 6, 0));
        panel.add(tableLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    txtId.setText(model.getValueAt(row, 0).toString());
                    txtName.setText(model.getValueAt(row, 1).toString());
                    txtAge.setText(model.getValueAt(row, 2).toString());
                    txtCourse.setText(model.getValueAt(row, 3).toString());
                }
            }
        });

        return panel;
    }

    private void registerEvents() {
        btnAdd.addActionListener(e -> {
            try {
                if (txtId.getText().trim().isEmpty() || txtName.getText().trim().isEmpty()
                        || txtAge.getText().trim().isEmpty() || txtCourse.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                    return;
                }

                int id = Integer.parseInt(txtId.getText().trim());
                String name = txtName.getText().trim();
                int age = Integer.parseInt(txtAge.getText().trim());
                String course = txtCourse.getText().trim();

                Student student = new Student(id, name, age, course);
                if (manager.addStudent(student)) {
                    refreshTable();
                    clearFields();
                    statusLabel.setText(" Student added successfully");
                } else {
                    JOptionPane.showMessageDialog(this, "Student ID already exists.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric values for ID and age.");
            }
        });

        btnSearch.addActionListener(e -> {
            try {
                if (txtId.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter a student ID to search.");
                    return;
                }

                int id = Integer.parseInt(txtId.getText().trim());
                Student s = manager.searchStudent(id);

                if (s != null) {
                    txtName.setText(s.getName());
                    txtAge.setText(String.valueOf(s.getAge()));
                    txtCourse.setText(s.getCourse());
                    statusLabel.setText(" Student found");
                } else {
                    JOptionPane.showMessageDialog(this, "Student not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid student ID.");
            }
        });

        btnUpdate.addActionListener(e -> {
            try {
                if (txtId.getText().trim().isEmpty() || txtName.getText().trim().isEmpty()
                        || txtAge.getText().trim().isEmpty() || txtCourse.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields before updating.");
                    return;
                }

                int id = Integer.parseInt(txtId.getText().trim());
                String name = txtName.getText().trim();
                int age = Integer.parseInt(txtAge.getText().trim());
                String course = txtCourse.getText().trim();

                if (manager.updateStudent(id, name, age, course)) {
                    refreshTable();
                    clearFields();
                    statusLabel.setText(" Student updated successfully");
                } else {
                    JOptionPane.showMessageDialog(this, "Student not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric values for ID and age.");
            }
        });

        btnDelete.addActionListener(e -> {
            try {
                if (txtId.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Enter a student ID to delete.");
                    return;
                }

                int id = Integer.parseInt(txtId.getText().trim());
                int choice = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to delete this student?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    if (manager.deleteStudent(id)) {
                        refreshTable();
                        clearFields();
                        statusLabel.setText(" Student deleted successfully");
                    } else {
                        JOptionPane.showMessageDialog(this, "Student not found.");
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid student ID.");
            }
        });

        btnClear.addActionListener(e -> clearFields());
    }

    private void refreshTable() {
        model.setRowCount(0);
        for (Student s : manager.getAllStudents()) {
            Object[] row = {s.getId(), s.getName(), s.getAge(), s.getCourse()};
            model.addRow(row);
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAge.setText("");
        txtCourse.setText("");
        table.clearSelection();
        txtId.requestFocus();
        statusLabel.setText(" Ready");
    }
}