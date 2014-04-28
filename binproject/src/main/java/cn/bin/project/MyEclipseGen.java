package cn.bin.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * @author yibu
 * 
 */
public class MyEclipseGen extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4682532148887938053L;
	private static final String LL = "Decompiling this copyrighted software is a violation of both your license agreement and the Digital Millenium Copyright Act of 1998 (http://www.loc.gov/copyright/legislation/dmca.pdf). Under section 1204 of the DMCA, penalties range up to a $500,000 fine or up to five years imprisonment for a first offense. Think about it; pay for a license, avoid prosecution, and feel better about yourself.";
    public JLabel title, lkey, lvlues;
    public JTextField keys, vlues;
    public JButton enter, exit;

    public String getSerial(String userId, String licenseNum) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.add(1, 3);
        cal.add(6, -1);
        java.text.NumberFormat nf = new java.text.DecimalFormat("000");
        licenseNum = nf.format(Integer.valueOf(licenseNum));
        String verTime = new StringBuilder("-").append(
                new java.text.SimpleDateFormat("yyMMdd").format(cal.getTime()))
                .append("0").toString();
        String type = "YE3MP-";
        String need = new StringBuilder(userId.substring(0, 1)).append(type)
                .append("300").append(licenseNum).append(verTime).toString();
        String dx = new StringBuilder(need).append(LL).append(userId)
                .toString();
        int suf = this.decode(dx);
        String code = new StringBuilder(need).append(String.valueOf(suf))
                .toString();
        return this.change(code);
    }

    private int decode(String s) {
        int i;
        char[] ac;
        int j;
        int k;
        i = 0;
        ac = s.toCharArray();
        j = 0;
        k = ac.length;
        while (j < k) {
            i = (31 * i) + ac[j];
            j++;
        }
        return Math.abs(i);
    }

    private String change(String s) {
        byte[] abyte0;
        char[] ac;
        int i;
        int k;
        int j;
        abyte0 = s.getBytes();
        ac = new char[s.length()];
        i = 0;
        k = abyte0.length;
        while (i < k) {
            j = abyte0[i];
            if ((j >= 48) && (j <= 57)) {
                j = (((j - 48) + 5) % 10) + 48;
            } else if ((j >= 65) && (j <= 90)) {
                j = (((j - 65) + 13) % 26) + 65;
            } else if ((j >= 97) && (j <= 122)) {
                j = (((j - 97) + 13) % 26) + 97;
            }
            ac[i] = (char) j;
            i++;
        }
        return String.valueOf(ac);
    }

    public MyEclipseGen() {
        super("MyEclipseGen 6.5 注册机");
        this.setLayout(null);

        title = new JLabel("MyEclipseGen 6.5 注册机");// title 为MyEclipseGen 6.5
                                                    // 注册机
        title.setBounds(120, 20, 150, 30);
        lkey = new JLabel("Key :");
        lkey.setBounds(30, 80, 50, 30);
        keys = new JTextField();
        keys.setBounds(80, 80, 260, 30);
        lvlues = new JLabel("Value :");
        lvlues.setBounds(30, 130, 50, 30);
        vlues = new JTextField();
        vlues.setBounds(80, 130, 260, 30);
        enter = new JButton(" 确 定 ");
        enter.addActionListener(this);
        exit = new JButton(" 退 出 ");
        exit.addActionListener(this);
        enter.setBounds(80, 180, 80, 30);
        exit.setBounds(230, 180, 80, 30);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);// 关闭窗口
        this.setResizable(false);// 最大化
        this.setLocationRelativeTo(null);// 居中
        this.add(title);
        this.add(lkey);
        this.add(keys);
        this.add(lvlues);
        this.add(vlues);
        this.add(enter);
        this.add(exit);
        this.setSize(400, 300);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MyEclipseGen();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enter) {
            if (keys.getText() == null) {
                System.out.println("0--");
                JOptionPane.showMessageDialog(null, "信息不能为空");
            } else {
                String res = this.getSerial(keys.getText(), "5");
                vlues.setText(res);
            }
        }
        if (e.getSource() == exit) {
            int chose = JOptionPane.showConfirmDialog(null, "你真的要退出吗", "系统警告",
                    JOptionPane.ERROR_MESSAGE);// 两个按钮一个确定,一个取消
            if (chose == JOptionPane.YES_OPTION) {
                System.exit(1);
            }
        }
    }
}