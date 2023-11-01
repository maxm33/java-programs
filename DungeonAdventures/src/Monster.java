package src;

import java.io.PrintWriter;

public class Monster {
    public PrintWriter out;
    public int HP, HPCap;

    public Monster(PrintWriter out) {
        this.HP = (int) (Math.random() * 10) + 20; // monsterHP generated between 20 and 30
        this.HPCap = this.HP;
        this.out = out;
    }

    public void Attack(Player player) {
        double failChance = Math.random();
        if (failChance < 0.70) { // monster missing chance is 30%
            int damageReceived = (int) (Math.random() * 3) + 3;
            player.HP -= damageReceived;
            this.out.printf("You received %s damage from Monster\n", damageReceived);
        } else
            this.out.printf("Monster missed!\n");
    }
}
