package src;

import java.io.PrintWriter;

public class Player {
    public PrintWriter out;
    public int HP, HPCap, potions;

    public Player(PrintWriter out) {
        this.HP = (int) (Math.random() * 15) + 10; // playerHP generated between 10 and 25
        this.HPCap = this.HP;
        this.potions = (int) (Math.random() * 4) + 1; // potionNumber generated between 1 and 5
        this.out = out;
    }

    public void Attack(Monster monster) {
        double failChance = Math.random();
        if (failChance < 0.80) { // player missing chance is 20%
            int damageDealt = (int) (Math.random() * 4) + 2;
            monster.HP -= damageDealt;
            this.out.printf("\nYou dealt %s damage to Monster\n", damageDealt);
        } else
            this.out.printf("\nYou missed...\n");
    }
}