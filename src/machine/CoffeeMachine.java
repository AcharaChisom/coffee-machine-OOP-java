package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CoffeeMachine coffeeMachine = new CoffeeMachine();
        boolean willContinue;

        System.out.println("Write action (buy, fill, take, remaining, exit):");
        do {
            willContinue = coffeeMachine.handleChoice(scanner.next());
        } while (willContinue);
    }

    private int totalWater = 400;
    private int totalMilk = 540;
    private int totalBeans = 120;
    private int totalDisposableCups = 9;
    private int totalCash = 550;

    private boolean isBuyingCoffee = false;
    private boolean isRefillingWater = false;
    private boolean isRefillingMilk = false;
    private boolean isRefillingBeans = false;
    private boolean isRefillingCups = false;


    public boolean handleChoice(String input) {
        if (this.isBuyingCoffee) {
            this.handleBuyingCoffee(input);
            this.isBuyingCoffee = false;
        } else if (this.isRefillingWater) {
            this.handleFillingWater(input);
            this.isRefillingWater = false;
            this.isRefillingMilk = true;
        } else if (this.isRefillingMilk) {
            this.handleFillingMilk(input);
            this.isRefillingMilk = false;
            this.isRefillingBeans = true;
        } else if (this.isRefillingBeans) {
            this.handleFillingBeans(input);
            this.isRefillingBeans = false;
            this.isRefillingCups = true;
        } else if (this.isRefillingCups) {
            this.handleFillingCups(input);
            this.isRefillingCups = false;
        } else {
            switch (input) {
                case "buy":
                    this.isBuyingCoffee = true;
                    this.initiateBuying();
                    break;
                case "fill":
                    this.isRefillingWater = true;
                    this.initiateFilling();
                    break;
                case "take":
                    this.handleTakingCash();
                    break;
                case "remaining":
                    this.handleRemaining();
                    break;
                case "exit":
                    return false;
            }
        }
        return true;
    }

    private void initiateBuying() {
        System.out.println();
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
    }
    private void handleBuyingCoffee(String input) {
        if (input.equalsIgnoreCase("back")) {
            System.out.println();
            System.out.println("Write action (buy, fill, take, remaining, exit):");
        } else {
            switch (input) {
                case "1":
                    this.handleEspresso();
                    break;
                case "2":
                    this.handleLatte();
                    break;
                case "3":
                    this.handleCappuccino();
                    break;
            }
            System.out.println();
            System.out.println("Write action (buy, fill, take, remaining, exit):");
        }
    }

    private void handleCoffee(int waterAmount, int milkAmount, int beansAmount, int price) {
        boolean hasEnoughWater = this.totalWater >= waterAmount;
        boolean hasEnoughMilk = this.totalMilk >= milkAmount;
        boolean hasEnoughBeans = this.totalBeans >= beansAmount;

        if (hasEnoughWater && hasEnoughMilk && hasEnoughBeans) {
            this.totalWater -= waterAmount;
            this.totalMilk -= milkAmount;
            this.totalBeans -= beansAmount;
            this.totalDisposableCups -= 1;
            this.totalCash += price;
            System.out.println("I have enough resources, making you a coffee!");
        } else {
            if (!hasEnoughWater) {
                System.out.println("Sorry, not enough water!");
            } else if (!hasEnoughMilk) {
                System.out.println("Sorry, not enough milk!");
            } else {
                System.out.println("Sorry, not enough coffee beans!");
            }
        }
    }

    private void handleEspresso() {
        int waterPerEspresso = 250;
        int beansPerEspresso = 16;
        int costPerEspresso = 4;
        this.handleCoffee(waterPerEspresso, 0, beansPerEspresso, costPerEspresso);
    }

    private void handleLatte() {
        int waterPerLatte = 350;
        int milkPerLatte = 75;
        int beansPerLatte = 20;
        int costPerLatte = 7;
        this.handleCoffee(waterPerLatte, milkPerLatte, beansPerLatte, costPerLatte);
    }

    private void handleCappuccino() {
        int waterPerCappuccino = 200;
        int milkPerCappuccino = 100;
        int beansPerCappuccino = 12;
        int costPerCappuccino = 6;
        this.handleCoffee(waterPerCappuccino, milkPerCappuccino, beansPerCappuccino, costPerCappuccino);
    }

    private void initiateFilling() {
        System.out.println();
        System.out.println("Write how many ml of water you want to add:");
    }

    private void handleFillingWater(String input) {
        this.totalWater += Integer.parseInt(input);
        System.out.println("Write how many ml of milk you want to add:");
    }

    private void handleFillingMilk(String input) {
        this.totalMilk += Integer.parseInt(input);
        System.out.println("Write how many grams of coffee beans you want to add:");
    }

    private void handleFillingBeans(String input) {
        this.totalBeans += Integer.parseInt(input);
        System.out.println("Write how many disposable cups you want to add:");
    }

    private void handleFillingCups(String input) {
        this.totalDisposableCups += Integer.parseInt(input);
        System.out.println();
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }

    private void handleTakingCash() {
        System.out.println();
        System.out.printf("I gave you %d", this.totalCash);
        this.totalCash = 0;
        System.out.println();
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }

    private void handleRemaining() {
        System.out.println();
        System.out.printf("""
                The coffee machine has:
                %d ml of water
                %d ml of milk
                %d g of coffee beans
                %d disposable cups
                $%d of money
                """, this.totalWater, this.totalMilk, this.totalBeans, this.totalDisposableCups, this.totalCash);
        System.out.println();
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }
}

























