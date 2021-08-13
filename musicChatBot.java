import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.lang.Math;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class musicChatBot<link> extends JFrame implements KeyListener {
    JPanel p = new JPanel();
    JTextArea dialogue = new JTextArea(25, 60);
    JTextArea input = new JTextArea(1, 60);
    JScrollPane scroll = new JScrollPane(
            dialogue,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );

    String[][] musicBot = {
            // greetings
            {"hello", "hi", "howdy", "what's up"},
            {"Hey", "Hello", "Hi"},

            //how are you
            {"how are you"},
            {"Good", "Doing Well"},

            //Composers:
            {"tchaikovsky", "tchaik"},
            {"The Seasons: https://www.youtube.com/watch?v=ryxAe4B_3Pg&ab_channel=HALIDONMUSIC",
                    "Piano Concerto No. 1: " + "https://www.youtube.com/watch?v=BWerj8FcprM&ab_channel=TheWickedNorth",
                    "The Sleeping Beauty Waltz: https://www.youtube.com/watch?v=2Sb8WCPjPDs&ab_channel=TheWickedNorth"},

            {"beethoven", "beeth", "beet"},
            {"Sonata Pathetique: https://www.youtube.com/watch?v=SrcOcKYQX3c&t=80s&ab_channel=DavidLongMusic",
                    "Sonata No 17. Mvmt 3: https://www.youtube.com/watch?v=hKkR4YFtyJk&ab_channel=Kassia",
                    "Symphony No. 6 Op. 68: https://www.youtube.com/watch?v=p4CCU2-AFZE&ab_channel=JamisonSanchez"},

            {"mozart"},
            {"Fantasia in D Minor: https://www.youtube.com/watch?v=nNeXg_JQnpA&ab_channel=Kassia",
                    "Symphony no. 25 in G Minor: https://www.youtube.com/watch?v=rNeirjA65Dk&ab_channel=Am4d3usM0z4rt",
                    "Piano Concerto no. 21: https://www.youtube.com/watch?v=i2uYb6bMKyI&ab_channel=Alessandro152"},

            {"bach"},
            {"Prelude and Fugue no. 6 BWV 851: https://www.youtube.com/watch?v=vIPXYK5bR_g&ab_channel=Andr%C3%A1sSchiff-Topic",
                    "Air: https://www.youtube.com/watch?v=rrVDATvUitA&ab_channel=NEBELWARNER",
                    "Cello Suite no. 1: https://www.youtube.com/watch?v=mGQLXRTl3Z0&ab_channel=Stealthbanningandcommentghostingisdespicable"},

            {"chopin"},
            {"Sonata no. 3 mvmt 4: https://www.youtube.com/watch?v=1pRQ4LqTJr4&ab_channel=WQXR",
                    "Etude op. 10 no. 3: https://www.youtube.com/watch?v=bzBH9Nm1BP8&ab_channel=Rousseau",
                    "Nocturne in C Minor, Posthumous: https://www.youtube.com/watch?v=xIqx0MOsNfo&ab_channel=Franciszekj"},

            //default
            {"Try inputting the name of ONE composer to get a listening suggestion.", "Try inputting the name of ONE composer to get a listening suggestion.", "I'm not sure I know that composer. Try another"}
    };

    public musicChatBot() {
        super("Music Chat Bot");
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        dialogue.setEditable(false);
        input.addKeyListener(this);

        p.add(scroll);
        p.add(input);
        p.setBackground(Color.lightGray);
        add(p);

        setVisible(true);
    }

    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            input.setEditable(false);

            String quote = input.getText();
            input.setText("");
            addText("-->You:\t" + quote);
            quote.trim();
            while (
                    (quote.charAt(quote.length() - 1) == '!') ||
                            (quote.charAt(quote.length() - 1) == '.') ||
                            (quote.charAt(quote.length() - 1) == ',') ||
                            (quote.charAt(quote.length() - 1) == '?')
            ) {
                quote = quote.substring(0, quote.length() - 1);
            }
            quote = quote.trim();
            byte response = 0;
            int j = 0;
            while (response == 0) {
                if (inMusicBot(quote.toLowerCase(), musicBot[j * 2])) {
                    response = 2;
                    int r = (int) Math.floor(Math.random() * musicBot[(j * 2) + 1].length);
                    addText("\n-->Music Bot\t" + musicBot[(j * 2) + 1][r]);
                }
                j++;
                if (j * 2 == musicBot.length - 1 && response == 0) {
                    response = 1;
                }
            }
            if (response == 1) {
                int r = (int) Math.floor(Math.random() * musicBot[musicBot.length - 1].length);
                addText("\n-->Music Bot\t" + musicBot[musicBot.length - 1][r]);
            }

            addText("\n");
        }
    }

    public void keyReleased (KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            input.setEditable(true);
        }
    }

    public void keyTyped (KeyEvent e) {}

    public void addText (String str){
        dialogue.setText(dialogue.getText() + str);
    }

    // checks if the user's input is in musicBot array
    public boolean inMusicBot(String in, String[] str) {
        boolean match = false;
        for (int i = 0; i < str.length; i++) {
            if (str[i].equals(in)) {
                match = true;
            }
        }
        return match;
    }

    public static void main(String[] args) {
        new musicChatBot();
    }
}
